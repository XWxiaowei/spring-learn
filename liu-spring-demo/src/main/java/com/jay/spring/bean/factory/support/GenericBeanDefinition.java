package com.jay.spring.bean.factory.support;

import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.ConstructorArgument;
import com.jay.spring.bean.PropertyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiang.wei on 2018/6/20
 *
 * @author xiang.wei
 */
public class GenericBeanDefinition implements BeanDefinition{
    private String id;
    private String beanClassName;
    private Class<?> beanClass;
    private boolean singleton = true;
    private boolean prototype = false;
    private String scope = SCOPE_DEFAULT;


    private List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

    private ConstructorArgument constructorArgument = new ConstructorArgument();

    //表明Bean是不是我们自己合成的。
    private boolean isSynthetic = false;


    public GenericBeanDefinition() {

    }
    public GenericBeanDefinition(String id, String beanClassName) {

        this.id = id;
        this.beanClassName = beanClassName;
    }

    public GenericBeanDefinition(Class<?> clz) {
        this.beanClass = clz;
        this.beanClassName = clz.getName();
    }
    public String getBeanClassName() {
        return this.beanClassName;
    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        return this.propertyValueList;
    }

    @Override
    public ConstructorArgument getConstructorArgument() {
        return this.constructorArgument;
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public boolean hasConstructorArgumentValues() {
        return !this.constructorArgument.isEmpty();
    }

    @Override
    public Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException {
        String className = getBeanClassName();
        if (className == null) {
            return null;
        }
        Class<?> resolvedClass = classLoader.loadClass(className);
        this.beanClass = resolvedClass;
        return resolvedClass;
    }

    @Override
    public Class<?> getBeanClass() throws IllegalStateException {
        if (this.beanClass == null) {
            throw new IllegalStateException("Bean class name[" + this.getBeanClassName() + "] has not beanclass");
        }
        return this.beanClass;
    }

    @Override
    public boolean hasBeanClass() {

        return this.beanClass!=null;
    }

    @Override
    public boolean isSynthetic() {
        return isSynthetic;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSingleton() {
        return this.singleton;
    }
    public boolean isPrototype() {
        return this.prototype;
    }
    public String getScope() {
        return this.scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);

    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public void setSynthetic(boolean synthetic) {
        isSynthetic = synthetic;
    }
}
