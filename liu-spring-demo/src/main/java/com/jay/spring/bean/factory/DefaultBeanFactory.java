package com.jay.spring.bean.factory;

import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.BeanException;
import com.jay.spring.bean.factory.config.ConfigurableBeanFactory;
import com.jay.spring.bean.factory.support.BeanDefinitionRegistry;
import com.jay.spring.bean.factory.support.DefaultSingletonBeanRegistry;
import com.jay.spring.util.ClassUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiang.wei
 * @create 2018/6/11 14:40
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory,BeanDefinitionRegistry {
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
            throw  new BeanException("BeanDefinition is not exist");
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
        try {
            return Class.forName(bd.getBeanClassName()).newInstance();
        } catch (Exception e) {
            throw new BeanException("bean create exception");
        }
    }

    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanId,beanDefinition);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.classLoader != null ? this.classLoader : ClassUtil.getDefaultClassLoader();
    }
}
