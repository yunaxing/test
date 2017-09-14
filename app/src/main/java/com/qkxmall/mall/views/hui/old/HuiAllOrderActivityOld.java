package com.qkxmall.mall.views.hui.old;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.adapter.AdsViewPagerAdapter;
import com.qkxmall.mall.define.adapter.BaseAdapterAllHuiWaitToPayAdapter;
import com.qkxmall.mall.define.adapter.BaseSimpleAdapterAllHuiOrderWaitToReceivingAdapter;
import com.qkxmall.mall.define.adapter.HuiOrder.BashHuiEvaluateAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HuiAllOrderActivityOld extends Activity {
    Context context;
    private ImageView backup = null;
    private TextView all = null;
    private TextView pay = null;
    private TextView divider = null;
    private TextView receiving = null;
    private TextView toEvaluate = null;

    private ViewPager viewPager = null;
    private View allOrder = null;
    private View  waitToPay = null;
    private View waitToPost = null;
    private View waitToReceiving = null;
    private View evaluate = null;
    List<View> list = new ArrayList<>();
    int pagID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_hui_order);
        context = HuiAllOrderActivityOld.this;
        init();
        pagID = getIntent().getIntExtra("pagerID", 0);

        /*
        * All order
        * use base adapter
        * 全部订单
        *
        * */
        List<HashMap<String, Object>> listMap = new ArrayList<>();
        allOrder = LayoutInflater.from(this).inflate(R.layout.all_hui_order_all_order_layout,null);
        ListView listView1 = (ListView) allOrder.findViewById(R.id.list);

        /*
        * Wait to receiving
        * use base adapter
        * 待收货
        * */
        waitToReceiving = LayoutInflater.from(this).inflate(R.layout.all_hui_order_wait_to_receiving_layout,null);
        ListView recListView = (ListView) waitToReceiving.findViewById(R.id.all_hui_order_wait_to_receiving_list_view);
        recListView.setAdapter( new BaseSimpleAdapterAllHuiOrderWaitToReceivingAdapter(this,listMap) );

        /*
        * Wait to pay
        * use base adapter
        * 待支付
        *
        * */
        waitToPay = LayoutInflater.from(this).inflate(R.layout.all_hui_order_wait_to_pay_layout,null);
        ListView payListView = (ListView) waitToPay.findViewById(R.id.all_hui_order_wait_to_pay_list_view);
        payListView.setAdapter(new BaseAdapterAllHuiWaitToPayAdapter(this,listMap));

        /*
        * 待发货
        *
        * */
        waitToPost = LayoutInflater.from(this).inflate(R.layout.all_hui_order_wait_to_post_layout,null);
        ListView postListView = (ListView) waitToPost.findViewById(R.id.all_hui_order_wait_to_post_list_view);
        postListView.setAdapter(new SimpleAdapter(this,listMap,R.layout.all_hui_order_wait_to_post_list_view_layout,new String[]{},new int[]{}));
        /*
        * 待评价
        * */
        evaluate = LayoutInflater.from(this).inflate(R.layout.all_hui_order_wait_to_evaluate_layout,null);
        ListView evaluateListView = (ListView) evaluate.findViewById(R.id.all_hui_order_wait_to_evaluate_list_view);
        evaluateListView.setAdapter(new BashHuiEvaluateAdapter(this, listMap));


        list.add(allOrder);
        list.add(waitToPay);
        list.add(waitToPost);
        list.add(waitToReceiving);
        list.add(evaluate);
        viewPager.setAdapter(new AdsViewPagerAdapter(list));
        all.setOnClickListener(new OrderOnClickListeners());
        pay.setOnClickListener(new OrderOnClickListeners());
        divider.setOnClickListener(new OrderOnClickListeners());
        receiving.setOnClickListener(new OrderOnClickListeners());
        toEvaluate.setOnClickListener(new OrderOnClickListeners());
        if (viewPager.getCurrentItem() == 0) {
            all.setTextColor(getResources().getColor(R.color.white));
            all.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
            pay.setTextColor(getResources().getColor(R.color.material_grey_600));
            pay.setBackgroundColor(getResources().getColor(R.color.white));
            divider.setTextColor(getResources().getColor(R.color.material_grey_600));
            divider.setBackgroundColor(getResources().getColor(R.color.white));
            receiving.setTextColor(getResources().getColor(R.color.material_grey_600));
            receiving.setBackgroundColor(getResources().getColor(R.color.white));
            toEvaluate.setTextColor(getResources().getColor(R.color.material_grey_600));
            toEvaluate.setBackgroundColor(getResources().getColor(R.color.white));
        }else if (viewPager.getCurrentItem() == 1){
            all.setTextColor(getResources().getColor(R.color.material_grey_600));
            all.setBackgroundColor(getResources().getColor(R.color.white));
            pay.setTextColor(getResources().getColor(R.color.white));
            pay.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
            divider.setTextColor(getResources().getColor(R.color.material_grey_600));
            divider.setBackgroundColor(getResources().getColor(R.color.white));
            receiving.setTextColor(getResources().getColor(R.color.material_grey_600));
            receiving.setBackgroundColor(getResources().getColor(R.color.white));
            toEvaluate.setTextColor(getResources().getColor(R.color.material_grey_600));
            toEvaluate.setBackgroundColor(getResources().getColor(R.color.white));
        }else if (viewPager.getCurrentItem() == 2){
            all.setTextColor(getResources().getColor(R.color.material_grey_600));
            all.setBackgroundColor(getResources().getColor(R.color.white));
            pay.setTextColor(getResources().getColor(R.color.material_grey_600));
            pay.setBackgroundColor(getResources().getColor(R.color.white));
            divider.setTextColor(getResources().getColor(R.color.white));
            divider.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
            receiving.setTextColor(getResources().getColor(R.color.material_grey_600));
            receiving.setBackgroundColor(getResources().getColor(R.color.white));
            toEvaluate.setTextColor(getResources().getColor(R.color.material_grey_600));
            toEvaluate.setBackgroundColor(getResources().getColor(R.color.white));
        }else if (viewPager.getCurrentItem() == 3){
            all.setTextColor(getResources().getColor(R.color.material_grey_600));
            all.setBackgroundColor(getResources().getColor(R.color.white));
            pay.setTextColor(getResources().getColor(R.color.material_grey_600));
            pay.setBackgroundColor(getResources().getColor(R.color.white));
            divider.setTextColor(getResources().getColor(R.color.material_grey_600));
            divider.setBackgroundColor(getResources().getColor(R.color.white));
            receiving.setTextColor(getResources().getColor(R.color.white));
            receiving.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
            toEvaluate.setTextColor(getResources().getColor(R.color.material_grey_600));
            toEvaluate.setBackgroundColor(getResources().getColor(R.color.white));
        }else if (viewPager.getCurrentItem() == 4){
            all.setTextColor(getResources().getColor(R.color.material_grey_600));
            all.setBackgroundColor(getResources().getColor(R.color.white));
            pay.setTextColor(getResources().getColor(R.color.material_grey_600));
            pay.setBackgroundColor(getResources().getColor(R.color.white));
            divider.setTextColor(getResources().getColor(R.color.material_grey_600));
            divider.setBackgroundColor(getResources().getColor(R.color.white));
            receiving.setTextColor(getResources().getColor(R.color.material_grey_600));
            receiving.setBackgroundColor(getResources().getColor(R.color.white));
            toEvaluate.setTextColor(getResources().getColor(R.color.white));
            toEvaluate.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
        }
        viewPager.setCurrentItem(pagID);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (viewPager.getCurrentItem() == 0) {
                    all.setTextColor(getResources().getColor(R.color.white));
                    all.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
                    pay.setTextColor(getResources().getColor(R.color.material_grey_600));
                    pay.setBackgroundColor(getResources().getColor(R.color.white));
                    divider.setTextColor(getResources().getColor(R.color.material_grey_600));
                    divider.setBackgroundColor(getResources().getColor(R.color.white));
                    receiving.setTextColor(getResources().getColor(R.color.material_grey_600));
                    receiving.setBackgroundColor(getResources().getColor(R.color.white));
                    toEvaluate.setTextColor(getResources().getColor(R.color.material_grey_600));
                    toEvaluate.setBackgroundColor(getResources().getColor(R.color.white));
                }else if (viewPager.getCurrentItem() == 1){
                    all.setTextColor(getResources().getColor(R.color.material_grey_600));
                    all.setBackgroundColor(getResources().getColor(R.color.white));
                    pay.setTextColor(getResources().getColor(R.color.white));
                    pay.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
                    divider.setTextColor(getResources().getColor(R.color.material_grey_600));
                    divider.setBackgroundColor(getResources().getColor(R.color.white));
                    receiving.setTextColor(getResources().getColor(R.color.material_grey_600));
                    receiving.setBackgroundColor(getResources().getColor(R.color.white));
                    toEvaluate.setTextColor(getResources().getColor(R.color.material_grey_600));
                    toEvaluate.setBackgroundColor(getResources().getColor(R.color.white));
                }else if (viewPager.getCurrentItem() == 2){
                    all.setTextColor(getResources().getColor(R.color.material_grey_600));
                    all.setBackgroundColor(getResources().getColor(R.color.white));
                    pay.setTextColor(getResources().getColor(R.color.material_grey_600));
                    pay.setBackgroundColor(getResources().getColor(R.color.white));
                    divider.setTextColor(getResources().getColor(R.color.white));
                    divider.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
                    receiving.setTextColor(getResources().getColor(R.color.material_grey_600));
                    receiving.setBackgroundColor(getResources().getColor(R.color.white));
                    toEvaluate.setTextColor(getResources().getColor(R.color.material_grey_600));
                    toEvaluate.setBackgroundColor(getResources().getColor(R.color.white));
                }else if (viewPager.getCurrentItem() == 3){
                    all.setTextColor(getResources().getColor(R.color.material_grey_600));
                    all.setBackgroundColor(getResources().getColor(R.color.white));
                    pay.setTextColor(getResources().getColor(R.color.material_grey_600));
                    pay.setBackgroundColor(getResources().getColor(R.color.white));
                    divider.setTextColor(getResources().getColor(R.color.material_grey_600));
                    divider.setBackgroundColor(getResources().getColor(R.color.white));
                    receiving.setTextColor(getResources().getColor(R.color.white));
                    receiving.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
                    toEvaluate.setTextColor(getResources().getColor(R.color.material_grey_600));
                    toEvaluate.setBackgroundColor(getResources().getColor(R.color.white));
                }else if (viewPager.getCurrentItem() == 4){
                    all.setTextColor(getResources().getColor(R.color.material_grey_600));
                    all.setBackgroundColor(getResources().getColor(R.color.white));
                    pay.setTextColor(getResources().getColor(R.color.material_grey_600));
                    pay.setBackgroundColor(getResources().getColor(R.color.white));
                    divider.setTextColor(getResources().getColor(R.color.material_grey_600));
                    divider.setBackgroundColor(getResources().getColor(R.color.white));
                    receiving.setTextColor(getResources().getColor(R.color.material_grey_600));
                    receiving.setBackgroundColor(getResources().getColor(R.color.white));
                    toEvaluate.setTextColor(getResources().getColor(R.color.white));
                    toEvaluate.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.all_hui_order_backup);
        viewPager = (ViewPager) findViewById(R.id.all_hui_order_view_pager);
        all = (TextView) findViewById(R.id.all_hui_order_all_order);
        pay = (TextView) findViewById(R.id.all_hui_order_wait_to_pay);
        divider = (TextView) findViewById(R.id.all_hui_order_wait_to_divider);
        receiving = (TextView) findViewById(R.id.all_hui_order_wait_to_receiving);
        toEvaluate = (TextView) findViewById(R.id.all_hui_order_evaluate);
    }

    private class OrderOnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.all_hui_order_all_order:{viewPager.setCurrentItem(0);}break;
                case R.id.all_hui_order_wait_to_pay:{viewPager.setCurrentItem(1);}break;
                case R.id.all_hui_order_wait_to_divider:{viewPager.setCurrentItem(2);}break;
                case R.id.all_hui_order_wait_to_receiving:{viewPager.setCurrentItem(3);}break;
                case R.id.all_hui_order_evaluate:{viewPager.setCurrentItem(4);}break;
            }
        }
    }
}
