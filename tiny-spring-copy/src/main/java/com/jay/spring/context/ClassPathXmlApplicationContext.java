package com.jay.spring.context;

import com.jay.spring.beans.BeanDefinition;
import com.jay.spring.beans.factory.AbstractBeanFactory;
import com.jay.spring.beans.factory.AutowireCapableBeanFactory;
import com.jay.spring.beans.io.ResourceLoader;
import com.jay.spring.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author xiang.wei
 * @create 2018/6/5 13:15
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    public String configLocation;

    public ClassPathXmlApplicationContext(String configLoaction) throws Exception {
        this(new AutowireCapableBeanFactory(), configLoaction);
    }

    public ClassPathXmlApplicationContext(AbstractBeanFactory abstractBeanFactory, String configLoaction) throws Exception {
        super(abstractBeanFactory);
        this.configLocation = configLoaction;
        refresh(configLocation);
    }

    @Override
    public void refresh(String location) throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(location);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            abstractBeanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }



}
