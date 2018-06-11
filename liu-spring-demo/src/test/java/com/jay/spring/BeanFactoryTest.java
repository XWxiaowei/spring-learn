package com.jay.spring;

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
        assertEquals("com.jay.spring.petStoreService", beanDefinition.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) factory.getBean("petStoreService");
        assertNotNull(petStoreService);

    }
}
