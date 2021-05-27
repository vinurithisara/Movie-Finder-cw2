package com.example.android_cw2;

import android.content.Context;

import androidx.multidex.MultiDexApplication;


public class MyApplication extends MultiDexApplication {
    private static MyApplication mInstance;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        this.setAppContext(getApplicationContext());
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    public void setAppContext(Context mAppContext) {
        this.mAppContext = mAppContext;
    }
}
