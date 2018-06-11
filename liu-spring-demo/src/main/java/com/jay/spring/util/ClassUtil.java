package com.jay.spring.util;

/**
 * @author xiang.wei
 * @create 2018/6/11 15:26
 */
public class ClassUtil {
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader c1 = null;
        try {
            c1 = Thread.currentThread().getContextClassLoader();
            if (c1 == null) {
                c1 = ClassLoader.getSystemClassLoader();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c1;
    }
}
