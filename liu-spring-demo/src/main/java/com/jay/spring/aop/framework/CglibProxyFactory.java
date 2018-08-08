package com.jay.spring.aop.framework;

import com.jay.spring.aop.Advice;
import com.jay.spring.util.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiang.wei on 2018/8/8
 *
 * @author xiang.wei
 */
public class CglibProxyFactory implements AopProxyFactory {
    private static final int AOP_PROXY = 0;
    private static final int INVOKE_TARGET = 1;
    private static final int NO_OVERRIDE = 2;
    private static final int DISPATCH_TARGET = 3;
    private static final int DISPATCH_ADVISED = 4;
    private static final int INVOKE_EQUALS = 5;
    private static final int INVOKE_HASHCODE = 6;

    protected static final Log logger = LogFactory.getLog(CglibProxyFactory.class);

    protected final AopConfig config;

    private Object[] constructorArgs;

    private Class<?>[] constructorArgTypes;

    public CglibProxyFactory(AopConfig config) throws AopConfigException {
        Assert.notNull(config, "AdvisedSupport must not be null");
        if (config.getAdvices().size() == 0 /*&& config.getTargetSource() == AdvisedSupport.EMPTY_TARGET_SOURCE*/) {
            throw new AopConfigException("No advisors and no TargetSource specified");
        }
        this.config = config;

    }

     @Override
    public Object getProxy() {
         return getProxy(null);
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        if (logger.isDebugEnabled()) {
            logger.debug("Creating CGLIB proxy: target source is " + this.config.getTargetClass());
        }
        Class<?> rootClass = this.config.getTargetClass();

        Enhancer enhancer = new Enhancer();
        if (classLoader != null) {
            enhancer.setClassLoader(classLoader);
        }
        enhancer.setSuperclass(rootClass);
        enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);//"BySpringCGLIB"

        enhancer.setInterceptDuringConstruction(false);


        Callback[] callbacks = getCallbacks(rootClass);
        Class<?>[] types = new Class<?>[callbacks.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = callbacks[i].getClass();
        }
        enhancer.setCallbackFilter(new ProxyCallbackFilter(this.config));
        enhancer.setCallbackTypes(types);
        enhancer.setCallbacks(callbacks);

        Object proxy = enhancer.create();
        return proxy;
    }

    private Callback[] getCallbacks(Class<?> rooClass) {
        Callback aopInterceptor = new DynamicAdvisedInterceptor(this.config);
        Callback[] callbacks = new Callback[] {
                aopInterceptor,  // AOP_PROXY for normal advice
				/*targetInterceptor,  // INVOKE_TARGET invoke target without considering advice, if optimized
				new SerializableNoOp(),  // NO_OVERRIDE  no override for methods mapped to this
				targetDispatcher,        //DISPATCH_TARGET
				this.advisedDispatcher,  //DISPATCH_ADVISED
				new EqualsInterceptor(this.advised),
				new HashCodeInterceptor(this.advised)*/
        };

        return callbacks;
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor, Serializable {
        private final AopConfig config;

        public DynamicAdvisedInterceptor(AopConfig advised) {
            this.config = advised;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            Object target = this.config.getTargetObject();

            List<Advice> chain = this.config.getAdvices(method);
            Object retVal;

            if (chain.isEmpty() && Modifier.isPublic(method.getModifiers())) {
                retVal = methodProxy.invoke(target, args);
            } else {
                List<org.aopalliance.intercept.MethodInterceptor> interceptors =
                        new ArrayList<org.aopalliance.intercept.MethodInterceptor>();

                interceptors.addAll(chain);


                // We need to create a method invocation...
                retVal = new ReflectiveMethodInvocation(target, method, args, interceptors).proceed();

            }
            return retVal;
        }
    }
    /**
     * CallbackFilter to assign Callbacks to methods.
     */
    private static class ProxyCallbackFilter implements CallbackFilter {

        private final AopConfig config;



        public ProxyCallbackFilter(AopConfig advised) {
            this.config = advised;

        }


        public int accept(Method method) {
            // 注意，这里做了简化
            return AOP_PROXY;

        }

    }

}
