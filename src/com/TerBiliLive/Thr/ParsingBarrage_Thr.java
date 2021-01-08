package com.TerBiliLive.Thr;


import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.Presents;
import com.TerBiliLive.Inlet.SendBarrage_Inlet;
import com.TerBiliLive.TerBiliLive.HttpClient;
import com.TerBiliLive.Utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.regex.Pattern;

import static com.TerBiliLive.Utils.TimeUtil.*;

/**
 * CODE IS POETRY
 *
 * @Name ：弹幕信息解析线程
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 11:51 2018/11/4  Renovation in 21:41 2020/9/14
 */
public class ParsingBarrage_Thr extends Thread {


    @Override
    public void run() {
        super.run();


        while (true) {

//          System.out.print("0");
            try {
                if ( null!=ConfInfo.ParsingBarrageList
                        && !ConfInfo.ParsingBarrageList.isEmpty()
                        && !ConfInfo.ParsingBarrageList.get(0).equals("")) {


                    String putDM = "";
                    String putTZ = "";
                    String minorNotice = "";
                    JSONObject object = JSON.parseObject(ConfInfo.ParsingBarrageList.get(0));
                    String msgType = object.getString("cmd");


                    switch (msgType) {
                        // 弹幕
                        case "DANMU_MSG": {
                            //{"cmd":"DANMU_MSG","info":[[0,1,25,16777215,1600055245505,1600055231,0,"a820de3c",0,0,0,""],"弹幕",[18169995,"mxnter",1,0,0,10000,1,""],[17,"小兔团","小小的小兔团儿",7471685,16752445,"",0,16752445,16752445,16752445,0,1,27897180],[36,0,10512625,"\u003e50000"],["title-89-1","title-89-1"],0,0,null,{"ts":1600055245,"ct":"A9E55EFB"},0,0,null,null,0]}
                            JSONArray Date = object.getJSONArray("info").getJSONArray(0); // 停留时间等数据
                            String text = object.getJSONArray("info").getString(1); // 接收的弹幕数据
                            JSONArray array = object.getJSONArray("info").getJSONArray(2); // 用户信息

                            String timeline = TimeUtil.timeStamp2Datess(Date.getString(4), null); // 发送弹幕时间

                            // 大航海信息
                            String guard = "";
                            switch (object.getJSONArray("info").getString(7)){
                                case "3":
                                    guard= "「舰长」";break;
                                case "2":
                                    guard= "「提督」";break;
                                case "1":
                                    guard= "「总督」";break;
                                default:
                                    guard="";break;
                            }

                            // 粉丝勋章
                            String medal = "";
                            try {
                                medal = "[ " + object.getJSONArray("info").getJSONArray(3).getString(1) + "."+object.getJSONArray("info").getJSONArray(3).getString(0) + " ] ";

                            } catch (Exception e) {
                                medal = "";
                            }

                            // 老爷
                            String vip = "";
                            if (array.getString(3).equals("1")) {
                                vip = "普通老爷";
                            }
                            if (array.getString(4).equals("1")) {
                                vip = "年费老爷";
                            }

                            // 房管
                            String isadmin = (array.getString(2).equals("1")) ? array.getString(0).equals(ConfInfo.getLiveRoomUserInfo.getRoomUseruid()) ? "[主]": "[房]" : "";

                            // 用户等级
                            String user_level = "[ UL." + object.getJSONArray("info").getJSONArray(4).getString(0) + " ]";

                            // 用户昵称
                            String nickname = array.getString(1);

                            // 用户id
                            String  uid = array.getString(0);

                            String _isadmin = isadmin.equals("")?"":("  " + isadmin);
                            String _vip = vip.equals("")?"":("  " + vip);
                            String _medal = medal.equals("")?"":("  " + medal);
                            String _guard = guard.equals("")?"":("  " +guard);
                            String _user_level = user_level.equals("")?"":("  " + user_level + " ");

                            // 整合后弹幕
                            putDM = "弹幕" + " | " + timeline + " | " + _guard + _medal + _vip + _isadmin + _user_level + nickname + " : " + text;


                            //TODO 根据等级显示不同颜色 暂时无法使用
//                        try {
//                            ConfInfo.PPutBUtil.ParsePut(timeline, GUARD ,vip, isadmin , medal,medal_level,  user_level ,  nickname , text);
//                        } catch (BadLocationException e) {
//                            e.printStackTrace();
//                        }

                            DmLogUtil.putBarrageInfoDatabase(timeline,ConfInfo.barrage.getRoomid(),msgType,guard,vip,isadmin,medal,user_level,uid,nickname,text,putDM,object.toString());

                            if (array.getString(2).equals("1") || uid.equals("18169995") || uid.equals("184464621") || uid.equals("301164820")) {

                                switch (text) {
                                    case "#version":
                                    case "# 版本":{
                                        new SendBarrage_Inlet(ConfInfo.AppVersion+"-"+ConfInfo.AppVersionBuildNum+"("+ConfInfo.AppVersionNum+")");
                                        break;
                                    }

                                    case "#open_sendlock": {
                                        ConfInfo.systemState.isSystemSendLock = true;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("锁已打开",2);
                                        break;
                                    }

                                    case "#close_sendlock": {
                                        ConfInfo.systemState.isSystemSendLock = false;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("锁已关闭");
                                        break;
                                    }

                                    case "#close_laoye":
                                    case "# 关闭欢迎老爷":
                                    case "# 关闭老爷":{
                                        ConfInfo.systemState.isGreetMaster = false;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("欢迎老爷已关闭");
                                        break;
                                    }

                                    case "#open_laoye":
                                    case "# 开启欢迎老爷":
                                    case "# 开启老爷": {
                                        ConfInfo.systemState.isGreetMaster = true;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("欢迎老爷已开启");
                                        break;
                                    }

                                    case "#open_thank":
                                    case "# 开启感谢": {
                                        ConfInfo.barrage.startThank();
                                        new SendBarrage_Inlet("感谢已开启");
                                        break;
                                    }

                                    case "#close_thank":
                                    case "# 关闭感谢": {
                                        ConfInfo.barrage.closeThank();
                                        new SendBarrage_Inlet("感谢已关闭");
                                        break;
                                    }


                                    case "#close_30": {
                                        ConfInfo.systemState.isSend30 = false;
                                        ConfInfo.barrage.startThank();
                                        new SendBarrage_Inlet("我最多可以发送20字了哟");
                                        break;
                                    }
                                    case "#open_30": {
                                        ConfInfo.systemState.isSend30 = true;
                                        ConfInfo.barrage.startThank();
                                        new SendBarrage_Inlet("我最多可以发送30字了哟");
                                        break;
                                    }

                                    case "#open_latiao":
                                    case "# 忽略辣条": {
                                        ConfInfo.systemState.isIgnoreSpicystrip = true;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("忽略辣条已开启");
                                        break;
                                    }
                                    case "#close_latiao":
                                    case "# 感谢辣条": {
                                        ConfInfo.systemState.isIgnoreSpicystrip = false;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("忽略辣条已关闭");
                                        break;
                                    }

                                    case "#open_user":
                                    case "# 欢迎用户": {
                                        ConfInfo.systemState.isInteractWord = true;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("欢迎用户已开启，此功能仅限人少的直播间，不推荐开启");
                                        break;
                                    }
                                    case "#close_user":
                                    case "# 忽略用户": {
                                        ConfInfo.systemState.isInteractWord = false;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("欢迎用户已关闭");
                                        break;
                                    }

                                    case "#open_jianzhang":
                                    case "# 开启欢迎舰长":
                                    case "# 开启舰长": {
                                        ConfInfo.systemState.isGreetCaptain = true;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("开启舰长 成功");
                                        break;
                                    }

                                    case "#close_jianzhang":
                                    case "# 关闭欢迎舰长":
                                    case "# 关闭舰长": {
                                        ConfInfo.systemState.isGreetCaptain = false;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("关闭舰长 成功");
                                        break;
                                    }

                                    case "#open_livetype":
                                    case "# 开启直播提醒":
                                    case "# 开启状态": {
                                        ConfInfo.systemState.isLiveStatus = true;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("直播提醒已开启");
                                        break;
                                    }

                                    case "#close_livetype":
                                    case "# 关闭直播提醒":
                                    case "# 关闭状态": {
                                        ConfInfo.systemState.isLiveStatus = false;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("直播提醒已关闭");
                                        break;
                                    }

                                    case "ter闭嘴":
                                    case "Ter闭嘴": {
                                        ConfInfo.systemState.isSystemSendLock = true;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("那好吧，人家不说话就是了（￣へ￣）",2);
                                        break;
                                    }

                                    case "ter感谢":
                                    case "Ter感谢": {
                                        ConfInfo.barrage.startThank();
                                        new SendBarrage_Inlet("大家多多送礼物哦，我会感谢的呦！");
                                        break;
                                    }

                                    case "ter停止感谢":
                                    case "Ter停止感谢": {
                                        ConfInfo.barrage.closeThank();
                                        new SendBarrage_Inlet("不感谢了？那我去休息一下了哦！");
                                        break;
                                    }


                                    case "ter忽略辣条":
                                    case "Ter忽略辣条": {
                                        ConfInfo.systemState.isIgnoreSpicystrip = true;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("辣条太多了忙不过来了，先忽略了呦！");
                                        break;
                                    }
                                    case "ter感谢辣条":
                                    case "Ter感谢辣条": {
                                        ConfInfo.systemState.isIgnoreSpicystrip = false;
                                        ConfInfo.barrage.updateStatus();
                                        new SendBarrage_Inlet("收到指令，我开启了感谢辣条哦");
                                        break;
                                    }


                                    // 已禁止抢辣条功能
//                                    case "# 开启低保": {
//                                        ConfInfo.terBiliLive_control_ui.Reply_LowSecurity.setSelected(true);
//                                        new HFJ_Fun("开启低保 成功");
//                                        break;
//                                    }
//                                    case "# 关闭低保": {
//                                        ConfInfo.terBiliLive_control_ui.Reply_LowSecurity.setSelected(false);
//                                        new HFJ_Fun("关闭低保 成功");
//                                        break;
//                                    }

                                }

                                String pattern = "# 呼叫Ter .*";
                                if (Pattern.matches(pattern, text)) {
                                    ConfInfo.dingtalk.chatAdmin(text.substring(2), nickname);
                                }
                            }

                            String patter = "# 呼叫Ter " + ConfInfo.confData.getRoomId() + " .*";
                            if (Pattern.matches(patter, text)) {
                                ConfInfo.dingtalk.chatAdmin(text.substring(2), nickname);
                            }



                            // 停留时间
                            if (text.equals("# 停留时间")) {
                                String t1 = Date.getString(4);
                                String t2 = Date.getString(5);
                                if(t1.length()==13){
                                    t1 = t1.substring(0,10);
                                }
                                if(t2.length()==13){
                                    t2 = t2.substring(0,10);
                                }
                                long TLTime = new Date(Integer.parseInt(t1)).getTime() - new Date(Integer.parseInt(t2)).getTime();
                                new SendBarrage_Inlet(nickname+"在直播间停留了" + ((TLTime) / 60) + "分" + (TLTime % 60) + "秒 (未必准确)");
                            }

                            // 聊天功能暂时关闭
//                            if (ConfInfo.terBiliLive_control_ui.Reply_chat.isSelected()) {
//                                if (!ConfInfo.terBiliLive_control_ui.Reply_tourist.isSelected()) {
//                                    if (array.getString(2).equals("1") || nickname.equals("mxnter")) {
//                                        String pattern = "@ .*";
//                                        if (Pattern.matches(pattern, text)) {
//                                            String TulingUtilMsg = TulingUtil.chat(text.substring(2), ConfInfo.confData.getTulingApikey());
////                                                    ConfInfo.putShowUtil.PutDMUtil(TulingUtilMsg);
//                                            switch (TulingUtilMsg) {
//                                                case "msgnull": {
//                                                    InOutPutUtil.outPut("消息为空");
//                                                    break;
//                                                }
//                                                case "TulingApikeynull": {
//                                                    ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false);
//                                                    new HFJ_Fun(TulingUtilMsg);
//                                                    break;
//                                                }
//                                                case "TerGGKey": {
//                                                    ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false);
//                                                    new HFJ_Fun(TulingUtilMsg);
//                                                    break;
//                                                }
//                                                case "TerGG": {
//                                                    ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false);
//                                                    new HFJ_Fun(TulingUtilMsg);
//                                                    break;
//                                                }
//                                                default: {
//                                                    new HFJ_Fun(TulingUtilMsg);
//                                                    break;
//                                                }
//                                            }
//                                        }
//                                    }
//                                } else {
//                                    String pattern = "@ .*";
//                                    if (Pattern.matches(pattern, text)) {
//                                        String TulingUtilMsg = TulingUtil.chat(text.substring(2), ConfInfo.confData.getTulingApikey());
////                                                    ConfInfo.putShowUtil.PutDMUtil(TulingUtilMsg);
//                                        switch (TulingUtilMsg) {
//                                            case "msgnull": {
//                                                InOutPutUtil.outPut("消息为空");
//                                                break;
//                                            }
//                                            case "TulingApikeynull": {
//                                                ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false);
//                                                new HFJ_Fun(TulingUtilMsg);
//                                                break;
//                                            }
//                                            case "TerGGKey": {
//                                                ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false);
//                                                new HFJ_Fun(TulingUtilMsg);
//                                                break;
//                                            }
//                                            case "TerGG": {
//                                                ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false);
//                                                new HFJ_Fun(TulingUtilMsg);
//                                                break;
//                                            }
//                                            default: {
//                                                new HFJ_Fun(TulingUtilMsg);
//                                                break;
//                                            }
//                                        }
//                                    }
//                                }
////
//                            }
                            InOutPutUtil.outPut(putDM);
                            break;
                        }

                        //普通礼物
                        case "SEND_GIFT": {
                            JSONObject giftData = object.getJSONObject("data");
                            String giftName = giftData.getString("giftName");
                            int giftNum = giftData.getInteger("num");
                            String uname = giftData.getString("uname");
                            String timestamp = giftData.getString("timestamp");

                            //整合礼物使用整合线程进行展示弹幕
                            if (!ConfInfo.integrated.containsKey(uname+giftName)) {
                                InOutPutUtil.outPut("开启整合 礼物："+giftName +"MAP："+ uname+giftName);
                                new GiftIntegration_Thr().start(uname, giftName);
                                ConfInfo.integrated.put(uname+giftName, new Presents(timestamp,uname,giftName,0));
                            }
                            InOutPutUtil.outPut("开启整合 礼物："+giftName +"MAP："+ uname+giftName +"数量："+ giftNum);
                            ConfInfo.integrated.get(uname+giftName).setTimestamp(timestamp);
                            ConfInfo.integrated.get(uname+giftName).setGiftNum(ConfInfo.integrated.get(uname+giftName).getGiftNum()+giftNum);
                            putDM = "礼物" +" | "+ TimeUtil.timeStamp2Date(timestamp, null) + " | " + " 感谢 " + uname + " 赠送 " + giftName + "*" + giftNum;
                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putDM, ConfInfo.barrage.getRoomid());
                            break;
                        }

                        //连击礼物
                        case "COMBO_SEND": {
                            JSONObject giftData = object.getJSONObject("data");
                            String gift_name = giftData.getString("gift_name");
                            String uname = giftData.getString("uname");
                            int combo_num = giftData.getInteger("combo_num");
                            String timestamp = giftData.getString("timestamp");
                            putDM = "礼物" +" | "+ TimeUtil.timeStamp2Date(timestamp, null) + " | " + " 感谢 " + uname + " 赠送 " + gift_name + "*" + combo_num;
                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putDM, ConfInfo.barrage.getRoomid());
                            if (ConfInfo.systemState.isThank) new SendBarrage_Inlet("感谢 " + uname + " 赠送的 " + gift_name + "*" + combo_num + " 喵~",1);
                            InOutPutUtil.outPut(putDM);
                            LogUtil.putLogGiftRecord(object.toString() + "\n");
                            break;
                        }

                        //连击礼物
                        case "COMBO_END": {
                            JSONObject giftData = object.getJSONObject("data");
                            String gift_name = giftData.getString("gift_name");
                            String gift_id = giftData.getString("gift_id");
                            int combo_num = giftData.getInteger("combo_num");
                            String uname = giftData.getString("uname");
                            String end_time = giftData.getString("end_time");

                            if (!ConfInfo.SEND_GIFT.equals(uname + gift_name + combo_num)) {
                                putDM = "礼物" +" | "+ TimeUtil.timeStamp2Date(end_time, null) + " | " + " 感谢 " + uname + " 赠送 " + gift_name + "*" + combo_num;
                                DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putDM, ConfInfo.barrage.getRoomid());
                                // TODO 多次感谢暂时关闭
//                                if (ConfInfo.systemState.isThank) new SendBarrage_Inlet("感谢 " + uname + " 赠送的 " + gift_name + "*[" + combo_num + "连击]  喵~");
                                InOutPutUtil.outPut(putDM);
                            } else {
                                InOutPutUtil.outPut("连送礼物结束" + ConfInfo.SEND_GIFT);
                            }
                            LogUtil.putLogGiftRecord(object.toString() + "\n");
                            break;
                        }

                        // 直播开始
                        case "LIVE": {
                            String roomid = object.getString("roomid");
                            putTZ = "通知 ： ~ " + "   id:" + roomid + " 直播开启,各位老爷拿好小板凳哟";
                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putTZ, ConfInfo.barrage.getRoomid());
                            if (ConfInfo.systemState.isLiveMsg) new SendBarrage_Inlet("直播开启,各位老爷拿好小板凳哟");
                            ConfInfo.barrage.setLiveStatus(true);
                            InOutPutUtil.outPut("通知 ： ~ " + "   id:" + roomid + " 直播开启,各位老爷拿好小板凳哟");
                            ConfInfo.dingtalk.LiveLive();
                            break;
                        }

                        // 直播结束
                        case "PREPARING": {
                            String roomid = object.getString("roomid");
                            putTZ = "通知 ： ~ " + "   id:" + roomid + " 直播结束啦，各位老爷点关注不迷路.";
                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putTZ, ConfInfo.barrage.getRoomid());
                            if (ConfInfo.systemState.isLiveMsg) new SendBarrage_Inlet("直播结束啦，各位老爷点关注不迷路.");
                            ConfInfo.barrage.setLiveStatus(false);
                            InOutPutUtil.outPut("通知 ： ~ " + "   id:" + roomid + " 直播结束啦，各位老爷点关注不迷路.");
                            ConfInfo.dingtalk.LivePreparing();
                            break;
                        }

