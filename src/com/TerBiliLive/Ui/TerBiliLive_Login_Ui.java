package com.TerBiliLive.Ui;

import com.TerBiliLive.Img.ImageBroker;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Utiliy.AgreementUtil;
import com.TerBiliLive.Utiliy.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @名称 登陆界面 UI
 * @作用 获取cookie
 * @作者 Mxnter
 *
 * */

public class TerBiliLive_Login_Ui extends JFrame  {

    public static String Version = "Beta02-0514";
    public static String Appname = "TerBiliLive Login";
    public static String ProjectName = "TerBiliLive Login";

    JLabel Login_UiL_Hint = new JLabel("Cookie");
    JLabel Login_UiL_Msg = new JLabel("Cookie");
    JTextField Login_UiT_Cookie = new JTextField(22);
    public static JButton Login_Ui_Login = new JButton("登陆");
    JPanel Login_Ui_Jpanel = new JPanel(new FlowLayout());




    public TerBiliLive_Login_Ui(){
        Container con = getContentPane();
        this.setTitle(Appname + " " + Version);
        this.setSize(400, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //设置图标
        Image icon = null;
        try {
            icon =  ImageBroker.loadImage("logoa.png").getImage();
        } catch (IOException e) {
            icon = Toolkit.getDefaultToolkit().getImage("Ter/logoa.png");
            e.printStackTrace();
        }

        if (icon != null) this.setIconImage(icon);  // 图片的具体位置

//        Login_UiT_Cookie.setText(FileUtil.readFile("Cookie"));

        Login_Ui_Jpanel.add(Login_UiL_Hint);
        Login_Ui_Jpanel.add(Login_UiT_Cookie);
        Login_Ui_Jpanel.add(Login_Ui_Login);


        con.add(Login_Ui_Jpanel, BorderLayout.CENTER);
        this.setVisible(true);
String SQ = "尊敬的用户：\n" +
        "　　您好，感谢您使用 TerBiliLive 弹幕姬。\n" +
        "　　在您使用之前我们将告知您，为了统计弹幕姬使用人和次数，\n" +
        "我们将统计部分信息，例如 IP、Mac、主机名等。这将发送到我们\n" +
        "的服务上，以帮助开发者了解是否有用户使用这个软件。\n" +
        "　　开发者不会发送您的 Cookie，您的Cookie存储在本地，我们在\n" +
        "某些版本上使用了加密手段加密了您的Cookie（由于部分电脑无法解密，\n" +
        "我们暂时将这个功能关闭）。\n" +
        "\n" +
        "如果您觉得我们可以信赖，您可以选择 “确定”。如果您不想统计您的信\n" +
        "息，请选择 “取消”。\n" +
        "\n" +
        "再次感谢您的使用。\n";
        if(AgreementUtil.readFile().equals("NO")){

          //  JOptionPane.showInputDialog(null,SQ,"用户授权",JOptionPane.PLAIN_MESSAGE).equals("我同意")
            if(JOptionPane.showConfirmDialog(null, SQ, "用户授权",JOptionPane.OK_CANCEL_OPTION)==0){
                AgreementUtil.putAgreement(SQ+"\n"+"用户：同意");
                ConfInfo.dingtalkUtil.Agreement("同意");
            }else{
                ConfInfo.dingtalkUtil.Agreement("不同意");
//                System.exit(0);
            }
        }



        Login_Ui_Login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                ConfInfo.cookie=Login_UiT_Cookie.getText();
                FileUtil.writeFile("Cookie",ConfInfo.cookie);
                ConfInfo.confData.setCookie(ConfInfo.cookie);
                ConfInfo.xmlUtil.writeData();
                ConfInfo.jsonUtil.writeData();

                TerBiliLive_Greet_Ui login =new TerBiliLive_Greet_Ui();
                dispose();
            }
        });

    }
}
