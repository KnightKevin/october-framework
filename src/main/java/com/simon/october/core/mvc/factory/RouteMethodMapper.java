package com.simon.october.core.mvc.factory;

import com.simon.october.annotation.RestController;
import com.simon.october.factory.ClassFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * save route-related mapping information
 */
@Slf4j
public class RouteMethodMapper {
    public static void loadRoutes() {
        // todo simon:
        //  先获得@RestController注解的类，
        //  在拿到其中被@GetMapping 和 @PostMapping注解过的方法，分别得到其中的路径信息
        //  组合好最终路劲
        log.info("save route-related mapping information");

        Set<Class<?>> restControllerMap = ClassFactory.CLASSES.get(RestController.class);
    }
}
