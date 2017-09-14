package com.qkxmall.mall.views.user;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.override.WebView;

public class AccountHapyBeanGetWayActivity extends Activity {
    private ImageView backup = null;
    private WebView info = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_hapy_bean_get_way);
        init();
        info.loadUrl("http://www.qkxmall.com/api/android/");
        info.getSettings().setJavaScriptEnabled(true);
//        info.getSettings().setDisplayZoomControls(true);
//        info.getSettings().setSupportZoom(true);
//        info.getSettings().setUseWideViewPort(true);
//        info.getSettings().setLoadWithOverviewMode(true);
//        info.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        info.getSettings().setLoadWithOverviewMode(true);
        backup.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.account_happy_bean_backup);
        info = (WebView) findViewById(R.id.info);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.account_happy_bean_backup:
                    AccountHapyBeanGetWayActivity.this.finish();
                    break;
            }
        }
    }
}
