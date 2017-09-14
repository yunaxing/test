package com.qkxmall.mall.views.address;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.views.ScrollListView;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddressSelectActivity extends AppCompatActivity {

    private Context context;
    private ImageView navigation = null;
    private TextView add = null;
    private ScrollListView listView;
    private FrameLayout frameLayout;
    List<HashMap<String ,Object>> addressList = new ArrayList<>();
    List<HashMap<String ,Object>> statusList = new ArrayList<>();

    TextView nothing = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_address_select);
        setContentView(R.layout.content_address_select);
        context = AddressSelectActivity.this;
        init();
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("获取地址中...");
        progressDialog.show();
        getMyAddress getMyAddress = new getMyAddress(progressDialog);
        LOD lod = new LOD(context);
        String address = API.USER_ADDRESS_LIST+"&uid="+lod.get("USER_INFO","UID","");
        BackgroundTask backgroundTask = new BackgroundTask(context,address,getMyAddress);
        backgroundTask.doInBackground();

        navigation.setOnClickListener(new OnClick());
        add.setOnClickListener(new OnClick());
    }

    @Override
    protected void onResume() {
        super.onResume();
        context = AddressSelectActivity.this;
        init();
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("获取地址中...");
        progressDialog.show();
        getMyAddress getMyAddress = new getMyAddress(progressDialog);
        LOD lod = new LOD(context);
        String address = API.USER_ADDRESS_LIST+"&uid="+lod.get("USER_INFO","UID","");
        BackgroundTask backgroundTask = new BackgroundTask(context,address, getMyAddress);
        backgroundTask.doInBackground();
    }

    private void init() {
        navigation = (ImageView) findViewById(R.id.navigation);
        add = (TextView) findViewById(R.id.add);
        listView = (ScrollListView) findViewById(R.id.address);
        frameLayout = (FrameLayout) findViewById(R.id.alertRoot);
        nothing = (TextView) findViewById(R.id.nothing);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.navigation:{
                    AddressSelectActivity.this.finish();
                }
                    break;
                case R.id.add:{
                    Intent intent = new Intent(AddressSelectActivity.this,AddAddressActivity.class);
                    startActivity(intent);

                }
                    break;
            }
        }
    }

    private class getMyAddress extends Handler{

        ProgressDialog progressDialog;

        public getMyAddress(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        String result = bundle.getString("result");
                        try {
                            if (!bundle.getString("result").equals("null")){
                                addressList.clear();
                                JSONArray jsonArray = new JSONArray(result);
                                for (int i = 0 ; i< jsonArray.length() ;i++){
                                    HashMap<String ,Object> data = new HashMap<>();
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    data.put("id", jsonObject.get("id"));
                                    data.put("addressname", jsonObject.get("addressname"));
                                    data.put("isdefault", jsonObject.get("isdefault"));
                                    data.put("provinceid", jsonObject.get("provinceid"));
                                    data.put("provincename", jsonObject.get("provincename"));
                                    data.put("cityid", jsonObject.get("cityid"));
                                    data.put("cityname", jsonObject.get("cityname"));
                                    data.put("districtid", jsonObject.get("districtid"));
                                    data.put("districtname", jsonObject.get("districtname"));
                                    data.put("address", jsonObject.get("address"));
                                    data.put("zipcode", jsonObject.get("zipcode"));
                                    data.put("mobile", jsonObject.get("mobile"));
                                    addressList.add(data);
                                    nothing.setVisibility(View.GONE);
                                }
                            }else {
                                nothing.setVisibility(View.VISIBLE);
                            }

                            statusList = new ArrayList<>();
                            for (int i = 0 ; i< addressList.size() ;i++){
                                HashMap<String ,Object> statusMap = new HashMap<>();
                                if (addressList.get(i).get("isdefault").equals("1")){
                                    statusMap.put("status",true);
                                }else {
                                    statusMap.put("status",false);
                                }
                                statusList.add(statusMap);
                            }
                            AddressSelectorAdapter addressSelectorAdapter;
                            ItemClickHandler itemClickHandler = new ItemClickHandler();
                            addressSelectorAdapter = new AddressSelectorAdapter(context,itemClickHandler,addressList,statusList);
                            listView.setAdapter(addressSelectorAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    progressDialog.dismiss();
                }
                    break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(context, "数据加载失败...", Toast.LENGTH_SHORT).show();
                }
                    break;
            }
        }
    }

    private class ItemClickHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            if (bundle != null){
                int position = Integer.parseInt(bundle.getString("position"));
                switch (msg.what){
                    case BackgroundTask.CLICK:{
                        Intent intent = new Intent();
                        intent.putExtra("addressid", (String) addressList.get(position).get("id"));
                        System.out.println("！！！！！！！！返回的 地址id："+addressList.get(position).get("id"));
                        setResult(RESULT_OK, intent);
                        AddressSelectActivity.this.finish();
                    }
                    break;
                    case BackgroundTask.LONG_CLICK:{
                        ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage("请等待...");
//                        progressDialog.show();
                        if (frameLayout.getVisibility() == View.GONE){
                            frameLayout.setVisibility(View.VISIBLE);
                        }
                        AlertFragment alertFragment = new AlertFragment();
                        ShowFragment showFragment = new ShowFragment();
                        alertFragment.newInstance(context, showFragment);
                        Bundle bundleTransport = new Bundle();
                        bundleTransport.putString("id", (String) addressList.get(position).get("id"));
                        bundleTransport.putString("username", (String) addressList.get(position).get("addressname"));
                        bundleTransport.putString("provinceid", (String) addressList.get(position).get("provinceid"));
                        bundleTransport.putString("cityid", (String) addressList.get(position).get("cityid"));
                        bundleTransport.putString("districtid", (String) addressList.get(position).get("districtid"));
                        bundleTransport.putString("address", (String) addressList.get(position).get("address"));
                        bundleTransport.putString("zipcode", (String) addressList.get(position).get("zipcode"));
                        bundleTransport.putString("mobile", (String) addressList.get(position).get("mobile"));
                        bundleTransport.putString("isdefault", (String) addressList.get(position).get("isdefault"));
                        alertFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.alertRoot,alertFragment).commit();
                    }
                    break;
                }
            }
        }
    }

    private class ShowFragment extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.IS_SHOW:{
                    frameLayout.setVisibility(View.INVISIBLE);
                }
                    break;
            }
        }
    }
}
