package com.TerBiliLive.Info.Nav;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveRoomInfo;
import com.TerBiliLive.TerBiliLive.HttpClient;
import com.TerBiliLive.Utils.InOutPutUtil;
import com.TerBiliLive.Utils.LogUtil;
import com.alibaba.fastjson.JSON;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 直播间信息
 */
public class LiveRoomInfoNav {

    public LiveRoomInfo getLiveRoomInfo(String id) {
        String returnData = HttpClient.sendGet(ConfInfo.LiveRoomInfoOldURL+id,ConfInfo.confData.getCookie());
        InOutPutUtil.outPut(returnData);

        try {
            JSONObject data = new JSONObject(returnData);

            switch (data.getString("code")){
                case "0":{
                    // json 转实体类
                    String strResult = data.getJSONObject("data").toString();
                    LiveRoomInfo liveRoomInfo = JSON.parseObject(JSON.parse(strResult).toString(), LiveRoomInfo.class);
                    return liveRoomInfo;
                }
                case "-101":{
                    return null;
                }
                default:{
                    InOutPutUtil.outPut("直播间信息信息异常 ："+returnData);
                    LogUtil.putLogException("直播间信息信息异常 ："+returnData,"UserInfoNav");
                    return null;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


}
