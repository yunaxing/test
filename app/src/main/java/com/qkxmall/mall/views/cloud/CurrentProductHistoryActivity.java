package com.qkxmall.mall.views.cloud;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.adapter.AdsViewPagerAdapter;
import com.qkxmall.mall.define.adapter.DefineRecyclerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrentProductHistoryActivity extends Activity {
    private ImageView backup = null;
    private ViewPager viewPager = null;
    private RecyclerView recyclerView = null;
    List list = new ArrayList();
    List<View> viewList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_product_history);
        init();
        String[] listFrom = new String[]{"current_history_list_item_num"
                ,"current_history_list_item_name"
                ,"current_history_list_item_time"
                ,"current_history_list_item_get_num"};
        int[] listTo = new int[]{R.id.current_history_list_item_num
                ,R.id.current_history_list_item_name
                ,R.id.current_history_list_item_time
                ,R.id.current_history_list_item_get_num};
        for (int i = 30 ; i >=1 ;i--){
            SimpleDateFormat format = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss");
            Date currentDate = new Date(System.currentTimeMillis());
            Map<String ,Object> map = new HashMap<>();
            map.put("current_history_list_item_num",i);
            map.put("current_history_list_item_name","小叮当"+(i+11));
            map.put("current_history_list_item_time",format.format(currentDate)+".121");
            map.put("current_history_list_item_get_num",i+11);
            list.add(map);
        }
        View currentHistory = LayoutInflater.from(this).inflate(R.layout.current_periods_history_layout,null);
        ListView listView = (ListView) currentHistory.findViewById(R.id.current_product_history_list);
        SimpleAdapter listAdapter = new SimpleAdapter(this,list,R.layout.current_product_history_list_layout,listFrom,listTo);
        listView.setAdapter(listAdapter);
        viewList.add(currentHistory);
        //old
        View oldHistory1 = LayoutInflater.from(this).inflate(R.layout.old_periods_history_layout,null);
        View oldHistory2 = LayoutInflater.from(this).inflate(R.layout.old_periods_history_layout,null);
        View oldHistory3 = LayoutInflater.from(this).inflate(R.layout.old_periods_history_layout,null);
        View oldHistory4 = LayoutInflater.from(this).inflate(R.layout.old_periods_history_layout,null);
        ListView listView1 = (ListView) oldHistory1.findViewById(R.id.old);
        ListView listView2 = (ListView) oldHistory1.findViewById(R.id.old);
        ListView listView3 = (ListView) oldHistory1.findViewById(R.id.old);
        ListView listView4 = (ListView) oldHistory1.findViewById(R.id.old);
        listView1.setAdapter(listAdapter);
        listView2.setAdapter(listAdapter);
        listView3.setAdapter(listAdapter);
        listView4.setAdapter(listAdapter);

        viewList.add(oldHistory1);
        viewList.add(oldHistory2);
        viewList.add(oldHistory3);
        viewList.add(oldHistory4);
        List<HashMap<String , Object>> list = new ArrayList<>();
        for (int i = 30 ; i>=1 ;i--){
            HashMap<String , Object> map = new HashMap<>();
            map.put("number",i+"");
            list.add(map);
        }
        viewPager.setAdapter(new AdsViewPagerAdapter(viewList));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new DefineRecyclerAdapter(CurrentProductHistoryActivity.this, list, viewPager));


        backup.setOnClickListener(new OnClickListeners());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }

        return super.onTouchEvent(event);
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.current_product_history_backup);
        viewPager = (ViewPager) findViewById(R.id.current_product_history_viewpager);
        recyclerView = (RecyclerView) findViewById(R.id.title_);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.current_product_history_backup:
                    finish();
                    break;
            }
        }
    }
}
