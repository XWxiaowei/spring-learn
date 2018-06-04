package com.jay.spring.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装可以对属性做些处理
 * @author xiang.wei
 * @create 2018/6/4 12:13
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

    public PropertyValues() {
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}
