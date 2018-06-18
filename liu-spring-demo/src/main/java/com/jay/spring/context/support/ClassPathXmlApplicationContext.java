package com.jay.spring.context.support;

import com.jay.spring.bean.factory.DefaultBeanFactory;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.context.ApplicationContext;

/**
 * Created by xiang.wei on 2018/6/18
 *
 * @author xiang.wei
 */
public class ClassPathXmlApplicationContext  implements ApplicationContext{
    private DefaultBeanFactory defaultBeanFactory = null;

    public ClassPathXmlApplicationContext(String configFile) {
        defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        reader.loadBeanDefinition(configFile);
    }

    @Override
    public Object getBean(String beanId) {
        return defaultBeanFactory.getBean(beanId);
    }

}
