package com.jay.spring.aop.cglib;

import com.jay.spring.aop.jdk.AdvisedSupport;
import com.jay.spring.aop.jdk.AopProxy;

/**
 * @author xiang.wei
 * @create 2018/6/8 10:52
 */
public abstract class AbstractAopProxy implements AopProxy {
    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
}
