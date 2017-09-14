package com.qkxmall.mall.views.user;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.define.override.WebView;

/**
 * Created by Sunshine on 01/31/2016.
 */
public class WebFragment extends Fragment {

    public static final String CLOUD_NEW_HELP = "http://www.qkxmall.com/api/android/yungou.html";
    public static final String HUI_NEW_HELP = "http://www.qkxmall.com/api/android/huigou.html";
    public static final String KE_FU = "http://www.qkxmall.com/api/android/kefu.html";
    public static final String FQA = "http://www.qkxmall.com/api/android/qkx.html";


    Handler handler = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;

    Context context;

    ImageView navigation = null;
    TextView title = null;
    TextView right = null;

    WebView webView = null;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_web_container, container, false);
        init(rootView);
        Button bt1 = (Button) rootView.findViewById(R.id.bt1);
        Button bt2 = (Button) rootView.findViewById(R.id.bt2);
        bt1.setVisibility(View.INVISIBLE);
        bt2.setVisibility(View.INVISIBLE);
        bt1.setOnClickListener(new OnClick());
        bt2.setOnClickListener(new OnClick());
        switch (type) {
            case CLOUD_NEW_HELP: {
                title.setText("云购新手指南");
            }
            break;
            case HUI_NEW_HELP: {
                title.setText("惠购新手指南");
            }
            break;
            case KE_FU: {
                title.setText("客服帮助");
                bt1.setVisibility(View.VISIBLE);
                bt2.setVisibility(View.VISIBLE);
            }
            break;
            case FQA: {
                title.setText("常见问题");
            }
            break;
        }
        webView.loadUrl(type);
        webView.getSettings().setJavaScriptEnabled(true);
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebShowActivity.webShowActivity.finish();
            }
        });
        return rootView;
    }

    private void init(View rootView) {
        navigation = (ImageView) rootView.findViewById(R.id.templet).findViewById(R.id.navigation);
        title = (TextView) rootView.findViewById(R.id.templet).findViewById(R.id.title);
        right = (TextView) rootView.findViewById(R.id.templet).findViewById(R.id.right);
        webView = (WebView) rootView.findViewById(R.id.info);
        right.setVisibility(View.GONE);
    }





    public void setHandler(Handler transectionHandler) {
        this.handler = transectionHandler;
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt1:{
                    if (handler != null){
                        Message message = new Message();
                        message.what = BackgroundTask.CLOUD_A_Q;
                        handler.sendMessage(message);
                    }
                }
                    break;
                case R.id.bt2:{
                    if (handler != null){
                        Message message = new Message();
                        message.what = BackgroundTask.HUI_A_Q;
                        handler.sendMessage(message);
                    }
                }
                    break;
            }
        }
    }
}