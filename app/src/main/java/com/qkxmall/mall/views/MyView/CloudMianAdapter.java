package com.qkxmall.mall.views.MyView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qkxmall.mall.R;

/**
 * 云购首页GridView适配器（用于特换原本的SimpleAdapter）在其中对图片加载进行优化
 * Created by Administrator on 2016/4/9.
 */
public class CloudMianAdapter extends BaseAdapter {
    Context mContext;
    int[] Marr;
    public CloudMianAdapter(Context context,int[] arr ){
        mContext=context;
        Marr=arr;
    }
    @Override
    public int getCount() {
        return Marr.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        A a;
        if (convertView==null){
            a=new A();
            convertView=View.inflate(mContext, R.layout.grid_item_layout,null);
            a.image=(ImageView)convertView.findViewById(R.id.item_image);
            convertView.setTag(a);
        }else{
            a=(A)convertView.getTag();
        }
        Glide.with(mContext).load(Marr[position]).into(a.image);
        return convertView;
    }
    public static class A{
        ImageView image;
    }
}
