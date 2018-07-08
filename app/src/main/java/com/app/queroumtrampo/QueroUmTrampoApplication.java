package com.app.queroumtrampo;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class QueroUmTrampoApplication extends Application {
    public static Context mGlobalContext;
    private static Map<String, Object> mGlobalObject;

    public static Context getGlobalContext() {

        return getGlobalContext();
    }

    public static Map<String, Object> getGlobalObject(){
        if(mGlobalObject == null)
            mGlobalObject = new HashMap<String, Object>();

        return mGlobalObject;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mGlobalContext = getApplicationContext();

    }
}
