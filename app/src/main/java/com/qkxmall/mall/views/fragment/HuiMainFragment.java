package com.qkxmall.mall.views.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.MyScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollViewWithListener;
import com.qkxmall.mall.Utils;
import com.qkxmall.mall.beans.GoodListItem;
import com.qkxmall.mall.beans.MainTuiJianBrandItem;
import com.qkxmall.mall.define.adapter.grid.BaseHuiClassItemAdapter;
import com.qkxmall.mall.model.LOD;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.adapter.PagerAdapter;
import com.qkxmall.mall.define.views.GridViewPager;
import com.qkxmall.mall.define.views.GridViewWithScrollView;
import com.qkxmall.mall.model.hui.adapter.HuiBrandAdapter;
import com.qkxmall.mall.model.hui.adapter.HuiItemAdapter;
import com.qkxmall.mall.model.hui.adapter.HuiJinPinAdapter;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.nets.JD;
import com.qkxmall.mall.provider.DataOper;
import com.qkxmall.mall.util.DateUtil;
import com.qkxmall.mall.util.Sha1;
import com.qkxmall.mall.views.ConnectGetServiceActivity;
import com.qkxmall.mall.views.fragment.giftCode.Gift;
import com.qkxmall.mall.views.func.coucou.activity.CouCouMainActivity;
import com.qkxmall.mall.views.func.coucou.adapter.HuiMainBrandAdapter;
import com.qkxmall.mall.views.func.webview.ShequActivity;
import com.qkxmall.mall.views.hui.HuiClassItemsActivity;
import com.qkxmall.mall.views.hui.container.HuiActivity;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 10/9/2015.
 */
public class HuiMainFragment extends Fragment implements MyScrollView.OnScrollChangeListener {
    private int defaultPage = 1;
    private int defaultRows = 16;
    private int oldPage = 1;
    private int currentPage = 1;
    private String oldUrl = "";
    private String currentUrl = "";


    List<HashMap<String, Object>> advertisementList = new ArrayList<>();
    List<HashMap<String, Object>> sixImages = new ArrayList<>();

    List<HashMap<String, Object>> hotList = new ArrayList<>();
    HuiItemAdapter hotAdapter;
    HuiMainBrandAdapter hotAdapter2;
    private TextView noInfoShow = null;
    List<HashMap<String, Object>> brandList = new ArrayList<>();

    private PullToRefreshScrollViewWithListener pullScroll = null;
    private ScrollView scrollView;

    private ImageView huiMenu = null;
    private ImageView huiSearch = null;
    private ImageView messageHui = null;

    private GridViewWithScrollView brandGrid = null;
    private GridViewWithScrollView grid;
    private GridView jinPin = null;
    private ViewPager ads = null;
    private GridViewPager viewPagerHuiBuyBottom;
    private View jph = null;
    RelativeLayout hui_buy_title;
    BaseHuiClassItemAdapter productsGridAdapter;
    List<HashMap<String, Object>> productsList = new ArrayList<>();
    /**
     * ----------------------------------------------------------
     * |   hot1   |   hot2    |   hot3    |   hot4    |   hot5 |
     * -----------------------------------------------------------
     * | 个护化妆 | 日用百货 | 手机数码 | 运动户外 | 电脑办公 |
     * -----------------------------------------------------------
     */
    private TextView hot1;
    private TextView hot2;
    private TextView hot3;
    private TextView hot4;
    private TextView hot5;


    /**
     * --------------------------------------------------------------------
     * |     p1    |     p2   |   p3     |      p4  |   p5     |      p6  |
     * -------------------------------------------------------------------
     * | 潮流舒适 | 时尚搭配 | 多姿多彩 | 帅气时尚 | 动感十足 | 足下时尚 |
     * -------------------------------------------------------------------
     */
    private ImageView p1 = null;
    private ImageView p2 = null;
    private ImageView p3 = null;
    private ImageView p4 = null;
    private SimpleDraweeView p5 = null;
    private SimpleDraweeView p6 = null;

    //惠购首页顶部分类商品
    private com.qkxmall.mall.define.override.GridView hot = null;
     ProgressDialog progressDialog;
    Context context;
    private PagerAdapter pagerAdapter;
    private AdHandler adHandler;
    private Button btnUp;
//    private Timer timer;

    static View rootView = null;

    public HuiMainFragment() {
    }

