package com.TerBiliLive.Ui;

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

    JMenuBar mBar = new JMenuBar();

    public JMenu m1 = new JMenu("管理账号");
    public JMenuItem m1_item1 = new JMenuItem("重新登陆");

    public JMenu m4 = new JMenu("关于");
    public JMenuItem m4_item1 = new JMenuItem("帮助");
    public JMenuItem m4_item2 = new JMenuItem("关于");


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


        ConfInfo.terBiliLive_control_ui =new TerBiliLive_Control_Ui();
        ConfInfo.terBiliLive_dmj_ui = new TerBiliLive_DMJ_Ui();
        ConfInfo.terBiliLive_hfj_ui = new TerBiliLive_HFJ_Ui();
        ConfInfo.terBiliLive_gg_ui= new TerBiliLive_GG_Ui();


        ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.setText(FileUtil.readFile("RoomId"));


        ConfInfo.terBiliLive_control_ui.Control_UiT_Uid.setText(ConfInfo.Uid);
        ConfInfo.terBiliLive_control_ui.Control_UiT_Uname.setText(ConfInfo.Uname);

//        ConfInfo.terBiliLive_control_ui.Control_UiT_Cookie.setText(FileUtil.readFile("Cookie"));ConfInfo.cookie=ConfInfo.terBiliLive_control_ui.Control_UiT_Cookie.getText();

        ConfInfo.terBiliLive_gg_ui.GG_UiT_Second.setText(FileUtil.readFile("Second"));
        ConfInfo.terBiliLive_gg_ui.GG_UiT_Text.setText(FileUtil.readFile("Text"));
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
        ConfInfo.putShowUtil.PutDMUtil(sendGet.sendGet("https://mxnter.github.io/TerBiliLiveV/Networking/","",""));
        if(sendGet.sendGet("https://mxnter.github.io/TerBiliLiveV/Networking/","","").equals(null)){
            ConfInfo.putShowUtil.PutDMUtil("未检测到网络，请检查您的网络（或许有可能检测服务器失效");
        }else {
            ConfInfo.putShowUtil.PutDMUtil("网络正常");
        }
        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), ConfInfo.terBiliLive_dmj_ui.DMJ_UiT_Text.getText(),Control_UiT_RoomId.getText());
        if(ConfInfo.Rnd.equals(""))ConfInfo.Rnd=TimeUtil.timeStamp();




        Ui_SOUTH_Jpanel.add(ConfInfo.terBiliLive_gg_ui.GG_Ui_Jpanel);
        Ui_SOUTH_Jpanel.add(ConfInfo.terBiliLive_hfj_ui.HFJ_Ui_Jpanel);


        // 设置菜单
        m1.add(m1_item1);


        m4.add(m4_item1);
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
                JOptionPane.showMessageDialog(null,"已经清除您得账号信息，请重新启动软件！");
                System.exit(0);


            }
        });
        m4_item1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub



            }
        });
        m4_item2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {



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
