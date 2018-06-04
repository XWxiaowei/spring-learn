package com.jay.spring.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiang.wei
 * @create 2018/6/4 15:19
 */
public interface Resource {

    /**
     * 获取文件流
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
