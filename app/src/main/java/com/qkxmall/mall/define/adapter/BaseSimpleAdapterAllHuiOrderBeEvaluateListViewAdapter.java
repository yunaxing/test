package com.qkxmall.mall.define.adapter;

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
import com.qkxmall.mall.views.hui.container.HuiActivity;
import com.qkxmall.mall.views.sale.AfterSaleActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 8/31/2015.
 */
public class BaseSimpleAdapterAllHuiOrderBeEvaluateListViewAdapter extends BaseAdapter {
    List<HashMap<String ,Object>> list = new ArrayList<>();
    Context context;

    public BaseSimpleAdapterAllHuiOrderBeEvaluateListViewAdapter(List<HashMap<String, Object>> list, Context context) {
        this.list = list;
        this.context = context;
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
        ViewHolder viewHolder;
        if (convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
        }else {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.all_hui_order_all_order_be_evaluate_list_view_layout,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_picture);
            viewHolder.name = (TextView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_name);
            viewHolder.serialNumber = (TextView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_serial_number);
            viewHolder.price = (TextView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_price);
            viewHolder.usedHappyBean = (TextView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_be_used_happy_bean);
            viewHolder.buyNumber = (TextView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_buy_number);
            viewHolder.bePayed = (TextView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_be_payed);
            viewHolder.dividerPrice = (TextView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_divider_price);
            viewHolder.evaluate = (Button) convertView.findViewById(R.id.all_hui_order_all_order_be_evaluate_list_item_evaluate);
            viewHolder.afterSale = (Button) convertView.findViewById(R.id.all_hui_order_all_order_be_evaluate_list_item_after_sale);
            //Set button click event.
            viewHolder.evaluate.setOnClickListener(new OnClickListeners(position));
            viewHolder.afterSale.setOnClickListener(new OnClickListeners(position));
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, HuiActivity.class));
                }
            });
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView name ;
        TextView serialNumber;
        TextView price;
        TextView usedHappyBean;
        TextView buyNumber;
        TextView bePayed;
        TextView dividerPrice;
        Button evaluate;
        Button afterSale;
    }

    private class OnClickListeners implements View.OnClickListener {
        int position;
        public OnClickListeners(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.all_hui_order_all_order_be_evaluate_list_item_evaluate:
                    Intent intent = new Intent(context, EvaluateActivity.class);

                    context.startActivity(intent);
                    break;

                case R.id.all_hui_order_all_order_be_evaluate_list_item_after_sale:
                    Intent intent1 = new Intent(context, AfterSaleActivity.class);

                    context.startActivity(intent1);
                    break;
            }

        }
    }
}