                        // 欢迎用户
                        case "INTERACT_WORD": {
                            minorNotice = "欢迎 "  + object.getJSONObject("data").getString("uname");
                            if (ConfInfo.systemState.isInteractWord) new SendBarrage_Inlet("欢迎 "+object.getJSONObject("data").getString("uname")+" 进入直播间，点个关注吧");

                            break;
                        }

                        // 欢迎老爷
                        case "WELCOME": {
                            //{"cmd":"WELCOME","data":{"uid":16042466,"uname":"蛊毒の果","is_admin":false,"svip":1}}
                            JSONObject welcData = object.getJSONObject("data");
                            String uname = welcData.getString("uname");
                            int vip = 0;
                            int svip = 0;
                            try {
                                vip = welcData.getInteger("vip");
                                svip = welcData.getInteger("svip");
                            } catch (Exception e) {
                                vip = 0;
                                svip = 0;
                            }
                            String vipStr = svip==1?"年费":(vip==1?"月费":"");
                            minorNotice = "欢迎" + vipStr + "老爷 " + uname;
                            if (ConfInfo.systemState.isGreetMaster) new SendBarrage_Inlet("欢迎" + vipStr + "老爷 " + uname);
                            //关闭弹幕显示欢迎老爷
//                            putDM = "提示 ：" + getFormat() + " @ " + "欢迎" + vipStr + "老爷 " + uname;
//                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putDM, ConfInfo.barrage.getRoomid());
//                            InOutPutUtil.outPut(putDM);
                            break;
                        }

