package com.simon.october.server;

import com.simon.october.core.mvc.factory.RequestHandlerFactory;
import com.simon.october.core.mvc.handler.RequestHandler;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 对于http request的处理
 */
@Slf4j
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final String FAVICON_ICO = "/favicon.ico";

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {

        String uri = fullHttpRequest.uri();

        if (FAVICON_ICO.equals(uri)) {
            return;
        }

        // request handler
        RequestHandler requestHandler = RequestHandlerFactory.get(fullHttpRequest.method());
        FullHttpResponse response = requestHandler.handle(fullHttpRequest);

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
