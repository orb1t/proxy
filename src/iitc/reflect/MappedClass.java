package iitc.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MappedClass
 * <p>
 * An annotation that provides the target data needed to properly map the host class.
 *
 * @author Ian
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface MappedClass {
    public Class<?> target();
}
