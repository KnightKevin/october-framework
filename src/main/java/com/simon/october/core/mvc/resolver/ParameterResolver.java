package com.simon.october.core.mvc.resolver;

import com.simon.october.core.mvc.entity.MethodDetail;

import java.lang.reflect.Parameter;

/**
 * Process method parameters
 */
public interface ParameterResolver {
    Object resolve(MethodDetail methodDetail, Parameter parameter);
}
