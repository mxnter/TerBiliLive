package com.TerBiliLive.Thr;

import com.TerBiliLive.Function.HFJ_Fun;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.TerBiliLive.getInfo;
import com.TerBiliLive.TerBiliLive.SendPost;
import com.TerBiliLive.Utiliy.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.regex.Pattern;

import static com.TerBiliLive.Ui.TerBiliLive_Control_Ui.Control_UiT_RoomId;
import static com.TerBiliLive.Ui.TerBiliLive_Control_Ui.Control_UiT_RoomRank;
import static com.TerBiliLive.Ui.TerBiliLive_Control_Ui.Control_UiT_State;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormat;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatHour;

public class ChargeNoticeS_Thr {



    public boolean AYO= true;
    String Parameter= "" ;
    SendPost SP = new SendPost();
    DmUtil DU = new DmUtil();
    String putDMJY =null;


    private Socket socket;
    private boolean keepRunning = true;
    private boolean isReConn = true;
    private String roomID;



    private getInfo client;

    public void start(String roomID, boolean isReConnect){
        this.roomID = roomID;
        isReConn = isReConnect;
        client = new getInfo();
        socket = client.connect(this.roomID);
        if (socket != null) {
            handle_data_loop hdp=  new handle_data_loop();
            try {
                hdp.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hdp.start();
        }
    }

    public void stop(){
        keepRunning = false;
        client.disconnect(socket);
        String uid =  ConfInfo.liveInfo.getUid();
        String name =  ConfInfo.liveInfo.getUname();
        ConfInfo.liveInfo=null;
        String putDM =  "系统 ："+getFormat()+" - "+"断开连接" +" 真实直播间ID："+roomID +"  UID："+uid+"  昵称："+name;
        ConfInfo.putShowUtil.PutDMUtil(putDM);
       // new PutDMUtil(putDM);
        Control_UiT_State.setText("已断开连接" );
        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
        System.out.println("断开连接" +" 真实直播间ID："+roomID );
    }

    private class handle_data_loop extends Thread {
        DataInputStream input = null;


        public void run(){
            if (socket != null){
                int bufferSize = 10 * 1024;
                try {
//                    LiveInfo xx=new LiveInfo(Control_UiT_RoomId.getText());
//                    DMJ_UiT_Text.append("房间信息："+xx.toString());
                    bufferSize = socket.getReceiveBufferSize();
                    String uid =  ConfInfo.liveInfo.getUid();
                    String name =  ConfInfo.liveInfo.getUname();
                    ConfInfo.putShowUtil.PutDMUtil("系统 ："+getFormat()+" ! "+"连接成功 " +"真实直播间ID："+roomID +"  UID："+uid+"  昵称："+name);

                    DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),"连接成功" +"真实直播间ID："+roomID ,Control_UiT_RoomId.getText());
                    System.out.println("连接成功" +"真实直播间ID："+roomID );
                    ConfInfo.isReConnSum=0;
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                byte[] ret = new byte[bufferSize];
                while (keepRunning){
                    try {
                        input = new DataInputStream(socket.getInputStream());
                        int retLength = input.read(ret);
                        if (retLength > 0 && keepRunning) {
                            byte[] recvData = new byte[retLength];
                            System.arraycopy(ret, 0, recvData, 0, retLength);
                            analyzeData(recvData);
                        }
                    }catch (Exception e){
                        if (isReConn && keepRunning ) {
                            ConfInfo.putShowUtil.PutDMUtil("系统 ："+getFormat()+" ! "+ConfInfo.isReConnSum+"-自动重连" +" 真实直播间ID："+roomID   );

                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),ConfInfo.isReConnSum+"-自动重连" +" 真实直播间ID："+roomID   ,Control_UiT_RoomId.getText());
                            System.out.println("自动重连" +" 真实直播间ID："+roomID );

                            if(ConfInfo.isReConnSum>=10){
                                keepRunning = false;
                            }
                            (new ChargeNoticeS_Thr()).start(roomID, true);

                        }
                        keepRunning = false;
                        e.printStackTrace();
                    }
                }
//                DMJ_UiT_Text.setCaretPosition(DMJ_UiT_Text.getText().length());
            }
        }

        private void analyzeData(byte[] data){
            String putDM = "";
            String putTZ = "";
            int dataLength = data.length;
            if (dataLength < 16){
                System.out.println("错误的数据");
            }else {
                DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(data));
                try {
                    int msgLength = inputStream.readInt();
                    if (msgLength < 16){
                        System.out.println("可能需要扩大缓冲区大小");
                    }else if(msgLength > 16 && msgLength == dataLength){
                        // 其实是两个char
                        inputStream.readInt();
                        int action = inputStream.readInt() - 1;
                        // 直播间在线用户数目
                        if (action == 2){
                            inputStream.readInt();
                            int userCount = inputStream.readInt();
//                            System.out.println("在线人数：" + userCount);
                            Control_UiT_State.setText("人气：" + userCount);
//                            ConfInfo.putDMUtil.PutDMUtil("在线人数：" + userCount);
                            System.out.println("人气：" + userCount);
                        }else if (action == 4){
                            inputStream.readInt();
                            int msgBodyLength = dataLength - 16;
                            byte[] msgBody = new byte[msgBodyLength];
                            if (inputStream.read(msgBody) == msgBodyLength){
                                String jsonStr = new String(msgBody, "utf-8");
                                System.out.println(jsonStr);
                                JSONObject object = JSON.parseObject(jsonStr);
                                String msgType = object.getString("cmd");

                                switch (msgType){
                                    case "DANMU_MSG":{
                                        JSONArray array = object.getJSONArray("info").getJSONArray(2);
                                        JSONArray Date = object.getJSONArray("info").getJSONArray(0);
//                                    int uid = array.getInteger(0);

                                        String putDM_text = object.getJSONArray("info").getString(1);
                                        String putDM_isadmin = "";
                                        String putDM_nickname = array.getString(1);
                                        String putDM_timeline = "";
                                        String putDM_vip = "";
                                        String putDM_medal="";
                                        String putDM_GUARD="";
                                        try {
                                            putDM_medal ="【" + object.getJSONArray("info").getJSONArray(3).getString(1)+object.getJSONArray("info").getJSONArray(3).getString(0)+"】 "  ;

                                        }catch (Exception e){
                                            putDM_medal="";
                                        }
                                        String putDM_user_level =" [" + object.getJSONArray("info").getJSONArray(4).getString(0) + "] " ;

                                        putDM_isadmin =(array.getString(2).equals("1"))?array.getString(0).equals(ConfInfo.getLiveRoomUserInfo.getRoomUseruid())?"<房主> ":"<房管> ":"";

//                                        switch (array.getString(2)){
//                                            case "3":
//                                                putDM_GUARD="$舰长$";break;
//                                            case "2":
//                                                putDM_GUARD="提督";break;
//                                            case "1":
//                                                putDM_GUARD="总督";break;
//                                            default:
//                                                putDM_GUARD="";break;
//                                        }

                                        putDM_timeline = TimeUtil.timeStamp2Date(Date.getString(4),null);

                                        if (array.getString(3).equals("1")) {
                                            putDM_vip = "普通老爷 ";
                                        }
                                        if (array.getString(4).equals("1")) {
                                            putDM_vip = "年费老爷 ";
                                        }
                                        putDM ="弹幕 ："+ putDM_timeline + " - " +putDM_GUARD+" "+putDM_vip + " " + putDM_isadmin +  putDM_medal + putDM_user_level + putDM_nickname + " ：" + putDM_text;

                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
                                        if(array.getString(2).equals("1")||putDM_nickname.equals("mxnter")){
//                                            if(putDM_text.equals("# 版本信息")) {new HFJ_Fun("当前版本："+ConfInfo.terBiliLive_ui.Version);}
//                                            if(putDM_text.equals("# 关闭老爷")){ ConfInfo.terBiliLive_control_ui.Reply_Master.setSelected(false);ConfInfo.terBiliLive_control_ui.Reply_MasterRadioGift.setSelected(false);new HFJ_Fun("关闭老爷 成功");}
//                                            if(putDM_text.equals("# 开启老爷")){ ConfInfo.terBiliLive_control_ui.Reply_Master.setSelected(true);ConfInfo.terBiliLive_control_ui.Reply_MasterRadioGift.setSelected(true); new HFJ_Fun("开启老爷 成功");}
//                                            if(putDM_text.equals("# 开启感谢")){ ConfInfo.terBiliLive_control_ui.Control_UiB_OpenThinks.doClick();new HFJ_Fun("开启感谢 成功");}
//                                            if(putDM_text.equals("# 关闭感谢")){ ConfInfo.terBiliLive_control_ui.Control_UiB_ClaseThinks.doClick();new HFJ_Fun("关闭感谢 成功");}
//                                            if(putDM_text.equals("# 关闭30")){ ConfInfo.terBiliLive_control_ui.Reply_30.setSelected(false);new HFJ_Fun("关闭30 成功");}
//                                            if(putDM_text.equals("# 开启30")){ ConfInfo.terBiliLive_control_ui.Reply_30.setSelected(true);new HFJ_Fun("开启30 成功");}
//                                            if(putDM_text.equals("# 开启舰长")){ ConfInfo.terBiliLive_control_ui.Reply_Guard.setSelected(true);new HFJ_Fun("开启舰长 成功");}
//                                            if(putDM_text.equals("# 关闭舰长")){ ConfInfo.terBiliLive_control_ui.Reply_Guard.setSelected(false);new HFJ_Fun("关闭舰长 成功");}

                                            switch (putDM_text){
//                                                case "# 版本信息":{new HFJ_Fun("当前版本："+ConfInfo.terBiliLive_ui.Version); }
                                                case "# 关闭老爷":{ConfInfo.terBiliLive_control_ui.Reply_Master.setSelected(false);ConfInfo.terBiliLive_control_ui.Reply_MasterRadioGift.setSelected(false);new HFJ_Fun("关闭老爷 成功");break;}
                                                case "# 开启老爷":{ ConfInfo.terBiliLive_control_ui.Reply_Master.setSelected(true);ConfInfo.terBiliLive_control_ui.Reply_MasterRadioGift.setSelected(true); new HFJ_Fun("开启老爷 成功");break;}
                                                case "# 开启感谢":{ ConfInfo.terBiliLive_control_ui.Control_UiB_OpenThinks.doClick();new HFJ_Fun("开启感谢 成功");break;}
                                                case "# 关闭感谢":{ConfInfo.terBiliLive_control_ui.Control_UiB_ClaseThinks.doClick();new HFJ_Fun("关闭感谢 成功");break;}
                                                case "# 关闭30":{ ConfInfo.terBiliLive_control_ui.Reply_30.setSelected(false);new HFJ_Fun("关闭30 成功");break;}
                                                case "# 开启30":{ ConfInfo.terBiliLive_control_ui.Reply_30.setSelected(true);new HFJ_Fun("开启30 成功");break;}
                                                case "# 开启舰长":{ ConfInfo.terBiliLive_control_ui.Reply_Guard.setSelected(true);new HFJ_Fun("开启舰长 成功");break;}
                                                case "# 关闭舰长":{ ConfInfo.terBiliLive_control_ui.Reply_Guard.setSelected(false);new HFJ_Fun("关闭舰长 成功");break;}
                                                case "# 开启状态":{ ConfInfo.terBiliLive_control_ui.Reply_LiveState.setSelected(true);new HFJ_Fun("开启状态 成功");break;}
                                                case "# 关闭状态":{ ConfInfo.terBiliLive_control_ui.Reply_LiveState.setSelected(false);new HFJ_Fun("关闭状态 成功");break;}
                                                case "# 开启低保":{ ConfInfo.terBiliLive_control_ui.Reply_LowSecurity.setSelected(true);new HFJ_Fun("开启低保 成功");break;}
                                                case "# 关闭低保":{ ConfInfo.terBiliLive_control_ui.Reply_LowSecurity.setSelected(false);new HFJ_Fun("关闭低保 成功");break;}
                                                case "# 开启聊天":{ ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(true);new HFJ_Fun("开启聊天 成功");break;}
                                                case "# 关闭聊天":{ ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false);new HFJ_Fun("关闭聊天 成功");break;}
                                                case "# 开启回应":{ ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(true);ConfInfo.terBiliLive_control_ui.Reply_tourist.setSelected(true);new HFJ_Fun("开启回应 成功 并且开启了聊天");break;}
                                                case "# 关闭回应":{ ConfInfo.terBiliLive_control_ui.Reply_tourist.setSelected(false);new HFJ_Fun("关闭回应 成功");break;}


//                                                case "# 禁用Send":{new HFJ_Fun("禁用 成功"); ConfInfo.terBiliLive_control_ui.Reply_XXX.setSelected(true);break;}
//                                                case "# 启用Send "+ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText().toString():{ ConfInfo.terBiliLive_control_ui.Reply_XXX.setSelected(false);new HFJ_Fun("请谨慎使用");break; }

                                            }

                                            String pattern = "# 呼叫Ter .*";
                                            if(Pattern.matches(pattern,putDM_text)){
                                                ConfInfo.dingtalkUtil.chatAdmin(putDM_text.substring(2),putDM_nickname);
                                            }
                                        }
                                        String patter = "# 呼叫Ter "+ ConfInfo.confData.getRoomId()+" .*";
                                        if(Pattern.matches(patter,putDM_text)){
                                            ConfInfo.dingtalkUtil.chatAdmin(putDM_text.substring(2),putDM_nickname);
                                        }

                                        if(ConfInfo.terBiliLive_control_ui.Reply_chat.isSelected()){
                                            if(!ConfInfo.terBiliLive_control_ui.Reply_tourist.isSelected()){
                                                if(array.getString(2).equals("1")||putDM_nickname.equals("mxnter")){
                                                    String pattern = "@ .*";
                                                    if(Pattern.matches(pattern,putDM_text)){
                                                        String TulingUtilMsg =TulingUtil.chat(putDM_text.substring(2),ConfInfo.confData.getTulingApikey());
//                                                    ConfInfo.putShowUtil.PutDMUtil(TulingUtilMsg);
                                                        switch (TulingUtilMsg){
                                                            case "msgnull":{System.out.println("消息为空");break;}
                                                            case "TulingApikeynull":{ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false); new HFJ_Fun(TulingUtilMsg); break;}
                                                            case "TerGGKey":{ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false);new HFJ_Fun(TulingUtilMsg); break;}
                                                            case "TerGG":{ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false);new HFJ_Fun(TulingUtilMsg); break;}
                                                            default:{  new HFJ_Fun(TulingUtilMsg);break;}
                                                        }
                                                    }
                                                }
                                            }else{
                                                String pattern = "@ .*";
                                                if(Pattern.matches(pattern,putDM_text)){
                                                    String TulingUtilMsg =TulingUtil.chat(putDM_text.substring(2),ConfInfo.confData.getTulingApikey());
//                                                    ConfInfo.putShowUtil.PutDMUtil(TulingUtilMsg);
                                                    switch (TulingUtilMsg){
                                                        case "msgnull":{System.out.println("消息为空");break;}
                                                        case "TulingApikeynull":{ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false); new HFJ_Fun(TulingUtilMsg); break;}
                                                        case "TerGGKey":{ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false);new HFJ_Fun(TulingUtilMsg); break;}
                                                        case "TerGG":{ConfInfo.terBiliLive_control_ui.Reply_chat.setSelected(false);new HFJ_Fun(TulingUtilMsg); break;}
                                                        default:{  new HFJ_Fun(TulingUtilMsg);break;}
                                                    }
                                                }
                                            }
//
                                        }

                                        System.out.println( putDM);
                                        break;
                                    }
                                    case "SEND_GIFT":{
                                        JSONObject giftData = object.getJSONObject("data");
                                        String giftName = giftData.getString("giftName");
                                        int giftNum = giftData.getInteger("num");
                                        String uname = giftData.getString("uname");
                                        String timestamp=giftData.getString("timestamp");
                                        //                                    int uid = giftData.getInteger("uid");
//                                        System.out.println(uname + "赠送 " + giftName + "*" + giftNum);
                                        if(giftName.equals("辣条")){


                                            if(!ConfInfo.lt_lt.containsKey(uname)) {
                                                System.out.println("整合辣条"+uname);
                                                new LT_Thr().start(uname,giftName);
                                                ConfInfo.lt_lt.put(uname,0);
                                            }
                                            System.out.println("整合辣条"+uname);
                                            ConfInfo.lt_lt.put(uname,ConfInfo.lt_lt.get(uname) + giftNum);
                                            putDM = "礼物 ："+TimeUtil.timeStamp2Date(timestamp,null)+" $ "+ " 感谢 "+uname + " 赠送 " +  giftName + "*" + giftNum;
                                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());

//                                            System.out.println(putDM);

                                        }else{
                                            putDM = "礼物 ："+TimeUtil.timeStamp2Date(timestamp,null)+" $ "+ " 感谢 "+uname + " 赠送 " +  giftName + "*" + giftNum;
                                            ConfInfo.SEND_GIFT=uname +  giftName  + giftNum;
                                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
                                            if (ConfInfo.Thank.equals("ok")) new HFJ_Fun("感谢 " + uname + " 赠送的 " + giftName +"*" + giftNum +" 喵~");

                                            System.out.println(putDM);
                                            LogUtil.putLog(getFormatDay(), getFormatHour(), object.toString()+ "\n","TerBiliLive LW Log");
                                        }

                                        break;
                                    }
                                    case "COMBO_SEND":{
                                        JSONObject giftData = object.getJSONObject("data");
                                        String gift_name = giftData.getString("gift_name");
                                        String uname = giftData.getString("uname");
                                        int combo_num = giftData.getInteger("combo_num");
                                        String timestamp=giftData.getString("timestamp");
//                                    int uid = giftData.getInteger("uid");
//                                        System.out.println(uname + "赠送 " + giftName + "*" + giftNum);
                                        putDM = "礼物 ："+getFormat()+" $ "+ " 感谢 "+uname + " 赠送 " +  gift_name+"*"  + combo_num ;


                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
                                        if (ConfInfo.Thank.equals("ok")) new HFJ_Fun("感谢 "+uname + " 赠送的 " +  gift_name+"*"  + combo_num +" 喵~");
                                        System.out.println(putDM);
                                        LogUtil.putLog(getFormatDay(), getFormatHour(), object.toString()+ "\n","TerBiliLive LW Log");
                                        break;
                                    }
                                    case "COMBO_END":{
                                        JSONObject giftData = object.getJSONObject("data");
                                        String gift_name = giftData.getString("gift_name");
                                        String gift_id = giftData.getString("gift_id");
                                        int combo_num = giftData.getInteger("combo_num");
                                        String uname = giftData.getString("uname");
                                        String end_time = giftData.getString("end_time");
//                                    int uid = giftData.getInteger("uid");
//                                        System.out.println(uname + "赠送 " + giftName + "*" + giftNum);

                                        if(gift_name.equals("摩天大楼")){
                                            ConfInfo.terBiliLive_control_ui.Reply_Master.setSelected(false);
                                            System.out.println("----------------------------------------关闭老爷----------------------------------------");
                                            new Thread(new Runnable(){
                                                public void run(){

                                                    try {
                                                        Thread.sleep(180*1000);
                                                        if(ConfInfo.terBiliLive_control_ui.Reply_MasterRadioGift.isSelected()){
                                                            //启动欢迎老爷
                                                            System.out.println("----------------------------------------启动老爷----------------------------------------");
                                                            ConfInfo.terBiliLive_control_ui.Reply_Master.setSelected(true);
                                                        }
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }


                                                }
                                            }).start();

                                        }

                                        if(!ConfInfo.SEND_GIFT.equals(uname +  gift_name + combo_num)){
                                            putDM = "礼物 ："+TimeUtil.timeStamp2Date(end_time,null)+" $ "+ " 感谢 "+uname + " 赠送 " +  gift_name + "*"  + combo_num;
                                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
                                            if (ConfInfo.Thank.equals("ok")) new HFJ_Fun("感谢 "+uname + " 赠送的 " +  gift_name+"*["  + combo_num +"连击]  喵~");
                                            System.out.println(putDM);
                                        }else{
                                            System.out.println("连送礼物结束"+ConfInfo.SEND_GIFT);
                                        }
                                        LogUtil.putLog(getFormatDay(), getFormatHour(), object.toString()+ "\n","TerBiliLive LW Log");
                                        break;
                                    }
                                    case "SPECIAL_GIFT":{
                                        JSONObject giftData = object.getJSONObject("data");
                                        JSONObject gift39 = giftData.getJSONObject("39");
                                        String giftaction = gift39.getString("action");

                                        String giftid = gift39.getString("id");
//                                        System.out.println(giftData);
                                        switch (giftaction){
                                            case "start":{

                                                String giftcontent = gift39.getString("content");
                                                ConfInfo.terBiliLive_control_ui.Reply_Master.setSelected(false);
                                                putTZ = "通知 ： ~ "+ "节奏风暴 开始 id:"+giftid +"内容："+giftcontent ;
                                                putDM = "通知 ： ~ "+ "节奏风暴 开始 id:"+giftid  +"内容："+giftcontent;
                                                //关闭欢迎老爷
                                                DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
                                                System.out.println("----------------------------------------关闭老爷----------------------------------------");
                                                break;
                                            }
                                            case "end":{
                                                if(ConfInfo.terBiliLive_control_ui.Reply_MasterRadioGift.isSelected()){
                                                    //启动欢迎老爷
                                                    System.out.println("----------------------------------------启动老爷----------------------------------------");
                                                    ConfInfo.terBiliLive_control_ui.Reply_Master.setSelected(true);
                                                }

//                                                if (ConfInfo.Thank.equals("ok")) new HFJ_Fun("感谢 "+uname + " 赠送的 " +  gift_name+"*"  + combo_num +" 喵~");
                                                putTZ = "通知 ： ~ "+ "节奏风暴 结束 id:"+giftid  ;
                                                break;
                                            }

                                        }
//                                                Map<String, String> paramMap = new HashMap<String, String>();
//                                                paramMap.put("id", giftid);
//                                                paramMap.put("color", "16777215");
//                                                paramMap.put("captcha_token", "");
//                                                paramMap.put("captcha_phrase", "");
//                                                paramMap.put("roomid", "5440");
//                                                paramMap.put("csrf_token", "810077afcf6973ee439bd6bd2caaddd5");
//                                                paramMap.put("visit_id", "");
//                                                System.out.println("\n\n\n\n" +   ConfInfo.sendPost.SendPost("https://api.live.bilibili.com/lottery/v1/Storm/join", null, ConfInfo.cookie)+ "aabbcc");
                                        LogUtil.putLog(getFormatDay(), getFormatHour(), object.toString()+ "\n","TerBiliLive LW Log");
                                        System.out.println(putTZ);
                                        break;
                                    }

                                    /*21:00:11:{"data":{"msg":{"msg":"晚枫终有叶落时:? 送给:? 小小的小兔团儿:? 1个小电视飞船，点击前往TA的房间去抽奖吧","styleType":2,"real_roomid":7471685,"msg_common":"全区广播：<%晚枫终有叶落时%> 送给<% 小小的小兔团儿%> 1个小电视飞船，点击前往TA的房间去抽奖吧","rnd":1537962336,"msg_self":"全区广播：<%晚枫终有叶落时%> 送给<% 小小的小兔团儿%> 1个小电视飞船，快来抽奖吧","cmd":"SYS_MSG","msg_text":"晚枫终有叶落时:? 送给:? 小小的小兔团儿:? 1个小电视飞船，点击前往TA的房间去抽奖吧","rep":1,"url":"http://live.bilibili.com/7471685","roomid":7471685,"broadcast_type":1},"asset_tips_pic":"http://s1.hdslb.com/bfs/live/ac43b069bec53d303a9a1e0c4e90ccd1213d1b6b.png","raffleId":142504,"asset_animation_pic":"http://i0.hdslb.com/bfs/live/746a8db0702740ec63106581825667ae525bb11a.gif","dtime":180,"title":"小电视飞船抽奖","type":"small_tv","from_user":{"face":"http://i1.hdslb.com/bfs/face/7c663fae141aa51ceb1ae6f624ed3dc636911cd9.jpg","uname":"晚枫终有叶落时"},"time_wait":120,"from":"晚枫终有叶落时","id":"142504","time":180,"max_time":180,"payflow_id":"1537966810111900001"},"cmd":"TV_START"}
                                     */
                                    case "TV_START":{
                                        ConfInfo.terBiliLive_control_ui.Reply_Master.setSelected(false);
                                        System.out.println("----------------------------------------关闭老爷----------------------------------------");
                                        JSONObject giftData = object.getJSONObject("data");
                                        String from =giftData.getString("from");
                                        String id=giftData.getString("id");
                                        putTZ = "通知 ： ~ "+ "小电视 开始 大佬："+ from +" Id:"+id ;
                                        putDM = "通知 ： ~ "+ "小电视 开始 大佬："+ from +" Id:"+id ;
                                        LogUtil.putLog(getFormatDay(), getFormatHour(), object.toString()+ "\n","TerBiliLive LW Log");
                                        break;
                                    }
                                    case "TV_END":{

                                        JSONObject giftData = object.getJSONObject("data");
                                        String from =giftData.getString("from");
                                        String id=giftData.getString("id");
                                        putTZ = "通知 ： ~ "+ "小电视 结束 大佬："+ from +" Id:"+id ;
                                        putDM = "通知 ： ~ "+ "小电视 结束 大佬："+ from +" Id:"+id ;
                                        if (ConfInfo.Thank.equals("ok"))new HFJ_Fun("哇，发现大佬 "+ from+"送的 小电视 喵~");
                                        LogUtil.putLog(getFormatDay(), getFormatHour(), object.toString()+ "\n","TerBiliLive LW Log");
                                        if(ConfInfo.terBiliLive_control_ui.Reply_MasterRadioGift.isSelected()){
                                            //启动欢迎老爷
                                            System.out.println("----------------------------------------启动老爷----------------------------------------");
                                            ConfInfo.terBiliLive_control_ui.Reply_Master.setSelected(true);
                                        }
                                        break;
                                    }
                                    case "ROOM_ADMINS":{
                                        LogUtil.putLog(getFormatDay(), getFormatHour(), object.toString()+ "\n","TerBiliLive Log");
                                        break;
                                    }

                                    case "RAFFLE_START":{

                                        ConfInfo.terBiliLive_control_ui.Reply_Master.setSelected(false);
                                        System.out.println("----------------------------------------关闭老爷----------------------------------------");
                                        JSONObject giftData = object.getJSONObject("data");
                                        String from =giftData.getString("from");
                                        String id=giftData.getString("id");
                                        String title = giftData.getString("title");
                                        String type = giftData.getString("type");

                                        if(title.equals("摩天大楼抽奖")||type.equals("GIFT_20003")){
                                            putTZ = "通知 ： ~ "+ "摩天大楼 开始 大佬："+ from +" Id:"+id ;
                                            putDM = "通知 ： ~ "+ "摩天大楼 开始 大佬："+ from +" Id:"+id ;

                                        }else{
                                            LogUtil.putLog(getFormatDay(), getFormatHour(), object.toString()+ "\n","TerBiliLive WZLW Log");
                                        }

                                        LogUtil.putLog(getFormatDay(), getFormatHour(), object.toString()+ "\n","TerBiliLive LW Log");
                                        break;
                                    }
                                    case "RAFFLE_END":{

                                        JSONObject giftData = object.getJSONObject("data");
                                        String from =giftData.getString("from");
                                        String id=giftData.getString("id");
                                        String title = giftData.getString("title");
                                        String type = giftData.getString("type");
                                        if(title.equals("摩天大楼抽奖")||type.equals("GIFT_20003")){
                                            putTZ = "通知 ： ~ "+ "摩天大楼 结束 大佬："+ from +" Id:"+id ;
                                            putDM = "通知 ： ~ "+ "摩天大楼 结束 大佬："+ from +" Id:"+id ;
                                            if (ConfInfo.Thank.equals("ok"))  new HFJ_Fun("哇，发现大佬 "+ from+"送的 摩天大楼 喵~");
                                        }else{
                                            LogUtil.putLog(getFormatDay(), getFormatHour(), object.toString()+ "\n","TerBiliLive WZLW Log");
                                        }

                                        if(ConfInfo.terBiliLive_control_ui.Reply_MasterRadioGift.isSelected()){
                                            //启动欢迎老爷
                                            System.out.println("----------------------------------------启动老爷----------------------------------------");
                                            ConfInfo.terBiliLive_control_ui.Reply_Master.setSelected(true);
                                        }

                                        LogUtil.putLog(getFormatDay(), getFormatHour(), object.toString()+ "\n","TerBiliLive LW Log");
                                        break;
                                    }

