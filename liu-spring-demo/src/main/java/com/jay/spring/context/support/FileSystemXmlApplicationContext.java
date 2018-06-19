package com.jay.spring.context.support;

import com.jay.spring.bean.factory.DefaultBeanFactory;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.context.ApplicationContext;
import com.jay.spring.core.io.FileSystemResource;
import com.jay.spring.core.io.Resource;

/**
 * Created by xiang.wei on 2018/6/19
 *
 * @author xiang.wei
 */
public class FileSystemXmlApplicationContext implements ApplicationContext {


    private DefaultBeanFactory defaultBeanFactory = null;

    public FileSystemXmlApplicationContext(String configFile) {
        defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        Resource resource = new FileSystemResource(configFile);
        reader.loadBeanDefinition(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return defaultBeanFactory.getBean(beanId);
    }
}
