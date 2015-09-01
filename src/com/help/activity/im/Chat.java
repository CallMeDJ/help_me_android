package com.help.activity.im;

import android.graphics.Bitmap;

public class Chat{
	public Bitmap icon;
	public String user_name;
	public String content;
	public String date;
	
	public Chat(Bitmap icon,String user_name, String content,String date){
		this.icon = icon;
		this.user_name = user_name;
		this.content = content;
		this.date = date;
	}
}