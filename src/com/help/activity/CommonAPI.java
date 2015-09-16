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
	//注册接口 http://1.pinganhelpersae.sinaapp.com/index.php?m=User&a=login
	public static String API_REGISTER = HOME_URL+"m=User&a=register";
	//发布任务信息接口
	public static String API_PUBLICORDER = HOME_URL+"m=Order&a=publicOrder";
	//基于位置获取信息接口
	public static String API_BYLOCATION = HOME_URL+"m=Order&a=getOrderByLocation";
	//接受方取消
	public static String API_CANCEL_JIESHOU = HOME_URL+"m=Order&a=reciverCancelOrder";
	//发布方取消
	public static String API_CANCEL_FABU = HOME_URL+"m=Order&a=senderCancelOrder";
	//评价
	public static String API_EVALUATE = HOME_URL+"m=Order&a=evaluate";
	//接受
	public static String API_ACCEPT = HOME_URL+"m=Order&a=acceptOrder";
	//完成
	public static String API_COMPLETE = HOME_URL+"m=Order&a=finishOrder";
	//info
	public static String API_INFO = HOME_URL+"m=Order&a=getPushMsg";
	

}
