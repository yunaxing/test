package com.qkxmall.mall.views.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.adapter.AdsViewPagerAdapter;
import com.qkxmall.mall.define.views.ScrollViewInViewPager;
import com.qkxmall.mall.views.cloud.AllCloudClassItem;
import com.qkxmall.mall.views.hui.HuiClassItemsActivity;

import java.util.ArrayList;
import java.util.List;

public class AllBuyMenuFragment extends Fragment {
    Typeface typeface;
    private View view = null;

    private ImageView backup = null;
    private ScrollViewInViewPager viewPager = null;
    private View huiView = null;
    private View cloudView = null;
    private TextView cloudClassify = null;
    private TextView huiClassify = null;
    private ViewPager huiViewDetail = null;
    List<View> list = new ArrayList<>();
    List<View> listDetail = new ArrayList<>();
    SimpleDraweeView simpledraweeView;

    TextView class1 = null;
    TextView class2 = null;
    TextView class3 = null;
    TextView class4 = null;
    TextView class5 = null;
    TextView class6 = null;
    TextView class7 = null;
    TextView class8 = null;
    TextView class9 = null;


    private TextView item0;
    private TextView item1;
    private TextView item2;
    private TextView item3;
    private TextView item4;
    private TextView item5;
    private TextView item6;
    private TextView item7;
    private TextView item8;
    private TextView item9;
    private TextView item10;
    private TextView item11;
    private TextView item12;
    private TextView item13;


    View cls1;
    private LinearLayout cls_1_1_1 = null;
    private LinearLayout cls_1_1_2 = null;
    private LinearLayout cls_1_1_3 = null;
    private LinearLayout cls_1_2_1 = null;
    private LinearLayout cls_1_2_2 = null;
    private LinearLayout cls_1_2_3 = null;

    View cls2;


    private LinearLayout cls_2_1_01 = null;
    private LinearLayout cls_2_1_02 = null;
    private LinearLayout cls_2_1_03 = null;


    private LinearLayout cls_2_1_1 = null;
    private LinearLayout cls_2_1_2 = null;
    private LinearLayout cls_2_1_3 = null;
    private LinearLayout cls_2_1_4 = null;
    private LinearLayout cls_2_1_5 = null;
    private LinearLayout cls_2_1_6 = null;
    private LinearLayout cls_2_1_7 = null;
    private LinearLayout cls_2_1_8 = null;
    private LinearLayout cls_2_1_9 = null;
    private LinearLayout cls_2_2_1 = null;
    private LinearLayout cls_2_2_2 = null;
    private LinearLayout cls_2_2_3 = null;
    private LinearLayout cls_2_2_4 = null;
    private LinearLayout cls_2_2_5 = null;
    private LinearLayout cls_2_2_6 = null;
    private LinearLayout cls_2_3_1 = null;
    private LinearLayout cls_2_3_2 = null;
    private LinearLayout cls_2_3_3 = null;
    private LinearLayout cls_2_3_4 = null;



    TextView cls_2_1_01t;
    TextView cls_2_1_02t;
    TextView cls_2_1_03t;



    TextView cls_2_1_1t;
    TextView cls_2_1_2t;
    TextView cls_2_1_3t;
    TextView cls_2_1_4t;
    TextView cls_2_1_5t;
    TextView cls_2_1_6t;
    TextView cls_2_1_7t;
    TextView cls_2_1_8t;
    TextView cls_2_1_9t;
    TextView cls_2_2_1t;
    TextView cls_2_2_2t;
    TextView cls_2_2_3t;
    TextView cls_2_2_4t;
    TextView cls_2_2_5t;
    TextView cls_2_2_6t;
    TextView cls_2_3t;
    TextView cls_2_3_1t;
    TextView cls_2_3_2t;
    TextView cls_2_3_3t;
    TextView cls_2_3_4t;

    View cls3;
    private LinearLayout cls_3_1_1 = null;
    private LinearLayout cls_3_1_2 = null;
    private LinearLayout cls_3_1_3 = null;
    private LinearLayout cls_3_1_4 = null;
    private LinearLayout cls_3_1_5 = null;
    private LinearLayout cls_3_1_6 = null;
    private LinearLayout cls_3_1_7 = null;
    private LinearLayout cls_3_1_8 = null;
    private LinearLayout cls_3_1_9 = null;
    private LinearLayout cls_3_2_01 = null;
    private LinearLayout cls_3_2_1 = null;
    private LinearLayout cls_3_2_2 = null;
    private LinearLayout cls_3_2_3 = null;
    private LinearLayout cls_3_3_1 = null;
    private LinearLayout cls_3_3_2 = null;
    private LinearLayout cls_3_3_3 = null;
    private LinearLayout cls_3_3_4= null;

    TextView cls_3_1_t;
    TextView cls_3_2_t;
    TextView cls_3_3_t;
    TextView cls_3_1_1_t;
    TextView cls_3_1_2_t;
    TextView cls_3_1_3_t;
    TextView cls_3_1_4_t;
    TextView cls_3_1_5_t;
    TextView cls_3_1_6_t;
    TextView cls_3_1_7_t;
    TextView cls_3_1_8_t;
    TextView cls_3_1_9_t;
    TextView cls_3_2_01_t;
    TextView cls_3_2_1_t;
    TextView cls_3_2_2_t;
    TextView cls_3_2_3_t;
    TextView cls_3_3_1_t;
    TextView cls_3_3_2_t;
    TextView cls_3_3_3_t;
    TextView cls_3_3_4_t;
    View cls4;
    private LinearLayout cls_4_1_1 = null;
    private LinearLayout cls_4_1_2 = null;
    private LinearLayout cls_4_1_3 = null;
    private LinearLayout cls_4_1_4 = null;
    private LinearLayout cls_4_1_5 = null;
    private LinearLayout cls_4_1_6 = null;
    private LinearLayout cls_4_1_7 = null;
    private LinearLayout cls_4_1_8 = null;
    private LinearLayout cls_4_2_1 = null;
    private LinearLayout cls_4_2_2 = null;
    private LinearLayout cls_4_2_3 = null;
    private LinearLayout cls_4_2_4 = null;
    private LinearLayout cls_4_3_1 = null;
    private LinearLayout cls_4_3_2 = null;
    private LinearLayout cls_4_3_3 = null;
    private LinearLayout cls_4_4_1 = null;
    private LinearLayout cls_4_4_2 = null;
    private LinearLayout cls_4_4_3 = null;
    private LinearLayout cls_4_4_4 = null;
    private LinearLayout cls_4_4_5 = null;
    private LinearLayout cls_4_4_6 = null;
    private LinearLayout cls_4_4_7 = null;
    private LinearLayout cls_4_5_1 = null;
    TextView cls_4_1_1_t;
    TextView cls_4_1_2_t;
    TextView cls_4_1_3_t;
    TextView cls_4_1_4_t;
    TextView cls_4_1_5_t;
    TextView cls_4_1_6_t;
    TextView cls_4_1_7_t;
    TextView cls_4_1_8_t;
    TextView cls_4_2_1_t;
    TextView cls_4_2_2_t;
    TextView cls_4_2_3_t;
    TextView cls_4_2_4_t;
    TextView cls_4_3_1_t;
    TextView cls_4_3_2_t;
    TextView cls_4_3_3_t;
    TextView cls_4_4_1_t;
    TextView cls_4_4_2_t;
    TextView cls_4_4_3_t;
    TextView cls_4_4_4_t;
    TextView cls_4_4_5_t;
    TextView cls_4_4_6_t;
    TextView cls_4_4_7_t;
    TextView cls_4_5_1_t;

    View cls5;
    private LinearLayout cls_5_1_1 = null;
    private LinearLayout cls_5_1_2 = null;
    private LinearLayout cls_5_1_3 = null;
    private LinearLayout cls_5_1_4 = null;
    private LinearLayout cls_5_1_5 = null;
    private LinearLayout cls_5_1_6 = null;
    private LinearLayout cls_5_1_7 = null;
    private LinearLayout cls_5_1_8 = null;
    private LinearLayout cls_5_2_1 = null;
    private LinearLayout cls_5_2_2 = null;
    private LinearLayout cls_5_2_3 = null;
    private LinearLayout cls_5_3_1 = null;
    private LinearLayout cls_5_3_2 = null;
    private LinearLayout cls_5_3_3 = null;
    private LinearLayout cls_5_3_4 = null;
    private LinearLayout cls_5_3_5 = null;
    private LinearLayout cls_5_3_6 = null;
    private LinearLayout cls_5_3_7 = null;
    private LinearLayout cls_5_3_8 = null;
    TextView cls_5_1_1_t;
    TextView cls_5_1_2_t;
    TextView cls_5_1_3_t;
    TextView cls_5_1_4_t;
    TextView cls_5_1_5_t;
    TextView cls_5_1_6_t;
    TextView cls_5_1_7_t;
    TextView cls_5_1_8_t;
    TextView cls_5_2_1_t;
    TextView cls_5_2_2_t;
    TextView cls_5_2_3_t;
    TextView cls_5_3_1_t;
    TextView cls_5_3_2_t;
    TextView cls_5_3_3_t;
    TextView cls_5_3_4_t;
    TextView cls_5_3_5_t;
    TextView cls_5_3_6_t;
    TextView cls_5_3_7_t;
    TextView cls_5_3_8_t;

    View cls6;
    private LinearLayout cls_6_1_1 = null;
    private LinearLayout cls_6_1_2 = null;
    private LinearLayout cls_6_1_3 = null;
    private LinearLayout cls_6_1_4 = null;
    private LinearLayout cls_6_1_5 = null;
    private LinearLayout cls_6_1_6 = null;
    private LinearLayout cls_6_1_7 = null;
    private LinearLayout cls_6_2_1 = null;
    private LinearLayout cls_6_2_2 = null;
    private LinearLayout cls_6_2_3 = null;
    private LinearLayout cls_6_2_4 = null;
    private LinearLayout cls_6_2_5 = null;
    private LinearLayout cls_6_2_6 = null;
    private LinearLayout cls_6_2_7 = null;
    private LinearLayout cls_6_2_8 = null;
    private LinearLayout cls_6_2_9 = null;
    private LinearLayout cls_6_2_10 = null;
    private LinearLayout cls_6_2_11 = null;
    private LinearLayout cls_6_2_12 = null;
    private LinearLayout cls_6_2_13 = null;
    private LinearLayout cls_6_2_14 = null;
    private LinearLayout cls_6_2_15 = null;
    private LinearLayout cls_6_2_16 = null;
    private LinearLayout cls_6_2_17 = null;
    TextView cls_6_1_1_t;
    TextView cls_6_1_2_t;
    TextView cls_6_1_3_t;
    TextView cls_6_1_4_t;
    TextView cls_6_1_5_t;
    TextView cls_6_1_6_t;
    TextView cls_6_1_7_t;
    TextView cls_6_2_1_t;
    TextView cls_6_2_2_t;
    TextView cls_6_2_3_t;
    TextView cls_6_2_4_t;
    TextView cls_6_2_5_t;
    TextView cls_6_2_6_t;
    TextView cls_6_2_7_t;
    TextView cls_6_2_8_t;
    TextView cls_6_2_9_t;
    TextView cls_6_2_10_t;
    TextView cls_6_2_11_t;
    TextView cls_6_2_12_t;
    TextView cls_6_2_13_t;
    TextView cls_6_2_14_t;
    TextView cls_6_2_15_t;
    TextView cls_6_2_16_t;
    TextView cls_6_2_17_t;
    View cls7;
    private LinearLayout cls_7_1_1 = null;
    private LinearLayout cls_7_1_2 = null;
    private LinearLayout cls_7_1_3 = null;
    private LinearLayout cls_7_1_4 = null;
    private LinearLayout cls_7_1_5 = null;
    private LinearLayout cls_7_2_1 = null;
    private LinearLayout cls_7_2_2 = null;
    private LinearLayout cls_7_2_3 = null;
    private LinearLayout cls_7_2_4 = null;
    private LinearLayout cls_7_2_5 = null;
    private LinearLayout cls_7_2_6 = null;
    TextView cls_7_1_1_t;
    TextView cls_7_1_2_t;
    TextView cls_7_1_3_t;
    TextView cls_7_1_4_t;
    TextView cls_7_1_5_t;
    TextView cls_7_2_1_t;
    TextView cls_7_2_2_t;
    TextView cls_7_2_3_t;
    TextView cls_7_2_4_t;
    TextView cls_7_2_5_t;
    TextView cls_7_2_6_t;

    View cls8;
    private LinearLayout cls_8_1_1 = null;
    private LinearLayout cls_8_1_2 = null;
    private LinearLayout cls_8_1_3 = null;
    private LinearLayout cls_8_1_4 = null;
    TextView cls_8_1_t;
    TextView cls_8_1_1_t;
    TextView cls_8_1_2_t;
    TextView cls_8_1_3_t;
    TextView cls_8_1_4_t;


    View cls9;
    private LinearLayout cls_9_1_1 = null;
    private LinearLayout cls_9_1_2 = null;
    private LinearLayout cls_9_1_3 = null;
    private LinearLayout cls_9_1_4 = null;
    private LinearLayout cls_9_1_5 = null;
    private LinearLayout cls_9_1_6 = null;
    private LinearLayout cls_9_1_7 = null;
    private LinearLayout cls_9_1_8 = null;
    private LinearLayout cls_9_1_9 = null;
    private LinearLayout cls_9_1_10 = null;
    private LinearLayout cls_9_1_11 = null;
    private LinearLayout cls_9_1_12 = null;
    private LinearLayout cls_9_1_13 = null;
    private LinearLayout cls_9_1_14 = null;
    private LinearLayout cls_9_1_15 = null;

    private LinearLayout cls_9_2_1 = null;
    private LinearLayout cls_9_2_2 = null;
    private LinearLayout cls_9_2_3 = null;
    private LinearLayout cls_9_2_4 = null;
    private LinearLayout cls_9_2_5 = null;
    private LinearLayout cls_9_2_6 = null;
    private LinearLayout cls_9_2_7 = null;
    private LinearLayout cls_9_2_8 = null;
    private LinearLayout cls_9_2_9 = null;
    private LinearLayout cls_9_2_10 = null;
    private LinearLayout cls_9_2_11= null;
    private LinearLayout cls_9_3_1 = null;
    private LinearLayout cls_9_3_2 = null;
    private LinearLayout cls_9_3_3 = null;
    private LinearLayout cls_9_3_4 = null;
    TextView cls_9_1_1_t;
    TextView cls_9_1_2_t;
    TextView cls_9_1_3_t;
    TextView cls_9_1_4_t;
    TextView cls_9_1_5_t;
    TextView cls_9_1_6_t;
    TextView cls_9_1_7_t;
    TextView cls_9_1_8_t;
    TextView cls_9_1_9_t;
    TextView cls_9_1_10_t;
    TextView cls_9_1_11_t;
    TextView cls_9_1_12_t;
    TextView cls_9_1_13_t;
    TextView cls_9_1_14_t;
    TextView cls_9_1_15_t;
    TextView cls_9_2_1_t;
    TextView cls_9_2_2_t;
    TextView cls_9_2_3_t;
    TextView cls_9_2_4_t;
    TextView cls_9_2_5_t;
    TextView cls_9_2_6_t;
    TextView cls_9_2_7_t;
    TextView cls_9_2_8_t;
    TextView cls_9_2_9_t;
    TextView cls_9_2_10_t;
    TextView cls_9_2_11_t;
    TextView cls_9_3_1_t;
    TextView cls_9_3_2_t;
    TextView cls_9_3_3_t;
    TextView cls_9_3_4_t;

    View cls10;
    private LinearLayout cls_10_1_1 = null;
    private LinearLayout cls_10_1_2 = null;
    private LinearLayout cls_10_1_3 = null;
    private LinearLayout cls_10_1_4 = null;
    private LinearLayout cls_10_1_5 = null;
    private LinearLayout cls_10_1_6 = null;
    private LinearLayout cls_10_1_7 = null;
    private LinearLayout cls_10_1_8 = null;
    private LinearLayout cls_10_1_9 = null;
    private LinearLayout cls_10_2_1 = null;
    private LinearLayout cls_10_2_2 = null;
    private LinearLayout cls_10_2_3 = null;
    private LinearLayout cls_10_2_4 = null;
    private LinearLayout cls_10_2_5 = null;
    private LinearLayout cls_10_2_6 = null;
    private LinearLayout cls_10_2_7 = null;
    TextView cls_10_1_1_t;
    TextView cls_10_1_2_t;
    TextView cls_10_1_3_t;
    TextView cls_10_1_4_t;
    TextView cls_10_1_5_t;
    TextView cls_10_1_6_t;
    TextView cls_10_1_7_t;
    TextView cls_10_1_8_t;
    TextView cls_10_1_9_t;
    TextView cls_10_2_1_t;
    TextView cls_10_2_2_t;
    TextView cls_10_2_3_t;
    TextView cls_10_2_4_t;
    TextView cls_10_2_5_t;
    TextView cls_10_2_6_t;
    TextView cls_10_2_7_t;

    View cls11;
    private LinearLayout cls_11_1_1 = null;
    private LinearLayout cls_11_1_2 = null;
    private LinearLayout cls_11_1_3 = null;
    private LinearLayout cls_11_2_1 = null;
    private LinearLayout cls_11_2_2 = null;
    private LinearLayout cls_11_2_3 = null;
    private LinearLayout cls_11_2_4 = null;
    private LinearLayout cls_11_2_5 = null;
    private LinearLayout cls_11_2_6 = null;
    private LinearLayout cls_11_2_7 = null;
    private LinearLayout cls_11_2_8 = null;
    private LinearLayout cls_11_2_9 = null;
    private LinearLayout cls_11_2_10 = null;
    private LinearLayout cls_11_3_1 = null;
    private LinearLayout cls_11_3_2 = null;
    private LinearLayout cls_11_3_3 = null;
    TextView cls_11_1_1_t;
    TextView cls_11_1_2_t;
    TextView cls_11_1_3_t;
    TextView cls_11_2_1_t;
    TextView cls_11_2_2_t;
    TextView cls_11_2_3_t;
    TextView cls_11_2_4_t;
    TextView cls_11_2_5_t;
    TextView cls_11_2_6_t;
    TextView cls_11_2_7_t;
    TextView cls_11_2_8_t;
    TextView cls_11_2_9_t;
    TextView cls_11_2_10_t;
    TextView cls_11_3_1_t;
    TextView cls_11_3_2_t;
    TextView cls_11_3_3_t;

    View cls12;
    private LinearLayout cls_12_1_1 = null;
    private LinearLayout cls_12_1_2 = null;
    private LinearLayout cls_12_1_3 = null;
    private LinearLayout cls_12_1_4 = null;
    private LinearLayout cls_12_1_5 = null;
    private LinearLayout cls_12_2_1 = null;
    private LinearLayout cls_12_2_2 = null;
    private LinearLayout cls_12_2_3 = null;
    private LinearLayout cls_12_2_4 = null;
    private LinearLayout cls_12_2_5 = null;
    private LinearLayout cls_12_3_1 = null;
    private LinearLayout cls_12_3_2 = null;
    TextView cls_12_1_1_t;
    TextView cls_12_1_2_t;
    TextView cls_12_1_3_t;
    TextView cls_12_1_4_t;
    TextView cls_12_1_5_t;
    TextView cls_12_2_1_t;
    TextView cls_12_2_2_t;
    TextView cls_12_2_3_t;
    TextView cls_12_2_4_t;
    TextView cls_12_2_5_t;
    TextView cls_12_3_1_t;
    TextView cls_12_3_2_t;

    View cls13;
    private LinearLayout cls_13_1_1 = null;
    private LinearLayout cls_13_1_2 = null;
    private LinearLayout cls_13_1_3 = null;
    private LinearLayout cls_13_1_4 = null;
    private LinearLayout cls_13_1_5 = null;
    private LinearLayout cls_13_1_6 = null;
    private LinearLayout cls_13_1_7 = null;
    private LinearLayout cls_13_1_8 = null;
    TextView cls_13_1_1_t;
    TextView cls_13_1_2_t;
    TextView cls_13_1_3_t;
    TextView cls_13_1_4_t;
    TextView cls_13_1_5_t;
    TextView cls_13_1_6_t;
    TextView cls_13_1_7_t;
    TextView cls_13_1_8_t;

    View cls14;
    private LinearLayout cls_14_1_1 = null;
    private LinearLayout cls_14_1_2 = null;
    private LinearLayout cls_14_1_3 = null;
    private LinearLayout cls_14_1_4 = null;
    private LinearLayout cls_14_1_5 = null;
    private LinearLayout cls_14_1_6 = null;
    private LinearLayout cls_14_1_7 = null;
    TextView cls_14_1_1_t;
    TextView cls_14_1_2_t;
    TextView cls_14_1_3_t;
    TextView cls_14_1_4_t;
    TextView cls_14_1_5_t;
    TextView cls_14_1_6_t;
    TextView cls_14_1_7_t;

    View cls15;
    private LinearLayout cls_15_1_1 = null;
    private LinearLayout cls_15_1_2 = null;
    private LinearLayout cls_15_1_3 = null;
    private LinearLayout cls_15_1_4 = null;
    private LinearLayout cls_15_1_5 = null;
    private LinearLayout cls_15_1_6 = null;
    FragmentActivity mActivity;
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_all_buy_menu, null);
        mActivity=getActivity();

        init();
        typeface = Typeface.createFromAsset(mActivity.getAssets(), "fonts/palamecia_titling.ttf");
        cloudClassify.setTypeface(typeface);
        huiClassify.setTypeface(typeface);


        cloudView = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_cloud_menu_layout, null);
        class1 = (TextView) cloudView.findViewById(R.id.shoujizhineng);
        class2 = (TextView) cloudView.findViewById(R.id.diannaobangong);
        class3 = (TextView) cloudView.findViewById(R.id.xishuhufu);
        class4 = (TextView) cloudView.findViewById(R.id.chuandaimingpin);
        class5 = (TextView) cloudView.findViewById(R.id.jingmeishipin);
        class6 = (TextView) cloudView.findViewById(R.id.yundonghuwai);
        class7 = (TextView) cloudView.findViewById(R.id.shenghuoyongpin);
        class8 = (TextView) cloudView.findViewById(R.id.chuangyilipin);
        class9 = (TextView) cloudView.findViewById(R.id.qita);
        class1.setOnClickListener(new OnClickListeners());
        class2.setOnClickListener(new OnClickListeners());
        class3.setOnClickListener(new OnClickListeners());
        class4.setOnClickListener(new OnClickListeners());
        class5.setOnClickListener(new OnClickListeners());
        class6.setOnClickListener(new OnClickListeners());
        class7.setOnClickListener(new OnClickListeners());
        class8.setOnClickListener(new OnClickListeners());
        class9.setOnClickListener(new OnClickListeners());
