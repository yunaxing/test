package com.qkxmall.mall.views.MyView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qkxmall.mall.R;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;

/**
 * Created by Administrator on 2016/5/5.
 */
public class HuiCartListViewAdapter extends BaseAdapter {
    private Context context;
    private Handler handler;
    private List<HashMap<String, Object>> list;

    public HuiCartListViewAdapter(Context context, Handler handler, List<HashMap<String, Object>>
            list) {
        this.context = context;
        this.handler = handler;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
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
       A a;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.list_gouwuche_hui_item_layout,null);
//            View view=View.inflate(context, R.layout.list_gouwuche_hui_item_layout,null);

        }

        return convertView;
    }
    public static class A{


    }
}
