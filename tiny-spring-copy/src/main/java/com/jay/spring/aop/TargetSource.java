package com.jay.spring.aop;

/**
 * Created by xiang.wei on 2018/6/7
 *
 * @author xiang.wei
 */
public class TargetSource {

    /**
     * 目标对象的Class对象
     */
    private Class targetClass;

    private Object target;

    public TargetSource(Class targetClass, Object target) {
        this.targetClass = targetClass;
        this.target = target;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
