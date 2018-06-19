package com.jay.spring.context.support;

import com.jay.spring.core.io.FileSystemResource;
import com.jay.spring.core.io.Resource;

/**
 * Created by xiang.wei on 2018/6/19
 *
 * @author xiang.wei
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {


    public FileSystemXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    public Resource getResourceByPath(String configFile) {
        return new FileSystemResource(configFile);
    }
}
