package com.help.activity.im;

import android.graphics.Bitmap;

/**
 * Created by zhaoyang on 15/9/9.
 */
public class Chat {
    public String chat_id;
    public boolean is_received;
    public String content;
    
    public boolean isIs_received() {
		return is_received;
	}

	public void setIs_received(boolean is_received) {
		this.is_received = is_received;
	}

	public String chat_date;
    public boolean is_deleted;


    public Chat(String chat_id, boolean is_received, String content, String chat_date, boolean is_deleted) {
        this.chat_id = chat_id;
        this.is_received = is_received;
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

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}


}
