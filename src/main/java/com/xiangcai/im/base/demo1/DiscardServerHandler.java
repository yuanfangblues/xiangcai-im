package com.xiangcai.im.base.demo1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author :元放
 * @date :2020-04-05 13:45
 **/
@Slf4j
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;

        try {
            if (in.isReadable()) {
                log.info("discard msg, {}", in.toString(io.netty.util.CharsetUtil.US_ASCII));
            }
        } finally {
            //// 这里需要手动释放
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("discard error", cause);
        ctx.close();
    }
}
