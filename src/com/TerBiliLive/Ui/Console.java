package com.TerBiliLive.Ui;

import com.TerBiliLive.Img.ImageBroker;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.TerBiliLive.HttpClient;
import com.TerBiliLive.TerBiliLive.TerWindowListener;
import com.TerBiliLive.Utils.InOutPutUtil;
import com.TerBiliLive.Utils.OpenUtil;
import com.TerBiliLive.Utils.TimeUtil;
import com.TerBiliLive.Utils.TimerUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
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
    private JButton autoSpeakSet;
    private JLabel billCoin;
    private JTextArea log;
    private JPanel statePanel;
    private JLabel doSignState;
    private JButton cs;
    private JLabel doSignText;
    private JScrollPane logJScrollPane;
    private JButton autoSpeak;
    private JLabel autoSpeakText;
    private JLabel autoSpeakState;
    private JPanel autoSpeakFunctionPanel;
    private JLabel basicsLabel;
    private JLabel autoSpeakLabel;
    private JLabel masterLabel;
    private JPanel masterPanel;
    private JLabel breathing;
    private JPanel breathingPanel;
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
        billCoin.setText(ConfInfo.liveUserInfo.getBillCoin());
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
        autoSpeakSet.setBorderPainted(false);//不绘制边框
        msgPanel.setOpaque(false); //背景透明
        functionPanel.setOpaque(false); //背景透明
        statePanel.setOpaque(false); //背景透明
        autoSpeakFunctionPanel.setOpaque(false); //背景透明
        breathingPanel.setOpaque(false); //背景透明
        masterPanel.setOpaque(false); //背景透明
        logJScrollPane.setOpaque(false); //背景透明
        logJScrollPane.getViewport().setOpaque(false); //背景透明
        logJScrollPane.setBorder(null); //取消边框
        log.setOpaque(false); //背景透明
        log.setLineWrap(true); //自动换行
        log.setEditable(false); //禁止输入


        //AWTUtilities.setWindowOpaque(this, false);//透明
        //AWTUtilities.setWindowOpacity(this, 0.6f); //透明


        this.setVisible(true);


        // 获取开发者通知
        try {
//            String msg="";
            JSONObject jsonObject = new JSONObject( HttpClient.sendGet(ConfInfo.AppServerHello,"",""));
            JSONArray msg = jsonObject.getJSONArray("msg");
            for(int i=0;i<msg.length();i++) {
                sendLog(msg.getString(i));
            }
        } catch (Exception e) {
            sendLog("[ Ter ] 服务器连接失败");
            e.printStackTrace();
        }

        // 呼吸灯
        new Thread(() -> {
            boolean rr = true;
            int r = 0;
            while(true){
                if(rr&&r>0)r--; else rr =false;
                if(!rr&&r<187)r++; else rr =true;
                breathing.setForeground(new Color(r,255,255));
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
//        if(ConfInfo.breathing==null){
//            ConfInfo.breathing = new TimerUtil("签到-每天");
//            ConfInfo.breathing.Init(new TimerTask() {
//                @Override
//                public void run() {
//        int r = 0;
//        int g = 0;
//        int b = 187;
//        while(true){
//            if(r!=187&&g==0&&b==187)r++;
//            if(r==187&&g==0&&b!=0)b--;
//            if(r==187&&g!=187&&b==0)g++;
//            System.out.println(g+","+b+","+r);
//            if(r==187&&g==187&&b==0){r = 0;g = 0;b = 187; }
//            breathing.setForeground(new Color(r,g,b));
//            try {
//                Thread.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//                    breathing.setForeground(new Color());
//                }
//            });
//        }

        sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                // 定时任务

                if(ConfInfo.doSign==null){
                    ConfInfo.doSign = new TimerUtil("签到-每天");
                    ConfInfo.doSign.Init(new TimerTask() {
                        @Override
                        public void run() {
                            InOutPutUtil.outPut("每日自动签到(Live)");
//                          msg.setText("每日自动签到");
                            sendLog("每日自动签到(Live)");
                            doSign();
                        }
                    });
                }
                if(!ConfInfo.systemState.isDoSign){
                    InOutPutUtil.outPut("开启每日自动签到(Live)");
//                  msg.setText("开启每日自动签到");
                    sendLog("开启每日自动签到(Live)");
                    ConfInfo.doSign.StartNow(60*60*24);
                    ConfInfo.systemState.isDoSign = true;
//                    sign.setText("关闭自动签到");
                }else{
                    InOutPutUtil.outPut("关闭每日自动签到(Live)");
//                  msg.setText("开启每日自动签到");
                    sendLog("关闭每日自动签到(Live)");
                    ConfInfo.doSign.Stop();
                    ConfInfo.systemState.isDoSign = false;
//                    sign.setText("开启自动签到");
                }
                // 更新状态
                updateStatus();
            }
        });

        autoSpeak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if(ConfInfo.autoSpeak==null){
                    ConfInfo.autoSpeak = new TimerUtil("广告姬");
                    ConfInfo.autoSpeak.Init(new TimerTask() {
                        @Override
                        public void run() {
                            InOutPutUtil.outPut("广告姬开始工作啦(Live)");
                            sendLog("广告姬开始工作啦(Live)");

                        }
                    });
                }
                if(!ConfInfo.systemState.isAutoSpeak){
                    InOutPutUtil.outPut("开启广告姬(Live)");
                    sendLog("开启广告姬(Live)");
                    ConfInfo.autoSpeak.StartNow(60);
                    ConfInfo.systemState.isAutoSpeak = true;
//                    autoSpeak.setText("开启广告姬");
                }else{
                    InOutPutUtil.outPut("关闭广告姬(Live)");
                    sendLog("关闭广告姬(Live)");
                    ConfInfo.autoSpeak.Stop();
                    ConfInfo.systemState.isAutoSpeak = false;
//                    autoSpeak.setText("关闭广告姬");
                }
                // 更新状态
                updateStatus();
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
        autoSpeakSet.addActionListener(new ActionListener() {
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
            sendLog(data.getString("message"));
//            msg.setText(data.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    void sendLog(String msg){
        log.append(TimeUtil.getFormat() + " | "+msg+"\n");
        log.setCaretPosition(log.getText().length());
        if(log.getSelectedText()!=null){
            log.setCaretPosition(log.getSelectedText().length());
            log.requestFocus();
        }
    }

    void startAutoSpeak(String msg){
        log.append(TimeUtil.getFormat() + " | "+msg+"\n");
        log.setCaretPosition(log.getText().length());
        if(log.getSelectedText()!=null){
            log.setCaretPosition(log.getSelectedText().length());
            log.requestFocus();
        }
    }


    void updateStatus(){
        if(ConfInfo.systemState.isDoSign)doSignState.setForeground(Color.green);
        else doSignState.setForeground(Color.black);
        if(ConfInfo.systemState.isAutoSpeak)autoSpeakState.setForeground(Color.green);
        else autoSpeakState.setForeground(Color.black);
    }

}
