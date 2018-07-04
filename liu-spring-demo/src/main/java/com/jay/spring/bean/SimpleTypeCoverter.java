package com.jay.spring.bean;

import com.jay.spring.bean.propertyeditors.CustomBooleanEditor;
import com.jay.spring.bean.propertyeditors.CustomNumberEditor;
import com.jay.spring.util.ClassUtils;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiang.wei on 2018/6/27
 *
 * @author xiang.wei
 */
public class SimpleTypeCoverter implements TypeConverter {
    private Map<Class<?>, PropertyEditor> defaultEditors;

    public SimpleTypeCoverter() {
    }

    @Override
    public <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException {
        if (ClassUtils.isAssignableValue(requiredType, value)) {
            return (T) value;
        } else {
            if (value instanceof String) {
                PropertyEditor defaultEditor = findDefaultEditor(requiredType);
                try {
                    defaultEditor.setAsText((String) value);
                } catch (IllegalArgumentException e) {
                    throw new TypeMismatchException(value, requiredType);
                }
                return (T) defaultEditor.getValue();
            } else {
                throw new RuntimeException("Todo : can't convert value for "+value +" class:"+requiredType);
            }

        }
    }

    private PropertyEditor findDefaultEditor(Class<?> requiredType) {
        PropertyEditor defaultEditor = this.getDefaultEditor(requiredType);
        if (defaultEditor == null) {
            throw new RuntimeException("Editor for" + requiredType + "has not been implemented");
        }
        return defaultEditor;
    }

    public PropertyEditor getDefaultEditor(Class<?> requiredType) {
        if (defaultEditors == null) {
            createDefaultEditors();
        }
        return defaultEditors.get(requiredType);
    }

    private void createDefaultEditors() {
        this.defaultEditors = new HashMap<Class<?>, PropertyEditor>(64);

        // Spring's CustomBooleanEditor accepts more flag values than the JDK's default editor.
        this.defaultEditors.put(boolean.class, new CustomBooleanEditor(false));
        this.defaultEditors.put(Boolean.class, new CustomBooleanEditor(true));

        // The JDK does not contain default editors for number wrapper types!
        // Override JDK primitive number editors with our own CustomNumberEditor.
		/*this.defaultEditors.put(byte.class, new CustomNumberEditor(Byte.class, false));
		this.defaultEditors.put(Byte.class, new CustomNumberEditor(Byte.class, true));
		this.defaultEditors.put(short.class, new CustomNumberEditor(Short.class, false));
		this.defaultEditors.put(Short.class, new CustomNumberEditor(Short.class, true));*/
        this.defaultEditors.put(int.class, new CustomNumberEditor(Integer.class, false));
        this.defaultEditors.put(Integer.class, new CustomNumberEditor(Integer.class, true));
		/*this.defaultEditors.put(long.class, new CustomNumberEditor(Long.class, false));
		this.defaultEditors.put(Long.class, new CustomNumberEditor(Long.class, true));
		this.defaultEditors.put(float.class, new CustomNumberEditor(Float.class, false));
		this.defaultEditors.put(Float.class, new CustomNumberEditor(Float.class, true));
		this.defaultEditors.put(double.class, new CustomNumberEditor(Double.class, false));
		this.defaultEditors.put(Double.class, new CustomNumberEditor(Double.class, true));
		this.defaultEditors.put(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
		this.defaultEditors.put(BigInteger.class, new CustomNumberEditor(BigInteger.class, true));*/

    }

}
