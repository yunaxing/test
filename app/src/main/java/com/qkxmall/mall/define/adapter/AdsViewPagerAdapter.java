package com.qkxmall.mall.define.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Sunshine on 8/21/2015.
 */
public class AdsViewPagerAdapter extends PagerAdapter {

    private List<View> list;

    public AdsViewPagerAdapter(List<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return  list.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)                       //销毁Item
    {
        ((ViewPager) container).removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
        container.addView(list.get(position), 0);//添加页卡
        return list.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

}
