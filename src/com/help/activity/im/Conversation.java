package com.help.activity.im;

import android.graphics.Bitmap;

public class Conversation {
	public Bitmap icon;
	public String user_name;
	public String user_id;
	public String content;
	public String date;
	
	public Conversation(Bitmap icon, String user_name,String user_id, String content, String date){
		this.icon = icon;
		this.user_name = user_name;
		this.user_id = user_id;
		this.content = content;
		this.date = date;
	}
}