package com.TerBiliLive;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Ui.TerBiliLive_Greet_Ui;
import com.TerBiliLive.Utiliy.DevLogUtil;
import com.TerBiliLive.Utiliy.FileUtil;
import com.TerBiliLive.Utiliy.LogUtil;

import static com.TerBiliLive.Utiliy.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatHour;



/*全软件启动类*/
/*当前版本： HFJ 02 -2018年6月2日14点45分*/

public class Main {

    public static void main(String[] args) {
	// write your code here

        //创建目录
        FileUtil.createDir("Ter/data/");
        FileUtil.createDir("Ter/log/");
        FileUtil.createDir("Ter/Dm/");
//      ConfInfo.dingtalk.OpenProgram();//统计用户

        ConfInfo.jsonUtil.readData(); //读取基础数据

        DevLogUtil.printf(ConfInfo.confData.toString());

        LogUtil.putLog(getFormatDay(), getFormatHour(), ConfInfo.confData.toString() + "\n", "TerBiliLive Logxx");

        TerBiliLive_Greet_Ui login =new TerBiliLive_Greet_Ui();


    }
}
