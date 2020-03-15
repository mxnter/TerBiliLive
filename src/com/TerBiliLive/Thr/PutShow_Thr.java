package com.TerBiliLive.Thr;

import com.TerBiliLive.Info.ConfInfo;

import java.awt.*;

/**
 * CODE IS POETRY
 * @Nmae ：弹幕显示线程
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 11:51 2018/11/4
 */

public class PutShow_Thr extends Thread {
    String putDM;
    Color ColorDM;
    PutShow_Thr(String putDM,Color ColorDM){
    this.ColorDM = ColorDM;
    this.putDM = putDM;
    }

    @Override
    public void run() {
        super.run();
        ConfInfo.putShowUtil.PutDMUtil(putDM,ColorDM);

        }
    }
