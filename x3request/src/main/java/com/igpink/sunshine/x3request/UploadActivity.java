package com.igpink.sunshine.x3request;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class UploadActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_upload);
        String url = "";
        RequestParams requestParams = new RequestParams(url);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
