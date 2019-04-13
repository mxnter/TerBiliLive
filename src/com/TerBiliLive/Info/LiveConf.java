package com.TerBiliLive.Info;

import com.TerBiliLive.TerBiliLive.SendPost;
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
public class LiveConf {
    String host=null;//"broadcastlv.chat.bilibili.com"
    String host_server_list=null;//[{host: "tx-sh3-live-comet-03.chat.bilibili.com", port: 2243, wss_port: 443, ws_port: 2244},…]
    String max_delay=null;//5000
    int port=0;//2243
    String refresh_rate=null;//100
    String refresh_row_factor=null;//0.125
    String server_list=null;//[{host: "172.81.242.35", port: 2243}, {host: "62.234.202.242", port: 2243},…]
    String token=null;//"GSNTlFkqCpZX4i//wREv4E1kQsJ+VgR0rDe6JlG5Eu1h

    public String ReturnData = null;

    public LiveConf(String Cid,String cookie) {
        ConfInfo.sendPost = new SendPost();
        ReturnData = ConfInfo.sendGet.sendGet(ConfInfo.LiveConfURL+Cid,cookie);
        System.out.println(ReturnData);
        try {
            JSONObject data = new JSONObject(ReturnData);
            JSONObject jsonObject = data.getJSONObject("data");
            if(data.getString("code").equals("0")){
                host = jsonObject.getString("host");
                host_server_list = jsonObject.getString("host_server_list");
                max_delay = jsonObject.getString("max_delay");
                port = Integer.parseInt(jsonObject.getString("port"));
                refresh_rate = jsonObject.getString("refresh_rate");
                refresh_row_factor = jsonObject.getString("refresh_row_factor");
                server_list = jsonObject.getString("server_list");
                token = jsonObject.getString("token");
            }else{
                LogUtil.putLog(getFormatDay(), getFormatHour(), "获取服务器接口数据信息异常 ："+ReturnData+ "\n", "Exception","Exception");
                System.out.println("获取服务器接口数据信息异常 ："+ReturnData);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost_server_list() {
        return host_server_list;
    }

    public void setHost_server_list(String host_server_list) {
        this.host_server_list = host_server_list;
    }

    public String getMax_delay() {
        return max_delay;
    }

    public void setMax_delay(String max_delay) {
        this.max_delay = max_delay;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getRefresh_rate() {
        return refresh_rate;
    }

    public void setRefresh_rate(String refresh_rate) {
        this.refresh_rate = refresh_rate;
    }

    public String getRefresh_row_factor() {
        return refresh_row_factor;
    }

    public void setRefresh_row_factor(String refresh_row_factor) {
        this.refresh_row_factor = refresh_row_factor;
    }

    public String getServer_list() {
        return server_list;
    }

    public void setServer_list(String server_list) {
        this.server_list = server_list;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
