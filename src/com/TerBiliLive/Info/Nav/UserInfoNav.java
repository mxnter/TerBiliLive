package com.TerBiliLive.Info.Nav;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.UserInfo.*;
import com.TerBiliLive.TerBiliLive.HttpClient;
import com.TerBiliLive.Utils.InOutPutUtil;
import com.TerBiliLive.Utils.LogUtil;
import com.alibaba.fastjson.JSON;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * CODE IS POETRY
 *
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 19:20 2019/4/13
 */
public class UserInfoNav {

    String returnData;
    String strResult;
    UserInfo userInfo;

    public UserInfoNav() {


    }

    public UserInfoNav(String cookie) throws JSONException {
        returnData = HttpClient.sendGet(ConfInfo.UserInfoURL,cookie);
        InOutPutUtil.outPut(returnData);
        JSONObject data = new JSONObject(returnData);
        switch (data.getString("code")){
            case "0":{
                // json 转实体类
                strResult = data.getJSONObject("data").toString();
                userInfo = JSON.parseObject(JSON.parse(strResult).toString(),UserInfo.class);
                break;
            }
            case "-101":{

                break;
            }
            default:{
                InOutPutUtil.outPut("获取个人信息数据信息异常 ："+returnData);
                LogUtil.putLogException("获取个人信息数据信息异常 ："+returnData);
                break;
            }
        }
    }

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }

    public String getStrResult() {
        return strResult;
    }

    public void setStrResult(String strResult) {
        this.strResult = strResult;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
