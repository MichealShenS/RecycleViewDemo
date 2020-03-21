package com.ssq.recyclerviewdemo;

import android.app.Application;
import android.content.Context;

/**
 * Author : Mr.Shen
 * Date : 2020/3/21 8:25
 * Description :Application
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
