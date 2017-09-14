package com.qkxmall.mall.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qkxmall.mall.nets.API;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sunshine on 11/10/2015.
 */
public class UserInfoAsyncTask extends AsyncTask<String ,Integer ,String> {
    Context context;
    ProgressDialog progressDialog;
    SimpleDraweeView headImage;
    TextView userName;

    public UserInfoAsyncTask(Context context, ProgressDialog progressDialog, SimpleDraweeView headImage, TextView userName) {
        this.context = context;
        this.progressDialog = progressDialog;
        this.headImage = headImage;
        this.userName = userName;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, API.USER_INFO+"&uid="+params[0], null,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        try {
                            JSONObject jsonObject = new JSONObject(responseInfo.result);
                            userName.setText(jsonObject.getString("username"));
                            headImage.setImageURI(Uri.parse(API.ADD+jsonObject.getString("ico")));
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        //销毁加载进度窗体
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(context, "错误："+s, Toast.LENGTH_SHORT).show();
                        //销毁加载进度窗体
                        progressDialog.dismiss();
                    }
                });
        return null;
    }
}
