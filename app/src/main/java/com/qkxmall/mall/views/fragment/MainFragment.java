package com.qkxmall.mall.views.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.adapter.PagerAdapter;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.nets.JD;
import com.qkxmall.mall.views.MyView.CloudMianAdapter;
import com.qkxmall.mall.views.MyView.CloudMianAdapter2;
import com.qkxmall.mall.views.cloud.AllCloudClassItem;
import com.qkxmall.mall.views.hui.HuiClassItemsActivity;
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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**
 * ViewHolder placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    private ImageView mainShareApp = null;
    private ImageView mainToCloud = null;
    private ImageView mainToHui = null;
    private RelativeLayout mainWillOpen = null;
    private ScrollView tabMainScroll= null;
    private com.qkxmall.mall.define.override.GridView cloudBuyGrid = null;
    private com.qkxmall.mall.define.override.GridView huiBuyGrid = null;
    private GridView huiBuyGridHot = null;
    private GridView congzhiGrid = null;
    private ViewPager ads = null;
    Context context;
    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

//    SimpleDraweeView image = null;
    ImageView image=null;
    TextView name = null;
    ProgressBar progress = null;
    TextView total = null;
    TextView current = null;
    TextView remain = null;

    Handler trHandler;

    private PagerAdapter pagerAdapter;
    private AdHandler  adHandler;
    private Timer timer;

    private ImageView toCloud = null;
    private ImageView toHui = null;

    public MainFragment() {
    }

    public void init(Context context, Handler trHandler) {
        this.context = context;
        this.trHandler = trHandler;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_main, container, false);
        init(rootView);

        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            String version = String.valueOf(packageInfo.versionCode);
            String versionName = packageInfo.versionName;
            org.xutils.http.RequestParams req = new org.xutils.http.RequestParams("http://www.qkxmall.com/api/android/check_update.php");
            NameValuePair pairVersion = new BasicNameValuePair("version",packageInfo.versionCode+"");
            NameValuePair pairVersionName = new BasicNameValuePair("versionName",packageInfo.versionName+"");
            req.addBodyParameter("version",packageInfo.versionCode+"");
            req.addBodyParameter("versionName",packageInfo.versionName+"");
            x.http().post(req, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.e("result",result);
                    if (result.equals("true")){
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setMessage("请更新").setTitle("提示")
                                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent();
                                        intent.setData(Uri.parse("http://www.qkxmall.com/api/android/apk/app-release.apk"));
                                        intent.setAction(Intent.ACTION_VIEW);
                                        startActivity(intent);
                                    }
                                }).setNeutralButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).create();
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Advertisement advertisement = new Advertisement();
        BackgroundTask backgroundTask = new BackgroundTask(context,API.ADVERTISEMENT+"&pos_id=9",advertisement);
        backgroundTask.doInBackground();

        String titleCloud = API.MAIN_TITLE_CLOUD;
        CloudWillGo cloudWillGo = new CloudWillGo();
        BackgroundTask cloudWill = new BackgroundTask(context,titleCloud,cloudWillGo);
        cloudWill.doInBackground();

        /*
        * 设置云购
        * */
        int[] imageProductIds = {
                R.drawable.smzn,
                R.drawable.dnbg,
                R.drawable.myhf,
                R.drawable.shkj,
                R.drawable.zsmp,
                R.drawable.hwxx
        };
        final String[] categoryTitles = {
                "手机智能",
                "电脑办公",
                "洗漱护肤",
                "生活用品",
                "穿戴名品",
                "运动户外"
        };
        final String[] category_ids = {
                "1",
                "2",
                "3",
                "7",
                "4",
                "6"
        };
        List<Map<String,Object>> gridList = new ArrayList<>();
        for (int i = 0;i<imageProductIds.length ; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("image",imageProductIds[i]);
            gridList.add(map);
        }
