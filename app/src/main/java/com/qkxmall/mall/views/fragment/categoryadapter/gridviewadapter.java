package com.qkxmall.mall.views.fragment.categoryadapter;

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
//import com.umeng.socialize.utils.Log;

import java.util.ArrayList;

/**
 * Created by xingyuna on 2017/5/6.
 */

public class gridviewadapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<ChildItem> mList;
    private ArrayList<Category> categorys;

    public gridviewadapter(Context context, ArrayList<ChildItem> list,ArrayList<Category> categorys) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.mList = list;
        this.categorys = categorys;
    }

    @Override
    public int getCount() {
        Log.v("allcate","mList=="+categorys.size());
        int count = 0;
        for(int i = 0; i<categorys.size(); i++){
//            for(int j=0;j<categorys.get(i).getChidCategorys().size(); j++) {
                count = categorys.get(i).getChidCategorys().size()+count;
//            }
        }
        Log.v("getcateposition","count-1=="+(count-1));
            return count;
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
        ViewHolder viewHolder = new gridviewadapter.ViewHolder();
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.item_all_buy_menu_gridview, null);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.cls_2);
            viewHolder.tvItem = (TextView) convertView.findViewById(R.id.cls_2_1_t);
            viewHolder.imgItem = (ImageView) convertView.findViewById(R.id.cls_2_1_p);
//            viewHolder.tvName3 = (TextView) convertView.findViewById(R.id.cls_3);
            viewHolder.tvItem3 = (TextView) convertView.findViewById(R.id.cls_3_1_t);
            viewHolder.imgItem3 = (ImageView) convertView.findViewById(R.id.cls_3_1_p);
//            viewHolder.tvName4 = (TextView) convertView.findViewById(R.id.cls_4);
            viewHolder.tvItem4 = (TextView) convertView.findViewById(R.id.cls_4_1_t);
            viewHolder.imgItem4 = (ImageView) convertView.findViewById(R.id.cls_4_1_p);
//            viewHolder.tvName5 = (TextView) convertView.findViewById(R.id.cls_5);
            viewHolder.tvItem5 = (TextView) convertView.findViewById(R.id.cls_5_1_t);
            viewHolder.imgItem5 = (ImageView) convertView.findViewById(R.id.cls_5_1_p);

            viewHolder.tvItem6 = (TextView) convertView.findViewById(R.id.cls_6_1_t);
            viewHolder.imgItem6 = (ImageView) convertView.findViewById(R.id.cls_6_1_p);
//            viewHolder.tvName3 = (TextView) convertView.findViewById(R.id.cls_3);
            viewHolder.tvItem7 = (TextView) convertView.findViewById(R.id.cls_7_1_t);
            viewHolder.imgItem7 = (ImageView) convertView.findViewById(R.id.cls_7_1_p);
//            viewHolder.tvName4 = (TextView) convertView.findViewById(R.id.cls_4);
            viewHolder.tvItem8 = (TextView) convertView.findViewById(R.id.cls_8_1_t);
            viewHolder.imgItem8 = (ImageView) convertView.findViewById(R.id.cls_8_1_p);
//            viewHolder.tvName5 = (TextView) convertView.findViewById(R.id.cls_5);
            viewHolder.tvItem9 = (TextView) convertView.findViewById(R.id.cls_9_1_t);
            viewHolder.imgItem9 = (ImageView) convertView.findViewById(R.id.cls_9_1_p);
            viewHolder.ll1= (LinearLayout)convertView.findViewById(R.id.ll1);
            viewHolder.ll2= (LinearLayout)convertView.findViewById(R.id.ll2);
            viewHolder.ll3= (LinearLayout)convertView.findViewById(R.id.ll3);
            viewHolder.ll4= (LinearLayout)convertView.findViewById(R.id.ll4);
            viewHolder.ll_1= (LinearLayout)convertView.findViewById(R.id.ll_1);
            viewHolder.ll_2= (LinearLayout)convertView.findViewById(R.id.ll_2);
            viewHolder.ll_3= (LinearLayout)convertView.findViewById(R.id.ll_3);
            viewHolder.ll_4= (LinearLayout)convertView.findViewById(R.id.ll_4);
            viewHolder.llxia=(LinearLayout)convertView.findViewById(R.id.ll_xia);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (gridviewadapter.ViewHolder) convertView.getTag();
        }
        Log.v("getcateposition","posiyion=="+position);
        viewHolder.tvName.setText(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getName());


