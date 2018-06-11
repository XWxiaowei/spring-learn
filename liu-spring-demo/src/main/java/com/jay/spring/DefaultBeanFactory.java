package com.jay.spring;

import com.jay.spring.bean.BeanDefinition;

import java.net.URLClassLoader;

/**
 * @author xiang.wei
 * @create 2018/6/11 14:40
 */
public class DefaultBeanFactory implements BeanFactory {
    public DefaultBeanFactory(String config) {

    }

    @Override
    public BeanDefinition getDefinition(String name) {
        return new BeanDefinition();
    }

    @Override
    public Object getBean(String name) {
        return null;
    }
}
