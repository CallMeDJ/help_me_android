package com.help.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.help.R;
import com.help.base.BaseActivity;
import com.help.util.ProcessDialogUtil;

public class YanZhengShouJiActivity extends BaseActivity implements
		OnClickListener{

	private TextView shouji = null;
	private EditText yanzhengma = null;

	private Button next = null;
	private Button huoqu = null;

	private String shoujihao = "";
	private String key = "";

	private Dialog juhua = null;
	
	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.main_wangjimima_yanzhengshouji;
	}

	@Override
	protected void getIntentWord() {
		// TODO Auto-generated method stub
		super.getIntentWord();
		if (getIntent().getStringExtra("shouji") != null) {
			shoujihao = getIntent().getStringExtra("shouji");
		}

	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("验证手机");
		showBack();

		juhua = new ProcessDialogUtil(YanZhengShouJiActivity.this);
		
		shouji = (TextView) findViewById(R.id.main_wangji_yanzhengshouji_shouji);
		shouji.setText(shoujihao);

		yanzhengma = (EditText) findViewById(R.id.main_wangji_yanzhengshouji_et);
		next = (Button) findViewById(R.id.main_wangji_yanzhengshouji_next);
		huoqu = (Button) findViewById(R.id.main_wangji_yanzhengshouji_huoqu);

		// 获取验证码
		startTimer();
	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarBack.setOnClickListener(this);

		next.setOnClickListener(this);
		huoqu.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.title_bar_back:
			YanZhengShouJiActivity.this.finish();
			anim_right_out();
			break;
		case R.id.main_wangji_yanzhengshouji_huoqu:
			break;
		case R.id.main_wangji_yanzhengshouji_next:
			// 下一步
			Intent intent = new Intent(YanZhengShouJiActivity.this,
					ChongZhiMiMaActivity.class);
			intent.putExtra("shouji", shoujihao);
			intent.putExtra("key", key);
			startActivity(intent);
			anim_right_in();
			finish();

			break;

		default:
			break;
		}
	}

	// 获取验证码的模块
	private Timer mTimer = null;
	private int time = 60;

	// 开启倒计时线程
	private void startTimer() {
		if (mTimer == null) {
			mTimer = new Timer();
		}
		mTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				time--;
				String text = "";
				if (time <= 0) {
					time = 60;
					text = "重新获取";
					Message msg = handler.obtainMessage(1, text);
					handler.sendMessage(msg);
				} else {
					text = time + "";
					Message msg = handler.obtainMessage(0, text);
					handler.sendMessage(msg);
				}
			}
		}, 1000, 1000);
	}

	// 页面结束的时候，线程销毁，
	private void canclTimer() {
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
	}

	// 线程交互，刷新倒计时
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0: {
				String text = (String) msg.obj;
				huoqu.setText(text);
				huoqu.setTextSize(16);
				huoqu.setEnabled(false);
			}
				break;
			case 1: {
				String text = (String) msg.obj;
				canclTimer();
				huoqu.setText(text);
				huoqu.setTextSize(10);
				huoqu.setEnabled(true);
			}
				break;
			default:
				break;
			}
		};
	};

	// 需要重写finish();停止线程
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		canclTimer();
	}

}
