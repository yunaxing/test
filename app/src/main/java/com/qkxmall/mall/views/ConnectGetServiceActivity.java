package com.qkxmall.mall.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qkxmall.mall.R;

public class ConnectGetServiceActivity extends AppCompatActivity {
    private ImageView backup = null;
    private Button nn = null;
    private Button ass = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_get_service);
        init();

        backup.setOnClickListener(new ONClickListeners());
    }


    private void init() {
        backup = (ImageView) findViewById(R.id.ss_backup);
        nn = (Button) findViewById(R.id.new_new);
        ass = (Button) findViewById(R.id.aas);
    }

    private class ONClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ss_backup:
                    finish();
                    break;
                case R.id.new_new:
                    break;
                case R.id.aas:
                    break;
            }
        }
    }
}
