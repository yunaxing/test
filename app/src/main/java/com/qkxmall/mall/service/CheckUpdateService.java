package com.qkxmall.mall.service;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xutils.common.Callback;
import org.xutils.x;

public class CheckUpdateService extends Service {

    private CheckBing checkBing = new CheckBing();

    public CheckUpdateService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return checkBing;
    }

    class CheckBing extends Binder{
        public void check(){
            try {
                PackageInfo packageInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(),0);
                String version = String.valueOf(packageInfo.versionCode);
                String versionName = packageInfo.versionName;
                org.xutils.http.RequestParams req = new org.xutils.http.RequestParams("http://www.qkxmall.com/api/android/check_update.php");
                NameValuePair pairVersion = new BasicNameValuePair("version",packageInfo.versionCode+"");
                NameValuePair pairVersionName = new BasicNameValuePair("versionName",packageInfo.versionName+"");
                req.addBodyParameter("version",packageInfo.versionCode+"");
                req.addBodyParameter("versionName",packageInfo.versionName+"");
                x.http().post(req, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("result",result);
                        if (result.equals("true")){
                        }
                        Log.e("Check","Check");
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });


            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
