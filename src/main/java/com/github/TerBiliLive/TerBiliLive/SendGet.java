package com.github.TerBiliLive.TerBiliLive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SendGet {
	public static String sendGet(String url) {
		return sendGet(url,null,null);
	}
	public static String sendGet(String url,String cookie) {
		return sendGet(url,null,cookie);
	}

	public static String sendGet(String url, String param, String cookie) {
		String result = "";
		BufferedReader in = null;
		// String cookie ="";
		try {
			String urlNameString = url ;//+ "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("Cookie", cookie);
			// 建立实际的连接
			connection.connect();
//			// 获取所有响应头字段
//			Map<String, List<String>> map = connection.getHeaderFields();
//			// 遍历所有的响应头字段
//			for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
//			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

}