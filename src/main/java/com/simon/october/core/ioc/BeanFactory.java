package com.simon.october.core.ioc;

import com.simon.october.annotation.Component;
import com.simon.october.annotation.RestController;
import com.simon.october.factory.ClassFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
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

        // 先将其@RestController的类加载到容器中
        try {
            Set<Class<?>> restControllers = ClassFactory.CLASSES.get(RestController.class);
            for (Class<?> i : restControllers) {
                // 先获取bean name
                String name = IocUtil.getName(i);
                BEANS.put(name, i.newInstance());
            }

            Set<Class<?>> components = ClassFactory.CLASSES.get(Component.class);
            for (Class<?> i : components) {
                // 先获取bean name
                String name = IocUtil.getName(i);
                BEANS.put(name, i.newInstance());
            }
        } catch (IllegalAccessException | InstantiationException e) {
            log.info("create object fail!");
        }
    }

    public static <T> T getBean(Class<T> type) {
        // todo bean
        String[] beanNames = getBeanNamesForType(type);
        return null;
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        // todo 找出某个类的bean
        return null;
    }

    private static String[] getBeanNamesForType(Class<?> type) {
        // todo 找出某个类的名称
        return null;
    }
}
