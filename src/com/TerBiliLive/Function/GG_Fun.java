package com.TerBiliLive.Function;

import com.TerBiliLive.TerBiliLive.getSubString;
import com.TerBiliLive.TerBiliLive.SendPost;
import com.TerBiliLive.Ui.TerBiliLive_Control_Ui;
import com.TerBiliLive.Ui.TerBiliLive_GG_Ui;
import com.TerBiliLive.Utiliy.CodingUtil;
import com.TerBiliLive.Utiliy.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.TerBiliLive.Utiliy.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatHour;

public class GG_Fun {

    String Parameter= "" ;
    String pathUrl = "http://live.bilibili.com/msg/send";
    public static String RTData ;
    public GG_Fun(){

        TerBiliLive_GG_Ui GG= new TerBiliLive_GG_Ui(Parameter);
        TerBiliLive_Control_Ui C=new TerBiliLive_Control_Ui(Parameter);
        // GG.GG_UiT_RoomId.setText(C.Control_UiT_RoomId.getText());
        // GG.GG_UiT_Cookie.setText(C.Control_UiT_Cookie.getText());

        Map<String, String> mapParam = new HashMap<String, String>();
        String roomid, cookie, msg,url;

        url=pathUrl;

        roomid = C.Control_UiT_RoomId.getText();

        try {
            msg = URLEncoder.encode(GG.GG_UiT_Text.getText(), "UTF-8");
            mapParam.put("msg", msg);
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //	System.out.print("cookie:");
        cookie = C.Control_UiT_Cookie.getText();
        mapParam.put("roomid", roomid);
        int x=(int)(Math.random()*1000000);
        mapParam.put("rnd", Integer.toString(x));

/*    //创建目录
                FileUtil.createDir("Ter/data/");
                FileUtil.createDir("Ter/log/");*/
/*    //写入数据
                FileUtil.writeFile("RoomId",GG.GG_UiT_RoomId.getText());
                FileUtil.writeFile("Text",GG.GG_UiT_Text.getText());
                FileUtil.writeFile("Cookie",GG.GG_UiT_Cookie.getText());*/


//        /*  cookie----->>DedeUserID=  后的值很重要*/
//        SendPost sp=new SendPost();
//        RTData = sp.sendPost(url,mapParam,cookie);
//        getSubString gs=new getSubString();


        /*使用JSON 获取返回值中的的信息
         * gs.getSubString(RTData, "\"code\":", ",\"msg\""),  <——老方法获取值
         * CodingUtil.ascii2native(gs.getSubString(RTData, "\"code\":", ",\"msg\""))  <——老方法获取值
         * JSONObject jsonObject = new JSONObject(RTData);<——新方法获取值
         *  jsonObject.getString("code")<——新方法获取值
         *
         *
         * */

        try {
            JSONObject jsonObject = new JSONObject(RTData);



            switch (jsonObject.getString("code")) {

                case "0":
                    //GG.GG_UiT_Time.setText(getFormatHour());
                    GG.GG_UiT_State.setText(getFormatHour()+"发送成功：OK"+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");

                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送成功]-->[" +roomid+"] ："+  GG.GG_UiT_Text.getText() +"\t< -OK- "+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",GG.ProjectName);
                    break;
                case "-101":
                   // GG.GG_UiT_Time.setText(getFormatHour());
                    GG.GG_UiT_State.setText(getFormatHour()+"发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+  GG.GG_UiT_Text.getText() +"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+ ">"+"\t 返回值：" + CodingUtil.ascii2native(RTData)  +"\n",GG.ProjectName);
                    break;
                case "-500":
                   // GG.GG_UiT_Time.setText(getFormatHour());
                    GG.GG_UiT_State.setText(getFormatHour()+"发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+  GG.GG_UiT_Text.getText()+"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" +CodingUtil.ascii2native(RTData)  + "\n",GG.ProjectName);
                    break;
                case "-400":
                   // GG.GG_UiT_Time.setText(+getFormatHour());
                    GG.GG_UiT_State.setText(getFormatHour()+"发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+ GG.GG_UiT_Text.getText()+"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",GG.ProjectName);
                    break;
                default:
                   // GG.GG_UiT_Time.setText(getFormatHour());
                    GG.GG_UiT_State.setText(getFormatHour()+"未知错误，"+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[未知错误]-->[" +roomid+"] ："+ GG.GG_UiT_Text.getText()+"\t<"+  CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",GG.ProjectName);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
