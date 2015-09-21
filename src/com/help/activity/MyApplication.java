<<<<<<< HEAD
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
=======
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
>>>>>>> f5dedd1b44cc18fab8dcc9e1bf20e6943e77ffd3
