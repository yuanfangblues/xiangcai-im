package com.xiangcai.im.base.voip.sip.handler;

import com.xiangcai.im.base.voip.UserAgent;
import com.xiangcai.im.base.voip.manager.challenge.ChallengeManager;

/**
 * @author :元放
 * @date :2020-04-09 17:12
 **/
public class ByeHandler {

    private UserAgent userAgent;

    public ByeHandler(UserAgent userAgent) {
        this.userAgent = userAgent;
    }

    public void setChallengeManager(ChallengeManager challengeManager) {

    }
}