    public void init(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_hui_main, container, false);
        }


        init(rootView);

        //获取六个图片
        String urlsixImage = API.ADVERTISEMENT + "&pos_id=13";
        LoadSixImage loadsixImage = new LoadSixImage();
        BackgroundTask advertisement1 = new BackgroundTask(context, urlsixImage, loadsixImage);
        advertisement1.doInBackground();
        //获取标题广告
        String url = API.ADVERTISEMENT + "&pos_id=11";
        LoadAdvrtisement loadAdvrtisement = new LoadAdvrtisement();
        BackgroundTask advertisement = new BackgroundTask(context, url, loadAdvrtisement);
        advertisement.doInBackground();

        //获取品牌列表
        LoadBrand loadBrand = new LoadBrand();
        String brandAddress = API.BRAND+"&isrecommend=1";
        BackgroundTask getBrand = new BackgroundTask(context, brandAddress, loadBrand);
        getBrand.doInBackground();

        //获取品牌item列表
        LoadRecommandBrand loadRecommandBrand = new LoadRecommandBrand();
        String brandRecommandAddress = API.MAIN_BRAND+"&access_token="+(new Sha1().getDigestOfString((DateUtil.getCurrentDate()+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());
        BackgroundTask getRecommandBrand = new BackgroundTask(context, brandRecommandAddress, loadRecommandBrand);
        getRecommandBrand.doInBackground();


        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("获取商品列表中...");
        progressDialog.show();
        String address = API.HUI_PRODUCTS_LIST +
                "&category_id=" + DataOper.queryName(getActivity().getContentResolver()) +
                "&rows=" + 16 +
                "&page=" + 1 +
                "&sort=" + "id" +
                "&order=" + "desc";

        LoadCaiNiXIHuanData loadcainiData = new LoadCaiNiXIHuanData(progressDialog);
        Log.e("loadData", address);
        BackgroundTask backgroundTask = new BackgroundTask(context, address, loadcainiData);
        backgroundTask.doInBackground();
        //获取精品推荐
//        LoadJinPin loadJinPin = new LoadJinPin();
//        String jinPinAddress = API.HUI_PRODUCTS_LIST + "&status_ext=1";
//        BackgroundTask getJinPin = new BackgroundTask(context, jinPinAddress, loadJinPin);
//        getJinPin.doInBackground();
        //获取分类热门推荐

        /**
         * 热门推荐  6
         * 潮流女装  7
         * 时尚男装 13
         * 鞋帽箱包 18
         * 手机数码 19
         * 各户化妆 20
         * 精美饰品 21
         * 天下美食 22
         * 日用百货 23
         * 户外用品 24
         * 电脑办公 25
         * 手表     26
         * 创意礼品 27
         * 营养保健 28
         *
         *
         * */
//         progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("努力加载数据中...");
//        progressDialog.show();
        String hotAddress = API.HUI_PRODUCTS_LIST + "&category_id=20&rows=16&page=1";//默认：个护化妆
        currentUrl = hotAddress;
//        LoadClassesShow loadClassesShow = new LoadClassesShow(progressDialog);
//        BackgroundTask backgroundTask = new BackgroundTask(context, hotAddress, loadClassesShow);
//        backgroundTask.doInBackground();
//        hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                Intent intent = new Intent(context, HuiActivity.class);
////                intent.putExtra("gid", (String) hotList.get(position).get("id"));
////                startActivity(intent);
//
//                Intent intent = new Intent(context, HuiClassItemsActivity.class);
//                HashMap<String ,Object> data = (HashMap<String ,Object> )hotAdapter.getItem(position);
//                intent.putExtra("brand_id", (String) data.get("id"));
//                intent.putExtra("categoryTitle", (String) data.get("name"));
//                startActivity(intent);
//            }
//        });

        //Refresh Load
//        pullScroll.setPullToRefreshOverScrollEnabled(true);
//        pullScroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<com.handmark.pulltorefresh.library.MyScrollView>() {
//
//
//            @Override
//            public void onPullDownToRefresh(PullToRefreshBase<com.handmark.pulltorefresh.library.MyScrollView> refreshView) {
//
////                System.out.println("！！！！！！！ 慧购上拉刷新");
////                oldPage = currentPage;
////                currentPage = 1;
////                progressDialog.show();
////                currentUrl = currentUrl.replace("page=" + oldPage, "page=" + currentPage);
////                Refresh refresh = new Refresh();
////                BackgroundTask task = new BackgroundTask(context, currentUrl, refresh);
////                task.doInBackground();
//            }
//
//            @Override
//            public void onPullUpToRefresh(PullToRefreshBase<com.handmark.pulltorefresh.library.MyScrollView> refreshView) {
//
//                System.out.println("！！！！！！！ 慧购下拉刷新");
////                oldPage = currentPage;
////                currentPage++;
////                currentUrl = currentUrl.replace("page=" + oldPage, "page=" + currentPage);
////                LoadMore loadMore = new LoadMore();
////                BackgroundTask task = new BackgroundTask(context, currentUrl, loadMore);
////                task.doInBackground();
//            }
//        });
        scrollView = pullScroll.getRefreshableView();
        pullScroll.setScrollListener(this);
        pullScroll.setReleaseLabel("小二疯狂上传中，敬请期待！");

        /**
         * 设置点击监听事件
         * */
        huiMenu.setOnClickListener(new OnClick(context));
        huiSearch.setOnClickListener(new OnClick(context));
        messageHui.setOnClickListener(new OnClick(context));
        p1.setOnClickListener(new OnClick(context));
        p2.setOnClickListener(new OnClick(context));
        p3.setOnClickListener(new OnClick(context));
        p4.setOnClickListener(new OnClick(context));
        p5.setOnClickListener(new OnClick(context));
        p6.setOnClickListener(new OnClick(context));
        jph.setOnClickListener(new OnClick(context));

        hot1.setOnClickListener(new OnClick(context));
        hot2.setOnClickListener(new OnClick(context));
        hot3.setOnClickListener(new OnClick(context));
        hot4.setOnClickListener(new OnClick(context));
        hot5.setOnClickListener(new OnClick(context));

        return rootView;
    }

    float haibaoHeight;

    /**
     * 初始化布局控件
     */
    private void init(View rootView) {
        huiMenu = (ImageView) rootView.findViewById(R.id.hui_buy_title_menu);
        grid = (GridViewWithScrollView) rootView.findViewById(R.id.hui_class_grid);
        ads = (ViewPager) rootView.findViewById(R.id.hui_buy_tab_view_pager_ads);
        viewPagerHuiBuyBottom = (GridViewPager) rootView.findViewById(R.id.pager_hui_buy_bottom);
        brandGrid = (GridViewWithScrollView) rootView.findViewById(R.id.hui_buy_pin_pai_grid);
        btnUp = (Button)rootView.findViewById(R.id.btn_up);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("scroll","*******************");
//                pullScroll.scrollTo(0,0);
                scrollView.scrollTo(0,0);
            }
        });
        brandGrid.setFocusable(false);
        jinPin = (GridView) rootView.findViewById(R.id.jinPin);
        jinPin.setFocusable(false);
        jph = rootView.findViewById(R.id.title_jin_pin);
        messageHui = (ImageView) rootView.findViewById(R.id.message2);
        hot1 = (TextView) rootView.findViewById(R.id.hot1);
        hot2 = (TextView) rootView.findViewById(R.id.hot2);
        hot3 = (TextView) rootView.findViewById(R.id.hot3);
        hot4 = (TextView) rootView.findViewById(R.id.hot4);
        hot5 = (TextView) rootView.findViewById(R.id.hot5);
        huiSearch = (ImageView) rootView.findViewById(R.id.hui_search);
        hui_buy_title = (RelativeLayout) rootView.findViewById(R.id.hui_buy_title);
        // TODO W540 x H532
        p1 = (ImageView) rootView.findViewById(R.id.ml_up);
        p2 = (ImageView) rootView.findViewById(R.id.ml_down);
        p3 = (ImageView) rootView.findViewById(R.id.ml_sh);

        p4 = (ImageView) rootView.findViewById(R.id.fe_up);
        p5 = (SimpleDraweeView) rootView.findViewById(R.id.fe_down);
        p6 = (SimpleDraweeView) rootView.findViewById(R.id.fe_sh);
        context = getActivity();
