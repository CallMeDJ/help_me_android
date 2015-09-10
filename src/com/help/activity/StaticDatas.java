package com.help.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.help.R;
import com.help.activity.im.Chat;
import com.help.activity.im.Conversation;

public class StaticDatas {
	
	public static Context context;
	//全球排名
	public static List<GlobalRankModel> globalRankList = new ArrayList<GlobalRankModel>();
	public static Map<String,String> achievementData = new HashMap<String, String>();	
	public static String currentPosition = "";
    public static List<String> positionList = new ArrayList<String>();
    public static List<Conversation> conversationList = new ArrayList<Conversation>();
    public static List<Chat> chatList = new ArrayList<Chat>();
    public static Conversation conversation;



	static{
		
		//init the global raning data
		for(int i = 0 ; i < 100 ; i++){
			GlobalRankModel model = new GlobalRankModel(null, "user"+i, i+"");
			globalRankList.add(model);
		}
		
		
		achievementData.put("achievement_position_value", "实习生");
		achievementData.put("achievement_global_rank_value", "100000");
		achievementData.put("achievement_current_level", "实习生");
        achievementData.put("achievement_expLiner","100/20000");
        achievementData.put("achievement_next_level", "职业生");
        achievementData.put("achievement_completed_mission","211");
        achievementData.put("achievement_agree_count", "12");
        achievementData.put("achievement_money_count", "￥1231");


        for(int i = 0 ; i < 5 ; i++){
            positionList.add("position"+i);
        }
        currentPosition = positionList.get(3);


		
		
        Bitmap meinv_icon_1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.gg1);
		Bitmap meinv_icon_2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.meinv2);
		Bitmap meinv_icon_3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.meinv3);
		Bitmap meinv_icon_4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.meinv4);
			
		Conversation meinv1 = new Conversation(meinv_icon_1, "柏拉图式","1","[微信红包]", "剩余23小时");
		Conversation meinv2 = new Conversation(meinv_icon_2, "向秋","2","[微信红包]", "剩余20小时");
		Conversation meinv3 = new Conversation(meinv_icon_3, "凡白","3","[微信红包]", "剩余8小时");
		Conversation meinv4 = new Conversation(meinv_icon_4, "从蕾","4", "[微信红包]", "剩余6小时");
		conversationList.add(meinv1);
		conversationList.add(meinv2);
		conversationList.add(meinv3);
		conversationList.add(meinv4);
        
        
		for(int i = 0 ; i < 50 ; i ++){
			Chat chat = new Chat(i+"",false, "收到消息"+i, "刚刚", false);
			int randomCode = (int)(Math.random()*5);
			if(i%randomCode <= 2){
				chat.setIs_deleted(true);
			}
			chatList.add(chat);
		}
		
		
	}
	
	
	
	
	
}
