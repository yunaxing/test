package com.qkxmall.mall.define.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 10/13/2015.
 */
public class DefineHistoryAdapter extends PagerAdapter {
    View view;
    List<View> view2;
    List<HashMap<String , Object>> list;
    Context context;

    public DefineHistoryAdapter(View view, List<View> view2, List<HashMap<String, Object>> list, Context context) {
        this.view = view;
        this.view2 = view2;
        this.list = list;
        this.context = context;
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
        if (position == 0) {
            container.removeView(view);
        }else {

            container.removeView(view2.get(position));
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position == 0) {
            container.addView(view);
            //TODO

            return view;
        }else {
            container.addView(view2.get(position));
            //TODO

            return view2.get(position);
        }
    }


    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
