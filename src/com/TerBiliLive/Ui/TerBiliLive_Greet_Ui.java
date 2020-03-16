package com.TerBiliLive.Ui;

import com.TerBiliLive.Img.ImageBroker;
import com.TerBiliLive.Info.*;
import com.TerBiliLive.Utils.InOutPutUtil;
import com.TerBiliLive.Utils.LogUtil;
import com.TerBiliLive.Utils.TimeUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static com.TerBiliLive.Utils.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utils.TimeUtil.getFormatHour;

/**
 * @名称 欢迎界面 UI
 * @作用 在开启程序时候读取图片 读取已存储的个人信息 测试 Cookie 是否可以登陆到 Bilibili
 * @作者 Mxnter
 *
 * */

public class TerBiliLive_Greet_Ui extends JFrame {

    JPanel pp=new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel ll=new JLabel("");
    JPanel p=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel l=new JLabel("登陆中.");
    JLabel bt=new JLabel("TerBiliLive");
    ImageIcon img_bj =null;
    JLabel imgLabel =null;
    public TerBiliLive_Greet_Ui(){

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
            icon =  ImageBroker.loadImage("logoa.png").getImage();
        } catch (IOException e) {
            icon = Toolkit.getDefaultToolkit().getImage("Ter/logoa.png");
            e.printStackTrace();
        }

        if (icon != null) this.setIconImage(icon);  // 图片的具体位置

        ImageIcon img_miao =null;
        try {
            img_miao = ImageBroker.loadImage("ganbei.png");
        } catch (IOException e) {
            img_miao = new ImageIcon("Ter/img/ganbei.png");//这是背景图片
            e.printStackTrace();
        }
        JLabel img_miaoLabel = new JLabel(img_miao);//将背景图放在标签里。

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
        pp.setOpaque(false); //背景透明
        pp.add(img_miaoLabel);
        pp.add(ll);
        p.add(l);

        //	con.add(bt,BorderLayout.CENTER);
        con.add(p,BorderLayout.SOUTH);
        con.add(pp,BorderLayout.CENTER);
//                FileUtil.readFile("Cookie");
        InOutPutUtil.outPut(ConfInfo.confData.getCookie());


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

        if (ConfInfo.confData.getCookie().equals("null")||ConfInfo.confData.getCookie().equals("")){
            new TerBiliLive_Login_Ui();
            this.setVisible(false);
        }else{
            try {
                ConfInfo.liveUserInfo = new LiveUserInfo(ConfInfo.confData.getCookie());
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"未检测到网络\n请连接网络再试\n\n（如果您多次收到此提示，请查看 Github ：TerBiliLive 是否有更新\n如果未找到更新，请发送邮件到 TerBiliLive@outlook.com 提醒作者程序无法使用！）");
                System.exit(0);
            }

            ConfInfo.Uid=ConfInfo.liveUserInfo.getUid();
            ConfInfo.Uname=ConfInfo.liveUserInfo.getUname();
            ConfInfo.liveConf=null;
            if(null==ConfInfo.liveUserInfo.getUid()||ConfInfo.Uid.equals("")){
                JOptionPane.showMessageDialog(null,"登陆过期，请重新登陆！");
                TerBiliLive_Login_Ui live_login_ui=new TerBiliLive_Login_Ui();
                dispose();
//                this.setVisible(false);

            }else{
                if(ConfInfo.Rnd.equals(""))ConfInfo.Rnd=TimeUtil.timeStamp();
                ConfInfo.infoNew = new InfoNew(ConfInfo.confData.getCookie());
                ConfInfo.dingtalk.LogIn();
                l.setText("登陆成功");
                TerBiliLive_Ui ui = new TerBiliLive_Ui();
                dispose();
                this.setVisible(false);
            }

        }
    }



}
