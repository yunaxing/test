package com.qkxmall.mall.views.cloud;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.views.GridViewWithScrollView;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.util.DateUtil;
import com.qkxmall.mall.util.Sha1;
import com.qkxmall.mall.views.cloud.detail.CloudDetailActivity;
import com.qkxmall.mall.views.fragment.adapter.CloudHotAdapter;
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

public class AllCloudClassItem extends AppCompatActivity {

//    String keyword = "";
//    String currentAddress = "";
//    int oldPage = 1;
//    int currentPage = 1;
//    int defaultRows = 16;
//    int defaultPage = 1;
//
//    String oldSort = "";
//    String currentSort = "";
//    String sortType = "";
//
//    private SimpleDraweeView image = null;
//    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
//    private PullToRefreshScrollView pullScroll = null;
//
//    String loadMoreAddress = "";
//
//    private ImageView backup = null;
//    private ImageView share = null;
//    private TextView title = null;
//    private TextView sort0 = null;
//    private TextView sort1 = null;
//    private TextView sort2 = null;
//    private TextView sort3 = null;
//    private TextView sort4 = null;
//    private GridViewWithScrollView grid = null;
//    Context context;
//
//    List<HashMap<String ,Object>> dataList = new ArrayList<>();
//    CloudHotAdapter cloudHotAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_all_cloud_class_item);
//        init();
//
//        pullScroll.setPullToRefreshOverScrollEnabled(true);
//        pullScroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
//            @Override
//            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                System.out.println("!!!!!!!!!!!!!!!!!!11 下拉刷新");
//                currentPage = 1;
//                if (getIntent().getStringExtra("keyword") != null)
//                    keyword = getIntent().getStringExtra("keyword");
//                context = AllCloudClassItem.this;
//                ProgressDialog progressDialog = new ProgressDialog(context);
//                progressDialog.setMessage("");
//                progressDialog.show();
//                sortType = "&sort=c_num&order=desc";
//                if (getIntent().getStringExtra("category_id").equals("0")){
//                    if (getIntent().getStringExtra("categoryTitle").equals("即将揭晓")){
//                        LoadData loadData = new LoadData(progressDialog);
//                        String address = API.JJJX_LIST_URL+"&page="+defaultPage+"&rows="+defaultRows;
//                        currentAddress = address;
//                        loadMoreAddress = currentAddress;
//                        Log.e("CurrentAddress",currentAddress);
//                        BackgroundTask backgroundTask = new BackgroundTask(context,/* address + "&type=1"*/currentAddress, loadData);
//                        backgroundTask.doInBackground();
//                    }else if (getIntent().getStringExtra("categoryTitle").equals("精品推荐")){
//                        LoadData loadData = new LoadData(progressDialog);
//                        String address = API.JPTJ_LIST_URL+"&page="+defaultPage+"&rows="+defaultRows;
//                        currentAddress = address + "&type=recommend";
//                        Log.e("CurrentAddress",currentAddress);
//                        loadMoreAddress = currentAddress;
//                        BackgroundTask backgroundTask = new BackgroundTask(context, currentAddress, loadData);
//                        backgroundTask.doInBackground();
//                    }
//                }else {
//
//                    //如果是广告页面跳转过来的
//                    //云购广告
//                    if (getIntent().getStringExtra("item_type") != null && getIntent().getStringExtra("item_type").equals("ad"))
//                    {
//                        LoadData loadData = new LoadData(progressDialog);
//                        String address;
//
//                        address = API.CLOUD_GET_JPTJ_ALL_LIST_URL + "&advtype=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
//                        currentAddress = address;
//                        Log.e("CurrentAddress",currentAddress);
//                        loadMoreAddress = currentAddress;
//                        BackgroundTask backgroundTask = new BackgroundTask(context, address +sortType, loadData);
//                        backgroundTask.doInBackground();
//                    }
//                    //惠购广告
//                    if (getIntent().getStringExtra("item_type") != null && getIntent().getStringExtra("item_type").equals("hg_ad"))
//                    {
//                        LoadData loadData = new LoadData(progressDialog);
//                        String address;
//
//                        address = API.HUI_PRODUCTS_LIST + "&category_id=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
//                        currentAddress = address;
//                        Log.e("CurrentAddress",currentAddress);
//                        loadMoreAddress = currentAddress;
//                        BackgroundTask backgroundTask = new BackgroundTask(context, address, loadData);
//                        backgroundTask.doInBackground();
//                    }
//                    else
//                    {
//                        LoadData loadData = new LoadData(progressDialog);
//                        String address;
//                        if (keyword.equals("")) {
//                            address = API.CLOUD_GET_ALL_LIST_URL + "&type=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
//                        }else {
//                            address = API.CLOUD_GET_ALL_LIST_URL + "&page=" + defaultPage + "&rows=" + defaultRows +"&kw="+keyword;
//                        }
//                        currentAddress = address;
//                        Log.e("CurrentAddress",currentAddress);
//                        loadMoreAddress = currentAddress;
//                        BackgroundTask backgroundTask = new BackgroundTask(context, address +sortType, loadData);
//                        backgroundTask.doInBackground();
//                    }
//
//                }
//                currentSort = sortType;
//                Log.e("currentSort",currentSort);
//            }
//
//            @Override
//            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//
//                System.out.println("!!!!!!!!!!!!!!!!!!11  上拉拉刷新");
//                int oldPage = currentPage;
//                currentPage+=1;
//                currentAddress = currentAddress.replace("page="+oldPage,"page="+currentPage);
//                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!11  上拉拉刷新"+currentAddress);
//                String url="http://www.qkxmall.com/?m=api&c=cloud&a=getalllist&type=1&page="+currentPage;
//                LoadMore loadMore = new LoadMore();
//                BackgroundTask backgroundTask = new BackgroundTask(context,url,loadMore);
//                backgroundTask.doInBackground();
//            }
//        });
//
//        if (getIntent().getStringExtra("keyword") != null)
//            keyword = getIntent().getStringExtra("keyword");
//        context = AllCloudClassItem.this;
//        ProgressDialog progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("");
//        progressDialog.show();
//        sortType = "&sort=c_num&order=desc";
//        if (getIntent().getStringExtra("category_id").equals("0")){
//            if (getIntent().getStringExtra("categoryTitle").equals("即将揭晓")){
//                LoadData loadData = new LoadData(progressDialog);
//                String address = API.JJJX_LIST_URL+"&page="+defaultPage+"&rows="+defaultRows;
//                currentAddress = address;
//                Log.e("CurrentAddress",currentAddress);
//                BackgroundTask backgroundTask = new BackgroundTask(context,/* address + "&type=1"*/currentAddress, loadData);
//                backgroundTask.doInBackground();
//            }else if (getIntent().getStringExtra("categoryTitle").equals("精品推荐")){
//                LoadData loadData = new LoadData(progressDialog);
//                String address = API.JPTJ_LIST_URL+"&page="+defaultPage+"&rows="+defaultRows;
//                currentAddress = address + "&type=recommend";
//                Log.e("CurrentAddress",currentAddress);
//                BackgroundTask backgroundTask = new BackgroundTask(context, currentAddress, loadData);
//                backgroundTask.doInBackground();
//            }
//        }else {
//
//            //如果是广告页面跳转过来的
//            //云购广告
//            if (getIntent().getStringExtra("item_type") != null && getIntent().getStringExtra("item_type").equals("ad")) {
//                LoadData loadData = new LoadData(progressDialog);
//                String address;
//
//                address = API.CLOUD_GET_JPTJ_ALL_LIST_URL + "&advtype=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
//                currentAddress = address;
//                Log.e("CurrentAddress",currentAddress);
//                BackgroundTask backgroundTask = new BackgroundTask(context, address +sortType, loadData);
//                backgroundTask.doInBackground();
//            }else
//            //惠购广告
//            if (getIntent().getStringExtra("item_type") != null && getIntent().getStringExtra("item_type").equals("hg_ad")) {
//                LoadData loadData = new LoadData(progressDialog);
//                String address;
//
//                address = API.HUI_PRODUCTS_LIST + "&category_id=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
//                currentAddress = address;
//                Log.e("CurrentAddress",currentAddress);
//                BackgroundTask backgroundTask = new BackgroundTask(context, address, loadData);
//                backgroundTask.doInBackground();
//            }else {
//                LoadData loadData = new LoadData(progressDialog);
//                String address;
//                if (keyword.equals("")) {
//                    address = API.CLOUD_GET_ALL_LIST_URL + "&type=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
//                }else {
//                    address = API.CLOUD_GET_ALL_LIST_URL + "&page=" + defaultPage + "&rows=" + defaultRows +"&kw="+keyword;
//                }
//                currentAddress = address;
//                Log.e("CurrentAddress",currentAddress);
//                BackgroundTask backgroundTask = new BackgroundTask(context, address +sortType, loadData);
//                backgroundTask.doInBackground();
//            }
//
//        }
//        currentSort = sortType;
//        Log.e("currentSort",currentSort);
//
//
//        title.setText(getIntent().getStringExtra("categoryTitle"));
//        sort0.setTextColor(getResources().getColor(R.color.main_line_blue_color));
//        sort1.setTextColor(getResources().getColor(R.color.white));
//        sort2.setTextColor(getResources().getColor(R.color.white));
//        sort3.setTextColor(getResources().getColor(R.color.white));
//        sort4.setTextColor(getResources().getColor(R.color.white));
//        sort0.setBackgroundResource(R.color.white);
//        sort1.setBackgroundResource(R.color.main_line_blue_color);
//        sort2.setBackgroundResource(R.color.main_line_blue_color);
//        sort3.setBackgroundResource(R.color.main_line_blue_color);
//        sort4.setBackgroundResource(R.color.main_line_blue_color);
//
//        backup.setOnClickListener(new ONClickListeners());
//        sort0.setOnClickListener(new ONClickListeners());
//        sort1.setOnClickListener(new ONClickListeners());
//        sort2.setOnClickListener(new ONClickListeners());
//        sort3.setOnClickListener(new ONClickListeners());
//        sort4.setOnClickListener(new ONClickListeners());
//
//        share.setOnClickListener(new ONClickListeners());
//    }
//
//    private void init() {
//        backup = (ImageView) findViewById(R.id.cloud_class_cloud_backup);
//        share = (ImageView) findViewById(R.id.cloud_class_cloud_share);
//        title = (TextView) findViewById(R.id.title);
//        sort0 = (TextView) findViewById(R.id.sort_0);
//        sort1 = (TextView) findViewById(R.id.sort_1);
//        sort2 = (TextView) findViewById(R.id.sort_2);
//        sort3 = (TextView) findViewById(R.id.sort_3);
//        sort4 = (TextView) findViewById(R.id.sort_4);
//        grid = (GridViewWithScrollView) findViewById(R.id.hui_class_grid);
//        image = (SimpleDraweeView) findViewById(R.id.image);
//
//        pullScroll = (PullToRefreshScrollView) findViewById(R.id.sc);
//
//    }
//
//    private class ONClickListeners implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()){
//                case R.id.cloud_class_cloud_backup:
//                    finish();
//                    break;
//                case R.id.cloud_class_cloud_share:{
//                    showShare();
//                }
//                    break;
//                //即将揭晓
//                case R.id.sort_0: {
//                    String temp = currentSort;/*
//                    sortType = "&sort=c_num&order=desc";
//                    currentSort = sortType;*/
//                    currentSort = "";
//                    //currentAddress = currentAddress.replace(temp, currentSort);
//                    ProgressDialog progressDialog = new ProgressDialog(context);
//                    progressDialog.setMessage("数据加载中...");
//                    progressDialog.show();
//                    LoadData loadData = new LoadData(progressDialog);
//                    BackgroundTask backgroundTask = new BackgroundTask(context, "http://www.qkxmall.com/?m=api&c=cloud&a=getalllist&type=1&page=1&rows=16", loadData);
//                    System.out.println("!!!!!!!!!!!!!!!!!!! 即将揭晓：" + currentAddress);
//                    backgroundTask.doInBackground();
//                    loadMoreAddress = currentAddress;
//                    Log.e("ClickCurrentAddress000", currentAddress);
//                    Log.e("currentSort",currentSort);
//                    sort0.setTextColor(getResources().getColor(R.color.main_line_blue_color));
//                    sort1.setTextColor(getResources().getColor(R.color.white));
//                    sort2.setTextColor(getResources().getColor(R.color.white));
//                    sort3.setTextColor(getResources().getColor(R.color.white));
//                    sort4.setTextColor(getResources().getColor(R.color.white));
//                    sort0.setBackgroundResource(R.color.white);
//                    sort1.setBackgroundResource(R.color.main_line_blue_color);
//                    sort2.setBackgroundResource(R.color.main_line_blue_color);
//                    sort3.setBackgroundResource(R.color.main_line_blue_color);
//                    sort4.setBackgroundResource(R.color.main_line_blue_color);
//                }
//                    break;
//                //人气
//                case R.id.sort_1: {
//                    String temp = currentSort;/*
//                    sortType = "&sort=c_num&order=asc";
//                    currentSort = sortType;*/
//                    currentSort = "&sort=c_num&order=desc";
//                    //currentAddress = currentAddress.replace(temp, currentSort);
//                    ProgressDialog progressDialog = new ProgressDialog(context);
//                    progressDialog.setMessage("数据加载中...");
//                    progressDialog.show();
//                    LoadData loadData = new LoadData(progressDialog);
//                    BackgroundTask backgroundTask = new BackgroundTask(context, "http://www.qkxmall.com/?m=api&c=cloud&a=getalllist&type=1&page=1&rows=16&sort=c_num&order=desc" , loadData);
//                    System.out.println("!!!!!!!!!!!!!!!!!!! 人气：" + currentAddress + currentSort);
//                    backgroundTask.doInBackground();
//                    loadMoreAddress = currentAddress + currentSort;
//                    Log.e("ClickCurrentAddress111", currentAddress);
//                    Log.e("currentSort",currentSort);
//                    sort0.setTextColor(getResources().getColor(R.color.white));
//                    sort1.setTextColor(getResources().getColor(R.color.main_line_blue_color));
//                    sort2.setTextColor(getResources().getColor(R.color.white));
//                    sort3.setTextColor(getResources().getColor(R.color.white));
//                    sort4.setTextColor(getResources().getColor(R.color.white));
//                    sort0.setBackgroundResource(R.color.main_line_blue_color);
//                    sort1.setBackgroundResource(R.color.white);
//                    sort2.setBackgroundResource(R.color.main_line_blue_color);
//                    sort3.setBackgroundResource(R.color.main_line_blue_color);
//                    sort4.setBackgroundResource(R.color.main_line_blue_color);
//                }
//                    break;
//                //价格
//                case R.id.sort_2: {
//                    String temp = currentSort;/*
//                    sortType = "&sort=c_totalprice&order=desc";
//                    currentSort = sortType;*/
//                    currentSort = "&sort=c_totalprice&order=asc";
//                    //currentAddress = currentAddress.replace(temp, currentSort);
//                    ProgressDialog progressDialog = new ProgressDialog(context);
//                    progressDialog.setMessage("数据加载中...");
//                    progressDialog.show();
//                    LoadData loadData = new LoadData(progressDialog);
//                    BackgroundTask backgroundTask = new BackgroundTask(context, "http://www.qkxmall.com/?m=api&c=cloud&a=getalllist&type=1&page=1&rows=16&sort=c_totalprice&order=asc", loadData);
//                    System.out.println("!!!!!!!!!!!!!!!!!!! 价格：" + currentAddress + currentSort);
//                    backgroundTask.doInBackground();
//                    loadMoreAddress = currentAddress + currentSort;
//                    Log.e("ClickCurrentAddress222", currentAddress);
//                    Log.e("currentSort",currentSort);
//                    sort0.setTextColor(getResources().getColor(R.color.white));
//                    sort1.setTextColor(getResources().getColor(R.color.white));
//                    sort2.setTextColor(getResources().getColor(R.color.main_line_blue_color));
//                    sort3.setTextColor(getResources().getColor(R.color.white));
//                    sort4.setTextColor(getResources().getColor(R.color.white));
//                    sort0.setBackgroundResource(R.color.main_line_blue_color);
//                    sort1.setBackgroundResource(R.color.main_line_blue_color);
//                    sort2.setBackgroundResource(R.color.white);
//                    sort3.setBackgroundResource(R.color.main_line_blue_color);
//                    sort4.setBackgroundResource(R.color.main_line_blue_color);
//                }
//                    break;
//                //最新
//                case R.id.sort_3: {
//                    String temp = currentSort;/*
//                    sortType = "&sort=c_totalprice&order=sdc";
//                    currentSort = sortType;*/
//                    currentSort = "&sort=id&order=desc";
//                    //currentAddress = currentAddress.replace(temp, currentSort);
//                    ProgressDialog progressDialog = new ProgressDialog(context);
//                    progressDialog.setMessage("数据加载中...");
//                    progressDialog.show();
//                    LoadData loadData = new LoadData(progressDialog);
//                    BackgroundTask backgroundTask = new BackgroundTask(context, "http://www.qkxmall.com/?m=api&c=cloud&a=getalllist&type=1&page=1&rows=16&sort=id&order=desc", loadData);
//                    System.out.println("!!!!!!!!!!!!!!!!!!! 价格：" + currentAddress + currentSort);
//                    backgroundTask.doInBackground();
//                    loadMoreAddress = currentAddress + currentSort;
//                    Log.e("ClickCurrentAddress333", currentAddress);
//                    Log.e("currentSort",currentSort);
//                    sort0.setTextColor(getResources().getColor(R.color.white));
//                    sort1.setTextColor(getResources().getColor(R.color.white));
//                    sort2.setTextColor(getResources().getColor(R.color.white));
//                    sort3.setTextColor(getResources().getColor(R.color.main_line_blue_color));
//                    sort4.setTextColor(getResources().getColor(R.color.white));
//                    sort0.setBackgroundResource(R.color.main_line_blue_color);
//                    sort1.setBackgroundResource(R.color.main_line_blue_color);
//                    sort2.setBackgroundResource(R.color.main_line_blue_color);
//                    sort3.setBackgroundResource(R.color.white);
//                    sort4.setBackgroundResource(R.color.main_line_blue_color);
//                }
//                    break;
//                //评论
//                case R.id.sort_4: {
//                    String tenp = currentSort;/*
//                    sortType = "&commnum=c_num&order=desc";
//                    currentSort = sortType;*/
//                    currentSort = "&sort=commnum&order=desc";
//                    //currentAddress = currentAddress.replace(tenp, currentSort);
//                    ProgressDialog progressDialog = new ProgressDialog(context);
//                    progressDialog.setMessage("数据加载中...");
//                    progressDialog.show();
//                    LoadData loadData = new LoadData(progressDialog);
//                    BackgroundTask backgroundTask = new BackgroundTask(context, "http://www.qkxmall.com/?m=api&c=cloud&a=getalllist&type=1&page=1&rows=16&sort=commnum&order=desc", loadData);
//                    System.out.println("!!!!!!!!!!!!!!!!!!! 价格：" + currentAddress + currentSort);
//                    backgroundTask.doInBackground();
//                    loadMoreAddress = currentAddress + currentSort;
//                    Log.e("ClickCurrentAddress444", currentAddress);
//                    Log.e("currentSort",currentSort);
//                    sort0.setTextColor(getResources().getColor(R.color.white));
//                    sort1.setTextColor(getResources().getColor(R.color.white));
//                    sort2.setTextColor(getResources().getColor(R.color.white));
//                    sort3.setTextColor(getResources().getColor(R.color.white));
//                    sort4.setTextColor(getResources().getColor(R.color.main_line_blue_color));
//                    sort0.setBackgroundResource(R.color.main_line_blue_color);
//                    sort1.setBackgroundResource(R.color.main_line_blue_color);
//                    sort2.setBackgroundResource(R.color.main_line_blue_color);
//                    sort3.setBackgroundResource(R.color.main_line_blue_color);
//                    sort4.setBackgroundResource(R.color.white);
//                }
//                    break;
//            }
//        }
//    }
//
//    private class OnItemClickListeners implements android.widget.AdapterView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Intent intent = new Intent(context, CloudDetailActivity.class);
//            intent.putExtra("cid", (String) dataList.get(position).get("cid"));
//            startActivity(intent);
//        }
//    }
//
//    private class LoadData extends Handler{
//        ProgressDialog progressDialog;
//
//        public LoadData(ProgressDialog progressDialog) {
//            this.progressDialog = progressDialog;
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            pullScroll.onRefreshComplete();
//            switch (msg.what){
//                case BackgroundTask.SUCCESS:{
//                    Bundle bundle = msg.getData();
//                    if (bundle != null){
//                        try {
//                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
//                            if (jsonObject.getBoolean("flag")){
//                                if (jsonObject.has("image"))
//                                    image.setImageURI(Uri.parse(API.ADD+jsonObject.getString("image")));
//                                JSONArray array = jsonObject.getJSONArray("data");
//                                dataList.clear();
//                                for (int i = 0 ; i< array.length();i++){
//                                    JSONObject object = array.getJSONObject(i);
//                                    HashMap<String ,Object> map = new HashMap<>();
//                                    map.put("cid", object.get("cid"));
//                                    map.put("name", object.get("name"));
//                                    if (object.has("commnum"))
//                                        map.put("commnum", object.get("commnum"));
//                                    if (object.has("c_totalprice"))
//                                        map.put("c_totalprice", object.get("c_totalprice"));
//                                    if (object.has("c_totalterm"))
//                                        map.put("c_totalterm", object.get("c_totalterm"));
//                                    map.put("goodsname", object.get("goodsname"));
//                                    map.put("goods_id", object.get("goods_id"));
//                                    map.put("totalnum", object.get("totalnum"));
//                                    map.put("curnum", object.get("curnum"));
//                                    if (object.has("remain"))
//                                        map.put("remain", object.get("remain"));
//                                    map.put("img", object.get("img"));
//                                    dataList.add(map);
//                                }
//                                cloudHotAdapter = new CloudHotAdapter(context,dataList);
//                                grid.setAdapter(cloudHotAdapter);
//                                cloudHotAdapter.notifyDataSetChanged();
//                                grid.setOnItemClickListener(new OnItemClickListeners());
//                            }else {
//                                Toast.makeText(context, "获取失败", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    progressDialog.dismiss();
//                }
//                    break;
//                case BackgroundTask.FAILURE:{
//                    progressDialog.dismiss();
//                    Toast.makeText(context, "数据请求失败", Toast.LENGTH_SHORT).show();
//                }
//                    break;
//            }
//        }
//    }
//
//    public void showShare() {
//
//        LOD lod = new LOD(context);
//        String shareAdd = API.USER_SHARE+"&uid="+lod.get("USER_INFO","UID","'");
//        Handler handler = new Handler();
//        BackgroundTask backgroundTask = new BackgroundTask(context,shareAdd,handler);
//        backgroundTask.doInBackground();
//
//        String appID = "wxb4be16d381b98898";
//        String appSecret = "d4624c36b6795d1d99dcf0547af5443d";
//        // 添加微信平台
//        UMWXHandler wxHandler = new UMWXHandler(this,appID,appSecret);
//        wxHandler.addToSocialSDK();
//        // 添加微信朋友圈
//        UMWXHandler wxCircleHandler = new UMWXHandler(this,appID,appSecret);
//        wxCircleHandler.setToCircle(true);
//        wxCircleHandler.addToSocialSDK();
//        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
//        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this, "1104756245",
//                "iZkRcSBIIl6qHAcQ");
//        qqSsoHandler.addToSocialSDK();
//        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
//        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this, "1104756245",
//                "iZkRcSBIIl6qHAcQ");
//        qZoneSsoHandler.addToSocialSDK();
//
//        //设置新浪SSO handler
//        mController.getConfig().setSsoHandler(new SinaSsoHandler());
//        //设置腾讯微博SSO handler
//        mController.getConfig().setSsoHandler(new TencentWBSsoHandler());
//
//        // 设置分享内容
//        mController.setShareContent("穷开心购物商城 专为开心生活量身定制，全场包邮 价格更实惠 用心来做 只为你那开心一刻");
//        // 设置分享图片, 参数2为图片的url地址
//        mController.setShareMedia(new UMImage(context, "http://www.qkxmall.com/xiazai/"));
//        mController.setShareImage(new UMImage(context, BitmapFactory.decodeResource(getResources(),
//                R.drawable.fenxiang)));
//        qqSsoHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
//        qqSsoHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
//        wxCircleHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
//        wxHandler.setTargetUrl("http://www.qkxmall.com/xiazai/");
//
//        mController.setAppWebSite(SHARE_MEDIA.RENREN, "http://www.qkxmall.com/xiazai/");
//        mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);
//        mController.openShare(this, false);
//    }
//
//
//    private class LoadMore extends Handler{
//
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case BackgroundTask.SUCCESS:{
//                    Bundle bundle = msg.getData();
//                    if (bundle != null){
//                        try {
//                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
//                            if (jsonObject.getBoolean("flag")){
//                                if (jsonObject.has("image"))
//                                    image.setImageURI(Uri.parse(API.ADD+jsonObject.getString("image")));
//                                JSONArray array = jsonObject.getJSONArray("data");
//                                List<HashMap<String ,Object>> tempMap = new ArrayList<>();
//                                for (int i = 0 ; i< array.length();i++){
//                                    JSONObject object = array.getJSONObject(i);
//                                    HashMap<String ,Object> map = new HashMap<>();
//                                    map.put("cid", object.get("cid"));
//                                    map.put("name", object.get("name"));
//                                    if (object.has("commnum"))
//                                        map.put("commnum", object.get("commnum"));
//                                    if (object.has("c_totalprice"))
//                                        map.put("c_totalprice", object.get("c_totalprice"));
//                                    if (object.has("c_totalterm"))
//                                        map.put("c_totalterm", object.get("c_totalterm"));
//                                    map.put("goodsname", object.get("goodsname"));
//                                    map.put("goods_id", object.get("goods_id"));
//                                    map.put("totalnum", object.get("totalnum"));
//                                    map.put("curnum", object.get("curnum"));
//                                    if (object.has("remain"))
//                                        map.put("remain", object.get("remain"));
//                                    map.put("img", object.get("img"));
//                                    tempMap.add(map);
//                                }
//                                dataList.addAll(tempMap);
//
//                                cloudHotAdapter.notifyDataSetChanged();
//                                grid.setOnItemClickListener(new OnItemClickListeners());
//                            }else {
//                                Toast.makeText(context, "获取失败", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    pullScroll.onRefreshComplete();
//                }
//                break;
//                case BackgroundTask.FAILURE:{
//                    pullScroll.onRefreshComplete();
//                    Bundle bundle = msg.getData();
//                    Toast.makeText(context,  "数据解析失败", Toast.LENGTH_SHORT).show();
//                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!+ " + bundle.getString("result"));
//                }
//                break;
//            }
//        }
//    }
//
String keyword = "";
    String currentAddress = "";
    int oldPage = 1;
    int currentPage = 1;
    int defaultRows = 16;
    int defaultPage = 1;

    String oldSort = "";
    String currentSort = "";
    String sortType = "";

    private SimpleDraweeView image = null;
    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
    private PullToRefreshScrollView pullScroll = null;

    String loadMoreAddress = "";

    private ImageView backup = null;
    private ImageView share = null;
    private TextView title = null;
    private TextView sort0 = null;
    private TextView sort1 = null;
    private TextView sort2 = null;
    private TextView sort3 = null;
    private TextView sort4 = null;
    private GridViewWithScrollView grid = null;
    Context context;

    List<HashMap<String ,Object>> dataList = new ArrayList<>();
    CloudHotAdapter cloudHotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cloud_class_item);
        init();

