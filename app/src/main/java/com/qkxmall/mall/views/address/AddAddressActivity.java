package com.qkxmall.mall.views.address;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.nets.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddAddressActivity extends Activity {
    private ImageView backup = null;
    private Button save = null;
    Context context;
    private Spinner province = null;
    private Spinner city = null;
    private Spinner area = null;
    private CheckBox setDefault;

    private String provenceID = "1";
    private String cityID = "1";
    private String areaID = "1";

    private TextView provinceView = null;
    private TextView cityView = null;
    private TextView areaView = null;

    private EditText address = null;
    private EditText name = null;
    private EditText phone = null;
    private EditText zipCode = null;


    private String provencePosition = "";
    private String cityPosition = "";
    private String areaPosition = "";
    private String isDefaultText = "0";

    //是否保存了地址
    public static boolean     isSave = false;

    List<HashMap<String ,Object>> provenceList = new ArrayList<>();
    List<HashMap<String ,Object>> cityList = new ArrayList<>();
    List<HashMap<String ,Object>> areaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_receiving_address);
        context = AddAddressActivity.this;
        init();

        LoadProvence loadProvence = new LoadProvence();
        String address = API.GET_REGION+"&pid=1";
        BackgroundTask backgroundTask = new BackgroundTask(context,address,loadProvence);
        backgroundTask.doInBackground();

        backup.setOnClickListener(new OnClickListeners());
        save.setOnClickListener(new OnClickListeners());
        setDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isDefaultText = "1";
                }else {
                    isDefaultText = "0";
                }
            }
        });
    }

    protected  void onResume()
    {
        super.onResume();

        isSave = false;
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.account_address_manage_add_address_backup);
        save = (Button) findViewById(R.id.save);
        province = (Spinner) findViewById(R.id.province);
        city = (Spinner) findViewById(R.id.city);
        area = (Spinner) findViewById(R.id.area);
        address = (EditText) findViewById(R.id.address);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        zipCode = (EditText) findViewById(R.id.zipCode);
        setDefault = (CheckBox) findViewById(R.id.setDefault);

        provinceView = (TextView) findViewById(R.id.provinceView);
        cityView = (TextView) findViewById(R.id.cityView);
        areaView = (TextView) findViewById(R.id.areaView);

        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        province.setMinimumWidth(displayMetrics.widthPixels / 3);
        city.setMinimumWidth(displayMetrics.widthPixels/3);
        area.setMinimumWidth(displayMetrics.widthPixels/3);

    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.account_address_manage_add_address_backup: {
                    AddAddressActivity.this.finish();
                }
                    break;
                case R.id.save:{
                    if (!address.getText().toString().trim().equals("")){
                        if (!name.getText().toString().trim().equals("")){
                            if (!phone.getText().toString().trim().equals("")){
                                if (! zipCode.getText().toString().trim().equals("")){
                                    // TODO: 01/17/2016
                                    ProgressDialog progressDialog = new ProgressDialog(context);
                                    progressDialog.setMessage("需要添加收货地址接口...");
                                    progressDialog.show();

                                    //获取用户UID
                                    SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO",Context.MODE_PRIVATE);
                                    boolean isLogin = sharedPreferences.getBoolean("isLogin",false);
                                    if (isLogin)
                                    {
                                        String tempAddr = address.getText().toString().trim();
                                        String tempName = name.getText().toString().trim();
                                        String tempPhone = phone.getText().toString().trim();
                                        String tempZip = zipCode.getText().toString().trim();

                                        String uid = sharedPreferences.getString("UID" , "");
                                        String url = API.ADD_ADDRESS + "&uid=" + uid + "&addressname=" + tempName +
                                                "&province=" + provenceID + "&city=" + cityID +
                                                "&district=" + areaID + "&address=" + tempAddr +
                                                "&zip=" + tempZip + "&mobile=" + tempPhone;
                                        Log.e("sp_id",provenceID+","+cityID+","+areaID);
                                        SaveAddress saveAddress  = new SaveAddress(progressDialog);
                                        BackgroundTask backgroundTask = new BackgroundTask(context,url,saveAddress);
                                        backgroundTask.doInBackground();
                                    }
                                    else
                                    {
                                        Toast.makeText(context, "请先登入", Toast.LENGTH_LONG).show();
                                    }


                                }else {
                                    Toast.makeText(context, "请输入youbian", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(context, "请输入收件人电话", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(context, "请输入收件人姓名", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context, "请输入详细地址", Toast.LENGTH_SHORT).show();
                    }
                }
                    break;
            }
        }
    }

    /**
     * 获取区域列表
     */
    private class LoadProvence extends Handler {
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
                            province.setAdapter(provenceAdapter);
                            for (int i = 0 ;i < provenceList.size();i++){
                                if (getIntent().getStringExtra("provinceid") != null)
                                if (getIntent().getStringExtra("provinceid").equals(provenceList.get(i).get("provinceid"))){
                                    province.setSelection(i);
                                }
                            }
                            province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    provenceID = (String) provenceList.get(position).get("area_id");
                                    provencePosition = position+"";
                                    provinceView.setText((CharSequence) provenceList.get(position).get("area_name"));
                                    cityView.setText("");
                                    areaView.setText("");

                                    /**
                                     * 获取城市列表
                                     */
                                    LoadCity loadCity = new LoadCity();
                                    String address = API.GET_REGION + "&pid=1&pid=" + provenceList.get(position).get("area_id");
                                    BackgroundTask backgroundTask = new BackgroundTask(context, address, loadCity);
                                    backgroundTask.doInBackground();
                                    Log.e("cityAddress", address);
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


    /**
     * 获取城市列表
     */
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
                                if (getIntent().getStringExtra("cityid") != null)
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
                                    cityView.setText((CharSequence) cityList.get(position).get("area_name"));
                                    areaView.setText("");
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

    /**
     * 获取区域列表
     */
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
                                if (getIntent().getStringExtra("districtid") !=null)
                                if (getIntent().getStringExtra("districtid").equals(areaList.get(i).get("districid"))){
                                    area.setSelection(i);
                                }
                            }
                            area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    areaID = (String) areaList.get(position).get("area_id");
                                    areaPosition = position+"";
                                    areaView.setText((CharSequence) areaList.get(position).get("area_name"));
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

    /**
     * 保存地址
     */
    private class SaveAddress extends Handler{
        ProgressDialog progressDialog;

        public SaveAddress(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    progressDialog.dismiss();
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            if (jsonObject.getBoolean("flag")){
                                Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                isSave = true;
                                AddAddressActivity.this.finish();
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
                    progressDialog.dismiss();
                    Toast.makeText(context, "保存失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                    break;
            }
        }
    }


}
