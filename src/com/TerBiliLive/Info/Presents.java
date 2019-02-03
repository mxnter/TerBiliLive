package com.TerBiliLive.Info;

/**
 * CODE IS POETRY
 *
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 16:53 2019/2/3
 */
public class Presents {

    String timestamp;
    String uname;
    String giftName;
    int giftNum;

    public Presents(String timestamp, String uname, String giftName, int giftNum) {
        this.timestamp = timestamp;
        this.uname = uname;
        this.giftName = giftName;
        this.giftNum = giftNum;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public int getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(int giftNum) {
        this.giftNum = giftNum;
    }

    @Override
    public String toString() {
        return "Presents{" +
                "timestamp='" + timestamp + '\'' +
                ", uname='" + uname + '\'' +
                ", giftName='" + giftName + '\'' +
                ", giftNum='" + giftNum + '\'' +
                '}';
    }
}
