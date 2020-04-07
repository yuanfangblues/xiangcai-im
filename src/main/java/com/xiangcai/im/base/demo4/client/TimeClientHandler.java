package com.xiangcai.im.base.demo4.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author :元放
 * @date :2020-04-05 15:14
 **/
@Slf4j
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 方式1：读一个int, 转换为时间
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UnixTime m = (UnixTime) msg;
        log.debug("unix time:{}", m);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("discard error", cause);
        ctx.close();
    }
}
