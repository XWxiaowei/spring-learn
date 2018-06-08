package com.jay.spring.aop.jdk;

/**
 * @author xiang.wei
 * @create 2018/6/7 15:26
 */
public interface ClassFilter {
    /**
     * 目标类是否匹配
     * @param targetClass
     * @return
     */
    boolean matches(Class targetClass);
}
