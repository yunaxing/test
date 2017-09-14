package com.qkxmall.mall.views.func.coucou.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.adapter.PagerAdapter;
import com.qkxmall.mall.define.views.GridViewWithScrollView;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.ConnectGetServiceActivity;
import com.qkxmall.mall.views.cloud.AllCloudClassItem;
import com.qkxmall.mall.views.cloud.detail.CloudDetailActivity;
import com.qkxmall.mall.views.cloud.search.CloudSearchActivity;
import com.qkxmall.mall.views.fragment.adapter.CloudHotAdapter;
import com.qkxmall.mall.views.fragment.adapter.WillOpenAdapter;
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
 * Created by yuna on 2017/6/17.
 */

public class newCloudMainFragment extends Fragment {
    List<HashMap<String, Object>> advList = new ArrayList<>();

    TextView nothing = null;

    String willOpenCid = "";


    private PullToRefreshScrollView pullScroll = null;
//    private ScrollView scrollView;
    private int defaultPage = 1;
    private int defaultRows = 16;
    private int oldPage = 1;
    private int currentPage = 1;
    private String currentAddress;

    private ImageView cloudBuyMenu = null;
    private LinearLayout open = null;
    private ViewPager advertisement = null;
    private ImageView cloudSearch = null;
    private ImageView messageCloud = null;
    private ImageView willOpen = null;


    private TextView periods;
    private SimpleDraweeView image;
    private TextView remain;
    private TextView total;
    private TextView current;
    private TextView name;
    private TextView price;
    private ProgressBar progress;

    private GridView willOPenGrid = null;
    List<HashMap<String, Object>> willOpenList = new ArrayList<>();
//    private GridView recommend = null;
//    List<HashMap<String, Object>> recommendList = new ArrayList<>();

    private com.qkxmall.mall.define.override.GridView hot = null;

    Context context;
    List<HashMap<String, Object>> dataList = new ArrayList<>();
//    CloudHotAdapter hotAdapter;

    private PagerAdapter pagerAdapter;
    private AdHandler adHandler;
    //    private Timer timer;
    private static View rootView;

    public newCloudMainFragment() {
    }

