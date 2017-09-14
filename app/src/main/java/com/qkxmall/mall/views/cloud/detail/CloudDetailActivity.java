package com.qkxmall.mall.views.cloud.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qkxmall.mall.R;

public class CloudDetailActivity extends AppCompatActivity {

    public static CloudDetailActivity cloudDetailActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_cloud_detail);
        cloudDetailActivity = this;
        String cid = getIntent().getStringExtra("cid");
        CloudDragFragment cloudDragFragment = new CloudDragFragment();
        cloudDragFragment.newInstance(CloudDetailActivity.this);
        Bundle bundle = new Bundle();
        bundle.putString("cid",cid);
        cloudDragFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.cloud,cloudDragFragment).commit();
    }

}
