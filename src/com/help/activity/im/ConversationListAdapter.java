package com.help.activity.im;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.help.R;

public class ConversationListAdapter extends BaseAdapter{
	Context context;
	List<Chat> conversationList;
public ConversationListAdapter(Context context,List<Chat> conversationList){
	this.context = context;
	this.conversationList = conversationList;
}

@Override
public int getCount() {
	// TODO Auto-generated method stub
	return conversationList.size();
}

@Override
public Chat getItem(int position) {
	// TODO Auto-generated method stub
	return conversationList.get(position);
}

@Override
public long getItemId(int position) {
	// TODO Auto-generated method stub
	return position;
}

@Override
public View getView(int position, View view, ViewGroup parentView) {
	View conversation_item = LayoutInflater.from(context).inflate(R.layout.conversation_list_item, null);
	Chat chat = getItem(position);
	ImageView icon = (ImageView) conversation_item.findViewById(R.id.conversation_user_icon);
	TextView user_name = (TextView)conversation_item.findViewById(R.id.conversation_user_name);
	TextView content = (TextView)conversation_item.findViewById(R.id.conversation_content);
	TextView time = (TextView)conversation_item.findViewById(R.id.conversation_time);
	
	icon.setImageBitmap(chat.icon);
	user_name.setText(chat.user_name);
	content.setText(chat.content);
	time.setText(chat.date);
	
	return conversation_item;
}

}
