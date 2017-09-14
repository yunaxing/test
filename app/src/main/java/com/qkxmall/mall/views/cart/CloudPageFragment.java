package com.qkxmall.mall.views.cart;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.pay.paypage.ALiPayPayActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.beans.CloudCartBean;
import com.qkxmall.mall.beans.TheGetCloudCartBean;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.views.ScrollListView;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.cloud.detail.CloudDetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ViewHolder simple {@link Fragment} subclass.
 */
public class CloudPageFragment extends Fragment {
    private static final int defaultPage = 1;
    private static final int defaultRows = 5;
    private static final boolean debug = true;
    private int oldPage = 1;
    private int currentPage = 1;
    private String currentAddress;

    private Context context;
    private LOD lod;

    private ScrollListView cartList = null;
    private PullToRefreshScrollView pullScroller = null;
    private ScrollView scrollView;

    private RelativeLayout nothing = null;
    private TextView notive = null;
    private Button states = null;
    private TextView total = null;
    private FloatingActionButton btn;
    private Button delete = null;
    Hashtable<String, CloudCartBean> cloudList = new Hashtable<>();
    CloudCartAdapter cloudCartAdapter;


    public CloudPageFragment() {
        // Required empty public constructor
    }

    public static CloudPageFragment newInstance(Context context) {
        CloudPageFragment fragment = new CloudPageFragment();
        fragment.context = context;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.child_cloud_cart_page, container, false);
        init(rootView);
        addData();
        lod = new LOD(context);
        //获取本地用户基本信息
        String uid = lod.get("USER_INFO", "UID", "");
        LoadCloudCartData loadCloudCartData = new LoadCloudCartData();
        String addressHuiCart = API.CLOUD_CART + "&uid=" + uid;
        BackgroundTask backgroundTaskHuiCart = new BackgroundTask(context, addressHuiCart,
                loadCloudCartData);
        backgroundTaskHuiCart.doInBackground();
        currentAddress = addressHuiCart;
        if (debug) {
            Log.e("CurrentAddres", currentAddress);
        }

        Typeface typeface = Typeface.createFromAsset(context.getAssets(),
                "fonts/palamecia_titling.ttf");
        notive.setTypeface(typeface);


        pullScroller.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                oldPage = currentPage;
//                currentPage = 1;
//                currentAddress = currentAddress.replace("pahe=" + oldPage, "page=" + currentPage);
//
//                pullScroller.onRefreshComplete();
                cloudList.clear();
                LOD lod = new LOD(context);
                String uid = lod.get("USER_INFO", "UID", "");
                LoadCloudCartData loadCloudCartData = new LoadCloudCartData();
                String addressHuiCart = API.CLOUD_CART + "&uid=" + uid;
                BackgroundTask backgroundTaskHuiCart = new BackgroundTask(context,
                        addressHuiCart, loadCloudCartData);
                backgroundTaskHuiCart.doInBackground();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                oldPage = currentPage;
                currentPage++;
                currentAddress = currentAddress.replace("pahe=" + oldPage, "page=" + currentPage);

