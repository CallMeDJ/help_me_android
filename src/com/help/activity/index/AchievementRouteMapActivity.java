package com.help.activity.index;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.example.help.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示覆盖物的用法
 */
public class AchievementRouteMapActivity extends Activity {

	/**
	 * MapView 是地图主控件
	 */
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	private InfoWindow mInfoWindow;

	private List mtaskDataList=null;

	// 初始化全局 bitmap 信息，不用时及时 recycle
	BitmapDescriptor bdGround = BitmapDescriptorFactory
			.fromResource(
					R.drawable.ground_overlay);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.achievement_route);
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
		mBaiduMap.setMapStatus(msu);
		initOverlay();
	}

	public void initOverlay() {
		/*
		* -数据接口-
		* 将_taskDataList接入，即可绘图。
		* */
		mtaskDataList = TaskObjectSingleton.getInstance().getmTaskList();//用单例取得数据
		final double _DRIFT = 0.007;//点偏移量
		LatLng _center = new LatLng(0,0);

		// add ground overlay
		for( int i=0; i<mtaskDataList.size(); i++ ) {
			TaskObject obj = (TaskObject) mtaskDataList.get(i);//取得单个对象
			LatLng southwest = new LatLng( obj.getmLat()-_DRIFT, obj.getmLng()-_DRIFT );
			LatLng northeast = new LatLng( obj.getmLat()+_DRIFT, obj.getmLng()+_DRIFT );
			LatLngBounds bounds = new LatLngBounds.Builder().include(northeast)
					.include(southwest).build();

			OverlayOptions ooGround = new GroundOverlayOptions()
					.positionFromBounds(bounds).image(bdGround).transparency(0.8f);
			//obj.setMmarker((Marker)mBaiduMap.addOverlay(ooGround) );//将Marker保存到taskobj中
			mBaiduMap.addOverlay(ooGround);

			if(i<1){
				_center = new LatLng(obj.getmLat(),obj.getmLng());
			}
			else{
				_center = new LatLng( (_center.latitude+obj.getmLat())/2,
						(_center.longitude+obj.getmLng())/2);
			}
		}

		MapStatusUpdate u = MapStatusUpdateFactory
				.newLatLng(_center);
		mBaiduMap.setMapStatus(u);

		//设置Marker的响应
		/*
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			public boolean onMarkerClick(final Marker marker) {
				for (int i = 0; i < mtaskDataList.size(); i++) {
					TaskObject obj = (TaskObject) mtaskDataList.get(i);
					if (marker == obj.getMmarker()) {
						// To Do,
						Toast.makeText(getApplicationContext(), obj.toString(),
								Toast.LENGTH_SHORT).show();
						break;
					}//end if
				}//end for loop
				return true;
			}
		});//*/

		mBaiduMap.setOnMarkerDragListener(new OnMarkerDragListener() {
			public void onMarkerDrag(Marker marker) {
			}

			public void onMarkerDragEnd(Marker marker) {
			}

			public void onMarkerDragStart(Marker marker) {}
		});
	}

	/**
	 * 清除所有Overlay
	 * 
	 * @param view
	 */
	public void clearOverlay(View view) {
		mBaiduMap.clear();
	}

	/**
	 * 重新添加Overlay
	 * 
	 * @param view
	 */
	public void resetOverlay(View view) {
		clearOverlay(null);
		initOverlay();
	}

	@Override
	protected void onPause() {
		// MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
		mMapView.onDestroy();
		super.onDestroy();
		// 回收 bitmap 资源
		bdGround.recycle();
	}

}
