package com.jay.spring.test.v4;

import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.factory.support.DefaultBeanFactory;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.context.annotation.ScannedGenericBeanDefinition;
import com.jay.spring.core.annotation.AnnotationAttributes;
import com.jay.spring.core.io.ClassPathResource;
import com.jay.spring.core.io.Resource;
import com.jay.spring.core.type.AnnotationMetadata;
import com.jay.spring.stereotype.Component;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiang.wei on 2018/7/15
 *
 * @author xiang.wei
 */
public class XmlBeanDefinitionReaderTest {
    @Test
    public void  testParseScanedBean() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petstore-v4.xml");

        reader.loadBeanDefinitions(resource);
        String annotation = Component.class.getName();        //下面的代码和ClassPathBeanDefinitionScannerTest重复，该怎么处理？
        {
            BeanDefinition bd = factory.getBeanDefinition("petStoreService");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();


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
