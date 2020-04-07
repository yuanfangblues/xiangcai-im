package com.xiangcai.im.text.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

/**
 * @author :元放
 * @date :2020-04-06 16:23
 **/
public class SecureChatServer {

    static final int PORT = Integer.parseInt(System.getProperty("port", "8992"));

    public static void main(String[] args) throws Exception {
        SelfSignedCertificate ssc = new SelfSignedCertificate();

        SslContext sslContext = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGourp = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGourp)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new SecureChatServerInitializer(sslContext));

            b.bind(PORT).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGourp.shutdownGracefully();
        }
    }
}
