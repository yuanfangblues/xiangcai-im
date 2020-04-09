package com.xiangcai.im.base.voip;

import com.xiangcai.im.base.voip.manager.request.InitialRequestManager;
import com.xiangcai.im.base.voip.manager.request.MidDialogRequestManager;
import com.xiangcai.im.base.voip.sip.RFC3261;

import java.util.ArrayList;

/**
 * client 处理呼出
 *
 * @author :元放
 * @date :2020-04-08 20:54
 **/
public class UAS {

    public final static ArrayList<String> SUPPORTED_METHODS;

    static {
        SUPPORTED_METHODS = new ArrayList<>();
        SUPPORTED_METHODS.add(RFC3261.METHOD_INVITE);
        SUPPORTED_METHODS.add(RFC3261.METHOD_ACK);
        SUPPORTED_METHODS.add(RFC3261.METHOD_CANCEL);
        SUPPORTED_METHODS.add(RFC3261.METHOD_OPTIONS);
        SUPPORTED_METHODS.add(RFC3261.METHOD_BYE);
    }

    public UAS(UserAgent userAgent, InitialRequestManager initialRequestManager, MidDialogRequestManager midDialogRequestManager) {

    }
}
