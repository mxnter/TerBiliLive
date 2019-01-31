package com.TerBiliLive.Thr;

import com.TerBiliLive.Info.ConfInfo;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * CODE IS POETRY
 * @Nmae ：弹幕显示线程
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 11:51 2018/11/4
 */

public class GetSendBarrageList_Thr extends Thread {

    private AtomicBoolean wait = new AtomicBoolean(false);


    @Override
    public void run() {
        super.run();



        while (true){

//          System.out.print("0");
        if(!ConfInfo.ChargeBarrageList.isEmpty()&&!ConfInfo.ChargeBarrageList.get(0).equals("")) {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(ConfInfo.ChargeBarrageList.get(0));
            ConfInfo.putShowUtil.PutDMUtil(ConfInfo.ChargeBarrageList.get(0), Color.BLACK);
            ConfInfo.ChargeBarrageList.remove(0);
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
        else{
            synchronized (ConfInfo.GBT) {
                    try {
                        ConfInfo.GBT.wait();
//                        ConfInfo.GetSendBarrageList_Thr_Size=false;
                        System.out.println("-----------------------显示弹幕数据进入休眠-----------------------");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
//            try {
//                sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }
    }
}
