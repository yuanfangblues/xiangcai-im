package com.xiangcai.im.text.udp_package;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.udt.UdtChannel;
import io.netty.channel.udt.nio.NioUdtProvider;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * @author :元放
 * @date :2020-04-16 17:53
 **/
public class ByteEchoServer {
    static final int port = 8007;

    public static void main(String[] args) throws Exception {

        final NioEventLoopGroup acceptGroup = new NioEventLoopGroup(1,  new DefaultThreadFactory("accept"), NioUdtProvider.BYTE_PROVIDER);
        final NioEventLoopGroup connectGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("connect"), NioUdtProvider.BYTE_PROVIDER);

        try {
            final ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(acceptGroup, connectGroup)
                    .channelFactory(NioUdtProvider.BYTE_ACCEPTOR)
                    .option(ChannelOption.SO_BACKLOG, 10)
                    .handler(new ChannelInitializer<UdtChannel>() {
                        @Override
                        protected void initChannel(UdtChannel ch) throws Exception {
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO), new ByteEchoServerHandler());
                        }
                    });

            final ChannelFuture future = serverBootstrap.bind(port).sync();

            future.channel().closeFuture().sync();
        } finally {
            acceptGroup.shutdownGracefully();
            connectGroup.shutdownGracefully();
        }
    }
}
