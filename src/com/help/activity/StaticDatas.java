package com.help.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.help.R;
import com.help.activity.im.Chat;

public class StaticDatas {
	
	//全球排名
public static List<GlobalRankModel> globalRankList = new ArrayList<GlobalRankModel>();
public static Map<String,String> achievementData = new HashMap<String, String>();	
public static String currentPosition = "";
    public static List<String> positionList = new ArrayList<String>();
    public static List<Chat> conversationList = new ArrayList<Chat>();




	static{
		
		//init the global raning data
		for(int i = 0 ; i < 10 ; i++){
			GlobalRankModel model0 = new GlobalRankModel(null,"五临萌组", (i*10+0)+"");
			GlobalRankModel model1 = new GlobalRankModel(null,"何星宏", (i*10+1)+"");
			GlobalRankModel model2 = new GlobalRankModel(null,"黄晨杰", (i*10+2)+"");
			GlobalRankModel model3 = new GlobalRankModel(null,"黄逸湄", (i*10+3)+"");
			GlobalRankModel model4 = new GlobalRankModel(null,"杨钊", (i*10+4)+"");
			GlobalRankModel model5 = new GlobalRankModel(null,"刘文龙", (i*10+5)+"");
			GlobalRankModel model6 = new GlobalRankModel(null,"徐浩东",(i*10+6)+"");
			GlobalRankModel model7 = new GlobalRankModel(null,"胡文静", (i*10+7)+"");
			GlobalRankModel model8 = new GlobalRankModel(null,"葛宇强", (i*10+8)+"");
			GlobalRankModel model9 = new GlobalRankModel(null,"宋培鑫", (i*10+9)+"");
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
		}
		
		
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
        currentPosition = positionList.get(3);


		
		
		
	}
	
	
	
	
	
}
