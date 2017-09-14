package com.qkxmall.mall.views.reg;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qkxmall.mall.R;

public class RegisterActivity extends AppCompatActivity {
    Context context;
    public static RegisterActivity registerActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = RegisterActivity.this;
        registerActivity = this;
        RegisterFragment registerFragment = new RegisterFragment();
        registerFragment.newInstance(context);
        getSupportFragmentManager().beginTransaction().replace(R.id.registerRoot,registerFragment).commit();

    }

}
