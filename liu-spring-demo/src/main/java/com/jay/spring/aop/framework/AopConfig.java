package com.jay.spring.aop.framework;

import com.jay.spring.aop.Advice;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by xiang.wei on 2018/8/8
 *
 * @author xiang.wei
 */
public interface AopConfig {


    Class<?> getTargetClass();

    Object getTargetObject();

    boolean isProxyTargetClass();

    Class<?>[] getProxiedInterfaces();

    boolean isInterfaceProxied(Class<?> intf);

    List<Advice> getAdvices();

    void addAdvice(Advice advice);

    List<Advice> getAdvices(Method method);

    void setTargetObject(Object obj);

 }
