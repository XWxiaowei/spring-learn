package com.jay.spring.bean.factory.support;

import com.jay.spring.bean.factory.DefaultBeanFactory;
import com.jay.spring.bean.factory.config.RuntimeBeanReference;
import com.jay.spring.bean.factory.config.TypedStringValue;

/**
 * Created by xiang.wei on 2018/6/26
 *
 * @author xiang.wei
 */
public class BeanDefinitionValueResolve {
    private final DefaultBeanFactory beanFactory;

    public BeanDefinitionValueResolve(DefaultBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessary(Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;
            String refName = ref.getBeanName();
            Object bean = this.beanFactory.getBean(refName);
            return bean;
        } else if (value instanceof TypedStringValue) {
            return ((TypedStringValue) value).getValue();
        } else {
            throw new RuntimeException("the value " + value + " has not implemented");
        }
    }
}
