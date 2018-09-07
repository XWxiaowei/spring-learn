package com.jay.spring.bean.factory.annotation;

import com.jay.spring.bean.factory.BeanCreationException;
import com.jay.spring.bean.factory.config.AutowireCapableBeanFactory;
import com.jay.spring.bean.factory.config.DependencyDescriptor;
import com.jay.spring.util.ReflectionUtils;

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
            //获取Bean的实例
            Object value = factory.resolveDependency(desc);
            if (value != null) {
                //将field 置于可访问的
                ReflectionUtils.makeAccessible(field);
                field.set(target, value);
            }
        } catch (Throwable ex) {
            throw new BeanCreationException("Could not autowire field: " + field, ex);
        }

    }
}
