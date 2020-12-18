package com.simon.october.core.mvc.factory;

import com.simon.october.annotation.RestController;
import com.simon.october.annotation.mvc.GetMapping;
import com.simon.october.annotation.mvc.PostMapping;
import com.simon.october.core.mvc.entity.MethodDetail;
import com.simon.october.factory.ClassFactory;
import io.netty.handler.codec.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * save route-related mapping information
 * 暂不支持路径参数
 */
@Slf4j
public class RouteMethodMapper {

    /**
     * key: http method
     * value : url -> method
     */
    private static final Map<HttpMethod, Map<String, Method>> REQUEST_METHOD_MAP = new HashMap<>(2);

    static {
        REQUEST_METHOD_MAP.put(HttpMethod.GET, new HashMap<>(64));
        REQUEST_METHOD_MAP.put(HttpMethod.POST, new HashMap<>(64));
    }

    public static void loadRoutes() {
        // todo simon:
        //  先获得@RestController注解的类，
        //  在拿到其中被@GetMapping 和 @PostMapping注解过的方法，分别得到其中的路径信息
        //  组合好最终路径
        log.info("save route-related mapping information");

        Set<Class<?>> restControllerSet = ClassFactory.CLASSES.get(RestController.class);

        for (Class<?> clazz : restControllerSet) {
            Method[] methods = clazz.getDeclaredMethods();

            String basePath = clazz.getDeclaredAnnotation(RestController.class).value();

            for (Method i : methods) {
                // 处理get
                GetMapping getMapping = i.getDeclaredAnnotation(GetMapping.class);
                if (!Objects.isNull(getMapping)) {
                    String uri = basePath + getMapping.value();
                    REQUEST_METHOD_MAP.get(HttpMethod.GET).put(uri, i);
                }

                // 处理post
                PostMapping postMapping = i.getDeclaredAnnotation(PostMapping.class);
                if (!Objects.isNull(postMapping)) {
                    String uri = basePath + postMapping.value();
                    REQUEST_METHOD_MAP.get(HttpMethod.POST).put(uri, i);
                }
            }


        }

    }

    /**
     * 根据uri取出须要执行的method的详细信息
     */
    public static MethodDetail getMethodDetail(String requestPath, HttpMethod httpMethod) {
        MethodDetail methodDetail = new MethodDetail();
        methodDetail.build(requestPath, REQUEST_METHOD_MAP.get(httpMethod));
        return methodDetail;
    }

}
