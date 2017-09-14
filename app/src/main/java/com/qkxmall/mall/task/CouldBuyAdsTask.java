package com.qkxmall.mall.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.adapter.AdsViewPagerAdapter;
import com.qkxmall.mall.nets.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/1/9.
 * 云购首页广告
 */
public class CouldBuyAdsTask extends AsyncTask<String ,Integer ,String> {

    Context context;
    ProgressDialog progressDialog;

    ViewPager pic;

    public CouldBuyAdsTask(Context c , ProgressDialog progressDialog , ViewPager pic)
    {
        this.context = c;
        this.progressDialog = progressDialog;
        this.pic = pic;
    }


    @Override
    protected String doInBackground(String... params) {

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, API.CLOUD_BUY_ADS_URL, null,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        try {
                            JSONObject jsonObject = new JSONObject(responseInfo.result);

                            //设置图片
//                            String[] url = new String[]{
//                                    "http://www.qkxmall.com/uploadfile/image/20150926/11111111.jpg",
//                                    "http://www.qkxmall.com/uploadfile/image/20150926/22222222.jpg",
//                                    "http://www.qkxmall.com/uploadfile/image/20150926/33333333.jpg",
//                            };
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            //String[] url = jsonObject.getJSONObject("rows").getString("content").split("\\,");

                            List<HashMap<String ,Object>> list = new ArrayList<>();
                            for (int i = 0 ; i<jsonArray.length() ;i++){

                                HashMap<String ,Object> map = new HashMap<String, Object>();
                                JSONObject object = jsonArray.getJSONObject(i);

                                map.put("url", API.ADD + object.getString("content"));
                                map.put("id" , object.getString("id"));
                                map.put("link" , object.getString("link"));
                                list.add(map);
                            }
                            List<View> views = new ArrayList<>();
                            for (int i = 0 ; i<list.size() ;i++){
                                View view = LayoutInflater.from(context).inflate(R.layout.image_layout, null);
                                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.image_title);
                                WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
                                simpleDraweeView.setMaxWidth(windowManager.getDefaultDisplay().getWidth());
                                simpleDraweeView.setMinimumWidth(windowManager.getDefaultDisplay().getWidth());
                                simpleDraweeView.setMaxHeight(windowManager.getDefaultDisplay().getWidth());
                                simpleDraweeView.setMinimumHeight(windowManager.getDefaultDisplay().getWidth());
                                Uri uri = Uri.parse(String.valueOf(list.get(i).get("url")));
                                simpleDraweeView.setImageURI(uri);
                                views.add(view);
                            }
                            AdsViewPagerAdapter ads = new AdsViewPagerAdapter(views);
                            pic.setAdapter(ads);

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
