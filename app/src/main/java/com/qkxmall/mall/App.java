package com.qkxmall.mall;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.bugly.crashreport.CrashReport;

import org.xutils.x;

/**
 * Created by Sunshine on 01/16/2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "8e358a0ff1", true);
        //Initialized xUtils3 framework.
        x.Ext.init(this);
        x.Ext.setDebug(true); // set output debug logs.

        //Initialized crash assistant.
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.setCrashHandler(getApplicationContext());

        //Initialized fresco framework.
        Fresco.initialize(getApplicationContext());
    }
}
