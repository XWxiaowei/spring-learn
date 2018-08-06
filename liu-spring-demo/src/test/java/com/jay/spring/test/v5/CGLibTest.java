package com.jay.spring.test.v5;

import com.jay.spring.service.v5.PetStoreService;
import com.jay.spring.tx.TransactionManager;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author xiang.wei
 * @create 2018/8/6 18:47
 */
public class CGLibTest {
    @Test
    public void testCallBack() {
        Enhancer enhancer = new Enhancer();
//        设置父类
        enhancer.setSuperclass(PetStoreService.class);
        enhancer.setCallback(new TransactionInterceptor());
//        创建代理
        PetStoreService petStoreService = (PetStoreService) enhancer.create();
        petStoreService.placeOrder();

    }

    public static class TransactionInterceptor implements MethodInterceptor {
        TransactionManager txManager = new TransactionManager();

        @Override

        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            txManager.start();
            Object result = proxy.invokeSuper(obj, args);
            txManager.commit();
            return result;
        }
    }

    @Test
    public void testFilter() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PetStoreService.class);

        enhancer.setInterceptDuringConstruction(false);
        Callback[] callbacks = {new TransactionInterceptor(), NoOp.INSTANCE};
        Class<?>[] types = new Class<?>[callbacks.length];

        for (int i = 0; i < types.length; i++) {
            types[i] = callbacks[i].getClass();
        }

        enhancer.setCallbackFilter(new ProxyCallbackFilter());
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackTypes(types);
    }

    private static class ProxyCallbackFilter implements CallbackFilter {
        public ProxyCallbackFilter() {

        }

        @Override
        public int accept(Method method) {
            if (method.getName().startsWith("place")) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
