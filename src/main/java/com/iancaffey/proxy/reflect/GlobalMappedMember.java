package com.iancaffey.proxy.reflect;

/**
 * GlobalMappedMember
 * <p>
 * An object similar to MappedMember in which all data for mapping purposes are stored
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class GlobalMappedMember {
    private final Object model;
    private final Class<?> modelClass;
    private final String wrapperName;
    private final String name;
    private final boolean declared;
    private final int access;
    private final TargetType type;

    /**
     * Constructs a GlobalMappedMember.
     *
     * @param access      determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName used to find the method within the wrapper class
     * @param name        used to find the method within the target class
     * @param type        the type of member to look for in the wrapper class
     * @param modelClass  the target class
     */
    public GlobalMappedMember(int access, String wrapperName, String name, TargetType type, Class<?> modelClass) {
        this(access, wrapperName, name, type, false, modelClass, null);
    }

    /**
     * Constructs a GlobalMappedMember.
     *
     * @param access      determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName used to find the method within the wrapper class
     * @param name        used to find the method within the target class
     * @param type        the type of member to look for in the wrapper class
     * @param model       the instance of the model class, if necessary
     */
    public GlobalMappedMember(int access, String wrapperName, String name, TargetType type, Object model) {
        this(access, wrapperName, name, type, false, model.getClass(), model);
    }

    /**
     * Constructs a GlobalMappedMember.
     *
     * @param access      determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName used to find the method within the wrapper class
     * @param name        used to find the method within the target class
     * @param type        the type of member to look for in the wrapper class
     * @param modelClass  the target class
     * @param model       the instance of the model class, if necessary
     */
    public GlobalMappedMember(int access, String wrapperName, String name, TargetType type, Class<?> modelClass, Object model) {
        this(access, wrapperName, name, type, false, modelClass, model);
    }

    /**
     * Constructs a GlobalMappedMember.
     *
     * @param access      determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName used to find the method within the wrapper class
     * @param name        used to find the method within the target class
     * @param type        the type of member to look for in the wrapper class
     * @param declared    determines the type of method and how to find it within the wrapper class
     * @param modelClass  the target class
     */
    public GlobalMappedMember(int access, String wrapperName, String name, TargetType type, boolean declared, Class<?> modelClass) {
        this(access, wrapperName, name, type, declared, modelClass, null);
    }

    /**
     * Constructs a GlobalMappedMember.
     *
     * @param access      determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName used to find the method within the wrapper class
     * @param name        used to find the method within the target class
     * @param type        the type of member to look for in the wrapper class
     * @param declared    determines the type of method and how to find it within the wrapper class
     * @param model       the instance of the model class, if necessary
     */
    public GlobalMappedMember(int access, String wrapperName, String name, TargetType type, boolean declared, Object model) {
        this(access, wrapperName, name, type, declared, model.getClass(), model);
    }

    /**
     * Constructs a GlobalMappedMember.
     *
     * @param access      determines whether or not the method needs to be called statically or through an object instance
     * @param wrapperName used to find the method within the wrapper class
     * @param name        used to find the method within the target class
     * @param type        the type of member to look for in the wrapper class
     * @param declared    determines the type of method and how to find it within the wrapper class
     * @param modelClass  the target class
     * @param model       the instance of the model class, if necessary
     * @throws IllegalArgumentException if access ==-1, name == null, type == null, modelClass == null, or the access is non-static and the model is null
     */
    public GlobalMappedMember(int access, String wrapperName, String name, TargetType type, boolean declared, Class<?> modelClass, Object model) {
        if (access == -1 || name == null || type == null || modelClass == null || (access & 0x8) == 0 && model == null)
            throw new IllegalArgumentException();
        this.access = access;
        this.wrapperName = wrapperName;
        this.name = name;
        this.type = type;
        this.declared = declared;
        this.modelClass = modelClass;
        this.model = model;
    }

    public Class<?> parent() {
        return modelClass;
    }

    public String wrapper() {
        return wrapperName;
    }

    public String name() {
        return name;
    }

    public boolean declared() {
        return declared;
    }

    public int access() {
        return access;
    }

    public Object model() {
        return model;
    }

    public TargetType type() {
        return type;
    }
}
