package com.qkxmall.mall.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qkxmall.mall.R;

public class CreateReportActivity extends Activity {
    private ImageView backup = null;
    private Button submit = null;
    private EditText reportUserName = null;
    private EditText reportUserSchools = null;
    private EditText reportUserPhone = null;
    private EditText reportUserQQ = null;
    private EditText reportInfo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);
        init();
        backup.setOnClickListener(new OnClickListeners());
        submit.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.report_create_backup);
        submit = (Button) findViewById(R.id.report_create_submit);
        reportUserName = (EditText) findViewById(R.id.report_create_username);
        reportUserSchools = (EditText) findViewById(R.id.report_create_schools);
        reportUserPhone = (EditText) findViewById(R.id.report_create_phone);
        reportUserQQ = (EditText) findViewById(R.id.report_create_qq);
        reportInfo = (EditText) findViewById(R.id.report_create_info);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.report_create_backup:
                    CreateReportActivity.this.finish();
                    break;
                case R.id.report_create_submit:
                    if (!reportUserName.getText().toString().trim().equals("")){
                        if (!reportUserSchools.getText().toString().trim().equals("")){
                            if (!reportInfo.getText().toString().trim().equals("")){
                                CreateReportActivity.this.finish();
                                Toast.makeText(CreateReportActivity.this, "建议提交成功!", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(CreateReportActivity.this,"请输入建议内容!",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(CreateReportActivity.this,"请输入学校名称!",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(CreateReportActivity.this,"请输入姓名!",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
