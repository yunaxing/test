package com.qkxmall.mall.views.cart;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.nets.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 1/6/2016.
 */
public class HuiCartListAdapter extends BaseAdapter{
    int STATE_WAITE = 0;
    int STATE_AFTER = 2;
    int STATE_PAYED = 3;

    Context context;
    Handler handler;
    List<HashMap<String ,Object>> list= new ArrayList();

    HuiCartListAdapter(Context context, Handler handler,  List<HashMap<String, Object>> list) {
        this.context = context;
        this.handler = handler;
        this.list = list;
    }

    public List<HashMap<String ,Object>> getList(){
        return list;
    }

    public View getView(final int position,View convertView,ViewGroup parent) {
        A a;

        if(convertView != null){
            a=(A)convertView.getTag();
        }else{
            a =new  A();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_gouwuche_hui_item_layout,null);
            a.select =(CheckBox) convertView.findViewById(R.id.select);
            a.image =(SimpleDraweeView) convertView.findViewById(R.id.picture);
            a.name = (TextView)convertView.findViewById(R.id.name) ;
            a.price =(TextView) convertView.findViewById(R.id.price);
            a.buyNumber = (TextView)convertView.findViewById(R.id.buyNumber);
            a.departure = (TextView)convertView.findViewById(R.id.dividerLocation);
            convertView.setTag(a);
        }
        HashMap<String ,Object>  data= list.get(position);
        a.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Message message= new Message();
                if(isChecked){
                    message.what = BackgroundTask.CHECKED;
                    message.arg1 = CartFragment.HUI;
                }else{
                    message.what = BackgroundTask.UN_CHECKED;
                    message.arg1 = CartFragment.HUI;
                }
                 Bundle bundle=new  Bundle();
                bundle.putString("position",position+"");
                message.obj = bundle;
                handler.sendMessage(message);
            }
        });
        String[] images = ((String)data.get("thumb") ).split(",");
        a.image.setImageURI(Uri.parse(API.ADD+images[0]));
        a.name.setText(""+data.get("name"));
        a.buyNumber.setText( ""+data.get("salesnumber"));
        a.price.setText( ""+data.get("c_price"));
        a.departure.setText(  "");
        return convertView;
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
        public int getCount() {
                return list.size();
        }

//        @Override
//        public int getViewTypeCount() {
//                return 3;
//        }
        //
//    override fun getItemViewType(position: Int): Int {
//        var data: HashMap<String ,Any> = list.get(position)
//        when (data["state"]) {
//            "1" -> {
//                return STATE_WAITE
//            }
//            "2" -> {
//                return STATE_PAYED
//            }
//            "3" -> {
//                return STATE_AFTER
//            }
//            else -> {
//                return STATE_WAITE
//            }
//        }
//    }
//
//        @Override
//        public int getItemViewType(int position) {
//                switch((int)list.get(position).get("state")){
//                    case 1:break;
//                    case 2:break;
//                    case 3:break;
//                }
//                return super.getItemViewType(position);
//        }

        class A{
        CheckBox select;
             SimpleDraweeView image;
        TextView name;
        TextView price;
        TextView buyNumber;
        TextView departure;

    }

}