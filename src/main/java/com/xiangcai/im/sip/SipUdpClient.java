package com.xiangcai.im.sip;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author :元放
 * @date :2020-05-18 20:29
 **/
@Slf4j
public class SipUdpClient {


    public static final int INIT_PORT = 15060;

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            //通过NioDatagramChannel创建Channel，并设置Socket参数支持广播
            //UDP相对于TCP不需要在客户端和服务端建立实际的连接，因此不需要为连接（ChannelPipeline）设置handler
            Bootstrap b = new Bootstrap();
            b.group(bossGroup)
                    .channel(NioDatagramChannel.class)
                    .handler(new SipClientHandler());
            Channel channel = b.bind(INIT_PORT).sync().channel();
            log.info("SipUdpClient 启动成功 ip:[{}],port:[{}]", "127.0.0.1", INIT_PORT);
            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("你好端口7397的bugstack虫洞栈，我是客户端小爱，你在吗", Charset.forName("GBK")), new InetSocketAddress("127.0.0.1", 5060)));
            log.debug("write log !!!");
            channel.closeFuture().await();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            bossGroup.shutdownGracefully();
        }
    }
}
