package com.iancaffey.proxy.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MappedMember
 * <p>
 * An annotation that provides the target data needed to properly map the host method.
 *
 * @author Ian Caffey
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface MappedMember {
    public TargetType type();

    public String name();

    public int access() default 180;

    public boolean declared() default false;
}
