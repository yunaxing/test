package com.qkxmall.mall.views.func.coucou.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.util.DateUtil;
import com.qkxmall.mall.util.Sha1;
import com.qkxmall.mall.views.cloud.detail.CloudDetailActivity;
import com.qkxmall.mall.views.hui.container.HuiDragFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by yuna on 2017/8/5.
 */

public class faqiCouCou5 extends Activity implements View.OnClickListener {

    private ImageView imgFaQiCouCou;
    private ImageView imgClose;

    private int payWay = -1;
    private Button btnBuy;
    public static final int PAY_ALIPAY = 0x001201;
    public static final int PAY_REMAINING = 0x001202;
    private TextView tvKaiXinDou;
    private TextView tvZhiFu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_faqi_coucou_5);
        initView();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
//        p.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的0.3
        p.width = (int) (d.getWidth() * 1); // 宽度设置为屏幕的0.7
        getWindow().setAttributes(p);
        initData();
    }

    private void initView() {
        imgClose = (ImageView)findViewById(R.id.img_close);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnBuy = (Button)findViewById(R.id.hui_buy_pay_pay_now);
        tvKaiXinDou = (TextView)findViewById(R.id.txt_kaixindou_kucun);
        tvZhiFu = (TextView)findViewById(R.id.txt_kaixindou_bencizhifu);
        tvZhiFu.setText("本次需支付 "+getIntent().getIntExtra("num",1)+"枚");
        btnBuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hui_buy_pay_pay_now: //判断是否选择了付款方式
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("支付...");
                progressDialog.show();
                SharedPreferences preferences = getApplication().getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
                preferences.getString("UID","");
                String tradeno = getOutTradeNo();
                String accesstoken = getIntent().getStringExtra("addressid")+getIntent().getStringExtra("cid")+getIntent().getStringExtra("create_pay")
                +DateUtil.getCurrentDate()+getIntent().getIntExtra("num",1)+6+tradeno+ preferences.getString("UID","");
                String address = API.YUER_BUY+"&create_pay="+getIntent().getStringExtra("create_pay")+
                        "&cid="+getIntent().getStringExtra("cid")+
                        "&uid="+ preferences.getString("UID","")+
                        "&num="+getIntent().getIntExtra("num",1)+
                        "&totalpay="+6+
                        "&tradeno="+tradeno+
                        "&address="+getIntent().getStringExtra("addressid")+
                        "&access_token="+(new Sha1().getDigestOfString((
                        accesstoken+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());
                System.out.println("!!!!!!!!!!!!!!!!! 慧购 url " + address);
                System.out.println("!!!!!!!!!!!!!!!!! 慧购 accesstoken== " + accesstoken);
                if (address.contains(" ")){
                    if(address.substring(address.length()-1)==" "){
                        address= address.substring(0,address.length()-1);
                    }else{
                        address= address.replace(" ","%20");
                    }
                }
                if (address.contains("\"")){
                    address= address.replace("\"","%22");
                }
                if (address.contains("{")){
                    address= address.replace("{","%7B");
                }
                if (address.contains("}")){
                    address= address.replace("{","%7D");
                }
                Log.e("Address", address);
                BUYNOW loadData = new BUYNOW(progressDialog);
                BackgroundTask backgroundTask = new BackgroundTask(getApplication(), address, loadData);
                backgroundTask.doInBackground();

        }
    }

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
    private void initData(){
        LOD lod = new LOD(this);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("");
        progressDialog.show();

        GetMoney getMoney = new GetMoney(progressDialog);
        String url = API.USER_INFO+"&uid="+lod.get("USER_INFO","UID","");
        BackgroundTask backgroundTask = new BackgroundTask(getApplication(),url,getMoney);
        backgroundTask.doInBackground();
    }
    private class GetMoney extends Handler {

        ProgressDialog progressDialog;

        public GetMoney(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
//                            tvYUE.setText(jsonObject.getString("user_money"));
                            tvKaiXinDou.setText("库存 "+jsonObject.getString("kaixindou")+" 枚");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplication(), "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                }
                break;
            }
        }
    }
    private class BUYNOW extends Handler {
        ProgressDialog progressDialog;

        public BUYNOW(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONObject object = new JSONObject(bundle.getString("result"));
                            if(object.getString("flag").equals("true")){
                                Toast.makeText(getApplication(), "支付成功", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplication(),CloudDetailActivity.class).putExtra("cid",getIntent().getStringExtra("cid")));
                            }else {
                                Toast.makeText(getApplication(), "操作失败", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplication(), "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE: {
                    progressDialog.dismiss();
                    Toast.makeText(getApplication(), "数据加载失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}