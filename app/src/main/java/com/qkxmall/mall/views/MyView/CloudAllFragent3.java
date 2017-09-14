package com.qkxmall.mall.views.MyView;

import android.app.AlertDialog;
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
import com.qkxmall.mall.views.cloud.order.AllCloudOrderActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/5/10.
 */
public class CloudAllFragent3 extends Fragment {

    Context context;
    ListView listView = null;
    TextView nothing = null;
    int tag = 0;
    LOD lod;
    int i;

    List<HashMap<String ,Object>> allOrderList = new ArrayList<>();


    public CloudAllFragent3(Context context) {
        // Required empty public constructor
        this.context = context;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tag = getArguments().getInt("tag");
        }
    }
    public void onPullDownToRefresh(){
        System.out.println("！！！！！！！！！！下拉刷新更多：");
//        loadmsg();
        ((AllCloudOrderActivity)getActivity()).finishrefresh();

    }
    public void onPullUpToRefresh(){
        //TODO 上拉加载自己写
        addData();
//        ((AllCloudOrderActivity)getActivity()).finishrefresh();

    }
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

    public void addData(){
        i+=1;
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("加载中...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        AllOrderHandler2 allOrderHandler2 = new AllOrderHandler2(progressDialog);
        String url = API.CLOUD_ORDER+"&type=3"+"&uid="+lod.get("USER_INFO","UID","")+"&page="+i;
        Log.e("URL", url);
        BackgroundTask backgroundTask = new BackgroundTask(context,url,allOrderHandler2);
        backgroundTask.doInBackground();

    }

    private void loadmsg() {

                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("加载中...");
                progressDialog.show();
                progressDialog.setCancelable(false);
                AllOrderHandler allOrderHandler = new AllOrderHandler(progressDialog);
                String url = API.CLOUD_ORDER+"&type=3"+"&uid="+lod.get("USER_INFO","UID","")+"&page="+i;
                Log.e("URL", url);
        String url2 =API.CLOUD_ORDER+"&type=3"+"&uid="+lod.get("USER_INFO","UID","")+"&page="+i+
                "&access_token="+(new Sha1().getDigestOfString((DateUtil.getCurrentDate()+i+"3"+lod.get("USER_INFO","UID","")+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());
                BackgroundTask backgroundTask = new BackgroundTask(context,url2,allOrderHandler);
                backgroundTask.doInBackground();


    }

    private void init(View rootView) {
        i=1;
        listView = (ListView) rootView.findViewById(R.id.list);
        nothing = (TextView) rootView.findViewById(R.id.nothing);
    }

    public class MyAdapter extends BaseAdapter{
             BitmapUtils utils=new BitmapUtils(context);
        @Override
        public int getCount() {
            return allOrderList.size();
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
            if (convertView != null){
                a = (A) convertView.getTag();
            }else {
                a = new A();
                convertView =View.inflate(context,R.layout.child_cloud_all3,null);
                a.image=(ImageView)convertView.findViewById(R.id.image_title);
                a.name=(TextView)convertView.findViewById(R.id.child_name);
                a.tv1=(TextView)convertView.findViewById(R.id.tv1);
                a.tv2=(TextView)convertView.findViewById(R.id.tv2);
                a.tv3=(TextView)convertView.findViewById(R.id.tv3);
                a.textView=(TextView)convertView.findViewById(R.id.textView);
                a.btn1=(Button)convertView.findViewById(R.id.btn1);
                a.btn2=(Button)convertView.findViewById(R.id.btn2);
                convertView.setTag(a);
            }
           final HashMap<String ,Object> data = allOrderList.get(position);
//            utils.display(a.image,API.ADD +data.get("img"));//设置图片
            Glide.with(CloudAllFragent3.this).load(API.ADD + data.get("img")).into(a.image);
            int num=Integer.parseInt(data.get("totalnum").toString().trim());//总需
            int current=Integer.parseInt(data.get("curnum").toString().trim());//已参与
//            float paydata=Float.parseFloat(data.get("paydate").toString().trim());
            long paydata=Long.parseLong(data.get("paydate").toString().trim())*1000;
            a.name.setText(data.get("goods_name").toString());//商品名
            Date da=new Date(paydata);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String s=data.get("microtime").toString().trim();
            String news=s.substring(s.length()-3);
            a.tv1.setText(data.get("term").toString());//
            a.tv2.setText(current + "");//
            a.tv3.setText(data.get("luckycode").toString());
            a.textView.setText(sdf.format(da)+":"+news);//

            a.btn1.setVisibility(View.VISIBLE);
            a.btn2.setVisibility(View.VISIBLE);

            a.btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     System.out.println("!!!!!!!!!!!!!!物流信息点击");
                    addPrompt(data.get("deliverycom").toString(),data.get("deliveryno").toString());


                }
            });
            a.btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("!!!!!!!!!!!!!!确认收货点击");

                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("提交中...");
                    progressDialog.show();
                    PayHandler3 payHandler3 = new PayHandler3(progressDialog);
                    String url = "http://www.qkxmall.com/?m=api&c=cloud&a=cshouhuo"+"&uid="+lod.get("USER_INFO","UID","")+"&orderno="+data.get("orderno").toString();
                                   System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! 云购收货"+url);
                    BackgroundTask backgroundTask = new BackgroundTask(context,url,payHandler3);
                    backgroundTask.doInBackground();


                }
            });


            return convertView;
        }
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
    public static class A {
        ImageView image;//商品图片
        TextView name;//商品名
        TextView tv1;//参与人次
        TextView tv2;//幸运号
        TextView tv3;//剩余
        TextView textView;//期数
        Button btn1;
        Button btn2;
    }
    MyAdapter adapter;
    private class AllOrderHandler extends Handler {

        ProgressDialog progressDialog;

        public AllOrderHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            AllCloudOrderActivity activity=(AllCloudOrderActivity)getActivity();
            activity.finishrefresh();
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONObject object = new JSONObject(bundle.getString("result"));
                            JSONArray array = object.optJSONArray("data");
                            for (int i = 0 ; i< array.length() ;i++){
                                JSONObject jsonObject = array.optJSONObject(i);
                                HashMap<String ,Object> data = new HashMap<>();
                                data.put("id", jsonObject.opt("id"));//云购订单id
                                data.put("cloud_id", jsonObject.opt("cloud_id"));//云购id
                                data.put("term", jsonObject.opt("term"));//期数
                                data.put("num", jsonObject.opt("num"));//购买数量
                                data.put("prderno", jsonObject.opt("orderno"));
                                data.put("paydate", jsonObject.opt("paydate"));//支付日期
                                data.put("goods_name", jsonObject.opt("goods_name"));//商品名
                                data.put("curnum", jsonObject.opt("curnum"));//当前数量
                                data.put("totalnum", jsonObject.opt("totalnum"));//总需数量
                                data.put("luckycode", jsonObject.opt("luckycode"));//云购幸运码
                                data.put("goods_id", jsonObject.opt("goods_id"));//商品id
                                data.put("img", jsonObject.opt("img"));//商品图片
                                data.put("microtime",jsonObject.opt("microtime"));//毫秒数
                                data.put("orderno",jsonObject.optString("orderno"));
                                data.put("deliverycom",jsonObject.optString("deliverycom"));//快递公司名
                                data.put("deliveryno",jsonObject.optString("deliveryno"));//快递单号
                                allOrderList.add(data);
                            }
//                            CloudAllAdapter cloudAllAdapter = new CloudAllAdapter(context,allOrderList);
                             adapter=new MyAdapter();
                            listView.setAdapter(adapter);
                            if (allOrderList.size() == 0){
                                nothing.setVisibility(View.VISIBLE);
                            }else {
                                nothing.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败1", Toast.LENGTH_SHORT).show();
                             System.out.println("!!!!!!!!!!!!！！！数据解析失败1"+e);
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(context, "网络连接错误", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class AllOrderHandler2 extends Handler {

        ProgressDialog progressDialog;

        public AllOrderHandler2(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            AllCloudOrderActivity activity=(AllCloudOrderActivity)getActivity();
            activity.finishrefresh();
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONObject object = new JSONObject(bundle.getString("result"));
                            JSONArray array = object.optJSONArray("data");
                            for (int i = 0 ; i< array.length() ;i++){
                                JSONObject jsonObject = array.optJSONObject(i);
                                HashMap<String ,Object> data = new HashMap<>();
                                data.put("id", jsonObject.opt("id"));//云购订单id
                                data.put("cloud_id", jsonObject.opt("cloud_id"));//云购id
                                data.put("term", jsonObject.opt("term"));//期数
                                data.put("num", jsonObject.opt("num"));//购买数量
                                data.put("prderno", jsonObject.opt("orderno"));
                                data.put("paydate", jsonObject.opt("paydate"));//支付日期
                                data.put("goods_name", jsonObject.opt("goods_name"));//商品名
                                data.put("curnum", jsonObject.opt("curnum"));//当前数量
                                data.put("totalnum", jsonObject.opt("totalnum"));//总需数量
                                data.put("luckycode", jsonObject.opt("luckycode"));//云购幸运码
                                data.put("goods_id", jsonObject.opt("goods_id"));//商品id
                                data.put("img", jsonObject.opt("img"));//商品图片
                                data.put("microtime",jsonObject.opt("microtime"));//毫秒数
                                data.put("orderno",jsonObject.optString("orderno"));
                                data.put("deliverycom",jsonObject.optString("deliverycom"));//快递公司名
                                data.put("deliveryno",jsonObject.optString("deliveryno"));//快递单号
                                allOrderList.add(data);
                            }
                            adapter.notifyDataSetChanged();
////                            CloudAllAdapter cloudAllAdapter = new CloudAllAdapter(context,allOrderList);
//                            adapter=new MyAdapter();
//                            listView.setAdapter(adapter);
//                            if (allOrderList.size() == 0){
//                                nothing.setVisibility(View.VISIBLE);
//                            }else {
//                                nothing.setVisibility(View.GONE);
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
//                            System.out.println("!!!!!!!!!!!!！！！数据解析失败1"+e);
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(context, "网络连接错误", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
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
                                    getActivity().finish();
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



}
