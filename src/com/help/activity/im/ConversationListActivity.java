package com.help.activity.im;

import java.util.List;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.help.R;
import com.help.activity.StaticDatas;
import com.help.base.BaseActivity;
import com.help.listview.XListView;
import com.help.listview.XListView.IXListViewListener;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class ConversationListActivity extends BaseActivity implements IXListViewListener{

	private XListView conversation_list;
	private List<Conversation> conversationList;
	private ConversationBoradcastReceiver conversationBoradcastReceiver;
	private ConversationListAdapter conversationListAdapter;
	
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
	    conversationListAdapter = new ConversationListAdapter(this,conversationList);
		conversation_list.setAdapter(conversationListAdapter);
		conversation_list.deferNotifyDataSetChanged();
		
	}



	/**
	 * @CREATE_TIME : 2015-9-11-上午9:41:16
	 * @Author : Administrator
	 * @ReturnType : void
	 * @Motify_USER Administrator
	 * @Motify_TIME : 2015-9-11-上午9:41:16
	 * @Function_Usage : 处理新的聊天列表
	 */
	public void handleNewChat(){
		List<Chat> newChatList = ChatManager.chatList;
		for(Chat chat : newChatList){
			
		}
	}
	
	@Override
	public void onRefresh() {
		ChatManager.refleshChat(this);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @author Administrator
	 * 
	 *
	 */
	private class ConversationBoradcastReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().endsWith(ChatManager.NEW_CHAT_COME_ACTION)){
				ConversationListActivity.this.handleNewChat();
			}
		}	
	}

}


