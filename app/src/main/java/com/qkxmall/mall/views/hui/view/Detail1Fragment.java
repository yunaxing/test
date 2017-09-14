package com.qkxmall.mall.views.hui.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.override.WebView;

/**
 * ViewHolder simple {@link Fragment} subclass.
 * Use the {@link Detail1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Detail1Fragment extends Fragment {
    private Context context;
    private WebView webview;
    private boolean hasInited = false;

    String html;

    public Detail1Fragment() {
        // Required empty public constructor
    }

    public void newInstance(Context context ,String html) {
        this.context = context;
        this.html = html;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail1, container, false);
        webview = (WebView) rootView.findViewById(R.id.detail);
        initView();
        System.out.println("-----------------------------"+html);

        return rootView;
    }

    //云购详情页

    public void initView() {
        if (null != webview && !hasInited) {
            hasInited = true;
            webview.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setDisplayZoomControls(true);
            webview.getSettings().setSupportZoom(true);
            webview.getSettings().setLoadWithOverviewMode(true);
        }
    }

}
