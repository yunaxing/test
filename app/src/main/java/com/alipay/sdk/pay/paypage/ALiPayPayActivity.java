package com.alipay.sdk.pay.paypage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TreeMap;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
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
import com.qkxmall.mall.views.user.AccountManageActivity;
import com.qkxmall.mall.views.user.PerCenterMyWalletActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ALiPayPayActivity extends FragmentActivity {

    Context context;


    private TextView name = null;
    private TextView detail = null;
    private TextView priceView = null;
    private TextView yue = null;

    String SN = "";


    // 商户PID
    public static final String PARTNER = "2088021597065955";
    // 商户收款账号
    public static final String SELLER = "1015971398@qq.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMYtIwdXFl" +
                    "+No4qFDfJ7MbYY7fyNTPKlvcHvuPnjrMie4oU/fn6LOmOjvGlh0VHItS6TchFVsQ0vKp" +
                    "+OxqZEmJd3EwDlh2Lb1RhgLKwkpwE9AoMgnu1hV/a4HsvSUhrqZFX6Idd3AamtSFqWpX5RmBR" +
                    "+qSwKrxMfi9VhuvzcTjp7AgMBAAECgYBn1xbXrcZHY0wiNP3N5MdzhzSMdO2w5y" +
                    "+7AwWFysqqhLZk45RAcmux8gWzolGv7N8i/Jd0hdYz9lb7LDDixma9abRpQx9uYHgVCxpZKe" +
                    "+ZosRy0YkOiPgvi6VKyE0htMz6" +
                    "+k9XJMC0az91eNkGEy5waSJa3CRwLPAjTsLYuQAwYQJBAPh2dqZhhrnZgdwxTxPleA3656tTbY7cQ3y5Sb4tLLlC7NLrKQUlZRXLSw+5+fDVVCfRlJk1GlS+pmxLjvvwbG8CQQDMMCdtsN3z1XAGU8a2shARu0H0iRNxddJlBKqPxr7iRJHNaPI5Q6SrnhhhkUL7fXQ2ndTSqczEt0tLoJNUr3C1AkEAmUYTKc6uGwZX+uscOkzrw8wNkH417tRoeY+gM0aQ+XSUvgS92GPaORihJIF4853WfX3nqp+8r5yw88uk2DzX1wJAET6z5ci9Rh2to2sp6j4pq0W0cVGuabO4vD1jplbzwipmYgHOC1YSL0hf6MWa8aCL0x0LX2tl1utgGZgLOWjDzQJAOsWKHH6IzX+2pbANTR0d95vsmjkrRYw3E+SuqNOGmgQZ65ieY9IDnsJtZ0uMNcP2cWBsoX2uOL+eAxyo2XvTGQ==";
    // 支付宝公钥
    public static final String RSA_PUBLIC =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh" +
                    "/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB" +
                    "/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6" +
                    "/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
    private static final int SDK_PAY_FLAG = 1;

    //余额支付
    private static final int SDK_PAY_FLAY_OTHER = 3;

    private static final int SDK_CHECK_FLAG = 2;

    //是否为账户充值
    private static boolean bl;
    //商户订单号
    String orderNo;
    String cid;
    String address;

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);

                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();
//                   BackgroundTask sendInfo=new BackgroundTask(getApplicationContext(),)
                    String resultStatus = payResult.getResultStatus();

                    System.out.println("！！！！！！！！！！！！！充值后返回的信息：" + resultStatus);
                    System.out.println("！！！！！！！！！！！！！ 是否是 充值界面过来：" + getIntent().getBooleanExtra
                            ("chongzhi", false));
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(getApplication(), "支付成功", Toast.LENGTH_SHORT).show();
                        //此处添加判断 是用户充值页跳转  充值成功才增长
                        if (getIntent().getBooleanExtra("chongzhi", false)) {
                            addBalance();//添加的代码  充值成功后账户余额 相应的增长
                            return;
                        }
//                        //此处购物车结算
//                        if (getIntent().getBooleanExtra("gwc", false)) {
//                            cloudPagePay(0);
//                            return;
//                        }
//
//                        if (getIntent().getBooleanExtra("yung", false)) {//判断是否是云购立即购买 支付宝支付
//                            yuePay("0");
//                            return;
//                        }
//
                        if (getIntent().getBooleanExtra("huidingdan", false)) {
                            orderNo = getIntent().getStringExtra("orderSN");
                        }

