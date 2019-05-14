package com.TerBiliLive.Info;

import com.TerBiliLive.TerBiliLive.SendGet;
import com.TerBiliLive.TerBiliLive.SendPost;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LiveRoom {

    static String LiveRoomURL = "https://api.live.bilibili.com/room/v1/Room/room_init?id=";
    String xml ="";
    String ReturnData = null;
    public static String room_id="";
    public static String uid="";
    public LiveRoom(String Cid) {


        //创建Post提交对象
        ConfInfo.sendGet = new SendGet();
        //获取返回值
        ReturnData = ConfInfo.sendGet.sendGet(LiveRoomURL + Cid);
        System.out.println(ReturnData);
        JSONObject AllData = null;
        try {
            AllData = new JSONObject(ReturnData);
            JSONObject Data = AllData.getJSONObject("data");
            room_id = Data.getString("room_id");
            uid= Data.getString("uid");
        } catch (JSONException e) {
            System.out.println("获取房间真实ID失败");
            e.printStackTrace();
        }

    }
}
