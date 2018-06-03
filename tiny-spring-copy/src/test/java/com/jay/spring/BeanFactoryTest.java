package com.jay.spring;

import com.jay.spring.beans.BeanDefinition;
import com.jay.spring.beans.factory.AutowireCapableBeanFactory;
import com.jay.spring.beans.factory.BeanFactory;

/**
 * Created by xiang.wei on 2018/6/4
 *
 * @author xiang.wei
 */
public class BeanFactoryTest {
    public static void main(String[] args) {
        // 1.初始化beanfactory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

// 2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("com.jay.spring.HelloWorldService");
        beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

// 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
