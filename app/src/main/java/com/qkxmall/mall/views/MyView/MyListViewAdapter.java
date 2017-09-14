package com.qkxmall.mall.views.MyView;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qkxmall.mall.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/4/20.
 */
public class MyListViewAdapter extends BaseAdapter {
    Context context;
    Handler handler;
    List<HashMap<String ,Object>> list = new ArrayList<>();
    List<Boolean> selectList = new ArrayList<>();
    Handler clickPosition;
    List<View> lsit=new ArrayList<>();

public MyListViewAdapter(Context context, Handler handler, List<HashMap<String, Object>> list, List<Boolean> selectList, Handler clickPosition){
    this.context = context;
    this.handler = handler;
    this.list = list;
    this.selectList = selectList;
    this.clickPosition = clickPosition;
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
        if(convertView!=null){
            a=(A)convertView.getTag();
        }else {
            a=new A();
            convertView = LayoutInflater.from(context).inflate(R.layout.my_list_adapter_time,null);
            a.mRadioGroup=(FlowRadioGroup)convertView.findViewById(R.id.flow_radiogroup);
            convertView.setTag(a);
        }
        final HashMap<String ,Object> data = list.get(position);

        if(selectList.get(position)){
            //设置为选中
        }else {
            //
        }





        return convertView;
    }
    static class A{
        FlowRadioGroup mRadioGroup;
    }

}
