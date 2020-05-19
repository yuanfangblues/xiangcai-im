package com.xiangcai.im.text.udp_package;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.udt.UdtChannel;
import io.netty.channel.udt.nio.NioUdtProvider;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * @author :元放
 * @date :2020-04-16 17:39
 **/
public class ByteEchoClient {

    public static void main(String[] args) throws Exception{
        NioEventLoopGroup group = new NioEventLoopGroup(1, new DefaultThreadFactory("connect"), NioUdtProvider.BYTE_PROVIDER);

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channelFactory(NioUdtProvider.BYTE_CONNECTOR)
                    .handler(new ChannelInitializer<UdtChannel>() {
                        @Override
                        protected void initChannel(UdtChannel ch) throws Exception {
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO), new ByteEchoClientHandler());
                        }
                    });

            final ChannelFuture f = bootstrap.connect("127.0.0.1", 8007).sync();

            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
