package com.jay.spring.bean.factory.config;

import java.lang.reflect.Field;

/**
 * 对@Autowired 注解的描述
 * Created by xiang.wei on 2018/7/22
 *
 * @author xiang.wei
 */
public class DependencyDescriptor {
    private Field field;

    private boolean required;

    public DependencyDescriptor(Field field, boolean required) {
        this.field = field;
        this.required = required;
    }
    public Class<?> getDepencyType() {
        if (this.field != null) {
            return field.getType();
        }
        throw new RuntimeException("only support field depency");
    }

    public boolean isRequired() {
        return required;
    }
}
