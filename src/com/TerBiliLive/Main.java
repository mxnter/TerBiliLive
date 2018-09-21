package com.TerBiliLive;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Ui.TerBiliLive_Hi_Ui;
import com.TerBiliLive.Ui.TerBiliLive_Login_Ui;
import com.TerBiliLive.Ui.TerBiliLive_Ui;
import com.TerBiliLive.Utiliy.FileUtil;
import com.TerBiliLive.Utiliy.TimeUtil;
import com.TerBiliLive.Utiliy.XmlUtil;



/*全软件启动类*/
/*当前版本： HFJ 02 -2018年6月2日14点45分*/

public class Main {

    public static void main(String[] args) {
	// write your code here

        //创建目录
        FileUtil.createDir("Ter/data/");
        FileUtil.createDir("Ter/log/");
        FileUtil.createDir("Ter/Dm/");


//        ConfInfo.xmlUtil.readData();
        ConfInfo.jsonUtil.readData();
        System.out.println(ConfInfo.confData.toString());

        TerBiliLive_Hi_Ui login =new TerBiliLive_Hi_Ui();

        /*开启发送弹幕的代码*/
        /*
        TerBiliLive_HFJ_Ui HFJ = new TerBiliLive_HFJ_Ui();
        HFJ.HFJ_UiT_RoomId.setText(FileUtil.readFile("RoomId"));
        //HFJ.HFJ_UiT_Text.setText(FileUtil.readFile("Text"));
        HFJ.HFJ_UiT_Cookie.setText(FileUtil.readFile("Cookie"));
        //System.out.print(readFile("RoomId.txt"));
        HFJ.HFJ_UiT_Time.setText("Ter 简单制造");
        HFJ.HFJ_UiT_State.setText("欢迎使用回复姬");
        */





    }
}
