package com.qkxmall.mall.views.MyView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qkxmall.mall.R;

/**
 * Created by Administrator on 2016/5/18.
 */
public class MyLucky extends Activity {
      private RelativeLayout relativeLayout;//已揭晓
    private ImageView imageView;//返回键
      private TextView textView;//幸运号
      private ListView listView;
      private String s1;//获奖者幸运号
      private String s2;//数据源
      private  String tiem;
      private String[] arr;//list数据源



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_lucky);
        init();


    }
    public void init(){
        relativeLayout= (RelativeLayout) findViewById(R.id.lucky_rl);
        textView= (TextView) findViewById(R.id.lucky_text);
        listView= (ListView) findViewById(R.id.lucky_lv);
        tiem=getIntent().getStringExtra("time");//下单时间
        s2=getIntent().getStringExtra("lucky");
        arr=s2.split(",");
        if (getIntent().getBooleanExtra("jiexiao",false)){//已揭晓
            relativeLayout.setVisibility(View.VISIBLE);
            s1=getIntent().getStringExtra("luckycode");
            textView.setText(s1);

        }
        listView.setAdapter(new LuvkyAdapter());

        imageView= (ImageView) findViewById(R.id.backup);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    public class LuvkyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return arr.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(MyLucky.this,R.layout.lucky_adapter,null);
            TextView tv1= (TextView) view.findViewById(R.id.tv1);
            TextView tv2= (TextView) view.findViewById(R.id.tv2);
            tv1.setText(tiem);
            tv2.setText(arr[position]);
            
            if (tv2.getText().toString().equals(s1)){
                tv2.setTextColor(Color.RED);
            }
            return view;
        }
    }

}
