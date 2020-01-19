package com.github.TerBiliLive.Function;

import com.github.TerBiliLive.Info.ConfInfo;
import com.github.TerBiliLive.Info.LiveRoom;
import com.github.TerBiliLive.TerBiliLive.SendBarrage;
import com.github.TerBiliLive.Utiliy.CodingUtil;
import com.github.TerBiliLive.Utiliy.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.github.TerBiliLive.Utiliy.TimeUtil.getFormatDay;
import static com.github.TerBiliLive.Utiliy.TimeUtil.getFormatHour;

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
        msg = ConfInfo.terBiliLive_adv_ui.GG_UiT_Text.getText();
        cookie = ConfInfo.cookie;


        ConfInfo.sendBarrage=new SendBarrage();
        if(ConfInfo.liveRoom==null)ConfInfo.liveRoom =new LiveRoom(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText().toString());
        String RTData =ConfInfo.sendBarrage.SendBarrage(ConfInfo.liveRoom.room_id,cookie,msg);


        try {
            JSONObject jsonObject = new JSONObject(RTData);



            switch (jsonObject.getString("code")) {

                case "0":
                    //GG.GG_UiT_Time.setText(getFormatHour());
                    ConfInfo.terBiliLive_adv_ui.GG_UiT_State.setText(getFormatHour()+"发送成功：OK"+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");

                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送成功]-->[" +roomid+"] ："+  ConfInfo.terBiliLive_adv_ui.GG_UiT_Text.getText() +"\t< -OK- "+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",ConfInfo.terBiliLive_adv_ui.ProjectName);
                    break;
                case "-101":
                   // GG.GG_UiT_Time.setText(getFormatHour());
                    ConfInfo.terBiliLive_adv_ui.GG_UiT_State.setText(getFormatHour()+"发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+  ConfInfo.terBiliLive_adv_ui.GG_UiT_Text.getText() +"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+ ">"+"\t 返回值：" + CodingUtil.ascii2native(RTData)  +"\n",ConfInfo.terBiliLive_adv_ui.ProjectName);
                    break;
                case "-500":
                   // GG.GG_UiT_Time.setText(getFormatHour());
                    ConfInfo.terBiliLive_adv_ui.GG_UiT_State.setText(getFormatHour()+"发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+  ConfInfo.terBiliLive_adv_ui.GG_UiT_Text.getText()+"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" +CodingUtil.ascii2native(RTData)  + "\n",ConfInfo.terBiliLive_adv_ui.ProjectName);
                    break;
                case "-400":
                   // GG.GG_UiT_Time.setText(+getFormatHour());
                    ConfInfo.terBiliLive_adv_ui.GG_UiT_State.setText(getFormatHour()+"发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+ ConfInfo.terBiliLive_adv_ui.GG_UiT_Text.getText()+"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",ConfInfo.terBiliLive_adv_ui.ProjectName);
                    break;
                default:
                   // GG.GG_UiT_Time.setText(getFormatHour());
                    ConfInfo.terBiliLive_adv_ui.GG_UiT_State.setText(getFormatHour()+"未知错误，"+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                    LogUtil.putLog(getFormatDay(), getFormatHour(), "[未知错误]-->[" +roomid+"] ："+ ConfInfo.terBiliLive_adv_ui.GG_UiT_Text.getText()+"\t<"+  CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",ConfInfo.terBiliLive_adv_ui.ProjectName);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
