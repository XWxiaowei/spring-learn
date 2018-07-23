package com.jay.spring.bean.factory.config;

import com.jay.spring.bean.BeansException;

/**
 * Created by xiang.wei on 2018/7/23
 *
 * @author xiang.wei
 */
public interface BeanPostProcessor {

    Object beforeInitialization(Object bean, String beanName) throws BeansException;

    Object afterInitialization(Object bean, String beanName) throws BeansException;


}
