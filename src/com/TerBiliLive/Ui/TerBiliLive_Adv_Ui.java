package com.TerBiliLive.Ui;

import com.TerBiliLive.Monitor.GG_Monitor;
import com.TerBiliLive.Monitor.HFJ_Monitor;

import javax.swing.*;
import java.awt.*;

/**
 * @名称 发送定时广告 UI
 * @作用 发送定时广告
 * @作者 Mxnter
 *
 * */

public class TerBiliLive_Adv_Ui {


    public static String Version = "Beta03";
    public static String Appname = "TerBiliLive Adv";
    public static String ProjectName = "TerBiliLive Adv";
    String pathUrl = "http://live.bilibili.com/msg/send";

    JLabel GG_UiL_Second = new JLabel("延时： ");
    JLabel GG_UiL_Text = new JLabel("弹幕： ");
    JLabel GG_UiL_State = new JLabel("状态：");
    public static JTextField GG_UiT_Second = new JTextField(3);
    public static JTextField GG_UiT_State = new JTextField(17);
    public static JTextField GG_UiT_Text = new JTextField(22);
    public static JButton GG_Ui_Start = new JButton("开始");
    public static JButton GG_Ui_Suspend = new JButton("暂停");

    public static JPanel GG_Ui_Jpanel = new JPanel(new FlowLayout());



    TerBiliLive_Adv_Ui(){

        GG_Ui_Suspend.setEnabled(false);
        GG_UiT_State.setEditable(false);

        GG_Ui_Jpanel.add(GG_UiL_State);
        GG_Ui_Jpanel.add(GG_UiT_State);
		GG_Ui_Jpanel.add(GG_UiL_Second);
        GG_Ui_Jpanel.add(GG_UiT_Second);
		GG_Ui_Jpanel.add(GG_UiL_Text);
        GG_Ui_Jpanel.add(GG_UiT_Text);
		GG_Ui_Jpanel.add(GG_Ui_Start);
		GG_Ui_Jpanel.add(GG_Ui_Suspend);

        new GG_Monitor();


    }

}
