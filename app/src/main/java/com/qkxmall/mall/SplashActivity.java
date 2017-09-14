package com.qkxmall.mall;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.qkxmall.mall.views.fragment.FragmentMainActivity;

public class SplashActivity extends Activity {
    private ProgressBar progressBar = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
        setContentView(R.layout.new_pay);
        //初始化用户基本信息
/*        SharedPreferences sharedPreferences = getSharedPreferences("USER_INFO",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UID","1");
        editor.commit();*/

//        volleyDefines.getJSONByVolley(SplashActivity.this, "http://172.27.139.1/haidao/api/android/goods_list.php");
//        progressBar = (ProgressBar) findViewById(R.id.splash_progress);
//        progressBar.setMax(1000);
//        if (isNetworkConnected(getApplicationContext())){
//            new Thread(){
//                @Override
//                public void run() {
//                    try {
//                        for (int i = 0 ;i<1000; i+=100) {
//                            sleep(100);
//                            progressBar.setProgress(i);
//                        }
//                        //在这里做判断是否是第一次登陆如果是第一次登陆就显示向导界面否则就直接跳转首页界面。
//                        // boolean isFristEnter=getSharedPreferences("USER_INFO",MODE_PRIVATE).getBoolean("isFristEnter",false);
////                        if(getSharedPreferences("USER_INFO",MODE_PRIVATE).getBoolean("isFristEnter",true)==true){
////                            startActivity(new Intent(SplashActivity.this,XiangDaoActivity.class));
////                            //添加代码第一次登陆后设置以后登陆直接跳转首页界面不再显示向导界面
////                            getSharedPreferences("USER_INFO",MODE_PRIVATE).edit().putBoolean("isFristEnter",false).commit();
////                        }else{
                            startActivity(new Intent(SplashActivity.this, FragmentMainActivity.class));
//                            SplashActivity.this.finish();
////                        }
//
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }.start();
//        }else {
//            AlertDialog alertDialog = new AlertDialog.Builder(SplashActivity.this).setTitle("网络连接失败").setMessage("请重试").setNegativeButton("退出", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            }).create();
//            alertDialog.show();
//        }
    }

//    public boolean isNetworkConnected(Context context) {
//        if (context != null) {
//            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
//                    .getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
//            if (mNetworkInfo != null) {
//                return mNetworkInfo.isAvailable();
//            }
//        }
//        return false;
//    }

}
