package com.simon.core;

import com.simon.common.Banner;
import lombok.extern.slf4j.Slf4j;

/**
 *  framework start
 * */
@Slf4j
public final class ApplicationContext {
    public void run(Class<?> clazz) {
        // print banner
        Banner.print();

        // analyse package

        // load classes with custom annotation

        // Load routes

        // Load beans managed by the ioc container

        // Load configuration

        // Load interceptors

        // Traverse all the beans in the ioc container and inject instances for all @Autowired annotated attributes

        // Perform some callback events
    }

}
