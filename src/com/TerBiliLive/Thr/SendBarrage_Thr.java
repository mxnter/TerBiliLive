package com.TerBiliLive.Thr;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveRoom;
import com.TerBiliLive.TerBiliLive.SendBarrage;
import com.TerBiliLive.Utils.*;
import org.json.JSONException;
import org.json.JSONObject;

import static com.TerBiliLive.Ui.TerBiliLive_Control_Ui.Control_UiT_RoomId;
import static com.TerBiliLive.Utils.TimeUtil.*;

/**
 * CODE IS POETRY
 * @Nmae ：发送弹幕线程
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 11:51 2018/11/4
 * @作用 在list内读取弹幕信息 并执行发送 获取回收值
 */

public class SendBarrage_Thr extends Thread {

    @Override
    public void run() {
        super.run();


        while (true) {

            if (!ConfInfo.SendBarrageList.isEmpty() && !ConfInfo.SendBarrageList.get(0).equals("")) {
                String msg = ConfInfo.SendBarrageList.get(0).getMsg();
                if (msg.equals(ConfInfo.Upper_barrage)) {
                    System.out.println("弹幕重复 - 判断时间");
                    if (TimeUtil.timeStamplong() - ConfInfo.Upper_barrage_time < 6) {
//                    ConfInfo.Upper_barrage_time =TimeUtil.timeStamplong();
                        System.out.println("弹幕重复 - 判断时间 - 小于6秒 - 未发送");
                        ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_State.setText("弹幕重复 - 判断时间 - 小于6秒 - 未发送");
                        ConfInfo.SendBarrageList.remove(0);
                        continue;
                    }

                }
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String roomid = ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText();
                if (ConfInfo.sendBarrage == null) ConfInfo.sendBarrage = new SendBarrage();
                ConfInfo.liveRoom = new LiveRoom(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText().toString());
                //TODO 测试接收礼物后回复的弹幕
                String RTData = "{\"msg\":\"强制暂停发送\",\"code\":-500,\"data\":[],\"message\":\"强制暂停发送\"}";
                LogUtil.putLog(getFormatDay(), getFormatHour(), msg.toString() + "\n", " DM Log", "DM");
//                String RTData;
                switch (ConfInfo.SendBarrageList.get(0).getType()){
                    case 1:{
                        if(ConfInfo.Thank.equals("ok")){
                            RTData=ConfInfo.sendBarrage.SendBarrage(ConfInfo.liveRoom.room_id,ConfInfo.confData.getCookie(),msg);
                        }
                        break;
                    }
                    case 2:{
                            RTData=ConfInfo.sendBarrage.SendBarrage(ConfInfo.liveRoom.room_id,ConfInfo.confData.getCookie(),msg);
                        break;
                    }
                    default:{
                        RTData=ConfInfo.sendBarrage.SendBarrage(ConfInfo.liveRoom.room_id,ConfInfo.confData.getCookie(),msg);
                        break;
                    }
                }
                ConfInfo.SendBarrageList.remove(0);
                ConfInfo.Upper_barrage = msg;
                ConfInfo.Upper_barrage_time = TimeUtil.timeStamplong();


                try {
                    JSONObject jsonObject = new JSONObject(RTData);

                    System.out.println(jsonObject);
                    switch (jsonObject.getString("code")) {

                        case "0":
                            ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Time.setText(getFormatHour());
                            ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_State.setText("发送成功：OK" + "<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + msg);
                            LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送成功]-->[" + roomid + "] ：" + ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.getText() + "\t< -OK- " + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + "\t 返回值：" + CodingUtil.ascii2native(RTData) + "\n", ConfInfo.terBiliLive_sendBarrage_ui.ProjectName);
                            if (jsonObject.getString("msg").equals("msg repeat"))
                                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_State.setText("弹幕重复");
                            break;
                        case "-101":
                            ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Time.setText(getFormatHour());
                            ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_State.setText("发送失败 ：" + "<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + msg);
                            LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" + roomid + "] ：" + ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.getText() + "\t<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + "\t 返回值：" + CodingUtil.ascii2native(RTData) + "\n", ConfInfo.terBiliLive_sendBarrage_ui.ProjectName);
                            ConfInfo.dingtalk.LiveLogin(); // 未登录通知
                            break;
                        case "-500":
                            ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Time.setText(getFormatHour());
                            ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_State.setText("发送失败 ：" + "<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + msg);
                            LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" + roomid + "] ：" + ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.getText() + "\t<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + "\t 返回值：" + CodingUtil.ascii2native(RTData) + "\n", ConfInfo.terBiliLive_sendBarrage_ui.ProjectName);
                            break;
                        case "-400":
                            ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Time.setText(getFormatHour());
                            ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_State.setText("发送失败 ：" + "<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + msg);
                            LogUtil.putLog(getFormatDay(), getFormatHour(), "[发送失败]-->[" + roomid + "] ：" + ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.getText() + "\t<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + "\t 返回值：" + CodingUtil.ascii2native(RTData) + "\n", ConfInfo.terBiliLive_sendBarrage_ui.ProjectName);
                            break;
                        case "1003": // 禁言返回值
                            ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Time.setText(getFormatHour());
                            ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_State.setText("已被禁言 ：" + "<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + msg);
                            LogUtil.putLog(getFormatDay(), getFormatHour(), "[已被禁言]-->[" + roomid + "] ：" + ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.getText() + "\t<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + "\t 返回值：" + CodingUtil.ascii2native(RTData) + "\n", ConfInfo.terBiliLive_sendBarrage_ui.ProjectName);
                            String ms = "禁言 ：" + getFormat() + " - " + jsonObject.getString("message");//提示信息
                            ConfInfo.putShowUtil.PutDMUtil(ms, ColorUtil.toColorFromString("ea9336"));
                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), ms, Control_UiT_RoomId.getText());//输出到弹幕日志
                            ConfInfo.terBiliLive_control_ui.Control_UiB_ClaseThinks.doClick(); // 被禁言 关闭感谢
                            //TODO 暂时只关闭 感谢
//                            ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false); // 关闭 聊天
//                            ConfInfo.terBiliLive_control_ui.Reply_tourist.setSelected(false);// 关闭 回应游客
//                            ConfInfo.terBiliLive_control_ui.Reply_LiveState.setSelected(false);// 关闭 状态
//                            ConfInfo.terBiliLive_control_ui.Reply_LowSecurity.setSelected(false);// 关闭 低保
//                            ConfInfo.terBiliLive_control_ui.Reply_Master.setSelected(false);// 关闭 老爷
//                            ConfInfo.terBiliLive_control_ui.Reply_MasterBarrage.setSelected(false);// 关闭 显示弹幕老爷
//                            ConfInfo.terBiliLive_control_ui.Reply_MasterRadioGift.setSelected(false);// 关闭 广播后开启老爷
//                            ConfInfo.terBiliLive_control_ui.Reply_Guard.setSelected(false);// 关闭 舰长
                            ConfInfo.dingtalk.bannedNotice(jsonObject.getString("message")); // 通知禁言
                            break;
                        default:
                            ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Time.setText(getFormatHour());
                            ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_State.setText("未知错误，" + "<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + msg);
                            LogUtil.putLog(getFormatDay(), getFormatHour(), "[未知错误]-->[" + roomid + "] ：" + ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.getText() + "\t<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + "\t 返回值：" + CodingUtil.ascii2native(RTData) + "\n", ConfInfo.terBiliLive_sendBarrage_ui.ProjectName);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiB_Send.setEnabled(true);
                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.setEnabled(true);
                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.grabFocus();


            } else {
                synchronized (ConfInfo.SBLT) {
                    try {
                        ConfInfo.SBLT.wait();
//                        ConfInfo.GetSendBarrageList_Thr_Size=false;
                        System.out.println("-----------------------发送弹幕　　进入休眠-----------------------");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
