package com.TerBiliLive.Thr;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.TerBiliLive.SendBarrage;
import com.TerBiliLive.Utils.*;
import org.json.JSONException;
import org.json.JSONObject;

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
                    InOutPutUtil.outPut("弹幕重复 - 判断时间");
                    if (TimeUtil.timeStamplong() - ConfInfo.Upper_barrage_time < 6) {
                        InOutPutUtil.outPut("弹幕重复 - 判断时间 - 小于6秒 - 未发送");
                        ConfInfo.barrage.setNotice(1,"弹幕重复未发送",null);
                        ConfInfo.SendBarrageList.remove(0);
                        continue;
                    }

                }
                if(!ConfInfo.systemState.isLink){//如果未连接则不发送
                    ConfInfo.barrage.setNotice(1,"请先连接到房间",null);
                    ConfInfo.barrage.setSendText(ConfInfo.SendBarrageList.get(0).getMsg());
                    ConfInfo.SendBarrageList.remove(0);
                    continue;
                }
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String roomid = ConfInfo.barrage.getRoomid();
                if (ConfInfo.sendBarrage == null) ConfInfo.sendBarrage = new SendBarrage();
                ConfInfo.liveRoom.getLiveRoom(ConfInfo.barrage.getRoomid());
                //TODO 测试接收礼物后回复的弹幕
                String RTData = "{\"msg\":\"请先解锁弹幕锁\",\"code\":-500,\"data\":[],\"message\":\"请先解锁弹幕锁\"}";
                LogUtil.putLogSendBarrageRecord(msg);
//                String RTData;
                switch (ConfInfo.SendBarrageList.get(0).getType()){
                    case 1:{
                        if(!ConfInfo.systemState.isSystemSendLock){
                            RTData=ConfInfo.sendBarrage.SendBarrage(ConfInfo.liveRoom.getRoom_id(),ConfInfo.confData.getCookie(),msg);
                        }
                        break;
                    }
                    case 2:{
                            RTData=ConfInfo.sendBarrage.SendBarrage(ConfInfo.liveRoom.getRoom_id(),ConfInfo.confData.getCookie(),msg);
                        break;
                    }
                    default:{
                        RTData=ConfInfo.sendBarrage.SendBarrage(ConfInfo.liveRoom.getRoom_id(),ConfInfo.confData.getCookie(),msg);
                        break;
                    }
                }
                ConfInfo.SendBarrageList.remove(0);
                ConfInfo.Upper_barrage = msg;
                ConfInfo.Upper_barrage_time = TimeUtil.timeStamplong();


                try {
                    JSONObject jsonObject = new JSONObject(RTData);

                    InOutPutUtil.outPut(jsonObject);
//                    TerBiliLive_SendBarrage_Ui.HFJ_UiT_Time.setText(getFormatHour());
                    String retMsg = "";
                    String logSendBarrage = "";
                    switch (jsonObject.getString("code")) {

                        case "0":
                            retMsg = "发送成功：OK" + "(" + CodingUtil.ascii2native(jsonObject.getString("message")) + ")" + msg;
                            logSendBarrage = "[发送成功]-->[" + roomid + "] : "+msg+"\t<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + "\t 返回值：" + CodingUtil.ascii2native(RTData) + "\n";
                            if (jsonObject.getString("msg").equals("msg repeat"))
                                retMsg = "弹幕重复";
                            break;
                        case "-101":

                            retMsg = "发送失败 ：" + "<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + msg;
                            logSendBarrage = "[发送失败]-->[" + roomid + "] ：" + msg + "\t<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + "\t 返回值：" + CodingUtil.ascii2native(RTData) + "\n";
                            ConfInfo.dingtalk.LiveLogin(); // 未登录通知
                            break;
                        case "-500":
                        case "-400":

                            retMsg = "发送失败 ：" + "<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + msg;
                            logSendBarrage ="[发送失败]-->[" + roomid + "] ：" + msg + "\t<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + "\t 返回值：" + CodingUtil.ascii2native(RTData) + "\n";
                            break;
                        case "1003": // 禁言返回值

                            retMsg = "已被禁言 ：" + "<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + msg;
                            logSendBarrage = "[已被禁言]-->[" + roomid + "] ：" + msg + "\t<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + "\t 返回值：" + CodingUtil.ascii2native(RTData) + "\n";
                            String ms = "禁言 ：" + getFormat() + " - " + jsonObject.getString("message");//提示信息
                            ConfInfo.putShowUtil.PutDMUtil(ms, ColorUtil.toColorFromString("ea9336"));
                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), ms, ConfInfo.barrage.getRoomid());//输出到弹幕日志
                            ConfInfo.barrage.closeThank(); // 被禁言 关闭感谢
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
                            retMsg = "未知错误，" + "<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + msg;
                            logSendBarrage = "[未知错误]-->[" + roomid + "] ：" + msg + "\t<" + CodingUtil.ascii2native(jsonObject.getString("message")) + ">" + "\t 返回值：" + CodingUtil.ascii2native(RTData) + "\n";
                    }
                    ConfInfo.barrage.setNotice(1,retMsg,null);
                    LogUtil.putLogSendBarrage(logSendBarrage);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else {
                synchronized (ConfInfo.SBLT) {
                    try {
                        ConfInfo.SBLT.wait();
//                        ConfInfo.GetSendBarrageList_Thr_Size=false;
                        InOutPutUtil.outPut("-----------------------发送弹幕　　进入休眠-----------------------");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
