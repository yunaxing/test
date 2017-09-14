package com.qkxmall.mall.views.func.coucou.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qkxmall.mall.R;
import com.qkxmall.mall.nets.API;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yuna on 2017/8/29.
 */

public class OrderDetailActivity extends Activity {

    private ImageView imgBack;
    private ImageView imgIcon;
    private TextView tvName;
    private TextView orderSN;
    private TextView tvPrice;
    private TextView tvNum;
    private TextView address;
    private TextView tvPhone;
    private TextView tvCustomer;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_huigou_order_detail);


        Bundle bundle = getIntent().getExtras();
        HashMap<String ,Object> data = (HashMap<String, Object>) bundle.get("data");
        Log.v("data",data+"");
//        bundle.
        initView();
        String img = (String)((HashMap<String ,Object>)((ArrayList)data.get("_goods_info")).get(0)).get("thumb");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!! img" +
                img);
//                    vh.image.setImageURI(Uri.parse(API.ADD + img));
//                    utils.display(vh.image, API.ADD + img);
        Glide.with(OrderDetailActivity.this).load( API.ADD + img).into(imgIcon);
        tvName.setText((String)((HashMap<String ,Object>)((ArrayList)data.get("_goods_info")).get(0)).get("name"));
        orderSN.setText((String)data.get("order_sn"));
        tvPrice.setText((String)data.get("real_amount"));
        address.setText((String)data.get("address"));
        tvCustomer.setText((String)data.get("accept_name"));
        tvPhone.setText((String)data.get("mobile"));
    }
    private void initView(){

        imgBack = (ImageView)this.findViewById(R.id.navigation);
        imgIcon = (ImageView)this.findViewById(R.id.image_title);
        orderSN = (TextView)this.findViewById(R.id.orderSN);
        tvName = (TextView)this.findViewById(R.id.product_name);
        tvPrice = (TextView)this.findViewById(R.id.price);
        tvNum = (TextView)this.findViewById(R.id.num);
        address = (TextView)this.findViewById(R.id.address);
        tvCustomer = (TextView)this.findViewById(R.id.customer_name);
        tvPhone = (TextView)this.findViewById(R.id.customer_phone);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
