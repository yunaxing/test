package com.qkxmall.mall.views.func.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.beans.Category;
import com.qkxmall.mall.beans.ChildItem;
import com.qkxmall.mall.views.fragment.categoryadapter.*;

import java.util.ArrayList;

/**
 * Created by yuna on 2017/9/14.
 */

public class gridviewadapter2 extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<ChildItem> mList;
    private ArrayList<Category> categorys;

    public gridviewadapter2(Context context, ArrayList<ChildItem> list) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.mList = list;
        this.categorys = categorys;
    }

    @Override
    public int getCount() {

        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.item_all_buy_menu_gridview3, null);
//            viewHolder.tvName = (TextView) convertView.findViewById(R.id.cls_2);
            viewHolder.tvItem = (TextView) convertView.findViewById(R.id.cls_2_1_t);
            viewHolder.imgItem = (ImageView) convertView.findViewById(R.id.cls_2_1_p);
//            viewHolder.tvName3 = (TextView) convertView.findViewById(R.id.cls_3);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//            for(int j=0;j<getCount(); j++){

        viewHolder.tvItem.setText(mList.get(position).getName());
        viewHolder.imgItem.setImageURI(Uri.parse(mList.get(position).getThumb_image()));

//            }


//        MemberTypeBean label = (MemberTypeBean) mList.get(position);
//        DecimalFormat df = new DecimalFormat("######0.00");
//        if(mList.get(position).getBigname().equals("none")){
//            viewHolder.tvName.setVisibility(View.GONE);
//            viewHolder.imgItem.setVisibility(View.GONE);
//            viewHolder.tvItem.setVisibility(View.GONE);
//
//        }else if(mList.get(position).getBigname().equals("invisible")){
////            viewHolder.tvName.setVisibility(View.INVISIBLE);
//////            convertView.setVisibility(View.VISIBLE);
////            viewHolder.imgItem.setVisibility(View.VISIBLE);
////            viewHolder.tvItem.setVisibility(View.VISIBLE);
//
//        }else if(mList.get(position).getBigname().equals("gone")){
//            viewHolder.tvName.setVisibility(View.GONE);
////            viewHolder.imgItem.setVisibility(View.GONE);
////            convertView.setVisibility(View.VISIBLE);
//            viewHolder.imgItem.setVisibility(View.VISIBLE);
//            viewHolder.tvItem.setVisibility(View.VISIBLE);
//        }else {
////            convertView.setVisibility(View.VISIBLE);
//            viewHolder.tvName.setVisibility(View.VISIBLE);
//            viewHolder.tvName.setText(mList.get(position).getBigname());
//            viewHolder.imgItem.setVisibility(View.VISIBLE);
//            viewHolder.tvItem.setVisibility(View.VISIBLE);
//        }
//        if(mList.get(position).getThumb_image() != null){
////            viewHolder.imgItem.setImageURI(Uri.parse("http://www.qkxmall.com/"+mList.get(position).getThumb_image()));
////            if(!mList.get(position+1).getThumb_image().isEmpty())
////            viewHolder.imgItem3.setImageURI(Uri.parse("http://www.qkxmall.com/"+mList.get(position+1).getThumb_image()));
////            if(!mList.get(position+2).getThumb_image().isEmpty())
////            viewHolder.imgItem4.setImageURI(Uri.parse("http://www.qkxmall.com/"+mList.get(position+2).getThumb_image()));
////            if(!mList.get(position+3).getThumb_image().isEmpty())
////            viewHolder.imgItem5.setImageURI(Uri.parse("http://www.qkxmall.com/"+mList.get(position+3).getThumb_image()));
//        }
//        Log.v("thumbimg","thumbimg==="+mList.get(position).getThumb_image());
//
////        if(!mList.get(position).getBigname().equals("none")){
////            if(mList.get(position).getName() == null || mList.get(position).getName().equals("")){
////                viewHolder.imgItem.setVisibility(View.INVISIBLE);
////                viewHolder.tvItem.setVisibility(View.INVISIBLE);
////            }else {
////                viewHolder.imgItem.setVisibility(View.VISIBLE);
////                viewHolder.tvItem.setVisibility(View.VISIBLE);
////                viewHolder.tvItem.setText(mList.get(position).getName());
////            }
////        }


//        viewHolder.tvPrice.setText("￥"+df.format((double)(Integer.parseInt(label.getPrice())/100.00)));
        return convertView;
    }

    private int getCatePosition(int pos){
        int count = 0;
        for(int i=0; i<categorys.size();i++){
            count = categorys.get(i).getChidCategorys().size()+count;
            Log.v("getcateposition","count123=="+count);
            if(pos<count){
                Log.v("getcateposition","=="+i);
                return i;
            }
        }
        Log.v("getcateposition","123=="+pos);
        return pos;
    }

    private int getCateItemPosition(int pos){
        int count = getCatePosition(pos);
        int sum = 0;
        for(int i=0; i<count;i++){
            sum = categorys.get(i).getChidCategorys().size()+sum;

        }

        return pos-sum;
    }

    static class ViewHolder {

        TextView tvName;
        TextView tvItem;
        ImageView imgItem;

    }

}