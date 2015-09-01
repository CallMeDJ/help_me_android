package com.help.activity.index;

import android.graphics.Bitmap;
import android.view.View;

import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.Marker;

/**
 * Created by Well_Done on 2015/8/25.
 */
// 锟斤拷锟斤拷锟洁（锟斤拷图锟斤拷锟斤拷时使锟矫ｏ拷
public class TaskObject {

	private String mmasterPerson;// 时间
	private String mtaskContext;// 内容
	private String mtaskReward;// 金钱
	private String maddress;// 地址
	private String mlength;// 距离
	private double mLat;// 经纬度
	private double mLng; //

	Bitmap mbmp;// 头像
	Bitmap mlittlebmp;// 头像下标

	int mbmpresid;// 头像id
	int mlittlebmpresid;// 头像下标id

	private Marker mmarker;// 地图上的覆盖点
	View mlayoutView;// 头像view.

	TaskObject() {
	}

	TaskObject(String mmasterPerson, String mtaskContext, String add,
			String length, String mtaskReward, double mLat, double mLng) {
		this.mmasterPerson = mmasterPerson;
		this.mtaskContext = mtaskContext;
		this.mtaskReward = mtaskReward;
		this.maddress = add;
		this.mlength = length;
		this.mLat = mLat;
		this.mLng = mLng;
		this.mmarker = null;
	}

	public void setMmasterPerson(String mmasterPerson) {
		this.mmasterPerson = mmasterPerson;
	}

	public void setMtaskContext(String mtaskContext) {
		this.mtaskContext = mtaskContext;
	}

	public void setMtaskReward(String mtaskReward) {
		this.mtaskReward = mtaskReward;
	}

	public void setmLat(double mLat) {
		this.mLat = mLat;
	}

	public String getMaddress() {
		return maddress;
	}

	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}

	public String getMlength() {
		return mlength;
	}

	public void setMlength(String mlength) {
		this.mlength = mlength;
	}

	public void setmLng(double mLng) {
		this.mLng = mLng;
	}

	public void setMmarker(Marker mmarker) {
		this.mmarker = mmarker;
	}

	public void setMbmpresid(int mbmpresid) {
		this.mbmpresid = mbmpresid;
	}

	public void setMlittlebmpresid(int mlittlebmpresid) {
		this.mlittlebmpresid = mlittlebmpresid;
	}

	public void setMlayoutView(View mlayoutView) {
		this.mlayoutView = mlayoutView;
	}

	public String getMmasterPerson() {
		return mmasterPerson;
	}

	public String getMtaskContext() {
		return mtaskContext;
	}

	public String getMtaskReward() {
		return mtaskReward;
	}

	public double getmLat() {
		return mLat;
	}

	public double getmLng() {
		return mLng;
	}

	public Marker getMmarker() {
		return mmarker;
	}

	public int getMbmpresid() {
		return mbmpresid;
	}

	public int getMlittlebmpresid() {
		return mlittlebmpresid;
	}

	public View getMlayoutView() {
		return mlayoutView;
	}

	@Override
	public String toString() {
		return mmasterPerson + "\n" + mtaskContext + "\n" + mtaskReward;
	}
}
