package com.jay.spring.context.support;

import com.jay.spring.bean.factory.DefaultBeanFactory;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.context.ApplicationContext;
import com.jay.spring.core.io.Resource;

/**
 * Created by xiang.wei on 2018/6/19
 *
 * @author xiang.wei
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory defaultBeanFactory = null;

    public AbstractApplicationContext(String configFile) {
        defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        Resource resource = getResourceByPath(configFile);
        reader.loadBeanDefinition(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return defaultBeanFactory.getBean(beanId);
    }

    public abstract Resource getResourceByPath(String configFile);
}
