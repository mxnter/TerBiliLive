package com.TerBiliLive.Ui;


import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Monitor.HFJ_Monitor;

import javax.swing.*;
import java.awt.*;

//import org.json.JSONObject;
/**
 * @名称 发送弹幕 UI
 * @作用 发送弹幕
 * @作者 Mxnter
 *
 * */

public class TerBiliLive_SendBarrage_Ui {

    public static String Version = "Beta9-0921";
    public static String Appname = "TerBiliLive SendBarrage";
    public static String ProjectName = "TerBiliLive SendBarrage";
    String pathUrl = "http://live.bilibili.com/msg/send";

    // 标签
    JLabel HFJ_UiL_Text = new JLabel("弹幕： ");
    JLabel HFJ_UiL_State = new JLabel("状态：");
    JLabel HFJ_UiL_Time = new JLabel("时间：");

    public static JTextField HFJ_UiT_State = new JTextField(13);
    public static JTextField HFJ_UiT_Time = new JTextField(7);
    public static JTextField HFJ_UiT_Text = new JTextField(30);

    public static JButton HFJ_UiB_Send = new JButton("发送");
    JPanel HFJ_Ui_Jpanel = new JPanel(new FlowLayout());

    public TerBiliLive_SendBarrage_Ui(){

        if(null!= ConfInfo.userInfo||!ConfInfo.cookie.equals("")){
            //登录后执行
        }else{
            //未登录执行
            HFJ_UiB_Send.setEnabled(false);
        }

        JPanel HFJ_Ui_Time = new JPanel(new FlowLayout());
        JPanel HFJ_Ui_State = new JPanel(new FlowLayout());
        JPanel HFJ_Ui_Text1 = new JPanel(new FlowLayout());
        JPanel HFJ_Ui_Text2 = new JPanel(new FlowLayout());
        JPanel HFJ_Ui_SS = new JPanel(new FlowLayout());

        HFJ_UiT_Time.setEditable(false);
        HFJ_UiT_State.setEditable(false);

        HFJ_Ui_Time.add(HFJ_UiL_Time);
        HFJ_Ui_Time.add(HFJ_UiT_Time);
        HFJ_Ui_State.add(HFJ_UiL_State);
        HFJ_Ui_State.add(HFJ_UiT_State);
        HFJ_Ui_Text1.add(HFJ_UiL_Text);
        HFJ_Ui_Text2.add(HFJ_UiT_Text);
        HFJ_Ui_SS.add(HFJ_UiB_Send);

        HFJ_Ui_Jpanel.add(HFJ_Ui_Time);
        HFJ_Ui_Jpanel.add(HFJ_Ui_State);
        HFJ_Ui_Jpanel.add(HFJ_Ui_Text2);
        HFJ_Ui_Jpanel.add(HFJ_Ui_SS);

        new HFJ_Monitor();
    }
}
