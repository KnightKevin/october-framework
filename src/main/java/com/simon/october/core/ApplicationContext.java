package com.simon.october.core;

import com.simon.october.annotation.ComponentScan;
import com.simon.october.common.Banner;
import com.simon.october.core.ioc.BeanFactory;
import com.simon.october.core.mvc.factory.RouteMethodMapper;
import com.simon.october.factory.ClassFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * framework start
 */
@Slf4j
public final class ApplicationContext {
    public void run(Class<?> clazz) {
        // print banner
        Banner.print();

        // get package
        String[] pagesNames = getPackageNames(clazz);

        // load classes with custom annotation
        ClassFactory.loadClass(pagesNames);

        // Load routes
        RouteMethodMapper.loadRoutes();

        // Load beans managed by the ioc container
        BeanFactory.loadBeans();

        // Load configuration

        // Load interceptors

        // Traverse all the beans in the ioc container and inject instances for all @Autowired annotated attributes

        // Perform some callback events
    }

    private static String[] getPackageNames(Class<?> clazz) {
        ComponentScan componentScan = clazz.getAnnotation(ComponentScan.class);

        // 如果没设置包名就去其默认的包名
        return !Objects.isNull(componentScan) ? componentScan.value() : new String[]{clazz.getPackage().getName()};
    }

}
