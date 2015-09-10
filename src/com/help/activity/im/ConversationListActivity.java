package com.help.activity.im;

import java.util.ArrayList;
import java.util.List;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.help.R;
import com.help.activity.StaticDatas;
import com.help.base.BaseActivity;
import com.help.listview.XListView;

public class ConversationListActivity extends BaseActivity{

	private XListView conversation_list;
	public List<Conversation> conversationList;
	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.conversation_list_main;
	}

	@Override
	protected void initView(){
		super.initView();
		setTitle("聊天");
		
		
		conversationList = StaticDatas.conversationList;
		conversation_list = (XListView)findViewById(R.id.conversation_list);
		ConversationListAdapter adapter = new ConversationListAdapter(this,conversationList);
		conversation_list.setAdapter(adapter);
		conversation_list.deferNotifyDataSetChanged();
		
		
		//open_conversation_list = (Button)findViewById(R.id.open_conversation_list);
		//open_conversation = (Button)findViewById(R.id.open_conversation);
	}
	
	


	
}
