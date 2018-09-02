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
    private boolean singleton = true;
    private boolean prototype = false;
    private String scope = SCOPE_DEFAULT;

    private List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

    private ConstructorArgument constructorArgument = new ConstructorArgument();

    public GenericBeanDefinition(String id, String beanClassName) {

        this.id = id;
        this.beanClassName = beanClassName;
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
    public boolean hasConstructorArgumentValues() {
        return !this.constructorArgument.isEmpty();
    }


}
