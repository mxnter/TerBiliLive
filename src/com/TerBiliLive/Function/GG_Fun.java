package com.TerBiliLive.Function;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveRoom;
import com.TerBiliLive.TerBiliLive.SendBarrage;
import com.TerBiliLive.Ui.TerBiliLive_Adv_Ui;
import com.TerBiliLive.Ui.TerBiliLive_Control_Ui;
import com.TerBiliLive.Utils.CodingUtil;
import com.TerBiliLive.Utils.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

import static com.TerBiliLive.Utils.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utils.TimeUtil.getFormatHour;

public class GG_Fun {

    public GG_Fun(){

        String roomid, cookie, msg;

        roomid = TerBiliLive_Control_Ui.Control_UiT_RoomId.getText();
        msg = TerBiliLive_Adv_Ui.GG_UiT_Text.getText();
        cookie = ConfInfo.confData.getCookie();

        ConfInfo.sendBarrage=new SendBarrage();
        if(ConfInfo.liveRoom==null)ConfInfo.liveRoom =new LiveRoom(TerBiliLive_Control_Ui.Control_UiT_RoomId.getText());
        String RTData =ConfInfo.sendBarrage.SendBarrage(LiveRoom.room_id,cookie,msg);


        try {
            JSONObject jsonObject = new JSONObject(RTData);
            String retMsg = "";
            String logTimingBarrage = "";


            switch (jsonObject.getString("code")) {

                case "0":
                    //GG.GG_UiT_Time.setText(getFormatHour());
                    retMsg = getFormatHour()+"发送成功：OK"+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">";
                    logTimingBarrage =  "[发送成功]-->[" +roomid+"] ："+  TerBiliLive_Adv_Ui.GG_UiT_Text.getText() +"\t< -OK- "+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData);
                    break;
                case "-101":
                case "-400":
                case "-500":
                    // GG.GG_UiT_Time.setText(+getFormatHour());
                    // GG.GG_UiT_Time.setText(getFormatHour());
                    retMsg = getFormatHour()+"发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">";
                    logTimingBarrage =  "[发送失败]-->[" +roomid+"] ："+  TerBiliLive_Adv_Ui.GG_UiT_Text.getText() +"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+ ">"+"\t 返回值：" + CodingUtil.ascii2native(RTData);
                    break;
                default:
                   // GG.GG_UiT_Time.setText(getFormatHour());
                    retMsg = getFormatHour()+"未知错误，"+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">";
                    logTimingBarrage =  "[未知错误]-->[" +roomid+"] ："+ TerBiliLive_Adv_Ui.GG_UiT_Text.getText()+"\t<"+  CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData);
            }
            TerBiliLive_Adv_Ui.GG_UiT_State.setText(retMsg);
            LogUtil.putLogTimingBarrage(logTimingBarrage);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
