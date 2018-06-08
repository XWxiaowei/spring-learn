package com.jay.spring.aop.jdk;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xiang.wei on 2018/6/7
 * 基于JDK的动态代理
 *
 * @author xiang.wei
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    private AdvisedSupport adviceSupport;

    public JdkDynamicAopProxy(AdvisedSupport adviceSupport) {
        this.adviceSupport = adviceSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(), adviceSupport.getTargetSource().getInterfaces(), this);
    }

    /**
     * 反射方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = adviceSupport.getMethodInterceptor();
        //找到匹配的类
        if (adviceSupport.getMethodMatcher() != null
                && adviceSupport.getMethodMatcher().matches(adviceSupport.getTargetSource().getTarget().getClass(), method)) {
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(adviceSupport.getTargetSource().getTarget(), method, args));
        } else {
            return method.invoke(adviceSupport.getTargetSource().getTarget(), args);
        }
    }
}
