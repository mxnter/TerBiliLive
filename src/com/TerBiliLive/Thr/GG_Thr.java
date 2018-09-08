package com.TerBiliLive.Thr;

import com.TerBiliLive.Function.Control_Fun;
import com.TerBiliLive.Function.GG_Fun;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveInfo;
import com.TerBiliLive.Info.LiveRoom;
import com.TerBiliLive.TerBiliLive.SendPost;
import com.TerBiliLive.Ui.TerBiliLive_Control_Ui;
import com.TerBiliLive.Ui.TerBiliLive_GG_Ui;
import com.TerBiliLive.Utiliy.DmUtil;
import com.TerBiliLive.Utiliy.LogUtil;

import static com.TerBiliLive.Ui.TerBiliLive_GG_Ui.GG_UiT_State;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatHour;

public class GG_Thr extends Thread {


    public boolean AYO = true;
    String Parameter = "";
    TerBiliLive_Control_Ui C = new TerBiliLive_Control_Ui(Parameter);
    TerBiliLive_GG_Ui GG = new TerBiliLive_GG_Ui(Parameter);
    SendPost SP = new SendPost();
    DmUtil DU = new DmUtil();
    String putDMJY = null;
    public int second;
    String LiveOpen = "open";



    @Override
    public void run() {
//        System.out.println("GT3");
        int i = 1;
       // GG_Fun GF =

        while (AYO) {

            AYO = Control_Fun.AYO_GG;
            if (!AYO) GG_UiT_State.setText("已暂停");


            LiveRoom liveRoom =new LiveRoom(C.Control_UiT_RoomId.getText().toString());
            LiveInfo LI = new LiveInfo(liveRoom.room_id,ConfInfo.cookie);




                //JSONObject jsonObject = new JSONObject(RTData);
//                System.out.println(LI.getState());

                    switch (LI.getState()) {

                        case "LIVE":
                            new GG_Fun();

                            break;
                        case "ROUND":
                            GG.GG_UiT_State.setText(getFormatHour() + "轮播中，等待验证" + "<" +LI.getState()+  ">");
                            if (!LiveOpen.equals("ROUND")) {
                                LogUtil.putLog(getFormatDay(), getFormatHour(), "轮播中，等待验证"  + "<" + LI.getState()+ ">"+ "\n", GG.ProjectName);

                            }
                            LiveOpen = "ROUND";
                            break;
                        case "PREPARING":
                            GG.GG_UiT_State.setText(getFormatHour() + "直播暂停或准备，等待验证" + "<" + LI.getState()+">");

                            if (!LiveOpen.equals("ROUND")) {
                                LogUtil.putLog(getFormatDay(), getFormatHour(), "直播暂停或准备，等待验证"  + "<" +LI.getState()+ ">" + "\n", GG.ProjectName);

                            }
                            LiveOpen = "ROUND";
                            break;
                        default:
                            GG.GG_UiT_State.setText(getFormatHour() + "读取直播状态失败 ：" + "<" +LI.getState()+">");
                            if (!LiveOpen.equals("OPEN")) {
                                LogUtil.putLog(getFormatDay(), getFormatHour(), "读取失败" + "<"  +LI.getState()+ ">" + "\n", GG.ProjectName);

                            }
                            LiveOpen = "OPEN";
                            break;

                    }

            /*线程停止运行的设计*/
            AYO = Control_Fun.AYO_GG;
           // System.out.println(AYO);
            if (!AYO) GG_UiT_State.setText("已暂停");
            //String RTData = GG_Fun.RTData;

            try {
                Thread.sleep(second * 1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            AYO = Control_Fun.AYO_GG;
            if (!AYO) GG_UiT_State.setText("已暂停");


           // super.run();
        }

    }
}
