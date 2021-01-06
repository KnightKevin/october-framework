package com.simon.october.core.mvc.util;

import io.netty.handler.codec.http.QueryStringDecoder;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;

public class UrlUtil {
    public static String getRequestPath(String uri) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri, Charsets.toCharset(CharEncoding.UTF_8));
        return queryStringDecoder.path();
    }
}
