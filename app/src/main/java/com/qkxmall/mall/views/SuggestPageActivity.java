package com.qkxmall.mall.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkxmall.mall.R;

public class SuggestPageActivity extends Activity {
    private ImageView backup = null;
    private TextView createSuggest = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_page);
        init();
        backup.setOnClickListener(new OnClickListeners());
        createSuggest.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.community_suggest_backup);
        createSuggest = (TextView) findViewById(R.id.community_suggest_topic_send_suggest);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.community_suggest_backup:
                    SuggestPageActivity.this.finish();
                    break;
                case R.id.community_suggest_topic_send_suggest:
                    startActivity(new Intent(SuggestPageActivity.this,CreateSuggestActivity.class));
                    break;
            }
        }
    }
}
