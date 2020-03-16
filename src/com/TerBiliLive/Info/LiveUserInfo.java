package com.TerBiliLive.Info;

import com.TerBiliLive.TerBiliLive.HttpClient;
import com.TerBiliLive.Utils.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

import static com.TerBiliLive.Utils.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utils.TimeUtil.getFormatHour;

/**
 * CODE IS POETRY
 *
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 19:20 2019/4/13
 */
public class LiveUserInfo {

    public String achieve = null ; //: 280
    public String billCoin = null ; //: 162
    public String face = null ; //: "https://i1.hdslb.com/bfs/face/12758bd3dc944ffd0f084c58d44b27c4c7cd06fe.jpg"
    public String gold = null ; //: 0
    public String is_level_top = null ; //: 0
    public String silver = null ; //: 28370
    public String svip = null ; //: 0
    public String uid = null ; //: 112332197
    public String uname = null ; //: "兔团儿のTer"
    public String user_charged = null ; //: 0
    public String user_intimacy = null ; //: 4331900
    public String user_level = null ; //: 26
    public String user_level_rank = null ; //: ">50000"
    public String user_next_intimacy = null ; //: 10000000
    public String user_next_level = null ; //: 27
    public String vip = null ; //: 1

    public String returnData = null;


    public LiveUserInfo() {


    }

    public LiveUserInfo(String cookie){

        returnData = HttpClient.sendGet(ConfInfo.UserInfoURL,cookie);
        System.out.println(returnData);
        try {
            JSONObject data = new JSONObject(returnData);
            switch (data.getString("code")){
                case "0":{
                    JSONObject jsonObject = data.getJSONObject("data");
                    achieve = jsonObject.getString("achieve");
                    billCoin = jsonObject.getString("billCoin");
                    face = jsonObject.getString("face");
                    gold = jsonObject.getString("gold");
                    is_level_top = jsonObject.getString("is_level_top");
                    silver = jsonObject.getString("silver");
                    svip = jsonObject.getString("svip");
                    uid = jsonObject.getString("uid");
                    uname = jsonObject.getString("uname");
                    user_charged = jsonObject.getString("user_charged");
                    user_intimacy = jsonObject.getString("user_intimacy");
                    user_level = jsonObject.getString("user_level");
                    user_level_rank = jsonObject.getString("user_level_rank");
                    user_next_intimacy = jsonObject.getString("user_next_intimacy");
                    user_next_level = jsonObject.getString("user_next_level");
                    vip = jsonObject.getString("vip");
                    break;
                }
                case "-101":{

                    break;
                }
                default:{ ;
                    System.out.println("获取个人信息数据信息异常 ："+returnData);
                    LogUtil.putLogException("获取个人信息数据信息异常 ："+returnData);
                    break;
                }
            }
//            if(data.getString("code").equals("0")){
//                JSONObject jsonObject = data.getJSONObject("data");
//                achieve = jsonObject.getString("achieve");
//                billCoin = jsonObject.getString("billCoin");
//                face = jsonObject.getString("face");
//                gold = jsonObject.getString("gold");
//                is_level_top = jsonObject.getString("is_level_top");
//                silver = jsonObject.getString("silver");
//                svip = jsonObject.getString("svip");
//                uid = jsonObject.getString("uid");
//                uname = jsonObject.getString("uname");
//                user_charged = jsonObject.getString("user_charged");
//                user_intimacy = jsonObject.getString("user_intimacy");
//                user_level = jsonObject.getString("user_level");
//                user_level_rank = jsonObject.getString("user_level_rank");
//                user_next_intimacy = jsonObject.getString("user_next_intimacy");
//                user_next_level = jsonObject.getString("user_next_level");
//                vip = jsonObject.getString("vip");
//            }else{
//                LogUtil.putLogException("获取个人信息数据信息异常 ："+returnData);
//                System.out.println("获取个人信息数据信息异常 ："+returnData);
//            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public LiveUserInfo(String achieve, String billCoin, String face, String gold, String is_level_top, String silver, String svip, String uid, String uname, String user_charged, String user_intimacy, String user_level, String user_level_rank, String user_next_intimacy, String user_next_level, String vip) {
        this.achieve = achieve;
        this.billCoin = billCoin;
        this.face = face;
        this.gold = gold;
        this.is_level_top = is_level_top;
        this.silver = silver;
        this.svip = svip;
        this.uid = uid;
        this.uname = uname;
        this.user_charged = user_charged;
        this.user_intimacy = user_intimacy;
        this.user_level = user_level;
        this.user_level_rank = user_level_rank;
        this.user_next_intimacy = user_next_intimacy;
        this.user_next_level = user_next_level;
        this.vip = vip;
    }




    public String getAchieve() {
        return achieve;
    }

    public void setAchieve(String achieve) {
        this.achieve = achieve;
    }

    public String getBillCoin() {
        return billCoin;
    }

    public void setBillCoin(String billCoin) {
        this.billCoin = billCoin;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getIs_level_top() {
        return is_level_top;
    }

    public void setIs_level_top(String is_level_top) {
        this.is_level_top = is_level_top;
    }

    public String getSilver() {
        return silver;
    }

    public void setSilver(String silver) {
        this.silver = silver;
    }

    public String getSvip() {
        return svip;
    }

    public void setSvip(String svip) {
        this.svip = svip;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUser_charged() {
        return user_charged;
    }

    public void setUser_charged(String user_charged) {
        this.user_charged = user_charged;
    }

    public String getUser_intimacy() {
        return user_intimacy;
    }

    public void setUser_intimacy(String user_intimacy) {
        this.user_intimacy = user_intimacy;
    }

    public String getUser_level() {
        return user_level;
    }

    public void setUser_level(String user_level) {
        this.user_level = user_level;
    }

    public String getUser_level_rank() {
        return user_level_rank;
    }

    public void setUser_level_rank(String user_level_rank) {
        this.user_level_rank = user_level_rank;
    }

    public String getUser_next_intimacy() {
        return user_next_intimacy;
    }

    public void setUser_next_intimacy(String user_next_intimacy) {
        this.user_next_intimacy = user_next_intimacy;
    }

    public String getUser_next_level() {
        return user_next_level;
    }

    public void setUser_next_level(String user_next_level) {
        this.user_next_level = user_next_level;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getreturnData() {
        return returnData;
    }

    public void setreturnData(String returnData) {
        returnData = returnData;
    }

    @Override
    public String toString() {
        return "LiveUserInfo{" +
                "achieve='" + achieve + '\'' +
                ", billCoin='" + billCoin + '\'' +
                ", face='" + face + '\'' +
                ", gold='" + gold + '\'' +
                ", is_level_top='" + is_level_top + '\'' +
                ", silver='" + silver + '\'' +
                ", svip='" + svip + '\'' +
                ", uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", user_charged='" + user_charged + '\'' +
                ", user_intimacy='" + user_intimacy + '\'' +
                ", user_level='" + user_level + '\'' +
                ", user_level_rank='" + user_level_rank + '\'' +
                ", user_next_intimacy='" + user_next_intimacy + '\'' +
                ", user_next_level='" + user_next_level + '\'' +
                ", vip='" + vip + '\'' +
                ", returnData='" + returnData + '\'' +
                '}';
    }
}
