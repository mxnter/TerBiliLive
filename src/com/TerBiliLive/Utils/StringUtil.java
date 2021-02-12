package com.TerBiliLive.Utils;

/**
 * @author myetear
 */
public class StringUtil {
    public static void main(String[] args) {
        String s = "sid=99thxb27; bili_jct=9b12ce745318a3b6f719d8027f053184; SESSDATA=447d5f36%2C1627444128%2Cb3b4a*11; DedeUserID__ckMd5=3a7bd8fa83a6790a; DedeUserID=309345634; ";
        System.out.println(stringInterception(s,"; bili_jct=","; SESSDATA="));
    }

    /**
     * 截取字符串
     * @param str 截取的字符串
     * @param start 开始字符串
     * @param end 结束字符串
     * @return 截取完成字符串
     */
    public static String stringInterception(String str,String start,String end){
        String strr = str;
        //之后字符串
        String str1=str.substring(0, str.indexOf(start));
        strr=str.substring(str1.length()+start.length(), str.length());
        //截取?之前字符串
        strr=strr.substring(0, strr.indexOf(end));
        return strr;

    }
}
