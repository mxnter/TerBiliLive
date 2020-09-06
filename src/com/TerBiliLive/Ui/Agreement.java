package com.TerBiliLive.Ui;

import com.TerBiliLive.Img.ImageBroker;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.TerBiliLive.HttpClient;
import com.TerBiliLive.TerBiliLive.TerWindowListener;
import com.TerBiliLive.Utils.AgreementUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Agreement extends JFrame{
    private JScrollPane logJScrollPane;
    private JTextArea log;
    private JPanel panel;
    private JPanel buttonPanel;
    private JButton yes;
    private JButton no;

    public Agreement(){
        this.setContentPane(panel);
        this.setTitle(ConfInfo.AppName.get(ConfInfo.AppSystemId));
        this.setName("Agreement");
        this.addWindowListener(new TerWindowListener(this));
        this.setIconImage(ImageBroker.loadImages("logoa.png"));  //设置Logo
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //隐藏边框
//        this.setUndecorated(true);
//        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        log.setOpaque(false); //背景透明
        log.setLineWrap(true); //自动换行
        log.setEditable(false); //禁止输入
        logJScrollPane.setOpaque(false); //背景透明
        logJScrollPane.getViewport().setOpaque(false); //背景透明
        logJScrollPane.setBorder(null); //取消边框
        buttonPanel.setOpaque(false); //背景透明
        yes.setBorderPainted(false);//不绘制边框
        no.setBorderPainted(false);//不绘制边框
//        panel.setOpaque(false); //背景透明
//        start.setBorderPainted(false);//不绘制边框
//        stop.setBorderPainted(false);//不绘制边框
        //弹出授权记录信息提示框
        String SQ = "隐私协议\n" +
                "　　您好，感谢您使用 TerBiliLive 弹幕姬。\n" +
                "　　在您使用之前我们将告知您，为了统计弹幕姬使用人和次数，" +
                "软件将会记录您的特征码(IP、Mac、主机名等),用于开发者判断使用次数和人数," +
                "以帮助开发者了解软件是否有价值.您的Cookie是加密存储在本地。\n" +
                "　　点击 “确定” 后表示您同意,我们将不再提示此信息.\n" +
                "　　点击 “取消” 后本次将不会记录任何信息,下次启动会提示此信息.\n" +
                "　　官方网站:http://terbililive.mter.top/\n" +
                "　　GITHUB:https://github.com/mxnter/TerBiliLive\n" +
                "　　码云:https://gitee.com/mxnter/TerBiliLive\n" +
                "再次感谢您的使用 ";


        try {
            JSONObject jsonObject = new JSONObject( HttpClient.sendGet(ConfInfo.AppServerAgreement,"",""));
            SQ = jsonObject.getString("content");
            SQ += "\n\n发布时间：" + jsonObject.getString("time");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        log.append(SQ);
        String finalSQ = SQ;
        yes.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgreementUtil().putAgreement(finalSQ +"\n"+"用户：同意");
                ConfInfo.dingtalk.Agreement("同意");
                goLogin();

            }
        });

        no.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfInfo.dingtalk.Agreement("不同意");
                goLogin();
                //System.exit(0); //不同意后关闭软件
            }
        });

        this.setVisible(true);
    }

    void goLogin(){
        new Login();
        this.setVisible(false);
    }

}
