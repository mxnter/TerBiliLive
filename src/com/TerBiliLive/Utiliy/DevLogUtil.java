package com.TerBiliLive.Utiliy;
import com.TerBiliLive.Info.ConfInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 日志输出工具类,用于同一开关输出
 * @author Mxnter
 */

public class DevLogUtil {
	public static void printf(Object log){
		if(ConfInfo.dev){
			System.out.println(log);
		}
	}
}

