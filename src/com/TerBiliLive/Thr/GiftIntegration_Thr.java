package com.TerBiliLive.Thr;

import com.TerBiliLive.Function.HFJ_Fun;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Utils.DmLogUtil;
import com.TerBiliLive.Utils.LogUtil;
import com.TerBiliLive.Utils.TimeUtil;

import static com.TerBiliLive.Ui.TerBiliLive_Control_Ui.Control_UiT_RoomId;
import static com.TerBiliLive.Utils.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utils.TimeUtil.getFormatHour;

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
//        if(giftName.equals("辣条")||giftName.equals("小猪爆竹")){
//            try {
//                sleep(5000);
//            } catch (InterruptedException e) {
//                LogUtil.putLog(getFormatDay(), getFormatHour(), "异常日志：礼物整合 睡眠异常"+uname+"--"+giftName+ "\n", "Exception","Exception");
//                e.printStackTrace();
//            }
//        }else{
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                LogUtil.putLog(getFormatDay(), getFormatHour(), "异常日志：礼物整合 睡眠异常"+uname+"--"+giftName+ "\n", "Exception","Exception");
//                e.printStackTrace();
//            }
//        }
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            LogUtil.putLog(getFormatDay(), getFormatHour(), "异常日志：礼物整合 睡眠异常"+uname+"--"+giftName+ "\n", "Exception","Exception");
            e.printStackTrace();
        }

        System.out.println(ConfInfo.integrated.get(uname+giftName));//查看礼物整合的数据
        if (ConfInfo.Thank.equals("ok")){
            if(ConfInfo.terBiliLive_control_ui.Reply_NeglectSpicy.isSelected()){
                if(giftName.equals("辣条")){
                    System.out.println("_______________________忽略辣条感谢_____________________________");
                }else{
                    new HFJ_Fun("感谢 " + uname + " 赠送的 " + giftName +"*" +  ConfInfo.integrated.get(uname+giftName).getGiftNum() +" 喵~",1);
                }
            }else{
                new HFJ_Fun("感谢 " + uname + " 赠送的 " + giftName +"*" +  ConfInfo.integrated.get(uname+giftName).getGiftNum() +" 喵~",1);
            }

        }
            putDM="整合礼物 ：" + TimeUtil.timeStamp2Date(ConfInfo.integrated.get(uname+giftName).getTimestamp(), null) + " $ " + " 感谢 " + uname + " 赠送 " + giftName + "*" + ConfInfo.integrated.get(uname+giftName).getGiftNum();
            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putDM, Control_UiT_RoomId.getText());
            ConfInfo.integrated.remove(uname+giftName);


        if (!putDM.equals("")) {
            ConfInfo.ChargeBarrageList.add(putDM);
            synchronized (ConfInfo.GBT) {
                ConfInfo.GBT.notify();
            }
        }

        if (!isInterrupted()) {
            interrupt();
        }


    }


}
