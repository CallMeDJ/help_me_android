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

	public static void initGlobalRank(Context context){
//init the global raning data

		Bitmap avatar_mid_1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar_mid_1);
		Bitmap avatar_mid_2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar_mid_2);
		Bitmap avatar_mid_3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar_mid_3);
		Bitmap avatar_mid_4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar_mid_4);
		Bitmap avatar_mid_5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar_mid_5);
		Bitmap avatar_mid_6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar_mid_6);
		Bitmap avatar_mid_7 = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar_mid_7);
		Bitmap avatar_mid_8 = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar_mid_8);
		Bitmap avatar_mid_9 = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar_mid_9);
		Bitmap avatar_mid_10 = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar_mid_10);
		Bitmap avatar_mid_11 = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar_mid_11);


			GlobalRankModel model0 = new GlobalRankModel(avatar_mid_1,"五临萌组", 1+"");
			GlobalRankModel model1 = new GlobalRankModel(avatar_mid_2,"何星宏", 2+"");
			GlobalRankModel model2 = new GlobalRankModel(avatar_mid_3,"黄晨杰", 3+"");
			GlobalRankModel model3 = new GlobalRankModel(avatar_mid_4,"黄逸湄", 4+"");
			GlobalRankModel model4 = new GlobalRankModel(avatar_mid_5,"杨钊", 5+"");
			GlobalRankModel model5 = new GlobalRankModel(avatar_mid_6,"刘文龙", 6+"");
			GlobalRankModel model6 = new GlobalRankModel(avatar_mid_7,"徐浩东",7+"");
			GlobalRankModel model7 = new GlobalRankModel(avatar_mid_8,"胡文静", 8+"");
			GlobalRankModel model8 = new GlobalRankModel(avatar_mid_9,"葛宇强", 9+"");
			GlobalRankModel model9 = new GlobalRankModel(avatar_mid_10,"宋培鑫", 10+"");
			GlobalRankModel model10 = new GlobalRankModel(avatar_mid_11,"村上春树", 10+"");





			globalRankList.add(model0);
			globalRankList.add(model1);
			globalRankList.add(model2);
			globalRankList.add(model3);
			globalRankList.add(model4);
			globalRankList.add(model5);
			globalRankList.add(model6);
			globalRankList.add(model7);
			globalRankList.add(model8);
			globalRankList.add(model9);
			globalRankList.add(model10);
	}

	public static void initPhoto(Context context){
		Bitmap meinv_icon_1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.gg1);
		Bitmap meinv_icon_2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.meinv2);
		Bitmap meinv_icon_3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.meinv3);
		Bitmap meinv_icon_4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.meinv4);

		Conversation meinv1 = new Conversation(meinv_icon_1, "柏拉图式","1","你在哪里啊", "剩余23小时");
		Conversation meinv2 = new Conversation(meinv_icon_2, "向秋","2","都说了不是那里咯", "剩余20小时");
		Conversation meinv3 = new Conversation(meinv_icon_3, "凡白","3","不行不行不够不够", "剩余8小时");
		Conversation meinv4 = new Conversation(meinv_icon_4, "从蕾","4", "不要", "剩余6小时");
		conversationList.add(meinv1);
		conversationList.add(meinv2);
		conversationList.add(meinv3);
		conversationList.add(meinv4);



	}


	static{
		

		
		
		achievementData.put("achievement_position_value", "实习生");
		achievementData.put("achievement_global_rank_value", "100000");
		achievementData.put("achievement_current_level", "实习生");
        achievementData.put("achievement_expLiner","100/20000");
        achievementData.put("achievement_next_level", "职业生");
        achievementData.put("achievement_completed_mission","211");
        achievementData.put("achievement_agree_count", "12");
        achievementData.put("achievement_money_count", "￥1231");


//        for(int i = 0 ; i < 5 ; i++){
//            positionList.add("position"+i);
//        }
        
        positionList.add("实习生");
        positionList.add("小职员");
        positionList.add("资深人员");
        positionList.add("总监");
        positionList.add("总裁");
        currentPosition = positionList.get(0);


		

        
        
		for(int i = 0 ; i < 5 ; i ++){
			Chat chat = new Chat(i+"",false, "收到消息"+i, "刚刚", false,i+"");
			//int randomCode = (int)(Math.random()*5) + 1;
			if(i%3 == 0 ){
				chat.setIs_received(true);
			}
			else{
				chat.setIs_received(false);
			}

			chat.setChat_date("18:2"+i);

			if(i == 5){
				chat.setChat_date("刚刚");
			}

			chatList.add(chat);
		}


		
		
	}
	
	
	
	
	
}
