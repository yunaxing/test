package com.qkxmall.mall.views.hui;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.MyScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.adapter.grid.BaseHuiClassItemAdapter;
import com.qkxmall.mall.define.views.GridViewWithScrollView;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.model.type.Type;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.nets.JD;
import com.qkxmall.mall.views.hui.container.HuiActivity;
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

public class HuiClassItemsActivity extends AppCompatActivity implements MyScrollView.OnScrollChangeListener {
    String keyword = "";
    String brand_id = "";
    String isJP = "";

    private PullToRefreshScrollView pullScroll = null;
    private ScrollView scrollView;

    private SimpleDraweeView image = null;
    private ImageView backup = null;
    private ImageView share = null;
    private TextView title = null;
    private ImageView sort0 = null;
    private TextView sort1 = null;
    private TextView sort2 = null;
//    private TextView sort3 = null;
    private TextView sort4 = null;
    private GridViewWithScrollView grid = null;
    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
    Context context;
    BaseHuiClassItemAdapter productsGridAdapter;
    List<HashMap<String, Object>> productsList = new ArrayList<>();

    //status_ext	status_ext=1	商品状态：推荐（1），热门（2），新品（3）
    private String category_id = "";
    private int defaultRows = 16;
    private String oldUrl = "";
    private static final int defaultPage = 1;
    private int currentPage = 1;
    private int oldPage = 1;
    private String currentAddress = "";

    private String currentSortType = "hits";
    private String currentOrderType = "desc";
    Type type = new Type();
    private Button btnUp;
    /*
    * id	主键id，最新商品
    * hits	浏览量
    * kaixindou	可使用开心豆
    * sales_number	销量
    * order	order=[desc,asc]	排序方式
    * 人气 价格 最新 ，开心豆 评论
    * */


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_hui_class_items);
        context = HuiClassItemsActivity.this;
        init();
        if (getIntent().getStringExtra("keyword") != null) {
            keyword = getIntent().getStringExtra("keyword");
        }
        if (getIntent().getStringExtra("brand_id") != null) {
            brand_id = getIntent().getStringExtra("brand_id");
        }
        if (getIntent().getStringExtra("category_id") != null)
            category_id = getIntent().getStringExtra("category_id");
        if (getIntent().getStringExtra("isJP") != null)
            isJP = getIntent().getStringExtra("isJP");

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("获取商品列表中...");
        progressDialog.show();
        String address = API.HUI_PRODUCTS_LIST +
                "&category_id=" + category_id +
                "&rows=" + defaultRows +
                "&page=" + currentPage +
                "&sort=" + currentSortType +
                "&order=" + currentOrderType;
        if (!keyword.equals("")) {
            address = API.HUI_PRODUCTS_LIST +
                    "&rows=" + defaultRows +
                    "&page=" + currentPage +
                    "&sort=" + currentSortType +
                    "&order=" + currentOrderType;
            address += "&keyword=" + keyword;
            title.setText("搜索");
        }
        if (!brand_id.equals("")) {
            address = API.HUI_PRODUCTS_LIST +
                    "&brand_id=" + brand_id +
                    "&rows=" + defaultRows +
                    "&page=" + currentPage +
                    "&sort=" + currentSortType +
                    "&order=" + currentOrderType;
        }
        if (!isJP.equals("")) {
            address = "http://www.qkxmall.com/?m=api&c=goods&a=lists&status_ext=1" +
                    "&page=" + currentPage +
                    "&rows=" + defaultRows +
                    "&sort=" + currentSortType +
                    "&order=" + currentOrderType;
        }
        currentAddress = address;
        LoadData loadData = new LoadData(progressDialog);
        Log.e("loadData", address);
        BackgroundTask backgroundTask = new BackgroundTask(context, address, loadData);
        backgroundTask.doInBackground();

        pullScroll.setPullToRefreshOverScrollEnabled(true);
        pullScroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                oldPage = currentPage;
                currentPage = 1;
                currentAddress = currentAddress.replace("page=" + oldPage, "page=" + currentPage);
                Refresh refresh = new Refresh();
                BackgroundTask task = new BackgroundTask(context, currentAddress, refresh);
                task.doInBackground();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                oldPage = currentPage;
                currentPage++;
                currentAddress = currentAddress.replace("page=" + oldPage, "page=" + currentPage);
                LoadMore loadMore = new LoadMore();
                BackgroundTask task = new BackgroundTask(context, currentAddress, loadMore);
                task.doInBackground();
            }
        });

        scrollView = pullScroll.getRefreshableView();
