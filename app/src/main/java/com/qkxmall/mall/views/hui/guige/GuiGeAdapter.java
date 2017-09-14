package com.qkxmall.mall.views.hui.guige;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.views.MyView.FlowRadioGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 01/13/2016.
 */
public class GuiGeAdapter extends BaseAdapter {
    Context context;
    List<Handler> childPoitionHandler = new ArrayList<>();
    List<HashMap<String, Object>> list = new ArrayList<>();
    List<List<Boolean>> isSelect = new ArrayList<>();
    List<Handler> clickPosition = new ArrayList<>();
    Handler childHandler;
    FlowRadioGroup frg;
    Handler clip;
    A a;

    public GuiGeAdapter(Context context, List<Handler> childPoitionHandler, List<HashMap<String,
            Object>> list, List<List<Boolean>> isSelect, List<Handler> clickPosition) {
        this.context = context;
        this.childPoitionHandler = childPoitionHandler;
        this.list = list;
        this.isSelect = isSelect;
        this.clickPosition = clickPosition;
        System.out.println("LsitView列表数据：" + list.toString());
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

        if (convertView != null) {
            a = (A) convertView.getTag();
        } else {
            a = new A();
            convertView = LayoutInflater.from(context).inflate(R.layout.child_guige_item, null);
            a.title = (TextView) convertView.findViewById(R.id.title);
            a.grid = (GridView) convertView.findViewById(R.id.guige);
//            a.mRadioGroup = (FlowRadioGroup) convertView.findViewById(R.id.flow_radiogroup);
            frg = (FlowRadioGroup) convertView.findViewById(R.id.flow_radiogroup);
//            a.mListView=(ListView)convertView.findViewById(R.id.mylist);
            convertView.setTag(a);
        }
        HashMap<String, Object> data = list.get(position);
        childHandler = childPoitionHandler.get(position);
        a.title.setText((CharSequence) data.get("title"));
        List<HashMap<String, Object>> guigeList = (List<HashMap<String, Object>>) data.get("guige");
         clip = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case BackgroundTask.CLICK:
                        Message message = new Message();
                        message.arg1 = position;
                        message.what = BackgroundTask.CLICK;
                        clickPosition.get(position).sendMessage(message);

                        break;
                }
            }
        };

//       addRadioButton(guigeList, a.mRadioGroup, position);
       GuiGeItemAdapter guiGeItemAdapter = new GuiGeItemAdapter(context,childHandler,guigeList,
 isSelect.get(position),clip,position,list);
       a.grid.setAdapter(guiGeItemAdapter);


        return convertView;
    }

    static class A {
        TextView title;
        FlowRadioGroup mRadioGroup;
        GridView  grid;
//        ListView mListView;
    }

    public void addText(List<HashMap<String, Object>> list, FlowRadioGroup mRadioGroup,View view){
        for(int i = 0; i < list.size(); i++){

        }


    }

    List<Integer> mlist = new ArrayList<>();
    public static int num;
    HashMap<String ,Object> data;
    public void addRadioButton(List<HashMap<String, Object>> list, FlowRadioGroup mRadioGroup,
                               int pos) {

        for (int i = 0; i < mlist.size(); i++) {
            if (mlist.get(i) == mRadioGroup.getTag()) {
                return;
            }
        }

        for (int i = 0; i < list.size(); i++) {
             data = list.get(i);
            num=i;
            System.out.println("动态加载单选按钮" + mRadioGroup.getTag());
            RadioButton tempButton = new RadioButton(context);
            tempButton.setTag(i);
//            tempButton.setButtonDrawable(R.drawable.guige_selector);
//             tempButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            tempButton.setId(i);
            tempButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            tempButton.setBackgroundResource(R.drawable.my_guige_selector);
            tempButton.setPadding(100, 30, 100, 30);                 // 设置文字距离按钮四周的距离
            tempButton.setText(list.get(i).get("item").toString());

            tempButton.setOnClickListener(new View.OnClickListener() {
                RadioButton tempButton2;
                RadioButton tempButton1;


                @Override
                public void onClick(View v) {
                    tempButton1 = (RadioButton) v;
                    tempButton1.getDrawingCacheBackgroundColor();
//                    if (tempButton1.isChecked()) {
//                        tempButton1.setBackgroundResource(R.drawable.my_guige_selector2);
//                    }
//                    if (tempButton2 != null ){
//                        tempButton2.setBackgroundResource(R.drawable.my_guige_selector);
//                    }


//                    isChecked((int)tempButton1.getTag());
//                    tempButton2 = tempButton1;

                    //能正确打印选择规格
//                    if (tempButton.getTag() == 0) {
//                        HuiToBuyNowActivity.isSelected.setText(tempButton.getText() + "");
//                    } else if (tempButton.getTag() == 1) {
//                        HuiToBuyNowActivity.isSelected2.setText(tempButton.getText() + "");
//                    }


                }
            });
            mRadioGroup.addView(tempButton, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout
                    .LayoutParams.WRAP_CONTENT);


            if (i == list.size() - 1) {
                mlist.add(i);
                mRadioGroup.setTag(i);
                ((RadioButton)mRadioGroup.getChildAt(0)).setChecked(true);

            }


        }
    }
}
