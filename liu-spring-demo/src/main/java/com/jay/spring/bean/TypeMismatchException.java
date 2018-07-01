package com.jay.spring.bean;

/**
 * Created by xiang.wei on 2018/6/30
 *
 * @author xiang.wei
 */
public class TypeMismatchException extends BeansException {
    private transient Object value;
    private Class<?> requiredType;

    public TypeMismatchException(Object value, Class<?> requiredType) {
        super("Failed to convert value:" + value + "to type" + requiredType);
        this.value = value;
        this.requiredType = requiredType;

    }

    public TypeMismatchException(String msg) {
        super(msg);
    }

    public TypeMismatchException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getRequiredType() {
        return requiredType;
    }
}
