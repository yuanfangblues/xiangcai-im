package com.xiangcai.im.text.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author :元放
 * @date :2020-04-06 16:30
 **/
@Slf4j
public class SecureChatServerHandler extends SimpleChannelInboundHandler<String> {

    public final ConnectionManager connectionManager = new DefaultConnectionManagerImpl();

    public static AtomicInteger userCount = new AtomicInteger(0);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel currChannel = ctx.channel();

        if (StringUtils.isEmpty(msg)) {
            currChannel.writeAndFlush("[you] " + msg + '\n');
            return;
        }

        try {
            sendMsg(ctx, msg, currChannel);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.channel().writeAndFlush("[sys] sys error break !");
            ctx.close();
        }


    }

    private void sendMsg(ChannelHandlerContext ctx, String msg, Channel currChannel) {
        if (msg.contains("#call")) {
            String[] split = msg.split(":");
            String s = split[1].trim();
            String toYouMsg = "";
            if (split.length > 2) {
                toYouMsg = split[2].trim();
            }else {
                toYouMsg = "hello";
            }

            Optional<Connection> connection = connectionManager.selectConnection(s);
            if (connection.isPresent()) {
                Channel channel = connection.get().getChannel();
                if (channel.equals(currChannel)) {
                    channel.writeAndFlush("[you] not talk you self!");
                }else{
                    channel.writeAndFlush("[]" + connectionManager.selectConnection(s).get().connectionId() + toYouMsg);
                }

            }else{
                log.warn("people is not exist! :{}", s);
                currChannel.writeAndFlush("[sys] people is not onLine or not exist !");
            }

        }else if("#bye".equals(msg.toLowerCase())) {
            currChannel.writeAndFlush("[sys] bye !");
            ctx.close();
        }else{
            currChannel.writeAndFlush("[you] " + msg + '\n');
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.pipeline().get(SslHandler.class).handshakeFuture().addListener(new GenericFutureListener<Future<? super Channel>>() {
            @Override
            public void operationComplete(Future<? super Channel> future) throws Exception {
                ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + " secure chat service!\n");
                ctx.writeAndFlush(
                        "Your session is protected by " +
                                ctx.pipeline().get(SslHandler.class).engine().getSession().getCipherSuite() +
                                " cipher suite.\n");

                userCount.incrementAndGet();

                ctx.writeAndFlush("Your Id "+ userCount.get() +"\n");
                connectionManager.registerConnection(new DefaultConnection(ctx.channel(), String.valueOf(userCount.get())));
            }


        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().writeAndFlush("[sys] sys error break !");
        ctx.close();
    }
}
