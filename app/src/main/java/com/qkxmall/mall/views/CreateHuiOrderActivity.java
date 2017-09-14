package com.qkxmall.mall.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qkxmall.mall.R;

public class CreateHuiOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back);
        toolbar.setSubtitle("创建订单");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateHuiOrderActivity.this.finish();
            }
        });
        setSupportActionBar(toolbar);
        CreateHuiOrderActivityFragment createHuiOrderActivityFragment = new CreateHuiOrderActivityFragment();

        getSupportFragmentManager();

    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.toolbar:{
                    CreateHuiOrderActivity.this.finish();
                }
                    break;
            }
        }
    }
}
