package com.jay.spring.bean.factory;

import com.jay.spring.bean.BeanException;

/**
 * 创建Bean出错时抛出的异常
 * @author xiang.wei
 * @create 2018/6/11 17:23
 */
public class BeanCreationException extends BeanException {
    private String beanName;
    public BeanCreationException(String message) {
        super(message);
    }


    public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanCreationException(String msg, String beanName) {
        super("Error creating bean with name '" + beanName + "': " + msg);
        this.beanName = beanName;
    }

    public BeanCreationException(String beanName,String message, Throwable cause) {
        this(beanName, message);
        initCause(cause);
    }

    public String getBeanName() {
        return beanName;
    }
}
