package com.TerBiliLive.Thr;

import com.TerBiliLive.Function.HFJ_Fun;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Utiliy.DmLogUtil;
import com.TerBiliLive.Utiliy.TimeUtil;
import com.alibaba.fastjson.JSONObject;

import static com.TerBiliLive.Ui.TerBiliLive_Control_Ui.Control_UiT_RoomId;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatHour;

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
    public void start(String uname,String giftName){
        this.uname = uname;
        this.giftName = giftName;
        this.start();
    }

    @Override
    public void run() {
        super.run();
        if(giftName.equals("辣条")||giftName.equals("小猪爆竹")){
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

System.out.print(ConfInfo.integrated.get(uname+giftName));
        if (ConfInfo.Thank.equals("ok")) new HFJ_Fun("感谢 " + uname + " 赠送的 " + giftName +"*" +  ConfInfo.integrated.get(uname+giftName).getGiftNum() +" 喵~");
            putDM="礼物 ：" + TimeUtil.timeStamp2Date(ConfInfo.integrated.get(uname+giftName).getTimestamp(), null) + " $ " + " 感谢 " + uname + " 赠送 " + giftName + "*" + ConfInfo.integrated.get(uname+giftName).getGiftNum();

        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putDM, Control_UiT_RoomId.getText());
            ConfInfo.integrated.remove(uname+giftName);


        if (!putDM.equals("")) {
            ConfInfo.ChargeBarrageList.add(putDM);
            synchronized (ConfInfo.GBT) {
                ConfInfo.GBT.notify();
            }
        }



    }


}
