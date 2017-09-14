package com.qkxmall.mall.views.hui.order;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
public class AllOrderAdapter extends BaseAdapter {

    static final int TYPE_PAY = 0;
    static final int TYPE_DELIVERY = 1;
    static final int TYPE_FINISH = 2;

    Context context;
    List<HashMap<String ,Object>> list = new ArrayList<>();

    public AllOrderAdapter(Context context, List<HashMap<String, Object>> list) {
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

//    @Override
//    public int getItemViewType(int position) {
//        HashMap<String ,Object> map = list.get(position);
//        if (map.get("order_status").equals("0")&&
//                map.get("pay_status").equals("0")&&
//                map.get("delivery_status").equals("0")){
//            return TYPE_PAY;
//        }else if (map.get("order_status").equals("1")&&
//                map.get("pay_status").equals("0")&&
//                map.get("delivery_status").equals("0")){
//            return TYPE_DELIVERY;
//        }else {
//            return TYPE_FINISH;
//        }
//    }

//    @Override
//    public int getViewTypeCount() {
//        return 3;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)){
            case TYPE_PAY:{
//                System.out.println("GetView  1");
                A a;
                if (convertView != null){
                    a = (A) convertView.getTag();
                }else {
                    a = new A();
                    convertView = LayoutInflater.from(context).inflate(R.layout.all_hui_order_wait_to_pay_list_view_layout,null);
                    a.name = (TextView) convertView.findViewById(R.id.name);
                    a.image = (SimpleDraweeView) convertView.findViewById(R.id.image_title);
                    a.price = (TextView) convertView.findViewById(R.id.price);
                    a.number = (TextView) convertView.findViewById(R.id.number);
                    a.transportFee = (TextView) convertView.findViewById(R.id.transport);
                    a.kaiXinDouNumber = (TextView) convertView.findViewById(R.id.kaiXinDouNumber);
                    a.pay = (Button) convertView.findViewById(R.id.pay);
                    convertView.setTag(a);
                }
                HashMap<String ,Object> data = list.get(position);
                List<HashMap<String,Object>> goods_list = (List<HashMap<String, Object>>) data.get("_goods_info");
                int kaixindoNum = 0;
                for (int i = 0 ; i< goods_list.size() ;i++){
                    if (i == 0){
                        HashMap<String ,Object> good = goods_list.get(i);
                        a.name.setText((CharSequence) good.get("name"));
                        String[] images = ((String)good.get("thumb")).split(",");
                        a.image.setImageURI(Uri.parse(API.ADD+images[0]));
                        kaixindoNum +=Integer.parseInt((String) good.get("kaixindou"));
                    }
                }
                a.price.setText((CharSequence) data.get("real_amount"));
                a.transportFee.setText("0");
                a.kaiXinDouNumber.setText(String.valueOf(kaixindoNum));
                a.pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                return convertView;
            }
            case TYPE_DELIVERY:{
//                System.out.println("GetView  2");
                A a;
                if (convertView != null){
                    a = (A) convertView.getTag();
                }else {
                    a = new A();
                    convertView = LayoutInflater.from(context).inflate(R.layout.all_hui_order_wait_to_receiving_list_view_layout,null);

                    convertView.setTag(a);
                }
                HashMap<String ,Object> data = list.get(position);
                return convertView;
            }
            case TYPE_FINISH:{
//                System.out.println("GetView  3");
                A a;
                if (convertView != null){
                    a = (A) convertView.getTag();
                }else {
                    a = new A();
                    convertView = LayoutInflater.from(context).inflate(R.layout.all_hui_order_wait_to_receiving_list_view_layout,null);
                    convertView.setTag(a);
//                    a.image=(SimpleDraweeView)convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_picture);
                    a.mImageView=(ImageView)convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_picture);//图片
                    a.name=(TextView)convertView.findViewById(R.id.all_cloud_order_evaluate_list_item_name);//产品名
                    a.price=(TextView)convertView.findViewById(R.id.all_hui_order_wait_to_receiving_list_item_price);//价钱
                    a.number=(TextView)convertView.findViewById(R.id.all_hui_order_wait_to_receiving_list_item_buy_number);//数量
                    a.kaiXinDouNumber=(TextView)convertView.findViewById(R.id.all_hui_order_wait_to_receiving_list_item_be_used_happy_bean);//开心豆数量
                    a.transportFee=(TextView)convertView.findViewById(R.id.all_hui_order_wait_to_receiving_list_item_transport_price);//运输费

                }
                HashMap<String ,Object> data = list.get(position);
                List<HashMap<String,Object>> goods_list = (List<HashMap<String, Object>>) data.get("_goods_info");
                int kaixindoNum = 0;
                for (int i = 0 ; i< goods_list.size() ;i++){
                    if (i == 0){
                        HashMap<String ,Object> good = goods_list.get(i);
                        a.name.setText((CharSequence) good.get("name"));
                        String[] images = ((String)good.get("thumb")).split(",");
                        a.mImageView.setImageURI(Uri.parse(API.ADD+images[0]));

                        kaixindoNum +=Integer.parseInt((String) good.get("kaixindou"));
                    }
                }
                a.price.setText((CharSequence) data.get("real_amount"));
                a.transportFee.setText("0");
                a.kaiXinDouNumber.setText(String.valueOf(kaixindoNum));
                return convertView;
            }
            default:{
//                System.out.println("GetView  4");
                A a;
                if (convertView != null){
                    a = (A) convertView.getTag();
                }else {
                    a = new A();
                    convertView = LayoutInflater.from(context).inflate(R.layout.all_hui_order_wait_to_receiving_list_view_layout,null);
                    convertView.setTag(a);
                }
                HashMap<String ,Object> data = list.get(position);
                return convertView;
            }
        }
    }

    static class A{
        SimpleDraweeView image;
        ImageView mImageView;
        TextView name;
        TextView price;
        TextView number;
        TextView transportFee;
        TextView kaiXinDouNumber;
        Button pay;
    }


}
