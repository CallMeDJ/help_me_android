package com.help.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticDatas {
	
	//全球排名
public static List<GlobalRankModel> globalRankList = new ArrayList<GlobalRankModel>();
public static Map<String,String> achievementData = new HashMap<String, String>();	
public static String currentPosition = "";
    public static List<String> positionList = new ArrayList<String>();




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


		
		
		
	}
	
	
	
	
	
}
