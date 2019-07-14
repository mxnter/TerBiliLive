package com.TerBiliLive.Ui;

import com.TerBiliLive.Img.ImageBroker;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Monitor.Control_Monitor;
import com.TerBiliLive.TerBiliLive.SendGet;
import com.TerBiliLive.Utiliy.*;
import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.json.JSONException;
import org.json.JSONObject;

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
import static com.TerBiliLive.Ui.TerBiliLive_Adv_Ui.GG_UiT_Second;
import static com.TerBiliLive.Ui.TerBiliLive_Adv_Ui.GG_UiT_Text;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatHour;

/**
 * @名称 整合所有 UI
 * @作用 整合所有
 * @作者 Mxnter
 *
 * */

public class TerBiliLive_Ui extends JFrame implements ActionListener {


    public static String StartInfo = "Ter制作的 “TerBiliLive” 弹幕姬 - 测试中";
    public static String NetworkingInfo = null;
    public static String Version = ConfInfo.Version;
    public static String Appname = "TerBiliLive";
    public static String ProjectName = "TerBiliLive";
    //String UipathUrl = "http://live.bilibili.com/msg/send";


    JPanel Ui_SOUTH_Jpanel = new JPanel(new GridLayout(2, 1, 5, 5));

    JMenuBar mBar = new JMenuBar();

    public JMenu m1 = new JMenu("编辑");
    public JMenuItem m1_item1 = new JMenuItem("清除账户");
    public JMenuItem m1_item2 = new JMenuItem("清除Key");
    public JMenuItem m1_item3 = new JMenuItem("保存信息");


    public JMenu m4 = new JMenu("关于");
    public JMenuItem m4_item1 = new JMenuItem("帮助");
    public JMenuItem m4_item2 = new JMenuItem("关于");
    public JMenuItem m4_item3 = new JMenuItem("官网");

    public JMenu m3 = new JMenu("帮助");
    public JMenuItem m3_item1 = new JMenuItem("检查更新");
    public JMenuItem m3_item2 = new JMenuItem("关于");
    public JMenuItem m3_item3 = new JMenuItem("官网");


    ImageIcon img_bj =null;
    JLabel imgLabel =null;
    public TerBiliLive_Ui()

    {
//        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
//        try {
//            UIManager.setLookAndFeel(lookAndFeel);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }



        this.setTitle(ConfInfo.Uname+" "+Appname + " " + ConfInfo.Version);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.white);


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
            icon =  ImageBroker.loadImage("logoa.png").getImage();
        } catch (IOException e) {
            icon = Toolkit.getDefaultToolkit().getImage("Ter/logoa.png");
            e.printStackTrace();
        }

        if (icon != null) this.setIconImage(icon);  // 图片的具体位置

//        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
//        imgLabel.setBounds(0,0,img_bj.getIconWidth(), img_bj.getIconHeight());//设置背景标签的位置

        Container con = getContentPane();
        this.setResizable(true); //允许调整大小


        ConfInfo.terBiliLive_control_ui =new TerBiliLive_Control_Ui();
        ConfInfo.terBiliLive_chargeBarrage_ui = new TerBiliLive_ChargeBarrage_Ui();
        ConfInfo.terBiliLive_sendBarrage_ui = new TerBiliLive_SendBarrage_Ui();
        ConfInfo.terBiliLive_adv_ui = new TerBiliLive_Adv_Ui();




//        ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.setText(FileUtil.readFile("RoomId"));

//老版本弃用
//        ConfInfo.terBiliLive_control_ui.Control_UiT_Uid.setText(ConfInfo.Uid);
//        ConfInfo.terBiliLive_control_ui.Control_UiT_Uname.setText(ConfInfo.Uname);
//
////        ConfInfo.terBiliLive_control_ui.Control_UiT_Cookie.setText(FileUtil.readFile("Cookie"));ConfInfo.cookie=ConfInfo.terBiliLive_control_ui.Control_UiT_Cookie.getText();
//
//        ConfInfo.terBiliLive_adv_ui.GG_UiT_Second.setText(FileUtil.readFile("Second"));
//        ConfInfo.terBiliLive_adv_ui.GG_UiT_Text.setText(FileUtil.readFile("Text"));

        ConfInfo.terBiliLive_control_ui.Control_UiT_Uid.setText(ConfInfo.Uid);
        ConfInfo.terBiliLive_control_ui.Control_UiT_Uname.setText(ConfInfo.Uname);

