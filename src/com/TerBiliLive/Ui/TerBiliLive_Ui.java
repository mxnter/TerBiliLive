package com.TerBiliLive.Ui;

import com.TerBiliLive.Img.ImageBroker;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveInfo;
import com.TerBiliLive.Monitor.Control_Monitor;
import com.TerBiliLive.TerBiliLive.sendGet;
import com.TerBiliLive.Utiliy.DmLogUtil;
import com.TerBiliLive.Utiliy.FileUtil;
import com.TerBiliLive.Utiliy.PutShowUtil;
import com.TerBiliLive.Utiliy.TimeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.TerBiliLive.Ui.TerBiliLive_Control_Ui.Control_UiT_RoomId;
import static com.TerBiliLive.Ui.TerBiliLive_GG_Ui.GG_UiT_Second;
import static com.TerBiliLive.Ui.TerBiliLive_GG_Ui.GG_UiT_Text;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatHour;

public class TerBiliLive_Ui extends JFrame implements ActionListener {


    public static String StartInfo = "Ter制作的 “TerBiliLive” 弹幕姬 - 测试中";
    public static String NetworkingInfo = null;
    public static String Version = "Beta04[D."+TerBiliLive_DMJ_Ui.Version+"]-[H."+TerBiliLive_HFJ_Ui.Version+"]-[G."+TerBiliLive_GG_Ui.Version+"]";
    public static String Appname = "TerBiliLive";
    public static String ProjectName = "TerBiliLive";
    //String UipathUrl = "http://live.bilibili.com/msg/send";


    JPanel Ui_SOUTH_Jpanel = new JPanel(new GridLayout(2, 1, 5, 5));

    JMenuBar mBar = new JMenuBar();

    public JMenu m1 = new JMenu("编辑");
    public JMenuItem m1_item1 = new JMenuItem("清除账户");
    public JMenuItem m1_item2 = new JMenuItem("保存信息");

    public JMenu m4 = new JMenu("关于");
    public JMenuItem m4_item1 = new JMenuItem("帮助");
    public JMenuItem m4_item2 = new JMenuItem("关于");


    ImageIcon img_bj =null;
    JLabel imgLabel =null;
    public TerBiliLive_Ui()

    {
        this.setTitle(Appname + " " + Version);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


//        //设置图片
//        try {
//            img_bj = ImageBroker.loadImage("hy.jpg");
//        } catch (IOException e) {
//            img_bj = new ImageIcon("Ter/img/hy.jpg");//这是背景图片
//            e.printStackTrace();
//        }
//        imgLabel = new JLabel(img_bj);//将背景图放在标签里。

        Image icon = null;
        try {
            icon =  ImageBroker.loadImage("logo.jpg").getImage();
        } catch (IOException e) {
            icon = Toolkit.getDefaultToolkit().getImage("Ter/logo.jpg");
            e.printStackTrace();
        }

        if (icon != null) this.setIconImage(icon);  // 图片的具体位置

//        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
//        imgLabel.setBounds(0,0,img_bj.getIconWidth(), img_bj.getIconHeight());//设置背景标签的位置

        Container con = getContentPane();
        this.setResizable(false);


        ConfInfo.terBiliLive_control_ui =new TerBiliLive_Control_Ui();
        ConfInfo.terBiliLive_dmj_ui = new TerBiliLive_DMJ_Ui();
        ConfInfo.terBiliLive_hfj_ui = new TerBiliLive_HFJ_Ui();
        ConfInfo.terBiliLive_gg_ui= new TerBiliLive_GG_Ui();


//        ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.setText(FileUtil.readFile("RoomId"));

//老版本弃用
//        ConfInfo.terBiliLive_control_ui.Control_UiT_Uid.setText(ConfInfo.Uid);
//        ConfInfo.terBiliLive_control_ui.Control_UiT_Uname.setText(ConfInfo.Uname);
//
////        ConfInfo.terBiliLive_control_ui.Control_UiT_Cookie.setText(FileUtil.readFile("Cookie"));ConfInfo.cookie=ConfInfo.terBiliLive_control_ui.Control_UiT_Cookie.getText();
//
//        ConfInfo.terBiliLive_gg_ui.GG_UiT_Second.setText(FileUtil.readFile("Second"));
//        ConfInfo.terBiliLive_gg_ui.GG_UiT_Text.setText(FileUtil.readFile("Text"));

        ConfInfo.terBiliLive_control_ui.Control_UiT_Uid.setText(ConfInfo.Uid);
        ConfInfo.terBiliLive_control_ui.Control_UiT_Uname.setText(ConfInfo.Uname);

//        ConfInfo.terBiliLive_control_ui.Control_UiT_Cookie.setText(FileUtil.readFile("Cookie"));ConfInfo.cookie=ConfInfo.terBiliLive_control_ui.Control_UiT_Cookie.getText();

        ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.setText(ConfInfo.confData.getRoomId());
        ConfInfo.terBiliLive_gg_ui.GG_UiT_Second.setText(ConfInfo.confData.getSecond());
        ConfInfo.terBiliLive_gg_ui.GG_UiT_Text.setText(ConfInfo.confData.getText());





//        HFJ.HFJ_UiT_RoomId.setText(FileUtil.readFile("RoomId"));
        //HFJ.HFJ_UiT_Text.setText(FileUtil.readFile("Text"));
//        HFJ.HFJ_UiT_Cookie.setText(FileUtil.readFile("Cookie"));
        //System.out.print(readFile("RoomId.txt"));
        ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_Time.setText("Ter 简单制造");
        ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_State.setText("Ter 简单制造"+TerBiliLive_HFJ_Ui.Version);
        ConfInfo.terBiliLive_control_ui.Control_UiT_State.setText("Ter 简单制造"+TerBiliLive_DMJ_Ui.Version);
        ConfInfo.terBiliLive_gg_ui.GG_UiT_State.setText("Ter 简单制造"+TerBiliLive_GG_Ui.Version);
        if (ConfInfo.putShowUtil == null) ConfInfo.putShowUtil = new PutShowUtil();
        ConfInfo.putShowUtil.PutDMUtil(StartInfo);
        ConfInfo.putShowUtil.PutDMUtil("欢迎使用 "+Appname+" 当前版本 "+Version);
        new Thread(new Runnable(){
                                    public void run(){
                                        try {
                                            ConfInfo.putShowUtil.PutDMUtil("开发者通知：\n    "+sendGet.sendGet("https://mxnter.github.io/TerBiliLiveV/Networking/index.html","",""));
                                        } catch (Exception e) {
                                            ConfInfo.putShowUtil.PutDMUtil("[ Ter ] ---X--->> [本机] 【获取开发者通知失败】");
                                            e.printStackTrace();
                                        }

                                    }
                                }).start();
        if(sendGet.sendGet("https://live.bilibili.com/","","").equals(null)){
            ConfInfo.putShowUtil.PutDMUtil("未检测到网络，请检查您的网络（或许有可能检测服务器失效");
        }else {
            try {
                ConfInfo.putShowUtil.PutDMUtil(" [Live Bilibili]  << ------ >>  ["+ InetAddress.getLocalHost().getHostName()+ "]    【网络连接正常】");
            } catch (UnknownHostException e) {
                ConfInfo.putShowUtil.PutDMUtil(" [Live Bilibili]  << ------ >>  [本机]    【网络连接正常】");
                e.printStackTrace();
            }
        }
        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), ConfInfo.terBiliLive_dmj_ui.DMJ_UiT_Text.getText(),Control_UiT_RoomId.getText());
        if(ConfInfo.Rnd.equals(""))ConfInfo.Rnd=TimeUtil.timeStamp();




        Ui_SOUTH_Jpanel.add(ConfInfo.terBiliLive_gg_ui.GG_Ui_Jpanel);
        Ui_SOUTH_Jpanel.add(ConfInfo.terBiliLive_hfj_ui.HFJ_Ui_Jpanel);


        // 设置菜单
        m1.add(m1_item1);
        m1.add(m1_item2);

