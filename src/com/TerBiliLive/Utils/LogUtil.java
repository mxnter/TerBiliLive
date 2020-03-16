package com.TerBiliLive.Utils;
import com.TerBiliLive.Info.ConfInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import static com.TerBiliLive.Utils.TimeUtil.getFormat;

public class LogUtil {


	public static void putLogSystemOut(String Log) {
		putLogDatabase("系统输出",Log);
	}

	public static void putLogGiftRecord(String Log) {
		putLogDatabase("礼物记录 ",Log);
	}

	public static void putLogUnknownGift(String Log) {
		putLogDatabase("未知礼物 ",Log);
	}

	public static void putLogSendBarrage(String Log) {
		putLogDatabase("发送弹幕 ",Log);
	}

	public static void putLogSendBarrageRecord(String Log) {
		putLogDatabase("发送弹幕记录 ",Log);
	}

	public static void putLogTimingBarrage(String Log) {
		putLogDatabase("定时弹幕 ",Log);
	}

	public static void putLogException(String Log) {
		putLogDatabase("异常信息 ",Log);
	}

	public static void putLogRoomSilent(String Log) {
		putLogDatabase("房间禁言 ",Log);
	}

	public static void putLogRoomBlock(String Log) {
		putLogDatabase("个人禁言 ",Log);
	}

	public static void putLogKnown(String Log) {
		putLogDatabase("已知推送 ",Log);
	}

	public static void putLogKnownUntreated(String Log) {
		putLogDatabase("已知未处理 ",Log);
	}

	public static void putLogUnknown(String Log) {
		putLogDatabase("未知推送 ",Log);
	}

	public static void putLog(String LogType,String Log) {
		putLogDatabase(LogType,Log);
	}

	public static void putLogDatabase(String LogType, String Log) {
		putLogDatabase(getFormat(),LogType,Log);

	}
	public static void putLogDatabase(String GenerationTime, String LogType, String Log) {
		ConfInfo.databaesUtil.executeUpdate("INSERT INTO \"SystemLog\" (\"GenerationTime\", \"LogType\", \"Log\") VALUES ('"+GenerationTime+"','"+LogType+"','"+Log+"')");
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

