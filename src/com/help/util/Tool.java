package com.help.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.help.activity.LoginActivity;
import com.help.been.UserMode;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Tool {

	// 锟斤拷证锟街伙拷锟�
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	// 锟斤拷证锟斤拷锟斤拷
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email))
			return false;
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //锟斤拷匹锟斤拷
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 锟斤拷锟斤拷匹锟斤拷
		Matcher m = p.matcher(email);
		return m.matches();
	}

	// 锟斤拷证锟绞憋拷
	public static boolean isZipNO(String zipString) {
		String str = "^[1-9][0-9]{5}$";
		return Pattern.compile(str).matcher(zipString).matches();
	}

	// 保存为临时的文件
	public static void writeData(Context ctx, String name, String Key,
			String value) {
		SharedPreferences mySharedPreferences = ctx.getSharedPreferences(name,
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = mySharedPreferences.edit();
		editor.putString(Key, value);
		editor.commit();
	}

	// 读取数据
	public static String readData(Context ctx, String name, String Key) {
		SharedPreferences mySharedPreferences = ctx.getSharedPreferences(name,
				Activity.MODE_PRIVATE);
		// 使用getString方法获得value，注意第2个参数是value的默认值
		return mySharedPreferences.getString(Key, "");
	}

}