    ProgressDialog progressDialog;
    private TextView sort0 = null;
    private TextView sort1 = null;
    private TextView sort2 = null;
    private TextView sort3 = null;
    private TextView sort4 = null;
    private GridViewWithScrollView grid = null;
    CloudHotAdapter cloudHotAdapter;
    public void init(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.new_fragment_cloud_main, container, false);
        }
        context = getActivity();
        init(rootView);


        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("数据加载中...");
        progressDialog.show();
        //获取广告
        String advertisementUrl = API.ADVERTISEMENT + "&pos_id=10";
        LoadAdvertisement loadAdvertisement = new LoadAdvertisement(progressDialog);
        BackgroundTask advertisementTask = new BackgroundTask(context, advertisementUrl,
                loadAdvertisement);
        advertisementTask.doInBackground();
        //即将揭晓
        WillAnnounce willAnnounce = new WillAnnounce();
        String urlAnnounce = API.MAIN_TITLE_CLOUD;
        BackgroundTask announceTask = new BackgroundTask(context, urlAnnounce, willAnnounce);
        announceTask.doInBackground();
        //即将揭晓Grid
        WillOpenGridHandler willOpenGridHandler = new WillOpenGridHandler();
        String willOpenGridUrl = API.JJJX_THREE_URL;
        BackgroundTask willOpenGridTask = new BackgroundTask(context, willOpenGridUrl,
                willOpenGridHandler);
        willOpenGridTask.doInBackground();
        //分类列
        String url = API.CLOUD_GET_ALL_LIST_URL + "&type=1&page=" + defaultPage + "&rows=" +
                defaultRows;
        currentAddress = url;

        pullScroll.setPullToRefreshOverScrollEnabled(true);
        pullScroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                progressDialog.show();
                System.out.println("！！！！！！！ 云购上拉刷新");
                Refresh refresh = new Refresh();
                oldPage = currentPage;
                currentPage = 1;
                currentAddress = currentAddress.replace("page=" + oldPage, "page=" + currentPage);
                WillAnnounce willAnnounce = new WillAnnounce();
                String urlAnnounce = API.MAIN_TITLE_CLOUD;
                BackgroundTask announceTask = new BackgroundTask(context, urlAnnounce, willAnnounce);
                announceTask.doInBackground();
                //即将揭晓Grid
                WillOpenGridHandler willOpenGridHandler = new WillOpenGridHandler();
                String willOpenGridUrl = API.JJJX_THREE_URL;
                BackgroundTask willOpenGridTask = new BackgroundTask(context, willOpenGridUrl,
                        willOpenGridHandler);
                willOpenGridTask.doInBackground();
                BackgroundTask task = new BackgroundTask(context, currentAddress, refresh);
                task.doInBackground();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                System.out.println("！！！！！！！ 云购下拉刷新");
                LoadMore loadMore = new LoadMore();
                oldPage = currentPage;
                currentPage++;
                currentAddress = currentAddress.replace("page=" + oldPage, "page=" + currentPage);
                BackgroundTask task = new BackgroundTask(context, currentAddress, loadMore);
                task.doInBackground();
            }
        });
        messageCloud.setOnClickListener(new OnClick());
        willOpen.setOnClickListener(new OnClick());
        cloudBuyMenu.setOnClickListener(new OnClick());
        cloudSearch.setOnClickListener(new OnClick());
        open.setOnClickListener(new OnClick());
        return rootView;
    }

    private void init(View rootView) {
        cloudBuyMenu = (ImageView) rootView.findViewById(R.id.cloud_buy_title_menu);
        advertisement = (ViewPager) rootView.findViewById(R.id.ads);
        open = (LinearLayout) rootView.findViewById(R.id.open);

        cloudSearch = (ImageView) rootView.findViewById(R.id.cloud_search);
        messageCloud = (ImageView) rootView.findViewById(R.id.message);
        willOpen = (ImageView) rootView.findViewById(R.id.will_open);
        periods = (TextView) rootView.findViewById(R.id.periods);
        image = (SimpleDraweeView) rootView.findViewById(R.id.picture);
        remain = (TextView) rootView.findViewById(R.id.remain);
        total = (TextView) rootView.findViewById(R.id.total);
        current = (TextView) rootView.findViewById(R.id.current);
        name = (TextView) rootView.findViewById(R.id.name);
        price = (TextView) rootView.findViewById(R.id.price);
        progress = (ProgressBar) rootView.findViewById(R.id.progress);

        willOPenGrid = (GridView) rootView.findViewById(R.id.willOpen);
        willOPenGrid.setFocusable(false);
//        recommend = (GridView) rootView.findViewById(R.id.recommend);
//        recommend.setFocusable(false);
//        hot = (com.qkxmall.mall2.define.override.GridView) rootView.findViewById(R.id.hot);
//        hot.setFocusable(false);
        pullScroll = (PullToRefreshScrollView) rootView.findViewById(R.id.cloud_buy_scroll);

        nothing = (TextView) rootView.findViewById(R.id.nothing);
        sort0 = (TextView) rootView.findViewById(R.id.sort_0);
        sort1 = (TextView) rootView.findViewById(R.id.sort_1);
        sort2 = (TextView) rootView.findViewById(R.id.sort_2);
        sort3 = (TextView) rootView.findViewById(R.id.sort_3);
        sort4 = (TextView) rootView.findViewById(R.id.sort_4);

        sort0.setTextColor(getResources().getColor(R.color.main_line_blue_color));
        sort1.setTextColor(getResources().getColor(R.color.white));
        sort2.setTextColor(getResources().getColor(R.color.white));
        sort3.setTextColor(getResources().getColor(R.color.white));
        sort4.setTextColor(getResources().getColor(R.color.white));
        sort0.setBackgroundResource(R.color.white);
        sort1.setBackgroundResource(R.color.main_line_blue_color);
        sort2.setBackgroundResource(R.color.main_line_blue_color);
        sort3.setBackgroundResource(R.color.main_line_blue_color);
        sort4.setBackgroundResource(R.color.main_line_blue_color);
        grid = (GridViewWithScrollView)rootView.findViewById(R.id.hui_class_grid);
        initData();
    }

    private void initData(){
        LoadData loadData = new LoadData(progressDialog);
        String address = API.JJJX_LIST_URL+"&page="+defaultPage+"&rows="+defaultRows;
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! URL:" + address);
        currentAddress = address;
//        loadMoreAddress = currentAddress;
        Log.e("CurrentAddress",currentAddress);
        BackgroundTask backgroundTask = new BackgroundTask(context,/* address + "&type=1"*/currentAddress, loadData);
        backgroundTask.doInBackground();
    }
    private class LoadData extends Handler{
        ProgressDialog progressDialog;

        public LoadData(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            pullScroll.onRefreshComplete();
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            if (jsonObject.getBoolean("flag")){
                                if (jsonObject.has("image"))
                                    image.setImageURI(Uri.parse(API.ADD+jsonObject.getString("image")));
                                JSONArray array = jsonObject.getJSONArray("data");
                                dataList.clear();
                                for (int i = 0 ; i< array.length();i++){
                                    JSONObject object = array.getJSONObject(i);
                                    HashMap<String ,Object> map = new HashMap<>();
                                    map.put("cid", object.get("cid"));
                                    map.put("name", object.get("name"));
                                    if (object.has("commnum"))
                                        map.put("commnum", object.get("commnum"));
                                    if (object.has("c_totalprice"))
                                        map.put("c_totalprice", object.get("c_totalprice"));
                                    if (object.has("c_totalterm"))
                                        map.put("c_totalterm", object.get("c_totalterm"));
                                    map.put("goodsname", object.get("goodsname"));
                                    map.put("goods_id", object.get("goods_id"));
                                    map.put("totalnum", object.get("totalnum"));
                                    map.put("curnum", object.get("curnum"));
                                    if (object.has("remain"))
                                        map.put("remain", object.get("remain"));
                                    map.put("img", object.get("img"));
                                    dataList.add(map);
                                }
                                System.out.println("!!!!!!!!!!!!!!!!! 数据源长度2：" + dataList.size());
                                cloudHotAdapter = new CloudHotAdapter(context,dataList);
                                grid.setAdapter(cloudHotAdapter);
                                cloudHotAdapter.notifyDataSetChanged();
//                                grid.setOnItemClickListener(new OnItemClickListeners());
                            }else {
                                Toast.makeText(context, "获取失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
//                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

    public void showShare() {


        LOD lod = new LOD(context);
        String shareAdd = API.USER_SHARE + "&uid=" + lod.get("USER_INFO", "UID", "'");
        Handler handler = new Handler();
        BackgroundTask backgroundTask = new BackgroundTask(context, shareAdd, handler);
        backgroundTask.doInBackground();


        String appID = "wxb4be16d381b98898";
        String appSecret = "d4624c36b6795d1d99dcf0547af5443d";
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

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, AllCloudClassItem.class);
            switch (v.getId()) {

                case R.id.open: {
                    Intent intent = new Intent(context, CloudDetailActivity.class);
                    intent.putExtra("cid", willOpenCid);
                    startActivity(intent);
                }
                break;
                case R.id.cloud_buy_title_menu:
                    showShare();
//                    startActivity(new Intent(context, AllBuyMenuActivity.class));
                    break;
                case R.id.cloud_search:
                    startActivity(new Intent(context, CloudSearchActivity.class));
                    break;
                case R.id.will_open:
                    i.putExtra("categoryTitle", "即将揭晓");
                    i.putExtra("category_id", "0");
                    startActivity(i);
                    break;
                case R.id.jp:
                    i.putExtra("categoryTitle", "精品推荐");
                    i.putExtra("category_id", "0");
                    startActivity(i);
                    break;
                case R.id.message:
                case R.id.message2:
                    startActivity(new Intent(context, ConnectGetServiceActivity.class));
                    break;
            }
        }
    }

    private class LoadAdvertisement extends Handler {
        ProgressDialog progressDialog;

        public LoadAdvertisement(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            List<View> viewList = new ArrayList<>();
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                final JSONObject object = jsonArray.getJSONObject(i);
                                View view = LayoutInflater.from(context).inflate(R.layout
                                        .image_layout, null);
                                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view
                                        .findViewById(R.id.image_title);
                                simpleDraweeView.setImageURI(Uri.parse(API.ADD + object.getString
                                        ("content")));
                                simpleDraweeView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(context, AllCloudClassItem
                                                .class);
                                        intent.putExtra("categoryTitle", "云");
                                        try {
                                            //item_type 广告
                                            intent.putExtra("category_id", object.getString
                                                    ("link"));
                                            intent.putExtra("item_type", "ad");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        startActivity(intent);
                                    }
                                });
                                viewList.add(view);
                                HashMap<String, Object> data = new HashMap<>();
                                data.put("id", object.get("id"));
                                data.put("link", object.get("link"));
                                data.put("content", object.get("content"));
                                advList.add(data);
                            }
                            pagerAdapter = new PagerAdapter(context, advertisement, viewList);
                            advertisement.setAdapter(pagerAdapter);
//                            pullScroll.scrollTo(0,0);
                            if (pagerAdapter.getCount() > 0) {
                                if (!isStartTask) {
//                                    timer = new Timer(true);
//                                    task=new MyTimerTask();
//                                    timer.schedule(task, 1000, 5000);
                                    if (adHandler == null)
                                        adHandler = new AdHandler();
                                    adHandler.sendEmptyMessageDelayed(0, 3000);
                                } else {
                                    adHandler.sendEmptyMessageDelayed(0, 3000);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE: {
                    progressDialog.dismiss();
                    Toast.makeText(context, "数据请求失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
//    MyTimerTask task;
//    class MyTimerTask extends TimerTask{
//        public void run() {
//            Message message = new Message();
//            message.what = 1;
//            adHandler.sendMessage(message);
//        }
//    };

    @Override
    public void onPause() {
        super.onPause();
        if (isStartTask) {
            isStartTask = false;

            if (adHandler != null) {
                adHandler.removeMessages(0);
            }
//            timer.purge();

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
//            task=new MyTimerTask();
//            timer.schedule(task, 1000, 5000);
            if (adHandler == null)
                adHandler = new AdHandler();
            if (pagerAdapter != null)
                adHandler.sendEmptyMessage(0);
        }
    }

    private class AdHandler extends Handler {
        public void handleMessage(Message msg) {
            if (pagerAdapter == null || !isStartTask)

                return;
            int count = pagerAdapter.getCount();
            if (count > 2) { // 实际上，多于1个，就多于3个
                int index = advertisement.getCurrentItem();
                index = index + 1; //这里修改过
                if (index >= count)
                    index = 0;
                advertisement.setCurrentItem(index, true);

            }
            sendEmptyMessageDelayed(0, 3000);
        }
    }

    private class WillAnnounce extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            JSONObject data = jsonObject.getJSONObject("data");
                            periods.setText(data.getString("c_term"));
                            name.setText(data.getString("name"));
                            price.setText(data.getString("c_totalprice"));
                            image.setImageURI(Uri.parse(API.ADD + data.getString("img")));
                            total.setText(data.getString("totalnum"));
                            current.setText(data.getString("curnum"));
                            remain.setText(data.getString("remain"));
                            progress.setMax(Integer.parseInt(data.getString("totalnum")));
                            progress.setProgress(Integer.parseInt(data.getString("curnum")));

                            willOpenCid = data.getString("cid");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据请求失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private class WillOpenGridHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            JSONArray dataArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject object = dataArray.getJSONObject(i);
                                HashMap<String, Object> data = new HashMap<>();
                                data.put("cid", object.getString("cid"));
                                data.put("name", object.getString("name"));
                                data.put("c_totalprice", object.getString("c_totalprice"));
                                data.put("c_totalterm", object.get("c_totalterm"));
                                data.put("goodsname", object.get("goodsname"));
                                data.put("goods_id", object.get("goods_id"));
                                data.put("totalnum", object.get("totalnum"));
                                data.put("curnum", object.get("curnum"));
                                data.put("remain", object.get("remain"));
                                data.put("img", object.get("img"));
                                willOpenList.add(data);
                            }
                            WillOpenAdapter willOpenAdapter = new WillOpenAdapter(context,
                                    willOpenList);
                            willOPenGrid.setAdapter(willOpenAdapter);
                            willOPenGrid.setOnItemClickListener(new AdapterView
                                    .OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int
                                        position, long id) {
                                    Intent intent = new Intent(context, CloudDetailActivity.class);
                                    intent.putExtra("cid", (String) willOpenList.get(position)
                                            .get("cid"));
                                    startActivity(intent);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据请求失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

//    private class RecommendHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case BackgroundTask.SUCCESS: {
//                    Bundle bundle = msg.getData();
//                    if (bundle != null) {
//                        try {
//                            JSONObject object = new JSONObject(bundle.getString("result"));
//                            JSONArray dataArray = object.getJSONArray("data");
//                            for (int i = 0; i < dataArray.length(); i++) {
//                                JSONObject jsonObject = dataArray.getJSONObject(i);
//                                HashMap<String, Object> data = new HashMap<>();
//                                data.put("cid", jsonObject.get("cid"));
//                                data.put("name", jsonObject.get("name"));
//                                data.put("goodsname", jsonObject.get("goodsname"));
//                                data.put("goods_id", jsonObject.get("goods_id"));
//                                data.put("c_term", jsonObject.get("c_term"));
//                                data.put("totalnum", jsonObject.get("totalnum"));
//                                data.put("curnum", jsonObject.get("curnum"));
//                                data.put("img", jsonObject.get("img"));
//                                recommendList.add(data);
//                            }
//                            RecommendItemAdapter recommendItemAdapter = new RecommendItemAdapter
//                                    (context, recommendList);
//                            recommend.setAdapter(recommendItemAdapter);
//                            recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> parent, View view, int
//                                        position, long id) {
//                                    Intent intent = new Intent(context, CloudDetailActivity.class);
//                                    intent.putExtra("cid", (String) recommendList.get(position)
//                                            .get("cid"));
//                                    startActivity(intent);
//                                }
//                            });
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//                break;
//                case BackgroundTask.FAILURE: {
//                    Toast.makeText(context, "数据请求失败，请检查网络设置", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            }
//        }
//    }

//    private class GetAll extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case BackgroundTask.SUCCESS: {
//                    Bundle bundle = msg.getData();
//                    if (bundle != null) {
//                        try {
//                            JSONObject object = new JSONObject(bundle.getString("result"));
//                            JSONArray dataArray = object.getJSONArray("data");
//                            dataList.clear();
//                            for (int i = 0; i < dataArray.length(); i++) {
//                                JSONObject jsonObject = dataArray.getJSONObject(i);
//                                HashMap<String, Object> data = new HashMap<>();
//                                data.put("cid", jsonObject.get("cid"));
//                                data.put("name", jsonObject.get("name"));
//                                data.put("commnum", jsonObject.get("commnum"));
//                                data.put("c_totalprice", jsonObject.get("c_totalprice"));
//                                data.put("c_totalterm", jsonObject.get("c_totalterm"));
//                                data.put("goodsname", jsonObject.get("goodsname"));
//                                data.put("goods_id", jsonObject.get("goods_id"));
//                                data.put("totalnum", jsonObject.get("totalnum"));
//                                data.put("curnum", jsonObject.get("curnum"));
//                                data.put("remain", jsonObject.get("remain"));
//                                data.put("img", jsonObject.get("img"));
//                                dataList.add(data);
//                            }
//                            hotAdapter = new CloudHotAdapter(context, dataList);
//                            hot.setAdapter(hotAdapter);
//                            if (dataList.size() == 0) {
//                                nothing.setVisibility(View.VISIBLE);
//                            } else {
//                                nothing.setVisibility(View.GONE);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(context, "数据解析失败，请联系客服", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//                break;
//                case BackgroundTask.FAILURE: {
//                    Toast.makeText(context, "数据请求失败，请检查网络连接", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            }
//        }
//    }

    private class Refresh extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    progressDialog.hide();
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONObject object = new JSONObject(bundle.getString("result"));
                            JSONArray dataArray = object.getJSONArray("data");
                            List<HashMap<String, Object>> tempList = new ArrayList<>();
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject jsonObject = dataArray.getJSONObject(i);
                                HashMap<String, Object> data = new HashMap<>();
                                data.put("cid", jsonObject.get("cid"));
                                data.put("name", jsonObject.get("name"));
                                data.put("commnum", jsonObject.get("commnum"));
                                data.put("c_totalprice", jsonObject.get("c_totalprice"));
                                data.put("c_totalterm", jsonObject.get("c_totalterm"));
                                data.put("goodsname", jsonObject.get("goodsname"));
                                data.put("goods_id", jsonObject.get("goods_id"));
                                data.put("totalnum", jsonObject.get("totalnum"));
                                data.put("curnum", jsonObject.get("curnum"));
                                data.put("remain", jsonObject.get("remain"));
                                data.put("img", jsonObject.get("img"));
                                tempList.add(data);
                            }
                            if (tempList != null) {
                                dataList.clear();
                                dataList.addAll(tempList);
//                                hotAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据加载失败，请稍后再试", Toast.LENGTH_SHORT).show();
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
                        try {
                            JSONObject object = new JSONObject(bundle.getString("result"));
                            JSONArray dataArray = object.getJSONArray("data");
                            List<HashMap<String, Object>> tempList = new ArrayList<>();
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject jsonObject = dataArray.getJSONObject(i);
                                HashMap<String, Object> data = new HashMap<>();
                                data.put("cid", jsonObject.get("cid"));
                                data.put("name", jsonObject.get("name"));
                                data.put("commnum", jsonObject.get("commnum"));
                                data.put("c_totalprice", jsonObject.get("c_totalprice"));
                                data.put("c_totalterm", jsonObject.get("c_totalterm"));
                                data.put("goodsname", jsonObject.get("goodsname"));
                                data.put("goods_id", jsonObject.get("goods_id"));
                                data.put("totalnum", jsonObject.get("totalnum"));
                                data.put("curnum", jsonObject.get("curnum"));
                                data.put("remain", jsonObject.get("remain"));
                                data.put("img", jsonObject.get("img"));
                                tempList.add(data);
                            }
                            if (tempList != null) {
                                dataList.addAll(tempList);
//                                hotAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(context, "没有啦(-｡-;)", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
                case BackgroundTask.FAILURE: {
                    Toast.makeText(context, "数据加载失败，请稍后再试", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            pullScroll.onRefreshComplete();
        }
    }
}
