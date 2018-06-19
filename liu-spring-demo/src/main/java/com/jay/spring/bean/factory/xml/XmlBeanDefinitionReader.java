package com.jay.spring.bean.factory.xml;

import com.jay.spring.Exception.BeanDefinitionException;
import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.bean.factory.support.BeanDefinitionRegistry;
import com.jay.spring.bean.factory.support.GenericBeanDefinition;
import com.jay.spring.core.io.Resource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by xiang.wei on 2018/6/18
 *
 * @author xiang.wei
 */
public class XmlBeanDefinitionReader {
    private static String ID_ATTRIBUTE = "id";
    private static String CLASS_ATTRIBUTE = "class";
    private static String SCOPE_ATTRIBUTE = "scope";
    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }


    public void loadBeanDefinition(Resource resource) {
        InputStream inputStream = null;
        try {
            try {
                //获取文件输入流
                inputStream = resource.getInputStream();

                SAXReader saxReader = new SAXReader();
                Document document = saxReader.read(inputStream);

//            获取根节点
                Element rootElement = document.getRootElement();
                Iterator<Element> iterator = rootElement.elementIterator();
                while (iterator.hasNext()) {
                    Element element = iterator.next();
                    String id = element.attributeValue(ID_ATTRIBUTE);
                    String className = element.attributeValue(CLASS_ATTRIBUTE);
                    BeanDefinition beanDefinition = new GenericBeanDefinition(id, className);
                    if (element.attribute(SCOPE_ATTRIBUTE) != null) {
                        beanDefinition.setScope(element.attributeValue(SCOPE_ATTRIBUTE));
                    }
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
