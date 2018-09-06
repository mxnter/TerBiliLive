package com.TerBiliLive.Utiliy;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	
	public static String getFormatHour() {
		Date date = new Date();
		long times = date.getTime();// ʱ���
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}

	public static String getFormatDay() {
		Date date = new Date();
		long times = date.getTime();// ʱ���
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}
	public static String getFormat() {
		Date date = new Date();
		long times = date.getTime();// ʱ���
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}

}
