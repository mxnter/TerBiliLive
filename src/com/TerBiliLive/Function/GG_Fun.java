package com.TerBiliLive.Function;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveRoom;
import com.TerBiliLive.TerBiliLive.SendBarrage;
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


        // GG.GG_UiT_RoomId.setText(C.Control_UiT_RoomId.getText());
        // GG.GG_UiT_Cookie.setText(C.Control_UiT_Cookie.getText());

        Map<String, String> mapParam = new HashMap<String, String>();
        String roomid, cookie, msg,url;


        roomid = ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText();
        msg = ConfInfo.terBiliLive_gg_ui.GG_UiT_Text.getText();
        cookie = ConfInfo.cookie;


        ConfInfo.sendBarrage=new SendBarrage();
        if(ConfInfo.liveRoom==null)ConfInfo.liveRoom =new LiveRoom(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText().toString());
        String RTData =ConfInfo.sendBarrage.SendBarrage(ConfInfo.liveRoom.room_id,cookie,msg);


        try {
            JSONObject jsonObject = new JSONObject(RTData);



            switch (jsonObject.getString("code")) {

                case "0":
                    //GG.GG_UiT_Time.setText(getFormatHour());
                    ConfInfo.terBiliLive_gg_ui.GG_UiT_State.setText(getFormatHour()+"发送成功：OK"+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");

                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送成功]-->[" +roomid+"] ："+  ConfInfo.terBiliLive_gg_ui.GG_UiT_Text.getText() +"\t< -OK- "+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",ConfInfo.terBiliLive_gg_ui.ProjectName);
                    break;
                case "-101":
                   // GG.GG_UiT_Time.setText(getFormatHour());
                    ConfInfo.terBiliLive_gg_ui.GG_UiT_State.setText(getFormatHour()+"发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+  ConfInfo.terBiliLive_gg_ui.GG_UiT_Text.getText() +"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+ ">"+"\t 返回值：" + CodingUtil.ascii2native(RTData)  +"\n",ConfInfo.terBiliLive_gg_ui.ProjectName);
                    break;
                case "-500":
                   // GG.GG_UiT_Time.setText(getFormatHour());
                    ConfInfo.terBiliLive_gg_ui.GG_UiT_State.setText(getFormatHour()+"发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+  ConfInfo.terBiliLive_gg_ui.GG_UiT_Text.getText()+"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" +CodingUtil.ascii2native(RTData)  + "\n",ConfInfo.terBiliLive_gg_ui.ProjectName);
                    break;
                case "-400":
                   // GG.GG_UiT_Time.setText(+getFormatHour());
                    ConfInfo.terBiliLive_gg_ui.GG_UiT_State.setText(getFormatHour()+"发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+ ConfInfo.terBiliLive_gg_ui.GG_UiT_Text.getText()+"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",ConfInfo.terBiliLive_gg_ui.ProjectName);
                    break;
                default:
                   // GG.GG_UiT_Time.setText(getFormatHour());
                    ConfInfo.terBiliLive_gg_ui.GG_UiT_State.setText(getFormatHour()+"未知错误，"+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[未知错误]-->[" +roomid+"] ："+ ConfInfo.terBiliLive_gg_ui.GG_UiT_Text.getText()+"\t<"+  CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",ConfInfo.terBiliLive_gg_ui.ProjectName);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
