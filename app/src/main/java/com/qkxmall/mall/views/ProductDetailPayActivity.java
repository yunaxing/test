package com.qkxmall.mall.views;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;

public class ProductDetailPayActivity extends Activity {
    private ImageView backup = null;
    private Button payNow = null;
    private LinearLayout payByRemaining = null;
    private LinearLayout payByAliPay = null;
    private LinearLayout unionPay = null;
    private TextView accountRemaining = null;
    private TextView text1 = null,text3 = null,text4 = null,text5 = null,text6 = null;
    private TextView needPay = null;
    String way = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_pay);
        init();
        needPay.setText(getIntent().getStringExtra("c_price"));
        backup.setOnClickListener(new OnClickListeners());
        payNow.setOnClickListener(new OnClickListeners());
        payByRemaining.setOnClickListener(new OnClickListeners());
        payByAliPay.setOnClickListener(new OnClickListeners());
        unionPay.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.pay_now_backup);
        payNow = (Button) findViewById(R.id.pay);
        payByRemaining = (LinearLayout) findViewById(R.id.remaining);
        payByAliPay = (LinearLayout) findViewById(R.id.alipay);
        unionPay = (LinearLayout) findViewById(R.id.union);
        accountRemaining = (TextView) findViewById(R.id.pay_account_remaining);
        text1 = (TextView) findViewById(R.id.text1);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);
        needPay = (TextView) findViewById(R.id.need_pay);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.pay_now_backup:
                    finish();
                    break;
                case R.id.remaining:
                    payByRemaining.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
                    payByAliPay.setBackgroundColor(getResources().getColor(R.color.white));
                    text1.setTextColor(getResources().getColor(R.color.white));
                    text3.setTextColor(getResources().getColor(R.color.white));
                    text4.setTextColor(getResources().getColor(R.color.white));
                    text5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    accountRemaining.setTextColor(getResources().getColor(R.color.white));
                    unionPay.setBackgroundResource(R.color.white);
                    text6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    way = "账户余额";
                    break;
                case R.id.alipay:
                    payByRemaining.setBackgroundColor(getResources().getColor(R.color.white));
                    accountRemaining.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    payByAliPay.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
                    text5.setTextColor(getResources().getColor(R.color.white));
                    text1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    text3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    text4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    unionPay.setBackgroundResource(R.color.white);
                    text6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    way = "支付宝";
                    break;
                case R.id.union:
                    payByRemaining.setBackgroundColor(getResources().getColor(R.color.white));
                    accountRemaining.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    payByAliPay.setBackgroundColor(getResources().getColor(R.color.white));
                    text5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    text1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    text3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    text4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    unionPay.setBackgroundResource(R.color.cloud_buy_text_color_red);
                    text6.setTextColor(Color.WHITE);
                    way = "银联";
                    break;
                case R.id.pay:
                    if (!way.equals("")){
                        Toast.makeText(ProductDetailPayActivity.this,way+"支付成功!",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(ProductDetailPayActivity.this,"请选择支付方式!",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
