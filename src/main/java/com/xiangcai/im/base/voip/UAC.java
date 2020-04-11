package com.xiangcai.im.base.voip;

import com.xiangcai.im.base.voip.manager.request.InitialRequestManager;
import com.xiangcai.im.base.voip.manager.request.MidDialogRequestManager;
import com.xiangcai.im.base.voip.sip.RFC3261;
import com.xiangcai.im.base.voip.sip.SipListener;
import com.xiangcai.im.base.voip.sip.exp.SipUriSyntaxException;
import com.xiangcai.im.base.voip.sip.pack.SipRequest;
import com.xiangcai.im.base.voip.utils.Utils;

/**
 * client 处理呼出
 *
 * @author :元放
 * @date :2020-04-08 20:54
 **/
public class UAC {

    private UserAgent userAgent;

    private String profileUri;

    private String registerCallID;

    public UAC(UserAgent userAgent, InitialRequestManager initialRequestManager, MidDialogRequestManager midDialogRequestManager) {

    }

    public SipRequest register() throws SipUriSyntaxException {
        String domain = userAgent.getDomain();

        String requestUri = RFC3261.SIP_SCHEME + RFC3261.SCHEME_SEPARATOR
                + domain;
        profileUri = RFC3261.SIP_SCHEME + RFC3261.SCHEME_SEPARATOR
                + userAgent.getUserpart() + RFC3261.AT + domain;
        registerCallID = Utils.generateCallID(
                userAgent.getConfig().getLocalInetAddress());

        SipRequest sipRequest = userAgent.getInitialRequestManager().createInitialRequest(
                requestUri, RFC3261.METHOD_REGISTER, profileUri,
                registerCallID);

        SipListener sipListener = userAgent.getSipListener();
        if (sipListener != null) {
            sipListener.registering(sipRequest);
        }

        return sipRequest;
    }

    public void unregister() {


    }

    public SipRequest invite(String requestUri, String callId) {
        return null;
    }
}
