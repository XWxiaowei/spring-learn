package com.jay.spring.test.v5;

import com.jay.spring.aop.config.AspectInstanceFactory;
import com.jay.spring.bean.factory.BeanFactory;
import com.jay.spring.bean.factory.support.DefaultBeanFactory;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.core.io.ClassPathResource;
import com.jay.spring.tx.TransactionManager;

import java.lang.reflect.Method;

/**
 * Created by xiang.wei on 2018/8/15
 *
 * @author xiang.wei
 */
public class AbstractV5Test {

    protected BeanFactory getBeanFactory(String configFile) {
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        ClassPathResource resource = new ClassPathResource(configFile);
        reader.loadBeanDefinitions(resource);
        return defaultBeanFactory;
    }

    protected Method getAdviceMethod(String methodName) throws NoSuchMethodException {
        return TransactionManager.class.getMethod(methodName);
    }

    protected AspectInstanceFactory getAspectInstanceFactory(String targetBeanName) {
        AspectInstanceFactory factory = new AspectInstanceFactory();
        factory.setAspectBeanName(targetBeanName);
        return factory;
    }
}
