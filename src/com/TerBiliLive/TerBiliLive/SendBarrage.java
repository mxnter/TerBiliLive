package com.TerBiliLive.TerBiliLive;

import com.TerBiliLive.Info.ConfInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SendBarrage {
    String url = "http://live.bilibili.com/msg/send";
    Map<String, String> paramMap = new HashMap<String, String>();

    public SendBarrage() {
    }

    public String SendBarrage(String roomid,String cookie,String msg){

        String result = null;
        url = ConfInfo.sendBarrageUrl;
        try {
            //转换字符串到URLEncoder
            System.out.println("SendBarrage :"+msg);
            msg =URLEncoder.encode(msg, "UTF-8");
            System.out.println("SendBarrage : "+msg);
            //封装弹幕消息
            paramMap.put("msg", msg);
            //封装房间号
            paramMap.put("roomid", roomid);
            //随机生成rnd
            int x=(int)(Math.random()*1000000);
            //封装Rnd
            paramMap.put("rnd", Integer.toString(x));
            //创建Post提交对象
            ConfInfo.sendPost = new SendPost();
            //获取返回值
            result = ConfInfo.sendPost.SendPost(url,paramMap,cookie);

        } catch (UnsupportedEncodingException e) {
            System.out.println("SendBarrage : 转换字符出错 "+msg);
            e.printStackTrace();
        }

        //回收资源
        ConfInfo.sendPost = null;
        return result;
    }

}