//                        LOD lod = new LOD(context);
//                        String outNo= getOutTradeNo();
//                        String payOrder = API.PAY_ORDER + "&uid=" + lod.get("USER_INFO", "UID", "'")
//                                + "&orderno=" + orderNo + "&tradeno=" +outNo +
//                                "&money=" + priceView.getText().toString().trim() + "&kxd=" +
//                                getIntent().getStringExtra("kaixindouWillUse") + "&yue=" + Utils.convertDouble("0")+Utils.string2MD5(lod.get("USER_INFO", "UID", "'") +"&sign="+Utils.string2MD5( lod.get("USER_INFO", "UID", "'")+ Utils.convertDouble(priceView.getText().toString().trim()) + outNo + orderNo+getIntent().getStringExtra("kaixindouWillUse"))+"Qde36s3der4");
//
//                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! 开心逗" + getIntent()
//                                .getStringExtra("kaixindouWillUse"));
//                        System.out.println("!!!!!!!!!! 订单号：" + orderNo);
//                        System.out.println("!!!!!!!!!! 交易号：" + payOrder);
//
//                        System.out.println("!!!!!!!!!!!!!!!!!!!! 慧购订单支付URL：" + payOrder);
//
//                        Handler handler = new Handler() {
//                            @Override
//                            public void handleMessage(Message msg) {
//                                switch (msg.what) {
//                                    case BackgroundTask.SUCCESS: {
//                                        Bundle bundle = msg.getData();
//                                        if (bundle != null) {
//                                            String result = bundle.getString("result");
//                                            try {
//                                                JSONObject jsonObject = new JSONObject(result);
//                                                if (jsonObject.getBoolean("flag")) {
////                                                    Toast.makeText(context, jsonObject.getString
////															("msg"), Toast.LENGTH_SHORT).show();
//                                                } else {
////                                                    Toast.makeText(context, jsonObject.getString
////															("msg"), Toast.LENGTH_SHORT).show();
//                                                }
//
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                                Toast.makeText(context, "数据解析失败", Toast
//                                                        .LENGTH_SHORT).show();
//                                            }
//
//                                        }
//                                    }
//                                    break;
//                                    case BackgroundTask.FAILURE: {
//
//                                    }
//                                    break;
//                                }
//                            }
//                        };
//                        BackgroundTask backgroundTask = new BackgroundTask(context, payOrder,
//                                handler);
//                        backgroundTask.doInBackground();

//                        Toast.makeText(ALiPayPayActivity.this, "支付成功",
//                                Toast.LENGTH_SHORT).show();
//					Toast.makeText(ALiPayPayActivity.this,"订单号："+orderNo,Toast.LENGTH_LONG).show();
                        //Notification
//                        NotificationCompat.Builder builder = new NotificationCompat.Builder
//                                (ALiPayPayActivity.this)
//                                .setSmallIcon(R.drawable.logo_alipay)
//                                .setContentTitle("支付消息")
//                                .setContentText("订单号：" + orderNo);
//                        Intent resultIntent = new Intent(ALiPayPayActivity.this,
//                                ALiPayPayActivity.class);
//                        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create
//                                (ALiPayPayActivity.this);
//                        taskStackBuilder.addNextIntent(resultIntent);
//                        PendingIntent resultPendIntent = taskStackBuilder.getPendingIntent(0,
//                                PendingIntent.FLAG_UPDATE_CURRENT);
//                        builder.setContentIntent(resultPendIntent);
//                        NotificationManager manager = (NotificationManager) getSystemService
//                                (Context.NOTIFICATION_SERVICE);
//                        manager.notify(1, builder.build());


//					CloudDetailActivity.cloudDetailActivity.finish();
//					finish();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(ALiPayPayActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(ALiPayPayActivity.this, "支付失败",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    Toast.makeText(ALiPayPayActivity.this, "检查结果为：" + msg.obj,
                            Toast.LENGTH_SHORT).show();
                    break;
                }

                //余额支付
                case SDK_PAY_FLAY_OTHER:
                    //云购余额支付
                    System.out.println("！！！！！！！！！！余额判断");
                    if (Double.parseDouble(yue.getText().toString().trim()) < Double.parseDouble
                            (getIntent().getStringExtra("c_price"))) {
                        Toast.makeText(context, "余额不足 请充值或选择其他支付方式", Toast.LENGTH_SHORT).show();
                    } else {
                        if (getIntent().getBooleanExtra("gwc", false)) {
                            cloudPagePay(1);
                        } else {
                            yuePay("1");
                        }

                    }

                    break;

                default:
                    break;
            }
        }
    };

    //云购 购物车结算
    public void cloudPagePay(int i) {

        String payPrice = getIntent().getStringExtra("c_price");
        cid = getIntent().getStringExtra("cid");
        String orderno = getIntent().getStringExtra("orderno");
        String addr = getIntent().getStringExtra("address");

        LOD lod = new LOD(context);
//        String num = getIntent().getStringExtra("num");
//        String sign=Utils.string2MD5(lod.get("USER_INFO", "UID", "'") +cid+num+ Utils.convertDouble(payPrice) + orderno + orderNo+addr+);
        String sign = Utils.string2MD5(lod.get("USER_INFO", "UID", "'") + Utils.convertDouble(payPrice) + orderno + orderNo + "Qde36s3der4");
        String payOrder = "https://www.qkxmall.com/index.php?m=api&c=cloud&a=payorder" + "&uid=" +

                lod.get("USER_INFO", "UID", "'")
                + "&fee=" + Utils.convertDouble(payPrice) + "&yue=" + Utils.convertDouble(String.valueOf(i)) + "&tradeno=" + orderNo + "&orderno=" + orderno + "&type=yue" + "&sign=" + sign;
        System.out.println("！！！！！！！！！！交易订单号 ：" + orderNo);
        System.out.println("！！！！！！！！！！交易号 ：" + orderno);
        Log.i("qkx", payOrder);
//				Toast.makeText(context , payOrder , Toast.LENGTH_LONG).show();
        Handler handler2 = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case BackgroundTask.SUCCESS: {
                        Bundle bundle = msg.getData();
                        if (bundle != null) {
                            String result = bundle.getString("result");
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                if (jsonObject.getBoolean("flag")) {
                                    Toast.makeText(context, jsonObject.getString("msg"), Toast
                                            .LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(context, jsonObject.getString("msg"), Toast
                                            .LENGTH_SHORT).show();
                                }

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
        };
        BackgroundTask backgroundTask = new BackgroundTask(context, payOrder, handler2);
        backgroundTask.doInBackground();

        finish();


    }

    //云购余额支付
    public void yuePay(String yue) {
        {
            System.out.println("！！！！！！！！！！！！！！！！！！！！云购 立即购买 ");


            orderNo = getIntent().getStringExtra("orderno");
            String payPrice = getIntent().getStringExtra("c_price");
            cid = getIntent().getStringExtra("cid");
            String num = getIntent().getStringExtra("num");
            String addr = getIntent().getStringExtra("address");//getIntent().getStringExtra
            LOD lod = new LOD(context);
            String payOrder = "";
            String signStr = lod.get("USER_INFO", "UID", "'") + cid + num + Utils.convertDouble(payPrice) + orderNo + addr + Utils.convertDouble(payPrice) + "Qde36s3der4";
            String sign = Utils.string2MD5(signStr);
            if (yue.equals("0")) {//未支付宝支付
                payOrder = API.CLOUD_PAY + "&uid=" + lod.get("USER_INFO", "UID", "'") + "&type=yue"
                        + "&cid=" + cid + "&num=" + num + "&tradeno=" + orderNo + "&totalpay=" +
                        Utils.convertDouble(payPrice) + "&address=" + addr + "&yue=" + Utils.convertDouble(payPrice) + "&sign=" + sign;
            } else {
                payOrder = API.CLOUD_PAY + "&uid=" + lod.get("USER_INFO", "UID", "'") + "&type=yue"
                        + "&cid=" + cid + "&num=" + num + "&tradeno=" + orderNo + "&totalpay=" +
                        Utils.convertDouble(payPrice) + "&address=" + addr + "&yue=" + Utils.convertDouble(payPrice) + "&sign=" + sign;
            }

            System.out.println("！！！！！！！！！！交易订单号 url ：" + payOrder);
            Log.i("qkx", payOrder);
//				Toast.makeText(context , payOrder , Toast.LENGTH_LONG).show();
            Handler handler2 = new Handler() {

                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case BackgroundTask.SUCCESS: {
                            Bundle bundle = msg.getData();
                            if (bundle != null) {
                                String result = bundle.getString("result");
                                try {
                                    JSONObject jsonObject = new JSONObject(result);
                                    if (jsonObject.getBoolean("flag")) {
                                        Toast.makeText(context, jsonObject.getString("msg"),
                                                Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(context, jsonObject.getString("msg"),
                                                Toast.LENGTH_SHORT).show();
                                    }

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
            };
            BackgroundTask backgroundTask = new BackgroundTask(context, payOrder, handler2);
            backgroundTask.doInBackground();
            finish();
        }

    }

    private Button bay;
    boolean isCloud = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_main);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! 开心逗" + getIntent().getStringExtra
                ("kaixindouWillUse"));
        context = ALiPayPayActivity.this;

        name = (TextView) findViewById(R.id.name);
        detail = (TextView) findViewById(R.id.detail);
        priceView = (TextView) findViewById(R.id.product_price);
        yue = (TextView) findViewById(R.id.pay_account_remaining);

        bay = (Button) findViewById(R.id.pay);
        //查询账户余额
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("");
        progressDialog.show();
        LOD lod = new LOD(context);
        String url = API.USER_INFO + "&uid=" + lod.get("USER_INFO", "UID", "");
        GetMoney getMoney = new GetMoney(progressDialog);
        BackgroundTask backgroundTask = new BackgroundTask(context, url, getMoney);
        backgroundTask.doInBackground();

        bl = getIntent().getBooleanExtra("chongzhi", false);

        name.setText(getIntent().getStringExtra("name"));
        String s;
        if (getIntent().getStringExtra("info") == null) {
            s = "";
        } else {
            s = getIntent().getStringExtra("info");
        }
        isCloud = getIntent().getBooleanExtra("isCloud", false);
        detail.setText(getIntent().getStringExtra("name") + " " + s);
        priceView.setText(getIntent().getStringExtra("c_price"));

        SN = getIntent().getStringExtra("ordersn");

        LinearLayout yButton = (LinearLayout) findViewById(R.id.ye_pay);
        yButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Double.parseDouble(yue.getText().toString().trim()) < Double.parseDouble
                        (getIntent().getStringExtra("c_price"))) {
                    Toast.makeText(context, "余额不足 请充值或选择其他支付方式", Toast.LENGTH_SHORT).show();
                } else {
                    addPrompt();
                }

//				payByOther();
            }
        });

        orderNo = getIntent().getStringExtra("orderno");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! 订单初始化：" + orderNo);
        //慧购直接点击支付宝付款
        if (getIntent().getBooleanExtra("bls", false)) {
//            bay.performClick();
        }


    }

    private class GetMoney extends Handler {

        ProgressDialog progressDialog;

        public GetMoney() {
        }

        public GetMoney(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            yue.setText(jsonObject.getString("user_money"));
//							myHappyBean.setText(jsonObject.getString("kaixindou"));
                            mID = jsonObject.getString("mobile_phone");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE: {
                    progressDialog.dismiss();
                }
                break;
            }
        }
    }

    /**
     * 使用余额宝支付
     */
    public void payByOther() {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE)
                || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(this)
                    .setTitle("警告")
                    .setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {
                                    //
                                    finish();
                                }
                            }).show();
            return;
        }
        // 订单
