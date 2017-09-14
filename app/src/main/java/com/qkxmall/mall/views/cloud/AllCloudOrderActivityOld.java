package com.qkxmall.mall.views.cloud;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.adapter.AdsViewPagerAdapter;
import com.qkxmall.mall.define.adapter.Cloudorder.CloudBuyAllOrder2;
import com.qkxmall.mall.define.adapter.Cloudorder.EvaluateViewListAdapter;
import com.qkxmall.mall.define.adapter.Cloudorder.PistachioNutsListAdapter;
import com.qkxmall.mall.define.adapter.Cloudorder.WaitToAnnounceViewListAdapter;
import com.qkxmall.mall.define.adapter.Cloudorder.WaitToReceivingViewListAdapter;
import com.qkxmall.mall.define.adapter.Cloudorder.WillBeAnnouncedViewListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllCloudOrderActivityOld extends Activity {
    private ViewPager viewPager = null;
    private ImageView backup = null;
    private TextView allOrder = null;
    private TextView waitToAnnounce = null;
    private TextView pistachioNuts = null;
    private TextView waitToReceiving = null;
    private TextView evaluate = null;

    List<View> list = new ArrayList<>();
    View allOrderView = null;
    View waitToAnnounceView = null;
    View pistachioNutsView = null;
    View waitToReceivingView = null;
    View evaluateView = null;

    int pageID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cloud_order);
        init();

        pageID = getIntent().getIntExtra("cloudOrderPagerID",0);
        //Pager pistachio nuts
        pistachioNutsView = LayoutInflater.from(this).inflate(R.layout.all_cloud_order_pistachio_nuts_layout,null);
        ListView listViewPagerPistachioNutsView = (ListView) pistachioNutsView.findViewById(R.id.all_cloud_order_pistachio_nuts_list_view);
        List list1 = new ArrayList();
        for (int i = 0 ; i<30 ; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("1",((BitmapDrawable)getResources().getDrawable(R.drawable.icon_smart_cuff)).getBitmap());
            map.put("2","商品名称"+i);
            map.put("3","6"+i);
            map.put("4","99"+i);
            map.put("5","1000021"+i);
            map.put("6","2015.06.08 12:23:35.21"+i);
            list1.add(map);
        }
        //Pager evaluate
        evaluateView = LayoutInflater.from(this).inflate(R.layout.all_cloud_order_evaluate_layout,null);
        ListView listViewEvaluateView = (ListView) evaluateView.findViewById(R.id.all_cloud_order_evaluate_list_view);
        List list2 = new ArrayList();
        for (int i = 0 ; i<20 ; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("1",((BitmapDrawable)getResources().getDrawable(R.drawable.icon_smart_cuff)).getBitmap());
            map.put("2","商品名称"+i);
            map.put("3","6"+i);
            map.put("4","99"+i);
            map.put("5","1000021"+i);
            map.put("6","2015.06.08 12:23:35.21"+i);
            if (i%3 == 0) {
                map.put("7", "评价");
            }else {
                map.put("7", "已评价");
            }
            list2.add(map);
        }
        //Pager wait to receiving.
        waitToReceivingView = LayoutInflater.from(this).inflate(R.layout.all_cloud_order_wait_to_receiving_layout,null);
        ListView listViewWaitToReceivingView = (ListView) waitToReceivingView.findViewById(R.id.all_cloud_order_wait_to_receiving_list_view);
        List list3 = new ArrayList();
        for (int i = 0 ; i<10 ; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("1",((BitmapDrawable)getResources().getDrawable(R.drawable.icon_smart_cuff)).getBitmap());
            map.put("2","商品名称"+i);
            map.put("3","6"+i);
            map.put("4","99"+i);
            map.put("5","1000021"+i);
            map.put("6","2015.06.08 12:23:35.21"+i);
            if (i%3 == 0) {
                map.put("7", "物流信息");
            }else {
                map.put("7", "已收货");
            }
            list3.add(map);
        }
        //Pager wait to announce.
        waitToAnnounceView = LayoutInflater.from(this).inflate(R.layout.all_cloud_order_wait_to_announce_layout,null);
        ListView listViewWaitToAnnounceView = (ListView) waitToAnnounceView.findViewById(R.id.all_cloud_order_wait_to_announce_list_view);
        List list4 = new ArrayList();
        for (int i = 0 ; i<10 ; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("1",((BitmapDrawable)getResources().getDrawable(R.drawable.icon_smart_cuff)).getBitmap());
            map.put("2","商品名称"+i);
            map.put("3","4"+i);
            map.put("4","51"+i);
            map.put("5","10"+i);
            map.put("6","46"+i);
            map.put("7","22"+i);
            list4.add(map);
        }
        //Pager all order.
        allOrderView = LayoutInflater.from(this).inflate(R.layout.all_cloud_order_all_order_layout,null);
        ListView listViewAllOrderWillBeAnnouncedView = (ListView) allOrderView.findViewById(R.id.all_cloud_order_all_order_will_be_announce_list_view);
        ListView listViewAllOrderBeAnnouncedView = (ListView) allOrderView.findViewById(R.id.all_cloud_order_all_order_be_announced_list_view);
        List list5 = new ArrayList();
        for (int i = 0 ; i<10 ; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("1",((BitmapDrawable)getResources().getDrawable(R.drawable.icon_smart_cuff)).getBitmap());
            map.put("2","商品名称"+i);
            map.put("3","412"+i);
            map.put("4","5126"+i);
            map.put("5","12"+i);
            map.put("6","62"+i);
            map.put("7","134");
            list5.add(map);
        }
        List list6 = new ArrayList();
        for (int i = 0 ; i<10 ; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("1",((BitmapDrawable)getResources().getDrawable(R.drawable.icon_smart_cuff)).getBitmap());
            map.put("2","商品名称"+i);
            map.put("3","41"+i);
            map.put("4","512"+i);
            map.put("5","喜羊羊"+i);
            map.put("6","2015.09.08 12:34:24:00"+i);
            list6.add(map);
        }


        //define simple adapter
        PistachioNutsListAdapter pistachioNutsListAdapter = new PistachioNutsListAdapter(AllCloudOrderActivityOld.this,list1);
        EvaluateViewListAdapter evaluateViewListAdapter = new EvaluateViewListAdapter(AllCloudOrderActivityOld.this,list2);
        WaitToReceivingViewListAdapter waitToReceivingViewListAdapter = new WaitToReceivingViewListAdapter(AllCloudOrderActivityOld.this,list3);
        WaitToAnnounceViewListAdapter waitToAnnounceViewListAdapter = new WaitToAnnounceViewListAdapter(AllCloudOrderActivityOld.this,list4);
        WillBeAnnouncedViewListAdapter willBeAnnouncedViewListAdapter = new WillBeAnnouncedViewListAdapter(AllCloudOrderActivityOld.this,list5);
        CloudBuyAllOrder2 cloudBuyAllOrder2 = new CloudBuyAllOrder2(AllCloudOrderActivityOld.this,list6,list6);

        //Define list view adapter
        listViewPagerPistachioNutsView.setAdapter(pistachioNutsListAdapter);
        listViewEvaluateView.setAdapter(evaluateViewListAdapter);
        listViewWaitToReceivingView.setAdapter(waitToReceivingViewListAdapter);
        listViewWaitToAnnounceView.setAdapter(waitToAnnounceViewListAdapter);
        listViewAllOrderWillBeAnnouncedView.setAdapter(cloudBuyAllOrder2);
        listViewAllOrderBeAnnouncedView.setTag(willBeAnnouncedViewListAdapter);

        list.add(allOrderView);
        list.add(waitToAnnounceView);
        list.add(pistachioNutsView);
        list.add(waitToReceivingView);
        list.add(evaluateView);
        viewPager.setAdapter(new AdsViewPagerAdapter(list));
        viewPager.setCurrentItem(pageID);
        allOrder.setOnClickListener(new OrderOnClickListeners());
        waitToAnnounce.setOnClickListener(new OrderOnClickListeners());
        pistachioNuts.setOnClickListener(new OrderOnClickListeners());
        waitToReceiving.setOnClickListener(new OrderOnClickListeners());
        evaluate.setOnClickListener(new OrderOnClickListeners());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (viewPager.getCurrentItem() == 0){
                    allOrder.setBackgroundColor(getResources().getColor(R.color.cloud_buy_text_color_red));
                    waitToAnnounce.setBackgroundColor(getResources().getColor(R.color.white));
                    pistachioNuts.setBackgroundColor(getResources().getColor(R.color.white));
                    waitToReceiving.setBackgroundColor(getResources().getColor(R.color.white));
                    evaluate.setBackgroundColor(getResources().getColor(R.color.white));
                }else if (viewPager.getCurrentItem() == 1){
                    allOrder.setBackgroundColor(getResources().getColor(R.color.white));
                    waitToAnnounce.setBackgroundColor(getResources().getColor(R.color.cloud_buy_text_color_red));
                    pistachioNuts.setBackgroundColor(getResources().getColor(R.color.white));
                    waitToReceiving.setBackgroundColor(getResources().getColor(R.color.white));
                    evaluate.setBackgroundColor(getResources().getColor(R.color.white));
                }else if (viewPager.getCurrentItem() == 2){
                    allOrder.setBackgroundColor(getResources().getColor(R.color.white));
                    waitToAnnounce.setBackgroundColor(getResources().getColor(R.color.white));
                    pistachioNuts.setBackgroundColor(getResources().getColor(R.color.cloud_buy_text_color_red));
                    waitToReceiving.setBackgroundColor(getResources().getColor(R.color.white));
                    evaluate.setBackgroundColor(getResources().getColor(R.color.white));
                }else if (viewPager.getCurrentItem() == 3){
                    allOrder.setBackgroundColor(getResources().getColor(R.color.white));
                    waitToAnnounce.setBackgroundColor(getResources().getColor(R.color.white));
                    pistachioNuts.setBackgroundColor(getResources().getColor(R.color.white));
                    waitToReceiving.setBackgroundColor(getResources().getColor(R.color.cloud_buy_text_color_red));
                    evaluate.setBackgroundColor(getResources().getColor(R.color.white));
                }else if (viewPager.getCurrentItem() == 4){
                    allOrder.setBackgroundColor(getResources().getColor(R.color.white));
                    waitToAnnounce.setBackgroundColor(getResources().getColor(R.color.white));
                    pistachioNuts.setBackgroundColor(getResources().getColor(R.color.white));
                    waitToReceiving.setBackgroundColor(getResources().getColor(R.color.white));
                    evaluate.setBackgroundColor(getResources().getColor(R.color.cloud_buy_text_color_red));
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        backup.setOnClickListener(new OnClickListeners());

    }

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.all_cloud_order_view_pager);
        backup = (ImageView) findViewById(R.id.all_cloud_order_backup);
        allOrder = (TextView) findViewById(R.id.all_cloud_order_all_order);
        waitToAnnounce = (TextView) findViewById(R.id.all_cloud_order_wait_to_announce);
        pistachioNuts = (TextView) findViewById(R.id.all_cloud_order_pistachio_nuts);
        waitToReceiving = (TextView) findViewById(R.id.all_cloud_order_wait_to_receiving);
        evaluate = (TextView) findViewById(R.id.all_cloud_order_evaluate);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.all_cloud_order_backup:
                    AllCloudOrderActivityOld.this.finish();
                    break;
            }
        }
    }

    private class OrderOnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.all_cloud_order_all_order:{viewPager.setCurrentItem(0);}break;
                case R.id.all_cloud_order_wait_to_announce:{viewPager.setCurrentItem(1);}break;
                case R.id.all_cloud_order_pistachio_nuts:{viewPager.setCurrentItem(2);}break;
                case R.id.all_cloud_order_wait_to_receiving:{viewPager.setCurrentItem(3);}break;
                case R.id.all_cloud_order_evaluate:{viewPager.setCurrentItem(4);}break;
            }
        }
    }
}
