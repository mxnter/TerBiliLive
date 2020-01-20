package com.TerBiliLive.Info;

import com.TerBiliLive.TerBiliLive.HttpClient;
import com.TerBiliLive.Utiliy.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

import static com.TerBiliLive.Utiliy.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatHour;

/**
 * CODE IS POETRY
 *
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 20:28 2019/4/13
 */
public class InfoNew {
    String addr = null;//"112.40.75.248"
    String city = null;//"大连"
    String country = null;//"中国"
    String isp = null;//"移动"
    String latitude = null;//"38.91459"
    String longitude = null;//"121.618622"
    String province = null;//"辽宁"

    public String ReturnData = null;

    public InfoNew(String cookie) {
        ReturnData =  HttpClient.sendGet(ConfInfo.InfoNewURL, cookie);
        System.out.println(ReturnData);
        try {
            JSONObject data = new JSONObject(ReturnData);
            JSONObject jsonObject = data.getJSONObject("data");
            if (data.getString("code").equals("0")) {
                addr = jsonObject.getString("addr");
                city = jsonObject.getString("city");
                country = jsonObject.getString("country");
                isp = jsonObject.getString("isp");
                latitude = jsonObject.getString("latitude");
                longitude = jsonObject.getString("longitude");
                province = jsonObject.getString("province");
            } else {
                LogUtil.putLog(getFormatDay(), getFormatHour(), "获取服务器接口数据信息异常 ：" + ReturnData + "\n", "Exception", "Exception");
                System.out.println("获取服务器接口数据信息异常 ：" + ReturnData);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getReturnData() {
        return ReturnData;
    }

    public void setReturnData(String returnData) {
        ReturnData = returnData;
    }
}

