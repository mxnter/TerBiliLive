package com.TerBiliLive.Ui;

import com.TerBiliLive.Monitor.GG_Monitor;
import com.TerBiliLive.Monitor.HFJ_Monitor;

import javax.swing.*;
import java.awt.*;

public class TerBiliLive_GG_Ui {


    public static String Version = "Beta02";
    public static String Appname = "TerBiliLive GG";
    public static String ProjectName = "TerBiliLive GG";
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



    public TerBiliLive_GG_Ui(String Parameter){



    }

    TerBiliLive_GG_Ui (){

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
