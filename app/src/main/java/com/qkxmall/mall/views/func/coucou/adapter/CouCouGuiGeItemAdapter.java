package com.qkxmall.mall.views.func.coucou.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.views.hui.guige.GuiGeItemAdapter;
import com.qkxmall.mall.views.hui.view.HuiToBuyNowActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yuna on 2017/8/5.
 */

public class CouCouGuiGeItemAdapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    Handler handler;
    List<HashMap<String ,Object>> list = new ArrayList<>();
    List<Boolean> selectList = new ArrayList<>();
    Handler clickPosition;
    List<View> lsit=new ArrayList<>();
    List<TextView> tvlist=new ArrayList<>();
    List<HashMap<String, Object>> lists = new ArrayList<>();
    public int i;
    public static int x;
    private TextView oldView;
//    public static TextView tv;

    public CouCouGuiGeItemAdapter(Context context, List<HashMap<String, Object>> list, List<Boolean> selectList, Handler clickPosition,int i,List<HashMap<String,
            Object>> lists) {
        this.context = context;
        this.list = list;
        this.selectList = selectList;
        this.clickPosition = clickPosition;

        this.i=i;
        this.lists=lists;
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

    public static String s="0,0";

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        A a;
        if (convertView != null){
            a = (A) convertView.getTag();

        }else {
            a = new A();
            convertView = LayoutInflater.from(context).inflate(R.layout.child_guige_grid_item,null);
            a.item = (TextView) convertView.findViewById(R.id.item);

            convertView.setTag(a);
        }
        HashMap<String ,Object> data = list.get(position);

        String[] arr=s.split(",");
//        System.out.println("!!!!!!!!!!! arr" + arr.toString());
//        if(arr[0].equals(i+"")&&arr[1].equals(position+"")){
//            a.item.setSelected(true);
//        }else{
//            a.item.setSelected(false);
//        }
//        List<Boolean> mlist= HuiToBuyNowActivity.bllist.get(i);
//        if (mlist.get(position)){
        if(position == 0){
            a.item.setSelected(true);
            oldView = a.item;
        }else{
            a.item.setSelected(false);
        }


        a.item.setText((CharSequence) data.get("item"));
        tvlist.add(a.item);

        a.item.setTag(position);
//        a.item.setTag(1,i);
//        a.item.setTag(2,position);
//            if(a.item.getText().toString().equals("暂无")){
//                a.item.setVisibility(View.VISIBLE);
//            }
        if (a.item.getText().toString().equals("暂无")){
//            a.item.setVisibility(View.INVISIBLE);
        }else {
//            a.item.setVisibility(View.VISIBLE);
            a.item.setOnClickListener(this);
        }
//        if (i==0&&position==0){
////                 a.item.performClick();
//            System.out.println("!!!!!!!!!!!!!!!!!!!!! 第一个按钮 ");
//            tv=a.item;
//        }
//        if (position==list.size()-1){
//            System.out.println("!!!!!!!!!!!!!!!!!!!!! 第一个按钮点击 " + tv.getText());
////                  tv.performClick();
//        }

        lsit.add(a.item);
        System.out.println("！！！！！！按钮集合的长度："+list.size());
//        if(position==0&&bl){
//            a.item.performClick();
//        }

        return convertView;
    }
    public static boolean bl=true;
    String string="";
    @Override
    public void onClick(View v) {
        Toast.makeText(context, "点击", Toast.LENGTH_SHORT).show();
        ((TextView)v).setSelected(true);
        oldView.setSelected(false);
        oldView = (TextView)v;
    }

    static class A{
        TextView item;

    }

}
