package com.TerBiliLive.Thr;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Inlet.SendBarrage_Inlet;
import com.TerBiliLive.Utils.DmLogUtil;
import com.TerBiliLive.Utils.InOutPutUtil;
import com.TerBiliLive.Utils.LogUtil;
import com.TerBiliLive.Utils.TimeUtil;

import static com.TerBiliLive.Utils.TimeUtil.*;

/**
 * CODE IS POETRY
 * @Nmae ：礼物整合
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 11:51 2018/11/4
 * @作用 用于整合5秒内赠送的礼物，防止刷屏
 */


public class GiftIntegration_Thr extends Thread {

    String uname = null;
    String giftName = null;
    String putDM = null;
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
            LogUtil.putLogException("异常日志：礼物整合 睡眠异常"+uname+"--"+giftName,"GiftIntegration_Thr");
            e.printStackTrace();
        }

        InOutPutUtil.outPut(ConfInfo.integrated.get(uname+giftName));//查看礼物整合的数据
        if (ConfInfo.systemState.isThank){
            if(ConfInfo.systemState.isIgnoreSpicystrip){
                if(giftName.equals("辣条")){
                    InOutPutUtil.outPut("_______________________忽略辣条感谢_____________________________");
                }else{
                    new SendBarrage_Inlet("感谢 " + uname + " 赠送的 " + giftName +"*" +  ConfInfo.integrated.get(uname+giftName).getGiftNum() +" 喵~",1);
                }
            }else{
                new SendBarrage_Inlet("感谢 " + uname + " 赠送的 " + giftName +"*" +  ConfInfo.integrated.get(uname+giftName).getGiftNum() +" 喵~",1);
            }

        }
            putDM="整合"+" | "+TimeUtil.timeStamp2Date(ConfInfo.integrated.get(uname+giftName).getTimestamp(), null)+" | "+ " 感谢 " + uname + " 赠送 " + giftName + "*" + ConfInfo.integrated.get(uname+giftName).getGiftNum();
            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putDM, ConfInfo.barrage.getRoomid());
            ConfInfo.integrated.remove(uname+giftName);


        if (!putDM.equals("")) {
            ConfInfo.ChargeBarrageList.add(putDM);
            synchronized (ConfInfo.GBT) {
//                InOutPutUtil.outPut(getFormat()+" -----------------------显示弹幕数据 唤醒");
                ConfInfo.GBT.notify();
            }
        }

        if (!isInterrupted()) {
            interrupt();
        }


    }

    public static void sendDm(String key){

        InOutPutUtil.outPut(ConfInfo.integrated.get(key));//查看礼物整合的数据
        String uname = ConfInfo.integrated.get(key).getUname();
        String giftName = ConfInfo.integrated.get(key).getGiftName();
        String putDM = "";

        if (ConfInfo.systemState.isThank){
            if(ConfInfo.systemState.isIgnoreSpicystrip){
                if(giftName.equals("辣条")){
                    InOutPutUtil.outPut("_______________________忽略辣条感谢_____________________________");
                }else{
                    new SendBarrage_Inlet("感谢 " + uname + " 赠送的 " + giftName +"*" +  ConfInfo.integrated.get(uname+giftName).getGiftNum() +" 喵~",1);
                }
            }else{
                new SendBarrage_Inlet("感谢 " + uname + " 赠送的 " + giftName +"*" +  ConfInfo.integrated.get(uname+giftName).getGiftNum() +" 喵~",1);
            }

        }
        putDM="整合"+" | "+TimeUtil.timeStamp2Date(ConfInfo.integrated.get(uname+giftName).getTimestamp(), null)+" | "+ " 感谢 " + uname + " 赠送 " + giftName + "*" + ConfInfo.integrated.get(uname+giftName).getGiftNum();
        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putDM, ConfInfo.barrage.getRoomid());
        ConfInfo.integrated.get(key).getTimer().cancel();
        ConfInfo.integrated.remove(uname+giftName);


        if (!putDM.equals("")) {
            ConfInfo.ChargeBarrageList.add(putDM);
            synchronized (ConfInfo.GBT) {
//                InOutPutUtil.outPut(getFormat()+" -----------------------显示弹幕数据 唤醒");
                ConfInfo.GBT.notify();
            }
        }
    }


}
