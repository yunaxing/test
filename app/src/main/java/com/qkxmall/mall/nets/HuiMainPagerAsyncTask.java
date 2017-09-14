package com.qkxmall.mall.nets;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qkxmall.mall.R;
import com.qkxmall.mall.define.adapter.AdsViewPagerAdapter;
import com.qkxmall.mall.define.adapter.grid.BaseHuiClassItemAdapter;
import com.qkxmall.mall.define.itemClickListeners.MAP1OnItemClickListener;
import com.qkxmall.mall.define.itemClickListeners.MAP2OnItemClickListener;
import com.qkxmall.mall.define.itemClickListeners.MAP3OnItemClickListener;
import com.qkxmall.mall.define.itemClickListeners.MAP4OnItemClickListener;
import com.qkxmall.mall.define.views.GridViewPager;
import com.qkxmall.mall.define.views.GridViewWithScrollView;
import com.qkxmall.mall.views.hui.container.HuiActivity;

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
public class HuiMainPagerAsyncTask extends AsyncTask<String ,Integer ,List<HashMap<String ,Object>> > {
    ProgressDialog progressDialog;
    Context context;
    View pagerMobile;
    GridViewWithScrollView pagerMobileGrid;
    View pagerGehu;
    GridViewWithScrollView  pagerGehuGrid;
    View pagerSmart;
    GridViewWithScrollView pagerSmartGrid;
    View pagerShipin;
    GridViewWithScrollView  pagerShipinGrid;
    View pagerIn;
    GridViewWithScrollView  pagerInGrid;
    GridViewPager viewPagerHuiBuyBottom;

    public HuiMainPagerAsyncTask(ProgressDialog progressDialog,
                                 Context context,
                                 View pagerMobile, GridViewWithScrollView pagerMobileGrid,
                                 View pagerGehu, GridViewWithScrollView pagerGehuGrid,
                                 View pagerSmart, GridViewWithScrollView pagerSmartGrid,
                                 View pagerShipin, GridViewWithScrollView pagerShipinGrid,
                                 View pagerIn, GridViewWithScrollView pagerInGrid,
                                 GridViewPager viewPagerHuiBuyBottom) {
        this.progressDialog = progressDialog;
        this.context = context;
        this.pagerMobile = pagerMobile;
        this.pagerMobileGrid = pagerMobileGrid;
        this.pagerGehu = pagerGehu;
        this.pagerGehuGrid = pagerGehuGrid;
        this.pagerSmart = pagerSmart;
        this.pagerSmartGrid = pagerSmartGrid;
        this.pagerShipin = pagerShipin;
        this.pagerShipinGrid = pagerShipinGrid;
        this.pagerIn = pagerIn;
        this.pagerInGrid = pagerInGrid;
        this.viewPagerHuiBuyBottom = viewPagerHuiBuyBottom;
    }

    @Override
    protected List<HashMap<String ,Object>> doInBackground(String... params) {
        List<HashMap<String ,Object>> listProducts = new ArrayList();
        RequestParams requestParams = new RequestParams();
        NameValuePair pairPage = new BasicNameValuePair("page","1");
        HttpUtils httpUtils = new HttpUtils();
        //实例化 网络请求回调
        RequestCall requestCall = new RequestCall();
        requestCall.init(listProducts,progressDialog);//传入一个List，一个ProgressDialog
        //开始请求网络
        httpUtils.send(HttpRequest.HttpMethod.GET, API.HUI_PRODUCTS_LIST, null, requestCall);
        /*
        * 监听加载窗是否被销毁
        * 当窗体被销毁，开始加载数据
        * */
        progressDialog.setOnDismissListener(new OnDismissListeners(listProducts));
        return listProducts;
    }


    //重写网络请求回调方法
    public class RequestCall extends RequestCallBack<String>{
        List<HashMap<String ,Object>> listProducts = new ArrayList<>();
        ProgressDialog progressDialog;

