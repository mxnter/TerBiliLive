package com.TerBiliLive.Thr;

import com.TerBiliLive.Function.HFJ_Fun;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.TerBiliLive.getInfo;
import com.TerBiliLive.TerBiliLive.SendPost;
import com.TerBiliLive.Utiliy.DmLogUtil;
import com.TerBiliLive.Utiliy.DmUtil;
import com.TerBiliLive.Utiliy.LogUtil;
import com.TerBiliLive.Utiliy.TimeUtil;
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
                            Control_UiT_State.setText("在线人数：" + userCount);
//                            ConfInfo.putDMUtil.PutDMUtil("在线人数：" + userCount);
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
                                        putDM = "礼物 ："+getFormat()+" $ "+ " 感谢 "+uname + " 赠送 " +  giftName + "*" + giftNum;


                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
                                        if (ConfInfo.Thank.equals("ok")) new HFJ_Fun("感谢 " + uname + " 赠送的 " + giftName + " 喵~");

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
                                        putDM = "礼物 ："+getFormat()+" $ "+ " 感谢 "+uname + " 赠送 " +  gift_name + "*" + combo_num;


                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
                                        if (ConfInfo.Thank.equals("ok")) new HFJ_Fun("感谢 "+uname + " 赠送的 " +  gift_name +" 喵~");
                                        System.out.println(putDM);
                                        break;
                                    }
                                    case "COMBO_SEND":{
                                        JSONObject giftData = object.getJSONObject("data");
                                        String gift_name = giftData.getString("gift_name");
                                        String uname = giftData.getString("uname");
//                                    int uid = giftData.getInteger("uid");
//                                        System.out.println(uname + "赠送 " + giftName + "*" + giftNum);
                                        putDM = "礼物 ："+getFormat()+" $ "+ " 感谢 "+uname + " 赠送 " +  gift_name ;


                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
                                        if (ConfInfo.Thank.equals("ok")) new HFJ_Fun("感谢 "+uname + " 赠送的 " +  gift_name +" 喵~");
                                        System.out.println(putDM);
                                        break;
                                    }
                                    case "WELCOME":{
                                        JSONObject welcData = object.getJSONObject("data");
//                                    int uid = welcData.getInteger("uid");
                                        String uname = welcData.getString("uname");
//                                        System.out.println("欢迎老爷 " + uname + " 进入直播间");
                                        putDM =  "提示 ："+getFormat()+" @ "+"欢迎老爷 "  +uname ;

                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putDM,Control_UiT_RoomId.getText());
                                        if (ConfInfo.terBiliLive_control_ui.Reply_Master.isSelected())  new HFJ_Fun("欢迎老爷 "  +uname +"\t");
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
                                                if (ConfInfo.terBiliLive_control_ui.Reply_Guard.isSelected())  new HFJ_Fun("欢迎舰长 "  +username +"\t");
                                                break;
                                            case "2":
                                                putDM =  "提示 ："+getFormat()+" @ "+"欢迎提督 "  +username ;
                                                if (ConfInfo.terBiliLive_control_ui.Reply_Guard.isSelected())  new HFJ_Fun("欢迎提督 "  +username +"\t");
                                                break;
                                            case "1":
                                                putDM =  "提示 ："+getFormat()+" @ "+"欢迎总督 "  +username ;
                                                if (ConfInfo.terBiliLive_control_ui.Reply_Guard.isSelected())  new HFJ_Fun("欢迎总督 "  +username +"\t");
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
                                    case "NOTICE_MSG":{
                                        String msg_common =  object.getString("msg_common");
                                        String real_roomid= object.getString("real_roomid");
                                        putTZ = "通知 ： ~ "+ "id:"+real_roomid+" "+ msg_common    ;


                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putTZ,Control_UiT_RoomId.getText());
//                                        new HFJ_Fun(putDM);
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


                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putTZ,Control_UiT_RoomId.getText());
//                                        new HFJ_Fun(putDM);
                                        System.out.println( rank_desc   + "id:"+roomid +"\t");

                                        break;
                                    }
                                    case "LIVE":{
                                        String roomid= object.getString("roomid");
                                        putTZ = "通知 ： ~ "+ "   id:"+roomid +" 直播开始啦，拿好小板凳哟."   ;


                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putTZ,Control_UiT_RoomId.getText());
                                        if (ConfInfo.terBiliLive_control_ui.Reply_LiveState.isSelected()) new HFJ_Fun("直播开始啦，拿好小板凳哟. ");
//                                        new HFJ_Fun(putDM);
                                        System.out.println( "通知 ： ~ "+ "   id:"+roomid +" 直播开始啦，拿好小板凳哟.");
                                        break;
                                    }
                                    case "PREPARING":{
                                        String roomid= object.getString("roomid");
                                        putTZ = "通知 ： ~ "+ "   id:"+roomid +" 直播结束啦，记得关注哦."   ;


                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putTZ,Control_UiT_RoomId.getText());
                                        if (ConfInfo.terBiliLive_control_ui.Reply_LiveState.isSelected()) new HFJ_Fun("直播结束啦，记得关注哦.");
//                                        new HFJ_Fun(putDM);
                                        System.out.println( "通知 ： ~ "+ "   id:"+roomid +" 直播结束啦，记得关注哦.");
                                        break;
                                    }
                                    case "GUARD_MSG":{
                                        String roomid= object.getString("roomid");
                                        putTZ = "通知 ： ~ " +" 出现航海" + " id:"+roomid  ;
                                        DmLogUtil.putDmLog(getFormatDay(), getFormatHour(),putTZ,Control_UiT_RoomId.getText());
                                        if (ConfInfo.terBiliLive_control_ui.Reply_LowSecurity.isSelected()) new HFJ_Fun(" 出现航海" + " id:"+roomid);
//                                        new HFJ_Fun(putDM);
                                        System.out.println( putTZ);
                                        break;
                                    }
                                    default:{
                                        putDM="";
                                        LogUtil.putLog(getFormatDay(), getFormatHour(), object.toString()+ "\n","TerBiliLive Log");
                                    }
                                }

                                Thread.sleep(50);//线程阻塞1秒后运行
                                            if(!putDM.equals(""))
                                                ConfInfo.putShowUtil.PutDMUtil(putDM);
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
