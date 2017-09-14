package com.qkxmall.mall.views.user;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;

public class WebShowActivity extends AppCompatActivity {

    public static WebShowActivity webShowActivity ;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_show);
        context = WebShowActivity.this;
        webShowActivity = this;
        if (getIntent().getStringExtra("type") != null) {
            String type = getIntent().getStringExtra("type");
            WebFragment webFragment = new WebFragment();
            webFragment.setType(type);
            webFragment.setHandler(new TransectionHandler());
            getSupportFragmentManager().beginTransaction().replace(R.id.webShowRoot, webFragment).commit();
        }else {
            Toast.makeText(context, "参数错误", Toast.LENGTH_SHORT).show();
        }

    }


    private class TransectionHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.CLOUD_A_Q:{
                    WebFragment webFragment = new WebFragment();
                    webFragment.setType(WebFragment.CLOUD_NEW_HELP);
                    webFragment.setHandler(new TransectionHandler());
                    getSupportFragmentManager().beginTransaction().replace(R.id.webShowRoot, webFragment).commit();
                }
                    break;
                case BackgroundTask.HUI_A_Q:{
                    WebFragment webFragment = new WebFragment();
                    webFragment.setType(WebFragment.FQA);
                    webFragment.setHandler(new TransectionHandler());
                    getSupportFragmentManager().beginTransaction().replace(R.id.webShowRoot, webFragment).commit();
                }
                    break;
            }
        }
    }
}
