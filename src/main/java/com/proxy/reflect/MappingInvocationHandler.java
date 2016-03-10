package com.proxy.reflect;

import com.proxy.util.Equality;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * MappingInvocationHandler
 * <p>
 * An InvocationHandler which maps all abstract methods with the annotation MappedMember to their appropriate designated target entity.
 *
 * @author Ian Caffey
 * @since 1.0
 * @see MappedMember
 */
public class MappingInvocationHandler<E> implements InvocationHandler {
    private final E source;
    private final Equality equality;

    /**
     * Constructs an InvocationHandler with a model class to pull data from
     *
     * @param source model class
     * @throws IllegalArgumentException if {@code source}
     *                                  is null.
     */
    public MappingInvocationHandler(E source) {
        this(source, null);
    }

    /**
     * Constructs an InvocationHandler with a model class to pull data from
     *
     * @param source   model class
     * @param equality method invoked for Object#equals(Object)
     * @throws IllegalArgumentException if {@code source}
     *                                  is null.
     */
    public MappingInvocationHandler(E source, Equality equality) {
        if (source == null)
            throw new IllegalArgumentException();
        this.source = source;
        this.equality = equality;
    }

    /**
     * Processes a method invocation on a proxy instance and returns
     * the result.  This method will be invoked on an invocation handler
     * when a method is invoked on a proxy instance that it is
     * associated with. All methods with an Annotation, MappedMember, will
     * be mapped accordingly to their designated target entity.
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
     * @see MappedMember
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(MappedMember.class)) {
            if ("toString".equals(method.getName()) && method.getParameterCount() == 0)
                return Wrapper.class.getCanonicalName() + "[" + source.getClass().getCanonicalName() + "]";
            if ("equals".equals(method.getName()) && method.getParameterCount() == 1 && Object.class.equals(method.getParameterTypes()[0])) {
                if (equality != null)
                    return equality.equals(proxy, args[0]);
                return proxy == args[0];
            }
            throw new MappingException(method);
        }
        MappedMember retriever = method.getAnnotation(MappedMember.class);
        switch (retriever.type()) {
            case METHOD:
                Method m = retriever.declared() ? source.getClass().getDeclaredMethod(retriever.name()) : source.getClass().getMethod(retriever.name());
                m.setAccessible(true);
                return m.invoke((retriever.access() & 0x8) != 0 ? null : source);
            case FIELD:
                Field f = retriever.declared() ? source.getClass().getDeclaredField(retriever.name()) : source.getClass().getField(retriever.name());
                f.setAccessible(true);
                return f.get((retriever.access() & 0x8) != 0 ? null : source);
            default:
                throw new IllegalStateException("Invalid type for ValueRetriever, " + retriever.type());
        }
    }
}
