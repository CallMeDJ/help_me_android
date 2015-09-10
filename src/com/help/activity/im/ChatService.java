package com.help.activity.im;

import java.util.Timer;
import java.util.TimerTask;

import android.app.IntentService;
import android.content.Intent;

public class ChatService extends IntentService{

	public ChatService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public static ChatService getInstance(){
		ChatService service = new ChatService("ChatService");
		return service;
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		//进行定时任务，每5秒刷新一次
		long delayMiniutes = 5;
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(getNewChat, 0, delayMiniutes);

	}
	
	private TimerTask getNewChat = new TimerTask() {
		@Override
		public void run() {
			ChatManager.refleshChat(ChatService.this);
		}
	};
}
