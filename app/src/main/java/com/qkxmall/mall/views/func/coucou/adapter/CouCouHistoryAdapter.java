package com.qkxmall.mall.views.func.coucou.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.beans.HistoryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yuna on 2017/8/7.
 */

public class CouCouHistoryAdapter extends BaseAdapter {

    private ArrayList<HistoryItem> itemList;
    private Context context;
    public CouCouHistoryAdapter(Context context,  ArrayList<HistoryItem> lists) {
        this.context = context;
        itemList = lists;

    }
    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder ;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.new_coucou_history_item,null);
            viewHolder.tvPaixu = (TextView) convertView.findViewById(R.id.tv_paixu);
            viewHolder.tvStatus = (TextView) convertView.findViewById(R.id.tv_status);
            viewHolder.tvOperator = (TextView) convertView.findViewById(R.id.tv_operation);
            viewHolder.tvCreater = (TextView) convertView.findViewById(R.id.tv_person);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvPaixu.setText((i+1)+"");
        viewHolder.tvCreater.setText(itemList.get(i).getCreater());
        if(itemList.get(i).getStatus() == 0){
//            viewHolder.tvStatus.setText("未结束");
            viewHolder.tvStatus.setBackground(context.getResources().getDrawable(R.mipmap.coucou_history_status_ongoing));
            viewHolder.tvOperator.setBackground(context.getResources().getDrawable(R.mipmap.coucou_history_operation_join));
        }else {
            viewHolder.tvStatus.setBackground(context.getResources().getDrawable(R.mipmap.coucou_history_status_finished));
            viewHolder.tvOperator.setBackground(context.getResources().getDrawable(R.mipmap.coucou_history_operation_view));
        }


        return convertView;
    }

    static class ViewHolder{
        TextView tvPaixu;
        TextView tvStatus;
        TextView tvOperator;
        TextView tvCreater;
    }
}
