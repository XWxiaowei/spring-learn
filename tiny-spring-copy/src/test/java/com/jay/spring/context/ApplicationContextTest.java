package com.jay.spring.context;

import com.jay.spring.HelloWorldService;
import org.junit.Test;

/**
 * @author xiang.wei
 * @create 2018/6/5 13:47
 */
public class ApplicationContextTest {
    @Test
    public void testGetBean() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