                        // 欢迎大航海舰长
                        case "WELCOME_GUARD": {
                            JSONObject welcData = object.getJSONObject("data");
                            String username = welcData.getString("username");
                            String guard_level = welcData.getString("guard_level");
                            String guard_level_string = "";
                            switch (guard_level) {
                                case "3":
                                    guard_level_string = "舰长";
                                    break;
                                case "2":
                                    guard_level_string = "提督";
                                    break;
                                case "1":
                                    guard_level_string = "总督";
                                    break;
                                default:
                                    guard_level_string = "";
                            }

                            putDM =  "提示"+" | "+getFormat()+" | "+"欢迎"+guard_level_string+" "+username +" 进入直播间\t";
                            if (ConfInfo.systemState.isGreetCaptain) new SendBarrage_Inlet("欢迎" + guard_level_string + " " + username + " 进入直播间");
                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putDM, ConfInfo.barrage.getRoomid());
                            InOutPutUtil.outPut(putDM);
                            break;
                        }

                        // 购买舰长
                        case "GUARD_BUY": {
                            //{"data":{"uid":263160725,"start_time":1537077349,"price":198000,"num":1,"gift_id":10003,"end_time":1537077349,"gift_name":"舰长","username":"送君千里带绿帽","guard_level":3},"cmd":"GUARD_BUY"}
                            JSONObject welcData = object.getJSONObject("data");
                            String gift_name = welcData.getString("gift_name");
                            String username = welcData.getString("username");
                            String start_time = welcData.getString("start_time");
                            String guard_level = welcData.getString("guard_level");
                            String guard_level_string = gift_name;
                            putDM = "提示" +" | " + TimeUtil.timeStamp2Date(start_time, null) +" | " +"大航海新添" + " " + username + " " + guard_level_string;
                            if (ConfInfo.systemState.isGreetCaptain) new SendBarrage_Inlet("大航海新添" + " " + username  + " " + guard_level_string+ " ，不要忘记添加"+ guard_level_string +"群",1);
                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putDM, ConfInfo.barrage.getRoomid());
                            InOutPutUtil.outPut(putDM);
                            break;
                        }

                        // 大航海全屏通知
                        case "GUARD_MSG": {
                            //{"msg":"用户 :?风烧的大兔子丶:? 在主播 肥皂菌丨珉珉的猫咪丨 的直播间开通了总督","buy_type":1,"cmd":"GUARD_MSG","msg_new":"用户<%风烧的大兔子丶%>在主播<%肥皂菌丨珉珉的猫咪丨%>的直播间开通了总督并触发了抽奖，点击前往TA的房间去抽奖吧","url":"https://live.bilibili.com/78787","roomid":78787,"broadcast_type":0}
                            String roomid = object.getString("roomid");
                            String msg = object.getString("msg");

                            int buy_type = object.getInteger("buy_type");
                            String buy_typeStr = "";
                            switch (buy_type) {
                                case 1: { buy_typeStr = "总督";break; }
                                case 2: { buy_typeStr = "提督";break; }
                                case 3: { buy_typeStr = "舰长";break; }
                            }
                            minorNotice = "大航海 " + roomid + " 类型:" + buy_typeStr + " " + msg;
                            InOutPutUtil.outPut(minorNotice);
                            break;
                        }

                        // 用户封禁 禁言
                        case "ROOM_BLOCK_MSG": {
                            // {"cmd":"ROOM_BLOCK_MSG","uid":27897180,"uname":"\u5c0f\u5c0f\u7684\u5c0f\u5154\u56e2\u513f","data":{"uid":27897180,"uname":"\u5c0f\u5c0f\u7684\u5c0f\u5154\u56e2\u513f","operator":1}}
                            String who = "";
                            switch (object.getJSONObject("data").getString("operator")){
                                case "1":{ who = "房管";break; }
                                case "2":{ who = "房主";break; }
                                default:{break; }
                            }
                            String block_msg =  "禁言" + " | " + getFormat()+ " | "+"[个人] 直播间ID："+ConfInfo.barrage.getRoomid() +" "+object.getString("uname")+" 被"+who+"禁言" +"  UID："+object.getString("uid")+"  昵称："+object.getString("uname");
                            ConfInfo.putShowUtil.PutDMUtil(block_msg,ColorUtil.toColorFromString("ea9336"));
                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), block_msg,ConfInfo.barrage.getRoomid());//输出到弹幕日志
                            LogUtil.putLogRoomBlock(object.toString());
                            LogUtil.putLogRoomBlock(block_msg);
                            break;
                        }

                        // 房间关闭禁言
                        case "ROOM_SILENT_OFF": {
                            // {"data":[],"cmd":"ROOM_SILENT_OFF","roomid":7471685}
                            String XX="解禁" + " | " + getFormat() + " | " + "[解禁] 直播间ID：" + ConfInfo.barrage.getRoomid();
                            ConfInfo.putShowUtil.PutDMUtil(XX,ColorUtil.toColorFromString("79B48E"));
                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), XX,ConfInfo.barrage.getRoomid());//输出到弹幕日志
                            LogUtil.putLogRoomSilent(object.toString());
                            LogUtil.putLogRoomSilent(XX);
                            break;
                        }

                        // 房间打开禁言
                        case "ROOM_SILENT_ON": {
                            //{"cmd":"ROOM_SILENT_ON","data":{"type":"level","level":1,"second":1549176518},"roomid":9938182} 等级禁言
                            //{"cmd":"ROOM_SILENT_ON","data":{"type":"member","level":1,"second":1549176544},"roomid":9938182} 全局禁言
                            //{"data":{"type":"level","level":1,"second":1600071138},"cmd":"ROOM_SILENT_ON"}
                            String block_msg="";
                            switch (object.getJSONObject("data").getString("type")){
                                case "level":{
                                    block_msg =  "禁言" + " | " + getFormat()+" | "+"[等级] 直播间ID："+ConfInfo.barrage.getRoomid()+" 对用户等级 UL."+object.getJSONObject("data").getString("level")+"以下的用户开启了禁言,到" +( object.getJSONObject("data").getString("second").equals("-1")?"本次直播结束":TimeUtil.timeStamp2Date(object.getJSONObject("data").getString("second"), null));
                                    break;
                                }
                                case "medal":{
                                    block_msg =  "禁言" + " | " + getFormat()+" | "+"[勋章] 直播间ID："+ConfInfo.barrage.getRoomid()+" 对勋章等级 "+object.getJSONObject("data").getString("level")+"以下的用户开启了禁言,到" +( object.getJSONObject("data").getString("second").equals("-1")?"本次直播结束":TimeUtil.timeStamp2Date(object.getJSONObject("data").getString("second"), null));
                                    break;
                                }
                                case "member":{
                                    block_msg =  "禁言" + " | " + getFormat()+" | "+"[全员] 直播间ID："+ConfInfo.barrage.getRoomid()+" 对所有用户开启了禁言,到" +( object.getJSONObject("data").getString("second").equals("-1")?"本次直播结束":TimeUtil.timeStamp2Date(object.getJSONObject("data").getString("second"), null));
                                    break;
                                }
                                default:{
                                    block_msg =  "禁言" + " | " + getFormat()+" | "+"[全局] 直播间ID："+ConfInfo.barrage.getRoomid()+" 开启了全局禁言,到" +( object.getJSONObject("data").getString("second").equals("-1")?"本次直播结束":TimeUtil.timeStamp2Date(object.getJSONObject("data").getString("second"), null));
                                    break;
                                }
                            }
                            ConfInfo.putShowUtil.PutDMUtil(block_msg,ColorUtil.toColorFromString("ea9336"));
                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), block_msg,ConfInfo.barrage.getRoomid());//输出到弹幕日志
                            LogUtil.putLogRoomSilent(object.toString());
                            LogUtil.putLogRoomSilent(block_msg);
                            break;
                        }

                        // 任命房管
                        case "room_admin_entrance":{
                            //{"cmd":"room_admin_entrance","msg":"系统提示：你已被主播设为房管","uid":184464621}
                            InOutPutUtil.outPut("*************************");
                            InOutPutUtil.outPut(object.toString());
                            String returnData = HttpClient.sendGet(ConfInfo.SearchUserURL +object.getString("uid"),ConfInfo.confData.getCookie());
                            JSONArray jsonArray = JSON.parseObject(returnData).getJSONObject("data").getJSONArray("items");
                            if(jsonArray.size()>0){
                                String uname = jsonArray.getJSONObject(0).getString("uname");
                                putDM = "提示" +" | " + getFormat() +" | " +"恭喜 " + uname + " 出任房管 ";
                            }
                            InOutPutUtil.outPut(putDM);
                            LogUtil.putLogRoomAdminEntrance(object.toString());
                            break;
                        }

                        // 房管变更后全部房管
                        case "ROOM_ADMINS":{
                            //{"cmd":"ROOM_ADMINS","uids":[18169995,1587578,10798725,4531305,23219652,351510111,289007608,184464621]}
                            LogUtil.putlogRoomAdmins(object.toString());
                            break;
                        }

                        // 房间等级（小时榜）
                        case "ROOM_RANK": {
                            JSONObject RoomRankData = object.getJSONObject("data");
                            String rank_desc = RoomRankData.getString("rank_desc");
                            String roomid = RoomRankData.getString("roomid");
                            putTZ = "通知 ： ~ " + "   id:" + roomid + " " + rank_desc;
                            ConfInfo.barrage.setRoomRank(rank_desc);
                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putTZ, ConfInfo.barrage.getRoomid());
                            LogUtil.putLogDatabase("小时榜",object.toString());
                            InOutPutUtil.outPut(rank_desc + " id:" + roomid + "\t");
                            break;
                        }

                        // 进入动画(不展示)
                        case "ENTRY_EFFECT": {
                            InOutPutUtil.outPut("进入动画" + object);
                            LogUtil.putLogDatabase("进入动画",object.toString());
                            break;
                        }

                        // 全局通知消息
                        case "NOTICE_MSG": {
                            //{"cmd":"NOTICE_MSG","full":{"head_icon":"http://i0.hdslb.com/bfs/live/b29add66421580c3e680d784a827202e512a40a0.webp","tail_icon":"http://i0.hdslb.com/bfs/live/822da481fdaba986d738db5d8fd469ffa95a8fa1.webp","head_icon_fa":"http://i0.hdslb.com/bfs/live/49869a52d6225a3e70bbf1f4da63f199a95384b2.png","tail_icon_fa":"http://i0.hdslb.com/bfs/live/38cb2a9f1209b16c0f15162b0b553e3b28d9f16f.png","head_icon_fan":24,"tail_icon_fan":4,"background":"#66A74EFF","color":"#FFFFFFFF","highlight":"#FDFF2FFF","time":20},"half":{"head_icon":"http://i0.hdslb.com/bfs/live/ec9b374caec5bd84898f3780a10189be96b86d4e.png","tail_icon":"","background":"#85B971FF","color":"#FFFFFFFF","highlight":"#FDFF2FFF","time":15},"side":{"head_icon":"","background":"","color":"","highlight":"","border":""},"roomid":21758977,"real_roomid":21758977,"msg_common":"\u003c%快融化的小56%\u003e投喂\u003c%MUA芸汐%\u003e1个小电视飞船，点击前往TA的房间吧！","msg_self":"\u003c%快融化的小56%\u003e投喂\u003c%MUA芸汐%\u003e1个小电视飞船，快来围观吧！","link_url":"https://live.bilibili.com/21758977?from=28003\u0026extra_jump_from=28003\u0026live_lottery_type=1\u0026broadcast_type=1","msg_type":2,"shield_uid":-1,"business_id":"25"}
                            String msg_common = object.getString("msg_common");
                            String real_roomid = object.getString("real_roomid");
                            minorNotice = "id:" + real_roomid + " " + msg_common;
                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putTZ, ConfInfo.barrage.getRoomid());
                            InOutPutUtil.outPut(putTZ);
                            break;
                        }

                        // 系统消息
                        case "SYS_MSG": {
                            String msg = object.getString("msg");
                            String real_roomid = object.getString("real_roomid");
                            minorNotice = "通知 ： ~ " + "id:" + real_roomid + " " + msg;
                            // TODO 低保
                            //if (real_roomid != null) {
                            //        new HFJ_Fun("出现低保：" + real_roomid);
                            //}
                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putTZ, ConfInfo.barrage.getRoomid());
                            InOutPutUtil.outPut(msg + "id:" + real_roomid + "\t");
                            break;
                        }


                        // 小电视开始
                        case "TV_START": {
                            //{"data":{"msg":{"msg":"晚枫终有叶落时:? 送给:? 小小的小兔团儿:? 1个小电视飞船，点击前往TA的房间去抽奖吧","styleType":2,"real_roomid":7471685,"msg_common":"全区广播：<%晚枫终有叶落时%> 送给<% 小小的小兔团儿%> 1个小电视飞船，点击前往TA的房间去抽奖吧","rnd":1537962336,"msg_self":"全区广播：<%晚枫终有叶落时%> 送给<% 小小的小兔团儿%> 1个小电视飞船，快来抽奖吧","cmd":"SYS_MSG","msg_text":"晚枫终有叶落时:? 送给:? 小小的小兔团儿:? 1个小电视飞船，点击前往TA的房间去抽奖吧","rep":1,"url":"http://live.bilibili.com/7471685","roomid":7471685,"broadcast_type":1},"asset_tips_pic":"http://s1.hdslb.com/bfs/live/ac43b069bec53d303a9a1e0c4e90ccd1213d1b6b.png","raffleId":142504,"asset_animation_pic":"http://i0.hdslb.com/bfs/live/746a8db0702740ec63106581825667ae525bb11a.gif","dtime":180,"title":"小电视飞船抽奖","type":"small_tv","from_user":{"face":"http://i1.hdslb.com/bfs/face/7c663fae141aa51ceb1ae6f624ed3dc636911cd9.jpg","uname":"晚枫终有叶落时"},"time_wait":120,"from":"晚枫终有叶落时","id":"142504","time":180,"max_time":180,"payflow_id":"1537966810111900001"},"cmd":"TV_START"}

                            if(ConfInfo.systemState.isGreetMaster&&!ConfInfo.systemState.isAlreadyOnGreetMaster){
                                ConfInfo.systemState.isGreetMaster = false;
                                ConfInfo.systemState.isAlreadyOnGreetMaster = true;
                                InOutPutUtil.outPut("----------------防止刷屏 关闭老爷----------------");
                            }

                            JSONObject giftData = object.getJSONObject("data");
                            String from = giftData.getString("from");
                            String id = giftData.getString("id");
                            putTZ = "通知 ： ~ " + "小电视 开始 来自：" + from + " Id:" + id;
                            putDM = "通知 | " + "小电视 开始 来自：" + from + " Id:" + id;
                            ConfInfo.inOperationRadioGift.add(id);
                            LogUtil.putLogGiftRecord(object.toString() + "\n");
                            break;
                        }
                        // 小电视结束
                        case "TV_END": {
                            JSONObject giftData = object.getJSONObject("data");
                            String from = giftData.getString("from");
                            String id = giftData.getString("id");
                            putTZ = "通知 ： ~ " + "小电视 结束 来自：" + from + " Id:" + id;
                            putDM = "通知 |" + "小电视 结束 来自：" + from + " Id:" + id;

                            ConfInfo.inOperationRadioGift.remove(id);
                            if(ConfInfo.inOperationRadioGift.size()==0&&ConfInfo.systemState.isAlreadyOnGreetMaster){
                                ConfInfo.systemState.isGreetMaster = true;
                                ConfInfo.systemState.isAlreadyOnGreetMaster = false;
                                InOutPutUtil.outPut("----------------防止刷屏 启动老爷----------------");
                            }

                            if (ConfInfo.systemState.isThank) new SendBarrage_Inlet("诶呀，发现了 "+from+" 送来的小电视，感谢喵~",1);
                            LogUtil.putLogGiftRecord(object.toString() + "\n");

                            break;
                        }

                        // 全局礼物开始
                        case "RAFFLE_START": {
                            //{"data":{"msg":{"msg":"晚枫终有叶落时:? 送给:? 小小的小兔团儿:? 1个摩天大楼，点击前往TA的房间去抽奖吧","styleType":2,"real_roomid":7471685,"msg_common":"娱乐区广播: <%晚枫终有叶落时%> 送给<% 小小的小兔团儿%> 1个摩天大楼，点击前往TA的房间去抽奖吧","rnd":1537962336,"msg_self":"娱乐区广播: <%晚枫终有叶落时%> 送给<% 小小的小兔团儿%> 1个摩天大楼，快来抽奖吧","cmd":"SYS_MSG","msg_text":"晚枫终有叶落时:? 送给:? 小小的小兔团儿:? 1个摩天大楼，点击前往TA的房间去抽奖吧","rep":1,"url":"http://live.bilibili.com/7471685","roomid":7471685,"broadcast_type":1},"asset_tips_pic":"http://s1.hdslb.com/bfs/live/380bcd708da496d75737c68930965dd67b82879d.png","raffleId":142536,"asset_animation_pic":"http://i0.hdslb.com/bfs/live/7e47e9cfb744acd0319a4480e681258ce3a611fe.gif","dtime":120,"title":"摩天大楼抽奖","type":"GIFT_20003","from_user":{"face":"http://i1.hdslb.com/bfs/face/7c663fae141aa51ceb1ae6f624ed3dc636911cd9.jpg","uname":"晚枫终有叶落时"},"time_wait":60,"from":"晚枫终有叶落时","id":"142536","time":120,"max_time":120,"payflow_id":"1537968322111900001"},"cmd":"RAFFLE_START"}
                            //{"data":{"raffleId":"142536","fromGiftId":20003,"uname":"墨竹清音","sname":"晚枫终有叶落时","giftName":"23333x银瓜子","from":"晚枫终有叶落时","id":"142536","type":"GIFT_20003","win":{"msg":"恭喜 <%墨竹清音%> 获得大奖 <%23333x银瓜子%>, 感谢 <%晚枫终有叶落时%> 的赠送","giftId":"silver","face":"http://i2.hdslb.com/bfs/face/1dfec3945b9eb47f334ca87095913cdefb83b6cc.jpg","giftImage":"http://s1.hdslb.com/bfs/live/00d768b444f1e1197312e57531325cde66bf0556.png","uname":"墨竹清音","giftName":"银瓜子","giftNum":23333},"fromFace":"http://i1.hdslb.com/bfs/face/7c663fae141aa51ceb1ae6f624ed3dc636911cd9.jpg","mobileTips":"恭喜 墨竹清音 获得23333x银瓜子"},"cmd":"RAFFLE_END"}
                            if(ConfInfo.systemState.isGreetMaster&&!ConfInfo.systemState.isAlreadyOnGreetMaster){
                                ConfInfo.systemState.isGreetMaster = false;
                                ConfInfo.systemState.isAlreadyOnGreetMaster = true;
                                InOutPutUtil.outPut("----------------防止刷屏 关闭老爷----------------");
                            }

                            JSONObject giftData = object.getJSONObject("data");
                            String from = giftData.getString("from");
                            String id = giftData.getString("id");
//                          String title = giftData.getString("title");
                            String type = giftData.getString("type");
                            String radioGiftName = "";
                            switch (type) {
                                case "GIFT_20003": {
                                    radioGiftName = "摩天大楼";
                                    break;
                                }
                                case "GIFT_30024": {
                                    radioGiftName = "月色真美";

                                    break;
                                }
                                default: {
                                    LogUtil.putLogUnknownGift(object.toString());
                                    break;
                                }
                            }
                            putTZ = "通知 ： ~ " + radioGiftName+" 开始 来自：" + from + " Id:" + id;
                            putDM = "通知 | " + radioGiftName+" 开始 来自：" + from + " Id:" + id;
                            ConfInfo.inOperationRadioGift.add(id);
                            LogUtil.putLogGiftRecord(object.toString() + "\n");
                            break;
                        }
                        // 全局礼物结束
                        case "RAFFLE_END": {
                            JSONObject giftData = object.getJSONObject("data");
                            String from = giftData.getString("from");
                            String id = giftData.getString("id");
//                          String title = giftData.getString("title");
                            String type = giftData.getString("type");
                            String radioGiftName = "";
                            switch (type) {
                                case "GIFT_20003": {
                                    radioGiftName = "摩天大楼";
                                    break;
                                }
                                case "GIFT_30024": {
                                    radioGiftName = "月色真美";
                                    break;
                                }
                                default: {
                                    LogUtil.putLogUnknownGift(object.toString());
                                    break;
                                }
                            }
                            putTZ = "通知 ： ~ " +radioGiftName+ " 结束 来自：" + from + " Id:" + id;
                            putDM = "通知 | " +radioGiftName+ " 结束 来自：" + from + " Id:" + id;
                            if (ConfInfo.systemState.isThank) new SendBarrage_Inlet("哇，发现大佬 " + from + "送的 "+radioGiftName+" 喵~",1);

                            ConfInfo.inOperationRadioGift.remove(id);
                            if(ConfInfo.inOperationRadioGift.size()==0&&ConfInfo.systemState.isAlreadyOnGreetMaster){
                                ConfInfo.systemState.isGreetMaster = true;
                                ConfInfo.systemState.isAlreadyOnGreetMaster = false;
                                InOutPutUtil.outPut("----------------防止刷屏 启动老爷----------------");
                            }
                            LogUtil.putLogGiftRecord(object.toString() + "\n");
                            break;
                        }

                        // 礼物 节奏风暴
                        case "SPECIAL_GIFT": {
                            JSONObject giftData = object.getJSONObject("data");
                            JSONObject gift39 = giftData.getJSONObject("39");
                            String giftaction = gift39.getString("action");
                            String giftid = gift39.getString("id");

                            switch (giftaction) {
                                case "start": {
                                    String giftcontent = gift39.getString("content");

                                    if(ConfInfo.systemState.isGreetMaster&&!ConfInfo.systemState.isAlreadyOnGreetMaster){
                                        ConfInfo.systemState.isGreetMaster = false;
                                        ConfInfo.systemState.isAlreadyOnGreetMaster = true;
                                        InOutPutUtil.outPut("----------------防止刷屏 关闭老爷----------------");
                                    }
                                    ConfInfo.inOperationRadioGift.add(giftid);
                                    putTZ = "通知 ： ~ " + "节奏风暴 开始 id:" + giftid + "内容：" + giftcontent;
                                    putDM = "通知 ： ~ " + "节奏风暴 开始 id:" + giftid + "内容：" + giftcontent;
                                    DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putDM, ConfInfo.barrage.getRoomid());
                                    break;
                                }
                                case "end": {
                                    if (ConfInfo.systemState.isThank) new SendBarrage_Inlet("哇，发现大佬 " + giftid + "送的 节奏风暴 喵~",1);
                                    LogUtil.putLogGiftRecord(object.toString() + "\n");
                                    ConfInfo.inOperationRadioGift.remove(giftid);
                                    if(ConfInfo.inOperationRadioGift.size()==0&&ConfInfo.systemState.isAlreadyOnGreetMaster){
                                        ConfInfo.systemState.isGreetMaster = true;
                                        ConfInfo.systemState.isAlreadyOnGreetMaster = false;
                                        InOutPutUtil.outPut("----------------防止刷屏 启动老爷----------------");
                                    }
                                    putTZ = "通知 ： ~ " + "节奏风暴 结束 id:" + giftid;
                                    putDM = "通知 ： ~ " + "节奏风暴 结束 id:" + giftid;
                                    DmLogUtil.putDmLog(getFormatDay(), getFormatHour(), putDM, ConfInfo.barrage.getRoomid());
                                    break;
                                }

                            }
                            LogUtil.putLogDatabase("SPECIAL_GIFT",object.toString());
                            LogUtil.putLogGiftRecord(object.toString() + "\n");
                            InOutPutUtil.outPut(putTZ);
                            break;
                        }
                        // TODO 小玩意列表 不知道干什么的
                        //{"data":{"widget_list":{"15":{"band_id":36,"sub_key":"","type":1,"sub_data":"%7B%22time%22%3A1609831800%2C%22has_winner%22%3Atrue%2C%22anchor_uid%22%3A1700384160%2C%22anchor_name%22%3A%22NJ%E9%98%BF%E8%A5%BF%22%2C%22anchor_face%22%3A%22http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Fface%2F5c11ea25192a8a427b7788051166c02cc43c662e.jpg%22%2C%22user_uid%22%3A320408778%2C%22user_name%22%3A%22%E7%8F%8D%E8%A5%BF_%E5%B0%8F%E7%81%AB%E9%94%85%22%2C%22user_face%22%3A%22http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Fface%2F30dc913acee0e6d2156dd925889af3db41a288aa.jpg%22%7D"}},"timestamp":1609831807},"cmd":"WIDGET_BANNER"}
                        case "WIDGET_BANNER":

                        /* PK
                        {"pk_id":131652,"data":{"uid":90708755,"face":"http://i0.hdslb.com/bfs/face/be74581c904733dd2722246fda18c2a41737600f.jpg","escape_all_time":10,"uname":"梨落大大","match_id":3647345,"is_portrait":false,"init_id":7471685,"escape_time":10},"pk_status":100,"cmd":"PK_MATCH","roomid":7471685}
                        {"pk_id":131652,"data":{"pk_start_time":1537084277,"match_id":3647345,"count_down":5,"pk_pre_time":1537084272,"end_time":1537084697,"pk_topic":"唱首中文歌","pk_end_time":1537084577,"init_id":7471685},"pk_status":200,"cmd":"PK_PRE"}
                        {"pk_id":131628,"data":{"match_id":7471685,"pk_topic":"做一项别人不会的绝技","init_id":11365011},"pk_status":300,"cmd":"PK_START"}
                        {"pk_id":131628,"data":{"uid":18169995,"match_votes":5,"init_votes":0,"user_votes":5},"pk_status":300,"cmd":"PK_PROCESS","roomid":7471685}
                        {"pk_id":131628,"data":{"punish_topic":"惩罚：模仿面筋哥","match_id":7471685,"init_id":11365011},"pk_status":400,"cmd":"PK_END"}
                        {"pk_id":131628,"data":{"match_info":{"is_winner":true,"uid":27897180,"badge":{"position":0,"url":"","desc":""},"face":"http://i0.hdslb.com/bfs/face/6232d18dff2835591c3cf867ef79acef3a6e74e8.jpg","face_frame":"","uname":"小小的小兔团儿","vip_type":0,"match_id":7471685,"votes":5,"exp":{"master_level":{"color":5805790,"level":15},"color":9868950,"user_level":8},"vip":{"vip":0,"svip":0}},"init_info":{"is_winner":false,"uid":330655469,"face":"http://i1.hdslb.com/bfs/face/1cee3f42eb4d6268e3c368f9506dc4eda230c532.jpg","uname":"圆啵叽","votes":0,"init_id":11365011},"pk_id":131628,"punish_topic":"惩罚：模仿面筋哥","best_user":{"uid":18169995,"privilege_type":0,"badge":{"position":0,"url":"","desc":""},"face":"http://i0.hdslb.com/bfs/face/ecd8064598c534ec311287dd9c0b17dc8f3bb4f9.jpg","face_frame":"","uname":"mxnter","vip_type":0,"exp":{"master_level":{"color":6406234,"level":10},"color":5805790,"user_level":28},"vip":{"vip":0,"svip":0}}},"pk_status":400,"cmd":"PK_SETTLE"}
                        */
                        case "PK_PROCESS":
                        case "PK_START":
                        case "PK_END":
                        case "PK_SETTLE":
                        case "PK_MATCH":
                        case "PK_PRE":
                        case "PK_MIC_END":
                        case "PK_CLICK_AGAIN":
                        case "PANEL":
                            //{"data":{"is_outer":0,"note":"娱乐 第26名","jump_url":"https://live.bilibili.com/p/html/live-app-rankcurrent/index.html?is_live_half_webview=1&hybrid_half_ui=1,5,85p,70p,FFE293,0,30,100,10;2,2,320,100p,FFE293,0,30,100,0;4,2,320,100p,FFE293,0,30,100,0;6,5,65p,60p,FFE293,0,30,100,10;5,5,55p,60p,FFE293,0,30,100,10;3,5,85p,70p,FFE293,0,30,100,10;7,5,65p,60p,FFE293,0,30,100,10;&anchor_uid=18908454&is_new_rank_container=1&area_v2_id=21&area_v2_parent_id=1&rank_type=master_realtime_area_hour&area_hour=1","operate":1,"status_type":1,"icon":"https://i0.hdslb.com/bfs/activity-plat/static/20200616/b5e210ef68e55c042f407870de28894b/8cvyZuYhx9d.png","weight":8,"biz_id":5,"title":"小时榜"},"cmd":"PANEL"}
                        case "ANCHOR_LOT_START":
                            //{"data":{"room_id":50821,"asset_icon":"https://i0.hdslb.com/bfs/live/992c2ccf88d3ea99620fb3a75e672e0abe850e9c.png","send_gift_ensure":0,"goaway_time":180,"lot_status":0,"gift_num":1,"danmu":"我就是天选之人","id":490193,"max_time":600,"current_time":1600092024,"gift_price":100,"require_type":2,"cur_gift_num":0,"join_type":1,"award_name":"bilibili键盘","show_panel":1,"award_num":1,"gift_id":20004,"goods_id":15,"url":"https://live.bilibili.com/p/html/live-lottery/anchor-join.html?is_live_half_webview=1&hybrid_biz=live-lottery-anchor&hybrid_half_ui=1,5,100p,100p,000000,0,30,0,0,1;2,5,100p,100p,000000,0,30,0,0,1;3,5,100p,100p,000000,0,30,0,0,1;4,5,100p,100p,000000,0,30,0,0,1;5,5,100p,100p,000000,0,30,0,0,1;6,5,100p,100p,000000,0,30,0,0,1;7,5,100p,100p,000000,0,30,0,0,1;8,5,100p,100p,000000,0,30,0,0,1","is_broadcast":1,"require_text":"当前主播粉丝勋章至少1级","web_url":"https://live.bilibili.com/p/html/live-lottery/anchor-join.html","gift_name":"吃瓜","time":599,"award_image":"","require_value":1,"status":1},"cmd":"ANCHOR_LOT_START"}
                        case "ACTIVITY_BANNER_UPDATE_V2":
                            //{"data":{"cover":"","add_banner":0,"closeable":1,"jump_url":"https://live.bilibili.com/p/html/live-app-rankcurrent/index.html?is_live_half_webview=1&hybrid_half_ui=1,5,85p,70p,FFE293,0,30,100,10;2,2,320,100p,FFE293,0,30,100,0;4,2,320,100p,FFE293,0,30,100,0;6,5,65p,60p,FFE293,0,30,100,10;5,5,55p,60p,FFE293,0,30,100,10;3,5,85p,70p,FFE293,0,30,100,10;7,5,65p,60p,FFE293,0,30,100,10;&anchor_uid=1774758&is_new_rank_container=1&area_v2_id=192&area_v2_parent_id=5&rank_type=master_realtime_area_hour&area_hour=1","banner_type":4,"background":"https://i0.hdslb.com/bfs/activity-plat/static/20190904/b5e210ef68e55c042f407870de28894b/eb7Wb-Dlel.png","title_color":"#8B5817","weight":18,"id":378,"title":">200名"},"cmd":"ACTIVITY_BANNER_UPDATE_V2"}
                        case "ROOM_BANNER":
                            //{"data":{"task_status":1,"is_block":false,"sort_list":["pk_info","task_info"],"pk_status":1,"rank_info":[],"task_info":{"task_status":1,"act_id":20,"task_info":{"period":0,"level":8,"total_level":8,"assiant_info":{"anchor":{"face":"http://i1.hdslb.com/bfs/face/a86d651acbb84ee8ce21869a154e2ebc93e8155a.jpg","uname":"李晓霾"},"assist":{"face":"http://i1.hdslb.com/bfs/face/3706ced629bfb2b19cf3ce9ebb22d163f624bcec.jpg","uname":"贩梦的梅"}},"list":[{"total_num":75,"current_num":75,"task_type":0},{"total_num":400000,"current_num":400000,"task_type":1}],"status":1},"config_info":{"level_font_color":"#DBA95F","bg_color":"#603B8E","progress_font_color":"#3C326F","yes_progress_color":"#FCD089","task_font_color":"#FEE1AB","list":[{"desc":"小心心任务"},{"desc":"金瓜子任务"}],"url":"https://live.bilibili.com/activity/live-activity-battle/index.html?room_id=50821#/task","no_progress_color":"#FFFFFF"}},"rank_status":0,"pk_info":{"uid":18908454,"score":48118,"season_name":"大乱斗S1赛季","rank":197,"id":26,"pk":{"level2_img":"https://i0.hdslb.com/bfs/live/1f8c2a959f92592407514a1afeb705ddc55429cd.png","level1_img":"https://i0.hdslb.com/bfs/live/62bb14bf475ecc4f37edebbd5b7ee1b981b33d61.png","level1_id":3,"top_rank":0,"level1_name":"白银斗士","level2_count":2},"has_battled":1,"next_rank_need_score":1882,"status":1}},"cmd":"ROOM_BANNER","id":7,"type":1}
                        case "ONLINERANK":
                            //{"data":{"list":[{"uid":35657283,"face":"http://i0.hdslb.com/bfs/face/736ba6ccffe7c29e27136d29a75ad0d901669ce7.jpg","guard_level":0},{"uid":34704231,"face":"http://i2.hdslb.com/bfs/face/29054c82cc845a1bb7c62f48cc06892947288792.jpg","guard_level":0},{"uid":27286145,"face":"http://i1.hdslb.com/bfs/face/6a99401550fd28496bf219c23a52fcaa3d9df5a3.jpg","guard_level":0},{"uid":14108325,"face":"http://i0.hdslb.com/bfs/face/f713396e4b0936b2e3d7871b34b77666a6a703eb.jpg","guard_level":0},{"uid":8746574,"face":"http://i2.hdslb.com/bfs/face/1548cea8821cc2f7b5377ea11cafca4fbf961bfc.jpg","guard_level":3}],"rank_type":"gold-rank"},"cmd":"ONLINERANK"}
                        case "CHANGE_ROOM_INFO":// 改变房间信息
                            // {"background":"http://static.hdslb.com/live-static/images/bg/4.jpg","cmd":"CHANGE_ROOM_INFO"}
                        case "ROOM_REAL_TIME_MESSAGE_UPDATE":// 房间实时信息更新
