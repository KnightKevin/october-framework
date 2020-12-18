package com.simon.october.core.mvc.handler;

import com.simon.october.core.ioc.BeanFactory;
import com.simon.october.core.ioc.BeanHelper;
import com.simon.october.core.mvc.entity.MethodDetail;
import com.simon.october.core.mvc.factory.FullHttpResponseFactory;
import com.simon.october.core.mvc.factory.RouteMethodMapper;
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
        // todo GetRequestHandler

        // 获取uri，包括参数的
        String uri = fullHttpRequest.uri();

        String requestPath = getRequestPath(uri);
        Map<String, String> param = getQueryParams(uri);

        log.info("the request uri is " + uri);

        MethodDetail methodDetail = RouteMethodMapper.getMethodDetail(requestPath, HttpMethod.GET);
        methodDetail.setQueryParameterMap(param);
        Method targetMethod = methodDetail.getMethod();
        if (null == targetMethod) {
            return null;
        }

        Parameter[] targetMethodParameters = targetMethod.getParameters();
        // todo 将method的参数变成List，因为下面调用方法须要用到
        List<Object> targetMethodParams = new ArrayList<>();
        for (Parameter parameter : targetMethodParameters) {

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

    private String getRequestPath(String uri) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri, Charsets.toCharset(CharEncoding.UTF_8));
        return queryStringDecoder.path();
    }
}
