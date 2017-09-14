package com.qkxmall.mall;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;


/**
 * Created by Sunshine on 01/19/2016.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler{

    private Context context;

    private static CrashHandler crashHandler = new CrashHandler();

    public static CrashHandler getInstance(){
        return crashHandler;
    }

    public void setCrashHandler(Context context){
        this.context = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //1 保存错误日志到SD卡
        Utils.saveCrashInfoToSDCard(context, ex);
        //2 提示Crash信息
        showCrashTipToast();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        //3 退出应用
        System.exit(0);
    }

    private void showCrashTipToast() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "正在请求网络请稍等", Toast.LENGTH_LONG).show();
//                AlertDialog alertDialog  = new AlertDialog.Builder(context).setTitle("崩溃警告").setMessage("程序异常即将退出").create();
//                alertDialog.show();
                Looper.loop();
            }
        }).start();
    }

}
