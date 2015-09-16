package com.help.activity;
import com.baidu.mapapi.SDKInitializer;

import android.app.Application;

public class MyApplication extends Application {
	
	public static final boolean VerifyHttps = true;
	//加载百度地图，
	@Override
	public void onCreate() {
		super.onCreate();
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);
	}


}
