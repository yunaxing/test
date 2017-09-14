package com.qkxmall.mall.views.hui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.pay.paypage.ALiPayPayActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qkxmall.mall.R;
import com.qkxmall.mall.Utils;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.func.webview.GetMethodActivity;
import com.qkxmall.mall.views.hui.order.AllHuiOrderActivity;
import com.qkxmall.mall.views.user.AccountHapyBeanGetWayActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class HuiBuyPayActivity extends Activity {
    /*
    * ①商品信息
    *   1、商品名称
    *   2、商品价格
    *   3、商品描述
    *   4、可用开心豆数量
    *   5、数据表ID
    *
    *
    * */
    //参数
    String proName = "";
    String orderPrice = "";
    String proDescribe = "";
    String proKainXinDouBeUse = "";
    String orderSN = "";
    String tabID = "";

    float orderFinalPrice;


    public static final int PAY_ALIPAY = 0x001201;
    public static final int PAY_REMAINING = 0x001202;

    Context context;
//    private TextView fright = null;
//    private TextView shouldPay = null;
    private Button payNow = null;
    private ImageView navigation = null;

    private TextView kaixindouRemain = null;
    private TextView kaixindouCouldUse = null;
//    private EditText kaixindouWillUse = null;

    private LinearLayout payAlipay = null;
    private LinearLayout payRemaining = null;
    private LinearLayout payKaixindou;
    private ImageView imgRemain;
    private ImageView imgAli;
    private ImageView imgKaixin;
    private int payWay = -1;
    private SimpleDraweeView imgTitle;
    private TextView tvGuige;
    private TextView tvName;

    String accountRemaining = "0";

//    private Button getWay = null;
//
//    private ImageView add = null;
//    private ImageView push = null;
    private RelativeLayout mRelativeLayout;

    String price = "";
    String keys = "";
    int kaiXinDou = 0;
    int num = 1;
    double prics;
    public String orderid;
    public String result2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            System.out.println("要传入的int " + Integer.parseInt(kaixindouWillUse.getText().toString().trim()));
//            double newPrice = prics - Integer.parseInt(kaixindouWillUse.getText().toString().trim());
//            System.out.println("!!!!!!!!!!!!!! prics" + prics + "-" + Integer.parseInt(kaixindouWillUse.getText().toString().trim()) + "=" + newPrice);
//            DecimalFormat df = new DecimalFormat("#.00");
//
//            shouldPay.setText(df.format(newPrice) + "");
//            int num=Integer.parseInt(kaixindouWillUse.getText().toString().trim());
//            addPressThe(num);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hui_buy_pay);
        setContentView(R.layout.new_pay);
        init();
        if (getIntent().getBooleanExtra("boolean", false)) {
            mRelativeLayout.setVisibility(View.INVISIBLE);
        }
        //可用开心豆输入框监听 确保在正确数值内
//        kaixindouWillUse.addTextChangedListener(new TextWatcher() {
//            int num = 0;
//            int num1 = 0;
//            int num2 = 0;
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                System.out.println("当前的" + s.toString().trim());
//                int maxAdd = Integer.parseInt(kaixindouCouldUse.getText().toString().trim());
////                num2 = Integer.parseInt(s.toString().trim());
//                int i = -1;
//                String Ms = s.toString().trim();
//                if (TextUtils.isEmpty(Ms)) {//输入框没值时设置成0
//                    kaixindouWillUse.setText(0 + "");
//                }
//                if (s.toString() != "") {
//                    i = Integer.parseInt(0 + s.toString());
//                }
//                if (s.toString().length() >= 1) {
//                    if (s.toString().charAt(0) == '0') {
//                        System.out.println("!!!!!!!!第一位是0 ");
//
//                    }
//
//                }
//                if (i != -1) {//输入值超过可用开心豆最大值时 默认为最大值
//                    if (i > maxAdd) {
//                        kaixindouWillUse.setText(maxAdd + "");
//                    }
//
//                }
//                //在handel中延迟进行
//                if (!kaixindouWillUse.getText().toString().trim().equals("")) {
//                    Message message = new Message();
//                    handler.sendMessage(message);
//                }
//
//
////                addPressThe(Integer.parseInt(s.toString().trim()));
////                float f = 0;
////
////                if (num2 > num1) {
////                    f = Float.parseFloat(shouldPay.getText().toString().trim());
////                    num = num2 - num1;
////                    shouldPay.setText(f - num + "");
//////                        System.out.println("增加 f：" + f + "num:" + num+"num2:"+num2+"-num1"+num1);
////                } else if (num2 < num1) {
////                    num = num1 - num2;
////                    f = Float.parseFloat(shouldPay.getText().toString().trim());
////                    shouldPay.setText(f + num + "");
//////                        System.out.println("减少 f：" + f + "num:" + num+"num1:"+num1+"-num2"+num2);
////                }
//            }
//
//
//        });

        if (getIntent() != null) {

        }


        if (getIntent().getStringExtra("orderid") != null) {
            orderid = getIntent().getStringExtra("orderid");
        }

        context = HuiBuyPayActivity.this;
        if (getIntent().getStringExtra("c_price") != null)
            price = getIntent().getStringExtra("c_price");
        if (getIntent().getStringExtra("key") != null)
            keys = getIntent().getStringExtra("key");
        if (getIntent().getIntExtra("kaixindou", 0) != 0) {
            kaiXinDou = getIntent().getIntExtra("kaixindou", 0);
            System.out.println("!!!!!!!!!!!!!!!!! 开心豆" + kaiXinDou);
        }
        if (getIntent().hasExtra("orderno")) {
            orderSN = getIntent().getStringExtra("orderno");
        }
        if (getIntent().getIntExtra("num", 0) != 0)
            num = getIntent().getIntExtra("num", 0);

//        shouldPay.setText(price);
        kaixindouCouldUse.setText(String.valueOf(kaiXinDou));
        if (!price.equals("")) {
            prics = Double.parseDouble(price);
        }

        //查询订单详情。
        if (getIntent() != null) {
            if (getIntent().getStringExtra("orderid") != null) {
                tabID = getIntent().getStringExtra("orderid");
                LOD lod = new LOD(context);
                String orderDetailAddress = API.HUI_ORDER_DETAIL + "&orderid=" + tabID + "&uid="
                        + lod.get("USER_INFO", "UID", "'");
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("获取中，请稍候...");
                progressDialog.show();
                Log.e("惠购商品详情，地址", orderDetailAddress);
                OrderDetailHandler orderDetailHandler = new OrderDetailHandler(progressDialog);
                BackgroundTask backgroundTask = new BackgroundTask(context, orderDetailAddress,
                        orderDetailHandler);
                backgroundTask.doInBackground();
            }
        }


        //获取用户账户开心豆数量
        LOD lod = new LOD(context);
        String urlInfo = API.USER_INFO + "&uid=" + lod.get("USER_INFO", "UID", "'");
        LoadMyKaiXindou loadMyKaiXindou = new LoadMyKaiXindou();
        Log.e("userinfo", urlInfo);
        BackgroundTask backgroundTask = new BackgroundTask(context, urlInfo, loadMyKaiXindou);
        backgroundTask.doInBackground();

//        String address = "http://www.qkxmall.com/index.php?m=api&c=member&a=login" +
//                "&phoen=" + mID + "&pwd=" + ed2.getText().toString().trim();
//        result2=validation(address);
//        System.out.println("!!!!!!!!!! address" + address);


        //Set default number
        //Set components click event
        payNow.setOnClickListener(new OnClick());
        navigation.setOnClickListener(new OnClick());
//        add.setOnClickListener(new OnClick());
        payAlipay.setOnClickListener(new OnClick());
        payRemaining.setOnClickListener(new OnClick());
        payKaixindou.setOnClickListener(new OnClick());
//        getWay.setOnClickListener(new OnClick());
//        push.setOnClickListener(new OnClick());

    }

    private void init() {
//        fright = (TextView) findViewById(R.id.hui_buy_pay_freight);
//        shouldPay = (TextView) findViewById(R.id.price);
        payNow = (Button) findViewById(R.id.hui_buy_pay_pay_now);
        navigation = (ImageView) findViewById(R.id.navigation);
        kaixindouCouldUse = (TextView) findViewById(R.id.could_use);
        kaixindouRemain = (TextView) findViewById(R.id.has);
        tvGuige = (TextView)findViewById(R.id.tv_guige);
        tvName = (TextView)findViewById(R.id.tv_name);
        imgTitle = (SimpleDraweeView)findViewById(R.id.img_title);
//        String[] arr = this.getIntent().getStringExtra("img").split(",");
        imgTitle.setImageURI(Uri.parse(getIntent().getStringExtra("img")));
        tvName.setText(getIntent().getStringExtra("name"));
        tvGuige.setText(getIntent().getStringExtra("guige"));
//        kaixindouWillUse = (EditText) findViewById(R.id.use_number);
//        add = (ImageView) findViewById(R.id.add);
//        push = (ImageView) findViewById(R.id.push);
//        getWay = (Button) findViewById(R.id.getway);

        imgAli = (ImageView)findViewById(R.id.imageAli);
        imgRemain = (ImageView)findViewById(R.id.imagRemain);
        imgKaixin = (ImageView)findViewById(R.id.imagKaixinDou);
        payAlipay = (LinearLayout) findViewById(R.id.payAlipay);
        payRemaining = (LinearLayout) findViewById(R.id.payRemaining);
        payKaixindou = (LinearLayout) findViewById(R.id.LL_kaixindou_jifen);
        //设置当前可用开心豆

        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl_relative);

    }

    private void addPressThe(int num) {
//        int kaixindou = Integer.parseInt(kaixindouWillUse.getText().toString().trim()) + num;
//        kaixindouWillUse.setText(kaixindou + "");
//
//        double newPrice = prics - Integer.parseInt(kaixindouWillUse.getText().toString().trim());
//        System.out.println("!!!!!!!!!!!!!! prics" + prics + "-" + Integer.parseInt
//                (kaixindouWillUse.getText().toString().trim()) + "=" + newPrice);
//        DecimalFormat df = new DecimalFormat("#.00");
//
//        shouldPay.setText(df.format(newPrice) + "");
    }

    private void pushPressThe(int num) {
        //按下增加键时 1.使用开心豆数量加1  2.实付款=原价-使用开心豆的数量
//        int kaixindou = Integer.parseInt(kaixindouWillUse.getText().toString().trim()) - num;
//        kaixindouWillUse.setText(kaixindou + "");
//        double newPrice = prics - Integer.parseInt(kaixindouWillUse.getText().toString().trim());
//        System.out.println("!!!!!!!!!!!!!! prics" + prics + "-" + Integer.parseInt(kaixindouWillUse.getText().toString().trim()) + "=" + newPrice);
//        DecimalFormat df = new DecimalFormat("#.00");
//
//        shouldPay.setText(df.format(newPrice) + "");
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add:
//                    if (Integer.parseInt(kaixindouWillUse.getText().toString()) < Integer
//                            .parseInt(kaixindouRemain.getText().toString())) {
//
//                        if (kaixindouWillUse.getText().toString().trim() == null) {
//                            kaixindouWillUse.setText("0");
//                        }
//                        int maxAdd = Integer.parseInt(kaixindouCouldUse.getText().toString().trim
//                                ());
//                        int userNumber = Integer.parseInt(kaixindouWillUse.getText().toString()
//                                .trim());
//                        if (userNumber < maxAdd) {
////                            userNumber++;
//                            addPressThe(1);
////                        float f=Float.parseFloat(shouldPay.getText().toString().trim())-1;
////                        System.out.println("++++++++++++++++++++实付款+" + f);
////                        shouldPay.setText(f + "");
//                        } else {
//                            Toast.makeText(context, "您可使用的开心豆数量已经达到上限", Toast.LENGTH_SHORT).show();
//                        }
////                        kaixindouWillUse.setText(userNumber + "");
////                    orderFinalPrice = Float.parseFloat(orderPrice) * num - Float.parseFloat
//// (kaixindouWillUse.getText().toString().trim());
//
//
//                    }

                    break;
                case R.id.push: {
//                    if (kaixindouWillUse.getText().toString().trim() == null) {
//                        kaixindouWillUse.setText("0");
//                    }
//                    int userNumber = Integer.parseInt(kaixindouWillUse.getText().toString().trim());
//                    if (userNumber >= 1) {
//                        pushPressThe(1);
////                        userNumber--;
////                        float f=Float.parseFloat(shouldPay.getText().toString().trim())+1;
////                        shouldPay.setText(f + "");
//                    }

//                    kaixindouWillUse.setText(userNumber + "");
//                    orderFinalPrice = Float.parseFloat(orderPrice) * num - Float.parseFloat
// (kaixindouWillUse.getText().toString().trim());
//                    shouldPay.setText(orderFinalPrice + "");
                }
                break;
                case R.id.navigation: {
                    finish();
                }
                break;
                case R.id.hui_buy_pay_pay_now: {
                    //判断是否选择了付款方式
                    if (payWay == -1) {
                        Toast.makeText(context, "请选择付款方式", Toast.LENGTH_SHORT).show();
                    } else {
                        if (payWay == PAY_ALIPAY) {
                            System.out.println("！！！！！！！！！！！ 1");
                            Intent intent = new Intent(HuiBuyPayActivity.this, ALiPayPayActivity
                                    .class);

                            intent.putExtra("name", proName + "等");
                            intent.putExtra("detail", "惠购商品");
                            intent.putExtra("c_price", prics);
                            if (getIntent().getBooleanExtra("dd", false)) {//订单车跳转过来的
                                orderSN = getIntent().getStringExtra("ordersn");
                            }
                            if (getIntent().getBooleanExtra("huidingdan", false)) {
                                orderSN = getIntent().getStringExtra("orderno");
                            }

                            intent.putExtra("orderno", orderSN);
//                            intent.putExtra("kaixindouWillUse", kaixindouWillUse.getText()
//                                    .toString()
//                                    .trim());
                            intent.putExtra("c_price",price);//支付总金额
                            intent.putExtra("info", HuiBuyPayActivity.this.getIntent()
                                    .getStringExtra("info"));//商品描述
                            intent.putExtra("bls", true);
                            System.out.println("！！！！！传递的商品名：" + proName);
                            System.out.println("！！！！！传递的商品名详细描述：" + HuiBuyPayActivity.this
                                    .getIntent()
                                    .getStringExtra("info"));
                            startActivity(intent);
                            HuiBuyPayActivity.this.finish();
                        } else {
                            System.out.println("！！！！！！！！！！！ 2");
//
//                        System.out.println("！！！！！！！！！！！！余额支付");
//                        float remaining = Float.parseFloat(accountRemaining);
//                        float  kxd = Float.parseFloat(kaixindouWillUse.getText().toString());
//                        BigDecimal b1 = new BigDecimal(Float.toString(kxd));
//                        BigDecimal b2 = new BigDecimal(Float.toString(orderFinalPrice));
//
//                        float  otherPay = b2.subtract(b1).floatValue();
//                        if (otherPay< remaining){
//                            // TODO: 02/18/2016
//                            //余额支付
//
//                            LOD lod = new LOD(context);
//                            String tradeno = getOutTradeNo();
//                            String payOrder = API.PAY_ORDER+"&uid="+lod.get("USER_INFO","UID","'")
//                                    +"&orderno="+orderSN+"&tradeno="+tradeno + "&money="+
// orderFinalPrice +"&kxd="+kaixindouWillUse.getText().toString().trim()+"&yue="+orderFinalPrice;
//                            Log.e("PayOrder",payOrder);
//                            Handler handler = new Handler(){
//                                @Override
//                                public void handleMessage(Message msg) {
//                                    switch (msg.what){
//                                        case BackgroundTask.SUCCESS:{
//                                            Bundle bundle = msg.getData();
//                                            if (bundle != null){
//                                                String result = bundle.getString("result");
//                                                Log.e("qkx" , "222222222222222=="+result);
//                                                try {
//                                                    JSONObject jsonObject = new JSONObject
// (result);
//                                                    if (jsonObject.optBoolean("falg")){
//
//                                                        Intent intentAllHuiOrder = new Intent
// (context,AllHuiOrderActivity.class);
//                                                        startActivity(intentAllHuiOrder);
//                                                        Toast.makeText(context, jsonObject
// .getString("msg"), Toast.LENGTH_SHORT).show();
//                                                    }else {
//                                                        Intent intentAllHuiOrder = new Intent
// (context,AllHuiOrderActivity.class);
//                                                        startActivity(intentAllHuiOrder);
//                                                        Toast.makeText(context, jsonObject
// .getString("msg"), Toast.LENGTH_SHORT).show();
//                                                    }
//
//                                                } catch (JSONException e) {
//                                                    e.printStackTrace();
//                                                    Toast.makeText(context, "数据解析失败", Toast
// .LENGTH_SHORT).show();
//                                                }
//
//                                            }
//                                        }
//                                            break;
//                                        case BackgroundTask.FAILURE:{
//
//                                        }
//                                            break;
//                                    }
//                                }
//                            };
//                            BackgroundTask backgroundTask = new BackgroundTask(context,
// payOrder,handler);
//                            backgroundTask.doInBackground();
//                        }else {
//                            Toast.makeText(context, "账户当前余额不足，请选择其他支付方式或充值", Toast
// .LENGTH_SHORT).show();
//                        }
                            addPrompt();
                        }
                    }

                }
                break;
                case R.id.payAlipay: {
                    payWay = PAY_ALIPAY;
//                    payAlipay.setBackgroundColor(getResources().getColor(R.color
//                            .main_line_pink_color));
//                    payRemaining.setBackgroundColor(getResources().getColor(R.color.white));
                    imgAli.setImageResource(R.mipmap.circle_clicked);
                    imgKaixin.setImageResource(R.mipmap.circle_unclicked);
                    imgRemain.setImageResource(R.mipmap.circle_unclicked);
                }
                break;
                case R.id.payRemaining: {
                    payWay = PAY_REMAINING;
//                    payRemaining.setBackgroundColor(getResources().getColor(R.color
//                            .main_line_pink_color));
//                    payAlipay.setBackgroundColor(getResources().getColor(R.color.white));
                    imgAli.setImageResource(R.mipmap.circle_unclicked);
                    imgKaixin.setImageResource(R.mipmap.circle_unclicked);
                    imgRemain.setImageResource(R.mipmap.circle_clicked);
                }
                break;
                case R.id.LL_kaixindou_jifen:
                    imgAli.setImageResource(R.mipmap.circle_unclicked);
                    imgKaixin.setImageResource(R.mipmap.circle_clicked);
                    imgRemain.setImageResource(R.mipmap.circle_unclicked);
                    break;
                case R.id.getway: {
                    startActivity(new Intent(context, GetMethodActivity.class));
                }

                break;
            }
        }
    }

    public EditText ed2;
    String mID = "";

    public void addPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("为了账号安全请输入登陆密码");
        View view = View.inflate(this, R.layout.my_login, null);
        ed2 = (EditText) view.findViewById(R.id.password);
        builder.setView(view);

        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String address = API.USER_LOGIN + "&phone=" + mID + "&pwd=" + ed2.getText()
                        .toString().trim();

                GetUserInformation mh = new GetUserInformation();
                BackgroundTask backgroundTask = new BackgroundTask(context, address, mh);
                backgroundTask.doInBackground();

            }
        });
        builder.setPositiveButton("取消", null);
        builder.create();
        builder.show();

    }

    private class GetUserInformation extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
