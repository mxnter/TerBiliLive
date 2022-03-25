package com.TerBiliLive.Utils;

import com.TerBiliLive.Info.ConfInfo;

public class InOutPutUtil {

    public InOutPutUtil() {
    }

    public static void outPut(Object msg){
        if(ConfInfo.dev){
            System.out.println(msg);
        }
        if(ConfInfo.devDatabase){
            if(ConfInfo.databaesUtil!=null) LogUtil.putLogSystemOut(msg.toString(),"InOutPutUtil");
        }

    }
    public static void outPut(Object msg,boolean database){
        if(ConfInfo.devDatabasePut){
            System.out.println(msg);
        }
        if(ConfInfo.devDatabase){
            if(ConfInfo.databaesUtil!=null&&database) LogUtil.putLogSystemOut(msg.toString(),"InOutPutUtil");
        }
    }
}
