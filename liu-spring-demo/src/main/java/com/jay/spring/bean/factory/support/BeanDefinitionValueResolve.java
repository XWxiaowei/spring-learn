package com.jay.spring.bean.factory.support;

import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.factory.BeanCreationException;
import com.jay.spring.bean.factory.BeanFactory;
import com.jay.spring.bean.factory.FactoryBean;
import com.jay.spring.bean.factory.config.RuntimeBeanReference;
import com.jay.spring.bean.factory.config.TypedStringValue;

/**
 * Created by xiang.wei on 2018/6/26
 *
 * @author xiang.wei
 */
public class BeanDefinitionValueResolve {
    private final AbstractBeanFactory beanFactory;

    public BeanDefinitionValueResolve(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessary(Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;
            String refName = ref.getBeanName();
            Object bean = this.beanFactory.getBean(refName);
            return bean;
        } else if (value instanceof TypedStringValue) {
            return ((TypedStringValue) value).getValue();
        } else if (value instanceof BeanDefinition) {
            BeanDefinition bd = (BeanDefinition) value;
            String innnerBeanName = "(inner bean)" + bd.getBeanClassName() + "#" +
                    Integer.toHexString(System.identityHashCode(bd));
            return resolveInnerBean(innnerBeanName, bd);
        } else {
            return value;
        }

    }
    private Object resolveInnerBean(String innerBeanName,BeanDefinition innerBd) {
        try {
            Object innerBean = this.beanFactory.createBean(innerBd);
            if (innerBean instanceof FactoryBean) {
                try {
                    return ((FactoryBean<?>) innerBean).getObject();
                } catch (Exception e) {
                    throw new BeanCreationException(innerBeanName, "FactoryBean threw exception on object creation", e);
                }
            }
            else{
                return innerBean;
            }
        } catch (BeanCreationException ex) {
            throw new BeanCreationException(
                    innerBeanName,
                    "Cannot create inner bean '" + innerBeanName + "' " +
                            (innerBd != null && innerBd.getBeanClassName() != null ? "of type [" + innerBd.getBeanClassName() + "] " : "")
                    , ex);
        }
    }
}