//        list.add(cloudView);

        huiView = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_layout, null);
        item0 = (TextView) huiView.findViewById(R.id.all_buy_menu_hui_item_0);
        item1 = (TextView) huiView.findViewById(R.id.all_buy_menu_hui_item_1);
        item2 = (TextView) huiView.findViewById(R.id.all_buy_menu_hui_item_2);
        item3 = (TextView) huiView.findViewById(R.id.all_buy_menu_hui_item_3);
        item4 = (TextView) huiView.findViewById(R.id.all_buy_menu_hui_item_4);
        item5 = (TextView) huiView.findViewById(R.id.all_buy_menu_hui_item_5);
        item6 = (TextView) huiView.findViewById(R.id.all_buy_menu_hui_item_6);
        item7 = (TextView) huiView.findViewById(R.id.all_buy_menu_hui_item_7);
        item8 = (TextView) huiView.findViewById(R.id.all_buy_menu_hui_item_8);
        item9 = (TextView) huiView.findViewById(R.id.all_buy_menu_hui_item_9);
        item10 = (TextView) huiView.findViewById(R.id.all_buy_menu_hui_item_10);
        item11 = (TextView) huiView.findViewById(R.id.all_buy_menu_hui_item_11);
        item12 = (TextView) huiView.findViewById(R.id.all_buy_menu_hui_item_12);
        item13 = (TextView) huiView.findViewById(R.id.all_buy_menu_hui_item_13);

        item0.setOnClickListener(new OnClickListeners());
        item1.setOnClickListener(new OnClickListeners());
        item2.setOnClickListener(new OnClickListeners());
        item3.setOnClickListener(new OnClickListeners());
        item4.setOnClickListener(new OnClickListeners());
        item5.setOnClickListener(new OnClickListeners());
        item6.setOnClickListener(new OnClickListeners());
        item7.setOnClickListener(new OnClickListeners());
        item8.setOnClickListener(new OnClickListeners());
        item9.setOnClickListener(new OnClickListeners());
        item10.setOnClickListener(new OnClickListeners());
        item11.setOnClickListener(new OnClickListeners());
        item12.setOnClickListener(new OnClickListeners());
        item13.setOnClickListener(new OnClickListeners());

        huiViewDetail = (ViewPager) huiView.findViewById(R.id.hui_buy_menu_view);

        cls1 = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_view_detail_layout, null);
        cls_1_1_1 = (LinearLayout) cls1.findViewById(R.id.cls_1_1);
        cls_1_1_2 = (LinearLayout) cls1.findViewById(R.id.cls_1_2);
        cls_1_1_3 = (LinearLayout) cls1.findViewById(R.id.cls_1_3);
        cls_1_2_1 = (LinearLayout) cls1.findViewById(R.id.cls_2_1);
        cls_1_2_2 = (LinearLayout) cls1.findViewById(R.id.cls_2_2);
        cls_1_2_3 = (LinearLayout) cls1.findViewById(R.id.cls_2_3);
        TextView cls_1_1_t = (TextView) cls1.findViewById(R.id.cls_1);
        cls_1_1_t.setText("男生装扮");
        cls_1_1_t.setTypeface(typeface);
        SimpleDraweeView cls_1_1_1p = (SimpleDraweeView) cls1.findViewById(R.id.cls_1_1_p);
        cls_1_1_1p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/217.jpg"));
        TextView cls_1_1_1t = (TextView) cls1.findViewById(R.id.cls_1_1_t);
        cls_1_1_1t.setText("男生上衣");
        SimpleDraweeView cls_1_1_2p = (SimpleDraweeView) cls1.findViewById(R.id.cls_1_2_p);
        cls_1_1_2p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/218.jpg"));
        TextView cls_1_1_2t = (TextView) cls1.findViewById(R.id.cls_1_2_t);
        cls_1_1_2t.setText("男生下衣");
        SimpleDraweeView cls_1_1_3p = (SimpleDraweeView) cls1.findViewById(R.id.cls_1_3_p);
        cls_1_1_3p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/221.jpg"));
        TextView cls_1_1_3t = (TextView) cls1.findViewById(R.id.cls_1_3_t);
        cls_1_1_3t.setText("鞋帽皮具");

        TextView cls_1_2_t = (TextView) cls1.findViewById(R.id.cls_2);
        cls_1_2_t.setText("女生装扮");
        cls_1_2_t.setTypeface(typeface);
        SimpleDraweeView cls_1_2_1p = (SimpleDraweeView) cls1.findViewById(R.id.cls_2_1_p);
        cls_1_2_1p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/219.jpg"));
        TextView cls_1_2_1t = (TextView) cls1.findViewById(R.id.cls_2_1_t);
        cls_1_2_1t.setText("女生上衣");
        SimpleDraweeView cls_1_2_2p = (SimpleDraweeView) cls1.findViewById(R.id.cls_2_2_p);
        cls_1_2_2p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/220.jpg"));
        TextView cls_1_2_2t = (TextView) cls1.findViewById(R.id.cls_2_2_t);
        cls_1_2_2t.setText("女生下衣");
        SimpleDraweeView cls_1_2_3p = (SimpleDraweeView) cls1.findViewById(R.id.cls_2_3_p);
        cls_1_2_3p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/222.jpg"));
        TextView cls_1_2_3t = (TextView) cls1.findViewById(R.id.cls_2_3_t);
        cls_1_2_3t.setText("鞋帽皮具");

        cls_1_1_1.setOnClickListener(new Cls11OnClickListeners());
        cls_1_1_2.setOnClickListener(new Cls11OnClickListeners());
        cls_1_1_3.setOnClickListener(new Cls11OnClickListeners());
        cls_1_2_1.setOnClickListener(new Cls11OnClickListeners());
        cls_1_2_2.setOnClickListener(new Cls11OnClickListeners());
        cls_1_2_3.setOnClickListener(new Cls11OnClickListeners());


        cls2 = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_view_detail_layout_2, null);


        cls_2_1_01 = (LinearLayout) cls2.findViewById(R.id.cls_1_01);
        cls_2_1_02 = (LinearLayout) cls2.findViewById(R.id.cls_1_02);
        cls_2_1_03 = (LinearLayout) cls2.findViewById(R.id.cls_1_03);


        cls_2_1_1 = (LinearLayout) cls2.findViewById(R.id.cls_1_1);
        cls_2_1_2 = (LinearLayout) cls2.findViewById(R.id.cls_1_2);
        cls_2_1_3 = (LinearLayout) cls2.findViewById(R.id.cls_1_3);
        cls_2_1_4 = (LinearLayout) cls2.findViewById(R.id.cls_1_4);
        cls_2_1_5 = (LinearLayout) cls2.findViewById(R.id.cls_1_5);
        cls_2_1_6 = (LinearLayout) cls2.findViewById(R.id.cls_1_6);
        cls_2_1_7 = (LinearLayout) cls2.findViewById(R.id.cls_1_7);
        cls_2_1_8 = (LinearLayout) cls2.findViewById(R.id.cls_1_8);
        cls_2_1_9 = (LinearLayout) cls2.findViewById(R.id.cls_1_9);
        TextView cls_2_1t = (TextView) cls2.findViewById(R.id.cls_1);


        SimpleDraweeView cls_2_1_01p = (SimpleDraweeView) cls2.findViewById(R.id.cls_1_01_p);
        cls_2_1_01t = (TextView) cls2.findViewById(R.id.cls_1_01_t);
        SimpleDraweeView cls_2_1_02p = (SimpleDraweeView) cls2.findViewById(R.id.cls_1_02_p);
        cls_2_1_02t = (TextView) cls2.findViewById(R.id.cls_1_02_t);
        SimpleDraweeView cls_2_1_03p = (SimpleDraweeView) cls2.findViewById(R.id.cls_1_03_p);
        cls_2_1_03t = (TextView) cls2.findViewById(R.id.cls_1_03_t);


        SimpleDraweeView cls_2_1_1p = (SimpleDraweeView) cls2.findViewById(R.id.cls_1_1_p);
        cls_2_1_1t = (TextView) cls2.findViewById(R.id.cls_1_1_t);
        SimpleDraweeView cls_2_1_2p = (SimpleDraweeView) cls2.findViewById(R.id.cls_1_2_p);
        cls_2_1_2t = (TextView) cls2.findViewById(R.id.cls_1_2_t);
        SimpleDraweeView cls_2_1_3p = (SimpleDraweeView) cls2.findViewById(R.id.cls_1_3_p);
        cls_2_1_3t = (TextView) cls2.findViewById(R.id.cls_1_3_t);
        SimpleDraweeView cls_2_1_4p = (SimpleDraweeView) cls2.findViewById(R.id.cls_1_4_p);
        cls_2_1_4t = (TextView) cls2.findViewById(R.id.cls_1_4_t);
        SimpleDraweeView cls_2_1_5p = (SimpleDraweeView) cls2.findViewById(R.id.cls_1_5_p);
        cls_2_1_5t = (TextView) cls2.findViewById(R.id.cls_1_5_t);
        SimpleDraweeView cls_2_1_6p = (SimpleDraweeView) cls2.findViewById(R.id.cls_1_6_p);
        cls_2_1_6t = (TextView) cls2.findViewById(R.id.cls_1_6_t);
        SimpleDraweeView cls_2_1_7p = (SimpleDraweeView) cls2.findViewById(R.id.cls_1_7_p);
        cls_2_1_7t = (TextView) cls2.findViewById(R.id.cls_1_7_t);
        SimpleDraweeView cls_2_1_8p = (SimpleDraweeView) cls2.findViewById(R.id.cls_1_8_p);
        cls_2_1_8t = (TextView) cls2.findViewById(R.id.cls_1_8_t);
        SimpleDraweeView cls_2_1_9p = (SimpleDraweeView) cls2.findViewById(R.id.cls_1_9_p);
        cls_2_1_9t = (TextView) cls2.findViewById(R.id.cls_1_9_t);
        cls_2_1t.setText("上 衣");


        cls_2_1_01p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/211.jpg"));
        cls_2_1_01t.setText("吊带背心");
        cls_2_1_02p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/209.jpg"));
        cls_2_1_02t.setText("T恤");
        cls_2_1_03p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/210.jpg"));
        cls_2_1_03t.setText("衬衣");


        cls_2_1_1p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/214.jpg"));
        cls_2_1_1t.setText("雪纺衫");
        cls_2_1_2p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/212.jpg"));
        cls_2_1_2t.setText("连衣裙");
        cls_2_1_3p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/98.jpg"));
        cls_2_1_3t.setText("针织衫");
        cls_2_1_4p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/107.jpg"));
        cls_2_1_4t.setText("卫衣");
        cls_2_1_5p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/135.jpg"));
        cls_2_1_5t.setText("夹克");
        cls_2_1_6p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/165.jpg"));
        cls_2_1_6t.setText("风衣");
        cls_2_1_7p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/174.jpg"));
        cls_2_1_7t.setText("毛呢大衣");
        cls_2_1_8p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/178.jpg"));
        cls_2_1_8t.setText("皮衣");
        cls_2_1_9p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/194.jpg"));
        cls_2_1_9t.setText("羽绒服");

        cls_2_2_1 = (LinearLayout) cls2.findViewById(R.id.cls_2_1);
        cls_2_2_2 = (LinearLayout) cls2.findViewById(R.id.cls_2_2);
        cls_2_2_3 = (LinearLayout) cls2.findViewById(R.id.cls_2_3);
        cls_2_2_4 = (LinearLayout) cls2.findViewById(R.id.cls_2_4);
        cls_2_2_5 = (LinearLayout) cls2.findViewById(R.id.cls_2_5);
        cls_2_2_6 = (LinearLayout) cls2.findViewById(R.id.cls_2_6);
        TextView cls_2_2t = (TextView) cls2.findViewById(R.id.cls_2);
        SimpleDraweeView cls_2_2_1p = (SimpleDraweeView) cls2.findViewById(R.id.cls_2_1_p);
        cls_2_2_1t = (TextView) cls2.findViewById(R.id.cls_2_1_t);
        SimpleDraweeView cls_2_2_2p = (SimpleDraweeView) cls2.findViewById(R.id.cls_2_2_p);
        cls_2_2_2t = (TextView) cls2.findViewById(R.id.cls_2_2_t);
        SimpleDraweeView cls_2_2_3p = (SimpleDraweeView) cls2.findViewById(R.id.cls_2_3_p);
        cls_2_2_3t = (TextView) cls2.findViewById(R.id.cls_2_3_t);
        SimpleDraweeView cls_2_2_4p = (SimpleDraweeView) cls2.findViewById(R.id.cls_2_4_p);
        cls_2_2_4t = (TextView) cls2.findViewById(R.id.cls_2_4_t);
        SimpleDraweeView cls_2_2_5p = (SimpleDraweeView) cls2.findViewById(R.id.cls_2_5_p);
        cls_2_2_5t = (TextView) cls2.findViewById(R.id.cls_2_5_t);
        SimpleDraweeView cls_2_2_6p = (SimpleDraweeView) cls2.findViewById(R.id.cls_2_6_p);
        cls_2_2_6t = (TextView) cls2.findViewById(R.id.cls_2_6_t);
        cls_2_2t.setText("下衣");
        cls_2_2_1p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/213.jpg"));
        cls_2_2_1t.setText("裙子");
        cls_2_2_2p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/13.jpg"));
        cls_2_2_2t.setText("打底裤");
        cls_2_2_3p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/45.jpg"));
        cls_2_2_3t.setText("牛仔裤");
        cls_2_2_4p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/73.jpg"));
        cls_2_2_4t.setText("休闲裤");
        cls_2_2_5p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/124.jpg"));
        cls_2_2_5t.setText("背带裤");
        cls_2_2_6p.setImageURI(Uri.parse(""));
        cls_2_2_6t.setText("阔腿裤");
        cls_2_2_6.setVisibility(View.GONE);

        cls_2_3_1 = (LinearLayout) cls2.findViewById(R.id.cls_3_1);
        cls_2_3_2 = (LinearLayout) cls2.findViewById(R.id.cls_3_2);
        cls_2_3_3 = (LinearLayout) cls2.findViewById(R.id.cls_3_3);
        cls_2_3_4 = (LinearLayout) cls2.findViewById(R.id.cls_3_4);
        cls_2_3t = (TextView) cls2.findViewById(R.id.cls_3);
        SimpleDraweeView cls_2_3_1p = (SimpleDraweeView) cls2.findViewById(R.id.cls_3_1_p);
        cls_2_3_1t = (TextView) cls2.findViewById(R.id.cls_3_1_t);
        SimpleDraweeView cls_2_3_2p = (SimpleDraweeView) cls2.findViewById(R.id.cls_3_2_p);
        cls_2_3_2t = (TextView) cls2.findViewById(R.id.cls_3_2_t);
        SimpleDraweeView cls_2_3_3p = (SimpleDraweeView) cls2.findViewById(R.id.cls_3_3_p);
        cls_2_3_3t = (TextView) cls2.findViewById(R.id.cls_3_3_t);
        SimpleDraweeView cls_2_3_4p = (SimpleDraweeView) cls2.findViewById(R.id.cls_3_4_p);
        cls_2_3_4t = (TextView) cls2.findViewById(R.id.cls_3_4_t);
        cls_2_3t.setText("内衣配件");
        cls_2_3_1p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/118.jpg"));
        cls_2_3_1t.setText("内衣");
        cls_2_3_2p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/57.jpg"));
        cls_2_3_2t.setText("睡衣");
        cls_2_3_3p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/96.jpg"));
        cls_2_3_3t.setText("袜子");
        cls_2_3_4p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/1.jpg"));
        cls_2_3_4t.setText("保暖");


        cls_2_1_01.setOnClickListener(new Cls2OnClickListeners());
        cls_2_1_02.setOnClickListener(new Cls2OnClickListeners());
        cls_2_1_03.setOnClickListener(new Cls2OnClickListeners());


        cls_2_1_1.setOnClickListener(new Cls2OnClickListeners());
        cls_2_1_2.setOnClickListener(new Cls2OnClickListeners());
        cls_2_1_3.setOnClickListener(new Cls2OnClickListeners());
        cls_2_1_4.setOnClickListener(new Cls2OnClickListeners());
        cls_2_1_5.setOnClickListener(new Cls2OnClickListeners());
        cls_2_1_6.setOnClickListener(new Cls2OnClickListeners());
        cls_2_1_7.setOnClickListener(new Cls2OnClickListeners());
        cls_2_1_8.setOnClickListener(new Cls2OnClickListeners());
        cls_2_1_9.setOnClickListener(new Cls2OnClickListeners());
        cls_2_2_1.setOnClickListener(new Cls2OnClickListeners());
        cls_2_2_2.setOnClickListener(new Cls2OnClickListeners());
        cls_2_2_3.setOnClickListener(new Cls2OnClickListeners());
        cls_2_2_4.setOnClickListener(new Cls2OnClickListeners());
        cls_2_2_5.setOnClickListener(new Cls2OnClickListeners());
        cls_2_2_6.setOnClickListener(new Cls2OnClickListeners());
        cls_2_3_1.setOnClickListener(new Cls2OnClickListeners());
        cls_2_3_2.setOnClickListener(new Cls2OnClickListeners());
        cls_2_3_3.setOnClickListener(new Cls2OnClickListeners());
        cls_2_3_4.setOnClickListener(new Cls2OnClickListeners());

        cls3 = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_view_detail_layout_3, null);
        cls_3_1_1 = (LinearLayout) cls3.findViewById(R.id.cls_1_1);
        cls_3_1_2 = (LinearLayout) cls3.findViewById(R.id.cls_1_2);
        cls_3_1_3 = (LinearLayout) cls3.findViewById(R.id.cls_1_3);
        cls_3_1_4 = (LinearLayout) cls3.findViewById(R.id.cls_1_4);
        cls_3_1_5 = (LinearLayout) cls3.findViewById(R.id.cls_1_5);
        cls_3_1_6 = (LinearLayout) cls3.findViewById(R.id.cls_1_6);
        cls_3_1_7 = (LinearLayout) cls3.findViewById(R.id.cls_1_7);
        cls_3_1_8 = (LinearLayout) cls3.findViewById(R.id.cls_1_8);
        cls_3_1_9 = (LinearLayout) cls3.findViewById(R.id.cls_1_9);
        cls_3_2_01 = (LinearLayout) cls3.findViewById(R.id.cls_2_01);
        cls_3_2_1 = (LinearLayout) cls3.findViewById(R.id.cls_2_1);
        cls_3_2_2 = (LinearLayout) cls3.findViewById(R.id.cls_2_2);
        cls_3_2_3 = (LinearLayout) cls3.findViewById(R.id.cls_2_3);
        cls_3_3_1 = (LinearLayout) cls3.findViewById(R.id.cls_3_1);
        cls_3_3_2 = (LinearLayout) cls3.findViewById(R.id.cls_3_2);
        cls_3_3_3 = (LinearLayout) cls3.findViewById(R.id.cls_3_3);
        cls_3_3_4 = (LinearLayout) cls3.findViewById(R.id.cls_3_4);
        cls_3_1_t = (TextView) cls3.findViewById(R.id.cls_1);
        cls_3_2_t = (TextView) cls3.findViewById(R.id.cls_2);
        cls_3_3_t = (TextView) cls3.findViewById(R.id.cls_3);
        cls_3_1_1_t = (TextView) cls3.findViewById(R.id.cls_1_1_t);
        cls_3_1_2_t = (TextView) cls3.findViewById(R.id.cls_1_2_t);
        cls_3_1_3_t = (TextView) cls3.findViewById(R.id.cls_1_3_t);
        cls_3_1_4_t = (TextView) cls3.findViewById(R.id.cls_1_4_t);
        cls_3_1_5_t = (TextView) cls3.findViewById(R.id.cls_1_5_t);
        cls_3_1_6_t = (TextView) cls3.findViewById(R.id.cls_1_6_t);
        cls_3_1_7_t = (TextView) cls3.findViewById(R.id.cls_1_7_t);
        cls_3_1_8_t = (TextView) cls3.findViewById(R.id.cls_1_8_t);
        cls_3_1_9_t = (TextView) cls3.findViewById(R.id.cls_1_9_t);
        cls_3_2_01_t = (TextView) cls3.findViewById(R.id.cls_2_01_t);
        cls_3_2_1_t = (TextView) cls3.findViewById(R.id.cls_2_1_t);
        cls_3_2_2_t = (TextView) cls3.findViewById(R.id.cls_2_2_t);
        cls_3_2_3_t = (TextView) cls3.findViewById(R.id.cls_2_3_t);
        cls_3_3_1_t = (TextView) cls3.findViewById(R.id.cls_3_1_t);
        cls_3_3_2_t = (TextView) cls3.findViewById(R.id.cls_3_2_t);
        cls_3_3_3_t = (TextView) cls3.findViewById(R.id.cls_3_3_t);
        cls_3_3_4_t = (TextView) cls3.findViewById(R.id.cls_3_4_t);
        SimpleDraweeView cls_3_1_1p = (SimpleDraweeView) cls3.findViewById(R.id.cls_1_1_p);
        SimpleDraweeView cls_3_1_2p = (SimpleDraweeView) cls3.findViewById(R.id.cls_1_2_p);
        SimpleDraweeView cls_3_1_3p = (SimpleDraweeView) cls3.findViewById(R.id.cls_1_3_p);
        SimpleDraweeView cls_3_1_4p = (SimpleDraweeView) cls3.findViewById(R.id.cls_1_4_p);
        SimpleDraweeView cls_3_1_5p = (SimpleDraweeView) cls3.findViewById(R.id.cls_1_5_p);
        SimpleDraweeView cls_3_1_6p = (SimpleDraweeView) cls3.findViewById(R.id.cls_1_6_p);
        SimpleDraweeView cls_3_1_7p = (SimpleDraweeView) cls3.findViewById(R.id.cls_1_7_p);
        SimpleDraweeView cls_3_1_8p = (SimpleDraweeView) cls3.findViewById(R.id.cls_1_8_p);
        SimpleDraweeView cls_3_1_9p = (SimpleDraweeView) cls3.findViewById(R.id.cls_1_9_p);
        SimpleDraweeView cls_3_2_01p = (SimpleDraweeView) cls3.findViewById(R.id.cls_2_01_p);
        SimpleDraweeView cls_3_2_1p = (SimpleDraweeView) cls3.findViewById(R.id.cls_2_1_p);
        SimpleDraweeView cls_3_2_2p = (SimpleDraweeView) cls3.findViewById(R.id.cls_2_2_p);
        SimpleDraweeView cls_3_2_3p = (SimpleDraweeView) cls3.findViewById(R.id.cls_2_3_p);
        SimpleDraweeView cls_3_3_1p = (SimpleDraweeView) cls3.findViewById(R.id.cls_3_1_p);
        SimpleDraweeView cls_3_3_2p = (SimpleDraweeView) cls3.findViewById(R.id.cls_3_2_p);
        SimpleDraweeView cls_3_3_3p = (SimpleDraweeView) cls3.findViewById(R.id.cls_3_3_p);
        SimpleDraweeView cls_3_3_4p = (SimpleDraweeView) cls3.findViewById(R.id.cls_3_4_p);
        cls_3_1_t.setText("上衣");
        cls_3_2_t.setText("下衣");
        cls_3_3_t.setText("内衣配件");
        cls_3_1_1_t.setText("背心");
        cls_3_1_2_t.setText("T恤");
        cls_3_1_3_t.setText("衬衫");
        cls_3_1_4_t.setText("卫衣");
        cls_3_1_5_t.setText("针织衫");
        cls_3_1_6_t.setText("夹克");
        cls_3_1_7_t.setText("风衣");
        cls_3_1_8_t.setText("皮衣");
        cls_3_1_9_t.setText("羽绒服");

        cls_3_2_01_t.setText("休闲裤");
        cls_3_2_1_t.setText("短裤");
        cls_3_2_2_t.setText("牛仔裤");
        cls_3_2_3_t.setText("卫裤");
        cls_3_3_1_t.setText("内衣");
        cls_3_3_2_t.setText("睡衣");
        cls_3_3_3_t.setText("袜子");
        cls_3_3_4_t.setText("保暖");
        cls_3_1_1p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/235.jpg"));
        cls_3_1_2p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/234.jpg"));
        cls_3_1_3p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/236.jpg"));
        cls_3_1_4p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/4.jpg"));
        cls_3_1_5p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/127.jpg"));
        cls_3_1_6p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/134.jpg"));
        cls_3_1_7p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/164.jpg"));
        cls_3_1_8p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/178.jpg"));
        cls_3_1_9p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/187.jpg"));
        cls_3_2_01p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/72.jpg"));
        cls_3_2_1p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/55.jpg"));
        cls_3_2_2p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/218.jpg"));
        cls_3_2_3p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/44.jpg"));
        cls_3_3_1p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/76.jpg"));
        // TODO: 01/09/2016
        cls_3_3_2p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/238.jpg"));
        cls_3_3_3p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/125.jpg"));
        cls_3_3_4p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/2.jpg"));

        cls_3_1_1.setOnClickListener(new Cls3OnClickListeners());
        cls_3_1_2.setOnClickListener(new Cls3OnClickListeners());
        cls_3_1_3.setOnClickListener(new Cls3OnClickListeners());
        cls_3_1_4.setOnClickListener(new Cls3OnClickListeners());
        cls_3_1_5.setOnClickListener(new Cls3OnClickListeners());
        cls_3_1_6.setOnClickListener(new Cls3OnClickListeners());
        cls_3_1_7.setOnClickListener(new Cls3OnClickListeners());
        cls_3_1_8.setOnClickListener(new Cls3OnClickListeners());
        cls_3_1_9.setOnClickListener(new Cls3OnClickListeners());
        cls_3_2_01.setOnClickListener(new Cls3OnClickListeners());
        cls_3_2_1.setOnClickListener(new Cls3OnClickListeners());
        cls_3_2_2.setOnClickListener(new Cls3OnClickListeners());
        cls_3_2_3.setOnClickListener(new Cls3OnClickListeners());
        cls_3_3_1.setOnClickListener(new Cls3OnClickListeners());
        cls_3_3_2.setOnClickListener(new Cls3OnClickListeners());
        cls_3_3_3.setOnClickListener(new Cls3OnClickListeners());
        cls_3_3_4.setOnClickListener(new Cls3OnClickListeners());

        cls4 = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_view_detail_layout_4, null);
        cls_4_1_1 = (LinearLayout) cls4.findViewById(R.id.cls_1_1);
        cls_4_1_2 = (LinearLayout) cls4.findViewById(R.id.cls_1_2);
        cls_4_1_3 = (LinearLayout) cls4.findViewById(R.id.cls_1_3);
        cls_4_1_4 = (LinearLayout) cls4.findViewById(R.id.cls_1_4);
        cls_4_1_5 = (LinearLayout) cls4.findViewById(R.id.cls_1_5);
        cls_4_1_6 = (LinearLayout) cls4.findViewById(R.id.cls_1_6);
        cls_4_1_7 = (LinearLayout) cls4.findViewById(R.id.cls_1_7);
        cls_4_1_8 = (LinearLayout) cls4.findViewById(R.id.cls_1_8);
        cls_4_2_1 = (LinearLayout) cls4.findViewById(R.id.cls_2_1);
        cls_4_2_2 = (LinearLayout) cls4.findViewById(R.id.cls_2_2);
        cls_4_2_3 = (LinearLayout) cls4.findViewById(R.id.cls_2_3);
        cls_4_2_4 = (LinearLayout) cls4.findViewById(R.id.cls_2_4);
        cls_4_3_1 = (LinearLayout) cls4.findViewById(R.id.cls_3_1);
        cls_4_3_2 = (LinearLayout) cls4.findViewById(R.id.cls_3_2);
        cls_4_3_3 = (LinearLayout) cls4.findViewById(R.id.cls_3_3);
        cls_4_4_1 = (LinearLayout) cls4.findViewById(R.id.cls_4_1);
        cls_4_4_2 = (LinearLayout) cls4.findViewById(R.id.cls_4_2);
        cls_4_4_3 = (LinearLayout) cls4.findViewById(R.id.cls_4_3);
        cls_4_4_4 = (LinearLayout) cls4.findViewById(R.id.cls_4_4);
        cls_4_4_5 = (LinearLayout) cls4.findViewById(R.id.cls_4_5);
        cls_4_4_6 = (LinearLayout) cls4.findViewById(R.id.cls_4_6);
        cls_4_4_7 = (LinearLayout) cls4.findViewById(R.id.cls_4_7);
        cls_4_5_1 = (LinearLayout) cls4.findViewById(R.id.cls_5_1);
        TextView cls_4_1_t = (TextView) cls4.findViewById(R.id.cls_1);
        TextView cls_4_2_t = (TextView) cls4.findViewById(R.id.cls_2);
        TextView cls_4_3_t = (TextView) cls4.findViewById(R.id.cls_3);
        TextView cls_4_4_t = (TextView) cls4.findViewById(R.id.cls_4);
        TextView cls_4_5_t = (TextView) cls4.findViewById(R.id.cls_5);
        cls_4_1_1_t = (TextView) cls4.findViewById(R.id.cls_1_1_t);
        cls_4_1_2_t = (TextView) cls4.findViewById(R.id.cls_1_2_t);
        cls_4_1_3_t = (TextView) cls4.findViewById(R.id.cls_1_3_t);
        cls_4_1_4_t = (TextView) cls4.findViewById(R.id.cls_1_4_t);
        cls_4_1_5_t = (TextView) cls4.findViewById(R.id.cls_1_5_t);
        cls_4_1_6_t = (TextView) cls4.findViewById(R.id.cls_1_6_t);
        cls_4_1_7_t = (TextView) cls4.findViewById(R.id.cls_1_7_t);
        cls_4_1_8_t = (TextView) cls4.findViewById(R.id.cls_1_8_t);
        cls_4_2_1_t = (TextView) cls4.findViewById(R.id.cls_2_1_t);
        cls_4_2_2_t = (TextView) cls4.findViewById(R.id.cls_2_2_t);
        cls_4_2_3_t = (TextView) cls4.findViewById(R.id.cls_2_3_t);
        cls_4_2_4_t = (TextView) cls4.findViewById(R.id.cls_2_4_t);
        cls_4_3_1_t = (TextView) cls4.findViewById(R.id.cls_3_1_t);
        cls_4_3_2_t = (TextView) cls4.findViewById(R.id.cls_3_2_t);
        cls_4_3_3_t = (TextView) cls4.findViewById(R.id.cls_3_3_t);
        cls_4_4_1_t = (TextView) cls4.findViewById(R.id.cls_4_1_t);
        cls_4_4_2_t = (TextView) cls4.findViewById(R.id.cls_4_2_t);
        cls_4_4_3_t = (TextView) cls4.findViewById(R.id.cls_4_3_t);
        cls_4_4_4_t = (TextView) cls4.findViewById(R.id.cls_4_4_t);
        cls_4_4_5_t = (TextView) cls4.findViewById(R.id.cls_4_5_t);
        cls_4_4_6_t = (TextView) cls4.findViewById(R.id.cls_4_6_t);
        cls_4_4_7_t = (TextView) cls4.findViewById(R.id.cls_4_7_t);
        cls_4_5_1_t = (TextView) cls4.findViewById(R.id.cls_5_1_t);

        SimpleDraweeView cls_4_1_1_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_1_1_p);
        SimpleDraweeView cls_4_1_2_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_1_2_p);
        SimpleDraweeView cls_4_1_3_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_1_3_p);
        SimpleDraweeView cls_4_1_4_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_1_4_p);
        SimpleDraweeView cls_4_1_5_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_1_5_p);
        SimpleDraweeView cls_4_1_6_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_1_6_p);
        SimpleDraweeView cls_4_1_7_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_1_7_p);
        SimpleDraweeView cls_4_1_8_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_1_8_p);
        SimpleDraweeView cls_4_2_1_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_2_1_p);
        SimpleDraweeView cls_4_2_2_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_2_2_p);
        SimpleDraweeView cls_4_2_3_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_2_3_p);
        SimpleDraweeView cls_4_2_4_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_2_4_p);
        SimpleDraweeView cls_4_3_1_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_3_1_p);
        SimpleDraweeView cls_4_3_2_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_3_2_p);
        SimpleDraweeView cls_4_3_3_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_3_3_p);
        SimpleDraweeView cls_4_4_1_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_4_1_p);
        SimpleDraweeView cls_4_4_2_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_4_2_p);
        SimpleDraweeView cls_4_4_3_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_4_3_p);
        SimpleDraweeView cls_4_4_4_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_4_4_p);
        SimpleDraweeView cls_4_4_5_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_4_5_p);
        SimpleDraweeView cls_4_4_6_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_4_6_p);
        SimpleDraweeView cls_4_4_7_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_4_7_p);
        SimpleDraweeView cls_4_5_1_p = (SimpleDraweeView) cls4.findViewById(R.id.cls_5_1_p);
        cls_4_1_t.setText("女鞋");
        cls_4_2_t.setText("女包");
        cls_4_3_t.setText("男包");
        cls_4_4_t.setText("男鞋");
        cls_4_5_t.setText("功能箱包");
        cls_4_1_1_t.setText("凉鞋");
        cls_4_1_2_t.setText("帆布鞋");
        cls_4_1_3_t.setText("休闲鞋");
        cls_4_1_4_t.setText("运动鞋");
        cls_4_1_5_t.setText("高跟鞋");
        cls_4_1_6_t.setText("增高鞋");
        cls_4_1_7_t.setText("女靴");
        cls_4_1_8_t.setText("拖鞋");
        cls_4_2_1_t.setText("手拿包");
        cls_4_2_2_t.setText("手提包");
        cls_4_2_3_t.setText("单肩包");
        cls_4_2_4_t.setText("双肩包");
        cls_4_3_1_t.setText("钱包");
        cls_4_3_2_t.setText("单肩包");
        cls_4_3_3_t.setText("潮流双肩");
        cls_4_4_1_t.setText("凉鞋");
        cls_4_4_2_t.setText("帆布鞋");
        cls_4_4_3_t.setText("休闲鞋");
        cls_4_4_4_t.setText("运动鞋");
        cls_4_4_5_t.setText("男靴");
        cls_4_4_6_t.setText("皮鞋");
        cls_4_4_7_t.setText("拖鞋");
        cls_4_5_1_t.setText("拉杆箱");
        cls_4_1_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/240.jpg"));
        cls_4_1_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/10.jpg"));
        cls_4_1_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/40.jpg"));
        cls_4_1_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/97.jpg"));
        cls_4_1_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/167.jpg"));
        cls_4_1_6_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/244.jpg"));
        cls_4_1_7_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/241.jpg"));
        cls_4_1_8_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/243.jpg"));
        cls_4_2_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/11.jpg"));
        cls_4_2_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/47.jpg"));
        cls_4_2_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/78.jpg"));
        cls_4_2_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/109.jpg"));
        cls_4_3_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/33.jpg"));
        cls_4_3_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/43.jpg"));
        // TODO: 01/09/2016
        cls_4_3_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/108.jpg"));


        cls_4_4_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/239.jpg"));
        cls_4_4_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/9.jpg"));
        cls_4_4_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/39.jpg"));
        cls_4_4_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/91.jpg"));
        cls_4_4_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/121.jpg"));
        cls_4_4_6_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/143.jpg"));
        cls_4_4_7_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/242.jpg"));
        cls_4_5_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/208.jpg"));


        cls_4_1_1.setOnClickListener(new Cls4OnClickListeners());
        cls_4_1_2.setOnClickListener(new Cls4OnClickListeners());
        cls_4_1_3.setOnClickListener(new Cls4OnClickListeners());
        cls_4_1_4.setOnClickListener(new Cls4OnClickListeners());
        cls_4_1_5.setOnClickListener(new Cls4OnClickListeners());
        cls_4_1_6.setOnClickListener(new Cls4OnClickListeners());
        cls_4_1_7.setOnClickListener(new Cls4OnClickListeners());
        cls_4_1_8.setOnClickListener(new Cls4OnClickListeners());
        cls_4_2_1.setOnClickListener(new Cls4OnClickListeners());
        cls_4_2_2.setOnClickListener(new Cls4OnClickListeners());
        cls_4_2_3.setOnClickListener(new Cls4OnClickListeners());
        cls_4_2_4.setOnClickListener(new Cls4OnClickListeners());
        cls_4_3_1.setOnClickListener(new Cls4OnClickListeners());
        cls_4_3_2.setOnClickListener(new Cls4OnClickListeners());
        cls_4_3_3.setOnClickListener(new Cls4OnClickListeners());
        cls_4_4_1.setOnClickListener(new Cls4OnClickListeners());
        cls_4_4_2.setOnClickListener(new Cls4OnClickListeners());
        cls_4_4_3.setOnClickListener(new Cls4OnClickListeners());
        cls_4_4_4.setOnClickListener(new Cls4OnClickListeners());
        cls_4_4_5.setOnClickListener(new Cls4OnClickListeners());
        cls_4_4_6.setOnClickListener(new Cls4OnClickListeners());
        cls_4_4_7.setOnClickListener(new Cls4OnClickListeners());
        cls_4_5_1.setOnClickListener(new Cls4OnClickListeners());

        cls5 = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_view_detail_layout_5, null);
        cls_5_1_1 = (LinearLayout) cls5.findViewById(R.id.cls_1_1);
        cls_5_1_2 = (LinearLayout) cls5.findViewById(R.id.cls_1_2);
        cls_5_1_3 = (LinearLayout) cls5.findViewById(R.id.cls_1_3);
        cls_5_1_4 = (LinearLayout) cls5.findViewById(R.id.cls_1_4);
        cls_5_1_5 = (LinearLayout) cls5.findViewById(R.id.cls_1_5);
        cls_5_1_6 = (LinearLayout) cls5.findViewById(R.id.cls_1_6);
        cls_5_1_7 = (LinearLayout) cls5.findViewById(R.id.cls_1_7);
        cls_5_1_8 = (LinearLayout) cls5.findViewById(R.id.cls_1_8);
        cls_5_2_1 = (LinearLayout) cls5.findViewById(R.id.cls_2_1);
        cls_5_2_2 = (LinearLayout) cls5.findViewById(R.id.cls_2_2);
        cls_5_2_3 = (LinearLayout) cls5.findViewById(R.id.cls_2_3);
        cls_5_3_1 = (LinearLayout) cls5.findViewById(R.id.cls_3_1);
        cls_5_3_2 = (LinearLayout) cls5.findViewById(R.id.cls_3_2);
        cls_5_3_3 = (LinearLayout) cls5.findViewById(R.id.cls_3_3);
        cls_5_3_4 = (LinearLayout) cls5.findViewById(R.id.cls_3_4);
        cls_5_3_5 = (LinearLayout) cls5.findViewById(R.id.cls_3_5);
        cls_5_3_6 = (LinearLayout) cls5.findViewById(R.id.cls_3_6);
        cls_5_3_7 = (LinearLayout) cls5.findViewById(R.id.cls_3_7);
        cls_5_3_8 = (LinearLayout) cls5.findViewById(R.id.cls_3_8);
        TextView cls_5_1_t = (TextView) cls5.findViewById(R.id.cls_1);
        TextView cls_5_2_t = (TextView) cls5.findViewById(R.id.cls_2);
        TextView cls_5_3_t = (TextView) cls5.findViewById(R.id.cls_3);
        cls_5_1_1_t = (TextView) cls5.findViewById(R.id.cls_1_1_t);
        cls_5_1_2_t = (TextView) cls5.findViewById(R.id.cls_1_2_t);
        cls_5_1_3_t = (TextView) cls5.findViewById(R.id.cls_1_3_t);
        cls_5_1_4_t = (TextView) cls5.findViewById(R.id.cls_1_4_t);
        cls_5_1_5_t = (TextView) cls5.findViewById(R.id.cls_1_5_t);
        cls_5_1_6_t = (TextView) cls5.findViewById(R.id.cls_1_6_t);
        cls_5_1_7_t = (TextView) cls5.findViewById(R.id.cls_1_7_t);
        cls_5_1_8_t = (TextView) cls5.findViewById(R.id.cls_1_8_t);
        cls_5_2_1_t = (TextView) cls5.findViewById(R.id.cls_2_1_t);
        cls_5_2_2_t = (TextView) cls5.findViewById(R.id.cls_2_2_t);
        cls_5_2_3_t = (TextView) cls5.findViewById(R.id.cls_2_3_t);
        cls_5_3_1_t = (TextView) cls5.findViewById(R.id.cls_3_1_t);
        cls_5_3_2_t = (TextView) cls5.findViewById(R.id.cls_3_2_t);
        cls_5_3_3_t = (TextView) cls5.findViewById(R.id.cls_3_3_t);
        cls_5_3_4_t = (TextView) cls5.findViewById(R.id.cls_3_4_t);
        cls_5_3_5_t = (TextView) cls5.findViewById(R.id.cls_3_5_t);
        cls_5_3_6_t = (TextView) cls5.findViewById(R.id.cls_3_6_t);
        cls_5_3_7_t = (TextView) cls5.findViewById(R.id.cls_3_7_t);
        cls_5_3_8_t = (TextView) cls5.findViewById(R.id.cls_3_8_t);
        SimpleDraweeView cls_5_1_1_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_1_1_p);
        SimpleDraweeView cls_5_1_2_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_1_2_p);
        SimpleDraweeView cls_5_1_3_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_1_3_p);
        SimpleDraweeView cls_5_1_4_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_1_4_p);
        SimpleDraweeView cls_5_1_5_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_1_5_p);
        SimpleDraweeView cls_5_1_6_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_1_6_p);
        SimpleDraweeView cls_5_1_7_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_1_7_p);
        SimpleDraweeView cls_5_1_8_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_1_8_p);
        SimpleDraweeView cls_5_2_1_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_2_1_p);
        SimpleDraweeView cls_5_2_2_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_2_2_p);
        SimpleDraweeView cls_5_2_3_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_2_3_p);
        SimpleDraweeView cls_5_3_1_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_3_1_p);
        SimpleDraweeView cls_5_3_2_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_3_2_p);
        SimpleDraweeView cls_5_3_3_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_3_3_p);
        SimpleDraweeView cls_5_3_4_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_3_4_p);
        SimpleDraweeView cls_5_3_5_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_3_5_p);
        SimpleDraweeView cls_5_3_6_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_3_6_p);
        SimpleDraweeView cls_5_3_7_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_3_7_p);
        SimpleDraweeView cls_5_3_8_p = (SimpleDraweeView) cls5.findViewById(R.id.cls_3_8_p);
        cls_5_1_t.setText("手机及配件");
        cls_5_2_t.setText("智能装备");
        cls_5_3_t.setText("摄影摄像");
        cls_5_1_1_t.setText("手机");
        cls_5_1_2_t.setText("耳机耳麦");
        cls_5_1_3_t.setText("蓝牙耳机");
        cls_5_1_4_t.setText("充电器数据线");
        cls_5_1_5_t.setText("无线音箱");
        cls_5_1_6_t.setText("充电宝");
        cls_5_1_7_t.setText("内存卡");
        cls_5_1_8_t.setText("自拍杆");
        cls_5_2_1_t.setText("智能手表");
        cls_5_2_2_t.setText("智能眼镜");
        cls_5_2_3_t.setText("智能配饰");
        cls_5_3_1_t.setText("数码相机");
        cls_5_3_2_t.setText("单反相机");
        cls_5_3_3_t.setText("单电微单相机");
        cls_5_3_4_t.setText("运动相机");
        cls_5_3_5_t.setText("拍立得相机");
        cls_5_3_6_t.setText("摄像机");
        cls_5_3_7_t.setText("单反镜头");
        cls_5_3_8_t.setText("摄相器材");
        cls_5_1_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/12.jpg"));
        cls_5_1_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/61.jpg"));
        cls_5_1_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/93.jpg"));
        cls_5_1_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/117.jpg"));
        cls_5_1_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/139.jpg"));
        cls_5_1_6_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/161.jpg"));
        cls_5_1_7_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/170.jpg"));
        cls_5_1_8_p.setImageURI(Uri.parse(""));
        cls_5_2_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/18.jpg"));
        cls_5_2_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/49.jpg"));
        cls_5_2_3_p.setImageURI(Uri.parse(""));
        cls_5_3_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/14.jpg"));
        cls_5_3_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/42.jpg"));
        cls_5_3_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/77.jpg"));
        cls_5_3_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/126.jpg"));
        cls_5_3_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/138.jpg"));
        cls_5_3_6_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/156.jpg"));
        cls_5_3_7_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/171.jpg"));
        cls_5_3_8_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/181.jpg"));
        cls_5_1_1.setOnClickListener(new Cls5OnClickListeners());
        cls_5_1_2.setOnClickListener(new Cls5OnClickListeners());
        cls_5_1_3.setOnClickListener(new Cls5OnClickListeners());
        cls_5_1_4.setOnClickListener(new Cls5OnClickListeners());
        cls_5_1_5.setOnClickListener(new Cls5OnClickListeners());
        cls_5_1_6.setOnClickListener(new Cls5OnClickListeners());
        cls_5_1_7.setOnClickListener(new Cls5OnClickListeners());
        cls_5_1_8.setOnClickListener(new Cls5OnClickListeners());
        cls_5_2_1.setOnClickListener(new Cls5OnClickListeners());
        cls_5_2_2.setOnClickListener(new Cls5OnClickListeners());
        cls_5_2_3.setOnClickListener(new Cls5OnClickListeners());
        cls_5_3_1.setOnClickListener(new Cls5OnClickListeners());
        cls_5_3_2.setOnClickListener(new Cls5OnClickListeners());
        cls_5_3_3.setOnClickListener(new Cls5OnClickListeners());
        cls_5_3_4.setOnClickListener(new Cls5OnClickListeners());
        cls_5_3_5.setOnClickListener(new Cls5OnClickListeners());
        cls_5_3_6.setOnClickListener(new Cls5OnClickListeners());
        cls_5_3_7.setOnClickListener(new Cls5OnClickListeners());
        cls_5_3_8.setOnClickListener(new Cls5OnClickListeners());

        cls6 = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_view_detail_layout_6, null);
        cls_6_1_1 = (LinearLayout) cls6.findViewById(R.id.cls_1_1);
        cls_6_1_2 = (LinearLayout) cls6.findViewById(R.id.cls_1_2);
        cls_6_1_3 = (LinearLayout) cls6.findViewById(R.id.cls_1_3);
        cls_6_1_4 = (LinearLayout) cls6.findViewById(R.id.cls_1_4);
        cls_6_1_5 = (LinearLayout) cls6.findViewById(R.id.cls_1_5);
        cls_6_1_6 = (LinearLayout) cls6.findViewById(R.id.cls_1_6);
        cls_6_1_7 = (LinearLayout) cls6.findViewById(R.id.cls_1_7);
        cls_6_2_1 = (LinearLayout) cls6.findViewById(R.id.cls_2_1);
        cls_6_2_2 = (LinearLayout) cls6.findViewById(R.id.cls_2_2);
        cls_6_2_3 = (LinearLayout) cls6.findViewById(R.id.cls_2_3);
        cls_6_2_4 = (LinearLayout) cls6.findViewById(R.id.cls_2_4);
        cls_6_2_5 = (LinearLayout) cls6.findViewById(R.id.cls_2_5);
        cls_6_2_6 = (LinearLayout) cls6.findViewById(R.id.cls_2_6);
        cls_6_2_7 = (LinearLayout) cls6.findViewById(R.id.cls_2_7);
        cls_6_2_8 = (LinearLayout) cls6.findViewById(R.id.cls_2_8);
        cls_6_2_9 = (LinearLayout) cls6.findViewById(R.id.cls_2_9);
        cls_6_2_10 = (LinearLayout) cls6.findViewById(R.id.cls_2_10);
        cls_6_2_11 = (LinearLayout) cls6.findViewById(R.id.cls_2_11);
        cls_6_2_12 = (LinearLayout) cls6.findViewById(R.id.cls_2_12);
        cls_6_2_13 = (LinearLayout) cls6.findViewById(R.id.cls_2_13);
        cls_6_2_14 = (LinearLayout) cls6.findViewById(R.id.cls_2_14);
        cls_6_2_15 = (LinearLayout) cls6.findViewById(R.id.cls_2_15);
        cls_6_2_16 = (LinearLayout) cls6.findViewById(R.id.cls_2_16);
        cls_6_2_17 = (LinearLayout) cls6.findViewById(R.id.cls_2_17);
        TextView cls_6_1_t = (TextView) cls6.findViewById(R.id.cls_1);
        TextView cls_6_2_t = (TextView) cls6.findViewById(R.id.cls_2);
        cls_6_1_1_t = (TextView) cls6.findViewById(R.id.cls_1_1_t);
        cls_6_1_2_t = (TextView) cls6.findViewById(R.id.cls_1_2_t);
        cls_6_1_3_t = (TextView) cls6.findViewById(R.id.cls_1_3_t);
        cls_6_1_4_t = (TextView) cls6.findViewById(R.id.cls_1_4_t);
        cls_6_1_5_t = (TextView) cls6.findViewById(R.id.cls_1_5_t);
        cls_6_1_6_t = (TextView) cls6.findViewById(R.id.cls_1_6_t);
        cls_6_1_7_t = (TextView) cls6.findViewById(R.id.cls_1_7_t);
        cls_6_2_1_t = (TextView) cls6.findViewById(R.id.cls_2_1_t);
        cls_6_2_2_t = (TextView) cls6.findViewById(R.id.cls_2_2_t);
        cls_6_2_3_t = (TextView) cls6.findViewById(R.id.cls_2_3_t);
        cls_6_2_4_t = (TextView) cls6.findViewById(R.id.cls_2_4_t);
        cls_6_2_5_t = (TextView) cls6.findViewById(R.id.cls_2_5_t);
        cls_6_2_6_t = (TextView) cls6.findViewById(R.id.cls_2_6_t);
        cls_6_2_7_t = (TextView) cls6.findViewById(R.id.cls_2_7_t);
        cls_6_2_8_t = (TextView) cls6.findViewById(R.id.cls_2_8_t);
        cls_6_2_9_t = (TextView) cls6.findViewById(R.id.cls_2_9_t);
        cls_6_2_10_t = (TextView) cls6.findViewById(R.id.cls_2_10_t);
        cls_6_2_11_t = (TextView) cls6.findViewById(R.id.cls_2_11_t);
        cls_6_2_12_t = (TextView) cls6.findViewById(R.id.cls_2_12_t);
        cls_6_2_13_t = (TextView) cls6.findViewById(R.id.cls_2_13_t);
        cls_6_2_14_t = (TextView) cls6.findViewById(R.id.cls_2_14_t);
        cls_6_2_15_t = (TextView) cls6.findViewById(R.id.cls_2_15_t);
        cls_6_2_16_t = (TextView) cls6.findViewById(R.id.cls_2_16_t);
        cls_6_2_17_t = (TextView) cls6.findViewById(R.id.cls_2_17_t);
        SimpleDraweeView cls_6_1_1_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_1_1_p);
        SimpleDraweeView cls_6_1_2_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_1_2_p);
        SimpleDraweeView cls_6_1_3_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_1_3_p);
        SimpleDraweeView cls_6_1_4_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_1_4_p);
        SimpleDraweeView cls_6_1_5_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_1_5_p);
        SimpleDraweeView cls_6_1_6_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_1_6_p);
        SimpleDraweeView cls_6_1_7_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_1_7_p);
        SimpleDraweeView cls_6_2_1_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_1_p);
        SimpleDraweeView cls_6_2_2_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_2_p);
        SimpleDraweeView cls_6_2_3_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_3_p);
        SimpleDraweeView cls_6_2_4_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_4_p);
        SimpleDraweeView cls_6_2_5_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_5_p);
        SimpleDraweeView cls_6_2_6_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_6_p);
        SimpleDraweeView cls_6_2_7_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_7_p);
        SimpleDraweeView cls_6_2_8_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_8_p);
        SimpleDraweeView cls_6_2_9_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_9_p);
        SimpleDraweeView cls_6_2_10_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_10_p);
        SimpleDraweeView cls_6_2_11_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_11_p);
        SimpleDraweeView cls_6_2_12_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_12_p);
        SimpleDraweeView cls_6_2_13_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_13_p);
        SimpleDraweeView cls_6_2_14_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_14_p);
        SimpleDraweeView cls_6_2_15_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_15_p);
        SimpleDraweeView cls_6_2_16_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_16_p);
        SimpleDraweeView cls_6_2_17_p = (SimpleDraweeView) cls6.findViewById(R.id.cls_2_17_p);
        cls_6_1_t.setText("男人妆扮");
        cls_6_2_t.setText("女人妆扮");
        cls_6_1_1_t.setText("洁面");
        cls_6_1_2_t.setText("防晒");
        cls_6_1_3_t.setText("面膜");
        cls_6_1_4_t.setText("乳液");
        cls_6_1_5_t.setText("爽肤");
        cls_6_1_6_t.setText("套装");
        cls_6_1_7_t.setText("剃须必备");
        cls_6_2_1_t.setText("洁面");
        cls_6_2_2_t.setText("防晒");
        cls_6_2_3_t.setText("面膜");
        cls_6_2_4_t.setText("套装");
        cls_6_2_5_t.setText("护手霜");
        cls_6_2_6_t.setText("乳液");
        cls_6_2_7_t.setText("爽肤水");
        cls_6_2_8_t.setText("去角质");
        cls_6_2_9_t.setText("隔离霜");
        cls_6_2_10_t.setText("卸妆");
        cls_6_2_11_t.setText("唇膏");
        cls_6_2_12_t.setText("粉底");
        cls_6_2_13_t.setText("腮红");
        cls_6_2_14_t.setText("香水");
        cls_6_2_15_t.setText("眼部美妆");
        cls_6_2_16_t.setText("精油");
        cls_6_2_17_t.setText("脱毛膏");
        cls_6_1_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/22.jpg"));
        cls_6_1_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/215.jpg"));
        cls_6_1_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/66.jpg"));
        cls_6_1_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/104.jpg"));
        cls_6_1_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/141.jpg"));
        cls_6_1_6_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/82.jpg"));
        cls_6_1_7_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/152.jpg"));
        cls_6_2_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/23.jpg"));
        cls_6_2_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/216.jpg"));
        cls_6_2_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/67.jpg"));
        cls_6_2_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/83.jpg"));
        cls_6_2_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/116.jpg"));
        cls_6_2_6_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/130.jpg"));
        cls_6_2_7_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/157.jpg"));
        cls_6_2_8_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/172.jpg"));
        cls_6_2_9_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/188.jpg"));
        cls_6_2_10_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/189.jpg"));
        cls_6_2_11_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/197.jpg"));
        cls_6_2_12_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/199.jpg"));
        cls_6_2_13_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/201.jpg"));
        cls_6_2_14_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/204.jpg"));//http://www.qkxmall.com/uploadfile/classes/203.jpg
        cls_6_2_15_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/203.jpg"));
        cls_6_2_16_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/205.jpg"));
        cls_6_2_17_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/206.jpg"));
        cls_6_1_1.setOnClickListener(new Clc6OnClickListeners());
        cls_6_1_2.setOnClickListener(new Clc6OnClickListeners());
        cls_6_1_3.setOnClickListener(new Clc6OnClickListeners());
        cls_6_1_4.setOnClickListener(new Clc6OnClickListeners());
        cls_6_1_5.setOnClickListener(new Clc6OnClickListeners());
        cls_6_1_6.setOnClickListener(new Clc6OnClickListeners());
        cls_6_1_7.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_1.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_2.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_3.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_4.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_5.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_6.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_7.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_8.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_9.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_10.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_11.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_12.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_13.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_14.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_15.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_16.setOnClickListener(new Clc6OnClickListeners());
        cls_6_2_17.setOnClickListener(new Clc6OnClickListeners());

        cls7 = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_view_detail_layout_7, null);
        cls_7_1_1 = (LinearLayout) cls7.findViewById(R.id.cls_1_1);
        cls_7_1_2 = (LinearLayout) cls7.findViewById(R.id.cls_1_2);
        cls_7_1_3 = (LinearLayout) cls7.findViewById(R.id.cls_1_3);
        cls_7_1_4 = (LinearLayout) cls7.findViewById(R.id.cls_1_4);
        cls_7_1_5 = (LinearLayout) cls7.findViewById(R.id.cls_1_5);
        cls_7_2_1 = (LinearLayout) cls7.findViewById(R.id.cls_2_1);
        cls_7_2_2 = (LinearLayout) cls7.findViewById(R.id.cls_2_2);
        cls_7_2_3 = (LinearLayout) cls7.findViewById(R.id.cls_2_3);
        cls_7_2_4 = (LinearLayout) cls7.findViewById(R.id.cls_2_4);
        cls_7_2_5 = (LinearLayout) cls7.findViewById(R.id.cls_2_5);
        cls_7_2_6 = (LinearLayout) cls7.findViewById(R.id.cls_2_6);
        TextView cls_7_1_t = (TextView) cls7.findViewById(R.id.cls_1);
        TextView cls_7_2_t = (TextView) cls7.findViewById(R.id.cls_2);
        cls_7_1_1_t = (TextView) cls7.findViewById(R.id.cls_1_1_t);
        cls_7_1_2_t = (TextView) cls7.findViewById(R.id.cls_1_2_t);
        cls_7_1_3_t = (TextView) cls7.findViewById(R.id.cls_1_3_t);
        cls_7_1_4_t = (TextView) cls7.findViewById(R.id.cls_1_4_t);
        cls_7_1_5_t = (TextView) cls7.findViewById(R.id.cls_1_5_t);
        cls_7_2_1_t = (TextView) cls7.findViewById(R.id.cls_2_1_t);
        cls_7_2_2_t = (TextView) cls7.findViewById(R.id.cls_2_2_t);
        cls_7_2_3_t = (TextView) cls7.findViewById(R.id.cls_2_3_t);
        cls_7_2_4_t = (TextView) cls7.findViewById(R.id.cls_2_4_t);
        cls_7_2_5_t = (TextView) cls7.findViewById(R.id.cls_2_5_t);
        cls_7_2_6_t = (TextView) cls7.findViewById(R.id.cls_2_6_t);
        SimpleDraweeView cls_7_1_1_p = (SimpleDraweeView) cls7.findViewById(R.id.cls_1_1_p);
        SimpleDraweeView cls_7_1_2_p = (SimpleDraweeView) cls7.findViewById(R.id.cls_1_2_p);
        SimpleDraweeView cls_7_1_3_p = (SimpleDraweeView) cls7.findViewById(R.id.cls_1_3_p);
        SimpleDraweeView cls_7_1_4_p = (SimpleDraweeView) cls7.findViewById(R.id.cls_1_4_p);
        SimpleDraweeView cls_7_1_5_p = (SimpleDraweeView) cls7.findViewById(R.id.cls_1_5_p);
        SimpleDraweeView cls_7_2_1_p = (SimpleDraweeView) cls7.findViewById(R.id.cls_2_1_p);
        SimpleDraweeView cls_7_2_2_p = (SimpleDraweeView) cls7.findViewById(R.id.cls_2_2_p);
        SimpleDraweeView cls_7_2_3_p = (SimpleDraweeView) cls7.findViewById(R.id.cls_2_3_p);
        SimpleDraweeView cls_7_2_4_p = (SimpleDraweeView) cls7.findViewById(R.id.cls_2_4_p);
        SimpleDraweeView cls_7_2_5_p = (SimpleDraweeView) cls7.findViewById(R.id.cls_2_5_p);
        SimpleDraweeView cls_7_2_6_p = (SimpleDraweeView) cls7.findViewById(R.id.cls_2_6_p);
        cls_7_1_t.setText("时尚配饰");
        cls_7_2_t.setText("名贵配饰");
        cls_7_1_1_t.setText("时尚戒指");
        cls_7_1_2_t.setText("项链");
        cls_7_1_3_t.setText("手链脚链");
        cls_7_1_4_t.setText("头饰");
        cls_7_1_5_t.setText("耳饰");
        cls_7_2_1_t.setText("银饰");
        cls_7_2_2_t.setText("黄金饰品");
        cls_7_2_3_t.setText("铂金饰品");
        cls_7_2_4_t.setText("天然木饰");
        cls_7_2_5_t.setText("宝石");
        cls_7_2_6_t.setText("钻石配饰");
        cls_7_1_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/16.jpg"));
        cls_7_1_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/68.jpg"));
        cls_7_1_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/86.jpg"));
        cls_7_1_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/112.jpg"));
        cls_7_1_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/146.jpg"));
        cls_7_2_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/34.jpg"));
        cls_7_2_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/69.jpg"));
        cls_7_2_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/99.jpg"));
        cls_7_2_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/111.jpg"));
        cls_7_2_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/136.jpg"));
        cls_7_2_6_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/163.jpg"));
        cls_7_1_1.setOnClickListener(new Cls7OnClickListeners());
        cls_7_1_2.setOnClickListener(new Cls7OnClickListeners());
        cls_7_1_3.setOnClickListener(new Cls7OnClickListeners());
        cls_7_1_4.setOnClickListener(new Cls7OnClickListeners());
        cls_7_1_5.setOnClickListener(new Cls7OnClickListeners());
        cls_7_2_1.setOnClickListener(new Cls7OnClickListeners());
        cls_7_2_2.setOnClickListener(new Cls7OnClickListeners());
        cls_7_2_3.setOnClickListener(new Cls7OnClickListeners());
        cls_7_2_4.setOnClickListener(new Cls7OnClickListeners());
        cls_7_2_5.setOnClickListener(new Cls7OnClickListeners());
        cls_7_2_6.setOnClickListener(new Cls7OnClickListeners());

        cls8 = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_view_detail_layout_8, null);
        cls_8_1_1 = (LinearLayout) cls8.findViewById(R.id.cls_1_1);
        cls_8_1_2 = (LinearLayout) cls8.findViewById(R.id.cls_1_2);
        cls_8_1_3 = (LinearLayout) cls8.findViewById(R.id.cls_1_3);
        cls_8_1_4 = (LinearLayout) cls8.findViewById(R.id.cls_1_4);
        cls_8_1_t = (TextView) cls8.findViewById(R.id.cls_1);
        cls_8_1_1_t = (TextView) cls8.findViewById(R.id.cls_1_1_t);
        cls_8_1_2_t = (TextView) cls8.findViewById(R.id.cls_1_2_t);
        cls_8_1_3_t = (TextView) cls8.findViewById(R.id.cls_1_3_t);
        cls_8_1_4_t = (TextView) cls8.findViewById(R.id.cls_1_4_t);
        SimpleDraweeView cls_8_1_1_p = (SimpleDraweeView) cls8.findViewById(R.id.cls_1_1_p);
        SimpleDraweeView cls_8_1_2_p = (SimpleDraweeView) cls8.findViewById(R.id.cls_1_2_p);
        SimpleDraweeView cls_8_1_3_p = (SimpleDraweeView) cls8.findViewById(R.id.cls_1_3_p);
        SimpleDraweeView cls_8_1_4_p = (SimpleDraweeView) cls8.findViewById(R.id.cls_1_4_p);
        cls_8_1_t.setText("天下美食");
        cls_8_1_1_t.setText("肉干零食");
        cls_8_1_2_t.setText("水果干");
        cls_8_1_3_t.setText("干果");
        cls_8_1_4_t.setText("饮品");
        cls_8_1_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/92.jpg"));
        cls_8_1_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/21.jpg"));
        cls_8_1_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/46.jpg"));
        cls_8_1_4_p.setImageURI(Uri.parse(""));
        cls_8_1_1.setOnClickListener(new Cls8OnClickListeners());
        cls_8_1_2.setOnClickListener(new Cls8OnClickListeners());
        cls_8_1_3.setOnClickListener(new Cls8OnClickListeners());
        cls_8_1_4.setOnClickListener(new Cls8OnClickListeners());

        cls9 = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_view_detail_layout_9, null);
        cls_9_1_1 = (LinearLayout) cls9.findViewById(R.id.cls_1_1);
        cls_9_1_2 = (LinearLayout) cls9.findViewById(R.id.cls_1_2);
        cls_9_1_3 = (LinearLayout) cls9.findViewById(R.id.cls_1_3);
        cls_9_1_4 = (LinearLayout) cls9.findViewById(R.id.cls_1_4);
        cls_9_1_5 = (LinearLayout) cls9.findViewById(R.id.cls_1_5);
        cls_9_1_6 = (LinearLayout) cls9.findViewById(R.id.cls_1_6);
        cls_9_1_7 = (LinearLayout) cls9.findViewById(R.id.cls_1_7);
        cls_9_1_8 = (LinearLayout) cls9.findViewById(R.id.cls_1_8);
        cls_9_1_9 = (LinearLayout) cls9.findViewById(R.id.cls_1_9);
        cls_9_1_10 = (LinearLayout) cls9.findViewById(R.id.cls_1_10);
        cls_9_1_11 = (LinearLayout) cls9.findViewById(R.id.cls_1_11);
        cls_9_1_12 = (LinearLayout) cls9.findViewById(R.id.cls_1_12);
        cls_9_1_13 = (LinearLayout) cls9.findViewById(R.id.cls_1_13);
        cls_9_1_14 = (LinearLayout) cls9.findViewById(R.id.cls_1_14);
        cls_9_1_15 = (LinearLayout) cls9.findViewById(R.id.cls_1_15);
        cls_9_2_1 = (LinearLayout) cls9.findViewById(R.id.cls_2_1);
        cls_9_2_2 = (LinearLayout) cls9.findViewById(R.id.cls_2_2);
        cls_9_2_3 = (LinearLayout) cls9.findViewById(R.id.cls_2_3);
        cls_9_2_4 = (LinearLayout) cls9.findViewById(R.id.cls_2_4);
        cls_9_2_5 = (LinearLayout) cls9.findViewById(R.id.cls_2_5);
        cls_9_2_6 = (LinearLayout) cls9.findViewById(R.id.cls_2_6);
        cls_9_2_7 = (LinearLayout) cls9.findViewById(R.id.cls_2_7);
        cls_9_2_8 = (LinearLayout) cls9.findViewById(R.id.cls_2_8);
        cls_9_2_9 = (LinearLayout) cls9.findViewById(R.id.cls_2_9);
        cls_9_2_10 = (LinearLayout) cls9.findViewById(R.id.cls_2_10);
        cls_9_2_11 = (LinearLayout) cls9.findViewById(R.id.cls_2_11);
        cls_9_3_1 = (LinearLayout) cls9.findViewById(R.id.cls_3_1);
        cls_9_3_2 = (LinearLayout) cls9.findViewById(R.id.cls_3_2);
        cls_9_3_3 = (LinearLayout) cls9.findViewById(R.id.cls_3_3);
        cls_9_3_4 = (LinearLayout) cls9.findViewById(R.id.cls_3_4);
        TextView cls_9_1_t = (TextView) cls9.findViewById(R.id.cls_1);
        TextView cls_9_2_t = (TextView) cls9.findViewById(R.id.cls_2);
        TextView cls_9_3_t = (TextView) cls9.findViewById(R.id.cls_3);
        cls_9_1_1_t = (TextView) cls9.findViewById(R.id.cls_1_1_t);
        cls_9_1_2_t = (TextView) cls9.findViewById(R.id.cls_1_2_t);
        cls_9_1_3_t = (TextView) cls9.findViewById(R.id.cls_1_3_t);
        cls_9_1_4_t = (TextView) cls9.findViewById(R.id.cls_1_4_t);
        cls_9_1_5_t = (TextView) cls9.findViewById(R.id.cls_1_5_t);
        cls_9_1_6_t = (TextView) cls9.findViewById(R.id.cls_1_6_t);
        cls_9_1_7_t = (TextView) cls9.findViewById(R.id.cls_1_7_t);
        cls_9_1_8_t = (TextView) cls9.findViewById(R.id.cls_1_8_t);
        cls_9_1_9_t = (TextView) cls9.findViewById(R.id.cls_1_9_t);
        cls_9_1_10_t = (TextView) cls9.findViewById(R.id.cls_1_10_t);
        cls_9_1_11_t = (TextView) cls9.findViewById(R.id.cls_1_11_t);
        cls_9_1_12_t = (TextView) cls9.findViewById(R.id.cls_1_12_t);
        cls_9_1_13_t = (TextView) cls9.findViewById(R.id.cls_1_13_t);
        cls_9_1_14_t = (TextView) cls9.findViewById(R.id.cls_1_14_t);
        cls_9_1_15_t = (TextView) cls9.findViewById(R.id.cls_1_15_t);
        cls_9_2_1_t = (TextView) cls9.findViewById(R.id.cls_2_1_t);
        cls_9_2_2_t = (TextView) cls9.findViewById(R.id.cls_2_2_t);
        cls_9_2_3_t = (TextView) cls9.findViewById(R.id.cls_2_3_t);
        cls_9_2_4_t = (TextView) cls9.findViewById(R.id.cls_2_4_t);
        cls_9_2_5_t = (TextView) cls9.findViewById(R.id.cls_2_5_t);
        cls_9_2_6_t = (TextView) cls9.findViewById(R.id.cls_2_6_t);
        cls_9_2_7_t = (TextView) cls9.findViewById(R.id.cls_2_7_t);
        cls_9_2_8_t = (TextView) cls9.findViewById(R.id.cls_2_8_t);
        cls_9_2_9_t = (TextView) cls9.findViewById(R.id.cls_2_9_t);
        cls_9_2_10_t = (TextView) cls9.findViewById(R.id.cls_2_10_t);
        cls_9_2_11_t = (TextView) cls9.findViewById(R.id.cls_2_11_t);
        cls_9_3_1_t = (TextView) cls9.findViewById(R.id.cls_3_1_t);
        cls_9_3_2_t = (TextView) cls9.findViewById(R.id.cls_3_2_t);
        cls_9_3_3_t = (TextView) cls9.findViewById(R.id.cls_3_3_t);
        cls_9_3_4_t = (TextView) cls9.findViewById(R.id.cls_3_4_t);
        SimpleDraweeView cls_9_1_1_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_1_p);
        SimpleDraweeView cls_9_1_2_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_2_p);
        SimpleDraweeView cls_9_1_3_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_3_p);
        SimpleDraweeView cls_9_1_4_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_4_p);
        SimpleDraweeView cls_9_1_5_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_5_p);
        SimpleDraweeView cls_9_1_6_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_6_p);
        SimpleDraweeView cls_9_1_7_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_7_p);
        SimpleDraweeView cls_9_1_8_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_8_p);
        SimpleDraweeView cls_9_1_9_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_9_p);
        SimpleDraweeView cls_9_1_10_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_10_p);
        SimpleDraweeView cls_9_1_11_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_11_p);
        SimpleDraweeView cls_9_1_12_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_12_p);
        SimpleDraweeView cls_9_1_13_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_13_p);
        SimpleDraweeView cls_9_1_14_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_14_p);
        SimpleDraweeView cls_9_1_15_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_1_15_p);
        SimpleDraweeView cls_9_2_1_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_2_1_p);
        SimpleDraweeView cls_9_2_2_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_2_2_p);
        SimpleDraweeView cls_9_2_3_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_2_3_p);
        SimpleDraweeView cls_9_2_4_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_2_4_p);
        SimpleDraweeView cls_9_2_5_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_2_5_p);
        SimpleDraweeView cls_9_2_6_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_2_6_p);
        SimpleDraweeView cls_9_2_7_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_2_7_p);
        SimpleDraweeView cls_9_2_8_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_2_8_p);
        SimpleDraweeView cls_9_2_9_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_2_9_p);
        SimpleDraweeView cls_9_2_10_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_2_10_p);
        SimpleDraweeView cls_9_2_11_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_2_11_p);
        SimpleDraweeView cls_9_3_1_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_3_1_p);
        SimpleDraweeView cls_9_3_2_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_3_2_p);
        SimpleDraweeView cls_9_3_3_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_3_3_p);
        SimpleDraweeView cls_9_3_4_p = (SimpleDraweeView) cls9.findViewById(R.id.cls_3_4_p);
        cls_9_1_t.setText("日用小家电");
        cls_9_2_t.setText("日常用品");
        cls_9_3_t.setText("收纳");
        cls_9_1_1_t.setText("电风扇");
        cls_9_1_2_t.setText("插排");
        cls_9_1_3_t.setText("台灯");
        cls_9_1_4_t.setText("吹风机");
        cls_9_1_5_t.setText("加湿器");
        cls_9_1_6_t.setText("电水壶");
        cls_9_1_7_t.setText("吸尘器");
        cls_9_1_8_t.setText("电饭煲");
        cls_9_1_9_t.setText("豆浆机");
        cls_9_1_10_t.setText("电磁炉");
        cls_9_1_11_t.setText("榨汁机");
        cls_9_1_12_t.setText("电熨斗");
        cls_9_1_13_t.setText("手电筒");
        cls_9_1_14_t.setText("电夹板");
        cls_9_1_15_t.setText("电子秤");
        cls_9_2_1_t.setText("卫生巾");
        cls_9_2_2_t.setText("纸制品");
        cls_9_2_3_t.setText("床上用品");
        cls_9_2_4_t.setText("肥皂");
        cls_9_2_5_t.setText("口腔保健");
        cls_9_2_6_t.setText("香皂");
        cls_9_2_7_t.setText("沐浴露");
        cls_9_2_8_t.setText("洗衣液");
        cls_9_2_9_t.setText("洗发水");
        cls_9_2_10_t.setText("保温杯");
        cls_9_2_11_t.setText("家庭卫生");
        cls_9_3_1_t.setText("衣架");
        cls_9_3_2_t.setText("收纳袋");
        cls_9_3_3_t.setText("压缩袋");
        cls_9_3_4_t.setText("储物箱");
        cls_9_1_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/228.jpg"));
        cls_9_1_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/223.jpg"));
        cls_9_1_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/198.jpg"));
        cls_9_1_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/5.jpg"));
        cls_9_1_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/229.jpg"));
        cls_9_1_6_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/120.jpg"));
        cls_9_1_7_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/231.jpg"));
        cls_9_1_8_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/225.jpg"));
        cls_9_1_9_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/227.jpg"));
        cls_9_1_10_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/224.jpg"));
        cls_9_1_11_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/233.jpg"));
        cls_9_1_12_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/192.jpg"));
        cls_9_1_13_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/202.jpg"));
        cls_9_1_14_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/54.jpg"));
        cls_9_1_15_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/226.jpg"));
        cls_9_2_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/3.jpg"));
        cls_9_2_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/60.jpg"));
        cls_9_2_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/84.jpg"));
        cls_9_2_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/123.jpg"));
        cls_9_2_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/144.jpg"));
        cls_9_2_6_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/166.jpg"));
        cls_9_2_7_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/183.jpg"));
        cls_9_2_8_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/190.jpg"));
        cls_9_2_9_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/232.jpg"));
        cls_9_2_10_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/195.jpg"));
        cls_9_2_11_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/230.jpg"));
        cls_9_3_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/32.jpg"));
        cls_9_3_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/48.jpg"));
        cls_9_3_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/79.jpg"));
        cls_9_3_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/105.jpg"));
        cls_9_1_1.setOnClickListener(new Cls9OnClickListeners());
        cls_9_1_2.setOnClickListener(new Cls9OnClickListeners());
        cls_9_1_3.setOnClickListener(new Cls9OnClickListeners());
        cls_9_1_4.setOnClickListener(new Cls9OnClickListeners());
        cls_9_1_5.setOnClickListener(new Cls9OnClickListeners());
        cls_9_1_6.setOnClickListener(new Cls9OnClickListeners());
        cls_9_1_7.setOnClickListener(new Cls9OnClickListeners());
        cls_9_1_8.setOnClickListener(new Cls9OnClickListeners());
        cls_9_1_9.setOnClickListener(new Cls9OnClickListeners());
        cls_9_1_10.setOnClickListener(new Cls9OnClickListeners());
        cls_9_1_11.setOnClickListener(new Cls9OnClickListeners());
        cls_9_1_12.setOnClickListener(new Cls9OnClickListeners());
        cls_9_1_13.setOnClickListener(new Cls9OnClickListeners());
        cls_9_1_14.setOnClickListener(new Cls9OnClickListeners());
        cls_9_1_15.setOnClickListener(new Cls9OnClickListeners());
        cls_9_2_1.setOnClickListener(new Cls9OnClickListeners());
        cls_9_2_2.setOnClickListener(new Cls9OnClickListeners());
        cls_9_2_3.setOnClickListener(new Cls9OnClickListeners());
        cls_9_2_4.setOnClickListener(new Cls9OnClickListeners());
        cls_9_2_5.setOnClickListener(new Cls9OnClickListeners());
        cls_9_2_6.setOnClickListener(new Cls9OnClickListeners());
        cls_9_2_7.setOnClickListener(new Cls9OnClickListeners());
        cls_9_2_8.setOnClickListener(new Cls9OnClickListeners());
        cls_9_2_9.setOnClickListener(new Cls9OnClickListeners());
        cls_9_2_10.setOnClickListener(new Cls9OnClickListeners());
        cls_9_2_11.setOnClickListener(new Cls9OnClickListeners());
        cls_9_3_1.setOnClickListener(new Cls9OnClickListeners());
        cls_9_3_2.setOnClickListener(new Cls9OnClickListeners());
        cls_9_3_3.setOnClickListener(new Cls9OnClickListeners());
        cls_9_3_4.setOnClickListener(new Cls9OnClickListeners());

        cls10 = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_view_detail_layout_10, null);
        cls_10_1_1 = (LinearLayout) cls10.findViewById(R.id.cls_1_1);
        cls_10_1_2 = (LinearLayout) cls10.findViewById(R.id.cls_1_2);
        cls_10_1_3 = (LinearLayout) cls10.findViewById(R.id.cls_1_3);
        cls_10_1_4 = (LinearLayout) cls10.findViewById(R.id.cls_1_4);
        cls_10_1_5 = (LinearLayout) cls10.findViewById(R.id.cls_1_5);
        cls_10_1_6 = (LinearLayout) cls10.findViewById(R.id.cls_1_6);
        cls_10_1_7 = (LinearLayout) cls10.findViewById(R.id.cls_1_7);
        cls_10_1_8 = (LinearLayout) cls10.findViewById(R.id.cls_1_8);
        cls_10_1_9 = (LinearLayout) cls10.findViewById(R.id.cls_1_9);
        cls_10_2_1 = (LinearLayout) cls10.findViewById(R.id.cls_2_1);
        cls_10_2_2 = (LinearLayout) cls10.findViewById(R.id.cls_2_2);
        cls_10_2_3 = (LinearLayout) cls10.findViewById(R.id.cls_2_3);
        cls_10_2_4 = (LinearLayout) cls10.findViewById(R.id.cls_2_4);
        cls_10_2_5 = (LinearLayout) cls10.findViewById(R.id.cls_2_5);
        cls_10_2_6 = (LinearLayout) cls10.findViewById(R.id.cls_2_6);
        cls_10_2_7 = (LinearLayout) cls10.findViewById(R.id.cls_2_7);
        TextView cls_10_1_t = (TextView) cls10.findViewById(R.id.cls_1);
        TextView cls_10_2_t = (TextView) cls10.findViewById(R.id.cls_2);
        cls_10_1_1_t = (TextView) cls10.findViewById(R.id.cls_1_1_t);
        cls_10_1_2_t = (TextView) cls10.findViewById(R.id.cls_1_2_t);
        cls_10_1_3_t = (TextView) cls10.findViewById(R.id.cls_1_3_t);
        cls_10_1_4_t = (TextView) cls10.findViewById(R.id.cls_1_4_t);
        cls_10_1_5_t = (TextView) cls10.findViewById(R.id.cls_1_5_t);
        cls_10_1_6_t = (TextView) cls10.findViewById(R.id.cls_1_6_t);
        cls_10_1_7_t = (TextView) cls10.findViewById(R.id.cls_1_7_t);
        cls_10_1_8_t = (TextView) cls10.findViewById(R.id.cls_1_8_t);
        cls_10_1_9_t = (TextView) cls10.findViewById(R.id.cls_1_9_t);
        cls_10_2_1_t = (TextView) cls10.findViewById(R.id.cls_2_1_t);
        cls_10_2_2_t = (TextView) cls10.findViewById(R.id.cls_2_2_t);
        cls_10_2_3_t = (TextView) cls10.findViewById(R.id.cls_2_3_t);
        cls_10_2_4_t = (TextView) cls10.findViewById(R.id.cls_2_4_t);
        cls_10_2_5_t = (TextView) cls10.findViewById(R.id.cls_2_5_t);
        cls_10_2_6_t = (TextView) cls10.findViewById(R.id.cls_2_6_t);
        cls_10_2_7_t = (TextView) cls10.findViewById(R.id.cls_2_7_t);
        SimpleDraweeView cls_10_1_1_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_1_1_p);
        SimpleDraweeView cls_10_1_2_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_1_2_p);
        SimpleDraweeView cls_10_1_3_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_1_3_p);
        SimpleDraweeView cls_10_1_4_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_1_4_p);
        SimpleDraweeView cls_10_1_5_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_1_5_p);
        SimpleDraweeView cls_10_1_6_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_1_6_p);
        SimpleDraweeView cls_10_1_7_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_1_7_p);
        SimpleDraweeView cls_10_1_8_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_1_8_p);
        SimpleDraweeView cls_10_1_9_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_1_9_p);
        SimpleDraweeView cls_10_2_1_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_2_1_p);
        SimpleDraweeView cls_10_2_2_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_2_2_p);
        SimpleDraweeView cls_10_2_3_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_2_3_p);
        SimpleDraweeView cls_10_2_4_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_2_4_p);
        SimpleDraweeView cls_10_2_5_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_2_5_p);
        SimpleDraweeView cls_10_2_6_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_2_6_p);
        SimpleDraweeView cls_10_2_7_p = (SimpleDraweeView) cls10.findViewById(R.id.cls_2_7_p);
        cls_10_1_t.setText("健身器材");
        cls_10_2_t.setText("自驾出行");
        cls_10_1_1_t.setText("篮球");
        cls_10_1_2_t.setText("足球");
        cls_10_1_3_t.setText("乒乓球拍");
        cls_10_1_4_t.setText("羽毛球拍");
        cls_10_1_5_t.setText("网球拍");
        cls_10_1_6_t.setText("哑铃");
        cls_10_1_7_t.setText("溜冰鞋");
        cls_10_1_8_t.setText("沙袋");
        cls_10_1_9_t.setText("滑板");
        cls_10_2_1_t.setText("山地车");
        cls_10_2_2_t.setText("公路车");
        cls_10_2_3_t.setText("死飞");
        cls_10_2_4_t.setText("独轮车");
        cls_10_2_5_t.setText("体感车");
        cls_10_2_6_t.setText("户外装备");
        cls_10_2_7_t.setText("骑行服");
        cls_10_1_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/30.jpg"));
        cls_10_1_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/64.jpg"));
        cls_10_1_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/70.jpg"));
        cls_10_1_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/122.jpg"));
        cls_10_1_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/145.jpg"));
        cls_10_1_6_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/153.jpg"));
        cls_10_1_7_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/176.jpg"));
        cls_10_1_8_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/184.jpg"));
        cls_10_1_9_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/191.jpg"));
        cls_10_2_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/7.jpg"));
        cls_10_2_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/41.jpg"));
        cls_10_2_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/88.jpg"));
        cls_10_2_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/119.jpg"));
        cls_10_2_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/131.jpg"));
        cls_10_2_6_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/154.jpg"));
        cls_10_2_7_p.setImageURI(Uri.parse(""));
        cls_10_1_1.setOnClickListener(new Cls10OnClickListeners());
        cls_10_1_2.setOnClickListener(new Cls10OnClickListeners());
        cls_10_1_3.setOnClickListener(new Cls10OnClickListeners());
        cls_10_1_4.setOnClickListener(new Cls10OnClickListeners());
        cls_10_1_5.setOnClickListener(new Cls10OnClickListeners());
        cls_10_1_6.setOnClickListener(new Cls10OnClickListeners());
        cls_10_1_7.setOnClickListener(new Cls10OnClickListeners());
        cls_10_1_8.setOnClickListener(new Cls10OnClickListeners());
        cls_10_1_9.setOnClickListener(new Cls10OnClickListeners());
        cls_10_2_1.setOnClickListener(new Cls10OnClickListeners());
        cls_10_2_2.setOnClickListener(new Cls10OnClickListeners());
        cls_10_2_3.setOnClickListener(new Cls10OnClickListeners());
        cls_10_2_4.setOnClickListener(new Cls10OnClickListeners());
        cls_10_2_5.setOnClickListener(new Cls10OnClickListeners());
        cls_10_2_6.setOnClickListener(new Cls10OnClickListeners());
        cls_10_2_7.setOnClickListener(new Cls10OnClickListeners());

        cls11 = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_view_detail_layout_11, null);
        cls_11_1_1 = (LinearLayout) cls11.findViewById(R.id.cls_1_1);
        cls_11_1_2 = (LinearLayout) cls11.findViewById(R.id.cls_1_2);
        cls_11_1_3 = (LinearLayout) cls11.findViewById(R.id.cls_1_3);
        cls_11_2_1 = (LinearLayout) cls11.findViewById(R.id.cls_2_1);
        cls_11_2_2 = (LinearLayout) cls11.findViewById(R.id.cls_2_2);
        cls_11_2_3 = (LinearLayout) cls11.findViewById(R.id.cls_2_3);
        cls_11_2_4 = (LinearLayout) cls11.findViewById(R.id.cls_2_4);
        cls_11_2_5 = (LinearLayout) cls11.findViewById(R.id.cls_2_5);
        cls_11_2_6 = (LinearLayout) cls11.findViewById(R.id.cls_2_6);
        cls_11_2_7 = (LinearLayout) cls11.findViewById(R.id.cls_2_7);
        cls_11_2_8 = (LinearLayout) cls11.findViewById(R.id.cls_2_8);
        cls_11_2_9 = (LinearLayout) cls11.findViewById(R.id.cls_2_9);
        cls_11_2_10 = (LinearLayout) cls11.findViewById(R.id.cls_2_10);
        cls_11_3_1 = (LinearLayout) cls11.findViewById(R.id.cls_3_1);
        cls_11_3_2 = (LinearLayout) cls11.findViewById(R.id.cls_3_2);
        cls_11_3_3 = (LinearLayout) cls11.findViewById(R.id.cls_3_3);
        TextView cls_11_1_t = (TextView) cls11.findViewById(R.id.cls_1);
        TextView cls_11_2_t = (TextView) cls11.findViewById(R.id.cls_2);
        TextView cls_11_3_t = (TextView) cls11.findViewById(R.id.cls_3);
        cls_11_1_1_t = (TextView) cls11.findViewById(R.id.cls_1_1_t);
        cls_11_1_2_t = (TextView) cls11.findViewById(R.id.cls_1_2_t);
        cls_11_1_3_t = (TextView) cls11.findViewById(R.id.cls_1_3_t);
        cls_11_2_1_t = (TextView) cls11.findViewById(R.id.cls_2_1_t);
        cls_11_2_2_t = (TextView) cls11.findViewById(R.id.cls_2_2_t);
        cls_11_2_3_t = (TextView) cls11.findViewById(R.id.cls_2_3_t);
        cls_11_2_4_t = (TextView) cls11.findViewById(R.id.cls_2_4_t);
        cls_11_2_5_t = (TextView) cls11.findViewById(R.id.cls_2_5_t);
        cls_11_2_6_t = (TextView) cls11.findViewById(R.id.cls_2_6_t);
        cls_11_2_7_t = (TextView) cls11.findViewById(R.id.cls_2_7_t);
        cls_11_2_8_t = (TextView) cls11.findViewById(R.id.cls_2_8_t);
        cls_11_2_9_t = (TextView) cls11.findViewById(R.id.cls_2_9_t);
        cls_11_2_10_t = (TextView) cls11.findViewById(R.id.cls_2_10_t);
        cls_11_3_1_t = (TextView) cls11.findViewById(R.id.cls_3_1_t);
        cls_11_3_2_t = (TextView) cls11.findViewById(R.id.cls_3_2_t);
        cls_11_3_3_t = (TextView) cls11.findViewById(R.id.cls_3_3_t);
        SimpleDraweeView cls_11_1_1_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_1_1_p);
        SimpleDraweeView cls_11_1_2_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_1_2_p);
        SimpleDraweeView cls_11_1_3_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_1_3_p);
        SimpleDraweeView cls_11_2_1_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_2_1_p);
        SimpleDraweeView cls_11_2_2_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_2_2_p);
        SimpleDraweeView cls_11_2_3_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_2_3_p);
        SimpleDraweeView cls_11_2_4_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_2_4_p);
        SimpleDraweeView cls_11_2_5_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_2_5_p);
        SimpleDraweeView cls_11_2_6_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_2_6_p);
        SimpleDraweeView cls_11_2_7_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_2_7_p);
        SimpleDraweeView cls_11_2_8_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_2_8_p);
        SimpleDraweeView cls_11_2_9_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_2_9_p);
        SimpleDraweeView cls_11_2_10_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_2_10_p);
        SimpleDraweeView cls_11_3_1_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_3_1_p);
        SimpleDraweeView cls_11_3_2_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_3_2_p);
        SimpleDraweeView cls_11_3_3_p = (SimpleDraweeView) cls11.findViewById(R.id.cls_3_3_p);
        cls_11_1_t.setText("电脑");
        cls_11_2_t.setText("电脑配件");
        cls_11_3_t.setText("网络产品");
        cls_11_1_1_t.setText("笔记本");
        cls_11_1_2_t.setText("台式机");
        cls_11_1_3_t.setText("DIY组装");
        cls_11_2_1_t.setText("显示屏");
        cls_11_2_2_t.setText("鼠标");
        cls_11_2_3_t.setText("键盘");
        cls_11_2_4_t.setText("音响");
        cls_11_2_5_t.setText("耳机耳麦");
        cls_11_2_6_t.setText("U盘");
        cls_11_2_7_t.setText("移动硬盘");
        cls_11_2_8_t.setText("线缆");
        cls_11_2_9_t.setText("鼠标垫");
        cls_11_2_10_t.setText("电脑清洁");
        cls_11_3_1_t.setText("无线网卡");
        cls_11_3_2_t.setText("路由器");
        cls_11_3_3_t.setText("交换机");
        cls_11_1_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/28.jpg"));
        cls_11_1_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/81.jpg"));
        cls_11_1_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/103.jpg"));
        cls_11_2_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/17.jpg"));
        cls_11_2_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/129.jpg"));
        cls_11_2_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/100.jpg"));
        cls_11_2_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/150.jpg"));
        cls_11_2_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/162.jpg"));
        cls_11_2_6_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/168.jpg"));
        cls_11_2_7_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/186.jpg"));
        cls_11_2_8_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/193.jpg"));
        cls_11_2_9_p.setImageURI(Uri.parse(""));
        cls_11_2_10_p.setImageURI(Uri.parse(""));
        cls_11_3_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/15.jpg"));
        cls_11_3_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/65.jpg"));
        cls_11_3_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/71.jpg"));
        cls_11_1_1.setOnClickListener(new Cls111OnClickListeners());
        cls_11_1_2.setOnClickListener(new Cls111OnClickListeners());
        cls_11_1_3.setOnClickListener(new Cls111OnClickListeners());
        cls_11_2_1.setOnClickListener(new Cls111OnClickListeners());
        cls_11_2_2.setOnClickListener(new Cls111OnClickListeners());
        cls_11_2_3.setOnClickListener(new Cls111OnClickListeners());
        cls_11_2_4.setOnClickListener(new Cls111OnClickListeners());
        cls_11_2_5.setOnClickListener(new Cls111OnClickListeners());
        cls_11_2_6.setOnClickListener(new Cls111OnClickListeners());
        cls_11_2_7.setOnClickListener(new Cls111OnClickListeners());
        cls_11_2_8.setOnClickListener(new Cls111OnClickListeners());
        cls_11_2_9.setOnClickListener(new Cls111OnClickListeners());
        cls_11_2_10.setOnClickListener(new Cls111OnClickListeners());
        cls_11_3_1.setOnClickListener(new Cls111OnClickListeners());
        cls_11_3_2.setOnClickListener(new Cls111OnClickListeners());
        cls_11_3_3.setOnClickListener(new Cls111OnClickListeners());

        cls12 = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_view_detail_layout_12, null);
        cls_12_1_1 = (LinearLayout) cls12.findViewById(R.id.cls_1_1);
        cls_12_1_2 = (LinearLayout) cls12.findViewById(R.id.cls_1_2);
        cls_12_1_3 = (LinearLayout) cls12.findViewById(R.id.cls_1_3);
        cls_12_1_4 = (LinearLayout) cls12.findViewById(R.id.cls_1_4);
        cls_12_1_5 = (LinearLayout) cls12.findViewById(R.id.cls_2_5);
        cls_12_2_1 = (LinearLayout) cls12.findViewById(R.id.cls_2_1);
        cls_12_2_2 = (LinearLayout) cls12.findViewById(R.id.cls_2_2);
        cls_12_2_3 = (LinearLayout) cls12.findViewById(R.id.cls_2_3);
        cls_12_2_4 = (LinearLayout) cls12.findViewById(R.id.cls_2_4);
        cls_12_2_5 = (LinearLayout) cls12.findViewById(R.id.cls_2_5);
        cls_12_3_1 = (LinearLayout) cls12.findViewById(R.id.cls_3_1);
        cls_12_3_2 = (LinearLayout) cls12.findViewById(R.id.cls_3_2);
        TextView cls_12_1_t = (TextView) cls12.findViewById(R.id.cls_1);
        TextView cls_12_2_t = (TextView) cls12.findViewById(R.id.cls_2);
        TextView cls_12_3_t = (TextView) cls12.findViewById(R.id.cls_3);
        cls_12_1_1_t = (TextView) cls12.findViewById(R.id.cls_1_1_t);
        cls_12_1_2_t = (TextView) cls12.findViewById(R.id.cls_1_2_t);
        cls_12_1_3_t = (TextView) cls12.findViewById(R.id.cls_1_3_t);
        cls_12_1_4_t = (TextView) cls12.findViewById(R.id.cls_1_4_t);
        cls_12_1_5_t = (TextView) cls12.findViewById(R.id.cls_1_5_t);
        cls_12_2_1_t = (TextView) cls12.findViewById(R.id.cls_2_1_t);
        cls_12_2_2_t = (TextView) cls12.findViewById(R.id.cls_2_2_t);
        cls_12_2_3_t = (TextView) cls12.findViewById(R.id.cls_2_3_t);
        cls_12_2_4_t = (TextView) cls12.findViewById(R.id.cls_2_4_t);
        cls_12_2_5_t = (TextView) cls12.findViewById(R.id.cls_2_5_t);
        cls_12_3_1_t = (TextView) cls12.findViewById(R.id.cls_3_1_t);
        cls_12_3_2_t = (TextView) cls12.findViewById(R.id.cls_3_2_t);
        SimpleDraweeView cls_12_1_1_p = (SimpleDraweeView) cls12.findViewById(R.id.cls_1_1_p);
        SimpleDraweeView cls_12_1_2_p = (SimpleDraweeView) cls12.findViewById(R.id.cls_1_2_p);
        SimpleDraweeView cls_12_1_3_p = (SimpleDraweeView) cls12.findViewById(R.id.cls_1_3_p);
        SimpleDraweeView cls_12_1_4_p = (SimpleDraweeView) cls12.findViewById(R.id.cls_1_4_p);
        SimpleDraweeView cls_12_1_5_p = (SimpleDraweeView) cls12.findViewById(R.id.cls_1_5_p);
        SimpleDraweeView cls_12_2_1_p = (SimpleDraweeView) cls12.findViewById(R.id.cls_2_1_p);
        SimpleDraweeView cls_12_2_2_p = (SimpleDraweeView) cls12.findViewById(R.id.cls_2_2_p);
        SimpleDraweeView cls_12_2_3_p = (SimpleDraweeView) cls12.findViewById(R.id.cls_2_3_p);
        SimpleDraweeView cls_12_2_4_p = (SimpleDraweeView) cls12.findViewById(R.id.cls_2_4_p);
        SimpleDraweeView cls_12_2_5_p = (SimpleDraweeView) cls12.findViewById(R.id.cls_2_5_p);
        SimpleDraweeView cls_12_3_1_p = (SimpleDraweeView) cls12.findViewById(R.id.cls_3_1_p);
        SimpleDraweeView cls_12_3_2_p = (SimpleDraweeView) cls12.findViewById(R.id.cls_3_2_p);
        cls_12_1_t.setText("男表");
        cls_12_2_t.setText("女表");
        cls_12_3_t.setText("情侣表");
        cls_12_1_1_t.setText("智能表");
        cls_12_1_2_t.setText("石英表");
        cls_12_1_3_t.setText("光能表");
        cls_12_1_4_t.setText("手动机械表");
        cls_12_1_5_t.setText("自动机械表");
        cls_12_2_1_t.setText("智能表");
        cls_12_2_2_t.setText("石英表");
        cls_12_2_3_t.setText("光能表");
        cls_12_2_4_t.setText("手动机械表");
        cls_12_2_5_t.setText("自动机械表");
        cls_12_3_1_t.setText("石英表");
        cls_12_3_2_t.setText("机械表");
        cls_12_1_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/19.jpg"));
        cls_12_1_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/58.jpg"));
        cls_12_1_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/74.jpg"));
        cls_12_1_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/114.jpg"));
        cls_12_1_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/147.jpg"));
        cls_12_2_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/20.jpg"));
        cls_12_2_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/59.jpg"));
        cls_12_2_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/75.jpg"));
        cls_12_2_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/115.jpg"));
        cls_12_2_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/148.jpg"));
        cls_12_3_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/27.jpg"));
        cls_12_3_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/50.jpg"));

        cls_12_1_1.setOnClickListener(new Cls12OnClickListeners());
        cls_12_1_2.setOnClickListener(new Cls12OnClickListeners());
        cls_12_1_3.setOnClickListener(new Cls12OnClickListeners());
        cls_12_1_4.setOnClickListener(new Cls12OnClickListeners());
        cls_12_1_5.setOnClickListener(new Cls12OnClickListeners());
        cls_12_2_1.setOnClickListener(new Cls12OnClickListeners());
        cls_12_2_2.setOnClickListener(new Cls12OnClickListeners());
        cls_12_2_3.setOnClickListener(new Cls12OnClickListeners());
        cls_12_2_4.setOnClickListener(new Cls12OnClickListeners());
        cls_12_2_5.setOnClickListener(new Cls12OnClickListeners());
        cls_12_3_1.setOnClickListener(new Cls12OnClickListeners());
        cls_12_3_2.setOnClickListener(new Cls12OnClickListeners());

        cls13 = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_view_detail_layout_13, null);
        cls_13_1_1 = (LinearLayout) cls13.findViewById(R.id.cls_1_1);
        cls_13_1_2 = (LinearLayout) cls13.findViewById(R.id.cls_1_2);
        cls_13_1_3 = (LinearLayout) cls13.findViewById(R.id.cls_1_3);
        cls_13_1_4 = (LinearLayout) cls13.findViewById(R.id.cls_1_4);
        cls_13_1_5 = (LinearLayout) cls13.findViewById(R.id.cls_1_5);
        cls_13_1_6 = (LinearLayout) cls13.findViewById(R.id.cls_1_6);
        cls_13_1_7 = (LinearLayout) cls13.findViewById(R.id.cls_1_7);
        cls_13_1_8 = (LinearLayout) cls13.findViewById(R.id.cls_1_8);
        TextView cls_13_1_t = (TextView) cls13.findViewById(R.id.cls_1);
        cls_13_1_1_t = (TextView) cls13.findViewById(R.id.cls_1_1_t);
        cls_13_1_2_t = (TextView) cls13.findViewById(R.id.cls_1_2_t);
        cls_13_1_3_t = (TextView) cls13.findViewById(R.id.cls_1_3_t);
        cls_13_1_4_t = (TextView) cls13.findViewById(R.id.cls_1_4_t);
        cls_13_1_5_t = (TextView) cls13.findViewById(R.id.cls_1_5_t);
        cls_13_1_6_t = (TextView) cls13.findViewById(R.id.cls_1_6_t);
        cls_13_1_7_t = (TextView) cls13.findViewById(R.id.cls_1_7_t);
        cls_13_1_8_t = (TextView) cls13.findViewById(R.id.cls_1_8_t);
        SimpleDraweeView cls_13_1_1_p = (SimpleDraweeView) cls13.findViewById(R.id.cls_1_1_p);
        SimpleDraweeView cls_13_1_2_p = (SimpleDraweeView) cls13.findViewById(R.id.cls_1_2_p);
        SimpleDraweeView cls_13_1_3_p = (SimpleDraweeView) cls13.findViewById(R.id.cls_1_3_p);
        SimpleDraweeView cls_13_1_4_p = (SimpleDraweeView) cls13.findViewById(R.id.cls_1_4_p);
        SimpleDraweeView cls_13_1_5_p = (SimpleDraweeView) cls13.findViewById(R.id.cls_1_5_p);
        SimpleDraweeView cls_13_1_6_p = (SimpleDraweeView) cls13.findViewById(R.id.cls_1_6_p);
        SimpleDraweeView cls_13_1_7_p = (SimpleDraweeView) cls13.findViewById(R.id.cls_1_7_p);
        SimpleDraweeView cls_13_1_8_p = (SimpleDraweeView) cls13.findViewById(R.id.cls_1_8_p);
        cls_13_1_t.setText("创意礼品");
        cls_13_1_1_t.setText("工艺礼品");
        cls_13_1_2_t.setText("名品火机");
        cls_13_1_3_t.setText("珍贵木艺");
        cls_13_1_4_t.setText("皮包");
        cls_13_1_5_t.setText("皮带");
        cls_13_1_6_t.setText("钱包");
        cls_13_1_7_t.setText("名贵手表");
        cls_13_1_8_t.setText("高档化妆品");
        cls_13_1_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/8.jpg"));
        cls_13_1_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/180.jpg"));
        cls_13_1_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/53.jpg"));
        cls_13_1_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/160.jpg"));
        cls_13_1_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/89.jpg"));
        cls_13_1_6_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/128.jpg"));
        cls_13_1_7_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/133.jpg"));
        cls_13_1_8_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/179.jpg"));
        cls_13_1_1.setOnClickListener(new Cls13OnClickListeners());
        cls_13_1_2.setOnClickListener(new Cls13OnClickListeners());
        cls_13_1_3.setOnClickListener(new Cls13OnClickListeners());
        cls_13_1_4.setOnClickListener(new Cls13OnClickListeners());
        cls_13_1_5.setOnClickListener(new Cls13OnClickListeners());
        cls_13_1_6.setOnClickListener(new Cls13OnClickListeners());
        cls_13_1_7.setOnClickListener(new Cls13OnClickListeners());
        cls_13_1_8.setOnClickListener(new Cls13OnClickListeners());

        cls14 = LayoutInflater.from(mActivity).inflate(R.layout.all_buy_menu_hui_menu_view_detail_layout_14, null);
        cls_14_1_1 = (LinearLayout) cls14.findViewById(R.id.cls_1_1);
        cls_14_1_2 = (LinearLayout) cls14.findViewById(R.id.cls_1_2);
        cls_14_1_3 = (LinearLayout) cls14.findViewById(R.id.cls_1_3);
        cls_14_1_4 = (LinearLayout) cls14.findViewById(R.id.cls_1_4);
        cls_14_1_5 = (LinearLayout) cls14.findViewById(R.id.cls_1_5);
        cls_14_1_6 = (LinearLayout) cls14.findViewById(R.id.cls_1_6);
        cls_14_1_7 = (LinearLayout) cls14.findViewById(R.id.cls_1_7);
        TextView cls_14_1_t = (TextView) cls14.findViewById(R.id.cls_1);
        cls_14_1_1_t = (TextView) cls14.findViewById(R.id.cls_1_1_t);
        cls_14_1_2_t = (TextView) cls14.findViewById(R.id.cls_1_2_t);
        cls_14_1_3_t = (TextView) cls14.findViewById(R.id.cls_1_3_t);
        cls_14_1_4_t = (TextView) cls14.findViewById(R.id.cls_1_4_t);
        cls_14_1_5_t = (TextView) cls14.findViewById(R.id.cls_1_5_t);
        cls_14_1_6_t = (TextView) cls14.findViewById(R.id.cls_1_6_t);
        cls_14_1_7_t = (TextView) cls14.findViewById(R.id.cls_1_7_t);
        SimpleDraweeView cls_14_1_1_p = (SimpleDraweeView) cls14.findViewById(R.id.cls_1_1_p);
        SimpleDraweeView cls_14_1_2_p = (SimpleDraweeView) cls14.findViewById(R.id.cls_1_2_p);
        SimpleDraweeView cls_14_1_3_p = (SimpleDraweeView) cls14.findViewById(R.id.cls_1_3_p);
        SimpleDraweeView cls_14_1_4_p = (SimpleDraweeView) cls14.findViewById(R.id.cls_1_4_p);
        SimpleDraweeView cls_14_1_5_p = (SimpleDraweeView) cls14.findViewById(R.id.cls_1_5_p);
        SimpleDraweeView cls_14_1_6_p = (SimpleDraweeView) cls14.findViewById(R.id.cls_1_6_p);
        SimpleDraweeView cls_14_1_7_p = (SimpleDraweeView) cls14.findViewById(R.id.cls_1_7_p);
        cls_14_1_t.setText("营养保健");
        cls_14_1_1_t.setText("红枣");
        cls_14_1_2_t.setText("枸杞");
        cls_14_1_3_t.setText("蜂蜜");
        cls_14_1_4_t.setText("养生茶饮");
        cls_14_1_5_t.setText("燕窝");
        cls_14_1_6_t.setText("冬虫夏草");
        cls_14_1_7_t.setText("人参");
        cls_14_1_1_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/31.jpg"));
        cls_14_1_2_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/51.jpg"));
        cls_14_1_3_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/94.jpg"));
        cls_14_1_4_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/106.jpg"));
        cls_14_1_5_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/140.jpg"));
        cls_14_1_6_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/151.jpg"));
        cls_14_1_7_p.setImageURI(Uri.parse("http://www.qkxmall.com/uploadfile/classes/169.jpg"));
        cls_14_1_1.setOnClickListener(new Cls14OnClickListeners());
        cls_14_1_2.setOnClickListener(new Cls14OnClickListeners());
        cls_14_1_3.setOnClickListener(new Cls14OnClickListeners());
        cls_14_1_4.setOnClickListener(new Cls14OnClickListeners());
        cls_14_1_5.setOnClickListener(new Cls14OnClickListeners());
        cls_14_1_6.setOnClickListener(new Cls14OnClickListeners());
        cls_14_1_7.setOnClickListener(new Cls14OnClickListeners());


        listDetail.add(cls1);
        listDetail.add(cls2);
        listDetail.add(cls3);
        listDetail.add(cls4);
        listDetail.add(cls5);
        listDetail.add(cls6);
        listDetail.add(cls7);
        listDetail.add(cls8);
        listDetail.add(cls9);
        listDetail.add(cls10);
        listDetail.add(cls11);
        listDetail.add(cls12);
        listDetail.add(cls13);
        listDetail.add(cls14);
        huiViewDetail.setAdapter(new AdsViewPagerAdapter(listDetail));

