package com.help.activity.achievement;


import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.help.R;
import com.help.activity.GlobalRankActivity;
import com.help.activity.PositionActivity;
import com.help.activity.StaticDatas;
import com.help.activity.index.AchievementRouteMapActivity;
import com.help.activity.more.Wallet;
import com.help.activity.task.TaskActivity;
import com.help.base.BaseActivity;
import com.help.util.expLiner;

public class AchievementActivity extends BaseActivity{

	private TextView achievement_position_value;
	private TextView achievement_global_rank_value;
	private TextView achievement_current_level;
	private expLiner achievement_expLiner;
	private TextView achievement_next_level;
	private TextView achievement_completed_mission;
	private TextView achievement_agree_count;
	private TextView achievement_money_count;
	private RelativeLayout achievement_my_route;
	private RelativeLayout achievement_finished_task;
	private RelativeLayout achievement_wallet;

	private Context context;
	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.achievement;
	}
	private void initData(){
		StaticDatas.initGlobalRank(this);

		Map<String, String> achievemengData = StaticDatas.achievementData;

		String[] datas  = achievemengData.get("achievement_expLiner").split("/");
		int progress = Integer.parseInt(datas[0]);
		int max = Integer.parseInt(datas[1]);
		achievement_expLiner.setProgress(progress);
		achievement_expLiner.setMax(max);


		achievement_position_value.setText(achievemengData.get("achievement_position_value"));
		achievement_global_rank_value.setText(achievemengData.get("achievement_global_rank_value"));
		achievement_completed_mission.setText(achievemengData.get("achievement_completed_mission"));
		achievement_next_level.setText(achievemengData.get("achievement_next_level"));
		achievement_agree_count.setText(achievemengData.get("achievement_agree_count"));
		achievement_money_count.setText(achievemengData.get("achievement_money_count"));

		achievement_current_level.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(context, PositionActivity.class);
				startActivity(i);
			}
		});

		achievement_global_rank_value.setOnClickListener(toRankListener);

	}


	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		
		achievement_position_value = (TextView) findViewById(R.id.achievement_position_value);
		achievement_global_rank_value = (TextView) findViewById(R.id.achievement_global_rank_value);
		achievement_current_level = (TextView) findViewById(R.id.achievement_position_value);
		achievement_expLiner = (expLiner) findViewById(R.id.achievement_expLiner);
		achievement_next_level = (TextView) findViewById(R.id.achievement_next_level);
		achievement_completed_mission = (TextView) findViewById(R.id.achievement_completed_mission);
		achievement_agree_count = (TextView) findViewById(R.id.achievement_agree_count);
		achievement_money_count = (TextView) findViewById(R.id.achievement_money_count);
		achievement_my_route = (RelativeLayout)findViewById(R.id.achievement_my_route);
		achievement_finished_task = (RelativeLayout)findViewById(R.id.achievement_finished_task);
		achievement_wallet = (RelativeLayout)findViewById(R.id.achievement_wallet);
		
		achievement_wallet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent = new Intent(context, Wallet.class);
				context.startActivity(intent);
				
			}
		});

		achievement_finished_task.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent = new Intent(context, TaskActivity.class);
				context.startActivity(intent);
			}
		});



		achievement_my_route.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent = new Intent(AchievementActivity.this, AchievementRouteMapActivity.class);
				startActivity(intent);
			}
		});

		context = this;


		initData();
	}

	private View.OnClickListener toRankListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			Intent i = new Intent(context, GlobalRankActivity.class);
			startActivity(i);
		}
	};

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
	}
	
	
	
}
