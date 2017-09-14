package com.qkxmall.mall.views.func.coucou.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.util.DateUtil;
import com.qkxmall.mall.util.Sha1;
import com.qkxmall.mall.views.MyView.CloudAllFragent0;
import com.qkxmall.mall.views.MyView.HuiAllFragment0;
import com.qkxmall.mall.views.address.AccountAddressManageActivity;
import com.qkxmall.mall.views.cloud.order.AllCloudOrderActivity;
import com.qkxmall.mall.views.fragment.CenterFragment;
import com.qkxmall.mall.views.fragment.FragmentMainActivity;
import com.qkxmall.mall.views.hui.order.AllHuiOrderActivity;
import com.qkxmall.mall.views.user.AccountManageActivity;
import com.qkxmall.mall.views.user.ChangePasswordActivity;
import com.qkxmall.mall.views.user.LoginActivity;
import com.qkxmall.mall.views.user.PerCenterMyWalletActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by yuna on 2017/8/9.
 */

public class NewCenterFragment extends Fragment {
    private TextView perCenterUserName = null;
    private SimpleDraweeView perCenterUserAvatar = null;
    private TextView perCenterMyWallet = null;
//    private TextView perCenterMyTrolley = null;
    private TextView perCenterAccountManager = null;
    private TextView perCenterAllCloudOrder = null;
    private TextView perCenterAllCouCouOrder;
    private RelativeLayout perCenterCloudWaitToAnnounce = null;
    private RelativeLayout perCenterCloudFourLeavedClover = null;
    private RelativeLayout perCenterCloudWaitToReceiving = null;
    private RelativeLayout perCenterCloudEvaluate = null;
    private RelativeLayout perCenterCloudAnnounce = null;
    private RelativeLayout perCenterCouDaijiexiao = null;
    private RelativeLayout perCenterCoukaixindou = null;
    private RelativeLayout perCenterCoudaishouhuo = null;
    private RelativeLayout rl1;
    private RelativeLayout rl2;
    private RelativeLayout rl3;
    private RelativeLayout rl4;
    private RelativeLayout rl5;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;

    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int count4 = 0;
    private int count5 = 0;
    private int count6 = 0;
    private int count7 = 0;
    private int count8 = 0;
//    private RelativeLayout perCenterHuiAllOrder = null;
//    private LinearLayout perCenterHuiWaitToPayment = null;
//    private LinearLayout perCenterHuiWaitToDeliver = null;
//    private LinearLayout perCenterHuiWaitToReceiving = null;
//    private LinearLayout perCenterHuiEvaluate = null;
//    private LinearLayout perCenterHuiAfterMarket = null;

    Context context;
    Bundle savedInstanceState;
    FragmentManager fragmentManager;
    ImageView cart;
    ImageView center;
    LOD lod;

    public void init(Context context , Bundle savedInstanceState , FragmentManager fragmentManager , ImageView cart , ImageView center) {
        this.context = context;
        this.savedInstanceState = savedInstanceState;
        this.fragmentManager = fragmentManager;
        this.cart = cart;
        this.center = center;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.new_fragment_center,container,false);
        init(rootView);
        //初始化用户数据
        /*
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("数据加载中...");
        progressDialog.show();*/
//        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO",Context.MODE_PRIVATE);
        loadUserInfo();