//        if (huiViewDetail.getCurrentItem() == 0) {
//            item0.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
//            item0.setTextColor(getResources().getColor(R.color.white));
//            item1.setBackgroundColor(getResources().getColor(R.color.white));
//            item1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
//            item2.setBackgroundColor(getResources().getColor(R.color.white));
//            item2.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
//            item3.setBackgroundColor(getResources().getColor(R.color.white));
//            item3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
//            item4.setBackgroundColor(getResources().getColor(R.color.white));
//            item4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
//            item5.setBackgroundColor(getResources().getColor(R.color.white));
//            item5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
//            item6.setBackgroundColor(getResources().getColor(R.color.white));
//            item6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
//            item7.setBackgroundColor(getResources().getColor(R.color.white));
//            item7.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
//            item8.setBackgroundColor(getResources().getColor(R.color.white));
//            item8.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
//            item9.setBackgroundColor(getResources().getColor(R.color.white));
//            item9.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
//            item10.setBackgroundColor(getResources().getColor(R.color.white));
//            item10.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
//            item11.setBackgroundColor(getResources().getColor(R.color.white));
//            item11.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
//            item12.setBackgroundColor(getResources().getColor(R.color.white));
//            item12.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
//            item13.setBackgroundColor(getResources().getColor(R.color.white));
//            item13.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
//
//        }
        huiViewDetail.setCurrentItem(1);
        huiViewDetail.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (huiViewDetail.getCurrentItem() == 0) {
                    item0.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item0.setTextColor(getResources().getColor(R.color.white));
                    item1.setBackgroundColor(getResources().getColor(R.color.white));
                    item1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item2.setBackgroundColor(getResources().getColor(R.color.white));
                    item2.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item3.setBackgroundColor(getResources().getColor(R.color.white));
                    item3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item4.setBackgroundColor(getResources().getColor(R.color.white));
                    item4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item5.setBackgroundColor(getResources().getColor(R.color.white));
                    item5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item6.setBackgroundColor(getResources().getColor(R.color.white));
                    item6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item7.setBackgroundColor(getResources().getColor(R.color.white));
                    item7.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item8.setBackgroundColor(getResources().getColor(R.color.white));
                    item8.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item9.setBackgroundColor(getResources().getColor(R.color.white));
                    item9.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item10.setBackgroundColor(getResources().getColor(R.color.white));
                    item10.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item11.setBackgroundColor(getResources().getColor(R.color.white));
                    item11.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item12.setBackgroundColor(getResources().getColor(R.color.white));
                    item12.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item13.setBackgroundColor(getResources().getColor(R.color.white));
                    item13.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                } else if (huiViewDetail.getCurrentItem() == 1) {
                    item0.setBackgroundColor(getResources().getColor(R.color.white));
                    item0.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item1.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item1.setTextColor(getResources().getColor(R.color.white));
                    item2.setBackgroundColor(getResources().getColor(R.color.white));
                    item2.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item3.setBackgroundColor(getResources().getColor(R.color.white));
                    item3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item4.setBackgroundColor(getResources().getColor(R.color.white));
                    item4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item5.setBackgroundColor(getResources().getColor(R.color.white));
                    item5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item6.setBackgroundColor(getResources().getColor(R.color.white));
                    item6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item7.setBackgroundColor(getResources().getColor(R.color.white));
                    item7.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item8.setBackgroundColor(getResources().getColor(R.color.white));
                    item8.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item9.setBackgroundColor(getResources().getColor(R.color.white));
                    item9.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item10.setBackgroundColor(getResources().getColor(R.color.white));
                    item10.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item11.setBackgroundColor(getResources().getColor(R.color.white));
                    item11.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item12.setBackgroundColor(getResources().getColor(R.color.white));
                    item12.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item13.setBackgroundColor(getResources().getColor(R.color.white));
                    item13.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                } else if (huiViewDetail.getCurrentItem() == 2) {
                    item0.setBackgroundColor(getResources().getColor(R.color.white));
                    item0.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item1.setBackgroundColor(getResources().getColor(R.color.white));
                    item1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item2.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item2.setTextColor(getResources().getColor(R.color.white));
                    item3.setBackgroundColor(getResources().getColor(R.color.white));
                    item3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item4.setBackgroundColor(getResources().getColor(R.color.white));
                    item4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item5.setBackgroundColor(getResources().getColor(R.color.white));
                    item5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item6.setBackgroundColor(getResources().getColor(R.color.white));
                    item6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item7.setBackgroundColor(getResources().getColor(R.color.white));
                    item7.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item8.setBackgroundColor(getResources().getColor(R.color.white));
                    item8.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item9.setBackgroundColor(getResources().getColor(R.color.white));
                    item9.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item10.setBackgroundColor(getResources().getColor(R.color.white));
                    item10.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item11.setBackgroundColor(getResources().getColor(R.color.white));
                    item11.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item12.setBackgroundColor(getResources().getColor(R.color.white));
                    item12.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item13.setBackgroundColor(getResources().getColor(R.color.white));
                    item13.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                } else if (huiViewDetail.getCurrentItem() == 3) {
                    item0.setBackgroundColor(getResources().getColor(R.color.white));
                    item0.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item1.setBackgroundColor(getResources().getColor(R.color.white));
                    item1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item2.setBackgroundColor(getResources().getColor(R.color.white));
                    item2.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item3.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item3.setTextColor(getResources().getColor(R.color.white));
                    item4.setBackgroundColor(getResources().getColor(R.color.white));
                    item4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item5.setBackgroundColor(getResources().getColor(R.color.white));
                    item5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item6.setBackgroundColor(getResources().getColor(R.color.white));
                    item6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item7.setBackgroundColor(getResources().getColor(R.color.white));
                    item7.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item8.setBackgroundColor(getResources().getColor(R.color.white));
                    item8.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item9.setBackgroundColor(getResources().getColor(R.color.white));
                    item9.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item10.setBackgroundColor(getResources().getColor(R.color.white));
                    item10.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item11.setBackgroundColor(getResources().getColor(R.color.white));
                    item11.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item12.setBackgroundColor(getResources().getColor(R.color.white));
                    item12.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item13.setBackgroundColor(getResources().getColor(R.color.white));
                    item13.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                } else if (huiViewDetail.getCurrentItem() == 4) {
                    item0.setBackgroundColor(getResources().getColor(R.color.white));
                    item0.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item1.setBackgroundColor(getResources().getColor(R.color.white));
                    item1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item2.setBackgroundColor(getResources().getColor(R.color.white));
                    item2.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item3.setBackgroundColor(getResources().getColor(R.color.white));
                    item3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item4.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item4.setTextColor(getResources().getColor(R.color.white));
                    item5.setBackgroundColor(getResources().getColor(R.color.white));
                    item5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item6.setBackgroundColor(getResources().getColor(R.color.white));
                    item6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item7.setBackgroundColor(getResources().getColor(R.color.white));
                    item7.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item8.setBackgroundColor(getResources().getColor(R.color.white));
                    item8.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item9.setBackgroundColor(getResources().getColor(R.color.white));
                    item9.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item10.setBackgroundColor(getResources().getColor(R.color.white));
                    item10.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item11.setBackgroundColor(getResources().getColor(R.color.white));
                    item11.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item12.setBackgroundColor(getResources().getColor(R.color.white));
                    item12.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item13.setBackgroundColor(getResources().getColor(R.color.white));
                    item13.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                } else if (huiViewDetail.getCurrentItem() == 5) {
                    item0.setBackgroundColor(getResources().getColor(R.color.white));
                    item0.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item1.setBackgroundColor(getResources().getColor(R.color.white));
                    item1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item2.setBackgroundColor(getResources().getColor(R.color.white));
                    item2.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item3.setBackgroundColor(getResources().getColor(R.color.white));
                    item3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item4.setBackgroundColor(getResources().getColor(R.color.white));
                    item4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item5.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item5.setTextColor(getResources().getColor(R.color.white));
                    item6.setBackgroundColor(getResources().getColor(R.color.white));
                    item6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item7.setBackgroundColor(getResources().getColor(R.color.white));
                    item7.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item8.setBackgroundColor(getResources().getColor(R.color.white));
                    item8.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item9.setBackgroundColor(getResources().getColor(R.color.white));
                    item9.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item10.setBackgroundColor(getResources().getColor(R.color.white));
                    item10.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item11.setBackgroundColor(getResources().getColor(R.color.white));
                    item11.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item12.setBackgroundColor(getResources().getColor(R.color.white));
                    item12.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item13.setBackgroundColor(getResources().getColor(R.color.white));
                    item13.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                } else if (huiViewDetail.getCurrentItem() == 6) {
                    item0.setBackgroundColor(getResources().getColor(R.color.white));
                    item0.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item1.setBackgroundColor(getResources().getColor(R.color.white));
                    item1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item2.setBackgroundColor(getResources().getColor(R.color.white));
                    item2.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item3.setBackgroundColor(getResources().getColor(R.color.white));
                    item3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item4.setBackgroundColor(getResources().getColor(R.color.white));
                    item4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item5.setBackgroundColor(getResources().getColor(R.color.white));
                    item5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item6.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item6.setTextColor(getResources().getColor(R.color.white));
                    item7.setBackgroundColor(getResources().getColor(R.color.white));
                    item7.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item8.setBackgroundColor(getResources().getColor(R.color.white));
                    item8.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item9.setBackgroundColor(getResources().getColor(R.color.white));
                    item9.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item10.setBackgroundColor(getResources().getColor(R.color.white));
                    item10.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item11.setBackgroundColor(getResources().getColor(R.color.white));
                    item11.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item12.setBackgroundColor(getResources().getColor(R.color.white));
                    item12.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item13.setBackgroundColor(getResources().getColor(R.color.white));
                    item13.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                } else if (huiViewDetail.getCurrentItem() == 7) {
                    item0.setBackgroundColor(getResources().getColor(R.color.white));
                    item0.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item1.setBackgroundColor(getResources().getColor(R.color.white));
                    item1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item2.setBackgroundColor(getResources().getColor(R.color.white));
                    item2.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item3.setBackgroundColor(getResources().getColor(R.color.white));
                    item3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item4.setBackgroundColor(getResources().getColor(R.color.white));
                    item4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item5.setBackgroundColor(getResources().getColor(R.color.white));
                    item5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item6.setBackgroundColor(getResources().getColor(R.color.white));
                    item6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item7.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item7.setTextColor(getResources().getColor(R.color.white));
                    item8.setBackgroundColor(getResources().getColor(R.color.white));
                    item8.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item9.setBackgroundColor(getResources().getColor(R.color.white));
                    item9.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item10.setBackgroundColor(getResources().getColor(R.color.white));
                    item10.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item11.setBackgroundColor(getResources().getColor(R.color.white));
                    item11.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item12.setBackgroundColor(getResources().getColor(R.color.white));
                    item12.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item13.setBackgroundColor(getResources().getColor(R.color.white));
                    item13.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                } else if (huiViewDetail.getCurrentItem() == 8) {
                    item0.setBackgroundColor(getResources().getColor(R.color.white));
                    item0.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item1.setBackgroundColor(getResources().getColor(R.color.white));
                    item1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item2.setBackgroundColor(getResources().getColor(R.color.white));
                    item2.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item3.setBackgroundColor(getResources().getColor(R.color.white));
                    item3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item4.setBackgroundColor(getResources().getColor(R.color.white));
                    item4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item5.setBackgroundColor(getResources().getColor(R.color.white));
                    item5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item6.setBackgroundColor(getResources().getColor(R.color.white));
                    item6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item7.setBackgroundColor(getResources().getColor(R.color.white));
                    item7.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item8.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item8.setTextColor(getResources().getColor(R.color.white));
                    item9.setBackgroundColor(getResources().getColor(R.color.white));
                    item9.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item10.setBackgroundColor(getResources().getColor(R.color.white));
                    item10.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item11.setBackgroundColor(getResources().getColor(R.color.white));
                    item11.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item12.setBackgroundColor(getResources().getColor(R.color.white));
                    item12.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item13.setBackgroundColor(getResources().getColor(R.color.white));
                    item13.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                } else if (huiViewDetail.getCurrentItem() == 9) {
                    item0.setBackgroundColor(getResources().getColor(R.color.white));
                    item0.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item1.setBackgroundColor(getResources().getColor(R.color.white));
                    item1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item2.setBackgroundColor(getResources().getColor(R.color.white));
                    item2.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item3.setBackgroundColor(getResources().getColor(R.color.white));
                    item3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item4.setBackgroundColor(getResources().getColor(R.color.white));
                    item4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item5.setBackgroundColor(getResources().getColor(R.color.white));
                    item5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item6.setBackgroundColor(getResources().getColor(R.color.white));
                    item6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item7.setBackgroundColor(getResources().getColor(R.color.white));
                    item7.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item8.setBackgroundColor(getResources().getColor(R.color.white));
                    item8.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item9.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item9.setTextColor(getResources().getColor(R.color.white));
                    item10.setBackgroundColor(getResources().getColor(R.color.white));
                    item10.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item11.setBackgroundColor(getResources().getColor(R.color.white));
                    item11.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item12.setBackgroundColor(getResources().getColor(R.color.white));
                    item12.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item13.setBackgroundColor(getResources().getColor(R.color.white));
                    item13.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                } else if (huiViewDetail.getCurrentItem() == 10) {
                    item0.setBackgroundColor(getResources().getColor(R.color.white));
                    item0.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item1.setBackgroundColor(getResources().getColor(R.color.white));
                    item1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item2.setBackgroundColor(getResources().getColor(R.color.white));
                    item2.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item3.setBackgroundColor(getResources().getColor(R.color.white));
                    item3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item4.setBackgroundColor(getResources().getColor(R.color.white));
                    item4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item5.setBackgroundColor(getResources().getColor(R.color.white));
                    item5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item6.setBackgroundColor(getResources().getColor(R.color.white));
                    item6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item7.setBackgroundColor(getResources().getColor(R.color.white));
                    item7.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item8.setBackgroundColor(getResources().getColor(R.color.white));
                    item8.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item9.setBackgroundColor(getResources().getColor(R.color.white));
                    item9.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item10.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item10.setTextColor(getResources().getColor(R.color.white));
                    item11.setBackgroundColor(getResources().getColor(R.color.white));
                    item11.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item12.setBackgroundColor(getResources().getColor(R.color.white));
                    item12.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item13.setBackgroundColor(getResources().getColor(R.color.white));
                    item13.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                } else if (huiViewDetail.getCurrentItem() == 11) {
                    item0.setBackgroundColor(getResources().getColor(R.color.white));
                    item0.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item1.setBackgroundColor(getResources().getColor(R.color.white));
                    item1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item2.setBackgroundColor(getResources().getColor(R.color.white));
                    item2.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item3.setBackgroundColor(getResources().getColor(R.color.white));
                    item3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item4.setBackgroundColor(getResources().getColor(R.color.white));
                    item4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item5.setBackgroundColor(getResources().getColor(R.color.white));
                    item5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item6.setBackgroundColor(getResources().getColor(R.color.white));
                    item6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item7.setBackgroundColor(getResources().getColor(R.color.white));
                    item7.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item8.setBackgroundColor(getResources().getColor(R.color.white));
                    item8.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item9.setBackgroundColor(getResources().getColor(R.color.white));
                    item9.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item10.setBackgroundColor(getResources().getColor(R.color.white));
                    item10.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item11.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item11.setTextColor(getResources().getColor(R.color.white));
                    item12.setBackgroundColor(getResources().getColor(R.color.white));
                    item12.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item13.setBackgroundColor(getResources().getColor(R.color.white));
                    item13.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                } else if (huiViewDetail.getCurrentItem() == 12) {
                    item0.setBackgroundColor(getResources().getColor(R.color.white));
                    item0.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item1.setBackgroundColor(getResources().getColor(R.color.white));
                    item1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item2.setBackgroundColor(getResources().getColor(R.color.white));
                    item2.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item3.setBackgroundColor(getResources().getColor(R.color.white));
                    item3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item4.setBackgroundColor(getResources().getColor(R.color.white));
                    item4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item5.setBackgroundColor(getResources().getColor(R.color.white));
                    item5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item6.setBackgroundColor(getResources().getColor(R.color.white));
                    item6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item7.setBackgroundColor(getResources().getColor(R.color.white));
                    item7.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item8.setBackgroundColor(getResources().getColor(R.color.white));
                    item8.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item9.setBackgroundColor(getResources().getColor(R.color.white));
                    item9.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item10.setBackgroundColor(getResources().getColor(R.color.white));
                    item10.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item11.setBackgroundColor(getResources().getColor(R.color.white));
                    item11.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item12.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item12.setTextColor(getResources().getColor(R.color.white));
                    item13.setBackgroundColor(getResources().getColor(R.color.white));
                    item13.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                } else if (huiViewDetail.getCurrentItem() == 13) {
                    item0.setBackgroundColor(getResources().getColor(R.color.white));
                    item0.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item1.setBackgroundColor(getResources().getColor(R.color.white));
                    item1.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item2.setBackgroundColor(getResources().getColor(R.color.white));
                    item2.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item3.setBackgroundColor(getResources().getColor(R.color.white));
                    item3.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item4.setBackgroundColor(getResources().getColor(R.color.white));
                    item4.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item5.setBackgroundColor(getResources().getColor(R.color.white));
                    item5.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item6.setBackgroundColor(getResources().getColor(R.color.white));
                    item6.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item7.setBackgroundColor(getResources().getColor(R.color.white));
                    item7.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item8.setBackgroundColor(getResources().getColor(R.color.white));
                    item8.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item9.setBackgroundColor(getResources().getColor(R.color.white));
                    item9.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item10.setBackgroundColor(getResources().getColor(R.color.white));
                    item10.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item11.setBackgroundColor(getResources().getColor(R.color.white));
                    item11.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item12.setBackgroundColor(getResources().getColor(R.color.white));
                    item12.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item13.setBackgroundColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    item13.setTextColor(getResources().getColor(R.color.white));
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        list.add(huiView);

        viewPager.setAdapter(new AdsViewPagerAdapter(list));
        viewPager.setCurrentItem(0);
        if (viewPager.getCurrentItem() == 0) {
            cloudClassify.setTextColor(getResources().getColor(R.color.white));
            cloudClassify.setBackgroundColor(getResources().getColor(R.color.main_line_blue_color));
            huiClassify.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
            huiClassify.setBackgroundColor(getResources().getColor(R.color.white));
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (viewPager.getCurrentItem() == 0) {
                    cloudClassify.setTextColor(getResources().getColor(R.color.white));
                    cloudClassify.setBackgroundColor(getResources().getColor(R.color.main_line_blue_color));
                    huiClassify.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    huiClassify.setBackgroundColor(getResources().getColor(R.color.white));
                } else if (viewPager.getCurrentItem() == 1) {
                    cloudClassify.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    cloudClassify.setBackgroundColor(getResources().getColor(R.color.white));
                    huiClassify.setTextColor(getResources().getColor(R.color.white));
                    huiClassify.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
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
        cloudClassify.setOnClickListener(new OnClickListeners());
        huiClassify.setOnClickListener(new OnClickListeners());


        return rootView;
    }
    private void init() {
        viewPager = (ScrollViewInViewPager) rootView.findViewById(R.id.all_buy_menu_hui_view_pager);
        cloudClassify = (TextView) rootView.findViewById(R.id.all_buy_menu_cloud_classify);
        huiClassify = (TextView) rootView.findViewById(R.id.all_buy_menu_hui_classify);
        backup = (ImageView)rootView. findViewById(R.id.all_menu_backup);
    }

    private class OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,AllCloudClassItem.class);
        Intent intent2 = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.all_buy_menu_cloud_classify:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.all_buy_menu_hui_classify:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.all_menu_backup:
//                    finish();
                    break;
                case R.id.shoujizhineng:
                    intent.putExtra("categoryTitle","手机智能");
                    intent.putExtra("category_id" , "1");
                    startActivity(intent);
                    break;
                case R.id.diannaobangong:
                    intent.putExtra("categoryTitle", "电脑办公");
                    intent.putExtra("category_id" , "2");
                    startActivity(intent);
                    break;
                case R.id.xishuhufu:
                    intent.putExtra("categoryTitle","洗漱护肤");
                    intent.putExtra("category_id" , "3");
                    startActivity(intent);
                    break;
                case R.id.chuandaimingpin:
                    intent.putExtra("categoryTitle","穿戴名品");
                    intent.putExtra("category_id" , "4");
                    startActivity(intent);
                    break;
                case R.id.jingmeishipin:
                    intent.putExtra("categoryTitle","精美饰品");
                    intent.putExtra("category_id" , "5");
                    startActivity(intent);
                    break;
                case R.id.yundonghuwai:
                    intent.putExtra("categoryTitle","运动户外");
                    intent.putExtra("category_id" , "6");
                    startActivity(intent);
                    break;
                case R.id.shenghuoyongpin:
                    intent.putExtra("categoryTitle","生活用品");
                    intent.putExtra("category_id" , "7");
                    startActivity(intent);
                    break;
                case R.id.chuangyilipin:
                    intent.putExtra("categoryTitle","创意礼品");
                    intent.putExtra("category_id" , "8");
                    startActivity(intent);
                    break;
                case R.id.qita:
                    intent.putExtra("categoryTitle","其它");
                    intent.putExtra("category_id" , "9");
                    startActivity(intent);
                    break;


                case R.id.all_buy_menu_hui_item_0:
                    huiViewDetail.setCurrentItem(0);
                    // Toast.makeText(AllBuyMenuActivity.mActivity,"0",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.all_buy_menu_hui_item_1:
                    huiViewDetail.setCurrentItem(1);
                    //Toast.makeText(AllBuyMenuActivity.mActivity,"1",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.all_buy_menu_hui_item_2:
                    huiViewDetail.setCurrentItem(2);
                    //Toast.makeText(AllBuyMenuActivity.mActivity,"2",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.all_buy_menu_hui_item_3:
                    huiViewDetail.setCurrentItem(3);
                    //Toast.makeText(AllBuyMenuActivity.mActivity,"3",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.all_buy_menu_hui_item_4:
                    huiViewDetail.setCurrentItem(4);
                    //Toast.makeText(AllBuyMenuActivity.mActivity,"4",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.all_buy_menu_hui_item_5:
                    huiViewDetail.setCurrentItem(5);
                    // Toast.makeText(AllBuyMenuActivity.mActivity,"5",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.all_buy_menu_hui_item_6:
                    huiViewDetail.setCurrentItem(6);
                    // Toast.makeText(AllBuyMenuActivity.mActivity,"6",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.all_buy_menu_hui_item_7:
                    huiViewDetail.setCurrentItem(7);
                    // Toast.makeText(AllBuyMenuActivity.mActivity,"7",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.all_buy_menu_hui_item_8:
                    huiViewDetail.setCurrentItem(8);
                    // Toast.makeText(AllBuyMenuActivity.mActivity,"8",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.all_buy_menu_hui_item_9:
                    huiViewDetail.setCurrentItem(9);
                    // Toast.makeText(AllBuyMenuActivity.mActivity,"9",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.all_buy_menu_hui_item_10:
                    huiViewDetail.setCurrentItem(10);
                    //  Toast.makeText(AllBuyMenuActivity.mActivity,"10",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.all_buy_menu_hui_item_11:
                    huiViewDetail.setCurrentItem(11);
                    // Toast.makeText(AllBuyMenuActivity.mActivity,"11",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.all_buy_menu_hui_item_12:
                    huiViewDetail.setCurrentItem(12);
                    //  Toast.makeText(AllBuyMenuActivity.mActivity,"12",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.all_buy_menu_hui_item_13:
                    huiViewDetail.setCurrentItem(13);
                    // Toast.makeText(AllBuyMenuActivity.mActivity,"13",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    //热门推荐
    private class Cls11OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cls_1_1:
                    intent.putExtra("categoryTitle","男生上衣");
                    intent.putExtra("category_id","29");
                    startActivity(intent);
                    break;
                case R.id.cls_1_2:
                    intent.putExtra("categoryTitle","男生下衣");
                    intent.putExtra("category_id","30");
                    startActivity(intent);
                    break;
                case R.id.cls_1_3:
                    intent.putExtra("categoryTitle","鞋帽皮具");
                    intent.putExtra("category_id","31");
                    startActivity(intent);
                    break;
                case R.id.cls_2_1:
                    intent.putExtra("categoryTitle","女生上衣");
                    intent.putExtra("category_id","32");
                    startActivity(intent);
                    break;
                case R.id.cls_2_2:
                    intent.putExtra("categoryTitle","女生下衣");
                    intent.putExtra("category_id","33");
                    startActivity(intent);
                    break;
                case R.id.cls_2_3:
                    intent.putExtra("categoryTitle","鞋帽皮具");
                    intent.putExtra("category_id","34");
                    startActivity(intent);
                    break;
            }
        }
    }

    //潮流女装
    private class Cls2OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cls_1_01:intent.putExtra("categoryTitle","吊带背心");intent.putExtra("category_id","282");startActivity(intent);break;
                case R.id.cls_1_02:intent.putExtra("categoryTitle","T恤");intent.putExtra("category_id","35");startActivity(intent);break;
                case R.id.cls_1_03:intent.putExtra("categoryTitle","衬衣");intent.putExtra("category_id","36");startActivity(intent);break;
                case R.id.cls_1_1:intent.putExtra("categoryTitle","雪纺衫");intent.putExtra("category_id","277");startActivity(intent);break;
                case R.id.cls_1_2:intent.putExtra("categoryTitle","连衣裙");intent.putExtra("category_id","274");startActivity(intent);break;
                case R.id.cls_1_3:intent.putExtra("categoryTitle","针织衫");intent.putExtra("category_id","37");startActivity(intent);break;
                case R.id.cls_1_4:intent.putExtra("categoryTitle","卫衣");intent.putExtra("category_id","38");startActivity(intent);break;
                case R.id.cls_1_5:intent.putExtra("categoryTitle","夹克");intent.putExtra("category_id","39");startActivity(intent);break;
                case R.id.cls_1_6:intent.putExtra("categoryTitle","风衣");intent.putExtra("category_id","40");startActivity(intent);break;
                case R.id.cls_1_7:intent.putExtra("categoryTitle","毛呢大衣");intent.putExtra("category_id","41");startActivity(intent);break;
                case R.id.cls_1_8:intent.putExtra("categoryTitle","皮衣");intent.putExtra("category_id","42");startActivity(intent);break;
                case R.id.cls_1_9:intent.putExtra("categoryTitle","羽绒服");intent.putExtra("category_id","43");startActivity(intent);break;
                // case R.id.cls_2_1:intent.putExtra("categoryTitle","打底裤");intent.putExtra("category_id","44");startActivity(intent);break;
                case R.id.cls_2_1:intent.putExtra("categoryTitle","裙子");intent.putExtra("category_id","46");startActivity(intent);break;
                case R.id.cls_2_2:intent.putExtra("categoryTitle","打底裤");intent.putExtra("category_id","44");startActivity(intent);break;
                //case R.id.cls_2_2:intent.putExtra("categoryTitle","牛仔裤");intent.putExtra("category_id","45");startActivity(intent);break;
                case R.id.cls_2_3:intent.putExtra("categoryTitle","牛仔裤");intent.putExtra("category_id","45");startActivity(intent);break;
                //case R.id.cls_2_3:intent.putExtra("categoryTitle","裙子");intent.putExtra("category_id","46");startActivity(intent);break;
                case R.id.cls_2_4: {intent.putExtra("categoryTitle", cls_2_2_4t.getText().toString().trim());intent.putExtra("category_id","47");startActivity(intent);}break;
                case R.id.cls_2_5: {intent.putExtra("categoryTitle", cls_2_2_5t.getText().toString().trim());intent.putExtra("category_id","48");startActivity(intent);}break;
                case R.id.cls_2_6: {intent.putExtra("categoryTitle", cls_2_2_6t.getText().toString().trim());intent.putExtra("category_id","");startActivity(intent);}break;

                case R.id.cls_3_1: {intent.putExtra("categoryTitle", cls_2_3_1t.getText().toString().trim());intent.putExtra("category_id","52");startActivity(intent);}break;
                case R.id.cls_3_2: {intent.putExtra("categoryTitle", cls_2_3_2t.getText().toString().trim());intent.putExtra("category_id","51");startActivity(intent);}break;
                case R.id.cls_3_3: {intent.putExtra("categoryTitle", cls_2_3_3t.getText().toString().trim());intent.putExtra("category_id","53");startActivity(intent);}break;
                case R.id.cls_3_4: {intent.putExtra("categoryTitle", cls_2_3_4t.getText().toString().trim());intent.putExtra("category_id","50");startActivity(intent);}break;
            }
        }
    }

    //时尚男装
    private class Cls3OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cls_1_1: {intent.putExtra("categoryTitle",cls_3_1_1_t.getText().toString().trim());intent.putExtra("category_id","279");startActivity(intent);}break;
                case R.id.cls_1_2: {intent.putExtra("categoryTitle",cls_3_1_2_t.getText().toString().trim());intent.putExtra("category_id","55");startActivity(intent);}break;
                case R.id.cls_1_3: {intent.putExtra("categoryTitle",cls_3_1_3_t.getText().toString().trim());intent.putExtra("category_id","56");startActivity(intent);}break;
                case R.id.cls_1_4: {intent.putExtra("categoryTitle",cls_3_1_4_t.getText().toString().trim());intent.putExtra("category_id","54");startActivity(intent);}break;
                case R.id.cls_1_5: {intent.putExtra("categoryTitle",cls_3_1_5_t.getText().toString().trim());intent.putExtra("category_id","57");startActivity(intent);}break;
                case R.id.cls_1_6: {intent.putExtra("categoryTitle",cls_3_1_6_t.getText().toString().trim());intent.putExtra("category_id","58");startActivity(intent);}break;
                case R.id.cls_1_7: {intent.putExtra("categoryTitle",cls_3_1_7_t.getText().toString().trim());intent.putExtra("category_id","59");startActivity(intent);}break;
                case R.id.cls_1_8: {intent.putExtra("categoryTitle",cls_3_1_8_t.getText().toString().trim());intent.putExtra("category_id","60");startActivity(intent);}break;
                case R.id.cls_1_9: {intent.putExtra("categoryTitle",cls_3_1_9_t.getText().toString().trim());intent.putExtra("category_id","61");startActivity(intent);}break;
                case R.id.cls_2_1: {intent.putExtra("categoryTitle",cls_3_2_1_t.getText().toString().trim());intent.putExtra("category_id","280");startActivity(intent);}break;
                case R.id.cls_2_2: {intent.putExtra("categoryTitle",cls_3_2_2_t.getText().toString().trim());intent.putExtra("category_id","62");startActivity(intent);}break;
                case R.id.cls_2_3: {intent.putExtra("categoryTitle",cls_3_2_3_t.getText().toString().trim());intent.putExtra("category_id","63");startActivity(intent);}break;
                // case R.id.cls_2_4: {intent.putExtra("categoryTitle",cls_3_2_01_t.getText().toString().trim());intent.putExtra("category_id","64");startActivity(intent);}break;

                //
                case R.id.cls_2_01: {intent.putExtra("categoryTitle",cls_3_2_01_t.getText().toString().trim());intent.putExtra("category_id","64");startActivity(intent);}break;
                case R.id.cls_3_1: {intent.putExtra("categoryTitle",cls_3_3_1_t.getText().toString().trim());intent.putExtra("category_id","66");startActivity(intent);}break;
                // TODO: 01/09/2016
                case R.id.cls_3_2: {intent.putExtra("categoryTitle",cls_3_3_2_t.getText().toString().trim());intent.putExtra("category_id","257");startActivity(intent);}break;
                case R.id.cls_3_3: {intent.putExtra("categoryTitle",cls_3_3_3_t.getText().toString().trim());intent.putExtra("category_id","67");startActivity(intent);}break;
                case R.id.cls_3_4: {intent.putExtra("categoryTitle",cls_3_3_4_t.getText().toString().trim());intent.putExtra("category_id","65");startActivity(intent);}break;
            }
        }
    }

    //鞋帽箱包
    private class Cls4OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cls_1_1: {intent.putExtra("categoryTitle",cls_4_1_1_t.getText().toString().trim());intent.putExtra("category_id","291");startActivity(intent);}break;
                case R.id.cls_1_2: {intent.putExtra("categoryTitle",cls_4_1_2_t.getText().toString().trim());intent.putExtra("category_id","234");startActivity(intent);}break;
                case R.id.cls_1_3: {intent.putExtra("categoryTitle",cls_4_1_3_t.getText().toString().trim());intent.putExtra("category_id","235");startActivity(intent);}break;
                case R.id.cls_1_4: {intent.putExtra("categoryTitle",cls_4_1_4_t.getText().toString().trim());intent.putExtra("category_id","236");startActivity(intent);}break;
                case R.id.cls_1_5: {intent.putExtra("categoryTitle",cls_4_1_5_t.getText().toString().trim());intent.putExtra("category_id","239");startActivity(intent);}break;
                case R.id.cls_1_6: {intent.putExtra("categoryTitle",cls_4_1_6_t.getText().toString().trim());intent.putExtra("category_id","238");startActivity(intent);}break;
                case R.id.cls_1_7: {intent.putExtra("categoryTitle",cls_4_1_7_t.getText().toString().trim());intent.putExtra("category_id","237");startActivity(intent);}break;
                case R.id.cls_1_8: {intent.putExtra("categoryTitle",cls_4_1_8_t.getText().toString().trim());intent.putExtra("category_id","240");startActivity(intent);}break;
                case R.id.cls_2_1: {intent.putExtra("categoryTitle",cls_4_2_1_t.getText().toString().trim());intent.putExtra("category_id","247");startActivity(intent);}break;
                case R.id.cls_2_2: {intent.putExtra("categoryTitle",cls_4_2_2_t.getText().toString().trim());intent.putExtra("category_id","248");startActivity(intent);}break;
                case R.id.cls_2_3: {intent.putExtra("categoryTitle",cls_4_2_3_t.getText().toString().trim());intent.putExtra("category_id","249");startActivity(intent);}break;
                case R.id.cls_2_4: {intent.putExtra("categoryTitle",cls_4_2_4_t.getText().toString().trim());intent.putExtra("category_id","250");startActivity(intent);}break;
                case R.id.cls_3_1: {intent.putExtra("categoryTitle",cls_4_3_1_t.getText().toString().trim());intent.putExtra("category_id","251");startActivity(intent);}break;
                case R.id.cls_3_2: {intent.putExtra("categoryTitle",cls_4_3_2_t.getText().toString().trim());intent.putExtra("category_id","252");startActivity(intent);}break;
                case R.id.cls_3_3: {intent.putExtra("categoryTitle",cls_4_3_3_t.getText().toString().trim());intent.putExtra("category_id","253");startActivity(intent);}break;
                // TODO: 01/09/2016  
                case R.id.cls_4_1: {intent.putExtra("categoryTitle",cls_4_4_1_t.getText().toString().trim());intent.putExtra("category_id","292");startActivity(intent);}break;
                case R.id.cls_4_2: {intent.putExtra("categoryTitle",cls_4_4_2_t.getText().toString().trim());intent.putExtra("category_id","241");startActivity(intent);}break;
                case R.id.cls_4_3: {intent.putExtra("categoryTitle",cls_4_4_3_t.getText().toString().trim());intent.putExtra("category_id","242");startActivity(intent);}break;
                case R.id.cls_4_4: {intent.putExtra("categoryTitle",cls_4_4_4_t.getText().toString().trim());intent.putExtra("category_id","243");startActivity(intent);}break;
                case R.id.cls_4_5: {intent.putExtra("categoryTitle",cls_4_4_5_t.getText().toString().trim());intent.putExtra("category_id","244");startActivity(intent);}break;
                case R.id.cls_4_6: {intent.putExtra("categoryTitle",cls_4_4_6_t.getText().toString().trim());intent.putExtra("category_id","245");startActivity(intent);}break;
                case R.id.cls_4_7: {intent.putExtra("categoryTitle",cls_4_4_7_t.getText().toString().trim());intent.putExtra("category_id","246");startActivity(intent);}break;
                case R.id.cls_5_1: {intent.putExtra("categoryTitle",cls_4_5_1_t.getText().toString().trim());intent.putExtra("category_id","254");startActivity(intent);}break;
            }
        }
    }

    //手机数码
    private class Cls5OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cls_1_1: {intent.putExtra("categoryTitle",cls_5_1_1_t.getText().toString().trim());intent.putExtra("category_id","112");startActivity(intent);}break;
                case R.id.cls_1_2: {intent.putExtra("categoryTitle",cls_5_1_2_t.getText().toString().trim());intent.putExtra("category_id","113");startActivity(intent);}break;
                case R.id.cls_1_3: {intent.putExtra("categoryTitle",cls_5_1_3_t.getText().toString().trim());intent.putExtra("category_id","114");startActivity(intent);}break;
                case R.id.cls_1_4: {intent.putExtra("categoryTitle",cls_5_1_4_t.getText().toString().trim());intent.putExtra("category_id","115");startActivity(intent);}break;
                case R.id.cls_1_5: {intent.putExtra("categoryTitle",cls_5_1_5_t.getText().toString().trim());intent.putExtra("category_id","116");startActivity(intent);}break;
                case R.id.cls_1_6: {intent.putExtra("categoryTitle",cls_5_1_6_t.getText().toString().trim());intent.putExtra("category_id","117");startActivity(intent);}break;
                case R.id.cls_1_7: {intent.putExtra("categoryTitle",cls_5_1_7_t.getText().toString().trim());intent.putExtra("category_id","118");startActivity(intent);}break;
                case R.id.cls_1_8: {intent.putExtra("categoryTitle",cls_5_1_8_t.getText().toString().trim());intent.putExtra("category_id","273");startActivity(intent);}break;
                case R.id.cls_2_1: {intent.putExtra("categoryTitle",cls_5_2_1_t.getText().toString().trim());intent.putExtra("category_id","120");startActivity(intent);}break;
                case R.id.cls_2_2: {intent.putExtra("categoryTitle",cls_5_2_2_t.getText().toString().trim());intent.putExtra("category_id","122");startActivity(intent);}break;
