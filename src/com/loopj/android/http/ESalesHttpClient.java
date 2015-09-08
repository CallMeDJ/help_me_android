package com.loopj.android.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;

public class ESalesHttpClient {

	// 网络请求管理器
	public static AsyncHttpClient client;

	public static final String HOST_COOKIE = "host_cookie";
	public static final String SALES_COOKIE = "sales_cookie";
	public static final String SHOP_COOKIE = "shop_cookie";

	static String currentCookie = HOST_COOKIE;
	
	// 网络请求post
	public static void requestPost(Context context, final String apiName,
			RequestParams params, AsyncHttpResponseHandler responseHandler) {
		try {
			if (client == null) {
				client = new AsyncHttpClient();
			}
			if (client != null) {
				client.post(context, apiName, params, responseHandler);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void requestGet(Context context, final String apiName,
			RequestParams params, AsyncHttpResponseHandler responseHandler) {
		try {
			if (client == null) {
				client = new AsyncHttpClient();
			}
			if (client != null) {
				client.get(context, apiName, params, responseHandler);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setDistCookie(String dist) {
		currentCookie = dist;
	}

}
