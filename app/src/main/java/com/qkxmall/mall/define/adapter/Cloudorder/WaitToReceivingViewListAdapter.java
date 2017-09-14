package com.qkxmall.mall.define.adapter.Cloudorder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.qkxmall.mall.views.cloud.CloudBuyProductDetailPageActivity;
import com.qkxmall.mall.views.MyLuckNumberActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 9/21/2015.
 */
public class WaitToReceivingViewListAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String , Object>> list;

    public WaitToReceivingViewListAdapter(Context context, List<HashMap<String, Object>> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.all_cloud_order_wait_to_receiving_list_layout,null);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.all_cloud_order_wait_to_receiving_list_item_picture);
            viewHolder.name = (TextView) convertView.findViewById(R.id.all_cloud_order_wait_to_receiving_list_item_name);
            viewHolder.number = (TextView) convertView.findViewById(R.id.all_cloud_order_wait_to_receiving_list_item_periods);
            viewHolder.joinNumber = (TextView) convertView.findViewById(R.id.all_cloud_order_wait_to_receiving_list_item_join_num_of_people);
            viewHolder.luckNumber = (TextView) convertView.findViewById(R.id.all_cloud_order_wait_to_receiving_list_item_luck_number);
            viewHolder.time = (TextView) convertView.findViewById(R.id.all_cloud_order_wait_to_receiving_list_item_announce_time);
            viewHolder.rec = (Button) convertView.findViewById(R.id.rec);
            viewHolder.divider = (Button) convertView.findViewById(R.id.all_cloud_order_wait_to_receiving_list_item_deliver_stated);
            convertView.setTag(viewHolder);
        }
        HashMap<String , Object> dataMap = list.get(position);
        viewHolder.picture.setImageBitmap((Bitmap) dataMap.get("1"));
        viewHolder.name.setText((String) dataMap.get("2"));
        viewHolder.number.setText((String) dataMap.get("3"));
        viewHolder.joinNumber.setText((String) dataMap.get("4"));
        viewHolder.luckNumber.setText((String) dataMap.get("5"));
        viewHolder.time.setText((String) dataMap.get("6"));
        viewHolder.divider.setText((String) dataMap.get("7"));
        if (viewHolder.divider.getText().toString().trim().equals("物流信息")) {
            viewHolder.divider.setBackgroundResource(R.color.cloud_buy_text_color_red);
            viewHolder.divider.setOnClickListener(new OnClickListeners(position));
        }else {
            viewHolder.divider.setBackgroundResource(R.color.material_grey_600);
            viewHolder.divider.setOnClickListener(new ToastOnClickListeners(position));
            viewHolder.rec.setVisibility(View.INVISIBLE);
        }
        viewHolder.rec.setOnClickListener(new OnClickListeners(position));
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
        Button divider;
        Button rec;
    }

    private class OnClickListeners implements View.OnClickListener {
        int position;

        public OnClickListeners(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            // TODO: 9/21/2015
            switch (v.getId()) {
                case R.id.rec:
                Dialog dialog = new AlertDialog.Builder(context).setTitle("提示").setMessage("确认收货").setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setNeutralButton("取消", null).create();
                dialog.show();
                    break;
                case R.id.all_cloud_order_wait_to_receiving_list_item_deliver_stated:
                    Dialog dialog1 = new AlertDialog.Builder(context).setTitle("物流信息").setMessage("顺丰"+"\n"+"944924429294").setNegativeButton("",null).create();
                    dialog1.show();
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
            switch (v.getId()) {
                case R.id.rec:   Toast.makeText(context, "该订单已收货!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
