package com.jay.spring.bean.factory.annotation;

import com.jay.spring.bean.factory.BeanCreationException;
import com.jay.spring.bean.factory.DefaultBeanFactory;
import com.jay.spring.bean.factory.config.AutowireCapableBeanFactory;
import com.jay.spring.bean.factory.config.DependencyDescriptor;
import com.jay.spring.util.ReflectionUtils;
import com.sun.deploy.util.ReflectionUtil;
import sun.reflect.Reflection;

import java.lang.reflect.Field;

/**
 * Created by xiang.wei on 2018/7/22
 *
 * @author xiang.wei
 */
public class AutowiredFieldElement extends InjectionElement {
    boolean required;

    public AutowiredFieldElement(Field f, boolean required, AutowireCapableBeanFactory factory) {
        super(f,factory);
        this.required = required;
    }
    public Field getField() {
        return (Field) this.member;
    }

    @Override
    public void inject(Object target) {
        Field field = this.getField();
        try {
            DependencyDescriptor desc = new DependencyDescriptor(field, this.required);

            Object value = factory.resolveDependency(desc);
            if (value != null) {
                ReflectionUtils.makeAccessible(field);
                field.set(target, value);
            }
        } catch (Throwable ex) {
            throw new BeanCreationException("Could not autowire field: " + field, ex);
        }

    }
}
