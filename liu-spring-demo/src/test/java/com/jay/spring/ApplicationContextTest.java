package com.jay.spring;

import com.jay.spring.context.ApplicationContext;
import com.jay.spring.context.support.ClassPathXmlApplicationContext;
import com.jay.spring.context.support.FileSystemXmlApplicationContext;
import com.sun.xml.internal.bind.v2.TODO;
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
    @Test
    public void testGetBeanFileSystemContext() {
//        TODO  1.全路径写死了

        ApplicationContext ctx = new FileSystemXmlApplicationContext("/Volumes/Develop/spring-source/spring-learn/liu-spring-demo/src/test/resources/petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStoreService");
        Assert.assertNotNull(petStoreService);
    }
}
