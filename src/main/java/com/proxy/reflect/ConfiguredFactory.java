package com.proxy.reflect;

import com.proxy.WrapperFactory;
import com.proxy.util.Equality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ConfiguredFactory
 * <p>
 * A substitute for using annotation wrapper. Using GlobalMappedMember, fields from the wrapper class was be mapped to their appropriate target field/method.
 * The target object does not have to be set before calling #newInstance as there are model classes for each GlobalMappedMember to pull data from for the
 * InvocationHandler to properly handle the undefined methods.
 *
 * @author Ian
 * @version 1.0
 */
public class ConfiguredFactory<W, E> {
    private final List<GlobalMappedMember> mappings = new ArrayList<>();
    private final Class<W> wrapperClass;
    private Equality equality;

    /**
     * Constructs a ConfiguredFactory with the specified wrapper class for mapping.
     *
     * @param wrapperClass model class to be wrapped and mapped
     * @throws IllegalArgumentException if {@code wrapperClass}
     *                                  is null.
     */
    public ConfiguredFactory(Class<W> wrapperClass) {
        if (wrapperClass == null)
            throw new IllegalArgumentException();
        this.wrapperClass = wrapperClass;
    }

    /**
     * Adds GlobalMappedMember to the list to be used for the InvocationHandler for mapping wrapper methods to target fields/methods.
     *
     * @param mappings EntityMappings to be added for use
     * @return this
     */
    public synchronized ConfiguredFactory<W, E> map(GlobalMappedMember... mappings) {
        if (mappings != null)
            Collections.addAll(this.mappings, mappings);
        return this;
    }

    /**
     * Sets the object which tests equality in Object#equals()
     *
     * @param equality Equality object to test comparison
     * @return this
     */
    public synchronized ConfiguredFactory<W, E> equality(Equality equality) {
        this.equality = equality;
        return this;
    }

    /**
     * Returns the object which tests equality in Object#equals()
     *
     * @return Equality object to test comparison
     */
    public Equality equality() {
        return equality;
    }

    /**
     * Returns all GlobalMappedMember to be used for the InvocationHandler for mapping wrapper methods to target fields/methods.
     *
     * @return EntityMappings to be used for mapping wrapper methods
     */
    public GlobalMappedMember[] mappings() {
        return mappings.toArray(new GlobalMappedMember[mappings.size()]);
    }

    /**
     * Creates a new instance of the wrapper class with all wrapper methods properly mapped to the target fields/methods
     *
     * @return a newly allocated instance of the wrapper class with all wrapper methods properly mapped to the target class
     */
    public final W newInstance() {
        return newInstance(equality());
    }

    /**
     * Creates a new instance of the wrapper class with all wrapper methods properly mapped to the target fields/methods
     * If no Equality object is set, the Object#equals(Object) method of the proxy class simply checks obj1 == obj2.
     *
     * @param equality method invoked for Object#equals(Object)
     * @return a newly allocated instance of the wrapper class with all wrapper methods properly mapped to the target class
     */
    public final W newInstance(Equality equality) {
        return WrapperFactory.newInstance(wrapperClass, new GlobalMappingInvocationHandler(equality, mappings()));
    }
}
