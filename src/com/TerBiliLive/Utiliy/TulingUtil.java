package com.TerBiliLive.Utiliy;

import com.TerBiliLive.Info.ConfInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.portable.ResponseHandler;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



public class TulingUtil {

    TulingUtil(){

    }
    public static String chat(String msg,String TulingApikey){

        if(msg==null||msg.equals(""))return "msgnull";
        if(TulingApikey==null||TulingApikey.equals(""))return "TulingApikeynull";

        String text="";
        String code="";
        JSONObject all = new JSONObject();
        JSONObject perception = new JSONObject();
        JSONObject inputText = new JSONObject();
        JSONObject userInfo = new JSONObject();

        try {
            userInfo.put("apiKey",TulingApikey);
            userInfo.put("userId","ter");

            inputText.put("text",msg);
            perception.put("inputText",inputText);

            all.put("userInfo",userInfo);
            all.put("perception",perception);


        } catch (JSONException e) {
            System.out.println("整合Json出错");
            e.printStackTrace();
        }
        try {
            System.out.println(all.toString());
            String aaa =ConfInfo.sendPost.doJsonPost("http://openapi.tuling123.com/openapi/api/v2",all.toString());
            System.out.println(aaa);
            JSONObject result = new JSONObject(aaa);

            JSONArray results = result.getJSONArray("results");
            JSONObject resultss=results.getJSONObject(0);
            JSONObject values =resultss.getJSONObject("values");
            text =values.getString("text");

            JSONObject intent =result.getJSONObject("intent");
            code = intent.getString("code");



        } catch (JSONException e) {
            System.out.println("解析Json出错");
            e.printStackTrace();
        }

        if(code.equals("4007"))return "TerGGKey";
//        if(code.equals("")||!code.equals("10004"))return "TerGG";


        return text;
    }
//
//    //发送JSON字符串 如果成功则返回成功标识。
//    public static String doJsonPost(String urlPath, String Json) {
//        // HttpClient 6.0被抛弃了
//        String result = "";
//        BufferedReader reader = null;
//        try {
//            URL url = new URL(urlPath);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setUseCaches(false);
//            conn.setRequestProperty("Connection", "Keep-Alive");
//            conn.setRequestProperty("Charset", "UTF-8");
//            // 设置文件类型:
//            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
//            // 设置接收类型否则返回415错误
//            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
//            conn.setRequestProperty("accept","application/json");
//            // 往服务器里面发送数据
//            if (Json != null ) {
//                byte[] writebytes = Json.getBytes("UTF-8");
//                // 设置文件长度
//                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
//                OutputStream outwritestream = conn.getOutputStream();
//                outwritestream.write(Json.getBytes("UTF-8"));
//                outwritestream.flush();
//                outwritestream.close();
//                System.out.println( "doJsonPost: conn"+conn.getResponseCode());
//            }
//            if (conn.getResponseCode() == 200) {
//                reader = new BufferedReader(
//                        new InputStreamReader(conn.getInputStream(),"UTF-8"));
//                result = reader.readLine();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return result;
//    }




}
