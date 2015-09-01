package com.help.activity.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.help.R;
import com.help.base.BaseActivity;
import com.help.listview.XListView;
import com.help.listview.XListView.IXListViewListener;
import com.help.util.ProcessDialogUtil;

public class TaskActivity extends BaseActivity implements
		IXListViewListener,OnClickListener {

	// list<object>集合
	private List<Map<String, String>> allList1 = new ArrayList<Map<String, String>>();
	//private List<Map<String, String>> allList2 = new ArrayList<Map<String, String>>();
	// 下拉刷新
	private XListView mListView = null;
	// 自定义适配器
	private MyAdapter1 mAdapter1 = null;
	//private MyAdapter2 mAdapter2 = null;
	// radioGroup
	private RadioGroup rg = null;

	// 
	private RadioButton rb1 = null;
	// 
	private RadioButton rb2 = null;

	// 记录当前刷新页
	private int curPage = 1;

	ProcessDialogUtil juhua = null;

	//记录当前选中的item
	private int item = 1;
	
	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.main_task;
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
		setTitle("我的任务");
		juhua = new ProcessDialogUtil(TaskActivity.this);

		rg = (RadioGroup) findViewById(R.id.tingchezhong_rg);
		rb1 = (RadioButton) findViewById(R.id.tingchezhong_rb1);
		rb1.setChecked(true);
		rb2 = (RadioButton) findViewById(R.id.tingchezhong_rb2);

		wodelist();
		
		mListView = (XListView) findViewById(R.id.tingchezhong_listview);
		mListView.setPullLoadEnable(false);
		mAdapter1 = new MyAdapter1();
		//mAdapter2 = new MyAdapter2();
		mListView.setAdapter(mAdapter1);
		mListView.setXListViewListener(this);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("ShowToast")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				// TODO Auto-generated method stub
				if (position != 0) {

					anim_right_in();
				} else {
					return;
				}
			}
		});

		// 接口
		// loadHttp("0");
	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarBack.setOnClickListener(this);

		rb1.setOnClickListener(this);
		rb2.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.title_bar_back:
			TaskActivity.this.finish();
			anim_right_out();
			break;
		case R.id.tingchezhong_rb1:
			curPage = 1;
			//allList1.clear();
			mListView.setAdapter(mAdapter1);
			mAdapter1.notifyDataSetChanged();
			// loadHttp("0");
			break;
		case R.id.tingchezhong_rb2:
			curPage = 1;
			//allList2.clear();
			mListView.setAdapter(mAdapter1);
			mAdapter1.notifyDataSetChanged();
			// loadHttp("1");
			break;

		default:
			break;
		}
	}

	/** 适配器 停车中 **/
	private class MyAdapter1 extends BaseAdapter {

		@Override
		public int getCount() {
			return allList1 != null ? allList1.size() : 0;
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
				convertView = LayoutInflater.from(TaskActivity.this)
						.inflate(R.layout.index_list_item, null);

				holder.time = (TextView) convertView
						.findViewById(R.id.index_item_time);

				holder.content = (TextView) convertView
						.findViewById(R.id.index_item_content);

				holder.dizhi = (TextView) convertView
						.findViewById(R.id.index_item_dizhi);
				holder.juli = (TextView) convertView
						.findViewById(R.id.index_item_juli);
				holder.qian = (TextView) convertView
						.findViewById(R.id.index_item_qian);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.time.setText(allList1.get(position).get("time"));
			holder.content.setText(allList1.get(position).get("time"));
			holder.dizhi.setText(allList1.get(position).get("time"));
			holder.juli.setText(allList1.get(position).get("time"));
			holder.qian.setText(allList1.get(position).get("time"));
			

			return convertView;
		}

		final class ViewHolder {
			TextView time;
			TextView content;
			TextView dizhi;
			TextView juli;
			TextView qian;
		}
	}

	// /** 适配器 ---预定记录 **/
	// private class MyAdapter2 extends BaseAdapter {
	//
	// @Override
	// public int getCount() {
	// return allList2 != null ? allList2.size() : 0;
	// }
	//
	// @Override
	// public Object getItem(int position) {
	// return null;
	// }
	//
	// @Override
	// public long getItemId(int position) {
	// return position;
	// }
	//
	// ViewHolder holder;
	//
	// @SuppressLint("ResourceAsColor")
	// @Override
	// public View getView(final int position, View convertView,
	// ViewGroup parent) {
	//
	// holder = new ViewHolder();
	//
	// if (convertView == null) {
	// convertView = LayoutInflater.from(TingCheZhongActivity.this)
	// .inflate(R.layout.menu_car_jilu_item, null);
	//
	// holder.title = (TextView) convertView
	// .findViewById(R.id.m_car_jilu_title);
	//
	// holder.time = (TextView) convertView
	// .findViewById(R.id.m_car_jilu_time);
	//
	// holder.zhifu = (TextView) convertView
	// .findViewById(R.id.m_car_jilu_zhifu);
	//
	// holder.jiner = (TextView) convertView
	// .findViewById(R.id.m_car_jilu_jiner);
	// convertView.setTag(holder);
	// } else {
	// holder = (ViewHolder) convertView.getTag();
	// }
	//
	// return convertView;
	// }
	//
	// final class ViewHolder {
	// TextView title;
	// TextView time;
	// TextView zhifu;
	// TextView jiner;
	// }
	// }

	private void onStopLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime(new Date().toLocaleString());
	}

	// listView下拉刷新，上拉加载
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

		if (rb1.isChecked()) {

			curPage = 1;
			// loadHttp("0");
		} else if (rb2.isChecked()) {

			curPage = 1;
			// loadHttp("1");
		}
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

		if (rb1.isChecked()) {
			curPage++;
			// loadHttp("0");
		} else if (rb2.isChecked()) {
			curPage++;
			// loadHttp("1");
		} 
	}

	public void wodelist() {
		allList1 = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("time", "2015-08-11");
		map1.put("content", "帮我去拿东西");
		map1.put("dizhi", "一楼大厅");
		map1.put("juli", "已完成");
		map1.put("jiner", "12￥");
		allList1.add(map1);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("time", "2015-08-11");
		map2.put("content", "求带饭求带饭");
		map2.put("dizhi", "一楼大厅");
		map2.put("juli", "已完成");
		map2.put("jiner", "102￥");
		allList1.add(map2);
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("time", "2015-08-11");
		map3.put("content", "求带饭求带饭");
		map3.put("dizhi", "一楼大厅");
		map3.put("juli", "已完成");
		map3.put("jiner", "2￥");
		allList1.add(map3);
		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("time", "2015-08-11");
		map4.put("content", "求带饭求带饭");
		map4.put("dizhi", "一楼大厅");
		map4.put("juli", "已完成");
		map4.put("jiner", "2￥");
		allList1.add(map4);
		allList1 = new ArrayList<Map<String, String>>();
		Map<String, String> map12 = new HashMap<String, String>();
		map12.put("time", "2015-08-11");
		map12.put("content", "求带饭求带饭");
		map12.put("dizhi", "一楼大厅");
		map12.put("juli", "已完成");
		map12.put("jiner", "2￥");
		allList1.add(map12);
		Map<String, String> map22 = new HashMap<String, String>();
		map22.put("time", "2015-08-11");
		map22.put("content", "求带饭求带饭");
		map22.put("dizhi", "一楼大厅");
		map22.put("juli", "已完成");
		map22.put("jiner", "10￥");
		allList1.add(map22);
		Map<String, String> map32 = new HashMap<String, String>();
		map32.put("time", "2015-08-11");
		map32.put("content", "求快递捎带求带饭");
		map32.put("dizhi", "二楼大厅");
		map32.put("juli", "已完成");
		map32.put("jiner", "3￥");
		allList1.add(map32);
		Map<String, String> map42 = new HashMap<String, String>();
		map42.put("time", "2015-08-11");
		map42.put("content", "求带饭求带饭");
		map42.put("dizhi", "一楼大厅");
		map42.put("juli", "已完成");
		map42.put("jiner", "2￥");
		allList1.add(map42);
	}

}
