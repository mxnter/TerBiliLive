package com.TerBiliLive.TerBiliLive;

import java.util.Map;

public class SendPost {
	public SendPost(){
	}
	public String  SendPost(String url) {
		return SendPost(url,null,"");
	}
	public String  SendPost(String url, Map<String, String> paramMap) {
        return SendPost(url,paramMap,"");
	}
	public String  SendPost(String url, Map<String, String> paramMap, String cookie) {
		return HttpClient.SendPost(url,paramMap,cookie);
	}
	public static String doJsonPost(String urlPath, String Json) {
		return doJsonPost(urlPath,Json,null);
	}
	public static String doJsonPost(String urlPath, String Json,String cookie) {
		return HttpClient.SendPostJson(urlPath,Json,cookie);
	}

}