//        String orderInfo = getOrderInfo(getIntent().getStringExtra("name"), getIntent()
//                .getStringExtra("detail"), getIntent().getStringExtra("c_price"));


        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {

                Message msg = new Message();
                msg.what = SDK_PAY_FLAY_OTHER;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    //返回键
    public void backspace(View v) {
        finish();
    }

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public void pay(View v) {
        System.out.println("！！！！！！！！！！！ 支付宝支付 ");
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE)
                || TextUtils.isEmpty(SELLER)) {

            System.out.println("！！！！！！！！！！！！ if 判断");
            new AlertDialog.Builder(this)
                    .setTitle("警告")
                    .setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {
                                    //
                                    finish();
                                }
                            }).show();
            return;
        }
        //https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.A865ju&treeId=58&articleId=103596&docType=1
        // 订单
//        TreeMap<String, String> orderInfo = getOrderInfoMap(getIntent().getStringExtra("name"), getIntent()
//                .getStringExtra("detail"), getIntent().getStringExtra("c_price"));
        String orderinfo1 = null;

        Intent intent = getIntent();
        boolean isHuiGou =intent.getBooleanExtra("bls", false);
        boolean isCloudLiJiGouMai = intent.getBooleanExtra("isCloud", false);
        boolean isCloudCart = intent.getBooleanExtra("gwc", false);
        String name = intent.getStringExtra("name");
        String detail = intent.getStringExtra("detail");
        String c_price = intent.getStringExtra("c_price");
        if (isHuiGou) {
            orderinfo1 = getOrderInfo(name, detail, c_price, "https://www.qkxmall.com/api/payment/alipay/huigou_buy_notify.php", false);//https://www.qkxmall.com/api/payment/alipay/cloud_buy_notify.php
        } else if (isCloudLiJiGouMai) {
            orderinfo1 = getOrderInfo(name, detail, c_price, "https://www.qkxmall.com/api/payment/alipay/cloud_buy_notify.php", true);
        } else if(isCloudCart){
            orderinfo1 = getOrderInfo(name, detail, c_price, "https://www.qkxmall.com/api/payment/alipay/cloud_multi_buy_notify.php", true);//https://www.qkxmall.com/api/payment/alipay/cloud_buy_notify.php
        }


