package com.xiangcai.im.base.voip.manager.request;

import com.xiangcai.im.base.voip.UserAgent;
import com.xiangcai.im.base.voip.manager.challenge.ChallengeManager;
import com.xiangcai.im.base.voip.manager.dialog.Dialog;

/**
 * @author :元放
 * @date :2020-04-09 17:23
 **/
public class MidDialogRequestManager {

    private UserAgent userAgent;

    public MidDialogRequestManager(UserAgent userAgent) {
        this.userAgent = userAgent;
    }

    public void generateMidDialogRequest(Dialog dialog, String methodBye, ChallengeManager challengeManager) {

    }
}
