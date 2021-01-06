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
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class PostRequestHandler implements RequestHandler {
    @Override
    public FullHttpResponse handle(FullHttpRequest fullHttpRequest) {
        // 获取uri，包括参数的
        String uri = fullHttpRequest.uri();

        String requestPath = UrlUtil.getRequestPath(uri);

        MethodDetail methodDetail = RouteMethodMapper.getMethodDetail(requestPath, HttpMethod.POST);
        Method targetMethod = methodDetail.getMethod();
        if (null == targetMethod) {
            return null;
        }

        String contentType = getContentType(fullHttpRequest.headers());

        List<Object> targetMethodParams = new ArrayList<>();

        if ("application/json".equals(contentType)) {
            String json = fullHttpRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
            methodDetail.setJson(json);
            Parameter[] targetMethodParameters = targetMethod.getParameters();
            for (Parameter parameter : targetMethodParameters) {
                ParameterResolver parameterResolver = ParameterResolverFactory.get(parameter);
                targetMethodParams.add(parameterResolver.resolve(methodDetail, parameter));
            }
        } else {
            throw new IllegalArgumentException("only receive application/json type data");
        }

        String beanName = BeanHelper.getBeanName(methodDetail.getMethod().getDeclaringClass());
        Object targetObject = BeanFactory.BEANS.get(beanName);
        return FullHttpResponseFactory.getSuccessResponse(targetMethod, targetMethodParams, targetObject);
    }

    private String getContentType(HttpHeaders headers) {
        String typeStr = headers.get("Content-Type");
        String[] list = typeStr.split(";");
        return list[0];
    }
}
