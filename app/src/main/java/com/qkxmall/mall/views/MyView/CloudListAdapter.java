package com.qkxmall.mall.views.MyView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qkxmall.mall.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/4/8.
 */
public class CloudListAdapter extends BaseAdapter {
    private List<HashMap<String, Object>> cloudList;
    private Context mContext;

    public CloudListAdapter(Context context, List list) {

        mContext=context;
        cloudList=list;
    }

    @Override
    public int getCount() {
        return cloudList.size();
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
              View view=View.inflate(mContext, R.layout.list_gouwuche_cloud_item_layout,null);

        return view;
    }
}
