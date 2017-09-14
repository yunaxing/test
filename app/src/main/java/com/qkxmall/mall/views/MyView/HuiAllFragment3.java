package com.qkxmall.mall.views.MyView;

import android.app.AlertDialog;
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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.qkxmall.mall.views.func.webview.WuLiuActivity;
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
public class HuiAllFragment3 extends Fragment {

    Context context; //上下文对象
    ListView listView = null;
    TextView nothing = null;
    LOD lod;
    List<HashMap<String ,Object>> payOrderList = new ArrayList<>();//列表数据源
    private int i;//数据分页下标
    AllOrderActivityFragment allord;
    private Dialog dialog;
public HuiAllFragment3(Context context, AllOrderActivityFragment allord){
    this.allord=allord;
    this.context=context;
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
            {//待付款 √
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("加载中...");
                progressDialog.show();
                PayHandler payHandler = new PayHandler(progressDialog);
                String url = API.HUI_ORDER_LIST+"&type=finish"+"&uid="+lod.get("USER_INFO","UID","")+"&page="+i;
                Log.e("URL",url);
                ((AllHuiOrderActivity)getActivity()).finishrefresh();//添加的代码
                BackgroundTask backgroundTask = new BackgroundTask(context,url,payHandler);
                backgroundTask.doInBackground();
            }
    }

    private void addData(){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("加载中...");
        progressDialog.show();
        PayHandler2 payHandler2 = new PayHandler2(progressDialog);
        i+=1;
        String url = API.HUI_ORDER_LIST+"&type=finish"+"&uid="+lod.get("USER_INFO","UID","")+"&page="+i;
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
                                    data.put("id", jsonObject.get("id"));
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
                                    data.put("user_id", jsonObject.get("user_id"));//用户id
                                    data.put("order_sn", jsonObject.get("order_sn"));//订单号
                                    data.put("delivery_txt",jsonObject.opt("delivery_txt"));//快递名
                                    data.put("delivery_sn",jsonObject.opt("delivery_sn"));//快递单号
                                    JSONArray array = jsonObject.getJSONArray("_goods_info");
                                    List<HashMap<String, Object>> goods_info = new ArrayList<>();
                                    for (int m = 0; m < array.length(); m++) {
                                        JSONObject object = array.getJSONObject(m);
                                        HashMap<String, Object> map = new HashMap<>();
                                        map.put("goods_id", object.get("goods_id"));
                                        map.put("product_id", object.get("product_id"));
                                        map.put("thumb", object.get("thumb"));
                                        map.put("name", object.get("name"));
                                        map.put("shop_number",object.get("shop_number"));
                                        map.put("spec_text", object.get("spec_text"));
                                        map.put("kaixindou",object.get("kaixindou"));
                                        map.put("order_id",object.get("order_id"));
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
                                Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
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
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    HashMap<String, Object> data = new HashMap<>();
                                    data.put("id", jsonObject.get("id"));
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
                                    data.put("user_id", jsonObject.get("user_id"));//用户id
                                    data.put("order_sn", jsonObject.get("order_sn"));//订单号
                                    data.put("delivery_txt",jsonObject.opt("delivery_txt"));//快递名
                                    data.put("delivery_sn",jsonObject.opt("delivery_sn"));//快递单号
                                    JSONArray array = jsonObject.getJSONArray("_goods_info");
                                    List<HashMap<String, Object>> goods_info = new ArrayList<>();
                                    for (int m = 0; m < array.length(); m++) {
                                        JSONObject object = array.getJSONObject(m);
                                        HashMap<String, Object> map = new HashMap<>();
                                        map.put("goods_id", object.get("goods_id"));
                                        map.put("product_id", object.get("product_id"));
                                        map.put("thumb", object.get("thumb"));
                                        map.put("name", object.get("name"));
                                        map.put("shop_number",object.get("shop_number"));
                                        map.put("spec_text", object.get("spec_text"));
                                        map.put("kaixindou",object.get("kaixindou"));
                                        map.put("order_id",object.get("order_id"));
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
//                    vh.image.setImageURI(Uri.parse(API.ADD + img));
//                    utils.display(vh.image, API.ADD + img);
                    Glide.with(HuiAllFragment3.this).load( API.ADD + img).into(vh.image);
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
            vh.pay.setTag(position);
            vh.pay.setText("快递单号");
            vh.pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("！！！！！！！！！！快递单号" + data.get("order_sn"));
//                    addPrompt(data.get("delivery_txt").toString(),data.get("delivery_sn").toString());
                    LOD lod = new LOD(context);
                    Intent intent = new Intent(context, WuLiuActivity.class);
                    Log.v("order","uid=="+lod.get("USER_INFO", "UID", "'"));
                    Log.v("order","orderid=="+data);
                    startActivity(intent.putExtra("uid",lod.get("USER_INFO", "UID", "'")).putExtra("orderid",data.get("id").toString()));
//                    getActivity().finish();

                }
            });

            vh.pay2.setVisibility(View.VISIBLE);
            vh.pay2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("！！！！！！！！！！确认收货");
                    confirmGoods(data.get("order_sn").toString());
                }
            });

            vh.pay3.setVisibility(View.VISIBLE);
            vh.pay3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("！！！！！！！！！！售后");
                    Intent intent =new Intent(getActivity(),AfterSaleActivity2.class);
                    intent.putExtra("uid",data.get("user_id").toString());//用户id
                    intent.putExtra("orderid",data.get("order_sn").toString());//订单号
                    intent.putExtra("delivery",data.get("delivery_txt").toString());//快递名称
                    intent.putExtra("deliverysn",data.get("delivery_sn").toString());//快递单号
                    intent.putExtra("id",data.get("id").toString());
                    startActivity(intent);
                }
            });


            return convertView;
        }
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
    public void confirmGoods(String s){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("提交中...");
        progressDialog.show();
        PayHandler3 payHandler3 = new PayHandler3(progressDialog);
        String url = "http://www.qkxmall.com/?m=api&c=order&a=shouhuo"+"&uid="+lod.get("USER_INFO","UID","")+"&ordersn="+s;
        BackgroundTask backgroundTask = new BackgroundTask(context,url,payHandler3);
        backgroundTask.doInBackground();

    }

    public void addPrompt(String s1,String s2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(s1);
         View view=View.inflate(getActivity(),R.layout.text_view,null);
        builder.setView(view);
        EditText editText= (EditText) view.findViewById(R.id.et);
        editText.setText(s2);
        builder.setNegativeButton("确定",null);
        builder.setPositiveButton("取消", null);
        builder.create();
        builder.show();
    }
    private class PayHandler3 extends Handler {

        ProgressDialog progressDialog;

        public PayHandler3(ProgressDialog progressDialog) {
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
//                                JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                                JSONObject jsonObject=new JSONObject(bundle.getString("result"));
                                Toast.makeText(getActivity(), jsonObject.optString("msg")
                                        .toString(), Toast.LENGTH_SHORT).show();
                                if (jsonObject.optBoolean("flag")){
                                    allord.setTab(4);



                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(), "数据解析失败", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
    public static class Vh{
        ImageView image;//商品图像
        TextView name;//商品名
        TextView number;//订单编号
        TextView price;//价钱
        TextView num;//数量
        Button pay;//付款按钮
        Button pay2;//确认收货
        Button pay3;//售后
        ImageView imgDel;
        ImageView imgDetail;
    }


}
