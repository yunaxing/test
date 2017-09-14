package com.qkxmall.mall.views.func.coucou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qkxmall.mall.R;

/**
 * Created by yuna on 2017/8/27.
 */

public class QianBaoKaiXinDouPageActivity extends Activity {

    private ImageView imgFaQiCouCou;
    private ImageView imgClose;
    private LinearLayout imgBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qianbao_kaixindou_page);
        initView();

    }

    private void initView(){
        imgBack = (LinearLayout) this.findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
