package com.TerBiliLive.Thr;

import com.TerBiliLive.Function.HFJ_Fun;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Utiliy.TimeUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * CODE IS POETRY
 * @Nmae ：礼物整合
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 11:51 2018/11/4
 * @作用 用于整合5秒内赠送的礼物，防止刷屏
 */


public class GiftIntegration_Thr extends Thread {

    String uname;
    String giftName;
    String putDM;
    String  timestamp;
    JSONObject giftData;
    public void start(String uname,String giftName,JSONObject giftData){
        timestamp = giftData.getString("timestamp");
        this.uname = uname;
        this.giftName = giftName;
        this.start();
    }

    @Override
    public void run() {
        super.run();
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (ConfInfo.Thank.equals("ok")) new HFJ_Fun("感谢 " + uname + " 赠送的 " + giftName +"*" +  ConfInfo.lt_lt.get(uname) +" 喵~");
        putDM = "礼物 ：" + TimeUtil.timeStamp2Date(timestamp, null) + " $ " + " 感谢 " + uname + " 赠送 " + giftName + "*" + ConfInfo.lt_lt.get(uname);
        ConfInfo.lt_lt.remove(uname);
    }


}
