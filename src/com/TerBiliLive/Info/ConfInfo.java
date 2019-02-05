package com.TerBiliLive.Info;

import com.TerBiliLive.Data.ConfData;
import com.TerBiliLive.Function.Control_Fun;
import com.TerBiliLive.Function.GG_Fun;
import com.TerBiliLive.Function.HFJ_Fun;
import com.TerBiliLive.TerBiliLive.GetLiveRoomUserInfo;
import com.TerBiliLive.TerBiliLive.SendBarrage;
import com.TerBiliLive.TerBiliLive.SendPost;
import com.TerBiliLive.Thr.*;
import com.TerBiliLive.Ui.*;
import com.TerBiliLive.Utiliy.*;

import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import java.util.*;

public class ConfInfo {

    public static int isReConnSum = 0;
    public static String Thank = "no";
    public static String SEND_GIFT ="";
    public static String cookie = "";
    public static String Rnd="";
    public static String Uid = "";
    public static String Uname = "";
    public static String RoomLive = "NO";//705952
    public static String sendBarrageUrl = "https://api.live.bilibili.com/msg/send";
    public static String LiveInfoURL = "https://api.live.bilibili.com/api/player?platform=pc&player_type=web&id=cid:";
    public static String LiveRoomUserInfoURL = "https://api.live.bilibili.com/room_ex/v1/RoomNews/get?roomid=";
    public static SendPost sendPost = null;
    public static LiveInfo liveInfo = null;
    public static LiveRoom liveRoom =null;
    public static SendBarrage sendBarrage = null;
    public static TerBiliLive_Control_Ui terBiliLive_control_ui = null;
    public static TerBiliLive_ChargeBarrage_Ui terBiliLive_chargeBarrage_ui = null;
    public static TerBiliLive_Adv_Ui terBiliLive_adv_ui = null;
    public static TerBiliLive_SendBarrage_Ui terBiliLive_sendBarrage_ui = null;
    public static TerBiliLive_Ui terBiliLive_ui = null;
    public static TerBiliLive_Login_Ui terBiliLive_login_ui = null;
    public static GetLiveRoomUserInfo getLiveRoomUserInfo=null;
    public static Control_Fun control_fun = null;
    public static GG_Fun gg_fun = null;
    public static HFJ_Fun hfj_fun = null;
    public static PutShowUtil putShowUtil;
    public static String Upper_barrage;
    public static long Upper_barrage_time;
    public static Boolean GetSendBarrageList_Thr_Size = false;
    public static DingtalkUtil dingtalkUtil = new DingtalkUtil();

    public static ConfData confData= new ConfData();
    public static XmlUtil xmlUtil =new XmlUtil();
    public static JsonUtil jsonUtil = new JsonUtil();


    public static GetSendBarrageList_Thr GBT;
    public static SendBarrage_Thr SBLT = new SendBarrage_Thr();
    public static ParsingBarrage_Thr PBT;
    public static SendAdvertising_Thr sendAdvertising_thr =null;
    public static Map<String,Integer> lt_lt = new HashMap();
    public static Map<String,Integer> pig_boom = new HashMap();
    public static Map<String,Presents> integrated = new HashMap();
//    public static GetSendBarrageList_Thr getSendBarrageList_thr =new GetSendBarrageList_Thr();

    private final String CID_INFO_URL = "http://live.bilibili.com/api/player?id=cid:";
    private final String DEFAULT_COMMENT_HOST = "livecmt-1.bilibili.com";

    public static List<String> SendBarrageList = new ArrayList<String>();
    public static List<String> ChargeBarrageList = new ArrayList<String>();

    public static List<String> ParsingBarrageList = new ArrayList<String>();



    public static ParsePutBarrageUtil PPutBUtil = new ParsePutBarrageUtil(); // 解析弹幕类型显示不同颜色
    public static ColorUtil colorUtil = new ColorUtil(); // 解析弹幕类型显示不同颜色
    public static Document docs = terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.getDocument();
    public static StyledDocument docsS = terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.getStyledDocument();
    public static SpicyIntegration_Thr spicyIntegration_thr =null;

}