//        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View view, int l, int t, int oldl, int oldt3) {
//                Log.v("yyyxxx",l+"***********"+t+"*************"+oldl+"*********"+oldt3);
//                if(t>1512){
//                    btnUp.setVisibility(View.VISIBLE);
//                }else {
//                    btnUp.setVisibility(View.GONE);
//                }
//            }
//        });

        title.setText(getIntent().getStringExtra("categoryTitle"));
//        sort0.setTextColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//        sort1.setTextColor(getResources().getColor(R.color.white));
//        sort2.setTextColor(getResources().getColor(R.color.white));
//        sort3.setTextColor(getResources().getColor(R.color.white));
//        sort4.setTextColor(getResources().getColor(R.color.white));
//        sort0.setBackgroundResource(R.color.white);
//        sort1.setBackgroundResource(R.color.cloud_buy_text_color_red);
//        sort2.setBackgroundResource(R.color.cloud_buy_text_color_red);
//        sort3.setBackgroundResource(R.color.cloud_buy_text_color_red);
//        sort4.setBackgroundResource(R.color.cloud_buy_text_color_red);

        backup.setOnClickListener(new ONClickListeners());
        sort0.setOnClickListener(new ONClickListeners());
        sort1.setOnClickListener(new ONClickListeners());
        sort2.setOnClickListener(new ONClickListeners());