//            for(int j=0;j<getCount(); j++){
                for(int m= 0; m<categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().size(); m++){
                   if(m == 0){
                       viewHolder.tvItem.setVisibility(View.VISIBLE);
                       viewHolder.ll_1.setVisibility(View.VISIBLE);
                       viewHolder.imgItem.setVisibility(View.VISIBLE);
                       viewHolder.tvItem.setText(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(0).getName());
                       viewHolder.imgItem.setImageURI(Uri.parse("http://www.qkxmall.com/"+categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(0).getThumb_image()));
                   }
                    if(m == 1){
                        viewHolder.ll_2.setVisibility(View.VISIBLE);
                        viewHolder.tvItem3.setVisibility(View.VISIBLE);
                        viewHolder.imgItem3.setVisibility(View.VISIBLE);
                        viewHolder.tvItem3.setText(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getName());
                        viewHolder.imgItem3.setImageURI(Uri.parse("http://www.qkxmall.com/"+categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getThumb_image()));
                    }
                    if(m == 2){
                        viewHolder.ll_3.setVisibility(View.VISIBLE);
                        viewHolder.tvItem4.setVisibility(View.VISIBLE);
                        viewHolder.imgItem4.setVisibility(View.VISIBLE);
                        viewHolder.tvItem4.setText(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getName());
                        viewHolder.imgItem4.setImageURI(Uri.parse("http://www.qkxmall.com/"+categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getThumb_image()));
                    }
                    if(m == 3){
                        viewHolder.ll_4.setVisibility(View.VISIBLE);
                        viewHolder.tvItem5.setVisibility(View.VISIBLE);
                        viewHolder.imgItem5.setVisibility(View.VISIBLE);
                        viewHolder.tvItem5.setText(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getName());
                        viewHolder.imgItem5.setImageURI(Uri.parse("http://www.qkxmall.com/"+categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getThumb_image()));
                    }
                    if(m == 4){
                        viewHolder.ll1.setVisibility(View.VISIBLE);
                        viewHolder.llxia.setVisibility(View.VISIBLE);
                        viewHolder.tvItem6.setVisibility(View.VISIBLE);
                        viewHolder.imgItem6.setVisibility(View.VISIBLE);
                        viewHolder.tvItem6.setText(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getName());
                        viewHolder.imgItem6.setImageURI(Uri.parse("http://www.qkxmall.com/"+categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getThumb_image()));
                    }
                    if(m == 5){
                        viewHolder.ll2.setVisibility(View.VISIBLE);
                        viewHolder.tvItem7.setVisibility(View.VISIBLE);
                        viewHolder.imgItem7.setVisibility(View.VISIBLE);
                        viewHolder.tvItem7.setText(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getName());
                        viewHolder.imgItem7.setImageURI(Uri.parse("http://www.qkxmall.com/"+categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getThumb_image()));
                    }
                    if(m == 6){
                        viewHolder.ll3.setVisibility(View.VISIBLE);
                        viewHolder.tvItem8.setVisibility(View.VISIBLE);
                        viewHolder.imgItem8.setVisibility(View.VISIBLE);
                        viewHolder.tvItem8.setText(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getName());
                        viewHolder.imgItem8.setImageURI(Uri.parse("http://www.qkxmall.com/"+categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getThumb_image()));
                    }
                    if(m == 7){
                        viewHolder.ll4.setVisibility(View.VISIBLE);
                        viewHolder.tvItem9.setVisibility(View.VISIBLE);
                        viewHolder.imgItem9.setVisibility(View.VISIBLE);
                        viewHolder.tvItem9.setText(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getName());
                        viewHolder.imgItem9.setImageURI(Uri.parse("http://www.qkxmall.com/"+categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getThumb_image()));
                    }

                }


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
        LinearLayout ll_1;
        LinearLayout ll_2;
        LinearLayout ll_3;
        LinearLayout ll_4;
        LinearLayout ll1;
        LinearLayout ll2;
        LinearLayout ll3;
        LinearLayout ll4;
        LinearLayout llxia;
        TextView tvName;
        TextView tvItem;
        ImageView imgItem;
//        TextView tvName3;
        TextView tvItem3;
        ImageView imgItem3;
//        TextView tvName4;
        TextView tvItem4;
        ImageView imgItem4;
//        TextView tvName5;
        TextView tvItem5;
        ImageView imgItem5;

        TextView tvItem6;
        ImageView imgItem6;
        //        TextView tvName3;
        TextView tvItem7;
        ImageView imgItem7;
        //        TextView tvName4;
        TextView tvItem8;
        ImageView imgItem8;
        //        TextView tvName5;
        TextView tvItem9;
        ImageView imgItem9;
    }

}