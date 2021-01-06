package com.simon.october.core.mvc.handler;

import com.simon.october.core.ioc.BeanFactory;
import com.simon.october.core.ioc.BeanHelper;
import com.simon.october.core.mvc.entity.MethodDetail;
import com.simon.october.core.mvc.factory.FullHttpResponseFactory;
import com.simon.october.core.mvc.factory.ParameterResolverFactory;
import com.simon.october.core.mvc.factory.RouteMethodMapper;
import com.simon.october.core.mvc.resolver.ParameterResolver;
import com.simon.october.core.mvc.util.UrlUtil;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class GetRequestHandler implements RequestHandler {
    @Override
    public FullHttpResponse handle(FullHttpRequest fullHttpRequest) {
        // 获取uri，包括参数的
        String uri = fullHttpRequest.uri();

        String requestPath = UrlUtil.getRequestPath(uri);
        Map<String, String> param = getQueryParams(uri);

        MethodDetail methodDetail = RouteMethodMapper.getMethodDetail(requestPath, HttpMethod.GET);
        methodDetail.setQueryParameterMap(param);
        Method targetMethod = methodDetail.getMethod();
        if (null == targetMethod) {
            return null;
        }

        Parameter[] targetMethodParameters = targetMethod.getParameters();
        List<Object> targetMethodParams = new ArrayList<>();
        for (Parameter parameter : targetMethodParameters) {
            ParameterResolver parameterResolver = ParameterResolverFactory.get(parameter);
            targetMethodParams.add(parameterResolver.resolve(methodDetail, parameter));
        }

        String beanName = BeanHelper.getBeanName(methodDetail.getMethod().getDeclaringClass());
        Object targetObject = BeanFactory.BEANS.get(beanName);
        return FullHttpResponseFactory.getSuccessResponse(targetMethod, targetMethodParams, targetObject);
    }

    private Map<String, String> getQueryParams(String uri) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri, Charsets.toCharset(CharEncoding.UTF_8));
        Map<String, String> param = new HashMap<>();
        queryStringDecoder.parameters().forEach((k, list) -> {
            for (String i : list) {
                param.put(k, i);
            }
        });
        return param;
    }

}
