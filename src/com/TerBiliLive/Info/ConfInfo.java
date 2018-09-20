package com.TerBiliLive.Info;

import com.TerBiliLive.Data.ConfData;
import com.TerBiliLive.Function.Control_Fun;
import com.TerBiliLive.Function.GG_Fun;
import com.TerBiliLive.Function.HFJ_Fun;
import com.TerBiliLive.TerBiliLive.GetLiveRoomUserInfo;
import com.TerBiliLive.TerBiliLive.SendBarrage;
import com.TerBiliLive.TerBiliLive.SendPost;
import com.TerBiliLive.Ui.*;
import com.TerBiliLive.Utiliy.PutShowUtil;
import com.TerBiliLive.Utiliy.XmlUtil;

import java.util.ArrayList;
import java.util.List;

public class ConfInfo {

    public static int isReConnSum = 0;
    public static String Thank = "no";
    public static String cookie = "";
    public static String Rnd="";
    public static String Uid = "";
    public static String Uname = "";
    public static String RoomLive = "NO";//705952
    public static String sendBarrageUrl = "http://live.bilibili.com/msg/send";
    public static String LiveInfoURL = "https://api.live.bilibili.com/api/player?platform=pc&player_type=web&id=cid:";
    public static String LiveRoomUserInfoURL = "https://api.live.bilibili.com/room_ex/v1/RoomNews/get?roomid=";
    public static SendPost sendPost = null;
    public static LiveInfo liveInfo = null;
    public static LiveRoom liveRoom =null;
    public static SendBarrage sendBarrage = null;
    public static TerBiliLive_Control_Ui terBiliLive_control_ui = null;
    public static TerBiliLive_DMJ_Ui terBiliLive_dmj_ui = null;
    public static TerBiliLive_GG_Ui terBiliLive_gg_ui = null;
    public static TerBiliLive_HFJ_Ui terBiliLive_hfj_ui = null;
    public static TerBiliLive_Ui terBiliLive_ui = null;
    public static TerBiliLive_Login_Ui terBiliLive_login_ui = null;
    public static GetLiveRoomUserInfo getLiveRoomUserInfo=null;
    public static Control_Fun control_fun = null;
    public static GG_Fun gg_fun = null;
    public static HFJ_Fun hfj_fun = null;
    public static PutShowUtil putShowUtil;
    public static String Upper_barrage;

    public static ConfData confData= new ConfData();
    public static XmlUtil xmlUtil =new XmlUtil();

    private final String CID_INFO_URL = "http://live.bilibili.com/api/player?id=cid:";
    private final String DEFAULT_COMMENT_HOST = "livecmt-1.bilibili.com";

    public static List<String> SendBarrageList = new ArrayList<String>();


}
