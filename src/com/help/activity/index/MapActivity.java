package com.help.activity.index;

import hdodenhof.circleimageview.CircleImageView;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.help.R;

public class MapActivity extends Activity {

	// 定位相关
	LocationClient mLocClient;
	public MyLocationListenner myListener;
	private MyLocationConfiguration.LocationMode mCurrentMode;
	BitmapDescriptor mCurrentMarker;

	// 地图控件相关
	MapView mMapView = null;
	BaiduMap mBaiduMap = null;

	// UI相关
	RadioGroup.OnCheckedChangeListener radioButtonListener;
	// Button requestLocButton;
	boolean isFirstLoc = true;// 是否首次定位

	CircleImageView circleImageView = null;

	// 数据相关
	List mTaskDataList;

	// 覆盖窗口
	private InfoWindow mInfoWindow;

//	public void setmTaskDataList(List mTaskDataList) {
//		this.mTaskDataList = mTaskDataList;
//	}

	// 模拟外部调用，测试时，直接供给数据，并把数据传到本Activity的mTaskDataList
//	public void onTestOutside() {
//		TaskObjectList taskObjList = new TaskObjectList();
//		this.setmTaskDataList(taskObjList.getmTaskList());
//	}

	public void initTaskList() {
		// BitmapDescriptor bd =
		// BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);

		for (int i = 0; i < mTaskDataList.size(); i++) {
			TaskObject obj = (TaskObject) mTaskDataList.get(i);// 取得单个对象

			LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View layoutView = inflater.inflate(R.layout.circleimageviewlayout,
					null);
			circleImageView = (CircleImageView) layoutView
					.findViewById(R.id.img_1);
			circleImageView.setImageResource(obj.getMbmpresid());// R.drawable.penguins，取得头像图像ID
			CircleImageView littleImageView = (CircleImageView) layoutView
					.findViewById(R.id.img_2);
			littleImageView.setImageResource(obj.getMlittlebmpresid());// R.drawable.penguins，取得小图片图像ID
			layoutView.setLayoutParams(new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));// 这一句不加，RelativeLayout布局会出问题
			BitmapDescriptor bd = BitmapDescriptorFactory.fromView(layoutView);

			LatLng ll = new LatLng(obj.getmLat(), obj.getmLng());
			OverlayOptions oo = new MarkerOptions().position(ll).icon(bd)
					.zIndex(13);
			obj.setMmarker((Marker) (mBaiduMap.addOverlay(oo)));
			obj.setMlayoutView(layoutView);
		}

	}

	public void initLocation() {
		myListener = new MyLocationListenner();
		// requestLocButton = (Button) findViewById(R.id.button1);
		mCurrentMode = LocationMode.NORMAL;
		// requestLocButton.setText("普通");
		// View.OnClickListener btnClickListener = new View.OnClickListener() {
		// public void onClick(View v) {
		// switch (mCurrentMode) {
		// case NORMAL:
		// requestLocButton.setText("跟随");
		// mCurrentMode = LocationMode.FOLLOWING;
		// mBaiduMap
		// .setMyLocationConfigeration(new MyLocationConfiguration(
		// mCurrentMode, true, mCurrentMarker));
		// break;
		// case COMPASS:
		// requestLocButton.setText("普通");
		// mCurrentMode = LocationMode.NORMAL;
		// mBaiduMap
		// .setMyLocationConfigeration(new MyLocationConfiguration(
		// mCurrentMode, true, mCurrentMarker));
		// break;
		// case FOLLOWING:
		// requestLocButton.setText("罗盘");
		// mCurrentMode = LocationMode.COMPASS;
		// mBaiduMap
		// .setMyLocationConfigeration(new MyLocationConfiguration(
		// mCurrentMode, true, mCurrentMarker));
		// break;
		// }
		// }
		// };
		// requestLocButton.setOnClickListener(btnClickListener);

		// RadioGroup group = (RadioGroup) this.findViewById(R.id.radioGroup);
		// radioButtonListener = new RadioGroup.OnCheckedChangeListener() {
		// @Override
		// public void onCheckedChanged(RadioGroup group, int checkedId) {
		// if (checkedId == R.id.defaulticon) {
		// // 传入null则，恢复默认图标
		// mCurrentMarker = null;
		// mBaiduMap
		// .setMyLocationConfigeration(new MyLocationConfiguration(
		// mCurrentMode, true, null));
		// }
		// if (checkedId == R.id.customicon) {
		// // 修改为自定义marker
		// mCurrentMarker = BitmapDescriptorFactory
		// .fromResource(R.drawable.icon_geo);
		// mBaiduMap
		// .setMyLocationConfigeration(new MyLocationConfiguration(
		// mCurrentMode, true, mCurrentMarker));
		// }
		// }
		// };
		// group.setOnCheckedChangeListener(radioButtonListener);

		// 地图初始化
		// mMapView = (MapView) findViewById(R.id.bmapView);
		// mBaiduMap = mMapView.getMap();
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
	}

	public void initOverlay() {
		this.initTaskList();

		// 点击不同的覆盖物的事件响应
		mBaiduMap
				.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
					public boolean onMarkerClick(final Marker marker) {

						for (int i = 0;i < mTaskDataList.size(); i++) {
							TaskObject temp_obj = (TaskObject) mTaskDataList
									.get(i);
							final int mapposition = i;
							if (marker == temp_obj.getMmarker()) {
								CircleImageView circleImageView = (CircleImageView) ((ViewGroup) temp_obj
										.getMlayoutView()).getChildAt(0);
								circleImageView.setBorderColor(getResources()
										.getColor(R.color.blue));
								// To Do, 点击不同的覆盖物（头像）的响应处。数据在temp_obj中。
								layout.setVisibility(View.VISIBLE);
								tv1.setText(temp_obj.getMmasterPerson());
								tv2.setText(temp_obj.getMtaskContext());
								tv3.setText(temp_obj.getMaddress());
								tv4.setText(temp_obj.getMlength());
								tv5.setText(temp_obj.getMtaskReward());
								layout.setOnClickListener(new View.OnClickListener() {

									@Override
									public void onClick(View arg0) {
										// TODO Auto-generated method stub
										//Toast.makeText(MapActivity.this, "---", 1000).show();
										getTask(mapposition);
									}
								});

							}// end if
						}// end for loop
						return true;
					}
				});// */
	}

	private TextView title = null;
	private TextView back = null;
	private TextView tolist = null;
	private EditText sousuo = null;

	private TextView tv1 = null;
	private TextView tv2 = null;
	private TextView tv3 = null;
	private TextView tv4 = null;
	private TextView tv5 = null;

	private LinearLayout layout = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.map);

		// 地图初始化
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		mBaiduMap.setMapStatus(MapStatusUpdateFactory
				.newMapStatus(new MapStatus.Builder().zoom(14).build()));// 设置缩放级别

		// 加载控件
		title = (TextView) findViewById(R.id.titile_bar_title);
		back = (TextView) findViewById(R.id.title_bar_back);
		tolist = (TextView) findViewById(R.id.index_map_list);
		sousuo = (EditText) findViewById(R.id.index_edit_map);

		tv1 = (TextView) findViewById(R.id.map_item_time);
		tv2 = (TextView) findViewById(R.id.map_item_content);
		tv3 = (TextView) findViewById(R.id.map_item_dizhi);
		tv4 = (TextView) findViewById(R.id.map_item_juli);
		tv5 = (TextView) findViewById(R.id.map_item_qian);

		layout = (LinearLayout) findViewById(R.id.map_item_layout);

		Intent intent = getIntent();
		mTaskDataList = (List) intent.getSerializableExtra("taskList");

		this.initLocation();
		this.initOverlay();

		// 返回列表
		// 返回按钮
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MapActivity.this.finish();
			}
		});
		// 返回列表
		tolist.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MapActivity.this.finish();
			}
		});

		mBaiduMap.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void onMapClick(LatLng arg0) {
				// TODO Auto-generated method stub
				layout.setVisibility(View.GONE);
			}
		});

	}

	public void clearOverlay(View view) {
		mBaiduMap.clear();
	}

	public void resetOverlay(View view) {
		clearOverlay(null);
		initOverlay();
	}

	@Override
	protected void onDestroy() {
		// 退出时销毁定位
		mLocClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;

		super.onDestroy();

		// 回收 bitmap 资源
		// bd.recycle();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mMapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
			if (isFirstLoc) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				mBaiduMap.animateMapStatus(u);
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}

	private Dialog dialog = null;

	/** 获取任务对话框 */
	private void getTask(final int position) {

		View view = LayoutInflater.from(MapActivity.this).inflate(
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
				Intent intent = new Intent(MapActivity.this,
						JieShouRenWuActivity.class);
				intent.putExtra("show", "jieshou");
				startActivity(intent);
				dialog.dismiss();
			}
		});

		dialog = new AlertDialog.Builder(MapActivity.this).setView(view)
				.create();
		dialog.show();
	}

}
