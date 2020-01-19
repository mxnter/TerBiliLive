package com.github.TerBiliLive.Utiliy;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogUtil {


	// 输出日志 ProjectName 项目名，区分日志
	public static void putLog(String Day, String Date, String Log,String ProjectName ) {
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
	public static void putLog(String Day, String Date, String Log ,String ProjectName,String path ) {
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