/*21:25:23:{"data":{"msg":{"msg":"晚枫终有叶落时:? 送给:? 小小的小兔团儿:? 1个摩天大楼，点击前往TA的房间去抽奖吧","styleType":2,"real_roomid":7471685,"msg_common":"娱乐区广播: <%晚枫终有叶落时%> 送给<% 小小的小兔团儿%> 1个摩天大楼，点击前往TA的房间去抽奖吧","rnd":1537962336,"msg_self":"娱乐区广播: <%晚枫终有叶落时%> 送给<% 小小的小兔团儿%> 1个摩天大楼，快来抽奖吧","cmd":"SYS_MSG","msg_text":"晚枫终有叶落时:? 送给:? 小小的小兔团儿:? 1个摩天大楼，点击前往TA的房间去抽奖吧","rep":1,"url":"http://live.bilibili.com/7471685","roomid":7471685,"broadcast_type":1},"asset_tips_pic":"http://s1.hdslb.com/bfs/live/380bcd708da496d75737c68930965dd67b82879d.png","raffleId":142536,"asset_animation_pic":"http://i0.hdslb.com/bfs/live/7e47e9cfb744acd0319a4480e681258ce3a611fe.gif","dtime":120,"title":"摩天大楼抽奖","type":"GIFT_20003","from_user":{"face":"http://i1.hdslb.com/bfs/face/7c663fae141aa51ceb1ae6f624ed3dc636911cd9.jpg","uname":"晚枫终有叶落时"},"time_wait":60,"from":"晚枫终有叶落时","id":"142536","time":120,"max_time":120,"payflow_id":"1537968322111900001"},"cmd":"RAFFLE_START"}


21:27:23:{"data":{"raffleId":"142536","fromGiftId":20003,"uname":"墨竹清音","sname":"晚枫终有叶落时","giftName":"23333x银瓜子","from":"晚枫终有叶落时","id":"142536","type":"GIFT_20003","win":{"msg":"恭喜 <%墨竹清音%> 获得大奖 <%23333x银瓜子%>, 感谢 <%晚枫终有叶落时%> 的赠送","giftId":"silver","face":"http://i2.hdslb.com/bfs/face/1dfec3945b9eb47f334ca87095913cdefb83b6cc.jpg","giftImage":"http://s1.hdslb.com/bfs/live/00d768b444f1e1197312e57531325cde66bf0556.png","uname":"墨竹清音","giftName":"银瓜子","giftNum":23333},"fromFace":"http://i1.hdslb.com/bfs/face/7c663fae141aa51ceb1ae6f624ed3dc636911cd9.jpg","mobileTips":"恭喜 墨竹清音 获得23333x银瓜子"},"cmd":"RAFFLE_END"}
*/
                                    case "WELCOME":{
                                        JSONObject welcData = object.getJSONObject("data");
//                                      int uid = welcData.getInteger("uid");
                                        String vipStr = "";
                                        try {
                                            int vip = welcData.getInteger("vip");
                                            if(vip==1)vipStr="月费";
                                        }catch (Exception e){

                                        }
                                        try {
                                            int svip = welcData.getInteger("svip");
                                            if(svip==1)vipStr="年费";
                                        }catch (Exception e){

                                        }

                                        String uname = welcData.getString("uname");
//                                        System.out.println("欢迎老爷 " + uname + " 进入直播间");
                                        putDM =  "提示 ："+getFormat()+" @ "+"欢迎"+vipStr+"老爷 "  +uname ;

                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
                                        if (ConfInfo.terBiliLive_control_ui.Reply_Master.isSelected())  new HFJ_Fun("欢迎"+vipStr+"老爷 "  +uname );
                                        System.out.println(putDM);
                                        break;
                                    }
                                    case "WELCOME_GUARD":{
                                        JSONObject welcData = object.getJSONObject("data");
//                                    int uid = welcData.getInteger("uid");
                                        String username = welcData.getString("username");
                                        String guard_level = welcData.getString("guard_level");
//                                        System.out.println("欢迎老爷 " + uname + " 进入直播间");
                                        switch (guard_level){
                                            case "3":
                                                putDM =  "提示 ："+getFormat()+" @ "+"欢迎舰长 "  +username ;
                                                if (ConfInfo.terBiliLive_control_ui.Reply_Guard.isSelected())  new HFJ_Fun("欢迎舰长 "  +username );
                                                break;
                                            case "2":
                                                putDM =  "提示 ："+getFormat()+" @ "+"欢迎提督 "  +username ;
                                                if (ConfInfo.terBiliLive_control_ui.Reply_Guard.isSelected())  new HFJ_Fun("欢迎提督 "  +username );
                                                break;
                                            case "1":
                                                putDM =  "提示 ："+getFormat()+" @ "+"欢迎总督 "  +username ;
                                                if (ConfInfo.terBiliLive_control_ui.Reply_Guard.isSelected())  new HFJ_Fun("欢迎总督 "  +username );
                                                break;
                                            default:
                                                putDM =  "提示 ："+getFormat()+" @ "+"欢迎 "  +username ;
                                        }

//                                        String putDM =  "提示 ："+getFormat()+" - "+"欢迎舰长 "  +uname +"\t";


                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
//                                        new HFJ_Fun("欢迎老爷 "  +username +"\t");
                                        System.out.println(putDM);
                                        break;
                                    }
