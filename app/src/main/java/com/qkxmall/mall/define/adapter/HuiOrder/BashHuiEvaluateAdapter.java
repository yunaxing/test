package com.qkxmall.mall.define.adapter.HuiOrder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.views.EvaluateActivity;
import com.qkxmall.mall.views.sale.AfterSaleActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 10/12/2015.
 */
public class BashHuiEvaluateAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String , Object>> list;

    public BashHuiEvaluateAdapter(Context context, List<HashMap<String, Object>> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.all_hui_order_to_evaluate_list_view_layout,null);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.picture);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.serial = (TextView) convertView.findViewById(R.id.serial);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.orderNumber = (TextView) convertView.findViewById(R.id.serial);
            viewHolder.bean = (TextView) convertView.findViewById(R.id.bean);
            viewHolder.all = (TextView) convertView.findViewById(R.id.all);
            viewHolder.pay = (TextView) convertView.findViewById(R.id.pay);
            viewHolder.divider = (TextView) convertView.findViewById(R.id.divider);
            viewHolder.evaluate = (Button) convertView.findViewById(R.id.evaluate);
            viewHolder.after = (Button) convertView.findViewById(R.id.after);
            convertView.setTag(viewHolder);
        }
        viewHolder.evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EvaluateActivity.class));
            }
        });
        viewHolder.after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AfterSaleActivity.class));
            }
        });


        return convertView;
    }

    static class ViewHolder{
        ImageView picture;
        TextView name,serial,price,orderNumber,bean,all,pay,divider;
        Button evaluate,after;
    }

}
