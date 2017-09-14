package com.qkxmall.mall.views.MyView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lidroid.xutils.BitmapUtils;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.util.DateUtil;
import com.qkxmall.mall.util.Sha1;
import com.qkxmall.mall.views.func.coucou.activity.OrderDetailActivity;
import com.qkxmall.mall.views.hui.container.HuiActivity;
import com.qkxmall.mall.views.hui.order.AllHuiOrderActivity;
import com.qkxmall.mall.views.hui.order.AllOrderActivityFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/5/9. 待付款页面
 */
public class HuiAllFragment0 extends Fragment {

    Context context; //上下文对象
    ListView listView = null;
    TextView nothing = null;
    AllOrderActivityFragment arrhui;
    ImageView imgDel;
    ImageView imgDetail;
    LOD lod;
    List<HashMap<String ,Object>> payOrderList = new ArrayList<>();//列表数据源
    private int i;//数据分页下标
    private Dialog dialog;

public HuiAllFragment0(Context context,AllOrderActivityFragment arrhui){
    this.context=context;
    this.arrhui=arrhui;

}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hui_all, container, false);
        i=1;
        init(rootView);
        lod = new LOD(context);
        loadmsg();
        return rootView;
    }

    private void init(View rootView) {
        listView = (ListView) rootView.findViewById(R.id.list);
        nothing = (TextView) rootView.findViewById(R.id.nothing);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void showDialog(final String id, final int pos) {

        View view = View.inflate(getActivity(), R.layout.dialog_cancle_delete, null);
        TextView textView3 = (TextView) view.findViewById(R.id.textView3);
        textView3.setText("确定删除该订单吗？");


        view.findViewById(R.id.upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delHuiOrder(id, pos);
                dialog.cancel();

            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();

            }
        });
        dialog = new Dialog(getActivity(), R.style.MyDialog);
        dialog.setContentView(view);
        dialog.show();
    }
    public void onPullDownToRefresh(){
        loadmsg();
        //下拉刷新
        System.out.println("！！！！！！！！！！！ 子类中下拉刷新：");
    }
    public void onPullUpToRefresh(){
        //TODO 上拉加载自己写
//        ((AllHuiOrderActivity)getActivity()).finishrefresh();
                   addData();

    }

    public void loadmsg() {

        payOrderList.clear();
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("加载中...");
                progressDialog.show();
                PayHandler payHandler = new PayHandler(progressDialog);
                String url = API.HUI_ORDER_LIST+"&uid="+lod.get("USER_INFO","UID","")+"&page="+i;
                Log.e("URL",url);
                ((AllHuiOrderActivity)getActivity()).finishrefresh();//添加的代码
                BackgroundTask backgroundTask = new BackgroundTask(context,url,payHandler);
                backgroundTask.doInBackground();

    }

    private void delHuiOrder(String orderId, int pos){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("加载中...");
        progressDialog.show();
        HuiDel payHandler = new HuiDel(progressDialog,pos);
        SharedPreferences preferences = context.getSharedPreferences("USER_INFO",Context.MODE_PRIVATE);
        preferences.getString("UID","");
        String url = API.DEL_HUI_ORDER+"&uid="+ preferences.getString("UID","")+"&orderid="+orderId+"&access_token="+(new Sha1().getDigestOfString((DateUtil.getCurrentDate()+orderId+lod.get("USER_INFO","UID","")+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());
        ;
        Log.e("URL",url);
        ((AllHuiOrderActivity)getActivity()).finishrefresh();//添加的代码
        BackgroundTask backgroundTask = new BackgroundTask(context,url,payHandler);
        backgroundTask.doInBackground();
    }

    private void addData(){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("加载中...");
        progressDialog.show();
        PayHandler2 payHandler2 = new PayHandler2(progressDialog);
        i+=1;
        String url = API.HUI_ORDER_LIST+"&uid="+lod.get("USER_INFO","UID","")+"&page="+i;
        System.out.println("上拉刷新数据 url：" + url);
        Log.e("URL",url);
        ((AllHuiOrderActivity)getActivity()).finishrefresh();//添加的代码
        BackgroundTask backgroundTask = new BackgroundTask(context,url,payHandler2);
        backgroundTask.doInBackground();
    }
    private class PayHandler2 extends Handler {

        ProgressDialog progressDialog;

        public PayHandler2(ProgressDialog progressDialog) {
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
                                    data.put("order_desc",jsonObject.optString("order_desc"));
                                    data.put("id", jsonObject.optString("id"));
                                    data.put("order_sn", jsonObject.optString("order_sn"));
                                    data.put("user_id", jsonObject.optString("user_id"));
                                    data.put("pay_code", jsonObject.optString("pay_code"));
                                    data.put("delivery_id", jsonObject.optString("delivery_id"));
                                    data.put("accept_name", jsonObject.optString("accept_name"));
                                    data.put("order_status", jsonObject.optString("order_status"));
                                    data.put("pay_status", jsonObject.optString("pay_status"));
                                    data.put("delivery_status", jsonObject.optString("delivery_status"));
                                    data.put("mobile", jsonObject.optString("mobile"));
                                    data.put("zipcode", jsonObject.optString("zipcode"));
                                    data.put("province", jsonObject.optString("province"));
                                    data.put("city", jsonObject.optString("city"));
                                    data.put("area", jsonObject.optString("area"));
                                    data.put("address", jsonObject.optString("address"));
                                    data.put("payable_amount", jsonObject.optString("payable_amount"));
                                    data.put("real_amount", jsonObject.optString("real_amount"));
                                    JSONArray array = jsonObject.getJSONArray("_goods_info");
                                    List<HashMap<String, Object>> goods_info = new ArrayList<>();
                                    for (int m = 0; m < array.length(); m++) {
                                        JSONObject object = array.getJSONObject(m);
                                        HashMap<String, Object> map = new HashMap<>();
                                        map.put("goods_id", object.optString("goods_id"));
                                        map.put("product_id", object.optString("product_id"));
                                        map.put("thumb", object.optString("thumb"));
                                        map.put("name", object.optString("name"));
                                        map.put("shop_number",object.optString("shop_number"));
                                        map.put("spec_text", object.optString("spec_text"));
                                        map.put("kaixindou",object.optString("kaixindou"));
                                        map.put("order_id",object.optString("order_id"));
                                        goods_info.add(map);
                                    }
                                    data.put("_goods_info", goods_info);
                                    payOrderList.add(data);
                                }

                                adapter.notifyDataSetChanged();
                                //拿到数据后 设置适配器
//                                MyAdapter adapter=new MyAdapter();
//                                PayOrderAdapter payOrderAdapter = new PayOrderAdapter(context, payOrderList);
//                                listView.setAdapter(adapter);
//                                if (listView.getCount() == 0) {
//                                    nothing.setVisibility(View.VISIBLE);
//                                } else {
//                                    nothing.setVisibility(View.GONE);
//                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "数据解析失败1", Toast.LENGTH_SHORT).show();
                                System.out.println("!!!!!!!!!!数据解析失败1"+e);
                            }
                        }else {
//                            Toast.makeText(context,"",Toast.LENGTH_SHORT).show(); //没有新数据的提示
//                            nothing.setVisibility(View.VISIBLE);
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

    MyAdapter adapter;

    private class HuiDel extends Handler{
        ProgressDialog progressDialog;
        int pos;

        public HuiDel(ProgressDialog progressDialog, int pos) {
            this.progressDialog = progressDialog;
            this.pos = pos;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        if (!bundle.getString("result").equals("null")) {
                            try {

                                JSONObject object = new JSONObject(bundle.getString("result"));
                                System.out.println("!!!!!!!!!!!!!!!!!! 订单数据" + object);
                                if(object.getString("flag").equals("true")){
                                    payOrderList.remove(pos);
                                    adapter.notifyDataSetChanged();
                                    if(payOrderList.size() == 0){
                                        nothing.setVisibility(View.VISIBLE);
                                    }
                                }

                                //拿到数据后 设置适配器


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "数据解析失败2", Toast.LENGTH_SHORT).show();

                                System.out.println("!!!!!!!!!!数据解析失败2" + e);
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

    private class PayHandler extends Handler {

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
                                System.out.println("!!!!!!!!!!!!!!!!!! 订单数据" + jsonArray);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                                    HashMap<String, Object> data = new HashMap<>();
                                    data.put("order_desc",jsonObject.optString("order_desc"));
                                    data.put("id", jsonObject.optString("id"));
                                    data.put("order_sn", jsonObject.optString("order_sn"));
                                    data.put("user_id", jsonObject.optString("user_id"));
                                    data.put("pay_code", jsonObject.optString("pay_code"));
                                    data.put("delivery_id", jsonObject.optString("delivery_id"));
                                    data.put("accept_name", jsonObject.optString("accept_name"));
                                    data.put("order_status", jsonObject.optString("order_status"));
                                    data.put("pay_status", jsonObject.optString("pay_status"));
                                    data.put("delivery_status", jsonObject.optString("delivery_status"));
                                    data.put("mobile", jsonObject.optString("mobile"));
                                    data.put("zipcode", jsonObject.optString("zipcode"));
                                    data.put("province", jsonObject.optString("province"));
                                    data.put("city", jsonObject.optString("city"));
                                    data.put("area", jsonObject.optString("area"));
                                    data.put("address", jsonObject.optString("address"));
                                    data.put("payable_amount", jsonObject.optString("payable_amount"));
                                    data.put("real_amount", jsonObject.optString("real_amount"));
                                    JSONArray array = jsonObject.optJSONArray("_goods_info");
                                    List<HashMap<String, Object>> goods_info = new ArrayList<>();
                                    for (int m = 0; m < array.length(); m++) {
                                        JSONObject object = array.optJSONObject(m);
                                        HashMap<String, Object> map = new HashMap<>();
                                        map.put("goods_id", object.optString("goods_id"));
                                        map.put("product_id", object.optString("product_id"));
                                        map.put("thumb", object.optString("thumb"));
                                        map.put("name", object.optString("name"));
                                        map.put("shop_number",object.optString("shop_number"));
                                        map.put("spec_text", object.optString("spec_text"));
                                        map.put("kaixindou",object.optString("kaixindou"));
                                        map.put("order_id",object.optString("order_id"));
                                        goods_info.add(map);
                                    }
                                    data.put("_goods_info", goods_info);
                                    payOrderList.add(data);
                                }
                               //拿到数据后 设置适配器
                                 adapter=new MyAdapter();
//                                PayOrderAdapter payOrderAdapter = new PayOrderAdapter(context, payOrderList);
                                listView.setAdapter(adapter);
                                if (listView.getCount() == 0) {
                                    nothing.setVisibility(View.VISIBLE);
                                } else {
                                    nothing.setVisibility(View.GONE);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "数据解析失败2", Toast.LENGTH_SHORT).show();

                                System.out.println("!!!!!!!!!!数据解析失败2" + e);
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

    public class MyAdapter extends BaseAdapter{

        BitmapUtils utils=new BitmapUtils(context);
        @Override
        public int getCount() {
            return payOrderList.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            Vh vh;
            if(convertView==null){
                convertView=View.inflate(context,R.layout.new_all_hui_order_listview_item_to_be_received,null);
                vh=new Vh();
                vh.image=(ImageView)convertView.findViewById(R.id.image_title);
                vh.name=(TextView)convertView.findViewById(R.id.name);
                vh.number=(TextView)convertView.findViewById(R.id.numbers);
                vh.price=(TextView)convertView.findViewById(R.id.price);
                vh.num=(TextView)convertView.findViewById(R.id.num);
                vh.pay=(Button)convertView.findViewById(R.id.pay);
                vh.pay2=(Button)convertView.findViewById(R.id.pay2);
                vh.pay3=(Button)convertView.findViewById(R.id.pay3);
                vh.imgDel = (ImageView) convertView.findViewById(R.id.img_del);
                vh.imgDetail = (ImageView) convertView.findViewById(R.id.img_detail);

                convertView.setTag(vh);
            }else{
                vh=(Vh)convertView.getTag();
            }
            final HashMap<String ,Object> data = payOrderList.get(position);
            List<HashMap<String ,Object>> goodsList = (List<HashMap<String, Object>>) data.get("_goods_info");
            int nums=0;
            for (int i = 0 ; i< goodsList.size() ; i++){
                final HashMap<String, Object> map = goodsList.get(i);
                if (i == 0 ) {

                    String img = (String)map.get("thumb");
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!! img" +
                            img);
//                    vh.image.setImageURI(Uri.parse(API.ADD + img));
//                    utils.display(vh.image, API.ADD + img);
                    Glide.with(HuiAllFragment0.this).load( API.ADD + img).into(vh.image);
                    vh.name.setText(map.get("name").toString());
                    vh.imgDel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            showDialog(payOrderList.get(position).get("id").toString(),position);
                        }
                    });
                    vh.imgDetail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getActivity(), OrderDetailActivity.class).putExtra("data",payOrderList.get(position)));
                        }
                    });
                    vh.image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent(getActivity(), HuiActivity.class);
                            intent.putExtra("gid", (String) map.get("goods_id"));
                            startActivity(intent);
                        }
                    });
                }
                nums+=Integer.parseInt(map.get("shop_number").toString());
            }
            vh.num.setText(nums+"");
            vh.price.setText(data.get("real_amount").toString());
            vh.number.setText(data.get("order_sn").toString());
            vh.pay.setTag(data.get("order_desc").toString());
            vh.pay.setText(data.get("order_desc").toString());
            if(data.get("order_desc").equals("待付款")){
                vh.pay2.setVisibility(View.INVISIBLE);
                vh.pay3.setVisibility(View.INVISIBLE);
                vh.imgDetail.setVisibility(View.INVISIBLE);
            }else if(data.get("order_desc").equals("待评论")){
                vh.pay2.setText("晒单评价");
                vh.pay3.setVisibility(View.INVISIBLE);
                vh.pay.setText("售后");
//                vh.imgDetail.setVisibility(View.INVISIBLE);
            }else if(data.get("order_desc").equals("待发货")){
                vh.pay2.setVisibility(View.INVISIBLE);
                vh.pay3.setVisibility(View.INVISIBLE);
                vh.pay.setText("售后");
//                vh.imgDetail.setVisibility(View.INVISIBLE);
            }

            vh.pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                      arrhui.setTab(1);
                    Button btn=(Button)v;
                       System.out.println("！！！！！！！！！！！！！ 点击的是：" +  btn.getTag().toString());
                    switch (btn.getTag().toString()) {
                        case "待付款":
                            arrhui.setTab(1);
                            break;
                        case "待发货":
                            arrhui.setTab(2);
                            break;
                        case "待收货":
                            arrhui.setTab(3);
                            break;
                        case "待评论":
                            arrhui.setTab(4);
                            break;


                    }

//                    getActivity().finish();

                }
            });

            return convertView;
        }
    }

    public static class Vh{
        ImageView image;//商品图像
        TextView name;//商品名
        TextView number;//订单编号
        TextView price;//价钱
        TextView num;//数量
        Button pay;//付款按钮
        ImageView imgDel;
        ImageView imgDetail;
        Button pay2;
        Button pay3;
    }


}
