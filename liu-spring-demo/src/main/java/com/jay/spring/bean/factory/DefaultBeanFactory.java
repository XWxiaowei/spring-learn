package com.jay.spring.bean.factory;

import com.jay.spring.Exception.BeanCreationException;
import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.BeanException;
import com.jay.spring.bean.PropertyValue;
import com.jay.spring.bean.SimpleTypeCoverter;
import com.jay.spring.bean.factory.config.ConfigurableBeanFactory;
import com.jay.spring.bean.factory.support.BeanDefinitionRegistry;
import com.jay.spring.bean.factory.support.BeanDefinitionValueResolve;
import com.jay.spring.bean.factory.support.DefaultSingletonBeanRegistry;
import com.jay.spring.util.ClassUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiang.wei
 * @create 2018/6/11 14:40
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory, BeanDefinitionRegistry {
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();
    private ClassLoader classLoader;

    @Override
    public BeanDefinition getDefinition(String id) {
        return beanDefinitionMap.get(id);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition bd = getDefinition(beanId);
        if (bd == null) {
            throw new BeanException("BeanDefinition is not exist");
        }
        //TODO ? 会不会线程不安全
        if (bd.isSingleton()) {
            Object bean = this.getSingleton(beanId);
            if (bean == null) {
                bean = createBean(bd);
                this.registerSingleton(beanId, bean);
            }
            return bean;
        }
        return createBean(bd);

    }

    public Object createBean(BeanDefinition bd) {
//        try {
//            return Class.forName(bd.getBeanClassName()).newInstance();
//        } catch (Exception e) {
//            throw new BeanException("bean create exception");
//        }
//        创建实例
        Object bean = instantiateBean(bd);
        //设置属性
        populateBean(bd, bean);
        return bean;
    }

    private Object instantiateBean(BeanDefinition bd) {
        ClassLoader beanClassLoader = this.getBeanClassLoader();
        String beanClassName = bd.getBeanClassName();

        try {
            Class<?> clz = beanClassLoader.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for "+ beanClassName +" failed",e);
        }
    }

    protected void populateBean(BeanDefinition bd, Object bean) {
        List<PropertyValue> pvs = bd.getPropertyValues();
        if (pvs == null || pvs.isEmpty()) {
            return;
        }
        BeanDefinitionValueResolve valueResolve = new BeanDefinitionValueResolve(this);
        SimpleTypeCoverter coverter = new SimpleTypeCoverter();

        try {
            for (PropertyValue pv : pvs) {
                String propertyName = pv.getName();
                Object originalValue = pv.getValue();
                Object resolvedValue = valueResolve.resolveValueIfNecessary(originalValue);

                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor pd : pds) {
                    if (pd.getName().equals(propertyName)) {
                        Object convertedValue = coverter.convertIfNecessary(resolvedValue, pd.getPropertyType());
                        pd.getWriteMethod().invoke(bean, convertedValue);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new BeanCreationException("Failed to obtain BeanInfo for class["+bd.getBeanClassName()+"]",e);
        }

    }

    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanId, beanDefinition);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader();
    }
}
