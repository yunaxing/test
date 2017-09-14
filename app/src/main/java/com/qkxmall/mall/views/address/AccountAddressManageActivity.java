package com.qkxmall.mall.views.address;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
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

public class AccountAddressManageActivity extends Activity {
    Context context;
    private TextView setDefault = null;
    private TextView deleteAddress = null;
    private ImageView backup = null;
    private ScrollListView address = null;
    private TextView nothing = null;
    private Button add = null;

    List<HashMap<String ,Object>> addressList = new ArrayList<>();
    AddressAdapter addressAdapter;

    List<HashMap<String ,Object>> checkedList = new ArrayList<>();
    CheckedChange checkedChange = new CheckedChange();
    int checkedPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_address_manage);
        context = AccountAddressManageActivity.this;
        init();
        LOD lod = new LOD(context);

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("获取地址中,请稍候...");
        progressDialog.show();
        GetUserAdddress getUserAdddress = new GetUserAdddress(progressDialog);
        String url = API.USER_ADDRESS_LIST+"&uid="+lod.get("USER_INFO","UID","");
        BackgroundTask backgroundTask = new BackgroundTask(context,url,getUserAdddress);
        backgroundTask.doInBackground();

        backup.setOnClickListener(new OnClickListeners());
        add.setOnClickListener(new OnClickListeners());
        deleteAddress.setOnClickListener(new OnClickListeners());
        setDefault.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.account_address_manage_backup);
        setDefault = (TextView) findViewById(R.id.account_address_manage_set_default_address);
        deleteAddress = (TextView) findViewById(R.id.account_address_manage_delete_address);
        address = (ScrollListView) findViewById(R.id.account_manage_manage_address_list);
        add = (Button) findViewById(R.id.account_manage_manage_add_address);
        nothing = (TextView) findViewById(R.id.nothing);
    }

    protected  void onResume()
    {
        super.onResume();

        //如果是保存成功则刷新
        if (AddAddressActivity.isSave)
        {
            AddAddressActivity.isSave = false;

            LOD lod = new LOD(context);
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("获取地址中,请稍候...");
            progressDialog.show();
            GetUserAdddress getUserAdddress = new GetUserAdddress(progressDialog);
            String url = API.USER_ADDRESS_LIST+"&uid="+lod.get("USER_INFO","UID","");
            BackgroundTask backgroundTask = new BackgroundTask(context,url,getUserAdddress);
            backgroundTask.doInBackground();

        }
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.account_address_manage_backup:
                    AccountAddressManageActivity.this.finish();
                    break;
                case R.id.account_manage_manage_add_address:
                    startActivity(new Intent(AccountAddressManageActivity.this,AddAddressActivity.class));
                    break;
                //删除：
                case R.id.account_address_manage_delete_address: {
                    if (checkedPosition != -1) {
                        LOD lod = new LOD(context);
                        ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage("删除中...");
                        progressDialog.show();
                        DeleteUserAddress deleteUserAddress = new DeleteUserAddress(progressDialog);
                        String deleteAddress = API.DELETE_ADDRESS + "&uid=" + lod.get("USER_INFO", "UID", "") + "&addressid="+addressList.get(checkedPosition).get("id");
                        BackgroundTask backgroundTask = new BackgroundTask(context,deleteAddress,deleteUserAddress);
                        backgroundTask.doInBackground();
                    }else {
                        Toast.makeText(context, "请选择一个地址", Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
                //设置默认：
                case R.id.account_address_manage_set_default_address:{
                    if (checkedPosition != -1) {
                        LOD lod = new LOD(context);
                        Handler handler = new Handler(){
                            @Override
                            public void handleMessage(Message msg) {
                                switch (msg.what){
                                    case BackgroundTask.SUCCESS:{
                                        Bundle bundle = msg.getData();
                                        if (bundle != null && !bundle.getString("result").equals("null")){
                                            String result = bundle.getString("result");
                                            if (result.equals("succ")){
                                                Toast.makeText(context, "设置成功", Toast.LENGTH_SHORT).show();
                                                addressAdapter.notifyDataSetChanged();
                                            }else {
                                                Toast.makeText(context, "设置失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                        break;
                                    case BackgroundTask.FAILURE:{
                                        Toast.makeText(context, "数据请求失败，请检查网络", Toast.LENGTH_SHORT).show();
                                    }
                                        break;
                                }
                            }
                        };
                        String deleteAddress = API.SET_DEFAULT_ADDRESS + "&uid=" + lod.get("USER_INFO", "UID", "") + "&addressid="+addressList.get(checkedPosition).get("id");
                        BackgroundTask backgroundTask = new BackgroundTask(context,deleteAddress,handler);
                        backgroundTask.doInBackground();
                    }else {
                        Toast.makeText(context, "请选择一个地址", Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
            }
        }
    }

    private class GetUserAdddress extends Handler{
        ProgressDialog progressDialog;

        public GetUserAdddress(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null && !bundle.getString("result").equals("null")){
                        try {

                            JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                            addressList.clear();
                            for (int i = 0 ; i< jsonArray.length() ;i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HashMap<String ,Object> data = new HashMap<>();
                                data.put("id", jsonObject.get("id"));
                                data.put("addressname", jsonObject.get("addressname"));
                                data.put("isdefault", jsonObject.get("isdefault"));
                                data.put("provinceid", jsonObject.get("provinceid"));
                                data.put("provincename", jsonObject.get("provincename"));
                                data.put("cityid", jsonObject.get("cityid"));
                                data.put("cityname", jsonObject.get("cityname"));//
                                data.put("distrivtid", jsonObject.get("districtid"));
                                data.put("districtname", jsonObject.get("districtname"));
                                data.put("address", jsonObject.get("address"));
                                data.put("zipcode", jsonObject.get("zipcode"));
                                data.put("mobile", jsonObject.get("mobile"));
                                data.put("select" , 0);
                                addressList.add(data);
                            }

                            for (int i = 0 ; i< addressList.size() ; i++){
                                HashMap<String ,Object> map = new HashMap<>();
                                map.put("check",false);
                                checkedList.add(map);
                            }

                            addressAdapter = new AddressAdapter(context,addressList,checkedChange);
                            if (addressList.size() == 0){
                                nothing.setVisibility(View.VISIBLE);
                            }else {
                                nothing.setVisibility(View.GONE);
                                //address.removeAllViews();
                                address.setAdapter(addressAdapter);
                                addressAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
                }
                    break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(context, "数据请求失败，请检查网络", Toast.LENGTH_SHORT).show();
                }
                    break;
            }
        }
    }

    /**
     * 删除地址回调
     */
    private class DeleteUserAddress extends Handler{
        ProgressDialog progressDialog;

        public DeleteUserAddress(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null && !bundle.getString("result").equals("null")){
                        String result = bundle.getString("result");

                        if (result.equals("succ")){
                            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                            addressList.remove(checkedPosition);
                            addressAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(context, "数据请求失败，请检查网络", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class CheckedChange extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.CHECKED:{
                    Bundle bundle = msg.getData();
                    int position = Integer.parseInt(bundle.getString("position"));
                    checkedList.remove(position);
                    HashMap<String ,Object> map = new HashMap<>();
                    map.put("check",true);
                    checkedList.add(position,map);
                    checkedPosition = position;
                }
                    break;
                case BackgroundTask.UN_CHECKED:{
                    Bundle bundle = msg.getData();
                    int position = Integer.parseInt(bundle.getString("position"));
                    checkedList.remove(position);
                    HashMap<String ,Object> map = new HashMap<>();
                    map.put("check",false);
                    checkedList.add(position,map);
                }
                    break;
            }
        }
    }
}
