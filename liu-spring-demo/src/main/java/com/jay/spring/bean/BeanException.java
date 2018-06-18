package com.jay.spring.bean;

/**
 * @author xiang.wei
 * @create 2018/6/11 16:55
 */
public class BeanException extends RuntimeException {
    public BeanException(String message) {
        super(message);
    }

    public BeanException(String message, Throwable cause) {
        super(message, cause);
    }
}
