package com.qkxmall.mall.views;

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
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;


/**
 * Created by xingyuna on 2017/4/1.
 */

public class CommonWebviewActivity extends Activity {
    private TextView tv_title;
    private String url,title;
    private WebView webview;
    private Context context;

    /****************************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        findViews();
        Toast.makeText(this,"请下载有云购apk",Toast.LENGTH_LONG);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


    public void findViews() {
        setContentView(R.layout.webview);
//        tv_title = (TextView) findViewById(R.id.tv_title);
        url = "https://www.qkxmall.com/xiazai/";
//        url="https://www.baidu.com/";
//        title = getIntent().getStringExtra("title");
//        tv_title.setText(title);
        webview = (WebView) findViewById(R.id.wv);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
        WebSettings settings = webview.getSettings();
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.getSettings().setDomStorageEnabled(true);

        webview.addJavascriptInterface(new JavaScriptInterface(), "jsObj");
        webview.loadUrl(url);
        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                ToastUtils.showToast(context,"onKey keyCode:"+keyCode);
                finish();

                return false;
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

        if(keyCode== KeyEvent.KEYCODE_BACK){
//            ToastUtils.showToast(context,"1");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
