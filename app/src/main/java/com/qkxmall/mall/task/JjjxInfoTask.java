package com.qkxmall.mall.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ProgressBar;
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
 * Created by Administrator on 2016/1/9.
 * 获取即将开奖信息
 */
public class JjjxInfoTask extends AsyncTask<String ,Integer ,String> {

    Context context;
    ProgressDialog progressDialog;

    TextView    qkxQi;
    SimpleDraweeView qkxPic;
    TextView    qkxDes;
    TextView    qkxPrice;
    TextView    qkxHave;
    TextView    qkxTotal;
    TextView    qkxOther;
    ProgressBar qkxPro;

    public JjjxInfoTask(Context c, ProgressDialog progressDialog, TextView qi, SimpleDraweeView pic, TextView des,
                        TextView price, TextView have, TextView total, TextView other, ProgressBar pro)
    {
        this.context = c;
        this.progressDialog = progressDialog;
        this.qkxQi = qi;
        this.qkxPic = pic;
        this.qkxDes = des;
        this.qkxPrice = price;
        this.qkxHave = have;
        this.qkxTotal = total;
        this.qkxOther = other;
        this.qkxPro = pro;
    }


    @Override
    protected String doInBackground(String... params) {

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, API.JJJX_ONE_URL, null,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        try {
                            JSONObject jsonObject = new JSONObject(responseInfo.result);
                            qkxQi.setText(jsonObject.getJSONObject("data").getString("c_term"));
                            qkxPic.setImageURI(Uri.parse(API.ADD+jsonObject.getJSONObject("data").getString("img")));
                            qkxDes.setText(jsonObject.getJSONObject("data").getString("goodsname"));
                            qkxPrice.setText("￥" + jsonObject.getJSONObject("data").getString("c_totalprice"));
                            qkxHave.setText(jsonObject.getJSONObject("data").getString("curnum"));
                            qkxOther.setText(jsonObject.getJSONObject("data").getString("remain"));
                            qkxTotal.setText(jsonObject.getJSONObject("data").getString("totalnum"));

                            //设置进度
                            int proValue = Integer.parseInt(jsonObject.getJSONObject("data").getString("curnum")) * 100 /
                                    Integer.parseInt(jsonObject.getJSONObject("data").getString("totalnum"));
                            qkxPro.setProgress(proValue);

                            //des 携带ID
                            qkxDes.setTag(jsonObject.getJSONObject("data").getString("cid"));

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
