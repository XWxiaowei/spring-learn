package com.jay.spring.aop.config;

import com.jay.spring.bean.BeansException;
import com.jay.spring.bean.factory.BeanFactory;
import com.jay.spring.bean.factory.BeanFactoryAware;
import com.jay.spring.util.StringUtils;

/**
 * Created by xiang.wei on 2018/8/15
 *
 * @author xiang.wei
 */
public class AspectInstanceFactory implements BeanFactoryAware {

    private String aspectBeanName;

    private BeanFactory beanFactory;


    public void setAspectBeanName(String aspectBeanName) {
        this.aspectBeanName = aspectBeanName;
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        if (!StringUtils.hasText(this.aspectBeanName)) {
            throw new IllegalArgumentException("'aspectBeanName' is required");
        }
    }

    public Object getAspectInstance() {
        return this.beanFactory.getBean(this.aspectBeanName);
    }

}
