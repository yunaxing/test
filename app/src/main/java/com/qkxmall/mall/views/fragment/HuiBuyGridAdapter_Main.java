package com.qkxmall.mall.views.fragment;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 1/7/2016.
 */
public class HuiBuyGridAdapter_Main extends BaseAdapter {
    Context context;
    List<HashMap<String ,Object>> list = new ArrayList<>();

    public HuiBuyGridAdapter_Main(Context context, List<HashMap<String, Object>> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_layout,null);
            a.imageView = (SimpleDraweeView) convertView.findViewById(R.id.item_image);
            convertView.setTag(a);
        }
        HashMap<String ,Object> data = list.get(position);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        //1a.imageView.setMaxHeight(windowManager.getDefaultDisplay().getWidth() / 3);
        //2a.imageView.setMinimumHeight(windowManager.getDefaultDisplay().getWidth() / 3);

//        Log.e("image_Height",a.imageView.getHeight()+"");

//        a.imageView.setImageResource((Integer) data.get("image"));
        //3a.imageView.setImageURI(Uri.parse((String) data.get("image")));
        //注释掉了1,2,3 选择另一种方式加载图片（优化）
        Glide.with(context).load(data.get("image")).into(a.imageView);

        return convertView;
    }

    static class A{
        SimpleDraweeView imageView;
    }

}
