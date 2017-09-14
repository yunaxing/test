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
 * Created by xingyuna on 2017/5/13.
 */

public class ShequActivity extends Activity {
    private TextView tv_title;
    private String url,title;
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
        setContentView(R.layout.fragment_luntan);
        url="http://bbs.qkxmall.com/";
        webview = (WebView) findViewById(R.id.wv);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
        WebSettings settings = webview.getSettings();
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.getSettings().setDomStorageEnabled(true);

//        webview.addJavascriptInterface(new getActivity().JavaScriptInterface(), "jsObj");
        webview.loadUrl(url);


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
