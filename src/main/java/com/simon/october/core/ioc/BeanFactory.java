package com.simon.october.core.ioc;

import com.simon.october.annotation.Component;
import com.simon.october.annotation.RestController;
import com.simon.october.core.config.ConfigurationFactory;
import com.simon.october.core.config.ConfigurationManager;
import com.simon.october.exception.DoGetBeanException;
import com.simon.october.factory.ClassFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class BeanFactory {

    /**
     * ioc 容器
     */
    public static final Map<String, Object> BEANS = new ConcurrentHashMap<>(64);

    private static final Map<String, String[]> SINGLE_BEAN_NAMES_TYPE_MAP = new ConcurrentHashMap<>(64);

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

            // think 须要思考的作者为什么在这将系统默认配置，初步考虑是因为这用不了依赖注入
            // 将系统默认配置加到容器当中
            BEANS.put(ConfigurationManager.class.getName(), new ConfigurationManager(ConfigurationFactory.getConfig()));
        } catch (IllegalAccessException | InstantiationException e) {
            log.info("create object fail!");
        }
    }

    public static <T> T getBean(Class<T> type) {
        // todo bean
        String[] beanNames = getBeanNamesForType(type);
        if (0 == beanNames.length) {
            throw new DoGetBeanException("not fount bean implement, the bean : " + type.getName());
        }
        Object o = BEANS.get(beanNames[0]);
        if (!type.isInstance(o)) {
            throw new DoGetBeanException("not fount bean implement, the bean :" + type.getName());
        }
        return type.cast(o);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        // todo 找出某个类的bean
        return null;
    }

    // think 当然没看懂了
    private static String[] getBeanNamesForType(final Class<?> type) {
        String beanName = type.getName();
        String[] beanNames = SINGLE_BEAN_NAMES_TYPE_MAP.get(beanName);
        if (beanNames == null) {
            List<String> beanNameList = new ArrayList<>();
            BEANS.forEach((k, v) -> {
                Class<?> beanClass = v.getClass();
                if (type.isInterface()) {
                    Class<?>[] interfaces = beanClass.getInterfaces();
                    for (Class<?> c : interfaces) {
                        if (type.getName().equals(c.getName())) {
                            beanNameList.add(k);
                            break;
                        }
                    }
                } else if (beanClass.isAssignableFrom(type)) {
                    beanNameList.add(k);
                }
            });

            beanNames = beanNameList.toArray(new String[0]);
            SINGLE_BEAN_NAMES_TYPE_MAP.put(beanName, beanNames);
        }
        return beanNames;
    }
}
