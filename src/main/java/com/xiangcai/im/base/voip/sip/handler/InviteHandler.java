package com.xiangcai.im.base.voip.sip.handler;

import com.xiangcai.im.base.voip.UserAgent;
import com.xiangcai.im.base.voip.manager.challenge.ChallengeManager;
import com.xiangcai.im.base.voip.sdp.SDPManager;

/**
 * @author :元放
 * @date :2020-04-09 17:12
 **/
public class InviteHandler {

    private UserAgent userAgent;

    public InviteHandler(UserAgent userAgent) {
        this.userAgent = userAgent;
    }

    public void setChallengeManager(ChallengeManager challengeManager) {

    }

    public void setSdpManager(SDPManager sdpManager) {

    }
}
