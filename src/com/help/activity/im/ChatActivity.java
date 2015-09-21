package com.help.activity.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.help.R;
import com.help.activity.StaticDatas;
import com.help.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyang on 15/9/9.
 */
public class ChatActivity extends BaseActivity{

    private List<Chat> chatList;
    private Conversation conversation;
    private ListView chat_list;
    private  ChatAdapter chatAdapter;
    public ChatBoradcastReceiver chatBoradcastReceiver;
    
    @Override
    protected int layoutId() {
        return R.layout.chat_main;
    }

    @Override
    protected void initView() {
        super.initView();
        conversation = StaticDatas.conversation;
        chatList = StaticDatas.chatList;
        
        
        setTitle(conversation.user_name);
        chat_list = (ListView)findViewById(R.id.chat_list);
        chatAdapter = new ChatAdapter();
        chat_list.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();
    }
    
	
	
	
	private class ChatAdapter extends BaseAdapter{
		private Integer size = null;
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if(size == null){
				size = chatList.size();
			}
			return size;
		}

		@Override
		public Chat getItem(int position) {
			// TODO Auto-generated method stub
			return chatList.get(size - position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return Integer.parseInt(getItem(position).getChat_id());
		}
		
		public void addChatList(List<Chat> chatList){
			chatList.addAll(chatList);
			chatAdapter.notifyDataSetChanged();
		}

		@Override
		public View getView(int position, View conventView, ViewGroup parentView) {
			// TODO Auto-generated method stub
			Chat chat = getItem(position);
			View messageView = null;
			TextView timeView = null;
			TextView content = null;
			ImageView user_icon = null;
			for(Chat loopChat : chatList){
				if(loopChat.is_received){
					messageView = LayoutInflater.from(ChatActivity.this).inflate(R.layout.message_receiver_left, null);
					timeView = (TextView)messageView.findViewById(R.id.message_time_left);
					content = (TextView)messageView.findViewById(R.id.message_content_left);
					user_icon = (ImageView)messageView.findViewById(R.id.message_user_icon_left);
				}
				else{
					messageView = LayoutInflater.from(ChatActivity.this).inflate(R.layout.message_receiver_right, null);
					timeView = (TextView)messageView.findViewById(R.id.message_time_right);
					content = (TextView)messageView.findViewById(R.id.message_content_right);
					user_icon = (ImageView)messageView.findViewById(R.id.message_user_icon_right);
				}
				
				timeView.setText(chat.getChat_date());
				content.setText(chat.getContent());
				user_icon.setImageBitmap(conversation.icon);
				
			}
			return messageView;
		}
		
	}
	@Override
	protected void onCreate(Bundle bundle){
		super.onCreate(bundle);
		chatBoradcastReceiver = new ChatBoradcastReceiver();
//		registerReceiver(chatBoradcastReceiver, null);
		
	}
	@Override
	protected void onDestroy(){
		super.onDestroy();
		StaticDatas.conversation = null;
	//	unregisterReceiver(chatBoradcastReceiver);
	}
	
	/**
	 * @CREATE_TIME : 2015-9-11-下午4:37:22
	 * @Author : Administrator
	 * @ReturnType : void
	 * @Motify_USER Administrator
	 * @Motify_TIME : 2015-9-11-下午4:37:22
	 * @Function_Usage 处理新收到的聊天记录
	 */
	public void handleNewChat(){
		List<Chat> newChatList = ChatManager.chatList;
		this.chatAdapter.addChatList(chatFilter(newChatList));
	}
	
	/**
	 * @CREATE_TIME : 2015-9-11-下午4:37:52
	 * @Author : Administrator
	 * @ReturnType : List<Chat>
	 * @Motify_USER Administrator
	 * @Motify_TIME : 2015-9-11-下午4:37:52
	 * @Function_Usage 对收到的新聊天记录进行过滤，只需要本次会话的数据
	 */
	public List<Chat> chatFilter(List<Chat> newChatList){
		List<Chat> chatList = new ArrayList<Chat>();
		for(Chat chat : newChatList){
			if(chat.user_id.equals(conversation.user_id)){
				chatList.add(chat);
			}
		}
		return chatList;
	}
	
	private class ChatBoradcastReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
			if(intent.getAction().endsWith(ChatManager.NEW_CHAT_COME_ACTION)){
			ChatActivity.this.handleNewChat();
			}
		}
	}


	
	
	
}
