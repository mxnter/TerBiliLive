package com.github.TerBiliLive.Ui;

import com.github.TerBiliLive.Info.ConfInfo;

import javax.swing.*;
import java.awt.*;

/**
 * @名称 控制台 UI
 * @作用 控制台
 * @作者 Mxnter
 *
 * */


public class TerBiliLive_Control_Ui {

    // 标签
    //JLabel Control_UiL_KB = new JLabel("　");
    JLabel Control_UiL_RoomId = new JLabel("房间号： ");
    JLabel Control_UiL_Cookie = new JLabel("Cookie： ");
    JLabel Control_UiL_Uid = new JLabel("UID： ");
    JLabel Control_UiL_Uname = new JLabel("UL： ");
    JLabel Control_UiL_RoomRank = new JLabel("小时榜： ");
    JLabel Control_UiL_State = new JLabel("状态：");
    JLabel Control_UiL_UpUname = new JLabel("UP：");

    JLabel Control_UiL_Reply = new JLabel("回复：");
    //复选框
    public static JCheckBox Reply_chat = new JCheckBox("聊天");
    public static JCheckBox Reply_tourist = new JCheckBox("回应游客");
    public static JCheckBox Reply_LiveState = new JCheckBox("状态");
    public static JCheckBox Reply_LowSecurity = new JCheckBox("低保");
    public static JCheckBox Reply_Master = new JCheckBox("老爷");
    public static JCheckBox Reply_MasterBarrage = new JCheckBox("显示弹幕老爷");
    public static JCheckBox Reply_MasterRadioGift = new JCheckBox("广播后开启老爷");
    public static JCheckBox Reply_Guard = new JCheckBox("舰长");
    public static JCheckBox Reply_30 = new JCheckBox("-30-");
    public static JCheckBox Reply_NeglectSpicy = new JCheckBox("忽略辣条");

//    public static JCheckBox Reply_XXX = new JCheckBox("禁用任何弹幕发送");



    public static JTextField Control_UiT_RoomId = new JTextField(10);
    public static JPasswordField Control_UiT_Cookie = new JPasswordField(20);
    public static JTextField Control_UiT_State = new JTextField(13);
    public static JTextField Control_UiT_Uid = new JTextField(8);
    public static JTextField Control_UiT_Uname = new JTextField(10);
    public static JTextField Control_UiT_UpUname = new JTextField(10);
    public static JTextField Control_UiT_RoomRank = new JTextField(8);

    //
/*    public static JTextField Control_UiT_Time = new JTextField(20);
    JLabel Control_UiL_Time = new JLabel("时　间：");
    JLabel Control_UiL_Text = new JLabel("弹　幕： ");
    public static JTextArea Control_UiT_Text = new JTextArea(26,20);*/

    public static JTextField Control_UiB_Text = new JTextField(30);

    public static JButton Control_UiB_Connect = new JButton("连接");
    public static JButton Control_UiB_Disconnect = new JButton("断开");
    public static JButton Control_UiB_OpenThinks = new JButton("启动感谢");
    public static JButton Control_UiB_ClaseThinks = new JButton("关闭感谢");
    public static JButton Control_UiB_Preservation = new JButton("保存");

    JPanel Control_Ui_Jpanel = new JPanel(new GridLayout(3, 1, 5, 5));
    JPanel Control_Ui_Up_Jpanel = new JPanel(new FlowLayout());
    JPanel Control_Ui_Dn_Jpanel = new JPanel(new FlowLayout());
    JPanel Control_Ui_Tz_Jpanel = new JPanel(new FlowLayout());
    //JPanel HFJ_Ui_Up_Jpanel = new JPanel(new GridLayout(5, 1, 10, 10));


