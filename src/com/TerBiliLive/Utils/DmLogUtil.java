package com.TerBiliLive.Utils;

import com.TerBiliLive.Info.ConfInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * CODE IS POETRY
 *
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 12:19 2019/2/3
 * @Name ：弹幕输出工具类
 */
public class DmLogUtil {



    public static void putBarrageInfoDatabase(String time, String roomId,String cmd, String guard, String vip, String isadmin, String medal, String userLevel, String uid, String nickname, String barrage,String msg, String info) {
        List<String> sqlValue = new ArrayList<>();
        sqlValue.add(time);
        sqlValue.add(roomId);
        sqlValue.add(cmd);
        sqlValue.add(guard);
        sqlValue.add(vip);
        sqlValue.add(isadmin);
        sqlValue.add(medal);
        sqlValue.add(userLevel);
        sqlValue.add(uid);
        sqlValue.add(nickname);
        sqlValue.add(barrage);
        sqlValue.add(msg);
        sqlValue.add(info);
        ConfInfo.databaesUtil.executeUpdate(ConfInfo.Database_InsertDatabaseBarrage,sqlValue);
    }

    // 输出弹幕日志 输出到Ter/Dm/[房间号]日期.txt
    public static void putDmLog(String Day, String Date, String DmLog ,String roomid ) {
        if(DmLog.equals("")||DmLog.equals("\n")){
            System.out.println("输出弹幕日志 一个空的字符串");
            return;
        }
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
