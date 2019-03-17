package com.TerBiliLive.Utiliy;


import com.TerBiliLive.Info.ConfInfo;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * CODE IS POETRY
 *
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 12:19 2019/2/3
 * @Name ： 钉钉群推送工具类
 */
public class DingtalkUtil {
    String WEBHOOK_TOKEN_START = "https://oapi.dingtalk.com/robot/send?access_token=65802d93f15daa2b8574fb3b3656e81caa115c8795acb9d56dedf533a842ee3f";
    String WEBHOOK_TOKEN_LINK = "https://oapi.dingtalk.com/robot/send?access_token=d1305a3cf23a8f77be683005f41f633bfe9538eeac2d8d7cc228ea82b82d7cdf";
    String WEBHOOK_TOKEN_OPEM = "https://oapi.dingtalk.com/robot/send?access_token=1fb3bcac9e9a074522b8eb8b24697e4c5c55873b887de8e1cdb30b0409819090";
    String WEBHOOK_TOKEN_LOGIN = "https://oapi.dingtalk.com/robot/send?access_token=fc98094a30514adaa319b4701a7c775666fb2525c0afb087a5afa3c4f42d27bc";
    public DingtalkUtil(){

    }
    public String Msg(String msg){ return "{ \"msgtype\": \"text\", \"text\": {\"content\": \""+msg+"\"}}"; }
    public void DingtalkInform(){

    }
    public void LogIn(){
        try {
            String msg = "[登陆]" + "\n PC: "+ InetAddress.getLocalHost().getHostName()+"\n Mac: "+ MacUtil.getMacAddress() +"\n IP: "+IpUtil.getIpAddress()+"\n UL: "+ConfInfo.Uname;
            String textMsg = Msg(msg);
            Send(WEBHOOK_TOKEN_LOGIN,textMsg);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
    public void OpenProgram(){
        try {
            String msg = "[启动]" + "\n PC: "+ InetAddress.getLocalHost().getHostName()+"\n Mac: "+ MacUtil.getMacAddress() +"\n IP: "+IpUtil.getIpAddress();
            String textMsg = Msg(msg);
            Send(WEBHOOK_TOKEN_OPEM,textMsg);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    public void OpenLink(){
        String msg = "[开启连接]" + "\n UL:"+ConfInfo.Uname+"\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_LINK,textMsg);
    }
    public void CloseLink(){
        String msg = "[关闭连接]" + "\n UL:"+ConfInfo.Uname+"\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_LINK,textMsg);
    }

    public void LiveLive(){

        String msg = "[直播开始]" + "\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_START,textMsg);



    }
    public void LivePreparing(){
        String msg = "[直播结束]" + "\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_START,textMsg);



    }

    public void chatAdmin(String m,String uanme){
        String msg = "[呼叫作者]" + "\n信息：\n"+uanme+":"+m+"\n\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_START,textMsg);



    }
    public void chatUp(String m){
        String msg = "[呼叫Up]" + "\n信息："+m+"\n\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_START,textMsg);



    }
    public void bannedNotice(String m){
        String msg = "[禁言通知]" + "\n信息：\n"+m+"，已将感谢关闭"+"\n\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_START,textMsg);



    }

    public void Agreement(String type){
        try {
            String msg = "[授权]" + "\n PC: "+ InetAddress.getLocalHost().getHostName()+"\n Mac: "+ MacUtil.getMacAddress() +"\n IP: "+IpUtil.getIpAddress()+"\n Type: "+type;
            String textMsg = Msg(msg);
            ConfInfo.sendPost.doJsonPost(WEBHOOK_TOKEN_OPEM,textMsg);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    public void Send(String WEBHOOK_TOKEN_START,String textMsg){
        if(!AgreementUtil.readFile().equals("NO")){
            ConfInfo.sendPost.doJsonPost(WEBHOOK_TOKEN_START,textMsg);
        }

    }
}


