package com.xiangcai.im.base.voip.manager.request;

import com.xiangcai.im.base.voip.UserAgent;
import com.xiangcai.im.base.voip.manager.challenge.MessageInterceptor;
import com.xiangcai.im.base.voip.manager.transaction.ClientTransaction;
import com.xiangcai.im.base.voip.sip.*;
import com.xiangcai.im.base.voip.sip.exp.SipUriSyntaxException;
import com.xiangcai.im.base.voip.sip.pack.SipRequest;
import com.xiangcai.im.base.voip.utils.NameAddress;
import com.xiangcai.im.base.voip.utils.Utils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author :元放
 * @date :2020-04-09 17:22
 **/
@Slf4j
public class InitialRequestManager {

    private UserAgent userAgent;

    public InitialRequestManager(UserAgent userAgent) {
        this.userAgent = userAgent;
    }

    public void createInitialRequest(String requestUri, String method, String profileUri, String callId, String fromTag) throws SipUriSyntaxException {

    }


    public SipRequest createInitialRequest(String requestUri, String method, String profileUri, String callId) throws SipUriSyntaxException {
        return createInitialRequest(requestUri, method, profileUri, callId,
                null, null);
    }

    public SipRequest createInitialRequest(String requestUri, String method,
                                           String profileUri, String callId, String fromTag,
                                           MessageInterceptor messageInterceptor)
            throws SipUriSyntaxException {

        //生成req
        SipRequest sipRequest = getGenericRequest(requestUri, method,
                profileUri, callId, fromTag);

        setRouterHeader(sipRequest);


        ClientTransaction clientTransaction = null;
        if (RFC3261.METHOD_INVITE.equals(method)) {
            clientTransaction = userAgent.getSipHandlerManager().preProcessInvite(sipRequest);
        } else if (RFC3261.METHOD_REGISTER.equals(method)) {
            clientTransaction = userAgent.getSipHandlerManager().preProcessRegister(sipRequest);
        }
        createInitialRequestEnd(sipRequest, clientTransaction, profileUri,
                messageInterceptor, true);
        return sipRequest;
    }

    private void createInitialRequestEnd(SipRequest sipRequest, ClientTransaction clientTransaction, String profileUri, MessageInterceptor messageInterceptor, boolean addContact) {
        if (clientTransaction == null) {
            log.error("method not supported");
            return;
        }
        if (addContact) {
            addContact(sipRequest, clientTransaction.getContact(), profileUri);
        }

        if (messageInterceptor != null) {
            messageInterceptor.postProcess(sipRequest);
        }


        // TODO create message receiver on client transport port
        clientTransaction.start();
    }


    private void addContact(SipRequest sipRequest, String contactEnd,
                            String profileUri) {
        SipHeaders sipHeaders = sipRequest.getSipHeaders();



        //Contact

        StringBuffer contactBuf = new StringBuffer();
        contactBuf.append(RFC3261.SIP_SCHEME);
        contactBuf.append(RFC3261.SCHEME_SEPARATOR);
        String userPart = Utils.getUserPart(profileUri);
        contactBuf.append(userPart);
        contactBuf.append(RFC3261.AT);
        contactBuf.append(contactEnd);

        NameAddress contactNA = new NameAddress(contactBuf.toString());
        SipHeaderFieldValue contact =
                new SipHeaderFieldValue(contactNA.toString());
        sipHeaders.add(new SipHeaderFieldName(RFC3261.HDR_CONTACT),
                new SipHeaderFieldValue(contact.toString()));
    }

    private void setRouterHeader(SipRequest sipRequest) {
        SipURI outboundProxy = userAgent.getOutboundProxy();
        if (outboundProxy != null) {
            NameAddress outboundProxyNameAddress =
                    new NameAddress(outboundProxy.toString());
            sipRequest.getSipHeaders().add(new SipHeaderFieldName(RFC3261.HDR_ROUTE),
                    new SipHeaderFieldValue(outboundProxyNameAddress.toString()), 0);
        }
    }

    private SipRequest getGenericRequest(String requestUri, String method, String profileUri, String callId, String fromTag) throws SipUriSyntaxException {

        SipRequest request = new SipRequest(method, new SipURI(requestUri));

        SipHeaders headers = request.getSipHeaders();
        Utils.addCommonHeaders(headers);

        //To
        NameAddress to = new NameAddress(requestUri);
        headers.add(new SipHeaderFieldName(RFC3261.HDR_TO),
                new SipHeaderFieldValue(to.toString()));

        //From
        NameAddress fromNA = new NameAddress(profileUri);

        SipHeaderFieldValue from = new SipHeaderFieldValue(fromNA.toString());
        from.addParam(new SipHeaderParamName(RFC3261.PARAM_TAG), getLocalFromTag(fromTag));

        headers.add(new SipHeaderFieldName(RFC3261.HDR_FROM), from);

        //Call-ID
        headers.add(new SipHeaderFieldName(RFC3261.HDR_CALLID),
                new SipHeaderFieldValue(getLocalCallId(callId)));

        //CSeq
        headers.add(new SipHeaderFieldName(RFC3261.HDR_CSEQ),
                new SipHeaderFieldValue(userAgent.generateCSeq(method)));

        return request;
    }

    private String getLocalFromTag(String fromTag) {
        String localFromTag;
        if (fromTag != null) {
            localFromTag = fromTag;
        } else {
            localFromTag = Utils.generateTag();
        }
        return localFromTag;
    }

    private String getLocalCallId(String callId) {
        String localCallId;
        if (callId != null) {
            localCallId = callId;
        } else {
            localCallId = Utils.generateCallID(
                    userAgent.getConfig().getLocalInetAddress());
        }
        return localCallId;
    }
}
