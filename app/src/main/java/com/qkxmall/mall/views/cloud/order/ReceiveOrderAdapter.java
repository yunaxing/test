package com.qkxmall.mall.views.cloud.order;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.nets.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 01/17/2016.
 */
public class ReceiveOrderAdapter extends BaseAdapter {

    Context context;
    List<HashMap<String ,Object>> list = new ArrayList<>();

    public ReceiveOrderAdapter(Context context, List<HashMap<String, Object>> list) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        A a;
        if (convertView != null){
            a = (A) convertView.getTag();
        }else {
            a = new A();
            convertView = LayoutInflater.from(context).inflate(R.layout.child_hui_receive,null);
            a.image = (SimpleDraweeView) convertView.findViewById(R.id.image_title);
            a.name = (TextView) convertView.findViewById(R.id.name);
            a.orderSN = (TextView) convertView.findViewById(R.id.orderSN);
            a.kaixindou = (TextView) convertView.findViewById(R.id.kaiXinDouNumber);
            a.price = (TextView) convertView.findViewById(R.id.price);
            a.number = (TextView) convertView.findViewById(R.id.number);
            a.payAmount = (TextView) convertView.findViewById(R.id.payAmount);
            a.deliveryPrice = (TextView) convertView.findViewById(R.id.deliveryPrice);
            a.receive = (Button) convertView.findViewById(R.id.receive);
            convertView.setTag(a);
        }
        final HashMap<String ,Object> data = list.get(position);
        a.orderSN.setText((CharSequence) data.get("order_sn"));
        a.kaixindou.setText((CharSequence) data.get("payable_freight"));
        a.price.setText((CharSequence) data.get("payable_amount"));
        List<HashMap<String ,Object>> goods_info = (List<HashMap<String, Object>>) data.get("_goods_info");
        a.number.setText(goods_info.size() + "");
        for (int i = 0 ; i < goods_info.size() ;i++){
            HashMap<String ,Object> hashMap = goods_info.get(i);
            a.name.setText((CharSequence) hashMap.get("name"));
            String[] image = ((String) hashMap.get("thumb") ).split(",");
            a.image.setImageURI(Uri.parse(API.ADD + image[0]));
        }
        return convertView;
    }

    static class A{
        SimpleDraweeView image;
        TextView name;
        TextView orderSN;
        TextView kaixindou;
        TextView price;
        TextView number;
        TextView payAmount;
        TextView deliveryPrice;
        Button receive;
    }

}
