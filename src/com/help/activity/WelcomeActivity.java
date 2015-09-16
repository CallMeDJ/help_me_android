package com.help.activity;

import android.app.Dialog;
import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.example.help.R;
import com.help.base.BaseActivity;
import com.help.been.UserMode;
import com.help.util.CommonUtil;
import com.help.util.ProcessDialogUtil;
import com.help.util.Tool;

public class WelcomeActivity extends BaseActivity {

	private ImageView welcomeImg = null;

	// 进入引导页的标记
	private Boolean toGuide = true;

	private Dialog juhua = null;

	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_welcome;
	}

	@Override
	protected void getIntentWord() {
		// TODO Auto-generated method stub
		super.getIntentWord();
		// 在此获取sharedPreferences的toGuide标记，firstUse
		if ("first".equals(CommonUtil.getStringByKey(WelcomeActivity.this,
				"firstUse", "", ""))) {
			toGuide = false;
		}
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();

		juhua = new ProcessDialogUtil(WelcomeActivity.this);

		welcomeImg = (ImageView) this.findViewById(R.id.welcome_img);
	}

	@Override
	protected void doOtherThing() {
		// TODO Auto-generated method stub
		super.doOtherThing();
		AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
		anima.setDuration(2000);// 设置动画显示时间
		welcomeImg.startAnimation(anima);
		anima.setAnimationListener(new AnimationImpl());
	}

	private class AnimationImpl implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {
			welcomeImg.setBackgroundResource(R.drawable.bg4);
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			skip(); // 动画结束后跳转到别的页面
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

	}

	// 在此判断缓存文件，是否是第一次登录，进入到引导页，否则判断是否登录，否则直接跳转到主页
	private void skip() {
		if (toGuide) {
			startActivity(new Intent(this, GuideActivity.class));
			finish();
		} else {

			// 获取个人资料信息 如果有用户资料，则刷新
			// TODO Auto-generated method stub
			String a = Tool.readData(this, "user", "login");
			if (!"ok".equals(a)) {
				startActivity(new Intent(WelcomeActivity.this,
						LoginActivity.class));
				anim_right_in();
				finish();
			} else {
				startActivity(new Intent(WelcomeActivity.this,
						MainActivity.class));
				anim_right_in();
				finish();
				//
				// juhua.show();
				//
				// Map<String, String> map = new HashMap<String, String>();
				// map.put("urlTag", "1");// 可不传（区分一个activity多个请求）
				// map.put("isLock", "0");// 0不锁，1是锁
				// RequestThreadAbstract thread =
				// RequestFactory.createRequestThread(
				// 2, map, WelcomeActivity.this, mHandler);
				// RequestPool.execute(thread);
			}

		}
	}

	// 方法
	private void toIntent() {
		startActivity(new Intent(this, MainActivity.class));
		anim_right_in();
		finish();
	}

}
