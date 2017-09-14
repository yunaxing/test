package com.qkxmall.mall.define.adapter;

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
import com.qkxmall.mall.views.hui.container.HuiActivity;
import com.qkxmall.mall.views.sale.AfterSaleActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 9/1/2015.
 */
public class BaseSimpleAdapterAllHuiOrderWaitToReceivingAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String ,Object>> list = new ArrayList<>();

    public BaseSimpleAdapterAllHuiOrderWaitToReceivingAdapter(Context context, List<HashMap<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
        }else {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.all_hui_order_wait_to_receiving_list_view_layout,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_picture);
            viewHolder.name = (TextView) convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_name);
            viewHolder.serialNumber = (TextView) convertView.findViewById(R.id.all_hui_order_wait_to_receiving_list_item_serial_number);
            viewHolder.price = (TextView) convertView.findViewById(R.id.all_hui_order_wait_to_receiving_list_item_price);
            viewHolder.beUsedHappyBean = (TextView) convertView.findViewById(R.id.all_hui_order_wait_to_receiving_list_item_be_used_happy_bean);
            viewHolder.number = (TextView) convertView.findViewById(R.id.all_hui_order_wait_to_receiving_list_item_buy_number);
            viewHolder.payed = (TextView) convertView.findViewById(R.id.all_hui_order_wait_to_receiving_list_item_payed);
            viewHolder.transportPrice = (TextView) convertView.findViewById(R.id.all_hui_order_wait_to_receiving_list_item_transport_price);
            viewHolder.receiving = (Button) convertView.findViewById(R.id.all_hui_order_wait_to_receiving_list_item_receiving);
            viewHolder.afterSale = (Button) convertView.findViewById(R.id.all_hui_order_wait_to_receiving_list_item_after_sale);

        /*
            * Setup view data
            * image
            * text
            * button text ,click event
            * */
            HashMap<String ,Object> dataMap = list.get(position);
            viewHolder.imageView.setImageBitmap((Bitmap) dataMap.get("0"));

            viewHolder.name.setText((String) dataMap.get("1"));
            viewHolder.serialNumber.setText((String) dataMap.get("2"));
            viewHolder.price.setText((String) dataMap.get("3"));
            viewHolder.beUsedHappyBean.setText((String) dataMap.get("5"));
            viewHolder.number.setText((String) dataMap.get("6"));
            viewHolder.payed.setText((String) dataMap.get("7"));
            viewHolder.transportPrice.setText((String) dataMap.get("8"));
            //button
            if ((Boolean)dataMap.get("9") == true){
                viewHolder.receiving.setText("已确认收货");
                viewHolder.receiving.setBackgroundColor(context.getResources().getColor(R.color.grid_view_background_grey_500));
                viewHolder.receiving.setClickable(false);
            }else if ((Boolean)dataMap.get("9") == false){
                viewHolder.receiving.setText("确认收货");
                viewHolder.receiving.setBackgroundColor(context.getResources().getColor(R.color.hui_buy_title_bg_color_red));
            }
            viewHolder.afterSale.setText((String) dataMap.get("10"));

            //setup click event.
            viewHolder.receiving.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewHolder.receiving.isClickable()) {
                        Dialog dialog = new AlertDialog.Builder(context).setTitle("确认收货").setMessage("确认!").setNegativeButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewHolder.receiving.setText("已确认收货");
                                viewHolder.receiving.setBackgroundColor(context.getResources().getColor(R.color.grid_view_background_grey_500));
                                viewHolder.receiving.setClickable(false);
                                Toast.makeText(context,"确认收货!", Toast.LENGTH_SHORT).show();
                            }
                        }).setNeutralButton("取消",null).create();
                        dialog.show();
                    }else if (!viewHolder.receiving.getText().toString().trim().equals("已确认收货") && !viewHolder.receiving.isClickable() ){
                        Toast.makeText(context,  "已经确认收货了!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            viewHolder.afterSale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AfterSaleActivity.class);
                    Toast.makeText(context, position + "提交售后申请!", Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, HuiActivity.class));
                }
            });



            convertView.setTag(viewHolder);
        }

        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView name;
        TextView serialNumber;
        TextView price;
        TextView beUsedHappyBean;
        TextView number;
        TextView payed;
        TextView transportPrice;
        Button receiving;
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
                case R.id.all_hui_order_wait_to_receiving_list_item_receiving:

                    break;
                case R.id.all_hui_order_wait_to_receiving_list_item_after_sale:

                    break;
            }
        }
    }
}
