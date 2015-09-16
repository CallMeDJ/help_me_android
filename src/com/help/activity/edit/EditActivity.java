package com.help.activity.edit;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.DataSetObserver;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
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
import com.help.activity.index.JieShouRenWuActivity;
import com.help.base.BaseActivity;
import com.help.util.ProcessDialogUtil;
import com.help.util.Tool;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ESalesHttpClient;
import com.loopj.android.http.RequestParams;

public class EditActivity extends BaseActivity implements OnClickListener {

	private EditText xinxi = null;
	private TextView weizhi = null;
	private EditText shuruweizhi = null;

	private TextView jian = null;
	private EditText shuru = null;
	private TextView jia = null;
	private TextView time = null;
	private TextView time1 = null;

	private TextView quxiao, queren;

	private double fenshu = 1.0;

	private Dialog juhua = null;

	private double  jingdu = 1.0;
	private double  weidu = 1.0;
	
	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.main_edit;
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
		setTitle("发布信息");
		juhua = new ProcessDialogUtil(EditActivity.this);

		xinxi = (EditText) findViewById(R.id.main_edit_xinxi);
		weizhi = (TextView) findViewById(R.id.main_edit_weizhi);
		shuruweizhi = (EditText) findViewById(R.id.main_edit_shuruweizhi);

		jian = (TextView) findViewById(R.id.main_edit_jian);
		shuru = (EditText) findViewById(R.id.main_edit_shuru);
		jia = (TextView) findViewById(R.id.main_edit_jia);
		time = (TextView) findViewById(R.id.main_edit_shijian);
		time1 = (TextView) findViewById(R.id.main_edit_shijiantv);

		quxiao = (TextView) findViewById(R.id.main_edit_quxiao);
		queren = (TextView) findViewById(R.id.main_edit_queren);
		
		getLocation();
		
	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarBack.setOnClickListener(this);

		weizhi.setOnClickListener(this);
		jian.setOnClickListener(this);
		jia.setOnClickListener(this);
		quxiao.setOnClickListener(this);
		queren.setOnClickListener(this);
		time.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.title_bar_back:
			EditActivity.this.finish();
			anim_right_out();
			break;
		case R.id.main_edit_jian:
			jian();
			break;
		case R.id.main_edit_jia:
			jia();
			break;
		case R.id.main_edit_shijian:
			showTime();
			break;
		case R.id.main_edit_weizhi:
			getLocation();
			break;
		case R.id.main_edit_queren:
			if("".equals(xinxi.getText().toString())){
				Toast.makeText(this, "内容不能为空", 1000).show();
				return;
			}else if("".equals(time1.getText().toString())){
				Toast.makeText(this, "限定时间不能为空", 1000).show();
				return;
			}else{
				loadHttp();
			}
		break;

		default:
			break;
		}
	}

	// 减
	private void jian() {
		fenshu = Double.valueOf(shuru.getText().toString());
		if (fenshu <= 1) {
			Toast.makeText(EditActivity.this, "至少1元", 1000).show();
		} else {
			fenshu = fenshu - 1;
			shuru.setText(fenshu + "");
		}
	}

	// 加
	private void jia() {
		fenshu = Double.valueOf(shuru.getText().toString());
		fenshu = fenshu + 1;
		shuru.setText(fenshu + "");
	}

	private Dialog dialog = null;

	// 选择时间
	private void showTime() {

		View view = LayoutInflater.from(EditActivity.this).inflate(
				R.layout.xianshishijian, null);

		ListView lv = (ListView) view.findViewById(R.id.xianshilv);
		final String[] strs = { "10分钟", "20分钟", "30分钟",
				 "1小时","2小时","6小时"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.xianshi_item, R.id.xianshi_tv, strs);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				time1.setVisibility(View.VISIBLE);
				time1.setText(strs[position]);
				dialog.dismiss();
			}
		});

		dialog = new AlertDialog.Builder(EditActivity.this).setView(view)
				.create();
		dialog.show();
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

	private void loadHttp() {
		RequestParams params = new RequestParams();
		params.put("userId",Tool.readData(EditActivity.this, "user", "userId") );//用户id
		params.put("location",loc+shuruweizhi.getText().toString() );//位置信息edit
		params.put("latitude",jingdu+"" );//经度
		params.put("longitude",weidu+"");//纬度
		params.put("content",xinxi.getText().toString());//内容
		params.put("limitTime", ""+strTimeToLong(time1.getText().toString()));//时间
		params.put("reward",shuru.getText().toString() );//奖励

//		try {
//			params.put("location",URLEncoder.encode(shuruweizhi.getText().toString(), "utf-8") );
//			params.put("content",URLEncoder.encode(xinxi.getText().toString(), "utf-8"));//内容
//			params.put("limitTime",URLEncoder.encode( time1.getText().toString(), "utf-8"));//时间
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		ESalesHttpClient.requestGet(this, CommonAPI.API_PUBLICORDER, params,
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
							if ("true".equals(status)) {
								Toast.makeText(EditActivity.this, "发布成功", 1000).show();
								xinxi.setText("");
								shuruweizhi.setText("");
								shuru.setText("1.0");
								time1.setText("");
								time1.setVisibility(View.GONE);
							} else if ("false".equals(status)) {
								String errinfo = json.getString("info");
								Toast.makeText(EditActivity.this, errinfo, 1000)
										.show();
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
						Toast.makeText(EditActivity.this, content, 1000).show();
					}

				});
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
			//locationOption.setScanSpan(1000*60*5);
			mLocationcClient.setLocOption(locationOption);
			mLocationcClient.start();

		}

		private class MyLocationListener implements BDLocationListener {

			@Override
			public void onReceiveLocation(BDLocation location) {
				// TODO Auto-generated method stub
				loc = location.getAddrStr();
				jingdu = location.getLatitude();
				weidu = location.getLongitude();
				//Toast.makeText(LieBiaoActivity.this, loc, 1000).show();
				if("".equals(loc)){
					weizhi.setText("获取位置信息失败");
				}else{
					weizhi.setText("  "+loc);
				}
				mLocationcClient.stop();
			}
		}
	
		
		private int strTimeToLong(String str){
			int l = 0;
			if(str.contains("分钟")){
				String[] s1 = str.split("分");
				l = Integer.valueOf(s1[0])*60*1000;
			}else if(str.contains("小时")){
				String[] s2 = str.split("小");
				l = Integer.valueOf(s2[0])*60*60*1000;
			}
			return l;
		}
		
}
