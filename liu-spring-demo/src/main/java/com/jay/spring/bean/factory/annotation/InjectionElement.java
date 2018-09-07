package com.jay.spring.bean.factory.annotation;

import com.jay.spring.bean.factory.config.AutowireCapableBeanFactory;

import java.lang.reflect.Member;

/**
 * Created by xiang.wei on 2018/7/22
 *
 * @author xiang.wei
 */
public abstract class InjectionElement {
    // TODO: 2018/9/5  
    protected Member member;
    protected AutowireCapableBeanFactory factory;

    public InjectionElement(Member member, AutowireCapableBeanFactory factory) {
        this.member = member;
        this.factory = factory;
    }

    public abstract void inject(Object object);
}
