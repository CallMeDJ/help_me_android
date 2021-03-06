package com.help.activity;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Intent;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.help.R;
import com.help.base.AppManager;
import com.help.base.BaseActivity;
import com.help.been.UserMode;
import com.help.util.ProcessDialogUtil;
import com.help.util.StaticVariable;
import com.help.util.Tool;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ESalesHttpClient;
import com.loopj.android.http.RequestParams;

public class LoginActivity extends BaseActivity implements OnClickListener {

	// 登录按钮
	private Button login = null;
	// 用户名
	private EditText et_name = null;
	// 密码
	private EditText et_pass = null;
	// 忘记密码
	private TextView tv_wang = null;
	private TextView tv_zhuce = null;
	
	//第三方登录
	private TextView tv_other = null;

	// usermode
	private UserMode allUser = null;

	private Dialog jindu = null;

	private String from = "";

	// 选择图片
	private Dialog dialog = null;
	
	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_login;
	}

	@Override
	protected void getIntentWord() {
		// TODO Auto-generated method stub
		super.getIntentWord();
		if (getIntent().getStringExtra("from") != null) {
			from = getIntent().getStringExtra("from");
		}

	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("登录");
		//showBack();
		showMenu();
		setMenu("注册");

		jindu = new ProcessDialogUtil(LoginActivity.this);

		login = (Button) findViewById(R.id.login_login);
		et_name = (EditText) findViewById(R.id.login_et_name);
		et_pass = (EditText) findViewById(R.id.login_et_pass);
		tv_wang = (TextView) findViewById(R.id.login_wangjimima);
		tv_zhuce = (TextView) findViewById(R.id.login_zhuce);
		tv_other = (TextView) findViewById(R.id.login_zhifubao);

	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarBack.setOnClickListener(this);
		titleBarMenu.setOnClickListener(this);
		login.setOnClickListener(this);
		tv_wang.setOnClickListener(this);
		tv_zhuce.setOnClickListener(this);
		tv_other.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.title_bar_back:
			// 返回键
			backTo();
			break;
		case R.id.title_bar_menu:
			// 注册键
			startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			anim_right_in();
			break;
		case R.id.login_login:
			// 登录键 调接口判断，返回成功，需要将 “该用户” 成功的标记存起来
			// if ("test".equals(et_name.getText().toString())) {
			// Intent intent3 = new Intent(this, MainActivity.class);
			// StaticVariable.put(StaticVariable.sv_toIndex, "1");
			// startActivity(intent3);
			// anim_right_in();
			// reviseLoginState();
			// finish();
			// } else {
			// Toast.makeText(LoginActivity.this, "用户名是test",
			// Toast.LENGTH_SHORT).show();
			// // Intent intent3 = new Intent(this, MainActivity.class);
			// // StaticVariable.put(StaticVariable.sv_toIndex, "1");
			// // startActivity(intent3);
			// // anim_right_in();
			// // finish();
			// }
			// 调接口验证登录
			loadHttp();
			break;
		case R.id.login_wangjimima:
			// 忘记密码
			startActivity(new Intent(LoginActivity.this,
					ZhaoHuiMiMaActivity.class));
			anim_right_in();
			break;
		case R.id.login_zhuce:
			// 忘记密码
			startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			anim_right_in();
			break;
		case R.id.login_zhifubao:
			// 忘记密码
			ShowDialog();
			break;
		default:
			break;
		}

	}

	// 修改登录状态
	private void reviseLoginState() {
		// 将当前用户保存到文件中
		
	}

	// 返回键事件
	private void backTo() {
		if ("setting".equals(from)) {
			AppManager.getAppManager().appExit(this);
			anim_right_out();
			finish();
		} else {
			LoginActivity.this.finish();
			anim_right_out();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			backTo();
		}
		return false;
	}

	private void loadHttp() {
		RequestParams params = new RequestParams();
		params.put("userPhone", et_name.getText().toString());
		params.put("userPassword", et_pass.getText().toString());

		ESalesHttpClient.requestGet(this, CommonAPI.API_LOGIN, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						jindu.show();
					}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						super.onFinish();
						jindu.dismiss();
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String content) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, headers, content);
						jindu.dismiss();
						try {
							JSONObject json = new JSONObject(content);
							String status = json.getString("status");
							String datastr = json.getString("data");
							JSONObject data = new JSONObject(datastr);
							if ("true".equals(status)) {
								Toast.makeText(LoginActivity.this, "登录成功",
										Toast.LENGTH_SHORT).show();
								Intent intent3 = new Intent(LoginActivity.this,
										MainActivity.class);
								StaticVariable.put(StaticVariable.sv_toIndex,
										"1");
								startActivity(intent3);
								Tool.writeData(LoginActivity.this, "user", "login", "ok");
								Tool.writeData(LoginActivity.this, "user", "userId", data.getString("user_id"));
								Tool.writeData(LoginActivity.this, "user", "phone", et_name.getText().toString());
								finish();
								anim_right_in();
							} else if ("false".equals(status)) {
								String errinfo = json.getString("info");
								Toast.makeText(LoginActivity.this, errinfo,
										Toast.LENGTH_SHORT).show();
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
						jindu.dismiss();
						Toast.makeText(LoginActivity.this, content, Toast.LENGTH_SHORT)
								.show();
					}

				});
	}

	private void ShowDialog() {
		dialog = new Dialog(this, R.style.dialogw);
		final View llContent = LayoutInflater.from(this).inflate(
				R.layout.dialog_bottom, null);

		ImageView tv1 = (ImageView) llContent.findViewById(R.id.dialog_title1);
		ImageView tv2 = (ImageView) llContent.findViewById(R.id.dialog_title2);
		ImageView tv3 = (ImageView) llContent.findViewById(R.id.dialog_title3);

		dialog.setContentView(llContent);

		tv1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 微信
				dialog.cancel();
			}
		});

		tv2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 支付宝
				dialog.cancel();
			}
		});

		tv3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//天下通
				dialog.cancel();
			}
		});

		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		WindowManager wm = dialogWindow.getWindowManager();
		Display d = wm.getDefaultDisplay();
		lp.width = d.getWidth();
		lp.x = 0;
		lp.y = 0;
		dialogWindow.setAttributes(lp);
		dialogWindow.setWindowAnimations(R.style.AnimBottom);
		if (!dialog.isShowing()) {
			dialog.show();
		}
	}
	
}
