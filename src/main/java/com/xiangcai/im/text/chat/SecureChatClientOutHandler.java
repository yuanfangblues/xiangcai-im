package com.xiangcai.im.text.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author :元放
 * @date :2020-04-06 17:31
 **/
@Slf4j
public class SecureChatClientOutHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
       log.debug("you receive msg:{}", msg);
    }
}
