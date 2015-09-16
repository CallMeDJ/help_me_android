package com.help.activity.index;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.help.activity.CommonAPI;
import com.help.util.Tool;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ESalesHttpClient;
import com.loopj.android.http.RequestParams;
import com.sun.xml.internal.ws.resources.HandlerMessages;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * Android Service 示例
 * 
 * @author dev
 * 
 */
public class ServiceDemo extends Service {
	private static final String TAG = "ServiceDemo";
	public static final String ACTION = "com.lql.service.ServiceDemo";

	@Override
	public IBinder onBind(Intent intent) {
		Log.v(TAG, "ServiceDemo onBind");
		return null;
	}

	private boolean isfinish = false;

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			if (msg.what == 0) {
				toRequest1();
			}

		}
	};

	@Override
	public void onCreate() {
		Log.v(TAG, "ServiceDemo onCreate");
		super.onCreate();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						Thread.sleep(5 * 1000);
						Message msg = new Message();
						msg.what = 0;
						handler.sendMessage(msg);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.v(TAG, "ServiceDemo onStart");
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v(TAG, "ServiceDemo onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	private String str1 = "";

	public void toRequest1() {
		RequestParams params = new RequestParams();
		params.put("userId", Tool.readData(ServiceDemo.this, "user", "userId"));// 用户id

		ESalesHttpClient.requestGet(this, CommonAPI.API_INFO, params,
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

					@SuppressLint("SimpleDateFormat")
					@Override
					public void onSuccess(int statusCode, String content) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, content);
						// Toast.makeText(ServiceDemo.this," ddd", 2000).show();
						// juhua.cancel();
						try {
							JSONObject json = new JSONObject(content);
							String status = json.getString("status");
							if ("true".equals(status)) {
								JSONObject data = json.getJSONObject("data");

								String orderstatus = data
										.getString("order_status");
								if (!str1.equals(orderstatus)) {
									Toast.makeText(ServiceDemo.this,
											orderstatus, 2000).show();
									if(orderstatus.indexOf("订单已被接受")!=-1){
										Intent intent = new Intent(ServiceDemo.this,
												JieShouRenWuActivity.class);
										intent.putExtra("show", "fabu");
										intent.putExtra("orderId",data.getString("order_id") );
										intent.putExtra("time",data.getString("order_limittime"));
										intent.putExtra("name",
												data.getString("user_nickname"));
										intent.putExtra("phone",
												data.getString("user_phone"));
										intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
										intent.putExtra("weizhi", "");
										
										startActivity(intent);
									}
								} else {
									str1 = orderstatus;
								}
								

							} else if ("false".equals(status)) {
								String errinfo = json.getString("info");
								// Toast.makeText(ServiceDemo.this, errinfo,
								// 1000).show();
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});
	}

}
