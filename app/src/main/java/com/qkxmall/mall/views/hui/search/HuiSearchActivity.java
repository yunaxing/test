package com.qkxmall.mall.views.hui.search;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qkxmall.mall.R;

public class HuiSearchActivity extends AppCompatActivity {
    Context context;
    public static HuiSearchActivity huiSearchActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hui_search_root);
        huiSearchActivity = this;
        context = HuiSearchActivity.this;
        HuiSearchFragment huiSearchFragment = new HuiSearchFragment();
        huiSearchFragment.newInstance(context);
        getSupportFragmentManager().beginTransaction().replace(R.id.huiSearchRoot,huiSearchFragment).commit();
    }

}
