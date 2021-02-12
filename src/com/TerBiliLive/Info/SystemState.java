package com.TerBiliLive.Info;

public class SystemState {

    public boolean isDoSign = false;
    public boolean isAutoSpeak = false;

    public boolean isLiveStatus = false; //是否在直播
    public boolean isAlreadyOnGreetMaster = false; //是否在系统改变时开启老爷


    /*
    * 弹幕姬用的控制变量
    * */

    public boolean isLiveMsg = false; // 直播提醒
    public boolean isGreetMaster = false; // 欢迎老爷
    public boolean isGreetCaptain = false; // 欢迎舰长
    public boolean isIgnoreSpicystrip = true; // 忽略辣条
    public boolean isSend30 = false; // 可发送30字




    public boolean isLink = false; // 弹幕姬已连接
    public boolean isThank = false; // 开启感谢
    public boolean isSystemSendLock = false; // 系统消息锁
    public boolean isInteractWord = false; // 欢迎用户
    public boolean isThankFollow = false; // 关注感谢
    public boolean isThankShare = false; // 分享感谢

    // TODO 如初关注抽奖
    public boolean isFollowDraw = false; // 关注抽奖

    @Override
    public String toString() {
        return "{" +
                "isLiveMsg=" + isLiveMsg +
                ", isGreetMaster=" + isGreetMaster +
                ", isGreetCaptain=" + isGreetCaptain +
                ", isIgnoreSpicystrip=" + isIgnoreSpicystrip +
                ", isSend30=" + isSend30 +
                '}';
    }
}
