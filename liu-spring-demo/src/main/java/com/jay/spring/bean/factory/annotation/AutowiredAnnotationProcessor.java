package com.jay.spring.bean.factory.annotation;

import com.jay.spring.bean.BeansException;
import com.jay.spring.bean.factory.BeanCreationException;
import com.jay.spring.bean.factory.config.AutowireCapableBeanFactory;
import com.jay.spring.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by xiang.wei on 2018/7/22
 *
 * @author xiang.wei
 */
public class AutowiredAnnotationProcessor {
    private AutowireCapableBeanFactory beanFactory;
    private String requiredParameterName = "required";
    private boolean requiredParameterValue = true;

    private final Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<Class<? extends Annotation>>();

    public InjectionMetadata buildAutowiringMetadata(Class<?> clazz) {
        LinkedList<InjectionElement> elements = new LinkedList<InjectionElement>();
        Class<?> targetClass = clazz;

        LinkedList<InjectionElement> currentElements = new LinkedList<InjectionElement>();
        for (Field field : targetClass.getDeclaredFields()) {
            Annotation ann = findAutowiredAnnotation(field);
            if (ann != null) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                boolean required = determineRequiredStatus(ann);
                currentElements.add(new AutowiredFieldElement(field, required, beanFactory));

            }
        }
        for (Method method : targetClass.getDeclaredMethods()) {
            //TODO 处理方法注入
        }
        elements.addAll(0, currentElements);
        targetClass = targetClass.getSuperclass();
        while (targetClass != null && targetClass != Object.class);

        return new InjectionMetadata(clazz, elements);

    }

    protected boolean determineRequiredStatus(Annotation ann) {
        try {
            Method method = ReflectionUtils.findMethod(ann.annotationType(), this.requiredParameterName);
            if (method == null) {
                // Annotations like @Inject and @Value don't have a method (attribute) named "required"
                // -> default to required status
                return true;
            }
            return (this.requiredParameterValue == (Boolean) ReflectionUtils.invokeMethod(method, ann));
        }
        catch (Exception ex) {
            // An exception was thrown during reflective invocation of the required attribute
            // -> default to required status
            return true;
        }
    }
    private Annotation findAutowiredAnnotation(AccessibleObject accessibleObject) {
        for (Class<? extends Annotation> type : this.autowiredAnnotationTypes) {
            Annotation annotation = AnnotationUtils.getAnnotation(accessibleObject, type);
            if (annotation != null) {
                return annotation;
            }
        }
        return null;
    }

    public void setBeanFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
    public Object beforeInitialization(Object bean, String beanName) throws BeansException {
        //do nothing
        return bean;
    }
    public Object afterInitialization(Object bean, String beanName) throws BeansException {
        // do nothing
        return bean;
    }
    public Object beforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    public boolean afterInstantiation(Object bean, String beanName) throws BeansException {
        // do nothing
        return true;
    }

    public void postProcessPropertyValues(Object bean, String beanName) throws BeansException {
        InjectionMetadata metadata = buildAutowiringMetadata(bean.getClass());
        try {
            metadata.inject(bean);
        }
        catch (Throwable ex) {
            throw new BeanCreationException(beanName, "Injection of autowired dependencies failed", ex);
        }
    }


}
