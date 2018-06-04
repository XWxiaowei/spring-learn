package com.jay.spring.beans.factory;

import com.jay.spring.beans.BeanDefinition;

/**
 * Created by xiang.wei on 2018/6/4
 *
 * @author xiang.wei
 */
public interface BeanFactory {

    /**
     * @param name
     * @return
     * @throws Exception
     */
    Object getBean(String name) throws Exception;

    /**
     * @param name
     * @param beanDefinition
     * @throws Exception
     */
    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;

}
