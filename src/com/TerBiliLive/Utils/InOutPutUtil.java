package com.TerBiliLive.Utils;

import com.TerBiliLive.Info.ConfInfo;

public class InOutPutUtil {

    public InOutPutUtil() {
    }

    public static void outPut(Object msg){
        if(ConfInfo.dev){
            System.out.println(msg);
        }
        LogUtil.putLogSystemOut(msg.toString());
    }
}
