package com.jay.spring;

import com.jay.spring.beans.BeanDefinition;
import com.jay.spring.beans.PropertyValue;
import com.jay.spring.beans.PropertyValues;
import com.jay.spring.beans.factory.AutowireCapableBeanFactory;
import com.jay.spring.beans.factory.BeanFactory;
import org.junit.Test;

/**
 * Created by xiang.wei on 2018/6/4
 *
 * @author xiang.wei
 */
public class BeanFactoryTest2 {
    @Test
    public void test() throws Exception {
        // 1.初始化beanfactory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

// 2.bean定义
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("com.jay.spring.HelloWorldService");
        //        设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("text", "Hello world！"));
        beanDefinition.setPropertyValues(propertyValues);
//        生成bean
        beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

// 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
