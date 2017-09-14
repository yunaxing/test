package com.qkxmall.mall.views.sale;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.qkxmall.mall.R;

public class AfterSaleActivity extends Activity {
    private ImageView backup = null;
    private Button submit = null;
    private RadioGroup type = null;
    private RadioButton exchange = null;
    private RadioButton repair = null;
    private RadioButton refund = null;
    private RadioButton postBack = null;
    private EditText message = null;
    String afterType = "";
    String orderSN = "";

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sale);
        init();
        context = AfterSaleActivity.this;
        orderSN = getIntent().getStringExtra("order_sn");
        type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
//                    case R.id.after_sale_exchange:
//                        afterType = exchange.getText().toString().trim();
//                        break;
//                    case R.id.after_sale_repair:
//                        afterType = repair.getText().toString().trim();
//                        break;
//                    case R.id.after_sale_refund:
//                        afterType = refund.getText().toString().trim();
//                        break;
//                    case R.id.after_sale_refund_and_post_back:
//                        afterType = postBack.getText().toString().trim();
//                        break;
                }
            }
        });

        backup.setOnClickListener(new OnClickListeners());
        submit.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.after_sale_backup);
        submit = (Button) findViewById(R.id.after_sale_submit);
        type = (RadioGroup) findViewById(R.id.after_sale_type);
//        exchange = (RadioButton) findViewById(R.id.after_sale_exchange);
//        repair = (RadioButton) findViewById(R.id.after_sale_repair);
//        refund = (RadioButton) findViewById(R.id.after_sale_refund);
//        postBack = (RadioButton) findViewById(R.id.after_sale_refund_and_post_back);
        message = (EditText) findViewById(R.id.after_sale_message);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.after_sale_backup:
                    finish();
                    break;
                case R.id.after_sale_submit:
                    Toast.makeText(getApplicationContext(),
                            "您选择的售后类型是"+afterType+"\n"
                            +"你的附加信息是 "+message.getText().toString().trim()+"\n"
                            +"提交成功!",Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "待添加接口", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
