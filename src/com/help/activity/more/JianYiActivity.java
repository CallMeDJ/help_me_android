package com.help.activity.more;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.help.R;
import com.help.base.BaseActivity;
import com.help.been.UserMode;
import com.help.util.ProcessDialogUtil;
import com.help.util.Tool;

public class JianYiActivity extends BaseActivity implements OnClickListener{

	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.main_more_yijian2;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("关于我们");
		showBack();
	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.title_bar_back:
			// 返回按钮
			JianYiActivity.this.finish();
			anim_right_out();
			break;
		default:
			break;
		}
	}

	

}
