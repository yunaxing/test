package com.qkxmall.mall.views.cloud;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.adapter.AdsViewPagerAdapter;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.AllProductDetailPageCloudActivity;
import com.qkxmall.mall.views.ConnectGetServiceActivity;
import com.qkxmall.mall.views.ProductDetailPayActivity;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloudBuyProductDetailPageActivity extends Activity {
    private ImageView backup = null;
    private ImageView tr = null;
    private ImageView push = null;
    private ImageView add = null;
    private Button buyNow = null;
    private TextView cloudCommunity = null;
    private TextView price = null;
    private TextView number = null;
    private LinearLayout pictureAndTextDetail = null;
    private LinearLayout evaluateDetail = null;
    private LinearLayout history = null;
    private LinearLayout getService = null;
    private ImageView share = null;
    private Button addToCart = null;
    private ViewPager image = null;

    Context context;
    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_buy_product_detail_page);
        init();
        context = CloudBuyProductDetailPageActivity.this;
        // TODO: 10/23/2015
        /*
        * Setup viewpager adapter
        *
        *
        * */
        String[] url = new String[]{
                "http://www.qkxmall.com/uploadfile/image/20150926/11111111.jpg",
                "http://www.qkxmall.com/uploadfile/image/20150926/22222222.jpg",
                "http://www.qkxmall.com/uploadfile/image/20150926/33333333.jpg",
        };
        List<HashMap<String ,Object>> list = new ArrayList<>();
        for (int i = 0 ; i<url.length ;i++){
            HashMap<String ,Object> map = new HashMap<>();
            map.put("url", url[i]);
            list.add(map);
        }
        List<View> views = new ArrayList<>();
        for (int i = 0 ; i<url.length ;i++){
            View view = LayoutInflater.from(context).inflate(R.layout.image_layout, null);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.image_title);
            WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            simpleDraweeView.setMaxWidth(windowManager.getDefaultDisplay().getWidth());
            simpleDraweeView.setMinimumWidth(windowManager.getDefaultDisplay().getWidth());
            simpleDraweeView.setMaxHeight(windowManager.getDefaultDisplay().getWidth());
            simpleDraweeView.setMinimumHeight(windowManager.getDefaultDisplay().getWidth());
            Uri uri = Uri.parse(String.valueOf(list.get(i).get("url")));
            simpleDraweeView.setImageURI(uri);
            views.add(view);
        }
        AdsViewPagerAdapter ads = new AdsViewPagerAdapter(views);
        image.setAdapter(ads);



        backup.setOnClickListener(new OnClickListeners());
        tr.setOnClickListener(new OnClickListeners());
        pictureAndTextDetail.setOnClickListener(new OnClickListeners());
        evaluateDetail.setOnClickListener(new OnClickListeners());
        history.setOnClickListener(new OnClickListeners());
        buyNow.setOnClickListener(new OnClickListeners());
        cloudCommunity.setOnClickListener(new OnClickListeners());
        push.setOnClickListener(new OnClickListeners());
        add.setOnClickListener(new OnClickListeners());
        getService.setOnClickListener(new OnClickListeners());
        share.setOnClickListener(new OnClickListeners());
        addToCart.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.backup);
        tr = (ImageView) findViewById(R.id.tr);
        push = (ImageView) findViewById(R.id.push);
        add = (ImageView) findViewById(R.id.add);
        pictureAndTextDetail = (LinearLayout) findViewById(R.id.detail);
        evaluateDetail = (LinearLayout) findViewById(R.id.evaluate);
        history = (LinearLayout) findViewById(R.id.history);
        getService = (LinearLayout) findViewById(R.id.get_service);
        buyNow = (Button) findViewById(R.id.buy);
        cloudCommunity = (TextView) findViewById(R.id.community);
        price = (TextView) findViewById(R.id.price);
        number = (TextView) findViewById(R.id.number);
        share = (ImageView) findViewById(R.id.share);
        addToCart = (Button) findViewById(R.id.add_to_cart);
        image = (ViewPager) findViewById(R.id.image_title);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.backup:
                    finish();
                    break;
                case R.id.share:

                    showShare();
                    break;
                case R.id.detail:
                    Intent intentWeb = new Intent(CloudBuyProductDetailPageActivity.this,AllProductDetailPageCloudActivity.class);
                    intentWeb.putExtra("pageID",0);
                    startActivity(intentWeb);
                    break;
                case R.id.evaluate:
                    Intent intentEvaluate = new Intent(CloudBuyProductDetailPageActivity.this,AllProductDetailPageCloudActivity.class);
                    intentEvaluate.putExtra("pageID",2);
                    startActivity(intentEvaluate);
                    break;
                case R.id.history:
                    Intent intentHistory = new Intent(CloudBuyProductDetailPageActivity.this,CurrentProductHistoryActivity.class);
                    intentHistory.putExtra("currentPeriods","");
                    startActivity(intentHistory);
                    break;
                case R.id.buy:
                    Intent intentPay = new Intent(CloudBuyProductDetailPageActivity.this,ProductDetailPayActivity.class);
                    intentPay.putExtra("c_price", price.getText().toString().trim());
                    startActivity(intentPay);
                    break;
                case R.id.community:
                    Intent intent = new Intent(CloudBuyProductDetailPageActivity.this,CloudCommunityPageActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.push:
                    if (Integer.parseInt(number.getText().toString().trim())>1){
                        number.setText((Integer.parseInt(number.getText().toString().trim())-1)+"");
                    }
                    break;
                case R.id.add:
                    number.setText((Integer.parseInt(number.getText().toString().trim())+1)+"");
                    break;
                case R.id.get_service:
                    startActivity(new Intent(CloudBuyProductDetailPageActivity.this,ConnectGetServiceActivity.class));
                    break;
                case R.id.add_to_cart:
                    // TODO: 10/23/2015
                    Toast.makeText(context, "Add to cart! ", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private void showShare() {


        LOD lod = new LOD(context);
        String shareAdd = API.USER_SHARE+"&uid="+lod.get("USER_INFO","UID","'");
        Handler handler = new Handler();
        BackgroundTask backgroundTask = new BackgroundTask(context,shareAdd,handler);
        backgroundTask.doInBackground();


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
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(CloudBuyProductDetailPageActivity.this, "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qqSsoHandler.addToSocialSDK();
        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(CloudBuyProductDetailPageActivity.this, "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qZoneSsoHandler.addToSocialSDK();

        //设置新浪SSO handler
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
        //设置腾讯微博SSO handler
        mController.getConfig().setSsoHandler(new TencentWBSsoHandler());

        // 设置分享内容
        mController.setShareContent("穷开心购物商城 专为开心生活量身定制，全场包邮 价格更实惠 用心来做 只为你那开心一刻");
        // 设置分享图片, 参数2为图片的url地址
        mController.setShareMedia(new UMImage(CloudBuyProductDetailPageActivity.this, "http://www.qkxmall.com/api/android/logo.png"));
        mController.setAppWebSite(SHARE_MEDIA.RENREN, "http://www.qkxmall.com/xiazai/");
        mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);
        mController.openShare(CloudBuyProductDetailPageActivity.this, false);
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


}
