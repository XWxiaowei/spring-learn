package com.jay.spring.aop.jdk;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * Created by xiang.wei on 2018/6/7
 *代理相关的元数据
 * @author xiang.wei
 */
public class AdvisedSupport {
    /**
     * 目标对象
     */
    private TargetSource targetSource;
    /**
     *对应AOP中的Advice
     */
    private MethodInterceptor methodInterceptor;
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