//                    if (bundle != null) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
//                            System.out.println("!!!!!!!!!!!!!! boolean" + jsonObject.getBoolean
//                                    ("flag"));
//
//                            if (jsonObject.getBoolean("flag")) {
//                                System.out.println("！！！！！！！！！！！！余额支付");
//                                float remaining = Float.parseFloat(accountRemaining);
//                                float kxd = Float.parseFloat(kaixindouWillUse.getText().toString());
//                                BigDecimal b1 = new BigDecimal(Float.toString(kxd));
//                                BigDecimal b2 = new BigDecimal(Float.toString(orderFinalPrice));
//
//                                double pric = Double.parseDouble(shouldPay.getText().toString().trim());
//                                double yues = pric + Double.parseDouble(kaixindouWillUse.getText
//                                        ().toString().trim());
//
//                                float otherPay = b2.subtract(b1).floatValue();
//                                if (otherPay < remaining) {
//                                    // TODO: 02/18/2016
//                                    //余额支付
//
//                                    LOD lod = new LOD(context);
//                                    String tradeno = getOutTradeNo();
//                                    if (orderSN.equals("")) {
//                                        orderSN = getIntent().getStringExtra("orderSN");
//                                    }
//                                    String payOrder = API.PAY_ORDER + "&uid=" + lod.get
//                                            ("USER_INFO", "UID", "'")
//                                            + "&orderno=" + orderSN + "&tradeno=" + tradeno +"&type=yue"+
//                                            "&money=" +
//                                            Utils.convertDouble(String.valueOf(yues)) + "&kxd=" + kaixindouWillUse.getText
//                                            ().toString().trim() + "&yue=" + Utils.convertDouble(String.valueOf(pric))+"&sign="+ Utils.string2MD5(lod.get
//                                            ("USER_INFO", "UID", "'")+Utils.convertDouble(String.valueOf(yues)) + tradeno + orderSN+kaixindouWillUse.getText
//                                            ().toString().trim()+"Qde36s3der4");
//                                    System.out.println("!!!!!!!!! 确认付款 url" + payOrder);
//                                    Log.e("PayOrder", payOrder);
//                                    Handler handler = new Handler() {
//                                        @Override
//                                        public void handleMessage(Message msg) {
//                                            switch (msg.what) {
//                                                case BackgroundTask.SUCCESS: {
//                                                    Bundle bundle = msg.getData();
//                                                    if (bundle != null) {
//                                                        String result = bundle.getString("result");
//                                                        Log.e("qkx", "222222222222222==" + result);
//                                                        try {
//                                                            JSONObject jsonObject = new JSONObject
//                                                                    (result);
//                                                            if (jsonObject.optBoolean("flag")) {
//
//                                                                Intent intentAllHuiOrder = new
//                                                                        Intent
//                                                                        (context,
//                                                                                AllHuiOrderActivity.class);
//                                                                startActivity(intentAllHuiOrder);
//                                                                Toast.makeText(context, jsonObject
//                                                                        .getString("msg"), Toast
//                                                                        .LENGTH_SHORT).show();
//                                                            } else {
//                                                                Intent intentAllHuiOrder = new
//                                                                        Intent
//                                                                        (context,
//                                                                                AllHuiOrderActivity.class);
//                                                                startActivity(intentAllHuiOrder);
//                                                                Toast.makeText(context, jsonObject
//                                                                        .getString("msg"), Toast
//                                                                        .LENGTH_SHORT).show();
//                                                            }
//
//                                                        } catch (JSONException e) {
//                                                            e.printStackTrace();
//                                                            Toast.makeText(context, "数据解析失败", Toast
//                                                                    .LENGTH_SHORT).show();
//                                                        }
//
//                                                    }
//                                                }
//                                                break;
//                                                case BackgroundTask.FAILURE: {
//
//                                                }
//                                                break;
//                                            }
//                                        }
//                                    };
//                                    BackgroundTask backgroundTask = new BackgroundTask(context,
//                                            payOrder, handler);
//                                    backgroundTask.doInBackground();
//                                } else {
//                                    Toast.makeText(context, "账户当前余额不足，请选择其他支付方式或充值", Toast
//                                            .LENGTH_SHORT).show();
//                                }
//
//                            } else {
//                                Toast.makeText(context, "密码错误", Toast.LENGTH_SHORT).show();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
//                        }
//                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "网络中断，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    String s = "";

    public String validation(String address) {

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configCurrentHttpCacheExpiry(1000 * 0);
        // 设置超时时间
        httpUtils.configTimeout(5 * 1000);
        httpUtils.configSoTimeout(5 * 1000);
        httpUtils.configCurrentHttpCacheExpiry(0);// 设置缓存0秒,5秒内直接返回上次成功请求的结果。
        httpUtils.send(HttpRequest.HttpMethod.GET, address, null,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        s = responseInfo.result;
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        Toast.makeText(HuiBuyPayActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        return s;
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

    private class LoadMyKaiXindou extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    if (result != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            accountRemaining = jsonObject.getString("user_money");
                            kaixindouRemain.setText(jsonObject.getString("kaixindou"));
                            mID = jsonObject.getString("mobile_phone");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {

                }
                break;
            }
        }
    }

    private class OrderDetailHandler extends Handler {
        ProgressDialog progressDialog;

        public OrderDetailHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    // TODO: 02/18/2016
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        String result = bundle.getString("result");
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONObject data = jsonObject.getJSONObject("data");
                            orderPrice = data.getString("real_amount");
                            orderSN = data.getString("order_sn");
                            int kaixindouTemp = 0;
                            JSONArray jsonArray = data.getJSONArray("goods");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                kaixindouTemp += Integer.parseInt(object.getString("kaixindou"));
                                proName = object.getString("name");
                            }
                            if (getIntent().getBooleanExtra("dd", false)) {

                            } else {
//                                kaixindouCouldUse.setText(String.valueOf(kaixindouTemp * num));
//                                orderFinalPrice = Float.parseFloat(orderPrice) * num - Float
//                                        .parseFloat(kaixindouWillUse.getText().toString().trim());
//                                shouldPay.setText((Float.parseFloat(orderPrice) * num) + "");
//                                prics = (Float.parseFloat(orderPrice) * num);
                                System.out.println("线程执行：" + Float.parseFloat(orderPrice) * num);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(context, "网络请求失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


}
