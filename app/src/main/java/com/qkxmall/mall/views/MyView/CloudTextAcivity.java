package com.qkxmall.mall.views.MyView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qkxmall.mall.R;

/**
 * Created by Administrator on 2016/5/17.
 */
public class CloudTextAcivity extends Activity {
     ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.text_activity2);
        imageView= (ImageView) findViewById(R.id.after_sale_backup);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
