package com.help.activity;

import android.view.View;

import java.util.Date;
import java.util.List;

import com.example.help.R;
import com.help.base.BaseActivity;
import com.help.listview.XListView;
import com.help.listview.XListView.IXListViewListener;

public class GlobalRankActivity extends BaseActivity implements
		IXListViewListener {

	private XListView listView;

	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.global_rank;
	}

	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("排行榜");
		showBack();
		titleBarBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				GlobalRankActivity.this.finish();
			}
		});

		listView = (XListView) findViewById(R.id.global_rank);
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(true);
		listView.setXListViewListener(this);
		
		GlobalRankListAdapter adapter = new GlobalRankListAdapter(this);
		adapter.setDataList(StaticDatas.globalRankList);
		listView.setAdapter(adapter);
		listView.deferNotifyDataSetChanged();
	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		onStopLoad();
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		onStopLoad();
	}

	private void onStopLoad() {
		listView.stopRefresh();
		listView.stopLoadMore();
		listView.setRefreshTime(new Date().toLocaleString());
	}
}
