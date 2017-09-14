package com.qkxmall.mall.views.cloud.order;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qkxmall.mall.R;

public class AllCloudOrderActivity extends AppCompatActivity {
    Context context;
    public static AllCloudOrderActivity allCloudOrderActivity;
    CloudOrderFragment cloudOrderFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_all_order);
        allCloudOrderActivity = this;
        context = AllCloudOrderActivity.this;

        cloudOrderFragment = new CloudOrderFragment();
        cloudOrderFragment.newInstance(context);
        Bundle bundle = new Bundle();
        bundle.putInt("pageID",getIntent().getIntExtra("pageID",0));
        cloudOrderFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.huiOrder, cloudOrderFragment).commit();
    }
    public  void finishrefresh(){
        cloudOrderFragment.finishrefresh();
    }
}
