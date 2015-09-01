package com.help.activity;

import android.widget.Button;
import android.widget.LinearLayout;
import com.example.help.R;
import com.help.base.BaseActivity;

/**
 * Created by YANGZHAO549 on 2015-08-19.
 */
public class PositionActivity extends BaseActivity{
    private Button currentPosition;
    private LinearLayout positionBarContainer;
    @Override
    protected int layoutId() {
        return R.layout.position;
    }
    @Override
    protected void initView() {
        super.initView();

        currentPosition = (Button)findViewById(R.id.position_current_position);
        positionBarContainer = (LinearLayout)findViewById(R.id.position_list_bar);
        currentPosition.setText("当前职位\n"+StaticDatas.currentPosition);
        positionBarContainer.addView(PositionBarUtil.getPositionBarByList(context,StaticDatas.positionList,StaticDatas.currentPosition));


    }

}
