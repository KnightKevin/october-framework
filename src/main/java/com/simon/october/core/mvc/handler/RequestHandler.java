package com.simon.october.core.mvc.handler;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

public interface RequestHandler {
    FullHttpResponse handle(FullHttpRequest fullHttpRequest);
}
