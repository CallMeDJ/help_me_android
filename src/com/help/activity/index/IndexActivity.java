package com.help.activity.index;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager.Request;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.example.help.R;
import com.help.activity.CommonAPI;
import com.help.activity.edit.EditActivity;
import com.help.base.BaseActivity;
import com.help.listview.XListView;
import com.help.listview.XListView.IXListViewListener;
import com.help.util.ProcessDialogUtil;
import com.help.util.Tool;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ESalesHttpClient;
import com.loopj.android.http.RequestParams;

public class IndexActivity extends BaseActivity implements OnClickListener,
		IXListViewListener {

	private EditText edit = null;
	private TextView map = null;
	// private TextView ser = null;

	// private ArrayList<Map<String, String>> list = null;

	MyAdapter adapter = null;
	
	// 下拉刷新
	private XListView mListView = null;
	// 记录当前刷新页
	private int curPage = 0;
	// 正在加载对话框
	private ProcessDialogUtil juhua = null;

	// 确认对话框
	private Dialog dialog = null;

	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.main_index;
	}

	@Override
	protected void getIntentWord() {
		// TODO Auto-generated method stub
		super.getIntentWord();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("主页");
		/*
		 * showMenu(); titleBarMenu.setText("+"); titleBarMenu.setTextSize(18);
		 */

		startService(new Intent(ServiceDemo.ACTION));  
		
		getLocation();

		// list = new ArrayList<Map<String, String>>();
		// Map<String, String> map1 = new HashMap<String, String>();
		// map1.put("time", "2015-08-10");
		// map1.put("content", "求取快递，，，，，，");
		// map1.put("dizhi", "平安大厦四楼");
		// map1.put("juli", "10000m");
		// map1.put("qian", "88.88");
		// list.add(map1);
		// Map<String, String> map2 = new HashMap<String, String>();
		// map2.put("time", "2015-08-10");
		// map2.put("content", "谁能送我一本JavaScript书，，，，，，");
		// map2.put("dizhi", "平安大厦四楼");
		// map2.put("juli", "10m");
		// map2.put("qian", "999.00");
		// list.add(map2);
		// Map<String, String> map3 = new HashMap<String, String>();
		// map3.put("time", "2015-08-10");
		// map3.put("content", "谁帮我送盒饭，，，，，，");
		// map3.put("dizhi", "八卦岭");
		// map3.put("juli", "100m");
		// map3.put("qian", "10.00");
		// list.add(map3);
		// Map<String, String> map4 = new HashMap<String, String>();
		// map4.put("time", "2015-08-10");
		// map4.put("content", "来来来求JavaScript书，，，，，，");
		// map4.put("dizhi", "平安大厦一楼");
		// map4.put("juli", "10m");
		// map4.put("qian", "10000.00");
		// list.add(map4);
		// Map<String, String> map5 = new HashMap<String, String>();
		// map5.put("time", "2015-08-10");
		// map5.put("content", "来来来求JavaScript书，，，，，，");
		// map5.put("dizhi", "平安大厦一楼");
		// map5.put("juli", "10m");
		// map5.put("qian", "10000.00");
		// list.add(map5);
		//
		// Map<String, String> map6 = new HashMap<String, String>();
		// map6.put("time", "2015-08-10");
		// map6.put("content", "来来来求JavaScript书，，，，，，");
		// map6.put("dizhi", "平安大厦一楼");
		// map6.put("juli", "10m");
		// map6.put("qian", "10000.00");
		// list.add(map6);
		//
		// Map<String, String> map7 = new HashMap<String, String>();
		// map7.put("time", "2015-08-10");
		// map7.put("content", "来来来求JavaScript书，，，，，，");
		// map7.put("dizhi", "平安大厦一楼");
		// map7.put("juli", "10m");
		// map7.put("qian", "10000.00");
		// list.add(map7);
		//
		// Map<String, String> map8 = new HashMap<String, String>();
		// map8.put("time", "2015-08-10");
		// map8.put("content", "来来来求JavaScript书，，，，，，");
		// map8.put("dizhi", "平安大厦一楼");
		// map8.put("juli", "10m");
		// map8.put("qian", "10000.00");
		// list.add(map8);

		edit = (EditText) findViewById(R.id.index_edit);
		map = (TextView) findViewById(R.id.index_map);
		// ser = (TextView) findViewById(R.id.index_ser);

		mListView = (XListView) findViewById(R.id.index_list);
		mListView.setPullLoadEnable(false);
		mListView.setPullRefreshEnable(true);

		// SimpleAdapter adapter = new SimpleAdapter(this, list,
		// R.layout.index_list_item, new String[] { "time", "content",
		// "dizhi", "juli", "qian" }, new int[] {
		// R.id.index_item_time, R.id.index_item_content,
		// R.id.index_item_dizhi, R.id.index_item_juli,
		// R.id.index_item_qian });
		adapter = new MyAdapter();
		mListView.setAdapter(adapter);

		mListView.setXListViewListener(this);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("ShowToast")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				// TODO Auto-generated method stub
				if (position != 0) {
					// if (position == 1) {
					getTask(position);
					// } else {
					// Intent intent = new Intent(IndexActivity.this,
					// JieShouRenWuActivity.class);
					// intent.putExtra("show", "fabu");
					// startActivity(intent);
					// anim_right_in();
					// }

				} else {
					return;
				}
			}
		});

		// toRequest(curPage, "0");

	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarMenu.setOnClickListener(this);
		map.setOnClickListener(this);
		// ser.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.title_bar_menu:

			break;
		case R.id.index_ser:
			// toRequest(0,"1");
			break;
		case R.id.index_map:
			Intent intent = new Intent(IndexActivity.this, MapActivity.class);
			intent.putExtra("taskList", mTaskList);

			startActivity(intent);
			anim_right_in();
			break;
		default:
			break;
		}
	}

	private void onStopLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime(new Date().toLocaleString());
	}

	// listView下拉刷新，上拉加载
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		curPage = 0;
		getLocation();
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		curPage++;
		getLocation();
	}

	private ArrayList mTaskList = new ArrayList();

	public void toRequest(int page, final String shuaxin) {
		RequestParams params = new RequestParams();
		// params.put("userId", Tool.readData(IndexActivity.this, "user",
		// "userId"));// 用户id
		params.put("latitude", jingdu + "");// 经度
		params.put("longitude", weidu + "");// 纬度
		params.put("pageNum", page + "");// 页数

		ESalesHttpClient.requestGet(this, CommonAPI.API_BYLOCATION, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
					}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						super.onFinish();
						onStopLoad();
					}

					@Override
					public void onFailure(Throwable error, String content) {
						// TODO Auto-generated method stub
						super.onFailure(error, content);
						onStopLoad();
					}

					@Override
					public void onSuccess(int statusCode, String content) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, content);
						onStopLoad();
						if ("1".equals(shuaxin)) {
							mTaskList.removeAll(mTaskList);
						}
						try {
							JSONObject json = new JSONObject(content);
							String status = json.getString("status");
							if ("true".equals(status)) {
								JSONArray jsonArr = json.getJSONArray("data");
								for (int i = 0; i < jsonArr.length(); i++) {
									JSONObject jsonObj = jsonArr
											.getJSONObject(i);
									TaskObject taskObj = new TaskObject(
											jsonObj.getString("order_id"),
											jsonObj.getString("order_submittime"),
											jsonObj.getString("order_content"),
											jsonObj.getString("order_location"),
											jsonObj.getString("dist"),
											jsonObj.getString("order_reward"),
											Double.valueOf(jsonObj
													.getString("order_location_latitude")),
											Double.valueOf(jsonObj
													.getString("order_location_longitude")));
									taskObj.setMbmpresid(R.drawable.meinv2);
									taskObj.setMlittlebmpresid(R.drawable.money);
									mTaskList.add(taskObj);
								}
								adapter.notifyDataSetChanged();

							} else if ("false".equals(status)) {
								String errinfo = json.getString("info");
								Toast.makeText(IndexActivity.this, errinfo,
										1000).show();
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});
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

	/** 获取任务对话框 */
	private void getTask(final int position) {

		View view = LayoutInflater.from(IndexActivity.this).inflate(
				R.layout.dialog_all, null);

		Button tv_cancel = (Button) view.findViewById(R.id.dialog_cancel);
		Button tv_submit = (Button) view.findViewById(R.id.dialog_submit);
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
				toRequest1(position);
			}
		});

		dialog = new AlertDialog.Builder(IndexActivity.this).setView(view)
				.create();
		dialog.show();
	}

	// 定位监听器
	private BDLocationListener myListener = null;
	private LocationClient mLocationcClient = null;
	// 地理位置描述
	private String loc = "";

	// 定位
	private void getLocation() {
		myListener = new MyLocationListener();
		mLocationcClient = new LocationClient(getApplicationContext());
		mLocationcClient.registerLocationListener(myListener);
		LocationClientOption locationOption = new LocationClientOption();
		locationOption.setOpenGps(true);
		locationOption.setLocationMode(LocationMode.Hight_Accuracy);// 设置高精度定位定位模式
		locationOption.setCoorType("bd09ll");
		locationOption.setIsNeedAddress(true);// 反编译获得具体位置，只有网络定位才可以
		locationOption.setAddrType("all");
		locationOption.setProdName("BaiduLocation");
		locationOption.setScanSpan(1000 * 60 * 5);
		mLocationcClient.setLocOption(locationOption);
		mLocationcClient.start();

	}

	// private double jingdu = 114.105849;
	// private double weidu = 22.565676;

	private double jingdu = 1.0;
	private double weidu = 1.0;

	private class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub
			loc = location.getAddrStr();
			jingdu = location.getLatitude();
			weidu = location.getLongitude();
			// Toast.makeText(LieBiaoActivity.this, loc, 1000).show();
			mLocationcClient.stop();
			// 先定位 ，获取金纬度之后再掉接口
			toRequest(curPage, "1");

		}
	}

	/** 适配器 **/
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mTaskList != null ? mTaskList.size() : 0;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		ViewHolder holder;

		@SuppressLint("ResourceAsColor")
		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {

			holder = new ViewHolder();

			if (convertView == null) {
				convertView = LayoutInflater.from(IndexActivity.this).inflate(
						R.layout.index_list_item, null);

				holder.tv1 = (TextView) convertView
						.findViewById(R.id.index_item_time);

				holder.tv2 = (TextView) convertView
						.findViewById(R.id.index_item_content);
				holder.tv3 = (TextView) convertView
						.findViewById(R.id.index_item_dizhi);

				holder.tv4 = (TextView) convertView
						.findViewById(R.id.index_item_juli);
				holder.tv5 = (TextView) convertView
						.findViewById(R.id.index_item_qian);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			TaskObject obj = (TaskObject) mTaskList.get(position);

			holder.tv1.setText(obj.getMmasterPerson());
			holder.tv2.setText(obj.getMtaskContext());
			holder.tv3.setText(obj.getMaddress());
			holder.tv4.setText(obj.getMlength());
			holder.tv5.setText(obj.getMtaskReward());

			return convertView;
		}

		final class ViewHolder {
			TextView tv1;
			TextView tv2;
			TextView tv3;
			TextView tv4;
			TextView tv5;
		}
	}

	// 倒计时
	private String time = "600000";

	public void toRequest1(final int position) {
		TaskObject taskObj = (TaskObject) mTaskList.get(position - 1);
		final String Id = taskObj.getOrderId();
		RequestParams params = new RequestParams();
		params.put("userId", Tool.readData(IndexActivity.this, "user", "userId"));// 用户id
		params.put("orderId", Id);// 任务id

		ESalesHttpClient.requestGet(this, CommonAPI.API_ACCEPT, params,
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
						// juhua.cancel();
						try {
							JSONObject json = new JSONObject(content);
							String status = json.getString("status");
							if ("true".equals(status)) {
								JSONArray dataArr = json.getJSONArray("data");
								JSONObject data = (JSONObject) dataArr.get(0);
								time = (data.getString("order_limittime") != "null") ? data
										.getString("order_limittime")
										: "600000";
								// System.out.println(time);
								// try {
								// SimpleDateFormat df = new
								// SimpleDateFormat("HH:mm:ss");
								// Date date = df.parse(time);
								// lastTime = 60*1000;
								//
								// System.out.println(lastTime);
								// mc = new MyCountDownTimer(lastTime
								// - System.currentTimeMillis(), 1000);
								// mc.start();
								// } catch (ParseException e) {
								// // TODO Auto-generated catch block
								// e.printStackTrace();
								// }

								Intent intent = new Intent(IndexActivity.this,
										JieShouRenWuActivity.class);
								intent.putExtra("show", "jieshou");
								intent.putExtra("orderId", Id);
								intent.putExtra("time", time);
								intent.putExtra("name",
										data.getString("user_nickname"));
								intent.putExtra("phone",
										data.getString("user_phone"));
								intent.putExtra("weizhi",
										data.getString("order_location"));
								startActivity(intent);
								anim_right_in();

							} else if ("false".equals(status)) {
								String errinfo = json.getString("info");
								Toast.makeText(IndexActivity.this, errinfo,
										1000).show();
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});
	}

}
