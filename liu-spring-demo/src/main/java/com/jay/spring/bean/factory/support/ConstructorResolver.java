package com.jay.spring.bean.factory.support;

import com.jay.spring.bean.factory.BeanCreationException;
import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.ConstructorArgument;
import com.jay.spring.bean.SimpleTypeCoverter;
import com.jay.spring.bean.factory.config.ConfigurableBeanFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Created by xiang.wei on 2018/7/7
 *
 * @author xiang.wei
 */
public class ConstructorResolver {
    protected final Log logger = LogFactory.getLog(getClass());

    private final AbstractBeanFactory beanFactory;

    public ConstructorResolver(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object autowireConstructor(final BeanDefinition beanDefinition) {
        Constructor<?> constructorToUse = null;

        Object[] argsToUse = null;

        Class<?> beanClass = null;


        try {
            beanClass = this.beanFactory.getBeanClassLoader().loadClass(beanDefinition.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new BeanCreationException(beanDefinition.getID(), "nstantiation of bean failed, can't resolve class", e);
        }
        Constructor<?>[] candidates = beanClass.getConstructors();
        BeanDefinitionValueResolve valueResolve = new BeanDefinitionValueResolve(this.beanFactory);
        ConstructorArgument cargs = beanDefinition.getConstructorArgument();
        SimpleTypeCoverter typeCoverter = new SimpleTypeCoverter();
        for (int i = 0; i < candidates.length; i++) {

            Class<?>[] parameterTypes = candidates[i].getParameterTypes();
            if (parameterTypes.length != cargs.getArgumentCount()) {
                continue;
            }
            argsToUse = new Object[parameterTypes.length];
            boolean result = this.valuesMatchTypes(parameterTypes,
                    cargs.getArgumentValues(),
                    argsToUse,
                    valueResolve,
                    typeCoverter);
            if (result) {
                constructorToUse = candidates[i];
                break;
            }

        }
        if (constructorToUse == null) {
            throw new BeanCreationException(beanDefinition.getID(), "can't find a apporiate constructor");
        }

        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException(beanDefinition.getID(), "can't find a create instance using " + constructorToUse);
        }
    }

    private boolean valuesMatchTypes(Class<?>[] parameterTypes,
                                     List<ConstructorArgument.ValueHolder> valueHolders,
                                     Object[] argsToUse,
                                     BeanDefinitionValueResolve valueResolve,
                                     SimpleTypeCoverter typeCoverter) {


        for (int i = 0; i < parameterTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder = valueHolders.get(i);
//            获取参数的值，可能是TypedStringValue,也可能是RuntimeBeanReference
            Object originalValue = valueHolder.getValue();
            try {
                //获得真正的值
                Object resolvedValue = valueResolve.resolveValueIfNecessary(originalValue);
//                如果参数类型是int，但是值是字符串，例如"3"，还需要转型
//                如果转型失败，则抛出异常，说明这个构造器不可用
                Object convertedValue = typeCoverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
//                转型成功，记录下来
                argsToUse[i] = convertedValue;

            } catch (Exception e) {
                logger.error(e);
                return false;
            }

        }
        return true;
    }
}
