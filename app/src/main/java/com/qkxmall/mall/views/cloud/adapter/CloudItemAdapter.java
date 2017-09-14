package com.qkxmall.mall.views.cloud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qkxmall.mall.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 2015/12/26.
 */
public class CloudItemAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String ,Object>> list = new ArrayList<>();

    public CloudItemAdapter(Context context, List<HashMap<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

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
        A a;
        if (convertView != null){
            a = (A) convertView.getTag();
        }else {
            a = new A();
            convertView = LayoutInflater.from(context).inflate(R.layout.cloud_buy_grid_view_item_2_layout ,null);
            convertView.setTag(a);
        }

        return convertView;
    }

    static class A{

    }

}
