package com.TerBiliLive.Ui;

import com.TerBiliLive.Img.ImageBroker;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.TerBiliLive.HttpClient;
import com.TerBiliLive.Utils.*;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.TerBiliLive.Utils.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utils.TimeUtil.getFormatHour;

public class Login extends JFrame{
    private JPanel contentPanel;
    private JLabel appName;
    private JLabel viceName;
    private JLabel version;
    private JLabel background;
    private JLabel title;
    private JTextField cookieValue;
    private JButton login;
    private JLabel cookie;
    private JLabel tips;
    private JLabel tips1;
    private JLabel tips2;
    private JLabel clear;
    private JLabel paste;
    private JLabel msg;
    private JPanel loginPanel;
    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JButton noLogin;
    private ImageIcon backgroundImg;

    public Login() {
        this.setContentPane(contentPanel);

        backgroundImg = ImageBroker.loadImageIcon("loginBackground500x750.jpg");
        background.setIcon(backgroundImg);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
        background.setBounds(0,0,backgroundImg.getIconWidth(), backgroundImg.getIconHeight());//设置背景标签的位置

        appName.setText(ConfInfo.AppName.get(ConfInfo.AppSystemId));
        viceName.setText(ConfInfo.AppViceName.get(ConfInfo.AppViceNameId));
        version.setText(ConfInfo.AppVersion);

        this.setTitle(ConfInfo.AppName.get(ConfInfo.AppSystemId));
        this.setIconImage(ImageBroker.loadImages("logoa.png"));  //设置Logo
        this.setSize(750, 500);
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
        title.setOpaque(false); //背景透明
        cookieValue.setOpaque(false); //背景透明
        cookie.setOpaque(false); //背景透明
        tips.setOpaque(false); //背景透明
        tips1.setOpaque(false); //背景透明
        tips2.setOpaque(false); //背景透明
        clear.setOpaque(false); //背景透明
        paste.setOpaque(false); //背景透明
        msg.setOpaque(false); //背景透明
        loginPanel.setOpaque(false); //背景透明
        panel1.setOpaque(false); //背景透明
        panel2.setOpaque(false); //背景透明
        panel3.setOpaque(false); //背景透明
        panel4.setOpaque(false); //背景透明
        panel5.setOpaque(false); //背景透明
        noLogin.setBorderPainted(false);//不绘制边框
        login.setBorderPainted(false);//不绘制边框

        //AWTUtilities.setWindowOpaque(this, false);//透明
        //AWTUtilities.setWindowOpacity(this, 0.6f); //透明

        InOutPutUtil.outPut(ConfInfo.confData.getCookie());

        this.setVisible(true);


        //弹出授权记录信息提示框
        String SQ = "尊敬的用户：\n" +
                "　　您好，感谢您使用 TerBiliLive 弹幕姬。\n" +
                "　　在您使用之前我们将告知您，为了统计弹幕姬使用人和次数，\n" +
                "我们将统计部分信息，例如 IP、Mac、主机名等。这将发送到我们\n" +
                "的服务上，以帮助开发者了解软件是否存在价值。\n" +
                "　　开发者仅发送您的Cookie到BiliBili服务器,您的Cookie加密存储在本地。\n" +
                "\n" +
                "如果您觉得我们可以信赖，您可以选择 “确定”。如果您不想统计您的信\n" +
                "息，请选择 “取消”。\n" +
                "\n" +
                "官方网站:http://terbililive.mter.top/\n"+
                "GITHUB:https://github.com/mxnter/TerBiliLive\n"+
                "\n" +
                "再次感谢您的使用。\n";
        if(new AgreementUtil().readFile().equals("NO")){
            //  JOptionPane.showInputDialog(null,SQ,"用户授权",JOptionPane.PLAIN_MESSAGE).equals("我同意")
            if(JOptionPane.showConfirmDialog(null, SQ, "用户授权",JOptionPane.OK_CANCEL_OPTION)==0){
                new AgreementUtil().putAgreement(SQ+"\n"+"用户：同意");
                ConfInfo.dingtalk.Agreement("同意");
            }else{
                ConfInfo.dingtalk.Agreement("不同意");
//                System.exit(0); //不同意后关闭软件
            }
        }
        new Thread(new Runnable() {
            public void run() {
                int SVersionNum = ConfInfo.VersionNum;
                int level = -1;
                String SVersion = ConfInfo.Version;
                JSONObject SVersionJson = null;
                try {
                    SVersionJson = new JSONObject( HttpClient.sendGet(ConfInfo.AppServerVersion, "", ""));
                    SVersion = SVersionJson.getString("version");
                    SVersionNum = Integer.parseInt(SVersionJson.getString("versionNum"));
                    level = Integer.parseInt(SVersionJson.getString("level"));
                    if (ConfInfo.VersionNum < SVersionNum) {
                        if (level >= -1) {
                            msg.setText("收到一份重要更新提醒,请尽快更新！");
                            msg.setForeground(Color.RED);
                        }
                        if (level > 0) {
                            if (JOptionPane.showConfirmDialog(null, "找到最新版本：\n" + SVersion + "\n请尽快更新", "更新", JOptionPane.OK_CANCEL_OPTION) == 0) {
                                OpenUtil.OpenUrl("https://github.com/mxnter/TerBiliLive");
                            } else {
                                if (level > 1) {
                                    JOptionPane.showMessageDialog(null, "当前更新为紧急更新，如果您取消了更新，可能会导致软件无法继续运行！");
                                }
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                ConfInfo.confData.setCookie(cookieValue.getText());
                if (ConfInfo.confData.getCookie()==null||ConfInfo.confData.getCookie().equals("null")||ConfInfo.confData.getCookie().equals("")){
                    msg.setText("请输入Cookie！");
                    return;
                }
                if(ConfInfo.dev){
                    FileUtil.writeFile("Cookie",ConfInfo.confData.getCookie());
                }
                ConfInfo.confData.writeConfData();
                new Greet();
                dispose();
            }
        });

        noLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                TerBiliLive_Ui ui =new TerBiliLive_Ui();
                ConfInfo.Uname = "游客";
                dispose();
            }
        });

        tips1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OpenUtil.OpenUrl("https://github.com/mxnter/TerBiliLive#%E4%B8%89%E4%B8%B6%E5%B8%AE%E5%8A%A9");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        tips2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OpenUtil.OpenUrl("https://github.com/mxnter/TerBiliLive");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        paste.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cookieValue.setText(new ShearPlateUtil().getFromClipboard());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        clear.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cookieValue.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }
}
