package com.TerBiliLive.Utils;
import com.TerBiliLive.Info.ConfInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.TerBiliLive.Utils.TimeUtil.getFormat;
import static com.TerBiliLive.Utils.TimeUtil.getFormatDistance;

public class LogUtil {


	public static void putLogSystemOut(String Log, String cmd) {
		putLogDatabase("系统输出",Log, cmd);
	}

	public static void putLogGiftRecord(String Log, String cmd) {
		putLogDatabase("礼物记录",Log, cmd);
	}

	public static void putLogUnknownGift(String Log, String cmd) {
		putLogDatabase("未知礼物",Log, cmd);
	}

	public static void putLogSendBarrage(String Log, String cmd) {
		putLogDatabase("发送弹幕",Log, cmd);
	}

	public static void putLogSendBarrageRecord(String Log, String cmd) {
		putLogDatabase("发送弹幕记录",Log, cmd);
	}

	public static void putLogTimingBarrage(String Log, String cmd) {
		putLogDatabase("定时弹幕",Log, cmd);
	}

	public static void putLogException(String Log, String cmd) {
		putLogDatabase("异常信息",Log, cmd);
	}

	public static void putLogRoomSilent(String Log, String cmd) {
		putLogDatabase("房间禁言",Log, cmd);
	}

	public static void putLogRoomBlock(String Log, String cmd) {
		putLogDatabase("个人禁言",Log, cmd);
	}

	public static void putLogRoomAdminEntrance(String Log, String cmd) {
		putLogDatabase("房管任命",Log, cmd);
	}

	public static void putlogRoomAdmins(String Log, String cmd) {
		putLogDatabase("房管变更",Log, cmd);
	}

	public static void putLogKnown(String Log, String cmd) {
		putLogDatabase("已知推送",Log, cmd);
	}

	public static void putold(String Log, String cmd) {
		putLogDatabase("老版本推送",Log, cmd);
	}

	public static void putLogKnownUntreated(String Log, String cmd) {
		putLogDatabase("已知未处理",Log,cmd);
	}

	public static void putLogUnknown(String Log, String cmd) {
		putLogDatabase("未知推送",Log, cmd);
	}

	public static void putLog(String LogType,String Log, String cmd) {
		putLogDatabase(LogType,Log,cmd);
	}

	public static void putLogDatabase(String LogType, String Log, String cmd) {
		putLogDatabase(getFormat(),LogType,Log,cmd);

	}

	public static void clearDatabase() {
		ConfInfo.databaesUtil.executeUpdate(ConfInfo.Database_DeleteSystemLog+"'"+getFormatDistance(-7)+"'");

	}
	public static void putLogDatabase(String GenerationTime, String LogType, String Log, String cmd) {
		if(null==Log||"".equals(Log)) return;
		List<String> sqlValue = new ArrayList<>();
		sqlValue.add(GenerationTime);
		sqlValue.add(LogType);
		sqlValue.add(Log);
		sqlValue.add(cmd);
		ConfInfo.databaesUtil.executeUpdate("INSERT INTO SystemLog (GenerationTime, LogType, Log, cmd) VALUES (?,?,?,?)",sqlValue);
	}

	// 输出日志 ProjectName 项目名，区分日志
	public static void putLogFile(String Day, String Date, String Log,String ProjectName ) {
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("Ter/log/" + Day+ProjectName+ ".terlog");
			fw = new FileWriter(f, true);

		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(Date + ":" + Log);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 输出日志 ProjectName 项目名，区分日志 带路径版path
	public static void putLogFile(String Day, String Date, String Log ,String ProjectName,String path ) {
		FileUtil.createDir("Ter/log/"+path);
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("Ter/log/" +path+"/"+ Day+ProjectName+ ".terlog");
			fw = new FileWriter(f, true);

		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(Date + ":" + Log);
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

