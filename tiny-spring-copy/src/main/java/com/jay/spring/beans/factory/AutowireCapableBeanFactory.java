package com.jay.spring.beans.factory;

import com.jay.spring.beans.BeanDefinition;

/**
 * Created by xiang.wei on 2018/6/4
 *
 * @author xiang.wei
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    public Object doCreateBean(BeanDefinition beanDefinition) {
        if (beanDefinition == null) {
            return null;
        }
        try {
            Class beanClass = Class.forName(beanDefinition.getBeanClassName());
            try {
                Object bean = beanClass.newInstance();
                return bean;

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
}
