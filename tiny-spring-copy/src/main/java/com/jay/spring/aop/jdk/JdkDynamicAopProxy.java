package com.jay.spring.aop.jdk;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xiang.wei on 2018/6/7
 *
 * @author xiang.wei
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    private AdviceSupport adviceSupport;

    public JdkDynamicAopProxy(AdviceSupport adviceSupport) {
        this.adviceSupport = adviceSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{adviceSupport.getTargetSource().getTargetClass()}, this);
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
        return methodInterceptor.invoke(new ReflectiveMethodInvocation(adviceSupport.getTargetSource().getTarget(), method, args));
    }
}
