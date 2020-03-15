package com.TerBiliLive.TerBiliLive;


import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Utils.AESUtil;
import com.TerBiliLive.Utils.AgreementUtil;
import com.TerBiliLive.Utils.IpUtil;
import com.TerBiliLive.Utils.MacUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * CODE IS POETRY
 *
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 12:19 2019/2/3
 * @Name ： 钉钉群推送工具类
 */
public class Dingtalk {

    private String WEBHOOK_TOKEN_PUBLIC = "https://oapi.dingtalk.com/robot/send?access_token=";
    private String WEBHOOK_TOKEN_START = null;
    private String WEBHOOK_TOKEN_LINK = null;
    private String WEBHOOK_TOKEN_OPEM = null;
    private String WEBHOOK_TOKEN_LOGIN = null;



    public Dingtalk() {
       if(ConfInfo.MacAddress==null)ConfInfo.MacAddress = new MacUtil().getMacAddress();
       if(ConfInfo.IpAddress==null)ConfInfo.IpAddress = new IpUtil().getIpAddress();
    }
    private String Msg(String msg){ return "{ \"msgtype\": \"text\", \"text\": {\"content\": \""+msg+"\"}}"; }
    private void DingtalkInform(){
        if(WEBHOOK_TOKEN_START == null){
            try {
                WEBHOOK_TOKEN_START = AESUtil.Decrypt(ConfInfo.DingtalkWebhookStart);
            } catch (Exception e) {
                WEBHOOK_TOKEN_START = WEBHOOK_TOKEN_PUBLIC+ConfInfo.DingtalkWebhookPK;
                e.printStackTrace();
            }
        }
        if(WEBHOOK_TOKEN_LOGIN == null){
            try {
                WEBHOOK_TOKEN_LOGIN = AESUtil.Decrypt(ConfInfo.DingtalkWebhookLogin);
            } catch (Exception e) {
                WEBHOOK_TOKEN_LOGIN = WEBHOOK_TOKEN_PUBLIC+ConfInfo.DingtalkWebhookPK;
                e.printStackTrace();
            }
        }
        if(WEBHOOK_TOKEN_OPEM == null){
            try {
                WEBHOOK_TOKEN_OPEM = AESUtil.Decrypt(ConfInfo.DingtalkWebhookOpen);
            } catch (Exception e) {
                WEBHOOK_TOKEN_OPEM = WEBHOOK_TOKEN_PUBLIC+ConfInfo.DingtalkWebhookPK;
                e.printStackTrace();
            }
        }
        if(WEBHOOK_TOKEN_LINK == null){
            try {
                WEBHOOK_TOKEN_LINK = AESUtil.Decrypt(ConfInfo.DingtalkWebhookLink);
            } catch (Exception e) {
                WEBHOOK_TOKEN_LINK = WEBHOOK_TOKEN_PUBLIC+ConfInfo.DingtalkWebhookPK;
                e.printStackTrace();
            }
        }




    }
    public void LogIn(){
        DingtalkInform();
        try {
            String msg = "[登陆]" + "\n PC: "+ InetAddress.getLocalHost().getHostName()+"\n Mac: "+ ConfInfo.MacAddress +"\n IP: "+ConfInfo.infoNew.getAddr()+"\n UL: "+ConfInfo.Uname+"\n latitude: "+ConfInfo.infoNew.getLatitude()+"\n longitude: "+ConfInfo.infoNew.getLongitude()+"\n Address: "+ConfInfo.infoNew.getCountry()+"-"+ConfInfo.infoNew.getProvince()+"-"+ConfInfo.infoNew.getCity()+"-"+ConfInfo.infoNew.getIsp();
            String textMsg = Msg(msg);
            Send(WEBHOOK_TOKEN_LOGIN,textMsg);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
    public void OpenProgram(){
        DingtalkInform();
        try {
            String msg = "[启动]" + "\n PC: "+ InetAddress.getLocalHost().getHostName()+"\n Mac: "+ ConfInfo.MacAddress +"\n IP: "+ ConfInfo.IpAddress;
            String textMsg = Msg(msg);
            Send(WEBHOOK_TOKEN_OPEM,textMsg);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    public void OpenLink(){
        DingtalkInform();
        String msg = "[开启连接]" + "\n UL:"+ConfInfo.Uname+"\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_LINK,textMsg);
    }
    public void CloseLink(){
        DingtalkInform();
        String msg = "[关闭连接]" + "\n UL:"+ConfInfo.Uname+"\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_LINK,textMsg);
    }

    public void LiveLive(){
        DingtalkInform();
        String msg = "[直播开始]" + "\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_START,textMsg);



    }
    public void LivePreparing(){
        DingtalkInform();
        String msg = "[直播结束]" + "\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_START,textMsg);
    }

    public void LiveLogin(){
        DingtalkInform();
        String msg = "[未登录]" + "\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_START,textMsg);
    }

    public void chatAdmin(String m,String uanme){
        DingtalkInform();
        String msg = "[呼叫作者]" + "\n信息：\n"+uanme+":"+m+"\n\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_START,textMsg);



    }
    public void chatUp(String m){
        DingtalkInform();
        String msg = "[呼叫Up]" + "\n信息："+m+"\n\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_START,textMsg);



    }
    public void bannedNotice(String m){
        DingtalkInform();
        String msg = "[禁言通知]" + "\n信息：\n"+m+"，已将感谢关闭"+"\n\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = Msg(msg);
        Send(WEBHOOK_TOKEN_START,textMsg);



    }

    public void Agreement(String type){
        DingtalkInform();
        try {
            String msg = "[授权]" + "\n PC: "+ InetAddress.getLocalHost().getHostName()+"\n Mac: "+ ConfInfo.MacAddress +"\n IP: "+ ConfInfo.IpAddress +"\n Type: "+type;
            String textMsg = Msg(msg);
            HttpClient.sendPostJson(WEBHOOK_TOKEN_OPEM,textMsg);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    public void Send(String WEBHOOK_TOKEN_START,String textMsg){
        if(!(new AgreementUtil().readFile().equals("NO"))){
            HttpClient.sendPostJson(WEBHOOK_TOKEN_START,textMsg);
        }

    }
}


