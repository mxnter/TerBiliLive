package com.TerBiliLive.Thr;

import com.TerBiliLive.Function.Control_Fun;
import com.TerBiliLive.Function.HFJ_Fun;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.TerBiliLive.getInfo;
import com.TerBiliLive.TerBiliLive.SendPost;
import com.TerBiliLive.Ui.TerBiliLive_Control_Ui;
import com.TerBiliLive.Utiliy.DmLogUtil;
import com.TerBiliLive.Utiliy.DmUtil;
import com.TerBiliLive.Utiliy.LogUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.Socket;

import static com.TerBiliLive.Ui.TerBiliLive_Control_Ui.Control_UiT_RoomId;
import static com.TerBiliLive.Ui.TerBiliLive_Control_Ui.Control_UiT_State;
import static com.TerBiliLive.Ui.TerBiliLive_DMJ_Ui.DMJ_UiT_Text;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormat;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatHour;

public class DMJ_Thr {



    public boolean AYO= true;
    String Parameter= "" ;
    TerBiliLive_Control_Ui C=new TerBiliLive_Control_Ui(Parameter);
   // TerBiliLive_DMJ_Ui DMJ =new TerBiliLive_DMJ_Ui();
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
            new handle_data_loop().start();
        }
    }

    public void stop(){
        keepRunning = false;
        client.disconnect(socket);
        String putDM =  "系统 ："+getFormat()+" - "+"断开连接" +" 真实直播间ID："+roomID  +"\t";
        DMJ_UiT_Text.append(putDM+"\r\n");
        DMJ_UiT_Text.setCaretPosition(DMJ_UiT_Text.getText().length());
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
                    DMJ_UiT_Text.append("系统 ："+getFormat()+" ! "+"连接成功 " +"真实直播间ID："+roomID +"\r\n");

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
                            DMJ_UiT_Text.append("系统 ："+getFormat()+" ! "+ConfInfo.isReConnSum+"-自动重连" +" 真实直播间ID："+roomID   +"\r\n");

                            DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),ConfInfo.isReConnSum+"-自动重连" +" 真实直播间ID："+roomID   ,Control_UiT_RoomId.getText());
                            System.out.println("自动重连" +" 真实直播间ID："+roomID );

                            if(ConfInfo.isReConnSum>=10){
                                keepRunning = false;
                            }
                            (new DMJ_Thr()).start(roomID, true);

                        }
                        keepRunning = false;
                        e.printStackTrace();
                    }
                }
