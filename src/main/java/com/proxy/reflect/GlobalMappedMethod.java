package com.proxy.reflect;

import java.util.Arrays;

/**
 * GlobalMappedMethod
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class GlobalMappedMethod extends GlobalMappedMember {
    private final Class<?>[] wrapperParameterTypes;
    private final Class<?>[] parameterTypes;
    private final Object[] parameterValues;

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access                determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName           used to find the method within the wrapper class
     * @param name                  used to find the method within the target class
     * @param declared              determines the type of method and how to find it within the wrapper class
     * @param modelClass            the target class
     * @param model                 the instance of the model class, if necessary
     * @param wrapperParameterTypes parameter types of the wrapper method being mapped
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, boolean declared, Class<?> modelClass, Object model, Class<?>... wrapperParameterTypes) {
        this(access, wrapperName, name, declared, modelClass, model, wrapperParameterTypes, null, null);
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access                determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName           used to find the method within the wrapper class
     * @param name                  used to find the method within the target class
     * @param modelClass            the target class
     * @param wrapperParameterTypes parameter types of the wrapper method being mapped
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, Class<?> modelClass, Class<?>... wrapperParameterTypes) {
        this(access, wrapperName, name, modelClass, wrapperParameterTypes, null, null);
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access                determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName           used to find the method within the wrapper class
     * @param name                  used to find the method within the target class
     * @param model                 the instance of the model class, if necessary
     * @param wrapperParameterTypes parameter types of the wrapper method being mapped
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, Object model, Class<?>... wrapperParameterTypes) {
        this(access, wrapperName, name, model, wrapperParameterTypes, null, null);
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access                determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName           used to find the method within the wrapper class
     * @param name                  used to find the method within the target class
     * @param modelClass            the target class
     * @param model                 the instance of the model class, if necessary
     * @param wrapperParameterTypes parameter types of the wrapper method being mapped
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, Class<?> modelClass, Object model, Class<?>... wrapperParameterTypes) {
        this(access, wrapperName, name, modelClass, model, wrapperParameterTypes, null, null);
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access                determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName           used to find the method within the wrapper class
     * @param name                  used to find the method within the target class
     * @param declared              determines the type of method and how to find it within the wrapper class
     * @param modelClass            the target class
     * @param wrapperParameterTypes parameter types of the wrapper method being mapped
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, boolean declared, Class<?> modelClass, Class<?>... wrapperParameterTypes) {
        this(access, wrapperName, name, declared, modelClass, wrapperParameterTypes, null, null);
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access                determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName           used to find the method within the wrapper class
     * @param name                  used to find the method within the target class
     * @param declared              determines the type of method and how to find it within the wrapper class
     * @param model                 the instance of the model class, if necessary
     * @param wrapperParameterTypes parameter types of the wrapper method being mapped
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, boolean declared, Object model, Class<?>... wrapperParameterTypes) {
        this(access, wrapperName, name, declared, model, wrapperParameterTypes, null, null);
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access          determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName     used to find the method within the wrapper class
     * @param name            used to find the method within the target class
     * @param modelClass      the target class
     * @param parameterTypes  types of the target method being mapped to, if null, the original types from the wrapper method will be passed
     * @param parameterValues values to be passed to the target method being mapped to, if null, the original values from the wrapper method will be passed
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, Class<?> modelClass, Class<?>[] parameterTypes, Object[] parameterValues) {
        this(access, wrapperName, name, modelClass, null, parameterTypes, parameterValues);
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access          determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName     used to find the method within the wrapper class
     * @param name            used to find the method within the target class
     * @param model           the instance of the model class, if necessary
     * @param parameterTypes  types of the target method being mapped to, if null, the original types from the wrapper method will be passed
     * @param parameterValues values to be passed to the target method being mapped to, if null, the original values from the wrapper method will be passed
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, Object model, Class<?>[] parameterTypes, Object[] parameterValues) {
        this(access, wrapperName, name, model, null, parameterTypes, parameterValues);
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access          determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName     used to find the method within the wrapper class
     * @param name            used to find the method within the target class
     * @param modelClass      the target class
     * @param model           the instance of the model class, if necessary
     * @param parameterTypes  types of the target method being mapped to, if null, the original types from the wrapper method will be passed
     * @param parameterValues values to be passed to the target method being mapped to, if null, the original values from the wrapper method will be passed
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, Class<?> modelClass, Object model, Class<?>[] parameterTypes, Object[] parameterValues) {
        this(access, wrapperName, name, modelClass, model, null, parameterTypes, parameterValues);
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access          determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName     used to find the method within the wrapper class
     * @param name            used to find the method within the target class
     * @param declared        determines the type of method and how to find it within the wrapper class
     * @param modelClass      the target class
     * @param parameterTypes  types of the target method being mapped to, if null, the original types from the wrapper method will be passed
     * @param parameterValues values to be passed to the target method being mapped to, if null, the original values from the wrapper method will be passed
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, boolean declared, Class<?> modelClass, Class<?>[] parameterTypes, Object[] parameterValues) {
        this(access, wrapperName, name, declared, modelClass, null, parameterTypes, parameterValues);
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access          determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName     used to find the method within the wrapper class
     * @param name            used to find the method within the target class
     * @param declared        determines the type of method and how to find it within the wrapper class
     * @param model           the instance of the model class, if necessary
     * @param parameterTypes  types of the target method being mapped to, if null, the original types from the wrapper method will be passed
     * @param parameterValues values to be passed to the target method being mapped to, if null, the original values from the wrapper method will be passed
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, boolean declared, Object model, Class<?>[] parameterTypes, Object[] parameterValues) {
        this(access, wrapperName, name, declared, model, null, parameterTypes, parameterValues);
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access          determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName     used to find the method within the wrapper class
     * @param name            used to find the method within the target class
     * @param declared        determines the type of method and how to find it within the wrapper class
     * @param modelClass      the target class
     * @param model           the instance of the model class, if necessary
     * @param parameterTypes  types of the target method being mapped to, if null, the original types from the wrapper method will be passed
     * @param parameterValues values to be passed to the target method being mapped to, if null, the original values from the wrapper method will be passed
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, boolean declared, Class<?> modelClass, Object model, Class<?>[] parameterTypes, Object[] parameterValues) {
        this(access, wrapperName, name, declared, modelClass, model, null, parameterTypes, parameterValues);
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access                determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName           used to find the method within the wrapper class
     * @param name                  used to find the method within the target class
     * @param modelClass            the target class
     * @param wrapperParameterTypes parameter types of the wrapper method being mapped
     * @param parameterTypes        types of the target method being mapped to, if null, the original types from the wrapper method will be passed
     * @param parameterValues       values to be passed to the target method being mapped to, if null, the original values from the wrapper method will be passed
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, Class<?> modelClass, Class<?>[] wrapperParameterTypes, Class<?>[] parameterTypes, Object[] parameterValues) {
        super(access, wrapperName, name, TargetType.METHOD, modelClass);
        this.wrapperParameterTypes = wrapperParameterTypes;
        this.parameterTypes = parameterTypes;
        this.parameterValues = parameterValues;
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access                determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName           used to find the method within the wrapper class
     * @param name                  used to find the method within the target class
     * @param model                 the instance of the model class, if necessary
     * @param wrapperParameterTypes parameter types of the wrapper method being mapped
     * @param parameterTypes        types of the target method being mapped to, if null, the original types from the wrapper method will be passed
     * @param parameterValues       values to be passed to the target method being mapped to, if null, the original values from the wrapper method will be passed
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, Object model, Class<?>[] wrapperParameterTypes, Class<?>[] parameterTypes, Object[] parameterValues) {
        super(access, wrapperName, name, TargetType.METHOD, model);
        this.wrapperParameterTypes = wrapperParameterTypes;
        this.parameterTypes = parameterTypes;
        this.parameterValues = parameterValues;
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access                determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName           used to find the method within the wrapper class
     * @param name                  used to find the method within the target class
     * @param modelClass            the target class
     * @param model                 the instance of the model class, if necessary
     * @param wrapperParameterTypes parameter types of the wrapper method being mapped
     * @param parameterTypes        types of the target method being mapped to, if null, the original types from the wrapper method will be passed
     * @param parameterValues       values to be passed to the target method being mapped to, if null, the original values from the wrapper method will be passed
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, Class<?> modelClass, Object model, Class<?>[] wrapperParameterTypes, Class<?>[] parameterTypes, Object[] parameterValues) {
        super(access, wrapperName, name, TargetType.METHOD, modelClass, model);
        this.wrapperParameterTypes = wrapperParameterTypes;
        this.parameterTypes = parameterTypes;
        this.parameterValues = parameterValues;
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access                determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName           used to find the method within the wrapper class
     * @param name                  used to find the method within the target class
     * @param declared              determines the type of method and how to find it within the wrapper class
     * @param modelClass            the target class
     * @param wrapperParameterTypes parameter types of the wrapper method being mapped
     * @param parameterTypes        types of the target method being mapped to, if null, the original types from the wrapper method will be passed
     * @param parameterValues       values to be passed to the target method being mapped to, if null, the original values from the wrapper method will be passed
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, boolean declared, Class<?> modelClass, Class<?>[] wrapperParameterTypes, Class<?>[] parameterTypes, Object[] parameterValues) {
        super(access, wrapperName, name, TargetType.METHOD, declared, modelClass);
        this.wrapperParameterTypes = wrapperParameterTypes;
        this.parameterTypes = parameterTypes;
        this.parameterValues = parameterValues;
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access                determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName           used to find the method within the wrapper class
     * @param name                  used to find the method within the target class
     * @param declared              determines the type of method and how to find it within the wrapper class
     * @param model                 the instance of the model class
     * @param wrapperParameterTypes parameter types of the wrapper method being mapped
     * @param parameterTypes        types of the target method being mapped to, if null, the original types from the wrapper method will be passed
     * @param parameterValues       values to be passed to the target method being mapped to, if null, the original values from the wrapper method will be passed
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, boolean declared, Object model, Class<?>[] wrapperParameterTypes, Class<?>[] parameterTypes, Object[] parameterValues) {
        super(access, wrapperName, name, TargetType.METHOD, declared, model);
        this.wrapperParameterTypes = wrapperParameterTypes;
        this.parameterTypes = parameterTypes;
        this.parameterValues = parameterValues;
    }

    /**
     * Constructs a GlobalMappedMethod.
     *
     * @param access                determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName           used to find the method within the wrapper class
     * @param name                  used to find the method within the target class
     * @param declared              determines the type of method and how to find it within the wrapper class
     * @param modelClass            the target class
     * @param model                 the instance of the model class, if necessary
     * @param wrapperParameterTypes parameter types of the wrapper method being mapped
     * @param parameterTypes        types of the target method being mapped to, if null, the original types from the wrapper method will be passed
     * @param parameterValues       values to be passed to the target method being mapped to, if null, the original values from the wrapper method will be passed
     */
    public GlobalMappedMethod(int access, String wrapperName, String name, boolean declared, Class<?> modelClass, Object model, Class<?>[] wrapperParameterTypes, Class<?>[] parameterTypes, Object[] parameterValues) {
        super(access, wrapperName, name, TargetType.METHOD, declared, modelClass, model);
        this.wrapperParameterTypes = wrapperParameterTypes;
        this.parameterTypes = parameterTypes;
        this.parameterValues = parameterValues;
    }

    public Class<?>[] wrapperParameterTypes() {
        return wrapperParameterTypes == null ? null : Arrays.copyOf(wrapperParameterTypes, wrapperParameterTypes.length);
    }

    public Class<?>[] parameterTypes() {
        return parameterTypes == null ? null : Arrays.copyOf(parameterTypes, parameterTypes.length);
    }

    public Object[] parameterValues() {
        return parameterValues == null ? null : Arrays.copyOf(parameterValues, parameterValues.length);
    }
}
