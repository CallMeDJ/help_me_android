package com.help.activity.im;

import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.help.R;
import com.help.activity.StaticDatas;

public class ConversationListAdapter extends BaseAdapter{
	Context context;
	List<Conversation> conversationList;
public ConversationListAdapter(Context context,List<Conversation> conversationList){
	this.context = context;
	this.conversationList = conversationList;
}

public void addConversationList(List<Conversation> newConversationList){
	conversationList.addAll(newConversationList);
	this.notifyDataSetChanged();
}

public void refleshConversationList(List<Conversation> newConversationList){
	conversationList = newConversationList;
	this.notifyDataSetChanged();
}

@Override
public int getCount() {
	// TODO Auto-generated method stub
	return conversationList.size();
}

@Override
public Conversation getItem(int position) {
	// TODO Auto-generated method stub
	return conversationList.get(position);
}

@Override
public long getItemId(int position) {
	// TODO Auto-generated method stub
	return position;
}

@Override
public View getView(final int position, View view, ViewGroup parentView) {
	View conversation_item = LayoutInflater.from(context).inflate(R.layout.conversation_list_item, null);
	Conversation conversation = getItem(position);
	ImageView icon = (ImageView) conversation_item.findViewById(R.id.conversation_user_icon);
	TextView user_name = (TextView)conversation_item.findViewById(R.id.conversation_user_name);
	TextView content = (TextView)conversation_item.findViewById(R.id.conversation_content);
	TextView time = (TextView)conversation_item.findViewById(R.id.conversation_time);
	
	icon.setImageBitmap(conversation.icon);
	user_name.setText(conversation.user_name);
	content.setText(conversation.content);
	time.setText(conversation.date);

	View.OnClickListener starConversation = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			performClick(position);
			ConversationListAdapter.this.notifyDataSetChanged();

		}
	};

	conversation_item.setOnClickListener(starConversation);
	return conversation_item;
}

	public void performClick(int position){
		Conversation conversation = getItem(position);
		Intent i = new Intent(context,ChatActivity.class);
		StaticDatas.conversation = conversation;
		context.startActivity(i);

	}




}
