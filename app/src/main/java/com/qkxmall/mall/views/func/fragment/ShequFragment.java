package com.qkxmall.mall.views.func.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.adapter.PagerAdapter;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.views.ConnectGetServiceActivity;
import com.qkxmall.mall.views.cloud.AllCloudClassItem;
import com.qkxmall.mall.views.cloud.detail.CloudDetailActivity;
import com.qkxmall.mall.views.cloud.search.CloudSearchActivity;
import com.qkxmall.mall.views.fragment.CloudMainFragment;
import com.qkxmall.mall.views.fragment.adapter.CloudHotAdapter;
import com.qkxmall.mall.views.fragment.adapter.RecommendItemAdapter;
import com.qkxmall.mall.views.fragment.adapter.WillOpenAdapter;
import com.qkxmall.mall.views.hui.container.AllComments;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xingyuna on 2017/5/9.
 */

public class ShequFragment extends Fragment {

    //    private Timer timer;
    private static View rootView;
    private String url,title;
    private WebView webview;
    public ShequFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_luntan, container, false);
        }

        findViews(rootView);


        return rootView;
    }


    public void findViews(View rootView) {

        String gid="";

//        url="https://www.baidu.com/";
//        title = getIntent().getStringExtra("title");
//        tv_title.setText(title);
        url="http://bbs.qkxmall.com/";
        webview = (WebView) rootView.findViewById(R.id.wv);
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

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

}