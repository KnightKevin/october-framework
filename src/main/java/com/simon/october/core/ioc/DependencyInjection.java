package com.simon.october.core.ioc;

import java.util.Map;

public class DependencyInjection {
    /**
     * 遍历容器的所有bean的属性，为所有带@Autowired/@Value注解的属性注入实例
     */
    public static void inject(String[] packageNames) {
        AutowiredBeanInitialization autowiredBeanInitialization = new AutowiredBeanInitialization(packageNames);
        Map<String, Object> beans = BeanFactory.BEANS;
        if (beans.size() <= 0) {
            return;
        }
        BeanFactory.BEANS.values().forEach(autowiredBeanInitialization::initialize);
    }
}
