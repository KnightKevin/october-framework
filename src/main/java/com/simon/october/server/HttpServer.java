package com.simon.october.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServer {
    private static final int PORT = 8080;

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel c) {
                            c.pipeline()
                                    .addLast(new HttpServerCodec())
                                    .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
                                    .addLast(new HttpRequestHandler())
                            ;
                        }
                    });

            Channel ch = bootstrap.bind(PORT).sync().channel();
            log.info("Netty Http server started on port {}", PORT);
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            log.info("occur exception when start server :", e);
        } finally {
            log.info("shutdown bossGroup and workGroup");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
