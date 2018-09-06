package com.TerBiliLive.Utiliy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DmLogUtil {



    // 输出日志 ProjectName 项目名，区分日志
    public static void putDmLog(String Day, String Date, String DmLog ,String roomid ) {
        FileWriter fw = null;
        try {
            // 如果文件存在，则追加内容；如果文件不存在，则创建文件
            File f = new File("Ter/Dm/" +"["+roomid+"]"+ Day+ ".txt");
            fw = new FileWriter(f, true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(DmLog); //Date + ":" +
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