//        Log.e("tag","-----------------------------------------context:"+context);
        Gift gift = new Gift(context);
//        gift.format(p1);
        gift.format(p4);

        //Gift Code

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);


        Log.e("width", String.valueOf(displayMetrics.widthPixels));
        int width = Integer.parseInt(String.valueOf(displayMetrics.widthPixels));
        Log.e("width", width+"jjjjj");
        Log.e("width", (width*120)/525+"jjjjj");
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(width,(width*48)/108);
        LinearLayout.LayoutParams lp2=new LinearLayout.LayoutParams(width/3,(width*525)/(1080));
        LinearLayout.LayoutParams lp3=new LinearLayout.LayoutParams(width/3,(width*525)/(1080));
        LinearLayout.LayoutParams lp4=new LinearLayout.LayoutParams(width/3,(width*525)/(1080));
        p1.setLayoutParams(lp);
        p2.setLayoutParams(lp2);
        p3.setLayoutParams(lp3);
        p4.setLayoutParams(lp4);
//        p1.setMinimumWidth(displayMetrics.widthPixels / 2);
//        p4.setMaxWidth(displayMetrics.widthPixels / 2);
//        p4.setMinimumWidth(displayMetrics.widthPixels / 2);
//
//        p1.setMaxHeight(((displayMetrics.widthPixels / 2) * 532) / 540);
//        p1.setMinimumHeight(((displayMetrics.widthPixels / 2) * 532) / 540);
//        p4.setMaxHeight(((displayMetrics.widthPixels / 2) * 532) / 540);
//        p4.setMinimumHeight(((displayMetrics.widthPixels / 2) * 532) / 540);


        Log.e("p1Width", String.valueOf(p1.getWidth()));
        Log.e("p2Width", String.valueOf(p2.getWidth()));
        Log.e("p3Width", String.valueOf(p3.getWidth()));
        Log.e("p4Width", String.valueOf(p4.getWidth()));
        Log.e("p5Width", String.valueOf(p5.getWidth()));
        Log.e("p6Width", String.valueOf(p6.getWidth()));


        Log.e("p1Height", String.valueOf(p1.getHeight()));
        Log.e("p2Height", String.valueOf(p2.getHeight()));
        Log.e("p3Height", String.valueOf(p3.getHeight()));
        Log.e("p4Height", String.valueOf(p4.getHeight()));
        Log.e("p5Height", String.valueOf(p5.getHeight()));
        Log.e("p6Height", String.valueOf(p6.getHeight()));


        hot = (com.qkxmall.mall.define.override.GridView) rootView.findViewById(R.id.hot);
        hot.setFocusable(false);
