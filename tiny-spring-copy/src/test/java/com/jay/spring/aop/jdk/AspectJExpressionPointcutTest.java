package com.jay.spring.aop.jdk;

import com.jay.spring.HelloWorldService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xiang.wei
 * @create 2018/6/7 15:45
 */
public class AspectJExpressionPointcutTest {
    @Test
    public void testClassFilter() {
        String expression = "execution(* com.jay.spring.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldService.class);
        Assert.assertTrue(matches);

    }

    @Test
    public void testMethodInterceptor() throws NoSuchMethodException {
        String expression = "execution(* com.jay.spring.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMather().matches(HelloWorldService.class, HelloWorldService.class.getDeclaredMethod("helloWorld"));
        Assert.assertTrue(matches);
    }
}