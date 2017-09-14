package com.qkxmall.mall.views.fragment;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.qkxmall.mall.views.cloud.order.AllCloudOrderActivity;
import com.qkxmall.mall.views.func.coucou.activity.WoDEQBActivity;
import com.qkxmall.mall.views.hui.order.AllHuiOrderActivity;
import com.qkxmall.mall.views.user.AccountManageActivity;
import com.qkxmall.mall.views.user.LoginActivity;
import com.qkxmall.mall.views.user.PerCenterMyWalletActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sunshine on 10/9/2015.
 */
public class CenterFragment extends Fragment {
    private TextView perCenterUserName = null;
    private SimpleDraweeView perCenterUserAvatar = null;
    private TextView perCenterMyWallet = null;
    private TextView perCenterMyTrolley = null;
    private TextView perCenterAccountManager = null;
    private RelativeLayout perCenterAllCloudOrder = null;
    private LinearLayout perCenterCloudWaitToAnnounce = null;
    private LinearLayout perCenterCloudFourLeavedClover = null;
    private LinearLayout perCenterCloudWaitToReceiving = null;
    private LinearLayout perCenterCloudEvaluate = null;
    private LinearLayout perCenterCloudAnnounce = null;
    private RelativeLayout perCenterHuiAllOrder = null;
    private LinearLayout perCenterHuiWaitToPayment = null;
    private LinearLayout perCenterHuiWaitToDeliver = null;
    private LinearLayout perCenterHuiWaitToReceiving = null;
    private LinearLayout perCenterHuiEvaluate = null;
    private LinearLayout perCenterHuiAfterMarket = null;

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
        View rootView = inflater.inflate(R.layout.fragment_center,container,false);
        init(rootView);
        //初始化用户数据
        /*
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("数据加载中...");
        progressDialog.show();*/
//        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_INFO",Context.MODE_PRIVATE);
        loadUserInfo();

