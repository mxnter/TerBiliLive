package com.TerBiliLive.Ui;

import com.TerBiliLive.Img.ImageBroker;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.TerBiliLive.TerWindowListener;

import javax.swing.*;
import java.awt.*;

public class AutoSpeak extends JFrame {
    private JButton start;
    private JButton stop;
    private JPanel panel;

    AutoSpeak(){
        this.setContentPane(panel);
        this.setTitle(ConfInfo.AppName.get(ConfInfo.AppSystemId));
        this.setName("AutoSpeak");
        this.addWindowListener(new TerWindowListener(this));
        this.setIconImage(ImageBroker.loadImages("logoa.png"));  //设置Logo
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //隐藏边框
//        this.setUndecorated(true);
//        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//        panel.setOpaque(false); //背景透明
        start.setBorderPainted(false);//不绘制边框
        stop.setBorderPainted(false);//不绘制边框





        this.setVisible(true);
    }

}
