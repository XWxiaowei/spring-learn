package com.jay.spring.context;

import com.jay.spring.beans.factory.AbstractBeanFactory;

/**
 * @author xiang.wei
 * @create 2018/6/5 13:14
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    protected AbstractBeanFactory abstractBeanFactory;

    public AbstractApplicationContext(AbstractBeanFactory abstractBeanFactory) {
        this.abstractBeanFactory = abstractBeanFactory;
    }

    public abstract void refresh(String location) throws Exception;

    @Override
    public Object getBean(String name) throws Exception {
        return abstractBeanFactory.getBean(name);
    }

}
