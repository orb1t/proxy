package com.iancaffey.proxy.reflect;

/**
 * Wrapper
 * <p>
 * A marker interface for a class that could be dynamically instantiated using reflection, mapping methods annotated by MappedMember to designated methods/fields.
 * E represents the type of model class in which the Wrapper grabs value from.
 * While not completely needed, it's provides strict requirements for passed objects/classes to WrapperFactory methods.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public interface Wrapper<E> {
}