        public void init(List<HashMap<String ,Object>> listProducts ,ProgressDialog progressDialog){
            this.listProducts = listProducts;
            this.progressDialog = progressDialog;
        }

        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                JSONObject jsonObject = new JSONObject(responseInfo.result);
                JSONArray jsonArray = jsonObject.getJSONArray("rows");
                for (int i = 0 ; i<jsonArray.length() ; i ++){
                    HashMap<String ,Object> productsMap = new HashMap<String, Object>();
                    JSONObject object = jsonArray.getJSONObject(i);
                    productsMap.put("id",object.getString("id"));
                    productsMap.put("name", object.getString("name"));
                    productsMap.put("image", object.getString("thumb"));
                    productsMap.put("kaixindou", object.getString("kaixindou"));
                    productsMap.put("sales_number", object.getString("sales_number"));
                    productsMap.put("commnum", object.getString("commnum"));
                    productsMap.put("goods_number", object.getString("goods_number"));
                    productsMap.put("sho_price", object.getString("sho_price"));
                    productsMap.put("cat_name", object.getString("cat_name"));
                    listProducts.add(productsMap);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //网络请求成功，返回List< >
            progressDialog.dismiss();
        }

        @Override
        public void onFailure(HttpException e, String s) {
            //网络请求失败时，输出错误信息
            progressDialog.dismiss();
        }
    }

    private class OnDismissListeners implements DialogInterface.OnDismissListener {
        List<HashMap<String ,Object>> list = new ArrayList<>();

        public OnDismissListeners(List<HashMap<String, Object>> list) {
            this.list = list;
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            List<View> listGrid = new ArrayList<>();
            pagerMobile = LayoutInflater.from(context).inflate(R.layout.pager_hui_buy_bottom_layout,null);
            pagerMobileGrid = (GridViewWithScrollView) pagerMobile.findViewById(R.id.grid_show);
            pagerMobileGrid.setAdapter(new BaseHuiClassItemAdapter(list,context,false));
            pagerMobileGrid.setOnItemClickListener(new MAP1OnItemClickListener(context));

            pagerGehu = LayoutInflater.from(context).inflate(R.layout.pager_hui_buy_bottom_layout,null);
            pagerGehuGrid = (GridViewWithScrollView) pagerGehu.findViewById(R.id.grid_show);
            pagerGehuGrid.setAdapter(new BaseHuiClassItemAdapter(list,context,false));
            pagerGehuGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, HuiActivity.class);
                    context.startActivity(intent);
                }
            });
            pagerSmart = LayoutInflater.from(context).inflate(R.layout.pager_hui_buy_bottom_layout,null);
            pagerSmartGrid = (GridViewWithScrollView) pagerSmart.findViewById(R.id.grid_show);
            pagerSmartGrid.setAdapter(new BaseHuiClassItemAdapter(list,context,false));
            pagerSmartGrid.setOnItemClickListener(new MAP2OnItemClickListener(context));
            pagerShipin = LayoutInflater.from(context).inflate(R.layout.pager_hui_buy_bottom_layout,null);
            pagerShipinGrid = (GridViewWithScrollView) pagerShipin.findViewById(R.id.grid_show);
            pagerShipinGrid.setAdapter(new BaseHuiClassItemAdapter(list,context,false));
            pagerShipinGrid.setOnItemClickListener(new MAP3OnItemClickListener(context));

            pagerIn = LayoutInflater.from(context).inflate(R.layout.pager_hui_buy_bottom_layout,null);
            pagerInGrid = (GridViewWithScrollView) pagerIn.findViewById(R.id.grid_show);
            pagerInGrid.setAdapter(new BaseHuiClassItemAdapter(list,context,false));
            pagerInGrid.setOnItemClickListener(new MAP4OnItemClickListener(context));

            listGrid.add(pagerMobile);
            listGrid.add(pagerGehu);
            listGrid.add(pagerSmart);
            listGrid.add(pagerShipin);
            listGrid.add(pagerIn);
            viewPagerHuiBuyBottom.setAdapter(new AdsViewPagerAdapter(listGrid));
            viewPagerHuiBuyBottom.setCurrentItem(0);
        }
    }
}
