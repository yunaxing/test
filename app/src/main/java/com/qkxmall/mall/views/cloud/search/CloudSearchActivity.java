package com.qkxmall.mall.views.cloud.search;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qkxmall.mall.R;

public class CloudSearchActivity extends AppCompatActivity {
    Context context;
    public static CloudSearchActivity cloudSearchActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_search);
        cloudSearchActivity = this;
        context = CloudSearchActivity.this;

        CloudSearchFragment cloudSearchFragment = new CloudSearchFragment();
        cloudSearchFragment.newInstance(context);
        getSupportFragmentManager().beginTransaction().replace(R.id.cloudSearchRoot,cloudSearchFragment).commit();

    }


}
