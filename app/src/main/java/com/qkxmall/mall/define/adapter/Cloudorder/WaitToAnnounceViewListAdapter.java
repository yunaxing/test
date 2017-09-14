package com.qkxmall.mall.define.adapter.Cloudorder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.views.cloud.CloudBuyProductDetailPageActivity;
import com.qkxmall.mall.views.MyLuckNumberActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 9/21/2015.
 */
public class WaitToAnnounceViewListAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String , Object>> list;

    public WaitToAnnounceViewListAdapter(Context context, List<HashMap<String, Object>> list) {
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

    ViewHolder viewHolder;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
        }else {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.all_cloud_order_wait_to_announce_list_layout,null);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.picture);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.number = (TextView) convertView.findViewById(R.id.periods);
            viewHolder.progressBar = (ProgressBar) convertView.findViewById(R.id.progress);
            viewHolder.beJoin = (TextView) convertView.findViewById(R.id.had_join);
            viewHolder.need = (TextView) convertView.findViewById(R.id.all_need);
            viewHolder.needToJoin = (TextView) convertView.findViewById(R.id.residue);
            viewHolder.follow = (Button) convertView.findViewById(R.id.follow);
            viewHolder.luckNumber = (TextView) convertView.findViewById(R.id.luck_number);
            viewHolder.luckNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, MyLuckNumberActivity.class));
                }
            });
            viewHolder.follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context,CloudBuyProductDetailPageActivity.class));
                }
            });
            viewHolder.picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, CloudBuyProductDetailPageActivity.class));
                }
            });
            convertView.setTag(viewHolder);
        }
        HashMap<String , Object> dataMap = list.get(position);
        viewHolder.picture.setImageBitmap((Bitmap) dataMap.get("1"));
        viewHolder.name.setText((String) dataMap.get("2"));
        viewHolder.number.setText((String) dataMap.get("3"));
        viewHolder.beJoin.setText((String) dataMap.get("5"));
        viewHolder.need.setText((String) dataMap.get("6"));
        viewHolder.needToJoin.setText((String) dataMap.get("7"));
        viewHolder.progressBar.setMax(Integer.parseInt((String) dataMap.get("6")));
        viewHolder.progressBar.setProgress(Integer.parseInt((String) dataMap.get("5")));
        viewHolder.luckNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MyLuckNumberActivity.class));
            }
        });
        viewHolder.follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CloudBuyProductDetailPageActivity.class));
            }
        });
        viewHolder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CloudBuyProductDetailPageActivity.class));
            }
        });

        viewHolder.follow.setOnClickListener(new OnClickListeners(position));
        return convertView;
    }

    public  static class ViewHolder{
        ImageView picture;
        TextView name;
        TextView number;
        ProgressBar progressBar;
        TextView beJoin;
        TextView need;
        TextView needToJoin;
        Button follow;
        TextView luckNumber;
    }

    private class OnClickListeners implements View.OnClickListener {
        int position;

        public OnClickListeners(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            // TODO: 9/21/2015
            switch (v.getId()){
                case R.id.follow:
                    context.startActivity(new Intent(context,CloudBuyProductDetailPageActivity.class));
                    break;
            }
        }
    }

    private class ToastOnClickListeners implements View.OnClickListener {
        int position;
        public ToastOnClickListeners(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context,"跟单!",Toast.LENGTH_SHORT).show();
        }
    }
}
