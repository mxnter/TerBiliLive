package com.TerBiliLive.Thr;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveInfo;
import com.TerBiliLive.Utils.LogUtil;

/**
 * CODE IS POETRY
 * @Nmae ：发送定时弹幕线程s
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 11:51 2018/11/4
 */

public class SendAdvertising_Thr extends Thread {


    public int second;
    String LiveOpen = "open";



    @Override
    public void run() {
//        InOutPutUtil.outPut("GT3");
        int i = 1;
        // GG_Fun GF =

        while (true) {

//            AYO = Control_Fun.AYO_GG;
//            if (!AYO) GG_UiT_State.setText("已暂停");


//            if (ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText().equals("")){
//                ConfInfo.putShowUtil.PutDMUtil("\n ****** 警告：请填写直播间ID ******\n");
//                return;
//            }
//            ConfInfo.liveRoom.getLiveRoom(TerBiliLive_Control_Ui.Control_UiT_RoomId.getText());
            LiveInfo LI = new LiveInfo(ConfInfo.liveRoom.getRoom_id(),ConfInfo.confData.getCookie());




            //JSONObject jsonObject = new JSONObject(RTData);
//                InOutPutUtil.outPut(LI.getState());

            switch (LI.getState()) {

                case "LIVE":
//                    new GG_Fun();
                    break;
                case "ROUND":
//                    TerBiliLive_Adv_Ui.GG_UiT_State.setText(getFormatHour() + "轮播中，等待验证" + "<" +LI.getState()+  ">");
                    if (!LiveOpen.equals("ROUND")) {
                        LogUtil.putLogTimingBarrage("轮播中，等待验证"  + "<" + LI.getState()+ ">","SendAdvertising_Thr");

                    }
                    LiveOpen = "ROUND";
                    break;
                case "PREPARING":
//                    TerBiliLive_Adv_Ui.GG_UiT_State.setText(getFormatHour() + "直播暂停或准备，等待验证" + "<" + LI.getState()+">");

                    if (!LiveOpen.equals("ROUND")) {
                        LogUtil.putLogTimingBarrage("直播暂停或准备，等待验证"  + "<" +LI.getState()+ ">","SendAdvertising_Thr");

                    }
                    LiveOpen = "ROUND";
                    break;
                default:
//                    TerBiliLive_Adv_Ui.GG_UiT_State.setText(getFormatHour() + "读取直播状态失败 ：" + "<" +LI.getState()+">");
                    if (!LiveOpen.equals("OPEN")) {
                        LogUtil.putLogTimingBarrage("读取失败" + "<"  +LI.getState()+ ">","SendAdvertising_Thr");

                    }
                    LiveOpen = "OPEN";
                    break;

            }
            /*线程停止运行的设计*/
//            AYO = Control_Fun.AYO_GG;
//           // InOutPutUtil.outPut(AYO);
//            if (!AYO) GG_UiT_State.setText("已暂停");
            //String RTData = GG_Fun.RTData;

            try {
                Thread.sleep(second * 1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//            AYO = Control_Fun.AYO_GG;
//            if (!AYO) GG_UiT_State.setText("已暂停");


            // super.run();
        }

    }
}
