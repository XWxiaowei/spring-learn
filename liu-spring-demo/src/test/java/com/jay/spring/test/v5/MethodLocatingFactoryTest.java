package com.jay.spring.test.v5;

import com.jay.spring.aop.config.MethodLocatingFactory;
import com.jay.spring.bean.factory.support.DefaultBeanFactory;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.core.io.ClassPathResource;
import com.jay.spring.core.io.Resource;
import com.jay.spring.tx.TransactionManager;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by xiang.wei on 2018/7/31
 *
 * @author xiang.wei
 */
public class MethodLocatingFactoryTest {
    @Test
    public void testGetMethod() throws Exception {
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        Resource resource = new ClassPathResource("petstore-v5.xml");
        reader.loadBeanDefinitions(resource);

        MethodLocatingFactory methodLocatingFactory = new MethodLocatingFactory();
        methodLocatingFactory.setTargetBeanName("tx");
        methodLocatingFactory.setMethodName("start");

        methodLocatingFactory.setBeanFactory(beanFactory);

        Method method = methodLocatingFactory.getMethod();

        Assert.assertTrue(TransactionManager.class.equals(method.getDeclaringClass()));
        Assert.assertTrue(method.equals(TransactionManager.class.getMethod("start")));
    }
}