//        sort3.setOnClickListener(new ONClickListeners());
        sort4.setOnClickListener(new ONClickListeners());
        share.setOnClickListener(new ONClickListeners());

        grid.setOnItemClickListener(new OnItemClickListeners());
    }


    private void init() {
        backup = (ImageView) findViewById(R.id.hui_class_hui_backup);
        share = (ImageView) findViewById(R.id.hui_class_hui_share);
        title = (TextView) findViewById(R.id.hui_class_hui_title_);
        image = (SimpleDraweeView) findViewById(R.id.image_title);
        sort0 = (ImageView) findViewById(R.id.hui_sort_0);
        sort1 = (TextView) findViewById(R.id.hui_sort_1);
        sort2 = (TextView) findViewById(R.id.hui_sort_2);
        //sort3 = (TextView) findViewById(R.id.hui_sort_3);
        sort4 = (TextView) findViewById(R.id.hui_sort_4);
        grid = (GridViewWithScrollView) findViewById(R.id.hui_class_grid);
        pullScroll = (PullToRefreshScrollView) findViewById(R.id.sc);
        pullScroll.setScrollListener(this);
        btnUp = (Button)findViewById(R.id.btn_up);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("scroll","*******************");
//                pullScroll.scrollTo(0,0);
                scrollView.scrollTo(0,0);
            }
        });

    }

    /**
     * Sort
     */
    private class ONClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.hui_class_hui_share:
                    showShare();
                    break;
                case R.id.hui_class_hui_backup:
                    finish();
                    break;
                //人气
                case R.id.hui_sort_0: {
                    currentPage = 1;
                    currentSortType = "hits";
                    String address = API.HUI_PRODUCTS_LIST +
                            "&category_id=" + category_id +
                            "&rows=" + defaultRows +
                            "&page=" + currentPage +
                            "&sort=" + currentSortType +
                            "&order=" + currentOrderType;
                    if (!keyword.equals("")) {
                        address = API.HUI_PRODUCTS_LIST +
                                "&rows=" + defaultRows +
                                "&page=" + currentPage +
                                "&sort=" + currentSortType +
                                "&order=" + currentOrderType;
                        address += "&keyword=" + keyword;
                        title.setText("搜索");
                    }
                    if (!brand_id.equals("")) {
                        address = API.HUI_PRODUCTS_LIST +
                                "&brand_id=" + brand_id +
                                "&rows=" + defaultRows +
                                "&page=" + currentPage +
                                "&sort=" + currentSortType +
                                "&order=" + currentOrderType;
                    }
                    if (!isJP.equals("")) {
                        address = "http://www.qkxmall.com/?m=api&c=goods&a=lists&status_ext=1" +
                                "&page=" + currentPage +
                                "&rows=" + defaultRows +
                                "&sort=" + currentSortType +
                                "&order=" + currentOrderType;
                    }
                    currentAddress = address;
                    Log.e("CurrentAddress", currentAddress);
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("努力加载中...");
                    progressDialog.show();
                    Sort sort = new Sort(progressDialog);
                    BackgroundTask backgroundTask = new BackgroundTask(context, address, sort);
                    backgroundTask.doInBackground();
//                    sort0.setTextColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    sort1.setTextColor(getResources().getColor(R.color.white));
//                    sort2.setTextColor(getResources().getColor(R.color.white));
//                    sort3.setTextColor(getResources().getColor(R.color.white));
//                    sort4.setTextColor(getResources().getColor(R.color.white));
                    sort0.setBackgroundResource(R.drawable.huisort0selected);
                    sort1.setBackgroundResource(R.drawable.huisort1);
                    sort2.setBackgroundResource(R.drawable.huisort2);
//                    sort3.setBackgroundResource(R.drawable.huisort3);
                    sort4.setBackgroundResource(R.drawable.huisort3);
                }
                break;
                //价格
                case R.id.hui_sort_1: {
                    currentPage = 1;
                    currentOrderType = "asc";
                    if (currentAddress.matches("&order=desc")) {
                        String address = API.HUI_PRODUCTS_LIST +
                                "&category_id=" + category_id +
                                "&rows=" + defaultRows +
                                "&page=" + currentPage +
                                "&sort=min_shop_price" +
                                "&order=asc";
                        if (!brand_id.equals("")) {
                            address = API.HUI_PRODUCTS_LIST +
                                    "&brand_id=" + brand_id +
                                    "&rows=" + defaultRows +
                                    "&page=" + currentPage +
                                    "&sort=min_shop_price" +
                                    "&order=asc";
                        }
                        if (!isJP.equals("")) {
                            address = "http://www.qkxmall.com/?m=api&c=goods&a=lists&status_ext=1" +
                                    "&page=" + currentPage +
                                    "&rows=" + defaultRows +
                                    "&sort=min_shop_price" +
                                    "&order=" + "asc";
                        }
                        currentAddress = address;
                        currentOrderType = "asc";
                    } else {
                        String address = API.HUI_PRODUCTS_LIST +
                                "&category_id=" + category_id +
                                "&rows=" + defaultRows +
                                "&page=" + currentPage +
                                "&sort=min_shop_price" +
                                "&order=asc";
                        if (!brand_id.equals("")) {
                            address = API.HUI_PRODUCTS_LIST +
                                    "&brand_id=" + brand_id +
                                    "&rows=" + defaultRows +
                                    "&page=" + currentPage +
                                    "&sort=min_shop_price" +
                                    "&order=asc";
                        }
                        if (!isJP.equals("")) {
                            address = "http://www.qkxmall.com/?m=api&c=goods&a=lists&status_ext=1" +
                                    "&page=" + currentPage +
                                    "&rows=" + defaultRows +
                                    "&sort=min_shop_price" +
                                    "&order=" + "asc";
                        }
                        currentAddress = address;
                        currentOrderType = "asc";
                    }

                    currentSortType = "min_shop_price";
                    Log.e("CurrentAddress", currentAddress);
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("努力加载中...");
                    progressDialog.show();
                    Sort sort = new Sort(progressDialog);
                    BackgroundTask backgroundTask = new BackgroundTask(context, currentAddress,
                            sort);
                    backgroundTask.doInBackground();
                    sort0.setBackgroundResource(R.drawable.huisort0);
                    sort1.setBackgroundResource(R.drawable.huisort1selected);
                    sort2.setBackgroundResource(R.drawable.huisort2);
//                    sort3.setBackgroundResource(R.drawable.huisort3);
                    sort4.setBackgroundResource(R.drawable.huisort3);
                }
                break;
                //最新
                case R.id.hui_sort_2: {
                    currentPage = 1;
                    String address = API.HUI_PRODUCTS_LIST +
                            "&category_id=" + category_id +
                            "&rows=" + defaultRows +
                            "&page=" + currentPage +
                            "&sort=id" +
                            "&order=" + currentOrderType;
                    if (!brand_id.equals("")) {
                        address = API.HUI_PRODUCTS_LIST +
                                "&brand_id=" + brand_id +
                                "&rows=" + defaultRows +
                                "&page=" + currentPage +
                                "&sort=id" +
                                "&order=" + currentOrderType;
                    }
                    if (!isJP.equals("")) {
                        address = "http://www.qkxmall.com/?m=api&c=goods&a=lists&status_ext=1" +
                                "&page=" + currentPage +
                                "&rows=" + defaultRows +
                                "&sort=id" +
                                "&order=" + currentOrderType;
                    }
                    currentAddress = address;
                    currentSortType = "id";
                    Log.e("CurrentAddress", currentAddress);
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("努力加载中...");
                    progressDialog.show();
                    Sort sort = new Sort(progressDialog);
                    BackgroundTask backgroundTask = new BackgroundTask(context, address, sort);
                    backgroundTask.doInBackground();
                    sort0.setBackgroundResource(R.drawable.huisort0);
                    sort1.setBackgroundResource(R.drawable.huisort1);
                    sort2.setBackgroundResource(R.drawable.huisort2selected);
//                    sort3.setBackgroundResource(R.drawable.huisort3);
                    sort4.setBackgroundResource(R.drawable.huisort3);
                }
                break;
                //评论
