package com.jay.spring.aop.jdk;

import org.aopalliance.aop.Advice;

/**
 * @author xiang.wei
 * @create 2018/6/7 15:28
 */
public interface Advisor {
    /**
     * 获取通知
     * @return
     */
    Advice getAdvice();
}
