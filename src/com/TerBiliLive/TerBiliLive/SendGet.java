package com.TerBiliLive.TerBiliLive;


public class SendGet {
	public static String sendGet(String url) {
		return sendGet(url,null,null);
	}
	public static String sendGet(String url,String cookie) {
		return sendGet(url,null,cookie);
	}

	public static String sendGet(String url, String param, String cookie) {
		return HttpClient.sendGet(url, param, cookie);
	}
}