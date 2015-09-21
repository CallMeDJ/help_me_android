package com.help.activity.index;

/**
 * Created by Well_Done on 2015/9/15.
 */
public class TaskObjectSingleton {
    private TaskObjectSingleton() {}
    private static TaskObjectList instance=null;
    public static TaskObjectList getInstance() {
        if (instance == null) {
            instance = new TaskObjectList();
        }
        return instance;
    }
}
