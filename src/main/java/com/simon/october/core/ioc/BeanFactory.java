package com.simon.october.core.ioc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    /**
     * ioc 容器
     */
    private static final Map<String, Object> BEANS = new ConcurrentHashMap<>(64);

    /**
     * load
     */
    public static void loadBeans() {
        // todo simon: 将被注解的类加载到容器中
    }
}
