package com.help.activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.help.R;
import com.help.activity.achievement.AchievementActivity;
import com.help.activity.edit.EditActivity;
import com.help.activity.im.ConversationListActivity;
import com.help.activity.index.IndexActivity;

import com.help.activity.index.ServiceDemo;
import com.help.activity.mine.MineActivity;
import com.help.activity.more.MoreActivity;
import com.help.base.AppManager;
import com.help.base.BaseActivity;
import com.help.util.StaticVariable;

public class MainActivity extends ActivityGroup implements OnClickListener {

	private LocalActivityManager localActivityManager;
	private FrameLayout container;
	private RadioButton rbIndex, rbMine, rbMore,rbEdit,rbTask;
	private String currentActivityName = "";
	private RadioGroup rg;
	private FrameLayout currentView;

	private Bundle bundle = new Bundle();

	// 读取登录状态 ，loginState = login.
	private Boolean loginState = false;

	private Intent iservice;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initView();
		initViewListener();
		doOtherThing();
		
		//bindService(new Intent(ServiceDemo.ACTION),conn,Context.BIND_AUTO_CREATE);  
		iservice = new Intent(ServiceDemo.ACTION);
		startService(iservice);
		//Toast.makeText(MainActivity.this, "service on", 1000).show();
	}

	private void initView() {
		rg = (RadioGroup) findViewById(R.id.rg);
		container = (FrameLayout) findViewById(R.id.container);
		rbIndex = (RadioButton) findViewById(R.id.rbIndex);
		rbTask = (RadioButton) findViewById(R.id.rbTask);
		rbEdit = (RadioButton) findViewById(R.id.rbEdit);
		rbMine = (RadioButton) findViewById(R.id.rbMine);
		rbMore = (RadioButton) findViewById(R.id.rbMore);
	}

	private void initViewListener() {
		rbIndex.setOnClickListener(this);
		rbMine.setOnClickListener(this);
		rbMore.setOnClickListener(this);
		rbTask.setOnClickListener(this);
		rbEdit.setOnClickListener(this);
	}

	private void doOtherThing() {
		localActivityManager = getLocalActivityManager();

		rbIndex.setSelected(true);
		rbIndex.setChecked(true);
		// 启动IndexActivity
		setContainerView("index", IndexActivity.class, null, false);
	}

	/**
	 * 为启动Activity初始化Intent信息
	 * 
	 * @param cls
	 * @return
	 */
	private Intent initIntent(Class<?> cls, Bundle b) {
		Intent ln = new Intent(this, cls);
		if (b != null) {
			ln.putExtras(b);
		}
		return ln.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	}

	private HashMap<String, FrameLayout> lytMap = new HashMap<String, FrameLayout>();

	/**
	 * 供开发�?在实现类中调用，能将Activity容器内的Activity移除，再将指定的某个Activity加入
	 * 
	 * @param activityName
	 *            加载的Activity在localActivityManager中的名字
	 * @param activityClassTye
	 *            要加载Activity的类�?
	 */
	@SuppressWarnings("rawtypes")
	protected void setContainerView(String activityName,
			Class<?> activityClassTye, Bundle b, boolean needRestart) {

		FrameLayout lyt = null;

		if (lytMap.containsKey(activityName)) {
			if (needRestart) {
				destroy(activityName);
				container.removeView(lytMap.get(activityName));
				lytMap.remove(activityName);
			} else { 
				BaseActivity bA = (BaseActivity) localActivityManager
						.getActivity(activityName);
				// bA.refresh(b);
			}
		}

		if (!lytMap.containsKey(activityName)) {
			lyt = new FrameLayout(this);

			Activity contentActivity = localActivityManager
					.getActivity(activityName);
			if (null == contentActivity) {
				localActivityManager.startActivity(activityName,
						initIntent(activityClassTye, b));
			}

			lyt.addView(localActivityManager.getActivity(activityName)
					.getWindow().getDecorView(), new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

			container.addView(lyt, new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));

			lytMap.put(activityName, lyt);
		}

		Iterator iter = lytMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			FrameLayout val = (FrameLayout) entry.getValue();
			if (val != null) {
				if (activityName.equals(entry.getKey())) {
					val.setVisibility(View.VISIBLE);
					currentActivityName = activityName;
					currentView = val;
				} else {
					val.setVisibility(View.GONE);
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rbIndex:
			setContainerView("index", IndexActivity.class, null, true);
			rbIndex.setChecked(true);
			BackKeyCount = 0;
			break;
		case R.id.rbTask:
			setContainerView("task", AchievementActivity.class, null, false);
			rbTask.setChecked(true);
			BackKeyCount = 0;
			break;
		case R.id.rbEdit:
			setContainerView("edit", EditActivity.class, null, false);
			rbEdit.setChecked(true);
			BackKeyCount = 0;
			break;
		case R.id.rbMine:
			setContainerView("mine", ConversationListActivity.class, null, false);
			rbMine.setChecked(true);
			BackKeyCount = 0;
			break;
		case R.id.rbMore:
			setContainerView("more", MoreActivity.class, null, false);
			rbMore.setChecked(true);
			BackKeyCount = 0;
			break;
		}
	}

	public void toTab(int resId, Bundle b, boolean needRestart) {
		switch (resId) {
		case R.id.rbIndex:
			setContainerView("index", IndexActivity.class, b, needRestart);
			rbIndex.setChecked(true);
			break;
		case R.id.rbTask:
			setContainerView("task", AchievementActivity.class, b, needRestart);
			rbTask.setChecked(true);
			break;
		case R.id.rbEdit:
			setContainerView("edit", EditActivity.class, b, needRestart);
			rbEdit.setChecked(true);
			break;
		case R.id.rbMine:
			setContainerView("mine", ConversationListActivity.class, b, needRestart);
			rbMine.setChecked(true);
			break;
		case R.id.rbMore:
			setContainerView("more", MoreActivity.class, b, needRestart);
			rbMore.setChecked(true);
			break;
		}
	}

	public boolean destroy(String id) {
		if (localActivityManager != null) {
			localActivityManager.destroyActivity(id, false);
			// http://code.google.com/p/android/issues/detail?id=12359
			// http://www.netmite.com/android/mydroid/frameworks/base/core/java/android/app/LocalActivityManager.java
			try {
				final Field mActivitiesField = LocalActivityManager.class
						.getDeclaredField("mActivities");
				if (mActivitiesField != null) {
					mActivitiesField.setAccessible(true);
					@SuppressWarnings("unchecked")
					final Map<String, Object> mActivities = (Map<String, Object>) mActivitiesField
							.get(localActivityManager);
					if (mActivities != null) {
						mActivities.remove(id);
					}
					final Field mActivityArrayField = LocalActivityManager.class
							.getDeclaredField("mActivityArray");
					if (mActivityArrayField != null) {
						mActivityArrayField.setAccessible(true);
						@SuppressWarnings("unchecked")
						final ArrayList<Object> mActivityArray = (ArrayList<Object>) mActivityArrayField
								.get(localActivityManager);
						if (mActivityArray != null) {
							for (Object record : mActivityArray) {
								final Field idField = record.getClass()
										.getDeclaredField("id");
								if (idField != null) {
									idField.setAccessible(true);
									final String _id = (String) idField
											.get(record);
									if (id.equals(_id)) {
										mActivityArray.remove(record);
										break;
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		String toIndex = StaticVariable.get(StaticVariable.sv_toIndex);
		String toTask = StaticVariable.get(StaticVariable.sv_toTask);
		String toEdit = StaticVariable.get(StaticVariable.sv_toEdit);
		String toMine = StaticVariable.get(StaticVariable.sv_toMine);
		String toMore = StaticVariable.get(StaticVariable.sv_toMore);
		if (toIndex.equals("1")) {
			toTab(R.id.rbIndex, null, true);
		}
		if (toTask.equals("2")) {
			toTab(R.id.rbTask, null, false);
		}
		if (toEdit.equals("3")) {
			toTab(R.id.rbEdit, null, false);
		}
		if (toMine.equals("4")) {
			toTab(R.id.rbMine, null, false);
		}
		if (toMore.equals("5")) {
			toTab(R.id.rbMore, null, false);
		}
		super.onResume();
	}

	/**
	 * 点击返回键退出程序，
	 */
	public static boolean isOnKeyDown;
	protected int BackKeyCount = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		// 先判断是否是返回键
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			BackKeyCount++;
			if (BackKeyCount >= 2) {
				isOnKeyDown = false;
				stopService(iservice);
				//Toast.makeText(MainActivity.this, "service offffffffff", 1000).show();
				AppManager.getAppManager().appExit(this);
				finish();

			} else {
				// if (AppConfig.SHOWTOAST) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				// }
				new Thread(new Runnable() {
					@Override
					public void run() {
						Timer timer = new Timer();
						timer.schedule(new ExitTask1(), 3000);
					}
				}).start();

			}
		}
		return false;
	}

	/**
	 * 指定时间后执行task任务
	 */
	class ExitTask1 extends TimerTask {

		@Override
		public void run() {
			BackKeyCount = 0;
		}

	}

}
