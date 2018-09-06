package com.TerBiliLive.Ui;

import com.TerBiliLive.Monitor.Control_Monitor;
import com.TerBiliLive.TerBiliLive.sendGet;
import com.TerBiliLive.Utiliy.DmLogUtil;
import com.TerBiliLive.Utiliy.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.TerBiliLive.Ui.TerBiliLive_Control_Ui.Control_UiT_RoomId;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatHour;

public class TerBiliLive_Ui extends JFrame implements ActionListener {


    public static String StartInfo = "Ter制作的 “TerBiliLive” 弹幕姬 - 测试中";
    public static String NetworkingInfo = null;
    public static String Version = "Beta03[D."+TerBiliLive_DMJ_Ui.Version+"]-[H."+TerBiliLive_HFJ_Ui.Version+"]-[G."+TerBiliLive_GG_Ui.Version+"]";
    public static String Appname = "TerBiliLive";
    public static String ProjectName = "TerBiliLive";
    //String UipathUrl = "http://live.bilibili.com/msg/send";


    JPanel Ui_SOUTH_Jpanel = new JPanel(new GridLayout(2, 1, 5, 5));


    public TerBiliLive_Ui()

    {
        this.setTitle(Appname + " " + Version);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        Image icon = null;
        icon = Toolkit.getDefaultToolkit().getImage("Ter/logo.jpg");
        if (icon != null) this.setIconImage(icon);  // 图片的具体位置

        Container con = getContentPane();
        this.setResizable(false);


        TerBiliLive_Control_Ui C =new TerBiliLive_Control_Ui();
        TerBiliLive_DMJ_Ui DMJ = new TerBiliLive_DMJ_Ui();
        TerBiliLive_HFJ_Ui HFJ = new TerBiliLive_HFJ_Ui();
        TerBiliLive_GG_Ui GG = new TerBiliLive_GG_Ui();

        C.Control_UiT_RoomId.setText(FileUtil.readFile("RoomId"));
        C.Control_UiT_Cookie.setText(FileUtil.readFile("Cookie"));
        GG.GG_UiT_Second.setText(FileUtil.readFile("Second"));
        GG.GG_UiT_Text.setText(FileUtil.readFile("Text"));
//        HFJ.HFJ_UiT_RoomId.setText(FileUtil.readFile("RoomId"));
        //HFJ.HFJ_UiT_Text.setText(FileUtil.readFile("Text"));
//        HFJ.HFJ_UiT_Cookie.setText(FileUtil.readFile("Cookie"));
        //System.out.print(readFile("RoomId.txt"));
        HFJ.HFJ_UiT_Time.setText("Ter 简单制造");
        HFJ.HFJ_UiT_State.setText("Ter 简单制造"+TerBiliLive_HFJ_Ui.Version);
        C.Control_UiT_State.setText("Ter 简单制造"+TerBiliLive_DMJ_Ui.Version);
        GG.GG_UiT_State.setText("Ter 简单制造"+TerBiliLive_GG_Ui.Version);
        DMJ.DMJ_UiT_Text.append(StartInfo+"\r\n");
        DMJ.DMJ_UiT_Text.append("欢迎使用 "+Appname+" 当前版本 "+Version+"\r\n");
        DMJ.DMJ_UiT_Text.append(sendGet.sendGet("https://mxnter.github.io/TerBiliLiveV/Networking/","","")+"\r\n");
        if(sendGet.sendGet("https://mxnter.github.io/TerBiliLiveV/Networking/","","").equals(null)){
            DMJ.DMJ_UiT_Text.append("未检测到网络，请检查您的网络（或许有可能检测服务器失效）\r\n");
        }else {
            DMJ.DMJ_UiT_Text.append("网络正常\r\n");
        }
        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), DMJ.DMJ_UiT_Text.getText(),Control_UiT_RoomId.getText());




        Ui_SOUTH_Jpanel.add(GG.GG_Ui_Jpanel);
        Ui_SOUTH_Jpanel.add(HFJ.HFJ_Ui_Jpanel);


        con.add(C.Control_Ui_Jpanel, BorderLayout.NORTH);
        con.add(Ui_SOUTH_Jpanel, BorderLayout.SOUTH);
        con.add(DMJ.DMJ_Ui_Jpanel, BorderLayout.CENTER);
        new Control_Monitor();





        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {

                //创建目录
                FileUtil.createDir("Ter/data/");
                FileUtil.createDir("Ter/log/");
                FileUtil.createDir("Ter/Dm/");
                //写入数据
                FileUtil.writeFile("RoomId", C.Control_UiT_RoomId.getText());
                FileUtil.writeFile("Cookie",C.Control_UiT_Cookie.getText());
                FileUtil.writeFile("Second", GG.GG_UiT_Second.getText());
                FileUtil.writeFile("Text",GG.GG_UiT_Text.getText());

                super.windowClosing(e);
            }

        });

        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }




}
