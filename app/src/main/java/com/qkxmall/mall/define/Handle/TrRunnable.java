package com.qkxmall.mall.define.Handle;

import android.content.Context;
import android.widget.TextView;

import com.qkxmall.mall.define.views.ListViewPager;

/**
 * Created by Sunshine on 10/9/2015.
 */
public class TrRunnable implements Runnable {
    Context context;
    ListViewPager listViewPager;
    TextView cloud;
    TextView hui;

    public TrRunnable(Context context, ListViewPager listViewPager, TextView cloud, TextView hui) {
        this.context = context;
        this.listViewPager = listViewPager;
        this.cloud = cloud;
        this.hui = hui;
    }

    @Override
    public void run() {
 /*
                * Setup Tr.
                * */

    }
}
