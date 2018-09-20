package com.TerBiliLive.Function;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveRoom;
import com.TerBiliLive.Thr.DMJ_Thr;
import com.TerBiliLive.Thr.GG_Thr;
import com.TerBiliLive.Ui.TerBiliLive_Control_Ui;
import com.TerBiliLive.Utiliy.FileUtil;

import static com.TerBiliLive.Ui.TerBiliLive_GG_Ui.*;

public class Control_Fun {


    public static  boolean AYO = true ;
    public static  boolean AYO_GG = true ;
    //boolean AYO=true;
    String Parameter= "" ;
    DMJ_Thr DT;
    GG_Thr GT = new GG_Thr();

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
        FileUtil.writeFile("Second", GG_UiT_Second.getText());
        FileUtil.writeFile("Text",GG_UiT_Text.getText());


        ConfInfo.confData.setCookie(ConfInfo.cookie);
        ConfInfo.confData.setSecond( GG_UiT_Second.getText());
        ConfInfo.confData.setText(GG_UiT_Text.getText());
        ConfInfo.confData.setRoomId(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText());
        ConfInfo.xmlUtil.writeData();



    }
    public void Connect(){
        DT= new DMJ_Thr();
        ConfInfo.liveRoom =new LiveRoom(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText().toString());
        DT.start(ConfInfo.liveRoom.room_id,true);
        ConfInfo.terBiliLive_control_ui.Control_UiB_Connect.setEnabled(false);
        ConfInfo.terBiliLive_control_ui.Control_UiB_Disconnect.setEnabled(true);










    }
    @SuppressWarnings("deprecation")
    public void Disconnect(){

        DT.stop();
        ConfInfo.terBiliLive_control_ui.Control_UiB_Connect.setEnabled(true);
        ConfInfo.terBiliLive_control_ui.Control_UiB_Disconnect.setEnabled(false);
        //DT.interrupt();








    }

    public void Start(){
//        System.out.println("GT1");
        GT.second =Integer.parseInt(GG_UiT_Second.getText());
        GG_Ui_Start.setEnabled(false);
        GG_Ui_Suspend.setEnabled(true);

        AYO_GG=true;
        //if（GT.start()）
        GT.start();









    }
    @SuppressWarnings("deprecation")
    public void Suspend(){
//        System.out.println("GT2");
        GG_Ui_Start.setEnabled(true);
        GG_Ui_Suspend.setEnabled(false);
        GG_UiT_State.setText("暂停中（可能等待您设定的时间）");
        AYO_GG=false;
        GT.stop();








    }



}
