package com.github.TerBiliLive.TerBiliLive;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

public class SendPost {
	public SendPost(){
	}


	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的 URL

	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public String  SendPost(String url) {
		return SendPost(url,null,"");
	}
	public String  SendPost(String url, Map<String, String> paramMap) {
        return SendPost(url,paramMap,"");
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的 URL

	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public String  SendPost(String url, Map<String, String> paramMap, String cookie) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			// conn.setRequestProperty("Content-Type","application/x-form-urlencoded");
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("Connection", "keep-alive");
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			conn.setRequestProperty("Cookie", cookie);

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());

			// 设置请求属性
			String param = "";
			if (paramMap != null && paramMap.size() > 0) {
				Iterator<String> ite = paramMap.keySet().iterator();
				while (ite.hasNext()) {
					String key = ite.next();// key
					String value = paramMap.get(key);
					param += key + "=" + value + "&";
				}
				param = param.substring(0, param.length() - 1);
				// System.out.print(param+"\n");
			}

			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			//	LogUtil.putLog(TimeUtil.getFormatDay(), TimeUtil.getFormatHour(), "发送 POST 请求出现异常！"+"\n");
			//TerBZDGG.HFJ_UiT_Time.setText("异常，请重启软件");
			//System.err.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}


	public static String doJsonPost(String urlPath, String Json) {
		return doJsonPost(urlPath,Json,null);
	}
	//发送JSON字符串 如果成功则返回成功标识。
	public static String doJsonPost(String urlPath, String Json,String cookie) {
		// HttpClient 6.0被抛弃了
		String result = "";
		BufferedReader reader = null;
//		System.out.print(Json);
		try {
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Connection", "Keep-Alive");
			if(cookie!=null){
				conn.setRequestProperty("Cookie", cookie);
			}
			conn.setRequestProperty("Charset", "UTF-8");
			// 设置文件类型:
			conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
			// 设置接收类型否则返回415错误
			//conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
			conn.setRequestProperty("accept","application/json");
			// 往服务器里面发送数据
			if (Json != null ) {
				byte[] writebytes = Json.getBytes("UTF-8");
				// 设置文件长度
				conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
				OutputStream outwritestream = conn.getOutputStream();
				outwritestream.write(Json.getBytes("UTF-8"));
				outwritestream.flush();
				outwritestream.close();
//				System.out.println( "doJsonPost: conn"+conn.getResponseCode());
			}
			if (conn.getResponseCode() == 200) {
				reader = new BufferedReader(
						new InputStreamReader(conn.getInputStream(),"UTF-8"));
				result = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
