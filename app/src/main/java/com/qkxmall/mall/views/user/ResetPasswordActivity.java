package com.qkxmall.mall.views.user;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qkxmall.mall.R;

public class ResetPasswordActivity extends AppCompatActivity {
    private ImageView backup =  null;
    private EditText phone = null;
    private EditText check = null;
    private Button get = null;
    private Button next = null;
    private EditText type = null;
    private EditText retype = null;
    private Button finish = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        init();

        backup.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.reset_backup);
        phone = (EditText) findViewById(R.id.reset_phone);
        check = (EditText) findViewById(R.id.reset_check);
        get = (Button) findViewById(R.id.reset_get);
        next = (Button) findViewById(R.id.reset_next);
        type = (EditText) findViewById(R.id.resetr_type);
        retype = (EditText) findViewById(R.id.reset_retype);
        finish = (Button) findViewById(R.id.reset_finish);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.reset_backup:
                    ResetPasswordActivity.this.finish();
                    break;
                case R.id.reset_next:
                    if (notNull(ResetPasswordActivity.this)) {
                        setContentView(R.layout.activity_reset_password_next);
                        init();
                    }
                    break;
                case R.id.reset_finish:

                    break;
            }
        }
    }

    private boolean notNull(Context context){
        if (phone.getText().toString().trim().equals("")){
            Toast.makeText(context, "请输入手机号码", Toast.LENGTH_SHORT).show();
        }else {
            if (check.getText().toString().trim().equals("")){
                Toast.makeText(context,"请输入短信验证码",Toast.LENGTH_SHORT).show();
            }else {
                return true;
            }
        }
        return false;
    }

}
