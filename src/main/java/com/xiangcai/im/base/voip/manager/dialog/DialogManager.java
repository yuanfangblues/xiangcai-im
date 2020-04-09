package com.xiangcai.im.base.voip.manager.dialog;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :元放
 * @date :2020-04-08 21:00
 **/
public class DialogManager {

    private ConcurrentHashMap<String, Dialog> dialogs;

    public DialogManager() {
        dialogs = new ConcurrentHashMap<>();
    }

    public Dialog getDialog(String callId) {
        return null;
    }
}
