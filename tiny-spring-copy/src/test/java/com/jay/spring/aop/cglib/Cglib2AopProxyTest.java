package com.jay.spring.aop.cglib;

import com.jay.spring.HelloWorldService;
import com.jay.spring.HelloWorldServiceImpl;
import com.jay.spring.aop.TimerInterceptor;
import com.jay.spring.aop.jdk.AdvisedSupport;
import com.jay.spring.aop.jdk.TargetSource;
import com.jay.spring.context.ApplicationContext;
import com.jay.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author xiang.wei
 * @create 2018/6/8 11:24
 */
public class Cglib2AopProxyTest {
    @Test
    public void testInterceptor() throws Exception {
        // --------- helloWorldService without AOP
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();

        // --------- helloWorldService with AOP
        // 1. 设置被代理对象(Joinpoint)
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class,
                HelloWorldService.class);
        advisedSupport.setTargetSource(targetSource);

        // 2. 设置拦截器(Advice)
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);

        // 3. 创建代理(Proxy)
        Cglib2AopProxy cglib2AopProxy = new Cglib2AopProxy(advisedSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService) cglib2AopProxy.getProxy();

        // 4. 基于AOP的调用
        helloWorldServiceProxy.helloWorld();

    }
}