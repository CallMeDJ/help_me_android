package com.help.been;
 
import org.json.JSONException;
import org.json.JSONObject;


import android.content.Context;

import com.help.util.Tool;

public class UserMode {

	public String yhzh; // 账户
	public String nc; // 昵称
	public String xb; // 性别
	public String csrq; // 出生日期
	public String sjh; // 手机号
	public String sfzh; // 身份证号
	public String xm; // 姓名
	public String sjsfsz; // 是否设置了手机号
	public String sfzsfrz; // 是否设置了身份认证
	
	public String znxwdts;//通知未读
	
	
	public String getZnxwdts() {
		return znxwdts;
	}

	public void setZnxwdts(String znxwdts) {
		this.znxwdts = znxwdts;
	}

	public String getYhzh() {
		return yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}


	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}


	public String getNc() {
		return nc;
	}

	public void setNc(String nc) {
		this.nc = nc;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getSjh() {
		return sjh;
	}

	public void setSjh(String sjh) {
		this.sjh = sjh;
	}

	public String getSjsfsz() {
		return sjsfsz;
	}

	public void setSjsfsz(String sjsfsz) {
		this.sjsfsz = sjsfsz;
	}

	public String getSfzsfrz() {
		return sfzsfrz;
	}

	public void setSfzsfrz(String sfzsfrz) {
		this.sfzsfrz = sfzsfrz;
	}
  
	// 初始化数据
	public UserMode() {
		// TODO Auto-generated constructor stub
		yhzh = "";
		nc = "0.00";
	}

	public void initMakeData(JSONObject json) {
		try {
			this.yhzh = json.getString("yhzh");
			this.nc = json.getString("nc");
			this.xb = json.getString("xb");
			this.csrq = json.getString("csrq");
			this.sjh = json.getString("sjh");
			this.sjsfsz = json.getString("sjsfsz");
			this.sfzsfrz = json.getString("sfzsfrz");
			this.sfzh = json.getString("sfzh");
			this.xm = json.getString("xm");
			this.znxwdts = json.getString("znxwdts");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// 手机号的两端显示
	public String new_mobile() {
		StringBuffer string = new StringBuffer();
		if (this.sjh.length() == 11) {
			string.append(this.sjh.substring(0, 3));
			string.append("****");
			string.append(this.sjh.substring(7, 11));
			return new String(string);
		}
		return "";
	}
 
	
	//写到数据库中
	public void saveUserToDB(Context ctx){
 
		Tool.writeData(ctx, "user", "yhzh", this.getYhzh());
		Tool.writeData(ctx, "user", "nc", this.getNc());
		Tool.writeData(ctx, "user", "xb", this.getXb());
		Tool.writeData(ctx, "user", "csrq", this.getCsrq());
		Tool.writeData(ctx, "user", "sjh", this.getSjh());
		Tool.writeData(ctx, "user", "sfzh", this.getSfzh());
		Tool.writeData(ctx, "user", "xm", this.getXm());
		Tool.writeData(ctx, "user", "sjsfsz", this.getSjsfsz());
		Tool.writeData(ctx, "user", "sfzsfrz", this.getSfzsfrz());
		Tool.writeData(ctx, "user", "znxwdts", this.getZnxwdts());
	}
 
}
