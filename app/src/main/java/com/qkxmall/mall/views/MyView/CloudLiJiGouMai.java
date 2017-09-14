package com.qkxmall.mall.views.MyView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.address.AddressSelectActivity;

/**
 * Created by Administrator on 2016/4/23.
 */
public class CloudLiJiGouMai extends Activity implements View.OnClickListener {
    private SimpleDraweeView image;//商品图片
    private TextView price;//商品单价
    private TextView number;//商品数量

    private LinearLayout Mll;//收货地址
    private TextView name;//收货人
    private TextView ress;//收货地址

    private ImageView imageView;//返回键
    private TextView textView;//立即购买
    private int mPrice;
    private int mNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_buy_choice_sth_page2);
        init();
        initData();
    }

    public void init() {
        image = (SimpleDraweeView) findViewById(R.id.picture);
        price = (TextView) findViewById(R.id.price);
        number = (TextView) findViewById(R.id.bean);
        Mll = (LinearLayout) findViewById(R.id.addressLine);
        name = (TextView) findViewById(R.id.name);
        ress = (TextView) findViewById(R.id.address);
        imageView = (ImageView) findViewById(R.id.navigation);
        textView = (TextView) findViewById(R.id.buy);

        imageView.setOnClickListener(this);
        textView.setOnClickListener(this);
        Mll.setOnClickListener(this);


//        mPrice = Integer.getInteger(this.getIntent().getStringExtra("c_price"));
//        mNumber = Integer.getInteger(this.getIntent().getStringExtra("num"));
        System.out.println("=================单价：" + this.getIntent().getStringExtra("c_price"));
        System.out.println("=================数量：" + this.getIntent().getStringExtra("num"));
    }

    public void initData() {
        price.setText(this.getIntent().getStringExtra("c_price"));
        number.setText(this.getIntent().getStringExtra("num"));
        //设置图片
//        picture.setImageURI(Uri.parse(API.ADD + images[0]));
        String[] arr = this.getIntent().getStringExtra("img").split(",");
        image.setImageURI(Uri.parse(API.ADD + arr[0]));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addressLine: //收货地址
                Intent intent = new Intent(CloudLiJiGouMai.this, AddressSelectActivity.class);
                startActivityForResult(intent,100);

                break;
            case R.id.navigation: //返回键
                   finish();
                break;
            case R.id.buy: //立即购买


                break;

        }


    }
}