//                        {"data":{"red_notice":-1,"roomid":21144080,"fans":499410},"cmd":"ROOM_REAL_TIME_MESSAGE_UPDATE"}
                        case "ACTIVITY_EVENT":// 活动事件
                            // {"data":{"limit":500000,"progress":74380,"keyword":"bls_winter_2018","type":"charge"},"cmd":"ACTIVITY_EVENT"}
                        case "WISH_BOTTLE":// 许愿瓶
                            // {"data":{"wish":{"uid":50621949,"wish_progress":25098,"type_id":3,"wish_limit":99999,"ctime":"2018-01-28 09:28:21","id":8427,"count_map":[1,10,100],"type":1,"content":"一个B克拉得老白勋章","status":1},"action":"update","id":8427},"cmd":"WISH_BOTTLE"}
                        case "PK_AGAIN": {
                            LogUtil.putLogKnownUntreated(object.toString());
                            break;
                        }

                        default: {
                            putDM = "";
                            LogUtil.putLogUnknown(object.toString());
                        }
                    }

                    if (!putDM.equals("")) {
                        ConfInfo.ChargeBarrageList.add(putDM);
                        synchronized (ConfInfo.GBT) { ConfInfo.GBT.notify(); }
                    }
                    if (!putTZ.equals("")) ConfInfo.putShowUtil.PutTZUtil(putTZ);
                    if (!minorNotice.equals("")) ConfInfo.putShowUtil.minorNotice(minorNotice);


                    ConfInfo.ParsingBarrageList.remove(0);


                } else {
                    synchronized (ConfInfo.PBT) {
                        try {
                            ConfInfo.PBT.wait();
                            InOutPutUtil.outPut("-----------------------解析弹幕数据进入休眠-----------------------");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }catch (Exception e){
                System.out.println(ConfInfo.ParsingBarrageList);
                ConfInfo.ParsingBarrageList.remove(0);
            }



        }


    }
}
