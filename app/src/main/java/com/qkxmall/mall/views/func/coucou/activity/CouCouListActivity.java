package com.qkxmall.mall.views.func.coucou.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.beans.HistoryItem;
import com.qkxmall.mall.define.Task.BackgroundTask;
import com.qkxmall.mall.nets.API;
import com.qkxmall.mall.util.DateUtil;
import com.qkxmall.mall.util.Sha1;
import com.qkxmall.mall.views.func.coucou.adapter.CouCouGuiGeItemAdapter;
import com.qkxmall.mall.views.func.coucou.adapter.CouCouHistoryAdapter;
import com.qkxmall.mall.views.hui.container.HuiDragFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by yuna on 2017/8/7.
 */

public class CouCouListActivity extends Activity {

    private ListView listView;
    private ImageView imgClose;
    private ImageView imgNone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_coucou_history);
        initView();
        getCouCouList();
    }

    private void initView(){
        listView = (ListView)this.findViewById(R.id.listview);
        imgClose = (ImageView)this.findViewById(R.id.navigation);
        imgNone = (ImageView)this.findViewById(R.id.img_none);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getCouCouList(){
        String address = API.COUCOU_LIST+"&access_token="+(new Sha1().getDigestOfString((DateUtil.getCurrentDate()+"tga5yuontrnbuaebq906").getBytes()).toLowerCase());

        Log.e("Address", address);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("凑凑历史...");
        progressDialog.show();
        COUCOULIST loadData = new COUCOULIST(progressDialog);
        BackgroundTask backgroundTask = new BackgroundTask(this, address, loadData);
        backgroundTask.doInBackground();
    }

    private class COUCOULIST extends Handler {
        ProgressDialog progressDialog;

        public COUCOULIST(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BackgroundTask.SUCCESS: {
                    Bundle bundle = msg.getData();
                    if (bundle != null) {
                        try {
                            JSONObject objectList = new JSONObject(bundle.getString("result"));
                            JSONArray jsonArray =  objectList.getJSONArray("rows");
                            ArrayList<HistoryItem> itemList = new ArrayList<>();
                            for(int i=0;i<jsonArray.length(); i++){
                                HistoryItem item = new HistoryItem();
                                JSONObject object = jsonArray.getJSONObject(i);
                                item.setCreater(object.getString("create_user"));
                                item.setStatus(object.getInt("status"));
                                itemList.add(item);
                            }

                            if(itemList.size() ==0){
                                listView.setVisibility(View.GONE);
                                imgNone.setVisibility(View.VISIBLE);
                                return;
                            }

                            listView.setVisibility(View.VISIBLE);
                            imgNone.setVisibility(View.GONE);
                            CouCouHistoryAdapter adapter = new CouCouHistoryAdapter(getApplication(), itemList);
                            listView.setAdapter(adapter);
                            Log.v("jsonarray==",objectList.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplication(), "数据解析失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE: {
                    progressDialog.dismiss();
                    Toast.makeText(getApplication(), "数据加载失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
