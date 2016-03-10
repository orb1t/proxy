package com.iancaffey.proxy.reflect;

import java.lang.reflect.Method;

/**
 * MappingException
 * <p>
 * An exception thrown when an abstract method of a Wrapper class does not have a MappedMember annotation present,
 * leaving the MappingInvocationHandler unable to return a valid value.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class MappingException extends Exception {
    /**
     * Constructs an Exception with a source method
     *
     * @param method target method
     * @throws IllegalArgumentException if {@code method}
     *                                  is null.
     */
    public MappingException(Method method) {
        super(method + " was not properly mapped to an appropriate method or field.");
    }
}