                pullScroller.onRefreshComplete();
            }
        });
        states.setOnClickListener(new OnClick());
        delete.setOnClickListener(new OnClick());
        btn.setOnClickListener(new OnClick());
        return rootView;
    }

    private void init(View rootView) {
        cartList = (ScrollListView) rootView.findViewById(R.id.cartList);
        pullScroller = (PullToRefreshScrollView) rootView.findViewById(R.id.pullScroller);
        nothing = (RelativeLayout) rootView.findViewById(R.id.nothing);
        notive = (TextView) rootView.findViewById(R.id.notice);
        delete = (Button) rootView.findViewById(R.id.delet);
        states = (Button) rootView.findViewById(R.id.states);
        total = (TextView) rootView.findViewById(R.id.total);
        btn = (FloatingActionButton) rootView.findViewById(R.id.clean);
//        cartList.setOnItemClickListener(new OnListItemClickListeners());
        cartList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(context, CloudDetailActivity.class);
                intent.putExtra("cid", cloudCartAdapter.getItem(position).cid);
                startActivity(intent);
            }
        });

    }

    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.clean://清空云购购物车
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle("提示")
                            .setMessage("确认清空购物车？").setNegativeButton("是", new DialogInterface
                                    .OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Handler handler = new Handler() {
                                        @Override
                                        public void handleMessage(Message msg) {
                                            switch (msg.what) {
                                                case BackgroundTask.SUCCESS: {
                                                    Bundle bundle = msg.getData();
                                                    if (bundle != null) {
                                                        try {
                                                            JSONObject jsonObject = new
                                                                    JSONObject(bundle.getString
                                                                    ("result"));
                                                            if (jsonObject.getBoolean("flag")) {
                                                                Toast.makeText(context, "清空成功",
                                                                        Toast.LENGTH_SHORT).show();
//                                                                huiList.clear();
                                                                cloudList.clear();//清空数据源
                                                                cloudCartAdapter
                                                                        .notifyDataSetChanged();
                                                                //属性适配器
                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                            Toast.makeText(context,
                                                                    "数据解析失败，请联系客服", Toast
                                                                            .LENGTH_SHORT).show();
                                                        }
                                                    }
                                                }
                                                break;
                                                case BackgroundTask.FAILURE: {
                                                    Toast.makeText(context, "网络连接失败，请检查网络连接",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                                break;
                                            }
                                        }
                                    };
                                    String address = "http://www.qkxmall.com/index" +
                                            ".php?m=api&c=cloud&a=clearcart" + "&uid=" + lod.get
                                            ("USER_INFO", "UID", "");
                                    BackgroundTask backgroundTask = new BackgroundTask(context,
                                            address, handler);
                                    backgroundTask.doInBackground();
                                }
                            }).setNeutralButton("否", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).create();
                    alertDialog.show();
                }


                break;
                case R.id.states: {//云购结算
                    //选择收货地址
                    addPrompt();


                }
                break;
                case R.id.delet: {
                    System.out.println("！！！！！！！删除");
                    LOD lod = new LOD(context);

                    Handler handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            switch (msg.what) {
                                case BackgroundTask.SUCCESS: {
                                    Bundle bundle = msg.getData();
                                    if (bundle != null) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(bundle
                                                    .getString("result"));

                                            System.out.println("返回的 JOSN 数据：" + bundle.getString
                                                    ("result"));
                                            if (jsonObject.getBoolean("flag")) {
                                                Toast.makeText(context, jsonObject.optString
                                                        ("msg"), Toast.LENGTH_SHORT).show();
                                                //更新数据源  刷新适配器
//                                                cloudCartAdapter.notifyDataSetChanged();
//                                                cloudList.clear();
//                                                LOD lod = new LOD(context);
//                                                String uid = lod.get("USER_INFO","UID","");
//                                                LoadCloudCartData loadCloudCartData = new
// LoadCloudCartData();
//                                                String addressHuiCart = API
// .CLOUD_CART+"&uid="+uid;
//                                                BackgroundTask backgroundTaskHuiCart = new
// BackgroundTask(context,addressHuiCart, loadCloudCartData);
//                                                backgroundTaskHuiCart.doInBackground();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(context, "数据解析失败，请联系客服", Toast
                                                    .LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                break;
                                case BackgroundTask.FAILURE: {

                                }
                                break;
                            }
                        }
                    };
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("删除中...");
                    progressDialog.show();
                    Set<Map.Entry<String, CloudCartBean>> entry = cloudList.entrySet();
                    Iterator<Map.Entry<String, CloudCartBean>> iterator = entry.iterator();
                    while (iterator.hasNext()) {
                        CloudCartBean c_price = iterator.next().getValue();

                        if (c_price.isChecked) {

                            String address = "http://www.qkxmall.com/index" +
                                    ".php?m=api&c=cloud&a=deletegoods" + "&uid=" + lod.get
                                    ("USER_INFO", "UID", "") + "&cartid=" + c_price.id
                                    + "&num=1" + "&oper=minus";
                            System.out.println("！！！！！！！！ 删除云购收藏夹：" + address);
                            BackgroundTask backgroundTask = new BackgroundTask(context, address,
                                    handler);
                            backgroundTask.doInBackground();
//                            cloudList.remove(i);
//                            System.out.println("!!!!!!!!!! 数据源：" + cloudList.toString());
                        }
                    }

                    progressDialog.dismiss();
                }

                break;
            }
        }
    }

    public ListView listView;

    //弹出地址选择对话框     在此处拿到地址id后 生成订单号 跳转支付页面
    public void addPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请选择收货地址");
        View view = View.inflate(context, R.layout.add_ress_listview, null);
        listView = (ListView) view.findViewById(R.id.add_ress_list);
        //拿到数据后给列表添加
        listView.setAdapter(new listAdapter(addressList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, Object> data = addressList.get(position);
                //拿到地址id后 生成 订单号
                statistical(data.get("id").toString().trim());

            }
        });

        builder.setView(view);
