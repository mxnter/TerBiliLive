package com.github.TerBiliLive;

import com.github.TerBiliLive.Info.ConfInfo;
import com.github.TerBiliLive.Ui.TerBiliLive_Greet_Ui;
import com.github.TerBiliLive.Utiliy.FileUtil;
import com.github.TerBiliLive.Utiliy.LogUtil;

import static com.github.TerBiliLive.Utiliy.TimeUtil.getFormatDay;
import static com.github.TerBiliLive.Utiliy.TimeUtil.getFormatHour;



/*全软件启动类*/
/*当前版本： HFJ 02 -2018年6月2日14点45分*/

public class Main {

    public static void main(String[] args) {
	// write your code here

        //创建目录
        FileUtil.createDir("Ter/data/");
        FileUtil.createDir("Ter/log/");
        FileUtil.createDir("Ter/Dm/");
//        ConfInfo.dingtalkUtil.OpenProgram();//统计用户

//        ConfInfo.xmlUtil.readData();
        ConfInfo.jsonUtil.readData();
        System.out.println(ConfInfo.confData.toString());
        LogUtil.putLog(getFormatDay(), getFormatHour(), ConfInfo.confData.toString() + "\n", "TerBiliLive Logxx");
        TerBiliLive_Greet_Ui login =new TerBiliLive_Greet_Ui();

        /*开启发送弹幕的代码*/
        /*
        TerBiliLive_SendBarrage_Ui HFJ = new TerBiliLive_SendBarrage_Ui();
        HFJ.HFJ_UiT_RoomId.setText(FileUtil.readFile("RoomId"));
        //HFJ.HFJ_UiT_Text.setText(FileUtil.readFile("Text"));
        HFJ.HFJ_UiT_Cookie.setText(FileUtil.readFile("Cookie"));
        //System.out.print(readFile("RoomId.txt"));
        HFJ.HFJ_UiT_Time.setText("Ter 简单制造");
        HFJ.HFJ_UiT_State.setText("欢迎使用回复姬");
        */





    }
}
