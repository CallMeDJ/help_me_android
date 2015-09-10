package com.help.activity.im;

import android.content.Intent;
import com.example.help.R;
import com.help.base.BaseActivity;

import java.util.List;

/**
 * Created by zhaoyang on 15/9/9.
 */
public class ChatActivity extends BaseActivity{

    private String userName;
    private List<Conversation> conversationList;

    @Override
    protected int layoutId() {
        return R.layout.chat_main;
    }

    @Override
    protected void initView() {
        super.initView();

        Intent i = getIntent();
        userName = i.getStringExtra("user_name");
        setTitle(userName);


    }


}
