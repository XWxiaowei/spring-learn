package com.jay.spring.Exception;

import com.jay.spring.bean.BeanException;

/**
 * 读取xml出错时抛出的异常
 * @author xiang.wei
 * @create 2018/6/11 17:22
 */
public class BeanDefinitionException extends BeanException {

    public BeanDefinitionException(String message) {
        super(message);
    }

    public BeanDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