//        hot1.setTextColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
        hot1.setBackgroundColor(getResources().getColor(R.color.white));
        hot2.setTextColor(getResources().getColor(R.color.white));
        hot2.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
        hot3.setTextColor(getResources().getColor(R.color.white));
        hot3.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
        hot4.setTextColor(getResources().getColor(R.color.white));
        hot4.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
        hot5.setTextColor(getResources().getColor(R.color.white));
        hot5.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));

        noInfoShow = (TextView) rootView.findViewById(R.id.noInfoShow);
        pullScroll = (PullToRefreshScrollViewWithListener) rootView.findViewById(R.id.hui_gou_scroll);
        haibaoHeight = Utils.dp2px(getResources(), 192);
        grid.setOnItemClickListener(new OnItemClickListeners());

    }

    @Override
    public void onScrollChange(MyScrollView view, int x, int y, int oldx, int oldy) {

        if (y > haibaoHeight) {
            int color = Color.argb(255, 255, 255, 255);
            hui_buy_title.setBackgroundColor(color);
        } else if (y > 0) {

            int alpha = (int) (255 / haibaoHeight * 1.0 * y);
            int color = Color.argb(alpha, 255, 255, 255);

            hui_buy_title.setBackgroundColor(color);


        } else {

            int color = Color.argb(0, 255, 255, 255);
            hui_buy_title.setBackgroundColor(color);
        }

        if(y>1512){
            btnUp.setVisibility(View.VISIBLE);
        }else {
            btnUp.setVisibility(View.GONE);
        }


        Log.e(this.getClass().getSimpleName(), "-----------------------------------x:" + x + ",y:" + y + ",oldx:" + oldx + ",oldy:" + oldy);
    }

    @Override
    public void onScrollBottomListener() {
        Log.e(this.getClass().getSimpleName(), "-----------------------------------------onScrollBottomListener");
    }

    @Override
    public void onScrollTopListener() {
        Log.e(this.getClass().getSimpleName(), "-----------------------------------------onScrollTopListener");
    }

    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

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
        UMWXHandler wxHandler = new UMWXHandler(getActivity(), appID, appSecret);
        wxHandler.addToSocialSDK();
        // 添加微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(getActivity(), appID, appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(getActivity(), "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qqSsoHandler.addToSocialSDK();
        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(getActivity(), "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qZoneSsoHandler.addToSocialSDK();
        qZoneSsoHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
        qqSsoHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
        wxCircleHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
        wxHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");

        //设置新浪SSO handler
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
        //设置腾讯微博SSO handler
        mController.getConfig().setSsoHandler(new TencentWBSsoHandler());

        // 设置分享内容
        mController.setShareContent("穷开心购物商城 专为开心生活量身定制，全场包邮 价格更实惠 用心来做 只为你那开心一刻");
        // 设置分享图片, 参数2为图片的url地址
        mController.setShareMedia(new UMImage(getActivity(), "http://www.qkxmall.com/xiazai/"));
        mController.setShareImage(new UMImage(context, BitmapFactory.decodeResource
                (getResources(), R.drawable.fenxiang)));


        mController.setAppWebSite(SHARE_MEDIA.RENREN, "http://www.qkxmall.com/xiazai/");
        mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);
        mController.openShare(getActivity(), false);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isStartTask) {
            isStartTask = false;
//            if (timer != null) {
//                timer.cancel();
            if (adHandler != null) {
                adHandler.removeMessages(0);
//                    adHandler.removeCallbacks();
            }
//                task = null;
//                timer = null;
//            }
        }
    }

    boolean isStartTask;

    @Override
    public void onResume() {
        super.onResume();
        if (!isStartTask) {
            isStartTask = true;
//            if (timer == null)
//                timer = new Timer(true);
//            task = new MyTimerTask();
//            timer.schedule(task, 1000, 5000);
            if (adHandler == null)
                adHandler = new AdHandler();
            if (pagerAdapter != null)
                adHandler.sendEmptyMessage(0);
        }
    }

    /**
     *
     * */
    private class OnClick implements View.OnClickListener {
        Context context;

        public OnClick(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, HuiClassItemsActivity.class);
            switch (v.getId()) {
                case R.id.hui_buy_title_menu:
//                    showShare();
                    startActivity(new Intent(getActivity(), ShequActivity.class));
                    break;
                case R.id.hui_search: {
//                    startActivity(new Intent(context, HuiSearchActivity.class));
                    getActivity().findViewById(R.id.category).performClick();
                }
                break;
                case R.id.message2:
                    startActivity(new Intent(context, ConnectGetServiceActivity.class));
                    break;
                case R.id.ml_up:
                    Toast.makeText(context, "女生上装", Toast.LENGTH_SHORT).show();
                    intent.putExtra("categoryTitle", "女生上装");
                    intent.putExtra("category_id", "10");
                    intent.putExtra("keyword", "");
                    startActivity(intent);
                    break;
                case R.id.ml_down:
                    Toast.makeText(context, "女生下装", Toast.LENGTH_SHORT).show();
                    intent.putExtra("categoryTitle", "女生下装");
                    intent.putExtra("category_id", "11");
                    intent.putExtra("keyword", "");
                    startActivity(intent);
                    break;
                case R.id.ml_sh:
                    Toast.makeText(context, "女鞋", Toast.LENGTH_SHORT).show();
                    intent.putExtra("categoryTitle", "女鞋");
                    intent.putExtra("category_id", "68");
                    intent.putExtra("keyword", "");

                    startActivity(intent);
                    break;
                case R.id.fe_up:
                    Toast.makeText(context, "男生上装", Toast.LENGTH_SHORT).show();
                    intent.putExtra("categoryTitle", "男生上装");
                    intent.putExtra("category_id", "14");
                    intent.putExtra("keyword", "");


                    startActivity(intent);

                    break;
                case R.id.fe_down:
                    Toast.makeText(context, "男生下装", Toast.LENGTH_SHORT).show();
                    intent.putExtra("categoryTitle", "男生下装");
                    intent.putExtra("category_id", "16");
                    intent.putExtra("keyword", "");
                    startActivity(intent);

                    break;
                case R.id.fe_sh:
                    Toast.makeText(context, "男鞋", Toast.LENGTH_SHORT).show();
                    intent.putExtra("categoryTitle", "男鞋");
                    intent.putExtra("category_id", "69");
                    intent.putExtra("keyword", "");
                    startActivity(intent);
                    break;
                case R.id.title_jin_pin:
                    intent.putExtra("categoryTitle", "精品推荐");
                    intent.putExtra("category_id", "6");
                    intent.putExtra("keyword", "");
                    intent.putExtra("isJP", "123");
                    startActivity(intent);
                    break;
//                case R.id.hot1: {
//                    //个护化妆
//                    ProgressDialog progressDialog = new ProgressDialog(context);
//                    progressDialog.setMessage("努力加载数据中...");
//                    progressDialog.show();
//                    currentPage = 1;
//                    String hotAddress = API.HUI_PRODUCTS_LIST + "&category_id=20&rows=" + defaultRows + "&page=" + currentPage;//手机数码
//                    currentUrl = hotAddress;
//                    LoadClassesShow loadClassesShow = new LoadClassesShow(progressDialog);
//                    BackgroundTask backgroundTask = new BackgroundTask(context, hotAddress, loadClassesShow);
//                    backgroundTask.doInBackground();
//                    hot1.setTextColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot1.setBackgroundColor(getResources().getColor(R.color.white));
//                    hot2.setTextColor(getResources().getColor(R.color.white));
//                    hot2.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot3.setTextColor(getResources().getColor(R.color.white));
//                    hot3.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot4.setTextColor(getResources().getColor(R.color.white));
//                    hot4.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot5.setTextColor(getResources().getColor(R.color.white));
//                    hot5.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                }
//                break;
//                case R.id.hot2: {
//                    //日用百货
//                    ProgressDialog progressDialog = new ProgressDialog(context);
//                    progressDialog.setMessage("努力加载数据中...");
//                    progressDialog.show();
//                    currentPage = 1;
//                    String hotAddress = API.HUI_PRODUCTS_LIST + "&category_id=23&rows=" + defaultRows + "&page=" + currentPage;//个护化妆
//                    currentUrl = hotAddress;
//                    LoadClassesShow loadClassesShow = new LoadClassesShow(progressDialog);
//                    BackgroundTask backgroundTask = new BackgroundTask(context, hotAddress, loadClassesShow);
//                    backgroundTask.doInBackground();
//                    hot1.setTextColor(getResources().getColor(R.color.white));
//                    hot1.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot2.setTextColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot2.setBackgroundColor(getResources().getColor(R.color.white));
//                    hot3.setTextColor(getResources().getColor(R.color.white));
//                    hot3.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot4.setTextColor(getResources().getColor(R.color.white));
//                    hot4.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot5.setTextColor(getResources().getColor(R.color.white));
//                    hot5.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                }
//                break;
//                case R.id.hot3: {
//                    //手机数码
//                    ProgressDialog progressDialog = new ProgressDialog(context);
//                    progressDialog.setMessage("努力加载数据中...");
//                    progressDialog.show();
//                    currentPage = 1;
//                    String hotAddress = API.HUI_PRODUCTS_LIST + "&category_id=19&rows=" + defaultRows + "&page=" + currentPage;
//                    currentUrl = hotAddress;
//                    LoadClassesShow loadClassesShow = new LoadClassesShow(progressDialog);
//                    BackgroundTask backgroundTask = new BackgroundTask(context, hotAddress, loadClassesShow);
//                    backgroundTask.doInBackground();
//                    hot1.setTextColor(getResources().getColor(R.color.white));
//                    hot1.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot2.setTextColor(getResources().getColor(R.color.white));
//                    hot2.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot3.setTextColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot3.setBackgroundColor(getResources().getColor(R.color.white));
//                    hot4.setTextColor(getResources().getColor(R.color.white));
//                    hot4.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot5.setTextColor(getResources().getColor(R.color.white));
//                    hot5.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                }
//                break;
//                case R.id.hot4: {
//                    //运功户外
//                    ProgressDialog progressDialog = new ProgressDialog(context);
//                    progressDialog.setMessage("努力加载数据中...");
//                    progressDialog.show();
//                    currentPage = 1;
//                    String hotAddress = API.HUI_PRODUCTS_LIST + "&category_id=24&rows=" + defaultRows + "&page=" + currentPage;
//                    currentUrl = hotAddress;
//                    LoadClassesShow loadClassesShow = new LoadClassesShow(progressDialog);
//                    BackgroundTask backgroundTask = new BackgroundTask(context, hotAddress, loadClassesShow);
//                    backgroundTask.doInBackground();
//                    hot1.setTextColor(getResources().getColor(R.color.white));
//                    hot1.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot2.setTextColor(getResources().getColor(R.color.white));
//                    hot2.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot3.setTextColor(getResources().getColor(R.color.white));
//                    hot3.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot4.setTextColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot4.setBackgroundColor(getResources().getColor(R.color.white));
//                    hot5.setTextColor(getResources().getColor(R.color.white));
//                    hot5.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                }
//                break;
//                case R.id.hot5: {
//                    //电脑办公
//                    ProgressDialog progressDialog = new ProgressDialog(context);
//                    progressDialog.setMessage("努力加载数据中...");
//                    progressDialog.show();
//                    currentPage = 1;
//                    String hotAddress = API.HUI_PRODUCTS_LIST + "&category_id=25&rows=" + defaultRows + "&page=" + currentPage;
//                    currentUrl = hotAddress;
//                    LoadClassesShow loadClassesShow = new LoadClassesShow(progressDialog);
//                    BackgroundTask backgroundTask = new BackgroundTask(context, hotAddress, loadClassesShow);
//                    backgroundTask.doInBackground();
//                    hot1.setTextColor(getResources().getColor(R.color.white));
//                    hot1.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot2.setTextColor(getResources().getColor(R.color.white));
//                    hot2.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot3.setTextColor(getResources().getColor(R.color.white));
//                    hot3.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot4.setTextColor(getResources().getColor(R.color.white));
//                    hot4.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot5.setTextColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
//                    hot5.setBackgroundColor(getResources().getColor(R.color.white));
//                }
//                break;
            }
        }
    }

