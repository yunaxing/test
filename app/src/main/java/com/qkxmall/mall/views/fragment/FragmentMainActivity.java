package com.qkxmall.mall.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.qkxmall.mall.R;
import com.qkxmall.mall.views.cart.CartFragment;
import com.qkxmall.mall.views.func.coucou.activity.NewCenterFragment;
import com.qkxmall.mall.views.func.fragment.ShequFragment;
import com.qkxmall.mall.views.func.webview.ShequActivity;
import com.qkxmall.mall.views.user.LoginActivity;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.common.Constants;

public class FragmentMainActivity extends AppCompatActivity {

    public static final int CLOUD = 0x00121;
    public static final int HUI = 0x00122;

    //    private ImageView main = null;
    private ImageView cloud = null;
    private ImageView hui = null;
    private ImageView category = null;
    private ImageView cart = null;
    private ImageView center = null;
    private ImageView mainShequ = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.new_activity_fragment_main);
        Context context = FragmentMainActivity.this;
        init();
        Log.e("fragmentactivity", "------------------------------ceshi dibug");
//        main.setOnClickListener(new OnCL(savedInstanceState,getApplicationContext()));
        cloud.setOnClickListener(new OnCL(savedInstanceState, getApplicationContext()));
        hui.setOnClickListener(new OnCL(savedInstanceState, getApplicationContext()));
        category.setOnClickListener(new OnCL(savedInstanceState, getApplicationContext()));
        cart.setOnClickListener(new OnCL(savedInstanceState, getApplicationContext()));
        center.setOnClickListener(new OnCL(savedInstanceState, getApplicationContext()));
        mainShequ.setOnClickListener(new OnCL(savedInstanceState, getApplicationContext()));
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
           mHuiMainFragment = new HuiMainFragment();
            TransHandler transHandler = new TransHandler();
            mHuiMainFragment.init(this);
            fragmentTransaction.add(R.id.fragment_main, mHuiMainFragment);
            fragmentTransaction.commit();
//            main.setSelected(true);
            hui.setSelected(true);
        }
    }

    private void init() {

        // 开启logcat输出，方便debug，发布时请关闭
        XGPushConfig.enableDebug(getApplicationContext(), true);

        // 如果需要知道注册是否成功，请使用registerPush(getApplicationContext(), XGIOperateCallback)带callback版本
        // 如果需要绑定账号，请使用registerPush(getApplicationContext(),account)版本
        // 具体可参考详细的开发指南
        // 传递的参数为ApplicationContext
//		String account="";//设置account.
        XGPushManager.registerPush(getApplicationContext(), new XGIOperateCallback() {

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.e(Constants.LogTag, "+++ register push fail. token:" + data + ", errCode:" + errCode + ",msg:" + msg);
            }

            @Override
            public void onSuccess(Object data, int arg1) {
                Log.e(Constants.LogTag, "+++ register push sucess. token:" + data);
            }
        });

//        main = (ImageView) findViewById(R.id.main);
        cloud = (ImageView) findViewById(R.id.cloud);
        hui = (ImageView) findViewById(R.id.hui);
        category = (ImageView) findViewById(R.id.category);
        mainShequ = (ImageView)findViewById(R.id.shequ);
        cart = (ImageView) findViewById(R.id.cart);
        center = (ImageView) findViewById(R.id.center);
    }


