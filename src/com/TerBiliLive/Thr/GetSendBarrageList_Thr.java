package com.TerBiliLive.Thr;

import com.TerBiliLive.Info.ConfInfo;

public class GetSendBarrageList_Thr extends Thread {



    @Override
    public void run() {
        super.run();



        while (true){
//          System.out.print("0");
        if(!ConfInfo.ChargeBarrageList.isEmpty()&&!ConfInfo.ChargeBarrageList.get(0).equals("")) {
            System.out.println(ConfInfo.ChargeBarrageList.get(0));
            ConfInfo.putShowUtil.PutDMUtil(ConfInfo.ChargeBarrageList.get(0));
            ConfInfo.ChargeBarrageList.remove(0);



        }
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
