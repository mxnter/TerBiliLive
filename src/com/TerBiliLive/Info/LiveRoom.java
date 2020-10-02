package com.TerBiliLive.Info;

import com.TerBiliLive.TerBiliLive.HttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import com.TerBiliLive.Utils.InOutPutUtil;

public class LiveRoom {

    static String LiveRoomURL = "https://api.live.bilibili.com/room/v1/Room/room_init?id=";
    String xml ="";
    String ReturnData = null;
    public String room_id="";
    public String uid="";
    public LiveRoom() {
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void getLiveRoom(String Cid){
        //获取返回值
        ReturnData = HttpClient.sendGet(LiveRoomURL + Cid);
        InOutPutUtil.outPut(ReturnData);
        JSONObject AllData = null;
        try {
            AllData = new JSONObject(ReturnData);
            JSONObject Data = AllData.getJSONObject("data");
            room_id = Data.getString("room_id");
            uid= Data.getString("uid");
        } catch (JSONException e) {
            InOutPutUtil.outPut("获取房间真实ID失败");
            e.printStackTrace();
        }
    }

    public String isLiveRoom(String Cid){
        //获取返回值
        ReturnData = HttpClient.sendGet(LiveRoomURL + Cid);
        InOutPutUtil.outPut(ReturnData);
        JSONObject AllData = null;
        try {
            AllData = new JSONObject(ReturnData);
            int code = AllData.getInt("code");
            if(code==0){
                JSONObject Data = AllData.getJSONObject("data");
                room_id = Data.getString("room_id");
                uid= Data.getString("uid");
                return null;
            }else{
                return AllData.getString("msg");
            }

        } catch (JSONException e) {
            InOutPutUtil.outPut("获取房间真实ID失败");
            e.printStackTrace();
            return "房间不存在";
        }
    }
}
