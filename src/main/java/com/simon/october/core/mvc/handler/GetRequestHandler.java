package com.simon.october.core.mvc.handler;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.QueryStringDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;

import java.util.HashMap;
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

        // 获取并解析出path和参数

        // 获取这个路由的目标方法

        return null;
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
