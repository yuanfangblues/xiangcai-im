package com.xiangcai.im.base.demo2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author :元放
 * @date :2020-04-05 13:45
 **/
@Slf4j
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        log.error("echo msg", in.toString(io.netty.util.CharsetUtil.US_ASCII));
        //写入是就已经自动释放
        ctx.write(msg);
        //write只到缓冲区，这里必须要刷新缓冲区，不然客户端接收不到数据
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("echo error", cause);
        ctx.close();
    }
}
