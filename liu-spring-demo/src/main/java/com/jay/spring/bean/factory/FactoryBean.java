package com.jay.spring.bean.factory;

/**
 * Created by xiang.wei on 2018/8/15
 *
 * @author xiang.wei
 */
public interface FactoryBean<T> {


    T getObject() throws Exception;

    Class<?> getObjectType();
}
