package com.help.activity.im;

import java.util.ArrayList;
import java.util.List;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.help.R;
import com.help.activity.StaticDatas;
import com.help.base.BaseActivity;
import com.help.listview.XListView;
import com.help.listview.XListView.IXListViewListener;

public class ConversationListActivity extends BaseActivity implements IXListViewListener{

	private XListView conversation_list;
	public List<Conversation> conversationList;
	private ConversationBoradcastReceiver conversationBoradcastReceiver;
	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.conversation_list_main;
	}

	@Override
	protected void onCreate(Bundle bundle){
		super.onCreate(bundle);
		conversationBoradcastReceiver = new ConversationBoradcastReceiver();
		registerReceiver(conversationBoradcastReceiver, null);
		
	}
	@Override
	protected void onDestroy(){
		super.onDestroy();
		unregisterReceiver(conversationBoradcastReceiver);
	}
	
	
	@Override
	protected void initView(){
		super.initView();
		setTitle("聊天");
		
		
		conversationList = StaticDatas.conversationList;
		conversation_list = (XListView)findViewById(R.id.conversation_list);
		conversation_list.setPullLoadEnable(false);
		ConversationListAdapter adapter = new ConversationListAdapter(this,conversationList);
		conversation_list.setAdapter(adapter);
		conversation_list.deferNotifyDataSetChanged();
		
		
		
		
		//open_conversation_list = (Button)findViewById(R.id.open_conversation_list);
		//open_conversation = (Button)findViewById(R.id.open_conversation);
	}

	
	public void handleNewChat(){
		List<Chat> newChatList = ChatManager.chatList;
	}
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		ChatManager.refleshChat(this);
		
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}
	private class ConversationBoradcastReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			ConversationListActivity.this.handleNewChat();
		}
	}

}


