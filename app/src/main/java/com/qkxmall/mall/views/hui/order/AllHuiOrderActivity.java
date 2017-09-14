package com.qkxmall.mall.views.hui.order;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qkxmall.mall.R;

public class AllHuiOrderActivity extends AppCompatActivity {
    Context context;
    AllOrderActivityFragment allOrderActivityFragment;
    public static AllHuiOrderActivity allHuiOrderActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_all_order);
        allHuiOrderActivity = this;
        context = AllHuiOrderActivity.this;
        allOrderActivityFragment = new AllOrderActivityFragment();
        allOrderActivityFragment.newInstance(context);
        Bundle bundle = new Bundle();
        bundle.putInt("pageID",getIntent().getIntExtra("pageID",0));
        allOrderActivityFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.huiOrder,allOrderActivityFragment).commit();
    }
    //自己添加代码
    public  void finishrefresh(){
        allOrderActivityFragment.finishrefresh();
    }

}
