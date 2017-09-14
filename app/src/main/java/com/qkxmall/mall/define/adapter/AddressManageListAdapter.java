package com.qkxmall.mall.define.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sunshine on 8/28/2015.
 */
public class AddressManageListAdapter extends BaseAdapter {

    public AddressManageListAdapter(List list) {
        this.list = list;
    }

    List list;

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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        return null;
    }

    public final class ViewHolder{
        RadioButton radioButton;
        TextView textViewType;
        TextView textViewName;
        TextView textViewPhone;
        TextView textViewAddress;
    }

}
