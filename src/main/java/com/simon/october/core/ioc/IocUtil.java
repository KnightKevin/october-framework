package com.simon.october.core.ioc;

import com.simon.october.annotation.Component;

public class IocUtil {
    public static String getName(Class<?> clazz) {
        String beanName = clazz.getName();

        if (clazz.isAnnotationPresent(Component.class)) {
            Component component = clazz.getAnnotation(Component.class);
            beanName = component.name().isEmpty() ? beanName : component.name();
        }

        return beanName;
    }
}
