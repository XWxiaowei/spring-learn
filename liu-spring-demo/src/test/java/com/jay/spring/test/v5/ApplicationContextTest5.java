package com.jay.spring.test.v5;

import com.jay.spring.context.ApplicationContext;
import com.jay.spring.context.support.ClassPathXmlApplicationContext;
import com.jay.spring.service.v5.PetStoreService;
import com.jay.spring.util.MessageTracker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by xiang.wei on 2018/7/30
 *
 * @author xiang.wei
 */
public class ApplicationContextTest5 {

    @Before
    public void  setUp() {
        MessageTracker.clearMsgs();
    }
    @Test
    public void testPlaceOrder(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v5.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStoreService");

        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());

        petStoreService.placeOrder();
        List<String> msgs = MessageTracker.getMsgs();

        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start tx", msgs.get(0));
        Assert.assertEquals("place order", msgs.get(1));
        Assert.assertEquals("commit tx", msgs.get(2));
    }
}
