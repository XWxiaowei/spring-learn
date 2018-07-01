package com.jay.spring.core.io;

import com.jay.spring.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xiang.wei on 2018/6/18
 *
 * @author xiang.wei
 */
public class ClassPathResource implements Resource {
    private String path;
    private ClassLoader classLoader;
    public ClassPathResource(String path) {
        this(path,null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = this.classLoader.getResourceAsStream(this.path);
        if (is == null) {
            throw new FileNotFoundException(path + "cannot be opened");
        }
        return is;
    }

    @Override
    public String getDescription() {
        return this.path;
    }
}