//                case R.id.cls_2_3: {intent.putExtra("categoryTitle",cls_5_2_3_t.getText().toString().trim());intent.putExtra("category_id","");startActivity(intent);}break;
                case R.id.cls_3_1: {intent.putExtra("categoryTitle",cls_5_3_1_t.getText().toString().trim());intent.putExtra("category_id","123");startActivity(intent);}break;
                case R.id.cls_3_2: {intent.putExtra("categoryTitle",cls_5_3_2_t.getText().toString().trim());intent.putExtra("category_id","124");startActivity(intent);}break;
                case R.id.cls_3_3: {intent.putExtra("categoryTitle",cls_5_3_3_t.getText().toString().trim());intent.putExtra("category_id","125");startActivity(intent);}break;
                case R.id.cls_3_4: {intent.putExtra("categoryTitle",cls_5_3_4_t.getText().toString().trim());intent.putExtra("category_id","126");startActivity(intent);}break;
                case R.id.cls_3_5: {intent.putExtra("categoryTitle",cls_5_3_5_t.getText().toString().trim());intent.putExtra("category_id","127");startActivity(intent);}break;
                case R.id.cls_3_6: {intent.putExtra("categoryTitle",cls_5_3_6_t.getText().toString().trim());intent.putExtra("category_id","128");startActivity(intent);}break;
                case R.id.cls_3_7: {intent.putExtra("categoryTitle",cls_5_3_7_t.getText().toString().trim());intent.putExtra("category_id","129");startActivity(intent);}break;
                case R.id.cls_3_8: {intent.putExtra("categoryTitle",cls_5_3_8_t.getText().toString().trim());intent.putExtra("category_id","130");startActivity(intent);}break;
            }
        }
    }

    //个护化妆
    private class Clc6OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cls_1_1: {intent.putExtra("categoryTitle",cls_6_1_1_t.getText().toString().trim());intent.putExtra("category_id","131");startActivity(intent);}break;
                case R.id.cls_1_2: {intent.putExtra("categoryTitle",cls_6_1_2_t.getText().toString().trim());intent.putExtra("category_id","286");startActivity(intent);}break;
                case R.id.cls_1_3: {intent.putExtra("categoryTitle",cls_6_1_3_t.getText().toString().trim());intent.putExtra("category_id","132");startActivity(intent);}break;
                case R.id.cls_1_4: {intent.putExtra("categoryTitle",cls_6_1_4_t.getText().toString().trim());intent.putExtra("category_id","133");startActivity(intent);}break;
                case R.id.cls_1_5: {intent.putExtra("categoryTitle",cls_6_1_5_t.getText().toString().trim());intent.putExtra("category_id","134");startActivity(intent);}break;
                case R.id.cls_1_6: {intent.putExtra("categoryTitle",cls_6_1_6_t.getText().toString().trim());intent.putExtra("category_id","135");startActivity(intent);}break;
                case R.id.cls_1_7: {intent.putExtra("categoryTitle",cls_6_1_7_t.getText().toString().trim());intent.putExtra("category_id","136");startActivity(intent);}break;





                case R.id.cls_2_1: {intent.putExtra("categoryTitle",cls_6_2_1_t.getText().toString().trim());intent.putExtra("category_id","137");startActivity(intent);}break;
                case R.id.cls_2_2: {intent.putExtra("categoryTitle",cls_6_2_2_t.getText().toString().trim());intent.putExtra("category_id","285");startActivity(intent);}break;
                case R.id.cls_2_3: {intent.putExtra("categoryTitle",cls_6_2_3_t.getText().toString().trim());intent.putExtra("category_id","138");startActivity(intent);}break;
                case R.id.cls_2_4: {intent.putExtra("categoryTitle",cls_6_2_4_t.getText().toString().trim());intent.putExtra("category_id","139");startActivity(intent);}break;
                case R.id.cls_2_5: {intent.putExtra("categoryTitle",cls_6_2_5_t.getText().toString().trim());intent.putExtra("category_id","140");startActivity(intent);}break;
                case R.id.cls_2_6: {intent.putExtra("categoryTitle",cls_6_2_6_t.getText().toString().trim());intent.putExtra("category_id","141");startActivity(intent);}break;
                case R.id.cls_2_7: {intent.putExtra("categoryTitle",cls_6_2_7_t.getText().toString().trim());intent.putExtra("category_id","142");startActivity(intent);}break;
                case R.id.cls_2_8: {intent.putExtra("categoryTitle",cls_6_2_8_t.getText().toString().trim());intent.putExtra("category_id","143");startActivity(intent);}break;
                case R.id.cls_2_9: {intent.putExtra("categoryTitle",cls_6_2_9_t.getText().toString().trim());intent.putExtra("category_id","144");startActivity(intent);}break;
                case R.id.cls_2_10: {intent.putExtra("categoryTitle",cls_6_2_10_t.getText().toString().trim());intent.putExtra("category_id","145");startActivity(intent);}break;
                case R.id.cls_2_11: {intent.putExtra("categoryTitle",cls_6_2_11_t.getText().toString().trim());intent.putExtra("category_id","146");startActivity(intent);}break;
                case R.id.cls_2_12: {intent.putExtra("categoryTitle",cls_6_2_12_t.getText().toString().trim());intent.putExtra("category_id","147");startActivity(intent);}break;
                case R.id.cls_2_13: {intent.putExtra("categoryTitle",cls_6_2_13_t.getText().toString().trim());intent.putExtra("category_id","148");startActivity(intent);}break;
                case R.id.cls_2_14: {intent.putExtra("categoryTitle",cls_6_2_14_t.getText().toString().trim());intent.putExtra("category_id","150");startActivity(intent);}break;
                case R.id.cls_2_15: {intent.putExtra("categoryTitle",cls_6_2_15_t.getText().toString().trim());intent.putExtra("category_id","149");startActivity(intent);}break;
                case R.id.cls_2_16: {intent.putExtra("categoryTitle",cls_6_2_16_t.getText().toString().trim());intent.putExtra("category_id","151");startActivity(intent);}break;
                case R.id.cls_2_17: {intent.putExtra("categoryTitle",cls_6_2_17_t.getText().toString().trim());intent.putExtra("category_id","152");startActivity(intent);}break;
            }

        }
    }

    //精美饰品
    private class Cls7OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cls_1_1: {intent.putExtra("categoryTitle",cls_7_1_1_t.getText().toString().trim());intent.putExtra("category_id","153");startActivity(intent);}break;
                case R.id.cls_1_2: {intent.putExtra("categoryTitle",cls_7_1_2_t.getText().toString().trim());intent.putExtra("category_id","154");startActivity(intent);}break;
                case R.id.cls_1_3: {intent.putExtra("categoryTitle",cls_7_1_3_t.getText().toString().trim());intent.putExtra("category_id","155");startActivity(intent);}break;
                case R.id.cls_1_4: {intent.putExtra("categoryTitle",cls_7_1_4_t.getText().toString().trim());intent.putExtra("category_id","156");startActivity(intent);}break;
                case R.id.cls_1_5: {intent.putExtra("categoryTitle",cls_7_1_5_t.getText().toString().trim());intent.putExtra("category_id","157");startActivity(intent);}break;
                case R.id.cls_2_1: {intent.putExtra("categoryTitle",cls_7_2_1_t.getText().toString().trim());intent.putExtra("category_id","158");startActivity(intent);}break;
                case R.id.cls_2_2: {intent.putExtra("categoryTitle",cls_7_2_2_t.getText().toString().trim());intent.putExtra("category_id","159");startActivity(intent);}break;
                case R.id.cls_2_3: {intent.putExtra("categoryTitle",cls_7_2_3_t.getText().toString().trim());intent.putExtra("category_id","160");startActivity(intent);}break;
                case R.id.cls_2_4: {intent.putExtra("categoryTitle",cls_7_2_4_t.getText().toString().trim());intent.putExtra("category_id","161");startActivity(intent);}break;
                case R.id.cls_2_5: {intent.putExtra("categoryTitle",cls_7_2_5_t.getText().toString().trim());intent.putExtra("category_id","162");startActivity(intent);}break;
                case R.id.cls_2_6: {intent.putExtra("categoryTitle",cls_7_2_6_t.getText().toString().trim());intent.putExtra("category_id","163");startActivity(intent);}break;
            }
        }
    }

    //天下美食
    private class Cls8OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cls_1_1: {intent.putExtra("categoryTitle",cls_8_1_1_t.getText().toString().trim());intent.putExtra("category_id","80");startActivity(intent);}break;
                case R.id.cls_1_2: {intent.putExtra("categoryTitle",cls_8_1_2_t.getText().toString().trim());intent.putExtra("category_id","81");startActivity(intent);}break;
                case R.id.cls_1_3: {intent.putExtra("categoryTitle",cls_8_1_3_t.getText().toString().trim());intent.putExtra("category_id","82");startActivity(intent);}break;
