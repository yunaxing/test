package com.qkxmall.mall.views.MyView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkxmall.mall.R;

/**
 * Created by Administrator on 2016/5/17.
 */
public class TextActivity extends Activity {

    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_activity);
        textView= (TextView) findViewById(R.id.text_phone);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:01057129469"));
                startActivity(intent);
            }
        });

        imageView= (ImageView) findViewById(R.id.after_sale_backup);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
