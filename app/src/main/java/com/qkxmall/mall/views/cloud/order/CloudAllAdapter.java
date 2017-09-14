package com.qkxmall.mall.views.cloud.order;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
public class CloudAllAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String ,Object>> list = new ArrayList<>();

    public CloudAllAdapter(Context context, List<HashMap<String, Object>> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.child_cloud_all,null);
            a.image = (SimpleDraweeView) convertView.findViewById(R.id.image_title);
            a.name = (TextView) convertView.findViewById(R.id.name);
            a.term = (TextView) convertView.findViewById(R.id.term);
            a.time = (TextView) convertView.findViewById(R.id.time);
            a.luck = (TextView) convertView.findViewById(R.id.luck);
            a.myLuck = (TextView) convertView.findViewById(R.id.luck_number);
            convertView.setTag(a);
        }
        HashMap<String ,Object> data = list.get(position);
        //Toast.makeText(context, "需要云购订单状态，", Toast.LENGTH_SHORT).show();
        a.name.setText(data.get("goodsname").toString());
        a.term.setText((CharSequence)data.get("term"));
        a.time.setText((CharSequence)data.get("paydate"));
        a.luck.setText((CharSequence)data.get("lucknumber"));
        a.image.setImageURI(Uri.parse(API.ADD+(CharSequence)data.get("img")));
        return convertView;
    }

    static class A{
        SimpleDraweeView image;
        TextView name;
        TextView term;
        TextView time;
        TextView luck;
        TextView myLuck;
    }

}