//                case R.id.hui_sort_3: {
//                    currentPage = 1;
//                    String address = API.HUI_PRODUCTS_LIST +
//                            "&category_id=" + category_id +
//                            "&rows=" + defaultRows +
//                            "&page=" + currentPage +
//                            "&sort=commnum" +
//                            "&order=" + "desc";
//                    if (!brand_id.equals("")) {
//                        address = API.HUI_PRODUCTS_LIST +
//                                "&brand_id=" + brand_id +
//                                "&rows=" + defaultRows +
//                                "&page=" + currentPage +
//                                "&sort=commnum" +
//                                "&order=" + "desc";
//                    }
//                    if (!isJP.equals("")) {
//                        address = "http://www.qkxmall.com/?m=api&c=goods&a=lists&status_ext=1" +
//                                "&page=" + currentPage +
//                                "&rows=" + defaultRows +
//                                "&sort=commnum" +
//                                "&order=" + currentOrderType;
//                    }
//                    currentAddress = address;
//                    currentSortType = "commnum";
//                    Log.e("CurrentAddress", currentAddress);
//                    ProgressDialog progressDialog = new ProgressDialog(context);
//                    progressDialog.setMessage("努力加载中...");
//                    progressDialog.show();
//                    Sort sort = new Sort(progressDialog);
//                    BackgroundTask backgroundTask = new BackgroundTask(context, address, sort);
//                    backgroundTask.doInBackground();
//                    sort0.setBackgroundResource(R.drawable.huisort0);
//                    sort1.setBackgroundResource(R.drawable.huisort1);
//                    sort2.setBackgroundResource(R.drawable.huisort2);
//                    sort3.setBackgroundResource(R.drawable.huisort3selected);
//                    sort4.setBackgroundResource(R.drawable.huisort3selected);
//                }
//                break;
                case R.id.hui_sort_4: {
                    currentPage = 1;
                    String address = API.HUI_PRODUCTS_LIST +
                            "&category_id=" + category_id +
                            "&rows=" + defaultRows +
                            "&page=" + currentPage +
                            "&sort=sales_number" +
                            "&order=" + currentOrderType;
                    if (!brand_id.equals("")) {
                        address = API.HUI_PRODUCTS_LIST +
                                "&brand_id=" + brand_id +
                                "&rows=" + defaultRows +
                                "&page=" + currentPage +
                                "&sort=sales_number" +
                                "&order=" + currentOrderType;
                    }
                    if (!isJP.equals("")) {
                        address = "http://www.qkxmall.com/?m=api&c=goods&a=lists&status_ext=1" +
                                "&page=" + currentPage +
                                "&rows=" + defaultRows +
                                "&sort=sales_number" +
                                "&order=" + currentOrderType;
                    }
                    currentAddress = address;
                    currentSortType = "sales_number";
                    Log.e("CurrentAddress", currentAddress);
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("努力加载中...");
                    progressDialog.show();
                    Sort sort = new Sort(progressDialog);
                    BackgroundTask backgroundTask = new BackgroundTask(context, address, sort);
                    backgroundTask.doInBackground();
                    sort0.setBackgroundResource(R.drawable.huisort0);
                    sort1.setBackgroundResource(R.drawable.huisort1);
                    sort2.setBackgroundResource(R.drawable.huisort2);
//                    sort3.setBackgroundResource(R.drawable.huisort3);
                    sort4.setBackgroundResource(R.drawable.huisort3selected);
                }
                break;
            }
        }
    }

    private class OnItemClickListeners implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(HuiClassItemsActivity.this, HuiActivity.class);
            intent.putExtra("gid", (String) productsList.get(position).get("id"));
            intent.putExtra("categoryid",category_id);
            System.out.println("\r\n+++++++++++++++++++++跳转详情页传递的数据：" + (String) productsList.get
                    (position).get("id"));

            startActivity(intent);

        }
    }

    @Override
    public void onScrollChange(MyScrollView view, int x, int y, int oldx, int oldy) {


        if(y>1512){
            btnUp.setVisibility(View.VISIBLE);
        }else {
            btnUp.setVisibility(View.GONE);
        }


        Log.e(this.getClass().getSimpleName(), "-----------------------------------x:" + x + ",y:" + y + ",oldx:" + oldx + ",oldy:" + oldy);
    }

    @Override
    public void onScrollBottomListener() {

    }

    @Override
    public void onScrollTopListener() {

    }

    public void showShare() {


        LOD lod = new LOD(context);
        String shareAdd = API.USER_SHARE + "&uid=" + lod.get("USER_INFO", "UID", "'");
        Handler handler = new Handler();
        BackgroundTask backgroundTask = new BackgroundTask(context, shareAdd, handler);
        backgroundTask.doInBackground();

//        String appID = "wxb4be16d381b98898";
//        String appSecret = "d4624c36b6795d1d99dcf0547af5443d";
        String appID = "wxc132cfd42b28010a";
        String appSecret = "33f0d3845904d17f056b77c0af8e5029";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(HuiClassItemsActivity.this, appID, appSecret);
        wxHandler.addToSocialSDK();
        // 添加微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(HuiClassItemsActivity.this, appID, appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(HuiClassItemsActivity.this, "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qqSsoHandler.addToSocialSDK();
        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(HuiClassItemsActivity.this,
                "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qZoneSsoHandler.addToSocialSDK();

        //设置新浪SSO handler
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
        //设置腾讯微博SSO handler
        mController.getConfig().setSsoHandler(new TencentWBSsoHandler());

        // 设置分享内容
        mController.setShareContent("穷开心购物商城 专为开心生活量身定制，全场包邮 价格更实惠 用心来做 只为你那开心一刻");
        // 设置分享图片, 参数2为图片的url地址
        mController.setShareMedia(new UMImage(HuiClassItemsActivity.this, "http://www.qkxmall" +
                ".com/xiazai/"));

        mController.setShareImage(new UMImage(context, BitmapFactory.decodeResource(getResources(),
                R.drawable.fenxiang)));
        qqSsoHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
        qqSsoHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
        wxCircleHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
        wxHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
        mController.setAppWebSite(SHARE_MEDIA.RENREN, "http://www.qkxmall.com/xiazai/");
        mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);
        mController.openShare(HuiClassItemsActivity.this, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**使用SSO授权必须添加如下代码 */
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    private class LoadData extends Handler {
        ProgressDialog progressDialog;

        public LoadData(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        JD jd = new JD(context);
                        try {
                            HashMap<String, Object> data = jd.decode(bundle.getString("result"),
                                    API.HUI_PRODUCTS_LIST);
//sss
//                            String total = (String) data.get("total");
                            productsList = (List<HashMap<String, Object>>) data.get("rows");
                            image.setImageURI(Uri.parse(API.ADD + data.get("cate_image")));
                            Log.e("currentTitleImageAdd", API.ADD + data.get("cate_image"));
                            String is_rectStr=data.get("is_rect").toString();
                            boolean is_rect="0".equals(is_rectStr)?true:false;
                            productsGridAdapter = new BaseHuiClassItemAdapter(productsList,
                                    context,is_rect);
                            grid.setAdapter(productsGridAdapter);
                            productsGridAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE: {
                    progressDialog.dismiss();
                    Toast.makeText(context, "数据请求失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class Sort extends Handler {
        public Sort(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        ProgressDialog progressDialog;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        JD jd = new JD(context);
                        try {
                            productsList.clear();
                            HashMap<String, Object> data = jd.decode(bundle.getString("result"),
                                    API.HUI_PRODUCTS_LIST);
                            String is_rectStr=data.get("is_rect").toString();
                            boolean is_rect="0".equals(is_rectStr)?true:false;
                            productsList = (List<HashMap<String, Object>>) data.get("rows");
                            productsGridAdapter = new BaseHuiClassItemAdapter(productsList,
                                    context,is_rect);
                            grid.setAdapter(productsGridAdapter);
                            productsGridAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE: {
                    progressDialog.dismiss();
                    Toast.makeText(context, "数据获取失败,请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class Refresh extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        JD jd = new JD(context);
                        try {
                            HashMap<String, Object> data = jd.decode(bundle.getString("result"),
                                    API.HUI_PRODUCTS_LIST);
                            List<HashMap<String, Object>> temp = (List<HashMap<String, Object>>)
                                    data.get("rows");
                            if (temp != null) {
                                productsList.clear();
                                productsList.addAll(temp);
                            }
                            if(temp.size()==0){
                                Log.v("jjj","没有数据了");
                                pullScroll.setRefreshingLabel("小二疯狂上传中，敬请期待！");
                            }else{
//                                pullScroll.setReleaseLabel("正在载入...");
                            }
                            String is_rectStr=data.get("is_rect").toString();
                            boolean is_rect="0".equals(is_rectStr)?true:false;
                            image.setImageURI(Uri.parse(API.ADD + data.get("cate_image")));
                            productsGridAdapter = new BaseHuiClassItemAdapter(productsList,
                                    context,is_rect);
                            grid.setAdapter(productsGridAdapter);
                            productsGridAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据请求失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            pullScroll.onRefreshComplete();
        }
    }

    private class LoadMore extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        JD jd = new JD(context);
                        try {
                            HashMap<String, Object> data = jd.decode(bundle.getString("result"),
                                    API.HUI_PRODUCTS_LIST);
                            List<HashMap<String, Object>> temp = (List<HashMap<String, Object>>)
                                    data.get("rows");
                            if(temp.size()==0){
                                Log.v("jjj","没有数据了");
                                pullScroll.setRefreshingLabel("小二疯狂上传中，敬请期待！");
                            }else{
//                                pullScroll.setReleaseLabel("正在载入...");
                            }
                            String is_rectStr=data.get("is_rect").toString();
                            boolean is_rect="0".equals(is_rectStr)?true:false;
                            if(productsGridAdapter==null){
                               productsGridAdapter=new BaseHuiClassItemAdapter();
                                productsGridAdapter.isRect(is_rect);
                                grid.setAdapter(productsGridAdapter);
                            }
                            productsGridAdapter.addHolder(temp);
                            productsGridAdapter.notifyDataSetChanged();
//                            if (temp != null) {
//                                productsList.addAll(temp);
//                            } else {
//                                Toast.makeText(context, "没有啦(-｡-;)", Toast.LENGTH_SHORT).show();
//                            }
                            image.setImageURI(Uri.parse(API.ADD + data.get("cate_image")));
//                            productsGridAdapter = new BaseHuiClassItemAdapter(productsList,
//                                    context);

//                            productsGridAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据请求失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            pullScroll.onRefreshComplete();
        }
    }


}
