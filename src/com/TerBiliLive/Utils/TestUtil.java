package com.TerBiliLive.Utils;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.Presents;
import com.TerBiliLive.Thr.GiftIntegration_Thr;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author myetear
 */
public class TestUtil {
    public static void main(String[] args) throws InterruptedException {


//        System.out.println(System.currentTimeMillis()/1000);
//        Map<String, Presents> integrated = new HashMap<String,Presents>();
//
//        String key = "1";
//        integrated.put(key, new Presents("时间", "名字", "礼物", 0, new Timer());
//        System.out.println(System.currentTimeMillis()/1000);
//
//        integrated.get(key).getTimer().cancel();
//        integrated.get(key).setTimer(new Timer());
//        integrated.get(key).setTt(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println(integrated.get(key).toString());
//                System.out.println(System.currentTimeMillis()/1000);
//                integrated.get(key).getTimer().cancel();
//                integrated.remove(key);
//                System.out.println(integrated.size());
//            }
//        });
//        System.out.println(System.currentTimeMillis()/1000);
//        integrated.get(key).getTimer().schedule(integrated.get(key).getTt(),1000);
////        if (!integrated.containsKey()) {
//            InOutPutUtil.outPut("开启整合 礼物："+giftName +"MAP："+ uname+giftName);
//            new GiftIntegration_Thr().start(uname, giftName);
//            ConfInfo.integrated.put(uname+giftName, new Presents(timestamp,uname,giftName,0));
//        }

//        Timer timer = new Timer();
//        TimerTask t = new TimerTask() {
//            public void run() {
//                System.out.println(System.currentTimeMillis()/1000);
//                System.out.println("延迟1.5s");
//                //延迟特定时间后执行该语句（public void run()的花括号里的语句）
//            } };
//        timer.schedule(t, 1500);        //这里的数字1500意思是延迟1500毫秒
//        timer.cancel();
//        timer = new Timer();
//        timer.schedule(t, 1500);
//        System.out.println(System.currentTimeMillis()/1000);
    }
}
