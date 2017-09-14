package com.qkxmall.mall.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;

/**
 * Created by xingyuna on 2017/4/17.
 */

public class PopuwindowActivity extends Activity {
    private TextView tv_title;
    private String url,title;
    private WebView webview;
    private Context context;

    /****************************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.popudialog);
        tv_title = (TextView)this.findViewById(R.id.textView3);
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), CommonWebviewActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        ToastUtils.showToast(context,"onKeyDown keyCode:"+keyCode);

        if(keyCode== KeyEvent.KEYCODE_BACK){
//            ToastUtils.showToast(context,"1");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
