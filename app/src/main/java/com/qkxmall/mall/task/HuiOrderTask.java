package com.qkxmall.mall.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qkxmall.mall.nets.API;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Sunshine on 11/10/2015.
 */
public class HuiOrderTask extends AsyncTask<String ,Integer ,String> {
    Context context;
    ProgressDialog progressDialog;

    @Override
    protected String doInBackground(String... params) {
        RequestParams requestParams = new RequestParams();
        NameValuePair pairUID = new BasicNameValuePair("uid",params[0]);
        NameValuePair pairStatus = new BasicNameValuePair("type",params[1]);
        requestParams.addBodyParameter(pairUID);
        requestParams.addBodyParameter(pairStatus);
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, API.HUI_ORDER_LIST, requestParams,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        try {
                            JSONArray jsonArray = new JSONArray(responseInfo.result);
                            for (int i = 0 ; i<jsonArray.length() ;i++){
                                HashMap<String ,Object> orderMap = new HashMap<String, Object>();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                orderMap.put("orderSN",jsonObject.getString("order_sn"));
                                orderMap.put("id",jsonObject.getString("id"));
                                orderMap.put("payable_freight",jsonObject.getString("payable_freight"));
                                JSONArray goodsArray = jsonObject.getJSONArray("goods_info");
                                for (int m = 0 ; i<goodsArray.length() ; m++){
                                    JSONObject object = goodsArray.getJSONObject(m);
                                    HashMap<String ,Object> goodsMap = new HashMap<String, Object>();
                                    goodsMap.put("name",object.getString("name"));
                                    goodsMap.put("picture",object.getString("thumb"));
                                    goodsMap.put("c_price",object.getString("shop_price"));
                                    goodsMap.put("bean",object.getString("integral"));
                                    goodsMap.put("buyNumber",object.getString("shop_number"));
                                    orderMap.put("goodsList",goodsMap);
                                }


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        progressDialog.dismiss();
                    }
                });

        return null;
    }
}
