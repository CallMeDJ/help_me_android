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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.help.R;
import com.help.activity.LoginActivity;
import com.help.base.BaseActivity;
import com.help.util.ProcessDialogUtil;
import com.help.util.Tool;

public class MoreActivity extends BaseActivity implements OnClickListener {

	// 个人信息
	private RelativeLayout layout1 = null;
	// 钱包
	private RelativeLayout layout2 = null;
	// 设置
	private RelativeLayout layout3 = null;
	// 关于
	private RelativeLayout layout4 = null;

	private TextView name = null;
	private TextView phone = null;

	// 退出
	private Button exit = null;

	private Dialog juhua = null;

	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.main_more;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("个人");

		juhua = new ProcessDialogUtil(MoreActivity.this);

		name = (TextView) findViewById(R.id.p_name);
		phone = (TextView) findViewById(R.id.p_phone);

		layout1 = (RelativeLayout) findViewById(R.id.playout1);
		layout2 = (RelativeLayout) findViewById(R.id.playout2);
		layout3 = (RelativeLayout) findViewById(R.id.playout3);
		layout4 = (RelativeLayout) findViewById(R.id.playout4);

		exit = (Button) findViewById(R.id.pbtn);

		phone.setText(Tool.readData(this, "user", "phone"));
	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();

		layout1.setOnClickListener(this);
		layout2.setOnClickListener(this);
		layout3.setOnClickListener(this);
		layout4.setOnClickListener(this);
		exit.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.playout1:
			// 跳转个人信息
			startActivity(new Intent(MoreActivity.this, PersonalInfo.class));
			anim_right_in();
			break;
		case R.id.playout2:
			// 跳转到钱包
			startActivity(new Intent(MoreActivity.this, Wallet.class));
			anim_right_in();
			break;
		case R.id.playout3:
			// 跳转到设置
			startActivity(new Intent(MoreActivity.this, SheZhiActivity.class));
			anim_right_in();
			break;
		case R.id.playout4:
			// 跳转关于
			startActivity(new Intent(MoreActivity.this, JianYiActivity.class));
			anim_right_in();
			break;
		case R.id.pbtn:
			// 退出
			clearLogin();
			Intent intent3 = new Intent(this, LoginActivity.class);
			intent3.putExtra("from", "setting");
			startActivity(intent3);
			finish();
			anim_right_out();
			break;
		default:
			break;
		}
	}

	// 清除登录账户，仅仅是登录人的登录状态，
	private void clearLogin() {
		Tool.writeData(MoreActivity.this, "user", "login", "");
		Tool.writeData(MoreActivity.this, "user", "userId", "");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// if (!needBack) {
		// return super.onKeyDown(keyCode, event);
		// }
		// 先判断是否是返回键
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			return false;
		}
		return false;
	}

}
