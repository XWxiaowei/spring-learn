package com.jay.spring.bean;

import com.jay.spring.bean.factory.BeanFactory;

/**
 * Created by xiang.wei on 2018/6/26
 *
 * @author xiang.wei
 */
public abstract class PropertyValue {
    protected  String name;
    protected  Object value;

    private boolean converted = false;
    private Object convertedValue;


    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public boolean isConverted() {
        return converted;
    }

    public void setConverted(boolean converted) {
        this.converted = converted;
    }

    public abstract Object resolve(BeanFactory beanFactory);

    public synchronized void setConvertedValue(Object value) {
        this.converted = true;
        this.convertedValue = value;
    }

    public synchronized Object getConvertedValue() {
        return this.convertedValue;
    }


}
