package com.jay.spring;

import com.jay.spring.Exception.BeanDefinitionException;
import com.jay.spring.Exception.BeanException;
import com.jay.spring.bean.BeanDefinition;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit test for simple App.
 */
public class BeanFactoryTest {
    @Test
    public void testGetBean() {
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        BeanDefinition beanDefinition = factory.getDefinition("petStoreService");
        assertEquals("com.jay.spring.PetStoreService", beanDefinition.getBeanClassName());

        PetStoreService petStoreService = null;
        petStoreService = (PetStoreService) factory.getBean(beanDefinition.getId());
        assertNotNull(petStoreService);

    }

    @Test
    public void testInvalidGetBean() {
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
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
            new DefaultBeanFactory("xxx.xml");
        } catch (BeanDefinitionException e) {
            return;
        }
        Assert.fail("读取xml出错");

    }
}
