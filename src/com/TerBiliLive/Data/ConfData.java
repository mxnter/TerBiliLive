package com.TerBiliLive.Data;

public class ConfData {

    String Cookie="" ;
    String RoomId="";
    String Second="";
    String Text="";

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

    @Override
    public String toString() {
        return "ConfData{" +
                "Cookie='" + Cookie + '\'' +
                ", RoomId='" + RoomId + '\'' +
                ", Second='" + Second + '\'' +
                ", Text='" + Text + '\'' +
                '}';
    }
}