//        SimpleAdapter simpleAdapter = new SimpleAdapter(context,gridList,R.layout.grid_item_layout,new String[]{"image"},new int[]{R.id.item_image});
        CloudMianAdapter cloudMianAdapter  = new CloudMianAdapter(context,imageProductIds);

       cloudBuyGrid.setAdapter(cloudMianAdapter);
        cloudBuyGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, AllCloudClassItem.class);
                intent.putExtra("categoryTitle", categoryTitles[position]);
                intent.putExtra("category_id",category_ids[position]);
                startActivity(intent);
            }
        });

        /*
        * 惠购
        * */
        int[] imageIds = {
                R.drawable.shenghuoyongpin,
                R.drawable.yuodonghuwai
        };
        int[] imageProductIdsHui = {
                R.drawable.clnz,
                R.drawable.ssnz,
                R.drawable.ghhz,
                R.drawable.dnbg2,
                R.drawable.jmsp,
                R.drawable.sjsm2
        };
        String[] imageProductHuiUrls = {
                "http://www.qkxmall.com/api/android/mainfiles/clnz.png",
                "http://www.qkxmall.com/api/android/mainfiles/ssnz.png",
                "http://www.qkxmall.com/api/android/mainfiles/ghhz.png",
                "http://www.qkxmall.com/api/android/mainfiles/dnbg2.png",
                "http://www.qkxmall.com/api/android/mainfiles/jmsp.png",
                "http://www.qkxmall.com/api/android/mainfiles/sjsm2.png"
        };
        List<HashMap<String,Object>> gridListHot = new ArrayList<>();
        for (int i = 0;i<imageIds.length ; i++){
            HashMap<String,Object> map = new HashMap<>();
            map.put("image",imageIds[i]);
            gridListHot.add(map);
        }
        List<HashMap<String,Object>> huiGridListHot = new ArrayList<>();
        String imgs[] = new String[]{"",""};
        for (int i = 0;i<imageProductIdsHui.length ; i++){
            HashMap<String,Object> map = new HashMap<>();
//            map.put("image",imageProductIdsHui[i]);
            map.put("image",imageProductHuiUrls[i]);
            huiGridListHot.add(map);
        }
//        SimpleAdapter simpleAdapter2 = new SimpleAdapter(context,gridListHot,R.layout.grid_item_layout_2,new String[]{"image"},new int[]{R.id.item_image});

