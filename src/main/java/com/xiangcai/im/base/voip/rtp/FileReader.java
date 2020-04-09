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

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// To create an audio file for peers, you can use audacity:
//
// Edit > Preferences
//
// - Peripherals
//   - Channels: 1 (Mono)
// - Quality
//   - Sampling frequency of default: 8000 Hz
//   - Default Sample Format: 16 bits
//
// Validate
//
// Record audio
//
// File > Export
//
// - File name: test.raw
//
// Validate

@Slf4j
public class FileReader implements SoundSource {

    public final static int BUFFER_SIZE = 256;

    private FileInputStream fileInputStream;

    public FileReader(String fileName) {
        try {
            fileInputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            log.error("file not found: " + fileName, e);
        }
    }

    public synchronized void close() {
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                log.error("io exception", e);
            }
            fileInputStream = null;
        }
    }

    @Override
    public synchronized byte[] readData() {
        if (fileInputStream == null) {
            return null;
        }
        byte buffer[] = new byte[BUFFER_SIZE];
        try {
            if (fileInputStream.read(buffer) >= 0) {
                Thread.sleep(15);
                return buffer;
            } else {
                fileInputStream.close();
                fileInputStream = null;
            }
        } catch (IOException e) {
            log.error("io exception", e);
        } catch (InterruptedException e) {
            log.debug("file reader interrupted");
        }
        return null;
    }

}
