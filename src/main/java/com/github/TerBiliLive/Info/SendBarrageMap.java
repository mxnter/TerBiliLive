package com.github.TerBiliLive.Info;

/**
 * CODE IS POETRY
 *
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 10:24 2019/2/17
 */
public class SendBarrageMap {
    String msg;
    int type;

    public SendBarrageMap(String msg, int type) {
        this.msg = msg;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SendBarrage{" +
                "msg='" + msg + '\'' +
                ", type=" + type +
                '}';
    }
}
