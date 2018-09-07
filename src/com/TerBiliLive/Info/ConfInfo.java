package com.TerBiliLive.Info;

import com.TerBiliLive.TerBiliLive.SendBarrage;
import com.TerBiliLive.TerBiliLive.SendPost;

import java.util.Map;

public class ConfInfo {

    public static int isReConnSum = 0;
    public static String cookie = "";
    public static String RoomLive = "NO";//705952
    public static String sendBarrageUrl = "http://live.bilibili.com/msg/send";
    public static String LiveInfoURL = "https://api.live.bilibili.com/api/player?platform=pc&player_type=web&id=cid:";
    public static SendPost sendPost = null;
    public static LiveInfo liveInfo = null;
    public static SendBarrage sendBarrage = null;
    private final String CID_INFO_URL = "http://live.bilibili.com/api/player?id=cid:";
    private final String DEFAULT_COMMENT_HOST = "livecmt-1.bilibili.com";


}
