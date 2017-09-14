package com.qkxmall.mall.model.hui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.Utils;
import com.qkxmall.mall.nets.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 01/03/2016.
 */
public class HuiBrandAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String, Object>> list = new ArrayList();
    int width;
    int height;
    WindowManager vm;

    public HuiBrandAdapter(Context context, List<HashMap<String, Object>> list) {
        this.context = context;
        this.list = list.subList(list.size()-4,list.size());
        this.vm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        this.width = vm.getDefaultDisplay().getWidth()/4;
        this.height=(int)(width*1.3);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder a;
        if (convertView != null) {
            a = (ViewHolder) convertView.getTag();
        } else {
            a =new  ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.child_brand_layout, null);
            a.brand = (ImageView) convertView.findViewById(R.id.brand);
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(width,height);
            a.brand.setLayoutParams(lp);
//            a.brand.setMaxWidth(width);
//            a.brand.setMaxHeight(height);
            convertView.setTag(a);

        }

        HashMap<String, Object> data = list.get(position);
        Uri uri = Uri.parse(API.ADD + data.get("logo"));
        DraweeController draweeController =
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri)

                        .setAutoPlayAnimations(true)

                        .build();

        //a.brand.setImageURI()
//        a.brand.setController(draweeController);
//        Log.e("brandAddress",API.ADD+data["logo"])
        Glide
                .with(context)
                .load(API.ADD + data.get("logo"))
//                .placeholder(context.getDrawable(R.drawable.load_default))
                .into(a.brand);
        return convertView;
    }

    @Override
    public Object getItem(int i) {
        return list == null ? null : list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getCount() {
        return 4;
    }

    public static class ViewHolder {
        ImageView brand;
    }

}
