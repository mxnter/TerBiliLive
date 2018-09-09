package com.TerBiliLive.Info;

import com.TerBiliLive.TerBiliLive.SendPost;
import com.TerBiliLive.TerBiliLive.getSubString;
import com.TerBiliLive.Utiliy.CodingUtil;

import javax.swing.text.Document;
import javax.swing.text.Element;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class LiveInfo {

	String ReturnData = null;
	String RoomLive = "NO";

	String Uid = null;// 用户uid只有登陆之后的用户才会返回这个，没有登陆的用户不会
	String Uname = null; // 用户昵称，同上
	String Login = null;// 登陆状态
	String Isadmin = null;// 是否管理员
	String Time = null;// 时间戳
	String Rank = null; // 排名
	String Level = null;// 等级
	String State = null;// 这个应该是直播间状态
	String Chatid = null;// 交谈id 这个不太清楚做什么用
	String Server = null;// 服务器地址
	String Sheid_user = null;// sheid这个不知道是啥
	String Block_time = null;// 这个应该是被封的时间
	String Block_type = null; // 被封禁的类型
	String Room_shield = null;// 房间的sheid
	String Level_sheid = null;// sheid的level
	String User_sheid_keyword = null;// 不知道。。
	String Room_silent_type = null; // 这个应该是房间禁言类型
	String Room_silent_level = null;// 禁言类型
	String Room_silent_second = null;// 禁言秒数
	String User_silent_level = null;// 用户禁言等级
	String User_silent_rank = null;// 用户禁言排名
	String User_silent_verify = null; // 用户禁言确认
	int Dm_ws_port = 0; // (重头戏)弹幕服务器端口号
	int Dm_wss_port = 0;// 弹幕服务器端口号2
	int Dm_port = 0; // 弹幕服务器端口号3
	String Dm_server = null;// 弹幕服务器地址
	String Need_authority = null;// 是否需要认证
	String Authority_range = null;// 授权区域(这个应该是本机ip地址的地区)
	String Forbidden = null;

	static String LiveInfoURL = ConfInfo.LiveInfoURL;

	String xml = "";

	public LiveInfo() {}

	public LiveInfo(String Cid) {

		new LiveInfo(Cid,"");
	}

	public LiveInfo(String Cid, String cookie) {

    //创建Post提交对象
        ConfInfo.sendPost = new SendPost();
        //获取返回值
        ReturnData = ConfInfo.sendPost.SendPost(LiveInfoURL+Cid,null,cookie);
        System.out.println(ReturnData);
//		ReturnData = CodingUtil.ascii2native(ReturnData);
		setAllDate();

	}





	void setAllDate() {
		Uid = getSubString.getSubString(ReturnData, "<uid>", "</uid>");
		Uname = getSubString.getSubString(ReturnData, "<uname>", "</uname>");
		Login = getSubString.getSubString(ReturnData, "<login>", "</login>");
		Isadmin = getSubString.getSubString(ReturnData, "<isadmin>", "</isadmin>");
		Time = getSubString.getSubString(ReturnData, "<time>", "</time>");
		Rank = getSubString.getSubString(ReturnData, "<rank>", "</rank>");
		Level = getSubString.getSubString(ReturnData, "<level>", "</level>");
		State = getSubString.getSubString(ReturnData, "<state>", "</state>");
		Chatid = getSubString.getSubString(ReturnData, "<chatid>", "</chatid>");
		Server = getSubString.getSubString(ReturnData, "<server>", "</server>");
		Sheid_user = getSubString.getSubString(ReturnData, "<sheid_user>", "</sheid_user>");
		Block_time = getSubString.getSubString(ReturnData, "<block_time>", "</block_time>");
		Block_type = getSubString.getSubString(ReturnData, "<block_type>", "</block_type>");
		Room_shield = getSubString.getSubString(ReturnData, "<room_shield>", "</room_shield>");
		Level_sheid = getSubString.getSubString(ReturnData, "<level_sheid>", "</level_sheid>");
		User_sheid_keyword = getSubString.getSubString(ReturnData, "<user_sheid_keyword>", "</user_sheid_keyword>");
		Room_silent_type = getSubString.getSubString(ReturnData, "<room_silent_type>", "</room_silent_type>");
		Room_silent_level = getSubString.getSubString(ReturnData, "<room_silent_level>", "</room_silent_level>");
		Room_silent_second = getSubString.getSubString(ReturnData, "<room_silent_second>", "</room_silent_second>");
		User_silent_level = getSubString.getSubString(ReturnData, "<user_silent_level>", "</user_silent_level>");
		User_silent_rank = getSubString.getSubString(ReturnData, "<user_silent_rank>", "</user_silent_rank>");
		User_silent_verify = getSubString.getSubString(ReturnData, "<user_silent_verify>", "</user_silent_verify>");
		Dm_ws_port = Integer.parseInt(getSubString.getSubString(ReturnData, "<dm_ws_port>", "</dm_ws_port>"));
		Dm_wss_port = Integer.parseInt(getSubString.getSubString(ReturnData, "<dm_wss_port>", "</dm_wss_port>"));
		Dm_port = Integer.parseInt(getSubString.getSubString(ReturnData, "<dm_port>", "</dm_port>"));
		Dm_server = getSubString.getSubString(ReturnData, "<dm_server>", "</dm_server>");
		Need_authority = getSubString.getSubString(ReturnData, "<need_authority>", "</need_authority>");
		Authority_range = getSubString.getSubString(ReturnData, "<authority_range>", "</authority_range>");
		Forbidden = getSubString.getSubString(ReturnData, "<forbidden>", "</forbidden>");

	}



	public String getReturnData() {
		return ReturnData;
	}

	public String getRoomLive() {
		return RoomLive;
	}

	public String getUid() {
		return Uid;
	}

	public String getUname() {
		return Uname;
	}

	public String getLogin() {
		return Login;
	}

	public String getIsadmin() {
		return Isadmin;
	}

	public String getTime() {
		return Time;
	}

	public String getRank() {
		return Rank;
	}

	public String getLevel() {
		return Level;
	}

	public String getState() {
		return State;
	}

	public String getChatid() {
		return Chatid;
	}

	public String getServer() {
		return Server;
	}

	public String getSheid_user() {
		return Sheid_user;
	}

	public String getBlock_time() {
		return Block_time;
	}

	public String getBlock_type() {
		return Block_type;
	}

	public String getRoom_shield() {
		return Room_shield;
	}

	public String getLevel_sheid() {
		return Level_sheid;
	}

	public String getUser_sheid_keyword() {
		return User_sheid_keyword;
	}

	public String getRoom_silent_type() {
		return Room_silent_type;
	}

	public String getRoom_silent_level() {
		return Room_silent_level;
	}

	public String getRoom_silent_second() {
		return Room_silent_second;
	}

	public String getUser_silent_level() {
		return User_silent_level;
	}

	public String getUser_silent_rank() {
		return User_silent_rank;
	}

	public String getUser_silent_verify() {
		return User_silent_verify;
	}

	public int getDm_ws_port() {
		return Dm_ws_port;
	}

	public int getDm_wss_port() {
		return Dm_wss_port;
	}

	public int getDm_port() {
		return Dm_port;
	}

	public String getDm_server() {
		return Dm_server;
	}

	public String getNeed_authority() {
		return Need_authority;
	}

	public String getAuthority_range() {
		return Authority_range;
	}

	public String getForbidden() {
		return Forbidden;
	}

	public static String getLiveInfoURL() {
		return LiveInfoURL;
	}

	@Override
	public String toString() {
		return "ReturnData='" + ReturnData + '\'' +
				", RoomLive='" + RoomLive + '\'' +
				", Uid='" + Uid + '\'' +
				", Uname='" + Uname + '\'' +
				", Login='" + Login + '\'' +
				", Isadmin='" + Isadmin + '\'' +
				", Time='" + Time + '\'' +
				", Rank='" + Rank + '\'' +
				", Level='" + Level + '\'' +
				", State='" + State + '\'' +
				", Chatid='" + Chatid + '\'' +
				", Server='" + Server + '\'' +
				", Sheid_user='" + Sheid_user + '\'' +
				", Block_time='" + Block_time + '\'' +
				", Block_type='" + Block_type + '\'' +
				", Room_shield='" + Room_shield + '\'' +
				", Level_sheid='" + Level_sheid + '\'' +
				", User_sheid_keyword='" + User_sheid_keyword + '\'' +
				", Room_silent_type='" + Room_silent_type + '\'' +
				", Room_silent_level='" + Room_silent_level + '\'' +
				", Room_silent_second='" + Room_silent_second + '\'' +
				", User_silent_level='" + User_silent_level + '\'' +
				", User_silent_rank='" + User_silent_rank + '\'' +
				", User_silent_verify='" + User_silent_verify + '\'' +
				", Dm_ws_port='" + Dm_ws_port + '\'' +
				", Dm_wss_port='" + Dm_wss_port + '\'' +
				", Dm_port='" + Dm_port + '\'' +
				", Dm_server='" + Dm_server + '\'' +
				", Need_authority='" + Need_authority + '\'' +
				", Authority_range='" + Authority_range + '\'' +
				", Forbidden='" + Forbidden + '\'' +
				'}';
	}
}
/*
 *
 *
 * response: <uid></uid> # 用户uid只有登陆之后的用户才会返回这个，没有登陆的用户不会 <uname></uname> #
 * 用户昵称，同上 <login></login> # 登陆状态 <isadmin></isadmin> # 是否管理员
 * <time>1508301890</time> # 时间戳 <rank></rank> # 排名 <level></level> # 等级
 * <state>LIVE</state> # 这个应该是直播间状态 <chatid>1250611</chatid> # 交谈id 这个不太清楚做什么用
 * <server>livecmt-2.bilibili.com</server> #
 * 服务器地址（特别注意的时这个服务器也可以接收到弹幕推送。应该是它们网站历史遗留的现在dm服务器有专门的地址返回了）
 * <sheid_user></sheid_user> # sheid这个不知道是啥 <block_time>0</block_time> #
 * 这个应该是被封的时间 <block_type>0</block_type> # 被封禁的类型 <room_shield>1</room_shield> #
 * 房间的sheid <level_sheid>0</level_sheid> # sheid的level
 * <user_sheid_keyword></user_sheid_keyword> # 不知道。。
 * <room_silent_type></room_silent_type> # 这个应该是房间禁言类型
 * <room_silent_level>0</room_silent_level> # 禁言类型
 * <room_silent_second>0</room_silent_second> # 禁言秒数
 * <user_silent_level></user_silent_level> # 用户禁言等级
 * <user_silent_rank></user_silent_rank> # 用户禁言排名
 * <user_silent_verify></user_silent_verify> # 用户禁言确认
 * <dm_ws_port>2244</dm_ws_port> # (重头戏)弹幕服务器端口号 <dm_wss_port>2245</dm_wss_port>
 * # 弹幕服务器端口号2 <dm_port>2243</dm_port> # 弹幕服务器端口号3
 * <dm_server>broadcastlv.chat.bilibili.com</dm_server> # 弹幕服务器地址
 * <need_authority>0</need_authority> # 是否需要认证
 * <authority_range>日本</authority_range> # 授权区域(这个应该是本机ip地址的地区)
 * <forbidden>0</forbidden>
 *
 *
 */