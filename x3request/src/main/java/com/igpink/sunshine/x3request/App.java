package com.igpink.sunshine.x3request;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Sunshine on 01/18/2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
