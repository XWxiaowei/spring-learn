package com.jay.spring.bean;

/**
 * @author xiang.wei
 * @create 2018/6/11 14:46
 */
public class BeanDefinition {
    private String id;
    private String beanClassName;

    public BeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }


}
