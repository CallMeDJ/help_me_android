package com.help.activity;

import android.graphics.Bitmap;

public class GlobalRankModel {

	
	public GlobalRankModel(	Bitmap icon,String userName,String ranking){
		this.icon = icon;
		this.userName = userName;
		this.ranking = ranking;
	}
	private Bitmap icon;
	private String userName;
	private String ranking;
	public Bitmap getIcon() {
		return icon;
	}
	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
}

