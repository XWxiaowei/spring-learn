package com.jay.spring;

import com.jay.spring.bean.BeanDefinition;

/**
 * @author xiang.wei
 * @create 2018/6/11 14:36
 */
public interface BeanFactory {

    /**
     * @param id
     * @return
     */
    BeanDefinition getDefinition(String id);

    /**
     * 获取service的实例
     * @param id
     * @return
     * @throws ClassNotFoundException
     */
    Object getBean(String id);
}