//                                    {"data":{"uid":263160725,"start_time":1537077349,"price":198000,"num":1,"gift_id":10003,"end_time":1537077349,"gift_name":"舰长","username":"送君千里带绿帽","guard_level":3},"cmd":"GUARD_BUY"}

                                    case "GUARD_BUY":{
                                        JSONObject welcData = object.getJSONObject("data");
                                        String gift_name = welcData.getString("gift_name");
                                        String username = welcData.getString("username");
                                        String guard_level = welcData.getString("guard_level");
//                                        System.out.println("欢迎老爷 " + uname + " 进入直播间");
                                        switch (guard_level){
                                            case "3":
                                                putDM =  "提示 ："+getFormat()+" @ "+"喵~ 感谢新的 "+gift_name+ " "+username +"开通特权" ;
                                                if (ConfInfo.terBiliLive_control_ui.Reply_Guard.isSelected())  new HFJ_Fun("喵~ 感谢新的 "+gift_name+ " "+username +"开通特权");
                                                break;
                                            case "2":
                                                putDM =  "提示 ："+getFormat()+" @ "+"喵~ 感谢新的 "+gift_name+ " "+username +"开通特权" ;
                                                if (ConfInfo.terBiliLive_control_ui.Reply_Guard.isSelected())  new HFJ_Fun("喵~ 感谢新的 "+gift_name+ " "+username +"开通特权");
                                                break;
                                            case "1":
                                                putDM =  "提示 ："+getFormat()+" @ "+"喵~ 感谢新的 "+gift_name+ " "+username +"开通特权" ;
                                                if (ConfInfo.terBiliLive_control_ui.Reply_Guard.isSelected())  new HFJ_Fun("喵~ 感谢新的 "+gift_name+ " "+username +"开通特权" );
                                                break;
                                        }

//                                        String putDM =  "提示 ："+getFormat()+" - "+"欢迎舰长 "  +uname +"\t";


                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
                                        System.out.println(putDM);
                                        break;
                                    }
                                    case "NOTICE_MSG":{
                                        String msg_common =  object.getString("msg_common");
                                        String real_roomid= object.getString("real_roomid");
                                        putTZ = "通知 ： ~ "+ "id:"+real_roomid+" "+ msg_common    ;


                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putTZ,Control_UiT_RoomId.getText());
                                        System.out.println(putTZ+"\t");
                                        break;
                                    }
                                    case "SYS_MSG":{
                                        String msg =  object.getString("msg");
                                        String real_roomid= object.getString("real_roomid");
                                        putTZ = "通知 ： ~ "+ "id:"+real_roomid +" "+ msg   ;

                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putTZ,Control_UiT_RoomId.getText());
                                        if(real_roomid!=null){
                                            if (ConfInfo.terBiliLive_control_ui.Reply_LowSecurity.isSelected())new HFJ_Fun("出现低保："+real_roomid);
                                        }
                                        System.out.println( msg   + "id:"+real_roomid +"\t");
                                        break;
                                    }
                                    case "ENTRY_EFFECT":{
                                        System.out.println( "进入动画"+"\t");
                                        break;
                                    }
                                    case "ROOM_RANK":{
                                        JSONObject RoomRankData = object.getJSONObject("data");
                                        String rank_desc =  RoomRankData.getString("rank_desc");
                                        String roomid= RoomRankData.getString("roomid");
                                        putTZ = "通知 ： ~ "+ "   id:"+roomid +" "+ rank_desc   ;

                                        Control_UiT_RoomRank.setText(rank_desc);
                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putTZ,Control_UiT_RoomId.getText());
                                        System.out.println( rank_desc   + " id:"+roomid +"\t");

                                        break;
                                    }
                                    case "LIVE":{
                                        String roomid= object.getString("roomid");
                                        putTZ = "通知 ： ~ "+ "   id:"+roomid +" 直播开始啦，拿好小板凳哟."   ;


                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putTZ,Control_UiT_RoomId.getText());
                                        if (ConfInfo.terBiliLive_control_ui.Reply_LiveState.isSelected()) new HFJ_Fun("直播开始啦，拿好小板凳哟. ");
                                        System.out.println( "通知 ： ~ "+ "   id:"+roomid +" 直播开始啦，拿好小板凳哟.");
                                        ConfInfo.dingtalkUtil.LiveLive();
                                        break;
                                    }
                                    case "PREPARING":{
                                        String roomid= object.getString("roomid");
                                        putTZ = "通知 ： ~ "+ "   id:"+roomid +" 直播结束啦，记得关注哦."   ;


                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putTZ,Control_UiT_RoomId.getText());
                                        if (ConfInfo.terBiliLive_control_ui.Reply_LiveState.isSelected()) new HFJ_Fun("直播结束啦，记得关注哦.");
                                        System.out.println( "通知 ： ~ "+ "   id:"+roomid +" 直播结束啦，记得关注哦.");
                                        ConfInfo.dingtalkUtil.LivePreparing();
                                        break;
                                    }
