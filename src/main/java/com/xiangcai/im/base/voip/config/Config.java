/*
    This file is part of Peers, a java SIP softphone.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    Copyright 2010 Yohann Martineau 
*/

package com.xiangcai.im.base.voip.config;


import com.xiangcai.im.base.voip.rtp.MediaMode;
import com.xiangcai.im.base.voip.sip.SipURI;

import java.net.InetAddress;

/**
 * SIP, RTP, 基础配置
 */
public interface Config {

    void save();

    /**
     * 获取本地地址
     * @return
     */
    InetAddress getLocalInetAddress();

    /**
     * 获取公网地址
     * @return
     */
    InetAddress getPublicInetAddress();

    String getUserPart();

    String getDomain();

    String getPassword();

    SipURI getOutboundProxy();

    /**
     * 获取Sip端口号
     * @return
     */
    int getSipPort();

    MediaMode getMediaMode();

    boolean isMediaDebug();

    String getMediaFile();

    /**
     * 获取Rtp端口号
     * @return
     */
    int getRtpPort();

    String getAuthorizationUsername();

    void setLocalInetAddress(InetAddress inetAddress);

    void setPublicInetAddress(InetAddress inetAddress);

    void setUserPart(String userPart);

    void setDomain(String domain);

    void setPassword(String password);

    void setOutboundProxy(SipURI outboundProxy);

    void setSipPort(int sipPort);

    void setMediaMode(MediaMode mediaMode);

    void setMediaDebug(boolean mediaDebug);

    void setMediaFile(String mediaFile);

    void setRtpPort(int rtpPort);

    void setAuthorizationUsername(String authorizationUsername);

}
