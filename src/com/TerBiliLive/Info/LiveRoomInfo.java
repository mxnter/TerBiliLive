package com.TerBiliLive.Info;

import com.TerBiliLive.TerBiliLive.HttpClient;
import com.TerBiliLive.Utils.InOutPutUtil;
import com.TerBiliLive.Utils.LogUtil;
import com.alibaba.fastjson.JSON;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 直播间信息
 */
public class LiveRoomInfo {
    private int roomStatus;
    private int roundStatus;
    /**
     * 直播状态 1直播中，0未直播
     */
    private int liveStatus;
    private String url;
    private String title;
    private String cover;
    private String online;
    private String roomid;
    private String broadcast_type;
    private String online_hidden;


    public int getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }

    public int getRoundStatus() {
        return roundStatus;
    }

    public void setRoundStatus(int roundStatus) {
        this.roundStatus = roundStatus;
    }

    public int getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(int liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getBroadcast_type() {
        return broadcast_type;
    }

    public void setBroadcast_type(String broadcast_type) {
        this.broadcast_type = broadcast_type;
    }

    public String getOnline_hidden() {
        return online_hidden;
    }

    public void setOnline_hidden(String online_hidden) {
        this.online_hidden = online_hidden;
    }
}
