package com.qkxmall.mall.views.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.adapter.AdsViewPagerAdapter;
import com.qkxmall.mall.define.adapter.cart.CloudAdapter;
import com.qkxmall.mall.define.adapter.cart.HuiAdapter;
import com.qkxmall.mall.define.views.ListViewPager;
import com.qkxmall.mall.define.views.PagerListView;
import com.qkxmall.mall.nets.API;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sunshine on 10/19/2015.
 */
public class CartAsyncTask extends AsyncTask <String ,Integer ,String> {
    Context context;
    ListViewPager listViewPager;
    TextView cloud;
    TextView hui;
    ProgressDialog progressDialog;
    HuiAdapter huiAdapter;
    CloudAdapter cloudAdapter;

    public CartAsyncTask(Context context, ListViewPager listViewPager, TextView cloud, TextView hui, ProgressDialog progressDialog) {
        this.context = context;
        this.listViewPager = listViewPager;
        this.cloud = cloud;
        this.hui = hui;
        this.progressDialog = progressDialog;
    }

    @Override
    protected String doInBackground(String... params) {
        RequestParams requestParams = new RequestParams();
        NameValuePair pairUID = new BasicNameValuePair("uid",params[0]);
        NameValuePair pairPage = new BasicNameValuePair("page",params[1]);
        NameValuePair pairRows = new BasicNameValuePair("rows","6");
        requestParams.addBodyParameter(pairUID);
        requestParams.addBodyParameter(pairPage);
        requestParams.addBodyParameter(pairRows);
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, API.HUI_CART+"&uid="+params[0]+"&page"+params[1]+"&rows=6", requestParams,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        System.out.println("");
                        System.out.println("");
                        System.out.println("Result:"+responseInfo.result);
                        System.out.println("");
                        System.out.println("");
                        List<View> listGuwuche = new ArrayList<>();
                        View viewCloud = LayoutInflater.from(context).inflate(R.layout.pager_gouwucge_layout,null);
                        View viewHui = LayoutInflater.from(context).inflate(R.layout.pager_gouwucge_layout, null);
                        PagerListView cloudBuyList = (PagerListView) viewCloud.findViewById(R.id.pager_item_list);
                        PagerListView huiBuyList = (PagerListView) viewHui.findViewById(R.id.pager_item_list);
                        try {
                            List<HashMap<String ,Object>> listHui = new ArrayList<HashMap<String, Object>>();
                            JSONArray jsonArrayHui = new JSONArray(responseInfo.result);
                            for (int i = 0 ; i < jsonArrayHui.length() ; i++ ){
                                HashMap<String ,Object> hashMapHui = new HashMap<String, Object>();
                                JSONObject object = jsonArrayHui.getJSONObject(i);
                                hashMapHui.put("goodsID",object.get("goods_id"));
                                hashMapHui.put("productsID",object.get("products_id"));
                                hashMapHui.put("buyNumber",object.get("num"));
                                hashMapHui.put("name",object.get("name"));
                                hashMapHui.put("picture",object.get("thumb"));
                                hashMapHui.put("c_price",object.get("c_price"));
                                listHui.add(hashMapHui);
                            }
                            huiAdapter = new HuiAdapter(context, listHui);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //可多选ListView适配器
                        /*
                        * 云购购物车
                        * 惠购购物车
                        * */
//                        cloudBuyList.setAdapter(new CloudAdapter(context, list2));
                        huiBuyList.setAdapter(huiAdapter);
                        cloudBuyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                CheckBox checkBox = (CheckBox) view.findViewById(R.id.select);
                                checkBox.setChecked(false);
                                if (checkBox.isChecked()) {
                                    Toast.makeText(context, position, Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(context, position, Toast.LENGTH_SHORT).show();
                            }
                        });
                        listGuwuche.add(viewCloud);
                        listGuwuche.add(viewHui);
                        listViewPager.setAdapter(new AdsViewPagerAdapter(listGuwuche));


                        //销毁进程等待
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                        Toast.makeText(context,"错误:"+s,Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });


        return null;
    }

}
