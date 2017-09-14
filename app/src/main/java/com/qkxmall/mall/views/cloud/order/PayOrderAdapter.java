package com.qkxmall.mall.views.cloud.order;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.alipay.sdk.pay.paypage.ALiPayPayActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.nets.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 01/17/2016.
 */
public class PayOrderAdapter extends BaseAdapter {

    Context context;
    List<HashMap<String ,Object>> list = new ArrayList<>();

    public PayOrderAdapter(Context context, List<HashMap<String, Object>> list) {
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
        final A a;
        if (convertView != null){
            a = (A) convertView.getTag();
        }else {
            a = new A();
            convertView = LayoutInflater.from(context).inflate(R.layout.all_hui_order_wait_to_pay_list_view_layout,null);
            a.image = (SimpleDraweeView) convertView.findViewById(R.id.image_title);
            a.name = (TextView) convertView.findViewById(R.id.name);
            a.price = (TextView) convertView.findViewById(R.id.price);
            a.total = (TextView) convertView.findViewById(R.id.total);
            a.kaixindou = (TextView) convertView.findViewById(R.id.kaiXinDouNumber);
            a.number = (TextView) convertView.findViewById(R.id.number);
            a.pay = (Button) convertView.findViewById(R.id.pay);
            convertView.setTag(a);
        }
        final HashMap<String ,Object> data = list.get(position);
        System.out.println("！！！！！！！！！！ 所有信息： " + data.toString());
        List<HashMap<String ,Object>> goodsList = (List<HashMap<String, Object>>) data.get("goods_info");
        for (int i = 0 ; i< goodsList.size() ; i++){
            if (i == 0 ) {
                HashMap<String, Object> map = goodsList.get(i);
                String [] img = ((String)map.get("thumb")).split(",");
                a.image.setImageURI(Uri.parse(API.ADD + img[0]));
                a.name.setText((CharSequence) map.get("name"));
            }

        }
        a.price.setText((CharSequence) data.get("real_amount"));
        a.number.setText(goodsList.size()+"");
        a.kaixindou.setText((CharSequence) data.get("payable_freight"));
        a.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ALiPayPayActivity.class);
                intent.putExtra("c_price", (String) data.get("real_amount"));
                intent.putExtra("name",a.name.getText().toString().trim());
                intent.putExtra("detail",a.name.getText().toString().trim());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class A{
        SimpleDraweeView image;
        TextView name;
        TextView price;
        TextView total;
        TextView kaixindou;
        TextView number;
        Button pay;
    }

}
