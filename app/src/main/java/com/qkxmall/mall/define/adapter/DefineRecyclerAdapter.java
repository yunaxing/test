package com.qkxmall.mall.define.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 10/13/2015.
 */
public class DefineRecyclerAdapter extends RecyclerView.Adapter {
    Context context;
    List<HashMap<String , Object>> list;
    ViewPager viewPager;

    public DefineRecyclerAdapter(Context context, List<HashMap<String, Object>> list, ViewPager viewPager) {
        this.context = context;
        this.list = list;
        this.viewPager = viewPager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.title_tag_layout , null));
        return viewHolder;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.getBg().setBackgroundColor(context.getResources().getColor(R.color.white));
        viewHolder.getA().setTextColor(context.getResources().getColor(R.color.text_black));
        viewHolder.getB().setTextColor(context.getResources().getColor(R.color.text_black));
        viewHolder.getNumber().setTextColor(context.getResources().getColor(R.color.text_black));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        HashMap<String , Object> data = list.get(position);
        viewHolder.getNumber().setText((String) data.get("number"));
        viewHolder.getBg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*int pos = Integer.parseInt(viewHolder.getNumber().getText().toString().trim());
                viewPager.setCurrentItem(pos);*/
                viewHolder.getBg().setBackgroundColor(Color.RED);
                viewHolder.getA().setTextColor(context.getResources().getColor(R.color.white));
                viewHolder.getB().setTextColor(context.getResources().getColor(R.color.white));
                viewHolder.getNumber().setTextColor(context.getResources().getColor(R.color.white));
                viewPager.setCurrentItem(position);
                Toast.makeText(context , "当前"+position , Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private View root;
        private LinearLayout bg;
        private TextView number;
        private TextView a;
        private TextView b;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            bg = (LinearLayout) root.findViewById(R.id.bg);
            number = (TextView) root.findViewById(R.id.number);
            a = (TextView) root.findViewById(R.id.a);
            b = (TextView) root.findViewById(R.id.b);
        }

        public TextView getB() {
            return b;
        }

        public void setB(TextView b) {
            this.b = b;
        }

        public TextView getA() {
            return a;
        }

        public void setA(TextView a) {
            this.a = a;
        }

        public TextView getNumber() {
            return number;
        }

        public void setNumber(TextView number) {
            this.number = number;
        }

        public LinearLayout getBg() {
            return bg;
        }

        public void setBg(LinearLayout bg) {
            this.bg = bg;
        }
    }

}
