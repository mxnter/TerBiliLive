package com.TerBiliLive.Ui;

import com.TerBiliLive.Img.ImageBroker;
import com.TerBiliLive.Info.ConfInfo;
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

    public static String Version = "Beta01";
    public static String Appname = "TerBiliLive Login";
    public static String ProjectName = "TerBiliLive Login";

    JLabel Login_UiL_Hint = new JLabel("Cookie");
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
