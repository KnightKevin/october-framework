package com.simon.october.core.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 最终执行的数据结构
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MethodDetail {
    private Method method;
    private Map<String, String> queryParameterMap;

    /**
     * json type http post request data
     */
    private String json;

    /**
     *
     */
    public void build(String requestPath, Map<String, Method> methodMap) {
        // 为以后的路径参数实现做准备
        methodMap.forEach((uri, method) -> {
            if (uri.equals(requestPath)) {
                this.method = method;
            }
        });
    }
}
