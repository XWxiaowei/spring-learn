package com.jay.spring.beans.factory;

import com.jay.spring.beans.BeanDefinition;
import com.jay.spring.beans.BeanReference;
import com.jay.spring.beans.PropertyValue;

import java.lang.reflect.Field;

/**
 * Created by xiang.wei on 2018/6/4
 *
 * @author xiang.wei
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    public Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        if (beanDefinition == null) {
            return null;
        }
        Class beanClass = Class.forName(beanDefinition.getBeanClassName());
        Object bean = beanClass.newInstance();
        return bean;
    }

    /**
     *
     * @param bean
     * @param mbd
     * @throws Exception
     */
    protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
        for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValueList()) {
            Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
            declaredField.setAccessible(true);
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }
            declaredField.set(bean, value);
        }
    }
}
