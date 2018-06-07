package com.jay.spring.aop.jdk;

import com.jay.spring.beans.BeanPostProcessor;
import com.jay.spring.beans.factory.AbstractBeanFactory;
import com.jay.spring.beans.factory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author xiang.wei
 * @create 2018/6/7 18:44
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private AbstractBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }

        return null;
    }

}
