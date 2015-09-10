package com.help.activity.im;

import android.graphics.Bitmap;

/**
 * Created by zhaoyang on 15/9/9.
 */
public class Chat {
    public String chat_id;
    public String user_name;
    public String content;
    public String chat_date;
    public String is_deleted;


    public Chat(String chat_id, String user_name, String content, String chat_date, String is_deleted) {
        this.chat_id = chat_id;
        this.user_name = user_name;
        this.content = content;
        this.chat_date = chat_date;
        this.is_deleted = is_deleted;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChat_date() {
        return chat_date;
    }

    public void setChat_date(String chat_date) {
        this.chat_date = chat_date;
    }

    public String getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }
}
