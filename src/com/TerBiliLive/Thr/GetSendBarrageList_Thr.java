package com.TerBiliLive.Thr;

import com.TerBiliLive.Info.ConfInfo;

import java.util.concurrent.atomic.AtomicBoolean;

public class GetSendBarrageList_Thr extends Thread {

    private AtomicBoolean wait = new AtomicBoolean(false);


    @Override
    public void run() {
        super.run();



        while (true){

//          System.out.print("0");
        if(!ConfInfo.ChargeBarrageList.isEmpty()&&!ConfInfo.ChargeBarrageList.get(0).equals("")) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(ConfInfo.ChargeBarrageList.get(0));
            ConfInfo.putShowUtil.PutDMUtil(ConfInfo.ChargeBarrageList.get(0));
            ConfInfo.ChargeBarrageList.remove(0);




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
