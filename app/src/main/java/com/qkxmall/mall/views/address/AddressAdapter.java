package com.qkxmall.mall.views.address;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 01/16/2016.
 */
public class AddressAdapter extends BaseAdapter {
    private static final int TYPE_INSIDE = 0;
    private static final int TYPE_OUTSIDE = 1;

    Context context;
    List<HashMap<String ,Object>> list = new ArrayList<>();
    Handler handler;

    public AddressAdapter(Context context, List<HashMap<String, Object>> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.account_manage_manage_address_list_item_layout,null);
            a.setDefault = (RadioButton) convertView.findViewById(R.id.setDefault);
            a.isDefault = (TextView) convertView.findViewById(R.id.isDefault);
            a.name = (TextView) convertView.findViewById(R.id.name);
            a.phone = (TextView) convertView.findViewById(R.id.phone);
            a.address = (TextView) convertView.findViewById(R.id.address);
            convertView.setTag(a);
        }
        HashMap<String ,Object> data = list.get(position);
        if ((Integer)data.get("isdefault") == 1){
            a.isDefault.setVisibility(View.VISIBLE);
        }else {
            a.isDefault.setVisibility(View.GONE);
        }
        a.name.setText((CharSequence) data.get("addressname"));
        a.address.setText((String) data.get("provincename")+data.get("cityname")+data.get("districtname")+data.get("address"));
        a.phone.setText((CharSequence) data.get("mobile"));

        a.setDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Message message = new Message();
                    message.what = BackgroundTask.CHECKED;
                    Bundle bundle = new Bundle();
                    bundle.putString("position", String.valueOf(position));
                    message.setData(bundle);
                    handler.sendMessage(message);
                }else {
                    Message message = new Message();
                    message.what = BackgroundTask.UN_CHECKED;
                    Bundle bundle = new Bundle();
                    bundle.putString("position", String.valueOf(position));
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
            }
        });

        return convertView;
    }

    static class A{
        RadioButton setDefault;
        TextView isDefault;
        TextView name;
        TextView phone;
        TextView address;
    }
}
