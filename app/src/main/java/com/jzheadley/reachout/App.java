package com.jzheadley.reachout;

import android.app.Application;

import com.jzheadley.reachout.models.ModelSingleton;

public class App extends Application {
    private static App instance;

    public static App get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ModelSingleton.getInstance().synchWithDB();
    }


}
