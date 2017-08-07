package com.qiaoxg.myjslogindemo.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by admin on 2017/8/7.
 */

public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
