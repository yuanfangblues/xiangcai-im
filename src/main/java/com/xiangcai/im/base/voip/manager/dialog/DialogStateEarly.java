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
public class DialogStateEarly extends DialogState {
    
    public DialogStateEarly(String id, Dialog dialog) {
        super(id, dialog);
    }
    
    @Override
    public void receivedOrSent101To199() {
        DialogState nextState = dialog.EARLY;
        dialog.setState(nextState);
    }
    
    @Override
    public void receivedOrSent2xx() {
        DialogState nextState = dialog.CONFIRMED;
        dialog.setState(nextState);
    }
    
    @Override
    public void receivedOrSent300To699() {
        DialogState nextState = dialog.TERMINATED;
        dialog.setState(nextState);
    }
    
    @Override
    public void receivedOrSentBye() {
        log.error(id + " invalid transition");
        throw new IllegalStateException();
    }
}
