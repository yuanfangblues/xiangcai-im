package com.xiangcai.im.base.voip.sip.handler;

import com.xiangcai.im.base.voip.UserAgent;
import com.xiangcai.im.base.voip.sdp.SDPManager;

/**
 * @author :元放
 * @date :2020-04-09 17:13
 **/
public class OptionsHandler {

    private UserAgent userAgent;


    public OptionsHandler(UserAgent userAgent) {
        this.userAgent = userAgent;
    }

    public void setSdpManager(SDPManager sdpManager) {

    }
}
