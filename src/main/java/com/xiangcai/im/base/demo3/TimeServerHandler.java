package com.xiangcai.im.base.demo3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 因为我们将忽略任何接收到的数据，而是在建立连接后立即发送消息，
 * 所以这次我们不能使用channelRead（）方法。
 * 相反，我们应该重写channelActive（）方法。
 * @author :元放
 * @date :2020-04-05 14:51
 **/
@Slf4j
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 写出一个int, 然后关闭连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /**
         * 分配一个4字节 存放int
         */
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int) ( System.currentTimeMillis() / 1000L + 22222222222L));
        log.info("write int -> to client");
        //写入缓冲区并刷新
        final ChannelFuture f = ctx.writeAndFlush(time);

        f.addListener(ChannelFutureListener.CLOSE);


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("time server error", cause);
        ctx.close();
    }
}
