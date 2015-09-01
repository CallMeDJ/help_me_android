package com.help.activity.more;

import android.R.integer;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.example.help.R;
import com.help.base.BaseActivity;
import com.help.util.ProcessDialogUtil;

public class InfoEditActivity extends BaseActivity implements OnClickListener {

	private EditText et1 = null;

	private Dialog juhua = null;

	private String tv1 = "";
	private String tv2 = "";

	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.main_more_yijian;
	}

	@Override
	protected void getIntentWord() {
		// TODO Auto-generated method stub
		super.getIntentWord();
		if (getIntent().getStringExtra("tv1") != null) {
			tv1 = getIntent().getStringExtra("tv1");
			tv2 = getIntent().getStringExtra("tv2");
		}

	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle(tv1);
		showBack();
		showMenu();
		setMenu("保存");

		juhua = new ProcessDialogUtil(InfoEditActivity.this);

		et1 = (EditText) findViewById(R.id.more_yijian_et111);
	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarBack.setOnClickListener(this);
		titleBarMenu.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.title_bar_back:
			// 返回按钮
			InfoEditActivity.this.finish();
			anim_right_out();
			break;
		case R.id.title_bar_menu:
			// 提交按钮
			
			InfoEditActivity.this.finish();
			anim_right_out();
			break;
		default:
			break;
		}
	}

}
