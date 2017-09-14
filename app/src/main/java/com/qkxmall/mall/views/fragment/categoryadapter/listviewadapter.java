package com.qkxmall.mall.views.fragment.categoryadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.beans.Category;

import java.util.ArrayList;

/**
 * Created by xingyuna on 2017/5/6.
 */

public class listviewadapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Category> mList;

    public listviewadapter(Context context, ArrayList<Category> list) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        gridviewadapter.ViewHolder viewHolder = new gridviewadapter.ViewHolder();
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.item_all_buy_menu_listview, null);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.all_buy_menu_hui_item_0);

//            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (gridviewadapter.ViewHolder) convertView.getTag();
        }

//        MemberTypeBean label = (MemberTypeBean) mList.get(position);
//        DecimalFormat df = new DecimalFormat("######0.00");
        viewHolder.tvName.setText(mList.get(position).getName());
//        viewHolder.tvPrice.setText("￥"+df.format((double)(Integer.parseInt(label.getPrice())/100.00)));
        return convertView;
    }

    static class ViewHolder {
        TextView tvName;
        TextView tvPrice;
    }

}
