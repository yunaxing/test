package com.qkxmall.mall.define.Task;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.multipart.HttpMultipartMode;
import com.lidroid.xutils.http.client.multipart.MultipartEntity;
import com.lidroid.xutils.http.client.multipart.content.ByteArrayBody;
import com.lidroid.xutils.http.client.multipart.content.ContentBody;
import com.lidroid.xutils.http.client.multipart.content.StringBody;

import org.apache.http.HttpEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by Sunshine on 11/19/2015.
 */
public class BackgroundTask {
    public static final int SUCCESS = 0x01;
    public static final int FAILURE = 0x02;
    public static final int START = 0x03;
    public static final int FINISH = 0x04;
    public static final int ORDER_CANCEL_SUCCESS = 0x05;
    public static final int ORDER_CANCEL_FAILURE = 0x06;
    public static final int ORDER_PAY_SUCCESS = 0x07;
    public static final int ORDER_PAY_FAILURE = 0x08;
    public static final int CHECKED = 0x09;
    public static final int UN_CHECKED = 0x0a;
    public static final int CLICK = 0x0b;
    public static final int LONG_CLICK = 0x0c;
    public static final int IS_SHOW = 0x0d;
    public static final int CLOUD_A_Q = 0x0e;
    public static final int HUI_A_Q = 0x0f;
    public static final int HUI_Q_A = 0x010;


    /**设置连接超时的时间*/
    private int connectionTimeout = 10000;
    /**设置读取超时的时间*/
    private int soTimeout = 20000;
    Context context;
    String address;
    Handler handler;
    int     type = 0;       //携带的数据，成功后返回。暂时定义为int，以后扩展可以用object


    public BackgroundTask(Context context, String address, Handler handler) {
        this.context = context;
        this.address = address;
        this.handler = handler;
    }

    public BackgroundTask(Context context, String address, Handler handler, int type) {
        this.context = context;
        this.address = address;
        this.handler = handler;
        this.type    = type;
    }


