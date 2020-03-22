package com.TerBiliLive.Function;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveRoom;
import com.TerBiliLive.Thr.ChargeNoticeS_Thr;
import com.TerBiliLive.Thr.GetSendBarrageList_Thr;
import com.TerBiliLive.Thr.ParsingBarrage_Thr;
import com.TerBiliLive.Thr.SendAdvertising_Thr;
import com.TerBiliLive.Utils.FileUtil;
import com.TerBiliLive.Utils.InOutPutUtil;

import javax.swing.*;

import static com.TerBiliLive.Ui.TerBiliLive_Adv_Ui.*;

public class Control_Fun {


    public static  boolean AYO = true ;
    public static  boolean AYO_GG = true ;
    //boolean AYO=true;
    String Parameter= "" ;
    ChargeNoticeS_Thr DT;
//    GetSendBarrageList_Thr GBT;


    public Control_Fun(){

    }


    /**
     * 保存基础信息
     */
    public void Preservation(){
        //创建目录
        FileUtil.createDir("Ter/data/");
        FileUtil.createDir("Ter/log/");
        FileUtil.createDir("Ter/Dm/");
        //写入数据
//      FileUtil.writeFile("RoomId", ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText());
//      FileUtil.writeFile("Cookie",ConfInfo.confData.getCookie());
//      FileUtil.writeFile("Second", GG_UiT_Second.getText());
//      FileUtil.writeFile("Text",GG_UiT_Text.getText());

        if(ConfInfo.dev){ FileUtil.writeFile("Cookie",ConfInfo.confData.getCookie());}

        ConfInfo.confData.setCookie(ConfInfo.confData.getCookie());
        ConfInfo.confData.setSecond( GG_UiT_Second.getText());
        ConfInfo.confData.setText(GG_UiT_Text.getText());
        ConfInfo.confData.setRoomId(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText());
//      ConfInfo.xmlUtil.writeData(); //关闭 xData.terda 输出
        ConfInfo.confData.writeConfData();

    }

    /**
     * 清除基础信息
     */
    public void Clear(){
        //创建目录
        FileUtil.createDir("Ter/data/");
        FileUtil.createDir("Ter/log/");
        FileUtil.createDir("Ter/Dm/");

        //写入数据
//        FileUtil.writeFile("RoomId", ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText());
        if(ConfInfo.dev){ FileUtil.writeFile("Cookie",""); }
//        FileUtil.writeFile("Second", ConfInfo.terBiliLive_adv_ui.GG_UiT_Second.getText());
//        FileUtil.writeFile("Text",ConfInfo.terBiliLive_adv_ui.GG_UiT_Text.getText());

        ConfInfo.confData.setCookie("");
        ConfInfo.confData.setSecond(GG_UiT_Second.getText());
        ConfInfo.confData.setText(GG_UiT_Text.getText());
        ConfInfo.confData.setRoomId(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText());
//      ConfInfo.xmlUtil.writeData(); //关闭 xData.terda 输出
        ConfInfo.confData.writeConfData();
        JOptionPane.showMessageDialog(null,"已经退出登录,请手动启动软件!");
        System.exit(0);

    }




    public void Connect(){

        DT= new ChargeNoticeS_Thr();
        ConfInfo.GBT =new GetSendBarrageList_Thr();
        ConfInfo.PBT =new ParsingBarrage_Thr();
        ConfInfo.liveRoom =new LiveRoom(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText().toString());
        ConfInfo.ChargeBarrageList.clear();
        DT.start(ConfInfo.liveRoom.room_id,true);
        ConfInfo.GBT.start();
        ConfInfo.PBT.start();
        ConfInfo.terBiliLive_control_ui.Control_UiB_Connect.setEnabled(false);
        ConfInfo.terBiliLive_control_ui.Control_UiB_Disconnect.setEnabled(true);

    }

    @SuppressWarnings("deprecation")
    public void Disconnect(){

        DT.stop();
        ConfInfo.GBT.stop();
        ConfInfo.PBT.stop();
        ConfInfo.terBiliLive_control_ui.Control_UiB_Connect.setEnabled(true);
        ConfInfo.terBiliLive_control_ui.Control_UiB_Disconnect.setEnabled(false);
        //DT.interrupt();








    }

    public void Start(){
//        InOutPutUtil.outPut("GT1");
        if(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText().equals("")){
            ConfInfo.terBiliLive_adv_ui.GG_UiT_State.setText("请填写直播间");
            return;
        }
        if(GG_UiT_Second.getText().equals("")){
            ConfInfo.terBiliLive_adv_ui.GG_UiT_State.setText("请填写时间");
            return;
        }
        if(GG_UiT_Text.getText().equals("")){
            ConfInfo.terBiliLive_adv_ui.GG_UiT_State.setText("请填写弹幕");
            return;
        }
        if(ConfInfo.sendAdvertising_thr==null) ConfInfo.sendAdvertising_thr = new SendAdvertising_Thr();
        ConfInfo.sendAdvertising_thr.second =Integer.parseInt(GG_UiT_Second.getText());
        GG_Ui_Start.setEnabled(false);
        GG_Ui_Suspend.setEnabled(true);

        AYO_GG=true;
//        if(ConfInfo.sendAdvertising_thr.isAlive()){
//            synchronized (ConfInfo.sendAdvertising_thr) {
//                ConfInfo.sendAdvertising_thr.notify();
//                InOutPutUtil.outPut("-----------------------广告姬重新激活-----------------------");
//            }
//        }else {
        InOutPutUtil.outPut("-----------------------广告姬线程启动-----------------------");
            ConfInfo.sendAdvertising_thr.start();
//        }


//        GT.start();









    }
    @SuppressWarnings("deprecation")
    public void Suspend(){
//        InOutPutUtil.outPut("GT2");
        GG_Ui_Start.setEnabled(true);
        GG_Ui_Suspend.setEnabled(false);
        GG_UiT_State.setText("暂停");
//        AYO_GG=false;

        ConfInfo.sendAdvertising_thr.stop();
        ConfInfo.sendAdvertising_thr=null;
        InOutPutUtil.outPut("-----------------------广告姬线程销毁-----------------------");
//        new Thread(new Runnable(){
//            public void run(){
//
//                synchronized (ConfInfo.sendAdvertising_thr) {
//                    try {
//                        ConfInfo.sendAdvertising_thr.wait();
//                        InOutPutUtil.outPut("-----------------------广告姬进入休眠-----------------------");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();












    }



}