//        builder.setNegativeButton("确定", null);
        builder.setPositiveButton("取消", null);
        builder.create();
        builder.show();

    }

    public class listAdapter extends BaseAdapter {
        List<HashMap<String, Object>> list;

        public listAdapter(List<HashMap<String, Object>> list) {
            this.list = list;

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            A a;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.add_ress_list_adapter, null);
                a = new A();
                a.name = (TextView) convertView.findViewById(R.id.name);
                a.phone = (TextView) convertView.findViewById(R.id.phone);
                a.userName = (TextView) convertView.findViewById(R.id.userName);
                convertView.setTag(a);

            } else {
                a = (A) convertView.getTag();
            }
            HashMap<String, Object> data = list.get(position);
            a.name.setText(data.get("addressname").toString());
            a.phone.setText(data.get("mobile").toString());
            a.userName.setText((String) data.get("provincename") + data.get("cityname") + data
                    .get("districtname") + data.get("address"));
            return convertView;
        }

    }

    public static class A {
        TextView name;//姓名
        TextView phone;//电话
        TextView userName;//收货地址


    }


    //初始化 地址id数据源
    public void addData() {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("获取地址中...");
        progressDialog.show();
        getMyAddress getMyAddress = new getMyAddress(progressDialog);
        LOD lod = new LOD(context);
        String address = API.USER_ADDRESS_LIST + "&uid=" + lod.get("USER_INFO", "UID", "");
        BackgroundTask backgroundTask = new BackgroundTask(context, address, getMyAddress);
        backgroundTask.doInBackground();

    }

    List<HashMap<String, Object>> addressList = new ArrayList<>();

    private class getMyAddress extends Handler {

        ProgressDialog progressDialog;

        public getMyAddress(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        String result = bundle.getString("result");
                        try {

                            addressList.clear();
                            JSONArray jsonArray = new JSONArray(result);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                HashMap<String, Object> data = new HashMap<>();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                data.put("id", jsonObject.get("id"));//收货地址id
                                data.put("addressname", jsonObject.get("addressname"));//收货人
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
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE: {
                    progressDialog.dismiss();
                    Toast.makeText(context, "数据加载失败...", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    public class OnListItemClickListeners implements android.widget.AdapterView
            .OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent(context, CloudDetailActivity.class);
            intent.putExtra("cid", cloudList.get(position).cid);
            startActivity(intent);
        }
    }

    private class LoadCloudCartData extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {

                        cloudList.clear();
                        String resultStr = bundle.getString("result");
                        Gson gson = new Gson();
                        TheGetCloudCartBean bean = gson.fromJson(resultStr, TheGetCloudCartBean.class);
//                            JSONObject object = new JSONObject();
//                            JSONObject dataObject = object.getJSONObject("data");
//                            JSONArray array = dataObject.getJSONArray("rows");
                        if (bean != null && bean.data != null && bean.data.rows != null) {
                            int len = bean.data.rows.size();
                            for (int i = 0; i < len; i++) {
                                CloudCartBean cloudCartBean = bean.data.rows.get(i);
                                cloudList.put(cloudCartBean.id, cloudCartBean);
                            }
                        }
                        CheckedChange checkedChange = new CheckedChange();
//
                        cloudCartAdapter = new CloudCartAdapter(context, checkedChange,
                                bean.data.rows);
                        cartList.setAdapter(cloudCartAdapter);
//                            cartList.setAdapter(new CloudListAdapter(context,cloudList));
                        if (bean != null && bean.data != null && bean.data.rows != null && bean.data.rows.size() == 0) {
                            nothing.setVisibility(View.VISIBLE);
                        } else {
                            nothing.setVisibility(View.GONE);
                        }

                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据加载失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            pullScroller.onRefreshComplete();
        }
    }

    //用于统计云购id
    public void statistical(String s) {
        StringBuilder ids = new StringBuilder();
        Set<Map.Entry<String, CloudCartBean>> entry = cloudList.entrySet();
        Iterator<Map.Entry<String, CloudCartBean>> iterator = entry.iterator();
        while (iterator.hasNext()) {
            CloudCartBean c_price = iterator.next().getValue();
            if (c_price.isChecked) {
                //统计商品id“，”隔开
                ids.append(c_price.cid + ",");
            }
        }


        if (ids.toString().endsWith(",")) {
            ids.deleteCharAt(ids.length() - 1);
        }
        if (ids.equals("")) {
            Toast.makeText(context, "商品未选择", Toast.LENGTH_SHORT).show();
        } else {
            //添加云购订单
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("获取地址中...");
            progressDialog.show();
            getMyAddress2 getMyAddress2 = new getMyAddress2(progressDialog);
            LOD lod = new LOD(context);
            String address = "https://www.qkxmall.com/index.php?m=api&c=cloud&a=multibuy" + "&uid=" + lod.get("USER_INFO", "UID", "") + "&cid=" + ids.toString() + "&num=1" + "&address=" + s;
            System.out.println("！！！！！！！！！云购订单生成：" + address);
            BackgroundTask backgroundTask = new BackgroundTask(context, address, getMyAddress2);
            backgroundTask.doInBackground();
        }

    }

    //订单生产  处理类
    private class getMyAddress2 extends Handler {

        ProgressDialog progressDialog;

        public getMyAddress2(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();

                    if (bundle != null) {
                        String result = bundle.getString("result");
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if (jsonObject.getBoolean("flag")) {
                                String dingdan = jsonObject.getString("orderid");
                                System.out.println("！！！！！！！！！云购订单号：" + dingdan);
                                //拿到 订单号后跳转到支付页面
                                Intent intent = new Intent(context, ALiPayPayActivity.class);
                                intent.putExtra("name", "云购购物车结算");
//                          intent.putExtra("kaixindou", kaiXinDou);
                                intent.putExtra("c_price", total.getText().toString().trim());
                                intent.putExtra("orderno", dingdan);
                                intent.putExtra("gwc", true);
                                startActivity(intent);
                            } else {
                                Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE: {
                    progressDialog.dismiss();
                    Toast.makeText(context, "数据加载失败...", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class CheckedChange extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            if (bundle != null) {
                CloudCartBean id = bundle.getParcelable("id");
                switch (msg.what) {
                    case BackgroundTask.CHECKED: {
                        System.out.println("！！！！！！！！！ 消息处理 1");
                        cloudList.remove(id);
//                        HashMap<String, Object> hashMap = new HashMap<>();
//                        hashMap.put("check", true);
                        id.isChecked = true;
                        cloudList.put(id.id, id);

                        System.out.println("！！！！！！！！！ 消息处理 数据源：" + cloudList.toString());
                        double totalPrice = 0.0;
//                        for (int i = 0; i < cloudList.size(); i++) {
//                            if (cloudList.get(i).isChecked) {
//                                if (cloudList.get(i).c_price != null)
//
//                            }
//                        }
                        Set<Map.Entry<String, CloudCartBean>> entry = cloudList.entrySet();
                        Iterator<Map.Entry<String, CloudCartBean>> iterator = entry.iterator();
                        while (iterator.hasNext()) {
                            CloudCartBean cloudCartBean = iterator.next().getValue();
                            if (cloudCartBean.isChecked) {
                                String c_price = cloudCartBean.c_price;
                                if (!TextUtils.isEmpty(c_price))
                                    totalPrice += Double.parseDouble(c_price);
                            }
                        }
                        total.setText(totalPrice + "");
                    }
                    break;
                    case BackgroundTask.UN_CHECKED: {
                        System.out.println("！！！！！！！！！ 消息处理 2");
                        id = cloudList.get(id.id);
                        id.isChecked = false;
                        cloudList.put(id.id, id);


                        double totalPrice = 0.0;
                        Set<Map.Entry<String, CloudCartBean>> entry = cloudList.entrySet();
                        Iterator<Map.Entry<String, CloudCartBean>> iterator = entry.iterator();
                        while (iterator.hasNext()) {
                            CloudCartBean cloudCartBean = iterator.next().getValue();
                            if (cloudCartBean.isChecked) {
                                String c_price = cloudCartBean.c_price;
                                if (!TextUtils.isEmpty(c_price))
                                    totalPrice += Double.parseDouble(c_price);
                            }
                        }
                        total.setText(totalPrice + "");
                    }
                    break;
                }
            }
        }
    }

    private class Refresh extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {

                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据获取失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

}
