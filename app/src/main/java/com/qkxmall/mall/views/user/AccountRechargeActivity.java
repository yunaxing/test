package com.qkxmall.mall.views.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.pay.paypage.ALiPayPayActivity;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class AccountRechargeActivity extends Activity {
    Context context;
    Activity mActivity=this;
    private ImageView navigation = null;
    private TextView accountRemaining = null;
    private Button recharge50 = null;
    private Button recharge100 = null;
    private Button recharge200 = null;
    private Button recharge500 = null;
    private Button recharge1000 = null;
    private EditText rechargeOther = null;
    private TextView rechargeAmount = null;
    private RadioGroup rechargeWay = null;
    private RadioButton rechargeByAliPay = null;
    private RadioButton rechargeByUnionPay = null;
    private Button recharge = null;
    private boolean use = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_recharge);
        init();
        context = AccountRechargeActivity.this;

        rechargeOther.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                rechargeAmount.setText(rechargeOther.getText().toString().trim());
            }
        });

        navigation.setOnClickListener(new OnClick());
        recharge.setOnClickListener(new OnClick());
        recharge50.setOnClickListener(new OnClick());
        recharge100.setOnClickListener(new OnClick());
        recharge200.setOnClickListener(new OnClick());
        recharge500.setOnClickListener(new OnClick());
        recharge1000.setOnClickListener(new OnClick());
        recharge50.setOnClickListener(new OnClick());
        rechargeOther.setOnClickListener(new OnClick());
        rechargeWay.setOnCheckedChangeListener(new CheckedChange());
    }

    private void init() {
        navigation = (ImageView) findViewById(R.id.navigation);
        recharge50 = (Button) findViewById(R.id.account_recharge_50);
        recharge100 = (Button) findViewById(R.id.account_recharge_100);
        recharge200 = (Button) findViewById(R.id.account_recharge_200);
        recharge500 = (Button) findViewById(R.id.account_recharge_500);
        recharge1000 = (Button) findViewById(R.id.account_recharge_1000);
        rechargeOther = (EditText) findViewById(R.id.account_recharge_other_amount);
        rechargeAmount = (TextView) findViewById(R.id.account_recharge_recharge_amount);
        rechargeWay = (RadioGroup) findViewById(R.id.account_recharge_way);
        rechargeByAliPay = (RadioButton) findViewById(R.id.account_recharge_a_li_pay);
        rechargeByUnionPay = (RadioButton) findViewById(R.id.account_recharge_union_pay);
        recharge = (Button) findViewById(R.id.account_recharge_recharge);
    }

    public static boolean bl = false;

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.navigation:
                    AccountRechargeActivity.this.finish();

                    break;
                case R.id.account_recharge_recharge:
                 final     String orderNo = getOutTradeNo();
