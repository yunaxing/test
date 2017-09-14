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
 * Created by Sunshine on 9/24/2015.
 */
public class CloudBuyAllOrder2 extends BaseAdapter {
    public CloudBuyAllOrder2(Context context, List<HashMap<String, Object>> list1, List<HashMap<String, Object>> list2) {
        this.context = context;
        this.list1 = list1;
        this.list2 = list2;
    }

    Context context;
    List<HashMap<String , Object>> list1;
    List<HashMap<String , Object>> list2;
    List list ;

    @Override
    public int getCount() {
        return list1.size()+list2.size();
    }

    @Override
    public Object getItem(int position) {
        return list1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    A a;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null){
            a = (A) convertView.getTag();
        }else {
            a = new A();
            convertView = LayoutInflater.from(context).inflate(R.layout.all_cloud_order_all_order_list_layout_be_announced,null);
            a.picture = (ImageView) convertView.findViewById(R.id.picture);
            a.name = (TextView) convertView.findViewById(R.id.name);
            a.periods = (TextView) convertView.findViewById(R.id.periods);
            a.luck = (TextView) convertView.findViewById(R.id.luck);
            a.time = (TextView) convertView.findViewById(R.id.time);
            a.luckNumber = (TextView) convertView.findViewById(R.id.luck_number);
            convertView.setTag(a);
            HashMap<String ,Object> dataMap1 = list1.get(position);
            a.picture.setImageBitmap((Bitmap) dataMap1.get("1"));
            a.name.setText((String) dataMap1.get("2"));
            a.periods.setText((String) dataMap1.get("3"));
            a.luck.setText((String) dataMap1.get("5"));
            a.time.setText((String) dataMap1.get("6"));
            convertView.setTag(a);
        }
        HashMap<String ,Object> dataMap2 = list2.get(position);
        a.picture.setImageBitmap((Bitmap) dataMap2.get("1"));
        a.name.setText((String) dataMap2.get("2"));
        a.periods.setText((String) dataMap2.get("3"));
        a.luck.setText((String) dataMap2.get("5"));
        a.time.setText((String) dataMap2.get("6"));
        a.luckNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MyLuckNumberActivity.class));
            }
        });
        a.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CloudBuyProductDetailPageActivity.class));
            }
        });
        return convertView;
    }

    static class A {
        ImageView picture;
        TextView name;
        TextView periods;
        TextView luck;
        TextView time;
        TextView luckNumber;
    }

}
