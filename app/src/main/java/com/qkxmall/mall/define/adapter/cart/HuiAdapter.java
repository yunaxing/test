package com.qkxmall.mall.define.adapter.cart;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.hui.container.HuiActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 9/29/2015.
 */
public class HuiAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String , Object>> list;

    public HuiAdapter(Context context, List<HashMap<String, Object>> list) {
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


    //控件创建视图
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
        }else {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_gouwuche_hui_item_layout,null);
            viewHolder.picture = (SimpleDraweeView) convertView.findViewById(R.id.picture);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.goodsID = (TextView) convertView.findViewById(R.id.goodsID);
            viewHolder.productsID = (TextView) convertView.findViewById(R.id.productID);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.dividerLocation = (TextView) convertView.findViewById(R.id.dividerLocation);
            viewHolder.buyNumber = (TextView) convertView.findViewById(R.id.buyNumber);
            viewHolder.select = (CheckBox) convertView.findViewById(R.id.select);
            convertView.setTag(viewHolder);
        }
        //获取病设置数据
        HashMap<String ,Object> dataMap = list.get(position);
       viewHolder.picture.setImageURI(Uri.parse(API.ADD + dataMap.get("picture")));
//        Glide.with(context).load(API.ADD + dataMap.get("picture")).into(viewHolder.picture);
        System.out.println("+++++++++++++++++++++++++++加载图片");
        viewHolder.name.setText((String) dataMap.get("name"));
        viewHolder.goodsID.setText((String) dataMap.get("goodsID"));
        viewHolder.productsID.setText((String) dataMap.get("productsID"));
        viewHolder.price.setText((String) dataMap.get("c_price"));
//        发货地址不显示
//        viewHolder.dividerLocation.setText((String) dataMap.get("dividerLocation"));
        viewHolder.buyNumber.setText((String) dataMap.get("buyNumber"));
        //商品多选
        viewHolder.select.setSelected(false);
        viewHolder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HuiActivity.class);
                intent.putExtra("goodsID", viewHolder.goodsID.getText().toString().trim());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    //控件静态内部类
    static class ViewHolder{
        SimpleDraweeView picture;
        TextView name;
        TextView goodsID;
        TextView productsID;
        TextView price;
        TextView dividerLocation;
        TextView buyNumber;
        CheckBox select;
    }

}
