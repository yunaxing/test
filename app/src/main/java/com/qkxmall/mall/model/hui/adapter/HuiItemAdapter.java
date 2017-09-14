package com.qkxmall.mall.model.hui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qkxmall.mall.R;
import com.qkxmall.mall.nets.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 02/18/2016.
 */
public class HuiItemAdapter extends BaseAdapter {

    Context context;
    List<HashMap<String ,Object>> list = new ArrayList<>();

    public HuiItemAdapter(Context context, List<HashMap<String, Object>> list) {
        this.context = context;
        getlist(list);
    }

    private void getlist(List<HashMap<String, Object>> list){
        for(int i=0; i<list.size(); i++){
            HashMap<String ,Object> data  = list.get(i);
            if(data.get("isrecommend").toString().equals("1")){
                this.list.add(data);
            }
        }
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
        if(convertView != null) {
            a = (A) convertView.getTag();
        }else{
            a = new A();
            convertView = LayoutInflater.from(context).inflate(R.layout.child_hui_show_item, null);
            a.image = (ImageView) convertView.findViewById(R.id.image_title);
//            a.name = (TextView) convertView.findViewById(R.id.name);
//            a.price = (TextView) convertView.findViewById(R.id.price);
//            a.saled = (TextView) convertView.findViewById(R.id.saled);
//            a.kaiXinDou = (TextView) convertView.findViewById(R.id.kaiXinDouNumber);
            convertView.setTag(a);
        }

        HashMap<String ,Object> data  = list.get(position);
//        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//         DisplayMetrics displayMetrics = new DisplayMetrics();
//        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
//        a.image.setMaxWidth(displayMetrics.widthPixels / 2 - 4);
//        a.image.setMinimumWidth(displayMetrics.widthPixels / 2 - 4);
//        a.image.setMaxHeight(displayMetrics.widthPixels/2-4);
//        a.image.setMinimumHeight(displayMetrics.widthPixels/2-4);


//        String[] imagesArray  = ((String) data.get("thumb")).split(",");



//        a.image.setImageURI(Uri.parse(API.ADD + imagesArray[0]));
        Glide
                .with(context)
                .load(API.ADD + data.get("logo"))
                .into(a.image);
//        a.name.setText((CharSequence) data.get("name"));
//        a.price.setText((CharSequence) data.get("shop_price"));
//        a.kaiXinDou.setText((CharSequence) data.get("kaixindou"));
//        a.saled.setText((CharSequence) data.get("sales_number")) ;

        return convertView;
    }

    static class A{
        ImageView image;
//        TextView name;
//        TextView price;
//        TextView kaiXinDou;
//        TextView saled;
    }

}
