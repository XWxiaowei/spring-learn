package com.jay.spring.test.v3;

import com.jay.spring.context.ApplicationContext;
import com.jay.spring.context.support.ClassPathXmlApplicationContext;
import com.jay.spring.service.v3.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiang.wei on 2018/7/7
 *
 * @author xiang.wei
 */
public class ApplicationContextTestV3 {
    @Test
    public void testGetBeans() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStoreService");
        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());

        Assert.assertEquals(1,petStoreService.getVersion());


    }


}
