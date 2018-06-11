package com.jay.spring;

import com.jay.spring.Exception.BeanDefinitionException;
import com.jay.spring.Exception.BeanException;
import com.jay.spring.bean.BeanDefinition;
import com.jay.spring.util.ClassUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author xiang.wei
 * @create 2018/6/11 14:40
 */
public class DefaultBeanFactory implements BeanFactory {
    private static String ID_ATTRIBUTE = "id";
    private static String CLASS_ATTRIBUTE = "class";
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

    public DefaultBeanFactory(String configFile) {
        loadBeanDefinition(configFile);
    }

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

    private void loadBeanDefinition(String configFile) {
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
                    beanDefinitionMap.put(id, beanDefinition);
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
