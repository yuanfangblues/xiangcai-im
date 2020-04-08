package com.xiangcai.im.text.chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;

/**
 * @author :元放
 * @date :2020-04-06 16:07
 **/
public class SecureChatInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslContext;

    public SecureChatInitializer(SslContext sslContext) {
        this.sslContext = sslContext;
    }


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(sslContext.newHandler(ch.alloc(), SecureChatClient.HOST, SecureChatClient.PORT));
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new SecureChatClientHandler());
//        pipeline.addLast(new SecureChatClientOutHandler());

    }
}
