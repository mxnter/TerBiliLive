package com.TerBiliLive.Info;

import com.TerBiliLive.Data.ConfData;
import com.TerBiliLive.Info.Nav.UserInfoNav;
import com.TerBiliLive.Inlet.Control_Inlet;
import com.TerBiliLive.TerBiliLive.Dingtalk;
import com.TerBiliLive.TerBiliLive.GetLiveRoomUserInfo;
import com.TerBiliLive.TerBiliLive.SendBarrage;
import com.TerBiliLive.Thr.GetSendBarrageList_Thr;
import com.TerBiliLive.Thr.ParsingBarrage_Thr;
import com.TerBiliLive.Thr.SendAdvertising_Thr;
import com.TerBiliLive.Thr.SendBarrage_Thr;
import com.TerBiliLive.Ui.Barrage;
import com.TerBiliLive.Utils.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfInfo {


    public static String Version = "Beta 7";
    public static int VersionNum = 202010020;



    // System Info
    public static String MacAddress = null;
    public static String IpAddress = null;

    public static final boolean dev = true;
    public static final boolean devDatabasePut = false;
    public static final boolean devDatabase = false;
    public static final HashMap<String, String> AppName = new HashMap<String, String>();
    static { AppName.put("A", "TerBiliAssistant");AppName.put("L", "TerBiliLive"); }
    public static final HashMap<String,String> AppViceName = new HashMap<String, String>();
    static { AppViceName.put("A", "BiliBili 智能助手 By.Mxnter");AppViceName.put("L", "哔哩哔哩直播弹幕姬 By.Mxnter"); }
    public static final String AppSystemId = "L";
    public static final String AppViceNameId = "L";
    public static final String AppVersion = "Beta 7";
    public static final String AppVersionBuildNum = "001";
    public static final int AppVersionNum = 202010020;



    //Server
    public static final String AppServerUrl = "http://s5caqz.coding-pages.com";
    public static final String AppServerHello = AppServerUrl+"/TerBiliLive/hello.json";
    public static final String AppServerVersion = AppServerUrl+"/TerBiliLive/Version.json";
    public static final String AppServerMsg = AppServerUrl+"/TerBiliLive/msg.json";
    public static final String AppServerAgreement = AppServerUrl+"/TerBiliLive/Agreement.json";

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
    public static UserInfoNav userInfoNav = null;

    public static SystemTray systemTray = SystemTray.getSystemTray();


    //Database
    public static final int AppDatabaseVersion = 2;

    public static final List<String> AppInitDatabases  = new ArrayList<>();
    static{
        AppInitDatabases.add("CREATE TABLE \"SystemInfo\"(\"Name\" varchar, \"Version\" varchar);");
        AppInitDatabases.add("INSERT INTO \"SystemInfo\" (\"Name\", \"Version\") VALUES (\"Database\", '"+AppDatabaseVersion+"')");
        AppInitDatabases.add("CREATE TABLE \"ConfData\"(\"Cookie\" text,\"RoomId\" varchar,\"Second\" varchar,\"Text\" varchar,\"TulingApiKey\" varchar);");
        AppInitDatabases.add("CREATE TABLE \"SystemLog\" (\"GenerationTime\" datetime, \"LogType\" varchar, \"Log\" text);");
        AppInitDatabases.add("CREATE TABLE BarrageInfo (time datetime,roomId varchar,cmd varchar,guard varchar,vip varchar,isadmin varchar,medal varchar,userLevel varchar,uid varchar,nickname varchar,barrage varchar,msg varchar,info text);");
        AppInitDatabases.add("CREATE TABLE BarrageNotice(time datetime,msg varchar,info text);");
        AppInitDatabases.add("CREATE TABLE BarrageLog (time datetime,cmd int, msg varchar,info text);");
    }

    public static final List<String[]> AppUpdateDatabases = new ArrayList<>();
    static {
        String sql = "UPDATE \"SystemInfo\" SET \"Version\" = "+AppDatabaseVersion+" WHERE \"Name\" = 'Database'";
        String[] sql0 = {};
        AppUpdateDatabases.add(sql0);
        String[] sql1 = {
                "CREATE TABLE \"SystemLog\" (\"GenerationTime\" datetime, \"LogType\" varchar, \"Log\" text);",
                sql
        };
        AppUpdateDatabases.add(sql1);
        String[] sql2 = {
                "CREATE TABLE \"SystemData\" (\"name\" varchar, \"data\" varchar);",
                "INSERT INTO SystemData VALUES ('roomId','')",
                "INSERT INTO SystemData VALUES ('systemState','')",
                sql
        };
        AppUpdateDatabases.add(sql2);
    }
    public static final String Database_SelectDatabaseVersion = "SELECT * FROM SystemInfo WHERE \"Name\" = 'Database'";

    public static final String Database_SelectSystemDataWhereName = "SELECT * FROM SystemData WHERE name =";

    public static final String Database_UpdateSystemDataWhereName = "UPDATE SystemData SET data = ? WHERE name = ?";

    public static final String Database_DeleteSystemLog = "DELETE FROM SystemLog WHERE GenerationTime <";

    public static final String Database_InsertDatabaseBarrage = "INSERT INTO BarrageInfo VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";



    // Timer
    public static TimerUtil doSign;
    public static TimerUtil autoSpeak;
    public static TimerUtil breathing;


    public static SystemState systemState = new SystemState();
    public static List<String> inOperationRadioGift = new ArrayList<>(); //运行中的小电视

    public static Barrage barrage = null;

    public static LiveRoom liveRoom =new LiveRoom();
    public static Control_Inlet control_inlet = new Control_Inlet();

    // 获取直播间
    public static String LiveRoomInfoOldURL = "https://api.live.bilibili.com/room/v1/Room/getRoomInfoOld?mid=";
    public static LiveRoomInfo liveRoomInfo;

    // 获取用户信息
    public static String SearchUserURL = "https://api.live.bilibili.com/banned_service/v2/Silent/search_user?search=";
    //{"code":0,"msg":"","message":"","data":{"items":[{"uid":191176177,"face":"https://i2.hdslb.com/bfs/face/df0ce20fa678a2c920d312358ee1569b3601f9be.jpg","uname":"WhaleEcns"}]}}

    //获取是否开启30字发言
    public static String isSend30URL = "https://api.live.bilibili.com/rc/v1/Achv/getList?type=normal&status=1&category=all&keywords=%E5%B0%8F%E6%9C%89%E6%89%80%E6%88%90&page=1&pageSize=6";
    //{"code":0,"msg":"","message":"","data":{"user":{"progress":26,"total":39,"achieve":510,"done":10,"can_receive_normal":0},"info":[{"tid":2,"css":"task-2","title":"小有所成","award":"<p>弹幕最大长度提升为30个字</p>","awards":[{"type":"dannmakuLength","text":"弹幕最大长度提升为30个字"}],"achieve":30,"descript":"达成条件：用户等级达到20级","status":true,"finished":true,"show_receive_icon":true,"progress":{"now":0,"max":0}}],"page":{"total":1,"totalPage":1,"current":1,"info":[]}}}



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
    public static String LiveUserInfoURL = "https://api.live.bilibili.com/xlive/web-ucenter/user/get_user_info"; // 长城宽带下无法调用接口
    public static String UserInfoURL = "https://api.bilibili.com/x/web-interface/nav";
    public static String LiveConfURL = "https://api.live.bilibili.com/room/v1/Danmu/getConf?room_id=";
    public static String InfoNewURL = "https://api.live.bilibili.com/client/v1/Ip/getInfoNew";
    public static String LiveRoomUserInfoURL = "https://api.live.bilibili.com/room_ex/v1/RoomNews/get?roomid=";

//    public static LiveInfo liveInfo = null; //TODO b站更改接口 停用 20190412

    public static LiveConf liveConf = null;
    public static InfoNew infoNew = null;

    public static SendBarrage sendBarrage = null;
    public static GetLiveRoomUserInfo getLiveRoomUserInfo=null;

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
//    public static Document docs = barrage.getEditorPane().getDocument();
//    public static StyledDocument docsS = null;
//            barrage.getEditorPane().getStyledDocument();
//    public static SpicyIntegration_Thr spicyIntegration_thr =null;

    // Dingtalk Robot Webhook
    public static String DingtalkWebhookStart = "s945Q0A+q3VWvmDSV27I4XNmPnEzpb+9y6298m2MnQvGzTCE8ciaYlQktDkNF3/xn+85ebPuEAmyyJxGzMEIdlQoZOPE0P4fS5HXTjqtN+uNkXMhlgEcQbtY8nuqoWOFXB/1+pDZ8Da6HSj1ugJngF9auE18oBvkA6ru2UHRJtc=";
    public static String DingtalkWebhookOpen = "s945Q0A+q3VWvmDSV27I4XNmPnEzpb+9y6298m2MnQvGzTCE8ciaYlQktDkNF3/xABTpARnN9l6n9bwIQZ2pwqGxZM5pxhUJ4cowU4wWvggshqgcBMsZv2Q3vmoXKsb/eXqBWPqcsVLIMYTZQeGGtbV8hEjE5Lmcn6jq9vmf40k=";
    public static String DingtalkWebhookLink = "s945Q0A+q3VWvmDSV27I4XNmPnEzpb+9y6298m2MnQvGzTCE8ciaYlQktDkNF3/xuTokoe7J6mOockxV28ZhBGHyCyxxEWQQDV1JX9qEKh0n57afBDZrOBEUBqJkGQ3VDZgznm3AC3ONNhHG1WinatUbe0dyXSqmb8UDQJw3WLo=";
    public static String DingtalkWebhookLogin = "s945Q0A+q3VWvmDSV27I4XNmPnEzpb+9y6298m2MnQvGzTCE8ciaYlQktDkNF3/xeTfhiOP9v02+hzDlAW1ML/dXiPvIZEmjuiMWXOV1uO8vCLDirDILIf8yt+0LixNqjZknNlyBQgjIcSAGrfkdfY8qWgiNVHPc7JJFGSGMqgA=";
    public static String DingtalkWebhookPK = "b244c6c6b95c002eb89da3259d8809af817415e77636c4e3e4f47c6a1944199f";
}
