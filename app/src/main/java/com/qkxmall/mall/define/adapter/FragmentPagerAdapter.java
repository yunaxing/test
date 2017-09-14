package com.qkxmall.mall.define.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunshine on 12/2/2015.
 */
public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    FragmentManager fragmentManager;
    private List<Fragment> fragmentList = new ArrayList<>();

    public FragmentPagerAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentManager = fm;
        this.fragmentList = fragmentList;
    }

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        fragment = fragmentList.get(position);
        return fragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragmentManager.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        Fragment fragment = fragmentList.get(position);
        fragmentManager.beginTransaction().hide(fragment).commit();
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}