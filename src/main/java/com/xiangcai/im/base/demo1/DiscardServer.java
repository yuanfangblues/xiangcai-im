package com.xiangcai.im.base.demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 丢弃所有数据
 *
 * @author :元放
 * @date :2020-04-05 13:48
 **/
public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }


    public void run() throws Exception {
        /**
         * 接受链接
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            /**
             * 启动server
             */
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    //// TODO: 2020-04-05 option, childOption区别
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(port).sync();

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    /**
     * 学习netty的基本组件和概念
     * 1.EventLoopGroup
     * 2.ServerBootstrap
     * 3.channel
     * 4.ChannelHandler
     * 5.ChannelOption
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new DiscardServer(5555).run();
    }

}
