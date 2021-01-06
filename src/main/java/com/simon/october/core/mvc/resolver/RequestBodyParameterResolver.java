package com.simon.october.core.mvc.resolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simon.october.annotation.mvc.RequestBody;
import com.simon.october.core.mvc.entity.MethodDetail;

import java.lang.reflect.Parameter;

public class RequestBodyParameterResolver implements ParameterResolver {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Override
    public Object resolve(MethodDetail methodDetail, Parameter parameter) {
        RequestBody requestBody = parameter.getAnnotation(RequestBody.class);
        Object param = null;
        if (null != requestBody) {
            try {
                param = OBJECT_MAPPER.readValue(methodDetail.getJson(), parameter.getType());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return param;
    }
}