/*
{"msg":"用户 :?风烧的大兔子丶:? 在主播 肥皂菌丨珉珉的猫咪丨 的直播间开通了总督","buy_type":1,"cmd":"GUARD_MSG","msg_new":"用户<%风烧的大兔子丶%>在主播<%肥皂菌丨珉珉的猫咪丨%>的直播间开通了总督并触发了抽奖，点击前往TA的房间去抽奖吧","url":"https://live.bilibili.com/78787","roomid":78787,"broadcast_type":0}
*/
                                    case "GUARD_MSG":{
                                        String roomid= object.getString("roomid");

                                        int buy_type = object.getInteger("buy_type");
                                        String buy_typeStr = "";
                                        switch (buy_type){
                                            case 1:{
                                                buy_typeStr="总督";
                                                break;
                                            }
                                            case 2:{
                                                buy_typeStr="提督";
                                                break;
                                            }
                                            case 3:{
                                                buy_typeStr="舰长";
                                                break;
                                            }

                                        }
                                        putTZ = "通知 ： ~ " +" 出现航海" + " id:"+roomid  +"类型：" +buy_typeStr ;
                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putTZ,Control_UiT_RoomId.getText());
                                        if (ConfInfo.terBiliLive_control_ui.Reply_LowSecurity.isSelected()) new HFJ_Fun(" 出现航海" + " id:"+roomid + "类型：" +buy_typeStr);
                                        System.out.println( putTZ);
                                        break;
                                    }







