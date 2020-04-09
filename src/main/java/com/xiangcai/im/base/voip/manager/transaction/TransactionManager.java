package com.xiangcai.im.base.voip.manager.transaction;

import com.xiangcai.im.base.voip.manager.transport.TransportManager;
import com.xiangcai.im.base.voip.sip.RFC3261;
import com.xiangcai.im.base.voip.sip.SipHeaderFieldName;
import com.xiangcai.im.base.voip.sip.SipHeaderFieldValue;
import com.xiangcai.im.base.voip.sip.SipHeaderParamName;
import com.xiangcai.im.base.voip.sip.pack.SipMessage;
import com.xiangcai.im.base.voip.sip.pack.SipRequest;
import com.xiangcai.im.base.voip.utils.Utils;

import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :元放
 * @date :2020-04-09 16:41
 **/
public class TransactionManager {

    protected Timer timer;


    // TODO remove client transactions when they reach terminated state
    // TODO check that server transactions are removed in all transitions to terminated
    private ConcurrentHashMap<String, ClientTransaction> clientTransactions;
    private ConcurrentHashMap<String, ServerTransaction> serverTransactions;

    private TransportManager transportManager;

    public TransactionManager() {
        clientTransactions = new ConcurrentHashMap<>();
        serverTransactions = new ConcurrentHashMap<>();
        timer = new Timer(TransactionManager.class.getSimpleName()
                + " " + Timer.class.getSimpleName());
    }

    public ServerTransaction getServerTransaction(SipMessage sipMessage) {
        //// TODO: 2020-04-09 这里其实是获取对应请求的 transaction, 剥离解析sip包的逻辑
        SipHeaderFieldValue via = Utils.getTopVia(sipMessage);
        String branchId = via.getParam(new SipHeaderParamName(
                RFC3261.PARAM_BRANCH));
        String method;
        if (sipMessage instanceof SipRequest) {
            method = ((SipRequest)sipMessage).getMethod();
        } else {
            String cseq = sipMessage.getSipHeaders().get(
                    new SipHeaderFieldName(RFC3261.HDR_CSEQ)).toString();
            method = cseq.substring(cseq.lastIndexOf(' ') + 1);
        }
        if (RFC3261.METHOD_ACK.equals(method)) {
            method = RFC3261.METHOD_INVITE;
//            InviteServerTransaction inviteServerTransaction =
//                (InviteServerTransaction)
//                serverTransactions.get(getTransactionId(branchId, method));
//            if (inviteServerTransaction == null) {
//                Logger.debug("received ACK for unknown transaction" +
//                		" branchId = " + branchId + ", method = " + method);
//            } else {
//                SipResponse sipResponse =
//                    inviteServerTransaction.getLastResponse();
//                if (sipResponse == null) {
//                    Logger.debug("received ACK but no response sent " +
//                    		"branchId = " + branchId + ", method = " + method);
//                } else {
//                    int statusCode = sipResponse.getStatusCode();
//                    if (statusCode >= RFC3261.CODE_MIN_SUCCESS &&
//                            statusCode < RFC3261.CODE_MIN_REDIR) {
//                        // success response => ACK is not in INVITE server
//                        // transaction
//                        return null;
//                    } else {
//                        // error => ACK belongs to INVITE server transaction
//                        return inviteServerTransaction;
//                    }
//                }
//            }
            // TODO if positive response, ACK does not belong to transaction
            // retrieve transaction and take responses from transaction
            // and check if a positive response has been received
            // if it is the case, a new standalone transaction must be created
            // for the ACK
        }
        return serverTransactions.get(getTransactionId(branchId, method));
    }

    private String getTransactionId(String branchId, String method) {
        StringBuffer buf = new StringBuffer();
        buf.append(branchId);
        buf.append(Transaction.ID_SEPARATOR);
        buf.append(method);
        return buf.toString();
    }


    public ClientTransaction getClientTransaction(SipMessage sipMessage) {
        SipHeaderFieldValue via = Utils.getTopVia(sipMessage);
        String branchId = via.getParam(new SipHeaderParamName(
                RFC3261.PARAM_BRANCH));
        String cseq = sipMessage.getSipHeaders().get(
                new SipHeaderFieldName(RFC3261.HDR_CSEQ)).toString();
        String method = cseq.substring(cseq.lastIndexOf(' ') + 1);
        return clientTransactions.get(getTransactionId(branchId, method));
    }


    public void setTransportManager(TransportManager transportManager) {
        this.transportManager = transportManager;
    }
}
