package com.jay.spring;

import com.jay.spring.Exception.BeanDefinitionException;
import com.jay.spring.bean.BeanException;
import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.factory.BeanFactory;
import com.jay.spring.bean.factory.DefaultBeanFactory;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.core.io.ClassPathResource;
import com.jay.spring.core.io.Resource;
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
    Resource resource = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinition(resource);

    }

    @Test
    public void testGetBean() {
        BeanDefinition beanDefinition = factory.getDefinition("petStoreService");

        assertEquals("com.jay.spring.PetStoreService", beanDefinition.getBeanClassName());

        PetStoreService petStoreService = null;
        petStoreService = (PetStoreService) factory.getBean(beanDefinition.getId());
        assertNotNull(petStoreService);

    }

    @Test
    public void testInvalidGetBean() {
        try {
            factory.getBean("invalidBean");
        } catch (BeanException e) {
            return;
        }
        Assert.fail("expect BeanException");
    }

    @Test
    public void testInvalidXml() {
        Resource resource = new ClassPathResource("xxx.xml");

        try {
            reader.loadBeanDefinition(resource);
        } catch (BeanDefinitionException e) {
            return;
        }
        Assert.fail("读取xml出错");

    }
}
