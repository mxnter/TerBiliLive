package com.TerBiliLive.Data;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.TerBiliLive.HttpClient;
import com.TerBiliLive.Utils.LogUtil;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONException;
import org.json.JSONObject;

import static com.TerBiliLive.Utils.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utils.TimeUtil.getFormatHour;

public class UserInfos {
    String face;
    String uname;
    String mid;
    String current_level;
    String money;
    String current_exp;

    public UserInfos() {
        String returnData = HttpClient.sendGet(ConfInfo.BiliServer_nav, ConfInfo.confData.getCookie());
        System.out.println(returnData);
        try {
            JSONObject Return = new JSONObject(returnData);
            JSONObject data = Return.getJSONObject("data");
            JSONObject level_info = data.getJSONObject("level_info");
            if (data.getString("code").equals("0")) {
//                imageNode.setImage(new Image(data.getString("face")));
                uname = data.getString("uname");
                mid = data.getString("mid");
                current_level = level_info.getString("current_level");
                money = data.getString("money");
                current_exp = level_info.getString("current_exp");
            } else {
                LogUtil.putLog(getFormatDay(), getFormatHour(), "获取服务器接口数据信息异常 ：" + returnData + "\n", "Exception", "Exception");
                System.out.println("获取服务器接口数据信息异常 ：" + returnData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public UserInfos(String uname, String mid, String current_level, String money, String current_exp) {
        this.uname = uname;
        this.mid = mid;
        this.current_level = current_level;
        this.money = money;
        this.current_exp = current_exp;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCurrent_level() {
        return current_level;
    }

    public void setCurrent_level(String current_level) {
        this.current_level = current_level;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCurrent_exp() {
        return current_exp;
    }


}
