package com.qkxmall.mall.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.adapter.AdsViewPagerAdapter;
import com.qkxmall.mall.nets.API;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/1/9.
 * 物品详细情况信息
 */
public class GoodsDetailInfoTask extends AsyncTask<String ,Integer ,String> {

    Context context;
    ProgressDialog progressDialog;

    TextView    name;
    ViewPager pic;
    TextView    price;
    TextView    pinglun;
    TextView    have;
    TextView    total;
    TextView    other;
    ProgressBar pro;

    public GoodsDetailInfoTask(Context c , ProgressDialog progressDialog , TextView name , ViewPager pic , TextView price ,
                               TextView pinglun , TextView have , TextView total , TextView other , ProgressBar pro)
    {
        this.context = c;
        this.progressDialog = progressDialog;
        this.name = name;
        this.pic = pic;
        this.pinglun = pinglun;
        this.price = price;
        this.have = have;
        this.total = total;
        this.other = other;
        this.pro = pro;
    }


    @Override
    protected String doInBackground(String... params) {

        RequestParams requestParams = new RequestParams();
        NameValuePair pairUID = new BasicNameValuePair("cid",params[0]);
        requestParams.addBodyParameter(pairUID);

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.POST, API.CLOUD_GOODS_DETAIL_URL, requestParams,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        try {
                            JSONObject jsonObject = new JSONObject(responseInfo.result);
                            name.setText(jsonObject.getJSONObject("data").getJSONObject("cloud").getString("goodsname"));
                            //pic.setImageURI(Uri.parse(API.ADD+jsonObject.getJSONObject("data").getString("img")));
                            pinglun.setText(jsonObject.getJSONObject("data").getJSONObject("cloud").getString("commnum"));
                            price.setText(jsonObject.getJSONObject("data").getJSONObject("cloud").getString("c_totalprice"));
                            have.setText(jsonObject.getJSONObject("data").getJSONObject("cloud").getString("curnum"));
                            total.setText(jsonObject.getJSONObject("data").getJSONObject("cloud").getString("totalnum"));

                            int otherNum = Integer.parseInt(jsonObject.getJSONObject("data").getJSONObject("cloud").getString("totalnum")) -
                                    Integer.parseInt(jsonObject.getJSONObject("data").getJSONObject("cloud").getString("curnum"));
                            other.setText(otherNum + "");
                            //设置进度
                            int proValue = Integer.parseInt(jsonObject.getJSONObject("data").getJSONObject("cloud").getString("curnum")) * 100 /
                                    Integer.parseInt(jsonObject.getJSONObject("data").getJSONObject("cloud").getString("totalnum"));
                            pro.setProgress(proValue);

                            //设置图片
//                            String[] url = new String[]{
//                                    "http://www.qkxmall.com/uploadfile/image/20150926/11111111.jpg",
//                                    "http://www.qkxmall.com/uploadfile/image/20150926/22222222.jpg",
//                                    "http://www.qkxmall.com/uploadfile/image/20150926/33333333.jpg",
//                            };
                            String[] url = jsonObject.getJSONObject("data").getJSONObject("cloud").getString("img").split("\\,");

                            List<HashMap<String ,Object>> list = new ArrayList<>();
                            for (int i = 0 ; i<url.length ;i++){
                                HashMap<String ,Object> map = new HashMap<>();
                                map.put("url", API.ADD + url[i]);
                                list.add(map);
                            }
                            List<View> views = new ArrayList<>();
                            for (int i = 0 ; i<url.length ;i++){
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
