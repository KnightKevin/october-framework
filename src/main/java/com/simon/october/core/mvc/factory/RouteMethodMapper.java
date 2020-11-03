package com.simon.october.core.mvc.factory;

import com.simon.october.annotation.RestController;
import com.simon.october.annotation.mvc.GetMapping;
import com.simon.october.annotation.mvc.PostMapping;
import com.simon.october.factory.ClassFactory;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * save route-related mapping information
 */
@Slf4j
public class RouteMethodMapper {
    private static final Map<String, Method> GET_REQUEST_MAP_METHOD = new HashMap<>();

    private static final Map<String, Method> POST_REQUEST_MAP_METHOD = new HashMap<>();

    public static void loadRoutes() {
        // todo simon:
        //  先获得@RestController注解的类，
        //  在拿到其中被@GetMapping 和 @PostMapping注解过的方法，分别得到其中的路径信息
        //  组合好最终路径
        log.info("save route-related mapping information");

        Set<Class<?>> restControllerSet = ClassFactory.CLASSES.get(RestController.class);

        Map<String, Method> pathToMethod = new HashMap<>();

        for (Class<?> clazz : restControllerSet) {
            Method[] methods = clazz.getDeclaredMethods();

            String basePath = clazz.getDeclaredAnnotation(RestController.class).value();

            for (Method i : methods) {

                // 处理get
                GetMapping getMapping = i.getDeclaredAnnotation(GetMapping.class);
                if (!Objects.isNull(getMapping)) {
                    String uri = basePath + getMapping.value();
                    GET_REQUEST_MAP_METHOD.put(uri, i);
                }

                // 处理post
                PostMapping postMapping = i.getDeclaredAnnotation(PostMapping.class);
                if (!Objects.isNull(postMapping)) {
                    String uri = basePath + postMapping.value();
                    POST_REQUEST_MAP_METHOD.put(uri, i);
                }
            }


        }

    }
}
