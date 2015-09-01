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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.help.R;
import com.help.base.BaseActivity;
import com.help.been.UserMode;
import com.help.util.ProcessDialogUtil;
import com.help.util.Tool;
import com.help.util.WebViewActivity;

public class RegisterActivity extends BaseActivity implements OnClickListener{

	// 手机号
	private EditText et_phone = null;
	// 登陆密码
	private EditText et_pass = null;
	// 确认登录密码
	private EditText et_repass = null;
	// 验证码
	private EditText et_yanzhengma = null;
	// 推荐人服务码
	private EditText et_tuijian = null;
	// 获取验证码按钮
	private Button huoqu = null;
	// 我同意
	private CheckBox cb_tongyi = null;
	// 注册按钮
	private Button register = null;

	private UserMode allUser = null;

	private Dialog juhua = null;
	
	private TextView xieyi = null;
	
	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_regiseter;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("注册");
		showBack();
		
		juhua = new ProcessDialogUtil(RegisterActivity.this);

		et_phone = (EditText) findViewById(R.id.register_et_phone);
		et_pass = (EditText) findViewById(R.id.register_et_pass);
		et_repass = (EditText) findViewById(R.id.register_et_repass);
		et_yanzhengma = (EditText) findViewById(R.id.register_et_yanzhengma);
		et_tuijian = (EditText) findViewById(R.id.register_et_tuijianren);
		huoqu = (Button) findViewById(R.id.register_tv_huoqu);
		cb_tongyi = (CheckBox) findViewById(R.id.register_cb);
		register = (Button) findViewById(R.id.register_register);
		
		xieyi = (TextView)findViewById(R.id.register_xieyi);

	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarBack.setOnClickListener(this);
		huoqu.setOnClickListener(this);
		register.setOnClickListener(this);
		
		xieyi.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.title_bar_back:
			RegisterActivity.this.finish();
			anim_right_out();
			break;
		case R.id.register_tv_huoqu:
			// 弹出对话框提示验证码发送到手机号码， -------------有问题
			if (!"".equals(et_phone) || !"".equals(et_pass)
					|| !"".equals(et_repass)) {
			}

			break;
		case R.id.register_register:
			// 注册成功直接登录，将 该用户的的信息标记为已登录，finish掉注册页面，加上动画 -----判断checkBox是否勾上
			// Toast.makeText(RegisterActivity.this, "注册成功", 1000).show();
			if(cb_tongyi.isChecked()){

			}else{
				Toast.makeText(RegisterActivity.this, "请选择同意注册协议", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.register_xieyi:
			Intent intent = new Intent(RegisterActivity.this,WebViewActivity.class);
			intent.putExtra("title", "注册协议");
			intent.putExtra("url","http://www.baidu.com");
			startActivity(intent);
			
			break;
		default:
			break;
		}

	}

	// 修改登录状态
	private void reviseLoginState() {
		// CommonUtil.addConfigInfo(LoginActivity.this, "loginState", "login",
		// "",
		// "");
		// 将当前用户保存到文件中
		// UserMode user = Tool.getUser(RegisterActivity.this);
		Tool.writeData(RegisterActivity.this, "loginState", "zhanghu",
				allUser.getYhzh());
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
