package com.jay.spring.util;

/**
 * Created by xiang.wei on 2018/6/19
 *
 * @author xiang.wei
 */
public abstract class Assert {
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

}
