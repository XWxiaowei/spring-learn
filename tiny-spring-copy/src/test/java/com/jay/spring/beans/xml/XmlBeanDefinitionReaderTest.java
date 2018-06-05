package com.jay.spring.beans.xml;

import com.jay.spring.beans.BeanDefinition;
import com.jay.spring.beans.io.ResourceLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;


/**
 * Created by xiang.wei on 2018/6/4
 *
 * @author xiang.wei
 */
public class XmlBeanDefinitionReaderTest {
    @Test
    public void loadBeanDefinitions() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        Assert.assertTrue(registry.size() >0);
    }

}