package com.TerBiliLive.TerBiliLive;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveRoom;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetLiveRoomUserInfo {

    String RoomUseruid ="";
    String RoomUseruname="";
    Map<String, String> paramMap = new HashMap<String, String>();
    public GetLiveRoomUserInfo() {
        String url = ConfInfo.LiveRoomUserInfoURL;
        ConfInfo.liveRoom=new LiveRoom(ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText().toString());
        String getUserInfoData= HttpClient.sendGet(url+ConfInfo.liveRoom.room_id);
        JSONObject object = JSON.parseObject(getUserInfoData);
        RoomUseruid=object.getJSONObject("data").getString("uid");
        RoomUseruname =object.getJSONObject("data").getString("uname");

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
}
