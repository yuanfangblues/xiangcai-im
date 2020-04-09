package com.xiangcai.im.base.voip.manager.request;

import com.xiangcai.im.base.voip.UserAgent;
import com.xiangcai.im.base.voip.manager.challenge.ChallengeManager;
import com.xiangcai.im.base.voip.sip.exp.SipUriSyntaxException;

/**
 * @author :元放
 * @date :2020-04-09 17:22
 **/
public class InitialRequestManager {

    private UserAgent userAgent;

    public InitialRequestManager(UserAgent userAgent) {
        this.userAgent = userAgent;
    }

    public void createInitialRequest(String requestUri, String method, String profileUri, String callId, String fromTag, ChallengeManager challengeManager) throws SipUriSyntaxException {

    }
}
