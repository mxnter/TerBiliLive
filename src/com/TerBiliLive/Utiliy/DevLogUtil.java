package com.TerBiliLive.Utiliy;
import com.TerBiliLive.Info.ConfInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DevLogUtil {
	public static void printf(Object log){
		if(ConfInfo.dev){
			System.out.println(log);
		}
	}
}

