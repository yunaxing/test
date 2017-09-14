package com.qkxmall.mall.define.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 2015/9/9.
 */
public class BaseTempPagerAdapter extends BaseAdapter {

    List<HashMap<String ,Object>> list;
    String[] from;
    Context context;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return convertView;
    }

    static class ViewHolder{

    }
}
