package com.jay.spring.test.v1;

import com.jay.spring.Exception.BeanDefinitionException;
import com.jay.spring.service.v1.PetStoreService;
import com.jay.spring.bean.BeanException;
import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.factory.support.DefaultBeanFactory;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.core.io.ClassPathResource;
import com.jay.spring.core.io.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        reader.loadBeanDefinitions(resource);

    }

    @Test
    public void testGetBean() {
        BeanDefinition beanDefinition = factory.getBeanDefinition("petStoreService");

        assertEquals("com.jay.spring.service.v1.PetStoreService", beanDefinition.getBeanClassName());
        assertTrue(beanDefinition.isSingleton());
        assertFalse(beanDefinition.isPrototype());

        assertEquals(BeanDefinition.SCOPE_DEFAULT, beanDefinition.getScope());

        PetStoreService petStoreService = null;
        petStoreService = (PetStoreService) factory.getBean("petStoreService");

        PetStoreService petStoreService1 = (PetStoreService) factory.getBean("petStoreService");
        assertTrue(petStoreService.equals(petStoreService1));

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
            reader.loadBeanDefinitions(resource);
        } catch (BeanDefinitionException e) {
            return;
        }
        Assert.fail("读取xml出错");

    }
}
