package com.qkxmall.mall.views.address;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 01/09/2016.
 */
public class AddressSelectorAdapter extends BaseAdapter {
    Context context;
    Handler handler;
    List<HashMap<String ,Object>> list = new ArrayList<>();
    List<HashMap<String ,Object>> statusList = new ArrayList<>();

    public AddressSelectorAdapter(Context context, Handler handler, List<HashMap<String, Object>> list, List<HashMap<String, Object>> statusList) {
        this.context = context;
        this.handler = handler;
        this.list = list;
        this.statusList = statusList;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.child_address_item,null);
            a.itemBody = (RelativeLayout) convertView.findViewById(R.id.itemBody);
            a.select = (CheckBox) convertView.findViewById(R.id.select);
            a.name = (TextView) convertView.findViewById(R.id.name);
            a.phone = (TextView) convertView.findViewById(R.id.phone);
            a.userName = (TextView) convertView.findViewById(R.id.userName);
            a.address = (TextView) convertView.findViewById(R.id.address);
            a.defaultText = (TextView) convertView.findViewById(R.id.defaultText);
            convertView.setTag(a);
        }
        HashMap<String ,Object> data = list.get(position);
        HashMap<String ,Object> statusData = statusList.get(position);
        a.select.setChecked((Boolean) statusData.get("status"));
        a.name.setText((CharSequence) data.get("addressname"));
        if (data.get("isdefault").equals("1")){
            a.defaultText.setVisibility(View.VISIBLE);
        }else{
            a.defaultText.setVisibility(View.GONE);
        }
        a.userName.setText((CharSequence) data.get("addressname"));
        a.phone.setText((CharSequence) data.get("mobile"));
        a.address.setText((String) data.get("provincename")+data.get("cityname")+data.get("districtname")+data.get("address"));
        a.itemBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = BackgroundTask.CLICK;
                Bundle bundle = new Bundle();
                bundle.putString("position",position+"");
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
        a.itemBody.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Message message = new Message();
                message.what = BackgroundTask.LONG_CLICK;
                Bundle bundle = new Bundle();
                bundle.putString("position",position+"");
                message.setData(bundle);
                handler.sendMessage(message);
                return true;
            }
        });
        return convertView;
    }

    static class A{
        RelativeLayout itemBody;
        CheckBox select;
        TextView name;
        TextView phone;
        TextView userName;
        TextView address;
        TextView defaultText;
    }

}
