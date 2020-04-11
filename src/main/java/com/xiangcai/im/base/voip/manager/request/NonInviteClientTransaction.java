package com.xiangcai.im.base.voip.manager.request;

import com.xiangcai.im.base.voip.manager.transaction.ClientTransaction;
import com.xiangcai.im.base.voip.manager.transaction.TransactionManager;
import com.xiangcai.im.base.voip.manager.transport.TransportManager;
import com.xiangcai.im.base.voip.sip.RFC3261;
import com.xiangcai.im.base.voip.sip.SipHeaderFieldName;
import com.xiangcai.im.base.voip.sip.SipHeaderFieldValue;
import com.xiangcai.im.base.voip.sip.SipHeaderParamName;
import com.xiangcai.im.base.voip.sip.pack.SipRequest;
import com.xiangcai.im.base.voip.sip.pack.SipResponse;

import java.net.InetAddress;
import java.util.Timer;

/**
 * @author :元放
 * @date :2020-04-10 20:32
 **/
public class NonInviteClientTransaction extends NonInviteTransaction
        implements ClientTransaction, SipClientTransportUser {

    protected String transport;

    protected int nbRetrans;

    public NonInviteClientTransaction(String branchId, InetAddress inetAddress,
                                      int port, String transport, SipRequest sipRequest,
                                      ClientTransactionUser transactionUser, Timer timer,
                                      TransportManager transportManager,
                                      TransactionManager transactionManager) {
        super(branchId, sipRequest.getMethod(), timer, transportManager, transactionManager);

        this.transport = transport;

        SipHeaderFieldValue via = new SipHeaderFieldValue("");
        via.addParam(new SipHeaderParamName(RFC3261.PARAM_BRANCH), branchId);
        sipRequest.getSipHeaders().add(new SipHeaderFieldName(RFC3261.HDR_VIA), via, 0);

        nbRetrans = 0;
    }

    @Override
    public void requestTransportError(SipRequest sipRequest, Exception e) {

    }

    @Override
    public void responseTransportError(Exception e) {

    }

    @Override
    public void receivedResponse(SipResponse sipResponse) {

    }

    @Override
    public void start() {

    }

    @Override
    public String getContact() {
        return null;
    }
}