/* PK
15:51:11:{"pk_id":131652,"data":{"uid":90708755,"face":"http://i0.hdslb.com/bfs/face/be74581c904733dd2722246fda18c2a41737600f.jpg","escape_all_time":10,"uname":"梨落大大","match_id":3647345,"is_portrait":false,"init_id":7471685,"escape_time":10},"pk_status":100,"cmd":"PK_MATCH","roomid":7471685}

15:51:12:{"pk_id":131652,"data":{"pk_start_time":1537084277,"match_id":3647345,"count_down":5,"pk_pre_time":1537084272,"end_time":1537084697,"pk_topic":"唱首中文歌","pk_end_time":1537084577,"init_id":7471685},"pk_status":200,"cmd":"PK_PRE"}
                                    15:21:15:{"pk_id":131628,"data":{"match_id":7471685,"pk_topic":"做一项别人不会的绝技","init_id":11365011},"pk_status":300,"cmd":"PK_START"}

                                    15:24:35:{"pk_id":131628,"data":{"uid":18169995,"match_votes":5,"init_votes":0,"user_votes":5},"pk_status":300,"cmd":"PK_PROCESS","roomid":7471685}

                                    15:26:16:{"pk_id":131628,"data":{"punish_topic":"惩罚：模仿面筋哥","match_id":7471685,"init_id":11365011},"pk_status":400,"cmd":"PK_END"}

                                    15:26:16:{"pk_id":131628,"data":{"match_info":{"is_winner":true,"uid":27897180,"badge":{"position":0,"url":"","desc":""},"face":"http://i0.hdslb.com/bfs/face/6232d18dff2835591c3cf867ef79acef3a6e74e8.jpg","face_frame":"","uname":"小小的小兔团儿","vip_type":0,"match_id":7471685,"votes":5,"exp":{"master_level":{"color":5805790,"level":15},"color":9868950,"user_level":8},"vip":{"vip":0,"svip":0}},"init_info":{"is_winner":false,"uid":330655469,"face":"http://i1.hdslb.com/bfs/face/1cee3f42eb4d6268e3c368f9506dc4eda230c532.jpg","uname":"圆啵叽","votes":0,"init_id":11365011},"pk_id":131628,"punish_topic":"惩罚：模仿面筋哥","best_user":{"uid":18169995,"privilege_type":0,"badge":{"position":0,"url":"","desc":""},"face":"http://i0.hdslb.com/bfs/face/ecd8064598c534ec311287dd9c0b17dc8f3bb4f9.jpg","face_frame":"","uname":"mxnter","vip_type":0,"exp":{"master_level":{"color":6406234,"level":10},"color":5805790,"user_level":28},"vip":{"vip":0,"svip":0}}},"pk_status":400,"cmd":"PK_SETTLE"}
*/
                                    case "PK_START":{

                                        break;
                                    }
                                    case "PK_PROCESS":{

                                        break;
                                    }
                                    case "PK_END":{

                                        break;
                                    }
                                    case "PK_SETTLE":{

                                        break;
                                    }
                                    case "PK_MATCH":{

                                        break;
                                    }
                                    case "PK_PRE":{

                                        break;
                                    }
                                    case "PK_MIC_END":{

                                        break;
                                    }
                                    case "PK_CLICK_AGAIN":{

                                        break;
                                    }
                                    default:{
                                        putDM="";
                                        LogUtil.putLog(getFormatDay(), getFormatHour(), object.toString()+ "\n","TerBiliLive Log");
                                    }
                                }

