package com.qkxmall.mall.views.hui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.views.ScrollListView;
import com.qkxmall.mall.views.CreateTopicActivity;
import com.qkxmall.mall.views.TopicInfomationActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuiCommunityPageActivity extends Activity {
    private ScrollListView scrollListView = null;
    private ScrollView scrollView = null;
    private TextView sendTopic = null;
    private ImageView backup = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_community_page);
        init();
        sendTopic.setOnClickListener(new OnClickListeners());
        backup.setOnClickListener(new OnClickListeners());
        scrollView.smoothScrollTo(0, 0);

        List list = new ArrayList();
        for (int i = 0 ;i< 20 ;i++){
            Map<String ,Object> map = new HashMap<>();
            map.put("community_cloud_topic_user_image",R.drawable.topic_user_image_background);
            map.put("community_cloud_topic_user_name",""+i);
            map.put("community_cloud_topic_title","消息的标题"+i++);
            map.put("community_cloud_topic_info","消息的内容"+i);
            map.put("community_cloud_topic_item_id",i);
            list.add(map);
        }

        SimpleAdapter simpleAdapterCommunityTopic;
        simpleAdapterCommunityTopic = new SimpleAdapter(this,list, R.layout.community_cloud_topic_list_layout
                ,new String[]{"community_cloud_topic_user_image"
                ,"community_cloud_topic_user_name"
                ,"community_cloud_topic_title"
                ,"community_cloud_topic_info"
                ,"community_cloud_topic_item_id"}
                ,new int[]{R.id.community_cloud_topic_user_image
                , R.id.community_cloud_topic_user_name
                ,R.id.title
                ,R.id.community_cloud_topic_info
                ,R.id.community_cloud_topic_item_id,
        });

        scrollListView.setAdapter(simpleAdapterCommunityTopic);
        scrollListView.setOnItemClickListener(new OnItemClickListeners());
    }

    private void init() {
        scrollListView = (ScrollListView) findViewById(R.id.community_hui_topic);
        scrollView = (ScrollView) findViewById(R.id.community_hui_scroll_view);
        sendTopic = (TextView) findViewById(R.id.community_hui_topic_send_topic);
        backup = (ImageView) findViewById(R.id.community_hui_backup);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.community_hui_topic_send_topic:
                    startActivity(new Intent(HuiCommunityPageActivity.this, CreateTopicActivity.class));
                    break;
                case R.id.community_hui_backup:
                    HuiCommunityPageActivity.this.finish();
                    break;
            }
        }
    }

    private class OnItemClickListeners implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView itemId = (TextView) view.findViewById(R.id.community_cloud_topic_item_id);
            Intent intentToTopicMoreInfo = new Intent(HuiCommunityPageActivity.this,TopicInfomationActivity.class);
            intentToTopicMoreInfo.putExtra("TopicItemId",itemId.getText().toString().trim());
            intentToTopicMoreInfo.putExtra("title","惠购论坛");
            startActivity(intentToTopicMoreInfo);
        }
    }
}
