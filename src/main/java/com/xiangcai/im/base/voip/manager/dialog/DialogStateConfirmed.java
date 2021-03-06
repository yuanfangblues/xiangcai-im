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
    
    Copyright 2007, 2008, 2009, 2010 Yohann Martineau 
*/

package com.xiangcai.im.base.voip.manager.dialog;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DialogStateConfirmed extends DialogState {

    public DialogStateConfirmed(String id, Dialog dialog) {
        super(id, dialog);
    }

    @Override
    public void receivedOrSent101To199() {
        log.error(id + " invalid transition");
        throw new IllegalStateException();
    }

    @Override
    public void receivedOrSent2xx() {
        log.error(id + " invalid transition");
        throw new IllegalStateException();
    }

    @Override
    public void receivedOrSent300To699() {
        log.error(id + " invalid transition");
        throw new IllegalStateException();
    }

    @Override
    public void receivedOrSentBye() {
        DialogState nextState = dialog.TERMINATED;
        dialog.setState(nextState);
    }

}
