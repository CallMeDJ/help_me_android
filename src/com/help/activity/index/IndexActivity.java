package com.help.activity.index;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager.Request;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.help.R;
import com.help.base.BaseActivity;
import com.help.listview.XListView;
import com.help.listview.XListView.IXListViewListener;
import com.help.util.ProcessDialogUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ESalesHttpClient;
import com.loopj.android.http.RequestParams;

public class IndexActivity extends BaseActivity implements OnClickListener,
		IXListViewListener {

	private EditText edit = null;
	private TextView map = null;
	// private TextView ser = null;

	private ArrayList<Map<String, String>> list = null;

	// 下拉刷新
	private XListView mListView = null;
	// 记录当前刷新页
	private int curPage = 1;
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

		list = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("time", "2015-08-10");
		map1.put("content", "求取快递，，，，，，");
		map1.put("dizhi", "平安大厦四楼");
		map1.put("juli", "10000m");
		map1.put("qian", "88.88");
		list.add(map1);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("time", "2015-08-10");
		map2.put("content", "谁能送我一本JavaScript书，，，，，，");
		map2.put("dizhi", "平安大厦四楼");
		map2.put("juli", "10m");
		map2.put("qian", "999.00");
		list.add(map2);
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("time", "2015-08-10");
		map3.put("content", "谁帮我送盒饭，，，，，，");
		map3.put("dizhi", "八卦岭");
		map3.put("juli", "100m");
		map3.put("qian", "10.00");
		list.add(map3);
		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("time", "2015-08-10");
		map4.put("content", "来来来求JavaScript书，，，，，，");
		map4.put("dizhi", "平安大厦一楼");
		map4.put("juli", "10m");
		map4.put("qian", "10000.00");
		list.add(map4);
		Map<String, String> map5 = new HashMap<String, String>();
		map5.put("time", "2015-08-10");
		map5.put("content", "来来来求JavaScript书，，，，，，");
		map5.put("dizhi", "平安大厦一楼");
		map5.put("juli", "10m");
		map5.put("qian", "10000.00");
		list.add(map5);

		Map<String, String> map6 = new HashMap<String, String>();
		map6.put("time", "2015-08-10");
		map6.put("content", "来来来求JavaScript书，，，，，，");
		map6.put("dizhi", "平安大厦一楼");
		map6.put("juli", "10m");
		map6.put("qian", "10000.00");
		list.add(map6);

		Map<String, String> map7 = new HashMap<String, String>();
		map7.put("time", "2015-08-10");
		map7.put("content", "来来来求JavaScript书，，，，，，");
		map7.put("dizhi", "平安大厦一楼");
		map7.put("juli", "10m");
		map7.put("qian", "10000.00");
		list.add(map7);

		Map<String, String> map8 = new HashMap<String, String>();
		map8.put("time", "2015-08-10");
		map8.put("content", "来来来求JavaScript书，，，，，，");
		map8.put("dizhi", "平安大厦一楼");
		map8.put("juli", "10m");
		map8.put("qian", "10000.00");
		list.add(map8);

		edit = (EditText) findViewById(R.id.index_edit);
		map = (TextView) findViewById(R.id.index_map);
		// ser = (TextView) findViewById(R.id.index_ser);

		mListView = (XListView) findViewById(R.id.index_list);
		mListView.setPullLoadEnable(false);
		mListView.setPullRefreshEnable(true);

		SimpleAdapter adapter = new SimpleAdapter(this, list,
				R.layout.index_list_item, new String[] { "time", "content",
						"dizhi", "juli", "qian" }, new int[] {
						R.id.index_item_time, R.id.index_item_content,
						R.id.index_item_dizhi, R.id.index_item_juli,
						R.id.index_item_qian });
		mListView.setAdapter(adapter);

		mListView.setXListViewListener(this);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("ShowToast")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				// TODO Auto-generated method stub
				if (position != 0) {
					if(position == 1){
						getTask(position);
					}
					else{
						Intent intent = new Intent(IndexActivity.this,
								JieShouRenWuActivity.class);
						intent.putExtra("show", "fabu");
						startActivity(intent);
						anim_right_in();
					}

				} else {
					return;
				}
			}
		});

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

			toRequest();

			break;
		case R.id.index_map:
			startActivity(new Intent(IndexActivity.this, MapActivity.class));
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
		curPage = 1;
		onStopLoad();
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		curPage++;
		onStopLoad();
	}

	public void toRequest() {
		RequestParams params = new RequestParams();
		
		ESalesHttpClient.requestGet(this, "url",params,
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
					}

					@Override
					public void onFailure(Throwable error, String content) {
						// TODO Auto-generated method stub
						super.onFailure(error, content);
					}

					@Override
					public void onSuccess(int statusCode, String content) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, content);
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
				Intent intent = new Intent(IndexActivity.this,
						JieShouRenWuActivity.class);
				intent.putExtra("show", "jieshou");
				startActivity(intent);
				anim_right_in();
				dialog.dismiss();
			}
		});

		dialog = new AlertDialog.Builder(IndexActivity.this).setView(view)
				.create();
		dialog.show();
	}

}