//                DMJ_UiT_Text.setCaretPosition(DMJ_UiT_Text.getText().length());
            }
        }

        private void analyzeData(byte[] data){

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
                            Control_UiT_State.setText("在线人数：" + userCount);
//                            DMJ_UiT_Text.append("在线人数：" + userCount+"\r\n");
                            System.out.println("在线人数：" + userCount);
                        }else if (action == 4){
                            inputStream.readInt();
                            int msgBodyLength = dataLength - 16;
                            byte[] msgBody = new byte[msgBodyLength];
                            if (inputStream.read(msgBody) == msgBodyLength){
                                String jsonStr = new String(msgBody, "utf-8");
                                System.out.println(jsonStr);
                                JSONObject object = JSON.parseObject(jsonStr);
                                String msgType = object.getString("cmd");
                                System.out.println("a");
                                switch (msgType){
                                    case "DANMU_MSG":{
                                        JSONArray array = object.getJSONArray("info").getJSONArray(2);
//                                    int uid = array.getInteger(0);

                                        String putDM_text = object.getJSONArray("info").getString(1);
                                        String putDM_isadmin = "";
                                        String putDM_nickname = array.getString(1);
                                        String putDM_timeline = "";
                                        String putDM_vip = "";
                                        String putDM_medal="";
                                        try {
                                            putDM_medal ="【" + object.getJSONArray("info").getJSONArray(3).getString(1)+object.getJSONArray("info").getJSONArray(3).getString(0)+"】 "  ;

                                        }catch (Exception e){
                                            putDM_medal="";
                                        }

                                        String putDM_user_level =" [" + object.getJSONArray("info").getJSONArray(4).getString(0) + "] " ;

                                        putDM_isadmin =(array.getString(2).equals("1"))?"<房主/管> ":"";
//                                        if (array.getString(2).equals("1")) {
//                                            putDM_isadmin = "<房主/管> ";
//                                        } else {
//                                            putDM_isadmin = "";
//                                        }


                                        putDM_timeline = getFormat();

                                        if (array.getString(3).equals("1")) {
                                            putDM_vip = "普通老爷 ";
                                        }
                                        if (array.getString(4).equals("1")) {
                                            putDM_vip = "年费老爷 ";
                                        }

                                        String putDM ="弹幕 ："+ putDM_timeline + " - " +putDM_vip + " " + putDM_isadmin +  putDM_medal + putDM_user_level + putDM_nickname + " ：" + putDM_text+"\t";
                                        DMJ_UiT_Text.append(putDM+"\r\n");

                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
                                        System.out.println( putDM);
                                        break;
                                    }
                                    case "SEND_GIFT":{
                                        JSONObject giftData = object.getJSONObject("data");
                                        String giftName = giftData.getString("giftName");
                                        int giftNum = giftData.getInteger("num");
                                        String uname = giftData.getString("uname");
//                                    int uid = giftData.getInteger("uid");
//                                        System.out.println(uname + "赠送 " + giftName + "*" + giftNum);
                                        String putDM = "礼物 ："+getFormat()+" $ "+ " 感谢 "+uname + " 赠送 " +  giftName + "*" + giftNum;
                                        DMJ_UiT_Text.append(putDM+"\r\n");

                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
//                                        new HFJ_Fun("感谢 "+uname + " 赠送的 " +  giftName +" 喵~");
                                        System.out.println(putDM);
                                        break;
                                    }
                                    case "COMBO_END":{
                                        JSONObject giftData = object.getJSONObject("data");
                                        String gift_name = giftData.getString("gift_name");
                                        int combo_num = giftData.getInteger("combo_num");
                                        String uname = giftData.getString("uname");
//                                    int uid = giftData.getInteger("uid");
//                                        System.out.println(uname + "赠送 " + giftName + "*" + giftNum);
                                        String putDM = "礼物 ："+getFormat()+" $ "+ " 感谢 "+uname + " 赠送 " +  gift_name + "*" + combo_num;
                                        DMJ_UiT_Text.append(putDM+"\r\n");

                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
//                                        new HFJ_Fun("感谢 "+uname + " 赠送的 " +  gift_name +" 喵~");
                                        System.out.println(putDM);
                                        break;
                                    }
                                    case "WELCOME":{
                                        JSONObject welcData = object.getJSONObject("data");
//                                    int uid = welcData.getInteger("uid");
                                        String uname = welcData.getString("uname");
//                                        System.out.println("欢迎老爷 " + uname + " 进入直播间");
                                        String putDM =  "提示 ："+getFormat()+" @ "+"欢迎老爷 "  +uname ;
                                        DMJ_UiT_Text.append(putDM+"\r\n");

                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
//                                        new HFJ_Fun("欢迎老爷 "  +uname +"\t");
                                        System.out.println(putDM);
                                        break;
                                    }
                                    case "WELCOME_GUARD":{
                                        JSONObject welcData = object.getJSONObject("data");
//                                    int uid = welcData.getInteger("uid");
                                        String username = welcData.getString("username");
                                        String guard_level = welcData.getString("guard_level");
//                                        System.out.println("欢迎老爷 " + uname + " 进入直播间");
                                        String putDM;
                                        switch (guard_level){
                                            case "3":
                                                putDM =  "提示 ："+getFormat()+" @ "+"欢迎舰长 "  +username ;
                                                new HFJ_Fun("欢迎舰长 "  +username +"\t");
                                                break;
                                            case "2":
                                                putDM =  "提示 ："+getFormat()+" @ "+"欢迎提督 "  +username ;
                                                new HFJ_Fun("欢迎提督 "  +username +"\t");
                                                break;
                                            case "1":
                                                putDM =  "提示 ："+getFormat()+" @ "+"欢迎总督 "  +username ;
                                                new HFJ_Fun("欢迎总督 "  +username +"\t");
                                                break;
                                            default:
                                                putDM =  "提示 ："+getFormat()+" @ "+"欢迎 "  +username ;
                                        }

//                                        String putDM =  "提示 ："+getFormat()+" - "+"欢迎舰长 "  +uname +"\t";
                                        DMJ_UiT_Text.append(putDM+"\r\n");

                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());

                                        System.out.println(putDM);
                                        break;
                                    }
                                    case "NOTICE_MSG":{

                                        String msg_common =  object.getString("msg_common");
                                        String real_roomid= object.getString("real_roomid");
                                        String putDM = "通知 ："+getFormat()+" ~ "+ msg_common   + "id:"+real_roomid ;
                                        DMJ_UiT_Text.append(putDM+"\r\n");

                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
//                                        new HFJ_Fun(putDM);
                                        System.out.println(putDM+"\t");
                                        break;
                                    }
                                    case "SYS_MSG":{

                                        String msg =  object.getString("msg");
                                        String real_roomid= object.getString("real_roomid");
                                        String putDM = "通知 ："+getFormat()+" ~ "+ msg   + "id:"+real_roomid +"\t";
                                        DMJ_UiT_Text.append(putDM+"\r\n");

                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
//                                        new HFJ_Fun(putDM);
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
                                        String putDM = "通知 ："+getFormat()+" ~ "+ rank_desc   + "   id:"+roomid +"\t";
                                        DMJ_UiT_Text.append(putDM+"\r\n");

                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
//                                        new HFJ_Fun(putDM);
                                        System.out.println( rank_desc   + "id:"+roomid +"\t");
                                        break;
                                    }
                                    default:{
                                        LogUtil.putLog(getFormatDay(), getFormatHour(), object.toString()+ "\n","TerBiliLive Log");
                                    }
                                }
//                                DMJ_UiT_Text.setCaretPosition(DMJ_UiT_Text.getText().length());
                                System.out.println("b");
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
                    System.out.println("c");
                }catch (Exception ex){

                    ex.printStackTrace();
                }
            }
        }

    }

}