//                case R.id.cls_1_4: {intent.putExtra("categoryTitle",cls_8_1_4_t.getText().toString().trim());intent.putExtra("category_id","");startActivity(intent);}break;
            }
        }
    }

    //日用百货
    private class Cls9OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cls_1_1: {intent.putExtra("categoryTitle",cls_9_1_1_t.getText().toString().trim());intent.putExtra("category_id","287");startActivity(intent);}break;
                case R.id.cls_1_2: {intent.putExtra("categoryTitle",cls_9_1_2_t.getText().toString().trim());intent.putExtra("category_id","166");startActivity(intent);}break;
                case R.id.cls_1_3: {intent.putExtra("categoryTitle",cls_9_1_3_t.getText().toString().trim());intent.putExtra("category_id","174");startActivity(intent);}break;
                case R.id.cls_1_4: {intent.putExtra("categoryTitle",cls_9_1_4_t.getText().toString().trim());intent.putExtra("category_id","164");startActivity(intent);}break;
                case R.id.cls_1_5: {intent.putExtra("categoryTitle",cls_9_1_5_t.getText().toString().trim());intent.putExtra("category_id","176");startActivity(intent);}break;
                case R.id.cls_1_6: {intent.putExtra("categoryTitle",cls_9_1_6_t.getText().toString().trim());intent.putExtra("category_id","167");startActivity(intent);}break;
                case R.id.cls_1_7: {intent.putExtra("categoryTitle",cls_9_1_7_t.getText().toString().trim());intent.putExtra("category_id","288");startActivity(intent);}break;
                case R.id.cls_1_8: {intent.putExtra("categoryTitle",cls_9_1_8_t.getText().toString().trim());intent.putExtra("category_id","168");startActivity(intent);}break;
                case R.id.cls_1_9: {intent.putExtra("categoryTitle",cls_9_1_9_t.getText().toString().trim());intent.putExtra("category_id","289");;startActivity(intent);}break;
                case R.id.cls_1_10: {intent.putExtra("categoryTitle",cls_9_1_10_t.getText().toString().trim());intent.putExtra("category_id","169");startActivity(intent);}break;
                case R.id.cls_1_11: {intent.putExtra("categoryTitle",cls_9_1_11_t.getText().toString().trim());intent.putExtra("category_id","171");startActivity(intent);}break;
                case R.id.cls_1_12: {intent.putExtra("categoryTitle",cls_9_1_12_t.getText().toString().trim());intent.putExtra("category_id","175");startActivity(intent);}break;
                case R.id.cls_1_13: {intent.putExtra("categoryTitle",cls_9_1_13_t.getText().toString().trim());intent.putExtra("category_id","173");startActivity(intent);}break;
                case R.id.cls_1_14: {intent.putExtra("categoryTitle",cls_9_1_14_t.getText().toString().trim());intent.putExtra("category_id","165");startActivity(intent);}break;
                case R.id.cls_1_15: {intent.putExtra("categoryTitle",cls_9_1_15_t.getText().toString().trim());intent.putExtra("category_id","172");startActivity(intent);}break;



                case R.id.cls_2_1: {intent.putExtra("categoryTitle",cls_9_2_1_t.getText().toString().trim());intent.putExtra("category_id","177");startActivity(intent);}break;
                case R.id.cls_2_2: {intent.putExtra("categoryTitle",cls_9_2_2_t.getText().toString().trim());intent.putExtra("category_id","178");startActivity(intent);}break;
                case R.id.cls_2_3: {intent.putExtra("categoryTitle",cls_9_2_3_t.getText().toString().trim());intent.putExtra("category_id","179");startActivity(intent);}break;
                case R.id.cls_2_4: {intent.putExtra("categoryTitle",cls_9_2_4_t.getText().toString().trim());intent.putExtra("category_id","180");startActivity(intent);}break;
                case R.id.cls_2_5: {intent.putExtra("categoryTitle",cls_9_2_5_t.getText().toString().trim());intent.putExtra("category_id","181");startActivity(intent);}break;
                case R.id.cls_2_6: {intent.putExtra("categoryTitle",cls_9_2_6_t.getText().toString().trim());intent.putExtra("category_id","182");startActivity(intent);}break;
                case R.id.cls_2_7: {intent.putExtra("categoryTitle",cls_9_2_7_t.getText().toString().trim());intent.putExtra("category_id","183");startActivity(intent);}break;
                case R.id.cls_2_8: {intent.putExtra("categoryTitle",cls_9_2_8_t.getText().toString().trim());intent.putExtra("category_id","184");startActivity(intent);}break;
                case R.id.cls_2_9: {intent.putExtra("categoryTitle",cls_9_2_9_t.getText().toString().trim());intent.putExtra("category_id","259");startActivity(intent);}break;
                case R.id.cls_2_10: {intent.putExtra("categoryTitle",cls_9_2_10_t.getText().toString().trim());intent.putExtra("category_id","185");startActivity(intent);}break;
                case R.id.cls_2_11: {intent.putExtra("categoryTitle",cls_9_2_11_t.getText().toString().trim());intent.putExtra("category_id","290");startActivity(intent);}break;
                case R.id.cls_3_1: {intent.putExtra("categoryTitle",cls_9_3_1_t.getText().toString().trim());intent.putExtra("category_id","186");startActivity(intent);}break;
                case R.id.cls_3_2: {intent.putExtra("categoryTitle",cls_9_3_2_t.getText().toString().trim());intent.putExtra("category_id","187");startActivity(intent);}break;
                case R.id.cls_3_3: {intent.putExtra("categoryTitle",cls_9_3_3_t.getText().toString().trim());intent.putExtra("category_id","188");startActivity(intent);}break;
                case R.id.cls_3_4: {intent.putExtra("categoryTitle",cls_9_3_4_t.getText().toString().trim());intent.putExtra("category_id","189");startActivity(intent);}break;
            }
        }
    }

    //运动户外
    private class Cls10OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cls_1_1: {intent.putExtra("categoryTitle",cls_10_1_1_t.getText().toString().trim());intent.putExtra("category_id","190");startActivity(intent);}break;
                case R.id.cls_1_2: {intent.putExtra("categoryTitle",cls_10_1_2_t.getText().toString().trim());intent.putExtra("category_id","191");startActivity(intent);}break;
                case R.id.cls_1_3: {intent.putExtra("categoryTitle",cls_10_1_3_t.getText().toString().trim());intent.putExtra("category_id","192");startActivity(intent);}break;
                case R.id.cls_1_4: {intent.putExtra("categoryTitle",cls_10_1_4_t.getText().toString().trim());intent.putExtra("category_id","193");startActivity(intent);}break;
                case R.id.cls_1_5: {intent.putExtra("categoryTitle",cls_10_1_5_t.getText().toString().trim());intent.putExtra("category_id","194");startActivity(intent);}break;
                case R.id.cls_1_6: {intent.putExtra("categoryTitle",cls_10_1_6_t.getText().toString().trim());intent.putExtra("category_id","195");startActivity(intent);}break;
                case R.id.cls_1_7: {intent.putExtra("categoryTitle",cls_10_1_7_t.getText().toString().trim());intent.putExtra("category_id","196");startActivity(intent);}break;
                case R.id.cls_1_8: {intent.putExtra("categoryTitle",cls_10_1_8_t.getText().toString().trim());intent.putExtra("category_id","197");startActivity(intent);}break;
                case R.id.cls_1_9: {intent.putExtra("categoryTitle",cls_10_1_9_t.getText().toString().trim());intent.putExtra("category_id","198");startActivity(intent);}break;
                case R.id.cls_2_1: {intent.putExtra("categoryTitle",cls_10_2_1_t.getText().toString().trim());intent.putExtra("category_id","199");startActivity(intent);}break;
                case R.id.cls_2_2: {intent.putExtra("categoryTitle",cls_10_2_2_t.getText().toString().trim());intent.putExtra("category_id","200");startActivity(intent);}break;
                case R.id.cls_2_3: {intent.putExtra("categoryTitle",cls_10_2_3_t.getText().toString().trim());intent.putExtra("category_id","201");startActivity(intent);}break;
                case R.id.cls_2_4: {intent.putExtra("categoryTitle",cls_10_2_4_t.getText().toString().trim());intent.putExtra("category_id","202");startActivity(intent);}break;
                case R.id.cls_2_5: {intent.putExtra("categoryTitle",cls_10_2_5_t.getText().toString().trim());intent.putExtra("category_id","203");startActivity(intent);}break;
                case R.id.cls_2_6: {intent.putExtra("categoryTitle",cls_10_2_6_t.getText().toString().trim());intent.putExtra("category_id","204");startActivity(intent);}break;
