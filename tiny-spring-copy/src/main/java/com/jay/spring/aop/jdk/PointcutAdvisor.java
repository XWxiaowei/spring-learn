package com.jay.spring.aop.jdk;

/**
 * @author xiang.wei
 * @create 2018/6/7 18:51
 */
public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
