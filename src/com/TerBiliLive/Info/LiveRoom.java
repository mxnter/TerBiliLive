package com.TerBiliLive.Info;

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
    public LiveRoom(String Cid) {

        try {

            // 创建url资源
            URL url = new URL(LiveRoomURL + Cid);
            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);

            conn.setDoInput(true);

            // 设置不用缓存
            conn.setUseCaches(true);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            // 转换为字节数组
            byte[] data = xml.getBytes();
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            // 设置文件类型:
            conn.setRequestProperty("contentType", "text/xml");
            // 开始连接请求
            conn.connect();
            OutputStream out = conn.getOutputStream();
            // 写入请求的字符串
            out.write(data);
            out.flush();
            out.close();

            // System.out.println(conn.getResponseCode());

            // 请求返回的状态
            if (conn.getResponseCode() == 200) {
                // System.out.println("获取直播间状态");
                // 请求返回的数据
                InputStream in = conn.getInputStream();
                ReturnData = null;
                try {
                    byte[] data1 = new byte[in.available()];
                    in.read(data1);
                    // 转成字符串
                    ReturnData = new String(data1);
                    // System.out.println("获取成功");
                    // outLog(getFormatDay(), getFormatHour(), "定位日志 " + "\t 返回值：" + ReturnData +
                    // "\n");
                    JSONObject AllData = new JSONObject(ReturnData);
                    JSONObject Data = AllData.getJSONObject("data");

                    room_id =Data.getString("room_id");

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {

            }

        } catch (Exception e) {

        }

    }
}
