package com.qkxmall.mall.views.user;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;

public class WebShowActivity2 extends AppCompatActivity {
    public static WebShowActivity2 webShowActivity2 ;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_show2);
        context = WebShowActivity2.this;
        webShowActivity2 = this;
        if (getIntent().getStringExtra("type") != null) {
            String type = getIntent().getStringExtra("type");
            WebFragment2 webFragment2 = new WebFragment2();
            webFragment2.setType(type);
            webFragment2.setHandler(new TypeHandler());
            getSupportFragmentManager().beginTransaction().replace(R.id.webShowRoot2, webFragment2).commit();
        }else {
            Toast.makeText(context, "参数错误", Toast.LENGTH_SHORT).show();
        }

    }


    private class TypeHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.HUI_A_Q:{
                    WebFragment2 webFragment2 = new WebFragment2();
                    webFragment2.setType(WebFragment2.HUI_NEW_HELP);
                    webFragment2.setHandler(new TypeHandler());
                    getSupportFragmentManager().beginTransaction().replace(R.id.webShowRoot2, webFragment2).commit();
                }
                break;
                case BackgroundTask.HUI_Q_A:{
                    WebFragment2 webFragment2 = new WebFragment2();
                    //FQA
                    webFragment2.setType(WebFragment2.FQA);
                    webFragment2.setHandler(new TypeHandler());
                    getSupportFragmentManager().beginTransaction().replace(R.id.webShowRoot2, webFragment2).commit();
                }
                break;
            }
        }
    }
}
