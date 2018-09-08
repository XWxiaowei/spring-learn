package com.jay.spring.test.v6;

import com.jay.spring.context.ApplicationContext;
import com.jay.spring.context.support.ClassPathXmlApplicationContext;
import com.jay.spring.service.v6.IPetStoreService;
import com.jay.spring.util.MessageTracker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by xiang.wei on 2018/9/8
 *
 * @author xiang.wei
 */
public class ApplicationContextTest6 {
    @Before
    public void setUp() {
        MessageTracker.clearMsgs();

    }

    @Test
    public void testGetBeanProperty() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v6.xml");
        IPetStoreService petStoreService = (IPetStoreService) applicationContext.getBean("petStoreService");

        petStoreService.placeOrder();

        List<String> msgs = MessageTracker.getMsgs();
        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start tx", msgs.get(0));
        Assert.assertEquals("place order", msgs.get(1));
        Assert.assertEquals("commit tx", msgs.get(2));

    }

}
