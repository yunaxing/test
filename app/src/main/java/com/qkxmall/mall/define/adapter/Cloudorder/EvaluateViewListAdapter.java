package com.qkxmall.mall.define.adapter.Cloudorder;

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
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.views.EvaluateActivity;
import com.qkxmall.mall.views.cloud.CloudBuyProductDetailPageActivity;
import com.qkxmall.mall.views.MyLuckNumberActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 9/21/2015.
 */
public class EvaluateViewListAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String , Object>> list;

    public EvaluateViewListAdapter(Context context, List<HashMap<String, Object>> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.all_cloud_order_evaluate_list_layout,null);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_picture);
            viewHolder.name = (TextView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_name);
            viewHolder.number = (TextView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_periods);
            viewHolder.joinNumber = (TextView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_join_num_of_people);
            viewHolder.luckNumber = (TextView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_luck_number);
            viewHolder.time = (TextView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_announce_time);
            viewHolder.evaluate = (Button) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_evaluate_stated);
            convertView.setTag(viewHolder);
        }
        HashMap<String , Object> dataMap = list.get(position);
        viewHolder.picture.setImageBitmap((Bitmap) dataMap.get("1"));
        viewHolder.name.setText((String) dataMap.get("2"));
        viewHolder.number.setText((String) dataMap.get("3"));
        viewHolder.joinNumber.setText((String) dataMap.get("4"));
        viewHolder.luckNumber.setText((String) dataMap.get("5"));
        viewHolder.time.setText((String) dataMap.get("6"));
        viewHolder.evaluate.setText((String) dataMap.get("7"));
        if (viewHolder.evaluate.getText().toString().trim().equals("评价")) {
            viewHolder.evaluate.setBackgroundResource(R.color.cloud_buy_text_color_red);
            viewHolder.evaluate.setOnClickListener(new OnClickListeners(position));
        }else {
            viewHolder.evaluate.setBackgroundResource(R.color.material_grey_600);
            viewHolder.evaluate.setOnClickListener(new ToastOnClickListeners(position));
        }
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
        Button evaluate;
    }

    private class OnClickListeners implements View.OnClickListener {
        int position;

        public OnClickListeners(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.all_cloud_order_evaluate_list_item_evaluate_stated:
                    context.startActivity(new Intent(context, EvaluateActivity.class));
                    break;
            }
        }
    }

    private class ToastOnClickListeners implements View.OnClickListener {
        int position;
        public ToastOnClickListeners(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context,"该订单已评价!",Toast.LENGTH_SHORT).show();
        }
    }
}
