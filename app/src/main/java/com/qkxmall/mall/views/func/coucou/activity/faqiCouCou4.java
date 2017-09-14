package com.qkxmall.mall.views.func.coucou.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.address.AddressSelectActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yuna on 2017/8/5.
 */

public class faqiCouCou4 extends Activity implements View.OnClickListener{

    private ImageView img;
    private ImageView imgClose;
    private TextView name = null;
    private TextView phone = null;
    private TextView address = null;
    private TextView goodName;
    private LinearLayout addressLine;
    private Button btnAdd;
    private Button btnJian;
    private TextView tvCount;
    private Button btnConfirm;
    String addressid = "";
    String listImg;
    private int count = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_faqi_coucou_4);
        listImg = getIntent().getStringExtra("imgs");
        initView();
        initData();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
//        p.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的0.3
        p.width = (int) (d.getWidth() * 1); // 宽度设置为屏幕的0.7
        getWindow().setAttributes(p);
    }
    private void initView(){
        img = (SimpleDraweeView)findViewById(R.id.image_title);
        name = (TextView) findViewById(R.id.name);
        goodName = (TextView) findViewById(R.id.tv_name);
        phone = (TextView) findViewById(R.id.phone);
        address = (TextView) findViewById(R.id.address);
        addressLine = (LinearLayout) findViewById(R.id.addressLine);
        btnConfirm = (Button)findViewById(R.id.btn_confirm);
        imgClose = (ImageView)findViewById(R.id.img_close);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnConfirm.setOnClickListener(this);
        btnAdd = (Button)findViewById(R.id.btn_jia);
        btnJian = (Button)findViewById(R.id.btn_jian);
        btnJian.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        tvCount = (TextView)findViewById(R.id.tv_count);
        addressLine.setOnClickListener(this);
        goodName.setText(getIntent().getStringExtra("name"));
    }

    private void initData(){
        String[] imageArray = listImg.split(",");
        img.setImageURI(Uri.parse(API.ADD+imageArray[0]));
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.addressLine: {
//                    Intent intent = new Intent(HuiBuyChoiceSthPageActivity.this, AccountAddressManageActivity.class);
                Intent intent = new Intent(this, AddressSelectActivity.class);
                startActivityForResult(intent, 100);
                break;
            }
            case R.id.btn_jia:
                count=count+1;
                tvCount.setText(count+"");
                break;
            case R.id.btn_jian:
                if(count >1){
                    count = count-1;
                    tvCount.setText(count+"");
                }
                break;
            case R.id.btn_confirm:
                if(addressid.isEmpty()){
                    Toast.makeText(getApplication(),"请填写地址信息",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(this, faqiCouCou5.class).putExtra("create_pay", getIntent().getStringExtra("create_pay")
                ).putExtra("cid", getIntent().getStringExtra("cid")).putExtra("num",count).putExtra("addressid", addressid).putExtra("price",getIntent().getStringExtra("price"));
                startActivity(intent);
                finish();
                break;
        }

    }

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
                                    Toast.makeText(getApplication(), "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        break;
                        case BackgroundTask.FAILURE: {
                            Toast.makeText(getApplication(), "地址设置失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
            };
            LOD lod = new LOD(this);
            String addressUrl = API.USER_ADDRESS_LIST + "&uid=" + lod.get("USER_INFO", "UID", "");
            BackgroundTask backgroundTask = new BackgroundTask(this, addressUrl, handler);
            backgroundTask.doInBackground();
        }
    }
}