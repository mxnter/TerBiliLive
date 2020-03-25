package com.TerBiliLive.Ui;

import com.TerBiliLive.Img.ImageBroker;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.InfoNew;
import com.TerBiliLive.Info.LiveUserInfo;
import com.TerBiliLive.Utils.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.TerBiliLive.Utils.TimeUtil.*;

public class Greet extends JFrame {
    private JPanel panel;
    private JLabel appName;
    private JLabel version;
    private JLabel viceName;
    private JLabel loading;
    private JLabel background;
    private JProgressBar progressBar;
    private JPanel contentPanel;
    private ImageIcon backgroundImg;
    private int progresValue = 0;



    public Greet() {

        this.setContentPane(panel);

        backgroundImg = ImageBroker.loadImageIcon("greetBackgroud450x300.jpg");
        background.setIcon(backgroundImg);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
        background.setBounds(0,0,backgroundImg.getIconWidth(), backgroundImg.getIconHeight());//设置背景标签的位置

        appName.setText(ConfInfo.AppName.get(ConfInfo.AppSystemId));
        viceName.setText(ConfInfo.AppViceName.get(ConfInfo.AppViceNameId));
        version.setText(ConfInfo.AppVersion);

        this.setTitle(ConfInfo.AppName.get(ConfInfo.AppSystemId));
        this.setIconImage(ImageBroker.loadImages("logoa.png"));  //设置Logo
        this.setSize(450, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //隐藏边框
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置圆角
        final Shape shape = new RoundRectangle2D.Double(0d, 0d, getWidth(), getHeight(), 50, 50);
        setShape(shape);
//        this.setBorder(new LineBorder(Color.WHITE, 5, true));
        background.setForeground(Color.WHITE);
        background.setOpaque(false); //背景透明
        panel.setOpaque(false); //背景透明
        contentPanel.setOpaque(false); //背景透明
        appName.setOpaque(false); //背景透明
        version.setOpaque(false); //背景透明
        viceName.setOpaque(false); //背景透明
        loading.setOpaque(false); //背景透明
        progressBar.setOpaque(false); //背景透明

        //AWTUtilities.setWindowOpaque(this, false);//透明
        //AWTUtilities.setWindowOpacity(this, 0.6f); //透明

//        ConfInfo.cookie=ConfInfo.confData.getCookie();


        this.setVisible(true);

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                int progress = progressBar.getValue();
                while (progress<100){
                    try {
                        if(progresValue>progress){
                            Thread.sleep(10);
                        }else{
                            Thread.sleep(500);
                            continue;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress++;
                    progressBar.setValue(progress);
//                    if(progress%10==0||progress%10==5)loading.setText("加载中");
//                    else loading.setText(loading.getText()+".");

                }
            }
        }).start();
        loading.setText("正在加载数据");
        progresValue = 10;


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if(FileUtil.auditFile(ConfInfo.DatabaseExternalPath)){
            loading.setText("正在加载数据");
            progresValue = 20;
            ConfInfo.databaesUtil=new DatabaesUtil();
            ResultSet resultSet =  ConfInfo.databaesUtil.select(ConfInfo.Database_SelectDatabaseVersion);
            try {
                if(resultSet.next()){
                    if(resultSet.getInt("Version") < ConfInfo.AppDatabaseVersion) {
                        List<String[]> UpdateDatabases = ConfInfo.AppUpdateDatabases.subList(resultSet.getInt("Version")+1,ConfInfo.AppDatabaseVersion+1);
                        UpdateDatabases.forEach(data->{
                            for(String sql : data){
                                ConfInfo.databaesUtil.executeUpdate(sql);
                            }
                        });
                        loading.setText("正在升级数据库");
                        progresValue = 50;
                    }
                }else{
                    InitDatabases();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            InOutPutUtil.outPut(ConfInfo.confData.getCookie());
            progresValue = 40;
            ConfInfo.confData.readConfData();
            progresValue = 50;
            InOutPutUtil.outPut(ConfInfo.confData.toString());
        }else{
            progresValue = 20;
//            FileUtil.copyInternalFiles(ConfInfo.DatabaseExternalPath,ConfInfo.DatabaseInternalPath);
            ConfInfo.databaesUtil=new DatabaesUtil();
            InitDatabases();
        }


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (ConfInfo.confData.getCookie()==null || ConfInfo.confData.getCookie().equals("null")||ConfInfo.confData.getCookie().equals("")){
            progresValue = 100;


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            new Login();
            this.setVisible(false);
        }else{
            try {

                loading.setText("正在自动登录");
                progresValue = 60;
                ConfInfo.liveUserInfo = new LiveUserInfo(ConfInfo.confData.getCookie());
                progresValue = 70;
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"未检测到网络\n请连接网络再试\n\n（如果您多次收到此提示，请查看 Github ：TerBiliLive 是否有更新\n如果未找到更新，请发送邮件到 TerBiliLive@outlook.com 提醒作者程序无法使用！）");
                System.exit(0);
            }
            progresValue = 80;
            ConfInfo.Uid=ConfInfo.liveUserInfo.getUid();
            ConfInfo.Uname=ConfInfo.liveUserInfo.getUname();
            progresValue = 85;
            ConfInfo.liveConf=null;
            if(null==ConfInfo.liveUserInfo.getUid()||ConfInfo.Uid.equals("")){
                progresValue = 100;


                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                JOptionPane.showMessageDialog(null,"登陆过期，请重新登陆！");
                new Login();
                this.dispose();
                this.setVisible(false);

            }else{
                if(ConfInfo.Rnd.equals(""))ConfInfo.Rnd=TimeUtil.timeStamp();
                progresValue = 90;
                ConfInfo.infoNew = new InfoNew(ConfInfo.confData.getCookie());
                ConfInfo.dingtalk.LogIn();
                progresValue = 100;


                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtil.clearDatabase(); //清理日志

                loading.setText("登陆成功");



                new Console();
                this.dispose();
                this.setVisible(false);
            }

        }

    }

    public void InitDatabases(){
        ConfInfo.AppInitDatabases.forEach(sql->{
            ConfInfo.databaesUtil.executeUpdate(sql);
        });
        loading.setText("正在创建数据");
        progresValue = 50;
    }


}
