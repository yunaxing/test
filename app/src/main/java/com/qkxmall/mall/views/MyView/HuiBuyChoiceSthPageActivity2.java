package com.qkxmall.mall.views.MyView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.views.ScrollListView;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.address.AddressSelectActivity;
import com.qkxmall.mall.views.hui.HuiBuyPayActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HuiBuyChoiceSthPageActivity2 extends Activity {

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

    public int kaixindou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_buy_choice_sth_page2);
        context = HuiBuyChoiceSthPageActivity2.this;
        init();
        if (getIntent().getStringExtra("id") != null)
            GID = getIntent().getStringExtra("id");
        if (getIntent().getStringExtra("pid") != null)
            PID = getIntent().getStringExtra("pid");
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("数据加载中...");
        progressDialog.show();

        LoadDetailInfo loadDetailInfo = new LoadDetailInfo(progressDialog);
        String address = API.HUI_PRODUCTS_DETAIL+"&gid="+getIntent().getStringExtra("id");
        BackgroundTask backgroundTask = new BackgroundTask(context,address,loadDetailInfo);
//        backgroundTask.doInBackground();

        LoadAddress loadAddress = new LoadAddress();
        LOD lod = new LOD(context);
        String addressUrl = API.USER_ADDRESS_LIST+"&uid="+lod.get("USER_INFO","UID","");
        Log.e("Address",addressUrl);
        BackgroundTask addressTack = new BackgroundTask(context,addressUrl,loadAddress);
        addressTack.doInBackground();

        navigation.setOnClickListener(new OnClick());
        buyNow.setOnClickListener(new OnClick());
        addressLine.setOnClickListener(new OnClick());
        addBuyNumber.setOnClickListener(new OnClick());
        cutBuyNumber.setOnClickListener(new OnClick());
        buyNow.setOnClickListener(new OnClick());
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


        addressLine = (LinearLayout) findViewById(R.id.addressLine);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK){
            Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what){
                        case BackgroundTask.SUCCESS:{
                            Bundle result = msg.getData();
                            if (result != null){
                                try {
                                    JSONArray jsonArray = new JSONArray(result.getString("result"));
                                    for (int i = 0 ;i< jsonArray.length() ;i++){
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        if (data.getStringExtra("addressid") != null)
                                            if (jsonObject.getString("id").equals(data.getStringExtra("addressid"))){
                                                addressid = data.getStringExtra("addressid");
                                                name.setText(jsonObject.getString("addressname"));
                                                phone.setText(jsonObject.getString("mobile"));
                                                address.setText(jsonObject.getString("provincename")+
                                                        jsonObject.getString("cityname")+
                                                        jsonObject.getString("districtname")+
                                                        jsonObject.getString("address"));
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
                        case BackgroundTask.FAILURE:{
                            Toast.makeText(context, "地址设置失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
            };
            LOD lod = new LOD(context);
            String addressUrl = API.USER_ADDRESS_LIST+"&uid="+lod.get("USER_INFO","UID","");
            BackgroundTask backgroundTask = new BackgroundTask(context,addressUrl,handler);
            backgroundTask.doInBackground();
        }
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.navigation: {
                    finish();
                }
                break;
                case R.id.addressLine: {
//                    Intent intent = new Intent(HuiBuyChoiceSthPageActivity.this, AccountAddressManageActivity.class);
                    Intent intent = new Intent(HuiBuyChoiceSthPageActivity2.this, AddressSelectActivity.class);
                    startActivityForResult(intent,100);
                }
                break;
                case R.id.add:{
                    if (editBuyNumber.getText().toString().trim().equals(""))
                        editBuyNumber.setText("1");

                    int number = Integer.parseInt(editBuyNumber.getText().toString().trim());
                    number++;


                    //添加代码部分以解决惠购中购买数量的增加而可使用开心豆不跟进的问题
                    int beaNumber=Integer.parseInt(bean.getText().toString().trim());
                    beaNumber++;
                    //注释之间的事添加的代码部分

                    bean.setText(""+beaNumber);
                    editBuyNumber.setText("" + number);
                }
                break;
                case R.id.push:{
                    if (editBuyNumber.getText().toString().trim().equals(""))
                        editBuyNumber.setText("1");
                    int number = Integer.parseInt(editBuyNumber.getText().toString().trim());



                    //添加代码部分



                    int beaNumber=Integer.parseInt(bean.getText().toString().trim());
                    if (number>1&&beaNumber>1){
                        number--;

                        //添加代码部分


                        beaNumber--;


                        editBuyNumber.setText(number+"");
                        bean.setText("" + beaNumber);
                    }
                }
                break;
                case R.id.buy:{
                    LOD lod = new LOD(context);
                    String url = API.HUI_BUY_NOW+"&uid="+lod.get("USER_INFO","UID","")+"&goodsid="+GID+"&productid="+PID+"&addressid="+addressid;
                    Log.e("惠购商品购买",url);
                    CreateHuiOrderHandler createHuiOrderHandler = new CreateHuiOrderHandler();
                    BackgroundTask backgroundTask = new BackgroundTask(context,url,createHuiOrderHandler);
                    backgroundTask.doInBackground();

                }
                break;
            }
        }
    }

    private class LoadDetailInfo extends Handler{
        ProgressDialog progressDialog;

        public LoadDetailInfo(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONArray jsonArray =  new JSONArray(bundle.getString("result"));
                            for (int i = 0 ; i< jsonArray.length(); i++){
                                final JSONObject object = jsonArray.getJSONObject(i);
                                String[] images = ((String) object.getString("list_img")).split(",");
                                price.setText(object.getString("shop_price"));
                                bean.setText(object.getString("kaixindou"));
                                kaixindou=Integer.parseInt(object.getString("kaixindou"));
                                picture.setImageURI(Uri.parse(API.ADD + images[0]));

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
                                            try {
                                                double priceIs = Integer.parseInt(editBuyNumber.getText().toString().trim()) * Double.parseDouble(object.getString("shop_price"));
                                                price.setText(priceIs + "");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                                editBuyNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                    @Override
                                    public void onFocusChange(View v, boolean hasFocus) {
                                        if (!hasFocus){
                                            if (editBuyNumber.getText().toString().trim().equals("")){
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
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(context, "数据请求失败,请检查网络链接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class LoadAddress extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                            for (int i = 0 ; i< jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if (jsonObject.getInt("isdefault") == 1){
                                    name.setText(jsonObject.getString("addressname"));
                                    phone.setText(jsonObject.getString("mobile"));
                                    address.setText(jsonObject.getString("provincename")+
                                            jsonObject.getString("cityname")+
                                            jsonObject.getString("districtname")+
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
                case BackgroundTask.FAILURE:{
                    Toast.makeText(context, "地址加载失败，请检查网络链接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class CreateHuiOrderHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            if (jsonObject.getBoolean("flag")){
                                // TODO: 01/19/2016
                                String orderid = jsonObject.getString("orderid");
                                Intent intent = new Intent(HuiBuyChoiceSthPageActivity2.this,HuiBuyPayActivity.class);
                                intent.putExtra("orderid",orderid);
                                Log.e("惠购支付订单TabID",orderid);
                                startActivity(intent);
                            }else {
                                Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE:{
                    Toast.makeText(context, "数据请求失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

}
