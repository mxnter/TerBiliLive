package com.github.TerBiliLive.Utiliy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DmUtil {
    String code ="";
    String msg ="";
    String data ="";
    String room ="";
    String admin ="";

    String R_text ="";
    String R_uid ="";
    String R_nickname ="";
    String R_uname_color ="";
    String R_timeline ="";
    String R_isadmin ="";
    String R_vip ="";
    String R_svip ="";
    String R_medal =""; //勋章
    String R_title =""; //备用
    String R_user_level ="";
    String R_rank ="";
    String R_teamid ="";
    String R_rnd ="";
    String R_user_title ="";
    String R_guard_level ="";
    String R_activity_info ="";

    String M_level ="";
    String M_name ="";
    String M_upname ="";
    String M_liveid ="";
    String M_xx1 ="";
    String M_xx2 ="";
    String M_xx3 ="";
    String T_title ="";  //备用

    String U_level ="";
    String U_xx1 ="";
    String U_xx2 ="";
    String U_ranking ="";
    String A_uname_color ="";//
    String R_room="";//为了输出最后一次的值
    String M_medal[]={"","","","","",""};

    String U_user_level="";
/*
    String R_room[]="";//为了输出最后一次的值
    String M_medal[] ="";
     String U_user_level[]="";//
*/










    public DmUtil(){

    }



    public DmUtil(String DMJData) throws JSONException {
        JSONObject JO = new JSONObject(DMJData);
        code=JO.getString("code");
        msg=JO.getString("msg");
        data=JO.getString("data");

        JSONObject JO_Data = new JSONObject(data);//{}
        room=JO_Data.getString("room").toString();
//        System.out.println(room);


        JSONArray JO_room  = new JSONArray(room);//[]

/*        for (int i = 0; i < JO_room.length(); i++) {

            JSONObject JO_R_room = JO_room.getJSONObject(i);
            R_room[i]=JO_R_room.toString();
        }

        */
        if(!room.equals("")){

        R_room=JO_room.getJSONObject(JO_room.length()-1).toString();

//        System.out.println(R_room);
        JSONObject JO_DMData = new JSONObject(R_room);//{}

        R_text =JO_DMData.getString("text");
        R_uid =JO_DMData.getString("uid");
        R_nickname =JO_DMData.getString("nickname");
        R_uname_color =JO_DMData.getString("uname_color");
        R_timeline =JO_DMData.getString("timeline");
        R_isadmin =JO_DMData.getString("isadmin");
        R_vip =JO_DMData.getString("vip");
        R_svip =JO_DMData.getString("svip");
        R_medal =JO_DMData.getString("medal"); //勋章
        R_title =JO_DMData.getString("title"); //备用
        R_user_level =JO_DMData.getString("user_level");
        R_rank =JO_DMData.getString("rank");
        R_teamid =JO_DMData.getString("teamid");
        R_rnd =JO_DMData.getString("rnd");
        R_user_title =JO_DMData.getString("user_title");
        R_guard_level =JO_DMData.getString("guard_level");
        R_activity_info =JO_DMData.getString("activity_info");
        JSONArray JO_medal  = new JSONArray(R_medal);//[]
//        System.out.println(R_medal);

           if(JO_medal.length()>0) {
                for (int i = 0; i < JO_medal.length(); i++) {

                    M_medal[i] = JO_medal.getString(i).toString();
                }
                M_level = M_medal[0];
                M_name = M_medal[1];
                M_upname = M_medal[2];
                M_liveid = M_medal[3];
                M_xx1 = M_medal[4];
                M_xx2 = M_medal[5];
                // String M_xx3 =M_medal[6];
//        System.out.println(M_medal[0]);
           }


                JSONArray JO_user_level = new JSONArray(R_user_level);//[]
//        System.out.println(R_user_level.toString());
                U_level = JO_user_level.getString(0).toString();


        }






        }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getR_text() {
        return R_text;
    }

    public void setR_text(String r_text) {
        R_text = r_text;
    }

    public String getR_uid() {
        return R_uid;
    }

    public void setR_uid(String r_uid) {
        R_uid = r_uid;
    }

    public String getR_nickname() {
        return R_nickname;
    }

    public void setR_nickname(String r_nickname) {
        R_nickname = r_nickname;
    }

    public String getR_uname_color() {
        return R_uname_color;
    }

    public void setR_uname_color(String r_uname_color) {
        R_uname_color = r_uname_color;
    }

    public String getR_timeline() {
        return R_timeline;
    }

    public void setR_timeline(String r_timeline) {
        R_timeline = r_timeline;
    }

    public String getR_isadmin() {
        return R_isadmin;
    }

    public void setR_isadmin(String r_isadmin) {
        R_isadmin = r_isadmin;
    }

    public String getR_vip() {
        return R_vip;
    }

    public void setR_vip(String r_vip) {
        R_vip = r_vip;
    }

    public String getR_svip() {
        return R_svip;
    }

    public void setR_svip(String r_svip) {
        R_svip = r_svip;
    }

    public String getR_medal() {
        return R_medal;
    }

    public void setR_medal(String r_medal) {
        R_medal = r_medal;
    }

    public String getR_title() {
        return R_title;
    }

    public void setR_title(String r_title) {
        R_title = r_title;
    }

    public String getR_user_level() {
        return R_user_level;
    }

    public void setR_user_level(String r_user_leve) {
        R_user_level = r_user_leve;
    }

    public String getR_rank() {
        return R_rank;
    }

    public void setR_rank(String r_rank) {
        R_rank = r_rank;
    }

    public String getR_teamid() {
        return R_teamid;
    }

    public void setR_teamid(String r_teamid) {
        R_teamid = r_teamid;
    }

    public String getR_rnd() {
        return R_rnd;
    }

    public void setR_rnd(String r_rnd) {
        R_rnd = r_rnd;
    }

    public String getR_user_title() {
        return R_user_title;
    }

    public void setR_user_title(String r_user_title) {
        R_user_title = r_user_title;
    }

    public String getR_guard_level() {
        return R_guard_level;
    }

    public void setR_guard_level(String r_guard_level) {
        R_guard_level = r_guard_level;
    }

    public String getR_activity_info() {
        return R_activity_info;
    }

    public void setR_activity_info(String r_activity_info) {
        R_activity_info = r_activity_info;
    }

    public String getM_level() {
        return M_level;
    }

    public void setM_level(String m_level) {
        M_level = m_level;
    }

    public String getM_name() {
        return M_name;
    }

    public void setM_name(String m_name) {
        M_name = m_name;
    }

    public String getM_upname() {
        return M_upname;
    }

    public void setM_upname(String m_upname) {
        M_upname = m_upname;
    }

    public String getM_liveid() {
        return M_liveid;
    }

    public void setM_liveid(String m_liveid) {
        M_liveid = m_liveid;
    }

    public String getM_xx1() {
        return M_xx1;
    }

    public void setM_xx1(String m_xx1) {
        M_xx1 = m_xx1;
    }

    public String getM_xx2() {
        return M_xx2;
    }

    public void setM_xx2(String m_xx2) {
        M_xx2 = m_xx2;
    }

    public String getT_title() {
        return T_title;
    }

    public void setT_title(String t_title) {
        T_title = t_title;
    }

    public String getU_level() {
        return U_level;
    }

    public void setU_level(String u_level) {
        U_level = u_level;
    }

    public String getU_xx1() {
        return U_xx1;
    }

    public void setU_xx1(String u_xx1) {
        U_xx1 = u_xx1;
    }

    public String getU_xx2() {
        return U_xx2;
    }

    public void setU_xx2(String u_xx2) {
        U_xx2 = u_xx2;
    }

    public String getU_ranking() {
        return U_ranking;
    }

    public void setU_ranking(String u_ranking) {
        U_ranking = u_ranking;
    }

    public String getA_uname_color() {
        return A_uname_color;
    }

    public void setA_uname_color(String a_uname_color) {
        A_uname_color = a_uname_color;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getM_xx3() {
        return M_xx3;
    }

    public void setM_xx3(String m_xx3) {
        M_xx3 = m_xx3;
    }
}
/*{
        "code": 0,
        "msg": "",
        "data": {
        "room": [{
        "text": "23",1
        "uid": 301164820,
        "nickname": "TerKong",
        "uname_color": "",
        "timeline": "2018-06-02 14:04:13",
        "isadmin": 1,
        "vip": 0,
        "svip": 0,
        "medal": [6, "出海", "出门看到海", "5600514", 5805790, ""],
        "title": ["title-144-1"],
        "user_level": [23, 0, 5805790, ">50000"],
        "rank": 10000,
        "teamid": 0,
        "rnd": 137692,
        "user_title": "title-144-1",
        "guard_level": 0,
        "activity_info": {
        "uname_color": ""
        }
        }, {
        "text": "111", //弹幕内容
        "uid": 18169995, //用户id
        "nickname": "mxnter", //用户名字
        "uname_color": "",//名字颜色
        "timeline": "2018-06-02 22:17:45",//弹幕时间
        "isadmin": 1,//管理员
        "vip": 0,//老爷
        "svip": 0,//年费老爷
        "medal": [13, "出海", "出门看到海", "5600514", 16746162, ""],//勋章内容
        "title": ["title-144-1"],//头衔
        "user_level": [26, 0, 5805790, ">50000"],//用户等级
        "rank": "10000",
        "teamid": 0,
        "rnd": 1527919468,//随机数
        "user_title": "title-144-1",//头衔
        "guard_level": 0,//警卫等级？？
        "activity_info": {
        "uname_color": ""
        }//活动名字颜色
        }],
        "admin": []
        }
        }*/