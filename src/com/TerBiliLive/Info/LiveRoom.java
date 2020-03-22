package com.TerBiliLive.Info;

import com.TerBiliLive.TerBiliLive.HttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import com.TerBiliLive.Utils.InOutPutUtil;

public class LiveRoom {

    static String LiveRoomURL = "https://api.live.bilibili.com/room/v1/Room/room_init?id=";
    String xml ="";
    String ReturnData = null;
    public static String room_id="";
    public static String uid="";
    public LiveRoom(String Cid) {

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
}
