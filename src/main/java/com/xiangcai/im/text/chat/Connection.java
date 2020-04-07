package com.xiangcai.im.text.chat;

import io.netty.channel.Channel;

/**
 * @author :元放
 * @date :2020-04-06 16:45
 **/
public interface Connection<T> {

    String connectionId();

    Channel getChannel();
}
