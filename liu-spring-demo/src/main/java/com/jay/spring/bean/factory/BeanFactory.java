package com.jay.spring.bean.factory;

import java.util.List;

/**
 * @author xiang.wei
 * @create 2018/6/11 14:36
 */
public interface BeanFactory {

    /**
     * 获取service的实例
     * @param id
     * @return
     * @throws ClassNotFoundException
     */
    Object getBean(String id);

    Class<?> getType(String name) throws NoSuchBeanDefinitionException;

    List<Object> getBeansByType(Class<?> type);


}
