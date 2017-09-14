package com.qkxmall.mall.views.cloud.order;


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
 * Use the {@link CloudAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CloudAllFragment extends Fragment {

    Context context;
    ListView listView = null;
    TextView nothing = null;
    int tag = 0;
    LOD lod;

    List<HashMap<String ,Object>> allOrderList = new ArrayList<>();
    List<HashMap<String ,Object>> waiteOrderList = new ArrayList<>();
    List<HashMap<String ,Object>> happyOrderList = new ArrayList<>();
    List<HashMap<String ,Object>> receiveOrderList = new ArrayList<>();
    List<HashMap<String ,Object>> evaluationOrderList = new ArrayList<>();

    public CloudAllFragment() {
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
    public void onPullDownToRefresh(){
        loadmsg();

    }
    public void onPullUpToRefresh(){
        //TODO 上拉加载自己写

        ((AllCloudOrderActivity)getActivity()).finishrefresh();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hui_all, container, false);
        init(rootView);

        lod = new LOD(context);
//        loadmsg();
        return rootView;
    }

    private void loadmsg() {
        switch (tag){
            case 0:{//全部订单 √
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("加载中...");
                progressDialog.show();
                progressDialog.setCancelable(false);
                AllOrderHandler allOrderHandler = new AllOrderHandler(progressDialog);
                String url = API.CLOUD_ORDER+"&type=1"+"&uid="+lod.get("USER_INFO","UID","");
                Log.e("URL", url);
                BackgroundTask backgroundTask = new BackgroundTask(context,url,allOrderHandler);
                backgroundTask.doInBackground();
            }
            break;
            case 1:{//待揭晓 ×
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("加载中...");
                progressDialog.show();
                progressDialog.setCancelable(false);
                String url = API.CLOUD_ORDER+"&type=pay"+"&uid="+lod.get("USER_INFO","UID","");
                //Toast.makeText(context, "需要云购订单状态，<待揭晓>", Toast.LENGTH_SHORT).show();
                ((AllCloudOrderActivity)getActivity()).finishrefresh();//此行代码是为了关闭下拉刷新功能，等功能实现了删除此行代码
                progressDialog.dismiss();
            }
            break;
            case 2:{//开心果 ×
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("加载中...");
                progressDialog.show();
                progressDialog.setCancelable(false);
                String url = API.CLOUD_ORDER+"&type=delivery"+"&uid="+lod.get("USER_INFO","UID","");
                ((AllCloudOrderActivity)getActivity()).finishrefresh();//此行代码是为了关闭下拉刷新功能，等功能实现了删除此行代码
                //Toast.makeText(context, "需要云购订单状态，<开心果>", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
            break;
            case 3:{//待收货 ×
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("加载中...");
                progressDialog.show();
                progressDialog.setCancelable(false);
                String url = API.CLOUD_ORDER+"&type=finish"+"&uid="+lod.get("USER_INFO","UID","");
                ((AllCloudOrderActivity)getActivity()).finishrefresh();//此行代码是为了关闭下拉刷新功能，等功能实现了删除此行代码
                //Toast.makeText(context, "需要云购订单状态，<待收货>", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
            break;
            case 4:{//晒单评价 ×
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("加载中...");
                progressDialog.show();
                //Toast.makeText(context, "待添加类型，<晒单评价>", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                nothing.setText("木有啦");
                ((AllCloudOrderActivity)getActivity()).finishrefresh();//此行代码是为了关闭下拉刷新功能，等功能实现了删除此行代码
                nothing.setVisibility(View.VISIBLE);
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
            AllCloudOrderActivity activity=(AllCloudOrderActivity)getActivity();
            activity.finishrefresh();
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONObject object = new JSONObject(bundle.getString("result"));
                            JSONArray array = object.getJSONArray("data");
                            for (int i = 0 ; i< array.length() ;i++){
                                JSONObject jsonObject = array.getJSONObject(i);
                                HashMap<String ,Object> data = new HashMap<>();
                                data.put("id", jsonObject.get("id"));
                                data.put("cloud_id", jsonObject.get("cloud_id"));
                                data.put("term", jsonObject.get("term"));
                                data.put("num", jsonObject.get("num"));
                                data.put("prderno", jsonObject.get("orderno"));
                                data.put("paydate", jsonObject.get("paydate"));
                                data.put("goodsname", jsonObject.get("cloudname"));
                                data.put("curnum", jsonObject.get("curnum"));
                                data.put("totalnum", jsonObject.get("totalnum"));
                                data.put("lucknumber", jsonObject.get("luckynumber"));
                                data.put("goods_id", jsonObject.get("goods_id"));
                                data.put("img", jsonObject.get("img"));
                                allOrderList.add(data);
                            }
                            CloudAllAdapter cloudAllAdapter = new CloudAllAdapter(context,allOrderList);
                            listView.setAdapter(cloudAllAdapter);
                            if (allOrderList.size() == 0){
                                nothing.setVisibility(View.VISIBLE);
                            }else {
                                nothing.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
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


}
