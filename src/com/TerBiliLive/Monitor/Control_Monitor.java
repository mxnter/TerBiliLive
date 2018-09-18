package com.TerBiliLive.Monitor;

import com.TerBiliLive.Function.Control_Fun;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.TerBiliLive.GetLiveRoomUserInfo;
import com.TerBiliLive.Ui.TerBiliLive_Hi_Ui;
import com.TerBiliLive.Ui.TerBiliLive_Ui;
import com.TerBiliLive.Utiliy.FileUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.TerBiliLive.Ui.TerBiliLive_Control_Ui.*;
import static com.TerBiliLive.Ui.TerBiliLive_GG_Ui.GG_UiT_Second;
import static com.TerBiliLive.Ui.TerBiliLive_GG_Ui.GG_UiT_Text;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormat;


public class Control_Monitor {


    public Control_Monitor(){

        if(ConfInfo.control_fun ==null) ConfInfo.control_fun =new Control_Fun();


        Control_UiB_Preservation.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                ConfInfo.control_fun.Preservation();


            }
        });
        Control_UiB_Connect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText().equals("")||ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText()==null){
                    ConfInfo.terBiliLive_control_ui.Control_UiT_State.setText("房间号不能为空");
                }else{
                    Control_UiB_OpenThinks.setEnabled(true);
                    ConfInfo.Thank="no";
                    ConfInfo.control_fun.Connect();
                    ConfInfo.getLiveRoomUserInfo = new GetLiveRoomUserInfo();
                    ConfInfo.terBiliLive_control_ui.Control_UiT_UpUname.setText(ConfInfo.getLiveRoomUserInfo.getRoomUseruname());
                }



            }
        });
        Control_UiB_Disconnect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                Control_UiB_OpenThinks.setEnabled(false);
                Control_UiB_ClaseThinks.setEnabled(false);
                ConfInfo.Thank="no";
                ConfInfo.control_fun.Disconnect();
                ConfInfo.getLiveRoomUserInfo =null;

            }
        });
        Control_UiB_OpenThinks.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                Control_UiB_OpenThinks.setEnabled(false);
                Control_UiB_ClaseThinks.setEnabled(true);
                ConfInfo.Thank="ok";
                String putDM =  "系统 ："+getFormat()+" - "+"开启感谢" +" 真实直播间ID："+ConfInfo.liveRoom.room_id +"  UID："+ConfInfo.liveInfo.getUid()+"  昵称："+ ConfInfo.liveInfo.getUname().toString()+"\t";
                ConfInfo.putShowUtil.PutDMUtil(putDM);



            }
        });
        Control_UiB_ClaseThinks.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                Control_UiB_OpenThinks.setEnabled(true);
                Control_UiB_ClaseThinks.setEnabled(false);
                ConfInfo.Thank="no";
                String putDM =  "系统 ："+getFormat()+" - "+"关闭感谢" +" 真实直播间ID："+ConfInfo.liveRoom.room_id +"  UID："+ConfInfo.liveInfo.getUid()+"  昵称："+ ConfInfo.liveInfo.getUname().toString()+"\t";
                ConfInfo.putShowUtil.PutDMUtil(putDM);



            }
        });




    }
}
