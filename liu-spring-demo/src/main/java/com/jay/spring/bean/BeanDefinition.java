package com.jay.spring.bean;

import java.util.List;

/**
 * @author xiang.wei
 * @create 2018/6/11 14:46
 */
public interface BeanDefinition {
    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";
    public static final String SCOPE_DEFAULT = "";

    boolean isSingleton();

    boolean isPrototype();

    String getScope();

    void setScope(String scope);

    String getBeanClassName();

    public List<PropertyValue> getPropertyValues();

    ConstructorArgument getConstructorArgument();

    String getID();

    public boolean hasConstructorArgumentValues();

    public Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException;
    public Class<?> getBeanClass() throws IllegalStateException ;
    public boolean hasBeanClass();

    boolean isSynthetic();

}
