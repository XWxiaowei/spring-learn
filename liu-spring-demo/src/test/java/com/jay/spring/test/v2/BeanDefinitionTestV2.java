package com.jay.spring.test.v2;

import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.PropertyValue;
import com.jay.spring.bean.factory.support.DefaultBeanFactory;
import com.jay.spring.bean.factory.config.RuntimeBeanReference;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by xiang.wei on 2018/6/26
 *n
 * @author xiang.wei
 */
public class BeanDefinitionTestV2 {

    @Test
    public void testGetBeanDefinition() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));

        BeanDefinition petStoreService = factory.getBeanDefinition("petStoreService");
        List<PropertyValue> pvs = petStoreService.getPropertyValues();
        Assert.assertTrue(pvs.size() == 4);
        {
            PropertyValue pv = this.getPropertyValue("accountDao", pvs);
            Assert.assertNotNull(pv);
            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
        {
            PropertyValue pv = this.getPropertyValue("itemDao", pvs);
            Assert.assertNotNull(pv);
            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
    }

    private PropertyValue getPropertyValue(String name, List<PropertyValue> pvs) {
        for (PropertyValue pv : pvs) {
            if (pv.getName().equals(name)) {
                return pv;
            }
        }
        return null;
    }

}
