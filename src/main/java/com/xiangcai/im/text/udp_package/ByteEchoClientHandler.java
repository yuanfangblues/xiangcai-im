package com.xiangcai.im.text.udp_package;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.udt.nio.NioUdtProvider;

/**
 * @author :元放
 * @date :2020-04-16 17:44
 **/
public class ByteEchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final ByteBuf message;

    public ByteEchoClientHandler() {
        super(false);
        message = Unpooled.buffer(1024);
        for (int i = 0; i < message.capacity(); i++) {
            message.writeByte(((byte) i));
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        ctx.write(msg);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.err.println("ECHO active " + NioUdtProvider.socketUDT(ctx.channel()));
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