//                                Thread.sleep(50);//线程阻塞1秒后运行
                                            if(!putDM.equals("")){
                                                ConfInfo.ChargeBarrageList.add(putDM);
//                                                if(!ConfInfo.GetSendBarrageList_Thr_Size){
                                                    synchronized (ConfInfo.GBT) {
                                                        ConfInfo.GBT.notify();
//                                                        ConfInfo.GetSendBarrageList_Thr_Size=true;
                                                        System.out.println("-----------------------显示弹幕数据重新激活-----------------------");
//                                                    }
                                                }



//                                                ConfInfo.putShowUtil.PutDMUtil(putDM);
                                            }

                                            if(!putTZ.equals(""))
                                                ConfInfo.putShowUtil.PutTZUtil(putTZ);

//                                String finalPutDM = putDM;
//                                new Thread(new Runnable(){
//                                    public void run(){
//
//                                        try {
//                                            Thread.sleep(100);//线程阻塞1秒后运行
//                                            if(!finalPutDM.equals(""))
//                                                DMJ_UiT_Text.append(finalPutDM +"\r\n");
//                                            DMJ_UiT_Text.setCaretPosition(DMJ_UiT_Text.getText().length());
//                                        } catch (InterruptedException e) {
//                                            e.printStackTrace();
//                                        }
//
//
//                                    }
//                                }).start();


                            }
                        }
                    }else if (msgLength > 16 && msgLength < dataLength){
                        byte[] singleData = new byte[msgLength];
                        System.arraycopy(data, 0, singleData, 0, msgLength);
                        analyzeData(singleData);
                        int remainLen = dataLength - msgLength;
                        byte[] remainDate = new byte[remainLen];
                        System.arraycopy(data, msgLength, remainDate, 0, remainLen);
                        analyzeData(remainDate);
                    }
                }catch (Exception ex){

                    ex.printStackTrace();
                }
            }
        }

    }

}
