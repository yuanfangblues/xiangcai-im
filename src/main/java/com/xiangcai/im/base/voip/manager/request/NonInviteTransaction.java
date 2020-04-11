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

package com.xiangcai.im.base.voip.manager.request;

import com.xiangcai.im.base.voip.manager.transaction.TransactionManager;
import com.xiangcai.im.base.voip.manager.transport.TransportManager;

import java.util.Timer;

public abstract class NonInviteTransaction extends Transaction {

    protected NonInviteTransaction(String branchId, String method, Timer timer,
                                   TransportManager transportManager,
                                   TransactionManager transactionManager ) {
        super(branchId, method, timer, transportManager, transactionManager);
    }

}
