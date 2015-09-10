package com.help.activity.im;

import java.util.ArrayList;
import java.util.List;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.help.R;
import com.help.base.BaseActivity;
import com.help.listview.XListView;

public class ConversationListActivity extends BaseActivity{

	private XListView conversation_list;
	public List<Conversation> conversationList = new ArrayList<Conversation>();
	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.conversation_list_main;
	}

	@Override
	protected void initView(){
		super.initView();
		setTitle("聊天");
		Bitmap meinv_icon_1 = BitmapFactory.decodeResource(getResources(), R.drawable.meinv1);
		Bitmap meinv_icon_2 = BitmapFactory.decodeResource(getResources(), R.drawable.meinv2);
		Bitmap meinv_icon_3 = BitmapFactory.decodeResource(getResources(), R.drawable.meinv3);
		Bitmap meinv_icon_4 = BitmapFactory.decodeResource(getResources(), R.drawable.meinv4);
			
		Conversation meinv1 = new Conversation(meinv_icon_1, "怀薇", "[微信红包]", "剩余23小时");
		Conversation meinv2 = new Conversation(meinv_icon_2, "向秋", "[微信红包]", "剩余20小时");
		Conversation meinv3 = new Conversation(meinv_icon_3, "凡白", "[微信红包]", "剩余8小时");
		Conversation meinv4 = new Conversation(meinv_icon_4, "从蕾", "[微信红包]", "剩余6小时");
		conversationList.add(meinv1);
		conversationList.add(meinv2);
		conversationList.add(meinv3);
		conversationList.add(meinv4);
		
		
		conversation_list = (XListView)findViewById(R.id.conversation_list);
		ConversationListAdapter adapter = new ConversationListAdapter(this,conversationList);
		conversation_list.setAdapter(adapter);
		conversation_list.deferNotifyDataSetChanged();
		
		
		//open_conversation_list = (Button)findViewById(R.id.open_conversation_list);
		//open_conversation = (Button)findViewById(R.id.open_conversation);
	
		
		
	}
	

	
}
