package com.simon.october.core.mvc.factory;

import com.simon.october.annotation.mvc.RequestBody;
import com.simon.october.annotation.mvc.RequestParam;
import com.simon.october.core.mvc.resolver.ParameterResolver;
import com.simon.october.core.mvc.resolver.RequestBodyParameterResolver;
import com.simon.october.core.mvc.resolver.RequestParamParameterResolver;

import java.lang.reflect.Parameter;

public class ParameterResolverFactory {
    public static ParameterResolver get(Parameter parameter) {
        if (parameter.isAnnotationPresent(RequestParam.class)) {
            return new RequestParamParameterResolver();
        }

        if (parameter.isAnnotationPresent(RequestBody.class)) {
            return new RequestBodyParameterResolver();
        }

        return null;
    }
}
