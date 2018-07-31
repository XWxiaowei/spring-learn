package com.jay.spring.aop;

import java.lang.reflect.Method;

/**
 * Created by xiang.wei on 2018/7/30
 *
 * @author xiang.wei
 */
public interface MethodMatcher {
    boolean matches(Method method1);
}
