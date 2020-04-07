package com.xiangcai.im.text.chat;

import java.util.Optional;

public interface ConnectionManager {
    void registerConnection(Connection connection);

    void cleanConnection(Connection connection);

    Optional<Connection> selectConnection(String selectParams);
}
