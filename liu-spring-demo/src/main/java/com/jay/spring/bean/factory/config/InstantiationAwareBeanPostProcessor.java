package com.jay.spring.bean.factory.config;


import com.jay.spring.bean.BeansException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

	Object beforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

	boolean afterInstantiation(Object bean, String beanName) throws BeansException;

	void postProcessPropertyValues(Object bean, String beanName)
			throws BeansException;

}