    //数据请求
    public void doInBackground() {

        final Message message = new Message();
        message.arg1 = this.type;
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configCurrentHttpCacheExpiry(1000 * 0);
        // 设置超时时间
        httpUtils.configTimeout(10 * 1000);
        httpUtils.configSoTimeout(10 * 1000);
        httpUtils.configCurrentHttpCacheExpiry(0);// 设置缓存0秒,5秒内直接返回上次成功请求的结果。
        RequestParams requestParams = new RequestParams();
        httpUtils.send(HttpRequest.HttpMethod.GET, address, null,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {

                        message.what = SUCCESS;
                        Bundle bundle = new Bundle();
                        bundle.putString("result", responseInfo.result);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        message.what = FAILURE;
                        Bundle bundle = new Bundle();
                        bundle.putString("result", msg);
                        message.setData(bundle);
                        handler.sendMessage(message);
                        Log.e("RequestError:",msg);
                    }
                }
        );

    }

    //带参数的
    public void doInBackground(HashMap<String , String> map , int type)
    {
        final Message message = new Message();
        message.arg1 = type;
        message.arg1 = this.type;
        HttpUtils httpUtils = new HttpUtils();
        RequestParams requestParams = new RequestParams();

        Iterator i = map.entrySet().iterator();

        while (i.hasNext()) {
            Map.Entry entry = (Map.Entry)i.next();

            requestParams.addBodyParameter((String) entry.getKey() , (String) entry.getValue());
        }


        httpUtils.send(HttpRequest.HttpMethod.POST, address, requestParams,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        message.what = SUCCESS;
                        Bundle bundle = new Bundle();
                        bundle.putString("result", responseInfo.result);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        message.what = FAILURE;
                        Bundle bundle = new Bundle();
                        bundle.putString("result", msg);
                        message.setData(bundle);
                        handler.sendMessage(message);
                        Log.e("RequestError:",msg);
                    }
                }
        );
    }

    //多文件上传
    public void doInBackground(List<HashMap<String, Object>> streams) {
        final Message message = new Message();
        HttpUtils httpUtils = new HttpUtils();
        RequestParams requestParams = new RequestParams();
        for (int i = 0 ; i< streams.size() ; i++){
            HashMap<String ,Object> map = streams.get(i);
            requestParams.addBodyParameter((String) map.get("key"),(File)map.get("file"),"application/octet-stream");
        }
        httpUtils.send(HttpRequest.HttpMethod.POST, address, requestParams,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        message.what = SUCCESS;
                        Bundle bundle = new Bundle();
                        bundle.putString("result", responseInfo.result);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        message.what = FAILURE;
                        Bundle bundle = new Bundle();
                        bundle.putString("result", msg);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }
        );
    }
    //
    public void doInBackground(String key, File file) throws FileNotFoundException {
        final Message message = new Message();
        HttpUtils httpUtils = new HttpUtils();
        RequestParams requestParams = new RequestParams();
        requestParams.addBodyParameter(key, new FileInputStream(file), file.length(), file.getName(), "application/octet-stream");
        httpUtils.send(HttpRequest.HttpMethod.GET, address, requestParams,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        message.what = SUCCESS;
                        Bundle bundle = new Bundle();
                        bundle.putString("result", responseInfo.result);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        message.what = FAILURE;
                        Bundle bundle = new Bundle();
                        bundle.putString("result", msg);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }
        );

    }

    //
    public void doInBackground(String url ,HashMap<String ,Object> params){
        final Message message = new Message();
        RequestParams requestParams = new RequestParams();
        Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
        HttpUtils httpUtils = new HttpUtils();
        MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.STRICT);
        HttpEntity httpEntity = null;

        while (it.hasNext()){
            Map.Entry<String, Object> entry=(Map.Entry<String, Object>)it.next();
            String key =(String) entry.getKey();
            Object value=entry.getValue();
            //上传文件keyValue
            if(key.toLowerCase().contains("headimg") || key.toLowerCase().contains("file")
                    || key.toLowerCase().equalsIgnoreCase("itineraryImg")
                    || key.toLowerCase().equalsIgnoreCase("divelogImg")
                    ){
//				if(key.toLowerCase().endsWith("file")){
                byte[] data = (byte[]) params.get(key);
                String fileName = System.currentTimeMillis()+".jpg";
                ContentBody c1 = new ByteArrayBody(data, fileName);
                multipartEntity.addPart(key, c1);
            }else{
                try {
                    if(value != null){
                        //Http POST
//								value = Base64.encode(value.toString().getBytes(),value.toString().length());
                        StringBody jsonBody = new StringBody((String) value, Charset.forName("utf-8"));
                        multipartEntity.addPart(key, jsonBody);
                    }
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
        }
        httpEntity = multipartEntity;
        requestParams.setBodyEntity(httpEntity);
        httpUtils.send(HttpRequest.HttpMethod.POST, url, requestParams,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Toast.makeText(context, responseInfo.result, Toast.LENGTH_SHORT).show();
                        System.out.println(responseInfo.result);
                        message.what = SUCCESS;
                        Bundle bundle = new Bundle();
                        bundle.putString("result", responseInfo.result);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        Toast.makeText(context, "Erroe:"+msg, Toast.LENGTH_SHORT).show();
                        message.what = FAILURE;
                        Bundle bundle = new Bundle();
                        bundle.putString("result", msg);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }
        );
    }

    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }


  /*  //迪华圈
    private HashMap<String,Object> params;
    private HttpPost httpPost;
    private int index;
    private String url;

    public BackgroundTask() {
//		this.context = context;
    }


    public void doInBackGround(String url , HttpPost httpPost, Handler responseHandler, HashMap<String, Object> params){
        String responseBody  = null;
        Message message = new Message();
        org.apache.http.entity.mime.MultipartEntity mpEntity = new org.apache.http.entity.mime.MultipartEntity();
        Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Object> entry=(Map.Entry<String, Object>)it.next();
            String key=(String) entry.getKey();
            Object value=entry.getValue();
            if(key.toLowerCase().contains("headimg")){
                byte[] data = (byte[]) params.get(key);
                String fileName = System.currentTimeMillis()+".jpg";
                ContentBody c1 = new ByteArrayBody(data, fileName);
                mpEntity.addPart(key, c1);
            }else{
                try {
                    if(value != null){
                        StringBody jsonBody = new StringBody((String) value, Charset.forName("UTF-8"));
                        mpEntity.addPart(key, jsonBody);
                    }
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
        }
        httpPost.setEntity(mpEntity);
//        responseHandler.sendStartMessage();
        HttpConnectionParams.setConnectionTimeout(httpPost.getParams(), connectionTimeout);
        //设置读数据超时时间(单位毫秒)
        HttpConnectionParams.setSoTimeout(httpPost.getParams(), soTimeout);
        HttpResponse response = null;
        try {
            response = new DefaultHttpClient().execute(httpPost);
            HttpEntity entity = null;
            HttpEntity temp = response.getEntity();
            if (temp != null) {
                entity = new BufferedHttpEntity(temp);
                responseBody = EntityUtils.toString(entity, "UTF-8");
                message.what = SUCCESS;
                Bundle bundle = new Bundle();
                bundle.putString("result",responseBody);
                message.setData(bundle);
                responseHandler.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.what = FAILURE;
            Bundle bundle = new Bundle();
            bundle.putString("result", e.getMessage());
            message.setData(bundle);
            responseHandler.sendMessage(message);
        }
    }
*/
}
