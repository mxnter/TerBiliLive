package com.TerBiliLive.Inlet;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.SendBarrageMap;
import com.TerBiliLive.Utils.DevLogUtil;

/**
 * 前置入口
 * 发送弹幕
 */
public class SendBarrage_Inlet {

    public SendBarrage_Inlet() {
    }

    public SendBarrage_Inlet(String msg) {
        new SendBarrage_Inlet(msg, 1);
    }

    public SendBarrage_Inlet(String msg, int type) {

        if (!ConfInfo.SBLT.isAlive()) ConfInfo.SBLT.start();//线程死亡启动线程
        ConfInfo.barrage.updateSendStatus(2); // 发送中
        if (msg.equals("")) {
            msg = ConfInfo.barrage.getSendText();
            type = 2;
            if (msg.equals("")) {
                ConfInfo.barrage.updateSendStatus(3); // 不能发送空的弹幕
                return;
            }
        }
        DevLogUtil.printf(msg);

        int ReplyLength = 20;
        if (ConfInfo.systemState.isSend30) ReplyLength = 30;
        if (msg.length() < ReplyLength) {
            ConfInfo.SendBarrageList.add(new SendBarrageMap(msg, type));
            DevLogUtil.printf(msg);
        } else {
            while (msg.length() > ReplyLength) {
                ConfInfo.SendBarrageList.add(new SendBarrageMap(msg.substring(0, ReplyLength), type));
                msg = msg.substring(ReplyLength);
            }
            ConfInfo.SendBarrageList.add(new SendBarrageMap(msg, type));
        }

        synchronized (ConfInfo.SBLT) {
            ConfInfo.barrage.updateSendStatus(1);
            ConfInfo.SBLT.notify();
        }
    }
}