//    MyTimerTask task;
//
//    class MyTimerTask extends TimerTask {
//        public void run() {
//            Message message = new Message();
//            message.what = 1;
//            adHandler.sendMessage(message);
//        }
//    }

    private class AdHandler extends Handler {
        public void handleMessage(Message msg) {
            if (pagerAdapter == null||!isStartTask)
                return;
            int count = pagerAdapter.getCount();
            if (count > 2) { // 实际上，多于1个，就多于3个
                int index = ads.getCurrentItem();
                index = index + 1; //这里修改过
                if (index >= count)
                    index = 0;
                ads.setCurrentItem(index, true);
            }
            sendEmptyMessageDelayed(0, 3000);
        }
    }

    private class LoadBrand extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        JD jd = new JD(context);
                        try {
                            HashMap<String, Object> brand = jd.decode(bundle.getString("result"), API.BRAND);
                            brandList = (List<HashMap<String, Object>>) brand.get("brand");
                            Log.v("brandlist=====","brandlist=="+brandList.size());
                            HuiBrandAdapter huiBrandAdapter = new HuiBrandAdapter(context, brandList);
                            brandGrid.setAdapter(huiBrandAdapter);
                            brandGrid.setOnItemClickListener(new BrandItemClick());

//                            hotAdapter = new HuiItemAdapter(context, brandList);
//                            hot.setAdapter(hotAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "品牌获取失败，请重试", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class LoadRecommandBrand extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
//                        JD jd = new JD(context);
//                        try {
//                            HashMap<String, Object> brand = jd.decode(bundle.getString("result"), API.BRAND);
//                            brandList = (List<HashMap<String, Object>>) brand.get("brand");
//                            Log.v("brandlist=====","brandlist=="+brandList.size());
//                            HuiBrandAdapter huiBrandAdapter = new HuiBrandAdapter(context, brandList);
//                            brandGrid.setAdapter(huiBrandAdapter);
//                            brandGrid.setOnItemClickListener(new BrandItemClick());
//
//                            hotAdapter = new HuiItemAdapter(context, brandList);
//                            hot.setAdapter(hotAdapter);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
//                        }
                        try {
                            JSONArray jsonArray = new JSONArray(bundle.getString("result"));
                            ArrayList<MainTuiJianBrandItem> mainItems = new ArrayList<>();
                            for(int i=0; i<jsonArray.length(); i++){
                                MainTuiJianBrandItem mainitem = new MainTuiJianBrandItem();
                                JSONObject object = new JSONObject(jsonArray.get(i).toString());
                                mainitem.setId(object.getString("id"));
                                mainitem.setLogo(object.getString("logo"));
                                JSONArray array = object.getJSONArray("goods_list");
                                ArrayList<GoodListItem> goodListItems = new ArrayList<>();
                                for(int j=0; j<array.length(); j++){
                                    JSONObject small = new JSONObject(array.get(j).toString());
                                    GoodListItem goodListItem = new GoodListItem();
                                    goodListItem.setId(small.getString("id"));
                                    goodListItem.setName(small.getString("name"));
                                    goodListItem.setPrice(small.getString("shop_price"));
                                    goodListItem.setSalednum(small.getString("sales_number"));
                                    String arrstr[] = small.getString("small_pics").split(",");
                                    if(arrstr != null && arrstr.length>0){
                                        goodListItem.setSmallPic(arrstr[0]);
                                    }else {
                                        goodListItem.setSmallPic(small.getString("small_pics"));
                                    }

                                    goodListItems.add(goodListItem);
                                }
                                mainitem.setGoodListItems(goodListItems);
                                mainItems.add(mainitem);

                            }
                            hotAdapter2 = new HuiMainBrandAdapter(context, mainItems);
                            hot.setAdapter(hotAdapter2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "品牌获取失败，请重试", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class LoadJinPin extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        JD jd = new JD(context);
                        try {
                            HashMap<String, Object> map = jd.decode(bundle.getString("result"), API.HUI_PRODUCTS_LIST);
                            final List<HashMap<String, Object>> jinPinList = (List<HashMap<String, Object>>) map.get("rows");
                            HuiJinPinAdapter huiJinPinAdapter = new HuiJinPinAdapter(context, jinPinList);
                            jinPin.setAdapter(huiJinPinAdapter);
                            jinPin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(context, HuiActivity.class);
                                    intent.putExtra("gid", (String) jinPinList.get(position).get("id"));
                                    startActivity(intent);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据获取失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class LoadClassesShow extends Handler {
        ProgressDialog progressDialog;

        public LoadClassesShow(ProgressDialog progressDialog) {
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
                            HashMap<String, Object> map = jd.decode(bundle.getString("result"), API.HUI_PRODUCTS_LIST);
                            hotList.clear();
                            hotList = (List<HashMap<String, Object>>) map.get("rows");
                            if (hotList.size() == 0) {
                                noInfoShow.setVisibility(View.VISIBLE);
                            } else {
                                noInfoShow.setVisibility(View.GONE);
                            }
                            hotAdapter = new HuiItemAdapter(context, hotList);
                            hot.setAdapter(hotAdapter);
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
                    Toast.makeText(context, "数据获取失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class BrandItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position == 0){
                Intent intent = new Intent(getActivity(), CouCouMainActivity.class);
                startActivity(intent);
            }else {
                Intent intent = new Intent(context, HuiClassItemsActivity.class);
                intent.putExtra("brand_id", (String) brandList.get(position).get("id"));
                intent.putExtra("categoryTitle", (String) brandList.get(position).get("name"));
                startActivity(intent);
            }

        }
    }

    private class LoadAdvrtisement extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        JD jd = new JD(context);
                        HashMap<String, Object> data = new HashMap<>();
                        try {
                            data = jd.decode(bundle.getString("result"), API.ADVERTISEMENT);
                            advertisementList = (List<HashMap<String, Object>>) data.get("rows");
                            List<View> viewList = new ArrayList<>();

                            for (int i = 0; i < advertisementList.size(); i++) {
                                View view = LayoutInflater.from(context).inflate(R.layout.fragment_advertisement, null);
                                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.image_title);
                                simpleDraweeView.setImageURI(Uri.parse(API.ADD + advertisementList.get(i).get("content")));
                                final int finalI = i;
                                simpleDraweeView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(context, HuiClassItemsActivity.class);
                                        intent.putExtra("categoryTitle", "惠");
                                        intent.putExtra("item_type", "hg_ad");
                                        intent.putExtra("category_id", (String) advertisementList.get(finalI).get("link"));
                                        startActivity(intent);
                                    }
                                });
                                viewList.add(view);
                            }
                            pagerAdapter = new PagerAdapter(context, ads, viewList);
                            ads.setAdapter(pagerAdapter);
//                            pullScroll.scrollTo(0,0);
                            if (pagerAdapter.getCount() > 0) {
                                    if (!isStartTask) {
//                                    timer = new Timer(true);
//                                    task = new MyTimerTask();
//                                    timer.schedule(task, 1000, 5000);
                                        if (adHandler == null)
                                            adHandler = new AdHandler();
                                        adHandler.sendEmptyMessageDelayed(0,3000);
                                    }else{
                                        adHandler.sendEmptyMessageDelayed(0,3000);
                                    }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据获取失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
    private class LoadSixImage extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        JD jd = new JD(context);
                        HashMap<String, Object> data = new HashMap<>();
                        try {
                            data = jd.decode(bundle.getString("result"), API.ADVERTISEMENT);
                            sixImages = (List<HashMap<String, Object>>) data.get("rows");
//                            p1.setImageURI(Uri.parse(API.ADD + sixImages.get(0).get("content")));
                            Glide
                                    .with(context)
                                    .load(API.ADD + sixImages.get(0).get("content"))
                                    .placeholder(getResources().getDrawable(R.drawable.load_default))
                                    .into(p1);
                            Glide
                                    .with(context)
                                    .load(API.ADD + sixImages.get(1).get("content"))
                                    .placeholder(getResources().getDrawable(R.drawable.load_default))
                                    .into(p2);
                            Glide
                                    .with(context)
                                    .load(API.ADD + sixImages.get(2).get("content"))
                                    .placeholder(getResources().getDrawable(R.drawable.load_default))
                                    .into(p3);
                            Glide
                                    .with(context)
                                    .load(API.ADD + sixImages.get(3).get("content"))
                                    .placeholder(getResources().getDrawable(R.drawable.load_default))
                                    .into(p4);
//                            p2.setImageURI(Uri.parse(API.ADD + sixImages.get(1).get("content")));
//                            p3.setImageURI(Uri.parse(API.ADD + sixImages.get(3).get("content")));
//                            p4.setImageURI(Uri.parse(API.ADD + sixImages.get(2).get("content")));
                            p5.setImageURI(Uri.parse(API.ADD + sixImages.get(4).get("content")));
                            p6.setImageURI(Uri.parse(API.ADD + sixImages.get(5).get("content")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据获取失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


//    private class Refresh extends Handler {
//
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case BackgroundTask.SUCCESS: {
//                    progressDialog.hide();
//                    Bundle bundle = msg.getData();
//                    if (bundle != null) {
//                        JD jd = new JD(context);
//                        try {
//                            HashMap<String, Object> map = jd.decode(bundle.getString("result"), API.HUI_PRODUCTS_LIST);
//                            hotList.clear();
//                            hotList = (List<HashMap<String, Object>>) map.get("rows");
//                            if (hotList.size() == 0) {
//                                noInfoShow.setVisibility(View.VISIBLE);
//                            } else {
//                                noInfoShow.setVisibility(View.GONE);
//                            }
//                            hotAdapter = new HuiItemAdapter(context, hotList);
//                            hot.setAdapter(hotAdapter);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//                break;
//                case BackgroundTask.FAILURE: {
//                    Toast.makeText(context, "数据获取失败", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            }
//            pullScroll.onRefreshComplete();
//        }
//    }
//
//    private class LoadMore extends Handler {
//
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case BackgroundTask.SUCCESS: {
//                    Bundle bundle = msg.getData();
//                    if (bundle != null) {
//                        JD jd = new JD(context);
//                        try {
//                            HashMap<String, Object> map = jd.decode(bundle.getString("result"), API.HUI_PRODUCTS_LIST);
//                            List<HashMap<String, Object>> temp = (List<HashMap<String, Object>>) map.get("rows");
//                            if (temp.size() == 0) {
//                                noInfoShow.setVisibility(View.VISIBLE);
//                            } else {
//                                noInfoShow.setVisibility(View.GONE);
//                            }
//                            hotList.addAll(temp);
//                            hotAdapter.notifyDataSetChanged();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//                break;
//                case BackgroundTask.FAILURE: {
//                    Toast.makeText(context, "数据获取失败", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            }
//            pullScroll.onRefreshComplete();
//        }
//    }

    private class OnItemClickListeners implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getActivity(), HuiActivity.class);
            intent.putExtra("gid", (String) productsList.get(position).get("id"));
            intent.putExtra("categoryid",DataOper.queryName(getActivity().getContentResolver()));
            System.out.println("\r\n+++++++++++++++++++++跳转详情页传递的数据：" + (String) productsList.get
                    (position).get("id"));

            startActivity(intent);

        }
    }
    private class LoadCaiNiXIHuanData extends Handler {
        ProgressDialog progressDialog;

        public LoadCaiNiXIHuanData(ProgressDialog progressDialog) {
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
//                            image.setImageURI(Uri.parse(API.ADD + data.get("cate_image")));
                            Log.e("currentTitleImageAdd", API.ADD + data.get("cate_image"));
                            String is_rectStr=data.get("is_rect").toString();
                            boolean is_rect="0".equals(is_rectStr)?true:false;
                            productsGridAdapter = new BaseHuiClassItemAdapter(productsList,
                                    context,is_rect);
                            grid.setAdapter(productsGridAdapter);
                            productsGridAdapter.notifyDataSetChanged();
                            btnUp.performClick();
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
}
