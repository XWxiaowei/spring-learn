package com.jay.spring.bean.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

/**
 * Created by xiang.wei on 2018/6/27
 *
 * @author xiang.wei
 */
public class CustomNumberEditor extends PropertyEditorSupport {
    private final Class<? extends Number> numberClass;
    private final boolean allowEmpty;
    private final NumberFormat numberFormat;

    public CustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty) {
        this(numberClass, allowEmpty,null);
    }

    public CustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty, NumberFormat numberFormat) {
        this.numberClass = numberClass;
        this.allowEmpty = allowEmpty;
        this.numberFormat = numberFormat;
    }

}
