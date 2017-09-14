package com.qkxmall.mall.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.views.ScrollListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyLuckNumberActivity extends AppCompatActivity {
    private ImageView backup = null;
    private TextView periods = null;
    private TextView hadJoin = null;
    private TextView isJoin = null;
    private ImageView userPic = null;
    private ScrollListView luckList = null;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_my_luck_number);
        init();
        context = MyLuckNumberActivity.this;

        periods.setText("90");
        hadJoin.setText("9690");
        isJoin.setText("36");
        userPic.setImageResource(R.drawable.logo_alipay);

        List<HashMap<String , Object>> list = new ArrayList<>();
        for (int i = 0 ;i < 10 ;i++){
            HashMap<String , Object> map = new HashMap<>();
            map.put("luckNumber","1000000"+i);
            map.put("time","29492dhwi29r"+i);
            list.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,
                R.layout.list_luck_history_layut,
                new String[]{"luckNumber","time"},
                new int[]{R.id.luck_number,R.id.time});
        luckList.setAdapter(simpleAdapter);

        backup.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.backup);
        periods = (TextView) findViewById(R.id.periods);
        hadJoin = (TextView) findViewById(R.id.had_join);
        isJoin = (TextView) findViewById(R.id.is_join);
        userPic = (ImageView) findViewById(R.id.user_pic);
        luckList = (ScrollListView) findViewById(R.id.luck_history);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.backup:
                    MyLuckNumberActivity.this.finish();
                    break;
            }
        }
    }
}
