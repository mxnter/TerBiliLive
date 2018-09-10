package com.TerBiliLive.Thr;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveRoom;
import com.TerBiliLive.TerBiliLive.SendBarrage;
import com.TerBiliLive.Utiliy.CodingUtil;
import com.TerBiliLive.Utiliy.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

import static com.TerBiliLive.Utiliy.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatHour;

public class SendBarrage_Thr extends Thread{

    @Override
    public void run() {
        super.run();

        for(String msg : ConfInfo.SendBarrageList){

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String roomid = ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText();
            if(ConfInfo.sendBarrage==null)ConfInfo.sendBarrage=new SendBarrage();
            ConfInfo.liveRoom=new LiveRoom(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText().toString());
            String RTData =ConfInfo.sendBarrage.SendBarrage(ConfInfo.liveRoom.room_id,ConfInfo.cookie,msg);





            try {
                JSONObject jsonObject = new JSONObject(RTData);

                System.out.println(jsonObject);
                switch (jsonObject.getString("code")) {

                    case "0":
                        ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_Time.setText(getFormatHour());
                        ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_State.setText("发送成功：OK"+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                        LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送成功]-->[" +roomid+"] ："+  ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_Text.getText() +"\t< -OK- "+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",ConfInfo.terBiliLive_hfj_ui.ProjectName);
                        if(jsonObject.getString("msg").equals("msg repeat"))ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_State.setText("弹幕重复");
                        break;
                    case "-101":
                        ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_Time.setText(getFormatHour());
                        ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_State.setText("发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                        LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+  ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_Text.getText() +"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+ ">"+"\t 返回值：" + CodingUtil.ascii2native(RTData)  +"\n",ConfInfo.terBiliLive_hfj_ui.ProjectName);
                        break;
                    case "-500":
                        ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_Time.setText(getFormatHour());
                        ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_State.setText("发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                        LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+  ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_Text.getText()+"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" +CodingUtil.ascii2native(RTData)  + "\n",ConfInfo.terBiliLive_hfj_ui.ProjectName);
                        break;
                    case "-400":
                        ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_Time.setText(getFormatHour());
                        ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_State.setText("发送失败 ："+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                        LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" +roomid+"] ："+ ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_Text.getText()+"\t<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",ConfInfo.terBiliLive_hfj_ui.ProjectName);
                        break;
                    default:
                        ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_Time.setText(getFormatHour());
                        ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_State.setText("未知错误，"+"<"+ CodingUtil.ascii2native(jsonObject.getString("msg"))+">");
                        LogUtil.putLog(getFormatDay(), getFormatHour(), "[未知错误]-->[" +roomid+"] ："+ ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_Text.getText()+"\t<"+  CodingUtil.ascii2native(jsonObject.getString("msg"))+">"+ "\t 返回值：" + CodingUtil.ascii2native(RTData)  + "\n",ConfInfo.terBiliLive_hfj_ui.ProjectName);
                }
                ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_Text.setText("");

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        ConfInfo.terBiliLive_hfj_ui.HFJ_UiB_Send.setEnabled(true);
        ConfInfo.terBiliLive_hfj_ui.HFJ_UiT_Text.setEnabled(true);
        ConfInfo.SendBarrageList.clear();

    }
}