        //设置控件点击事件
        perCenterAllCloudOrder.setOnClickListener(new NewCenterFragment.OnClickListeners());
        perCenterAllCouCouOrder.setOnClickListener(new NewCenterFragment.OnClickListeners());
        perCenterMyWallet.setOnClickListener(new NewCenterFragment.OnClickListeners());
        perCenterAccountManager.setOnClickListener(new NewCenterFragment.OnClickListeners());
//        perCenterHuiAllOrder.setOnClickListener(new NewCenterFragment.OnClickListeners());
        perCenterCloudWaitToAnnounce.setOnClickListener(new NewCenterFragment.OnClickListeners());
        perCenterCloudFourLeavedClover.setOnClickListener(new NewCenterFragment.OnClickListeners());
        perCenterCloudWaitToReceiving.setOnClickListener(new NewCenterFragment.OnClickListeners());
        perCenterCloudEvaluate.setOnClickListener(new NewCenterFragment.OnClickListeners());
        perCenterCloudAnnounce.setOnClickListener(new NewCenterFragment.OnClickListeners());
        perCenterCoudaishouhuo.setOnClickListener(new NewCenterFragment.OnClickListeners());
        perCenterCoukaixindou.setOnClickListener(new NewCenterFragment.OnClickListeners());
        perCenterCouDaijiexiao.setOnClickListener(new NewCenterFragment.OnClickListeners());
        rl1.setOnClickListener(new NewCenterFragment.OnClickListeners());
        rl2.setOnClickListener(new NewCenterFragment.OnClickListeners());
        rl3.setOnClickListener(new NewCenterFragment.OnClickListeners());
        rl4.setOnClickListener(new NewCenterFragment.OnClickListeners());
        rl5.setOnClickListener(new NewCenterFragment.OnClickListeners());
//        perCenterHuiEvaluate.setOnClickListener(new NewCenterFragment.OnClickListeners());
//        perCenterHuiAfterMarket.setOnClickListener(new NewCenterFragment.OnClickListeners());
//        perCenterHuiWaitToDeliver.setOnClickListener(new NewCenterFragment.OnClickListeners());
//        perCenterHuiWaitToPayment.setOnClickListener(new NewCenterFragment.OnClickListeners());
//        perCenterHuiWaitToReceiving.setOnClickListener(new NewCenterFragment.OnClickListeners());
//        perCenterMyTrolley.setOnClickListener(new NewCenterFragment.OnClickListeners());
        loadmsg();
        loadmsg1();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadUserInfo();
    }

    private void loadUserInfo() {
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case BackgroundTask.SUCCESS:{
                        Bundle bundle = msg.getData();
                        if (bundle != null){
                            try {
                                JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                                perCenterUserAvatar.setImageURI(Uri.parse(jsonObject.getString("ico")));
//                                perCenterUserAvatar.setImageURI(Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504788259229&di=b7b1da6317d346bd9fa0796cabf5169f&imgtype=0&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F120423%2F107913-12042323220753.jpg"));
                                perCenterUserName.setText(jsonObject.getString("nickname"));
                            } catch (JSONException e) {
                                e.printStackTrace();
//                                Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    break;
                    case BackgroundTask.FAILURE:{
                        Toast.makeText(context, "请检查网络连接", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        };
        lod = new LOD(context);
        String url = API.USER_INFO+"&uid=" + lod.get("USER_INFO", "UID","");
        BackgroundTask backgroundTask = new BackgroundTask(context,url,handler);
        backgroundTask.doInBackground();
    }

    //初始换布局控件
    private void init(View rootView) {
        perCenterUserName = (TextView) rootView.findViewById(R.id.per_center_user_name);
        perCenterUserAvatar = (SimpleDraweeView) rootView.findViewById(R.id.headImage);
//        perCenterMyTrolley = (TextView) rootView.findViewById(R.id.per_center_my_trolley);
        perCenterMyWallet = (TextView) rootView.findViewById(R.id.per_center_my_wallet);
        perCenterAccountManager = (TextView) rootView.findViewById(R.id.per_center_account_manager);
        perCenterAllCloudOrder = (TextView) rootView.findViewById(R.id.per_center_all_cloud_order);
        perCenterAllCouCouOrder = (TextView) rootView.findViewById(R.id.per_center_all_cloud_order2);
        perCenterCouDaijiexiao = (RelativeLayout) rootView.findViewById(R.id.per_center_cloud_wait_to_jiexiao2);
        perCenterCoukaixindou = (RelativeLayout) rootView.findViewById(R.id.per_center_cloud_kaixinguo2);
        perCenterCoudaishouhuo = (RelativeLayout) rootView.findViewById(R.id.per_center_cloud_wait_to_shouhuo2);
        perCenterCloudAnnounce = (RelativeLayout) rootView.findViewById(R.id.per_center_cloud_announce);

        perCenterCloudWaitToAnnounce = (RelativeLayout) rootView.findViewById(R.id.per_center_cloud_wait_to_announce);
        perCenterCloudFourLeavedClover = (RelativeLayout) rootView.findViewById(R.id.per_center_cloud_four_leaved_clover);
        perCenterCloudWaitToReceiving = (RelativeLayout) rootView.findViewById(R.id.per_center_cloud_wait_to_receiving);
        perCenterCloudEvaluate = (RelativeLayout) rootView.findViewById(R.id.per_center_cloud_evaluate);
        rl1 = (RelativeLayout) rootView.findViewById(R.id.rl_center_my_wallet);
        rl2 = (RelativeLayout) rootView.findViewById(R.id.rl_center_my_address);
        rl3 = (RelativeLayout) rootView.findViewById(R.id.rl_center_my_account);
        rl4 = (RelativeLayout) rootView.findViewById(R.id.rl_center_my_account2);
        rl5 = (RelativeLayout) rootView.findViewById(R.id.rl_center_my_layout);
//        perCenterHuiAllOrder = (RelativeLayout) rootView.findViewById(R.id.per_center_hui_all_order);
//        perCenterHuiWaitToPayment = (LinearLayout) rootView.findViewById(R.id.per_center_hui_wait_to_payment);
//        perCenterHuiWaitToDeliver = (LinearLayout) rootView.findViewById(R.id.per_center_hui_wait_to_deliver);
//        perCenterHuiEvaluate = (LinearLayout) rootView.findViewById(R.id.per_center_hui_evaluate);
//        perCenterHuiAfterMarket = (LinearLayout) rootView.findViewById(R.id.per_center_hui_aftermarket);
//        perCenterHuiWaitToReceiving = (LinearLayout) rootView.findViewById(R.id.per_center_hui_wit_to_receiving);
        tv1 = (TextView)rootView.findViewById(R.id.tv1);
        tv2 = (TextView)rootView.findViewById(R.id.tv2);
        tv3 = (TextView)rootView.findViewById(R.id.tv3);
        tv4 = (TextView)rootView.findViewById(R.id.tv4);
        tv5 = (TextView)rootView.findViewById(R.id.tv5);
        tv6 = (TextView)rootView.findViewById(R.id.tv6);
        tv7 = (TextView)rootView.findViewById(R.id.tv7);
        tv8 = (TextView)rootView.findViewById(R.id.tv8);


    }

    @Override
    public void onStart() {
        super.onStart();
//        loadmsg();
//        loadmsg1();
    }

    private void loadmsg1() {

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("加载中...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        AllOrderHandler allOrderHandler = new AllOrderHandler(progressDialog);
//        SharedPreferences preferences = getActivity().getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
//        preferences.getString("UID","");

        String url2 =API.CLOUD_ORDER+"&uid="+lod.get("USER_INFO","UID","")+
                "&access_token="+(new Sha1().getDigestOfString((DateUtil.getCurrentDate()+lod.get("USER_INFO","UID","")+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());
        Log.e("URL", url2);
        BackgroundTask backgroundTask = new BackgroundTask(context,url2,allOrderHandler);
        backgroundTask.doInBackground();


    }
    public void loadmsg() {

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("加载中...");
        progressDialog.show();
        PayHandler payHandler = new PayHandler(progressDialog);
        String url = API.HUI_ORDER_LIST+"&uid="+lod.get("USER_INFO","UID","");
        Log.e("URL",url);
        BackgroundTask backgroundTask = new BackgroundTask(context,url,payHandler);
        backgroundTask.doInBackground();

    }

    private class AllOrderHandler extends Handler {

        ProgressDialog progressDialog;

        public AllOrderHandler(ProgressDialog progressDialog) {
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
                            if(object != null){
                                JSONArray array = object.optJSONArray("data");
                                if(array != null){
                                    for (int i = 0 ; i< array.length() ;i++){
                                        JSONObject jsonObject = array.optJSONObject(i);
                                        HashMap<String ,Object> data = new HashMap<>();

                                        if(jsonObject.optString("status").equals("0")){
                                            count6 = count6+1;
                                        }

                                    }
                                }


                            }

                            if(count6 == 0){
                                tv6.setVisibility(View.GONE);
                            }else {
                                tv6.setText((count6+1)+"");
                            }
//                            CloudAllAdapter cloudAllAdapter = new CloudAllAdapter(context,allOrderList);


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
                                    if(jsonObject.optString("order_desc").equals("待付款")){
                                        count1 = count1+1;
                                    }else if(jsonObject.optString("order_desc").equals("待发货")){
                                        count2 = count2+1;
                                    }else if(jsonObject.optString("order_desc").equals("待收货")){
                                        count3 = count3+1;
                                    }else if(jsonObject.optString("order_desc").equals("待评价")){
                                        count4 = count4+1;
                                    }
                                    if(count1 == 0){
                                        tv1.setVisibility(View.GONE);
                                    }else {
                                        tv1.setVisibility(View.VISIBLE);
                                        tv1.setText((count1+1)+"");
                                    }
                                    if(count2 == 0){
                                        tv2.setVisibility(View.GONE);
                                    }else {
                                        tv1.setVisibility(View.VISIBLE);
                                        tv2.setText((count2+1)+"");
                                    }
                                    if(count3 == 0){

                                        tv3.setVisibility(View.GONE);
                                    }else {
                                        tv1.setVisibility(View.VISIBLE);
                                        tv3.setText((count3+1)+"");
                                    }
                                    if(count4 == 0){
                                        tv4.setVisibility(View.GONE);
                                    }else {
                                        tv1.setVisibility(View.VISIBLE);
                                        tv4.setText((count4+1)+"");
                                    }

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "数据解析失败2", Toast.LENGTH_SHORT).show();

                                System.out.println("!!!!!!!!!!数据解析失败2" + e);
                            }
                        }else {

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

    //内部点击事件监听类
    private class OnClickListeners implements View.OnClickListener {
        Intent intentAllCouCouOrder = new Intent(context,AllCloudOrderActivity.class);
        Intent intentAllHuiOrder = new Intent(context,AllHuiOrderActivity.class);
        @Override
        public void onClick(View v) {

            SharedPreferences preferences = context.getSharedPreferences("USER_INFO", MODE_PRIVATE);
            boolean isLogin = preferences.getBoolean("isLogin",false);
            if (isLogin){
                switch (v.getId()){
                    case R.id.per_center_all_cloud_order:
                        startActivity(intentAllHuiOrder);
                        break;
                    case R.id.per_center_cloud_announce:
//                        Intent intent = new Intent(context, AllCloudClassItem.class);
//                      intent.putExtra("ClassTitle","即将揭晓");
//                        intentAllCloudOrder.putExtra("pageID", 0);
//                        startActivity(intent);
                        intentAllHuiOrder.putExtra("pageID", 1);
                        startActivity(intentAllHuiOrder);
                        break;
                    case R.id.per_center_cloud_wait_to_announce:
                        intentAllHuiOrder.putExtra("pageID", 1);
                        startActivity(intentAllHuiOrder);
                        break;
                    case R.id.per_center_cloud_four_leaved_clover:
                        intentAllHuiOrder.putExtra("pageID",2);
                        startActivity(intentAllHuiOrder);
                        break;
                    case R.id.per_center_cloud_wait_to_receiving:
                        intentAllHuiOrder.putExtra("pageID",3);
                        startActivity(intentAllHuiOrder);
                        break;
                    case R.id.per_center_cloud_evaluate:
                        intentAllHuiOrder.putExtra("pageID",4);
                        startActivity(intentAllHuiOrder);
                        break;
                    case R.id.per_center_my_trolley:
//                       FragmentTransaction ft= getActivity().getSupportFragmentManager().beginTransaction();
                        FragmentActivity activity= getActivity();
                        if(activity!= null){
                            FragmentMainActivity mainActivit =(FragmentMainActivity)activity;
                            mainActivit.selectCart(null);

                        }
//                        if (savedInstanceState == null){
//                            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                            CartFragment cartFragment = new CartFragment();
//                            cartFragment.init(context);
//                            fragmentTransaction.add(R.id.fragment_main, cartFragment);
//                            fragmentTransaction.commit();
//                            cart.setSelected(true);
//                            center.setSelected(false);
//                        }
                        break;
                    case R.id.per_center_all_cloud_order2:
                        startActivity(intentAllCouCouOrder);
                        break;
                    case R.id.per_center_hui_wait_to_payment:
                        intentAllCouCouOrder.putExtra("pageID",1);
                        startActivity(intentAllCouCouOrder);
                        break;
                    case R.id.per_center_cloud_wait_to_jiexiao2:
                        intentAllCouCouOrder.putExtra("pageID",1);
                        startActivity(intentAllCouCouOrder);
                        break;
                    case R.id.per_center_cloud_kaixinguo2:
                        intentAllCouCouOrder.putExtra("pageID",2);
                        startActivity(intentAllCouCouOrder);
                        break;
                    case R.id.per_center_cloud_wait_to_shouhuo2:
                        startActivity(intentAllCouCouOrder);
                        intentAllCouCouOrder.putExtra("pageID", 3);
                        break;
                    case R.id.per_center_hui_aftermarket:
                        intentAllHuiOrder.putExtra("pageID", 0);
                        startActivity(intentAllHuiOrder);
                        break;
                    //钱包
//                    case R.id.per_center_my_wallet:
//
//                        break;
                    case R.id.rl_center_my_account:
                        startActivity(new Intent(context, AccountManageActivity.class));
                        break;

                    case R.id.rl_center_my_account2:
                        break;
                    case R.id.rl_center_my_address:
                        startActivity(new Intent(getActivity(),AccountAddressManageActivity.class));
                        break;
                    case R.id.rl_center_my_layout:
                        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLogin",false);
                        editor.commit();
                        Toast.makeText(context,"退出成功!",Toast.LENGTH_SHORT).show();
//                        AccountManageActivity.this.finish();
                        break;
                    case R.id.rl_center_my_wallet:
                        startActivity(new Intent(context, WoDEQBActivity.class));
                        break;
                    case R.id.per_center_account_manager:
                        startActivity(new Intent(context,ChangePasswordActivity.class));
//                        startActivity(new Intent(context, AccountManageActivity.class));
                    /*
                    if (savedInstanceState == null){
                        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        AccountFragment accountFragment = new AccountFragment();
                        accountFragment.init(context);
                        fragmentTransaction.replace(R.id.fragment_main, accountFragment);
                        fragmentTransaction.addToBackStack("");
                        fragmentTransaction.commit();
                    }*/
                        break;
                }
            }
            else
            {
                Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }


        }
    }

    private class Click implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            //设置控件点击事件
            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        }
    }
}
