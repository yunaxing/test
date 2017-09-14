package com.qkxmall.mall.views.func.coucou.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.beans.ZhangDanItem;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.model.LOD;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.util.DateUtil;
import com.qkxmall.mall.util.Sha1;
import com.qkxmall.mall.views.func.coucou.adapter.ZhangDanMingXiAdapter;
import com.umeng.socialize.utils.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by yuna on 2017/8/20.
 */

public class TiXianActivity  extends Activity {

    private ListView listView;
    private ZhangDanMingXiAdapter adapter;
    private ImageView imgClose;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_zhangdan_mingxi);
        initView();
        initData();
    }

    private void initView(){
        listView = (ListView)this.findViewById(R.id.listview);
        imgClose = (ImageView)this.findViewById(R.id.img_back);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initData(){
        LOD lod = new LOD(this);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("");
        progressDialog.show();

        GetMoney getMoney = new GetMoney(progressDialog);
        String url = API.ZHANGDAN_LIST+"&uid="+lod.get("USER_INFO","UID","")+
        "&access_token="+(new Sha1().getDigestOfString((DateUtil.getCurrentDate()
                +lod.get("USER_INFO","UID","")+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());
        BackgroundTask backgroundTask = new BackgroundTask(getApplication(),url,getMoney);
        backgroundTask.doInBackground();
    }
    private class GetMoney extends Handler {

        ProgressDialog progressDialog;

        public GetMoney(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                            Log.v("jsonObject", jsonObject.toString());
                            JSONArray array = new JSONArray(jsonObject.getJSONArray("rows"));
                            ArrayList<ZhangDanItem> items = new ArrayList<>();
                            for(int i=0; i<array.length(); i++){
                                ZhangDanItem item = new ZhangDanItem();
                                JSONObject object = (JSONObject) array.get(i);
                                item.setAccount(object.getString("account_amount"));
                                item.setDate(object.getString("postdate"));
                                item.setName(object.getString("account_remark"));
                                items.add(item);
                            }
                            adapter = new ZhangDanMingXiAdapter(getApplication(),items);
                            listView.setAdapter(adapter);
//                            tvYUE.setText(jsonObject.getString("user_money"));
//                            tvKaixinDou.setText(jsonObject.getString("kaixindou"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplication(), "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                }
                break;
            }
        }
    }
}