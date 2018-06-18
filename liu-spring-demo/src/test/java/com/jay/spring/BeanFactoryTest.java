package com.jay.spring;

import com.jay.spring.Exception.BeanDefinitionException;
import com.jay.spring.bean.BeanException;
import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.factory.BeanFactory;
import com.jay.spring.bean.factory.DefaultBeanFactory;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit test for simple App.
 */
public class BeanFactoryTest {
    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;
    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);

    }
    @Test
    public void testGetBean() {
        reader.loadBeanDefinition("petstore-v1.xml");
        BeanDefinition beanDefinition = factory.getDefinition("petStoreService");

        assertEquals("com.jay.spring.PetStoreService", beanDefinition.getBeanClassName());

        PetStoreService petStoreService = null;
        petStoreService = (PetStoreService) factory.getBean(beanDefinition.getId());
        assertNotNull(petStoreService);

    }

    @Test
    public void testInvalidGetBean() {
        reader.loadBeanDefinition("petstore-v1.xml");
        try {
            factory.getBean("invalidBean");
        } catch (BeanException e) {
            return;
        }
        Assert.fail("expect BeanException");
    }
    @Test
    public void  testInvalidXml() {
        try {
            reader.loadBeanDefinition("xxx.xml");
        } catch (BeanDefinitionException e) {
            return;
        }
        Assert.fail("读取xml出错");

    }
}
