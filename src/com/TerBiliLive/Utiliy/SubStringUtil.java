package com.TerBiliLive.Utiliy;

public class SubStringUtil {
	/**
	 * 拆分字符串
	 * @param text 字符串
	 * @param left 左边的标识字符串
	 * @param right 右边标识字符串
	 * @return 取出的字符串
	 */
	public static String getSubString(String text, String left, String right) {
		String result = "";
		int zLen;
		if (left == null || left.isEmpty()) {
			zLen = 0;
		} else {
			zLen = text.indexOf(left);
			if (zLen > -1) {
				zLen += left.length();
			} else {
				zLen = 0;
			}
		}
		int yLen = text.indexOf(right, zLen);
		if (yLen < 0 || right == null || right.isEmpty()) {
			yLen = text.length();
		}
		result = text.substring(zLen, yLen);
		return result;
	}

}