//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//    }


    @Override
    protected void onPause() {
        super.onPause();
        XGPushManager.onActivityStarted(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        XGPushManager.onActivityStarted(this);
    }

    newCloudMainFragment mCloudMainFragment;
    HuiMainFragment mHuiMainFragment;
    AllBuyMenuFragment2 communityFragment;
    CartFragment cartFragment;
    NewCenterFragment centerFragment;
//    ShequFragment shequFragment;

    private class OnCL implements View.OnClickListener {
        Bundle savedInstanceState;
        Context context;

        public OnCL(Bundle savedInstanceState, Context context) {
            this.savedInstanceState = savedInstanceState;
            this.context = context;
        }

        void hideFragment(FragmentTransaction ft) {
            if (mCloudMainFragment != null) {
                ft.hide(mCloudMainFragment);
            }
            if (mHuiMainFragment != null) {
                ft.hide(mHuiMainFragment);
            }
            if (communityFragment != null) {
                ft.hide(communityFragment);
            }
            if (centerFragment != null) {
                ft.hide(centerFragment);
            }
            if (cartFragment != null) {
                ft.hide(cartFragment);
            }
//            if (shequFragment != null) {
//                ft.hide(shequFragment);
//            }

        }

        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            hideFragment(fragmentTransaction);

            switch (v.getId()) {
//                case R.id.main:
//                    if (savedInstanceState == null){
//                        TransHandler transHandler = new TransHandler();
//                        FragmentManager fragmentManager = getSupportFragmentManager();
//                        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                        MainFragment mainFragment = new MainFragment();
//                        mainFragment.init(FragmentMainActivity.this,transHandler);
//                        fragmentTransaction.replace(R.id.fragment_main, mainFragment);
//                        fragmentTransaction.commit();
//                        main.setSelected(true);
//                        cloud.setSelected(false);
//                        hui.setSelected(false);
//                        category.setSelected(false);
//                        cart.setSelected(false);
//                        center.setSelected(false);
//                    }
//                    break;
                case R.id.shequ:
//                    if (shequFragment == null) {
//                        shequFragment = new ShequFragment();
////                        shequFragment.init(FragmentMainActivity.this);
//                        fragmentTransaction.add(R.id.fragment_main, shequFragment);
//
//                    } else {
//                        fragmentTransaction.show(shequFragment);
//                    }
//
//
////                        main.setSelected(false);
//                    cloud.setSelected(false);
//                    hui.setSelected(false);
//                    category.setSelected(false);
//                    mainShequ.setSelected(true);
//                    cart.setSelected(false);
//                    center.setSelected(false);
//                    fragmentTransaction.commit();
                    startActivity(new Intent(getApplication(), ShequActivity.class));
                    break;

                case R.id.cloud:
                    if (mCloudMainFragment == null) {
                        mCloudMainFragment = new newCloudMainFragment();
                        mCloudMainFragment.init(FragmentMainActivity.this);
                        fragmentTransaction.add(R.id.fragment_main, mCloudMainFragment);

                    } else {
                        fragmentTransaction.show(mCloudMainFragment);
                    }


//                        main.setSelected(false);
                    cloud.setSelected(true);
                    hui.setSelected(false);
                    category.setSelected(false);
                    mainShequ.setSelected(false);
                    cart.setSelected(false);
                    center.setSelected(false);
                    fragmentTransaction.commit();
                    break;
                case R.id.hui:
                    if (mHuiMainFragment == null) {
                        mHuiMainFragment = new HuiMainFragment();
                        mHuiMainFragment.init(FragmentMainActivity.this);
                        fragmentTransaction.add(R.id.fragment_main, mHuiMainFragment);
                    } else {
                        fragmentTransaction.show(mHuiMainFragment);
                    }
//                        main.setSelected(false);
                    cloud.setSelected(false);
                    hui.setSelected(true);
                    category.setSelected(false);
                    mainShequ.setSelected(false);
                    cart.setSelected(false);
                    center.setSelected(false);
                    fragmentTransaction.commit();
                    break;
                case R.id.category:
                    if (true) {
                        if (communityFragment == null) {
                            communityFragment = new AllBuyMenuFragment2();
                            fragmentTransaction.add(R.id.fragment_main, communityFragment);
                        } else {
                            fragmentTransaction.show(communityFragment);
                        }
//                        communityFragment.init(FragmentMainActivity.this);


//                        main.setSelected(false);
                        cloud.setSelected(false);
                        hui.setSelected(false);
                        category.setSelected(true);
                        cart.setSelected(false);
                        mainShequ.setSelected(false);
                        center.setSelected(false);
                        fragmentTransaction.commit();
                    } else {
                        Toast.makeText(context, " '社区' 即将上线", Toast.LENGTH_SHORT).show();
                    }

                    break;
                case R.id.cart:
                   selectCart(fragmentTransaction);
                    break;
                case R.id.center:
//                    if (savedInstanceState == null) {
                        if (centerFragment == null) {
                            centerFragment = new NewCenterFragment();
                            centerFragment.init(FragmentMainActivity.this, savedInstanceState, fragmentManager, cart, center);
                            fragmentTransaction.add(R.id.fragment_main, centerFragment);
                        } else {
                            fragmentTransaction.show(centerFragment);
                        }
//                        main.setSelected(false);
                        cloud.setSelected(false);
                        hui.setSelected(false);
                        category.setSelected(false);
                        mainShequ.setSelected(false);
                        cart.setSelected(false);
                        center.setSelected(true);
//                    }
                    fragmentTransaction.commit();
                    break;
            }

        }
    }

    public void selectCart(FragmentTransaction fragmentTransaction){
        if(fragmentTransaction==null)
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
//        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        SharedPreferences sharedPreferences = getSharedPreferences("USER_INFO", MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        if (isLogin) {
            if (cartFragment == null) {
                cartFragment = new CartFragment();
                cartFragment.init(FragmentMainActivity.this);
                fragmentTransaction.add(R.id.fragment_main, cartFragment);
            } else {
                fragmentTransaction.show(cartFragment);
            }


//                        main.setSelected(false);
            cloud.setSelected(false);
            hui.setSelected(false);
            category.setSelected(false);
            mainShequ.setSelected(false);
            cart.setSelected(true);
            center.setSelected(false);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        fragmentTransaction.commit();
    }

    private class TransHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HUI: {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    HuiMainFragment huiMainFragment = new HuiMainFragment();
                    huiMainFragment.init(FragmentMainActivity.this);
                    fragmentTransaction.replace(R.id.fragment_main, huiMainFragment);
                    fragmentTransaction.commit();
//                    main.setSelected(false);
                    cloud.setSelected(false);
                    hui.setSelected(true);
                    category.setSelected(false);
                    mainShequ.setSelected(false);
                    cart.setSelected(false);
                    center.setSelected(false);
                }
                break;
                case CLOUD: {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    CloudMainFragment cloudMainFragment = new CloudMainFragment();
                    cloudMainFragment.init(FragmentMainActivity.this);
                    fragmentTransaction.replace(R.id.fragment_main, cloudMainFragment);
                    fragmentTransaction.commit();
//                    main.setSelected(false);
                    cloud.setSelected(true);
                    hui.setSelected(false);
                    category.setSelected(false);
                    mainShequ.setSelected(false);
                    cart.setSelected(false);
                    center.setSelected(false);
                }
                break;
            }
        }
    }

    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
//				main_fragment.saveIds();
//                overridePendingTransition(0,R.anim.)
                moveTaskToBack(true);
                ;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityCompat.finishAffinity(this);
                }
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
                finish();


            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
