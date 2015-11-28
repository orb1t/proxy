package com.proxy;

import com.proxy.reflect.ConfiguredFactory;
import com.proxy.reflect.MappedClass;
import com.proxy.reflect.MappingInvocationHandler;
import com.proxy.reflect.Wrapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * WrapperFactory
 * <p>
 * A method class providing utility methods that will generate a wrapper class with valid method returns for abstract methods with the Annotation, GlobalMappedMember.
 *
 * @author Ian
 * @version 1.0
 */
public class WrapperFactory {
    /**
     * Creates a proxy class of {@code = wrapperClass} mapping all methods with GlobalMappedMember annotations to the appropriate field or method of the {@code = source}
     *
     * @param wrapperClass    model class to be wrapped and mapped
     * @param source          model of the object holding the data to be retrieved
     * @param parameterTypes  types of the source class constructor parameters
     * @param parameterValues desired values of the source class constructor parameters
     * @return proxy class of the model class with all GlobalMappedMember methods mapped accordingly
     */
    public static <W extends Wrapper<E>, E> W newInstance(Class<W> wrapperClass, Class<E> source, Class<?>[] parameterTypes, Object[] parameterValues) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (wrapperClass == null || source == null || parameterTypes == null || parameterValues == null || parameterTypes.length != parameterValues.length)
            throw new IllegalArgumentException();
        return newInstance(wrapperClass, source.getConstructor(parameterTypes).newInstance(parameterValues));
    }

    /**
     * Creates a proxy class of {@code = wrapperClass} mapping all methods with GlobalMappedMember annotations to the appropriate field or method of the {@code = source}
     *
     * @param wrapperClass model class to be wrapped and mapped
     * @param source       model of the object holding the data to be retrieved
     * @return proxy class of the model class with all GlobalMappedMember methods mapped accordingly
     */
    public static <W extends Wrapper<E>, E> W newInstance(Class<W> wrapperClass, Class<E> source) throws IllegalAccessException, InstantiationException {
        if (wrapperClass == null || source == null)
            throw new IllegalArgumentException();
        return newInstance(wrapperClass, source.newInstance());
    }

    /**
     * Creates a proxy class of {@code = wrapperClass} mapping all methods with GlobalMappedMember annotations to the appropriate field or method of the {@code = source}
     *
     * @param wrapperClass model class to be wrapped and mapped
     * @param source       object holding the data to be retrieved
     * @return proxy class of the model class with all GlobalMappedMember methods mapped accordingly
     */
    public static <W extends Wrapper<E>, E> W newInstance(Class<W> wrapperClass, E source) throws InstantiationException {
        if (wrapperClass == null || source == null)
            throw new IllegalArgumentException();
        return (W) Proxy.newProxyInstance(wrapperClass.getClassLoader(), new Class[]{wrapperClass}, new MappingInvocationHandler<>(source));
    }

    /**
     * Creates a proxy class of {@code = wrapperClass} mapping all methods with GlobalMappedMember annotations to the appropriate field or method of the model class.
     * The wrapper class for this method must have a ClassRetriever annotation present to provide the proper class to instantiate.
     *
     * @param wrapperClass model class to be wrapped and mapped
     * @return proxy class of the model class with all GlobalMappedMember methods mapped accordingly
     */
    public static <W extends Wrapper<E>, E> W newInstance(Class<W> wrapperClass) throws InstantiationException, IllegalAccessException {
        if (wrapperClass == null || !wrapperClass.isAnnotationPresent(MappedClass.class))
            throw new IllegalArgumentException();
        Class<?> model = wrapperClass.getAnnotation(MappedClass.class).target();
        return newInstance(wrapperClass, new MappingInvocationHandler<>(model.newInstance()));
    }

    /**
     * Creates a proxy class of {@code = wrapperClass} using an InvocationHandler to handle all abstract methods.
     * The wrapper class for this method must have a ClassRetriever annotation present to provide the proper class to instantiate.
     *
     * @param wrapperClass model class to be wrapped and mapped
     * @param handler      handler for all abstract methods
     * @return proxy class of the model class with all GlobalMappedMember methods mapped accordingly
     */
    public static <W> W newInstance(Class<W> wrapperClass, InvocationHandler handler) {
        if (handler == null)
            throw new IllegalArgumentException();
        return (W) Proxy.newProxyInstance(wrapperClass.getClassLoader(), new Class[]{wrapperClass}, handler);
    }

    /**
     * Creates a ConfigurationFactory for the {@code = wrapperClass} capable of manually mapping wrapper methods.
     *
     * @param wrapperClass model class to be wrapped and mapped
     * @return ConfigurationFactory prepared for manual wrapper mapping
     */
    public static <W> ConfiguredFactory<W, ?> configuredFactory(Class<W> wrapperClass) {
        return new ConfiguredFactory<>(wrapperClass);
    }
}
