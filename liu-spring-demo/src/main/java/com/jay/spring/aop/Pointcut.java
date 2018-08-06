package com.jay.spring.aop;

/**
 * Created by xiang.wei on 2018/7/30
 *
 * @author xiang.wei
 */
public interface Pointcut {
    MethodMatcher getMethodMatcher();

    String getExpression();
}