        pullScroll.setPullToRefreshOverScrollEnabled(true);
        pullScroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! 下拉刷新");
                currentPage = 1;
                if (getIntent().getStringExtra("keyword") != null)
                    keyword = getIntent().getStringExtra("keyword");
                context = AllCloudClassItem.this;
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("");
                progressDialog.show();
                sortType = "&sort=c_num&order=desc";
                if (getIntent().getStringExtra("category_id").equals("0")){
                    if (getIntent().getStringExtra("categoryTitle").equals("即将揭晓")){
                        LoadData loadData = new LoadData(progressDialog);
                        String address = API.JJJX_THREE_URL+"&access_token="+(new Sha1().getDigestOfString((DateUtil.getCurrentDate()+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());;
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! URL:" + address);
                        currentAddress = address;
                        loadMoreAddress = currentAddress;
                        Log.e("CurrentAddress",currentAddress);
                        BackgroundTask backgroundTask = new BackgroundTask(context,/* address + "&type=1"*/currentAddress, loadData);
                        backgroundTask.doInBackground();
                    }else if (getIntent().getStringExtra("categoryTitle").equals("精品推荐")){
                        LoadData loadData = new LoadData(progressDialog);
                        String address = API.JPTJ_LIST_URL+"&page="+defaultPage+"&rows="+defaultRows;
                        currentAddress = address + "&type=recommend";
                        Log.e("CurrentAddress",currentAddress);
                        loadMoreAddress = currentAddress;
                        BackgroundTask backgroundTask = new BackgroundTask(context, currentAddress, loadData);
                        backgroundTask.doInBackground();
                    }
                }else {

                    //濡傛灉鏄箍鍛婇〉闈㈣烦杞繃鏉ョ殑
                    //浜戣喘骞垮憡
                    if (getIntent().getStringExtra("item_type") != null && getIntent().getStringExtra("item_type").equals("ad"))
                    {
                        LoadData loadData = new LoadData(progressDialog);
                        String address;

                        address = API.CLOUD_GET_JPTJ_ALL_LIST_URL + "&advtype=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
                        currentAddress = address;
                        Log.e("CurrentAddress",currentAddress);
                        loadMoreAddress = currentAddress;
                        BackgroundTask backgroundTask = new BackgroundTask(context, address +sortType, loadData);
                        backgroundTask.doInBackground();
                    }
                    //鎯犺喘骞垮憡
                    if (getIntent().getStringExtra("item_type") != null && getIntent().getStringExtra("item_type").equals("hg_ad"))
                    {
                        LoadData loadData = new LoadData(progressDialog);
                        String address;

                        address = API.HUI_PRODUCTS_LIST + "&category_id=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
                        currentAddress = address;
                        Log.e("CurrentAddress",currentAddress);
                        loadMoreAddress = currentAddress;
                        BackgroundTask backgroundTask = new BackgroundTask(context, address, loadData);
                        backgroundTask.doInBackground();
                    }
                    else
                    {
                        LoadData loadData = new LoadData(progressDialog);
                        String address;
                        if (keyword.equals("")) {
                            address = API.CLOUD_GET_ALL_LIST_URL + "&type=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
                        }else {
                            address = API.CLOUD_GET_ALL_LIST_URL + "&page=" + defaultPage + "&rows=" + defaultRows +"&kw="+keyword;
                        }
                        currentAddress = address;
                        Log.e("CurrentAddress",currentAddress);
                        loadMoreAddress = currentAddress;
                        BackgroundTask backgroundTask = new BackgroundTask(context, address +sortType, loadData);
                        backgroundTask.doInBackground();
                    }

                }
                currentSort = sortType;
                Log.e("currentSort",currentSort);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! 上拉拉刷新");
                int oldPage = currentPage;
                currentPage+=1;
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! oldPage+"+oldPage+"|||||currentPage+"+currentPage);
                currentAddress = currentAddress.replaceAll("page=" + oldPage, "page=" + currentPage);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! 转化前url="+currentAddress);
                String url=currentAddress.replaceAll("page=" + oldPage, "page=" + currentPage);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! 转化后url="+url);
                LoadMore loadMore = new LoadMore();
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! 上拉拉刷新URL "+url);
                BackgroundTask backgroundTask = new BackgroundTask(context,url,loadMore);
                backgroundTask.doInBackground();

            }
        });

        if (getIntent().getStringExtra("keyword") != null)
            keyword = getIntent().getStringExtra("keyword");
        context = AllCloudClassItem.this;
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("");
        progressDialog.show();
        sortType = "&sort=c_num&order=desc";
        if (getIntent().getStringExtra("category_id").equals("0")){
            if (getIntent().getStringExtra("categoryTitle").equals("即将揭晓")){
                LoadData loadData = new LoadData(progressDialog);
                String address = API.JJJX_THREE_URL+"&access_token="+(new Sha1().getDigestOfString((DateUtil.getCurrentDate()+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());
                currentAddress = address;
                Log.e("CurrentAddress",currentAddress);
                BackgroundTask backgroundTask = new BackgroundTask(context,/* address + "&type=1"*/currentAddress, loadData);
                backgroundTask.doInBackground();
            }else if (getIntent().getStringExtra("categoryTitle").equals("精品推荐")){
                LoadData loadData = new LoadData(progressDialog);
                String address = API.JPTJ_LIST_URL+"&page="+defaultPage+"&rows="+defaultRows;
                currentAddress = address + "&type=recommend";
                Log.e("CurrentAddress",currentAddress);
                BackgroundTask backgroundTask = new BackgroundTask(context, currentAddress, loadData);
                backgroundTask.doInBackground();
            }
        }else {

            //濡傛灉鏄箍鍛婇〉闈㈣烦杞繃鏉ョ殑
            //浜戣喘骞垮憡
            if (getIntent().getStringExtra("item_type") != null && getIntent().getStringExtra("item_type").equals("ad")) {
                LoadData loadData = new LoadData(progressDialog);
                String address;

                address = API.CLOUD_GET_JPTJ_ALL_LIST_URL + "&advtype=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
                currentAddress = address;
                Log.e("CurrentAddress",currentAddress);
                BackgroundTask backgroundTask = new BackgroundTask(context, address +sortType, loadData);
                backgroundTask.doInBackground();
            }else
                //鎯犺喘骞垮憡
                if (getIntent().getStringExtra("item_type") != null && getIntent().getStringExtra("item_type").equals("hg_ad")) {
                    LoadData loadData = new LoadData(progressDialog);
                    String address;

                    address = API.HUI_PRODUCTS_LIST + "&category_id=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
                    currentAddress = address;
                    Log.e("CurrentAddress",currentAddress);
                    BackgroundTask backgroundTask = new BackgroundTask(context, address, loadData);
                    backgroundTask.doInBackground();
                }else {
                    LoadData loadData = new LoadData(progressDialog);
                    String address;
                    if (keyword.equals("")) {
                        address = API.CLOUD_GET_ALL_LIST_URL + "&type=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
                    }else {
                        address = API.CLOUD_GET_ALL_LIST_URL + "&page=" + defaultPage + "&rows=" + defaultRows +"&kw="+keyword;
                    }
                    currentAddress = address;
                    Log.e("CurrentAddress",currentAddress);
                    BackgroundTask backgroundTask = new BackgroundTask(context, address +sortType, loadData);
                    backgroundTask.doInBackground();
                }

        }
        currentSort = sortType;
        Log.e("currentSort",currentSort);


        title.setText(getIntent().getStringExtra("categoryTitle"));
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

        backup.setOnClickListener(new ONClickListeners());
        sort0.setOnClickListener(new ONClickListeners());
        sort1.setOnClickListener(new ONClickListeners());
        sort2.setOnClickListener(new ONClickListeners());
        sort3.setOnClickListener(new ONClickListeners());
        sort4.setOnClickListener(new ONClickListeners());

        share.setOnClickListener(new ONClickListeners());
    }

    public void initData(){

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! 下拉刷新");
        currentPage = 1;
        if (getIntent().getStringExtra("keyword") != null)
            keyword = getIntent().getStringExtra("keyword");
        context = AllCloudClassItem.this;
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("");
        progressDialog.show();
        sortType = "&sort=c_num&order=desc";
        if (getIntent().getStringExtra("category_id").equals("0")){
            if (getIntent().getStringExtra("categoryTitle").equals("即将揭晓")){
                LoadData loadData = new LoadData(progressDialog);
                String address = API.JJJX_LIST_URL+"&page="+defaultPage+"&rows="+defaultRows;
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! URL:" + address);
                currentAddress = address;
                loadMoreAddress = currentAddress;
                Log.e("CurrentAddress",currentAddress);
                BackgroundTask backgroundTask = new BackgroundTask(context,/* address + "&type=1"*/currentAddress, loadData);
                backgroundTask.doInBackground();
            }else if (getIntent().getStringExtra("categoryTitle").equals("精品推荐")){
                LoadData loadData = new LoadData(progressDialog);
                String address = API.JPTJ_LIST_URL+"&page="+defaultPage+"&rows="+defaultRows;
                currentAddress = address + "&type=recommend";
                Log.e("CurrentAddress",currentAddress);
                loadMoreAddress = currentAddress;
                BackgroundTask backgroundTask = new BackgroundTask(context, currentAddress, loadData);
                backgroundTask.doInBackground();
            }
        }else {

            //濡傛灉鏄箍鍛婇〉闈㈣烦杞繃鏉ョ殑
            //浜戣喘骞垮憡
            if (getIntent().getStringExtra("item_type") != null && getIntent().getStringExtra("item_type").equals("ad"))
            {
                LoadData loadData = new LoadData(progressDialog);
                String address;

                address = API.CLOUD_GET_JPTJ_ALL_LIST_URL + "&advtype=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
                currentAddress = address;
                Log.e("CurrentAddress",currentAddress);
                loadMoreAddress = currentAddress;
                BackgroundTask backgroundTask = new BackgroundTask(context, address +sortType, loadData);
                backgroundTask.doInBackground();
            }
            //鎯犺喘骞垮憡
            if (getIntent().getStringExtra("item_type") != null && getIntent().getStringExtra("item_type").equals("hg_ad"))
            {
                LoadData loadData = new LoadData(progressDialog);
                String address;

                address = API.HUI_PRODUCTS_LIST + "&category_id=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
                currentAddress = address;
                Log.e("CurrentAddress",currentAddress);
                loadMoreAddress = currentAddress;
                BackgroundTask backgroundTask = new BackgroundTask(context, address, loadData);
                backgroundTask.doInBackground();
            }
            else
            {
                LoadData loadData = new LoadData(progressDialog);
                String address;
                if (keyword.equals("")) {
                    address = API.CLOUD_GET_ALL_LIST_URL + "&type=" + getIntent().getStringExtra("category_id") + "&page=" + defaultPage + "&rows=" + defaultRows;
                }else {
                    address = API.CLOUD_GET_ALL_LIST_URL + "&page=" + defaultPage + "&rows=" + defaultRows +"&kw="+keyword;
                }
                currentAddress = address;
                Log.e("CurrentAddress",currentAddress);
                loadMoreAddress = currentAddress;
                BackgroundTask backgroundTask = new BackgroundTask(context, address +sortType, loadData);
                backgroundTask.doInBackground();
            }

        }
        currentSort = sortType;
        Log.e("currentSort",currentSort);
    }
    private void init() {
        backup = (ImageView) findViewById(R.id.cloud_class_cloud_backup);
        share = (ImageView) findViewById(R.id.cloud_class_cloud_share);
        title = (TextView) findViewById(R.id.title);
        sort0 = (TextView) findViewById(R.id.sort_0);
        sort1 = (TextView) findViewById(R.id.sort_1);
        sort2 = (TextView) findViewById(R.id.sort_2);
        sort3 = (TextView) findViewById(R.id.sort_3);
        sort4 = (TextView) findViewById(R.id.sort_4);
        grid = (GridViewWithScrollView) findViewById(R.id.hui_class_grid);
        image = (SimpleDraweeView) findViewById(R.id.image_title);

        pullScroll = (PullToRefreshScrollView) findViewById(R.id.sc);

    }

    private class ONClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cloud_class_cloud_backup:
                    finish();
                    break;
                case R.id.cloud_class_cloud_share:{
                    showShare();
                }
                break;
                //鍗冲皢鎻檽
                case R.id.sort_0: {
                    initData();
                    String temp = currentSort;/*
                    sortType = "&sort=c_num&order=desc";
                    currentSort = sortType;*/
                    currentSort = "";
                    //currentAddress = currentAddress.replace(temp, currentSort);
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("数据加载中...");
                    progressDialog.show();
                    LoadData loadData = new LoadData(progressDialog);
                    System.out.println("!!!!!!!!!!!!!!!!!!!   URL :" + currentAddress);
                    BackgroundTask backgroundTask = new BackgroundTask(context, currentAddress, loadData);
                    backgroundTask.doInBackground();
                    loadMoreAddress = currentAddress;
                    Log.e("ClickCurrentAddress000", currentAddress);
                    Log.e("currentSort",currentSort);
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
                }
                break;
                //浜烘皵
                case R.id.sort_1: {
                    initData();
                    String temp = currentSort;/*
                    sortType = "&sort=c_num&order=asc";
                    currentSort = sortType;*/
                    currentSort = "&sort=c_num&order=desc";
                    //currentAddress = currentAddress.replace(temp, currentSort);
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("数据加载中...");
                    progressDialog.show();
                    LoadData loadData = new LoadData(progressDialog);
                    BackgroundTask backgroundTask = new BackgroundTask(context, currentAddress + currentSort , loadData);
                    backgroundTask.doInBackground();
                    loadMoreAddress = currentAddress + currentSort;
                    Log.e("ClickCurrentAddress111", currentAddress);
                    Log.e("currentSort",currentSort);
                    sort0.setTextColor(getResources().getColor(R.color.white));
                    sort1.setTextColor(getResources().getColor(R.color.main_line_blue_color));
                    sort2.setTextColor(getResources().getColor(R.color.white));
                    sort3.setTextColor(getResources().getColor(R.color.white));
                    sort4.setTextColor(getResources().getColor(R.color.white));
                    sort0.setBackgroundResource(R.color.main_line_blue_color);
                    sort1.setBackgroundResource(R.color.white);
                    sort2.setBackgroundResource(R.color.main_line_blue_color);
                    sort3.setBackgroundResource(R.color.main_line_blue_color);
                    sort4.setBackgroundResource(R.color.main_line_blue_color);
                }
                break;
                //浠锋牸
                case R.id.sort_2: {
                    initData();
                    String temp = currentSort;/*
                    sortType = "&sort=c_totalprice&order=desc";
                    currentSort = sortType;*/
                    currentSort = "&sort=c_totalprice&order=asc"+"&access_token="+(new Sha1().getDigestOfString((DateUtil.getCurrentDate()+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());
                    //currentAddress = currentAddress.replace(temp, currentSort);
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("数据加载中...");
                    progressDialog.show();
                    LoadData loadData = new LoadData(progressDialog);
                    BackgroundTask backgroundTask = new BackgroundTask(context, API.JJJX_THREE_URL + currentSort, loadData);
                    backgroundTask.doInBackground();
                    loadMoreAddress = currentAddress + currentSort;
                    Log.e("ClickCurrentAddress222", currentAddress);
                    Log.e("currentSort",currentSort);
                    sort0.setTextColor(getResources().getColor(R.color.white));
                    sort1.setTextColor(getResources().getColor(R.color.white));
                    sort2.setTextColor(getResources().getColor(R.color.main_line_blue_color));
                    sort3.setTextColor(getResources().getColor(R.color.white));
                    sort4.setTextColor(getResources().getColor(R.color.white));
                    sort0.setBackgroundResource(R.color.main_line_blue_color);
                    sort1.setBackgroundResource(R.color.main_line_blue_color);
                    sort2.setBackgroundResource(R.color.white);
                    sort3.setBackgroundResource(R.color.main_line_blue_color);
                    sort4.setBackgroundResource(R.color.main_line_blue_color);
                }
                break;
                //鏈€鏂?
                case R.id.sort_3: {
                    initData();
                    String temp = currentSort;/*
                    sortType = "&sort=c_totalprice&order=sdc";
                    currentSort = sortType;*/
                    currentSort = "&sort=id&order=desc";
                    //currentAddress = currentAddress.replace(temp, currentSort);
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("数据加载中...");
                    progressDialog.show();
                    LoadData loadData = new LoadData(progressDialog);
                    BackgroundTask backgroundTask = new BackgroundTask(context, currentAddress + currentSort, loadData);
                    backgroundTask.doInBackground();
                    loadMoreAddress = currentAddress + currentSort;
                    Log.e("ClickCurrentAddress333", currentAddress);
                    Log.e("currentSort",currentSort);
                    sort0.setTextColor(getResources().getColor(R.color.white));
                    sort1.setTextColor(getResources().getColor(R.color.white));
                    sort2.setTextColor(getResources().getColor(R.color.white));
                    sort3.setTextColor(getResources().getColor(R.color.main_line_blue_color));
                    sort4.setTextColor(getResources().getColor(R.color.white));
                    sort0.setBackgroundResource(R.color.main_line_blue_color);
                    sort1.setBackgroundResource(R.color.main_line_blue_color);
                    sort2.setBackgroundResource(R.color.main_line_blue_color);
                    sort3.setBackgroundResource(R.color.white);
                    sort4.setBackgroundResource(R.color.main_line_blue_color);
                }
                break;
                //璇勮
                case R.id.sort_4: {
                    initData();
                    String tenp = currentSort;/*
                    sortType = "&commnum=c_num&order=desc";
                    currentSort = sortType;*/
                    currentSort = "&sort=commnum&order=desc";
                    //currentAddress = currentAddress.replace(tenp, currentSort);
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("数据加载中...");
                    progressDialog.show();
                    LoadData loadData = new LoadData(progressDialog);
                    BackgroundTask backgroundTask = new BackgroundTask(context, currentAddress + currentSort, loadData);
                    backgroundTask.doInBackground();
                    loadMoreAddress = currentAddress + currentSort;
                    Log.e("ClickCurrentAddress444", currentAddress);
                    Log.e("currentSort",currentSort);
                    sort0.setTextColor(getResources().getColor(R.color.white));
                    sort1.setTextColor(getResources().getColor(R.color.white));
                    sort2.setTextColor(getResources().getColor(R.color.white));
                    sort3.setTextColor(getResources().getColor(R.color.white));
                    sort4.setTextColor(getResources().getColor(R.color.main_line_blue_color));
                    sort0.setBackgroundResource(R.color.main_line_blue_color);
                    sort1.setBackgroundResource(R.color.main_line_blue_color);
                    sort2.setBackgroundResource(R.color.main_line_blue_color);
                    sort3.setBackgroundResource(R.color.main_line_blue_color);
                    sort4.setBackgroundResource(R.color.white);
                }
                break;
            }
        }
    }

    private class OnItemClickListeners implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(context, CloudDetailActivity.class);
            intent.putExtra("cid", (String) dataList.get(position).get("cid"));
            startActivity(intent);
        }
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
//                                    map.put("cid", object.get("cid"));
                                    map.put("name", object.get("goods_name"));
                                    if (object.has("commnum"))
                                        map.put("commnum", object.get("commnum"));
                                    if (object.has("totalprice"))
                                        map.put("c_totalprice", object.get("totalprice"));
                                    if (object.has("totalterm"))
                                        map.put("c_totalterm", object.get("totalterm"));
                                    map.put("goodsname", object.get("goods_name"));
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
                                grid.setOnItemClickListener(new OnItemClickListeners());
                            }else {
                                Toast.makeText(context, "获取失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
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
        // 娣诲姞寰俊骞冲彴
        UMWXHandler wxHandler = new UMWXHandler(this,appID,appSecret);
        wxHandler.addToSocialSDK();
        // 娣诲姞寰俊鏈嬪弸鍦?
        UMWXHandler wxCircleHandler = new UMWXHandler(this,appID,appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
        //鍙傛暟1涓哄綋鍓岮ctivity锛屽弬鏁?涓哄紑鍙戣€呭湪QQ浜掕仈鐢宠鐨凙PP ID锛屽弬鏁?涓哄紑鍙戣€呭湪QQ浜掕仈鐢宠鐨凙PP kEY.
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this, "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qqSsoHandler.addToSocialSDK();
        //鍙傛暟1涓哄綋鍓岮ctivity锛屽弬鏁?涓哄紑鍙戣€呭湪QQ浜掕仈鐢宠鐨凙PP ID锛屽弬鏁?涓哄紑鍙戣€呭湪QQ浜掕仈鐢宠鐨凙PP kEY.
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this, "1104756245",
                "iZkRcSBIIl6qHAcQ");
        qZoneSsoHandler.addToSocialSDK();

        //璁剧疆鏂版氮SSO handler
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
        //璁剧疆鑵捐寰崥SSO handler
        mController.getConfig().setSsoHandler(new TencentWBSsoHandler());

        // 璁剧疆鍒嗕韩鍐呭
        mController.setShareContent("穷开心购物商城 专为开心生活量身定制，全场包邮 价格更实惠 用心来做 只为你那开心一刻");
                // 璁剧疆鍒嗕韩鍥剧墖, 鍙傛暟2涓哄浘鐗囩殑url鍦板潃
                mController.setShareMedia(new UMImage(context, "http://www.qkxmall.com/xiazai/"));
        mController.setAppWebSite(SHARE_MEDIA.RENREN, "http://www.qkxmall.com/xiazai/");
        mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);
        mController.openShare(this, false);
    }


    private class LoadMore extends Handler{

        @Override
        public void handleMessage(Message msg) {
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
                                List<HashMap<String ,Object>> tempMap = new ArrayList<>();
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
                                    tempMap.add(map);
                                }
                                dataList.addAll(tempMap);
                                System.out.println("!!!!!!!!!!!!!!!!! 数据源长度：" + dataList.size());

                                cloudHotAdapter.notifyDataSetChanged();
                                grid.setOnItemClickListener(new OnItemClickListeners());
                            }else {
                                Toast.makeText(context, "获取失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    pullScroll.onRefreshComplete();
                }
                break;
                case BackgroundTask.FAILURE:{
                    pullScroll.onRefreshComplete();
                    Toast.makeText(context, "数据解析失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


}
