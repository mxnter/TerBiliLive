package com.TerBiliLive.Info;

import com.TerBiliLive.Data.ConfData;
import com.TerBiliLive.Function.Control_Fun;
import com.TerBiliLive.TerBiliLive.Dingtalk;
import com.TerBiliLive.TerBiliLive.GetLiveRoomUserInfo;
import com.TerBiliLive.TerBiliLive.SendBarrage;
import com.TerBiliLive.Thr.*;
import com.TerBiliLive.Ui.*;
import com.TerBiliLive.Utils.*;

import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ConfInfo {




    public static String Version = "Beta06[D."+ TerBiliLive_ChargeBarrage_Ui.Version+"]-[H."+ TerBiliLive_SendBarrage_Ui.Version+"]-[G."+ TerBiliLive_Adv_Ui.Version+"]"+"-[L."+ TerBiliLive_Login_Ui.Version+"]";
    public static int VersionNum = 202001190;

    // System Info
    public static String MacAddress = null;
    public static String IpAddress = null;

    public static final boolean dev = false;
    public static final HashMap<String, String> AppName = new HashMap<String, String>();
    static { AppName.put("A", "TerBiliAssistant");AppName.put("L", "TerBiliLive"); }
    public static final HashMap<String,String> AppViceName = new HashMap<String, String>();
    static { AppViceName.put("A", "BiliBili 智能助手 By.Mxnter");AppViceName.put("L", "哔哩哔哩直播弹幕姬 By.Mxnter"); }
    public static final String AppSystemId = "A";
    public static final String AppViceNameId = "A";
    public static final String AppVersion = "Beta 7";
    public static final String AppVersionBuildNum = "00001";
    public static final int AppVersionNum = 202001190;

    //Server
    public static final String AppServerUrl = "http://uxj608.coding-pages.com";
    public static final String AppServerVersion = AppServerUrl+"/Networking/Version.json";
    public static final String AppServerMsg = AppServerUrl+"/Networking/msg.json";

    public static final String DatabaseInternalPath = "../assets/terbili";
    public static final String DatabaseExternalPath = "Ter/database/terbili";

    // jdbc
    public static final String JDBC_URL = "jdbc:sqlite:Ter/database/terbili";
    public static final String JDBC_USERNAME = "test";
    public static final String JDBC_PASSWORD = "test";
    public static final String JDBC_DRIVER = "org.sqlite.JDBC";
    public static DatabaesUtil databaesUtil;


    //BiliServer
    public static String BiliServer ="https://api.bilibili.com/";
    public static String BiliServer_nav = BiliServer + "x/web-interface/nav";
    //BiliLiveServer
    public static String BiliLiveServer ="https://api.live.bilibili.com/";
    public static String BiliLiveServer_doSign= BiliLiveServer + "sign/doSign";


    public static LiveUserInfo liveUserInfo = null;

    public static SystemTray systemTray = SystemTray.getSystemTray();


    public static int isReConnSum = 0;
    public static String Thank = "no";
    public static String SEND_GIFT ="";
    public static String cookie = "";
    public static String Rnd="";
    public static String Uid = "";
    public static String Uname = "";
//    public static String RoomLive = "NO";//705952
    public static String sendBarrageUrl = "https://api.live.bilibili.com/msg/send";
    public static String LiveInfoURL = "https://api.live.bilibili.com/api/player?platform=pc&player_type=web&id=cid:";
    public static String UserInfoURL = "https://api.live.bilibili.com/xlive/web-ucenter/user/get_user_info";
    public static String LiveConfURL = "https://api.live.bilibili.com/room/v1/Danmu/getConf?room_id=";
    public static String InfoNewURL = "https://api.live.bilibili.com/client/v1/Ip/getInfoNew";
    public static String LiveRoomUserInfoURL = "https://api.live.bilibili.com/room_ex/v1/RoomNews/get?roomid=";
//    public static LiveInfo liveInfo = null; //TODO b站更改接口 停用 20190412

    public static LiveConf liveConf = null;
    public static InfoNew infoNew = null;
    public static LiveRoom liveRoom =null;
    public static SendBarrage sendBarrage = null;
    public static TerBiliLive_Control_Ui terBiliLive_control_ui = null;
    public static TerBiliLive_ChargeBarrage_Ui terBiliLive_chargeBarrage_ui = null;
    public static TerBiliLive_Adv_Ui terBiliLive_adv_ui = null;
    public static TerBiliLive_SendBarrage_Ui terBiliLive_sendBarrage_ui = null;
    public static TerBiliLive_Ui terBiliLive_ui = null;
//    public static TerBiliLive_Login_Ui terBiliLive_login_ui = null;
    public static GetLiveRoomUserInfo getLiveRoomUserInfo=null;
    public static Control_Fun control_fun = new Control_Fun();
//    public static GG_Fun gg_fun = null;
//    public static HFJ_Fun hfj_fun = null;
    public static PutShowUtil putShowUtil;
    public static SubStringUtil subStringUtil = new SubStringUtil();
    public static String Upper_barrage;
    public static long Upper_barrage_time;
//    public static Boolean GetSendBarrageList_Thr_Size = false;
    public static Dingtalk dingtalk = new Dingtalk();

    public static ConfData confData= new ConfData();
//    public static XmlUtil xmlUtil =new XmlUtil(); //关闭 xData.terda
    public static JsonUtil jsonUtil = new JsonUtil();
//    public static AESUtil aesUtil = new AESUtil();


    public static GetSendBarrageList_Thr GBT;
    public static SendBarrage_Thr SBLT = new SendBarrage_Thr();
    public static ParsingBarrage_Thr PBT;
    public static SendAdvertising_Thr sendAdvertising_thr =null;
    public static Map<String,Integer> lt_lt = new HashMap();
    public static Map<String,Integer> pig_boom = new HashMap();
    public static Map<String,Presents> integrated = new HashMap();
//    public static GetSendBarrageList_Thr getSendBarrageList_thr =new GetSendBarrageList_Thr();

//    private final String CID_INFO_URL = "http://live.bilibili.com/api/player?id=cid:";
//    private final String DEFAULT_COMMENT_HOST = "livecmt-1.bilibili.com";

    public static List<SendBarrageMap> SendBarrageList = new ArrayList<SendBarrageMap>();
    public static List<String> ChargeBarrageList = new ArrayList<String>();
    public static List<String> ParsingBarrageList = new ArrayList<String>();



//    public static ParsePutBarrageUtil PPutBUtil = new ParsePutBarrageUtil(); // 解析弹幕类型显示不同颜色
//    public static ColorUtil colorUtil = new ColorUtil(); // 解析弹幕类型显示不同颜色
    public static Document docs = terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.getDocument();
    public static StyledDocument docsS = terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.getStyledDocument();
//    public static SpicyIntegration_Thr spicyIntegration_thr =null;

    // Dingtalk Robot Webhook
    public static String DingtalkWebhookStart = "s945Q0A+q3VWvmDSV27I4XNmPnEzpb+9y6298m2MnQvGzTCE8ciaYlQktDkNF3/xn+85ebPuEAmyyJxGzMEIdlQoZOPE0P4fS5HXTjqtN+uNkXMhlgEcQbtY8nuqoWOFXB/1+pDZ8Da6HSj1ugJngF9auE18oBvkA6ru2UHRJtc=";
    public static String DingtalkWebhookOpen = "s945Q0A+q3VWvmDSV27I4XNmPnEzpb+9y6298m2MnQvGzTCE8ciaYlQktDkNF3/xABTpARnN9l6n9bwIQZ2pwqGxZM5pxhUJ4cowU4wWvggshqgcBMsZv2Q3vmoXKsb/eXqBWPqcsVLIMYTZQeGGtbV8hEjE5Lmcn6jq9vmf40k=";
    public static String DingtalkWebhookLink = "s945Q0A+q3VWvmDSV27I4XNmPnEzpb+9y6298m2MnQvGzTCE8ciaYlQktDkNF3/xuTokoe7J6mOockxV28ZhBGHyCyxxEWQQDV1JX9qEKh0n57afBDZrOBEUBqJkGQ3VDZgznm3AC3ONNhHG1WinatUbe0dyXSqmb8UDQJw3WLo=";
    public static String DingtalkWebhookLogin = "s945Q0A+q3VWvmDSV27I4XNmPnEzpb+9y6298m2MnQvGzTCE8ciaYlQktDkNF3/xeTfhiOP9v02+hzDlAW1ML/dXiPvIZEmjuiMWXOV1uO8vCLDirDILIf8yt+0LixNqjZknNlyBQgjIcSAGrfkdfY8qWgiNVHPc7JJFGSGMqgA=";
    public static String DingtalkWebhookPK = "b244c6c6b95c002eb89da3259d8809af817415e77636c4e3e4f47c6a1944199f";
}
