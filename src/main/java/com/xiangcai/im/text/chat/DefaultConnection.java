package com.xiangcai.im.text.chat;


import io.netty.channel.Channel;

/**
 * @author :元放
 * @date :2020-04-06 16:55
 **/
public class DefaultConnection implements Connection<Channel> {

    private final Channel channel;
    private final String id;

    public DefaultConnection(Channel channel, String id) {
        this.channel = channel;
        this.id = id;
    }

    @Override
    public String connectionId() {
        return id;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }
}
