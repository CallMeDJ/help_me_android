package com.help.activity.edit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.DataSetObserver;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.help.R;
import com.help.base.BaseActivity;
import com.help.util.ProcessDialogUtil;

public class EditActivity extends BaseActivity implements OnClickListener {

	private EditText xinxi = null;
	private TextView weizhi = null;
	private EditText shuruweizhi = null;

	private TextView jian = null;
	private EditText shuru = null;
	private TextView jia = null;
	private TextView time = null;
	private TextView time1 = null;

	private TextView quxiao, queren;

	private double fenshu = 1.0;

	private Dialog juhua = null;

	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.main_edit;
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
		setTitle("发布信息");

		juhua = new ProcessDialogUtil(EditActivity.this);

		xinxi = (EditText) findViewById(R.id.main_edit_xinxi);
		weizhi = (TextView) findViewById(R.id.main_edit_weizhi);
		shuruweizhi = (EditText) findViewById(R.id.main_edit_shuruweizhi);

		jian = (TextView) findViewById(R.id.main_edit_jian);
		shuru = (EditText) findViewById(R.id.main_edit_shuru);
		jia = (TextView) findViewById(R.id.main_edit_jia);
		time = (TextView) findViewById(R.id.main_edit_shijian);
		time1 = (TextView) findViewById(R.id.main_edit_shijiantv);

		quxiao = (TextView) findViewById(R.id.main_edit_quxiao);
		queren = (TextView) findViewById(R.id.main_edit_queren);

		shuru.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				String newString = shuru.getText().toString();
			}
		});
	}

	@Override
	protected void initViewListener() {
		// TODO Auto-generated method stub
		super.initViewListener();
		titleBarBack.setOnClickListener(this);

		jian.setOnClickListener(this);
		jia.setOnClickListener(this);
		quxiao.setOnClickListener(this);
		queren.setOnClickListener(this);
		time.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.title_bar_back:
			EditActivity.this.finish();
			anim_right_out();
			break;
		case R.id.main_edit_jian:
			jian();
			break;
		case R.id.main_edit_jia:
			jia();
			break;
		case R.id.main_edit_shijian:
			showTime();
			break;
		case R.id.main_edit_queren:
			Toast.makeText(EditActivity.this, "发布成功", 1000).show();
			xinxi.setText("");
			shuruweizhi.setText("");
			shuru.setText("1.0");
			time1.setText("");
			time1.setVisibility(View.GONE);
			break;

		default:
			break;
		}
	}

	// 减
	private void jian() {
		fenshu = Double.valueOf(shuru.getText().toString());
		if (fenshu <= 1) {
			Toast.makeText(EditActivity.this, "至少1元", 1000).show();
		} else {
			fenshu = fenshu - 1;
			shuru.setText(fenshu + "");
		}
	}

	// 加
	private void jia() {
		fenshu = Double.valueOf(shuru.getText().toString());
		fenshu = fenshu + 1;
		shuru.setText(fenshu + "");
	}

	private Dialog dialog = null;

	// 选择时间
	private void showTime() {

		View view = LayoutInflater.from(EditActivity.this).inflate(
				R.layout.xianshishijian, null);

		ListView lv = (ListView) view.findViewById(R.id.xianshilv);
		final String[] strs = { "10分钟", "20分钟", "30分钟", "40分钟", "50分钟", "1小时",
				"2小时", "3小时", "4小时", "5小时", "6小时", "12小时" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.xianshi_item, R.id.xianshi_tv, strs);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				time1.setVisibility(View.VISIBLE);
				time1.setText(strs[position]);
				dialog.dismiss();
			}
		});

		dialog = new AlertDialog.Builder(EditActivity.this).setView(view)
				.create();
		dialog.show();
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
}
