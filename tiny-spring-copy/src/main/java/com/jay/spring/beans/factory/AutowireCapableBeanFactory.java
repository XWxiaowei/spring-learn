package com.jay.spring.beans.factory;

import com.jay.spring.beans.BeanDefinition;

/**
 * Created by xiang.wei on 2018/6/4
 *
 * @author xiang.wei
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    public void doCreateBean(BeanDefinition beanDefinition) {
        if (beanDefinition == null) {
            return;
        }

    }
}
