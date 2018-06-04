package com.app.myapp;

import android.app.Application;
import android.content.Context;

public class QueroUmTrampoApplication extends Application {
    public static Context mGlobalContext;

    @Override
    public void onCreate(){
        super.onCreate();

        mGlobalContext = getApplicationContext();

    }

    public static Context getGlobalContext(){

        return getGlobalContext();
    }
}
