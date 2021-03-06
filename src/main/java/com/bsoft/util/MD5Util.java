package com.bsoft.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 采用MD5加密解密
 * 
 * @author wujc
 */
public class MD5Util {
	// 正则表达式，用于匹配
	private final static Pattern pattern = Pattern.compile("\\d+");

	private final static String charset = "utf-8";

	public final static String key = "bsoft";

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}

	// 加密处理
	public static String encode(String src, String key) {
		try {
			// 得到一个指定的编码格式的字节数组，Linux和windows默认的编码格式不同，所以要指定特定的编码
			byte[] data = src.getBytes(charset);
			byte[] keys = key.getBytes();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < data.length; i++) {
				// 结合key和相应的数据进行加密操作,ofxx的作用是补码，byte是8bits，而int是32bits
				int n = (0xff & data[i]) + (0xff & keys[i % keys.length]);
				sb.append("@" + n);
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return src;
	}

	// 解密处理
	public static String decode(String src, String key) {
		if (src == null || src.length() == 0) {
			return src;
		}
		// 正则表达式字符串匹配
		Matcher m = pattern.matcher(src);

		List<Integer> list = new ArrayList<Integer>();
		// find方法(部分匹配):尝试去发现输入串中是否匹配相应的子串
		while (m.find()) {
			try {
				// 返回匹配到的子字符串
				String group = m.group();
				list.add(Integer.valueOf(group));
			} catch (Exception e) {
				e.printStackTrace();
				return src;
			}
		}

		// 如果有匹配的字符串
		if (list.size() > 0) {
			try {
				byte[] data = new byte[list.size()];
				byte[] keys = key.getBytes();
				// 相对于加密过程的解密过程
				for (int i = 0; i < data.length; i++) {
					data[i] = (byte) (list.get(i) - (0xff & keys[i % keys.length]));
				}
				return new String(data, charset);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return src;
		} else {
			return src;
		}
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	// // 测试主函数
	public static void main(String args[]) {
		System.out.println(encode("wujicheng1205", key));
		// System.out.println(decode("@217@232@217@207@215@202@216@221@205@165@148@163@164",
		// key));
	}
}