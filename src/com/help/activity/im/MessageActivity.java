package com.help.activity.im;

import java.util.ArrayList;
import java.util.List;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.help.R;
import com.help.activity.StaticDatas;
import com.help.base.BaseActivity;
import com.help.listview.XListView;

public class MessageActivity extends BaseActivity{

	private XListView conversation_list;
	public List<Chat> conversationList = new ArrayList<Chat>();
	@Override
	protected int layoutId() {
		// TODO Auto-generated method stub
		return R.layout.message_main;
	}

	@Override
	protected void initView(){
		super.initView();
		setTitle("聊天");
		Bitmap meinv_icon_1 = BitmapFactory.decodeResource(getResources(), R.drawable.meinv1);
		Bitmap meinv_icon_2 = BitmapFactory.decodeResource(getResources(), R.drawable.meinv2);
		Bitmap meinv_icon_3 = BitmapFactory.decodeResource(getResources(), R.drawable.meinv3);
		Bitmap meinv_icon_4 = BitmapFactory.decodeResource(getResources(), R.drawable.meinv4);
			
		Chat meinv1 = new Chat(meinv_icon_1, "小美", "你欠我的钱还没还呢", "星期一");
		Chat meinv2 = new Chat(meinv_icon_2, "芳芳", "你在哪里吖", "星期六");
		Chat meinv3 = new Chat(meinv_icon_3, "怡茜", "你吃饭了吗", "昨天");
		Chat meinv4 = new Chat(meinv_icon_4, "小雪", "我去洗澡了", "星期三");
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
