package com.qkxmall.mall.views.fragment.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.nets.API;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/1/9.
 * 推荐类适配器
 */
public class RecommendTypeItemAdapter extends BaseAdapter {

    private List<HashMap<String ,Object>> data;
    private LayoutInflater mInflater;
    private Context context;

    public RecommendTypeItemAdapter(Context c , List<HashMap<String ,Object>> d)
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
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.cloud_buy_grid_view_item_2_layout , null);

            viewHolder = new ViewHolder();
            viewHolder.pic = (SimpleDraweeView) convertView.findViewById(R.id.image_title);
            viewHolder.goodsName = (TextView)convertView.findViewById(R.id.name);
            viewHolder.have = (TextView)convertView.findViewById(R.id.item_text_cloud_buy_12);
            viewHolder.total = (TextView)convertView.findViewById(R.id.item_text_cloud_buy_22);
            viewHolder.other = (TextView)convertView.findViewById(R.id.item_text_cloud_buy_32);
            viewHolder.pro = (ProgressBar)convertView.findViewById(R.id.item_pro_progress_cloud_buy);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (data != null) {
            HashMap<String, Object> item = data.get(position);
            viewHolder.pic.setImageURI(Uri.parse(API.ADD + (String)item.get("img")));
            viewHolder.goodsName.setText((String)item.get("name"));
            viewHolder.have.setText((String)item.get("curnum"));
            viewHolder.total.setText((String)item.get("totalnum"));

            int other = Integer.parseInt((String)item.get("totalnum")) - Integer.parseInt((String)item.get("curnum"));
            viewHolder.other.setText(other + "");

            int value = 100 * Integer.parseInt((String)item.get("curnum")) / Integer.parseInt((String)item.get("totalnum"));
            viewHolder.pro.setProgress(value);
        }

        return convertView;
    }

    public class ViewHolder
    {
        SimpleDraweeView pic;            //图片
        TextView    goodsName;      //名称
        TextView 	have;           //已有
        TextView 	total;          //总共
        TextView    other;          //还需
        ProgressBar pro;            //进度
    }
}
