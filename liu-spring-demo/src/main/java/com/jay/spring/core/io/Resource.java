package com.jay.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xiang.wei on 2018/6/18
 *
 * @author xiang.wei
 */
public interface Resource {
    /**
     * @return
     */
    InputStream getInputStream() throws IOException;

    String getDescription();
}
