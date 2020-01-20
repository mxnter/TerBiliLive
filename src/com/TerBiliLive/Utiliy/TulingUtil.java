package com.TerBiliLive.Utiliy;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.TerBiliLive.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.portable.ResponseHandler;

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
            String aaa = HttpClient.sendPostJson("http://openapi.tuling123.com/openapi/api/v2",all.toString());
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





}
