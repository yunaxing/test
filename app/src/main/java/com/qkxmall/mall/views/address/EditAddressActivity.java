package com.qkxmall.mall.views.address;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EditAddressActivity extends AppCompatActivity {
    private Context context;
    private Spinner provence = null;
    private Spinner city = null;
    private Spinner area = null;

    private ImageView navigation = null;
    private EditText name = null;
    private EditText detailAddress = null;
    private EditText phone = null;
    private EditText zipCode = null;
    private Button save = null;
    private RadioButton isDefault = null;

    private String provenceID = "";
    private String cityID = "";
    private String areaID = "";
    private String provencePosition = "";
    private String cityPosition = "";
    private String areaPosition = "";
    private String isDefaultText = "0";

    String addressID = "";


    List<HashMap<String ,Object>> provenceList = new ArrayList<>();
    List<HashMap<String ,Object>> cityList = new ArrayList<>();
    List<HashMap<String ,Object>> areaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_edit_address);
        context = EditAddressActivity.this;
        init();
        addressID = getIntent().getStringExtra("addressid");
        LoadProvence loadProvence = new LoadProvence();
        String address = API.GET_REGION+"&pid=1";
        BackgroundTask backgroundTask = new BackgroundTask(context,address,loadProvence);
        backgroundTask.doInBackground();
        addressID = getIntent().getStringExtra("id");
        name.setText(getIntent().getStringExtra("addressname"));
        detailAddress.setText(getIntent().getStringExtra("address"));
        zipCode.setText(getIntent().getStringExtra("zipcode"));
        phone.setText(getIntent().getStringExtra("mobile"));

        navigation.setOnClickListener(new OnClick());
        save.setOnClickListener(new OnClick());
        isDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isDefaultText = "1";
                    isDefault.setText("是");
                }else {
                    isDefaultText = "0";
                    isDefault.setTag("否");
                }
            }
        });
    }

    private void init() {
        navigation = (ImageView) findViewById(R.id.navigation);
        provence = (Spinner) findViewById(R.id.provence);
        city = (Spinner) findViewById(R.id.city);
        area = (Spinner) findViewById(R.id.area);
        save = (Button) findViewById(R.id.save);
        name = (EditText) findViewById(R.id.userName);
        detailAddress = (EditText) findViewById(R.id.address);
        phone = (EditText) findViewById(R.id.phone);
        zipCode = (EditText) findViewById(R.id.zipCode);
        isDefault = (RadioButton) findViewById(R.id.isDefault);

        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        provence.setMinimumWidth(displayMetrics.widthPixels / 3);
        city.setMinimumWidth(displayMetrics.widthPixels/3);
        area.setMinimumWidth(displayMetrics.widthPixels/3);

    }

    private class LoadProvence extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                            for (int i = 0 ; i< jsonArray.length() ;i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HashMap<String ,Object> data = new HashMap<>();
                                data.put("area_id", jsonObject.get("area_id"));
                                data.put("parent_id", jsonObject.get("parent_id"));
                                data.put("area_name", jsonObject.get("area_name"));
                                data.put("sort", jsonObject.get("sort"));
                                provenceList.add(data);
                            }
                            SimpleAdapter provenceAdapter = new SimpleAdapter(context,provenceList,R.layout.child_text_item,new String[]{"area_name"},new int[]{R.id.text});
                            provence.setAdapter(provenceAdapter);
                            for (int i = 0 ;i < provenceList.size();i++){
                                if (getIntent().getStringExtra("provindeid").equals(provenceList.get(i).get("provinceid"))){
                                    provence.setSelection(i);
                                }
                            }
                            provence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    provenceID = (String) provenceList.get(position).get("area_id");
                                    provencePosition = position+"";
                                    LoadCity loadCity = new LoadCity();
                                    String address = API.GET_REGION + "&pid=1&pid=" + provenceList.get(position).get("area_id");
                                    BackgroundTask backgroundTask = new BackgroundTask(context, address, loadCity);
                                    backgroundTask.doInBackground();
                                    Log.e("cityAddress",address);
                                    Log.e("Event","Click Provence");
                                }
                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系工程师", Toast.LENGTH_SHORT).show();
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
    }


    private class LoadCity extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                            cityList.clear();
                            for (int i = 0 ; i< jsonArray.length() ;i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HashMap<String ,Object> data = new HashMap<>();
                                data.put("area_id", jsonObject.get("area_id"));
                                data.put("parent_id", jsonObject.get("parent_id"));
                                data.put("area_name", jsonObject.get("area_name"));
                                data.put("sort", jsonObject.get("sort"));
                                cityList.add(data);
                            }
                            SimpleAdapter cityAdapter = new SimpleAdapter(context,cityList,
                                    R.layout.child_text_item,new String[]{"area_name"},
                                    new int[]{R.id.text});
                            city.setAdapter(cityAdapter);
                            for (int i = 0 ; i< cityList.size() ;i++){
                                if (getIntent().getStringExtra("cityid").equals(cityList.get(i).get("cityid"))){
                                    city.setSelection(i);
                                }
                            }
                            city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view,
                                                           int position, long id) {
                                    String address = API.GET_REGION + "&pid=1&pid=" + provenceID +
                                            "&pid=" + cityList.get(position).get("area_id");
                                    cityID = (String) cityList.get(position).get("area_id");
                                    cityPosition = position+"";
                                    LoadArea loadArea = new LoadArea();
                                    BackgroundTask backgroundTask = new BackgroundTask(context, address, loadArea);
                                    backgroundTask.doInBackground();
                                    Log.e("AreaAddress", address);
                                    Log.e("Event","Click City");
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系工程师", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                    break;
                case BackgroundTask.FAILURE:{
                    Toast.makeText(context, "数据请求失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                    break;
            }
        }
    }

    private class LoadArea extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                            areaList.clear();
                            for (int i = 0 ; i< jsonArray.length() ;i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HashMap<String ,Object> data = new HashMap<>();
                                data.put("area_id", jsonObject.get("area_id"));
                                data.put("parent_id", jsonObject.get("parent_id"));
                                data.put("area_name", jsonObject.get("area_name"));
                                data.put("sort", jsonObject.get("sort"));
                                areaList.add(data);
                            }
                            SimpleAdapter areaAdapter = new SimpleAdapter(context,areaList,
                                    R.layout.child_text_item,new String[]{"area_name"},
                                    new int[]{R.id.text});
                            area.setAdapter(areaAdapter);
                            for (int i = 0 ; i< areaList.size() ;i++){
                                if (getIntent().getStringExtra("districtid").equals(areaList.get(i).get("districid"))){
                                    area.setSelection(i);
                                }
                            }
                            area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    areaID = (String) areaList.get(position).get("area_id");
                                    areaPosition = position+"";
                                    Log.e("Event","Click Area");
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系工程师", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE:{
                    Toast.makeText(context, "数据请求失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.navigation:{
                    EditAddressActivity.this.finish();
                }
                    break;
                case R.id.save:{
                    if (!name.getText().toString().trim().equals("")) {
                        if (!phone.getText().toString().trim().equals("")){
                            if (!detailAddress.getText().toString().trim().equals("")){
                                if (!zipCode.getText().toString().trim().equals("")){
                                    if (!provenceID.equals("")){
                                        if (!cityID.equals("")){
                                            if (!areaID.equals("")){
                                                ProgressDialog progressDialog = new ProgressDialog(context);
                                                progressDialog.setMessage("上传中...");
                                                progressDialog.show();
                                                SaveAddress saveAddress = new SaveAddress(progressDialog);
                                                LOD lod = new LOD(context);
                                                String address = API.UPDATE_ADDRESS + "&uid=" + lod.get("USRT_INFO", "UID", "") +
                                                        "&addressid=" + addressID +
                                                        "&addressname=" + name.getText().toString().trim() +
                                                        "&province=" + provenceID +
                                                        "&city=" + cityID +
                                                        "&district=" + areaID +
                                                        "&address=" + detailAddress.getText().toString().trim() +
                                                        "&zip=" + zipCode.getText().toString().trim() +
                                                        "mobile=" + phone.getText().toString().trim();
                                                BackgroundTask backgroundTask = new BackgroundTask(context,address,saveAddress);
                                                backgroundTask.doInBackground();
                                            }else {
                                                Toast.makeText(context, "请选择区域", Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            Toast.makeText(context, "请选择城市", Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(context, "请选择省份", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(context, "请输入邮编", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(context, "请输入详细地址", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(context, "请输入手机号码", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context, "请输入姓名", Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
            }
        }
    }

    private class SaveAddress extends Handler{
        ProgressDialog progressDialog;

        public SaveAddress(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    progressDialog.dismiss();
                    if (bundle != null){
                        String res = bundle.getString("result");
                        if (res.equals("succ")){
                            Toast.makeText(context, "地址保存成功", Toast.LENGTH_SHORT).show();
                            EditAddressActivity.this.finish();
                        }else if (res.equals("fail")){
                            Toast.makeText(context, "上传失败，请重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                    break;
                case BackgroundTask.FAILURE:{
                    Toast.makeText(context, "数据请求失败，请检查网络链接", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                    break;
            }
        }
    }

}
