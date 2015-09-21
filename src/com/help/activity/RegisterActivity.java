package com.help.activity;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

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
import com.help.util.StaticVariable;
import com.help.util.Tool;
import com.help.util.WebViewActivity;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ESalesHttpClient;
import com.loopj.android.http.RequestParams;

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

	private TextView reback = null;
	
	
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
		reback = (TextView) findViewById(R.id.login_zhuce_back);


	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarBack.setOnClickListener(this);
		huoqu.setOnClickListener(this);
		register.setOnClickListener(this);
		reback.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.login_zhuce_back:
			RegisterActivity.this.finish();
			anim_right_out();
			break;
		case R.id.register_tv_huoqu:
			// 弹出对话框提示验证码发送到手机号码， -------------有问题
			if (!"".equals(et_phone.getText().toString()) || !"".equals(et_pass.getText().toString())
					|| !"".equals(et_repass.getText().toString())) {
				startTimer();
			}

			break;
		case R.id.register_register:
			// 注册成功直接登录，将 该用户的的信息标记为已登录，finish掉注册页面，加上动画 -----判断checkBox是否勾上
			// Toast.makeText(RegisterActivity.this, "注册成功", 1000).show();
			if (!"".equals(et_phone.getText().toString()) || !"".equals(et_pass.getText().toString())
					|| !"".equals(et_repass.getText().toString())) {
				loadHttp();
			}
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

	private void loadHttp() {
		RequestParams params = new RequestParams();
		params.put("userPhone", et_phone.getText().toString());
		params.put("userPassword", et_pass.getText().toString());
		params.put("userIdentity", et_tuijian.getText().toString());//身份整好
		params.put("verification", et_yanzhengma.getText().toString());//验证码

		ESalesHttpClient.requestGet(this, CommonAPI.API_REGISTER, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						juhua.show();
					}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						super.onFinish();
						juhua.dismiss();
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String content) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, headers, content);
						juhua.dismiss();
						try {
							JSONObject json = new JSONObject(content);
							String status = json.getString("status");
							String datastr = json.getString("data");
							JSONObject data = new JSONObject(datastr);
							if ("true".equals(status)) {
								Toast.makeText(RegisterActivity.this, "注册成功",
										1000).show();
								Intent intent3 = new Intent(RegisterActivity.this,
										MainActivity.class);
								StaticVariable.put(StaticVariable.sv_toIndex,
										"1");
								startActivity(intent3);
								Tool.writeData(RegisterActivity.this, "user", "login", "ok");
								Tool.writeData(RegisterActivity.this, "user", "userId", data.getString("user_id"));
								Tool.writeData(RegisterActivity.this, "user", "phone", et_phone.getText().toString());
								finish();
								anim_right_in();
							} else if ("false".equals(status)) {
								String errinfo = json.getString("info");
								Toast.makeText(RegisterActivity.this, errinfo,
										1000).show();
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(Throwable error, String content) {
						// TODO Auto-generated method stub
						super.onFailure(error, content);
						juhua.dismiss();
						Toast.makeText(RegisterActivity.this, content, 1000)
								.show();
					}

				});
	}

	
}
