package com.jay.spring.aop.cglib;

import com.jay.spring.aop.jdk.AdvisedSupport;
import com.jay.spring.aop.jdk.ReflectiveMethodInvocation;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xiang.wei
 * @create 2018/6/8 10:53
 */
public class Cglib2AopProxy extends AbstractAopProxy {
    public Cglib2AopProxy(AdvisedSupport advised) {
        super(advised);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTargetClass());
        enhancer.setInterfaces(advised.getTargetSource().getInterfaces());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        Object enhanced = enhancer.create();
        return enhanced;
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {
        private AdvisedSupport advised;

        private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;
        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
            this.delegateMethodInterceptor = advised.getMethodInterceptor();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            if (advised.getMethodMatcher() == null
                    || advised.getMethodMatcher().matches(advised.getTargetSource().getTargetClass(),method)) {
                return delegateMethodInterceptor.invoke(new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, methodProxy));
            }
            return new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, methodProxy).proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
        private  MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy) {
            super(target, method, args);
            this.methodProxy = methodProxy;
        }

        public CglibMethodInvocation(Object target, Method method, Object[] args) {
            super(target, method, args);
        }
        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }
    }
}
