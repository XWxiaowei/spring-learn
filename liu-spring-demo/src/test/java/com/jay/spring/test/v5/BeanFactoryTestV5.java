package com.jay.spring.test.v5;

import com.jay.spring.aop.Advice;
import com.jay.spring.aop.aspectj.AspectJAfterReturningAdvice;
import com.jay.spring.aop.aspectj.AspectJAfterThrowingAdvice;
import com.jay.spring.aop.aspectj.AspectJBeforeAdvice;
import com.jay.spring.bean.factory.BeanFactory;
import com.jay.spring.tx.TransactionManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by xiang.wei on 2018/8/16
 *
 * @author xiang.wei
 */
public class BeanFactoryTestV5 extends AbstractV5Test {

    static String expectedExpression = "execution(* com.jay.spring.service.v5.*.placeOrder(..))";
    @Test
    public void  testGetBeanByType() throws NoSuchMethodException {
        BeanFactory factory = super.getBeanFactory("petstore-v5.xml");
        List<Object> advices = factory.getBeansByType(Advice.class);

        Assert.assertEquals(3, advices.size());

        {
            AspectJBeforeAdvice advice = (AspectJBeforeAdvice) this.getAdvice(AspectJBeforeAdvice.class, advices);
            Assert.assertEquals(TransactionManager.class.getMethod("start"), advice.getAdviceMethod());
            Assert.assertEquals(expectedExpression, advice.getPointcut().getExpression());
            Assert.assertEquals(TransactionManager.class, advice.getAdviceInstance().getClass());
        }
        {
            AspectJAfterReturningAdvice advice = (AspectJAfterReturningAdvice)this.getAdvice(AspectJAfterReturningAdvice.class, advices);

            Assert.assertEquals(TransactionManager.class.getMethod("commit"), advice.getAdviceMethod());

            Assert.assertEquals(expectedExpression,advice.getPointcut().getExpression());

            Assert.assertEquals(TransactionManager.class,advice.getAdviceInstance().getClass());

        }

        {
            AspectJAfterThrowingAdvice advice = (AspectJAfterThrowingAdvice)this.getAdvice(AspectJAfterThrowingAdvice.class, advices);

            Assert.assertEquals(TransactionManager.class.getMethod("rollback"), advice.getAdviceMethod());

            Assert.assertEquals(expectedExpression,advice.getPointcut().getExpression());

            Assert.assertEquals(TransactionManager.class,advice.getAdviceInstance().getClass());

        }
    }

    public Object getAdvice(Class<?> type, List<Object> advices) {
        for (Object advice : advices) {
            if (advice.getClass().equals(type)) {
                return advice;
            }
        }
        return null;
    }
}
