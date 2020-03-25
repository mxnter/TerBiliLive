package com.TerBiliLive.Utils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerUtil {
    Timer timer;
    TimerTask timerTask;
    public TimerUtil(String name) {
        this.timer = new Timer(name);
    }

    public TimerUtil(String name, TimerTask timerTask, long period) {
        this.timer = new Timer(name);
        Init(timerTask);
        StartNow(period);
    }

    public TimerUtil(String name, TimerTask timerTask, long delay, long period) {
        this.timer = new Timer(name);
        Init(timerTask);
        Start(delay,period);
    }
    public TimerUtil(String name, TimerTask timerTask, Date date, long period) {
        this.timer = new Timer(name);
        Init(timerTask);
        Start(date,period);
    }

    /**
     * 定时任务初始化
     */
    public Boolean Init(TimerTask timerTask) {
        if(timer==null)return false;
        this.timerTask=timerTask;
        return true;
    }
    /**
     * 定时任务开启
     */
    public Boolean StartNow(long period) {
        if(timer==null)return false;
        if(timerTask==null)return false;
        timer.purge();
        timer.schedule(timerTask, new Date(), period*1000);
        return true;
    }
    /**
     * 定时任务开启
     */
    public Boolean Start(Date date,long period) {
        if(timer==null)return false;
        if(timerTask==null)return false;
        timer.purge();
        timer.schedule(timerTask, date, period*1000);
        return true;
    }
    /**
     * 定时任务开启
     */
    public Boolean Start(long delay,long period) {
        if(timer==null)return false;
        if(timerTask==null)return false;
        timer.purge();
        timer.schedule(timerTask, delay, period*1000);
        return true;
    }
    /**
     * 定时任务取消
     */
    public Boolean Stop(){
        if(timer==null)return false;
        if(timerTask==null)return false;
        timerTask.cancel();
        timerTask = null;//需要重新init
        return true;
    }

}
