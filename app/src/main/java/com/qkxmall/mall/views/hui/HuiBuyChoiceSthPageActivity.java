package com.qkxmall.mall.views.hui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.pay.paypage.ALiPayPayActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.views.ScrollListView;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.address.AddressSelectActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class HuiBuyChoiceSthPageActivity extends Activity {

    String GID = "";
    String PID = "";
    String addressid = "";

    private ImageView navigation = null;
    private Context context;

    private SimpleDraweeView picture = null;
    private TextView price = null;
    private TextView bean = null;
    private ScrollListView select = null;

    private TextView name = null;
    private TextView phone = null;
    private TextView address = null;

    private ImageView addBuyNumber = null;
    private ImageView cutBuyNumber = null;
    private EditText editBuyNumber = null;

    private LinearLayout addressLine = null;

    private TextView buyNow = null;
    private TextView addTr = null;

    private int kaixnidou;
    private double mPric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_buy_choice_sth_page);
        context = HuiBuyChoiceSthPageActivity.this;
        init();
        //如果是云购跳转过来的  改变相应的布局设置不同参数
        if (getIntent().getBooleanExtra("bl", false)) {
            findViewById(R.id.add_tr).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.my_rl)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.tv_1)).setText("每份单价：");
            ((TextView) findViewById(R.id.tv_2)).setText("购买分数：");
            price.setText(this.getIntent().getStringExtra("c_price"));
            bean.setText(this.getIntent().getStringExtra("num"));
            kaixnidou = Integer.parseInt(bean.getText().toString());
            String[] arr = this.getIntent().getStringExtra("img").split(",");
            picture.setImageURI(Uri.parse(API.ADD + arr[0]));

        }

        if (getIntent().getStringExtra("c_price") != null) {
            price.setText(getIntent().getStringExtra("c_price"));
        }
        if (getIntent().getStringExtra("kaixindou") != null) {
            bean.setText(getIntent().getStringExtra("kaixindou"));
        }
        if (getIntent().getStringExtra("id") != null)
            GID = getIntent().getStringExtra("id");
        if (getIntent().getStringExtra("pid") != null)
            PID = getIntent().getStringExtra("pid");
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("数据加载中...");
        progressDialog.show();

        LoadDetailInfo loadDetailInfo = new LoadDetailInfo(progressDialog);
        String address = API.HUI_PRODUCTS_DETAIL + "&gid=" + getIntent().getStringExtra("id");
        BackgroundTask backgroundTask = new BackgroundTask(context, address, loadDetailInfo);
        backgroundTask.doInBackground();

        LoadAddress loadAddress = new LoadAddress();
        LOD lod = new LOD(context);
        String addressUrl = API.USER_ADDRESS_LIST + "&uid=" + lod.get("USER_INFO", "UID", "");
        Log.e("Address", addressUrl);
        BackgroundTask addressTack = new BackgroundTask(context, addressUrl, loadAddress);
        addressTack.doInBackground();

        navigation.setOnClickListener(new OnClick());
        buyNow.setOnClickListener(new OnClick());
        addressLine.setOnClickListener(new OnClick());
        addBuyNumber.setOnClickListener(new OnClick());
        cutBuyNumber.setOnClickListener(new OnClick());
        buyNow.setOnClickListener(new OnClick());
        addTr.setOnClickListener(new OnClick());

        mPric = Double.parseDouble(price.getText().toString().trim());

    }

    private void init() {
        navigation = (ImageView) findViewById(R.id.navigation);
        buyNow = (TextView) findViewById(R.id.buy);

        picture = (SimpleDraweeView) findViewById(R.id.picture);
        price = (TextView) findViewById(R.id.price);
        bean = (TextView) findViewById(R.id.bean);

        select = (ScrollListView) findViewById(R.id.select);

        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        address = (TextView) findViewById(R.id.address);

        addBuyNumber = (ImageView) findViewById(R.id.add);
        cutBuyNumber = (ImageView) findViewById(R.id.push);
        editBuyNumber = (EditText) findViewById(R.id.number);

        addressLine = (LinearLayout) findViewById(R.id.addressLine);

        addTr = (TextView) findViewById(R.id.add_tr);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case BackgroundTask.SUCCESS: {
                            Bundle result = msg.getData();
                            if (result != null) {
                                try {
                                    JSONArray jsonArray = new JSONArray(result.getString("result"));
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        if (data.getStringExtra("addressid") != null)
                                            if (jsonObject.getString("id").equals(data.getStringExtra("addressid"))) {
                                                addressid = data.getStringExtra("addressid");
                                                name.setText(jsonObject.getString("addressname"));
                                                phone.setText(jsonObject.getString("mobile"));
                                                address.setText(jsonObject.getString("provincename") +
                                                        jsonObject.getString("cityname") +
                                                        jsonObject.getString("districtname") +
                                                        jsonObject.getString("address"));
                                                System.out.println("===============用户收货地址：" + data.getStringExtra("addressid"));
                                                break;
                                            }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        break;
                        case BackgroundTask.FAILURE: {
                            Toast.makeText(context, "地址设置失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
            };
            LOD lod = new LOD(context);
            String addressUrl = API.USER_ADDRESS_LIST + "&uid=" + lod.get("USER_INFO", "UID", "");
            BackgroundTask backgroundTask = new BackgroundTask(context, addressUrl, handler);
            backgroundTask.doInBackground();
        }
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.navigation: {
                    finish();
                }
                break;
                case R.id.addressLine: {
//                    Intent intent = new Intent(HuiBuyChoiceSthPageActivity.this, AccountAddressManageActivity.class);
                    Intent intent = new Intent(HuiBuyChoiceSthPageActivity.this, AddressSelectActivity.class);
                    startActivityForResult(intent, 100);
                }
                break;
                case R.id.add: {
                    if (editBuyNumber.getText().toString().trim().equals(""))
                        editBuyNumber.setText("1");

                    int number = Integer.parseInt(editBuyNumber.getText().toString().trim());
                    number++;


                    //添加代码部分以解决惠购中购买数量的增加而可使用开心豆不跟进的问题
                    int beaNumber = Integer.parseInt(bean.getText().toString().trim());
                    beaNumber += kaixnidou;
                    //注释之前的事添加的代码部分

                    //价格联动改变
                    double numPrice = Double.parseDouble(price.getText().toString().trim());
                    numPrice += mPric;

                    DecimalFormat df = new DecimalFormat("#.00");
                    price.setText(df.format(numPrice) + "");
                    bean.setText("" + beaNumber);
                    editBuyNumber.setText("" + number);
                }
                break;
                case R.id.push: {
                    if (editBuyNumber.getText().toString().trim().equals(""))
                        editBuyNumber.setText("1");
                    int number = Integer.parseInt(editBuyNumber.getText().toString().trim());


                    //添加代码部分


                    int beaNumber = Integer.parseInt(bean.getText().toString().trim());
                    if (number > 1 && beaNumber > 1) {
                        number--;

                        //添加代码部分


                        beaNumber -= kaixnidou;
                        //价格联动改变
                        double numPrice = Double.parseDouble(price.getText().toString().trim());
                        numPrice -= mPric;

                        DecimalFormat df = new DecimalFormat("#.00");
                        price.setText(df.format(numPrice) + "");

                        editBuyNumber.setText(number + "");
                        bean.setText("" + beaNumber);
                    }
                }
                break;
                case R.id.buy: {


                    //云购和慧购执行不同的跳转
                    if (HuiBuyChoiceSthPageActivity.this.getIntent().getBooleanExtra("bl", false)) {
                        //收货地址不为空的判断
                        if (addressid.equals("")) {
                            Toast.makeText(context, "收货地址不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            float a = Float.parseFloat(HuiBuyChoiceSthPageActivity.this.getIntent().getStringExtra("c_price"));

                            int b = Integer.parseInt(HuiBuyChoiceSthPageActivity.this.getIntent()
                                    .getStringExtra("num"));
                            float c = a * b;
                            Intent intent = new Intent(HuiBuyChoiceSthPageActivity.this,
                                    ALiPayPayActivity.class);
                            intent.putExtra("isCloud", true);
                            //云购支付需要 云购id 购买数量 支付总金额 用户收货地址id
                            intent.putExtra("cid", HuiBuyChoiceSthPageActivity.this.getIntent()
                                    .getStringExtra("cid"));//id
                            intent.putExtra("num", HuiBuyChoiceSthPageActivity.this.getIntent()
                                    .getStringExtra("num"));//购买数量
                            intent.putExtra("name", HuiBuyChoiceSthPageActivity.this.getIntent()
                                    .getStringExtra("name"));//商品名
                            intent.putExtra("info", HuiBuyChoiceSthPageActivity.this.getIntent()
                                    .getStringExtra("info"));//商品描述
                            intent.putExtra("c_price", c + "");//支付总金额
                            intent.putExtra("address", addressid);//收货地址id
                            intent.putExtra("orderno", getIntent().getStringExtra("orderno"));
                            intent.putExtra("yung", true);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        LOD lod = new LOD(context);
                        String url = API.HUI_BUY_NOW + "&uid=" + lod.get("USER_INFO", "UID", "") + "&goodsid=" + GID + "&productid=" + PID + "&addressid=" + addressid + "&num=" + editBuyNumber.getText().toString();
                        Log.e("惠购商品购买", url);
                        CreateHuiOrderHandler createHuiOrderHandler = new CreateHuiOrderHandler();
                        BackgroundTask backgroundTask = new BackgroundTask(context, url, createHuiOrderHandler);
                        backgroundTask.doInBackground();
                    }


                }
                break;
                case R.id.add_tr://加入购物车
                    System.out.println("加入购物车点击");

                    addNum(editBuyNumber.getText().toString());

                    break;
            }
        }
    }

    //    public void statistical(String s) {
//        String ids = "";
//        for (int i = 0; i < cloudList.size(); i++) {
//            if ((Boolean) huiItemChecked.get(i).get("check")) {
//                //统计商品id“，”隔开
//                ids += cloudList.get(i).get("cid") + ",";
//            }
//        }
//        if (ids.equals("")){
//            Toast.makeText(context,"商品未选择",Toast.LENGTH_SHORT).show();
//        }else{
//            //添加云购订单
//            ProgressDialog progressDialog = new ProgressDialog(context);
//            progressDialog.setMessage("获取地址中...");
//            progressDialog.show();
//
//            LOD lod = new LOD(context);
//            String address = "http://www.qkxmall.com/index.php?m=api&c=cloud&a=multibuy" + "&uid=" + lod.get("USER_INFO", "UID", "")+"&cid="+ids.substring(0, ids.length() - 1)+"&num=1"+"&address="+s;
//            System.out.println("！！！！！！！！！云购订单生成：" + address);
//            BackgroundTask backgroundTask = new BackgroundTask(context, address, getMyAddress2);
//            backgroundTask.doInBackground();
//        }
//
//    }
    public void addNum(String num) {
        {
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case BackgroundTask.SUCCESS: {
                            Bundle bundle = msg.getData();
                            if (bundle != null) {
                                Log.e("result", bundle.getString("result"));
                                try {
                                    JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                                    if (jsonObject.getBoolean("flag")) {
                                        Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
//                                        HuiAddToCartActivity.this.finish();
                                    } else {
                                        Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        break;
                        case BackgroundTask.FAILURE: {
                            Toast.makeText(context, "添加失败，请稍后再试", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
            };
            LOD lod = new LOD(context);
            String url = API.HUI_ADD_TO_CART +
                    "&uid=" + lod.get("USER_INFO", "UID", "") +
                    "&goods_id=" + GID +
                    "&product_id=" + getIntent().getStringExtra("pid") + "&num=" + num;
            BackgroundTask backgroundTask = new BackgroundTask(context, url, handler);
            backgroundTask.doInBackground();
        }
    }

    private class LoadDetailInfo extends Handler {
        ProgressDialog progressDialog;

        public LoadDetailInfo(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                final JSONObject object = jsonArray.getJSONObject(i);
                                String[] images = ((String) object.getString("list_img")).split(",");

                                //原商品价格和可用开心豆数量 初始化
//                                c_price.setText(object.getString("shop_price"));
//                                bean.setText(object.getString("kaixindou"));
                                kaixnidou = Integer.parseInt(bean.getText().toString());
                                picture.setImageURI(Uri.parse(API.ADD + images[0]));
                                picture.setTag(Uri.parse(API.ADD + images[0]));

                                editBuyNumber.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                        if (editBuyNumber.getText().toString().trim().equals("")) {

                                        } else {
//                                            try {
//                                                double priceIs = Integer.parseInt(editBuyNumber.getText().toString().trim()) * Double.parseDouble(object.getString("shop_price"));
//                                                c_price.setText(priceIs + "");
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
                                        }
                                    }
                                });
                                editBuyNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                    @Override
                                    public void onFocusChange(View v, boolean hasFocus) {
                                        if (!hasFocus) {
                                            if (editBuyNumber.getText().toString().trim().equals("")) {
                                                editBuyNumber.setText("1");
                                            }
                                        }
                                    }
                                });

//                                SpecAdapter specAdapter = new SpecAdapter(context,specList);
//                                select.setAdapter(specAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (!getIntent().getBooleanExtra("bl", false)) {
                                Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE: {
                    progressDialog.dismiss();
                    Toast.makeText(context, "数据请求失败,请检查网络链接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class LoadAddress extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {


                            JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if (jsonObject.getInt("isdefault") == 1) {
                                    name.setText(jsonObject.getString("addressname"));
                                    phone.setText(jsonObject.getString("mobile"));
                                    address.setText(jsonObject.getString("provincename") +
                                            jsonObject.getString("cityname") +
                                            jsonObject.getString("districtname") +
                                            jsonObject.getString("address"));
                                    addressid = jsonObject.getString("id");
                                    break;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                            Toast.makeText(context, "请先添加地址", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "地址加载失败，请检查网络链接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class CreateHuiOrderHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));

                            if (jsonObject.has("flag")) {
                                if (jsonObject.getBoolean("flag")) {

                                    // TODO: 01/19/2016
                                    String orderid = jsonObject.getString("orderid");
                                    Intent intent = new Intent(HuiBuyChoiceSthPageActivity.this, HuiBuyPayActivity.class);
                                    if (TextUtils.isEmpty(orderid)) {
                                        Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    intent.putExtra("orderid", orderid);
                                    intent.putExtra("c_price", price.getText().toString() + "");//支付总金额
                                    intent.putExtra("info", HuiBuyChoiceSthPageActivity.this.getIntent()
                                            .getStringExtra("info"));//商品描述
                                    System.out.println("!!!!!!!商品详细描述：" +
                                            HuiBuyChoiceSthPageActivity.this.getIntent()
                                                    .getStringExtra("info"));
//                                intent.putExtra("kaixindou", Integer.parseInt(bean.getText()
//                                        .toString().trim()));
                                    System.out.println("!!!!!!!!!!!!!!!!! kaixibdou" + bean.getText().toString().trim());
                                    intent.putExtra("num", Integer.parseInt(editBuyNumber.getText().toString()));
                                    intent.putExtra("name",getIntent().getStringExtra("name"));
                                    intent.putExtra("img",picture.getTag().toString());
                                    intent.putExtra("guige",getIntent().getStringExtra("guige"));

                                    Log.e("惠购支付订单TabID", orderid);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                            System.out.println("!!!!!!!!!!!!!!!!!!  " + e);
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据请求失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

}
