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
import com.qkxmall.mall.define.override.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 1/7/2016.
 */
public class SpecAdapter extends BaseAdapter {

    Context context;
    List<HashMap<String ,Object>> list = new ArrayList<>();

    public SpecAdapter(Context context, List<HashMap<String, Object>> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.hui_spec_list_item,null);
            a.title = (TextView) convertView.findViewById(R.id.title);
            a.item = (GridView) convertView.findViewById(R.id.specItem);
            convertView.setTag(a);
        }
        HashMap<String ,Object> data = list.get(position);
        final List<HashMap<String ,Object>> selectList = new ArrayList<>();
        HuiSpecItemAdapter huiSpecItemAdapter;
        final boolean[] select = new boolean[((List<HashMap<String ,Object>>)data.get("spec") ).size()];
        for (int i = 0 ; i< select.length ;i++) {
            if (i == 0) {
                select[i] = true;
            }else {
                select[i] = false;
            }
            HashMap<String ,Object> selected = new HashMap<>();
            selected.put("select",select[i]);
            selectList.add(selected);
        }
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                int position = Integer.parseInt(bundle.getString("position"));
                switch (msg.what){
                    case BackgroundTask.CHECKED:{
                        for (int i = 0 ; i< select.length ;i++){
                            if (position == i){
                                selectList.remove(position);
                                HashMap<String ,Object> temp = new HashMap<>();
                                temp.put("select",true);
                                selectList.add(position,temp);
                                select[i] = true;
                            }else {
                                select[i] = false;
                            }
                        }
                    }
                        break;
                }
            }
        };
        a.title.setText((CharSequence) data.get("name"));
        huiSpecItemAdapter = new HuiSpecItemAdapter(context,handler, (List<HashMap<String, Object>>) data.get("spec"),selectList);
        a.item.setAdapter(huiSpecItemAdapter);
        huiSpecItemAdapter.notifyDataSetChanged();

        return convertView;
    }

    static class A{
        TextView title;
        GridView item;
    }


}
