package com.qkxmall.mall.views.func.webview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkxmall.mall.R;

/**
 * Created by xingyuna on 2017/5/9.
 */

public class NewGuidActivity extends Activity {
    private TextView tv_title;
    private String url;
    private WebView webview;
    private Context context;
    ImageView image;//返回按钮

    /****************************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        findViews();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


    public void findViews() {
        setContentView(R.layout.fragment_shequ);

            url="http://www.qkxmall.com/api/android/huigou.html";

//        url="https://www.baidu.com/";
//        title = getIntent().getStringExtra("title");
//        tv_title.setText(title);
        webview = (WebView) findViewById(R.id.wv);
        TextView title = (TextView) findViewById(R.id.templet).findViewById(R.id.title);
        title.setText("惠购新手指南");
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
        WebSettings settings = webview.getSettings();
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.getSettings().setDomStorageEnabled(true);

        webview.addJavascriptInterface(new NewGuidActivity.JavaScriptInterface(), "jsObj");
        webview.loadUrl(url);
        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                ToastUtils.showToast(context,"onKey keyCode:"+keyCode);
                finish();

                return false;
            }
        });
        image= (ImageView) findViewById(R.id.navigation);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    class JavaScriptInterface {
        JavaScriptInterface() {
        }
        @JavascriptInterface
        public void back() {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        ToastUtils.showToast(context,"onKeyDown keyCode:"+keyCode);

        if(keyCode==KeyEvent.KEYCODE_BACK){
//            ToastUtils.showToast(context,"1");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}

