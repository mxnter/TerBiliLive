package com.github.TerBiliLive.Ui;

import com.github.TerBiliLive.Img.ImageBroker;
import com.github.TerBiliLive.Info.ConfInfo;
import com.github.TerBiliLive.TerBiliLive.SendGet;
import com.github.TerBiliLive.Utiliy.AgreementUtil;
import com.github.TerBiliLive.Utiliy.FileUtil;
import com.github.TerBiliLive.Utiliy.OpenUtil;
import com.github.TerBiliLive.Utiliy.ShearPlateUtil;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * @名称 登陆界面 UI
 * @作用 获取cookie
 * @作者 Mxnter
 * */

public class TerBiliLive_Login_Ui extends JFrame  {

    public static String Version = "Beta03-0916";
    public static String Appname = "TerBiliLive Login";
    public static String ProjectName = "TerBiliLive Login";
    Font font = new Font(null,Font.PLAIN,24);
    public static JLabel Login_UiL_Title = new JLabel("TerBiliLive");
    public static JLabel Login_UiL_Hint = new JLabel("Cookie");
    public static JLabel Login_UiL_Msg = new JLabel("哔哩哔哩直播弹幕姬，使用Java");
    public static JLabel Login_UiL_Tips = new JLabel("Tips :" );
    public static JLabel Login_UiL_Notice = new JLabel("新增游客登录，可能出现未知情况。" );
    public static JLabel Login_UiL_Tips1 = new JLabel("获取Cookie方法");
    public static JLabel Login_UiL_Tips2 = new JLabel(" |　无法运行？获取最新版！");
    public static JTextField Login_UiT_Cookie = new JTextField(22);
    public static JButton Login_Ui_Login = new JButton("登陆");
    public static JButton Login_Ui_NoLogin = new JButton("游客");
    public static JLabel Login_Ui_ShearPlate = new JLabel("粘贴");
    public static JLabel Login_Ui_Empty = new JLabel("清空");
    JPanel Login_Ui_Jpanel = new JPanel(new GridLayout(6, 1, 2, 2));  // 2行1列，水平间距为2，垂直间距为2
    JPanel Login_Ui_Title = new JPanel(new FlowLayout());
    JPanel Login_Ui_Msg = new JPanel(new FlowLayout());
    JPanel Login_Ui_Cookie = new JPanel(new FlowLayout());
    JPanel Login_Ui_Button = new JPanel(new FlowLayout());
    JPanel Login_Ui_Tips = new JPanel(new FlowLayout(2));
    JPanel Login_Ui_Notice = new JPanel(new FlowLayout());
    public TerBiliLive_Login_Ui(){
        Container con = getContentPane();
        this.setTitle(Appname + " " + Version);
        this.setSize(400, 250);
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
        Login_UiL_Title.setFont(font);
        Login_UiL_Tips.setForeground(Color.GREEN);
        Login_UiL_Tips1.setForeground(Color.GREEN);
        Login_UiL_Tips2.setForeground(Color.GREEN);
//        Login_Ui_NoLogin.setEnabled(false);


        Login_Ui_Title.add(Login_UiL_Title);

        Login_Ui_Msg.add(Login_UiL_Msg);

        Login_Ui_Cookie.add(Login_UiL_Hint);
        Login_Ui_Cookie.add(Login_UiT_Cookie);
        Login_Ui_Cookie.add(Login_Ui_ShearPlate);
        Login_Ui_Cookie.add(Login_Ui_Empty);

        Login_Ui_Button.add(Login_Ui_NoLogin);
        Login_Ui_Button.add(Login_Ui_Login);

        Login_Ui_Notice.add(Login_UiL_Notice);

        Login_Ui_Tips.add(Login_UiL_Tips);
        Login_Ui_Tips.add(Login_UiL_Tips1);
        Login_Ui_Tips.add(Login_UiL_Tips2);

        Login_Ui_Jpanel.add(Login_Ui_Title);
        Login_Ui_Jpanel.add(Login_Ui_Msg);
        Login_Ui_Jpanel.add(Login_Ui_Cookie);
        Login_Ui_Jpanel.add(Login_Ui_Button);
        Login_Ui_Jpanel.add(Login_Ui_Notice);
        Login_Ui_Jpanel.add(Login_Ui_Tips);


        con.add(Login_Ui_Jpanel, BorderLayout.CENTER);
        this.setVisible(true);

        //弹出授权记录信息提示框
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
                    SVersionJson = new JSONObject(SendGet.sendGet("https://mxnter.github.io/TerBiliLiveV/Networking/Version.json", "", ""));
                    SVersion = SVersionJson.getString("version");
                    SVersionNum = Integer.parseInt(SVersionJson.getString("versionNum"));
                    level = Integer.parseInt(SVersionJson.getString("level"));
                    if (ConfInfo.VersionNum < SVersionNum) {
                        if (level >= -1) {
                            Login_UiL_Notice.setText("收到一份重要更新提醒,请尽快更新！");
                            Login_UiL_Notice.setForeground(Color.RED);
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

        Login_Ui_Login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                ConfInfo.cookie=Login_UiT_Cookie.getText();
                FileUtil.writeFile("Cookie",ConfInfo.cookie);
                ConfInfo.confData.setCookie(ConfInfo.cookie);
                ConfInfo.xmlUtil.writeData();
                ConfInfo.jsonUtil.writeData();
                TerBiliLive_Greet_Ui greetUi =new TerBiliLive_Greet_Ui();
                dispose();
            }
        });

        Login_Ui_NoLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                TerBiliLive_Ui ui =new TerBiliLive_Ui();
                ConfInfo.Uname = "游客";
                dispose();
            }
        });

        Login_UiL_Tips1.addMouseListener(new MouseListener() {
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
        Login_UiL_Tips2.addMouseListener(new MouseListener() {
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

        Login_Ui_ShearPlate.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Login_UiT_Cookie.setText(ShearPlateUtil.getFromClipboard());
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
        Login_Ui_Empty.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Login_UiT_Cookie.setText("");
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

        Login_UiL_Tips.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