//                 final String  orderNo = getIntent().getStringExtra("ordersn");
                    System.out.println("!!!!!!!!!!!!! 拿到的的订单号2：" + orderNo);
                    if (bl) {
                        if (use) {
                            Toast.makeText(context, "只用支付宝支付", Toast.LENGTH_SHORT).show();
                            LOD lod = new LOD(context);

                            System.out.println("++++++++++++++++++++++++++订单号：" + orderNo);
                            String url = API.ACCOUNT_RECHARGE +
                                    "&uid=" + lod.get("USER_INFO", "UID", "") +
                                    "&money=" + rechargeAmount.getText().toString().trim() +
                                    "&orderno=" + orderNo;
                            Handler handler = new Handler() {

                                @Override
                                public void handleMessage(Message msg) {
                                    switch (msg.what) {
                                        case BackgroundTask.SUCCESS: {
                                            Bundle bundle = msg.getData();
                                            if (bundle != null) {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(bundle
                                                            .getString("result"));
                                                    if (jsonObject.getBoolean("flag")) {
                                                        Intent intent = new Intent(context,
                                                                ALiPayPayActivity.class);
                                                        intent.putExtra("orderno", orderNo);
                                                        System.out.println("!!!!!!!!!!!!! " +
                                                                "传递的订单号2.1：" + orderNo);
                                                        intent.putExtra("name", "账号充值");
                                                        intent.putExtra("info", "充值金额：" +
                                                                rechargeAmount.getText().toString
                                                                        ().trim());
                                                        intent.putExtra("c_price", rechargeAmount
                                                                .getText().toString().trim());
                                                        intent.putExtra("chongzhi",true);

                                                        System.out.println("！！！！！！！！！！账户充值页面");
                                                        startActivity(intent);
                                                         mActivity.finish();
                                                    }
                                                } catch (JSONException e) {

                                                    e.printStackTrace();
                                                    Toast.makeText(context, "数据解析失败", Toast
                                                            .LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                        break;
                                        case BackgroundTask.FAILURE: {
                                            Toast.makeText(context, "创建失败", Toast.LENGTH_SHORT)
                                                    .show();
                                        }
                                        break;
                                    }
                                }
                            };
                            BackgroundTask backgroundTask = new BackgroundTask(context, url,
                                    handler);
                            backgroundTask.doInBackground();


                        } else {
                            Toast.makeText(context, "使用银联支付", Toast.LENGTH_SHORT).show();
                            Toast.makeText(AccountRechargeActivity.this, "缺少充值接口", Toast
                                    .LENGTH_SHORT).show();

                        }
                    } else {
                        Intent intent = new Intent(context, ALiPayPayActivity.class);
                        intent.putExtra("orderno", orderNo);
                        System.out.println("!!!!!!!!!!!!! 传递的订单号2：" + orderNo);
                        intent.putExtra("name", "账号充值");
                        intent.putExtra("info", "充值金额：" + rechargeAmount.getText().toString()
                                .trim());

                        intent.putExtra("c_price", rechargeAmount.getText()
                                .toString().trim());
                        intent.putExtra("bls",true);
                        intent.putExtra("chongzhi",true);
                        startActivity(intent);

                        mActivity.finish();
                    }
//                    AccountRechargeActivity.this.finish();
//                    mActivity.finish();

                    break;
                case R.id.account_recharge_50:
                    rechargeAmount.setText("50.00");
                    recharge50.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_red_button_background));
                    recharge50.setTextColor(getResources().getColor(R.color.white));
                    recharge100.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge100.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge200.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge200.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge500.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge500.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge1000.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge1000.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    rechargeOther.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    rechargeOther.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    break;
                case R.id.account_recharge_100:
                    rechargeAmount.setText("100.00");
                    recharge50.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge50.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge100.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_red_button_background));
                    recharge100.setTextColor(getResources().getColor(R.color.white));
                    recharge200.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge200.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge500.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge500.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge1000.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge1000.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    rechargeOther.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    rechargeOther.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    break;
                case R.id.account_recharge_200:
                    rechargeAmount.setText("200.00");
                    recharge50.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge50.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge100.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge100.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge200.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_red_button_background));
                    recharge200.setTextColor(getResources().getColor(R.color.white));
                    recharge500.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge500.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge1000.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge1000.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    rechargeOther.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    rechargeOther.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    break;
                case R.id.account_recharge_500:
                    rechargeAmount.setText("500.00");
                    recharge50.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge50.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge100.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge100.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge200.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge200.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge500.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_red_button_background));
                    recharge500.setTextColor(getResources().getColor(R.color.white));
                    recharge1000.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge1000.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    rechargeOther.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    rechargeOther.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    break;
                case R.id.account_recharge_1000:
                    rechargeAmount.setText("1000.00");
                    recharge50.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge50.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge100.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge100.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge200.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge200.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge500.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge500.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge1000.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_red_button_background));
                    recharge1000.setTextColor(getResources().getColor(R.color.white));
                    rechargeOther.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    rechargeOther.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    break;
                case R.id.account_recharge_other_amount:
                    recharge50.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge50.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge100.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge100.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge200.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge200.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge500.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge500.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    recharge1000.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_white_button_background));
                    recharge1000.setTextColor(getResources().getColor(R.color
                            .grid_view_background_grey_500));
                    rechargeOther.setBackgroundDrawable(getResources().getDrawable(R.drawable
                            .radius_red_button_background));
                    rechargeOther.setTextColor(getResources().getColor(R.color.white));
                    break;
            }
        }
    }

    private class CheckedChange implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.account_recharge_a_li_pay: {
                    use = true;
                }
                break;
                case R.id.account_recharge_union_pay: {
                    use = false;
                }
                break;
            }
        }

    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    public String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        String s = (new SimpleDateFormat("", Locale.getDefault())).format(new Date());

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }
}
