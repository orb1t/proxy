package com.proxy.reflect;

import jdk.internal.org.objectweb.asm.Opcodes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MappedMember
 * <p>
 * An annotation that provides the target data needed to properly map the host method.
 *
 * @author Ian
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface MappedMember {
    public TargetType type();

    public String name();

    public int access() default Opcodes.GETFIELD;

    public boolean declared() default false;
}
