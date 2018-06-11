package com.jay.spring;

import com.jay.spring.bean.BeanDefinition;

/**
 * @author xiang.wei
 * @create 2018/6/11 14:36
 */
public interface BeanFactory {

    BeanDefinition getDefinition(String name);

    Object getBean(String name);
}
