package com.qkxmall.mall.views.func.coucou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.beans.HistoryItem;
import com.qkxmall.mall.beans.ZhangDanItem;

import java.util.ArrayList;

/**
 * Created by yuna on 2017/8/20.
 */

public class ZhangDanMingXiAdapter extends BaseAdapter {

    private ArrayList<ZhangDanItem> itemList;
    private Context context;
    public ZhangDanMingXiAdapter(Context context,  ArrayList<ZhangDanItem> lists) {
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

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder ;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.new_zhang_dan_mingxi_item,null);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvAccount = (TextView) convertView.findViewById(R.id.tv_account);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tv_date);
            viewHolder.imgType = (ImageView) convertView.findViewById(R.id.img_tapy);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder.tvPaixu.setText((i+1)+"");
//        viewHolder.tvCreater.setText(itemList.get(i).getCreater());
//        if(itemList.get(i).getStatus() == 0){
//            viewHolder.tvStatus.setText("未结束");
//            viewHolder.tvOperator.setText("马上参与");
//        }else {
//            viewHolder.tvStatus.setText("结束");
//            viewHolder.tvOperator.setText("查看历史");
//        }


        return convertView;
    }

    static class ViewHolder{
        TextView tvName;
        TextView tvAccount;
        TextView tvDate;
        ImageView imgType;
    }
}
