package com.jay.spring.beans.io;

import java.net.URL;

/**
 * @author xiang.wei
 * @create 2018/6/4 15:36
 */
public class ResourceLoader {
    public Resource getResource(String location) {
        URL url = this.getClass().getResource(location);
        return new UrlResource(url);
    }
}
