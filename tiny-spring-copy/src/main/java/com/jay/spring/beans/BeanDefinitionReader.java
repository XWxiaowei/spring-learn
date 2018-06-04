package com.jay.spring.beans;

/**
 * Created by xiang.wei on 2018/6/4
 *
 * @author xiang.wei
 */
public interface BeanDefinitionReader {
    /**
     * @param location
     * @throws Exception
     */
    void loadBeanDefinitions(String location) throws Exception;
}
