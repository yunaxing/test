package com.qkxmall.mall.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.adapter.AdsViewPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllProductDetailPageCloudActivity extends Activity {
    private ImageView backup = null;
    private TextView modeWeb = null;
    private TextView modeArgument = null;
    private TextView modeEvaluate = null;
    private ViewPager viewPager = null;
    ProgressDialog progressDialog;

    List<View> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product_detail_page);
        init();
        int pageID = getIntent().getIntExtra("pageID", 3);
        //Initialize view.
        View webViewModeWeb = LayoutInflater.from(this).inflate(R.layout.webview_layout, null);
        View webViewModeArgument = LayoutInflater.from(this).inflate(R.layout.webview_layout, null);
        View viewModeEvaluate = LayoutInflater.from(this).inflate(R.layout.cloud_buy_product_comment_layout,null);
        ListView comment = (ListView) viewModeEvaluate.findViewById(R.id.cloud_buy_comment_list);
        List list1 = new ArrayList();
        for (int i = 0 ;i < 30 ;i++){
            Map<String ,Object> map = new HashMap<>();
            list1.add(map);
        }

        comment.setAdapter(new SimpleAdapter(this,list1,R.layout.cloud_buy_comment_list_item_layout,new String[]{},new int[]{}));

        //Initialize webView
        final WebView webViewModeWebView = (WebView) webViewModeWeb.findViewById(R.id.layout_web_view);
        final WebView webViewModeWebArgument = (WebView) webViewModeArgument.findViewById(R.id.layout_web_view);
        /*
        * Setup webView setting
        * 1.Enable javaScript support.
         * 2.Set load web url.
         *
        * */
        progressDialog = ProgressDialog.show(this,"","请稍候...");
        WebSettings webSettings = webViewModeWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewModeWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
            }
        });
        webViewModeWebView.loadUrl("http://www.baidu.com");
        WebSettings webViewModeWebArgumentSetting = webViewModeWebArgument.getSettings();
        webViewModeWebArgumentSetting.setJavaScriptEnabled(true);
        webViewModeWebArgument.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
            }
        });
        webViewModeWebArgument.loadUrl("http://www.apple.com");

        //When click back ,set webView go back
        webViewModeWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webViewModeWebView.canGoBack()) {  //click back
                        webViewModeWebView.goBack();   //go back
                        return true;    //Be del
                    }
                }
                return false;
            }
        });
        webViewModeWebArgument.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webViewModeWebArgument.canGoBack()) {  //click back
                        webViewModeWebArgument.goBack();   //Go back
                        //webview.goForward();//Go forward
                        return true;    //Be del
                    }
                }
                return false;
            }
        });
        /*
        * 1.Add view to list.
        * 2.Set viewpager adapter.
        * 3.Get intent and turn to pager item.
        * */
        list.add(webViewModeWeb);
        list.add(webViewModeArgument);
        list.add(viewModeEvaluate);
        viewPager.setAdapter(new AdsViewPagerAdapter(list));
        if (pageID != 3){
            viewPager.setCurrentItem(pageID);
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (viewPager.getCurrentItem() == 0){
                    modeWeb.setTextColor(getResources().getColor(R.color.white));
                    modeWeb.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
                    modeArgument.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    modeArgument.setBackgroundColor(getResources().getColor(R.color.white));
                    modeEvaluate.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    modeEvaluate.setBackgroundColor(getResources().getColor(R.color.white));
                }else if (viewPager.getCurrentItem() == 1){
                    modeWeb.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    modeWeb.setBackgroundColor(getResources().getColor(R.color.white));
                    modeArgument.setTextColor(getResources().getColor(R.color.white));
                    modeArgument.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
                    modeEvaluate.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    modeEvaluate.setBackgroundColor(getResources().getColor(R.color.white));
                }else if (viewPager.getCurrentItem() == 2){
                    modeWeb.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    modeWeb.setBackgroundColor(getResources().getColor(R.color.white));
                    modeArgument.setTextColor(getResources().getColor(R.color.grid_view_background_grey_500));
                    modeArgument.setBackgroundColor(getResources().getColor(R.color.white));
                    modeEvaluate.setTextColor(getResources().getColor(R.color.white));
                    modeEvaluate.setBackgroundColor(getResources().getColor(R.color.hui_buy_title_bg_color_red));
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        backup.setOnClickListener(new OnClickListeners());
        modeWeb.setOnClickListener(new OnClickListeners());
        modeArgument.setOnClickListener(new OnClickListeners());
        modeEvaluate.setOnClickListener(new OnClickListeners());
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.product_detail_page_backup);
        modeWeb = (TextView) findViewById(R.id.product_detail_page_pic);
        modeArgument = (TextView) findViewById(R.id.product_detail_page_argument);
        modeEvaluate = (TextView) findViewById(R.id.product_detail_page_evaluate);
        viewPager = (ViewPager) findViewById(R.id.product_detail_page_pager);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.product_detail_page_backup:
                    finish();
                    break;
                case R.id.product_detail_page_pic:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.product_detail_page_argument:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.product_detail_page_evaluate:
                    viewPager.setCurrentItem(2);
                    break;
            }
        }
    }
}
