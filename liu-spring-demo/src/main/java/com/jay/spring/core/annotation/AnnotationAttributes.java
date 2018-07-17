package com.jay.spring.core.annotation;

import com.jay.spring.util.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * 本质上是一个HashMap
 * Created by xiang.wei on 2018/7/14
 *
 * @author xiang.wei
 */
public class AnnotationAttributes extends LinkedHashMap<String, Object> {

    public AnnotationAttributes() {

    }

    public AnnotationAttributes(Map<String, Object> map) {
        super(map);
    }

    public AnnotationAttributes(int initialCapacity) {
        super(initialCapacity);
    }

    public String getString(String attributeName) {
        return doGet(attributeName, String.class);
    }

    public String[] getStringArray(String attributeName) {
        return doGet(attributeName, String[].class);
    }

    public boolean getBoolean(String attributeName) {
        return doGet(attributeName, Boolean.class);
    }

    public <N extends Number> N getNumber(String attributeName) {
        return (N) doGet(attributeName, Integer.class);
    }

    public <E extends Enum<?>> E getEnum(String attributeName) {
        return (E) doGet(attributeName, Enum.class);
    }

    public <T> Class<? extends T> getClass(String attributeName) {
        return doGet(attributeName, Class.class);
    }

    public Class<?>[] getClassArray(String attributeName) {
        return doGet(attributeName, Class[].class);
    }


    private <T> T doGet(String attributeName, Class<T> expectedType) {

        Object value = this.get(attributeName);
        Assert.notNull(value, format("Attribute '%s' not found", attributeName));
        return (T) value;
    }

}
