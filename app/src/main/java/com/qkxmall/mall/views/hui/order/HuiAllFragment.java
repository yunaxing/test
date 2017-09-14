package com.qkxmall.mall.views.hui.order;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
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

/**
 * ViewHolder simple {@link Fragment} subclass.
 * Use the {@link HuiAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HuiAllFragment extends Fragment {
    Context context;
    ListView listView = null;
    TextView nothing = null;
    int tag = 0;
    LOD lod;
    List<HashMap<String ,Object>> allOrderList = new ArrayList<>();
    List<HashMap<String ,Object>> payOrderList = new ArrayList<>();
    List<HashMap<String ,Object>> receiveOrderList = new ArrayList<>();
    List<HashMap<String ,Object>> finishOrderList = new ArrayList<>();

    public HuiAllFragment() {
        // Required empty public constructor
    }

    public void newInstance(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tag = getArguments().getInt("tag");
        }
    }
//以下自己添加代码

    public void onPullDownToRefresh(){
        loadmsg();
    }
    public void onPullUpToRefresh(){
        //TODO 上拉加载自己写
        ((AllHuiOrderActivity)getActivity()).finishrefresh();

    }

//以上自己添加代码

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hui_all, container, false);
        init(rootView);
        lod = new LOD(context);
        loadmsg();
        return rootView;
    }
    private void loadmsg() {
        switch (tag){
            case 0:{//全部订单 √
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("加载中...");
                progressDialog.show();
                AllOrderHandler allOrderHandler = new AllOrderHandler(progressDialog);
                String url = API.HUI_ORDER_LIST+"&type=1"+"&uid="+lod.get("USER_INFO","UID","");
                Log.e("URL",url);
                BackgroundTask backgroundTask = new BackgroundTask(context,url,allOrderHandler);
                backgroundTask.doInBackground();
            }
            break;
            case 1:{//待付款 √
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("加载中...");
                progressDialog.show();
                PayHandler payHandler = new PayHandler(progressDialog);
                String url = API.HUI_ORDER_LIST+"&type=pay"+"&uid="+lod.get("USER_INFO","UID","");
                Log.e("URL",url);
                ((AllHuiOrderActivity)getActivity()).finishrefresh();//添加的代码
                BackgroundTask backgroundTask = new BackgroundTask(context,url,payHandler);
                backgroundTask.doInBackground();
            }
            break;
            case 2:{//待发货 √
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("加载中...");
                progressDialog.show();
                ReceiveHandler receiveHandler = new ReceiveHandler(progressDialog);
                String url = API.HUI_ORDER_LIST+"&type=delivery"+"&uid="+lod.get("USER_INFO","UID","");
                Log.e("URL",url);
                ((AllHuiOrderActivity)getActivity()).finishrefresh();//添加的代码
                BackgroundTask backgroundTask = new BackgroundTask(context,url,receiveHandler);
                backgroundTask.doInBackground();
            }
            break;
            case 3:{//待收货 ×
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("加载中...");
                progressDialog.show();
                FinishHandler finishHandler = new FinishHandler(progressDialog);
                String url = API.HUI_ORDER_LIST+"&type=finish"+"&uid="+lod.get("USER_INFO","UID","");
                Log.e("URL",url);
                ((AllHuiOrderActivity)getActivity()).finishrefresh();//添加的代码
                BackgroundTask backgroundTask = new BackgroundTask(context,url,finishHandler);
                backgroundTask.doInBackground();
            }
            break;
            case 4:{//待评价 ×
                nothing.setText("木有啦");
                nothing.setVisibility(View.VISIBLE);
                ((AllHuiOrderActivity)getActivity()).finishrefresh();//添加的代码
            }
            break;
        }

    }

    private void init(View rootView) {
        listView = (ListView) rootView.findViewById(R.id.list);
        nothing = (TextView) rootView.findViewById(R.id.nothing);
    }

    private class AllOrderHandler extends Handler{
        ProgressDialog progressDialog;

        public AllOrderHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
//以下是自己添加的代码
            AllHuiOrderActivity activity=(AllHuiOrderActivity)getActivity();
            activity.finishrefresh();
            //以上是自己添加的代码

            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        if (!bundle.getString("result").equals("null")) {
                            try {
                                JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    HashMap<String, Object> data = new HashMap<>();
                                    data.put("id", jsonObject.get("id"));
                                    data.put("order_sn", jsonObject.get("order_sn"));
                                    data.put("user_id", jsonObject.get("user_id"));
                                    data.put("pay_code", jsonObject.get("pay_code"));
                                    data.put("delivery_id", jsonObject.get("delivery_id"));
                                    data.put("accept_name", jsonObject.get("accept_name"));
                                    data.put("order_status", jsonObject.get("order_status"));
                                    data.put("pay_status", jsonObject.get("pay_status"));
                                    data.put("delivery_status", jsonObject.get("delivery_status"));
                                    data.put("mobile", jsonObject.get("mobile"));
                                    data.put("zipcode", jsonObject.get("zipcode"));
                                    data.put("province", jsonObject.get("province"));
                                    data.put("city", jsonObject.get("city"));
                                    data.put("area", jsonObject.get("area"));
                                    data.put("address", jsonObject.get("address"));
                                    data.put("payable_amount", jsonObject.get("payable_amount"));
                                    data.put("real_amount", jsonObject.get("real_amount"));
                                    JSONArray array = jsonObject.getJSONArray("_goods_info");
                                    List<HashMap<String, Object>> goods_info = new ArrayList<>();
                                    for (int m = 0; m < array.length(); m++) {
                                        JSONObject object = array.getJSONObject(m);
                                        HashMap<String, Object> map = new HashMap<>();
                                        map.put("goods_id", object.get("goods_id"));
                                        map.put("product_id", object.get("product_id"));
                                        map.put("thumb", object.get("thumb"));
                                        map.put("name", object.get("name"));
                                        map.put("spec_text", object.get("spec_text"));
                                        map.put("kaixindou",object.get("kaixindou"));
                                        goods_info.add(map);
                                    }
                                    data.put("_goods_info", goods_info);
                                    allOrderList.add(data);
                                }
                                System.out.println("全部订单数据源：" + allOrderList.toString());


                                AllOrderAdapter allOrderAdapter = new AllOrderAdapter(context, allOrderList);
                                listView.setAdapter(allOrderAdapter);
                                if (listView.getCount() == 0) {
                                    nothing.setVisibility(View.VISIBLE);
                                } else {
                                    nothing.setVisibility(View.GONE);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            nothing.setVisibility(View.VISIBLE);
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(context, "请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class PayHandler extends Handler{
        ProgressDialog progressDialog;

        public PayHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        if (!bundle.getString("result").equals("null")) {
                            try {
                                JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    HashMap<String, Object> data = new HashMap<>();
                                    data.put("id", jsonObject.get("id"));
                                    data.put("order_sn", jsonObject.get("order_sn"));
                                    data.put("user_id", jsonObject.get("user_id"));
                                    data.put("pay_code", jsonObject.get("pay_code"));
                                    data.put("delivery_id", jsonObject.get("delivery_id"));
                                    data.put("accept_name", jsonObject.get("accept_name"));
                                    data.put("order_status", jsonObject.get("order_status"));
                                    data.put("pay_status", jsonObject.get("pay_status"));
                                    data.put("delivery_status", jsonObject.get("delivery_status"));
                                    data.put("mobile", jsonObject.get("mobile"));
                                    data.put("zipcode", jsonObject.get("zipcode"));
                                    data.put("province", jsonObject.get("province"));
                                    data.put("city", jsonObject.get("city"));
                                    data.put("area", jsonObject.get("area"));
                                    data.put("address", jsonObject.get("address"));
                                    data.put("payable_amount", jsonObject.get("payable_amount"));
                                    data.put("real_amount", jsonObject.get("real_amount"));
                                    JSONArray array = jsonObject.getJSONArray("_goods_info");
                                    List<HashMap<String, Object>> goods_info = new ArrayList<>();
                                    for (int m = 0; m < array.length(); m++) {
                                        JSONObject object = array.getJSONObject(m);
                                        HashMap<String, Object> map = new HashMap<>();
                                        map.put("goods_id", object.get("goods_id"));
                                        map.put("product_id", object.get("product_id"));
                                        map.put("thumb", object.get("thumb"));
                                        map.put("name", object.get("name"));
                                        map.put("spec_text", object.get("spec_text"));
                                        map.put("kaixindou",object.get("kaixindou"));
                                        goods_info.add(map);
                                    }
                                    data.put("_goods_info", goods_info);
                                    payOrderList.add(data);
                                }

                                PayOrderAdapter payOrderAdapter = new PayOrderAdapter(context, payOrderList);
                                listView.setAdapter(payOrderAdapter);
                                if (listView.getCount() == 0) {
                                    nothing.setVisibility(View.VISIBLE);
                                } else {
                                    nothing.setVisibility(View.GONE);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            nothing.setVisibility(View.VISIBLE);
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(context, "请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class ReceiveHandler extends Handler{
        ProgressDialog progressDialog;

        public ReceiveHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        if (!bundle.getString("result").equals("null")) {
                            try {
                                JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    HashMap<String, Object> data = new HashMap<>();
                                    data.put("id", jsonObject.get("id"));
                                    data.put("order_sn", jsonObject.get("order_sn"));
                                    data.put("user_id", jsonObject.get("user_id"));
                                    data.put("pay_code", jsonObject.get("pay_code"));
                                    data.put("delivery_id", jsonObject.get("delivery_id"));
                                    data.put("accept_name", jsonObject.get("accept_name"));
                                    data.put("order_status", jsonObject.get("order_status"));
                                    data.put("pay_status", jsonObject.get("pay_status"));
                                    data.put("delivery_status", jsonObject.get("delivery_status"));
                                    data.put("mobile", jsonObject.get("mobile"));
                                    data.put("zipcode", jsonObject.get("zipcode"));
                                    data.put("province", jsonObject.get("province"));
                                    data.put("city", jsonObject.get("city"));
                                    data.put("area", jsonObject.get("area"));
                                    data.put("address", jsonObject.get("address"));
                                    data.put("payable_amount", jsonObject.get("payable_amount"));
                                    data.put("real_amount", jsonObject.get("real_amount"));
                                    JSONArray array = jsonObject.getJSONArray("_goods_info");
                                    List<HashMap<String, Object>> goods_info = new ArrayList<>();
                                    for (int m = 0; m < array.length(); m++) {
                                        JSONObject object = array.getJSONObject(m);
                                        HashMap<String, Object> map = new HashMap<>();
                                        map.put("goods_id", object.get("goods_id"));
                                        map.put("product_id", object.get("product_id"));
                                        map.put("thumb", object.get("thumb"));
                                        map.put("name", object.get("name"));
                                        map.put("spec_text", object.get("spec_text"));
                                        map.put("kaixindou",object.get("kaixindou"));
                                        goods_info.add(map);
                                    }
                                    data.put("_goods_info", goods_info);
                                    receiveOrderList.add(data);
                                }
                                FinishOrderAdapter finishOrderAdapter = new FinishOrderAdapter(context, payOrderList);
                                listView.setAdapter(finishOrderAdapter);
                                if (listView.getCount() == 0) {
                                    nothing.setVisibility(View.VISIBLE);
                                } else {
                                    nothing.setVisibility(View.GONE);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            nothing.setVisibility(View.VISIBLE);
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(context, "请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class FinishHandler extends Handler{
        ProgressDialog progressDialog;

        public FinishHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        if (!bundle.getString("result").equals("null")) {
                            try {
                                JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    HashMap<String, Object> data = new HashMap<>();
                                    data.put("id", jsonObject.get("id"));
                                    data.put("order_sn", jsonObject.get("order_sn"));
                                    data.put("user_id", jsonObject.get("user_id"));
                                    data.put("pay_code", jsonObject.get("pay_code"));
                                    data.put("delivery_id", jsonObject.get("delivery_id"));
                                    data.put("accept_name", jsonObject.get("accept_name"));
                                    data.put("order_status", jsonObject.get("order_status"));
                                    data.put("pay_status", jsonObject.get("pay_status"));
                                    data.put("delivery_status", jsonObject.get("delivery_status"));
                                    data.put("mobile", jsonObject.get("mobile"));
                                    data.put("zipcode", jsonObject.get("zipcode"));
                                    data.put("province", jsonObject.get("province"));
                                    data.put("city", jsonObject.get("city"));
                                    data.put("area", jsonObject.get("area"));
                                    data.put("address", jsonObject.get("address"));
                                    data.put("payable_amount", jsonObject.get("payable_amount"));
                                    data.put("real_amount", jsonObject.get("real_amount"));
                                    JSONArray array = jsonObject.getJSONArray("_goods_info");
                                    List<HashMap<String, Object>> goods_info = new ArrayList<>();
                                    for (int m = 0; m < array.length(); m++) {
                                        JSONObject object = array.getJSONObject(m);
                                        HashMap<String, Object> map = new HashMap<>();
                                        map.put("goods_id", object.get("goods_id"));
                                        map.put("product_id", object.get("product_id"));
                                        map.put("thumb", object.get("thumb"));
                                        map.put("name", object.get("name"));
                                        map.put("spec_text", object.get("spec_text"));
                                        map.put("kaixindou",object.get("kaixindou"));
                                        goods_info.add(map);
                                    }
                                    data.put("_goods_info", goods_info);
                                    finishOrderList.add(data);
                                }
                                FinishOrderAdapter finishOrderAdapter = new FinishOrderAdapter(context, payOrderList);
                                listView.setAdapter(finishOrderAdapter);
                                if (listView.getCount() == 0) {
                                    nothing.setVisibility(View.VISIBLE);
                                } else {
                                    nothing.setVisibility(View.GONE);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            nothing.setVisibility(View.VISIBLE);
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(context, "请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


}
