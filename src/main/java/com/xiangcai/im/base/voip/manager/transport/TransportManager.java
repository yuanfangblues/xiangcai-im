package com.xiangcai.im.base.voip.manager.transport;

import com.xiangcai.im.base.voip.config.Config;
import com.xiangcai.im.base.voip.manager.transaction.TransactionManager;
import com.xiangcai.im.base.voip.sip.RFC3261;
import com.xiangcai.im.base.voip.sip.pack.SipParser;

import java.net.DatagramSocket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :元放
 * @date :2020-04-09 16:45
 **/
public class TransportManager {

    public static final int SOCKET_TIMEOUT = RFC3261.TIMER_T1;


    private static int NO_TTL = -1;


    private SipServerTransportUser sipServerTransportUser;


    protected SipParser sipParser;

    private TransactionManager transactionManager;

    private Config config;

    private int sipPort;


    private ConcurrentHashMap<SipTransportConnection, DatagramSocket> datagramSockets;
    private ConcurrentHashMap<SipTransportConnection, MessageSender> messageSenders;
    private ConcurrentHashMap<SipTransportConnection, MessageReceiver> messageReceivers;


    public TransportManager(TransactionManager transactionManager,
                            Config config) {
        sipParser = new SipParser();
        datagramSockets = new ConcurrentHashMap<>();
        messageSenders = new ConcurrentHashMap<>();
        messageReceivers = new ConcurrentHashMap<>();
        this.transactionManager = transactionManager;
        this.config = config;
    }

    public MessageSender getMessageSender(
            SipTransportConnection sipTransportConnection) {
        return messageSenders.get(sipTransportConnection);
    }
}
