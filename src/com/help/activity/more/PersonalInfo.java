package com.help.activity.more;

import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.help.R;
import com.help.activity.LoginActivity;
import com.help.base.BaseActivity;
import com.help.util.ProcessDialogUtil;
import com.help.util.Tool;

public class PersonalInfo extends BaseActivity implements OnClickListener {

	// 头像
	private RelativeLayout layout1 = null;
	// 昵称
	private RelativeLayout layout2 = null;
	// 性别
	private RelativeLayout layout3 = null;
	// 地区
	private RelativeLayout layout4 = null;
	// 签名
	private RelativeLayout layout5 = null;

	private ImageView img = null;
	private TextView tv1 = null;
	private TextView tv2 = null;
	private TextView tv3 = null;
	private TextView tv4 = null;

	private Dialog juhua = null;

	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.peosonalinfo;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("个人");
		showBack();

		juhua = new ProcessDialogUtil(PersonalInfo.this);

		tv1 = (TextView) findViewById(R.id.geren_tv1);
		tv2 = (TextView) findViewById(R.id.geren_tv2);
		tv3 = (TextView) findViewById(R.id.geren_tv3);
		tv4 = (TextView) findViewById(R.id.geren_tv4);

		layout1 = (RelativeLayout) findViewById(R.id.geren_layout1);
		layout2 = (RelativeLayout) findViewById(R.id.geren_layout2);
		layout3 = (RelativeLayout) findViewById(R.id.geren_layout3);
		layout4 = (RelativeLayout) findViewById(R.id.geren_layout4);
		layout5 = (RelativeLayout) findViewById(R.id.geren_layout5);

	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarBack.setOnClickListener(this);

		layout1.setOnClickListener(this);
		layout2.setOnClickListener(this);
		layout3.setOnClickListener(this);
		layout4.setOnClickListener(this);
		layout5.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.geren_layout1:
			// 头像
			// startActivity(new Intent(PersonalInfo.this,
			// SheZhiActivity.class));
			anim_right_in();
			break;
		case R.id.geren_layout2:
			// 昵称
			Intent intent1 = new Intent(PersonalInfo.this,
					InfoEditActivity.class);
			intent1.putExtra("tv1", "设置昵称");
			intent1.putExtra("tv2", "1");
			startActivityForResult(intent1, 1);
			anim_right_in();
			break;
		case R.id.geren_layout3:
			// 性别
			Intent intent2 = new Intent(PersonalInfo.this,
					InfoEditActivity.class);
			intent2.putExtra("tv1", "设置性别");
			intent2.putExtra("tv2", "2");
			startActivityForResult(intent2, 2);
			anim_right_in();
			break;
		case R.id.geren_layout4:
			// 地区
			Intent intent3 = new Intent(PersonalInfo.this,
					InfoEditActivity.class);
			intent3.putExtra("tv1", "设置地区");
			intent3.putExtra("tv2", "3");
			startActivityForResult(intent3, 3);
			anim_right_in();
			break;
		case R.id.geren_layout5:
			// 签名
			Intent intent4 = new Intent(PersonalInfo.this,
					InfoEditActivity.class);
			intent4.putExtra("tv1", "设置签名");
			intent4.putExtra("tv2", "4");
			startActivityForResult(intent4, 4);
			anim_right_in();
			break;
		case R.id.title_bar_back:
			// 退出
			PersonalInfo.this.finish();
			anim_right_out();
			break;
		default:
			break;
		}
	}
}
