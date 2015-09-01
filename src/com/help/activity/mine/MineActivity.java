package com.help.activity.mine;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.help.R;
import com.help.base.BaseActivity;


public class MineActivity extends BaseActivity implements OnClickListener{

	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.main_mine;
	}

	@Override
	protected void getIntentWord() {
		// TODO Auto-generated method stub
		super.getIntentWord();
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("我的");
		showMenu();
		setMenu("菜单");

	}


	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarMenu.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.title_bar_menu:
			anim_right_in();
			break;
		default:
			break;
		}
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
