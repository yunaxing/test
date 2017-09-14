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

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.views.ScrollListView;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.hui.HuiBuyPayActivity;
import com.qkxmall.mall.views.hui.container.HuiActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ViewHolder simple {@link Fragment} subclass.
 */
public class HuiPageFragment extends Fragment {
    private static final int defaultPage = 1;
    private static final int defaultRows = 5;
    private static final boolean debug = true;
    private int oldPage = 1;
    private int currentPage = 1;
    private String currentAddress;
    private FloatingActionButton clean = null;

    //private int kaiXinDou = 0;

    private Context context;
    private LOD lod;

    private ScrollListView cartList = null;
    private HuiCartListAdapter huiCartListAdapter;
    private PullToRefreshScrollView pullScroller = null;
    private ScrollView scrollView;
    List<HashMap<String, Object>> huiItemChecked = new ArrayList<>();
    List<HashMap<String, Object>> huiList = new ArrayList<>();
    private RelativeLayout nothing = null;
    private TextView notive = null;
    private Button delete = null;
    private Button states = null;
    private TextView total = null;


    public HuiPageFragment() {

        // Required empty public constructor
    }

    public static HuiPageFragment newInstance(Context context) {
        HuiPageFragment fragment = new HuiPageFragment();
        fragment.context = context;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.child_hui_cart_page, container, false);
        init(rootView);
        addData();
        lod = new LOD(context);
        //获取本地用户基本信息
             String uid = lod.get("USER_INFO", "UID", "");
        LoadHuiCartData loadHuiCartData = new LoadHuiCartData();
        String addressHuiCart = API.HUI_CART + "&uid=" + uid + "&page=" + currentPage + "&rows="
                + defaultRows;
        BackgroundTask backgroundTaskHuiCart = new BackgroundTask(context, addressHuiCart,
                loadHuiCartData);
        backgroundTaskHuiCart.doInBackground();
        currentAddress = addressHuiCart;
        if (debug) {
            Log.e("CurrentAddres", currentAddress);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 加载~~");
        }

        Typeface typeface = Typeface.createFromAsset(context.getAssets(),
                "fonts/palamecia_titling.ttf");
        notive.setTypeface(typeface);

        pullScroller.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                oldPage = currentPage;
                currentPage = 1;
                currentAddress = currentAddress.replace("pahe=" + oldPage, "page=" + currentPage);
                Refresh refresh = new Refresh();
                BackgroundTask backgroundTask = new BackgroundTask(context, currentAddress,
                        refresh);
                backgroundTask.doInBackground();


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {


//                System.out.println("上拉加载+++++++++++++++++++++++++++++++++++++++++++++++++++++");
//                currentPage+=1;
//                lod = new LOD(context);
//                //获取本地用户基本信息
//                String uid = lod.get("USER_INFO", "UID", "");
//                LoadHuiCartData2 loadHuiCartData2 = new LoadHuiCartData2();
//                String addressHuiCart = API.HUI_CART + "&uid=" + uid + "&page=" + currentPage + "&rows="
//                        + defaultRows;
//                BackgroundTask backgroundTaskHuiCart = new BackgroundTask(context, addressHuiCart,
//                        loadHuiCartData2);
//                backgroundTaskHuiCart.doInBackground();
                //-------------------------------------------------以下是原代码
                oldPage = currentPage;
                currentPage++;
                currentAddress = currentAddress.replace("pahe=" + oldPage, "page=" + currentPage);
                pullScroller.onRefreshComplete();
            }
        });
        clean.setOnClickListener(new OnClick());
        states.setOnClickListener(new OnClick());
        delete.setOnClickListener(new OnClick());
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
        clean = (FloatingActionButton) rootView.findViewById(R.id.clean);


        cartList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //点击购物车跳转到相应的页面
//                Intent intent = new Intent(context, CloudDetailActivity.class);
                Intent intent = new Intent(context, HuiActivity.class);
