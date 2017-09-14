package com.qkxmall.mall.views.cloud.detail;


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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.widget.DragLayout;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.util.DateUtil;
import com.qkxmall.mall.util.Sha1;
import com.qkxmall.mall.views.hui.HuiBuyChoiceSthPageActivity;
import com.qkxmall.mall.views.hui.view.HuiDetailSecondFragment;
import com.qkxmall.mall.views.user.LoginActivity;
import com.qkxmall.mall.views.user.WebFragment;
import com.qkxmall.mall.views.user.WebShowActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

/**
 * ViewHolder simple {@link Fragment} subclass.
 * Use the {@link CloudDragFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CloudDragFragment extends Fragment {
    private String CID;
    private Context context;
    private DragLayout dragLayout = null;
    CloudFirstFragment cloudFirstFragment;
    CloudSecondFragment cloudSecondFragment;

    private HuiDetailSecondFragment huiDetailSecondFragment;//慧购详细参数逻辑类

    private int buyNum = 1;

    private ImageView newer = null;
    private ImageView community = null;
    private ImageView customerService = null;
    private ImageView buy = null;
    private ImageView addToCollect = null;

    HashMap<String, Object> data = new HashMap<>();

    public CloudDragFragment() {
        // Required empty public constructor
    }

    public void newInstance(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            CID = getArguments().getString("cid");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cloud_drag, container, false);
        init(rootView);
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("数据加载中...");
        progressDialog.show();
        LoadInfo loadInfo = new LoadInfo(progressDialog);
        String url = API.CLOUD_GOODS_DETAIL_URL+"&cid="+CID+"&access_token="+(new Sha1().getDigestOfString((
                CID+ DateUtil.getCurrentDate()+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());
        Log.e("Cloud_Address", url);
        BackgroundTask backgroundTask = new BackgroundTask(context,url,loadInfo);
        backgroundTask.doInBackground();
//        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO",Context.MODE_PRIVATE);
//        boolean isLogin = sharedPreferences.getBoolean("isLogin",false);
//        if (isLogin){
//            buy.setOnClickListener(new OnClick());
//            addToCollect.setOnClickListener(new OnClick());
//        }else {
//            buy.setOnClickListener(new Click());
//            addToCollect.setOnClickListener(new Click());
//        }

        newer.setOnClickListener(new AnyClick());
        customerService.setOnClickListener(new AnyClick());
        community.setOnClickListener(new AnyClick());

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO",Context.MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("isLogin",false);
        if (isLogin){
            buy.setOnClickListener(new OnClick());
            addToCollect.setOnClickListener(new OnClick());
        }else {
            buy.setOnClickListener(new Click());
            addToCollect.setOnClickListener(new Click());
        }
    }

    private void init(View rootView) {
        dragLayout = (DragLayout) rootView.findViewById(R.id.drag);
        buy = (ImageView) rootView.findViewById(R.id.buy);
        addToCollect = (ImageView) rootView.findViewById(R.id.addToCollect);
        newer = (ImageView) rootView.findViewById(R.id.newer);
        community = (ImageView) rootView.findViewById(R.id.community);
        customerService = (ImageView) rootView.findViewById(R.id.customService);

    }

    private class LoadInfo extends Handler{
        ProgressDialog progressDialog;

        public LoadInfo(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONObject object = new JSONObject(bundle.getString("result"));
                            JSONObject dataObj = object.getJSONObject("data");
                            if (dataObj.getJSONObject("cloud") != null) {
                                JSONObject jsonObject = dataObj.getJSONObject("cloud");
                                data.put("id", jsonObject.get("id"));
                                data.put("name", jsonObject.get("goods_name"));
                                data.put("goodsname", jsonObject.get("goods_name"));
                                data.put("goods_id", jsonObject.get("goods_id"));
                                data.put("c_totalprice", jsonObject.get("totalprice"));
                                data.put("c_price", jsonObject.get("price"));
                                data.put("c_term", jsonObject.get("term"));
                                data.put("curnum", jsonObject.get("curnum"));
                                data.put("totalnum", jsonObject.get("totalnum"));
                                data.put("commnum", jsonObject.get("commnum"));
                                data.put("img", jsonObject.get("img"));

                                BuyNumHandler buyNumHandler = new BuyNumHandler();
                                cloudFirstFragment = new CloudFirstFragment(CID);
                                cloudFirstFragment.newInstance(context, data, buyNumHandler);
                                cloudSecondFragment = new CloudSecondFragment();
                                cloudSecondFragment.newInstance(context);
                                cloudSecondFragment.setData(data);

                                getFragmentManager().beginTransaction().add(R.id.first, cloudFirstFragment).add(R.id.second, cloudSecondFragment).commit();
                                dragLayout.setNextPageListener(new DragLayout.ShowNextPageNotifier() {
                                    @Override
                                    public void onDragNext() {
                                        cloudSecondFragment.newInstance(context);
                                    }
                                });
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                    break;
                case BackgroundTask.FAILURE:{
                    Toast.makeText(context, "数据加载失败，请检查网络连接后重试", Toast.LENGTH_SHORT).show();
                }
                    break;
            }
            progressDialog.dismiss();
        }
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                //立即购买
                case R.id.buy:
//                {
//
//                    //获取个数
//                    EditText numTemp = (EditText) cloudFirstFragment.getView().findViewById(R.id.number);
//                    buyNum = Integer.parseInt(numTemp.getText().toString().trim());
//
//                    LOD lod = new LOD(context);
//                    double buyPrice = Double.parseDouble((String) data.get("c_price"));
//                    buyPrice *= buyNum;
//                    final String orderno = getOutTradeNo();
//                    final double finalBuyPrice = buyPrice;
//                    Handler handler = new Handler(){
//                        @Override
//                        public void handleMessage(Message msg) {
//                            switch (msg.what){
//                                case BackgroundTask.SUCCESS:{
//                                    Bundle bundle = msg.getData();
//                                    try {
//                                        JSONObject jsonObject = new JSONObject(bundle.getString("result"));
//                                         if (jsonObject.getBoolean("flag")){
////                                             Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
////                                             Intent intent = new Intent(context, ALiPayPayActivity.class);
//                                             Intent intent = new Intent(context, CloudLiJiGouMai.class);
//                                             intent.putExtra("orderno",orderno);
//                                             intent.putExtra("c_price", finalBuyPrice +"");
//                                             intent.putExtra("name", "云购 "+ (String) data.get("name"));
//                                             intent.putExtra("info", (String) data.get("goodsname"));
//                                             intent.putExtra("cid" , (String) data.get("id"));
//                                             intent.putExtra("num" , buyNum + "");
//                                             intent.putExtra("c_price",(String)data.get("c_price"));
//                                             intent.putExtra("boolean",true);
//
//                                             startActivity(intent);
//                                         }else {
//                                             Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
//                                         }
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                        Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
//                                    }
//
//                                }
//                                    break;
//                                case BackgroundTask.FAILURE:{
//                                    Toast.makeText(context, "网络连接失败，请重试", Toast.LENGTH_SHORT).show();
//                                }
//                                    break;
//                            }
//                        }
//                    };
//                    String url = API.BUY_CLOUD_PRODUCTS+"&uid="+lod.get("USER_INFO","UID","")
//                            +"&cid="+CID
//                            +"&num="+buyNum
//                            +"&totalpay="+buyPrice
//                            +"&tradeno="+orderno;
//                    System.out.println("=======url" + url);
//
//                    BackgroundTask backgroundTask = new BackgroundTask(context,url,handler);
//                    backgroundTask.doInBackground();
//
//                }
                    //获取个数
                    EditText numTemp = (EditText) cloudFirstFragment.getView().findViewById(R.id.number);
                    buyNum = Integer.parseInt(numTemp.getText().toString().trim());
                    final String orderno = getOutTradeNo();

                                              Intent intent = new Intent(context, HuiBuyChoiceSthPageActivity.class);

                                             intent.putExtra("name", "云购 "+ (String) data.get("name"));
                                             intent.putExtra("info", (String) data.get("goodsname"));
                                             intent.putExtra("cid", (String) data.get("id"));
                                             intent.putExtra("num", buyNum + "");
                                             intent.putExtra("c_price",(String)data.get("c_price"));
                                             intent.putExtra("img",(String)data.get("img"));
                                             intent.putExtra("bl",true);
                                             intent.putExtra("name",(String)data.get("name"));
                                             intent.putExtra("info",(String)data.get("goodsname"));
                                                  intent.putExtra("orderno",orderno);
                                             startActivity(intent);
                                             getActivity().finish();
                    System.out.println("获取云购商品个数：" + buyNum);
                    System.out.println("云购id：" + data.get("id"));
                    System.out.println("全局的Cid：" + CID);
                    System.out.println("商品name" + data.get("name"));
                    System.out.println("商品描述info：" +  data.get("goodsname").toString());


                    break;
                case R.id.addToCollect:{
                    System.out.println("！！！！！！！！！！！云购点击加入购物车：");
                    Handler handler = new Handler(){

                        @Override
                        public void handleMessage(Message msg) {
                            switch (msg.what){
                                case BackgroundTask.SUCCESS:{
                                    Bundle bundle = msg.getData();
                                    System.out.println("！！！！！！！！！！数据源+"+bundle.getString("result"));

                                        try {
                                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));

                                            if (jsonObject.optBoolean("flag")){
                                                Toast.makeText(context, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                                            }else {
                                                Toast.makeText(context, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(context, "添加购物车成功", Toast.LENGTH_SHORT).show();
                                            System.out.println("！！！！！！！！数据解析失败+" + e);
                                        }

                                }
                                    break;
                                case BackgroundTask.FAILURE:{
                                    Toast.makeText(context, "网络请求失败，请重试", Toast.LENGTH_SHORT).show();
                                }
                                    break;
                            }
                        }
                    };

                    //获取个数
                    EditText numTemp2 = (EditText) cloudFirstFragment.getView().findViewById(R.id.number);
                    buyNum = Integer.parseInt(numTemp2.getText().toString().trim());
                    LOD lod = new LOD(context);
                    String url = API.ADD_TO_CLOUD_COLLECT+"&uid="+lod.get("USER_INFO","UID","")+"&cloud_id="+CID+"&num="+buyNum;
                    System.out.println("！！！！云购加入购物车 URL:" +url);

                    Log.e("ErrorUrl",url);
                    BackgroundTask backgroundTask = new BackgroundTask(context,url,handler);
                    backgroundTask.doInBackground();
                }
                    break;
            }
        }
    }

    private class BuyNumHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.FINISH:{
                    Bundle bundle = msg.getData();
                    buyNum = bundle.getInt("number");
                }
                    break;
            }
        }
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     *
     */
    public String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        String s = (new SimpleDateFormat("",Locale.getDefault())).format(new Date());

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    private class Click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        }
    }

    private class AnyClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.newer:{
                    Intent intent = new Intent(context, WebShowActivity.class);
                    intent.putExtra("type", WebFragment.CLOUD_NEW_HELP);
                    context.startActivity(intent);
                }
                    break;
                case R.id.customService:{
                    Intent intent = new Intent(context, WebShowActivity.class);
                    intent.putExtra("type", WebFragment.KE_FU);
                    context.startActivity(intent);
                }
                    break;
                case R.id.community:{
                    Toast.makeText(context, "即将上线", Toast.LENGTH_SHORT).show();
                }
                    break;
            }
        }
    }
}
