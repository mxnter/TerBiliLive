package com.TerBiliLive.Ui;

import com.TerBiliLive.Entity.HttpClientEntity;
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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.TerBiliLive.TerBiliLive.HttpClient.sendGetHeader;
import static com.TerBiliLive.TerBiliLive.HttpClient.sendPostHeader;
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
    private JLabel qrcode;
    private JPanel qrcodePanel;
    private JLabel qrcodeMsg;
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
        qrcode.setOpaque(false); //背景透明
        msg.setOpaque(false); //背景透明
        loginPanel.setOpaque(false); //背景透明
        qrcodePanel.setOpaque(false); //背景透明
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

        new Thread(new Runnable() {
            public void run() {
                // 开始使用二维码登录
                try {
                    cookieValue.setText(qrCodeLogin(qrcode,qrcodeMsg));
                    login.doClick();
                } catch (JSONException | IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //弹出授权记录信息提示框
        String SQ = "　　您好，感谢您使用 TerBiliLive 弹幕姬。\n" +
                "　　在您使用之前我们将告知您，为了统计弹幕姬使用人和次数，\n" +
                "软件将会记录您的特征码(IP、Mac、主机名等),用于开发者判断使用次数和人数,\n" +
                "以帮助开发者了解软件是否有价值.您的Cookie是加密存储在本地。\n" +
                "点击 “确定” 后表示您同意,我们将不再提示此信息.\n" +
                "点击 “取消” 后本次使用将不会记录任何信息,下次启动会提示此信息.\n" +
                "官方网站:http://terbililive.mter.top/\n" +
                "GITHUB:https://github.com/mxnter/TerBiliLive\n" +
                "码云:https://gitee.com/mxnter/TerBiliLive\n" +
                "再次感谢您的使用。 ";
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
                setVisible(false);
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
        qrcode.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        // 开始使用二维码登录
                        try {
                            cookieValue.setText(qrCodeLogin(qrcode,qrcodeMsg));
                            login.doClick();
                        } catch (JSONException | IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
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


    // 使用网络API 生成二维码 弃用
    public static String qrCodeLoginWebApi(JLabel qrcode,JLabel qrcodeMsg) throws JSONException, InterruptedException, IOException {
        String getQrCodeUrl = "https://passport.bilibili.com/qrcode/getLoginUrl";
        String getQrCodeLoginUrl = "https://passport.bilibili.com/qrcode/getLoginInfo";
        String getQrCodeImgUrl = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=";
        StringBuilder cookie = new StringBuilder();
        String oauthKey = "";

        qrcodeMsg.setText("正在获取二维码");
        HttpClientEntity getQrCode = sendGetHeader(getQrCodeUrl,null,null);
        for (String setCookie : getQrCode.getHeader().get("Set-Cookie")) {
            cookie.append(setCookie.split(";")[0]).append("; ");
        }
        JSONObject result = new JSONObject(getQrCode.getResult());
        JSONObject resultData = result.getJSONObject("data");
        oauthKey = resultData.getString("oauthKey");
//        System.out.println(cookie);
//        System.out.println(resultData.getString("url"));
        try {
            ImageIcon image = new ImageIcon(new URL(getQrCodeImgUrl+resultData.getString("url")),"头像");//设置图片的来源路径（图片的URL）
            image.setImage(image.getImage().getScaledInstance(200, 200, 100));//设置图片大小
            qrcode.setIcon(image);
        } catch (MalformedURLException e) {
            qrcode.setIcon(ImageBroker.loadImageIcon("defaultFace.jpg"));
            e.printStackTrace();
        }
//        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+getQrCodeImgUrl+resultData.getString("url"));
        HttpClientEntity getQrCodeLogin;
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("oauthKey",oauthKey);
        paramMap.put("gourl","https://www.bilibili.com/");
        JSONObject getLoginInfo = new JSONObject(getQrCode.getResult());
        do{
            Thread.sleep(1000);
            getQrCodeLogin = sendPostHeader(getQrCodeLoginUrl,paramMap, cookie.toString());
            getLoginInfo = new JSONObject(getQrCodeLogin.getResult());
            if(!getLoginInfo.getBoolean("status")){
                if(getLoginInfo.getInt("data")==-2){
                    qrcodeMsg.setText("二维码已超时,点击重新获取");
//                    System.out.println("二维码已超时,请重新获取");
                    return null;
                }else if(getLoginInfo.getInt("data")==-4){
                    qrcodeMsg.setText("扫码二维,登录账号");
//                    System.out.println("未扫描二维码");
                }else if(getLoginInfo.getInt("data")==-5){
                    qrcodeMsg.setText("已扫码二维码");
//                    System.out.println("已扫码二维码");
                }
            }
        }while (!getLoginInfo.getBoolean("status"));
        for (String setCookie : getQrCodeLogin.getHeader().get("Set-Cookie")) {
            cookie.append(setCookie.split(";")[0]).append("; ");
        }
//        System.out.println(cookie);
//        HttpClientEntity get3 = sendGetSetCookie("https://api.live.bilibili.com/User/getUserInfo",null, cookie.toString());
//        System.out.println(get3.getResult());

        return cookie.toString();
    }

    public static String qrCodeLogin(JLabel qrcode,JLabel qrcodeMsg) throws JSONException, InterruptedException, IOException {
        String getQrCodeUrl = "https://passport.bilibili.com/qrcode/getLoginUrl";
        String getQrCodeLoginUrl = "https://passport.bilibili.com/qrcode/getLoginInfo";
        StringBuilder cookie = new StringBuilder();
        String oauthKey = "";

        qrcodeMsg.setText("正在获取二维码");
        HttpClientEntity getQrCode = sendGetHeader(getQrCodeUrl,null,null);
        for (String setCookie : getQrCode.getHeader().get("Set-Cookie")) {
            cookie.append(setCookie.split(";")[0]).append("; ");
        }
        JSONObject result = new JSONObject(getQrCode.getResult());
        JSONObject resultData = result.getJSONObject("data");
        oauthKey = resultData.getString("oauthKey");
        // 使用本地生成二维码
        try {
            ImageIcon image = new ImageIcon(QRCodeUtil.buildLoginQr(resultData.getString("url")),"头像");//设置图片的来源路径（图片的URL）
            image.setImage(image.getImage().getScaledInstance(200, 200, 100));//设置图片大小
            qrcode.setIcon(image);
        } catch (Exception e) {
            qrcode.setIcon(ImageBroker.loadImageIcon("defaultFace.jpg"));
            e.printStackTrace();
        }

        HttpClientEntity getQrCodeLogin;
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("oauthKey",oauthKey);
        paramMap.put("gourl","https://www.bilibili.com/");
        JSONObject getLoginInfo = new JSONObject(getQrCode.getResult());
        do{
            Thread.sleep(1000);
            getQrCodeLogin = sendPostHeader(getQrCodeLoginUrl,paramMap, cookie.toString());
            getLoginInfo = new JSONObject(getQrCodeLogin.getResult());
            if(!getLoginInfo.getBoolean("status")){
                if(getLoginInfo.getInt("data")==-2){
                    qrcodeMsg.setText("二维码已超时,点击重新获取");
                    return null;
                }else if(getLoginInfo.getInt("data")==-4){
                    qrcodeMsg.setText("扫码二维,登录账号");
                }else if(getLoginInfo.getInt("data")==-5){
                    qrcodeMsg.setText("已扫码二维码");
                }
            }
        }while (!getLoginInfo.getBoolean("status"));
        for (String setCookie : getQrCodeLogin.getHeader().get("Set-Cookie")) {
            cookie.append(setCookie.split(";")[0]).append("; ");
        }

        return cookie.toString();
    }
}