//                case R.id.cls_2_7: {intent.putExtra("categoryTitle",cls_10_2_7_t.getText().toString().trim());intent.putExtra("category_id","");startActivity(intent);}break;
            }
        }
    }

    //电脑办公
    private class Cls111OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cls_1_1: {intent.putExtra("categoryTitle",cls_11_1_1_t.getText().toString().trim());intent.putExtra("category_id","206");startActivity(intent);}break;
                case R.id.cls_1_2: {intent.putExtra("categoryTitle",cls_11_1_2_t.getText().toString().trim());intent.putExtra("category_id","207");startActivity(intent);}break;
                case R.id.cls_1_3: {intent.putExtra("categoryTitle",cls_11_1_3_t.getText().toString().trim());intent.putExtra("category_id","208");startActivity(intent);}break;
                case R.id.cls_2_1: {intent.putExtra("categoryTitle",cls_11_2_1_t.getText().toString().trim());intent.putExtra("category_id","209");startActivity(intent);}break;
                case R.id.cls_2_2: {intent.putExtra("categoryTitle",cls_11_2_2_t.getText().toString().trim());intent.putExtra("category_id","261");startActivity(intent);}break;
                case R.id.cls_2_3: {intent.putExtra("categoryTitle",cls_11_2_3_t.getText().toString().trim());intent.putExtra("category_id","211");startActivity(intent);}break;
                case R.id.cls_2_4: {intent.putExtra("categoryTitle",cls_11_2_4_t.getText().toString().trim());intent.putExtra("category_id","212");startActivity(intent);}break;
                case R.id.cls_2_5: {intent.putExtra("categoryTitle",cls_11_2_5_t.getText().toString().trim());intent.putExtra("category_id","213");startActivity(intent);}break;
                case R.id.cls_2_6: {intent.putExtra("categoryTitle",cls_11_2_6_t.getText().toString().trim());intent.putExtra("category_id","214");startActivity(intent);}break;
                case R.id.cls_2_7: {intent.putExtra("categoryTitle",cls_11_2_7_t.getText().toString().trim());intent.putExtra("category_id","215");startActivity(intent);}break;
                case R.id.cls_2_8: {intent.putExtra("categoryTitle",cls_11_2_8_t.getText().toString().trim());intent.putExtra("category_id","216");startActivity(intent);}break;
                //case R.id.cls_2_9: {intent.putExtra("categoryTitle",cls_11_2_9_t.getText().toString().trim());intent.putExtra("category_id","");startActivity(intent);}break;
                //case R.id.cls_2_10: {intent.putExtra("categoryTitle",cls_11_2_10_t.getText().toString().trim());intent.putExtra("category_id","");startActivity(intent);}break;
                case R.id.cls_3_1: {intent.putExtra("categoryTitle",cls_11_3_1_t.getText().toString().trim());intent.putExtra("category_id","219");startActivity(intent);}break;
                case R.id.cls_3_2: {intent.putExtra("categoryTitle",cls_11_3_2_t.getText().toString().trim());intent.putExtra("category_id","220");startActivity(intent);}break;
                case R.id.cls_3_3: {intent.putExtra("categoryTitle",cls_11_3_3_t.getText().toString().trim());intent.putExtra("category_id","221");startActivity(intent);}break;
            }
        }
    }

    //手表
    private class Cls12OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cls_1_1: {intent.putExtra("categoryTitle",cls_12_1_1_t.getText().toString().trim());intent.putExtra("category_id","222");startActivity(intent);}break;
                case R.id.cls_1_2: {intent.putExtra("categoryTitle",cls_12_1_2_t.getText().toString().trim());intent.putExtra("category_id","223");startActivity(intent);}break;
                case R.id.cls_1_3: {intent.putExtra("categoryTitle",cls_12_1_3_t.getText().toString().trim());intent.putExtra("category_id","224");startActivity(intent);}break;
                case R.id.cls_1_4: {intent.putExtra("categoryTitle",cls_12_1_4_t.getText().toString().trim());intent.putExtra("category_id","225");startActivity(intent);}break;
                case R.id.cls_1_5: {intent.putExtra("categoryTitle",cls_12_1_5_t.getText().toString().trim());intent.putExtra("category_id","226");startActivity(intent);}break;
                case R.id.cls_2_1: {intent.putExtra("categoryTitle",cls_12_2_1_t.getText().toString().trim());intent.putExtra("category_id","227");startActivity(intent);}break;
                case R.id.cls_2_2: {intent.putExtra("categoryTitle",cls_12_2_2_t.getText().toString().trim());intent.putExtra("category_id","228");startActivity(intent);}break;
                case R.id.cls_2_3: {intent.putExtra("categoryTitle",cls_12_2_3_t.getText().toString().trim());intent.putExtra("category_id","229");startActivity(intent);}break;
                case R.id.cls_2_4: {intent.putExtra("categoryTitle",cls_12_2_4_t.getText().toString().trim());intent.putExtra("category_id","230");startActivity(intent);}break;
                case R.id.cls_2_5: {intent.putExtra("categoryTitle",cls_12_2_5_t.getText().toString().trim());intent.putExtra("category_id","231");startActivity(intent);}break;
                case R.id.cls_3_1: {intent.putExtra("categoryTitle",cls_12_3_1_t.getText().toString().trim());intent.putExtra("category_id","232");startActivity(intent);}break;
                case R.id.cls_3_2: {intent.putExtra("categoryTitle",cls_12_3_2_t.getText().toString().trim());intent.putExtra("category_id","233");startActivity(intent);}break;
            }
        }
    }

    //创意礼品
    private class Cls13OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cls_1_1: {intent.putExtra("categoryTitle",cls_13_1_1_t.getText().toString().trim());intent.putExtra("category_id","95");startActivity(intent);}break;
                case R.id.cls_1_2: {intent.putExtra("categoryTitle",cls_13_1_2_t.getText().toString().trim());intent.putExtra("category_id","97");startActivity(intent);}break;
                case R.id.cls_1_3: {intent.putExtra("categoryTitle",cls_13_1_3_t.getText().toString().trim());intent.putExtra("category_id","98");startActivity(intent);}break;
                case R.id.cls_1_4: {intent.putExtra("categoryTitle",cls_13_1_4_t.getText().toString().trim());intent.putExtra("category_id","99");startActivity(intent);}break;
                case R.id.cls_1_5: {intent.putExtra("categoryTitle",cls_13_1_5_t.getText().toString().trim());intent.putExtra("category_id","100");startActivity(intent);}break;
                case R.id.cls_1_6: {intent.putExtra("categoryTitle",cls_13_1_6_t.getText().toString().trim());intent.putExtra("category_id","101");startActivity(intent);}break;
                case R.id.cls_1_7: {intent.putExtra("categoryTitle",cls_13_1_7_t.getText().toString().trim());intent.putExtra("category_id","102");startActivity(intent);}break;
                case R.id.cls_1_8: {intent.putExtra("categoryTitle",cls_13_1_8_t.getText().toString().trim());intent.putExtra("category_id","103");startActivity(intent);}break;
            }
        }
    }

    //营养保健
    private class Cls14OnClickListeners implements View.OnClickListener {
        Intent intent = new Intent(mActivity,HuiClassItemsActivity.class);
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cls_1_1: {intent.putExtra("categoryTitle",cls_14_1_1_t.getText().toString().trim());intent.putExtra("category_id","104");startActivity(intent);}break;
                case R.id.cls_1_2: {intent.putExtra("categoryTitle",cls_14_1_2_t.getText().toString().trim());intent.putExtra("category_id","105");startActivity(intent);}break;
                case R.id.cls_1_3: {intent.putExtra("categoryTitle",cls_14_1_3_t.getText().toString().trim());intent.putExtra("category_id","106");startActivity(intent);}break;
                case R.id.cls_1_4: {intent.putExtra("categoryTitle",cls_14_1_4_t.getText().toString().trim());intent.putExtra("category_id","107");startActivity(intent);}break;
                case R.id.cls_1_5: {intent.putExtra("categoryTitle",cls_14_1_5_t.getText().toString().trim());intent.putExtra("category_id","108");startActivity(intent);}break;
                case R.id.cls_1_6: {intent.putExtra("categoryTitle",cls_14_1_6_t.getText().toString().trim());intent.putExtra("category_id","109");startActivity(intent);}break;
                case R.id.cls_1_7: {intent.putExtra("categoryTitle",cls_14_1_7_t.getText().toString().trim());intent.putExtra("category_id","110");startActivity(intent);}break;
            }
        }
    }
}
