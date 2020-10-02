package com.TerBiliLive.TerBiliLive;

import com.TerBiliLive.Info.ConfInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetLiveRoomUserInfo {

    String RoomUseruid ="";
    String RoomUseruname="";
    String LiveContent=""; //直播公告
    Map<String, String> paramMap = new HashMap<String, String>();
    public GetLiveRoomUserInfo() {
        String url = ConfInfo.LiveRoomUserInfoURL;
        ConfInfo.liveRoom.getLiveRoom(ConfInfo.barrage.getRoomid());
        String getUserInfoData= HttpClient.sendGet(url+ConfInfo.liveRoom.getRoom_id());
        JSONObject object = JSON.parseObject(getUserInfoData);
        RoomUseruid=object.getJSONObject("data").getString("uid");
        RoomUseruname =object.getJSONObject("data").getString("uname");
        LiveContent =object.getJSONObject("data").getString("content");

    }

    public String getRoomUseruid() {
        return RoomUseruid;
    }

    public void setRoomUseruid(String roomUseruid) {
        RoomUseruid = roomUseruid;
    }

    public String getRoomUseruname() {
        return RoomUseruname;
    }

    public void setRoomUseruname(String roomUseruname) {
        RoomUseruname = roomUseruname;
    }

    public String getLiveContent() {
        return LiveContent;
    }

    public void setLiveContent(String liveContent) {
        LiveContent = liveContent;
    }
}
