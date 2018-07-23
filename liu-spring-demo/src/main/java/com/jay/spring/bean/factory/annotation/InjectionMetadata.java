package com.jay.spring.bean.factory.annotation;

import java.util.List;

/**
 * Created by xiang.wei on 2018/7/22
 *
 * @author xiang.wei
 */
public class InjectionMetadata {

    private final Class<?> targetClass;
    private List<InjectionElement> injectionElementList;

    public InjectionMetadata(Class<?> targetClass, List<InjectionElement> injectionElementList) {
        this.targetClass = targetClass;
        this.injectionElementList = injectionElementList;
    }

    public List<InjectionElement> getInjectionElementList() {
        return injectionElementList;
    }

    public void inject(Object target) {
        if (injectionElementList == null || injectionElementList.isEmpty()) {
            return;
        }
        for (InjectionElement ele : injectionElementList) {
            ele.inject(target);
        }
    }
}