//        ConfInfo.terBiliLive_control_ui.Control_UiT_Cookie.setText(FileUtil.readFile("Cookie"));ConfInfo.cookie=ConfInfo.terBiliLive_control_ui.Control_UiT_Cookie.getText();

        ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.setText(ConfInfo.confData.getRoomId());
        ConfInfo.terBiliLive_adv_ui.GG_UiT_Second.setText(ConfInfo.confData.getSecond());
        ConfInfo.terBiliLive_adv_ui.GG_UiT_Text.setText(ConfInfo.confData.getText());





//        HFJ.HFJ_UiT_RoomId.setText(FileUtil.readFile("RoomId"));
        //HFJ.HFJ_UiT_Text.setText(FileUtil.readFile("Text"));
//        HFJ.HFJ_UiT_Cookie.setText(FileUtil.readFile("Cookie"));
        //System.out.print(readFile("RoomId.txt"));
        ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Time.setText("Ter 简单制造");
        ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_State.setText("Ter 简单制造"+ TerBiliLive_SendBarrage_Ui.Version);
        ConfInfo.terBiliLive_control_ui.Control_UiT_State.setText("Ter 简单制造"+ TerBiliLive_ChargeBarrage_Ui.Version);
        ConfInfo.terBiliLive_adv_ui.GG_UiT_State.setText("Ter 简单制造"+ TerBiliLive_Adv_Ui.Version);
        if (ConfInfo.putShowUtil == null) ConfInfo.putShowUtil = new PutShowUtil();
        ConfInfo.putShowUtil.PutDMUtil(StartInfo+"\n"+"欢迎使用 "+Appname+" 当前版本 "+Version,Color.DARK_GRAY);
        new Thread(new Runnable(){
            public void run(){
                try {

                    String msg="";
                    JSONObject jsonObject = new JSONObject(SendGet.sendGet("https://mxnter.github.io/TerBiliLiveV/Networking/msg.json","",""));
                    msg = jsonObject.getString("msg");
                    ConfInfo.putShowUtil.PutDMUtil("开发者通知：\n    "+msg ,Color.DARK_GRAY);
                    int SVersionNum = ConfInfo.VersionNum;
                    int level = -1;
                    String SVersion = Version;
                    JSONObject SVersionJson = new JSONObject(SendGet.sendGet("https://mxnter.github.io/TerBiliLiveV/Networking/Version.json","",""));
                    SVersion = SVersionJson.getString("version");
                    SVersionNum = Integer.parseInt(SVersionJson.getString("versionNum"));
                    level = Integer.parseInt(SVersionJson.getString("level"));
                    ConfInfo.putShowUtil.PutDMUtil("最新版本：\n    "+SVersion ,Color.DARK_GRAY);
                    Thread.sleep(100);
                    if(ConfInfo.VersionNum<SVersionNum){
                        if(level>-1){
                            ConfInfo.putShowUtil.PutDMUtil("收到一份更新提醒,请尽快更新！\n    " ,Color.DARK_GRAY);
                        }else  if(level>0){
                            if(JOptionPane.showConfirmDialog(null, "找到最新版本：\n"+SVersion+"\n请尽快更新", "更新",JOptionPane.OK_CANCEL_OPTION)==0){
                                OpenUtil.OpenUrl("https://github.com/mxnter/TerBiliLive");
                            }else{
                                if(level>1){
                                    JOptionPane.showMessageDialog(null,"当前更新为紧急更新，如果您取消了更新，可能会导致软件无法继续运行！");
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    ConfInfo.putShowUtil.PutDMUtil("[ Ter ] ---X--->> [本机] 【获取开发者通知失败】",Color.DARK_GRAY);
                    e.printStackTrace();
                }

            }
        }).start();
        if(SendGet.sendGet("https://live.bilibili.com/","","").equals(null)){
            ConfInfo.putShowUtil.PutDMUtil("未检测到网络，请检查您的网络（或许有可能检测服务器失效",Color.DARK_GRAY);
        }else {
            try {
                ConfInfo.putShowUtil.PutDMUtil(" [Live Bilibili]  << ------ >>  ["+ InetAddress.getLocalHost().getHostName()+ "]    【网络连接正常】",Color.DARK_GRAY);
            } catch (UnknownHostException e) {
                ConfInfo.putShowUtil.PutDMUtil(" [Live Bilibili]  << ------ >>  [本机]    【网络连接正常】",Color.DARK_GRAY);
                e.printStackTrace();
            }
        }
        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), ConfInfo.terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.getText(),Control_UiT_RoomId.getText());
        if(ConfInfo.Rnd.equals(""))ConfInfo.Rnd=TimeUtil.timeStamp();




        Ui_SOUTH_Jpanel.add(ConfInfo.terBiliLive_adv_ui.GG_Ui_Jpanel);
        Ui_SOUTH_Jpanel.add(ConfInfo.terBiliLive_sendBarrage_ui.HFJ_Ui_Jpanel);


        // 设置菜单
        m1.add(m1_item1);
        m1.add(m1_item2);
        m1.add(m1_item3);

        m3.add(m3_item1);
//        m3.add(m3_item2);
//        m3.add(m3_item3);

//        m4.add(m4_item1);
        m4.add(m4_item2);
        m4.add(m4_item3);

        mBar.add(m1);
        mBar.add(m3);
        mBar.add(m4);
        // mBar.add(m5);

        this.setJMenuBar(mBar);

        m1_item1.addActionListener(this);
        m3_item1.addActionListener(this);
        m4_item1.addActionListener(this);
        m4_item2.addActionListener(this);
        m4_item3.addActionListener(this);



        con.add(ConfInfo.terBiliLive_control_ui.Control_Ui_Jpanel, BorderLayout.NORTH);
        con.add(Ui_SOUTH_Jpanel, BorderLayout.SOUTH);
        con.add(ConfInfo.terBiliLive_chargeBarrage_ui.DMJ_Ui_Jpanel, BorderLayout.CENTER);


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
                FileUtil.writeFile("Second", ConfInfo.terBiliLive_adv_ui.GG_UiT_Second.getText());
                FileUtil.writeFile("Text",ConfInfo.terBiliLive_adv_ui.GG_UiT_Text.getText());
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
                ConfInfo.confData.setTulingApikey("");
                ConfInfo.jsonUtil.writeData();
                ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false);
                JOptionPane.showMessageDialog(null,"已经清除图灵Key");



            }
        });
        m1_item3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                ConfInfo.control_fun.Preservation();



            }
        });

        m3_item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int SVersionNum = ConfInfo.VersionNum;
                int level = -1;
                String SVersion = ConfInfo.Version;
                JSONObject SVersionJson = null;
                try {
                    SVersionJson = new JSONObject(SendGet.sendGet("https://mxnter.github.io/TerBiliLiveV/Networking/Version.json","",""));
                    SVersion = SVersionJson.getString("version");
                    SVersionNum = Integer.parseInt(SVersionJson.getString("versionNum"));
                    level = Integer.parseInt(SVersionJson.getString("level"));
                    if(ConfInfo.VersionNum<SVersionNum){
                        if(JOptionPane.showConfirmDialog(null, "找到最新版本：\n"+SVersion+"\n请尽快更新", "更新",JOptionPane.OK_CANCEL_OPTION)==0){
                            OpenUtil.OpenUrl("https://github.com/mxnter/TerBiliLive");
                        }else{
                            if(level>1){
                                JOptionPane.showMessageDialog(null,"当前更新为紧急更新，如果您取消了更新，可能会导致软件无法继续运行！");
                            }
                        }

                    }else{
                        JOptionPane.showMessageDialog(null,"您已经是最新版本啦！");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        m4_item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(new JFrame().getContentPane(), "欢迎使用 "+Appname+" 当前版本 "+Version +"\n 问题反馈 ：TerBiliLive@outlook.com", "关于", JOptionPane.QUESTION_MESSAGE);
            }
        });
        m4_item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                OpenUtil.OpenUrl("https://github.com/mxnter/TerBiliLive");
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
                FileUtil.writeFile("Second", ConfInfo.terBiliLive_adv_ui.GG_UiT_Second.getText());
                FileUtil.writeFile("Text",ConfInfo.terBiliLive_adv_ui.GG_UiT_Text.getText());

                super.windowClosing(e);
            }

        });

        this.setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }




}
