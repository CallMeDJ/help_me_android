package com.help.activity.im;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;

import com.help.activity.CommonAPI;
import com.loopj.android.http.ESalesHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * @author Administrator
 * @function 进行聊天信息的管理
 */
public class ChatManager {
	
	public static String NEW_CHAT_COME_ACTION = "com.help.newChatCome";
	public static String NEW_CHAT_COME_NOTIFICATION_ACTION = "com.help.newChatComeNotification";
	
	public static List<Chat> chatList = new ArrayList<Chat>();
	
	/**
	 * @CREATE_TIME : 2015-9-11-下午4:39:30
	 * @Author : Administrator
	 * @ReturnType : void
	 * @Motify_USER Administrator
	 * @Motify_TIME : 2015-9-11-下午4:39:30
	 * @Function_Usage 进行新聊天信息的请求
	 */
	public synchronized static void refleshChat(final Context context){	
		ESalesHttpClient.requestPost(context, CommonAPI.GET_NEW_CHAT, null,new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				try {
					chatList.clear();
					JSONArray datas = response.getJSONArray("data");
					int length = datas.length();
					
					//将收到的json转换为特有的Chat模型
					for(int i = 0 ; i < length ; i ++){
						JSONObject current_data = datas.getJSONObject(i);
						Chat chat = new Chat();
						chat.chat_id = current_data.getString("chat_id");
						chat.content = current_data.getString("content");
						chat.chat_date = current_data.getString("chat_date");
						chat.is_received = current_data.getBoolean("is_received");
						chatList.add(chat);
						Intent intent = new Intent();
						intent.setAction(NEW_CHAT_COME_NOTIFICATION_ACTION);
						context.sendBroadcast(intent);
					}
					Intent newChatListCome = new Intent();
					newChatListCome.setAction(ChatManager.NEW_CHAT_COME_ACTION);
					context.sendBroadcast(newChatListCome);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
}
