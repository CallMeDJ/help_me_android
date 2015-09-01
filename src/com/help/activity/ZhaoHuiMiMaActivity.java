package com.help.activity;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.help.R;
import com.help.base.BaseActivity;
import com.help.util.ProcessDialogUtil;

public class ZhaoHuiMiMaActivity extends BaseActivity implements
		OnClickListener{

	private EditText shouji = null;

	private Button next = null;

	private Dialog juhua = null;
	
	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.main_wangjimima_zhihuimima;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("找回密码");
		showBack();

		juhua = new ProcessDialogUtil(ZhaoHuiMiMaActivity.this);
		
		shouji = (EditText) findViewById(R.id.main_wangji_zhaohuimima_shouji);
		next = (Button) findViewById(R.id.main_wangji_zhaohuimima_btn);
	
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
			ZhaoHuiMiMaActivity.this.finish();
			anim_right_out();
			break;
		case R.id.main_wangji_zhaohuimima_btn:
			Intent intent = new Intent(ZhaoHuiMiMaActivity.this,
					YanZhengShouJiActivity.class);
			intent.putExtra("shouji",shouji.getText().toString() );
			startActivity(intent);
			anim_right_in();
			finish();
			break;

		default:
			break;
		}
	}

	

}
