package com.jay.spring;

import com.jay.spring.beans.BeanDefinition;
import com.jay.spring.beans.PropertyValue;
import com.jay.spring.beans.PropertyValues;
import com.jay.spring.beans.factory.AbstractBeanFactory;
import com.jay.spring.beans.factory.AutowireCapableBeanFactory;
import com.jay.spring.beans.factory.BeanFactory;
import com.jay.spring.beans.io.ResourceLoader;
import com.jay.spring.beans.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

/**
 * Created by xiang.wei on 2018/6/4
 *
 * @author xiang.wei
 *
 */
public class BeanFactoryTest {
    @Test
    public void testLazy() throws Exception {
        // 1.读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");

        // 2.初始化BeanFactory并注册bean
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        // 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
    @Test
    public void testPreInstantiate() throws Exception {
        // 1.读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");

        // 2.初始化BeanFactory并注册bean
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        // 3.初始化bean
        beanFactory.preInstantiateSingletons();

        // 4.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
