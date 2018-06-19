package com.jay.spring.bean.factory.config;

/**
 * Created by xiang.wei on 2018/6/20
 *
 * @author xiang.wei
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

}
