package com.jay.spring.aop.jdk;

import com.jay.spring.beans.factory.BeanFactory;

/**
 * @author xiang.wei
 * @create 2018/6/7 19:07
 */
public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
