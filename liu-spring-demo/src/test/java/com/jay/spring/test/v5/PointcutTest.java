package com.jay.spring.test.v5;

import com.jay.spring.aop.MethodMatcher;
import com.jay.spring.aop.aspectj.AspectJExpressionPointcut;
import com.jay.spring.service.v5.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by xiang.wei on 2018/7/30
 *
 * @author xiang.wei
 */
public class PointcutTest {
    @Test
    public void testPointcut() throws NoSuchMethodException {
        String expression = "execution(* com.jay.spring.service.v5.*.placeOrder(..))";

        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);

        MethodMatcher mm = pc.getMethodMatcher();
        {
            Class<?> targetClass = PetStoreService.class;

            Method method1 = targetClass.getMethod("placeOrder");
            Assert.assertTrue(mm.matches(method1));


            Method method2 = targetClass.getMethod("getAccountDao");
            Assert.assertFalse(mm.matches(method2));

        }
        {
            Class<?> targetClass = com.jay.spring.service.v4.PetStoreService.class;
            Method method = targetClass.getMethod("getAccountDao");
            Assert.assertFalse(mm.matches(method));
        }
    }
}
