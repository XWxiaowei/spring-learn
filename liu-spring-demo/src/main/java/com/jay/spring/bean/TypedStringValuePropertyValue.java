package com.jay.spring.bean;

import com.jay.spring.bean.factory.BeanFactory;

/**
 * Created by xiang.wei on 2018/7/8
 *
 * @author xiang.wei
 */
public class TypedStringValuePropertyValue extends PropertyValue {

    public TypedStringValuePropertyValue(String value) {
        this.value = value;
    }

    @Override
    public Object resolve(BeanFactory factory) {
        return value;
    }
}
