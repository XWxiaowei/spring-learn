package com.jay.spring.bean.factory;


import com.jay.spring.bean.BeansException;

public class BeanDefinitionStoreException extends BeansException {

	public BeanDefinitionStoreException(String msg, Throwable cause) {
		super(msg, cause);
		
	}

    public BeanDefinitionStoreException(String s) {
        super(s);
    }
}
