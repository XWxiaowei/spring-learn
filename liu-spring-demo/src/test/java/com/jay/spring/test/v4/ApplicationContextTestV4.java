package com.jay.spring.test.v4;

import com.jay.spring.context.ApplicationContext;
import com.jay.spring.context.support.ClassPathXmlApplicationContext;
import com.jay.spring.service.v4.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiang.wei on 2018/7/14
 *
 * @author xiang.wei
 */
public class ApplicationContextTestV4 {
    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v4.xml");
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStoreService");

        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());

    }
}