//        m4.add(m4_item1);
        m4.add(m4_item2);

        mBar.add(m1);
        mBar.add(m4);
        // mBar.add(m5);

        this.setJMenuBar(mBar);

        m1_item1.addActionListener(this);
        m4_item1.addActionListener(this);
        m4_item2.addActionListener(this);



        con.add(ConfInfo.terBiliLive_control_ui.Control_Ui_Jpanel, BorderLayout.NORTH);
        con.add(Ui_SOUTH_Jpanel, BorderLayout.SOUTH);
        con.add(ConfInfo.terBiliLive_dmj_ui.DMJ_Ui_Jpanel, BorderLayout.CENTER);


        m1_item1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                //创建目录
                FileUtil.createDir("Ter/data/");
                FileUtil.createDir("Ter/log/");
                FileUtil.createDir("Ter/Dm/");
                //写入数据
                FileUtil.writeFile("RoomId", ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText());
                FileUtil.writeFile("Cookie","");
                FileUtil.writeFile("Second", ConfInfo.terBiliLive_gg_ui.GG_UiT_Second.getText());
                FileUtil.writeFile("Text",ConfInfo.terBiliLive_gg_ui.GG_UiT_Text.getText());
                ConfInfo.confData.setCookie("");
                ConfInfo.confData.setSecond( GG_UiT_Second.getText());
                ConfInfo.confData.setText(GG_UiT_Text.getText());
                ConfInfo.confData.setRoomId(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText());
                ConfInfo.xmlUtil.writeData();
                ConfInfo.jsonUtil.writeData();
                JOptionPane.showMessageDialog(null,"已经清除您得账号信息，请重新启动软件！");
                System.exit(0);


            }
        });
        m1_item2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                ConfInfo.control_fun.Preservation();



            }
        });
        m4_item2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {


                JOptionPane.showMessageDialog(new JFrame().getContentPane(), "欢迎使用 "+Appname+" 当前版本 "+Version +"\n 问题反馈 ：git@mter.xyz", "关于", JOptionPane.QUESTION_MESSAGE);

            }
        });

        new Control_Monitor();





        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {

                //创建目录
                FileUtil.createDir("Ter/data/");
                FileUtil.createDir("Ter/log/");
                FileUtil.createDir("Ter/Dm/");
                //写入数据
                FileUtil.writeFile("RoomId", ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText());
                FileUtil.writeFile("Cookie",ConfInfo.cookie);
                FileUtil.writeFile("Second", ConfInfo.terBiliLive_gg_ui.GG_UiT_Second.getText());
                FileUtil.writeFile("Text",ConfInfo.terBiliLive_gg_ui.GG_UiT_Text.getText());

                super.windowClosing(e);
            }

        });

            this.setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }




}
