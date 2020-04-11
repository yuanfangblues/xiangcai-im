package com.xiangcai.im.base.voip;

import com.xiangcai.im.base.voip.config.Config;
import com.xiangcai.im.base.voip.manager.challenge.ChallengeManager;
import com.xiangcai.im.base.voip.manager.dialog.DialogManager;
import com.xiangcai.im.base.voip.manager.request.InitialRequestManager;
import com.xiangcai.im.base.voip.manager.request.MidDialogRequestManager;
import com.xiangcai.im.base.voip.manager.transaction.TransactionManager;
import com.xiangcai.im.base.voip.manager.transport.TransportManager;
import com.xiangcai.im.base.voip.rtp.AbstractSoundManager;
import com.xiangcai.im.base.voip.rtp.Echo;
import com.xiangcai.im.base.voip.rtp.MediaManager;
import com.xiangcai.im.base.voip.rtp.MediaMode;
import com.xiangcai.im.base.voip.sdp.SDPManager;
import com.xiangcai.im.base.voip.sip.SipListener;
import com.xiangcai.im.base.voip.sip.SipURI;
import com.xiangcai.im.base.voip.sip.exp.SipUriSyntaxException;
import com.xiangcai.im.base.voip.sip.handler.SipHandlerManager;
import com.xiangcai.im.base.voip.sip.pack.SipRequest;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户代理
 *
 * @author :元放
 * @date :2020-04-08 20:29
 **/
public class UserAgent {

    /**
     * SIP协议回调接口
     */
    private SipListener sipListener;

    /**
     * RTP协议音频流处理
     */
    private AbstractSoundManager soundManager;

    /**
     * 配置
     */
    private Config config;


    private DialogManager dialogManager;

    /**
     * 传输层
     */
    private TransactionManager transactionManager;

    /**
     * 保持协议层的链接
     */
    private TransportManager transportManager;


    private SipHandlerManager sipHandlerManager;



    private ChallengeManager challengeManager;

    private InitialRequestManager initialRequestManager;

    private MidDialogRequestManager midDialogRequestManager;


    private List<String> peers;

    private SDPManager sdpManager;


    private MediaManager mediaManager;

    private int cseqCounter;



    private UAC uac;
    private UAS uas;

    public UserAgent(SipListener sipListener, AbstractSoundManager soundManager)
            throws SocketException {
        this(sipListener, soundManager, null);
    }

    public UserAgent(SipListener sipListener, AbstractSoundManager soundManager, Config config) {
        this.sipListener = sipListener;
        this.soundManager = soundManager;
        this.config = config;

        //log, ip, port, doamin


        dialogManager = new DialogManager();

        transactionManager = new TransactionManager();

        transportManager = new TransportManager(transactionManager,
                config);

        transactionManager.setTransportManager(transportManager);


        sipHandlerManager = new SipHandlerManager(this);

        InitialRequestManager initialRequestManager = new InitialRequestManager(this);


        MidDialogRequestManager midDialogRequestManager = new MidDialogRequestManager(this);

        uas = new UAS(this, initialRequestManager, midDialogRequestManager);


        uac = new UAC(this, initialRequestManager, midDialogRequestManager);


        challengeManager = new ChallengeManager(config,
                initialRequestManager,
                midDialogRequestManager,
                dialogManager);

        sipHandlerManager.setChallengeManager(challengeManager);

        peers = new ArrayList<>();


        sdpManager = new SDPManager(this);

        sipHandlerManager.setSdpManager(sdpManager);
        sipHandlerManager.setSdpManager(sdpManager);

        mediaManager = new MediaManager(this);

    }

    public Config getConfig() {
        return config;
    }

    public boolean isMediaDebug() {
        return false;
    }

    public String getPeersHome() {
        return null;
    }

    public MediaMode getMediaMode() {
        return config.getMediaMode();
    }

    public void setEcho(Echo echo) {

    }

    public AbstractSoundManager getSoundManager() {
        return this.soundManager;
    }

    public Echo getEcho() {
        return null;
    }


    public SipRequest register() throws SipUriSyntaxException {
        return uac.register();
    }

    public void unregister() throws SipUriSyntaxException {
        uac.unregister();
    }

    public SipRequest invite(String requestUri, String callId)
            throws SipUriSyntaxException {
        return uac.invite(requestUri, callId);
    }

    public String getDomain() {

        return null;
    }

    public String getUserpart() {
        return null;
    }

    public InitialRequestManager getInitialRequestManager() {
        return initialRequestManager;
    }

    public SipListener getSipListener() {
        return sipListener;
    }

    public String generateCSeq(String method) {
        StringBuffer buf = new StringBuffer();
        buf.append(cseqCounter++);
        buf.append(' ');
        buf.append(method);
        return buf.toString();
    }

    public SipURI getOutboundProxy() {
        return null;
    }

    public SipHandlerManager getSipHandlerManager() {
        return sipHandlerManager;
    }
}
