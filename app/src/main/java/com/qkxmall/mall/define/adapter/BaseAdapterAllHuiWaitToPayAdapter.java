package com.qkxmall.mall.define.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.views.hui.HuiBuyPayActivity;
import com.qkxmall.mall.views.hui.container.HuiActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 9/2/2015.
 */
public class BaseAdapterAllHuiWaitToPayAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String , Object>> list;

    public BaseAdapterAllHuiWaitToPayAdapter(Context context, List<HashMap<String, Object>> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.all_hui_order_wait_to_pay_list_view_layout,null);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image_title);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.beUseHappyBean = (TextView) convertView.findViewById(R.id.kaiXinDouNumber);
            viewHolder.number = (TextView) convertView.findViewById(R.id.number);
            viewHolder.transport = (TextView) convertView.findViewById(R.id.transport);
            viewHolder.pay = (Button) convertView.findViewById(R.id.pay);
            convertView.setTag(viewHolder);
        }
        HashMap<String ,Object> dataMap = list.get(position);
        viewHolder.image.setImageBitmap((Bitmap) dataMap.get("0"));
//        Glide.with(context).load( dataMap.get("0")).into(viewHolder.image);
        viewHolder.name.setText((String) dataMap.get("1"));
        viewHolder.price.setText((String) dataMap.get("3"));
        viewHolder.beUseHappyBean.setText((String) dataMap.get("5"));
        viewHolder.number.setText((String) dataMap.get("6"));
        viewHolder.transport.setText((String) dataMap.get("8"));
        viewHolder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPay = new Intent(context, HuiBuyPayActivity.class);
                context.startActivity(intentPay);
            }
        });
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, HuiActivity.class));
            }
        });


        return convertView;
    }

    static class ViewHolder{
        ImageView image;
        TextView name;
        TextView price;
        TextView beUseHappyBean;
        TextView number;
        TextView transport;
        Button pay;
    }

}
