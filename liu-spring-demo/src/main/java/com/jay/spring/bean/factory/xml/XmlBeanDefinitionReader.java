package com.jay.spring.bean.factory.xml;

import com.jay.spring.Exception.BeanDefinitionException;
import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.factory.support.BeanDefinitionRegistry;
import com.jay.spring.util.ClassUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by xiang.wei on 2018/6/18
 *
 * @author xiang.wei
 */
public class XmlBeanDefinitionReader {
    private static String ID_ATTRIBUTE = "id";
    private static String CLASS_ATTRIBUTE = "class";
    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }


    public void loadBeanDefinition(String configFile) {
        InputStream inputStream = null;
        try {
            try {
                ClassLoader cl = ClassUtil.getDefaultClassLoader();
                //获取文件输入流
                inputStream = cl.getResourceAsStream(configFile);

                SAXReader saxReader = new SAXReader();
                Document document = saxReader.read(inputStream);

//            获取根节点
                Element rootElement = document.getRootElement();
                Iterator<Element> iterator = rootElement.elementIterator();
                while (iterator.hasNext()) {
                    Element element = iterator.next();
                    String id = element.attributeValue(ID_ATTRIBUTE);
                    String className = element.attributeValue(CLASS_ATTRIBUTE);
                    BeanDefinition beanDefinition = new BeanDefinition(id, className);
                    registry.registerBeanDefinition(id, beanDefinition);
                }
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } catch (Exception e) {
            throw new BeanDefinitionException("读取xml出错");
        }
    }

}
