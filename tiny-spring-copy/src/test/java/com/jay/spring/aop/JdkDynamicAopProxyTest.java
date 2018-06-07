package com.jay.spring.aop;

import com.jay.spring.HelloWorldService;
import com.jay.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xiang.wei
 * @create 2018/6/7 14:30
 */
public class JdkDynamicAopProxyTest {

    @Test
    public void getProxy() throws Exception {
//        helloWorldService without AOP
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
//       helloWorldService with AOP
//        1.设置被代理对象(Joinpoint)
        AdviceSupport adviceSupport = new AdviceSupport();
        TargetSource targetSource = new TargetSource(HelloWorldService.class, helloWorldService);
        adviceSupport.setTargetSource(targetSource);
//         2.设置拦截器(Advice)
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        adviceSupport.setMethodInterceptor(timerInterceptor);
        // 3.创建代理（Proxy）
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(adviceSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();
        //4.基于Aop的调用
        helloWorldServiceProxy.helloWorld();
    }

    @Test
    public void invoke() {
    }
}