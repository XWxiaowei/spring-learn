package com.jay.spring.bean;

import com.sun.corba.se.impl.io.TypeMismatchException;

import java.beans.PropertyEditor;
import java.util.Map;

/**
 * Created by xiang.wei on 2018/6/27
 *
 * @author xiang.wei
 */
public class SimpleTypeCoverter implements  TypeConverter{
    private Map<Class<?>, PropertyEditor> defaultEditors;

    public SimpleTypeCoverter() {
    }

    @Override
    public <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException {
        return null;
    }

}
