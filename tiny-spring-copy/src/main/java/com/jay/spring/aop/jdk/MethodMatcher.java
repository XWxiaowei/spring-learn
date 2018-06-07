package com.jay.spring.aop.jdk;

import java.lang.reflect.Method;

/**
 * @author xiang.wei
 * @create 2018/6/7 15:26
 */
public interface MethodMatcher {

    /**
     * 查找匹配的类
     * @param targetClass
     * @param method
     * @return
     */
    boolean matches(Class targetClass, Method method);
}
