package com.jay.spring.beans;

/**
 * 用于bean属性注入
 * @author xiang.wei
 * @create 2018/6/4 12:12
 */
public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
