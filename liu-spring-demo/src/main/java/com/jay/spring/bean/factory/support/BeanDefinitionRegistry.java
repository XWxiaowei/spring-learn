package com.jay.spring.bean.factory.support;

import com.jay.spring.bean.BeanDefinition;

/**
 * Created by xiang.wei on 2018/6/18
 *
 * @author xiang.wei
 */
public interface BeanDefinitionRegistry {

    /**
     * @param id
     * @return
     */
    BeanDefinition getBeanDefinition(String id);

    void registerBeanDefinition(String beanId, BeanDefinition beanDefinition);

}
