/*
    This file is part of Peers, a java SIP softphone.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General  License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General  License for more details.

    You should have received a copy of the GNU General  License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    Copyright 2007, 2008, 2009, 2010 Yohann Martineau 
*/


package com.xiangcai.im.base.voip.sip;


import com.xiangcai.im.base.voip.sip.pack.SipRequest;
import com.xiangcai.im.base.voip.sip.pack.SipResponse;

/**
 * SIP 监听器
 */
 public interface SipListener {

     /**
      * UAC向 FS 发起注册
      *
      * @param sipRequest
      */
     void registering(SipRequest sipRequest);

     /**
      * UAC向 FS 注册成功
      * @param sipResponse
      */
     void registerSuccessful(SipResponse sipResponse);

     /**
      * UAC向 FS 注册失败
      * @param sipResponse
      */
     void registerFailed(SipResponse sipResponse);

     /**
      * 呼入
      * @param sipRequest
      * @param provResponse
      */
     void incomingCall(SipRequest sipRequest, SipResponse provResponse);

     /**
      * 对方挂断
      * @param sipRequest
      */
     void remoteHangup(SipRequest sipRequest);

     /**
      * 对方振铃
      * @param sipResponse
      */
     void ringing(SipResponse sipResponse);

     /**
      * 被叫者应答
      * @param sipResponse
      */
     void calleePickup(SipResponse sipResponse);

     /**
      * 出错
      * @param sipResponse
      */
     void error(SipResponse sipResponse);

}
