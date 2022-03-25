package com.TerBiliLive.Info.Nav;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveRoomInfo;
import com.TerBiliLive.TerBiliLive.HttpClient;
import com.TerBiliLive.Utils.InOutPutUtil;
import com.TerBiliLive.Utils.LogUtil;
import com.TerBiliLive.Utils.StringUtil;
import com.alibaba.fastjson.JSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.TerBiliLive.Utils.TimeUtil.getFormat;

/**
 * 关注主播信息
 */
public class RelationUPNav {

    /**
     * 获取是否关注了主播
     * @param id 主播id
     * @return
     */
    public boolean getRelationUP(String id) {
        String returnData = HttpClient.sendGet(ConfInfo.RelationURL+id,ConfInfo.confData.getCookie());
        InOutPutUtil.outPut(returnData);
        try {
            JSONObject data = new JSONObject(returnData);
            switch (data.getString("code")){
                case "0":{
                    // json 转实体类
                    JSONObject strResult = data.getJSONObject("data");
                    if(strResult.getInt("attribute") == 0){
                        ConfInfo.RelationUP = false;
                        return false;
                    }else{
                        ConfInfo.RelationUP = true;
                        return true;
                    }
                }
                default:{
                    InOutPutUtil.outPut("获取关注主播信息信息异常 ："+returnData);
                    LogUtil.putLogException("获取关注主播信息信息异常 ："+returnData,"RelationUPNav");
                    ConfInfo.RelationUP = false;
                    return false;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ConfInfo.RelationUP = false;
            return false;
        }
    }


    /**
     * 关注
     * @param fid 目标用户UID
     * @param act 操作代码
     * 1	关注
     * 2	取关
     * 3	悄悄关注
     * 4	取消悄悄关注
     * 5	拉黑
     * 6	取消拉黑
     * 7	踢出粉丝
     * @param re_src 	关注来源代码
     * 直播间：0
     * 空间：11
     * 视频：14
     * 文章：115
     * 活动页面：222
     */
    public void Modify(String fid,String act,String re_src) {
        String csrf = StringUtil.stringInterception(ConfInfo.confData.getCookie(),"; bili_jct=","; SESSDATA=");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("fid",fid);
        paramMap.put("act",act);
        paramMap.put("re_src",re_src);
        paramMap.put("csrf",csrf);
        paramMap.put("csrf_token",csrf);
        paramMap.put("spm_id","live.live-room-detail.interaction.chat-draw-auto");
//        String postData = "{fid:"+fid+",act:"+act+",re_src:"+re_src+",csrf:'"+ConfInfo.confData.getCookie()+"'}";
        String returnData = HttpClient.sendPost(ConfInfo.ModifyURL,paramMap,ConfInfo.confData.getCookie());
        InOutPutUtil.outPut(returnData);
        try {
            JSONObject data = new JSONObject(returnData);
            switch (data.getString("code")){
                case "0":{
                    ConfInfo.putShowUtil.PutDMUtil("系统"+" | "+getFormat()+" | "+(act.equals("1")?"关注成功：":"取关成功：")+" 当前连接直播间的主播 " + fid, Color.BLUE);
                    InOutPutUtil.outPut((act.equals("1")?"关注成功：":"取关成功：")+" 当前连接直播间的主播 " +fid);
                    LogUtil.putLogSystemOut((act.equals("1")?"关注成功：":"取关成功：")+" 当前连接直播间的主播 " +fid,"RelationUPNav");
                    break;
                }
                default:{
                    InOutPutUtil.outPut("关注异常 ："+returnData);
                    LogUtil.putLogException("关注异常 ："+returnData,"RelationUPNav");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 是否关注了主播
     * @param vmid 用户id
     * @return
     */
    public int getJudgmentFocus(String vmid,String mid) {
        if(ConfInfo.RelationUP||getRelationUP(mid)){
            String returnData = HttpClient.sendGet(ConfInfo.FollowingsURL+vmid,ConfInfo.confData.getCookie());
            InOutPutUtil.outPut(returnData);
            try {
                JSONObject data = new JSONObject(returnData);
                switch (data.getString("code")){
                    case "0":{
                        // json 转实体类
                        JSONObject strResult = data.getJSONObject("data");
                        JSONArray list = strResult.getJSONArray("list");
                        for (int i = 0; i < list.length(); i++) {
                            if(list.getJSONObject(i).getString("mid").equals(mid)){
                                return 1; // 关注了主播
                            }
                        }
                        return 0; // 未关注主播
                    }
                    default:{
                        InOutPutUtil.outPut("是否关注了主播 ："+returnData);
                        LogUtil.putLogException("是否关注了主播 ："+returnData,"RelationUPNav");
                        ConfInfo.RelationUP = false;
                        return -2;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                ConfInfo.RelationUP = false;
                return -3;
            }
        }else{
            return -1; // 未关注主播
        }


    }

}
