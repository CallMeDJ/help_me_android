package com.help.activity.im;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ChatNotificationBroadcastReceiver extends BroadcastReceiver{
	NotificationManager nm ;
	public static Integer CHAT_NOTIFICATION_ID = 12;
	public ChatNotificationBroadcastReceiver(){
		
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		//uncompleted notification..
		nm = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
		Notification notification = new Notification();
		notification.setLatestEventInfo(context, "new chat", "chat", null);
		nm.notify(CHAT_NOTIFICATION_ID, notification);
	}

}
