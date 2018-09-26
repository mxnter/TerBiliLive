package com.TerBiliLive.Monitor;

import com.TerBiliLive.Function.Control_Fun;
import com.TerBiliLive.Function.HFJ_Fun;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.TerBiliLive.GetLiveRoomUserInfo;
import com.TerBiliLive.Thr.GetSendBarrageList_Thr;
import com.TerBiliLive.Ui.TerBiliLive_Hi_Ui;
import com.TerBiliLive.Ui.TerBiliLive_Ui;
import com.TerBiliLive.Utiliy.FileUtil;
import com.TerBiliLive.Utiliy.TulingUtil;

import javax.swing.*;
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
//                ConfInfo.dingtalkUtil.LiveLive();



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
                ConfInfo.terBiliLive_gg_ui.GG_Ui_Suspend.doClick();

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
        Reply_Master.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                if(ConfInfo.terBiliLive_control_ui.Reply_Master.isSelected())Reply_MasterRadioGift.setSelected(true);
                else Reply_MasterRadioGift.setSelected(false);




            }
        });
        Reply_tourist.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                if(ConfInfo.terBiliLive_control_ui.Reply_tourist.isSelected())
                    if(!ConfInfo.terBiliLive_control_ui.Reply_chat.isSelected())
                        ConfInfo.terBiliLive_control_ui.Reply_chat.doClick();




            }
        });
//        Reply_XXX.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                // TODO Auto-generated method stub
//
//                if(!ConfInfo.terBiliLive_control_ui.Reply_XXX.isSelected())
//                    JOptionPane.showMessageDialog(new JFrame().getContentPane(), "忠告,本程序无法保证信息发送是否正确,无法保证是否会重复发送错误的信息,请使用小号使用。\n不要去其他直播间试探底线,作者已经收到部分房间一个月的弹幕封禁.\n请勿使用任何回复功能去官方直播间试探底线,官方封禁会7天无法在任何地方发送弹幕！\n请注意！！！！", "警告", JOptionPane.QUESTION_MESSAGE);
//
//
//
//
//            }
//        });
        Reply_chat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if(ConfInfo.terBiliLive_control_ui.Reply_chat.isSelected()){
                    if(ConfInfo.confData.getTulingApikey()==null||ConfInfo.confData.getTulingApikey().equals("")) ConfInfo.confData.setTulingApikey( JOptionPane.showInputDialog("请输入图灵Apikey"));

//                    if(ConfInfo.confData.getTulingApikey()!=null&&ConfInfo.confData.getTulingApikey().equals("")){
                        String TulingUtilMsg = TulingUtil.chat("hi",ConfInfo.confData.getTulingApikey());
                        switch (TulingUtilMsg){
                            case "TulingApikeynull":{ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false); JOptionPane.showMessageDialog(null, "apikey为空,已帮您关闭了聊天功能", "TulingUtil 错误", JOptionPane.ERROR_MESSAGE);ConfInfo.confData.setTulingApikey(null); break;}
                            case "TerGGKey":{ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false); JOptionPane.showMessageDialog(null, "apikey不合法,已帮您关闭了聊天功能", "TulingUtil 错误", JOptionPane.ERROR_MESSAGE);ConfInfo.confData.setTulingApikey(null); break;}
                            case "TerGG":{ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false); JOptionPane.showMessageDialog(null, "位置错误,已帮您关闭了聊天功能", "TulingUtil 错误", JOptionPane.ERROR_MESSAGE);ConfInfo.confData.setTulingApikey(null); break;}
                            case "嗨":{ ConfInfo.jsonUtil.writeData();break;}
                        }
//                    }
                }else{
                    ConfInfo.terBiliLive_control_ui.Reply_tourist.setSelected(false);
                }





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
