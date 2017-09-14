package com.qkxmall.mall.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkxmall.mall.R;

public class ReportPageActivity extends Activity {
    private ImageView backup = null;
    private TextView createReport = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_page);
        init();
        backup.setOnClickListener(new OnClickListeners());
        createReport.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.community_report_backup);
        createReport = (TextView) findViewById(R.id.community_report_topic_send_report);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.community_report_backup:
                    ReportPageActivity.this.finish();
                    break;
                case R.id.community_report_topic_send_report:
                    startActivity(new Intent(ReportPageActivity.this,CreateReportActivity.class));
                    break;
            }
        }
    }
}
