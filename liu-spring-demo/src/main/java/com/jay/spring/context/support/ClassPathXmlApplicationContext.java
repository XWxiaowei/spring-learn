package com.jay.spring.context.support;

import com.jay.spring.core.io.ClassPathResource;
import com.jay.spring.core.io.Resource;

/**
 * Created by xiang.wei on 2018/6/18
 *
 * @author xiang.wei
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {



    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    public Resource getResourceByPath(String configFile) {
        return new ClassPathResource(configFile,this.getBeanClassLoader());
    }
}
