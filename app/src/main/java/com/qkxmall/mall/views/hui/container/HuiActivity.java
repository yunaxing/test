package com.qkxmall.mall.views.hui.container;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qkxmall.mall.R;
import com.qkxmall.mall.provider.DataOper;

public class HuiActivity extends AppCompatActivity {
    private Context context;
    public static HuiActivity huiActivity;
    private String categoryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_hui);
        context = HuiActivity.this;
        huiActivity = this;
        HuiDragFragment huiDragFragment = new HuiDragFragment();
        huiDragFragment.newInstance(context);
        Bundle bundle = new Bundle();
        bundle.putString("gid",getIntent().getStringExtra("gid"));
        categoryId = getIntent().getStringExtra("categoryid");
        DataOper.operateTable(getContentResolver(),categoryId);
        huiDragFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.hui, huiDragFragment).commit();
    }

}
