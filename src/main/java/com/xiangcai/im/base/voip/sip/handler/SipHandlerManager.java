package com.xiangcai.im.base.voip.sip.handler;

import com.xiangcai.im.base.voip.UserAgent;
import com.xiangcai.im.base.voip.manager.challenge.ChallengeManager;
import com.xiangcai.im.base.voip.manager.transaction.ClientTransaction;
import com.xiangcai.im.base.voip.sdp.SDPManager;
import com.xiangcai.im.base.voip.sip.pack.SipRequest;

/**
 * @author :元放
 * @date :2020-04-09 17:10
 **/
public class SipHandlerManager {

    private UserAgent userAgent;

    private RegisterHandler registerHandler;


    private InviteHandler inviteHandler;

    private CancelHandler cancelHandler;

    private ByeHandler byeHandler;

    private OptionsHandler optionsHandler;

    public SipHandlerManager(UserAgent userAgent) {
        this.userAgent = userAgent;
        this.registerHandler = new RegisterHandler(userAgent);
        this.inviteHandler = new InviteHandler(userAgent);
        this.cancelHandler = new CancelHandler(userAgent);
        this.byeHandler = new ByeHandler(userAgent);
        this.optionsHandler = new OptionsHandler(userAgent);
    }

    public void setChallengeManager(ChallengeManager challengeManager) {
        //init
        registerHandler.setChallengeManager(challengeManager);
        inviteHandler.setChallengeManager(challengeManager);
        byeHandler.setChallengeManager(challengeManager);
    }

    public void setSdpManager(SDPManager sdpManager) {
        inviteHandler.setSdpManager(sdpManager);
        optionsHandler.setSdpManager(sdpManager);
    }

    public ClientTransaction preProcessInvite(SipRequest sipRequest) {
        return null;
    }

    public ClientTransaction preProcessRegister(SipRequest sipRequest) {
        return null;
    }
}
