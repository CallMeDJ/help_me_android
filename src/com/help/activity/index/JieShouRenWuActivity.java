package com.help.activity.index;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.example.help.R;
import com.help.activity.CommonAPI;
import com.help.base.BaseActivity;
import com.help.util.ProcessDialogUtil;
import com.help.util.TimeUtil;
import com.help.util.Tool;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ESalesHttpClient;
import com.loopj.android.http.RequestParams;

public class JieShouRenWuActivity extends BaseActivity implements
		OnClickListener {

	// 倒计时
	private TextView tv1 = null;
	// 位置
	private TextView tv2 = null;
	private LinearLayout layout = null;
	// 姓名
	private TextView tv3 = null;
	// 手机号
	private TextView tv4 = null;
	// 取消任务
	private Button cancel = null;
	// 确认完成
	private Button submit = null;
	//发送消息
	private Button sendmsg = null;

	private Dialog dialog = null;
	// private Dialog juhua = null;

	// 是否显示地理位置
	private String showadd = "";

	// 剩余时间
	private long lastTime;

	// 任务id
	private String orderId;

	// 剩余时间
	private String shengyutime;
	private String name;
	private String phone;
	private String weizhi;

	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.index_jieshourenwu;
	}

	@Override
	protected void getIntentWord() {
		// TODO Auto-generated method stub
		super.getIntentWord();
		if (getIntent().getStringExtra("show") != null) {
			showadd = getIntent().getStringExtra("show");
			orderId = getIntent().getStringExtra("orderId");
			shengyutime = getIntent().getStringExtra("time");
			name = getIntent().getStringExtra("name");
			phone = getIntent().getStringExtra("phone");
			weizhi = getIntent().getStringExtra("weizhi");
		}

	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("接受任务");
		//showBack();

		// juhua = new ProcessDialogUtil(JieShouRenWuActivity.this);

		tv1 = (TextView) findViewById(R.id.jieshou_tv1);
		layout = (LinearLayout) findViewById(R.id.showweizhi);
		if ("fabu".equals(showadd)) {
			layout.setVisibility(View.GONE);
		} else {
			layout.setVisibility(View.VISIBLE);
		}
		tv2 = (TextView) findViewById(R.id.jieshou_tv2);
		tv3 = (TextView) findViewById(R.id.jieshou_tv3);
		tv4 = (TextView) findViewById(R.id.jieshou_tv4);

		cancel = (Button) findViewById(R.id.jieshou_cancel);
		submit = (Button) findViewById(R.id.jieshou_submit);
		sendmsg = (Button) findViewById(R.id.jieshou_info);

		// toRequest();

		tv2.setText(weizhi);
		tv3.setText(name);
		tv4.setText(phone);

		// 启动倒计时
		lastTime = Integer.valueOf(shengyutime);
		System.out.println(lastTime);
		//lastTime = System.currentTimeMillis() + (3500 * 1000);
		mc = new MyCountDownTimer(lastTime, 1000);
		mc.start();
	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarBack.setOnClickListener(this);
		cancel.setOnClickListener(this);
		submit.setOnClickListener(this);
		
		tv4.setOnClickListener(this);
		sendmsg.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.title_bar_back:
			// 返回按钮
			JieShouRenWuActivity.this.finish();
			anim_right_out();
			break;
		case R.id.jieshou_cancel:
			// 按钮
			getTask();
			break;
		case R.id.jieshou_submit:
			// 按钮
			getTask1();
			break;
		case R.id.jieshou_info:
			// 按钮
			break;
		case R.id.jieshou_tv4:
			// 
			Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
					+ tv4.getText().toString()));
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	/** 获取任务对话框 */
	private void getTask() {

		View view = LayoutInflater.from(JieShouRenWuActivity.this).inflate(
				R.layout.dialog_all, null);

		TextView title = (TextView) view.findViewById(R.id.dialog_title);
		Button tv_cancel = (Button) view.findViewById(R.id.dialog_cancel);
		Button tv_submit = (Button) view.findViewById(R.id.dialog_submit);

		title.setText("取消后将扣除佣金的20%作为违约金，确定取消此任务吗？");

		tv_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		tv_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if ("fabu".equals(showadd)) {
					cancel_fabu();
				} else {
					cancel_jieshou();
				}
				dialog.dismiss();
				JieShouRenWuActivity.this.finish();
			}
		});

		dialog = new AlertDialog.Builder(JieShouRenWuActivity.this).setView(
				view).create();
		dialog.show();
	}

	private void getTask1() {

		View view = LayoutInflater.from(JieShouRenWuActivity.this).inflate(
				R.layout.dialog_all, null);

		TextView title = (TextView) view.findViewById(R.id.dialog_title);
		Button tv_cancel = (Button) view.findViewById(R.id.dialog_cancel);
		Button tv_submit = (Button) view.findViewById(R.id.dialog_submit);

		title.setText("是否确认完成任务？");

		tv_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		tv_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				dialog.dismiss();
				if ("fabu".equals(showadd)) {
					complete();
				} else {
					//complete();
					JieShouRenWuActivity.this.finish();
				}
			}
		});

		dialog = new AlertDialog.Builder(JieShouRenWuActivity.this).setView(
				view).create();
		dialog.show();
	}

	private void pingjia() {

		View view = LayoutInflater.from(JieShouRenWuActivity.this).inflate(
				R.layout.index_pingjia, null);

		TextView tv1 = (TextView) view.findViewById(R.id.pingjia_tv1);
		final TextView tv2 = (TextView) view.findViewById(R.id.pingjia_tv2);
		final RatingBar rb = (RatingBar) view.findViewById(R.id.ratingBar1);
		rb.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

			@Override
			public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
				// TODO Auto-generated method stub
				if (rb.getRating() >= 4) {
					tv2.setText("好评");
				} else if (rb.getRating() == 3) {
					tv2.setText("中评");
				} else {
					tv2.setText("差评");
				}

			}
		});

		Button tv_submit = (Button) view.findViewById(R.id.pingjia_submit);

		tv_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				evaluate(rb.getRating() + "");
				dialog.dismiss();
				JieShouRenWuActivity.this.finish();
			}
		});

		dialog = new AlertDialog.Builder(JieShouRenWuActivity.this).setView(
				view).create();
		dialog.show();
	}

	// 倒计时
	private MyCountDownTimer mc;

	class MyCountDownTimer extends CountDownTimer {

		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			mc.cancel();
		}

		@Override
		public void onTick(long arg0) {
			// TODO Auto-generated method stub
			tv1.setText(strTime(arg0));
		}

	}

	public String strTime(long date) {
		long time = date;
		time /= 1000;
		long day = time / (24 * 3600);
		time = time % (24 * 3600);
		long hour = time / 3600;
		time = time % 3600;
		long min = time / 60;
		time = time % 60;
		long sec = time;
		return hour + ":" + min + ":" + sec;
	}

	// 网络请求-获取任务详情
	// public void toRequest() {
	// RequestParams params = new RequestParams();
	// params.put("userId",
	// Tool.readData(JieShouRenWuActivity.this, "user", "userId"));// 用户id
	// params.put("orderId", orderId);// 任务id
	//
	// ESalesHttpClient.requestGet(this, CommonAPI.API_ACCEPT, params,
	// new AsyncHttpResponseHandler() {
	// @Override
	// public void onStart() {
	// // TODO Auto-generated method stub
	// super.onStart();
	// //juhua.show();
	// }
	//
	// @Override
	// public void onFinish() {
	// // TODO Auto-generated method stub
	// super.onFinish();
	// //juhua.cancel();
	// }
	//
	// @Override
	// public void onFailure(Throwable error, String content) {
	// // TODO Auto-generated method stub
	// super.onFailure(error, content);
	// //juhua.cancel();
	// }
	//
	// @SuppressLint("SimpleDateFormat")
	// @Override
	// public void onSuccess(int statusCode, String content) {
	// // TODO Auto-generated method stub
	// super.onSuccess(statusCode, content);
	// //juhua.cancel();
	// try {
	// JSONObject json = new JSONObject(content);
	// String status = json.getString("status");
	// if ("true".equals(status)) {
	// JSONArray dataArr = json.getJSONArray("data");
	// JSONObject data = (JSONObject) dataArr.get(0);
	// // time = data.getString("order_limittime");
	// // System.out.println(time);
	// // try {
	// // SimpleDateFormat df = new
	// // SimpleDateFormat("HH:mm:ss");
	// // Date date = df.parse(time);
	// // lastTime = 60*1000;
	// //
	// // System.out.println(lastTime);
	// // mc = new MyCountDownTimer(lastTime
	// // - System.currentTimeMillis(), 1000);
	// // mc.start();
	// // } catch (ParseException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	//
	// tv3.setText(data.getString("user_nickname"));
	// tv4.setText(data.getString("user_phone"));
	//
	// } else if ("false".equals(status)) {
	// String errinfo = json.getString("info");
	// Toast.makeText(JieShouRenWuActivity.this,
	// errinfo, 1000).show();
	// }
	//
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	//
	// });
	// }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Toast.makeText(JieShouRenWuActivity.this, "任务进行中，请不要退出", 1000).show();
			return false;
		}
		return false;
	}
	
	
	// 网络请求-完成任务
	public void complete() {
		RequestParams params = new RequestParams();
		params.put("userId",
				Tool.readData(JieShouRenWuActivity.this, "user", "userId"));// 用户id
		params.put("orderId", orderId);// 任务id

		ESalesHttpClient.requestGet(this, CommonAPI.API_COMPLETE, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						// juhua.show();
					}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						super.onFinish();
						// juhua.cancel();
					}

					@Override
					public void onFailure(Throwable error, String content) {
						// TODO Auto-generated method stub
						super.onFailure(error, content);
						// juhua.cancel();
					}

					@Override
					public void onSuccess(int statusCode, String content) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, content);
						// juhua.cancel();
						try {
							JSONObject json = new JSONObject(content);
							String status = json.getString("status");
							if ("true".equals(status)) {
								Toast.makeText(JieShouRenWuActivity.this,
										"任务已完成", 1000).show();
								pingjia();
							} else if ("false".equals(status)) {
								String errinfo = json.getString("info");
								Toast.makeText(JieShouRenWuActivity.this,
										errinfo, 1000).show();
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});
	}

	// 网络请求-发布方取消
	public void cancel_fabu() {
		RequestParams params = new RequestParams();
		params.put("userId",
				Tool.readData(JieShouRenWuActivity.this, "user", "userId"));// 用户id
		params.put("orderId", orderId);// 任务id

		ESalesHttpClient.requestGet(this, CommonAPI.API_CANCEL_FABU, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						// juhua.show();
					}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						super.onFinish();
						// juhua.cancel();
					}

					@Override
					public void onFailure(Throwable error, String content) {
						// TODO Auto-generated method stub
						super.onFailure(error, content);
						// juhua.cancel();
					}

					@Override
					public void onSuccess(int statusCode, String content) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, content);
						// juhua.cancel();
						try {
							JSONObject json = new JSONObject(content);
							String status = json.getString("status");
							if ("true".equals(status)) {
								Toast.makeText(JieShouRenWuActivity.this,
										"任务已取消", 1000).show();
							} else if ("false".equals(status)) {
								String errinfo = json.getString("info");
								Toast.makeText(JieShouRenWuActivity.this,
										errinfo, 1000).show();
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});
	}

	// 网络请求-接受方取消
	public void cancel_jieshou() {
		RequestParams params = new RequestParams();
		params.put("userId",
				Tool.readData(JieShouRenWuActivity.this, "user", "userId"));// 用户id
		params.put("orderId", orderId);// 任务id

		ESalesHttpClient.requestGet(this, CommonAPI.API_CANCEL_JIESHOU, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						// juhua.show();
					}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						super.onFinish();
						// juhua.cancel();
					}

					@Override
					public void onFailure(Throwable error, String content) {
						// TODO Auto-generated method stub
						super.onFailure(error, content);
						// juhua.cancel();
					}

					@Override
					public void onSuccess(int statusCode, String content) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, content);
						// juhua.cancel();
						try {
							JSONObject json = new JSONObject(content);
							String status = json.getString("status");
							if ("true".equals(status)) {
								Toast.makeText(JieShouRenWuActivity.this,
										"任务已取消", 1000).show();
							} else if ("false".equals(status)) {
								String errinfo = json.getString("info");
								Toast.makeText(JieShouRenWuActivity.this,
										errinfo, 1000).show();
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});
	}

	// 网络请求-评价
	public void evaluate(String str) {
		RequestParams params = new RequestParams();
		params.put("userId",
				Tool.readData(JieShouRenWuActivity.this, "user", "userId"));// 用户id
		params.put("orderId", orderId);// 任务id
		params.put("evaluateCode", str);// 任务id

		ESalesHttpClient.requestGet(this, CommonAPI.API_EVALUATE, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						// juhua.show();
					}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						super.onFinish();
						// juhua.cancel();
					}

					@Override
					public void onFailure(Throwable error, String content) {
						// TODO Auto-generated method stub
						super.onFailure(error, content);
						// juhua.cancel();
					}

					@Override
					public void onSuccess(int statusCode, String content) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, content);
						// juhua.cancel();
						try {
							JSONObject json = new JSONObject(content);
							String status = json.getString("status");
							if ("true".equals(status)) {
								Toast.makeText(JieShouRenWuActivity.this,
										"任务已取消", 1000).show();
							} else if ("false".equals(status)) {
								String errinfo = json.getString("info");
								Toast.makeText(JieShouRenWuActivity.this,
										errinfo, 1000).show();
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});
	}

}
