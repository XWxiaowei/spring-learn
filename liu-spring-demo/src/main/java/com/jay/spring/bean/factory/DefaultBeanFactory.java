package com.jay.spring.bean.factory;

import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.BeanException;
import com.jay.spring.bean.factory.support.BeanDefinitionRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiang.wei
 * @create 2018/6/11 14:40
 */
public class DefaultBeanFactory implements BeanFactory,BeanDefinitionRegistry {
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

    @Override
    public BeanDefinition getDefinition(String id) {
        return beanDefinitionMap.get(id);
    }

    @Override
    public Object getBean(String id) {
        BeanDefinition bd = getDefinition(id);
        if (bd == null) {
            throw  new BeanException("BeanDefinition is not exist");
        }
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

}
