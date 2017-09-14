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
import com.umeng.socialize.utils.Log;

/**
 * Created by xingyuna on 2017/5/10.
 */

public class WuLiuActivity extends Activity {
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
        setContentView(R.layout.wuliu_activity);
        String gid="";
        String orderid="";
//        tv_title = (TextView) findViewById(R.id.tv_title);
        gid=getIntent().getStringExtra("uid");
        orderid=getIntent().getStringExtra("orderid");

        url="https://www.qkxmall.com/index.php?m=qkx&c=apipage&a=kuaidiapi"+"&uid="+gid+"&orderid="+orderid;
//        url="https://www.baidu.com/";
//        title = getIntent().getStringExtra("title");
//        tv_title.setText(title);
        Log.v("urlurl","url=="+url);
        webview = (WebView) findViewById(R.id.wv);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
        WebSettings settings = webview.getSettings();
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.getSettings().setDomStorageEnabled(true);

        webview.addJavascriptInterface(new WuLiuActivity.JavaScriptInterface(), "jsObj");
        webview.loadUrl(url);
        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                ToastUtils.showToast(context,"onKey keyCode:"+keyCode);
                finish();

                return false;
            }
        });
//        image= (ImageView) findViewById(R.id.arr_del);
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

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

