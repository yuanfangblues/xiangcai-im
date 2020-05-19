package com.xiangcai.im.base.voip.refactory.server;

import com.xiangcai.im.base.voip.refactory.client.DefaultConfig;
import com.xiangcai.im.base.voip.refactory.client.SipClientHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author :元放
 * @date :2020-04-24 10:46
 **/
public class UAS {

    public static void main(String[] args) {
        DefaultConfig defaultConfig = new DefaultConfig();

        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        NioEventLoopGroup boss = new NioEventLoopGroup();

        try {
//            bootstrap.group(boss, worker).channel(NioDatagramChannel.class)
//                    .option(ChannelOption.SO_BROADCAST, true)
//                    .handler(new SipClientHandler());
//            ChannelFuture channelFuture = bootstrap.bind(defaultConfig.getServerIp(), defaultConfig.getSipPort()).sync();
//            channelFuture.channel().closeFuture().sync();


        }finally {
            worker.shutdownGracefully();
        }
    }
}
