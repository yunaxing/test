package com.qkxmall.mall.define.adapter.Cloudorder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.views.cloud.CloudBuyProductDetailPageActivity;
import com.qkxmall.mall.views.MyLuckNumberActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 9/21/2015.
 */
public class PistachioNutsListAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String , Object>> list;

    public PistachioNutsListAdapter(Context context, List<HashMap<String, Object>> list) {
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

    ViewHolder viewHolder;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
        }else {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.all_cloud_order_pistachio_nuts_list_layout,null);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.all_cloud_order_pistachio_nuts_list_item_picture);
            viewHolder.name = (TextView) convertView.findViewById(R.id.all_cloud_order_pistachio_nuts_list_item_name);
            viewHolder.number = (TextView) convertView.findViewById(R.id.all_cloud_order_pistachio_nuts_list_item_periods);
            viewHolder.joinNumber = (TextView) convertView.findViewById(R.id.all_cloud_order_pistachio_nuts_list_item_join_num_of_people);
            viewHolder.luckNumber = (TextView) convertView.findViewById(R.id.all_cloud_order_pistachio_nuts_list_item_luck_number);
            viewHolder.time = (TextView) convertView.findViewById(R.id.all_cloud_order_pistachio_nuts_list_item_announce_time);
            convertView.setTag(viewHolder);
        }
        HashMap<String , Object> dataMap = list.get(position);
        viewHolder.picture.setImageBitmap((Bitmap) dataMap.get("1"));
        viewHolder.name.setText((String) dataMap.get("2"));
        viewHolder.number.setText((String) dataMap.get("3"));
        viewHolder.joinNumber.setText((String) dataMap.get("4"));
        viewHolder.luckNumber.setText((String) dataMap.get("5"));
        viewHolder.time.setText((String) dataMap.get("6"));
        viewHolder.luckNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MyLuckNumberActivity.class));
            }
        });
        viewHolder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CloudBuyProductDetailPageActivity.class));
            }
        });
        return convertView;
    }

    public  static class ViewHolder{
        ImageView picture;
        TextView name;
        TextView number;
        TextView joinNumber;
        TextView luckNumber;
        TextView time;
    }

}
