package com.jay.spring.test.v4;

import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.factory.support.DefaultBeanFactory;
import com.jay.spring.core.annotation.AnnotationAttributes;
import com.jay.spring.context.annotation.ClassPathBeanDefinitionScanner;
import com.jay.spring.context.annotation.ScannedGenericBeanDefinition;
import com.jay.spring.core.type.AnnotationMetadata;
import org.junit.Assert;
import org.junit.Test;
import com.jay.spring.stereotype.Component;

/**
 * Created by xiang.wei on 2018/7/15
 *
 * @author xiang.wei
 */
public class ClassPathBeanDefinitionScannerTest {

    @Test
    public void testParseScanedBean() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        String basePackages = "com.jay.spring.service.v4,com.jay.spring.dao.v4";

        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);
        scanner.doScan(basePackages);

        String annotation = Component.class.getName();

        {
            BeanDefinition bd = factory.getBeanDefinition("petStoreService");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata amd=sbd.getMetadata();

            Assert.assertTrue(amd.hasAnnotation(annotation));
            AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
            Assert.assertEquals("petStoreService", attributes.get("value"));

        }

        {
            BeanDefinition bd = factory.getBeanDefinition("accountDao");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }
        {
            BeanDefinition bd = factory.getBeanDefinition("itemDao");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }
    }
}
