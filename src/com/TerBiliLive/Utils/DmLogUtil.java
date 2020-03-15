package com.TerBiliLive.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * CODE IS POETRY
 *
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 12:19 2019/2/3
 * @Name ：弹幕输出工具类
 */
public class DmLogUtil {



    // 输出弹幕日志 输出到Ter/Dm/[房间号]日期.txt
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
