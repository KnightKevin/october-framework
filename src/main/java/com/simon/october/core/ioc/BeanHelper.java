package com.simon.october.core.ioc;

import com.simon.october.annotation.Component;

public class BeanHelper {

    /**
     * 根据要注入的类，获取它应当在ioc容器存在的bean name
     */
    public static String getBeanName(Class<?> clazz) {
        String name = clazz.getName();
        if (clazz.isAnnotationPresent(Component.class)) {
            Component component = clazz.getAnnotation(Component.class);
            name = "".equals(component.name()) ? clazz.getName() : component.name();
        }
        return name;
    }
}
