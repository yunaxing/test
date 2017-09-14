package com.qkxmall.mall.views.MyView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qkxmall.mall.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class HuiListAdapter extends BaseAdapter {
    List<HashMap<String ,Object>> huiList;
    Context mContext;

    public HuiListAdapter(Context context, List list){
        mContext=context;
        huiList=list;
    }

    @Override
    public int getCount() {
        return huiList.size();
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
       View view=View.inflate(mContext, R.layout.list_gouwuche_hui_item_layout,null);
//        TextView tv=(TextView)view.findViewById(R.id.my_list_tv);
//        tv.setText(position+"条目");
        return view;
    }

}
