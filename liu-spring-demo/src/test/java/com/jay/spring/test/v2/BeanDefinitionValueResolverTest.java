package com.jay.spring.test.v2;

import com.jay.spring.bean.factory.support.DefaultBeanFactory;
import com.jay.spring.bean.factory.config.RuntimeBeanReference;
import com.jay.spring.bean.factory.config.TypedStringValue;
import com.jay.spring.bean.factory.support.BeanDefinitionValueResolve;
import com.jay.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.jay.spring.core.io.ClassPathResource;
import com.jay.spring.dao.v2.AccountDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by xiang.wei on 2018/6/26
 *
 * @author xiang.wei
 */
public class BeanDefinitionValueResolverTest {
    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;
    BeanDefinitionValueResolve resolve = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));
        resolve = new BeanDefinitionValueResolve(factory);

    }

    @Test
    public void testResolveRuntimeBeanRefence() {
        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");
        Object value = resolve.resolveValueIfNecessary(reference);
        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof AccountDao);

    }

    @Test
    public void testResolveTypeStringValue() {
        TypedStringValue stringValue = new TypedStringValue("test");
        Object value = resolve.resolveValueIfNecessary(stringValue);
        Assert.assertNotNull(value);
        Assert.assertEquals("test", value);
    }
}
