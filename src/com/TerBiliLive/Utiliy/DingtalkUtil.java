package com.TerBiliLive.Utiliy;


import com.TerBiliLive.Info.ConfInfo;

public class DingtalkUtil {

    public DingtalkUtil(){

    }

   public void LiveLive(){
        String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=65802d93f15daa2b8574fb3b3656e81caa115c8795acb9d56dedf533a842ee3f";
        String msg = "[直播开始]" + "\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \""+msg+"\"}}";
        System.out.println(ConfInfo.sendPost.doJsonPost(WEBHOOK_TOKEN,textMsg));



    }
    public void LivePreparing(){
        String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=65802d93f15daa2b8574fb3b3656e81caa115c8795acb9d56dedf533a842ee3f";
        String msg = "[直播结束]" + "\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \""+msg+"\"}}";
        System.out.println(ConfInfo.sendPost.doJsonPost(WEBHOOK_TOKEN,textMsg));



    }

    public void chatAdmin(String m,String uanme){
        String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=65802d93f15daa2b8574fb3b3656e81caa115c8795acb9d56dedf533a842ee3f";
        String msg = "[呼叫作者]" + "\n信息：\n"+uanme+":"+m+"\n\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \""+msg+"\"}}";
        System.out.println(ConfInfo.sendPost.doJsonPost(WEBHOOK_TOKEN,textMsg));



    }
    public void chatUp(String m){
        String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=65802d93f15daa2b8574fb3b3656e81caa115c8795acb9d56dedf533a842ee3f";
        String msg = "[呼叫Up]" + "\n信息："+m+"\n\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \""+msg+"\"}}";
        System.out.println(ConfInfo.sendPost.doJsonPost(WEBHOOK_TOKEN,textMsg));



    }
    public void bannedNotice(String m){
        String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=65802d93f15daa2b8574fb3b3656e81caa115c8795acb9d56dedf533a842ee3f";
        String msg = "[禁言通知]" + "\n信息：\n"+m+"，已将感谢关闭"+"\n\n UP:"+ConfInfo.getLiveRoomUserInfo.getRoomUseruname()+"\n RoomId:"+ConfInfo.liveRoom.room_id +"\n 直播地址:"+"https://live.bilibili.com/"+ConfInfo.liveRoom.room_id;
        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \""+msg+"\"}}";
        System.out.println(ConfInfo.sendPost.doJsonPost(WEBHOOK_TOKEN,textMsg));



    }
}


