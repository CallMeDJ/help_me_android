package com.help.activity;
import com.help.activity.im.ChatService;

import android.app.Application;
import android.content.Intent;

public class MyApplication extends Application {
	
	public static final boolean VerifyHttps = true;
	static MyApplication instance;

	/**
	 * 单例模式，获取 Application对象
	 * @return
	 */
	public static MyApplication getInstance() {
		return instance;
	}
	
	
	//加载百度地图，
	@Override
	public void onCreate() {
		super.onCreate();
		StaticDatas.context = this;
		Intent ChatServiceIntent = new Intent(this,ChatService.class);
		startService(ChatServiceIntent);
	}
}
