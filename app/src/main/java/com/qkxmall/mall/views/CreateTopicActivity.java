package com.qkxmall.mall.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;

public class CreateTopicActivity extends Activity {
    private EditText topicTitle = null;
    private EditText topicMsg = null;
    private TextView addPictureToTopic = null;
    private ImageView sendTopic = null;
    private ImageView backUP = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_topic);
        init();
        addPictureToTopic.setOnClickListener(new OnClickListeners());
        sendTopic.setOnClickListener(new OnClickListeners());
        backUP.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        topicTitle = (EditText) findViewById(R.id.topic_title_edit);
        topicMsg = (EditText) findViewById(R.id.topic_info_edit);
        addPictureToTopic = (TextView) findViewById(R.id.add_picture_to_topic);
        sendTopic = (ImageView) findViewById(R.id.send_topic);
        backUP = (ImageView) findViewById(R.id.topic_create_title_backup);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.add_picture_to_topic:
                    break;
                case R.id.send_topic:
                    if (!topicTitle.getText().toString().trim().equals("") ){
                        if (!topicMsg.getText().toString().trim().equals("")){
                            Toast.makeText(CreateTopicActivity.this,"消息发送成功!",Toast.LENGTH_SHORT).show();
                            CreateTopicActivity.this.finish();
                        }else {
                            Toast.makeText(CreateTopicActivity.this,"请输内容!",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(CreateTopicActivity.this,"请输入标题!",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.topic_create_title_backup:
                    CreateTopicActivity.this.finish();
                    break;
            }
        }
    }
}
