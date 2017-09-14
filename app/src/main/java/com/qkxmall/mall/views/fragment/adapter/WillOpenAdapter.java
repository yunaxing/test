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

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/1/9.
 * 即将揭晓适配器
 */
public class WillOpenAdapter extends BaseAdapter {

    private List<HashMap<String ,Object>> data;
    private LayoutInflater mInflater;
    private Context context;

    public WillOpenAdapter(Context c , List<HashMap<String ,Object>> d)
    {
        context = c;
        data = d;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        if (data != null)
        {
            return data.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (data != null)
        {
            return data.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        A a;
        if (convertView == null) {
            a = new A();
            convertView = mInflater.inflate(R.layout.cloud_buy_grid_view_item_1_layout , null);
            a.pic = (ImageView) convertView.findViewById(R.id.item_img_cloud_buy);
            a.goodsName = (TextView)convertView.findViewById(R.id.item_name_cloud_buy);
            a.have = (TextView)convertView.findViewById(R.id.item_text_cloud_buy_1);
            a.total = (TextView)convertView.findViewById(R.id.item_text_cloud_buy_2);
            a.other = (TextView)convertView.findViewById(R.id.item_text_cloud_buy_3);
            a.pro = (ProgressBar)convertView.findViewById(R.id.item_progress_cloud_buy);
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);;
            a.pic.setMaxWidth(displayMetrics.widthPixels / 3 - 8);
            a.pic.setMinimumWidth(displayMetrics.widthPixels/3-8);
            a.pic.setMaxHeight(displayMetrics.widthPixels / 3 - 8);
            a.pic.setMinimumHeight(displayMetrics.widthPixels/3-8);
            convertView.setTag(a);
        } else {
            a = (A) convertView.getTag();
        }

        if (data != null) {
            HashMap<String, Object> item = data.get(position);
//            a.pic.setImageURI());
            a.goodsName.setText((String)item.get("name"));
            a.have.setText((String)item.get("curnum"));
            a.total.setText((String)item.get("totalnum"));
            a.other.setText((String)item.get("remain"));
            Glide
                    .with(context)
                    .load(API.ADD + item.get("img"))
                    .into(a.pic);
            int value = 100 * Integer.parseInt((String)item.get("curnum")) / Integer.parseInt((String)item.get("totalnum"));
            a.pro.setProgress(value);
        }

        return convertView;
    }

    public class A {
        ImageView pic;            //图片
        TextView    goodsName;      //名称
        TextView 	have;           //已有
        TextView 	total;          //总共
        TextView    other;          //还需
        ProgressBar pro;            //进度
    }
}