//                intent.putExtra("cid", (String) huiList.get(position).get("cid"));
                intent.putExtra("gid", (String) huiList.get(position).get("goods_id"));

                startActivity(intent);

            }
        });

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
                addKeys(data.get("id").toString().trim());

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


    List<HashMap<String, Object>> addressList = new ArrayList<>();

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
                            if (jsonObject.has("order_sn")) {


                                String dingdan = jsonObject.getString("order_sn");
                                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! 云购购物车 订单" + dingdan);
                                //拿到 订单号后跳转到支付页面e)

                                Intent intent = new Intent(context, HuiBuyPayActivity.class);
                                intent.putExtra("key", keys);
                                intent.putExtra("kaixindou", kaiXinDou);
                                intent.putExtra("c_price", total.getText().toString().trim());
                                intent.putExtra("orderno", dingdan);
                                intent.putExtra("huidingdan", true);
                                startActivity(intent);
                            } else {

                                Toast.makeText(getContext(), jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();


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

    public int kaiXinDou = 0;
    public String keys = "";

    //生成订单号的方法
    public void addKeys(String s) {

        kaiXinDou = 0;
        String keys = "";
        //统计可用开心豆数量

        for (int i = 0; i < huiList.size(); i++) {
            if ((Boolean) huiItemChecked.get(i).get("check")) {
                //统计商品信息“，”隔开
                keys += huiList.get(i).get("key") + ",";
                System.out.println("！！！！！！！！ keys：" + keys);
                //统计可用开心豆
                kaiXinDou += Integer.parseInt((String) huiList.get(i).get
                        ("kaixindou")) * Integer.parseInt((String) huiList.get(i).get
                        ("num"));
            }
        }
        if (keys.equals("")) {
            Toast.makeText(context, "商品未选择", Toast.LENGTH_SHORT).show();
        } else {
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("获取地址中...");
            progressDialog.show();
            getMyAddress2 getMyAddress2 = new getMyAddress2(progressDialog);
            LOD lod = new LOD(context);
            String address = "http://www.qkxmall.com/index.php?m=api&c=order&a=addorder" + "&uid=" + lod.get("USER_INFO", "UID", "") + "&address=" + s + "&keys=" + keys;
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!  购物车结算：" + address);
            BackgroundTask backgroundTask = new BackgroundTask(context, address, getMyAddress2);
            backgroundTask.doInBackground();
        }


    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.states: {
                    //结算时 需要添加收货地址选项，从而生成购物车结算订单号
                    addPrompt();

//                    Intent intent = new Intent(getActivity(), HuiBuyChoiceSthPageActivity.class);
//                    startActivity(intent);
//                    startActivityForResult(intent, Activity.RESULT_FIRST_USER);   选择收货地址


//                    String keys = "";
//                    //统计可用开心豆数量
//                    int kaiXinDou = 0;
//                    for (int i = 0; i < huiList.size(); i++) {
//                        if ((Boolean) huiItemChecked.get(i).get("check")) {
//                            keys += huiList.get(i).get("key") + ",";
//                            System.out.println("！！！！！！！！ keys：" + keys);
//                            kaiXinDou += Integer.parseInt((String) huiList.get(i).get
//                                    ("kaixindou")) * Integer.parseInt((String) huiList.get(i).get
//                                    ("num"));
//                        }
//                    }
//
//                    Intent intent = new Intent(context, HuiBuyPayActivity.class);
//                    intent.putExtra("key", keys);
//                    intent.putExtra("kaixindou", kaiXinDou);
//                    intent.putExtra("c_price", total.getText().toString().trim());
//                    startActivity(intent);
                }
                break;
                case R.id.delet: {

                    LOD lod = new LOD(context);
                    List<HashMap<String, Object>> list = huiCartListAdapter.getList();
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
                                            if (jsonObject.getBoolean("flag")) {
                                                Toast.makeText(context, jsonObject.getString
                                                        ("msg"), Toast.LENGTH_SHORT).show();
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
                    for (int i = 0; i < list.size(); i++) {
                        if ((Boolean) huiItemChecked.get(i).get("check")) {
                            String address = API.HUI_DELETE_FROM_CART + "&key=" + list.get(i).get
                                    ("key") + "&uid=" + lod.get("USER_INFO", "UID", "");
                            BackgroundTask backgroundTask = new BackgroundTask(context, address,
                                    handler);
                            backgroundTask.doInBackground();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case R.id.clean: {
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
                                                                huiList.clear();
                                                                huiCartListAdapter
                                                                        .notifyDataSetChanged();
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
                                    String address = API.HUI_CLEAN_CART + "&uid=" + lod.get
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
            }
        }
    }

    private class LoadHuiCartData extends Handler {
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
                                HashMap<String, Object> data = new HashMap<>();
                                data.put("goods_id", jsonObject.getString("goods_id"));
                                data.put("products_id", jsonObject.getString("products_id"));
                                data.put("num", jsonObject.getString("num"));
                                data.put("key", jsonObject.getString("key"));
                                data.put("name", jsonObject.getString("name"));
                                data.put("thumb", jsonObject.getString("thumb"));
                                data.put("kaixindou", jsonObject.getString("kaixindou"));
                                data.put("salesnumber", jsonObject.getString("num"));

                                data.put("c_price", jsonObject.getString("price"));
                                huiList.add(data);
                            }
                            CheckedChange checkedChange = new CheckedChange();
                            for (int i = 0; i < huiList.size(); i++) {
                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("check", false);
                                huiItemChecked.add(hashMap);
                            }

                            System.out.println("!!!!!!!!!!!!!慧购购物车 数据源：" + huiList.toString());
                            huiCartListAdapter = new HuiCartListAdapter(context, checkedChange,
                                    huiList);


                            cartList.setAdapter(huiCartListAdapter);
//                            cartList.setAdapter(new HuiListAdapter(context,huiList));


                            if (huiList.size() == 0) {
                                nothing.setVisibility(View.VISIBLE);
                            } else {
                                nothing.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据加载失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class LoadHuiCartData2 extends Handler {
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
                                HashMap<String, Object> data = new HashMap<>();
                                data.put("goods_id", jsonObject.getString("goods_id"));
                                data.put("products_id", jsonObject.getString("products_id"));
                                data.put("num", jsonObject.getString("num"));
                                data.put("key", jsonObject.getString("key"));
                                data.put("name", jsonObject.getString("name"));
                                data.put("thumb", jsonObject.getString("thumb"));
                                data.put("kaixindou", jsonObject.getString("kaixindou"));
                                data.put("salesnumber", jsonObject.getString("num"));

                                data.put("c_price", jsonObject.getString("c_price"));
                                huiList.add(data);
                            }
                            CheckedChange checkedChange = new CheckedChange();
                            for (int i = 0; i < huiList.size(); i++) {
                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("check", false);
                                huiItemChecked.add(hashMap);
                            }


                            huiCartListAdapter.notifyDataSetChanged();
//                            cartList.setAdapter(new HuiListAdapter(context,huiList));
                            pullScroller.onRefreshComplete();

                            if (huiList.size() == 0) {
                                nothing.setVisibility(View.VISIBLE);
                            } else {
                                nothing.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                            pullScroller.onRefreshComplete();
                        }
                    }
                    pullScroller.onRefreshComplete();
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据加载失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


    private class CheckedChange extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = (Bundle) msg.obj;
            if (bundle != null) {
                int position = Integer.parseInt(bundle.getString("position"));
                switch (msg.what) {
                    case BackgroundTask.CHECKED: {
                        huiItemChecked.remove(position);
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("check", true);
                        huiItemChecked.add(position, hashMap);
                        List<HashMap<String, Object>> list = huiCartListAdapter.getList();
                        double totalPrice = 0.0;//kaiXinDou=0;

                        for (int i = 0; i < list.size(); i++) {
                            if ((Boolean) huiItemChecked.get(i).get("check")) {
                                /*
                                * 当商品"价格值"不为"null"的时候计算选中商品价格
                                * */
                                if (!list.get(i).get("c_price").equals("null")) {
//                                    System.out.println("商品价格计算：" + list.get(i).get("c_price"));
//                                    System.out.println("商品数量计算：" + list.get(i).get("num"));
//                                    System.out.println("商品开心豆计算：" + list.get(i).get("kaixindou"));
                                    totalPrice += Double.parseDouble((String) list.get(i).get
                                            ("c_price")) * Integer.parseInt((String) list.get(i).get
                                            ("num"));

//                                    kaiXinDou += Integer.parseInt((String) list.get(i).get
// ("kaixindou"));
//                                    System.out.println("！！！！！！！！！ 选中商品开心豆：" + Integer.parseInt(
// (String) list.get(i).get("kaixindou")));
                                }
                            }
                        }

                        total.setText(totalPrice + "");
                    }
                    break;
                    case BackgroundTask.UN_CHECKED: {
                        huiItemChecked.remove(position);
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("check", false);
                        huiItemChecked.add(position, hashMap);
                        List<HashMap<String, Object>> list = huiCartListAdapter.getList();
                        double totalPrice = 0.0;//kaiXinDou=0;
                        for (int i = 0; i < list.size(); i++) {
                            if ((Boolean) huiItemChecked.get(i).get("check")) {
                                if (!list.get(i).get("c_price").equals("null")) {
                                    totalPrice += Double.parseDouble((String) list.get(i).get
                                            ("c_price")) * Integer.parseInt((String) list.get(i).get
                                            ("num"));
                                    //kaiXinDou += Integer.parseInt((String) list.get(i).get
                                    // ("kaixindou"));
                                }
                            }
                        }
                        total.setText(totalPrice + "");
                    }
                    break;
                }
            }
        }
    }

    List<HashMap<String, Object>> huiList2 = new ArrayList<>();

    private class Refresh extends Handler {
        @Override
        public void handleMessage(Message msg) {
            huiList2.clear();
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            huiList.clear();
                            JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HashMap<String, Object> data = new HashMap<>();
                                data.put("goods_id", jsonObject.getString("goods_id"));
                                data.put("products_id", jsonObject.getString("products_id"));
                                data.put("num", jsonObject.getString("num"));
                                data.put("key", jsonObject.getString("key"));
                                data.put("name", jsonObject.getString("name"));
                                data.put("thumb", jsonObject.getString("thumb"));
                                data.put("kaixindou", jsonObject.getString("kaixindou"));
                                data.put("salesnumber", jsonObject.getString("num"));
                                data.put("c_price", jsonObject.optString("price"));
                                huiList2.add(data);
                            }
                            huiList.addAll(huiList2);
                            CheckedChange checkedChange = new CheckedChange();
                            for (int i = 0; i < huiList2.size(); i++) {
                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("check", false);
                                huiItemChecked.add(hashMap);
                            }
                            huiCartListAdapter = new HuiCartListAdapter(context, checkedChange,
                                    huiList);
                            cartList.setAdapter(huiCartListAdapter);
                            if (huiList.size() == 0) {
                                nothing.setVisibility(View.VISIBLE);
                            } else {
                                nothing.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据获取失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            pullScroller.onRefreshComplete();
        }
    }

}
