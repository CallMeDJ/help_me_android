package com.help.activity.index;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

import com.example.help.R;
import com.help.base.BaseActivity;

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

	private Dialog dialog = null;

	// 是否显示地理位置
	private String showadd = "";

	// 剩余时间
	private long lastTime ;
	
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
		}

	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		setTitle("接受任务");
		showBack();

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

		//启动倒计时
		lastTime = System.currentTimeMillis()+(3500*1000);
		
		mc = new MyCountDownTimer(lastTime-System.currentTimeMillis(), 1000);
		mc.start();
	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarBack.setOnClickListener(this);
		cancel.setOnClickListener(this);
		submit.setOnClickListener(this);
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
					pingjia();
				} else {
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
				if(rb.getRating()>= 4){
					tv2.setText("好评");
				}else if(rb.getRating() == 3){
					tv2.setText("中评");
				}else{
					tv2.setText("差评");
				}
				
			}
		});
		
		Button tv_submit = (Button) view.findViewById(R.id.pingjia_submit);

		tv_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				dialog.dismiss();
				JieShouRenWuActivity.this.finish();
			}
		});

		dialog = new AlertDialog.Builder(JieShouRenWuActivity.this).setView(
				view).create();
		dialog.show();
	}

	
	//倒计时
	private MyCountDownTimer mc ;
	class MyCountDownTimer extends CountDownTimer{

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

}
