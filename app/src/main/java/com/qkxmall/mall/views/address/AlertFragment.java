package com.qkxmall.mall.views.address;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ViewHolder simple {@link Fragment} subclass.
 * Use the {@link AlertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlertFragment extends Fragment {
    private String ID;
    private Context context;
    private Handler handler;
    private ListView listView = null;
    private CardView cardView = null;

    private String addressName;
    private String provinceid;
    private String cityid;
    private String districtid;
    private String address;
    private String zipcode;
    private String mobile;
    private String isDefault;



    public AlertFragment() {
        // Required empty public constructor
    }
    public void newInstance(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ID = getArguments().getString("id");
            addressName = getArguments().getString("addressname");
            provinceid = getArguments().getString("peocinvrid");
            cityid = getArguments().getString("cityid");
            districtid =getArguments().getString("districtid");
            address = getArguments().getString("address");
            zipcode = getArguments().getString("zipcode");
            mobile = getArguments().getString("mobile");
            isDefault = getArguments().getString("isdefault");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_alert, container, false);
        init(rootView);
        String[] item = new String[]{"删除","编辑","设为默认地址"};
        List<HashMap<String ,Object>> section = new ArrayList<>();
        for (int i = 0 ; i< item.length ;i++){
            HashMap<String ,Object> hashMap = new HashMap<>();
            hashMap.put("item",item[i]);
            section.add(hashMap);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(context,section,R.layout.child_text_item,new String[]{"item"},new int[]{R.id.text});
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        cardView.setMaxCardElevation(displayMetrics.widthPixels / 5);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage("删除中...");
                        progressDialog.show();
                        DeleteAddressHandler deleteAddressHandler = new DeleteAddressHandler(progressDialog);
                        String address = API.DELETE_ADDRESS+"&id="+ID;
                        Log.e("deleteAddress",address);
                        BackgroundTask backgroundTask = new BackgroundTask(context,address,deleteAddressHandler);
                        backgroundTask.doInBackground();
                    }
                        break;
                    case 1:{
                        Intent intent = new Intent(context,EditAddressActivity.class);
                        intent.putExtra("id",ID);
                        intent.putExtra("addressname",addressName);
                        intent.putExtra("isdefault",isDefault);
                        intent.putExtra("provinceid",provinceid);
                        intent.putExtra("cityid",cityid);
                        intent.putExtra("districtid",districtid);
                        intent.putExtra("address",address);
                        intent.putExtra("zipcode",zipcode);
                        intent.putExtra("mobile",mobile);
                        context.startActivity(intent);
                        Message message = new Message();
                        message.what = BackgroundTask.IS_SHOW;
                        handler.sendMessage(message);
                    }
                        break;
                    case 2:{
                        Handler handler = new Handler(){
                            @Override
                            public void handleMessage(Message msg) {
                                switch (msg.what){
                                    case BackgroundTask.SUCCESS:{
                                        Bundle bundle = msg.getData();
                                        if (bundle != null){
                                            String res = bundle.getString("result");
                                            if (res.equals("succ")){
                                                Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
                                            }else {
                                                Toast.makeText(context, "修改失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                        break;
                                    case BackgroundTask.FAILURE:{
                                        Toast.makeText(context, "网络连接故障，请重试", Toast.LENGTH_SHORT).show();
                                    }
                                        break;
                                }
                            }
                        };
                        LOD lod = new LOD(context);
                        String addressUrl = API.SET_DEFAULT_ADDRESS+"&addressid="+ID+"&uid="+lod.get("USER_ID","UID","");
                        BackgroundTask backgroundTask = new BackgroundTask(context,addressUrl,handler);
                        backgroundTask.doInBackground();
                    }
                        break;
                }
            }
        });
        return rootView;
    }

    private void init(View rootView) {
        listView = (ListView) rootView.findViewById(R.id.list);
        cardView = (CardView) rootView.findViewById(R.id.card);
    }

    private class DeleteAddressHandler extends Handler{
        ProgressDialog progressDialog;

        public DeleteAddressHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle!=null){
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE:
                    break;
            }
            Message message = new Message();
            message.what = BackgroundTask.IS_SHOW;
            handler.sendMessage(message);
        }
    }

}
