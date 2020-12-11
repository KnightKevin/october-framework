package com.simon.october.core;

import com.simon.october.annotation.ComponentScan;
import com.simon.october.common.Banner;
import com.simon.october.core.aop.factory.InterceptorFactory;
import com.simon.october.core.config.Configuration;
import com.simon.october.core.config.ConfigurationManager;
import com.simon.october.core.ioc.BeanFactory;
import com.simon.october.core.ioc.DependencyInjection;
import com.simon.october.core.mvc.factory.RouteMethodMapper;
import com.simon.october.factory.ClassFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

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
        loadConfig(clazz);

        // Load interceptors
        InterceptorFactory.loadInterceptors(pagesNames);

        // Traverse all the beans in the ioc container and inject instances for all @Autowired annotated attributes
        DependencyInjection.inject(pagesNames);

        // Applies bean post processors on the classes which are from ClassFactory.
        // For example, the class annotated by @Component or @RestController.
        BeanFactory.applyBeanPostProcessor();

        // Perform some callback events
    }

    private static String[] getPackageNames(Class<?> clazz) {
        ComponentScan componentScan = clazz.getAnnotation(ComponentScan.class);

        // 如果没设置包名就去其默认的包名
        return !Objects.isNull(componentScan) ? componentScan.value() : new String[]{clazz.getPackage().getName()};
    }

    private void loadConfig(Class<?> applicationClass) {
        URL url = applicationClass.getClassLoader().getResource("");
        List<Path> filePaths = new ArrayList<>();
        try {
            Path path = Paths.get(url.toURI());

            Stream<Path> pathStream = Files.list(path);
            Iterator<Path> pathIterator = pathStream.iterator();

            while (pathIterator.hasNext()) {
                Path p = pathIterator.next();
                if (p.getFileName().toString().startsWith(Configuration.APPLICATION_NAME)) {
                    filePaths.add(p);
                }
            }


        } catch (URISyntaxException | IOException e) {
            log.error(e.getMessage());
        }
        ConfigurationManager configurationManager = BeanFactory.getBean(ConfigurationManager.class);
        configurationManager.loadResources(filePaths);
    }

}
