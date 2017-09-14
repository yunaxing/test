package com.qkxmall.mall.views.func.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.beans.Category;
import com.qkxmall.mall.beans.ChildItem;
import com.qkxmall.mall.views.hui.HuiClassItemsActivity;


import java.util.ArrayList;

import static com.alipay.sdk.protocol.h.i;

/**
 * Created by yuna on 2017/9/14.
 */

public class gridviewadapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
//    private ArrayList<ChildItem> mList;
    private ArrayList<Category> categorys;

    public gridviewadapter(Context context,ArrayList<Category> categorys) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
//        this.mList = list;
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
    public Object getItem(int i) {
        return null;
    }

//    @Override
//    public Object getItem(int position) {
//        return mList.get(position);
//    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.item_all_buy_menu_gridview2, null);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.cls_2);
            viewHolder.gridView = (GridView)convertView.findViewById(R.id.grid);
            viewHolder.tvAll = (TextView)convertView.findViewById(R.id.tv_all);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Log.v("getcateposition","posiyion=="+position);
        viewHolder.tvName.setText(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getName());


//            for(int j=0;j<getCount(); j++){
        final ArrayList<ChildItem> childItems = new ArrayList<>();
        for(int m= 0; m<categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().size(); m++){
//            if(m == 0){
//                viewHolder.tvItem.setVisibility(View.VISIBLE);
//                viewHolder.ll_1.setVisibility(View.VISIBLE);
//                viewHolder.imgItem.setVisibility(View.VISIBLE);
//                viewHolder.tvItem.setText(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(0).getName());
//                viewHolder.imgItem.setImageURI(Uri.parse("http://www.qkxmall.com/"+categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(0).getThumb_image()));
//            }
           ChildItem childItem = new ChildItem();
            childItem.setName(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getName());
            childItem.setThumb_image("http://www.qkxmall.com/"+categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getThumb_image());
            childItem.setId(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getChilditems().get(m).getId());
            childItems.add(childItem);

        }
        final gridviewadapter2 gridviewadapter2 = new gridviewadapter2(context,childItems);
        viewHolder.gridView.setAdapter(gridviewadapter2);
        int col = 4;
        int totalHeight = 0;
        for (int i = 0; i < gridviewadapter2.getCount(); i += col) {
            View listItem = gridviewadapter2.getView(i, null, viewHolder.gridView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = viewHolder.gridView.getLayoutParams();
        params.height = totalHeight;
        viewHolder.gridView.setLayoutParams(params);
        viewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context,HuiClassItemsActivity.class);
                intent.putExtra("categoryTitle",(childItems.get(i).getName()));
                intent.putExtra("category_id",(childItems.get(i).getId()));
                context.startActivity(intent);
            }
        });
        viewHolder.tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,HuiClassItemsActivity.class);
                intent.putExtra("categoryTitle",(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getName()));
                intent.putExtra("category_id",(categorys.get(getCatePosition(position)).getChidCategorys().get(getCateItemPosition(position)).getId()));
                context.startActivity(intent);
            }
        });
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
        GridView gridView;
        TextView tvAll;

    }

}