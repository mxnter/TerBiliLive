package com.github.TerBiliLive.Function;

import com.github.TerBiliLive.Info.ConfInfo;
import com.github.TerBiliLive.Info.LiveRoom;
import com.github.TerBiliLive.Thr.ChargeNoticeS_Thr;
import com.github.TerBiliLive.Thr.GetSendBarrageList_Thr;
import com.github.TerBiliLive.Thr.ParsingBarrage_Thr;
import com.github.TerBiliLive.Thr.SendAdvertising_Thr;
import com.github.TerBiliLive.Utiliy.FileUtil;
import com.github.TerBiliLive.Ui.TerBiliLive_Adv_Ui;

public class Control_Fun {


    public static  boolean AYO = true ;
    public static  boolean AYO_GG = true ;
    //boolean AYO=true;
    String Parameter= "" ;
    ChargeNoticeS_Thr DT;
//    GetSendBarrageList_Thr GBT;


    public Control_Fun(){





    }



    public  void Preservation(){


        //创建目录
        FileUtil.createDir("Ter/data/");
        FileUtil.createDir("Ter/log/");
        FileUtil.createDir("Ter/Dm/");
        //写入数据
        FileUtil.writeFile("RoomId", ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText());
//        FileUtil.writeFile("Cookie",ConfInfo.cookie);
        FileUtil.writeFile("Second", TerBiliLive_Adv_Ui.GG_UiT_Second.getText());
        FileUtil.writeFile("Text", TerBiliLive_Adv_Ui.GG_UiT_Text.getText());


        ConfInfo.confData.setCookie(ConfInfo.cookie);
        ConfInfo.confData.setSecond( TerBiliLive_Adv_Ui.GG_UiT_Second.getText());
        ConfInfo.confData.setText(TerBiliLive_Adv_Ui.GG_UiT_Text.getText());
        ConfInfo.confData.setRoomId(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText());
        ConfInfo.xmlUtil.writeData();
        ConfInfo.jsonUtil.writeData();



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
//        System.out.println("GT1");
        if(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText().equals("")){
            ConfInfo.terBiliLive_adv_ui.GG_UiT_State.setText("请填写直播间");
            return;
        }
        if(TerBiliLive_Adv_Ui.GG_UiT_Second.getText().equals("")){
            ConfInfo.terBiliLive_adv_ui.GG_UiT_State.setText("请填写时间");
            return;
        }
        if(TerBiliLive_Adv_Ui.GG_UiT_Text.getText().equals("")){
            ConfInfo.terBiliLive_adv_ui.GG_UiT_State.setText("请填写弹幕");
            return;
        }
        if(ConfInfo.sendAdvertising_thr==null) ConfInfo.sendAdvertising_thr = new SendAdvertising_Thr();
        ConfInfo.sendAdvertising_thr.second =Integer.parseInt(TerBiliLive_Adv_Ui.GG_UiT_Second.getText());
        TerBiliLive_Adv_Ui.GG_Ui_Start.setEnabled(false);
        TerBiliLive_Adv_Ui.GG_Ui_Suspend.setEnabled(true);

        AYO_GG=true;
//        if(ConfInfo.sendAdvertising_thr.isAlive()){
//            synchronized (ConfInfo.sendAdvertising_thr) {
//                ConfInfo.sendAdvertising_thr.notify();
//                System.out.println("-----------------------广告姬重新激活-----------------------");
//            }
//        }else {
        System.out.println("-----------------------广告姬线程启动-----------------------");
            ConfInfo.sendAdvertising_thr.start();
//        }


//        GT.start();









    }
    @SuppressWarnings("deprecation")
    public void Suspend(){
//        System.out.println("GT2");
        TerBiliLive_Adv_Ui.GG_Ui_Start.setEnabled(true);
        TerBiliLive_Adv_Ui.GG_Ui_Suspend.setEnabled(false);
        TerBiliLive_Adv_Ui.GG_UiT_State.setText("暂停");
//        AYO_GG=false;

        ConfInfo.sendAdvertising_thr.stop();
        ConfInfo.sendAdvertising_thr=null;
        System.out.println("-----------------------广告姬线程销毁-----------------------");
//        new Thread(new Runnable(){
//            public void run(){
//
//                synchronized (ConfInfo.sendAdvertising_thr) {
//                    try {
//                        ConfInfo.sendAdvertising_thr.wait();
//                        System.out.println("-----------------------广告姬进入休眠-----------------------");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();












    }



}
