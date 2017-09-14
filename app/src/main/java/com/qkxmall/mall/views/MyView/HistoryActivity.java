package com.qkxmall.mall.views.MyView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.R;
import com.qkxmall.mall.define.Task.BackgroundTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.qkxmall.mall.model.LOD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 */
public class HistoryActivity extends Activity{

    private LinearLayout mLinearLayout;
    private  WebView mWebView;
    private String cid;
    private String url;
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_mian);
        init();
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);// 表支持js
        settings.setDefaultTextEncodingName("utf-8");
        settings.setBuiltInZoomControls(true);// 显示放大缩小按钮
        settings.setUseWideViewPort(true);// 支持双击缩放

        //获取期数 数据
        String url="http://www.qkxmall.com/index.php?m=api&c=cloud&a=termlist&cid="+cid+"&order=desc";
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        AllOrderHandler allOrderHandler = new AllOrderHandler(progressDialog);
        BackgroundTask backgroundTask = new BackgroundTask(this,url,allOrderHandler);
        backgroundTask.doInBackground();



    }
    public void init(){
        mLinearLayout= (LinearLayout) findViewById(R.id.histor_ll);
        mWebView= (WebView) findViewById(R.id.webview);
        cid=getIntent().getStringExtra("cid");
        LOD lod = new LOD(this);
        String id= lod.get("USER_INFO", "UID", "'");
        url="http://www.qkxmall.com/index.php?m=qkx&c=apipage&a=kaijiang"+"&cid="+cid+"&tid=";

        mImageView= (ImageView) findViewById(R.id.history_del);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    List<TextView> list=new ArrayList<>();
    public void addText(){

        for (int i=0;i<allOrderList.size();i++){
            TextView tv=new TextView(this);
                   tv.setPadding(30,0,0,0);//
            tv.setText("第" + allOrderList.get(i).get("term").toString() + "期");
            tv.setTag(Integer.parseInt(allOrderList.get(i).get("term").toString()));
            if (i==0){
                tv.setTextColor(Color.RED);
            }
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView tv = (TextView) v;
                    textColor((int) tv.getTag());
                    System.out.println("！！！！！！！！点击了  第几期：" + url + tv.getTag());
                    mWebView.loadUrl(url + tv.getTag());
                }
            });
            mLinearLayout.addView(tv);
            list.add(tv);
        }

        mWebView.loadUrl(url+allOrderList.size());

    }

    private void textColor(int i){
        for (int f=0;f<list.size();f++){
            if ((int)list.get(f).getTag()==i){
                list.get(f).setTextColor(Color.RED);
            }else{
                list.get(f).setTextColor(Color.BLACK);
            }

        }


    }

    List<HashMap<String ,Object>> allOrderList = new ArrayList<>();
    private class AllOrderHandler extends Handler {

        ProgressDialog progressDialog;

        public AllOrderHandler(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case BackgroundTask.SUCCESS:{
                    Bundle bundle = msg.getData();
                    if (bundle != null){
                        try {
                            JSONObject object = new JSONObject(bundle.getString("result"));
                            JSONArray array = object.optJSONArray("data");
                            for (int i = 0 ; i< array.length() ;i++){
                                JSONObject jsonObject = array.optJSONObject(i);
                                HashMap<String ,Object> data = new HashMap<>();
                                data.put("term",jsonObject.optString("term"));//期数
                                data.put("cloud_id",jsonObject.optString("cloud_id"));//云购id
                                allOrderList.add(data);
                            }
                            //拿到数据后动态添加text
                            addText();
                                      System.out.println("!!!!!!!!!!!!!!!! data" + allOrderList.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(HistoryActivity.this, "数据解析失败", Toast.LENGTH_SHORT).show();
                            System.out.println("!!!!!!!!!!!!！！！数据解析失败1"+e);
                        }
                    }
                    progressDialog.dismiss();
                }
                break;
                case BackgroundTask.FAILURE:{
                    progressDialog.dismiss();
                    Toast.makeText(HistoryActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

}
