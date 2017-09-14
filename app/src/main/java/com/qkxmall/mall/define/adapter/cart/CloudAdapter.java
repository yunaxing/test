package com.qkxmall.mall.define.adapter.cart;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.views.cloud.CloudBuyProductDetailPageActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 9/23/2015.
 */
public class CloudAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String , Object>> list;

    public CloudAdapter(Context context, List<HashMap<String, Object>> list) {
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
        final ViewHolder viewHolder;
        if (convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
        }else {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_gouwuche_cloud_item_layout,null);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.check);
            viewHolder.picture = (SimpleDraweeView) convertView.findViewById(R.id.picture);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.joined = (TextView) convertView.findViewById(R.id.is_join);
            viewHolder.allNeed = (TextView) convertView.findViewById(R.id.all_need);
            viewHolder.need = (TextView) convertView.findViewById(R.id.need);
            viewHolder.goodsID = (TextView) convertView.findViewById(R.id.goodsID);
            viewHolder.progressBar = (ProgressBar) convertView.findViewById(R.id.progress);
            convertView.setTag(viewHolder);
        }
        HashMap<String, Object> data = list.get(position);
        viewHolder.picture.setImageURI((Uri) data.get("picture"));
        viewHolder.checkBox.setChecked(false);
        viewHolder.name.setText((Integer) data.get("name"));
        viewHolder.joined.setText((Integer) data.get("joined"));
        viewHolder.allNeed.setText((Integer) data.get("allNeed"));
        viewHolder.need.setText(Integer.parseInt(viewHolder.allNeed.getText().toString().trim())-Integer.parseInt(viewHolder.joined.getText().toString().trim()));
        viewHolder.progressBar.setMax(Integer.parseInt(viewHolder.allNeed.getText().toString().trim()));
        viewHolder.progressBar.setProgress(Integer.parseInt(viewHolder.joined.getText().toString().trim()));
        viewHolder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CloudBuyProductDetailPageActivity.class);
                intent.putExtra("goodsID",viewHolder.goodsID.getText().toString().trim());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    static class ViewHolder{
        CheckBox checkBox;
        SimpleDraweeView picture;
        TextView name;
        TextView joined;
        TextView allNeed;
        TextView need;
        TextView goodsID;
        ProgressBar progressBar;
    }

}
