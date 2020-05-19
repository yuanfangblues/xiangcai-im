package com.xiangcai.im.sip;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author :元放
 * @date :2020-05-18 20:35
 **/
@Slf4j
public class SipUdpServer {

    public static final int INIT_PORT = 5060;


    public static void main(String[] args) {
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        try{
            //通过NioDatagramChannel创建Channel，并设置Socket参数支持广播
            //UDP相对于TCP不需要在客户端和服务端建立实际的连接，因此不需要为连接（ChannelPipeline）设置handler
            Bootstrap b=new Bootstrap();
            b.group(bossGroup)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new SipServerHandler());
            ChannelFuture channelFuture=b.bind(INIT_PORT).sync();
            log.info("SipUdpClient 启动成功 ip:[{}],port:[{}]", "127.0.0.1", INIT_PORT);
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally{
            bossGroup.shutdownGracefully();
        }
    }
}
