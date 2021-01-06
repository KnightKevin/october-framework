package com.simon.october.core.mvc.resolver;

import com.simon.october.annotation.mvc.RequestParam;
import com.simon.october.common.util.ObjectUtil;
import com.simon.october.core.mvc.entity.MethodDetail;

import java.lang.reflect.Parameter;

public class RequestParamParameterResolver implements ParameterResolver {
    @Override
    public Object resolve(MethodDetail methodDetail, Parameter parameter) {

        RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
        String requestParameter = requestParam.value();
        String requestParameterValue = methodDetail.getQueryParameterMap().get(requestParameter);
        if (null == requestParameterValue) {
            if (requestParam.require() && requestParam.defaultValue().isEmpty()) {
                return new IllegalArgumentException("the parameter " + requestParameter + " can not be null");
            } else {
                requestParameterValue = null;
            }
        }

        return ObjectUtil.convert(parameter.getType(), requestParameterValue);
    }
}
