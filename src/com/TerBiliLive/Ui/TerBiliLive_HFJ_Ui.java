package com.TerBiliLive.Ui;


import com.TerBiliLive.Monitor.HFJ_Monitor;

import javax.swing.*;
import java.awt.*;

//import org.json.JSONObject;


public class TerBiliLive_HFJ_Ui{
        //extends JPanel implements ActionListener, KeyListener {

    public static String Version = "Beta05";
    public static String Appname = "TerBiliLive HFJ";
    public static String ProjectName = "TerBiliLive HFJ";
    String pathUrl = "http://live.bilibili.com/msg/send";


    // 标签
   // JLabel HFJ_UiL_KB = new JLabel("　");
    JLabel HFJ_UiL_RoomId = new JLabel("房间号： ");
    JLabel HFJ_UiL_Second = new JLabel("延时： ");
    JLabel HFJ_UiL_Text = new JLabel("弹幕： ");
    JLabel HFJ_UiL_Cookie = new JLabel("Cookie： ");
    JLabel HFJ_UiL_State = new JLabel("状态：");
    JLabel HFJ_UiL_Time = new JLabel("时间：");

    public static JTextField HFJ_UiT_RoomId = new JTextField(20);
    public static JTextField HFJ_UiT_Second = new JTextField(20);
    public static JPasswordField HFJ_UiT_Cookie = new JPasswordField(20);
    public static JTextField HFJ_UiT_State = new JTextField(13);
    public static JTextField HFJ_UiT_Time = new JTextField(7);
    public static JTextField HFJ_UiT_Text = new JTextField(30);
   // static JTextArea HFJ_UiT_Text = new JTextArea(5,25);



    public static JButton HFJ_UiB_Send = new JButton("发送");
   // static JButton HFJ_UiB_Suspend = new JButton("暂停");
    // JPanel HFJ_Ui_Jpanel = new JPanel(new GridLayout(6, 1, 10, 10));
   JPanel HFJ_Ui_Jpanel = new JPanel(new FlowLayout());



    public TerBiliLive_HFJ_Ui(String Parameter){



    }


