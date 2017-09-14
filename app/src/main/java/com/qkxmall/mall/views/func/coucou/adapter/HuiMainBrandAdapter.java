package com.qkxmall.mall.views.func.coucou.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qkxmall.mall.R;
import com.qkxmall.mall.beans.HistoryItem;
import com.qkxmall.mall.beans.MainTuiJianBrandItem;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.hui.HuiClassItemsActivity;
import com.qkxmall.mall.views.hui.container.HuiActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yuna on 2017/9/7.
 */

public class HuiMainBrandAdapter extends BaseAdapter {

    private ArrayList<MainTuiJianBrandItem> itemList;
    private Context context;
    public HuiMainBrandAdapter(Context context,  ArrayList<MainTuiJianBrandItem> lists) {
        this.context = context;
        itemList = lists;

    }
    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder ;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.new_item_jingchang_tuijian,null);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.price);
            viewHolder.tvPrice1 = (TextView) convertView.findViewById(R.id.price1);
            viewHolder.tvPrice4 = (TextView) convertView.findViewById(R.id.price4);
            viewHolder.tvPrice3 = (TextView) convertView.findViewById(R.id.price3);
            viewHolder.tvPrice5 = (TextView) convertView.findViewById(R.id.price4);
            viewHolder.tvPrice6 = (TextView) convertView.findViewById(R.id.price5);
            viewHolder.tvYiShou = (TextView) convertView.findViewById(R.id.saled);
            viewHolder.tvYiShou1 = (TextView) convertView.findViewById(R.id.saled1);
            viewHolder.tvYiShou4 = (TextView) convertView.findViewById(R.id.saled3);
            viewHolder.tvYiShou3 = (TextView) convertView.findViewById(R.id.saled4);
            viewHolder.tvYiShou5 = (TextView) convertView.findViewById(R.id.saled5);
            viewHolder.tvYiShou6 = (TextView) convertView.findViewById(R.id.saled6);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.img1 = (ImageView) convertView.findViewById(R.id.image1);
            viewHolder.img3 = (ImageView) convertView.findViewById(R.id.image3);
            viewHolder.img4 = (ImageView) convertView.findViewById(R.id.image4);
            viewHolder.img5 = (ImageView) convertView.findViewById(R.id.image5);
            viewHolder.img6 = (ImageView) convertView.findViewById(R.id.image6);
            viewHolder.imgTitle = (ImageView)convertView.findViewById(R.id.image_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvPrice.setText(itemList.get(i).getGoodListItems().get(0).getPrice());
        viewHolder.tvPrice1.setText(itemList.get(i).getGoodListItems().get(1).getPrice());
        viewHolder.tvPrice3.setText(itemList.get(i).getGoodListItems().get(2).getPrice());
        viewHolder.tvPrice4.setText(itemList.get(i).getGoodListItems().get(3).getPrice());
        viewHolder.tvPrice5.setText(itemList.get(i).getGoodListItems().get(4).getPrice());
        viewHolder.tvPrice6.setText(itemList.get(i).getGoodListItems().get(5).getPrice());

        viewHolder.tvYiShou.setText(itemList.get(i).getGoodListItems().get(0).getSalednum());
        viewHolder.tvYiShou1.setText(itemList.get(i).getGoodListItems().get(1).getSalednum());
        viewHolder.tvYiShou3.setText(itemList.get(i).getGoodListItems().get(2).getSalednum());
        viewHolder.tvYiShou4.setText(itemList.get(i).getGoodListItems().get(3).getSalednum());
        viewHolder.tvYiShou5.setText(itemList.get(i).getGoodListItems().get(4).getSalednum());
        viewHolder.tvYiShou6.setText(itemList.get(i).getGoodListItems().get(5).getSalednum());
        viewHolder.imgTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HuiClassItemsActivity.class);

                intent.putExtra("brand_id", itemList.get(i).getId());
                intent.putExtra("categoryTitle", itemList.get(i).getName());
                context.startActivity(intent);
            }
        });

        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HuiActivity.class);
                intent.putExtra("gid",  itemList.get(i).getGoodListItems().get(0).getId());

                context.startActivity(intent);
            }
        });

        viewHolder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HuiActivity.class);
                intent.putExtra("gid",  itemList.get(i).getGoodListItems().get(1).getId());

                context.startActivity(intent);
            }
        });

        viewHolder.img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HuiActivity.class);
                intent.putExtra("gid",   itemList.get(i).getGoodListItems().get(2).getId());

                context.startActivity(intent);
            }
        });

        viewHolder.img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HuiActivity.class);
                intent.putExtra("gid",  itemList.get(i).getGoodListItems().get(3).getId());

                context.startActivity(intent);
            }
        });

        viewHolder.img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, HuiActivity.class);
                intent.putExtra("gid",  itemList.get(i).getGoodListItems().get(4).getId());

                context.startActivity(intent);
            }
        });

        viewHolder.img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HuiActivity.class);
                intent.putExtra("gid",  itemList.get(i).getGoodListItems().get(5).getId());

                context.startActivity(intent);
            }
        });

        Glide
                .with(context)
                .load(API.ADD + itemList.get(i).getGoodListItems().get(0).getSmallPic())
                .into(viewHolder.img);
        Glide
                .with(context)
                .load(API.ADD + itemList.get(i).getGoodListItems().get(2).getSmallPic())
                .into(viewHolder.img3);
        Glide
                .with(context)
                .load(API.ADD + itemList.get(i).getGoodListItems().get(3).getSmallPic())
                .into(viewHolder.img4);
        Glide
                .with(context)
                .load(API.ADD + itemList.get(i).getGoodListItems().get(4).getSmallPic())
                .into(viewHolder.img5);
        Glide
                .with(context)
                .load(API.ADD + itemList.get(i).getGoodListItems().get(5).getSmallPic())
                .into(viewHolder.img6);
        Glide
                .with(context)
                .load(API.ADD + itemList.get(i).getGoodListItems().get(1).getSmallPic())
                .into(viewHolder.img1);
        Glide
                .with(context)
                .load(API.ADD + itemList.get(i).getLogo())
                .into(viewHolder.imgTitle);


        return convertView;
    }

    static class ViewHolder{
        ImageView imgTitle;
        ImageView img;
        ImageView img1;
        ImageView img4;
        ImageView img3;
        ImageView img5;
        ImageView img6;
        TextView tvPrice;
        TextView tvYiShou;
        TextView tvPrice1;
        TextView tvYiShou1;
        TextView tvPrice4;
        TextView tvYiShou4;
        TextView tvPrice3;
        TextView tvYiShou3;
        TextView tvPrice5;
        TextView tvYiShou5;
        TextView tvPrice6;
        TextView tvYiShou6;
    }
}
