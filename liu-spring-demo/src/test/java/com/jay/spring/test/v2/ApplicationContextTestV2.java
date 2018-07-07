package com.jay.spring.test.v2;

import com.jay.spring.context.ApplicationContext;
import com.jay.spring.context.support.ClassPathXmlApplicationContext;
import com.jay.spring.dao.v2.AccountDao;
import com.jay.spring.dao.v2.ItemDao;
import com.jay.spring.service.v2.PetStoreService;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Created by xiang.wei on 2018/6/18
 *
 * @author xiang.wei
 */
public class ApplicationContextTestV2 {
    @Test
    public void testGetBeans() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStoreService");
        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());

        assertTrue(petStoreService.getAccountDao() instanceof AccountDao);
        assertTrue(petStoreService.getItemDao() instanceof ItemDao);

        assertEquals("xiangwei",petStoreService.getOwner());
        assertEquals(2, petStoreService.getVersion());

    }

}