    public TerBiliLive_HFJ_Ui(){




























//        this.setTitle(Appname + " " + Version);
//        this.setSize(300, 250);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLocationRelativeTo(null);
//        Image icon=null;
//        icon=Toolkit.getDefaultToolkit().getImage("Ter/logo.jpg");
//        if(icon!=null )this.setIconImage(icon);  // 图片的具体位置
//        //设置窗口的logo
//        // HFJ_Ui_Jpanel。setOpaque(false);



       // HFJ_UiT_Text.setLineWrap(true);
     //   HFJ_UiT_Text.addKeyListener(this);  设置回车发送

        //JScrollPane scroll = new JScrollPane( HFJ_UiT_Text);
        //scroll.setSize(336, 103);

//        Container con = getContentPane();
//        this.setResizable(false);
//        ((JPanel) con).setOpaque(false);


        //JPanel HFJ_Ui_Up_Jpanel = new JPanel(new GridLayout(5, 1, 10, 10));
        //JPanel HFJ_Ui_Dn_Jpanel = new JPanel(new GridLayout(2, 1, 10, 10));

        JPanel HFJ_Ui_Time = new JPanel(new FlowLayout());
        JPanel HFJ_Ui_State = new JPanel(new FlowLayout());
//      JPanel HFJ_Ui_RoomId = new JPanel(new FlowLayout());
//      JPanel HFJ_Ui_Second = new JPanel(new FlowLayout());
        JPanel HFJ_Ui_Text1 = new JPanel(new FlowLayout());
        JPanel HFJ_Ui_Text2 = new JPanel(new FlowLayout());
//        JPanel HFJ_Ui_Cookie = new JPanel(new FlowLayout());
        JPanel HFJ_Ui_SS = new JPanel(new FlowLayout());
        HFJ_UiT_Time.setEditable(false);
        HFJ_UiT_State.setEditable(false);


        HFJ_Ui_Time.add(HFJ_UiL_Time);
        HFJ_Ui_Time.add(HFJ_UiT_Time);
        HFJ_Ui_State.add(HFJ_UiL_State);
        HFJ_Ui_State.add(HFJ_UiT_State);
//        HFJ_Ui_RoomId.add(HFJ_UiL_RoomId);
//        HFJ_Ui_RoomId.add(HFJ_UiT_RoomId);
       // HFJ_Ui_Second.add(HFJ_UiL_Second);
       // HFJ_Ui_Second.add(HFJ_UiT_Second);
        HFJ_Ui_Text1.add(HFJ_UiL_Text);
        HFJ_Ui_Text2.add(HFJ_UiT_Text);
//        HFJ_Ui_Cookie.add(HFJ_UiL_Cookie);
//        HFJ_Ui_Cookie.add(HFJ_UiT_Cookie);

      //  HFJ_Ui_SS.add(HFJ_UiL_Text);
        HFJ_Ui_SS.add(HFJ_UiB_Send);

        HFJ_Ui_Jpanel.add(HFJ_Ui_Time);
        HFJ_Ui_Jpanel.add(HFJ_Ui_State);
//        HFJ_Ui_Jpanel.add(HFJ_Ui_RoomId);
//        HFJ_Ui_Jpanel.add(HFJ_Ui_Second);
//        HFJ_Ui_Jpanel.add(HFJ_Ui_Cookie);
       // HFJ_Ui_Jpanel.add(HFJ_Ui_Text1);
        HFJ_Ui_Jpanel.add(HFJ_Ui_Text2);
        HFJ_Ui_Jpanel.add(HFJ_Ui_SS);


        new HFJ_Monitor();


      //  HFJ_Ui_Jpanel.add(HFJ_Ui_Up_Jpanel);
       // HFJ_Ui_Jpanel.add(HFJ_Ui_Dn_Jpanel);

        //getContentPane().add(HFJ_Ui_Jpanel);
        // con.add(HFJ_Ui_Jpanel, BorderLayout.CENTER);

      //  HFJ_UiB_Start.addActionListener(this);
       // HFJ_UiB_Suspend.addActionListener(this);

//        HFJ_UiB_Send.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                // TODO Auto-generated method stub
//
//                new HFJ_Fun();
//
//            }
//        });
//        HFJ_UiB_Suspend.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                // TODO Auto-generated method stub
//                // YJ.gdpd=false;
//               // putDM_Suspend();
//            }
//        });


      //  this.setVisible(true);






    }

/*    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e){
    //当按下Enter键时，插入一个换行符(Enter键的keycode为10)
        if(e.getKeyCode()==10){
            getRootPane().setDefaultButton(HFJ_UiB_Send);
           // HFJ_UiT_Text.append("\b");
           // HFJ_UiT_Text.getInputMap().put(enter, "none");

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }*/



}
/* Map<String, String> mapParam = new HashMap<String, String>();
                String roomid, cookie, msg,url;

                url=pathUrl;

                roomid = HFJ_UiT_RoomId.getText();

                try {
                    msg = URLEncoder.encode(HFJ_UiT_Text.getText(), "UTF-8");
                    mapParam.put("msg", msg);
                } catch (UnsupportedEncodingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                //	System.out.print("cookie:");
                cookie = HFJ_UiT_Cookie.getText();
                mapParam.put("roomid", roomid);
                int x=(int)(Math.random()*1000000);
                mapParam.put("rnd", Integer.toString(x));

                //创建目录
                FileUtil.createDir("Ter/data/");
                FileUtil.createDir("Ter/log/");
                //写入数据
                FileUtil.writeFile("RoomId",HFJ_UiT_RoomId.getText());
                FileUtil.writeFile("Text",HFJ_UiT_Text.getText());
                FileUtil.writeFile("Cookie",HFJ_UiT_Cookie.getText());


                *//*  cookie----->>DedeUserID=  后的值很重要*//*
                sendPost sp=new sendPost();
                String RTData = sp.sendPost(url,mapParam,cookie);
                getSubString gs=new getSubString();


                *//*使用JSON 获取返回值中的的信息
 * gs.getSubString(RTData, "\"code\":", ",\"msg\""),  <——老方法获取值
 * CodingUtil.ascii2native(gs.getSubString(RTData, "\"code\":", ",\"msg\""))  <——老方法获取值
 * JSONObject jsonObject = new JSONObject(RTData);<——新方法获取值
 *  jsonObject.getString("code")<——新方法获取值
 *
 *
 * *//*

                try {
                    JSONObject jsonObject = new JSONObject(RTData);



                switch (jsonObject.getString("code")) {

                    case "0":
                        HFJ_UiT_Time.setText(getFormatHour());
                        HFJ_UiT_State.setText("发送成功：OK"+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");

                        LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送成功]-->[" +roomid+"] ："+  HFJ_UiT_Text.getText() +"\t< -OK- "+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",ProjectName);
                        break;
                    case "-101":
                        HFJ_UiT_Time.setText(getFormatHour());
                        HFJ_UiT_State.setText("发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                        LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+  HFJ_UiT_Text.getText() +"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+ ">"+"\t 返回值：" + CodingUtil.ascii2native(RTData)  +"\n",ProjectName);
                        break;
                    case "-500":
                        HFJ_UiT_Time.setText(getFormatHour());
                        HFJ_UiT_State.setText("发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                        LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+  HFJ_UiT_Text.getText()+"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" +CodingUtil.ascii2native(RTData)  + "\n",ProjectName);
                        break;
                    case "-400":
                        HFJ_UiT_Time.setText(getFormatHour());
                        HFJ_UiT_State.setText("发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                        LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+ HFJ_UiT_Text.getText()+"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",ProjectName);
                        break;
                    default:
                        HFJ_UiT_Time.setText(getFormatHour());
                        HFJ_UiT_State.setText("未知错误，"+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                        LogUtil.putLog(getFormatDay(), getFormatHour(), "[未知错误]-->[" +roomid+"] ："+ HFJ_UiT_Text.getText()+"\t<"+  CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",ProjectName);
                }
                HFJ_UiT_Text.setText("");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                if (gs.getSubString(RTData, "\"code\":", ",\"msg\"").equals("0")) {
//
//
//
//                } else  if (gs.getSubString(RTData, "\"code\":", ",\"msg\"").equals("-101")) {
//                    HFJ_UiT_Time.setText(getFormatHour());
//                    HFJ_UiT_State.setText("发送失败，Cookie 可能时失效");
//                    LogUtil.putLog(getFormatDay(), getFormatHour(), "发送失败 ：Cookie 可能时失效 "+ HFJ_UiT_Text.getText() + "\t 返回值：" + RTData +"\n");
//
//
//
//                } else if (gs.getSubString(RTData, "\"code\":", ",\"msg\"").equals("-500")) {
//                    HFJ_UiT_Time.setText(getFormatHour());
//                    HFJ_UiT_State.setText("发送失败，Cookie 为空或错误");
//                    LogUtil.putLog(getFormatDay(), getFormatHour(), "发送失败 ：Cookie 为空或错误 " + RTData+ HFJ_UiT_Text.getText()+ "\t 返回值："  + "\n");
//
//                } else{
//
//                    HFJ_UiT_Time.setText(getFormatHour());
//                    HFJ_UiT_State.setText("未知错误，请查看日志");
//                    LogUtil.putLog(getFormatDay(), getFormatHour(), "未知错误 ：" + RTData+ HFJ_UiT_Text.getText()+ "\t 返回值："  + "\n");
//
//                }


                //HFJ_UiT_State.setText(HFJ_UiT_Text.getText());




                //putDM_Start();
*/