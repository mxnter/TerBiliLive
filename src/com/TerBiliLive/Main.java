package com.TerBiliLive;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Ui.Greet;
import com.TerBiliLive.Utils.DevLogUtil;
import com.TerBiliLive.Utils.FileUtil;
import com.TerBiliLive.Utils.LogUtil;

import static com.TerBiliLive.Utils.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utils.TimeUtil.getFormatHour;



/*全软件启动类*/
/*当前版本： HFJ 02 -2018年6月2日14点45分*/

public class Main {

    public static void main(String[] args) {
	// write your code here

        //创建目录
        FileUtil.createDir("Ter/data/");
        FileUtil.createDir("Ter/log/");
        FileUtil.createDir("Ter/Dm/");
        FileUtil.createDir("Ter/database/");
        FileUtil.createDir("Ter/Ruchu/");
//      ConfInfo.dingtalk.OpenProgram();//统计用户

//        TerBiliLive_Greet_Ui login =new TerBiliLive_Greet_Ui();

        new Greet();
//        dialog.pack();
//        dialog.setVisible(true);
//        new Greet();


    }
}
