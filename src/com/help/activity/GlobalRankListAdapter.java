package com.help.activity;

import java.util.List;

import android.graphics.Bitmap;
import com.example.help.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GlobalRankListAdapter extends BaseAdapter{

	
	private List<GlobalRankModel> list;
	private Context context;
	
	public GlobalRankListAdapter(Context context){
		this.context = context;
	}
	
	public  void setDataList(List<GlobalRankModel> list){
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public GlobalRankModel getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = LayoutInflater.from(context).inflate(R.layout.global_rank_item, null);
		
		ImageView icon = (ImageView) view.findViewById(R.id.global_header_icon);
		TextView username = (TextView) view.findViewById(R.id.global_user_name_text);
		TextView ranking = (TextView) view.findViewById(R.id.global_user_ranking);
		GlobalRankModel data = getItem(position);
		Bitmap icon_map = data.getIcon();
		if(icon_map != null) {
			icon.setImageBitmap(icon_map);
		}

		username.setText(data.getUserName());
		ranking.setText(data.getRanking());
		
		return view;
	}


}
