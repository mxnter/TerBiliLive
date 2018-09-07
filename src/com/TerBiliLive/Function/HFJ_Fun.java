package com.TerBiliLive.Function;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveRoom;
import com.TerBiliLive.TerBiliLive.SendBarrage;
import com.TerBiliLive.TerBiliLive.getSubString;
import com.TerBiliLive.TerBiliLive.SendPost;
import com.TerBiliLive.Ui.TerBiliLive_Control_Ui;
import com.TerBiliLive.Ui.TerBiliLive_HFJ_Ui;
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

//import static com.TerBiliLive.Ui.TerBiliLive_HFJ_Ui;


public class HFJ_Fun {
    String Parameter= "" ;
    String pathUrl = "http://live.bilibili.com/msg/send";

    public HFJ_Fun() {
    }

    public HFJ_Fun(String msg){

        TerBiliLive_HFJ_Ui HFJ= new TerBiliLive_HFJ_Ui(Parameter);
        TerBiliLive_Control_Ui C=new TerBiliLive_Control_Ui(Parameter);
       // HFJ.HFJ_UiT_RoomId.setText(C.Control_UiT_RoomId.getText());
       // HFJ.HFJ_UiT_Cookie.setText(C.Control_UiT_Cookie.getText());

    Map<String, String> mapParam = new HashMap<String, String>();
    String roomid, cookie,url;

    url=pathUrl;

        System.out.println(msg);
    roomid = C.Control_UiT_RoomId.getText();
//
//                try {
//                    if(msg.equals("")){

//                    }else{
//                        msg = URLEncoder.encode(msg, "UTF-8");
//                    }
//                    if(msg.length()>20){
//                        String msgg=msg.substring(20);
//                        msg=msg.substring(0, 20);
//                        new HFJ_Fun(msgg);
//                    }



//                    mapParam.put("msg", msg);
//    } catch (UnsupportedEncodingException e1) {
//        // TODO Auto-generated catch block
//        e1.printStackTrace();
//    }
    //	System.out.print("cookie:");
         msg =HFJ.HFJ_UiT_Text.getText();
            cookie = C.Control_UiT_Cookie.getText();
//                mapParam.put("roomid", roomid);
//    int x=(int)(Math.random()*1000000);
//                mapParam.put("rnd", Integer.toString(x));

/*    //创建目录
                FileUtil.createDir("Ter/data/");
                FileUtil.createDir("Ter/log/");*/
/*    //写入数据
                FileUtil.writeFile("RoomId",HFJ.HFJ_UiT_RoomId.getText());
                FileUtil.writeFile("Text",HFJ.HFJ_UiT_Text.getText());
                FileUtil.writeFile("Cookie",HFJ.HFJ_UiT_Cookie.getText());*/


    /*  cookie----->>DedeUserID=  后的值很重要*/

//    SendPost sp=new SendPost();
    ConfInfo.sendBarrage=new SendBarrage();
    LiveRoom liveRoom =new LiveRoom(C.Control_UiT_RoomId.getText().toString());
    String RTData =ConfInfo.sendBarrage.SendBarrage(liveRoom.room_id,cookie,msg);
    ConfInfo.sendBarrage=null;
//            = sp.SendPost(roomid,cookie,msg);
//    getSubString gs=new getSubString();


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
                HFJ.HFJ_UiT_Time.setText(getFormatHour());
                HFJ.HFJ_UiT_State.setText("发送成功：OK"+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");

                LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送成功]-->[" +roomid+"] ："+  HFJ.HFJ_UiT_Text.getText() +"\t< -OK- "+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",HFJ.ProjectName);
                break;
            case "-101":
                HFJ.HFJ_UiT_Time.setText(getFormatHour());
                HFJ.HFJ_UiT_State.setText("发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+  HFJ.HFJ_UiT_Text.getText() +"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+ ">"+"\t 返回值：" + CodingUtil.ascii2native(RTData)  +"\n",HFJ.ProjectName);
                break;
            case "-500":
                HFJ.HFJ_UiT_Time.setText(getFormatHour());
                HFJ.HFJ_UiT_State.setText("发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+  HFJ.HFJ_UiT_Text.getText()+"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" +CodingUtil.ascii2native(RTData)  + "\n",HFJ.ProjectName);
                break;
            case "-400":
                HFJ.HFJ_UiT_Time.setText(getFormatHour());
                HFJ.HFJ_UiT_State.setText("发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+ HFJ.HFJ_UiT_Text.getText()+"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",HFJ.ProjectName);
                break;
            default:
                HFJ.HFJ_UiT_Time.setText(getFormatHour());
                HFJ.HFJ_UiT_State.setText("未知错误，"+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                LogUtil.putLog(getFormatDay(), getFormatHour(), "[未知错误]-->[" +roomid+"] ："+ HFJ.HFJ_UiT_Text.getText()+"\t<"+  CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",HFJ.ProjectName);
        }
        HFJ.HFJ_UiT_Text.setText("");

    } catch (JSONException e) {
        e.printStackTrace();
    }
}
}