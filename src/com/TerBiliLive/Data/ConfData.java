package com.TerBiliLive.Data;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Utils.AESUtil;
import com.TerBiliLive.Utils.InOutPutUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfData {

    String Cookie="" ;
    String RoomId="";
    String Second="";
    String Text="";
    String TulingApikey="";

    public String getCookie() {
        return Cookie;
    }

    public void setCookie(String cookie) {
        Cookie = cookie;
    }

    public String getRoomId() {
        return RoomId;
    }

    public void setRoomId(String roomId) {
        RoomId = roomId;
    }

    public String getSecond() {
        return Second;
    }

    public void setSecond(String second) {
        Second = second;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getTulingApikey() {
        return TulingApikey;
    }

    public void setTulingApikey(String tulingApikey) {
        TulingApikey = tulingApikey;
    }

    public void readConfData(){
        ResultSet resultSet = ConfInfo.databaesUtil.select("select * from ConfData");
        try {
            if(resultSet.next()){
                ConfInfo.confData.setCookie(AESUtil.Decrypt(resultSet.getString("Cookie")));
                ConfInfo.confData.setRoomId(resultSet.getString("Roomid"));
                ConfInfo.confData.setSecond(resultSet.getString("Second"));
                ConfInfo.confData.setText(resultSet.getString("Text"));
                ConfInfo.confData.setTulingApikey(resultSet.getString("TulingApikey"));
            }else{
                InOutPutUtil.outPut("数据库暂无数据");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeConfData(){
        ConfInfo.databaesUtil.executeUpdate("delete from ConfData");
        try {
            ConfInfo.databaesUtil.executeUpdate("INSERT INTO ConfData VALUES (\""+AESUtil.Encryption(Cookie)+"\",\""+RoomId+"\",\""+Second+"\",\""+Text+"\",\""+TulingApikey+"\")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "ConfData{" +
                "Cookie='" + Cookie + '\'' +
                ", RoomId='" + RoomId + '\'' +
                ", Second='" + Second + '\'' +
                ", Text='" + Text + '\'' +
                ", TulingApikey='" + TulingApikey + '\'' +
                '}';
    }
}
