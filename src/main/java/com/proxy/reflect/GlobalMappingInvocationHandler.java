package com.proxy.reflect;

import com.proxy.util.Equality;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * GlobalMappingInvocationHandler
 * <p>
 * An InvocationHandler which maps all abstract methods, using GlobalMappedMember objects, to their appropriate designated target entity.
 *
 * @author Ian
 * @version 1.0
 */
public class GlobalMappingInvocationHandler implements InvocationHandler {
    private final Map<String, GlobalMappedMember> mapping;
    private final Equality equality;

    /**
     * Constructs an InvocationHandler with EntityMappings to use to map all wrapper methods accordingly
     *
     * @param mappings wrapper methods mappings for fields/methods
     * @throws IllegalArgumentException if {@code source}
     *                                  is null.
     */
    public GlobalMappingInvocationHandler(GlobalMappedMember... mappings) {
        this(null, mappings);
    }

    /**
     * Constructs an InvocationHandler with EntityMappings to use to map all wrapper methods accordingly
     *
     * @param equality method invoked for Object#equals(Object)
     * @param mappings wrapper methods mappings for fields/methods
     * @throws IllegalArgumentException if {@code source}
     *                                  is null.
     */
    public GlobalMappingInvocationHandler(Equality equality, GlobalMappedMember... mappings) {
        if (mappings == null)
            throw new IllegalArgumentException();
        this.equality = equality;
        this.mapping = new HashMap<>();
        for (GlobalMappedMember mapping : mappings)
            this.mapping.put(mapping instanceof GlobalMappedMethod ? key(mapping.wrapper(), ((GlobalMappedMethod) mapping).wrapperParameterTypes()) : mapping.wrapper(), mapping);
    }


    /**
     * Returns an GlobalMappedMember with a wrapper name as specified
     *
     * @param name wrapper method name of the GlobalMappedMember
     * @return GlobalMappedMember with the corresponding name
     */
    public GlobalMappedMember mapping(String name) {
        return mapping.get(name);
    }

    /**
     * Returns a compressed key for a method with the specified parameters
     *
     * @param name           method name
     * @param parameterTypes parameter types of the method
     * @return String key for a method with the given name and parameter types
     */
    public String key(String name, Class<?>... parameterTypes) {
        if (parameterTypes == null)
            return name;
        StringBuilder builder = new StringBuilder(name);
        for (Class<?> type : parameterTypes) {
            builder.append(type.getName().replace('.', '/'));
        }
        return builder.toString();
    }

    /**
     * Processes a method invocation on a proxy instance and returns
     * the result.  This method will be invoked on an invocation handler
     * when a method is invoked on a proxy instance that it is
     * associated with. All GlobalMappedMember will be used to map wrapper
     * methods accordingly to their designated target entity.
     *
     * @param proxy  the proxy instance that the method was invoked on
     * @param method the {@code Method} instance corresponding to
     *               the interface method invoked on the proxy instance.  The declaring
     *               class of the {@code Method} object will be the interface that
     *               the method was declared in, which may be a superinterface of the
     *               proxy interface that the proxy class inherits the method through.
     * @param args   an array of objects containing the values of the
     *               arguments passed in the method invocation on the proxy instance,
     *               or {@code null} if interface method takes no arguments.
     *               Arguments of primitive types are wrapped in instances of the
     *               appropriate primitive wrapper class, such as
     *               {@code java.lang.Integer} or {@code java.lang.Boolean}.
     * @return the value to return from the method invocation on the
     * proxy instance.  If the declared return type of the interface
     * method is a primitive type, then the value returned by
     * this method must be an instance of the corresponding primitive
     * wrapper class; otherwise, it must be a type assignable to the
     * declared return type.  If the value returned by this method is
     * {@code null} and the interface method's return type is
     * primitive, then a {@code NullPointerException} will be
     * thrown by the method invocation on the proxy instance.  If the
     * value returned by this method is otherwise not compatible with
     * the interface method's declared return type as described above,
     * a {@code ClassCastException} will be thrown by the method
     * invocation on the proxy instance.
     * @throws Throwable the exception to throw from the method
     *                   invocation on the proxy instance.  The exception's type must be
     *                   assignable either to any of the exception types declared in the
     *                   {@code throws} clause of the interface method or to the
     *                   unchecked exception types {@code java.lang.RuntimeException}
     *                   or {@code java.lang.Error}.  If a checked exception is
     *                   thrown by this method that is not assignable to any of the
     *                   exception types declared in the {@code throws} clause of
     *                   the interface method, then an
     *                   {@link java.lang.reflect.UndeclaredThrowableException} containing the
     *                   exception that was thrown by this method will be thrown by the
     *                   method invocation on the proxy instance.
     * @see java.lang.reflect.UndeclaredThrowableException
     * @see GlobalMappedMember
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        GlobalMappedMember mapping = mapping(key(method.getName(), method.getParameterTypes()));
        if (mapping == null) {
            if ("toString".equals(method.getName()) && method.getParameterCount() == 0)
                return Wrapper.class.getCanonicalName() + "[DynamicEntityMapping(various target classes possible)]";
            if ("equals".equals(method.getName()) && method.getParameterCount() == 1) {
                if (equality != null)
                    return equality.equals(proxy, args[0]);
                return proxy == args[0];
            }
            return null;
        }
        switch (mapping.type()) {
            case METHOD:
                if (mapping instanceof GlobalMappedMethod) {
                    Class<?>[] parameterTypes = ((GlobalMappedMethod) mapping).parameterTypes();
                    Method m = mapping.declared() ? mapping.parent().getDeclaredMethod(mapping.name(), parameterTypes == null ? method.getParameterTypes() : parameterTypes) : mapping.parent().getMethod(mapping.name(), parameterTypes == null ? method.getParameterTypes() : parameterTypes);
                    m.setAccessible(true);
                    Object[] parameterValues = ((GlobalMappedMethod) mapping).parameterValues();
                    return m.invoke((mapping.access() & 0x8) != 0 ? null : mapping.model(), parameterValues == null ? args : parameterValues);
                } else {
                    Method m = mapping.declared() ? mapping.parent().getDeclaredMethod(mapping.name()) : mapping.parent().getMethod(mapping.name());
                    m.setAccessible(true);
                    return m.invoke((mapping.access() & 0x8) != 0 ? null : mapping.model());
                }
            case FIELD:
                Field f = mapping.declared() ? mapping.parent().getDeclaredField(mapping.name()) : mapping.parent().getField(mapping.name());
                f.setAccessible(true);
                return f.get((mapping.access() & 0x8) != 0 ? null : mapping.model());
            default:
                throw new IllegalStateException("Invalid type for ValueRetriever, " + mapping.type());
        }
    }
}
