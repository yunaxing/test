package com.qkxmall.mall.define.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunshine on 01/14/2016.
 */
public class PagerAdapter extends android.support.v4.view.PagerAdapter implements ViewPager.OnPageChangeListener{
    List<View> list = new ArrayList<>();
    private Context mContext; // 上下文
    private LayoutInflater mInflater; // 用于解XML
    private ViewPager mViewPager; //页面

    public PagerAdapter(Context context, ViewPager viewPager,List<View> list) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mViewPager = viewPager;
        if (list != null) {
            this.list = list;
        }
    }

    public PagerAdapter(List<View> list)
    {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ( (ViewPager) container).removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager)container).addView(list.get(position));
        return list.get(position);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if ( list.size() > 1) { //多于1，才会循环跳转
            if ( position < 1) { //首位之前，跳转到末尾（N）
                position = list.size(); //注意这里是mList，而不是mViews
                mViewPager.setCurrentItem(position, false);
            } else if ( position > list.size()) { //末位之后，跳转到首位（1）
                mViewPager.setCurrentItem(1, false); //false:不显示跳转过程的动画
                position = 1;
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
