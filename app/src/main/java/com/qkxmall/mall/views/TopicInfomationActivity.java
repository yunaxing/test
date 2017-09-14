package com.qkxmall.mall.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.views.ScrollListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopicInfomationActivity extends Activity {
    private TextView intentInfo = null;
    private ImageView backup = null;
    private TextView userName = null;
    private ScrollListView scrollListView = null;
    private TextView title  = null;
    private EditText reInfo = null;
    String topicId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_infomation);
        topicId = getIntent().getStringExtra("TopicItemId");
        init();
        title.setText(getIntent().getStringExtra("title"));
        intentInfo.setText(topicId);
        userName.setText(topicId);
        List list = new ArrayList();
        for (int i = 0 ;i<10 ;i++){
            Map<String , Object> map = new HashMap<>();
            map.put("topic_replay_list_item_user_image",R.drawable.topic_user_image_background);
            map.put("topic_replay_list_item_user_name","用户名");
            map.put("topic_replay_list_item_replay_time","2015.08.01"+i);
            map.put("topic_replay_list_item_replay_message","回复消息内容"+i);
            list.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.topic_replay_list_layout
                , new String[]{"topic_replay_list_item_user_image","topic_replay_list_item_user_name","topic_replay_list_item_replay_time","topic_replay_list_item_replay_message"}
                , new int[]{R.id.topic_replay_list_item_user_image,R.id.topic_replay_list_item_user_name,R.id.topic_replay_list_item_replay_time,R.id.topic_replay_list_item_replay_message});
        scrollListView.setAdapter(simpleAdapter);
        scrollListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                reInfo.setHint("回复"+position);
            }
        });
        backup.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        intentInfo = (TextView) findViewById(R.id.topic_information_id);
        backup = (ImageView) findViewById(R.id.topic_info_title_backup);
        userName = (TextView) findViewById(R.id.topic_info_user_name);
        scrollListView = (ScrollListView) findViewById(R.id.topic_replay_list);
        title = (TextView) findViewById(R.id.title);
        reInfo = (EditText) findViewById(R.id.re_info);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.topic_info_title_backup:
                    TopicInfomationActivity.this.finish();
                    break;
                case R.id.topic_info_user_name:
                    userName.setText(intentInfo.getText().toString().trim());
                    break;
            }
        }
    }
}