//        Set<String> keyset = orderInfo.keySet();
//        List<String> list = new ArrayList<>(keyset);

        //对key键值按字典升序排序
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
//        Collections.sort(list);  // 对订单做RSA 签名
//        StringBuilder sb = new StringBuilder();
//        for (String key : keyset) {
//            if(TextUtils.isEmpty(orderInfo.get(key))){
//                continue;
//            }
//            sb.append(key).append("=").append(orderInfo.get(key)).append("&");
//        }
//        if (sb.toString().endsWith("&")) {
//            sb.deleteCharAt(sb.length() - 1);
//        }
//        String info = sb.toString();

//        String sign1 = info + "j1ftbdxcn16lif5wo3hpuhk85d9mvic1";
//        final String sign = Utils.string2MD5(sign1);

//        /**
//         * 拼接订单
        String sign = sign(orderinfo1);


        try {
            System.out.println("！！！！！！！！！！！！ try 判断");
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 完整的符合支付宝参数规范的订单信息
        final String payInfo = orderinfo1 + "&sign=\"" + sign + "\"&"
                + getSignType();
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(ALiPayPayActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo);


                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * check whether the device has authentication alipay account.
     * 查询终端设备是否存在支付宝认证账户
     */
    public void check(View v) {

        PayProcess();
    }

    EditText ed2;
    String mID;

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
                BackgroundTask backgroundTask = new BackgroundTask(ALiPayPayActivity.this, address, mh);
                backgroundTask.doInBackground();

            }
        });
        builder.setPositiveButton("取消", null);
        builder.create();
        builder.show();
    }

    //原check方法中的代码
    public void PayProcess() {
        Runnable checkRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask payTask = new PayTask(ALiPayPayActivity.this);
                // 调用查询接口，获取查询结果
                boolean isExist = payTask.checkAccountIfExist();

                Message msg = new Message();
                msg.what = SDK_CHECK_FLAG;
                msg.obj = isExist;
                mHandler.sendMessage(msg);
            }
        };
        Thread checkThread = new Thread(checkRunnable);
        checkThread.start();

    }


    /**
     * get the sdk version. 获取SDK版本号
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
        Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
    }

    public void addBalance() {
        LOD lod = new LOD(context);
        String url = API.ACCOUNT_RECHARGE +
                "&uid=" + lod.get("USER_INFO", "UID", "") +
                "&money=" + priceView.getText().toString().trim() +
                "&orderno=" + orderNo;
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configCurrentHttpCacheExpiry(1000 * 0);
        // 设置超时时间
        httpUtils.configTimeout(5 * 1000);
        httpUtils.configSoTimeout(5 * 1000);
        httpUtils.configCurrentHttpCacheExpiry(0);// 设置缓存0秒,5秒内直接返回上次成功请求的结果。
        httpUtils.send(HttpRequest.HttpMethod.GET, url, null,
                new RequestCallBack<Object>() {
                    @Override
                    public void onSuccess(ResponseInfo<Object> responseInfo) {
                        //添加代码  充值成功后跳转到我的钱包
                        startActivity(new Intent(ALiPayPayActivity.this, PerCenterMyWalletActivity.class));
//						startActivity(new Intent(ALiPayPayActivity.this,AccountManageActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        startActivity(new Intent(ALiPayPayActivity.this, AccountManageActivity.class));
                        finish();
                    }
                }
        );

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

    /**
     * create the order info. 创建订单信息
     */
    public String getOrderInfo(String subject, String body, String price, String notifiurl, boolean isyungou) {
        LOD lod = new LOD(context);
        orderNo = getIntent().getStringExtra("orderno");
//        if (orderNo.equals("")) {
//            orderNo = getOutTradeNo();
//        }
        cid = getIntent().getStringExtra("cid");
        System.out.println("！！！！！！！！！！！！！！支付宝传递的订单号：" + orderNo);

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + orderNo + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + Utils.convertDouble(price) + "\"";
// 商品金额
//        orderInfo += "&c_price=" + "\"" + c_price + "\"";
        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + notifiurl
                + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";
        String num = getIntent().getStringExtra("num");
        String addr = getIntent().getStringExtra("address");//getIntent().getStringExtra
        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
        String extrainfo = null;
        if (isyungou) {
             extrainfo = "cid=" + cid + "&uid=" + lod.get("USER_INFO", "UID", "") + "&num=" + num + "&address=" + addr;
//            extrainfo = "uid=" + lod.get("USER_INFO", "UID", "");
        } else {
            extrainfo = "uid=" + lod.get("USER_INFO", "UID", "") + "&kxd=" + getIntent().getStringExtra("kaixindouWillUse");
        }


        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";
        orderInfo += "&extra_common_param=\"" + extrainfo + "\"";
        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * create the order info. 创建订单信息
     */
    public TreeMap<String, String> getOrderInfoMap(String subject, String body, String price) {
        TreeMap<String, String> infoMap = new TreeMap<>();
        LOD lod = new LOD(context);
        orderNo = getIntent().getStringExtra("orderno");
        if (orderNo.equals("")) {
            orderNo = getOutTradeNo();
        }
        cid = getIntent().getStringExtra("cid");
        System.out.println("！！！！！！！！！！！！！！支付宝传递的订单号：" + orderNo);

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";
        infoMap.put("partner", PARTNER);
        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";
        infoMap.put("seller_id", SELLER);
        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + orderNo + "\"";
        infoMap.put("out_trade_no", orderNo);
//        orderInfo += "&c_price=" + "\"" + c_price + "\"";
//        infoMap.put("c_price", Utils.convertDouble(c_price));
        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";
        infoMap.put("subject", subject);
        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";
        infoMap.put("body", body);
        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";
        infoMap.put("total_fee", Utils.convertDouble(price));
        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "https://www.qkxmall.com/api/payment/alipay/cloud_buy_notify.php"
                + "\"";
        infoMap.put("notify_url", "https://www.qkxmall.com/api/payment/alipay/cloud_buy_notify.php");
        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";
        infoMap.put("service", "mobile.securitypay.pay");
        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";
        infoMap.put("payment_type", "1");
        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";
        infoMap.put("_input_charset", "utf-8");
        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";
        infoMap.put("it_b_pay", "30m");
        String num = getIntent().getStringExtra("num");
        String addr = getIntent().getStringExtra("address");//getIntent().getStringExtra
        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
        String extrainfo = "\"cid=" + cid + "&uid=" + lod.get("USER_INFO", "UID", "") + "&num=" + num + "&address=" + addr + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";
        infoMap.put("return_url", "m.alipay.com");
        orderInfo += "&extra_common_param=" + extrainfo;
        infoMap.put("extra_common_param", extrainfo);

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return infoMap;
    }

    private class GetUserInformation extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            System.out.println("!!!!!!!!!!!!!! boolean" + jsonObject.getBoolean
                                    ("flag"));

                            if (jsonObject.getBoolean("flag")) {
//								PayProcess();
                                payByOther();
                            } else {
                                Toast.makeText(context, "密码错误", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "网络中断，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    public String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    public String getSignType() {
        return "sign_type=\"RSA\"";
    }

}
