package com.help.activity;

/**
 * Created by YANGZHAO549 on 2015-08-19.
 */
public class CommonAPI {
	public static String server = "http://localhost:8787";
	
	public static String Host = "http://1.pinganhelpersae.sinaapp.com/";
	public static String HOME_URL = Host + "index.php?";
	
	//登录接口 http://1.pinganhelpersae.sinaapp.com/index.php?m=User&a=login
	public static String API_LOGIN = HOME_URL+"m=User&a=login";
	
	public static String GET_NEW_CHAT = Host + "getNewChat.php?";

}
