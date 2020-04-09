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
    
    Copyright 2012 Yohann Martineau
*/

package com.xiangcai.im.base.voip.rtp;

public interface SoundSource {

    /**
     * 8K16位, 单通道，小端模式
     *
     * read raw data linear PCM 8kHz, 16 bits signed, mono-channel, little endian
     * @return
     */
    byte[] readData();

}
