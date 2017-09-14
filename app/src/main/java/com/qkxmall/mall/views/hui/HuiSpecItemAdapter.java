package com.qkxmall.mall.views.hui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 1/7/2016.
 */
public class HuiSpecItemAdapter extends BaseAdapter {
    Context context;
    Handler handler;
    List<HashMap<String ,Object>> list = new ArrayList<>();
    List<HashMap<String ,Object>> selectors = new ArrayList<>();

    public HuiSpecItemAdapter(Context context, Handler handler, List<HashMap<String, Object>> list, List<HashMap<String, Object>> selectors) {
        this.context = context;
        this.handler = handler;
        this.list = list;
        this.selectors = selectors;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        A a;
        if (convertView != null){
            a = (A) convertView.getTag();
        }else {
            a = new A();
            convertView = LayoutInflater.from(context).inflate(R.layout.spec_item_view,null);
            convertView.setTag(a);
        }
        HashMap<String ,Object> data = list.get(position);
        HashMap<String ,Object> select = selectors.get(position);
        a.item.setText((CharSequence) data.get("title"));
        a.item.setSelected((Boolean) select.get("select"));
        a.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = BackgroundTask.CHECKED;
                Bundle bundle = new Bundle();
                bundle.putString("position",position+"");
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
        return convertView;
    }

    static class A{
        TextView item;
    }

}
