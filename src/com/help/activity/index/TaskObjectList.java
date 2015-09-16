package com.help.activity.index;

import java.util.ArrayList;
import java.util.List;

import com.example.help.R;

/**
 * Created by Well_Done on 2015/8/25.
 */
public class TaskObjectList {
	List mTaskList;

	TaskObjectList() {
		init();
	}

	public void init() {
		mTaskList = new ArrayList();

		TaskObject taskobj;

		taskobj = new TaskObject("1","2015-08-11", "取快递111111", "平安大厦1楼","200m","20￥", 22.567637,
				114.101474);
		taskobj.setMbmpresid(R.drawable.gg1);
		taskobj.setMlittlebmpresid(R.drawable.money);
		mTaskList.add(taskobj);

		taskobj = new TaskObject("1","2015-11-12", "取快递22222", "平安大厦","200m","20￥", 22.547637,
				114.121474);
		taskobj.setMbmpresid(R.drawable.meinv1);
		taskobj.setMlittlebmpresid(R.drawable.money);
		mTaskList.add(taskobj);

		taskobj = new TaskObject("1","2015-12-11", "啦啦啦33333", "平安大厦2楼","200m","20￥", 22.557637,
				114.111474);
		taskobj.setMbmpresid(R.drawable.meinv2);
		taskobj.setMlittlebmpresid(R.drawable.money);
		mTaskList.add(taskobj);

		taskobj = new TaskObject("1","2015-11-11", "取快递444", "平安大厦11","200m","20￥", 22.537637,
				114.121474);
		taskobj.setMbmpresid(R.drawable.meinv3);
		taskobj.setMlittlebmpresid(R.drawable.money);
		mTaskList.add(taskobj);

		taskobj = new TaskObject("1","2015-11-11", "求带饭222255", "平安大厦","200m","20￥", 22.577637,
				114.131474);
		taskobj.setMbmpresid(R.drawable.meinv4);
		taskobj.setMlittlebmpresid(R.drawable.money);
		mTaskList.add(taskobj);
	}

	public List getmTaskList() {
		return mTaskList;
	}
}