//        SimpleAdapter simpleAdapter3 = new SimpleAdapter(context,huiGridListHot,R.layout.grid_item_layout,new String[]{"image"},new int[]{R.id.item_image});

        HuiBuyGridAdapter_Main huiBuyGridAdapter_main = new HuiBuyGridAdapter_Main(context ,huiGridListHot);
        huiBuyGridHot.setAdapter(new CloudMianAdapter2(context,imageIds));
        huiBuyGrid.setAdapter(huiBuyGridAdapter_main);
        huiBuyGridHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, HuiClassItemsActivity.class);
                switch (position) {
                    case 0:
//                        Toast.makeText(context, "生活用品", Toast.LENGTH_SHORT).show();
                        intent.putExtra("categoryTitle", "生活用品");
                        intent.putExtra("category_id","23");
                        startActivity(intent);
                        break;
                    case 1:
//                        Toast.makeText(context, "运动户外", Toast.LENGTH_SHORT).show();
                        intent.putExtra("categoryTitle", "运动户外");
                        intent.putExtra("category_id","24");
                        startActivity(intent);
                        break;
                }
            }
        });
        //惠购分类
        huiBuyGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, HuiClassItemsActivity.class);
                switch (position) {
                    case 0:
                        Toast.makeText(context, "潮流女装", Toast.LENGTH_SHORT).show();
                        intent.putExtra("categoryTitle", "潮流女装");
                        intent.putExtra("category_id","7");
                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(context, "时尚男装", Toast.LENGTH_SHORT).show();
                        intent.putExtra("categoryTitle", "时尚男装");
                        intent.putExtra("category_id","13");
                        startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(context, "个护化妆", Toast.LENGTH_SHORT).show();
                        intent.putExtra("categoryTitle", "个护化妆");
                        intent.putExtra("category_id","20");
                        startActivity(intent);
                        break;
                    case 3:
                        Toast.makeText(context, "电脑办公", Toast.LENGTH_SHORT).show();
                        intent.putExtra("categoryTitle", "电脑办公");
                        intent.putExtra("category_id","25");
                        startActivity(intent);
                        break;
                    case 4:
                        Toast.makeText(context, "精美饰品", Toast.LENGTH_SHORT).show();
                        intent.putExtra("categoryTitle", "精美饰品");
                        intent.putExtra("category_id","21");
                        startActivity(intent);
                        break;
                    case 5:
                        Toast.makeText(context, "手机数码", Toast.LENGTH_SHORT).show();
                        intent.putExtra("categoryTitle", "手机数码");
                        intent.putExtra("category_id","19");
                        startActivity(intent);
                        break;
                }
            }
        });


        //充值
        List listCongZhi = new ArrayList();
        int[] imageCongZhi = {R.drawable.icon_zhongguoyidong_congzhi,R.drawable.icon_zhongguoliantong_congzhi,R.drawable.icon_zhongguodianxin_congzhi};
        for (int i = 0 ;i<imageCongZhi.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("imageCongzhi",imageCongZhi[i]);
            listCongZhi.add(map);
        }
        SimpleAdapter simpleAdapterCongZhi = new SimpleAdapter(context,listCongZhi,R.layout.congzhi_grid_layout,new String[]{"imageCongzhi"},new int[]{R.id.congzhi_item});
        congzhiGrid.setAdapter(simpleAdapterCongZhi);

        tabMainScroll.smoothScrollTo(0, 0);
        tabMainScroll.setVerticalScrollBarEnabled(false);

        /*
        * 请求首页顶部广告数据
        * */
        final List<HashMap<String ,Object>> list = new ArrayList<>();
        RequestParams requestParams = new RequestParams();
        HttpUtils httpUtils = new HttpUtils();
        NameValuePair pairID = new BasicNameValuePair("pos_id",API.MAIN_POS_ID);//请求ID
        requestParams.addBodyParameter(pairID);
        httpUtils.send(HttpRequest.HttpMethod.GET, API.ADVERTISEMENT, requestParams,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        try {
                            JSONObject jsonObject = new JSONObject(responseInfo.result);

                            int total = jsonObject.getInt("total");

                            JSONArray jsonArray = jsonObject.getJSONArray("rows");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                HashMap<String, Object> advertisement = new HashMap<String, Object>();
                                //解析数据
                                String id = object.getString("id");
                                String link = object.getString("link");
                                String content = object.getString("content");
                                //将解析得到的数据放入Hashmap中
                                advertisement.put("id", id);
                                advertisement.put("link", link);
                                advertisement.put("content", content);
                                list.add(advertisement);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        List<View> views = new ArrayList<View>();

                        /*
                        //设置顶部广告pager适配器
                        //遍历得到的数据，将数据放入到View中
                        for (int i = 0 ;i<list.size() ;i++){
                            View im = LayoutInflater.from(context).inflate(R.layout.ads_view_pager_layout,null);
                            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) im.findViewById(R.id.image);
                            simpleDraweeView.setImageURI(Uri.parse((String) list.get(i).get("content")));
                            views.add(im);
                        }

                        ads.setAdapter(new AdsViewPagerAdapter(views));
                        */
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {

                    }

                }
        );
        /*
        * ========================================OOM======================================================
        * */

        /*AutoSlide mainAutoSlide = new AutoSlide(ads,3);
        mainAutoSlide.slide();*/


        /*
        * 首页点击事件
        * 1、分享
        * 2、跳转到云购页面
        * 3、跳转到惠购页面
        * 4、即将揭晓页
        * */
        mainShareApp.setOnClickListener(new OnClickListeners());
        mainToCloud.setOnClickListener(new OnClickListeners());
        mainToHui.setOnClickListener(new OnClickListeners());
        mainWillOpen.setOnClickListener(new OnClickListeners());

        /*
        * 充值栏
        * */
        congzhiGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "即将推出", Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0: {
//                        Toast.makeText(getContext(), "中国移动", Toast.LENGTH_SHORT).show();
                    }
                    break;
                    case 1: {
//                        Toast.makeText(getContext(), "中国联通", Toast.LENGTH_SHORT).show();
                    }
                    break;
                    case 2: {
//                        Toast.makeText(getContext(), "中国电信", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        });

        toHui.setOnClickListener(new OnClick());
        toCloud.setOnClickListener(new OnClick());




        return rootView;
    }

    //初始换布局控件
    private void init(View rootView) {
        tabMainScroll = (ScrollView) rootView.findViewById(R.id.tab_main_scroll);
        congzhiGrid = (GridView) rootView.findViewById(R.id.congzhi_grid);
        mainShareApp = (ImageView) rootView.findViewById(R.id.share_main_share_app);
        mainToCloud = (ImageView) rootView.findViewById(R.id.cloud_buy_text);
        mainToHui = (ImageView) rootView.findViewById(R.id.main_youhui_title);
        mainWillOpen = (RelativeLayout) rootView.findViewById(R.id.cloud_buy_pro_will_info);
        huiBuyGridHot = (GridView) rootView.findViewById(R.id.huigou_class_line_1);
        huiBuyGrid = (com.qkxmall.mall.define.override.GridView) rootView.findViewById(R.id.huigou_class);
        cloudBuyGrid = (com.qkxmall.mall.define.override.GridView) rootView.findViewById(R.id.cloud_buy_class);
        ads = (ViewPager) rootView.findViewById(R.id.ads);

        image = (ImageView) rootView.findViewById(R.id.image_title);
        name = (TextView) rootView.findViewById(R.id.name);
        progress = (ProgressBar) rootView.findViewById(R.id.progress);
        total = (TextView) rootView.findViewById(R.id.total);
        current = (TextView) rootView.findViewById(R.id.current);
        remain = (TextView) rootView.findViewById(R.id.remain);

        toHui = (ImageView) rootView.findViewById(R.id.main_youhui_title);
        toCloud = (ImageView) rootView.findViewById(R.id.cloud_buy_text);

    }

    //首页控件点击事件监听器
    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,HuiClassItemsActivity.class);
            Intent i = new Intent(context,AllCloudClassItem.class);
            switch (v.getId()){
                //云购即将揭晓
                case R.id.cloud_buy_pro_will_info:
                    Intent intent11 = new Intent(context,AllCloudClassItem.class);
                    intent11.putExtra("categoryTitle","即将揭晓");
                    intent11.putExtra("category_id","0");
                    startActivity(intent11);
                    break;
                //分享APP
                case R.id.share_main_share_app:
                    showShare();
                    break;
                //广告监听器
                case R.id.ads:
                    switch (ads.getCurrentItem()){
                        case 0:
                            Toast.makeText(context,"广告1",Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(context,"广告2",Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(context,"广告3",Toast.LENGTH_SHORT).show();
                            break;
                    }
                    break;
                //云购文字
                case R.id.cloud_buy_text:

                    break;
                //惠购文字
                case R.id.main_youhui_title:

                    break;
            }
        }
    }

    public void showShare() {



        LOD lod = new LOD(context);
        String shareAdd = API.USER_SHARE+"&uid="+lod.get("USER_INFO","UID","'");
        Handler handler = new Handler();
        BackgroundTask backgroundTask = new BackgroundTask(context,shareAdd,handler);
        backgroundTask.doInBackground();


//        String appID = "wxb4be16d381b98898";
//        String appSecret = "d4624c36b6795d1d99dcf0547af5443d";
        String appID = "wxc132cfd42b28010a";
        String appSecret = "33f0d3845904d17f056b77c0af8e5029";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(getActivity(),appID,appSecret);
        wxHandler.addToSocialSDK();
        // 添加微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(getActivity(),appID,appSecret);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**使用SSO授权必须添加如下代码 */
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode) ;
        if(ssoHandler != null){
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    TimerTask task = new TimerTask(){
        public void run() {
            Message message = new Message();
            message.what = 1;
            adHandler.sendMessage(message);
        }
    };

    private class AdHandler extends Handler{
        public void handleMessage(Message msg)
        {
            int count = pagerAdapter.getCount();
            if (count > 2) { // 实际上，多于1个，就多于3个
                int index = ads.getCurrentItem();
                index = index + 1; //这里修改过
                if (index >= count)
                    index = 0;
                ads.setCurrentItem(index, true);
            }
        }
    }

    private class Advertisement extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        JD jd = new JD(context);
                        HashMap<String ,Object> data = new HashMap<>();
                        try {
                            data = jd.decode(bundle.getString("result"), API.ADVERTISEMENT);
                            List<HashMap<String ,Object>> advertisementList = (List<HashMap<String, Object>>) data.get("rows");
                            List<View> viewList = new ArrayList<>();
                            for (int i = 0 ; i< advertisementList.size() ;i++){
                                View view = LayoutInflater.from(context).inflate(R.layout.fragment_advertisement,null);
                                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.image_title);
                                simpleDraweeView.setImageURI(Uri.parse(API.ADD+advertisementList.get(i).get("content")));
                                viewList.add(view);
                            }
                            pagerAdapter = new PagerAdapter(context , ads , viewList);
                            ads.setAdapter(pagerAdapter);

                            if (pagerAdapter.getCount() > 0)
                            {
                                timer = new Timer(true);
                                timer.schedule(task,1000, 5000);
                                adHandler = new AdHandler();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                    break;
                case BackgroundTask.FAILURE:{

                }
                    break;
            }
        }
    }

    private class CloudWillGo extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            if (jsonObject.getBoolean("flag")){
                                JSONObject data = jsonObject.getJSONObject("data");
                                name.setText(data.getString("name"));
//                                image.setImageURI(Uri.parse(API.ADD + data.getString("img")));
                                Glide.with(context).load(API.ADD + data.getString("img")).into(image);
                                total.setText(data.getString("totalnum"));
                                current.setText(data.getString("curnum"));
                                remain.setText(data.getString("remain"));
                                progress.setMax(Integer.parseInt(data.getString("totalnum")));
                                progress.setProgress(Integer.parseInt(data.getString("curnum")));
                            }else {
                                Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                    break;
                case BackgroundTask.FAILURE:{
                    Toast.makeText(context, "数据加载失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
                    break;
            }
        }
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cloud_buy_text:{
                    Message message = new Message();
                    message.what = FragmentMainActivity.CLOUD;
                    trHandler.sendMessage(message);
                }
                    break;
                case R.id.main_youhui_title:{
                    Message message = new Message();
                    message.what = FragmentMainActivity.HUI;
                    trHandler.sendMessage(message);
                }
                    break;
            }
        }
    }
}
