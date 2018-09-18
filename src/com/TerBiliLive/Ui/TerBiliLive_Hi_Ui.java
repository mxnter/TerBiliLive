package com.TerBiliLive.Ui;

import com.TerBiliLive.Img.ImageBroker;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveInfo;
import com.TerBiliLive.Utiliy.FileUtil;
import com.TerBiliLive.Utiliy.TimeUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TerBiliLive_Hi_Ui extends JFrame {


    JPanel p=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel l=new JLabel("登陆中.");
    JLabel bt=new JLabel("TerBiliLive");
    ImageIcon img_bj =null;
    JLabel imgLabel =null;
    public TerBiliLive_Hi_Ui(){

        //设置图片
        try {
            img_bj = ImageBroker.loadImage("hy.jpg");
        } catch (IOException e) {
            img_bj = new ImageIcon("Ter/img/hy.jpg");//这是背景图片
            e.printStackTrace();
        }
        imgLabel = new JLabel(img_bj);//将背景图放在标签里。
        //设置图标
        Image icon = null;
        try {
            icon =  ImageBroker.loadImage("logo.jpg").getImage();
        } catch (IOException e) {
            icon = Toolkit.getDefaultToolkit().getImage("Ter/logo.jpg");
            e.printStackTrace();
        }

        if (icon != null) this.setIconImage(icon);  // 图片的具体位置


        this.setTitle("TerBiliLive");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
        imgLabel.setBounds(0,0,img_bj.getIconWidth(), img_bj.getIconHeight());//设置背景标签的位置
        Container con = getContentPane();
        this.setResizable(false);
        ((JPanel)con).setOpaque(false);


        //隐藏边框
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        con.setLayout(new BorderLayout());
        l.setForeground(Color.WHITE);
        //	bt.setForeground(Color.WHITE);
        p.setOpaque(false); //背景透明

        p.add(l);

        //	con.add(bt,BorderLayout.CENTER);
        con.add(p,BorderLayout.SOUTH);
        ConfInfo.cookie=FileUtil.readFile("Cookie");
        System.out.println(ConfInfo.cookie);




        this.setVisible(true);

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                l.setText(l.getText()+".");
            }
        }).start();

        if (ConfInfo.cookie==null){
            TerBiliLive_Login_Ui live_login_ui=new TerBiliLive_Login_Ui();
            this.setVisible(false);
        }else{
            ConfInfo.liveInfo = new LiveInfo("9938182",ConfInfo.cookie);
            ConfInfo.Uid=ConfInfo.liveInfo.getUid();
            ConfInfo.Uname=ConfInfo.liveInfo.getUname();
            ConfInfo.liveInfo=null;
            if(ConfInfo.Uid.equals("")){
                JOptionPane.showMessageDialog(null,"登陆过期，请重新登陆！");
                TerBiliLive_Login_Ui live_login_ui=new TerBiliLive_Login_Ui();
                dispose();
//                this.setVisible(false);

            }else{
                if(ConfInfo.Rnd.equals(""))ConfInfo.Rnd=TimeUtil.timeStamp();
                l.setText("登陆成功");
                TerBiliLive_Ui HFJ = new TerBiliLive_Ui();
                dispose();
//                this.setVisible(false);
            }

        }


    }
}
