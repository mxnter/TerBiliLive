package com.TerBiliLive.Ui;

import com.TerBiliLive.Img.ImageBroker;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.TerBiliLive.HttpClient;
import com.TerBiliLive.TerBiliLive.TerWindowListener;
import com.TerBiliLive.Utils.InOutPutUtil;
import com.TerBiliLive.Utils.TimerUtil;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.TimerTask;

public class Console extends JFrame {
    private JPanel contentPanel;
    private JLabel appName;
    private JLabel viceName;
    private JLabel version;
    private JLabel background;
    private JProgressBar user_level_rank;
    private JPanel panel;
    private JPanel userInfoPanel;
    private JLabel uname;
    private JLabel user_level;
    private JLabel face;
    private JPanel facePanel;
    private JPanel infoPanel;
    private JPanel panel1;
    private JButton sign;
    private JLabel msg;
    private JButton master;
    private JButton logout;
    private JPanel functionPanel;
    private JPanel msgPanel;
    private JButton autoSpeak;
    private ImageIcon backgroundImg;
    public Console() {
        this.setContentPane(panel);

        backgroundImg = ImageBroker.loadImageIcon("consoleBackground600x400.jpg");
        background.setIcon(backgroundImg);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
        background.setBounds(0,0,backgroundImg.getIconWidth(), backgroundImg.getIconHeight());//设置背景标签的位置

        appName.setText(ConfInfo.AppName.get(ConfInfo.AppSystemId));
        viceName.setText(ConfInfo.AppViceName.get(ConfInfo.AppViceNameId));
        version.setText(ConfInfo.AppVersion);

        uname.setText(ConfInfo.liveUserInfo.getUname());
        user_level.setText(ConfInfo.liveUserInfo.getUser_level());
        String path=ConfInfo.liveUserInfo.getFace();
        try {
            ImageIcon image = new ImageIcon(new URL(path),"头像");//设置图片的来源路径（图片的URL）
            image.setImage(image.getImage().getScaledInstance(100, 100, 100));//设置图片大小
            face.setIcon(image);
        } catch (MalformedURLException e) {
            face.setIcon(ImageBroker.loadImageIcon("defaultFace.jpg"));
            e.printStackTrace();
        }
        user_level_rank.setMaximum(Integer.parseInt(ConfInfo.liveUserInfo.getUser_next_intimacy()));
        user_level_rank.setValue(Integer.parseInt(ConfInfo.liveUserInfo.getUser_intimacy()));
        user_level_rank.setString(ConfInfo.liveUserInfo.getUser_intimacy()+"/"+ConfInfo.liveUserInfo.getUser_next_intimacy());
        this.setTitle(ConfInfo.AppName.get(ConfInfo.AppSystemId));
        this.setName("Console");
        this.addWindowListener(new TerWindowListener(this));
        this.setIconImage(ImageBroker.loadImages("logoa.png"));  //设置Logo
        this.setSize(400, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //隐藏边框
//        this.setUndecorated(true);
//        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        background.setForeground(Color.WHITE);
        panel.setOpaque(false); //背景透明
        contentPanel.setOpaque(false); //背景透明
        appName.setOpaque(false); //背景透明
        viceName.setOpaque(false); //背景透明
        version.setOpaque(false); //背景透明
        background.setOpaque(false); //背景透明
        userInfoPanel.setOpaque(false); //背景透明
        facePanel.setOpaque(false); //背景透明
        infoPanel.setOpaque(false); //背景透明
        panel1.setOpaque(false); //背景透明
        logout.setOpaque(false); //背景透明
        logout.setBorderPainted(false);//不绘制边框
        sign.setBorderPainted(false);//不绘制边框
        master.setBorderPainted(false);//不绘制边框
        autoSpeak.setBorderPainted(false);//不绘制边框
        msgPanel.setOpaque(false); //背景透明
        functionPanel.setOpaque(false); //背景透明


        //AWTUtilities.setWindowOpaque(this, false);//透明
        //AWTUtilities.setWindowOpacity(this, 0.6f); //透明


        this.setVisible(true);

        sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                ConfInfo.doSign = new TimerUtil("签到-每天");
                ConfInfo.doSign.Init(new TimerTask() {
                    @Override
                    public void run() {
                        InOutPutUtil.outPut("开启每日自动签到");
                        msg.setText("开启每日自动签到");
                        doSign();
                    }
                });
                ConfInfo.doSign.StartNow(60*60*24);



            }
        });

        master.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if(ConfInfo.terBiliLive_ui == null){
                    ConfInfo.terBiliLive_ui = new TerBiliLive_Ui();
                }else{
                    ConfInfo.terBiliLive_ui.setVisible(true);
                }

            }
        });
        autoSpeak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                new AutoSpeak();

            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                ConfInfo.control_fun.Clear();
            }
        });


    }

    public void doSign() {
        String returnData = HttpClient.sendGet(ConfInfo.BiliLiveServer_doSign,ConfInfo.confData.getCookie());
        try {
            JSONObject data = new JSONObject(returnData);
            msg.setText(data.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
