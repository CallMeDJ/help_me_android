package com.help.activity;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.help.R;
import com.help.base.BaseActivity;
import com.help.been.UserMode;
import com.help.util.ProcessDialogUtil;

public class ChongZhiMiMaActivity extends BaseActivity implements
		OnClickListener {

	// 新密码输入
	private EditText xin = null;
	// 确认新密码
	private EditText queren = null;
	// 完成
	private Button next = null;

	private String shoujihao = "";
	private String key = "";

	private Dialog juhua = null;

	// usermode
	private UserMode allUser = null;

	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.main_wangjimima_chongzhimima;
	}

	@Override
	protected void getIntentWord() {
		// TODO Auto-generated method stub
		super.getIntentWord();
		if (getIntent().getStringExtra("shouji") != null) {
			shoujihao = getIntent().getStringExtra("shouji");
		}
		if (getIntent().getStringExtra("key") != null) {
			key = getIntent().getStringExtra("key");
		}
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("重置密码");
		showBack();

		juhua = new ProcessDialogUtil(ChongZhiMiMaActivity.this);

		xin = (EditText) findViewById(R.id.main_wangji_chongzhimima_xin);
		queren = (EditText) findViewById(R.id.main_wangji_chongzhimima_queren);
		next = (Button) findViewById(R.id.main_wangji_chongzhimima_btn);

	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarBack.setOnClickListener(this);

		next.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.title_bar_back:
			ChongZhiMiMaActivity.this.finish();
			anim_right_out();
			break;
		case R.id.main_wangji_chongzhimima_btn:
			// 完成 应该记录登录状态，跳转到相应的界面，或者应该判断是否需要设置手势密码，进入首页
			Toast.makeText(ChongZhiMiMaActivity.this, "成功", 1000).show();
			anim_right_out();
			finish();
			break;

		default:
			break;
		}
	}

}
