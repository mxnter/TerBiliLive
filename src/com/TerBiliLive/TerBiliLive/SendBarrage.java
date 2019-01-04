package com.TerBiliLive.TerBiliLive;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Utiliy.TimeUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SendBarrage {
    String url = "https://api.live.bilibili.com/msg/send";
    Map<String, String> paramMap = new HashMap<String, String>();

    public SendBarrage() {
    }

    public String SendBarrage(String roomid,String cookie,String msg){

//        if(ConfInfo.terBiliLive_control_ui.Reply_XXX.isSelected()) return "{\"code\":10000,\"msg\":\"请打开发送总开关\",\"data\":[]}";

        String result = null;
        url = ConfInfo.sendBarrageUrl;
        try {
            //转换字符串到URLEncoder
            System.out.println("SendBarrage :"+msg);
            msg =URLEncoder.encode(msg, "UTF-8");
            System.out.println("SendBarrage : "+msg);
            //封装弹幕颜色
            paramMap.put("color", "16777215");
            //封装弹幕字体大小
            paramMap.put("fontsize", "25");
            //封装弹幕模式
            paramMap.put("mode", "1");
            //封装弹幕消息
            paramMap.put("msg", msg);
            //封装房间号
            paramMap.put("roomid", roomid);
            if(ConfInfo.Rnd.equals(""))ConfInfo.Rnd=TimeUtil.timeStamp();
            //封装Rnd
            paramMap.put("rnd", ConfInfo.Rnd);
            //封装csrf_token = bili_jct=
            paramMap.put("csrf_token", getSubString.getSubString(ConfInfo.cookie,"bili_jct=",";"));
            //封装csrf
            paramMap.put("csrf", getSubString.getSubString(ConfInfo.cookie,"bili_jct=",";"));
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
