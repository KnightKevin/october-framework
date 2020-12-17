package com.simon.october.core.mvc.entity;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 最终执行的数据结构
 */
public class MethodDetail {
    private Method method;
    private Map<String, String> queryParameterMap;
}
