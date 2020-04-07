package com.xiangcai.im.text.chat;

import java.nio.channels.Channel;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :元放
 * @date :2020-04-06 16:45
 **/
public class DefaultConnectionManagerImpl implements ConnectionManager {

    public static final Map<String, Connection> CONNECTION_MAP = new ConcurrentHashMap<>(1024);


    @Override
    public void registerConnection(Connection connection) {
        CONNECTION_MAP.put(connection.connectionId(), connection);
    }

    @Override
    public void cleanConnection(Connection connection) {
        CONNECTION_MAP.remove(connection.connectionId());
    }

    @Override
    public Optional<Connection> selectConnection(String selectParams) {
        return Optional.ofNullable(CONNECTION_MAP.get(selectParams));
    }
}
