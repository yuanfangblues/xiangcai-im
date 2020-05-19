package com.xiangcai.im.text.telnet;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * telnet client
 * 1.不停的从命令行接收一行数据，以回车换行为一行结束符
 * 2.当输入 "bye" 则关闭cline
 *
 * @author :元放
 * @date :2020-04-05 22:37
 **/
public class TelnetClient {

    static final boolean SSL = System.getProperty("ssl") != null;

    public static String HOST = System.getProperty("host", "127.0.0.1");

    public static int PORT = Integer.parseInt(System.getProperty("port", SSL ? "8992" : "8023"));


    public static void main(String[] args) throws Exception {
        final SslContext sslContext;
        if (SSL) {
            sslContext = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslContext = null;
        }

        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new TelnetClientInitializer(sslContext));

        //启动链接
        Channel ch = b.connect(HOST, PORT).sync().channel();

        BufferedReader in = new BufferedReader(new StringReader("DX0001|  23|2|801|0|20090312113024|"));

        String line = in.readLine();
        //发送接收到的数据到服务端
        ch.writeAndFlush(line + "\r\n");

        ch.closeFuture().sync();
    }
}
