package com.TerBiliLive.Thr;

import com.TerBiliLive.Function.HFJ_Fun;
import com.TerBiliLive.Info.ConfInfo;

/**
 * CODE IS POETRY
 * @Nmae ：辣条整合
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 11:51 2018/11/4
 * @作用 用于整合5秒内赠送的辣条，防止刷屏
 */


public class SpicyIntegration_Thr extends Thread {

    String uname;String giftName;
    public void start(String uname,String giftName){
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

        if (ConfInfo.Thank.equals("ok")) new HFJ_Fun("感谢 " + uname + " 赠送的 " + giftName +"*" +  ConfInfo.lt_lt.get(uname) +" 喵~",1);
        ConfInfo.lt_lt.remove(uname);
    }


}
