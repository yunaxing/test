package com.qkxmall.mall.views.fragment.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qkxmall.mall.R;
import com.qkxmall.mall.nets.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 01/15/2016.
 */
public class CloudHotAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String ,Object>> list = new ArrayList<>();

    public CloudHotAdapter(Context context, List<HashMap<String, Object>> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.cloud_buy_grid_view_item_1_layout , null);
            a.image = (ImageView) convertView.findViewById(R.id.item_img_cloud_buy);
            a.name = (TextView)convertView.findViewById(R.id.item_name_cloud_buy);
            a.current = (TextView)convertView.findViewById(R.id.item_text_cloud_buy_1);
            a.total = (TextView)convertView.findViewById(R.id.item_text_cloud_buy_2);
            a.remain = (TextView)convertView.findViewById(R.id.item_text_cloud_buy_3);
            a.progress = (ProgressBar)convertView.findViewById(R.id.item_progress_cloud_buy);
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);;
            a.image.setMaxWidth(displayMetrics.widthPixels / 3 - 8);
            a.image.setMinimumWidth(displayMetrics.widthPixels/3-8);
            a.image.setMaxHeight(displayMetrics.widthPixels / 3 - 8);
            a.image.setMinimumHeight(displayMetrics.widthPixels/3-8);
            convertView.setTag(a);
        }
        HashMap<String ,Object> data = list.get(position);
//        a.image.setImageURI(Uri.parse(API.ADD + ));
        Glide
                .with(context)
                .load(API.ADD +data.get("img"))
                .into(a.image);
        a.name.setText((CharSequence) data.get("name"));
        a.current.setText((CharSequence) data.get("curnum"));
        a.total.setText((CharSequence) data.get("totalnum"));
        a.remain.setText((CharSequence) data.get("remain"));

        int totalNum = Integer.parseInt((String) data.get("totalnum"));
        int currentNum = Integer.parseInt((String) data.get("curnum"));
        int remain = totalNum-currentNum;
        a.remain.setText(String.valueOf(remain));

        a.progress.setMax(Integer.parseInt((String) data.get("totalnum")));
        a.progress.setProgress(Integer.parseInt((String) data.get("curnum")));
        return convertView;
    }

    static class A{
        ImageView image;            //图片
        TextView name;      //名称
        TextView 	current;           //已有
        TextView 	total;          //总共
        TextView    remain;          //还需
        ProgressBar progress;            //进度
    }
}
