package com.TerBiliLive.Function;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.SendBarrageMap;
import com.TerBiliLive.Utils.DevLogUtil;

//import static com.TerBiliLive.Ui.TerBiliLive_SendBarrage_Ui;


public class HFJ_Fun {
    String Parameter= "" ;
    String pathUrl = "http://live.bilibili.com/msg/send";

    public HFJ_Fun() {
    }
    public HFJ_Fun(String msg) {
        new HFJ_Fun(msg,1);
    }

    public HFJ_Fun(String msg,int type){

        if(!ConfInfo.SBLT.isAlive()) ConfInfo.SBLT.start();//线程死亡启动线程

        if(msg.equals("")){
            msg =ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.getText();
            type = 2;
            if(msg.equals("")){
                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_State.setText("不能发送空的弹幕");
                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.setEnabled(true);
                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiB_Send.setEnabled(true);
                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.grabFocus();
                return;
            }
        }
        DevLogUtil.printf(msg);
        ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_State.setText("发送中");
        int ReplyLength=20;
        if (ConfInfo.terBiliLive_control_ui.Reply_30.isSelected())ReplyLength=30;
        if(msg.length()<ReplyLength){
            ConfInfo.SendBarrageList.add(new SendBarrageMap(msg,type));
            DevLogUtil.printf(msg);}
        else{
            while(msg.length()>ReplyLength){
                ConfInfo.SendBarrageList.add(new SendBarrageMap(msg.substring(0, ReplyLength),type));
                msg=msg.substring(ReplyLength);
            }
            ConfInfo.SendBarrageList.add(new SendBarrageMap(msg,type));
        }

        synchronized (ConfInfo.SBLT) {
            ConfInfo.SBLT.notify();
        }

        /* 使用JSON 获取返回值中的的信息
         * gs.GetSubString(RTData, "\"code\":", ",\"msg\""),  <——老方法获取值
         * CodingUtil.ascii2native(gs.GetSubString(RTData, "\"code\":", ",\"msg\""))  <——老方法获取值
         * JSONObject jsonObject = new JSONObject(RTData);<——新方法获取值
         *  jsonObject.getString("code")<——新方法获取值
         *
         *
         * */


    }
}