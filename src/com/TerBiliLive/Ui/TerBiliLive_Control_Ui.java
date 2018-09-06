package com.TerBiliLive.Ui;

import javax.swing.*;
import java.awt.*;

public class TerBiliLive_Control_Ui {

    // 标签
    //JLabel Control_UiL_KB = new JLabel("　");
    JLabel Control_UiL_RoomId = new JLabel("房间号： ");
    JLabel Control_UiL_Cookie = new JLabel("Cookie： ");
    JLabel Control_UiL_State = new JLabel("状　态：");


    public static JTextField Control_UiT_RoomId = new JTextField(10);
    public static JPasswordField Control_UiT_Cookie = new JPasswordField(20);
    public static JTextField Control_UiT_State = new JTextField(13);

    //
/*    public static JTextField Control_UiT_Time = new JTextField(20);
    JLabel Control_UiL_Time = new JLabel("时　间：");
    JLabel Control_UiL_Text = new JLabel("弹　幕： ");
    public static JTextArea Control_UiT_Text = new JTextArea(26,20);*/


    public static JButton Control_UiB_Connect = new JButton("连接");
    public static JButton Control_UiB_Disconnect = new JButton("断开");
    public static JButton Control_UiB_Preservation = new JButton("保存");

    JPanel Control_Ui_Jpanel = new JPanel(new GridLayout(2, 1, 5, 10));
    JPanel Control_Ui_Up_Jpanel = new JPanel(new FlowLayout());
    JPanel Control_Ui_Dn_Jpanel = new JPanel(new FlowLayout());
    //JPanel HFJ_Ui_Up_Jpanel = new JPanel(new GridLayout(5, 1, 10, 10));


    TerBiliLive_Control_Ui(){


        Control_UiT_State.setEditable(false);

        Control_Ui_Up_Jpanel.add(Control_UiL_State);
        Control_Ui_Up_Jpanel.add(Control_UiT_State);
        Control_Ui_Up_Jpanel.add(Control_UiL_RoomId);
        Control_Ui_Up_Jpanel.add(Control_UiT_RoomId);
        Control_Ui_Up_Jpanel.add(Control_UiL_Cookie);
        Control_Ui_Up_Jpanel.add(Control_UiT_Cookie);
        Control_Ui_Up_Jpanel.add(Control_UiB_Preservation);


        Control_Ui_Dn_Jpanel.add(Control_UiB_Connect);
        Control_Ui_Dn_Jpanel.add(Control_UiB_Disconnect);




        Control_Ui_Jpanel.add(Control_Ui_Up_Jpanel);
        Control_Ui_Jpanel.add(Control_Ui_Dn_Jpanel);








    }

    public TerBiliLive_Control_Ui(String Parameter){



    }

}