        //设置控件点击事件
        perCenterAllCloudOrder.setOnClickListener(new OnClickListeners());
        perCenterMyWallet.setOnClickListener(new OnClickListeners());
        perCenterAccountManager.setOnClickListener(new OnClickListeners());
        perCenterHuiAllOrder.setOnClickListener(new OnClickListeners());
        perCenterCloudWaitToAnnounce.setOnClickListener(new OnClickListeners());
        perCenterCloudFourLeavedClover.setOnClickListener(new OnClickListeners());
        perCenterCloudWaitToReceiving.setOnClickListener(new OnClickListeners());
        perCenterCloudEvaluate.setOnClickListener(new OnClickListeners());
        perCenterCloudAnnounce.setOnClickListener(new OnClickListeners());
        perCenterHuiEvaluate.setOnClickListener(new OnClickListeners());
        perCenterHuiAfterMarket.setOnClickListener(new OnClickListeners());
        perCenterHuiWaitToDeliver.setOnClickListener(new OnClickListeners());
        perCenterHuiWaitToPayment.setOnClickListener(new OnClickListeners());
        perCenterHuiWaitToReceiving.setOnClickListener(new OnClickListeners());
        perCenterMyTrolley.setOnClickListener(new OnClickListeners());
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
        perCenterMyTrolley = (TextView) rootView.findViewById(R.id.per_center_my_trolley);
        perCenterMyWallet = (TextView) rootView.findViewById(R.id.per_center_my_wallet);
        perCenterAccountManager = (TextView) rootView.findViewById(R.id.per_center_account_manager);
        perCenterAllCloudOrder = (RelativeLayout) rootView.findViewById(R.id.per_center_all_cloud_order);
        perCenterCloudAnnounce = (LinearLayout) rootView.findViewById(R.id.per_center_cloud_announce);
        perCenterCloudWaitToAnnounce = (LinearLayout) rootView.findViewById(R.id.per_center_cloud_wait_to_announce);
        perCenterCloudFourLeavedClover = (LinearLayout) rootView.findViewById(R.id.per_center_cloud_four_leaved_clover);
        perCenterCloudWaitToReceiving = (LinearLayout) rootView.findViewById(R.id.per_center_cloud_wait_to_receiving);
        perCenterCloudEvaluate = (LinearLayout) rootView.findViewById(R.id.per_center_cloud_evaluate);
        perCenterHuiAllOrder = (RelativeLayout) rootView.findViewById(R.id.per_center_hui_all_order);
        perCenterHuiWaitToPayment = (LinearLayout) rootView.findViewById(R.id.per_center_hui_wait_to_payment);
        perCenterHuiWaitToDeliver = (LinearLayout) rootView.findViewById(R.id.per_center_hui_wait_to_deliver);
        perCenterHuiEvaluate = (LinearLayout) rootView.findViewById(R.id.per_center_hui_evaluate);
        perCenterHuiAfterMarket = (LinearLayout) rootView.findViewById(R.id.per_center_hui_aftermarket);
        perCenterHuiWaitToReceiving = (LinearLayout) rootView.findViewById(R.id.per_center_hui_wit_to_receiving);
    }

    //内部点击事件监听类
    private class OnClickListeners implements View.OnClickListener {
        Intent intentAllCloudOrder = new Intent(context,AllCloudOrderActivity.class);
        Intent intentAllHuiOrder = new Intent(context,AllHuiOrderActivity.class);
        @Override
        public void onClick(View v) {

            SharedPreferences preferences = context.getSharedPreferences("USER_INFO",Context.MODE_PRIVATE);
            boolean isLogin = preferences.getBoolean("isLogin",false);
            if (isLogin){
                switch (v.getId()){
                    case R.id.per_center_all_cloud_order:
                        startActivity(intentAllCloudOrder);
                        break;
                    case R.id.per_center_cloud_announce:
//                        Intent intent = new Intent(context, AllCloudClassItem.class);
//                      intent.putExtra("ClassTitle","即将揭晓");
//                        intentAllCloudOrder.putExtra("pageID", 0);
//                        startActivity(intent);
                        intentAllCloudOrder.putExtra("pageID", 1);
                        startActivity(intentAllCloudOrder);
                        break;
                    case R.id.per_center_cloud_wait_to_announce:
                        intentAllCloudOrder.putExtra("pageID", 1);
                        startActivity(intentAllCloudOrder);
                        break;
                    case R.id.per_center_cloud_four_leaved_clover:
                        intentAllCloudOrder.putExtra("pageID",2);
                        startActivity(intentAllCloudOrder);
                        break;
                    case R.id.per_center_cloud_wait_to_receiving:
                        intentAllCloudOrder.putExtra("pageID",3);
                        startActivity(intentAllCloudOrder);
                        break;
                    case R.id.per_center_cloud_evaluate:
                        intentAllCloudOrder.putExtra("pageID",4);
                        startActivity(intentAllCloudOrder);
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
                    case R.id.per_center_hui_all_order:
                        startActivity(intentAllHuiOrder);
                        break;
                    case R.id.per_center_hui_wait_to_payment:
                        intentAllHuiOrder.putExtra("pageID",1);
                        startActivity(intentAllHuiOrder);
                        break;
                    case R.id.per_center_hui_wait_to_deliver:
                        intentAllHuiOrder.putExtra("pageID",2);
                        startActivity(intentAllHuiOrder);
                        break;
                    case R.id.per_center_hui_wit_to_receiving:
                        intentAllHuiOrder.putExtra("pageID",3);
                        startActivity(intentAllHuiOrder);
                        break;
                    case R.id.per_center_hui_evaluate:
                        startActivity(intentAllHuiOrder);
                        intentAllHuiOrder.putExtra("pageID", 4);
                        break;
                    case R.id.per_center_hui_aftermarket:
                        intentAllHuiOrder.putExtra("pageID", 0);
                        startActivity(intentAllHuiOrder);
                        break;
                    //钱包
                    case R.id.per_center_my_wallet:
//                        startActivity(new Intent(context, PerCenterMyWalletActivity.class));
                        startActivity(new Intent(context, WoDEQBActivity.class));
                        break;
                    case R.id.per_center_account_manager:
                        startActivity(new Intent(context, AccountManageActivity.class));
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
