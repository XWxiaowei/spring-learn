package com.jay.spring.bean.factory.support;

import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.factory.BeanCreationException;
import com.jay.spring.bean.factory.config.ConfigurableBeanFactory;

/**
 * Created by xiang.wei on 2018/8/19
 *
 * @author xiang.wei
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    protected abstract Object createBean(BeanDefinition bd) throws BeanCreationException;

}
