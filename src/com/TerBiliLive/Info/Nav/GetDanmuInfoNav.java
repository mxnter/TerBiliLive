package com.TerBiliLive.Info.Nav;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.DanmuInfo.DanmuInfo;
import com.TerBiliLive.Info.UserInfo.UserInfo;
import com.TerBiliLive.TerBiliLive.HttpClient;
import com.TerBiliLive.Utils.InOutPutUtil;
import com.TerBiliLive.Utils.LogUtil;
import com.alibaba.fastjson.JSON;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 获取连接弹幕服务器的服务器地址和token
 * @author myetear
 */
public class GetDanmuInfoNav {
//    String returnData;
//    String strResult;
//    DanmuInfo danmuInfo;

    public DanmuInfo getDanmuInfoNav(String roomID,String cookie){
        String returnData;
        if(null != ConfInfo.liveUserInfo|| cookie.equals("")){
            returnData = HttpClient.sendGet(ConfInfo.GetDanmuInfoURL + roomID,cookie);
        }else{
            returnData = HttpClient.sendGet(ConfInfo.GetDanmuInfoURL + roomID);
        }
        InOutPutUtil.outPut(returnData);
        try {
            JSONObject data = new JSONObject(returnData);
            switch (data.getString("code")){
                case "0":{
                    // json 转实体类
                    String strResult = data.getString("data");
                    return JSON.parseObject(JSON.parse(strResult).toString(), DanmuInfo.class);
                }
                default:{
                    InOutPutUtil.outPut("获取连接服务器token信息异常 ："+returnData);
                    LogUtil.putLogException("获取连接服务器token信息异常 ："+returnData);
                    return null;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
