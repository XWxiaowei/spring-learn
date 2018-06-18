package com.jay.spring.Exception;

import com.jay.spring.bean.BeanException;

/**
 * 创建Bean出错时抛出的异常
 * @author xiang.wei
 * @create 2018/6/11 17:23
 */
public class BeanCreationException extends BeanException {
    public BeanCreationException(String message) {
        super(message);
    }


    public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
