package com.qkxmall.mall.views.cart;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.adapter.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunshine on 10/9/2015.
 */
public class CartFragment extends Fragment {
    public static final int HUI = 0x0000;
    public static final int CLOUD = 0x0001;
    private static final boolean debug = true;
    private ViewPager cartPager;
    private Context context;
    FragmentPagerAdapter fragmentPagerAdapter;

    private TextView cloud = null;
    private TextView hui = null;

    public void init(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        init(rootView);
        List<Fragment> fragmentList = new ArrayList<>();
//        fragmentList.add(CloudPageFragment.newInstance(context));
//        fragmentList.add(HuiPageFragment.newInstance(context));
        fragmentList.add(HuiPageFragment.newInstance(context));
        fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager(), fragmentList);
        cartPager.setAdapter(fragmentPagerAdapter);

        hui.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
        cloud.setBackgroundColor(getResources().getColor(R.color.white));
        hui.setTextColor(getResources().getColor(R.color.white));
        cloud.setTextColor(getResources().getColor(R.color.text_black));
        cartPager.setCurrentItem(0);
        cartPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        hui.setBackgroundColor(getResources().getColor(R.color.white));
                        cloud.setBackgroundColor(getResources().getColor(R.color.main_line_blue_color));
                        hui.setTextColor(getResources().getColor(R.color.text_black));
                        cloud.setTextColor(getResources().getColor(R.color.white));
                    }
                    break;
                    case 1: {
                        hui.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
                        cloud.setBackgroundColor(getResources().getColor(R.color.white));
                        hui.setTextColor(getResources().getColor(R.color.white));
                        cloud.setTextColor(getResources().getColor(R.color.text_black));
                    }
                    break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        cloud.setOnClickListener(new OnClick());
        hui.setOnClickListener(new OnClick());
        return rootView;
    }

    public String addressid;


    private void init(View rootView) {
        cloud = (TextView) rootView.findViewById(R.id.cloud);
        hui = (TextView) rootView.findViewById(R.id.hui);
        cartPager = (ViewPager) rootView.findViewById(R.id.cartPager);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.hui: {
                    hui.setBackgroundColor(getResources().getColor(R.color.main_line_pink_color));
                    cloud.setBackgroundColor(getResources().getColor(R.color.white));
                    hui.setTextColor(getResources().getColor(R.color.white));
                    cloud.setTextColor(getResources().getColor(R.color.text_black));
                    cartPager.setCurrentItem(1);
                }
                break;
                case R.id.cloud: {
                    hui.setBackgroundColor(getResources().getColor(R.color.white));
                    cloud.setBackgroundColor(getResources().getColor(R.color.main_line_blue_color));
                    hui.setTextColor(getResources().getColor(R.color.text_black));
                    cloud.setTextColor(getResources().getColor(R.color.white));
                    cartPager.setCurrentItem(0);
                }
                break;
            }
        }
    }


}
