package com.qkxmall.mall.define.adapter.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.qkxmall.mall.R;
import com.qkxmall.mall.Utils;
import com.qkxmall.mall.nets.API;
import com.umeng.socialize.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 10/15/2015.
 */
public class BaseHuiClassItemAdapter extends BaseAdapter {
    List<HashMap<String, Object>> list = new ArrayList<>();
    Context context;
    int dp2;
    boolean isRect=false;
    Glide mGlide;
    public void isRect(boolean isRect){
        this.isRect=isRect;
    }
    //    BitmapUtils utils;
    public BaseHuiClassItemAdapter() {
    }

    public BaseHuiClassItemAdapter(List<HashMap<String, Object>> list, Context context, boolean isRect) {
        if (list != null) {
            this.list = list;
        }
        this.context = context;
        dp2 = Utils.dp2px(context.getResources(), 2);
        this.isRect = isRect;
        mGlide=Glide.get(context);
        mGlide.setMemoryCategory(MemoryCategory.HIGH);
//        utils=new BitmapUtils(context);
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


    public void addHolder(List<HashMap<String, Object>> list) {
        this.list.addAll(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        A a;
        if (convertView != null) {
            a = (A) convertView.getTag();
        } else {
            a = new A();
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_right_item_layout, null);
            a.image = (ImageView) convertView.findViewById(R.id.image_title);
            a.name = (TextView) convertView.findViewById(R.id.name);
            a.price = (TextView) convertView.findViewById(R.id.price);
            a.bean = (TextView) convertView.findViewById(R.id.bean);
//            a.sale = (TextView) convertView.findViewById(R.id.sale);
            convertView.setTag(a);
        }
        HashMap<String, Object> data = list.get(position);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth() / 2 - dp2;
//        int height=windowManager.getDefaultDisplay().getWidth() / 2;
        int height = 0;
        if (!isRect)
            height = (int) (width / 502.0f * 668);
        else{
            height=width;
        }
        a.image.setMaxHeight(height);
        a.image.setMinimumHeight(height);
        a.image.setMaxWidth(width);
        a.image.setMinimumWidth(width);


        String[] imgs = ((String) data.get("thumb")).split(",");
//        Glide.with(context).load(API.ADD + imgs[0]).into(a.image);
//          a.image.setImageURI(Uri.parse(API.ADD + imgs[0]));
        a.name.setText((CharSequence) data.get("name"));
        a.bean.setText(" 减"+data.get("kaixindou").toString()+" ");
        a.price.setText("￥ " + (CharSequence) data.get("shop_price"));
//        a.sale.setText((CharSequence) data.get("sales_number"));

        Log.v("width==",+width+"***"+height);

        Glide
                .with(context)
                .load(API.ADD + imgs[0])
//                .override(width,height+200)

                .into(a.image);
        return convertView;
    }

    static class A {
        ImageView image;
        TextView name;
        TextView price;
        TextView bean;
        TextView sale;
    }

}
