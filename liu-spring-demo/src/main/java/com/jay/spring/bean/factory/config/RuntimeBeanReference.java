package com.jay.spring.bean.factory.config;

/**
 * Created by xiang.wei on 2018/6/26
 *
 * @author xiang.wei
 */
public class RuntimeBeanReference {
    private final String beanName;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
