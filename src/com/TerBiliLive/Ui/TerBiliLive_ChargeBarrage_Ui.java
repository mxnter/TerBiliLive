package com.TerBiliLive.Ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * @名称 收取弹幕 UI
 * @作用 收取弹幕
 * @作者 Mxnter
 *
 * */

public class TerBiliLive_ChargeBarrage_Ui {
    public static String Version = "Beta08-0203";
    public static String Appname = "TerBiliLive ChargeBarrage";
    public static String ProjectName = "TerBiliLive ChargeBarrage";
    String DMJpathUrl = "http://live.bilibili.com/msg/send";




    public static  JTextPane DMJ_UiT_Text =new JTextPane();

//    public static JTextArea DMJ_UiT_Text = new JTextArea();
    public static JScrollPane jsp = new JScrollPane(DMJ_UiT_Text);
    public static JScrollPane logScroll = new JScrollPane(DMJ_UiT_Text,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    public static JPanel DMJ_Ui_Jpanel = new JPanel(new GridLayout(1, 1, 10, 10));

    JTextField sendMessage;
    //JScrollPane jsp = new JScrollPane(DMJ_UiT_Text);

    TerBiliLive_ChargeBarrage_Ui(String Parameter){



    }


    public TerBiliLive_ChargeBarrage_Ui(){

//
//        DMJ_UiT_Text.setWrapStyleWord(true);
//        DMJ_UiT_Text.setLineWrap(true);//设置自动换行
        DMJ_UiT_Text.setBorder(new TitledBorder("信息："));//设置标题
//        DMJ_UiT_Text.setWrapStyleWord(false);//设置以单词为整体换行，(即不会将单词切割成两半)
        DMJ_UiT_Text.setEditable(false);//不可编辑
        JScrollPane scrollPane = new JScrollPane(DMJ_UiT_Text);//添加滚动条
        scrollPane.setBounds(5, 0, 250, 160);
//        sendMessage = new JTextField();
//        sendMessage.setBounds(5, 170, 250, 30);


//        sendMessage.setText("按回车发送信息：输入的信息要够长，够多，才可以看到自动换行和滚动条，滚动条显示在末尾，即最新输入的一行中！");

        //jsp.setVerticalScrollBarPolicy(5);
        //给JTextArea添加垂直滚动条

      //  DMJ_UiT_Text.add(logScroll);
//        jsp.setHorizontalScrollBarPolicy(
//                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        jsp.setVerticalScrollBarPolicy(
//                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);



        //jsp.setViewportView(DMJ_UiT_Text);


        // jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
       // DMJ_UiT_Text.setCaretPosition(DMJ_UiT_Text.getText().length());

        DMJ_Ui_Jpanel.add(scrollPane);
//        DMJ_Ui_Jpanel.add(sendMessage);

        //MJ_Ui_Jpanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
















    }






//        public JTextArea getDMJ_UiT_Text() {
//        return DMJ_UiT_Text;
//    }

    public void setDMJ_UiT_Text(String Text) {
        this.DMJ_UiT_Text.setText(Text);
    }
}
