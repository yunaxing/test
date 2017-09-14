package com.qkxmall.mall.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qkxmall.mall.R;

public class CreateSuggestActivity extends Activity {
    private ImageView backup = null;
    private Button submit = null;
    private EditText suggestUserName = null;
    private EditText suggestUserSchools = null;
    private EditText suggestUserPhone = null;
    private EditText suggestUserQQ = null;
    private EditText suggestInfo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_suggest);
        init();

        backup.setOnClickListener(new OnClickListeners());
        submit.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.suggest_create_backup);
        submit = (Button) findViewById(R.id.suggest_create_submit);
        suggestUserName = (EditText) findViewById(R.id.suggest_create_username);
        suggestUserSchools = (EditText) findViewById(R.id.suggest_create_schools);
        suggestUserPhone = (EditText) findViewById(R.id.suggest_create_phone);
        suggestUserQQ = (EditText) findViewById(R.id.suggest_create_qq);
        suggestInfo = (EditText) findViewById(R.id.suggest_create_info);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.suggest_create_backup:
                    CreateSuggestActivity.this.finish();
                    break;
                case R.id.suggest_create_submit:
                    if (!suggestUserName.getText().toString().trim().equals("")){
                        if (!suggestUserSchools.getText().toString().trim().equals("")){
                            if (!suggestInfo.getText().toString().trim().equals("")){
                                CreateSuggestActivity.this.finish();
                                Toast.makeText(CreateSuggestActivity.this,"建议提交成功!",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(CreateSuggestActivity.this,"请输入建议内容!",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(CreateSuggestActivity.this,"请输入学校名称!",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(CreateSuggestActivity.this,"请输入姓名!",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
