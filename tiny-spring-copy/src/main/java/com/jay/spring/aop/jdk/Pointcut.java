package com.jay.spring.aop.jdk;

/**
 * @author xiang.wei
 * @create 2018/6/7 15:30
 */
public interface Pointcut {
    /**
     * 得到匹配的类
     *
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * 得到匹配的方法
     *
     * @return
     */
    MethodMatcher getMethodMather();
}
