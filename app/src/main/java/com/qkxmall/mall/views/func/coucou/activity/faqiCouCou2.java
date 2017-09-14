package com.qkxmall.mall.views.func.coucou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.qkxmall.mall.R;

/**
 * Created by yuna on 2017/8/4.
 */

public class faqiCouCou2 extends Activity {

    private ImageView imgFaQiCouCou;
    private ImageView imgClose;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_faqi_coucou2);
        initView();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
//        p.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的0.3
        p.width = (int) (d.getWidth() * 1); // 宽度设置为屏幕的0.7
        getWindow().setAttributes(p);


    }

    private void initView(){
        imgFaQiCouCou = (ImageView)findViewById(R.id.goon_faqi);
        imgClose = (ImageView)findViewById(R.id.faqi_coucou_close);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgFaQiCouCou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), faqiCouCou3.class).putExtra("imgs",getIntent().getStringExtra("imgs")).putExtra("id", getIntent().getStringExtra("id"))
                        .putExtra("create_pay", getIntent().getStringExtra("create_pay"))
                .putExtra("cid", getIntent().getStringExtra("cid")).putExtra("price",getIntent().getStringExtra("price")));
                finish();
            }
        });
    }
}
