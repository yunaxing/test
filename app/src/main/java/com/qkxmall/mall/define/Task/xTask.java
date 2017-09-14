package com.qkxmall.mall.define.Task;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Sunshine on 01/18/2016.
 */
public class xTask {
    public static final int SUCCESS = 0x0000;
    public static final int ERROR = 0x0002;
    public static final int CANCELLED = 0x0003;
    public static final int FINISHED = 0x0001;

    Context context;

    public xTask(Context context) {
        this.context = context;
    }

    Handler handler;
    String url;
    RequestParams requestParams;

    public xTask(Context context, final Handler handler, String url, RequestParams requestParams) {
        this.context = context;
        this.handler = handler;
        this.url = url;
        this.requestParams = requestParams;
    }

    public void doing(){

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Message message = new Message();
                message.what = SUCCESS;
                Bundle bundle = new Bundle();
                bundle.putString("result",result);
                message.setData(bundle);
                handler.sendMessage(message);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Message message = new Message();
                message.what = ERROR;
                Bundle bundle = new Bundle();
                String content = "";
                if(ex!=null && ex.getMessage() != null){//有时候会报空指针
                    content = ex.getMessage().toString();
                }
                bundle.putString("result",content);
                Log.e("upload","---------->error:"+content);
                message.setData(bundle);
                handler.sendMessage(message);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Message message = new Message();
                message.what = CANCELLED;
                Bundle bundle = new Bundle();
                String content = "";
                if(cex!=null && cex.getMessage() != null){//有时候会报空指针
                    content = cex.getMessage().toString();
                }
                bundle.putString("result",content);
                message.setData(bundle);
                handler.sendMessage(message);
            }

            @Override
            public void onFinished() {
                Message message = new Message();
                message.what = FINISHED;
                handler.sendMessage(message);
            }
        });
    }
}
