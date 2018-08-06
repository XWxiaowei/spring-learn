package com.jay.spring.aop.aspectj;

import com.jay.spring.aop.Advice;
import com.jay.spring.aop.Pointcut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xiang.wei
 * @create 2018/8/6 16:00
 */
public abstract class AbstractAspectJAdvice implements Advice {
    protected Method adviceMethod;
    protected AspectJExpressionPointcut pointcut;
    protected Object adviceObject;

    public AbstractAspectJAdvice(Method adviceMethod,
                                 AspectJExpressionPointcut pointcut,
                                 Object adviceObject) {
        this.adviceMethod = adviceMethod;
        this.pointcut = pointcut;
        this.adviceObject = adviceObject;
    }
    public void invokeAdviceMethod() throws InvocationTargetException, IllegalAccessException {
        adviceMethod.invoke(adviceObject);
    }
    public Pointcut getPointcut(){
        return this.pointcut;
    }
    public Method getAdviceMethod() {
        return adviceMethod;
    }}
