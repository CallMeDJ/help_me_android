package com.help.activity.more;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.help.R;
import com.help.activity.LoginActivity;
import com.help.base.BaseActivity;
import com.help.util.ProcessDialogUtil;
import com.help.util.Tool;

public class SheZhiActivity extends BaseActivity implements OnClickListener {

	// 账户
	private RelativeLayout xiugai = null;
	// 浏览设置
	private RelativeLayout liulan = null;
	// 通知
	private RelativeLayout tongzhi = null;
	// 聊天
	private RelativeLayout liaotian = null;
	// 检查更新
	private RelativeLayout jiancha = null;
	// 退出按钮
	private Button btn = null;

	private Dialog juhua = null;

	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.main_more_shezhi;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("设置");
		showBack();

		juhua = new ProcessDialogUtil(SheZhiActivity.this);

		xiugai = (RelativeLayout) findViewById(R.id.more_shezhi_tv1);
		jiancha = (RelativeLayout) findViewById(R.id.more_shezhi_tv5);
		liulan = (RelativeLayout) findViewById(R.id.more_shezhi_tv2);
		tongzhi = (RelativeLayout) findViewById(R.id.more_shezhi_tv3);
		liaotian = (RelativeLayout) findViewById(R.id.more_shezhi_tv4);
		btn = (Button) findViewById(R.id.more_shezhi_btn);

	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarBack.setOnClickListener(this);
		xiugai.setOnClickListener(this);
		jiancha.setOnClickListener(this);
		tongzhi.setOnClickListener(this);
		liaotian.setOnClickListener(this);
		liulan.setOnClickListener(this);

		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.title_bar_back:
			// 返回按钮
			SheZhiActivity.this.finish();
			anim_right_out();
			break;
		case R.id.more_shezhi_tv1:
			// 调接口更新
			Toast.makeText(SheZhiActivity.this, "账户", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.more_shezhi_tv2:
			// 调接口更新
			Toast.makeText(SheZhiActivity.this, "浏览设置", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.more_shezhi_tv3:
			// 调接口更新
			Toast.makeText(SheZhiActivity.this, "通知", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.more_shezhi_tv4:
			// 调接口更新
			Toast.makeText(SheZhiActivity.this, "聊天", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.more_shezhi_tv5:
			// 调接口更新
			Toast.makeText(SheZhiActivity.this, "检查更新", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.more_shezhi_btn:
			// 退出按钮.清除登录账户密码，回到首页
			clearLogin();
			Intent intent3 = new Intent(this, LoginActivity.class);
			intent3.putExtra("from", "setting");
			startActivity(intent3);
			finish();
			anim_right_out();
		default:
			break;
		}
	}

	// 清除登录账户，仅仅是登录人的登录状态，
	private void clearLogin() {
		// CommonUtil.clearByKey(SheZhiActivity.this, "loginState", "", "");
		Tool.writeData(SheZhiActivity.this, "user", "login", "");
		Tool.writeData(SheZhiActivity.this, "user", "userId", "");
		Tool.writeData(SheZhiActivity.this, "user", "cookieValue", "");
	}

}
