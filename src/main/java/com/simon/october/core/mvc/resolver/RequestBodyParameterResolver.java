package com.simon.october.core.mvc.resolver;

import com.simon.october.core.mvc.entity.MethodDetail;

import java.lang.reflect.Parameter;

public class RequestBodyParameterResolver implements ParameterResolver {
    @Override
    public Object resolve(MethodDetail methodDetail, Parameter parameter) {
        return null;
    }
}
