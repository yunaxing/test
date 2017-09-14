package com.qkxmall.mall.views.MyView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qkxmall.mall.R;

/**
 * Created by Administrator on 2016/4/11.
 */
public class CloudMianAdapter2 extends BaseAdapter {
    Context mContext;
    int[] imgeIds;

    public CloudMianAdapter2(Context context,int[] ids){
        mContext=context;
        imgeIds=ids;

    }

    @Override
    public int getCount() {
        return imgeIds.length;
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
        ViewHoder vh =null;
        if(convertView==null){
            vh=new ViewHoder();
            convertView=View.inflate(mContext, R.layout.grid_item_layout_2,null);
            vh.image=(ImageView)convertView.findViewById(R.id.item_image);
            convertView.setTag(vh);
        }else{
            vh=(ViewHoder)convertView.getTag();
        }
        Glide.with(mContext).load(imgeIds[position]).into(vh.image);


        return convertView;
    }
    public static class ViewHoder{
        ImageView image;
    }
}
