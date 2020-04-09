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
    
    Copyright 2009, 2010, 2012 Yohann Martineau 
*/

package com.xiangcai.im.base.voip.rtp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;

@Slf4j
public class Echo implements Runnable {

    public static final int BUFFER_SIZE = 2048;

    private DatagramSocket datagramSocket;
    private InetAddress remoteAddress;
    private int remotePort;
    private boolean isRunning;

    public Echo(DatagramSocket datagramSocket,
                String remoteAddress, int remotePort)
            throws UnknownHostException {
        this.datagramSocket = datagramSocket;
        this.remoteAddress = InetAddress.getByName(remoteAddress);
        this.remotePort = remotePort;
        isRunning = true;
    }

    @Override
    public void run() {
        try {
            while (isRunning) {
                byte[] buf = new byte[BUFFER_SIZE];
                DatagramPacket datagramPacket = new DatagramPacket(buf,
                        buf.length);
                try {
                    datagramSocket.receive(datagramPacket);
                } catch (SocketTimeoutException e) {
                    log.debug("echo socket timeout");
                    continue;
                }
                datagramPacket = new DatagramPacket(buf,
                        datagramPacket.getLength(), remoteAddress, remotePort);
                datagramSocket.send(datagramPacket);
            }
        } catch (IOException e) {
            log.error("input/output error", e);
        } finally {
            datagramSocket.close();
        }

    }

    public synchronized void stop() {
        isRunning = false;
    }
}
