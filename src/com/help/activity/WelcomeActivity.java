package com.help.activity;

import java.util.Random;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.help.R;
import com.help.base.BaseActivity;
import com.help.util.CommonUtil;
import com.help.util.ProcessDialogUtil;
import com.help.util.Tool;

public class WelcomeActivity extends BaseActivity {

	// private ImageView welcomeImg = null;

	// 进入引导页的标记
	private Boolean toGuide = true;

	private TextView tv1 = null;
	private TextView tv2 = null;

	private Dialog juhua = null;

	private KeywordsFlow keywordsFlow;

	public static final String[] keywords = { "帮吗", "中国平安", "帮吗", "中国平安", "帮吗",
			"中国平安", "帮吗", "中国平安", "帮吗","中国平安", "平安WiFi","中国平安", "帮吗", "平安万里通","中国平安", "帮吗", "好福利",
			"帮吗", "平安好车主", "帮吗", "平安彩票", "帮吗", "口袋银行", "帮吗", "一账通", "帮吗",
			"平安证券","中国平安", "帮吗", "平安天下通","中国平安", "帮吗", "平安橙子", "帮吗", "平安人寿", "帮么", "陆金所",
			"帮吗", "平安好医生", "帮吗", "平安易贷", "帮吗", "平安财富宝", "帮吗", "壹钱包", "帮吗",
			"平安车险", "帮吗", "平安快付", "帮吗", "保险商城", "帮吗", "金融管家" };

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

		// welcomeImg = (ImageView) this.findViewById(R.id.welcome_img);
		tv1 = (TextView) this.findViewById(R.id.tv_wel1);
		tv2 = (TextView) this.findViewById(R.id.tv_wel2);
		tv1.setVisibility(View.GONE);
		tv2.setVisibility(View.GONE);
	}

	@Override
	protected void doOtherThing() {
		// TODO Auto-generated method stub
		super.doOtherThing();
		AlphaAnimation anima = new AlphaAnimation(0.5f, 1.0f);
		anima.setDuration(4500);// 设置动画显示时间
		// welcomeImg.startAnimation(anima);
		// tv1.startAnimation(anima);
		// tv2.startAnimation(anima);
		anima.setAnimationListener(new AnimationImpl());

		keywordsFlow = (KeywordsFlow) findViewById(R.id.fly_view);
		keywordsFlow.setDuration(3000);
		// 添加
		feedKeywordsFlow(keywordsFlow, keywords);
		keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);// 开启动画
		keywordsFlow.startAnimation(anima);
	}

	/**
	 * @param keywordsFlow
	 * @param arr
	 *            填充的文字
	 */
	private static void feedKeywordsFlow(KeywordsFlow keywordsFlow, String[] arr) {
		Random random = new Random();
		for (int i = 0; i < KeywordsFlow.MAX; i++) {
			int ran = random.nextInt(arr.length);
			String tmp = arr[ran];
			keywordsFlow.feedKeyword(tmp);
		}
	}

	private class AnimationImpl implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {
			// welcomeImg.setBackgroundResource(R.drawable.bg3);
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			skip(); // 动画结束后跳转到别的页面
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

	}

	// private class AnimationImpl2 implements AnimationListener {
	//
	// @Override
	// public void onAnimationStart(Animation animation) {
	// // welcomeImg.setBackgroundResource(R.drawable.bg3);
	// }
	//
	// @Override
	// public void onAnimationEnd(Animation animation) {
	// keywordsFlow.rubKeywords();
	// feedKeywordsFlow(keywordsFlow, keywords);
	// keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);// 开启动画
	// }
	//
	// @Override
	// public void onAnimationRepeat(Animation animation) {
	//
	// }
	//
	// }

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