    TerBiliLive_Control_Ui(){
        if(null!= ConfInfo.userInfo||!ConfInfo.cookie.equals("")){
           //登录后执行
        }else{
            //未登录执行
            Reply_chat.setEnabled(false);
            Reply_tourist.setEnabled(false);
            Reply_LiveState.setEnabled(false);
            Reply_LowSecurity.setEnabled(false);
            Reply_Master.setEnabled(false);
            Reply_MasterBarrage.setEnabled(false);
            Reply_MasterRadioGift.setEnabled(false);
            Reply_Guard.setEnabled(false);
            Reply_30.setEnabled(false);
            Reply_NeglectSpicy.setEnabled(false);
        }
        Control_UiB_Disconnect.setEnabled(false);
        Control_UiB_ClaseThinks.setEnabled(false);
        Control_UiB_OpenThinks.setEnabled(false);
        Control_UiT_State.setEditable(false);
        Control_UiT_Uid.setEditable(false);
        Control_UiT_Uname.setEditable(false);
        Control_UiT_UpUname.setEditable(false);
        Control_UiT_RoomRank.setEditable(false);
        Control_UiB_Text.setEditable(false);
        Reply_MasterBarrage.setSelected(true);
//        Reply_XXX.setSelected(true);
//        Reply_MasterRadioGift.setBorderPaintedFlat();

        Control_Ui_Up_Jpanel.add(Control_UiL_State);
        Control_Ui_Up_Jpanel.add(Control_UiT_State);
        Control_Ui_Up_Jpanel.add(Control_UiT_RoomRank);
        Control_Ui_Up_Jpanel.add(Control_UiL_RoomId);
        Control_Ui_Up_Jpanel.add(Control_UiT_RoomId);
//        Control_Ui_Up_Jpanel.add(Control_UiB_Preservation);

//        Control_Ui_Up_Jpanel.add(Control_UiT_Cookie);
        Control_Ui_Up_Jpanel.add(Control_UiB_Connect);
        Control_Ui_Up_Jpanel.add(Control_UiB_Disconnect);
        Control_Ui_Up_Jpanel.add(Control_UiB_OpenThinks);
        Control_Ui_Up_Jpanel.add(Control_UiB_ClaseThinks);



//        Control_Ui_Dn_Jpanel.add(Control_UiL_Cookie);

        Control_Ui_Dn_Jpanel.add(Control_UiL_Uid);
        Control_Ui_Dn_Jpanel.add(Control_UiT_Uid);
        Control_Ui_Dn_Jpanel.add(Control_UiL_Uname);
        Control_Ui_Dn_Jpanel.add(Control_UiT_Uname);
        Control_Ui_Dn_Jpanel.add(Control_UiL_UpUname);
        Control_Ui_Dn_Jpanel.add(Control_UiT_UpUname);

        Control_Ui_Dn_Jpanel.add(Control_UiB_Text);
//        Control_Ui_Dn_Jpanel.add(Control_UiL_RoomRank);
//        Control_Ui_Dn_Jpanel.add(Control_UiT_RoomRank);



        Control_Ui_Tz_Jpanel.add(Control_UiL_Reply);
        Control_Ui_Tz_Jpanel.add(Reply_chat);
        Control_Ui_Tz_Jpanel.add(Reply_tourist);

        Control_Ui_Tz_Jpanel.add(Reply_LiveState);
        Control_Ui_Tz_Jpanel.add(Reply_LowSecurity);
        Control_Ui_Tz_Jpanel.add(Reply_Master);
        Control_Ui_Tz_Jpanel.add(Reply_Guard);
        Control_Ui_Tz_Jpanel.add(Reply_30);
        Control_Ui_Tz_Jpanel.add(Reply_NeglectSpicy);

//        Control_Ui_Tz_Jpanel.add( Reply_XXX);
//        Control_Ui_Tz_Jpanel.add(Reply_MasterRadioGift);



        Control_Ui_Jpanel.add(Control_Ui_Up_Jpanel);
        Control_Ui_Jpanel.add(Control_Ui_Dn_Jpanel);
        Control_Ui_Jpanel.add(Control_Ui_Tz_Jpanel);
//
//        Control_Ui_Dn_Jpanel.setOpaque(false);
//        Control_Ui_Tz_Jpanel.setBackground(Color.white);
//        Control_Ui_Jpanel.setBackground(Color.white);








    }

}
