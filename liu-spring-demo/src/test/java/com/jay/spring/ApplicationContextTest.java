package com.jay.spring;

import com.jay.spring.context.ApplicationContext;
import com.jay.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiang.wei on 2018/6/18
 *
 * @author xiang.wei
 */
public class ApplicationContextTest {
    @Test
    public void testGetBeans() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStoreService");
        Assert.assertNotNull(petStoreService);
    }
}
