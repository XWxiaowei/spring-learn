package com.jay.spring.aop.jdk;

/**
 * Created by xiang.wei on 2018/6/7
 *
 * @author xiang.wei
 */
public class TargetSource {

    /**
     * 目标对象的Class对象
     */
    private Class<?> targetClass;

    private Class<?>[] interfaces;

    private Object target;


    public TargetSource(Object target, Class<?> targetClass,Class<?>... interfaces) {
        this.target = target;
        this.targetClass = targetClass;
        this.interfaces = interfaces;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Class<?>[] interfaces) {
        this.interfaces = interfaces;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
