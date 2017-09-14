package com.qkxmall.mall.views.hui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.adapter.FragmentPagerAdapter;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.nets.JD;
import com.qkxmall.mall.views.AllProductDetailPageHuiActivity;
import com.qkxmall.mall.views.ConnectGetServiceActivity;
import com.qkxmall.mall.views.fragment.detail.DetailImageFragment;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HuiBuyProductDetailPageActivityOld extends AppCompatActivity {
    private ImageView navigation = null;
    private ImageView cart = null;
    private ViewPager images = null;
    private TextView name = null;
    private TextView price = null;
    private TextView sale = null;
    private TextView bean = null;
    private TextView send = null;
    private LinearLayout detail = null;
    private LinearLayout buy = null;
    private TextView evaluation = null;
    private LinearLayout evaluateLine = null;
    private TextView guide = null;
    private TextView community = null;
    private LinearLayout customService = null;
    private ImageView share = null;

    Context context;
    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_buy_product_detail_page);
        init();
        context = HuiBuyProductDetailPageActivityOld.this;

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("获取商品详情中...");
        progressDialog.show();
        LoadData loadDate = new LoadData(progressDialog);
        String address = API.HUI_PRODUCTS_DETAIL+"&gid="+getIntent().getStringExtra("id");
        BackgroundTask backgroundTask = new BackgroundTask(context ,address,loadDate);
        backgroundTask.doInBackground();

        Log.e("--ErrorAddress--",address);

        navigation.setOnClickListener(new OnClickListeners());
        cart.setOnClickListener(new OnClickListeners());
        detail.setOnClickListener(new OnClickListeners());
        evaluateLine.setOnClickListener(new OnClickListeners());
        community.setOnClickListener(new OnClickListeners());
        buy.setOnClickListener(new OnClickListeners());
        customService.setOnClickListener(new OnClickListeners());
        share.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        navigation = (ImageView) findViewById(R.id.navigation);
        cart = (ImageView) findViewById(R.id.cart);
        images = (ViewPager) findViewById(R.id.images);
        name = (TextView) findViewById(R.id.name);
        price = (TextView) findViewById(R.id.price);
        sale = (TextView) findViewById(R.id.sale);
        bean = (TextView) findViewById(R.id.bean);
        send = (TextView) findViewById(R.id.send);
        detail = (LinearLayout) findViewById(R.id.detail);
        buy = (LinearLayout) findViewById(R.id.buy);
        evaluation = (TextView) findViewById(R.id.evaluate);
        evaluateLine = (LinearLayout) findViewById(R.id.evaluateLine);
        guide = (TextView) findViewById(R.id.guide);
        community = (TextView) findViewById(R.id.community);
        customService = (LinearLayout) findViewById(R.id.customService);
        share = (ImageView) findViewById(R.id.share);
    }

    private class OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(HuiBuyProductDetailPageActivityOld.this,AllProductDetailPageHuiActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.navigation:
                    finish();
                    break;
                case R.id.share:
                    showShare();
                    break;
                case R.id.community:
                    startActivity(new Intent(HuiBuyProductDetailPageActivityOld.this,HuiCommunityPageActivity.class));
                    break;
                case R.id.detail:
                    intent.putExtra("pageID",0);
                    startActivity(intent);
                    break;
                case R.id.buy:
                    Intent intentChoice = new Intent(HuiBuyProductDetailPageActivityOld.this,HuiBuyChoiceSthPageActivity.class);
                    startActivity(intentChoice);
                    break;
                case R.id.evaluateLine:
                    intent.putExtra("pageID",2);
                    startActivity(intent);
                    break;
                case R.id.get_service:
                    startActivity(new Intent(HuiBuyProductDetailPageActivityOld.this,ConnectGetServiceActivity.class));
                    break;
            }
        }
    }


    private void showShare() {
//        String appID = "wx131c0fe7c97bbe02";
//        String appSecret = "0be9613033a3ca9edef85eb0b9b7f6f1";
        String appID = "wxc132cfd42b28010a";
        String appSecret = "33f0d3845904d17f056b77c0af8e5029";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(context,appID,appSecret);
        wxHandler.addToSocialSDK();
        // 添加微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(context,appID,appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(HuiBuyProductDetailPageActivityOld.this, "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qqSsoHandler.addToSocialSDK();
        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(HuiBuyProductDetailPageActivityOld.this, "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qZoneSsoHandler.addToSocialSDK();

        //设置新浪SSO handler
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
        //设置腾讯微博SSO handler
        mController.getConfig().setSsoHandler(new TencentWBSsoHandler());

        // 设置分享内容
        mController.setShareContent("穷开心购物商城 专为开心生活量身定制，全场包邮 价格更实惠 用心来做 只为你那开心一刻");
        // 设置分享图片, 参数2为图片的url地址
        mController.setShareMedia(new UMImage(HuiBuyProductDetailPageActivityOld.this, "http://www.umeng.com/images/pic/banner_module_social.png"));
        mController.setAppWebSite(SHARE_MEDIA.RENREN, "http://www.umeng.com/social");
        mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);
        mController.openShare(HuiBuyProductDetailPageActivityOld.this, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**使用SSO授权必须添加如下代码 */
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode) ;
        if(ssoHandler != null){
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    private class LoadData extends Handler{
        ProgressDialog progressDialog;

        public LoadData(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        JD jd = new JD(context);
                        try {
                            HashMap<String ,Object> hashMap = jd.decode(bundle.getString("result"),API.HUI_PRODUCTS_DETAIL);
                            List<HashMap<String ,Object>> JSON = (List<HashMap<String, Object>>) hashMap.get("JSON");
                            for (int l = 0 ; l< JSON.size() ;l++){
                                HashMap<String ,Object> data = JSON.get(l);
                                name.setText((CharSequence) data.get("name"));
                                price.setText((CharSequence) data.get("shop_price"));
                                bean.setText((CharSequence) data.get("kaixindou"));
                                String[] images = ((String) data.get("list_img")).split(",");
                                List<Fragment> fragments = new ArrayList<>();
                                for (int img = 0 ; img <images.length ;img++){
                                    fragments.add(DetailImageFragment.newInstance(context,images[img]));
                                }
                                FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(),fragments);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                    break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(context, "数据请求失败", Toast.LENGTH_SHORT).show();
                }
                    break;
            }
        }
    }
}
